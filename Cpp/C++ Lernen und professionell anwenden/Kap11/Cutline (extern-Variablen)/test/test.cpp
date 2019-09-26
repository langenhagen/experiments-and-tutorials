// test.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <string>
using namespace std;

void cutline( void );          // Prototyp
string line;                   // Globaler String


int main()
{
 while( getline(cin, line))  // Solange eine Zeile 
   {                           // eingelesen werden kann. 
     cutline();                // Zeile kürzen
     cout << line << endl;     // Zeile ausgeben
   }
   return 0;

}

