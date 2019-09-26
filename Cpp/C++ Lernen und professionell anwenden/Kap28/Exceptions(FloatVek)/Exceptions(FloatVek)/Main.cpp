#include "FloatVek.h"
#include <iostream>
using namespace std;

void main()
{
	FloatVek a( 10, 4.3);

	cout << a;
	try
	{
		cout << a[55];
	}	
	catch(BadIndex& arr)
	{
		cout << "NIGGER!!!\n" << arr.getBadIndex();
		exit(1);
	}
}