#include <iostream>

using namespace std;

#define EXIT cout << "\n\nShutting down... (Press enter)"; cin.get(); return
#define p(i) cout << i << endl;


void function1( const int i);
const int* function2( int i);

void main()
{
	int i= 1;
	const int* j= NULL;

	function1(i);
	j = function2( i+1);
	// j++; // geht
	// (*j)++; // geht nicht!

	EXIT;
}

void function1( const int i)
{
	cout << "function1: " << i << endl;
}

const int* function2( int i)
{
	int* ret= new int(i);
	cout << "function2: " << *ret << endl;
	return ret;
}

