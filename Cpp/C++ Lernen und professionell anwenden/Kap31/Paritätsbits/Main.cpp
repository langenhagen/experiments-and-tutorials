// Zeigt eine Anwendung von Paritätsbits
#include <iostream>
using namespace std;

inline unsigned int bit0( unsigned int x)
{
	return (x & 1);
}

int parity( unsigned int n)
{
	unsigned int par = 0;

	for(; n!=0; n >>=1)
		par ^= bit0( n);
	return par;
}

void main()
{
	unsigned int x;

	cout << "Ya know whut to do!\n>> "; cin >> x;

	if(parity(x) != 0)
		cout << "Ey, die Zahl hat ja mal ne ungerade Anzahl an Bits!\n";
	else
		cout << "Ey, de Zahl hat ja mal ne korrekt gerade Anzahl an Bits!\n";
}