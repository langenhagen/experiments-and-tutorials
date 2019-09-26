/////////////////////////////////////////////////////////////////////////////
// Header Kfz.h mit Klassen Kfz und Pkw
/////////////////////////////////////////////////////////////////////////////
#include <iostream>
#include <string>
using namespace std;

class Kfz
{
private:
	long nr;
	string hersteller;
public:
	Kfz( long n = 0L, const string& herst = "");
	~Kfz();

	long getNr() const { return nr;}
	void setNr( long n ) { nr = n; }
	const string& getHerst() const { return hersteller; }
	void setHerst( const string &h ) { hersteller = h; }
	void display() const;
};

class Pkw : public Kfz
{
private:
	string pkwTyp;
	bool schiebe;
public:
	Pkw( const string& tp = "", bool sd = false, long n = 0, const string& h = "" );
	~Pkw();

	const string& getTyp() const { return pkwTyp; }
	void setTyp( const string &s ) { pkwTyp = s; }
	bool getSchiebe() const { return schiebe; }
	void setSchiebe( bool b ) { schiebe = b; }
	void display() const;
};

class Lkw : public Kfz
{
private:
	int axes;
	double capacity;
public:
	Lkw( int achsen =2, double cap=0, long n = 0, const string &h = "" );
	~Lkw();
	
	void setAchsen( int a ) { axes = a; }
	int getAchsen() const { return axes; }
	void setKapazitaet( double cp ) { capacity = cp; }
	double getKapazitaet() { return capacity; }
	void display() const;
};