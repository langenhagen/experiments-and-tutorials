#include "Wohnung.h"
#include "Wohnwagen.h"
#include "ctime"

void main()
{
	Wohnung a(140, 4,1,1, "ja", "schöne Aussicht");

	a.display();

	Wohnwagen b( 15645656, "Spyder", 2, 20, LUXUS), c;
	
	b.display();
	c.display();

	c.setHerst("KataKar");
	c.setKlasse( GEHOBEN);
	c.setNr(987654321);
	c.setQM(555);
	c.setRooms(17);
	c.display();
cout << sizeof(Kfz) << endl << sizeof(Wohnung) << endl << sizeof(Wohnwagen);
}

