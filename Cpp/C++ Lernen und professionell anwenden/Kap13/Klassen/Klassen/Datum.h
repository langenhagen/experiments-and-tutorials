/*	Header-Datei mit der Klasse Datum	*/

#ifndef _DATUM_
#define _DATUM_

#include <ctime>			//Notwendige Inkludierungen für die Methoden von "Datum"
#include <iostream>

class Datum																//Klasse Datum
{
	private:																					//PRIVATE
		short day, month, year;

	public:																						//PUBLIC
		void init(short tag, short monat, short jahr);
		void init(void);
		void print(void);
};

#endif