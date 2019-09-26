#include "FloatVek.h"

void main()
{
	FloatVek a, b( 10, 3.0), c(b);

	a.append(44);
	a += 3.5;

	b.insert( 888,3);
	b += a;
	b.remove(3);

	c.insert( b, 9);

	a = c;

	cout << a << b << c;

	cout << endl << a[20] << endl;

}