/////////////////////////////////////////////////////////////////////////////
// Header-Datei index.h mit den Klassen IndexEntry, IndexFile
/////////////////////////////////////////////////////////////////////////////
#ifndef _INDEX_H
#define _INDEX_H
#include <fstream>
#include <iostream>
#include <string>
#include "Konto.h"
#include "exceptio.h"
using namespace std;

class IndexEntry
{
private:
	long key;									// Schlüssel
	long recNr;								// Offset
public:
	IndexEntry(long k=0L, long n=0L)
	{ key = k; recNr = n; }

	int recordSize() const
	{ return sizeof(key) + sizeof(recNr); }
	
	long getKey() const
	{ return key; }
	long getPos() const
	{ return recNr; }
	void setKey( long k) { key = k; }
	void setPos( long p) { recNr = p; }

	fstream& write( fstream& ind) const;
	fstream& read( fstream& ind);

	fstream& write_at( fstream& ind, long pos) const;
	fstream& read_at( fstream& ind, long pos);
};

class IndexFile
{
private:
	fstream index;
	string name;
public:
	IndexFile( const string& s);
	~IndexFile()
	{ index.close(); }

	void insert( long key, long pos) throw(read_error, write_error);
	long search( long key) throw(read_error);
	void retreive( IndexEntry& entry, long pos) throw(read_error);
};

class IndexFileSystem
{
private:
	KontoFile konten;				// Primärdatei
	IndexFile index;				// Indexdatei
public:
	IndexFileSystem(const string& s) : konten( s + ".prim"), index( s + ".ind") {}
	
	bool insert( const Konto& kto);
	Konto* retreive( long key);
};

#endif