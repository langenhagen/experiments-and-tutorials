#include "stdafx.h"		//IOSTREAM und CTIME schon ab Datum.h inkludiert
#include "Datum.h"

using namespace std;

void main()
{

Datum ownInput;
Datum now;

ownInput.init(23,12,2009);
now.init();

now.print();

cout << endl << endl;

ownInput.print();

cout << endl << endl;

}

