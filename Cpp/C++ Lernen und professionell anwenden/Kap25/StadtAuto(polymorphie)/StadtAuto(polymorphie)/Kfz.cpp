/////////////////////////////////////////////////////////////////////////////
// Cpp der Klassen Kfz und Pkw aus Kfz.h
/////////////////////////////////////////////////////////////////////////////
#include "Kfz.h"

///////////////////////////////////////////////////////////////
// Konstruktor von Kfz
Kfz::Kfz( long n, const string &herst )	: nr(n), hersteller(herst)
{}

///////////////////////////////////////////////////////////////
// Destruktor von Kfz
Kfz::~Kfz()
{}

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
{}

///////////////////////////////////////////////////////////////
// Destruktor von Pkw
Pkw::~Pkw()
{}

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
{}

///////////////////////////////////////////////////////////////
// Destruktor von Lkw
Lkw::~Lkw()
{}

///////////////////////////////////////////////////////////////
// Zeigt Lkw-Objekte an
void Lkw::display() const
{
	Kfz::display();
	cout	<<   "Achsen:       " << axes
				<< "\nKapazitaet:   " << capacity << " Tonnen\n";
}