#include "stdafx.h"
#include <iostream>
#include "Sortieralgorithmen.h"

using namespace std;







int main()
{
	int arr[100], v, i = 0;

	cout << "Geben sie max. 100 Zahlen ein, oder beenden sie\n" 
					"ihre Eingabe ggf. mit einem Buchstaben\n\n";

	for(i = 0 ; cin >> v && i<100; i++)
	{ arr[i] = v; }

	BubbleSort(arr,0,i);

	cout << "\n\nBubbleSort...\n\n";

	for(int j = 1; j<=i; j++)
	{ cout << arr[j] << endl; }
}

