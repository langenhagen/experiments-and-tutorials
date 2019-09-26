#include <iostream>
#include <iomanip>
using namespace std;

#define COLS 3
#define ROWS 11

// Matrizenfunktionen mit Zeigern.....................................................................

typedef int Row_t[COLS];			// Row_t: Zeilentyp einer Matrix

void init( Row_t pMat[], int nRows)			// initialisiert die Matrix komplett
{
	for( int i=0; i < nRows; i++)
		for( int j=0; j < COLS; j++)
			pMat[i][j] = i + j;
}

void display( const Row_t pMat[], int nRows)
{
	for( int i=0; i< nRows; i++)
	{
		for( int j=0; j < COLS; j++)
			cout << setw(4) << pMat[i][j] << ' ';
		cout << endl;
	}
}

// Matrizenfunktionen mit Referenzen..................................................................

typedef int Matrix_t[ROWS][COLS];		// Matrix_t: Typ der Matrix


void init( Matrix_t& rMat)					// initialisiert die Matrix komplett
{
	for( int i=0; i<ROWS; i++)
		for( int j=0; j<COLS; j++)
			rMat[i][j] = i + j;
}

void display( Matrix_t& rMat)
{
	for( int i=0; i<ROWS; i++)
	{
		for( int j=0; j<COLS; j++)
			cout << setw(4) << rMat[i][j] << ' ';
		cout << endl;
	}
}

// Die Main-Funktion.................................................................................

void main()
{
	Row_t matrix[11];

	cout << "Matrix per Zeiger auf die Matrix initialisieren und anzeigen\n\n";
	init( matrix, 11);	
	display( matrix, 11);

	Matrix_t matrix2;

	cout << "\nUnd jetzt zeigen wir eine Matrix ueber Referenzen an\n\n";
	init( matrix2);
	display( matrix2);
}
