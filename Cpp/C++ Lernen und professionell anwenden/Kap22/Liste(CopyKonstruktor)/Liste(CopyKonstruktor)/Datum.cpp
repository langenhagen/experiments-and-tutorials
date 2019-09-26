/////////////////////////////////////////////////////////////////////////////
// cpp-Datei für Methoden der Klasse Datum in der Header Datum.h
/////////////////////////////////////////////////////////////////////////////
#include "Datum.h"

///////////////////////////////////////////////////////////////
// Default-Konstruktor der Klasse Datum
Datum::Datum()
{
day   = 11;
month = 2;
year  = 1988;
}

///////////////////////////////////////////////////////////////
// Konstruktor #2 der Klasse Datum
Datum::Datum(short tag, short monat, short jahr)
{
	if(!setDatum(tag, monat, jahr))
		day   = 11, month = 2, year  = 1988;
}

///////////////////////////////////////////////////////////////
// Übergibt dem Objekt das aktuelle Datum
void Datum::setDatum()
{
	tm *zeit;
	time_t sek;
	time(&sek);
	zeit = localtime(&sek);

	day		= zeit->tm_mday;
	month	=	zeit->tm_mon + 1;
	year	= zeit->tm_year + 1900;
}

///////////////////////////////////////////////////////////////
// Übergibt dem Objekt das aktuelle Datum
bool Datum::setDatum(short tg, short mn, short jr)
{
	if(tg>31 || tg < 1) return false;
	if(mn>12 || mn < 1) return false;

	switch(mn)
	{
		case 2: if(isLeapYear(jr))
							{ if(tg>29) return false; }	
						else if(tg>28) return false;
						break;
		case 4:
		case 6:
		case 9:
		case 11: if(tg>30) return false;
	}

	day   = tg;
	month = mn;
	year  = jr;
	return true;
}

///////////////////////////////////////////////////////////////
// Holt den Tag vom Objekt
short Datum::getTag() const { return day; }

///////////////////////////////////////////////////////////////
// Holt den Monat vom Objekt
short Datum::getMonat() const { return month; }

///////////////////////////////////////////////////////////////
//Holt den Monat vom Objekt
short Datum::getJahr() const { return year; }

///////////////////////////////////////////////////////////////
// Vergleicht das angegebene Objet mit dem Aktuellen auf Gleichheit
bool Datum::isEqual(const Datum& Date) const
{
	if(day == Date.day && month == Date.month && year == Date.year)
		return true;
	return false;
}
		
///////////////////////////////////////////////////////////////
// Vergleicht das angegebene Objet mit dem Aktuellen auf Größe
bool Datum::isLess(const Datum& Date) const
{
	if(year < Date.year || year == Date.year && month < Date.month ||
		year == Date.year && month == Date.month && day < Date.day)
		return true;
	return false;
}

///////////////////////////////////////////////////////////////
// Gibt Rerenz auf einen formatierten String mit den aktuellen Werten wieder
const string& Datum::asString() const		//siehe Projekt StringStreams in Kap14
{
	static string sDate;
	stringstream iostr;

	iostr << setfill('0') << setw(2) << day << '.'
				<< setw(2) << month << '.'
				<< setw(4) << year;

	iostr >> sDate;
	return sDate;
}

///////////////////////////////////////////////////////////////
// Gibt Formatierte Werte des Objektes wieder
void Datum::print() const { cout << asString(); }
