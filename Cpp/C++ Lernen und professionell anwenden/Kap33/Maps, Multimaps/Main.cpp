// Programm zum SourceCode auf Seite 804 / 805
#include <iostream>
#include <string>
#include <map>
using namespace std;

typedef multimap<int,string> MULTI_MAP;
typedef MULTI_MAP::iterator it;

typedef pair<int,string> p;

void main()
{
	MULTI_MAP m;			// Multimap anlegen
	it pos;						// Iterator dazu

	m.insert( pair<int,string>(7, "Uwe") );			// Werden sortiert
	m.insert( pair<int,string>(3, "Edith") );
	m.insert( p(1, "Lina"));
	m.insert( p(1, "Andi"));

	cout << "Hier die Multimap:\n";

	for( pos= m.begin(); pos != m.end(); pos++)			
		cout << pos->first << "   " << (*pos).second << endl;

	pos = m.find(3);		// Paar zum Schlüssel suchen

	if( pos != m.end())
		cout << pos->first << "   " << (*pos).second << endl;
	else
		cout << "Kein Objekt mit dem Schluessel 3 gefunden!\n";

	int key = 1;				// Anzahl der Objekte ermitteln
	cout << "Zum Schluessel " << key << " gibt es " << m.count(key) << " Objekte.\n";
}