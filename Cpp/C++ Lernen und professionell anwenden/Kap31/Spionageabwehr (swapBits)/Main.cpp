#include <iostream>
using namespace std;

int swapBits( int val, int p1, int p2)
{
	int mx = 8*sizeof(int);
	
	if(	p1 == p2 ||											// Kriterien beachten
			p1 < 0 || p2 < 0 ||
			p1 >= mx || p2 >= mx )
		return val;
	
	int b1 = (val >> p1) & 1 ; 
	int b2 = (val >> p2) & 1 ;

	val &= ~((1 << p1) | (1 << p2));		// Bits an Positionen löschen

	b1 <<= p2;	b2 <<= p1;							// Bitpositionen vertauschen
	val |= (b1 | b2);										// vertauschte Bits setzen

	return val;
}

void main()
{
	int x, o1, o2;
	cout << "Zahl eingeben: ";
	cin >> x;
	cout << "Offstet #1 eingeben: ";
	cin >> o1;
	cout << "Offstet #2 eingeben: ";
	cin >> o2;

	cout << endl << swapBits(x,o1,o2) << endl;
}