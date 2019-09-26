#include "stdafx.h"
#include <iostream>
#include "console.h"
#include <ctime>

using namespace std;

#define DELAY 60000000L


inline void warten(unsigned int seconds);

void main()
{
	char pause[] = "PAUSE****";
	int i, start = 0, len = (int)strlen(pause);

	cursorOff();
	setCursor(15,24);
	cout << "Beenden mit <Strg> + <C>";

	while(true)
	{
		setCursor(11,30);
		
		i = start;
		do
		{
			cout << pause[i++];
			if(i==len) i = 0;
		}
		while(i != start);

		++start;											//genial...
		start %= len;									//...

		warten(1);
	}
}

inline void warten(unsigned int seconds)
{
	time_t zeit = time(NULL) + seconds;
	while(time(NULL)<zeit) ;
}
