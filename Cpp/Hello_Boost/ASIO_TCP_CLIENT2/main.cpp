//
// blocking_tcp_echo_client.cpp
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
// Copyright (c) 2003-2008 Christopher M. Kohlhoff (chris at kohlhoff dot com)
//
// Distributed under the Boost Software License, Version 1.0. (See accompanying
// file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
//

#include <cstdlib>
#include <cstring>
#include <iostream>
#include <boost/asio.hpp>

using boost::asio::ip::tcp;

//enum { max_length = 1024 }; // TODO find out y!
const int max_length = 1024;


using namespace std; // For strlen.

int main(int argc, char* argv[])
{
	try
	{
		boost::asio::io_service io_service;

		tcp::resolver resolver(io_service);
		tcp::resolver::query query(tcp::v4(), "127.0.0.1", "6666");
		tcp::resolver::iterator iterator = resolver.resolve(query);

		tcp::socket socket(io_service);
		socket.connect(*iterator);

		cout << "Client up and running\n";

		while(1)
		{
			cout << "Client: ";
			char request[max_length];

			
			cin.getline(request, max_length);
		
			size_t request_length = strlen(request);
			boost::asio::write(socket, boost::asio::buffer(request, request_length));

			char reply[max_length];
			
			size_t reply_length = socket.read_some(boost::asio::buffer(reply, max_length));
			cout << "Server: ";
			std::cout.write(reply, reply_length);
			cout << "\n";
		}

	}
	catch (std::exception& e)
	{
		std::cerr << "Exception: " << e.what() << "\n";
	}

	return 0;
}