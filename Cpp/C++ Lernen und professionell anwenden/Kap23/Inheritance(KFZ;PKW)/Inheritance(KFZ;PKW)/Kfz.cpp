/////////////////////////////////////////////////////////////////////////////
// Cpp der Klassen Kfz und Pkw aus Kfz.h
/////////////////////////////////////////////////////////////////////////////
#ifndef _KFZ_
#define _KFZ_
#include "Kfz.h"

///////////////////////////////////////////////////////////////
// Konstruktor von Kfz
Kfz::Kfz( long n, const string &herst )	: nr(n), hersteller(herst)
{ cout << "\nIch baue ein Objekt vom Typ Kfz auf.\n"; }

///////////////////////////////////////////////////////////////
// Destruktor von Kfz
Kfz::~Kfz()
{ cout << "\nIch zerstoere ein Objekt vom Typ Kfz.\n"; }

///////////////////////////////////////////////////////////////
// Methode zum Anzeigen der Daten vom Kfz-Objekten
void Kfz::display() const
{
	cout	<< "------------------------------------------------\n"
				<<   "Hersteller:   " << hersteller
				<< "\nNummer:       " << nr << endl;
}

///////////////////////////////////////////////////////////////
// Konstruktor von Pkw
Pkw::Pkw( const string& tp, bool sd, long n, const string& h )
	: Kfz(n, h), pkwTyp(tp), schiebe(sd)
{ cout << "\nIch baue ein Objekt vom Typ Pkw auf.\n"; }

///////////////////////////////////////////////////////////////
// Destruktor von Pkw
Pkw::~Pkw()
{ cout << "\nIch zerstoere ein Objekt vom Typ Pkw.\n"; }

///////////////////////////////////////////////////////////////
// Methode zum Anzeigen der Daten vom Pkw-Objekten
void Pkw::display() const
{
	Kfz::display();
	cout	<<   "Typ:          " << pkwTyp
				<< "\nSchiebedach:  ";
	if(schiebe)	cout << "ja\n";
	else				cout << "nein\n";
}

//void display() const;

///////////////////////////////////////////////////////////////
// Konstruktor von Lkw
Lkw::Lkw( int achsen, double cap, long n, const string &h )
	: Kfz(n,h), axes(achsen), capacity(cap)
{ cout << "\nIch baue ein Objekt vom Typ Lkw auf.\n"; }

///////////////////////////////////////////////////////////////
// Destruktor von Lkw
Lkw::~Lkw()
{ cout << "\nIch zerstoere ein Objekt vom Typ Lkw.\n"; }

///////////////////////////////////////////////////////////////
// Zeigt Lkw-Objekte an
void Lkw::display() const
{
	Kfz::display();
	cout	<<   "Achsen:       " << axes
				<< "\nKapazitaet:   " << capacity << " Tonnen\n";
}



#endif