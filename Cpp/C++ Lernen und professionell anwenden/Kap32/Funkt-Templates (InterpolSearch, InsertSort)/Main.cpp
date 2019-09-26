#include <iostream>
#include <iomanip>
#include "search.h"
using namespace std;

#define MAX		5
#define MVAL  17

template <class T>
void insertSort(T* vp, unsigned int vSize);

template <class T>
void display(T *v, int vSize);

/////////////////////////////////////////////////
// main()-Funktion
void main()
{
	double vector[MAX], x;
	for(int i=0; i<MAX; i++)
	{	
		cout << "Zahl " << i+1 << ": ";
		cin >> x;
		vector[i]=x;
	}

	display(vector, MAX);

	cout << "\nSortiere...\n\n";
	insertSort(vector, MAX);

	display(vector, MAX);

	char a;
	cout << "Moechten sie eine Zahl suchen? (j fuer ja) ";
	cin.sync(); cin.get(a);
	
	while( a == 'j' || a == 'J')
	{
		double y; int i;
		cout << "Geben sie die gesuchte Zahl ein: ";
		cin >> y; cout << "\n...\n";

		if ( (i = interpolSearch( y, vector, MAX)) == -1)
			cout << "Zahl nicht gefunden!";
		else
			cout << "Die Zahl " << y << " ist an Indexposition " << i << endl;

		cout << "\nNoch eine Zahl suchen? ";
		cin.sync(); cin.get(a);
	}

	cout << "Good Bye!!!\n";
	cin.sync();
	cin.get();
}

/////////////////////////////////////////////////
// Funktions-Template insertSort()
// zur aufsteigenden Sortierung von Zahlen in
// einen Vektor
template <class T>
void insertSort( T *vp, unsigned int vSize)
{
	int sorted=0, cur;
	T temp;
	vSize--;

	while( sorted < vSize)										// Gesamte Sortierschleife
	{
		temp = vp[++sorted];
		cur = sorted;

		while( --cur >= 0 && vp[cur] > temp )		// Zahlen verschieben
			vp[cur+1] = vp[cur];
				
		vp[cur+1] = temp;												// Zahl einsetzen
	}
}

/////////////////////////////////////////////////
// Funktions-Template display()
// zeigt einen numerischen Vektor 
// formatiert am Bildschirm an
template <class T>
void display( T *v, int vSize)
{
	cout << "\nVektor mit " << vSize << " Elementen:\n";
	
	for(int i=0; i < vSize; i++)
		cout << "vektor[" << setw(4) << setfill('.') << i << "] : \t" 
			<< *(v+i) << endl;
}


//###########################################################################################
// Schniekel Schnakel
//###########################################################################################
//###########################################################################################
/*
	int vec[MAX];


	for( int i=0; i<MAX; i++)
		vec[i] = i+MVAL;

	for(int i=0; i<= MVAL+MAX; i++)
		cout	<< setw(2) <<  i << setw(6) << interpolSearch( i, vec, MAX)
					<< endl;


*/