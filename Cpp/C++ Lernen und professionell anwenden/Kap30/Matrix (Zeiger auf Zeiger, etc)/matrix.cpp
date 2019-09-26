/////////////////////////////////////////////////////////////////////////////
// cpp-Datei der matrix.h für die Klassen Zeile und Matrix 
// zur Darstellung von 2-dimensionalen dynamischen Matrizen
/////////////////////////////////////////////////////////////////////////////
#include "matrix.h"

//####################################################################################### Zeile
// Klasse Zeile zur Darstellung eines eindimensionalen double-Vektors

/////////////////////////////////////////////////
// Operator []
double& Zeile::operator[](int i) throw(out_of_range)
{
	if( i<0 || i>=size )
		throw out_of_range("Spaltenindex: Out of Range\n");
	else
		return z[i];
}

/////////////////////////////////////////////////
// const Operator []
const	double& Zeile::operator[](int i) const throw(out_of_range)
{
	if( i<0 || i>=size )
		throw out_of_range("Spaltenindex: Out of Range\n");
	else
		return z[i];
}

/////////////////////////////////////////////////
// Zuweisungsoperator +=
Zeile& Zeile::operator+=( const Zeile& line)	throw(out_of_range)
{
	int sz = line.getSize();

	if( size < sz)
		throw out_of_range("Fehler: Zeile - Operation nicht durchfuehrbar - Out Of Range");

	for(int i=0; i<sz; i++)
		z[i] += line[i];
}

/////////////////////////////////////////////////
// gibt alle Zeilenelemente formatiert aus
void Zeile::display() const
{
	for(int i=0; i<size; i++)
		cout << setw(5) << z[i] << " ";
	cout << endl;
}

//####################################################################################### Matrix
// Klasse Matrix zur Darstellung einer double-Matrix

/////////////////////////////////////////////////
// Konstruktor #1
Matrix::Matrix( int z, int s)
	{
		lines = z; cols = s;
		mat = new Zeile*[lines];
		for( int i=0; i< lines; i++)
			mat[i] = new Zeile(cols);
	}

/////////////////////////////////////////////////
// Konstruktor #2
Matrix::Matrix( int z, int s, double val)
{
	lines = z; cols = s;
	mat = new Zeile*[lines];
	for( int i=0; i< lines; i++)
	{
		mat[i] = new Zeile(cols);
		for(int j=0; j<cols; j++)
			(*this)[i][j] = val;
	}
}

/////////////////////////////////////////////////
// Kopierkonstruktor (Konstruktor #3)
Matrix::Matrix( Matrix& m)
{
	lines = m.getLines(); cols = m.getCols();
	mat = new Zeile*[lines];
	for( int i=0; i< lines; i++)
	{
		mat[i] = new Zeile(cols);
		for(int j=0; j<cols; j++)
			(*this)[i][j] = m[i][j];
	}
}
/////////////////////////////////////////////////
// Destruktor
Matrix::~Matrix()
{
	for( int i=0; i<lines; i++)
		delete mat[i];
	delete[] mat;
}

/////////////////////////////////////////////////
// Zuweisungsoperator =
Matrix& Matrix::operator =( const Matrix& m)
{
	(*this).~Matrix();
	
	lines = m.getLines(); cols = m.getCols();
	mat = new Zeile*[lines];
	for( int i=0; i< lines; i++)
	{
		mat[i] = new Zeile(cols);
		for(int j=0; j<cols; j++)
			(*this)[i][j] = m[i][j];
	}
	return (*this);
}

/////////////////////////////////////////////////
// Zuweisungsoperator +=
Matrix& Matrix::operator +=(const Matrix& m)	throw(out_of_range)
{
	int zl = m.getLines(), sp = m.getCols();
	
	if( lines < zl || cols < sp)
		throw out_of_range("Fehler: Matrix - Operation nicht durchfuehrbar - Out Of Range\n");

	for(int i=0; i<zl; i++)
		for(int j=0; j<sp; j++)
			(*this)[i][j] += m[i][j];
	return *this;
}

/////////////////////////////////////////////////
// Operator []
Zeile& Matrix::operator[](int i) throw(out_of_range)
{
	if( i<0 || i>=lines)
		throw out_of_range("Zeilenindex: Out of Range\n");
	else
		return *mat[i];
}

/////////////////////////////////////////////////
// const Operator []
const	Zeile& Matrix::operator[](int i) const throw(out_of_range)
{
	if( i<0 || i>=lines)
		throw out_of_range("Zeilenindex: Out of Range\n");
	else
		return *mat[i];
}

/////////////////////////////////////////////////
// gibt alle Matrixelemente formatiert aus
void Matrix::display() const
{
	for(int i=0; i<lines; i++)
		(*this)[i].display();
}