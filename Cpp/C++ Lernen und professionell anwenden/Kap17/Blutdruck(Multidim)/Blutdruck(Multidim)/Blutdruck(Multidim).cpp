#include <iostream>
using namespace std;

int MatrixSum(int Matrix2d[][5], int MatrixLength, int Zeilen[], int Spalten[]);

void main()
{
	int Matrix[3][5]	= {	{ 25, 34, 26 , 12, 8 },
												{ 19, 27, 24 , 11, 4 },
												{ 6,  15, 35, 36, 18 }	},
			ZVektor[3]		= { 0,0,0 },
			SVektor[5]		= { 0,0,0,0,0 };

	cout << MatrixSum(Matrix,3,ZVektor,SVektor) << endl << endl;

	for(int i=0; i<3; i++)
		cout << ZVektor[i] << endl;
	cout << endl;
	for(int i=0; i<5; i++)
		cout << SVektor[i] << endl;
}

int MatrixSum(int M2d[][5], int mLen, int zl[], int sp[])
{
	int i, j, sum=0;

	for(i=0; i<mLen; i++)
		for(j=0; j<5; j++)
			zl[i] += M2d[i][j];

	for(j=0; j<5; j++)
		for(i=0; i<mLen; i++)
			sp[j] += M2d[i][j];
	
	return ( zl[0] + zl[1] + zl[2] );
}