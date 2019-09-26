/////////////////////////////////////////////////////////////////////////////
// List.h mit Klasse InhomList zur Darstellung einer Inhomogenen Liste
// der abgeleiteten Elemente von Cell
/////////////////////////////////////////////////////////////////////////////
#ifndef _LIST_H
#define _LIST_H
#include "Cell.h"

class InhomList
{
private:
	Cell* first;
protected:
	Cell* getPrev(const string& s);
	Cell* getPos( const string& s);

	void insertAfter(const string& s, Cell* prev);
	void insertAfter(const string& s, const string& b, Cell* prev);

	void erasePos(Cell* pos);
public:
	InhomList() { first = NULL; }
	InhomList( const InhomList& src );  //
	~InhomList();

	InhomList& operator=(const InhomList& src);  //
	
	void insert(const string& n);
	void insert(const string& n, const string& b);
	void erase( const string& n);

	void displayAll() const;
};

#endif