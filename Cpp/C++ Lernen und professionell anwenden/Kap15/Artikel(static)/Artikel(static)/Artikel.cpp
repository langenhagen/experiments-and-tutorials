/////////////////////////////////////////////////////////////////////////////
// cpp-Datei für Methoden der Header Artikel.h
/////////////////////////////////////////////////////////////////////////////
#include "stdafx.h"
#include "Artikel.h"

/////////////////////////////////////////////////////////////////////////////
// Statische Variablen und Elementfunktionen
unsigned int Artikel::counter = 0;

/////////////////////////////////////////////////////////////////////////////
// Der Default-Konstruktor von Artikel
Artikel::Artikel(long Nr,const string& Bez, double Preis)
{
	ArtNr = Nr;

	if(Bez.size()<1)
		ArtBez = "Artikel";
	else
		ArtBez = Bez;

	Preis < 0.0? ArtPreis = 0.0 :	ArtPreis = Preis;

	cout	<< "KONSTRUKTOR (" << ArtBez << ")\nEs wird ein Objekt fuer den Artikel '" << ArtBez << "' angelegt."
				<< "\nDies ist der " << ++counter << "-te Artikel.\n";
}

/////////////////////////////////////////////////////////////////////////////
// Der Kopier-Konstruktor von Artikel
Artikel::Artikel(const Artikel& art) : ArtNr(art.ArtNr), ArtBez(art.ArtBez), ArtPreis(art.ArtPreis)
{
	cout	<< "KOPIER-KONSTRUKTOR (" << ArtBez << ")\nEs wird ein Objekt fuer den Artikel '" << ArtBez << "' angelegt."
				<< "\nDies ist der " << ++counter << "-te Artikel.\n";
}

/////////////////////////////////////////////////////////////////////////////
//Der Destruktor von Artikel
Artikel::~Artikel()
{
	cout	<< "DESTRUKTOR (" << ArtBez << ")\nDas Objekt fuer den Artikel '" << ArtBez << "' wird zerstoert."
				<< "\nEs gibt noch " << --counter << " Artikel.\n";
}

/////////////////////////////////////////////////////////////////////////////
//Methode print() zum Anzeigen der Daten
void Artikel::print()
{
	cout	<< "\n------------------------------------------------\n"
				<< setw(20) << "Artikelbezeichnung: "  << ArtBez << endl
				<< setw(20) << "Artikelnummer: " << ArtNr << endl
				<< setw(20) << "Preis: " << setprecision(2) << fixed << ArtPreis << " Euro"
				<< "\n------------------------------------------------\n";
}