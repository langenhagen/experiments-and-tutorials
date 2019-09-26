/////////////////////////////////////////////////////////////////////////////
// Header-Datei für die Klasse Liste und ListEl
// Zur Darstellung von Listenelementen durch dynamische Datenstrukturen
/////////////////////////////////////////////////////////////////////////////
#ifndef _LISTE_
#define _LISTE_
#include "Datum.h"
using namespace std;

///////////////////////////////////////////////////////////////
// Klasse ListEl (Listenelement)
class ListEl
{
private:
	Datum datum;				// Datum
	double betrag;			// Geldbetrag in double
	ListEl *next;				// Zeiger auf Nachfolger
public:
	ListEl( Datum d = Datum(1,1,1), double b = 0.0,								// Konstruktor
					ListEl *p = NULL )	
					: datum(d), betrag(b), next(p)	{}

	ListEl*				getNext()		const { return next; }							// get Methoden
	const Datum&	getDatum()	const { return datum; }
	const double&	getBetrag()	const { return betrag; }

	void setDatum()	{ datum.setDatum(); }													// set Methoden
	bool setDatum( short tag, short monat, short jahr )
	{ return datum.setDatum( tag, monat, jahr ); }
	void setDatum( const Datum& date )	{ datum = date; }
	void setBetrag( double b ) { betrag = b; }
																																
	friend ostream& operator<<( ostream& os, const ListEl& Element );		// friend Funktionen
	friend class Liste;																						// friend Klassen
};

///////////////////////////////////////////////////////////////
// Klasse Liste (bestehend aus ListEl-Objekten)
class Liste
{
private:
	ListEl *first, *last;
public:
	Liste() { first = last = NULL; }										// Konstruktoren / Destruktoren
	~Liste();

	ListEl *getFront()	const { return first; }					// get Methoden
	ListEl *getBack()	const { return last; }

	void pushBack( const Datum& d, double b );					// Arbeitsmethoden
	void popFront();

	friend ostream& operator <<( ostream& os, const Liste& list );
};
#endif