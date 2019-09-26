// Summe.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>

using namespace std;

long summe(long, long, long = 0, long = 0);

int main()
{

	cout << summe(5,2) << endl;
	cout << summe(5,2,1) << endl;
  cout << summe(5,2,1,3) << endl;
}

