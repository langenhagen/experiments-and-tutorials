#include "stdafx.h"
#include <iostream>

using namespace std;

void main()
{
	char zeile[72], *ptr;
	int length = sizeof(zeile) / sizeof(char);
	
	cout << "Geben sie eine Zeile Text ein:\n";
	
	for( ptr = zeile; ptr < zeile + length && cin.get(*ptr) && *(ptr) != '\n'; ptr++ )
		;

	for(; ptr >= zeile; ptr--)
		cout << *ptr;

	cout << endl;
}

