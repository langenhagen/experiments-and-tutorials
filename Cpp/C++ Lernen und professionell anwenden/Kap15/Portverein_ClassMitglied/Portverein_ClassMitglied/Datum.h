/////////////////////////////////////////////////////////////////////////////
// Header-Datei Datum.h mit der Klasse Datum
/////////////////////////////////////////////////////////////////////////////
#ifndef _DATUM_
#define _DATUM_

#include <ctime>			//Notwendige Inkludierungen für die Methoden von "Datum"
#include <iostream>
#include <iomanip>
#include <string>
#include <sstream>

using namespace std;


///////////////////////////////////////////////////////////////
//Die Klasse Datum für die Darstellung von Datumsangaben
class Datum
{
	private:																					//PRIVATE
		short day, month, year;

	public:																						//PUBLIC
		Datum();																		//Konstruktoren und set-Methoden
		Datum(short tag, short monat, short jahr);
		void setDatum();
		bool setDatum(short tg, short mn, short jr);

		short getTag() const;					//konstante get-Methoden
		short getMonat() const;
		short getJahr() const;

		bool isEqual(const Datum& Date) const;			//El speciale
		bool isLess(const Datum& Date) const;
		const string& asString() const;			
		void print() const;
};

///////////////////////////////////////////////////////////////
//Gibt an, ob das eingegebene Jahr ein Schaltjahr ist
inline bool isLeapYear(short year)
{
	return ( year % 4 == 0 && year % 100 !=0 ) || year % 400 == 0;
}

#endif