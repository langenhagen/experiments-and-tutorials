/**
 * @file File contains class Chat, which is either a server or a client.
 * @author barn
 * @version 20131102
 */
#pragma once

#include <sstream>

#include <boost/bind.hpp>
#include <boost/asio.hpp>

#include <rlutil/rlutil.h>

using boost::asio::ip::tcp;
namespace asio = boost::asio;


/// Convenience macro that help saving LoC when logging.
/// Just handle some string as parameter, concatenate with stream out operator << .
#define CHAT_PRINT_INFO(MSG) std::ostringstream oss; \
                             oss << MSG; \
                             printInfo( oss.str(), 1, rlutil::trows());


/// Class Chat
class Chat
{
private: // members

    /// This enum stores the maximum message length.
    enum { max_message_length = 1024 };

    /// Positions for printing incoming messages.
    enum
    {
        read_col = 5, 
        read_row = 10
    };

    /// Positions for printing outgoing messages.
    enum
    {
        write_col = 5, 
        write_row = 15
    };

    /// The acceptor.
    tcp::acceptor _acceptor;

    /// The io_service.
    asio::io_service& _io_service;
    
    /// The socket.
    tcp::socket _socket;

    /// The password to encrypt/decrypt messages.
    const std::string _password;

    /// The outgoing message.
    std::string _out_message;

    /// The freshly received message.
    char _in_message[max_message_length];

public: // constructor

    /**
     * Server constructor.
     * @param io_service The boost io_service that handles all underlying communication stuff.
     * @param port The port number.
     * @param read_or_write_mode Specifies whether this object is in read ('r') or write ('w') mode.
     * @param password The key necessary to encrypt/decrypt the messages.
     */
    Chat( const bool is_server_mode,
          asio::io_service& io_service,
          short port,
          const char read_or_write_mode,
          const std::string& password)
        :
        _io_service(io_service),
        _socket(io_service),
        _acceptor(io_service, tcp::endpoint(tcp::v4(), port)),
        _password(password)
    {
        // Accept one (and only one) client
        _acceptor.async_accept( _socket,
                                boost::bind( &Chat::onConnect,
                                             this,
                                             read_or_write_mode,
                                             asio::placeholders::error));
    }


   /**
    * Constructor.
    * @param is_server_mode Specifies, if Chat objects shall be started 
    * in server mode or in client mode.
    * TRUE  - start as server
    * FALSE - start as client
    * @param io_service The boost io_service that handles all underlying communication stuff.
    * @param host The host IP.
    * @param port The port number.
    * @param read_or_write_mode Specifies whether this object is in read ('r') or write ('w') mode.
    * @param password The key necessary to encrypt/decrypt the messages.
    */
    Chat( const bool is_server_mode,
          asio::io_service& io_service, 
          const std::string& host, 
          const short port, 
          const char read_or_write_mode,
          const std::string& password)
        :
        _io_service(io_service),
        _socket(io_service),
        _acceptor(io_service, tcp::endpoint(tcp::v4(), port)),
        _password(password)
    {

        if( is_server_mode)
        {
            // Accept one (and only one) client
            _acceptor.async_accept( _socket,
                                    boost::bind( &Chat::onConnect,
                                                 this,
                                                 read_or_write_mode,
                                                 asio::placeholders::error));
        }
        else
        {
            tcp::endpoint endpoint( asio::ip::address::from_string(host), port);

            _socket.async_connect( endpoint,
			                       boost::bind( &Chat::onConnect,
                                                this,
                                                read_or_write_mode,
                                                asio::placeholders::error));
        }
    }

public: // methods

    /**
     * Calls the private doRead() function.
     */
    void read()
    {
        _socket.async_read_some( asio::buffer( _in_message, max_message_length),
                                 boost::bind( &Chat::onRead,
                                              this,
                                              asio::placeholders::error,
                                              asio::placeholders::bytes_transferred));
    }

    /**
     * Activates the write mode. Does a getline operation and calls doWrite
     * with the string received by getline.
     */
    void write()
    {
        if( _socket.is_open() == false)
        {
            CHAT_PRINT_INFO( "doWrite: Socket closed.")
        }
        else
        {
            _out_message = captureAndPrintLine(write_col, write_row);
            if( _socket.is_open())
            {
                asio::async_write( _socket, 
                               asio::buffer( _out_message, _out_message.size()),
                               boost::bind( &Chat::onWrite,
                                            this,
                                            asio::placeholders::error,
                                            asio::placeholders::bytes_transferred));
            }
            
        }
    }

private: // helpers

