#include "StadtAuto.h"

int menu();
void getPkw(string& name, string& hst, bool& sd, long& n);
void getLkw(string& hst, int& ax, double& cap, long& n);

void main()
{
	StadtAuto sa;

	sa.insert("Forsche", 1, 11111111, "Perrari");
	sa.insert(6, 10.2, 2222222, "MAN");

	while(true)
	{
		string name, hst;		long n=0;		int ax=0;		double cap=0.0;		bool sd = false;
		switch(menu())
		{
		case 1: getPkw(name, hst, sd, n); sa.insert(name,sd,n,hst); break;
		case 2: getLkw(hst, ax, cap, n); sa.insert(ax,cap,n,hst); break;
		case 3: sa.display(); break;
		case 0: exit(0);
		}
	}
}

int menu()
{
	cout	<< "\n      * * *  Verwaltung des StadtAuto-Fuhrparks  * * *\n\n"
				<< "  P : Pkw neu aufnehmen\n\n"
				<< "  L : Lkw neu aufnehmen\n\n"
				<< "  A : Fuhrpark anzeigen\n\n"
				<< "  E : Programm Beenden\n\n"
				<< "  Ihre Wahl: ";
	char c;
	cin.get(c);
	cout << endl << endl;
	cin.sync(); cin.clear();
	switch( c )
	{
		case 'P':
		case 'p':	return 1;					
		case 'L':
		case 'l': return 2;
		case 'A':
		case 'a': return 3;
		case 'E':
		case 'e': return 0;
		default: return 99;
	}
}

void getPkw(string& name, string& hst, bool& sd, long& n)
{	
	char s;

	cout << "Name des Fahrzeuges:      "; getline(cin, name);
	cout << "Name des Herstellers:     "; getline(cin, hst);
	cout << "Typennummer:              "; cin >> n;
		cin.sync(); cin.clear();
	cout << "Schiebedach  <j> fuer ja: "; cin.get(s);
		if(s == 'J' || s == 'j')
			sd = true;
	cin.sync(); cin.clear();
}

void getLkw(string& hst, int& ax, double& cap, long& n)
{
	cout << "Name des Herstellers: "; getline(cin, hst);
	cout << "Typennummer:          "; cin >> n;
		cin.sync(); cin.clear();
	cout << "Kapazitaet in t       "; cin >> cap;
		cin.sync(); cin.clear();
	cout << "Anzahl Achsen:        "; cin >> ax;
		cin.sync(); cin.clear();
}