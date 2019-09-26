#pragma once


#include <boost/asio.hpp>


using boost::asio::ip::tcp;

class AClient
{
private: // vars

    boost::asio::io_service _io_service;
    tcp::socket _socket;

public: // constructor & destructor

    AClient( s
}