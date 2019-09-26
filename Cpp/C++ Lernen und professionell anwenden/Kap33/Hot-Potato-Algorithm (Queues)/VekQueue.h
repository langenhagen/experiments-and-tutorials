/////////////////////////////////////////////////////////////////////////////
// Header-Datei VekQueue.h f�r das Klassen-Template VekQueue<T>
// zur Darstellung von Queue-Containern
/////////////////////////////////////////////////////////////////////////////
#ifndef _VEKQUEUE_H
#define _VEKQUEUE_H

#include <bitset>
#include <cstdlib>
#include <ctime>
#include <queue>
#include <vector>
using namespace std;

//####################################################################################### VekQueue<T>
template< class T>
class VekQueue
{
private:
	vector< queue<T> > vek;		// Der Queue-Vektor
	int anz;									// Anzahl der Queues
public:
	VekQueue( int num);

	int size() const;
	int size( int i) const;

	bool empty() const;
	bool empty( int i) const;

	void push(const T& msg);

	const T& pop();
	const T& pop( int i);
};

/////////////////////////////////////////////////
// Konstruktor von VekQueue
template< class T>
VekQueue<T>::VekQueue( int num)
{
	if(num>0)
	{
		vek.resize(num);
	  anz = num;
	}
	else
	{
		vek.resize(0);
	  anz = 0;
	}
}

/////////////////////////////////////////////////
// Liefert Anzahl aller Elemente in den Queues
template<class T>
int VekQueue<T>::size() const
{
	int cnt;

	for( int i=0; i< anz; i++)
		cnt += vek[i].size();
	return cnt;	
}

/////////////////////////////////////////////////
// Liefert Anzahl aller Elemente des i-ten Queues
template< class T>
int VekQueue<T>::size(int i) const
{
	if( i >= 0 && i<anz)
		return vek[i].size();
	return -1;
}

/////////////////////////////////////////////////
// Gibt an, ob alle Queues leer sind
template<class T>
bool VekQueue<T>::empty() const
{ return size() == 0; }

/////////////////////////////////////////////////
// Gibt an, ob der i-te Queue leer ist
template< class T>
bool VekQueue<T>::empty(int i) const
{
	if( i>=0 && i<anz)
		return ( vek[i].empty() );
}

/////////////////////////////////////////////////
// F�gt ein neues Element per 
// Hop-Potato-Alogrithmus an den Container an
template<class T>
void VekQueue<T>::push(const T& msg)
{
	int srt=0;														// K�rzesten Queue ermitteln
	for(int i=1; i< anz; i++)
		if( vek[srt].size() > vek[i].size())
			srt=i;

	vek[srt].push(msg);										// einf�gen
}

/////////////////////////////////////////////////
// Gibt ein Element aus einem zuf�llig gew�hlten
// Queue aus
template<class T>
const T& VekQueue<T>::pop()
{
	time_t seed;								// Zufallsgenerator initialisieren
	time( &seed);
	srand( (unsigned)seed ); 

	T temp;
	int nq;
													
	nq = rand() % anz;											// Einen Queue zuf�llig w�hlen
	
	while( vek[nq].empty() )
		nq = rand() % anz;	
	if( !vek[nq].empty() )
	{	
		temp = vek[nq].front();
		vek[nq].pop();
	}
	return temp;
}

/////////////////////////////////////////////////
// Gibt ein Element aus dem i-ten Queue aus
template<class T>
const T& VekQueue<T>::pop(int i)
{
	T temp;
	if( i>=0 && i<anz && !vek[i].empty() )
	{	
		temp = vek[i].front();
		vek[i].pop();
	}
	return temp;
}

#endif