#include <iostream>
#include <fstream>

using namespace std;

bool  copy(istream& is, ostream& os);
inline void openError(const char *datei)
{
	cerr << "Fehler beim Oeffnen von " << datei;
	exit(2);
}

inline void fileError()
{
	cerr << "Fehler beim Lesen/Schreiben der Dateien";
	exit(3);
}


int main(int argc, char* argv[])
{
	switch(argc)
	{
		case 1:
		{
			char Source[256],  Target[256];

			do	//QUELLDATEI LADEN
				{ cout << "Ok, nenn ma Kwelldatei, alter: "; cin.getline(Source, 256); }
			while( strlen(Source) < 1 );
			ifstream flSource( Source, ios::in | ios::binary );
			if( !flSource )
				openError(Source);

			cin.sync();

			//ZIELDATEI ERSTELLEN/LADEN ODER WAT WEES ICK
			cout << "Jut, und jezz ma bitte die Zieldatei (angabe nicht nötig): ";	cin.getline(Target, 256);
			if(strlen(Target) < 1)
				if(!copy(flSource, cout))	fileError();
				else											return 0;

			ofstream flTarget( Target, ios::out | ios::binary );
			if( !flTarget )
				openError(Target);

			if( !copy(flSource, flTarget) )		//MÜSSN TUN WOLLN
				fileError();

			cout	<< "Kopiervorgang von " << Source << " nach " << Target
						<< " erfolgreich abgeschlossen.";

				return 0;
		}
		case 2:
		{
			ifstream flSource( argv[1], ios::in | ios::binary );
			if( !copy(flSource, cout) )		//MÜSSN TUN WOLLN
				fileError();

			return 0;
		}
		case 3:
		{
			ifstream flSource( argv[1], ios::in | ios::binary );
			ofstream flTarget( argv[2], ios::out | ios::binary );

			if( !copy(flSource, flTarget) )		//MÜSSN TUN WOLLN
				fileError();

			cout	<< "Kopiervorgang von " << argv[1] << " nach " << argv[2]
						<< " erfolgreich abgeschlossen.";
			return 0;
		}
		default:
		{
			cerr	<< "Aufruf: fcopy [quelldatei] [zieldatei]"
						<< "\n(Angabe einer Zieldatei erfordert Angabe der Qelldatei)";
			return 1;
		}
	}
}

// Die COPY-Funktion
bool  copy(istream& is, ostream& os)
{
	const int BSize = 1024;
	char line[BSize];

	while( !is.eof() && !is.fail() && !os.fail())
	{
		is.read(line,BSize);
		os.write(line, is.gcount());
	}

	if(is.eof())
		return true;
	return false;
}
