//  Ball1.cpp
//  Simuliert einen springenden Ball
//  Hinweis: Programm mit Strg+C beenden.
//  --------------------------------------------------
//  Statt der ANSI-Bildschirmsteuerzeichen werden hier
//  die systemabhängigen Funktionen der Windows-API 
//  (API == Application Programming Interface) verwendet.
// ----------------------------------------------------- 

#include "stdafx.h"
#include <iostream>
#include <string>
#include <conio.h>					// Kontrolle durch Tastenabfrage in der Konsole
#include "console.h"				// Win32 Konsolenfunktionen
using namespace std;

#define  CLS          cls()              // Bildschirm löschen
#define  LOCATE(z,s)  setCursor(z-1,s-1) // Cursor in Zeile z, 
                                         // Spalte s positionieren.

#define ESC		27

void main()
{
	 unsigned long delay(20000000);    
   int x = 2, y = 3, dx = 1, geschw = 0;
	 bool end = false;
   string boden(79, '-'), header = "****  SPRINGENDER BALL  ****";

	 setColor( 7 );
	 cursorOff();

   CLS;
   LOCATE(1,25);  cout << header;
   LOCATE(25,1);  cout << boden;

   while(!end)      // Ball springen lassen, bis end=True
   {
     LOCATE(y,x);
     cout << 'o' << endl;              // Ball anzeigen
     for( long warten = 0; warten < delay; ++warten)
       ;

     if(x == 1 || x == 79) dx = -dx;   // An einer Wand?
     if( y == 24 )                     // Am Boden?
     {
       geschw = - geschw;
       if( geschw == 0 ) geschw = -7;  // Neu anstossen
     }
     geschw += 1;                 // Beschleunigung = 1

     LOCATE(y,x); cout <<  ' ';   // Anzeige löschen
     y += geschw;  x += dx;       // Neue Position
   
	 if(_kbhit()!=0)				//Wurde Taste gedrückt?!
	 {
		switch(_getch())			//Ja?! Welche?!
		{
			case '+':{					//Schneller
								delay -=	delay/5;
								break;
								}
			case '-':{					//Slower
								delay +=	delay/5;
								break;
							 }
			case ESC: end=true;	//Ende im Jelände
		}
	 }
	 
	 }
}
