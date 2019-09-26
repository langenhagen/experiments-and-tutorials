#include <iostream>
#include <iomanip>
using namespace std;

#define LENGTH1						20
#define LENGTH2						13
#define SCHNITTPOS				14		

int* splice( int *pV1, int *pV2, const int &lenV1, const int &lenV2, const int &weldingPoint );

void main()
{
	int	*v1 = new int[LENGTH1],
			*v2 = new int[LENGTH2],
			*newV,
			i=0;


	for( i=0 ; i < LENGTH1; i++ )
		v1[i] = (i+1)*100;
	for( i=0; i < LENGTH2; i++ )
		v2[i] = i;
	
	newV = splice( v1, v2 , LENGTH1, LENGTH2, SCHNITTPOS );

	delete[] v1;
	delete[] v2;
	
	int len = LENGTH1 + LENGTH2;
	for( int i=0; i < len; i++ )
	{
		cout << setfill('0') << setw(2) << i +1 << ".\t" << *(newV + i) << endl;
	}
}

int* splice( int *pV1, int *pV2, const int &lenV1, const int &lenV2, const int &wPoint )
{
	int	i = 0,
			*pVector = new int[lenV1 + lenV2];

	for(; i < wPoint; i++)
		*(pVector + i) = *(pV1 + i);

	pVector += wPoint;
	
	for( i=0; i < lenV2; i++ )
		*(pVector + i) = *(pV2 + i);

	pVector += lenV2;
	pV1 += wPoint;
	int maxLen = lenV1 - wPoint;

	for( i=0; i < maxLen; i++ )
		*(pVector + i) = *(pV1 + i);

	return pVector - (wPoint + lenV2);
}