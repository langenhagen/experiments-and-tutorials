#include <iostream>
using namespace std;

void putBits( unsigned int n)
{
	for( int i=15; i>=0; i--)
	{
		cout << ((n >> i) & 1 );
		if( i % 4 ==0  && i>0 )
			cout << ' ';
	}
}

void main()
{
	for( int n=0; n<50; n++)
	{
		cout << n << ":\t"; putBits(n); 
		cout << "\t\t" << n+50 << ":\t"; putBits(n+50);
		cout << endl;
	}
}