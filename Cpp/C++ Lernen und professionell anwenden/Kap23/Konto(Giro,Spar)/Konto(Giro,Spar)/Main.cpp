#include "Konto.h"
#include <iostream>
using namespace std;

void main()
{
	GiroKonto joga( "Anna Liebkow", 44344, 2323, 3);

	SparKonto die4tedim;

	die4tedim.display();

	joga.display();

	Konto jarr;

	jarr = joga;
	jarr.display();
}