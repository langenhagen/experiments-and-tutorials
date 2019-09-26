/////////////////////////////////////////////////////////////////////////////
// Header StadtAuto.h mit Klasse StadtAuto
/////////////////////////////////////////////////////////////////////////////
#ifndef _STADTAUTO_
#define _STADTAUTO_
#include "Kfz.h"

#define MAX		 100

class StadtAuto
{
private:
	Kfz *v[MAX];
	int anz;
public:
	StadtAuto() { anz = 0; }								// Konstruktor
	~StadtAuto()														// Destruktor
	{
		for( int i=0; i<anz; i++)
			delete v[i];
	}

	bool insert( const string& tp, bool sd, long n, const string& hst );
	bool insert( int a, double t, long n, const string& hst );
	void display();
};
#endif