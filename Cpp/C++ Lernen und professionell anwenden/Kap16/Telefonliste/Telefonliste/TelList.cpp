/////////////////////////////////////////////////////////////////////////////
// Cpp TelList.cpp mit Methoden von TelList.h für die Klasse TelList
/////////////////////////////////////////////////////////////////////////////
#include "stdafx.h"
#include "TelList.h"

/////////////////////////////////////////////////////////////////////////////
// Fügt neue Elemente zum Objekt hinzu
bool TelList::append( const string& name, const string& telNr)
{
	if(search(name) != PSEUDO || name.size()<1 || count == MAX)
		return false;

	v[count].name		= name;
	v[count].telNr	= telNr;
	count += 1;
	return true;
}

/////////////////////////////////////////////////////////////////////////////
//Löscht Element des angegebenen Namen, liefert dann True, sonst False
bool TelList::erase(const string& name)
{
	int pos(search(name));

	if(pos == PSEUDO)
		return false;

	count -= 1;
	v[pos].name = v[count].name;
	v[pos].telNr = v[count].telNr;
	return true;
}

/////////////////////////////////////////////////////////////////////////////
//Sucht nach Namen und gibt Indexposition zurück
int TelList::search(const string& name) const
{
	if(name.length()<1)
		return PSEUDO;
	for(int i=0; i<MAX; i++)
		if(v[i].name == name)
			return i;
	return PSEUDO;
}

/////////////////////////////////////////////////////////////////////////////
//Zeigt alle Elemente des Objekts am Bildschirm an
void TelList::print() const
{
	for(int i=0; i<count; i++)
		cout	<< left << setw(30) << v[i].name
					<< left << setw(15) << v[i].telNr << endl;
}

/////////////////////////////////////////////////////////////////////////////
//Zeigt alle mit der angegebenen Zeichenfolge beginnenden Elemente an
//und gibt die Anzahl der entsprechenden Elemente zurück
int TelList::print(const std::string &name) const
{
	int  anz = 0;
	size_t size = name.length();

	for(int i=0; i<count; i++)
	{
		if(v[i].name.compare(0, size, name) == 0)
		{	
			anz += 1;
			cout	<< left << setw(30) << v[i].name
						<< left << setw(15) << v[i].telNr << endl;
		}
	}
	return anz;
}

/////////////////////////////////////////////////////////////////////////////
//Ermöglicht das Einlesen neuer Einträge von der Tastatur,
//Gibt die Anzahl der eingelesenen Elemente zurück
int TelList::getNewEntries()
{
	int anz = 0;

	while(true)
	{
	string name, nr;
	cout << "Name:      "; getline(cin, name);
	
	if(name.length()<1)
		return anz;

	cout << "Telefonnr: "; getline(cin, nr);
	
	append(name,nr);
	anz += 1;
	}
}