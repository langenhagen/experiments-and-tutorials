#include <cassert>

#include <iostream>

#include <boost/asio.hpp>
#include <boost/bind.hpp>

const char* ServerIP = "127.0.0.1";
const unsigned short ServerPort = 50013;

namespace
{
	namespace asio	= boost::asio;
	namespace sys	= boost::system;
}

class TcpClient
{
public:
	TcpClient(asio::io_service& _ioService) :
		ioService(_ioService),
		socket(_ioService),
		seqNumber(0)
	{}

	void connect(asio::ip::tcp::endpoint& _endpoint)
	{
		socket.async_connect(_endpoint,
			boost::bind(&TcpClient::handle_connect, this, asio::placeholders::error));
	}

private:
	void postWrite()
	{
		if (socket.is_open() == false)
		{
			return;
		}

		if (seqNumber > 7)
		{
			socket.close();
			return;
		}

		++seqNumber;

		char message[128] = {0,};
		sprintf_s(message, "%d - sended", seqNumber);

		writeMessage = message;

		asio::async_write(socket, asio::buffer(writeMessage),
			boost::bind(
				&TcpClient::handle_write,
				this,
				asio::placeholders::error,
				asio::placeholders::bytes_transferred)
			);

		postReceive();
	}


	void postReceive()
	{
		memset(&recvBuffer, 0, sizeof(recvBuffer));

		socket.async_read_some(asio::buffer(recvBuffer),
			boost::bind(
				&TcpClient::handle_receive,
				this,
				asio::placeholders::error,
				asio::placeholders::bytes_transferred)
				);
	}

	void handle_connect(const sys::error_code& _error)
	{
		if (_error)
		{
			std::cout << "connection failed: " << _error.message() << std::endl;
		}

		else
		{
			std::cout << " connected " << std::endl;
			postWrite();
		}
	}

	void handle_write(const sys::error_code& _error, size_t _bytes){}

	void handle_receive(const sys::error_code& _error, size_t _bytes)
	{
		if (_error)
		{
			if (_error == asio::error::eof)
			{
				std::cout << "disconnected"<< std::endl;
			}
			else
			{
				std::cout << "error no:" << _error.value() <<
					" error message : " << _error.message() << std::endl;
			}
			return;
		}

		const std::string recvMessage = recvBuffer.data();

		std::cout << "received message : " << recvMessage <<
			", size : " << _bytes << std::endl;

		postWrite();
	}
	

	asio::io_service&		ioService;
	asio::ip::tcp::socket	socket;
	int						seqNumber;

	std::array<char, 128>	recvBuffer;
	std::string				writeMessage;
};

int main()
{
	asio::io_service ioService;

	asio::ip::tcp::endpoint endpoint(
		asio::ip::address::from_string(ServerIP), ServerPort);


	TcpClient client(ioService);
	client.connect(endpoint);

	ioService.run();

	std::cout << "connection closed " << std::endl;

	getchar();

	return 0;
}