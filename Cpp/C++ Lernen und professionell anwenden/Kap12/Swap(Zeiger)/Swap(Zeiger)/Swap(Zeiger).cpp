// zeiger2.cpp
// Definition und Aufruf der Funktion swap().
// Zeigt die Verwendung von Zeigern als Parameter.
// ----------------------------------------------------
#include "stdafx.h"
#include <iostream>
using namespace std;

void swap( float *, float *);   // Prototyp von swap()
void swapRef( float& , float&); 

int main()
{
  float x = 11.1F;
  float y = 22.2F;

  cout << "Test von swap()...\n" << "x und y vor dem Tauschen:  "
       << x << "   " << y << endl;
  
  swap( &x, &y );

  cout << "x und y nach dem Tauschen: "
       << x << "   " << y << endl << endl;

	x = 11.1F;
  y = 22.2F;


  cout << "Und jetz swapRef()...\n" << "x und y vor dem Tauschen:  "
       << x << "   " << y << endl;
  
  swapRef( x, y );

  cout << "x und y nach dem Tauschen: "
       << x << "   " << y << endl;
 
  return 0;
} 

void swap(float* p1, float* p2)
{
  float temp;          // Hilfsvariable 

  temp = *p1;          // Bei obigem Aufruf zeigt
  *p1  = *p2;          // p1 auf x und p2 auf y
  *p2  = temp;
}

void swapRef(float& p1, float& p2)
{
 float temp;          // Hilfsvariable 

 temp = p1;						// Bei obigem Aufruf zeigt
 p1   = p2;						// p1 auf x und p2 auf y
 p2   = temp;
}