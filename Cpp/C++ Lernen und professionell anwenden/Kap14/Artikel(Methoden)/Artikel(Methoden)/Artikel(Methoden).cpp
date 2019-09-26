#include "stdafx.h"
#include <string>
#include "Artikel.h"

using namespace std;

void test(void);

Artikel obj1(54321,"Schnaps", 103.45);

void main()
{
	Artikel obj2(12345, "Stampfe-Amphe");

	cout	<< endl << obj1.getBez() << endl << obj1.getNr()
				<< endl << obj1.getPreis() << endl;

obj1.setNr(98765);
obj1.setPreis(101.01);

obj1.print();

obj2.print();

test();

}

void test(void)
{
	cout << "\n\n!...starte Funktion \"Test\"\n\n";
	
	Artikel obj3(44444,"Schmüselknüsel");
	static Artikel obj4(55555,"Statisches Ding",99.95);

	obj3.print();
	cout << endl;
	obj4.print();
	cout << endl;

	cout << "\n\n!...beende Funktion \"Test\"\n\n";
}

