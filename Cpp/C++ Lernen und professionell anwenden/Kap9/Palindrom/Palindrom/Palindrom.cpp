#include "stdafx.h"
#include <iostream>
#include <string>

using namespace std;


int main()
{

	cout << "_____________________________Haben_Wir_ein_Palindrom?!_________________________\n";
	string wort;
	bool palindrom = true;
	char uw;


	do
	{
	  palindrom = true;					//Palindrom vor auf true setzen

		cout << endl;
		cin >> wort;							//Eingabe des zu überprüfenden Wortes

		for(int i=0; i<=(wort.length()-1)/2; ++i)
		{
			
			if(wort[i] == wort[wort.length()-1-i])
				;
			else
			{	
			palindrom = false;
				break;
			}
		}

			palindrom ? cout << "Wir haben ein Palindrom!" : cout << "Kein Palindrom!";
			cout << endl;

		do
		{
			cout << "Na Watn? Weiter <w>? Oder Beenden <q>? ";
			cin.clear();
			cin.sync();
			cin.get(uw);
			
			cin.clear();
			cin.sync();
		}
		while(uw!='w' && uw!='W' && uw!='q' && uw!='Q');
	}
	while(uw=='w' || uw=='W');

	cout << "\nAdios!! Du Tuerke!!\n";

}

