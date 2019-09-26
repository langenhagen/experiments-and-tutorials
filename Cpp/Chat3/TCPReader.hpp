/**
 * @file File contains class TCPReader, which is either a server or a client.
 * @author barn
 * @version 20131105
 */
#pragma once

#include "chat_common.h"

/// Class TCPReader
class TCPReader
{
private: // members
   
    /// Positions for printing incoming messages.
    enum { col = 5, row = 10 };
   
    /// The incoming message.
    char _message[max_message_length];

    /// The acceptor.
    tcp::acceptor _acceptor;

    /// The io_service.
    asio::io_service& _io_service;
    
    /// The socket.
    tcp::socket _socket;

    /// The password to encrypt/decrypt messages.
    const std::string _password;

public: // constructor

   /**
    * Constructor.
    * @param is_server_mode Specifies, whether the Reader is to be used in server or client mode.
    * TRUE  - server mode
    * FALSE - client mode
    * @param io_service The boost io_service that handles all underlying communication stuff.
    * @param host The host IP.
    * @param port The port number.
    * @param password The key necessary to encrypt/decrypt the messages.
    */
    TCPReader( const bool is_server_mode,
               asio::io_service& io_service, 
               const std::string& host, 
               const short port, 
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
                                    boost::bind( &TCPReader::onConnect,
                                                 this,
                                                 asio::placeholders::error));
        }
        else
        {
            tcp::endpoint endpoint( asio::ip::address::from_string(host), port);

            _socket.async_connect( endpoint,
			                       boost::bind( &TCPReader::onConnect,
                                                this,
                                                asio::placeholders::error));
        }
    }

public: // methods

    /**
     * Reads from the socket.
     */
    void read()
    {
        _socket.async_read_some( asio::buffer( _message, max_message_length),
                                 boost::bind( &TCPReader::onRead,
                                              this,
                                              asio::placeholders::error,
                                              asio::placeholders::bytes_transferred));
    }

private: // callbacks

    /**
     * Handles a new connection.
     * @param read_or_write_mode Specifies whether this object is in read ('r') or write ('w') mode.
     * Param shall be either 'w' or 'r' otherwise an error occurs.
     * @param error_code The boost error code.
     */
    void onConnect( const boost::system::error_code& error_code)
    {
        if (error_code)
            printErrorMessage(error_code, "onConnect", 1);
        else
            read();
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
            std::string message(_message, bytes);
            printInfo( message, col, row);
            
            read();
        }
    }

}; // END CLASS TCPReader