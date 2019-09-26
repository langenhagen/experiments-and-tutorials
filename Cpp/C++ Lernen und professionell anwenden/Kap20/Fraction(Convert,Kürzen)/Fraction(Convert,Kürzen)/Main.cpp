#include <iostream>
#include "Fraction.h"

using namespace std;

void main()
{
	Fraction a(456,213);
	a.kuerzen();
	a = 512.33 + 11.33;
	cout <<  a;
}