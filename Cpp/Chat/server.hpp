/**
 * @file File contains code for the server.
 * @author barn
 * @version 20131015
 *
 * TODO everything
 * doc
 */
#pragma once

#include "session.hpp"

#include <boost/bind.hpp>
#include <boost/asio.hpp>


using boost::asio::ip::tcp;


/// Class server
class chat_server
{
public: // constructor

    /**
     * Main constructor
     * @param io_service The boost io_service that handles all underlying communication stuff.
     * @param port The port number.
     * @param password The key necessary to encrypt/decrypt the messages.
     */
    chat_server(boost::asio::io_service& io_service, short port, const std::string& password)
        :
        io_service_(io_service),
        acceptor_(io_service, tcp::endpoint(tcp::v4(), port)),
        password_(password)
    {
        do_accept();
    }

private: // methods

    /**
     * Accepts incoming tcp requests.
     */
    void do_accept()
    {
        chat_session* session = new chat_session(io_service_, password_);
        acceptor_.async_accept(
            session->socket(),
            boost::bind(
                &chat_server::on_accept,
                this,
                session,
                boost::asio::placeholders::error
            )
        );
    }

private: // callbacks

    /**
     * Handles incoming accept requests.
     * Starts a new session if the error code if no error occured.
     * Otherwise deletes the given session.
     * @param new_session The new session to be established.
     * @param error The error code that will be used for checking the validity of the new session.
     */
    void on_accept(chat_session* new_session, const boost::system::error_code& error_code)
    {
        if (!error_code)
            new_session->start();
        else
            delete new_session;

        do_accept();
    }

private: // members

    /// The acceptor_
    tcp::acceptor acceptor_;

    /// The io_service
    boost::asio::io_service& io_service_;

    /// The password to encrypt/decrypt messages
    const std::string& password_;

}; // END CLASS server
