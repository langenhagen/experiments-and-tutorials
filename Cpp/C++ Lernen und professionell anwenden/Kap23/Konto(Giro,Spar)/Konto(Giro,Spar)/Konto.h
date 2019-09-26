/////////////////////////////////////////////////////////////////////////////
// Header-Datei Konto.h mit der Klasse Konto
// und den davon abgeleiteten Klassen GiroKonto und SparKonto
/////////////////////////////////////////////////////////////////////////////
#ifndef _KONTO_
#define _KONTO_
#include <string>
using namespace std;

class Konto
{
private:
	string name;
	unsigned long nr;
	double stand;
public:
	Konto( const string& Name = "Unknown, Unkwown", unsigned long Nr = 1111, double Stand = 0.0);
	void display() const;
	void setName( const string& str) { name = str; }
	const string& getName() const { return name; }
	void setNr( unsigned long n ) { nr = n; }
	unsigned long getNr() const { return nr; }
	void setStand( double x ) { stand = x; }
	double getStand() const { return stand; }
};

class GiroKonto : public Konto
{
private:
	double limit;
	double soll;
public:
	GiroKonto(	const string& Name = "Unknown, Unknown", unsigned long Nr = 12321,
							double Stand = 0.0, double Limit = 0.0, double Soll = 0.04 )
		: Konto( Name, Nr, Stand), limit(Limit), soll(Soll)
	{}
	void setLimit( double Lim) { limit = Lim; }
	double getLimit() const { return limit; }
	void setSoll( double Soll) { soll = Soll; }
	double getSoll() const { return soll; }
	void display() const;
};

class SparKonto : public Konto
{
private:
	double haben;
public:
	SparKonto(	const string& Name = "Unknown, Unknown", unsigned long Nr = 12321,
							double Stand = 0.0, double Haben = 0.04 )
		: Konto( Name, Nr, Stand), haben(Haben)
	{}
	void setHaben( double Zins) { haben = Zins; }
	double getHaben() const { return haben; }
	void display() const;
};

#endif