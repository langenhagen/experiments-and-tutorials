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
	char c;

	while( (c = cin.get()) != EOF )
	{
		if( c >= 32)
		{
			c = swapBits( c,5,6);
			c = swapBits( c,0,4);
			c = swapBits( c,1,3);
		}
		cout << c;
	}

}