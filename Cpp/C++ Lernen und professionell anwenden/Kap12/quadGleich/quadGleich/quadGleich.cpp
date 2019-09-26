#include "stdafx.h"
#include <iostream>
#include <iomanip>
#include <cmath>

using namespace std;

bool quadGleich(double, double, double, double* , double* );

void main()
{
	double	x1 = 0.0, x2 = 0.0,
					a1 = 2.0, a2 = 2.0,
					a3 = -1.5;

	cout << "Ma kukken wegen Erjebnisse wa...\n";

	if( quadGleich( a1, a2, a3, &x1, &x2 ) == true)
	{
		cout	<< "Aaalso...\nx1 = " << showpos << x1
					<< "\nx2 = " << x2 << endl;
	}
	else
		cout << "Tut ma ja Leid aber leider kein Erjebnis ermittelbar, ne\n";

}


bool quadGleich(double a, double b, double c, double* x1, double* x2)
{
	if(b*b - 4*a*c < 0)																// WENN Formel kein Ergebnis hat...
		return false;																			// return false

	*x1 = ( -b + sqrt( b * b - 4*a*c )) / (2*a) ;				// wenn das nich der Fall is, rechne 
	*x2 = ( -b - sqrt( b * b - 4*a*c )) / (2*a) ;				// Ergebnisse aus und übertrage nach x1, x2 
		return true;																			// und return true...						
}