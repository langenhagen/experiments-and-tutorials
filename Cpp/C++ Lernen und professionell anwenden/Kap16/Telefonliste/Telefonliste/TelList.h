/////////////////////////////////////////////////////////////////////////////
// Header TelList.h für die Klasse TelList
/////////////////////////////////////////////////////////////////////////////
#ifndef _TELLIST_
#define _TELLIST_

#include <iostream>
#include <iomanip>
#include <string>

using namespace std;

#define PSEUDO -1													//Wert der keinem Eintrag zugeordnet ist
#define MAX	1000														//Maximalwert der Elemente

struct Element { string name, telNr; };		//ein Datenelement für TelList

class TelList
{
	private:																//PRIVATE
		Element v[MAX];
		int count;
	public:																	//PUBLIC
		TelList() { count = 0; }							// Default-Konstruktor

		int getCount() const { return count; }	//GET-Methoden

		Element *retrieve( int i)
		{ 
			return ( i>=0 && i< count ) ? &v[i] : NULL;
		}

		bool append( const Element& el ) { return append( el.name, el.telNr ); }
		bool append( const string& name, const string& telNr);
		bool erase( const string& name);
		
		int search( const string& name) const;				//El Speciale
		void print() const;
		int print( const string& name) const;
		int getNewEntries();
};

#endif