#include <iostream>
#include "DayTime.h"

using namespace std;

void main()
{
	DayTime Times[4] = {	DayTime(5,30),
												DayTime(13,40,10),
												DayTime(13,40,11),
												DayTime(3,59,59) };

	char anz = sizeof(Times) / sizeof(DayTime);

	for(char i = 0; i < anz; i++)
	{
		Times[i].print();
		cout << endl;
	}

	DayTime *small(&Times[0]), *big(&Times[0]);
	
	for(char i = 1; i<anz; i++)
	{
		if(Times[i].isLess(*small))
			small = &Times[i];
		else if(big->isLess(Times[i]))
			big = &Times[i];
	}	
		
	cout << "\nDie kleinste Zeit: "; small->print();
	cout << "\n\nDie groesste Zeit: "; big->print();
	cout << endl << endl;
}