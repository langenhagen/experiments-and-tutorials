#include <iostream>
using namespace std;

unsigned int getBin()
{
	char c;
	unsigned int val = 0;

	while( (c = cin.get()) == ' ' || c == '\t');			// führende  Blanks oder Tabs überlesen

	while( c == '0' || c == '1' )
	{
		 val = val << 1 | (c - '0');										// val verschieben | verknüpfung mit 0|1 .. genyal!
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