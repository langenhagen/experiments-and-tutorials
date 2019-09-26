#include <stdafx.h>
#include <iostream>
#include <stdlib.h>
#include <time.h>

using namespace std;

int main()
{
	time_t sek = time(&sek);
	srand((unsigned)sek);

	short ghm = 0;
	int ui = 0;
	char uw = 'w';

	while(uw=='w' || uw=='W')
	{
		ghm = rand()%15 +1;
		cout << "\nTuerke!!! Errate die Zahl zwischen 1 && 15!!!\n";
		
		for(short i=1; i<=3;++i)
		{
	
			cin.clear(), cin.sync();
			
			cin >> ui;

			if(i==3 && ui!=ghm)
			{
				cout << "\tMensch...Peiler...\n\tDie Zahl is natuerlich " << ghm << endl << endl;
				break;
			}
			
			if(ui<ghm)
				cout << "\tIdiota, Karl ++\n";
			else if(ui>ghm)
				cout << "\tIdiota, Karl --\n";
			else if(ui==ghm)
			{	
				cout << "\tOu Yeah mister Donut.. Jewonnen!!!\n";
				break;
			}

		}
		
		do
		{
			cout << "Na Watn? Weiter <w>? Oder Beenden <q>? ";
			cin.clear();
			cin.sync();
			cin.get(uw);
		}
		while(uw!='w' && uw!='W' && uw!='q' && uw!='Q');

	}

}
