/////////////////////////////////////////////////////////////////////////////
// Cpp-Datei Konto.cpp mit Methoden der Klasse Konto
/////////////////////////////////////////////////////////////////////////////
#include "konto.h"
#include <iostream>
#include <iomanip>
using namespace std;

//####################################################################################### Konto

///////////////////////////////////////////////////////////////
// Konstruktor der Klasse Konto
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
// Schreibt die Konto-Datenelemente in einen Stream
ostream& Konto::write( ostream& os) const
{
	os.write((char*)&name, sizeof(name));
	os.write((char*)&nr, sizeof(nr));
	os.write((char*)&stand, sizeof(stand));
	return os;
}

///////////////////////////////////////////////////////////////
// Liest Konto-Datenelemente aus einem Stream
istream& Konto::read( istream& is)
{
	is.read((char*)&name, sizeof(name));
	is.read((char*)&nr, sizeof(nr));
	is.read((char*)&stand, sizeof(stand));
	return is;
}

//####################################################################################### GiroKonto

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
// Schreibt die GiroKonto-Datenelemente in einen Stream
ostream& GiroKonto::write( ostream& os) const
{
	if( !Konto::write(os))
		return os;
	os.write((char*)&limit, sizeof(limit));
	os.write((char*)&soll, sizeof(soll));
	return os;
}

///////////////////////////////////////////////////////////////
// Liest GiroKonto-Datenelemente aus einem Stream
istream& GiroKonto::read( istream& is)
{
	if( !Konto::read( is)
		return is;
	is.read((char*)&limit, sizeof(limit));
	is.read((char*)&soll, sizeof(soll));
	return is;
}

//####################################################################################### SparKonto

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
// Schreibt die SparKonto-Datenelemente in einen Stream
ostream& SparKonto::write( ostream& os) const
{
	if( !Konto::write(os))
		return os;
	os.write((char*)&haben, sizeof(haben));
	return os;
}

///////////////////////////////////////////////////////////////
// Liest SparKonto-Datenelemente aus einem Stream
istream& SparKonto::read( istream& is)
{
	if( !Konto::read( is)
		return is;
	is.read((char*)&haben, sizeof(haben));
	return is;
}

//####################################################################################### KontoFile

///////////////////////////////////////////////////////////////
// Konstruktor der Klasse KontoFile
KontoFile::KontoFile(const std::string &str) throw( open_error)
{
	ios::openmode mode =	ios::in | ios::out | 					// ios::app wahlfreies lesen,
												ios::app | ios::binary ;			// schreiben am dateiende
																											// datei muss nich existieren
	file.open(str.c_str(), mode);
	if( !file)
		throw open_error(str);
	else
		name = str;
}

///////////////////////////////////////////////////////////////
// Hängt ein polymorphes Konto-Objekt an den Stream an
// und gibt die Position im Stream zurück
long KontoFile::append(const Konto &kto) throw( write_error)
{
	file.seekp( 0L, ios::end);
	long pos = file.tellp();
	if( !file) throw write_error(name);

	TypeId id = kto.getTypeId();

	file.write((char*)&id,sizeof(id));
	if( !file) throw write_error(name);
	else
		kto.write(file);
	if( !file) throw write_error(name);

	return pos;
}

///////////////////////////////////////////////////////////////
// Liest ein polymorphes Konto-Objekt aus einem Stream aus
// und gibt einen Zeiger auf das Objekt zurück
Konto* KontoFile::retreive(long pos) throw( read_error)
{
	file.seekg(pos);													// Auf pos positionieren
	if( !file) throw read_error(name);
	
	TypeId id;																
	file.read((char*)&id, sizeof(id));				// Id ermitteln
	if( !file) throw read_error(name);

	Konto* kto;
	switch( id)																// mithilfe der Id Objekt dynamisch erstellen
		{
		case KONTO: 
			kto = new Konto;
			break;
		case GIRO:
			kto = new GiroKonto;
			break;
		case SPAR:
			kto = new SparKonto;
			break;
		default: return NULL;
		}

	if( !(kto->read(file))) throw read_error(name);
	return kto;
}

///////////////////////////////////////////////////////////////
// Gibt die Daten eines KontoFile-Objektes formatiert 
//am Bildschirm aus
void KontoFile::display() const throw(read_error)
{
	file.clear();
	file.seekg( 0L, ios::end);
	if(!file) throw read_error(name);

	long	pos = 0L,
				max = file.tellg();
	if(!file) throw read_error(name);
	Konto* kto;

	if( max > 0L)
	{
		while( pos < max)
		{
			kto = retreive(pos);
			kto->display();

			switch( kto->getTypeId())									// pos um jeweilige Kontogröße erhöhen
			{
			case KONTO:
				pos += sizeof(KONTO) + sizeof(Konto);
				break;
			case GIRO:
				pos += sizeof(GIRO) + sizeof(GiroKonto);
				break;
			case SPAR:
				pos += sizeof(SPAR) + sizeof(SparKonto);
				break;
			}

		}
	}
}