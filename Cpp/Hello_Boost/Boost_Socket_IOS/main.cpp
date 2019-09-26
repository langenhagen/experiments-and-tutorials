#include <iostream>
#include <boost/asio.hpp>

using namespace std;
using namespace boost::asio;

void main()
{

	/* SIMPLE INIT
	
	ip::tcp::iostream stream("www.boost.org", "http");
	if (!stream)
		cout << "Can't connect.\n";
	else
		cout << "Connected.\n";

	cout << endl << "*** END OF STAGE 1    PRESS ANY KEY TO GET ON ***\n";
	cin.sync();
	cin.get();
	*/

	ip::tcp::iostream stream;
	stream.expires_from_now(boost::posix_time::seconds(60));
	stream.connect("www.boost.org", "http");
	if (!stream)
	{
		cout << "Can't connect. Press any Key to Exit\n";
		cin.sync();
		cin.get();
		exit(1);
	}
	
	cout << "*** Connected. ***\n\n";
	
	stream << "GET /LICENSE_1_0.txt HTTP/1.0\r\n";
	stream << "Host: www.boost.org\r\n";
	stream << "Accept: */*\r\n";
	stream << "Connection: close\r\n\r\n";
	stream.flush();
	std::cout << stream.rdbuf();

	cout << endl << "*** END OF PROGRAM    PRESS ANY KEY TO EXIT***\n";
	cin.sync();
	cin.get();
}