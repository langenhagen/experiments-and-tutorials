/////////////////////////////////////////////////////////////////////////////
// Header-Datei stack.h für das Klassen-Template Stack<T, n>
// zur Darstellung von Stacks
/////////////////////////////////////////////////////////////////////////////
#include <iostream>
#include <iomanip>
using namespace std;

//####################################################################################### Stack<T,n>
// Klassen-Template Stack<T,n>
template<class T, int n>
class Stack
{
private:
	T		vek[n];		// Vektor
	int tip;			// Stack-Spitze
	int max;			// max. Anzahl der Elemente
public:
	Stack()		{ max = n; tip = 0; }

	int getMax() { return max; }

	bool empty()	{ return (tip == 0); }
	bool push( const T& x );
	bool pop( T& x);		
};

/////////////////////////////////////////////////
// push() fügt ein Objekt oben an den Stack an
template<class T, int n>
bool Stack<T,n>::push(const T& x)
{
	if( tip < max )									// Falls noch Platz
	{
		vek[tip++] = x; return true;
	}
	else return false;
}

/////////////////////////////////////////////////
// pop() holt das oberste Objekt aus dem Stack
template<class T, int n>
bool Stack<T,n>::pop(T &x)
{
	if( tip > 0)
	{
		x = vek[--tip]; return true;
	}
	else return false;
}

//####################################################################################### Zusätzliche Funktionen

typedef Stack<unsigned, 256>			USTACK;		// USTACK: Stack mit 256 unsigned-Slots

/////////////////////////////////////////////////
// füllt USTACK über die Standardeingabe,
// Abbruch mit 0
void fill( USTACK& stk)
{
	unsigned x;
	cout	<< "\nPositive ganze Zahlen eingeben "
				<< "(Abbruch mit 0):\n";
	while( cin >> x && x != 0)
		if( !stk.push(x))
		{
			cerr << "Stack voll!\n"; break;
		}
}
/////////////////////////////////////////////////
// leert USTACK, gibt Daten formatiert aus
void clear( USTACK& stk)
{
	unsigned x;

	while( stk.pop(x) )
		cout << setw(4) << x << "   ";
	cerr << "\nStack leer!\n";
}