// funcptr.cpp		Demonstriert die Verwendung eines
//								Vektors mit Zeigern auf Funktionen
//----------------------------------------------------------
#include <iostream>
#include <cstdlib>			// Prototyp von atoi()		O,ô
#include <cctype>				// Makros toupper(), tolower()
using namespace std;

void	error_message(char*), message( char*),
			message_up( char*), message_low( char*);

void (*functab[]) ( char*) = {	error_message, message,
																message_up, message_low };

char aufruf[] = "Eingabe: 1, 2 oder 3 !!!";

void main()
{
	int n= 0;

	cout << "Welche von drei Funktionen soll aufgerufen werden (1, 2 oder 3)?\n";
	cin >> n;

	if( n<1 || n> 3)
		(*functab[0]) (aufruf);
	else
		(*functab[n]) ("Hello, World!\n");
}

void error_message( char *s)	
{ 
	cerr << s << endl;
}

void message( char *s)
{
	cout << s << endl;
}

void message_up( char *s)
{
	char c;
	for(; *s != '\0'; s++)
		c = toupper(*s) , cout.put(c);
}

void message_low( char *s)
{
	char c;
	for(; *s != '\0'; s++)
		c = tolower(*s), cout.put(c);
}
