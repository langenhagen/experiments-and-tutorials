#include "server.hpp"

#include <iostream>

#include <boost/bind.hpp>
#include <boost/smart_ptr.hpp>
#include <boost/asio.hpp>
#include <boost/thread.hpp>

#include <rlutil.h>

/*****************************************************************************
 >>> This is Chat
 It is a verrry simple chat client that sends simple chat messages
 between two peers.

 using
    - Boost
    - rlutil

TODO:
    -everything
        - exception handling (not schmiering ab if not necessary)
        - asynchronous communication?
    -make group chats possible
    -encryption/decryption with salt
    -cleanup

DESIGN GOALS:
    -cross platform
    -super fast
    -super simple
    -super small memory footprint

@file Main file of Chat project.
@author barn
@version 20131016

******************************************************************************/

using namespace std;


//typedef boost::shared_ptr<boost::asio::ip::tcp::socket> socket_ptr;


const short std_port(6666);
const string std_password("GURKEGURKEGURKE");
const unsigned int max_msg_length(1024);


int server_mode( const short port= std_port, const string& password= std_password);
int client_mode( const char* host, const short port= std_port, const string& password= std_password);


const std::string usage_description();

/// main function
void main(int argc, char** argv)
{
	rlutil::cls();
    cout << "***********************" << endl
         << "*** Welcome to Chat ***" << endl 
         << "***********************" << endl
         << endl;


    // According to the number of input parameters, Chat will treat them differently 
    // and will itself behave differently.
    
    if( argc == 1)                  // SERVER mode
    {
        cout    <<  "USAGE:\n"
                <<  usage_description();

        server_mode();    
    }
    else if( argc == 2)             // CLIENT mode
    {
        client_mode( argv[1]);
    }
    else if( argc == 3)             // SERVER mode
    {
        server_mode( std::atoi(argv[1]), argv[2]);
        // TODO atoi error handling
    }
    else if( argc <= 4)             // CLIENT mode
    {
        client_mode( argv[1], std::atoi(argv[2]), argv[3]);
        // TODO atoi error handling
    }
    else                            // ERROR CASE
    {
        std::cerr   <<  "Error: Number of parameters cannot be handled\n"
                        "Use Chat as follows:\n"
                    <<  usage_description() << "\n"
                        "*** Press any key to exit *** ";
    
        rlutil::anykey();
    }


    rlutil::anykey(); // TODO remove
    return;
}


/**
 * Activates the server mode.
 * @param port The port on which connect.
 * @param password The password to be used for decryption/encryption.
 * @return An error code.
 *         0: no error
 */
int server_mode( const short port, const string& password)
{  
    int ret(0);
 
    try
    {
        boost::asio::io_service io_service;

        chat_server server( io_service, port, password);

        std::cout << "Server starting up..."
                     "\n";

        io_service.run();
    }
    catch (std::exception& e)
    {
        std::cerr << "Exception in server_mode:\n" 
                  << e.what() 
                  << "\n";
        ret = 1;
    }

    return ret;
}



/**
 * Activates the client mode.
 * @param port The port on which connect.
 * @param password The password to be used for decryption/encryption.
 * @return An error code.
 *         0: no error
 */
int client_mode( const char* host, const short port, const string& password)
{
    int ret(0);

    try
    {
        boost::asio::io_service io_service;
        
        std::ostringstream port_oss;
        port_oss << port;

        tcp::resolver resolver(io_service);
        tcp::resolver::query query(tcp::v4(), host, port_oss.str().c_str());
        tcp::resolver::iterator iterator = resolver.resolve(query);

        tcp::socket socket(io_service);
        boost::asio::connect(socket, iterator);

        // ** CONNECTED ***

    }
    catch (std::exception& e)
    {
        std::cerr   << "Exception in client_mode:\n"
                    << e.what() 
                    << "\n";
        ret = 1;
    }
    
    return ret;
}


/**
 * Retrieves the usage description string.
 * @return A constant string.
 */
const std::string usage_description()
{
    std::ostringstream oss;
    oss <<  "chat.exe                                (Server mode)\n"
            "chat.exe <host>                         (Client mode)\n"
            "chat.exe <port> <password>              (Server mode)\n"
            "chat.exe <host> <port> <password>       (Client mode)\n"
            "\n"
            "For example you could start Chat as a client with\n"
            "\n"
            "      chat.exe 127.0.0.1\n"
            "\n"
            "which connects to server at 127.0.0.1 at the Chat standard port " << std_port << "\n"
            "and the Chat standard password " << std_password << "\n"
            "\n";

    return oss.str();
}