/////////////////////////////////////////////////////////////////////////////
// cpp-Datei zur Header Liste.h
// Enthält Operatorüberladungen von den Klassen ListEl, Liste
// und die Konstruktoren, Destruktoren der Klasse Liste
/////////////////////////////////////////////////////////////////////////////
#include "Liste.h"

///////////////////////////////////////////////////////////////
// Out-Shift Operator von ListEl
ostream& operator<<( ostream& os, const ListEl& Element )
{
	os	<< Element.datum.asString() << ": "
			<< fixed << setprecision(2) << setw(15) << Element.betrag << " Euro";
	return os;
}

///////////////////////////////////////////////////////////////
// Destruktor der Klasse Liste
Liste::~Liste()
{
	ListEl	*pEl = first,
					*next = NULL;

	for( ; pEl != NULL; pEl = next )
	{
		next = pEl->next;
		delete pEl;
	}
}

///////////////////////////////////////////////////////////////
// Hängt ein neues Listenelement an die Liste an
void Liste::pushBack(const Datum &d, double b)
{
	ListEl *pEl = new ListEl( d, b);

	if( last == NULL )
		first = last = pEl;
	else
		last->next = pEl,	last = pEl;
}

///////////////////////////////////////////////////////////////
// Löscht das älteste Listenelement
void Liste::popFront()
{
	if( first != NULL )
	{
		ListEl *pEl = first;
		first = first->next;
		delete pEl;
		if( first == NULL )
			last = NULL;				// ...genial
	}
}

///////////////////////////////////////////////////////////////
// Out-Shift Operator von Liste, zeigt alle Listenelemente an
ostream& operator<<( ostream& os, const Liste& list )
{
	ListEl *pEl = list.getFront();

	for( ; pEl != NULL; pEl = pEl->getNext() )
		os << *pEl << endl;
	return os;
}
