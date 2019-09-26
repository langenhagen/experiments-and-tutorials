// Programm zur Vergleich der Geschwindigkeiten der
// QUICKSORT und SELECTIONSORT Algorithmen
#include <iostream>
#include <iomanip>
#include <string>
#include <cstdlib>
#include <time.h>
using namespace std;

//////////////////////////////////////////
// Funktion SelSort
void SelSort(int *vctr, int length)
{
	int i, temp, *sml, start = 0;
		
	for(start = 0; start<length; start++)
	{
		sml = vctr+start;
		for(i = start+1; i<length; i++)
			if(*sml > *(vctr+i))
				sml = vctr+i;
			temp						= *(vctr + start);
			*(vctr + start) =	*sml; 
			*sml						= temp;
	}
}

//////////////////////////////////////////
// Standardfunktion QuickSort
void qsort( void* arr, size_t n, size_t size, int (*compare)(const void*, const void*));

//////////////////////////////////////////
// Vergleichsfunkion comp
extern "C" int comp( const void* a, const void* b)
{	
	return (*(int*)a - *(int*)b);
}

/////////////////////////////////////////
//Main Funktion
void main()
{
	int n;
	string line( 80, '*');
	line = "\n\n" + line + '\n';
	time_t sek, sek2;

	cout << "Jo, Welcum!\n";
	cout << "Anzahl der zu sortierenden Zufallszahlen eingeben: "; cin >> n;
	cout << "...";

	int *v1, *v2;
	v1 = new int[n];	v2 = new int[n];
	
	for(int i=0; i<n;i++)
	{ v1[i] = v2[i] = rand() % 10000; }

	cout << "\n\nVector #1 unsortiert:\n";
	for(int i=0; i<n;i++)
	{ cout << setw(4) << v1[i] << " "; }
	
	cin.sync();
	cin.get();
	cout << line;

	cout << "Vector #2 unsortiert:\n";
	for(int i=0; i<n;i++)
	{ cout << setw(4) << v2[i] << " "; }
	
	cin.sync();
	cin.get();

	cout << line << "Verfahren: \"Selection Sort\"-Algorithmus:";
	
	time( &sek);
	SelSort( v1, n);
	time( &sek2);
	
	cout << "\n\nVector #1:\n";
	for(int i=0; i<n;i++)
	{ cout << setw(4) << v1[i] << " "; }
	sek = sek2-sek;
	cout << endl << "Beanspruchte Zeit: " << sek << " Sekunden";
	cin.sync();
	cin.get();

	cout << line << "Verfahren: \"Quick Sort\"-Algorithmus:";
	
	time( &sek);
	qsort( v2, n, sizeof(int), comp);
	time( &sek2);
	
	cout << "\nVector #2:\n";
	for(int i=0; i<n;i++)
	{ cout << setw(4) << v2[i] << " "; }
	sek = sek2-sek;
	cout << endl << "Beanspruchte Zeit: " << sek << " Sekunden\n";
	cin.sync();
	cin.get();


}