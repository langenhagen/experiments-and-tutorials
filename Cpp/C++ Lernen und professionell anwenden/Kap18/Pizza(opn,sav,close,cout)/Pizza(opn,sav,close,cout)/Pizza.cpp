#include <iostream>
#include <iomanip>
#include <fstream>

using namespace std;

inline void openErr( const char *fileName );
inline void writeErr( const char *fileName );
inline void readErr( const char *fileName );

char header[] = "    * * * P I Z Z A  P R O N T O * * *\n\n";
char line[] = "\n- - - - - - - - - - - - - - - - - - - - - - - - - -\n\n";

const int MAXANZ = 10;

struct Pizza {char name[32]; float preis; };

Pizza pizzakarte[MAXANZ] =
	{	{ "Margerita", 9.90F }, { "Regina", 15.90F },
		{ "Fungi", 12.50F }, { "Calzone", 14.90F } };

int anz = 4;
char pizzaDatei[256] = "pizza.dat";

int main()
{
	char ui;

	cout	<< header
				<< "1 Speisekarte anzeigen\n2 Pizzen hinzufuegen und speichern"
				<<	"\n0 Standardwerte\ne fuer Escape\n" << line;

	while(true)
	{
		do
		{
		cout << "Ihre Eingabe: ";
		cin.sync();
		cin.get(ui);
		}
		while( !(ui=='1' || ui=='2' || ui=='0' || ui=='e' || ui=='E') );

		cout << endl;

		switch(ui)
		{
		case '1':						//Speisekarte anzeigen
			{
				ifstream inFile( pizzaDatei, ios::in | ios::binary );
				if( !inFile.is_open() )
					openErr( pizzaDatei );

				if(	!inFile.read( (char*)&anz, sizeof(anz) ) )
					readErr(pizzaDatei);

				cout	<< line << header << "       * * * SPEISEKARTE * * *\n\n"
							<< setw(32) << left << "Gericht"
							<< setw(5) << right << "Preis" << line;

				for( int i=0; i<anz; i++ )
				{
					if(	!inFile.read( (char*)&pizzakarte[i], sizeof(Pizza) ) )
						readErr(pizzaDatei);
					cout	<< setw(32) << left << pizzakarte[i].name
								<< setw(5) << right << fixed << setprecision(2) << pizzakarte[i].preis << " EURO\n";
				}

				inFile.close();

				break;
			}
		case '2':						//Speisekarte speichern
			{
				cout	<< line
							<< "Bitte Pizzen zum Sortiment hinzufuegen\n";

				while(true)
				{
					if( MAXANZ - anz < 1 )
					{
						cerr << "\nAlle Plaetze der Speisekarte belegt!\n";
						break;
					}

					cout << "Es besteht noch Platz fuer " << MAXANZ - anz << " Pizzen.\n\n";

					cin.clear();
					cin.sync();
					cout << "Name:  "; ;
					if( cin.getline( pizzakarte[anz].name, 32 ) && strlen(pizzakarte[anz].name)<1 )	break;
					cout << "Preis: "; cin.clear(); cin.sync(); cin >> pizzakarte[anz].preis;

					anz++;
				}

				cout << "\nKommen wir nun zum Speicherprozess der Daten...\n";

				ofstream outFile( pizzaDatei, ios::out | ios::binary );
				if( !outFile.is_open() )
					openErr( pizzaDatei );

				if( !outFile.write( (char*)&anz, sizeof(anz) ))
						writeErr(pizzaDatei);

				for(int i=0; i<anz; i++)
					if( !outFile.write( (char*)&pizzakarte[i], sizeof(Pizza) ))
						writeErr(pizzaDatei);

				outFile.close();

				cout << "Daten in " << pizzaDatei << " gespeichert.\n";
				break;
			}
		case '0':
			{
				anz = 4;
				ofstream outFile( pizzaDatei, ios::out | ios::binary );

				Pizza pizzakarte[] =
					{	{ "Margerita", 9.90F }, { "Regina", 15.90F },
						{ "Fungi", 12.50F }, { "Calzone", 14.90F } };

				if( !outFile.write( (char*)&anz, sizeof(anz) ))
						writeErr(pizzaDatei);

				for(int i=0; i<anz; i++)
					if( !outFile.write( (char*)&pizzakarte[i], sizeof(Pizza) ))
						writeErr(pizzaDatei);

				outFile.close();

				cout	<< "Standardeinstellungen wieder hergestellt\n"
							<< "und Daten in " << pizzaDatei << " gespeichert.\n";
				break;
			}
		case 'E':
		case 'e':
			{
				return 0;
			}
		}
	cout << endl;
	}
}

inline void openErr( const char *fileName )
{
	cerr	<< "Fehler beim Oeffnen der Datei " << fileName << "!\n"
				<< "Programm endet.\n(Error Nr. 1)";
	exit(1);
}

inline void writeErr( const char *fileName )
{
	cerr	<< "Fehler beim Schreiben der Datei " << fileName << "!\n"
				<< "Programm endet.\n(Error Nr. 2)";
	exit(2);
}

inline void readErr( const char *fileName )
{
	cerr	<< "Fehler beim Lesen der Datei " << fileName << "!\n"
				<< "Programm endet.\n(Error Nr. 3)";
	exit(3);
}