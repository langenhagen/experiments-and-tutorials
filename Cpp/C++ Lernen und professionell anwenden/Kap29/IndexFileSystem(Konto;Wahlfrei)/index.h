//////////////////////////////////////////////////////////////////////
// index.h enthält Klassen IndexEntry
//////////////////////////////////////////////////////////////////////
#ifndef _INDEX_H
#define _INDEX_H
#include <fstream>
#include <iostream>
#include "exceptio.h"
#include "Konto.h"
using namespace std;

//####################################################################################### IndexEntry
class IndexEntry
{
private:
		long key;								// Schlüssel
		long recNr;							// Offset in Datei
public:
	IndexEntry( long k=0, long n=0) : key(k), recNr(n)
	{}
																								// Zugriffsmethoden
	void setKey( long k) { key = k; }						
	void setPos( long n) { recNr = n; }
	long getKey() const { return key;}
	long getPos() const { return recNr;}

	int recordSize() const { return sizeof(key) + sizeof(recNr); }

	fstream& write( fstream& ind) const;
	fstream& read( fstream& ind);

	fstream& write_at( fstream& ind, long pos) const;
	fstream& read_at( fstream& ind, long pos);

	void display() const;
};

//####################################################################################### IndexFile
class IndexFile
{
private:
	fstream index;				// Die Datei selbst
	string name;					// Speichert den Namen der Datei
public:
	IndexFile( const string& file) throw( open_error);
	~IndexFile() { index.close(); }

	void insert( long key, long pos) throw( read_error, write_error);
	long search( long key) throw(read_error);
	void retrieve( IndexEntry& entry, long pos) throw( read_error);

	void display() const throw(read_error);
};

//####################################################################################### IndexFileSystem
class IndexFileSystem
{
private:
	KontoFile konten;					// Primärdatei
	IndexFile index;					// Indexdatei
public:
	IndexFileSystem( const string& name)
		: konten( name + ".prim"), index( name + ".ind") {}

	bool insert( const Konto& kto);
	Konto* retrieve( long key);

	void displayKontoFile() throw(read_error)
	{ konten.display(); }
	void displayIndexFile() throw(read_error)
	{ index.display(); }
};

#endif