#include <iostream>
#include <cstdlib> // für die rand() funktionen
#include <list>
using namespace std;

typedef list<int> intlist;
void display( const intlist& ilc);

void main()
{
	intlist ls, sls;
	int i;

	for( i=1; i<=3; i++)
		ls.push_back( rand()%10);

	ls.push_back(ls.front());

	ls.reverse();

	ls.sort();

	for( i=1; i<=3; i++)
		sls.push_back( rand()%10);

	intlist::iterator pos = ls.end();

	ls.splice(--pos, sls, sls.begin());

	display(sls);
	cout << "\nPOWR\n";

	ls.sort();
	sls.sort();
	ls.merge(sls);
	ls.unique();

	display(ls);
}

void display( const intlist& c)
{
	intlist::const_iterator pos = c.begin();
	for( ; pos!=c.end(); pos++)
		cout << *pos << "   ";
}