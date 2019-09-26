#pragma once


#include "session.hpp"

#include <boost/asio.hpp>

 
using boost::asio::ip::tcp;
 
class AServer
{
private: // vars

    boost::asio::io_service& ios_;
    tcp::acceptor acp_;

public:
    
    AServer(boost::asio::io_service& ios)
        :
    ios_(ios),
    acp_(ios, tcp::endpoint(tcp::v4(), ECHO_PORT))
    {
        createSession();
    }

    void handleAccept(ASession* session, const boost::system::error_code& error)
    {
        if(!error)
        {
            session->start();
            createSession();
        }
        else
        {
            delete session;
        }
    }

private:
    
    void createSession()
    {
        ASession* session = new ASession(ios_);
 
        acp_.async_accept( session->socket(),
                           boost::bind( &AServer::handleAccept,
                                        this,
                                        session,
                                        boost::asio::placeholders::error));
        
        std::cout << "A new session is available to clients" << std::endl;
    }
};