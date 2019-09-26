//
// blocking_tcp_echo_server.cpp
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
// Copyright (c) 2003-2008 Christopher M. Kohlhoff (chris at kohlhoff dot com)
//
// Distributed under the Boost Software License, Version 1.0. (See accompanying
// file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
//

#include <cstdlib>
#include <iostream>
#include <boost/bind.hpp>
#include <boost/smart_ptr.hpp>
#include <boost/asio.hpp>
#include <boost/thread.hpp>

using boost::asio::ip::tcp;
using namespace std; // For atoi.

const int max_length = 1024;

typedef boost::shared_ptr<tcp::socket> socket_ptr;


void send_session( socket_ptr socket)
{
	try
	{
		while(1)
		{
			char data[max_length];

			boost::system::error_code error_code;
			size_t length = socket->read_some(boost::asio::buffer(data), error_code);
			if (error_code == boost::asio::error::eof)
				break; // Connection closed cleanly by peer.
			else if (error_code)
			{
				cerr << "error code: " << error_code << endl;
				throw boost::system::system_error(error_code); // Some other error.
			}


			std::string data_str(string(data).substr(0, length));

			cout << "Client: " << data_str << "\nServer: ";

			string answer;
			cin >> answer;

			socket->write_some( boost::asio::buffer( answer, answer.length()) );
		}
	}
	catch (std::exception& e)
	{
		std::cerr << "Exception in thread: " << e.what() << "\n";
		exit(0);
	}
}

void session(socket_ptr socket)
{
	try
	{
		while(1)
		{
			char data[max_length];

			boost::system::error_code error_code;
			size_t length = socket->read_some(boost::asio::buffer(data), error_code);
			if (error_code == boost::asio::error::eof)
				break; // Connection closed cleanly by peer.
			else if (error_code)
			{
				cerr << "error code: " << error_code << endl;
				throw boost::system::system_error(error_code); // Some other error.
			}


			std::string data_str(string(data).substr(0, length));

			cout << "Client: " << data_str << "\nServer: ";

			string answer;
			cin >> answer;

			socket->write_some( boost::asio::buffer( answer) );
		}
	}
	catch (std::exception& e)
	{
		std::cerr << "Exception in thread: " << e.what() << "\n";
		exit(0);
	}
}

void server(boost::asio::io_service& io_service, short port)
{
	tcp::acceptor acceptor(io_service, tcp::endpoint(tcp::v4(), port));
  
	cout << "Server up and running\n";	

	while(1)
	{
		socket_ptr socket(new tcp::socket(io_service));
		acceptor.accept(*socket);
		//boost::thread thread(boost::bind(send_session, socket));
		boost::thread thread(boost::bind(session, socket));
	}
}

int main(int argc, char* argv[])
{
	try
	{
		boost::asio::io_service io_service;


		server(io_service, 6666);
	}
	catch (std::exception& e)
	{
		std::cerr << "Exception: " << e.what() << "\n";
	}

	return 0;
}