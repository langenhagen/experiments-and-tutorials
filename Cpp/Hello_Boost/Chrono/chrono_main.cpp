#include <iostream>
#include <conio.h>

#include <boost/chrono.hpp>

using namespace std;
using namespace boost;
using namespace boost::chrono;

typedef boost::chrono::duration<int_least16_t, boost::chrono::milliseconds> duration;

void main()
{

	while(1)
	{
		steady_clock::time_point start = chrono::steady_clock::now();

		getch();
		
		milliseconds ms = round<milliseconds>(boost::chrono::steady_clock::now() - start);
		
		cout << "INPUT:   " << ms << endl;
	}
}