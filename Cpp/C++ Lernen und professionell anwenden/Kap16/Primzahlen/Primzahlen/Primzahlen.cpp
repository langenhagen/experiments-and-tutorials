#include <iostream>
#include <iomanip>

using namespace std;

#define MAX		1000

void main()
{

	register int i,j;

	bool Prim[MAX] = { false, false };

	for( i = 2; i<MAX; i++)	Prim[i] = true;

	for( i = 2; i<MAX/2; i++)
		if(Prim[i])
			for( j = i+i; j<MAX; j += i)
				Prim[j] = false;

	j = 0;
	for(i = 0; i<MAX; i++)
		if(Prim[i] == true)
			cout << i << endl, j++;

	cout << "\nSumme: " << j << endl;


}