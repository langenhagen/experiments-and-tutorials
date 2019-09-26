#include "stdafx.h"
#include <iostream>
#include <math.h>

using namespace std;

double pot(double, int);

int main()
{
cout << pot(-6,0);
}

double pot(double basis, int exp)
{

	double pot = 1;
	
	if(exp>0)						//wenn Exponent größer Null...
	{
		for(int i=1; i<=exp; ++i)
		{
			pot *= basis;
		}
		return pot;
	}
	else if(exp==0)			//wenn Exponent gleich Null...
	{
		return 1.0;
	}
	else								//wenn Exponent kleiner Null...
	{
		if(basis>0 || basis<0)
		{
			for(int i=1; i<=-exp; ++i)
			{
				pot *= basis;
			}
			return (1/pot);
		}
		else
			return HUGE_VAL;
	}

}