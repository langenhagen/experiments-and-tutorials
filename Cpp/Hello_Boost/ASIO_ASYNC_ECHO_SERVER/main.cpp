#include "Server.hpp"

#include <iostream>

#include <boost/asio.hpp>


void asyncServer();

void main()
{
    asyncServer();
}


void asyncServer()
{
    try
    {
        boost::asio::io_service ios;
        AServer server(ios);
        ios.run();
    }
    catch(std::exception& e)
    {
        std::cout << "Exception: " << e.what() << std::endl;
    }
}