#include <iostream>
#include <iomanip>

#include <boost/thread.hpp>
#include <boost/thread/locks.hpp>
#include <boost/bind.hpp>
#include <boost/circular_buffer.hpp>
#include <boost/numeric/ublas/vector.hpp>
#include <boost/numeric/ublas/io.hpp>

using namespace std;
using namespace boost;
using namespace boost::numeric::ublas;

typedef boost::numeric::ublas::vector<unsigned long> vector_ulong;

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

	static Manfred* manf;

	boost::mutex _mutex;
	unsigned long _var;
	circular_buffer<unsigned long> _buffer;

	Manfred() : _var(42)
	{
		_buffer = circular_buffer<unsigned long>(4);
	}

	void operator()()
	{
		while(true)
		{
			{
				boost::lock_guard<boost::mutex> lockObj( _mutex); // locks mutex until lockObj is destroyed
				_buffer.push_front(++_var);
			}
		}
	}

	unsigned long getBufferValue( unsigned int i=0)
	{
			return _buffer[i];
	}

	vector_ulong getBufferValue( unsigned int startIndex, unsigned int length)
	{
		vector_ulong ret = vector_ulong( length);
		unsigned int ix=0;

		boost::lock_guard<boost::mutex> lockObj( _mutex); // locks mutex until lockObj is destroyed
		
		for(unsigned int i = startIndex; i < startIndex + length; i++)
		{

			ret(ix++) = _buffer[i];
		}
		
		return ret;
	}

};

Manfred* Manfred::manf;

void main()
{

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
	
	cout << "Erstmal das Manpferd initialisieren:...";
	Manfred::manf = new Manfred();
	Manfred* manf = Manfred::manf;
	cout << "Manfred meint initial: " << manf->_var << endl;
	boost::thread( boost::ref( *manf));


	cout << "Capacity of Manf's buffer: " << manf->_buffer.capacity() << endl;

	unsigned long counter = 0;
	unsigned long errorcounter = 0;
	while(true)
	{
		char ch;
		cin.get(ch);
		cin.sync();
		cin.clear();
		if( ch == 'q')
		{
			return;
		}
		else if( ch == '\t')
		{
			cout << endl << "Runs:   " << counter << endl << "Errors: " << errorcounter;
			cin.get();
		}
		
		vector_ulong v = manf->getBufferValue(0,4);
		bool assert = v(0)-v(1)==1 && v(1)-v(2)==1 && v(2)-v(3)==1 ? true : false;
		string assertString = assert ? ":)" : "!!!";
		errorcounter += assert ? 0 : 1;
		counter++;

		cout << "Manfred parallel: " << setw(6) << counter << "  "
			<< "v(0): " << v(0) << " v(1): " << v(1) << " v(2): " << v(2) << " v(3): " << v(3) << '\t' << assertString;
	}
}




void testfunc( string kaese)
{
	cout << "What comes around: " << kaese << endl;
}