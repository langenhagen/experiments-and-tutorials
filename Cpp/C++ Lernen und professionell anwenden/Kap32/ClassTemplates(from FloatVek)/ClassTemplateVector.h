/////////////////////////////////////////////////////////////////////////////
// Header-Datei ClassTemplateVector.h mit der Klasse 
/////////////////////////////////////////////////////////////////////////////
#ifndef _VectorTemplate_
#define _VectorTemplate_
#include <iostream>
#include <iomanip>
using namespace std;

//####################################################################################### BadIndex
//////////////////////////////////////////////////////////////////////////////
// Fehlerklasse BadIndex
class BadIndex
{
private:
	int index;
public:
	BadIndex( int i) : index(i) {}
	int getBadIndex() const { return index; }
};

//####################################################################################### OutOfRange
//////////////////////////////////////////////////////////////////////////////
// Fehlerklasse OutOfRange
class OutOfRange
{};

//####################################################################################### Vektor<T,n>
//////////////////////////////////////////////////////////////////////////////
// Klassen-Template Vektor<T,n>
template <class T, int n = 255>
class Vector
{
private:
	T vekPtr[n];
	int anz;

public:
	Vector();																												// Konstruktoren
	Vector(int num, T val);

	int				size() const { return n; }																// Sonstige Methoden
	int				length() const { return anz; }

	void			append(T val) throw(OutOfRange);											// Anfügen, Einfügen, Löschen
	void			append(const Vector &v) throw(OutOfRange);
	void			insert(T wert, int pos) throw(OutOfRange);
	void			insert(const Vector &v, int pos) throw(BadIndex, OutOfRange);
	void			remove(int pos) throw(BadIndex);
	
	T&				operator[]( int i) throw(BadIndex);								// Operatoren
	T					operator[]( int i) const throw(BadIndex);					// !
};

/////////////////////////////////////////////////
// Default-Konstruktor von Vector<T,n>
template<class T, int n>
Vector<T,n>::Vector()
{
		for( int i=0; i < n; i++)
			vekPtr[i]=0;
		anz = n;
}

/////////////////////////////////////////////////
// Konstruktor #2 von Vector<T,n>
template<class T, int n>
Vector<T,n>::Vector(int num, T val)
{
	int i=0;
	for(; i<num && i < n; i++)
		vekPtr[i]=val;
	anz=i;
}

///////////////////////////////////////////////////////////////
// Hängt Elemente an Vector<T,n> an
template<class T, int n>
void Vector<T,n>::append(T val) throw(OutOfRange)
{
	if( anz < n )
		vekPtr[anz++] = val;
	else
		throw OutOfRange();
}

///////////////////////////////////////////////////////////////
// Hängt einen Vector<T,n> an das Objekt an
template<class T, int n>
void Vector<T,n>::append( const Vector &v ) throw(OutOfRange)
{
	if( (anz + v.length()) < n)
	{
		for(int i=0 ; i < v.anz;i++ )
			*(vekPtr+anz+i) = *(v.vekPtr+i);
		anz = anz+v.length();
	}
	else
		throw OutOfRange;
}

///////////////////////////////////////////////////////////////
// Fügt einen Wert wert an der Stelle pos ein
template<class T, int n>
void Vector<T,n>::insert(T wert, int pos) throw(BadIndex)
{
	if( pos < 0 || pos >= n)
		throw BadIndex(pos);
	else
	{
		vekPtr[pos]=wert;
		if( anz < pos)
			anz= pos+1;
	}
}

///////////////////////////////////////////////////////////////
// Fügt einen Vector<T,n> an der Stelle pos ein
template<class T, int n>
void Vector<T,n>::insert(const Vector<T,n> &v, int pos) throw(BadIndex, OutOfRange)
{
	if(pos<0 || pos>=n)
		throw BadIndex( pos);

	if( pos+v.length() >= n )
		throw OutOfRange;
	anz += v.anz;
	int	i = anz;

	for( ; i>pos; i--)
		*(vekPtr+i) = *(vekPtr + i - v.anz);
	for( i=0; i<v.anz; i++)
		*(vekPtr+i+pos) = v.vekPtr[i];
}

///////////////////////////////////////////////////////////////
// Löscht ein Element an der Stelle pos
template<class T, int n>
void Vector<T, n>::remove(int pos) throw(BadIndex)
{
	if( pos >= 0 && pos < anz )
	{
		for( int i = pos; i < anz-1; i++ )
			vekPtr[i] = vekPtr[i+1];
		anz--;
	}
	else
		throw BadIndex(pos);
}

/////////////////////////////////////////////////
// Index-Operator [] für Vector
template<class T, int n>
T& Vector<T,n>::operator[](int i) throw(BadIndex)
{
	if( i<0 || i>anz)
		throw BadIndex(i);
	else
		return vekPtr[i];
}

/////////////////////////////////////////////////
// const Index-Operator [] für Vector
template<class T, int n>
T Vector<T,n>::operator[](int i) const throw(BadIndex)
{
	if( i<0 || i>anz)
		throw BadIndex(i);
	else
		return vekPtr[i];
}

/////////////////////////////////////////////////
// ShiftOut-Operator für Vector als Friend-Funktion
template<class T, int n>
ostream& operator<<(ostream &os, const Vector<T,n> &v)
{
	cout	<< "\nLaenge: " << v.length() << "\t Max. Laenge: " << v.size() << endl
				<< setw(4) << "Index" << setw(9) << "Wert" << endl;

	for(int i=0; i < v.length(); i++)
		cout << setw(4) << setfill(' ') << i << ": " << setw(8) << v[i] << endl;
	return os;			
}

#endif