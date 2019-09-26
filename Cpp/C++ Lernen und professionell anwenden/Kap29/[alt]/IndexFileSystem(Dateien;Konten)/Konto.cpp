/////////////////////////////////////////////////////////////////////////////
// Cpp-Datei Konto.cpp mit Methoden der Klasse Konto
/////////////////////////////////////////////////////////////////////////////
#include "konto.h"
#include <iostream>
#include <iomanip>
using namespace std;

//##################################################################################  Konto

///////////////////////////////////////////////////////////////
// Konstruktor des Objektes Konto
Konto::Konto( const string& i_name, unsigned long i_nr, double i_stand)
{
	name = "Unknown, Unknown";
	if( i_name.size() > 0)
		name = i_name;
	nr = i_nr;
	stand = i_stand;
}

///////////////////////////////////////////////////////////////
// Gibt die Daten vom Konto-Objet am Bildschirm aus
void Konto::display() const
{
	cout	<< fixed << setprecision(2)
				<< "\n---------------------------------------------\n"
				<<   "Kontoinhaber:  " << name << endl
				<<   "Kontonummer:   " << nr << endl
				<<   "Kontostand:    " << stand << " Euro" << endl
				<<   "---------------------------------------------\n\n";
}

///////////////////////////////////////////////////////////////
// Schreibt ein Konto Objekt in einen Stream
ostream& Konto::write(ostream& os) const
{
	os.write((char*)&name, sizeof(name));
	os.write((char*)&nr, sizeof(nr));
	os.write((char*)&stand, sizeof(stand));
	return os;
}

///////////////////////////////////////////////////////////////
// Liest ein Konto Objekt aus einen Stream
istream& Konto::read(istream& is)
{
	is.read((char*)&name, sizeof(name));
	is.read((char*)&nr, sizeof(nr));
	is.read((char*)&stand, sizeof(stand));;
	return is;
}

//##################################################################################  GiroKonto

///////////////////////////////////////////////////////////////
// Gibt die Daten von GiroKonto-Objeten am Bildschirm aus
void GiroKonto::display() const
{
	cout	<< fixed << setprecision(2)
				<< "\n---------------------------------------------\n"
				<<   "Kontoinhaber:        " << getName() << endl
				<<   "Kontonummer:         " << getNr() << endl
				<<   "Kontostand:          " << getStand() << " Euro" << endl
				<<   "Ueberziehungslimit:  " << limit << " Euro" << endl
				<<   "Soll-Zinssatz:       " << soll << " %" << endl
				<<   "---------------------------------------------\n\n";
}

///////////////////////////////////////////////////////////////
// Schreibt ein GiroKonto Objekt in einen Stream
ostream& GiroKonto::write(ostream& os) const
{
	if( !Konto::write( os))
		return os;
	os.write((char*)&limit, sizeof(limit));
	os.write((char*)&soll, sizeof(soll));
	return os;
}

///////////////////////////////////////////////////////////////
// Liest ein GiroKonto Objekt aus einen Stream
istream& GiroKonto::read(istream& is)
{
	if( !Konto::read( is))
		return is;
	is.read((char*)&limit, sizeof(limit));
	is.read((char*)&soll, sizeof(soll));
	return is;
}

//##################################################################################  SparKonto

///////////////////////////////////////////////////////////////
// Gibt die Daten von SparKonto-Objeten am Bildschirm aus
void SparKonto::display() const
{
	cout	<< fixed << setprecision(2)
				<< "\n---------------------------------------------\n"
				<<   "Kontoinhaber:    " << getName() << endl
				<<   "Kontonummer:     " << getNr() << endl
				<<   "Kontostand:      " << getStand() << " Euro" << endl
				<<   "Haben-Zinssatz:  " << haben << " %" << endl
				<<   "---------------------------------------------\n\n";
}

///////////////////////////////////////////////////////////////
// Schreibt ein SparKonto Objekt in einen Stream
ostream& SparKonto::write(ostream& os) const
{
	if( !Konto::write( os))
		return os;
	os.write((char*)&haben, sizeof(haben));
	return os;
}

///////////////////////////////////////////////////////////////
// Liest ein SparKonto Objekt aus einen Stream
istream& SparKonto::read(istream& is)
{
	if( !Konto::read( is))
		return is;
	is.read((char*)&haben, sizeof(haben));
	return is;
}

//##################################################################################  KontoFile

///////////////////////////////////////////////////////////////
// Hängt ein Konto an die Datei an
long KontoFile::append(const Konto &kto) throw(write_error)
{
	f.seekp(0L, ios::end);				// Auf Dateiende positionieren
	long pos = f.tellp();

	if( !f)
		throw write_error(name);

	TypeId id = kto.getTypeId();		// Id-Typ erfassen

	f.write( (char*)&id, sizeof(id));

	if(!f)
		throw write_error(name);
	else
		kto.write(f);
	if(!f)
		throw write_error(name);
	return pos;
}
