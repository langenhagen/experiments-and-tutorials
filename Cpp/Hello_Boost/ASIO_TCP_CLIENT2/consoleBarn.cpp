// ----------------------------------------------------------------------------
// Datei : consoleBarn.cpp
//
// Funktionen zur Steuerung der Consolenausgabe unter
// Win2000, XP, Vista, Win95, Win98, WinNT
// ----------------------------------------------------------------------------

#include "consoleBarn.h"

static HANDLE hStdOut;
static CONSOLE_SCREEN_BUFFER_INFO csbInfo;

/////////////////////////////////////////////////
// setCursor(): setzt den Cursor auf die 
// angegebene Stelle auf der StdOut
bool setCursor( int line, int row)
{
	COORD cursorPos = {row, line};

	hStdOut = GetStdHandle(STD_OUTPUT_HANDLE); 
	return SetConsoleCursorPosition(hStdOut, cursorPos) != 0;
}

/////////////////////////////////////////////////
// setColor(): setzt die Farbe des Cursors
bool setColor( int color)
{
	hStdOut = GetStdHandle(STD_OUTPUT_HANDLE); 
	return SetConsoleTextAttribute(hStdOut, (WORD)color) != 0;
}

/////////////////////////////////////////////////
// cls(): CLearScreen. sagt doch alles. oder?
bool cls()
{
	COORD home = { 0, 0 };          // Home-Position
	DWORD dwCellsWritten;           // Anzahl beschriebener Char-Zellen 
	DWORD dwConSize;                // Anzahl Zeichen im Puffer

	// Größe des Puffers und das aktuelle Attribut holen
	hStdOut = GetStdHandle(STD_OUTPUT_HANDLE); 
	GetConsoleScreenBufferInfo(hStdOut, &csbInfo);

	dwConSize = csbInfo.dwSize.X * csbInfo.dwSize.Y;
 
	// blanks in den Puffer
	if( ! FillConsoleOutputCharacter(hStdOut, (TCHAR)' ', dwConSize,
				home, &dwCellsWritten) )
		return false;

	// Attribute setzen:
	if( ! FillConsoleOutputAttribute(hStdOut, csbInfo.wAttributes, dwConSize,
				home, &dwCellsWritten) )
		return false;

	// cursor oben links platzieren
	if( ! SetConsoleCursorPosition(hStdOut, home) )
		return false;
	
	return true;
}

/////////////////////////////////////////////////
// getCursorPos() gibt die aktuellen Koordinaten
// des Cursors in den übergebenen 
// Referenzen zurück
void getCursorPos( int& line, int& row)
{
	hStdOut = GetStdHandle(STD_OUTPUT_HANDLE); 
	CONSOLE_SCREEN_BUFFER_INFO CSBI;
	
	GetConsoleScreenBufferInfo( hStdOut, &CSBI);

	row		= CSBI.dwCursorPosition.X;
	line	= CSBI.dwCursorPosition.Y;
}

/////////////////////////////////////////////////
// cursorOff(): cursor invisible.
void cursorOff()
{
	CONSOLE_CURSOR_INFO cursorInfo;   
	HANDLE hStdOut = GetStdHandle(STD_OUTPUT_HANDLE); 

	GetConsoleCursorInfo( hStdOut, &cursorInfo);
	cursorInfo.bVisible = FALSE;
	SetConsoleCursorInfo( hStdOut, &cursorInfo);
}

/////////////////////////////////////////////////
// cursorOn(): cursor visible.
void cursorOn()
{
	CONSOLE_CURSOR_INFO cursorInfo;   
	HANDLE hStdOut = GetStdHandle(STD_OUTPUT_HANDLE); 

	GetConsoleCursorInfo( hStdOut, &cursorInfo);
	cursorInfo.bVisible = TRUE;
	SetConsoleCursorInfo( hStdOut, &cursorInfo);
}

/////////////////////////////////////////////////
// cursorOn(): switches between on/off cursor
void cursorOnOffSwitch()
{
	CONSOLE_CURSOR_INFO cursorInfo;
	HANDLE hStdOut = GetStdHandle(STD_OUTPUT_HANDLE);
	GetConsoleCursorInfo( hStdOut, &cursorInfo);
	
	cursorInfo.bVisible = !cursorInfo.bVisible;
}
