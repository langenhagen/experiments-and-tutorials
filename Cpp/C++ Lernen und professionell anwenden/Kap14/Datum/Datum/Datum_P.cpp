#include "stdafx.h"
#include "Datum.h"

void main()
{

	string jo;

	Datum ursprung, jezz, später(3,3,2008);
	
	ursprung.print();
	cout << endl;

	jezz.setDatum();

	jezz.print();
	cout << endl;

	später.print();
	cout << endl;

	ursprung.setDatum(15,2,1998);

	ursprung.print();
	cout << endl;

	jo = ursprung.asString();
	cout << jo << " (ursprung ...MADE WITH jo)";

	cout	<< endl << ursprung.getJahr() << endl
				<< ursprung.getMonat() << endl
				<< ursprung.getTag() << endl;

Datum Nigger(1,1,1), Nazzi(2,2,2);

	cout << "ist Ursprung gleich Nigger?" << endl;

	if(ursprung.isEqual(Nigger))
		cout << "JAAAAA!" << endl;
	else
		cout << "NEEEIN!" << endl;

		cout << "ist Nigger kleiner Ursprung?" << endl;

if(Nigger.isLess(Nazzi))
			cout << "JAAAAA!" << endl;
	else
		cout << "NEEEIN!" << endl;


		cout << endl << endl;

		int jahr, monat, tag;

		cout << "und jez noch was zum Eingeben!!\nJahr, Monat, Tag...\n";
		cin >> jahr >> monat >> tag;

		Datum lulatsch(tag, monat, jahr);

		lulatsch.print();

		cout << endl << endl;

}

