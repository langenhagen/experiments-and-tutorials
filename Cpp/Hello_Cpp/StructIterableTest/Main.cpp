#include <iostream>

using namespace std;

struct Foo
{
	float* a;
	float* b;
	float* c;
};

void main()
{
	Foo f;

	float i = 23;
	float j = 42;
	float k = 144;

	f.a = &i;
	f.b = &j;
	f.c = &k;

	cout << "f.a: " << &f.a << "\t" << *f.a << endl << "f.b " << &f.b << "\t"  << *f.b << endl ;
	cout << **(1+(&f.a));
	cin.get();
}