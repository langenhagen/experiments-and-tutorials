#include "Liste.h"

void main()
{
	ListEl d,k;
	Datum j(11,2,1988), r(31,1,1111);
	k.setDatum(j);
	k.setBetrag(800000000.6);
	cout << k;

	cout << endl << endl;

	Liste list;

	cout << list.getBack() << endl << list.getFront();
	list.popFront();
	list.pushBack(j,223);
	list.pushBack(r, 80.6);
	list.pushBack(Datum(4,3,2),22.02);


	cout << list.getBack() << " " << list.getFront() << endl << endl;
	list.popFront();

	cout << list;
}