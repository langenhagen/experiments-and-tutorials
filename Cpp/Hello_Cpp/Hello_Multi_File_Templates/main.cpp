#include <iostream>

#include "Foo.h"

using namespace std;

void main()
{
	Foo<int> f = Foo<int>();

	f.publ = 17;
	
	cout << "f.publ: " << f.publ << endl << "f.bar(23): " << f.bar(23) << endl;
	
	cin.get();
}