    /**
     * Captures and prints the at the specified position in the console input 
     * until ENTER is pressed or the maximum string buffer size is reached. 
     * DEL deletes the last character.
     * param col The column on which to position the cursor when writing the strnig.
     * param row The row on which to position he cursor when writing the string.
     * @return The string that is captured until ENTER is pressed 
     * or the maximum string buffer size is reached.
     */
    std::string captureAndPrintLine(unsigned int col, unsigned int row)
    {
        char ret[max_message_length]("\0");
        unsigned int cursor(0);

        rlutil::locate(col,row);

        // non-blocking getch
        int kbval ( rlutil::nb_getch());

        while( 13!=kbval )
        {
            // until ENTER is pressed

            if(0==kbval)
            {
                // NOTHING
            }
            else if( 8==kbval && cursor > 0)
            {
                // DEL
                ret[--cursor] = '\0';

                rlutil::locate(col,row);
                
                printf(ret);
                printf(" ");
                rlutil::locate(col+cursor,row);
            }
            else if( 27== kbval)
            {
                // ESC
                rlutil::cls();
                exit(0); // TODO u can do that better
            }
            else if(cursor < max_message_length)
            {
                // ORDINARY CHARACTERS
                
                rlutil::locate(col+cursor,row);
                ret[cursor] = unsigned char(kbval);
                
                printf( &ret[cursor++]);
            }
            
            kbval = rlutil::nb_getch();
        }
        
        rlutil::locate( col, row);
        printf( std::string(cursor, ' ').c_str());

        return std::string(ret);
    }


    /**
     * Prints an error message with the given error and a string that specifies a user given information.
     * @param error_code The original error code.
     * @param error_in An additional information string 
     * that precedes the other information given by the error code.
     * @param exit_code The exit code that shall be thrown by the application.
     */
    void printErrorMessage( const boost::system::error_code& error_code, const std::string& error_in, int exit_code)
    {
        rlutil::cls();
        rlutil::locate( 1, rlutil::trows()/2);
        std::cerr << "ERROR: " << error_in << ":\n"
                     "   Error No:  " << error_code.value() << "\n" 
                     "   Error Msg: " << error_code.message() << "\n";
    }


    /**
     * Prints an info message at the lower end of the console window.
     * @param sring The string to be printed.
     * @col The 1 based number of the column at which to print.
     * @row The 1 based number of the row at which to print.
     */
    void printInfo( std::string& string, unsigned int col, unsigned int row)
    {
        string.append( std::string(rlutil::tcols()-string.length(), ' '));
        rlutil::locate( 1, rlutil::trows());
        printf( string.c_str());
    }


private: // callbacks


    /**
     * Handles a new connection.
     * @param read_or_write_mode Specifies whether this object is in read ('r') or write ('w') mode.
     * Param shall be either 'w' or 'r' otherwise an error occurs.
     * @param error_code The boost error code.
     */
    void onConnect( const char read_or_write_mode, const boost::system::error_code& error_code)
    {
        if (error_code)
        {
            printErrorMessage(error_code, "onConnect", 1);
        }
        else
        {
            if( 'r'==read_or_write_mode)
            {
                read();
            }
            else if( 'w'==read_or_write_mode)
            {
                CHAT_PRINT_INFO( "ready");
                write();
            }
            else
            {
                CHAT_PRINT_INFO( "ERROR: onConnect: mode " << read_or_write_mode << " is unknown.");
            }
        }
    }


    /**
     * Handles the completion of a read message, meaning starts a new reading process
     * for continuous reading.
     * @param error_code The error code that is handed to this handler.
     * @param bytes The number of bytes transferred.
     */
    void onRead( const boost::system::error_code& error_code, size_t bytes)
    {
        if( error_code == asio::error::eof)
        {
            printErrorMessage(error_code, "onRead: disconnected", 2);
        }
        else if (error_code)
        {
            printErrorMessage(error_code, "onRead", 3);
        }
        else
        {
            std::string message(_in_message, bytes);
            printInfo( message, read_col, read_row);
            
            read();
        }
    }


    /**
     * Handles the completion of a write message.
     * @param error_code The error code that is handed to this handler.
     * @param bytes The number of bytes transferred.
     */
    void onWrite( const boost::system::error_code& error_code, size_t bytes)
    {
        if (error_code)
        {
            printErrorMessage(error_code, "onWrite", 4);
        }
        else
        {
            CHAT_PRINT_INFO( "onWrite (" << bytes << " bytes transferred)")
            
            write();
        }
    }

}; // END CLASS Chat