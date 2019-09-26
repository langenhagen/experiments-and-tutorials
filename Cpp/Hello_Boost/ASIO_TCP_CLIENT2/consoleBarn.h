// ----------------------------------------------------------------------------
// Datei : consoleBarn.h
// Zweck : Prototypen etc für consoleBarn.cpp
// ----------------------------------------------------------------------------
//
// Funktionen zur Steuerung der Consolenausgabe unter
// Win2000, XP, Vista, Win95, Win98, WinNT
// ----------------------------------------------------------------------------

#ifndef _CONSOLE_BARN_
#define _CONSOLE_BARN_

#include <windows.h>

bool cls();
bool setCursor( int line, int row);
bool setColor( int color );

void getCursorPos( int& line, int& row);

void cursorOff();
void cursorOn();
void cursorOnOffSwitch();

#endif
