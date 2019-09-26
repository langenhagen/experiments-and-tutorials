/////////////////////////////////////////////////////////////////////////////
// Cpp-Datei FloatVek.cpp mit Methoden der Klasse FloatVek
/////////////////////////////////////////////////////////////////////////////
#include "FloatVek.h"

///////////////////////////////////////////////////////////////
// Konstruktor #1 von FloatVek
FloatVek::FloatVek(int n )
{
	max = n;		anz = 0;
	vekPtr = new float[max];
}

///////////////////////////////////////////////////////////////
// Konstruktor #2 von FloatVek
FloatVek::FloatVek(int n, float wert)
{
	max = anz = n;
	vekPtr = new float[max];
	for(int i=0; i<max; i++)
		*(vekPtr+i) = wert;
}

///////////////////////////////////////////////////////////////
// Kopierkonstruktor von FloatVek
FloatVek::FloatVek( const FloatVek &src )
{
	max = src.max;	anz = src.anz;
	vekPtr = new float[max];
	for( int i=0; i < anz; i++ )
		*(vekPtr+i) = src.vekPtr[i];
}

///////////////////////////////////////////////////////////////
// Destruktor von FloatVek
FloatVek::~FloatVek()
{
	delete[] vekPtr;
}

///////////////////////////////////////////////////////////////
// private Hilfsfunktion zum Vergrößern des Vektors
void FloatVek::expand( int neueGroesse )
{
	if(neueGroesse == max)	return;
	max = neueGroesse;
	float *ptrTemp = new float[max];
	
	if( max < anz )									// Hilfsabfrage zur Laufzeitverbesserung
		anz = max;
	for( int i=0; i < anz; i++ )
		*(ptrTemp+i) = *(vekPtr+i);
	delete[] vekPtr;
	vekPtr = ptrTemp;
}

///////////////////////////////////////////////////////////////
// Indexoperator [] für nicht const-Objekte
float& FloatVek::operator [](int i)
{
	if( i< 0 || i >= anz )
	{
		cerr << "\nClass FloatVek: Out of Range!";
		exit(1);
	}
	return vekPtr[i];
}

///////////////////////////////////////////////////////////////
// Indexoperator [] für  const-Objekte
float FloatVek::operator [](int i) const
{
	if( i< 0 || i >= anz )
	{
		cerr << "\nClass FloatVek: Out of Range!";
		exit(1);
	}
	return vekPtr[i];
}

///////////////////////////////////////////////////////////////
// Zuweisungsoperator = für FloatVek
FloatVek&	FloatVek::operator=( const FloatVek &src )
{
	if( this != &src )
	{
		max = src.max;
		anz = src.anz;
		delete[] vekPtr;
		vekPtr = new float[max];
		for( int i=0; i< anz; i++ )
			*(vekPtr+i) = src.vekPtr[i];
	}
	return *this;
}

///////////////////////////////////////////////////////////////
//  Operator += für FloatVek in der FLOAT-Überladung
FloatVek& FloatVek::operator+=( float wert )
{
	append(wert);
	return *this;
}

///////////////////////////////////////////////////////////////
//  Operator += für FloatVek in der FloatVek-Überladung
FloatVek& FloatVek::operator +=(const FloatVek &v)
{
	append(v);
	return *this;
}


///////////////////////////////////////////////////////////////
// Hängt Elemente an FloatVek an
void FloatVek::append(float wert)
{
	if( anz < max )
		vekPtr[anz++] = wert;
	else
	{ expand( max + 10 );		vekPtr[anz++] = wert; }
}

///////////////////////////////////////////////////////////////
// Hängt einen anderen FloatVek an FloatVek an
void FloatVek::append( const FloatVek &v )
{
	int	i = anz, j=0;
			anz += v.anz;
	expand(anz);
	for( ; j < v.anz; i++, j++ )
		*(vekPtr+i) = *(v.vekPtr+j);
}
///////////////////////////////////////////////////////////////
// Löscht einen Wert des Vektors an der Stelle pos
bool FloatVek::remove(int pos)
{
	if( pos >= 0 && pos < anz )
	{
		for( int i = pos; i < anz-1; i++ )
			vekPtr[i] = vekPtr[i+1];
		anz--;
		return true;
	}
	return false;
}

///////////////////////////////////////////////////////////////
// Fügt einen Wert wert an der Stelle pos ein
bool FloatVek::insert(float wert, int pos)
{
	if( pos < 0 || pos >= anz )
		return false;
	if(anz == max)
		expand(max + 10);

	for( int i=anz; i>pos; i-- )
		*(vekPtr+i) = *(vekPtr+i-1);
	vekPtr[pos]=wert;
	anz++;
	return true;
}

///////////////////////////////////////////////////////////////
// Fügt einen FloaVek an der Stelle pos ein
bool FloatVek::insert(const FloatVek &v, int pos)
{
	if( pos < 0 || pos >= anz )
		return false;
	anz += v.anz;
	int	i = anz;
	expand(anz);

	for( ; i>pos; i--)
		*(vekPtr+i) = *(vekPtr + i - v.anz);
	for( i=0; i<v.anz; i++)
		*(vekPtr+i+pos) = v.vekPtr[i];
	return true;
}

///////////////////////////////////////////////////////////////
// Shift-Out Operator der Klasse FloatVek
ostream& operator<<(ostream& os, const FloatVek &v)
{
	cout	<< "\nLaenge: " << v.anz << "\t Max. Laenge: " << v.max << endl
				<< setw(4) << "Index" << setw(9) << "Wert" << endl;

	for(int i=0; i < v.anz; i++)
		cout << setw(4) << i << ": " << setw(8) << v.vekPtr[i] << endl;
	return os;			
}
