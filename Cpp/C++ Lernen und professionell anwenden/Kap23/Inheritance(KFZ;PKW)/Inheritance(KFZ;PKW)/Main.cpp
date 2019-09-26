#include "Kfz.h"

void main()
{
	Lkw a;
	a.display();
	int i;
	Lkw *ptrLkw = NULL;
	Pkw *ptrPkw = NULL;

	while(cin >> i && i>1)
	{
		if( cin >> i && i == 1)
			{
			ptrLkw = new Lkw( 4, 34345, 3344546, "jezz");
			ptrLkw->display();
			}
		else if (i == 2)
		{
			ptrPkw = new Pkw("fihio",0,445,"5fg");
			ptrPkw->display();
		}
	}	
}