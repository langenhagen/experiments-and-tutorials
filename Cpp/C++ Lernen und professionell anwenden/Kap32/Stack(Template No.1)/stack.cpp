#include "stack.h"

/////////////////////////////////////////////////
// push() fügt ein Objekt oben an den Stack an
template<class T>
bool Stack<T>::push(const T& x)
{
	if( tip < max - 1)									// Falls noch Platz
	{
		basePtr[tip++] = x; return true;
	}
	else return false;
}

/////////////////////////////////////////////////
// pop() holt das oberste Objekt aus dem Stack
template<class T>
bool Stack<T>::pop(T &x)
{
	if( tip > 0)
	{
		x = basePtr[--tip]; return true;
	}
	else return false;
}
