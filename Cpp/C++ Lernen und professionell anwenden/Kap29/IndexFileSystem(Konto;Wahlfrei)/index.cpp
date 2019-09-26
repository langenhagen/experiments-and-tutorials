//////////////////////////////////////////////////////////////////////
// index.cpp enthält Methoden der Klassen IndexEntry
//////////////////////////////////////////////////////////////////////
#include "index.h"

//####################################################################################### IndexEntry

/////////////////////////////////////////////////////////////////////////
// Schreibt einen Indexeintrag in eine bestimmte Stelle im fstream
fstream& IndexEntry::write_at( fstream& ind, long pos) const
{
	ind.seekp(pos);
	ind.write((char*)&key, sizeof(key));
	ind.write((char*)&recNr, sizeof(recNr));
	return ind;
}

/////////////////////////////////////////////////////////////////////////
// Liest einen Indexeintrag an einer bestimmten Stelle aus dem fstream aus
fstream& IndexEntry::read_at( fstream& ind, long pos)
{
	ind.seekp(pos);
	ind.read((char*)&key, sizeof(key));
	ind.read((char*)&recNr, sizeof(recNr));
	return ind;
}

/////////////////////////////////////////////////////////////////////////
// Schreibt einen Indexeintrag in den fstream
fstream& IndexEntry::write( fstream& ind) const
{
	ind.write((char*)&key, sizeof(key));
	ind.write((char*)&recNr, sizeof(recNr));
	return ind;
}

/////////////////////////////////////////////////////////////////////////
// Liest einen Indexeintrag aus dem fstream aus	
fstream& IndexEntry::read( fstream& ind)
{
	ind.read((char*)&key, sizeof(key));
	ind.read((char*)&recNr, sizeof(recNr));
	return ind;
}

/////////////////////////////////////////////////////////////////////////
// Zeigt die Daten eines IndexEntry formatiert am Bildschirm an
void IndexEntry::display() const
{
	cout	<< "\n---------------------------------------------\n"
				<<   "Schluessel:   " << key
				<< "\nDatei-Offset: " << recNr << endl
				<<   "---------------------------------------------\n\n";
}

//####################################################################################### IndexFile

/////////////////////////////////////////////////////////////////////////
// Konstruktor der Klasse IndexFile
IndexFile::IndexFile( const string& file) throw(open_error)
{
	ios::openmode mode = ios::in | ios::out | ios::binary;
	index.open( file.c_str(), mode);

	if(!index)
	{
		index.clear();
		mode |= ios::trunc;
		index.open( file.c_str(), mode);
		if(!index)
			throw open_error(file);
	}
	name = file;
}

/////////////////////////////////////////////////////////////////////////
// Fügt einen Schlüssel und einen Byte-Offset sortiert in den Stream ein
void IndexFile::insert(long key, long pos) throw( read_error, write_error)
{
	IndexEntry entry;
	int size = entry.recordSize();						// Länge des Indexeintrages

	index.clear();														
	index.seekg(0, ios::end);
	long nr = index.tellg();									// Dateilänge ermitteln
	if( !index) throw read_error(name);

	nr -= size;																// letzter Eintrag
	bool found = false;
	while( nr>=0 && !found)
	{
		if( entry.read_at(index,nr))
			throw read_error(name);

		if( key < entry.getKey())
		{
			entry.write_at(index, nr+size);
			nr -= size;
		}
		else
			found = true;
	}

	entry.setKey(key); entry.setPos(pos);
	entry.write_at( index, nr+size);
	if( !index) throw write_error(name);
}

/////////////////////////////////////////////////////////////////////////
// Schreibt Datenelemente an der Stelle pos aus einem Stream
// in den als Argument übergebenen IndexEntry
void IndexFile::retrieve(IndexEntry &entry, long pos) throw(read_error)
{
	index.clear();
	if( ! entry.read_at( index, pos) )
		throw read_error(name);
}

/////////////////////////////////////////////////////////////////////////
// Gibt die Stelle zum gesuchten Indexeintrag in zurück
long IndexFile::search(long key) throw(read_error)
{
	index.clear();

	IndexEntry entry;
	int size = entry.recordSize();

	index.seekg(0L, ios::end);
	if( !index) throw read_error(name);
	
	long	dwn = 1,
				up = (index.tellg() / size);
	if( !index) throw read_error(name);

	while( dwn <= up )										// Suchschleife
	{
		long mid = ((up - dwn) / 2) + dwn -1 ;		// mid berechnen					!!!!!!!!!!!!!!

		entry.read_at( index, mid * size );
		if( !index) throw read_error(name);

		if( key == entry.getKey() )
			return entry.getPos();
		else
			if( key < entry.getKey() )						// kleiner als
				up = mid-1;
			else																	// größer als
				dwn = mid +1;
	}
	return -1;
}

/////////////////////////////////////////////////////////////////////////
// Zeigt die Daten eines IndexFile-Objektes formatiert am Bildschirm an
void IndexFile::display() const throw( read_error)
{
	IndexEntry temp;
	index.seekg(0L , ios::end);
	long max = index.tellg();
	if(!index) throw read_error(name);
	
	long pos = 0;
	
	for(; pos < max; pos += temp.recordSize()) 
	{
		retrieve( temp, pos);
		temp.display();
	}
}

//####################################################################################### IndexFileSystem

/////////////////////////////////////////////////////////////////////////
// Fügt ein polymorphes Konto-Objekt in eine Datei ein und schreibt einen
// Indexeintrag in die zugehörige Indexdatei
bool IndexFileSystem::insert(const Konto &kto)
{
	if( index.search( kto.getNr()) == -1 )
	{
		long pos = konten.append(kto);		// In Primärdatei schreiben
		if( pos != -1)
		{
			index.insert( kto.getNr(), pos);
			return true;
		}
	}
	return false;
}

/////////////////////////////////////////////////////////////////////////
// Liest einen ein Konto-Datensatz mithilfe einer Indexdatei 
// aus einem Stream aus
Konto* IndexFileSystem::retrieve(long key)
{
	long pos = index.search(key);				// Byte-Offset im Index
	
	if( pos == -1)											// KontoNr gefunden?
		return NULL;
	else																// wenn ja...
	{
		IndexEntry entry;									// Indexeintrag lesen
		index.retrieve( entry, pos);
		
		return konten.retreive( entry.getPos());		// Datensatz aus Primärdatei holen
	}
}