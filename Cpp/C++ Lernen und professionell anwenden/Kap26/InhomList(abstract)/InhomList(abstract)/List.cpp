/////////////////////////////////////////////////////////////////////////////
// cpp-Datei mit Methoden der Klasse InhomList
/////////////////////////////////////////////////////////////////////////////
#include "List.h"
#include <typeinfo>

///////////////////////////////////////////////////////////////
// Kopierkonstruktor von InhomList
InhomList::InhomList(const InhomList &src)
{
	first = NULL;
	Cell *pEl = src.first;
	for( ; pEl != NULL; pEl = pEl->getNext())
		if( typeid(*pEl) == typeid(BaseEl) )
			insert( dynamic_cast<BaseEl*>(pEl)->getName() );
		else
			insert(	dynamic_cast<DerivedEl*>(pEl)->getName(),
							dynamic_cast<DerivedEl*>(pEl)->getBem() );
}

///////////////////////////////////////////////////////////////
// Zuweisungsoperator von InhomList
InhomList& InhomList::operator=(const InhomList &src)
{
	Cell	*pEl = first;
	while(pEl != NULL)
	{
		first = pEl->getNext();
		delete pEl;
		pEl = first;
	}
	first = NULL;
	
	for(pEl = src.first; pEl != NULL; pEl = pEl->getNext() )
		if( typeid(*pEl) == typeid(BaseEl) )
			insert( dynamic_cast<BaseEl*>(pEl)->getName() );
		else
			insert(	dynamic_cast<DerivedEl*>(pEl)->getName(),
							dynamic_cast<DerivedEl*>(pEl)->getBem() );
	return *this;
}



///////////////////////////////////////////////////////////////
// Destruktor der Klasse InhomList
InhomList::~InhomList()
{
	Cell	*pEl = first;
	while(pEl != NULL)
	{
		first = pEl->getNext();
		delete pEl;
		pEl = first;
	}
	first = NULL;
}

///////////////////////////////////////////////////////////////
// gibt das vorhergehde Listenelement zur�ck
Cell* InhomList::getPrev(const string &s)
{
	Cell	*pEl = first,
				*prev= NULL;
	while( pEl != NULL)
		if( s > dynamic_cast<BaseEl*>(pEl)->getName() )
		{ prev = pEl; pEl = pEl->getNext(); }
		else
			return prev;
	return prev;
}

///////////////////////////////////////////////////////////////
// F�gt Ein Element an der Stelle nach prev ein
void InhomList::insertAfter(const string &s, Cell *prev)
{
	if( prev == NULL )
		first = new BaseEl(first, s);
	else
	{
		Cell *p = new BaseEl(prev->getNext(), s);
		prev->setNext(p);
	}
}

///////////////////////////////////////////////////////////////
// F�gt Ein Element an der Stelle nach prev ein
void InhomList::insertAfter(const string &s, const string &b, Cell *prev)
{
	if( prev == NULL )
		first = new DerivedEl(first, s, b);
	else
	{
		Cell* p = new DerivedEl(prev->getNext(), s, b);
		prev->setNext(p);
	}
}

///////////////////////////////////////////////////////////////
// F�gt Ein Element angeordnet in die InhomList ein
void InhomList::insert(const string& n)
{
	Cell* prev = getPrev( n);
	insertAfter( n, prev );
}

///////////////////////////////////////////////////////////////
// F�gt Ein Element angeordnet in die InhomList ein
void InhomList::insert(const string& n, const string& b)
{
		Cell* prev = getPrev( n);
		insertAfter( n, b, prev);
}

///////////////////////////////////////////////////////////////
// Zeigt alle Listenelemente am Bildschirm an
void InhomList::displayAll() const
{
	Cell *c = first;
	for(; c!=NULL; c = c->getNext() )
		c->display();
}

///////////////////////////////////////////////////////////////
// Ermittelt die Position eines Listenelements
Cell* InhomList::getPos(const std::string &s)
{
	Cell *pEl = first;
	while( pEl != NULL)
	{
		if( s == dynamic_cast<BaseEl*>(pEl)->getName() )
			return pEl;
		else
			pEl = pEl->getNext();
	}
	return NULL;
}

///////////////////////////////////////////////////////////////
// L�scht ein Listenelement an der angegebenen Stelle
void InhomList::erasePos(Cell *pos)
{
	if( pos != NULL )
	{
		Cell* prev = getPrev(dynamic_cast<BaseEl*>(pos)->getName() );
		if( pos != first )
			prev->setNext( pos->getNext() );
		else
			first = pos->getNext();
		delete pos;
	}
}

///////////////////////////////////////////////////////////////
// L�scht ein angegebenes Listenelement
void InhomList::erase(const std::string &n)
{
	erasePos( getPos(n) );
}