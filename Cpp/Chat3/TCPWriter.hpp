/**
 * @file File contains class TCPWriter, which is either a server or a client.
 * @author barn
 * @version 20131105
 */
#pragma once

#include "chat_common.h"

/// Class TCPWriter
class TCPWriter
{
private: // members
   
    /// Positions for printing outgoing messages.
    enum { col = 5, row = 15 };

    /// The outgoing message.
    std::string _out_message;

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
    TCPWriter( const bool is_server_mode,
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
                                    boost::bind( &TCPWriter::onConnect,
                                                 this,
                                                 asio::placeholders::error));
        }
        else
        {
            tcp::endpoint endpoint( asio::ip::address::from_string(host), port);

            _socket.async_connect( endpoint,
			                       boost::bind( &TCPWriter::onConnect,
                                                this,
                                                asio::placeholders::error));
        }
    }


public: // methods

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
            _out_message = captureAndPrintLine(col, row);
            if( _socket.is_open())
            {
                asio::async_write( _socket, 
                               asio::buffer( _out_message, _out_message.size()),
                               boost::bind( &TCPWriter::onWrite,
                                            this,
                                            asio::placeholders::error,
                                            asio::placeholders::bytes_transferred));
            }
        }
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
            write();
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

}; // END CLASS TCPWriter