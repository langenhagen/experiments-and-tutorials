#include "stdafx.h"
#include <iostream>
#include <iomanip>
#include <string>


using namespace std;

string line(79,'-');

long double fakultaet(unsigned int);
long double fakultaet1(unsigned int);

int main()
{

	line+='\n';

	cout << line << setw(5) << 'n' << setw(36)  << "Fakulaet von n\n" << line;

	for(int i=0; i<=20; ++i)
	{
		cout << setw(5) << i << right << fixed << setprecision(0) 
			<< setw(35) << fakultaet1((unsigned) i) << endl;
	}
	
	cout << line << endl;

}

long double fakultaet(unsigned int n)
{
	long double fac = 1.0;

	for(unsigned int i=2; i<=n; ++i)
		fac *= i;

	return fac;
}

long double fakultaet1(unsigned int n)
{	
	if(n<=1)
		return 1.0;
	else
		return  fakultaet1(n-1) * n;
}