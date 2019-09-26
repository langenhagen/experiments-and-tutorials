/////////////////////////////////////////////////////////////////////////////
// Cell.h mit den Klassen Cell und den davon abgeleiteten Klassen
// BaseEl und DerivedEl
/////////////////////////////////////////////////////////////////////////////
#ifndef _CELL_
#define _CELL_
#include <string>
#include <iostream>
using namespace std;

class Cell
{
private:
	Cell* next;
protected:
	Cell(Cell* suc = NULL) { next = suc; }
public:
	virtual ~Cell() {}

	Cell* getNext() const { return next; }
	void setNext(Cell* n) { next = n; }
	
	virtual void display() const = 0;
};

class BaseEl : public Cell
{
private:
	string name;
public:
	BaseEl( Cell *suc = NULL, const string& s ="")
		: Cell(suc), name(s) {}

	string getName() const { return name; }
	void setName(const string& s="") { name = s; }

	void display() const
	{
		cout	<< "-----------------------------------------\n"
					<< name << endl;
	}
};

class DerivedEl : public BaseEl
{
private:
	string bem;
public:
	DerivedEl(Cell* suc = NULL, const string& s="", const string& b="")
		: BaseEl( suc, s), bem(b) {}

	string getBem() const { return bem; }
	void setBem(const string& s="") { bem = s; }

	void display() const
	{
		cout	<< "-----------------------------------------\n"
					<< getName() << "\n  " << bem << endl;
	}
};
#endif