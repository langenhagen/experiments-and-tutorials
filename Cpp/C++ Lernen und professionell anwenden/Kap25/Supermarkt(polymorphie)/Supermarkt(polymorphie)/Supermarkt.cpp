/////////////////////////////////////////////////////////////////////////////
// cpp-Datei zur Header Supermarkt.h
/////////////////////////////////////////////////////////////////////////////
#include "Supermarkt.h"
#include <iostream>
#include <iomanip>
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
	cin.sync(); cin.clear();
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
	cout << "Stueckpreis in Euro: ";
	if( !(cin >> prc))
	{
		cerr << "Fehler: Eingabe des Stueckpreises inkorrekt!";
		return false;
	}
	stkpreis = prc;
	cin.sync(); cin.clear();
	return true;
}

///////////////////////////////////////////////////////////////
// Printer von PackedFood
void PackedFood::printer() const
{
	Product::printer();
	cout	<< fixed << setprecision(2) 
				<< "Stueckpreis:        " << stkpreis << " Euro\n";
}

///////////////////////////////////////////////////////////////
// Scanner von UnpackedFood
bool UnpackedFood::scanner()
{
	double prc;
	double wgt;
	if(!(Product::scanner()))
		return false;
	cout << "Kilopreis in Euro: ";
	if( !(cin >> prc))
	{ cerr << "Fehler: Eingabe des Kilopreises inkorrekt!"; return false; }
	klopreis = prc;
	
	cout << "Gewicht in kg: ";
	if( !(cin >> wgt))
	{ cerr << "Fehler: Eingabe des Gewichtes inkorrekt!"; return false; }
	weight = wgt;
	cin.sync(); cin.clear();
	return true;
}

///////////////////////////////////////////////////////////////
// Printer von UnpackedFood
void UnpackedFood::printer() const
{
	Product::printer();
	cout	<< fixed << setprecision(2)
				<< "Kilopreis:          " << klopreis << " Euro\n"
				<< "Gewicht:            " << weight << " kg\n";
}

///////////////////////////////////////////////////////////////
// Gibt das Produkt mit dem kleineren Barcode zurück
Product& isLowerCode( Product& a, Product& b)
{
	if( a.getCode() < b.getCode() )
		return a;
	return b;
}

