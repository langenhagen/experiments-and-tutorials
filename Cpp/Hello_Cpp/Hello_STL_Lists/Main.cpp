#include <iostream>
#include <list>

using namespace std;

void main() {

	list<int*> l;

	int* test = 0;

	for( int i = 0; i<100; i++) {
		int* ptr = new int(i);
		l.push_back( ptr);

		if( i==5)
			test = ptr;
	}

	l.clear();

	cout << "*test: " << *test << endl;
    delete test;
	cout << "*test: " << *test << endl;

	// wir lernen daraus, dass zwar die pointer gelöscht werden,
	// jedoch nicht deren *werte auf die sie pointen

	cin.get();

}