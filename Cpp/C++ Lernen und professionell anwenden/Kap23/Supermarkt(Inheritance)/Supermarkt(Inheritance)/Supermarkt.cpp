/////////////////////////////////////////////////////////////////////////////
// cpp-Datei zur Header Supermarkt.h
/////////////////////////////////////////////////////////////////////////////
#include "Supermarkt.h"
#include <iostream>
using namespace std;

///////////////////////////////////////////////////////////////
// Scanner von Product
bool Product::scanner()
{
	long cd;
	cout << "\nGeben sie einen Produktnamen ein: ";
	getline( cin, name);
	cout << "Bitte den Barcode eingeben: ";
	if( !(cin >> cd))
	{
		cerr << "Fehler: Eingabe des Barcodes inkorrekt!";
		return false;
	}	
	code = cd;
	return true;
}

///////////////////////////////////////////////////////////////
// Printer von Product
void Product::printer() const
{
	cout	<< "\n-------------------------------------------"
				<< "\nProduktbezeichnung: " << name
				<< "\nBarcode:            " << code << endl;
}

///////////////////////////////////////////////////////////////
// Scanner von PackedFood
bool PackedFood::scanner()
{
	double prc;
	if(!(Product::scanner()))
		return false;
	cout << "Stueckpreis: ";
	if( !(cin >> prc))
	{
		cerr << "Fehler: Eingabe des Stueckpreises inkorrekt!";
		return false;
	}
	stkpreis = prc;
	return true;
}

///////////////////////////////////////////////////////////////
// Printer von PackedFood
void PackedFood::printer() const
{
	Product::printer();
	cout << "Stueckpreis:        " << stkpreis << " Euro\n";
}

///////////////////////////////////////////////////////////////
// Scanner von UnpackedFood
bool UnpackedFood::scanner()
{
	double prc;
	double wgt;
	if(!(Product::scanner()))
		return false;
	cout << "Kilopreis: ";
	if( !(cin >> prc))
	{ cerr << "Fehler: Eingabe des Kilopreises inkorrekt!"; return false; }
	klopreis = prc;
	
	cout << "Gewicht in kg: ";
	if( !(cin >> wgt))
	{ cerr << "Fehler: Eingabe des Gewichtes inkorrekt!"; return false; }
	weight = wgt;
	return true;
}

///////////////////////////////////////////////////////////////
// Printer von UnpackedFood
void UnpackedFood::printer() const
{
	Product::printer();
	cout	<< "Kilopreis:          " << klopreis << " Euro\n"
				<< "Gewicht:            " << weight << " kg\n";
}