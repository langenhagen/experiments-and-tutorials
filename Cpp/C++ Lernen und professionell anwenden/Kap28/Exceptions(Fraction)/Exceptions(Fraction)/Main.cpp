#include <iostream>
#include "fraction.h"

void main()
{
	Fraction	a(2,3),
						b(1,2),
						c(0);

	try
	{
		cout << "Try I\n";
			Fraction x(4,5);
			cout << "One Clear\n";
			Fraction y(3,0);
			cout << "Two CLear\n";
	}
	catch(Fraction::DivError&)
	{
		cout << "Div durch Nuuulll\n";
	}

	try
	{
		cout << a/(Fraction)0.0 << endl;

	}
	catch(Fraction::DivError&)
	{
		cout << "Gniggert\n";
	}


}