#include "VekQueue.h"
#include <iostream>
#include <iomanip>
#include <cstdlib>
using namespace std;

void main()
{
	int nQueues = 9;
	VekQueue<int> vq( nQueues );

	cout	<< "es wurden " << nQueues << " Queues angelegt\n" 
				<< "und werden nun per Hot-Potato-Algorithmus gefuellt...\n";

	int m =100, n= m/2;
	for(int i=0; i<m; i++)
		vq.push( rand()%100 );
	cout << "...Gefuellt!\n";

	cout << endl << n << " Elemente zufaellig entfernen...\n";

	for(int i=0; i<n; i++)
		vq.pop();
	
	cout << "\nDie Restlichen Elemente anzeigen und entfernen...\n";

	int cnt=0;

	for(int i=0; i<nQueues; i++)
	{
		cout << endl << endl << i+1 << ". Queue: ";
		while( !vq.empty(i) )
		{
			cout << setw(4) << vq.pop(i);
			cnt++;
		}
	}

	cout << endl << endl << cnt << " Elemente entfernt.\n";
	cin.get();
}