/////////////////////////////////////////////////////////////////////////////
// CPP-Datei für Methoden der Klasse Mitglied in Mitglied.h
/////////////////////////////////////////////////////////////////////////////
#include "stdafx.h"
#include "Mitglied.h"

///////////////////////////////////////////////////////////////
//Statische Elemente
Mitglied* Mitglied::ptrVorstand = NULL;			//dass das '*' direkt nach Mitglied kommt is hier essentiell

int i(345);

///////////////////////////////////////////////////////////////
//Konstruktor #1 der Klasse Mitglied
Mitglied::Mitglied(unsigned int Nummer, string Name, Datum Birthday) : MemberNr(Nummer), bday(Birthday)
{ if(!setName(Name)) name = "UNBEKANNT"; }

///////////////////////////////////////////////////////////////
//Konstruktor #2 der Klasse Mitglied
Mitglied::Mitglied(unsigned int Nummer, string Name, short Tag, short Monat, short Jahr) : MemberNr(Nummer), 
																																									bday(Tag,Monat,Jahr)
{
	if(Name.size()<1)
		name="UNBEKANNT";
	else
		name=Name;
}

///////////////////////////////////////////////////////////////
//Gibt die Daten des Objekts formatiert am Bildschirm aus
void Mitglied::print()
{
	cout	<< "\n----------------------------------------" 
				<< setfill('0') << setw(5) << fixed << right << MemberNr << setfill(' ')
				<< setw(17) << left << "\nSpielernummer: " << MemberNr
				<< setw(17) << left << "\nName: " <<  name 
				<< setw(17) << left << "\nGeburtsdatum: " << bday.asString()
				<< "\n---------------------------------------------\n";
}