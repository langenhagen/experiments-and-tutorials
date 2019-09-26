/////////////////////////////////////////////////////////////////////////////
// Header-Datei matrix.h für die Klassen Zeile und Matrix 
// zur Darstellung von 2-dimensionalen dynamischen Matrizen
/////////////////////////////////////////////////////////////////////////////
#include <iostream>
#include <iomanip>
using namespace std;

//####################################################################################### Zeile
// Klasse Zeile zur Darstellung eines eindimensionalen double-Vektors
class Zeile
{
	double *z;			// Erstes Vektorelement
	int size;				// Spaltenzahl
public:
	Zeile( int s) { size = s; z = new double[s]; }			// Konstruktor
	~Zeile() { delete[] z; }														// Destruktor

	int getSize() const { return size; }								// Gibt Anz. der Zeilenelemente wieder
	void display() const;

	double& operator[](int i) throw(out_of_range);
	const	double& operator[](int i) const throw(out_of_range);
	Zeile& operator+=( const Zeile& line)	throw(out_of_range);
};

//####################################################################################### Matrix
// Klasse Matrix zur Darstellung einer double-Matrix

class Matrix
{
	Zeile **mat;							// Zeiger auf 'Zeilen(-Zeiger)'-Vektor O,ô
	int lines, cols;					// Zeilen, Spaltenzahl
public:
	Matrix( int z, int s);
	Matrix( int z, int s, double val);
	Matrix( Matrix& m);
	~Matrix();

	int getLines()	const { return lines; }				// gibt Zeilenzahl wieder
	int getCols()		const { return cols; }				// gibt Spaltenzahl wieder
	void display() const;
	
	Matrix& operator=( const Matrix& m);
	Matrix& operator+=(const Matrix& m)	throw(out_of_range);
	Zeile& operator[](int i) throw(out_of_range);
	const	Zeile& operator[](int i) const throw(out_of_range);
};