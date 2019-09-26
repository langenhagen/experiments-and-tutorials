/////////////////////////////////////////////////////////////////////////////
// Cpp-Datei der Fraction.h für die Klasse Fraction 
// Ermöglicht Darstellung vongemeinen Brüchen
/////////////////////////////////////////////////////////////////////////////
#include "Fraction.h"

///////////////////////////////////////////////////////////////
// long Konstruktor
Fraction::Fraction( long Zaehler, long Nenner ) : zaehler(Zaehler)
{
	if( Nenner == 0 )
	{
		cerr << "JIBTET JARNICH DU BANAUSE!!)";
		exit(1);
	}

	if( Nenner<0 )
		Nenner = -Nenner, Zaehler = -Zaehler;
	nenner	= Nenner;
	zaehler	= Zaehler;
}

///////////////////////////////////////////////////////////////
// double Konstruktor ; Konvertierungskonstruktor
Fraction::Fraction( double dbl )
{
	zaehler	= dbl > 0 ? (long)(dbl * 1000.0 + 0.5) : (long)(dbl * 1000.0 - 0.5);
	nenner	= 1000;
	this->kuerzen();
}

///////////////////////////////////////////////////////////////
// int Konstruktor ; Konvertierungskonstruktor
Fraction::Fraction( int n ) : zaehler( n ) , nenner( 1 )
{}

// Methoden###################################################################

///////////////////////////////////////////////////////////////
// Methode Kuerzen() zum Kürzen der Brüche
void Fraction::kuerzen()
{
	if( zaehler == 0 )
	{
		nenner = 1;
		return;
	}

	long a = (zaehler < 0 ) ? -zaehler : zaehler,
			 b = nenner,
			 hilf;

	while( b != 0 )
	{
		hilf = a % b; a = b; b = hilf;
	}

	zaehler	/= a;
	nenner	/= a;
}

///////////////////////////////////////////////////////////////
// Converter in double
Fraction::operator double() const
{
	return (double)zaehler/nenner;
}

// Operatoren#################################################################

//////////////////////////////////////////////////////////
// Negation
Fraction Fraction::operator-() const																
{ 
	Fraction t;
	t.zaehler	= -zaehler;
	t.nenner	= nenner;
	return t;
}

//////////////////////////////////////////////////////////
// Präfix-Inkrementierung
Fraction& Fraction::operator++()
{
	zaehler+=nenner;
	return *this;
}

//////////////////////////////////////////////////////////
// Präfix-Dekrementierung
Fraction& Fraction::operator--()
{
	zaehler-=nenner;
	return *this;
}

//////////////////////////////////////////////////////////
// += Addition
Fraction& Fraction::operator+=(const Fraction &a)
{
	zaehler	= zaehler * a.nenner + a.zaehler * nenner;
	nenner	*= a.nenner;
	this->kuerzen();

	return *this;
}

//////////////////////////////////////////////////////////
// -= Subtraktion
Fraction& Fraction::operator-=(const Fraction &a)
{
	*this += (-a);
	return *this;
}

//////////////////////////////////////////////////////////
// *= Multiplikation
Fraction& Fraction::operator*=(const Fraction &a)
{
	zaehler	*=a.zaehler;
	nenner	*=a.nenner;
	this->kuerzen();
	return *this;
}

//////////////////////////////////////////////////////////
// /= Multiplikation
Fraction& Fraction::operator/=(const Fraction &a)
{
	zaehler	*=a.nenner;
	nenner	*=a.zaehler;
	this->kuerzen();
	return *this;
}

//////////////////////////////////////////////////////////
// Addition
Fraction operator+(const Fraction& a, const Fraction& b)
{
	Fraction t(a);
	t+=b;
	return t;
}

//////////////////////////////////////////////////////////
// Subtraktion
Fraction operator-(const Fraction& a, const Fraction& b)
{
	Fraction t(a);
	t-=b;
	return t;
}

//////////////////////////////////////////////////////////
// Multiplikation
Fraction operator*(const Fraction& a, const Fraction& b)
{
	Fraction t(a);
	t*=b;
	return t;
}	

//////////////////////////////////////////////////////////
// Division
Fraction operator/(const Fraction& a, const Fraction& b)
{
	Fraction t(a);
	t/=b;
	return t;
}	

//////////////////////////////////////////////////////////
// << Shiftoperator Out 
ostream& operator<<(ostream& os, const Fraction& a)
{
	os << a.zaehler << '/' << a.nenner;
	return os;
}

//////////////////////////////////////////////////////////
// >> Shiftoperator In 
istream& operator>>(istream& is, Fraction& a)
{
	char c;
	
	is >> a.zaehler >> c >> a.nenner;

	if(!is) return is;

	if( a.nenner<0 )
		a.nenner = -a.nenner , a.zaehler = -a.zaehler;

	if( a.nenner == 0  )
		exit(1);

	return is;
}