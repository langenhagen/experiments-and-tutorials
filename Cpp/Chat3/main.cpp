/*****************************************************************************
 >>> This is Chat
 It is a verrry simple chat client that sends simple chat messages
 between two peers.

 using
    - Boost
    - rlutil

TODO:
    -encryption/decryption with salt
    -thread synchronisation for not mixing up different printed strings (doesnt really happen anymore but is possible)
    -automatic server mode ? (n)(n)( )
    -cleanup

DESIGN GOALS:
    -super cross platform
    -super fast
    -super simple
    -super small memory footprint

@file Main file of Chat project.
@author barn
@version 20131102

******************************************************************************/
#include "TCPReader.hpp"
#include "TCPWriter.hpp"

#include <boost/thread.hpp>

#include <rlutil/rlutil.h>

using namespace std;


///////////////////////////////////////////////////////////////////////////////
// CONSTANTS

const unsigned short std_port1(6666);
const unsigned short std_port2(9999);
const string std_password("GURKEGURKEGURKE");


/// error codes for return statements
const unsigned int argument_error_code (1);
const unsigned int start_error_code    (2);


///////////////////////////////////////////////////////////////////////////////
// FUNCTION HEADERS
int start_chat( const bool is_server_mode,
                const string& host,
                const unsigned short read_port,
                const unsigned short write_port, 
                const string& password);

const string usage_description();
int on_command_line_param_error();


///////////////////////////////////////////////////////////////////////////////
// FUNCTION IMPLEMENTATIONS

/// main function
int main(int argc, char** argv)
{
    int ret(0);
    rlutil::cls();
    rlutil::hidecursor();
    cout << "***********************\n"
         << "*** Welcome to Chat ***\n"
         << "***********************\n"
            "\n";

    CHAT_PRINT_INFO( "version 20131105");

    // CHECK FOR COMMAND LINE ARGUMENTS
    if( argc >= 2 && 0==stricmp(argv[1],"s"))
    {
        // SERVER mode
        
        const unsigned short read_port  (argc >= 3 ? atoi( argv[2]) : std_port1); 
        const unsigned short write_port (argc >= 4 ? atoi( argv[3]) : std_port2);
        const string password           (argc >= 5 ? argv[4] : std_password);


        if( 0!=read_port && 0!=write_port)
        {
            ret = start_chat( true, "", read_port, write_port, password);
        }
        else
        {
            ret = on_command_line_param_error();
        }
    }
    else if( argc >= 3 && 0==stricmp(argv[1],"c"))
    {
        // CLIENT MODE

        const string host               (argv[2]);
        const unsigned short read_port  (argc >= 4 ? atoi( argv[3]) : std_port2); // !
        const unsigned short write_port (argc >= 5 ? atoi( argv[4]) : std_port1);
        const string password           (argc >= 6 ? argv[5] : std_password);


        if( 0!=read_port && 0!=write_port)
        {
            ret = start_chat( false, host, read_port, write_port, password);
        }
        else
        {
            ret = on_command_line_param_error();
        }
    }
    else
    {
        // ERROR Case
        ret = on_command_line_param_error();
    }


#ifdef _DEBUG
    cout << "\n"
            "Press any key to exit";
    rlutil::anykey();
#endif

    rlutil::showcursor();
    rlutil::cls();
    return ret;
}


/**
 * Prints the usage description, waits for any key to be pressed 
 * and then returns an integer which represents the error code
 * for wrong command line parameters.
 * @return Returns the integer 1
 */
int on_command_line_param_error()
{
    cout <<  "USAGE:\n"
         <<  usage_description()
         << "Press any Key to exit.\n";

    rlutil::anykey();

    return argument_error_code;
}


/** 
 * Retrieves the usage description of the Chat executable.
 * @return The usage description of Chat.
 */
const string usage_description()
{
    ostringstream oss;
    oss << "You may use Chat in two different ways: As Server or as a Client.\n\n"
           "chat.exe s [<read_port>] [<write_port>] [<password>]            (Server mode)\n"
           "chat.exe c <host> [<read_port>] [<write_port>] [<password>]     (Client mode)\n"
           "\n"
           "For example you could start Chat as a client with\n"
           "\n"
           "      chat.exe c 127.0.0.1\n"
           "\n"
           "which connects to server at 127.0.0.1 at the Chat standard ports " << std_port1 << " and " << std_port2 << "\n"
           "and the Chat standard password " << std_password << ".\n"
           "\n";

    return oss.str();
}


/**
 * Starts Chat.
 * @param is_server_mode Specifies, if Chat objects shall be started 
 * in server mode or in client mode.
 * TRUE  - start as server
 * FALSE - start as client
 * @param host The host with which to connect.
 * @param read_port The port on which to read for incoming messages.
 * @param write_port The port in which to send outgoing messages.
 * @param password The password to be used for decryption/encryption.
 * @return An error code.
 *         0: no error
 */
int start_chat( const bool is_server_mode,
                 const string& host,
                 const unsigned short read_port,
                 const unsigned short write_port, 
                 const string& password)
{
    int ret(0);

    try
    {
        boost::asio::io_service io_service1;
        boost::asio::io_service io_service2;

        TCPReader reader( is_server_mode, io_service1, host, read_port,  password);
        TCPWriter writer( is_server_mode, io_service2, host, write_port, password);

        boost::thread io_service2_thread( boost::bind(&boost::asio::io_service::run, &io_service2));
        io_service1.run();

        cout << "Connection closed.\n";
    }
    catch (exception& e)
    {
        cerr << "Exception in start_chat:\n"
                  << e.what() << "\n";

        ret = start_error_code;
    }

    return ret;
}