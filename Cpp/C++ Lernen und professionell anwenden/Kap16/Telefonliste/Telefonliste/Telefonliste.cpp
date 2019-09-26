#include "stdafx.h"
#include "TelList.h"
#include "console.h"
#include <conio.h>

using namespace std;

int menu();

void main()
{
	textcolor(14);

	bool on = true;
	string line(79, '-'); line += '\n';
	TelList Liste;

	while(on)
	{
		switch(menu())
		{
			case 1:
				{																					//Alle Anzeigen
				cls();
				cout	<< setw(30) << left << "Name" << "Telefonnummer\n" << line;
				Liste.print();
				cout	<< endl << line << '\n' << Liste.getCount() << " Eintraege\n\n"
							<< "\nWeiter mit beliebiger Taste...";
				
				cin.get();

				break;
				}
			case 2:
				{																					//Finden
				cls();
				cursorOn();
				string name;
				int anz;
				cout << "\n\nFinden einer Telefonnummer\n\nGeben sie den gesuchten Namen ein: ";
				getline(cin,name);
				cls();
				cout	<< setw(30) << left << "Name" << "Telefonnummer\n" << line;
				anz = Liste.print(name);
				cout	<< endl << line << '\n' << anz << " Ergebnisse fuer " << name 
							<< " gefunden\n\nWeiter mit beliebiger Taste...";
				cursorOff();
				cin.sync();
				cin.get();
				break;			 
				}
			case 3:
				{ 
				cls();
				cursorOn();
				string name, nr;
				cout	<< "\nGeben sie einen Namen und dann eine Nummer ein,\nbestaetigen sie jeweils mit <Enter>:\n\n"
							<< "Name: ";
				getline(cin, name);
				if(name.length()<1)
				{	
					cout	<< "\nEintrag wurde nicht angelegt."
								<< "\n\nWeiter mit beliebiger Taste...";
					cursorOff();
					cin.sync();
					cin.get();
					break;
				}
				cout	<< "Telefonnummer: ";
				getline(cin, nr);
				
				if(Liste.append(name,nr))
					cout << "\nEintrag wurde angelegt.";
				else
					cout << "\nEintrag wurde nicht angelegt.";

				cout << "\n\nWeiter mit beliebiger Taste...";
				cursorOff();
				cin.sync();
				cin.get();
				break;
				}
			case 4:
				{
				cls();
				cursorOn();
				string name;
				cout	<< "\nGeben sie den Namen des zu loeschenden Eintrags an\nund bestaetigen sie mit <Enter>:\n";
				getline(cin, name);
				if(Liste.erase(name))
					cout << "\n\nEintrag wurde geloescht.";
				else
					cout << "\n\nEintrag nicht gefunden.";
				
				cout << "\n\nWeiter mit beliebiger Taste...";
				cursorOff();
				cin.sync();
				cin.get();
				break;	 
				}
			case 5:
				{ 
				cls();
				cout	<< "\n\n\n\n\n\n\n\n\n\n                                 Auf Wiedersehen!";
				on = false;
				cin.sync();
				cin.get();
				break;
				}
	}
	}
}

int menu()
{
	cls();
	cursorOff();
	cout	<< "\n                            **** Telefonliste ****\n\n\n"
				<< "                        A - Anzeigen aller Eintraege\n\n"
				<< "                        F - Finden einer Telefonnummer\n\n"
				<< "                        H - Hinzufuegen eines Eintrags\n\n"
				<< "                        L - Loeschen eines Eintrags\n\n"
				<< "                        B - Beenden des Programms";
	
	while(true)
	{
	if(_kbhit()!=0)				//Wurde Taste gedrückt?!
	 {
		switch(_getch())			//Ja?! Welche?!
		{
			case 'a':
			case 'A':	return 1;
			case 'f':
			case 'F': return 2;
			case 'h':
			case 'H':	return 3;
			case 'l':
			case 'L': return 4;
			case 'b':
			case 'B': return 5;
		}
	 }
	}
}