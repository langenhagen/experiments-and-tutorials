#include "quadmat.h"
#include <iomanip>
using namespace std;

template class QuadMatrix<long double, 5>;				// Explizite Instanziierung
// ...anders als QuadMatrix<long double, 5> quadmat; <-> Implizit

void main()
{
	QuadMatrix<long double, 5> m;

	try
	{
		for(int k=0; k< m.dim(); k++)
		{
			for( int l=0; l<m.dim(); l++)
			{
				m[k][l] = k*l;	cout << setw(2) << m[k][l] << " ";
			}
			cout << endl;
		}
	}
	catch(out_of_range &err)
	{
		cout << err.what();
	}

	cin.get();
}