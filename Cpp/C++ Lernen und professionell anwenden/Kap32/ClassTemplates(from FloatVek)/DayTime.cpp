/////////////////////////////////////////////////////////////////////////////
// Cpp für Methoden der Klasse DayTime in DayTime.h 
/////////////////////////////////////////////////////////////////////////////
#include "DayTime.h"
#include <iostream>
#include <iomanip>

using namespace std;

void DayTime::swap( DayTime& t )
{
	DayTime temp(t);	t = *this;	*this = temp;
}

void DayTime::print()
{
	cout	<< setw(2) << setfill('0') << hour << ':'
				<< setw(2) << minute << ':'
				<< setw(2) << second;
}