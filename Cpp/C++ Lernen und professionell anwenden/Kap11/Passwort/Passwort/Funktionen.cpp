#include "stdafx.h"
#include <iostream>
#include <iomanip>
#include <string>
#include <ctime>

using namespace std;

time_t zeitdiff(void);                      // Prototyp
extern string geheimwort;        // Passwort 
static long  maxanzahl = 3, maxzeit = 60; // Limits

bool getPassword()  // Passwort einlesen und überprüfen.
{               // Return-Wert: true, falls Passwort ok.
   bool ok_flag = false;        // Für die Rückgabe 
   string wort;                 // Für die Eingabe 
   int anzahl = 0, zeit = 0;

   zeitdiff();                   // Die Stoppuhr starten
   while( ok_flag != true && 
          ++anzahl <= maxanzahl) // Anzahl Versuche 
   {
      cout << "\n\nGeben Sie das Passwort ein:  ";
      cin.sync();                // Keine alte Eingabe
      cin >> setw(20) >> wort;
      zeit += zeitdiff();
      if( zeit >= maxzeit )     // Im Zeitlimit?
			{ 
				cout <<"\nZeit abgelaufen!\n";
				break;									// nein!
			}
      if( wort != geheimwort)
         cout << "Passwort ungültig!\n" << endl;
      else 
         ok_flag = true;         // Erlaubnis geben
    }
    return ok_flag;              // Ergebnis
}

time_t zeitdiff()          // Liefert die Anzahl Sekunden
{                        // seit dem letzten Aufruf.
    static time_t  sek = 0;    // Zeit vom letzten Aufruf.
    time_t  altsek = sek;      // Alte Zeit merken.
    time( &sek);             // Neue Zeit lesen.
    return (sek - altsek);   // Differenz zurückgeben.
}

void changePassword()
{
	string PW1="", PW2="";
	
	cout << "Geben sie ein neues Passwort ein: ";
	cin.sync();
	cin >> PW1;
	
	cout << "Passwort nochmal eingeben: ";
	cin.sync();
	cin >> PW2;

	if(PW1==PW2)
	{
		geheimwort = PW1;
		cout << "\nNeues Passwort gespeichert.\n";
	}
	else
		cout << "\nPassworter nicht identisch. Vorgang abgebrochen.\n";
}