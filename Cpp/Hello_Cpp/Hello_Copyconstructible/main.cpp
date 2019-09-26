#include <iostream>
#include "A.h"

using namespace std;

void main()
{

	A* a = new A();

	cout << "a->i: " << a->i << endl;

	a->i = 3;
	cout << "a->i: " << a->i << endl;

	A* b = new A( *a);

	a->i = 4;
	cout << "a->i: " << a->i << endl;
	cout << "b->i: " << b->i << endl;

	cin.get();
}