/////////////////////////////////////////////////////////////////////////////
//Header-Datei Fraction.h f�r die Klasse Fraction 
//Erm�glicht Darstellung vongemeinen Br�chen
/////////////////////////////////////////////////////////////////////////////

#ifndef _FRACTION_
#define _FRACTION_

#include <iostream>
using namespace std;

///////////////////////////////////////////////////////////////
// Die Klasse Fraction f�r die Darstellung von Br�chen
class Fraction
{
private:
	long zaehler, nenner;
public:
	Fraction( long Zaehler = 0 , long Nenner = 1 );	// long-Konstruktor
	Fraction( double Kommazahl );										// double-Konstruktor ; Konvertierungskonstruktor
	Fraction( int Zahl );														// int-Konstruktor ; Konvertierungskonstruktor

	void kuerzen();																// Methode zum K�rzen
	operator double() const;											// Converter in double

	Fraction operator-() const;										// Negation
	Fraction& operator++();												// Pr�fix Inkrementierung
	Fraction& operator--();												// Pr�fix Dekrementierung
	Fraction& operator+=(const Fraction& a);			// += Addition
	Fraction& operator-=(const Fraction& a);			// -= Subtraktion
	Fraction& operator*=(const Fraction& a);			// *= Multiplikation
	Fraction& operator/=(const Fraction& a);			// /= Division

	//FRIEND-Funktionen
	friend Fraction operator+(const Fraction& a, const Fraction& b);	// Addition
	friend Fraction operator-(const Fraction& a, const Fraction& b);	// Subtraktion
	friend Fraction operator*(const Fraction& a, const Fraction& b);	// Multiplikation
	friend Fraction operator/(const Fraction& a, const Fraction& b);	// Division
	friend ostream& operator<<(ostream& os, const Fraction& a);				// Shiftoperator Out
	friend istream& operator>>(istream& is, Fraction& a);							// Shiftoperator In
};


#endif