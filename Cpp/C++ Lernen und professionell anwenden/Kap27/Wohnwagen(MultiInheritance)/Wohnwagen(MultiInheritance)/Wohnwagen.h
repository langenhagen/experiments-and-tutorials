/////////////////////////////////////////////////////////////////////////////
// Header Wohnwagen.h mit der Klasse Wohnwagen
/////////////////////////////////////////////////////////////////////////////
#ifndef _Wohnwagen_
#define _Wohnwagen_
#include "Kfz.h"
#include "Wohnung.h"

enum KATEGORIE { LUXUS, GEHOBEN, MITTEL, EINFACH };

class Wohnwagen : public Kfz, public Wohnung
{
private:
	KATEGORIE kat;
public:
	Wohnwagen(	long Nr = 0L, const string& h = "Unbekannt",
							short zimmer = 1, short QM = 0, KATEGORIE k = EINFACH )
		: Kfz( Nr, h), Wohnung( QM, zimmer), kat(k)	{}

	KATEGORIE getKlasse() const { return kat; }
	void setKlasse( KATEGORIE k) { kat = k; }

	void display() const;
};

void Wohnwagen::display() const
{
	cout	<< "\n-----------------------------------------\n"
				<< getHerst() << "-Wohnwagen Klasse \"";
	switch(kat)
	{
	case EINFACH: cout << "Einfach"; break;
	case MITTEL: cout << "Mittel"; break;
	case GEHOBEN: cout << "Gehoben"; break;
	case LUXUS: cout << "Luxus"; break;
	}
	cout	<< "\"\n" << getRooms() << " Zimmer auf\n"
				<< getQM() << " Quadratmeter\n"
				<< "Modellnummer: " << getNr()
				<< "\n-----------------------------------------\n";
}

#endif