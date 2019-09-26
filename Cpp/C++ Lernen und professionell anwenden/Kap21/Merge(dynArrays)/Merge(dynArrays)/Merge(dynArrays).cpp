#include <iostream>
#include <iomanip>
#include <cstdlib>
#include <ctime>
#include "Sortieralgorithmen.h"
using namespace std;

#define LEN1	15
#define LEN2	25

int* merge( const int *v1 , const int *v2 , const int &lenV1 , const int &lenV2 );

void main()
{
	int	*pV1 = new int[LEN1],
			*pV2 = new int[LEN2],
			*newV,
			i = 0;

	srand( (unsigned int)time(NULL) );
	for( ; i < LEN1; i++ )
		pV1[i] = rand() % 100;
	for( i=0; i < LEN2; i++ )
		pV2[i] = rand() % 100; 

	SelSort( pV1, LEN1 );	SelSort( pV2, LEN2 );

	newV = merge( pV1, pV2, LEN1, LEN2 );

	delete[] pV1;	delete[] pV2;

	for( i=0; i< LEN1 + LEN2 ; i++ )
		cout << setw(2) << setfill('0') << i << ".\t" << *(newV + i) << endl;

}

int* merge( const int *v1 , const int *v2 , const int &lenV1 , const int &lenV2 )
{
	int	*sumV = new int[lenV1 + lenV2],
			i;
	
	for( i = 0; i < lenV1; i++ )
		*(sumV + i) = *(v1 + i);

	sumV += lenV1;
	for( i = 0; i < lenV2; i++ )
		*(sumV + i) = *(v2 + i);
	
	sumV -= lenV1;
	SelSort( sumV, lenV1 + lenV2 );
		return sumV;
}

/*FICKT EUCH ALLE MAL KRÄFTICH INS KNIE ICK KOMM DEMNÄCHST AUCH MAL ZU DEINR MUDDER!!*/