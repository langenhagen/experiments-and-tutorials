#include "matrix.h"


void main()
{
	try
	{
		Matrix kat(16,12,2.29);

		kat.display();
	}
	catch(out_of_range& err)
	{
		cerr << err.what() << endl;
	}
	
}