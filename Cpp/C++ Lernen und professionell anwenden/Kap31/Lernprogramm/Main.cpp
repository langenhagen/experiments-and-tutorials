#include <iostream>
#include <iomanip>
using namespace std;

void putBits( unsigned int n)
{
	for( int i=15; i>=0; i--)
	{
		cout << ( (n >> i) & 1 );								// der rechnet halt den binären Code wieder zurück in Dezimal
		if( i % 4 ==0  && i>0 )									// ehe er ihn ausspuckt, aber da nur das letzte Bit (nur) den Wert 1
			cout << ' ';													// haben _KANN_ wird alles gut^^
	}	
}

void main()
{
	int x, y, anz;
	char b;
	do
	{
		cout	<< "          ******   BITOPERATOREN   ******\n\n"
					<< "Bitte geben sie zwei ganze Zahlen ein.\n\n"
					
					<< "1. Zahl  -->  "; cin >> x;
		cout	<< "2. Zahl  -->  "; cin >> y;
		cout << endl;
		
		cout << "Das Bitmuster von " << setw(4) << x << " = x :  ";
		putBits(x); cout << endl;
		cout << "Das Bitmuster von " << setw(4) << y << " = y :  ";
		putBits(y); cout << endl;

		cout	<< "Das Bitmuster von    x & y :  "; putBits( x & y); cout << endl
					<< "Das Bitmuster von    x | y :  "; putBits( x | y); cout << endl
					<< "Das Bitmuster von    x ^ y :  "; putBits( x ^ y); cout << endl << endl
		
					<< "Um wie viele Positionen soll x geshiftet werden?\n"
					<< "Anzahl --> "; cin >> anz; cout << endl

					<< "Das Bitmuster von   x << " << anz << " : "; putBits( x << anz); cout << endl
					<< "Das Bitmuster von   x >> " << anz << " : "; putBits( x >> anz);

		cout << "\n\nWiederholen (j/n)? "; 
		cin.sync();		cin.get(b);
	}
	while( b == 'j' || b == 'J');

}