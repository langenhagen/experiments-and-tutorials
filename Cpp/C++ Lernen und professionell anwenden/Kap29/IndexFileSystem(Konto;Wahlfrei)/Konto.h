/////////////////////////////////////////////////////////////////////////////
// Header-Datei Konto.h mit der Klasse Konto
// und den davon abgeleiteten Klassen GiroKonto und SparKonto
/////////////////////////////////////////////////////////////////////////////
#ifndef _KONTO_
#define _KONTO_
#include <string>
#include <fstream>
#include "exceptio.h"
using namespace std;

enum TypeId { KONTO, GIRO, SPAR };		// Datentyp TypeId zur Unterscheidung der Kontoarten

//####################################################################################### Konto

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

	virtual TypeId getTypeId() const { return KONTO; }
	virtual ostream& write( ostream& os) const;
	virtual istream& read( istream& is);
};

//####################################################################################### GiroKonto

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

	TypeId getTypeId() const { return GIRO; }
	ostream& write( ostream& os) const;
	istream& read( istream& is);
};

//####################################################################################### SparKonto

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

	TypeId getTypeId() const { return SPAR; }
	ostream& write( ostream& os) const;
	istream& read( istream& is);
};

//####################################################################################### KontoFile

class KontoFile
{
private:
	fstream file;									// Stream
	string name;									// Name
public:
	KontoFile(const string& str) throw(open_error);
	~KontoFile() { file.close(); }
	
	long append( const Konto& kto) throw( write_error);
	Konto* retreive( long pos) throw( read_error);

	void display() const throw(read_error);
};

#endif