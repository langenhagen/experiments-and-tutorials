#include "Foo.h"



void main()
{
	int number;
	cout << "Gib eine Nummer ein: ";
	cin >> number;
	cin.sync();

	Foo f( number);
}