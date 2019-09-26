/////////////////////////////////////////////////////////////////////////////
// Cpp-Datei zur index.h
/////////////////////////////////////////////////////////////////////////////
#include "index.h"

//##################################################################################  IndexEntry - Methoden
// IndexEntry - Methoden

///////////////////////////////////////////////////////////////
// Liest einen IndexEntry ab einer bestimmten Position
fstream& IndexEntry::write_at(fstream &ind, long pos) const
{
	ind.seekp(pos);
	ind.write((char*)&key, sizeof(key));
	ind.write((char*)&recNr, sizeof(recNr));
	return ind;
}

///////////////////////////////////////////////////////////////
// Schreibt einen IndexEntry ab einer bestimmten Position
fstream& IndexEntry::read_at(fstream &ind, long pos)
{
	ind.seekg(pos);
	ind.read((char*)&key,sizeof(key));
	ind.read((char*)&recNr,sizeof(recNr));
	return ind;
}

//##################################################################################  IndexFile - Methoden
// IndexFile - Methoden

///////////////////////////////////////////////////////////////
// Konstruktor
IndexFile::IndexFile(const string& file)
{
	ios::openmode mode = ios::in | ios::out | ios::binary;
	index.open(file.c_str(), mode);

	if( !index)													// Falls Datei nicht existiert
	{
		index.clear();
		mode |= ios::trunc;
		index.open(file.c_str(), mode);
		if( !index)
			throw open_error(file);
	}
	name = file;
}

///////////////////////////////////////////////////////////////
// Ordnet einen neuen Indexeintrag in eine Indexdatei ein
void IndexFile::insert(long k, long n) throw(read_error, write_error)
{
	IndexEntry entry;
	int size = entry.recordSize();			// Länge des Indexeintrags

	index.clear();
	index.seekg(0, ios::end);
	long nr = index.tellg();
	
	if( !index)
		throw read_error(name);

	nr -= size;
	bool found = false;
	while( nr >=0 && !found)
	{
		if( !entry.read_at(index, nr))
			throw read_error(name);
		if( k < entry.getKey())
		{
			entry.write_at(index, nr + size);
			nr -= size;
		}
		else
		{ found = true; }
	}
	
	entry.setKey(k); entry.setPos(n);
	entry.write_at(index, nr + size);

	if(!index)
		throw write_error(name);
}

void IndexFile::retreive(IndexEntry &entry, long pos)
{
	index.clear();
	if( !entry.read_at(index,pos))
		throw read_error(name);
}

long IndexFile::search(long k)
{
	index.clear();
	IndexEntry entry;
	int size = entry.recordSize();
	long key, mid, begin=0, end;
	
	index.seekg(0, ios::end);

	end = index.tellg() / size;

	if( !index)
		throw read_error(name);

	if( end == 0)
		return -1;

	end -= 1;

	while(begin < end)
	{
		mid = (begin + end + 1) / 2;
		entry.read_at(index, mid*size);
		
		if( !index)
			throw read_error(name);
	key = entry.getKey();
	if(k < key)
		end = mid - 1;
	else
		begin = mid;
	}

	entry.read_at( index, begin*size);
	if( !index)
		throw read_error(name);

	if( k== entry.getKey())
		return begin*size;
	else
		return -1;
}

//##################################################################################  IndexFileSystem - Methoden

///////////////////////////////////////////////////////////////
// Fügt einen Kontoindex an eine Indexdatei an
bool IndexFileSystem::insert(const Konto &kto)
{
	if( index.search(kto.getNr() == -1))			// Keine Mehrfacheinträge
	{
		long pos = konten.append(kto);				// in Primärdatei einfügen
		if( pos != -1)
		{
			index.insert(kto.getNr(), pos);			// In Index einfügen		
			return true;
		}
	}
	return false;
}

///////////////////////////////////////////////////////////////
// Entimmt einen Datensatz aus einer Primärdatei
Konto* IndexFileSystem::retreive(long key)
{
	long pos = index.search(key);				// Byte Offset im Index
	if( pos == -1 )
		return NULL;
	else
	{
		IndexEntry entry;
		index.retreive(entry, pos);
		return konten.retreive( entry.getPos());
	}
}
