#include "stdafx.h"
#include <iostream>
#include <ctime>

using namespace std;

void main()
{

struct tm *zeit;				//Zeiger auf Struktur tm (S. 280)
time_t	sek;						//Variable sek vom typ time_t

time(&sek);							//Weil Zeiger in Funktion, Angabe der Adresse per &
zeit = localtime(&sek);		//Struktur von tm ("zeit") initialisieren
int day;											//Zeiger darauf zurückgeben

/* tm_mon+1... Monat, tm_yday.. Tag des Jahres, tm_mday.. tag des Monats, tm_hour ..Stunde
tm_year+1900.. aktuelles Jahr*/

day		= zeit->tm_mday;
cout << day;




}