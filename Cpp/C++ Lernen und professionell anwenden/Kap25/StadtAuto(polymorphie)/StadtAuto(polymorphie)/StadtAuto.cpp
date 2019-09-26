/////////////////////////////////////////////////////////////////////////////
// cpp-Datei zur StadtAuto.h mit Methoden der Klasse StadtAuto
/////////////////////////////////////////////////////////////////////////////
#include "StadtAuto.h"

///////////////////////////////////////////////////////////////
// fügt ein Pkw-Objekt an die Datenstruktur an
bool StadtAuto::insert( const string& tp, bool sd, long n, const string& hst )
{
	if( anz >= MAX )
		return false;
	v[anz++] = new Pkw( tp, sd, n, hst);
	return true;
}

///////////////////////////////////////////////////////////////
// fügt ein Lkw-Objekt an die Datenstruktur an
bool StadtAuto::insert( int a, double t, long n, const string& hst )
{
	if( anz >= MAX )
		return false;
	v[anz++] = new Lkw( a, t, n, hst);
	return true;
}
	
///////////////////////////////////////////////////////////////
// zeigt alle Elemente des Objektes an
void StadtAuto::display()
{
	for(int i=0; i< anz; i++)
		v[i]->display();
}