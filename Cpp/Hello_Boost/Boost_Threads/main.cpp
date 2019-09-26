/**************************************************************************************************

Unfortunately boost::thread is not a "header-only" library -- hence you need to have it compiled.
There are basically two ways to go around it.

- you download a prebuilt install package from boostpro (assuming that you are on windows) -- http://www.boostpro.com/download
- you can build it yourself - see http://www.boost.org/doc/libs/1_35_0/more/getting_started/index.html



**************************************************************************************************/

#include <iostream>
#include <boost/thread.hpp>
#include <boost/bind.hpp>

using namespace std;
using namespace boost;

void testfunc( string kaese);

struct murks
{
    void operator()()
    {
        cout << endl << endl << "hello murks" << endl << endl;
    }
};  


class Manfred
{
public:

	unsigned long _japp;

    Manfred()
	{
		_japp = 42; 
	}

	void oneUp()
	{
		while(true)
		{
			_japp +=1;
		}
	}

	void operator()()
	{
		oneUp();
	}

};


void main()
{
	char c=0;

	cout << "HALLO" << endl << endl ;

	/*
	thread therd1(testfunc, "edamer");
	thread therd2(testfunc, "gouda");
	thread therd3(testfunc, "holländer");
	thread therd4(testfunc, "parmesan");
	thread therd5(testfunc, "pecorino");
	thread therd6(testfunc, "tilsiter");

	murks m;
	thread therdomatic(m);
	

	
	Manfred manf;

	cout << "Manpferd sargt:" << manf._japp << endl;
	cout << "Manpferd: run!" << endl;

	manf.runManfredRun();

	while(true)
	{
		cout << "Manpferd sargt:" << manf._japp << endl;
	}
	*/

    
	Manfred manf;
	cout << "Manfred meint initial: " << manf._japp << endl;

	thread terd( boost::ref( manf));


	while( c != 'q')
	{
		cin.get(c);
		cin.clear();
		cin.sync();
		
		cout << "Manfred meint parallel: " << manf._japp << endl;
	}
	
	cin.get();
}




void testfunc( string kaese)
{
	cout << "What comes around: " << kaese << endl;
}