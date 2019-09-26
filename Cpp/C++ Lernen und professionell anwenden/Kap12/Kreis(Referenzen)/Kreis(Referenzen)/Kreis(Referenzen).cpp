//#include "stdafx.h
#include <iostream>
#include <iomanip>

using namespace std;

void kreis(const double& , double& , double& );

void main()
{

	double U, A;

	cout << "     Arbeiten mit Referenzen in Funktionen am Beispiel der Kreisgleichung\n"
		<< "-------------------------------------------------------------------------------\n";

	for(double r=0.5; r<=10.0; r+=0.5)
	{
		kreis(r,A,U);
		cout << setw(4) << setprecision(1) << fixed << r 
			<< setw(20) << setprecision(10) << U << setw(20) << A << endl;
	}

	cout << "\nUnd Ciao!\n";

}

void kreis(const double& Radius, double& Flaeche, double& Umfang)
{
	const double pi = 3.1415926536;

	Flaeche = pi * Radius * Radius;
	Umfang  = 2 * pi * Radius;
}