#ifndef _My_Makros_
#define _My_Makros_

#include <iostream>
#include <windows.h>

static HANDLE hStdout; 
static CONSOLE_SCREEN_BUFFER_INFO csbInfo; 

////////////////////////////////////////////////////////////////////////
//Betrag der eingegebenen Zahl ermitteln per ABS(zahl) ermitteln
#define ABS(zahl)							(zahl >= 0 ? zahl : -zahl)

////////////////////////////////////////////////////////////////////////
// Von zwei Zahlen größere [MAX(a,b)], bzw kleinere Zahl [MIN(a,b)] ermitteln
#define MAX(zahl1, zahl2)			(zahl1>=zahl2 ? zahl1 : zahl2)
#define MIN(zahl1, zahl2)			(zahl1<=zahl2 ? zahl1 : zahl2)



////////////////////////////////////////////////////////////////////////
// Funktion cls() : Consolen-Puffer mit aktuellem Attribut löschen und 
//                  Cursor auf die Home-Position setzen.

bool cls()
{
    COORD home = { 0, 0 };          // Home-Position
    DWORD dwCellsWritten;           // Anzahl beschriebener Char-Zellen 
    DWORD dwConSize;                // Anzahl Zeichen im Puffer

    // Größe des Puffers und das aktuelle Attribut holen:
    hStdout = GetStdHandle(STD_OUTPUT_HANDLE); 
    GetConsoleScreenBufferInfo(hStdout, &csbInfo);
    
    dwConSize = csbInfo.dwSize.X * csbInfo.dwSize.Y;
 
    // Den Puffer mit Blanks füllen:
		if( ! FillConsoleOutputCharacter(hStdout, (TCHAR)' ', dwConSize,
                                     home, &dwCellsWritten) )
        return false;

    // Attribute setzen:
    if( ! FillConsoleOutputAttribute(hStdout, csbInfo.wAttributes, dwConSize,
                                     home, &dwCellsWritten) )
        return false;

    // Cursor auf Home-Position setzen 

    if( ! SetConsoleCursorPosition(hStdout, home) )
        return false;

    return true;
}

////////////////////////////////////////////////////////////////////////
// Funktion cursorOff() : Cursor unsichtbar machen.

void cursorOff()
{
    CONSOLE_CURSOR_INFO cursorInfo;   
    HANDLE hStdout = GetStdHandle(STD_OUTPUT_HANDLE); 

    GetConsoleCursorInfo( hStdout, &cursorInfo);
    cursorInfo.bVisible = FALSE;
    SetConsoleCursorInfo( hStdout, &cursorInfo);
}

////////////////////////////////////////////////////////////////////////
// Funktion cursorOn() : Cursor wieder sichtbar machen.

void cursorOn()
{
    CONSOLE_CURSOR_INFO cursorInfo;   
    HANDLE hStdout = GetStdHandle(STD_OUTPUT_HANDLE); 

    GetConsoleCursorInfo( hStdout, &cursorInfo);
    cursorInfo.bVisible = TRUE;
    SetConsoleCursorInfo( hStdout, &cursorInfo);
}

////////////////////////////////////////////////////////////////////////
// Funktion setCursor() : Cursor auf die angegebene Position setzen.

bool setCursor( int zeile, int spalte)
{
    COORD dwCursorPos = {spalte, zeile};

    hStdout = GetStdHandle(STD_OUTPUT_HANDLE); 
    return SetConsoleCursorPosition(hStdout, dwCursorPos) != 0;
}

#endif
