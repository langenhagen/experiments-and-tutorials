#include <iostream>
using namespace std;

unsigned int getBin()
{
	char c;
	unsigned int val = 0;

	while( (c = cin.get()) == ' ' || c == '\t');			// f�hrende  Blanks oder Tabs �berlesen

	while( c == '0' || c == '1' )
	{
		 val = val << 1 | (c - '0');										// val verschieben | verkn�pfung mit 0|1 .. genyal!
		 c = cin.get();
	}
	return val;
}

void main()
{
	cout << "Maaan, ya know what to do..\n>> ";
	unsigned int val = getBin();
	cout << "Jamaaan...\n>> " << val << endl;
}