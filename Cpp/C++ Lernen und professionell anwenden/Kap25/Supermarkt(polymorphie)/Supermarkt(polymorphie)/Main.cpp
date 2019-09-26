#include <iostream>
#include <iomanip>
#include "Supermarkt.h"
using namespace std;

void registrieren();

void main()
{
	char ui;
	do
	{
		registrieren();
		cout	<< "\n\n\t\tNoch ein Kunde?\n\n"
					<<		 "\t\tFalls ja: <j> fuer Registrieren\n";
	cin.get(ui);
	cin.sync(); cin.clear();
	}while( ui == 'j' || ui == 'J');
}

void registrieren()
{
	Product *Waren[100];
	char ui, i = 0;
	bool ext = false;
	double sum = 0.0, t1, t2;
		
	while( ext == false && i<100)
	{
		cout	<< "\nWas ist der naechste Artikel?\n"
					<< "\n\t0 : kein weiterer Artikel\n"
					<< "\n\t1 : Unverpackter Artikel\n"
					<< "\n\t2 : Verpackter Artikel\n\n  ? ";
		cin.get(ui);
		cin.sync(); cin.clear();

		switch(ui)
		{
		case '0': 
			ext = true; break;
		case '1':
			Waren[i] = new UnpackedFood;
			Waren[i]->scanner();
			t1 = dynamic_cast<UnpackedFood*>(Waren[i])->getKiloPreis();
			t2 = dynamic_cast<UnpackedFood*>(Waren[i++])->getWeight();
			sum +=	t1 * t2;
			break;
		case '2':
			Waren[i] = new PackedFood;
			Waren[i]->scanner();
			sum += dynamic_cast<PackedFood*>(Waren[i++])->getPreis();
			break;
		}
	}
	
	for(int j=0; j<i; j++)
	{
		Waren[j]->printer();
		delete Waren[j];
	}
	cout	<< "\n------------------------------------------------\n"
				<< "Geamtsumme : " << fixed << setprecision(2) << sum <<  " Euro" << endl;
}