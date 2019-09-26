#include <iostream>
#include <stdlib.h>				// für rand() , srand() 
#include <time.h>					// für den absoluten Zufallskick...
using namespace std;

void SelSortind(int Vector[], int length);
void SelSortzeig(int *Vector, int length);

void main()
{
	time_t sek;							// Variable sek..
	time( &sek);						// Sekunden in sek einschreiben
	srand((unsigned)sek);		// Mit sek Generator initialisieren

	int vctr[50];
	
	for(int i=0; i<50; i++)
		vctr[i]= rand() % 2000 - 1000;

	for(int i=0; i<50; i++)
		cout << vctr[i] << endl;

	cout << endl << "###################################################" << endl;

	SelSortzeig(vctr,50);

	for(int i=0; i<50; i++)
		cout << vctr[i] << endl;

}

void SelSortind(int Vector[], int length)
{
	int i, temp, *sml, start = 0;
		
	for(start = 0; start<length; start++)
	{
		sml = &Vector[start];
		for(i = start+1; i<length; i++)
			if(*sml > Vector[i])
				sml = &Vector[i];
			temp					= Vector[start];
			Vector[start] =	*sml; 
			*sml					= temp;
	}
}

void SelSortzeig(int *vctr, int length)
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