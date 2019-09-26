// -----------------------------------------------------------------------
// Datei : Console.cpp
// Zweck : Einige Funktionen zur Steuerung der Ausgabe im Consolen-Fenster
// -----------------------------------------------------------------------


#include "console.h" 

static HANDLE hStdout; 
static CONSOLE_SCREEN_BUFFER_INFO csbInfo; 

////////////////////////////////////////////////////////////////////////
// Funktion setCursor() : Cursor auf die angegebene Position setzen.

bool setCursor( int zeile, int spalte)
{
    COORD dwCursorPos = {spalte, zeile};

    hStdout = GetStdHandle(STD_OUTPUT_HANDLE); 
    return SetConsoleCursorPosition(hStdout, dwCursorPos) != 0;
}
    
////////////////////////////////////////////////////////////////////////
// Funktion setColor() : Vorder- und Hintergrundfarbe setzen.

bool setColor( int color)
{
    hStdout = GetStdHandle(STD_OUTPUT_HANDLE); 
    return SetConsoleTextAttribute(hStdout, (WORD)color) != 0;
}

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