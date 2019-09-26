#include <iostream>

using namespace std;

void main()
{
	int i;

	while(1)
	{
		cout << "Gibt Nummer ein:"; cin >> i;
		cin.sync();
		cout << i << "/2 = " << i/2 << endl << endl;
	}
}