// der Source zur Seite 802 / 803
// In Sets werden die Daten jut sortiert gespeichert, wie bei der Kompilierung auffällt...
#include <iostream>
#include <cstdlib> // für srand() & rand()
#include <ctime> // Zur initialisierung
#include <set>
using namespace std;

typedef set<int> IntSet;            // Set und 
typedef IntSet::iterator SetIter;		// sein Iteratortyp

typedef multiset<int> IntMSet;				// Multiset und 
typedef IntMSet::iterator MSetIter;		// sein Iterator

int main()
{
	IntSet lotto;			// Set anlegen
	SetIter pos;			// Iterator bidirektional

	srand( (unsigned)time(NULL));

	while(lotto.size() < 6)				// Sets können nur Objekte mit eindeutigem Schlüssel enthaltenm, deshalb (!) while()
		lotto.insert( 1+rand()%49);

	cout << "Hier die Lottozahlen ;)\n";

	for( pos = lotto.begin(); pos != lotto.end(); pos++)
		cout << *pos << "   ";
	cout << endl << endl;

	IntMSet ms;		// Multisets können mehrere Objekte mit gleichem Schlüssel enthalten
	MSetIter mpos;

	for( int i=0; i<10; i++)
		ms.insert( rand()%10);

	cout << "Und hier 10 Zufallszahlen zwischen 0 und 10: \n";

	for( mpos=ms.begin(); mpos!=ms.end(); mpos++)
		cout << *mpos << "   ";
	cout << endl;
}