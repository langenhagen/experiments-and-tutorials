/////////////////////////////////////////////////////////////////////////////
// Header-Datei Mitglied.h mit der Klasse Mitglied
/////////////////////////////////////////////////////////////////////////////
#ifndef _MITGLIED_
#define _MITGLIED_

#include "Datum.h"


class Mitglied
{
private:																						//PRIVATE
	unsigned int MemberNr;
	string name;
	const Datum bday;
	static Mitglied *ptrVorstand;

public:																							//PUBLIC
	Mitglied(unsigned int Nummer, string Name, Datum Birthday);				//Konstruktor #1
	Mitglied(unsigned int Nummer, string Name, short Tag, short Monat, short Jahr);

	unsigned int	getNummer() const { return MemberNr; }								//Get-Methoden
	const string& getName()		const { return name; }
	const Datum&	getBDay()		const { return bday; }
	static Mitglied* getVorstand() {return ptrVorstand;}

	void setNummer(unsigned int Nummer) { MemberNr = Nummer; }						//Set-Methoden
	bool setName(string Name)
	{
		if(Name.size()<1)
			return false;
		name = Name;
		return true;
	}
	static void setVorstand(Mitglied *newVorstand) { ptrVorstand = newVorstand; }

	void print();																							//Weitere Methoden

};

#endif