// InlineFunc.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <cctype>
#include <string>

using namespace std;

inline double Max(double, double);
inline char Max(char, char);

int main()
{
	
	double a, b;	
	
	cout << "Geben se zwei Zahlen ein, und bestaetigen sie jeweils mit <Enter>\n";
	
	if(cin >> a && cin >> b)
		cout << "\nde groessere Zahl is " << Max(a,b) << endl;
	else
		cout << "\nMoooment so jeht dat ja mal nich hier, wa\n" << endl;

	cout << "\nJetzz zwei Buchstaben und nich das <Enter> vergessn\n";

	cin.sync();
	cin.clear();
	char q, w;

	if(cin >> q && cin >> w)
		cout << "\nde groessere Buchstab' laut ASCII is " << Max(q,w) << endl;
	else
		cout << "\nMoooment so jeht dat ja mal nich hier, wa\n" << endl;

	cout << "\nEEEENDDEEEEE\n";





}

inline double Max(double x, double y)
{
	return ( x >= y ? x : y);
}

inline char Max(char x, char y)
{
	return ( x >= y ? x : y);
}