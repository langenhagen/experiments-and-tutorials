/////////////////////////////////////////////////////////////////////////////
// Header Kfz.h mit Klassen Kfz, Pkw und Lkw
/////////////////////////////////////////////////////////////////////////////
#ifndef _KFZ_H_
#define _KFZ_H_
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

class Pkw : public virtual Kfz
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

class Lkw : public virtual Kfz
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

class Transporter : public virtual Kfz
{
private:
	double last;
public:
	Transporter(	const string& h = "", long nr = 0, double Last = 750.0)
		: Kfz( nr, h)
	{ Last > 750. ? last = 750. : last = Last; }

	double getLast() const { return last; }
	void setLast( double l) { l > 750. ? last = 750. : last = l; }

	void display() const;
								
};

class Kombi : public Pkw, public Transporter
{
private:
	short seats;
public:
	Kombi(	const string& tp = "", bool sd = false, long n = 0, const string& h = "",
					double Last = 500., short Sitze = 2 )
		: Kfz( n, h), Pkw( tp, sd), Transporter(h,n, Last), seats(Sitze)	{}

	void display() const;
};



#endif