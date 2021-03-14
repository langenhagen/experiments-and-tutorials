#include <iostream>
#include <fstream>

using namespace std;

char header[]="    * * * P I Z Z A  P R O N T O * * *\n\n";

const int MAXANZ = 10;

struct Pizza {char name[32]; float preis; };

Pizza pizzakarte[MAXANZ] =
	{	{ "Margerita", 9.90F }, { "Regina", 15.90F },
		{ "Fungi", 12.50F }, { "Calzone", 14.90F } };

int anz = 4;

char pizzaDatei[256] = "pizza.dat";

int main()
{
	cout <<header << endl;

	int exitCode=0;

	ofstream outFile(pizzaDatei, ios::out | ios::binary);
	if( !outFile )
	{
		cerr << "Fehler beim Oeffnen der Datei!\n";
		exitCode = 1;
	}
	else
	{
		for( int i = 0; i < anz; i++ )
			if( !outFile.write((char*)&pizzakarte[i], sizeof(Pizza)) )
			{
				cerr << "Fehler beim Schreiben!\n";
				exitCode = 2;
			}
	}
	if( exitCode == 0 )
		cout << "\nDaten in die Datei " << pizzaDatei << " geschrieben\n\n";

return exitCode;
}