#include "stdafx.h"
#include <iostream>
#include <cmath>
#include <string>
#include "console.h"

using namespace std;

#define HeadLine		(cout << "                        **** Die Sinus-Funktion ****\n")
#define LOCATE(z,s)	setCursor(z-1,s-1)
#define DOT(x,y)		(LOCATE(y,x), cout << '*')

void KoordSystem(int zOffset, int sOffset, string yCaption = "y", string xCaption = "x");

int main()
{
	HeadLine << endl;

	KoordSystem(15,10,"sin(x)","x");

	int j=0;

	for(int i=0; i<=69; ++i)
	{
		j=int(sin((double)i/10)*9+0.5);
		DOT(i+10,-j+15);
	}

	LOCATE(25,41);
}

void KoordSystem(int zOffset, int sOffset, string yCaption, string xCaption )
{
	//Pfeile Am rechten und oberen Achsenende setzen,
	//danach Bezeichnungen für die Achsen eintragen

	LOCATE(3,sOffset);
	cout << (char)30;					
	LOCATE(zOffset,80);			
	cout << (char)16;

	LOCATE(3,sOffset+2);
	cout << yCaption;					
	LOCATE(zOffset-1,80-xCaption.length()+1);			
	cout << xCaption;

	//In den For-Schleifen 1. die y-Achse, dann die x-Achse ziehen

	for(int i=4; i<=25; ++i)
	{
		LOCATE(i,sOffset);
		cout << (char)197;
	}

	for(int i=1; i<=79; ++i)
	{
		LOCATE(zOffset,i);
		i%5 ?	cout << (char)196 : cout << (char)197;
	}



}


