/////////////////////////////////////////////////////////////////////////////
// Cpp-Datei Konto.cpp mit Methoden der Klasse Konto
/////////////////////////////////////////////////////////////////////////////
#include "konto.h"
#include <iostream>
#include <iomanip>
using namespace std;

///////////////////////////////////////////////////////////////
// Konstruktor des Objektes Konto
Konto::Konto( const string& i_name, unsigned long i_nr, double i_stand)
{
	name = "Unknown, Unknown";
	if( i_name.size() > 0)
		name = i_name;
	nr = i_nr;
	stand = i_stand;
}

///////////////////////////////////////////////////////////////
// Gibt die Daten vom Konto-Objet am Bildschirm aus
void Konto::display() const
{
	cout	<< fixed << setprecision(2)
				<< "\n---------------------------------------------\n"
				<<   "Kontoinhaber:  " << name << endl
				<<   "Kontonummer:   " << nr << endl
				<<   "Kontostand:    " << stand << " Euro" << endl
				<<   "---------------------------------------------\n\n";
}

///////////////////////////////////////////////////////////////
// Gibt die Daten von GiroKonto-Objeten am Bildschirm aus
void GiroKonto::display() const
{
	cout	<< fixed << setprecision(2)
				<< "\n---------------------------------------------\n"
				<<   "Kontoinhaber:        " << getName() << endl
				<<   "Kontonummer:         " << getNr() << endl
				<<   "Kontostand:          " << getStand() << " Euro" << endl
				<<   "Ueberziehungslimit:  " << limit << " Euro" << endl
				<<   "Soll-Zinssatz:       " << soll << " %" << endl
				<<   "---------------------------------------------\n\n";
}

///////////////////////////////////////////////////////////////
// Gibt die Daten von SparKonto-Objeten am Bildschirm aus
void SparKonto::display() const
{
	cout	<< fixed << setprecision(2)
				<< "\n---------------------------------------------\n"
				<<   "Kontoinhaber:    " << getName() << endl
				<<   "Kontonummer:     " << getNr() << endl
				<<   "Kontostand:      " << getStand() << " Euro" << endl
				<<   "Haben-Zinssatz:  " << haben << " %" << endl
				<<   "---------------------------------------------\n\n";
}