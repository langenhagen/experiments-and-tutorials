/////////////////////////////////////////////////////////////////////////////
// Header Artikel.h für die Definition der Klasse Artikel, einige ihrer Methoden lassen
// sich noch in Artikel.cpp finden.
/////////////////////////////////////////////////////////////////////////////

#ifndef _ARTIKEL_
#define _ARTIKEL_

#include <iostream>
#include <string>
#include <iomanip>

using namespace std;

class Artikel											//Klasse Artikel
{
	private:											//Private
		long ArtNr;
		string ArtBez;
		double ArtPreis;
	
	public:												//Public
		Artikel(long Nr=11111,const string& Bez="Barn", double Preis=0.0);
				~Artikel();
		void print();

		long getNr() const  { return ArtNr; }						//Definition der get-Methoden ...const
		const string& getBez() const { return ArtBez; }
		double getPreis() const {return ArtPreis;}
		
		void setNr(long Nr) { ArtNr = Nr; }							//Definition der set-Methoden
		
		bool setBez(const string& Bez)
		{
			if(Bez.size()<1)
				return false;

			ArtBez = Bez;
			return true;
		}
		
		void setPreis(double Preis)
		{
			Preis < 0.0? ArtPreis = 0.0 :	ArtPreis = Preis;
		}
};


#endif