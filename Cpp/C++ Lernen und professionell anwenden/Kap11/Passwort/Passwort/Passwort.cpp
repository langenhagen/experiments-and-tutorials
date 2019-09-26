#include "stdafx.h"
#include "MyMakros.h"
#include <iostream>
#include <iomanip>
#include <string>
#include <ctime>

using namespace std;

string geheimwort = "ISUS";						// Passwort 

extern bool getPassword();						// Sclüsselwort 'extern', kann wie 
void changePassword();								// hier gezeigt auch weggelassen werden

int main()
{
	char ui = ' ';
	do
	{
		cls();
		cout << "Welcome!\n\n" << "B = Buchen\nE = Ende des Programms\n";
		cin.sync();
		cin.get(ui);
		if(ui=='E' || ui=='e')
			break;
		else if(ui=='B' || ui=='b')
		{
			if(getPassword()==true)
			{
				cout	<< "\nIhr Passwort ist korrekt.\nWollen sie es nun ändern?"
							<< " <J>/<N>: ";
				cin.sync();
				cin.get(ui);
				
				if(ui=='J' || ui=='j')
				{
					changePassword();
					cout << endl;
				}
				else
					cout << "\nPasswort wurde nicht geändert.\n\n";
			}
		}
		cin.sync();
		cin.get();
	}
	while(ui!='e' || ui!='E');


}