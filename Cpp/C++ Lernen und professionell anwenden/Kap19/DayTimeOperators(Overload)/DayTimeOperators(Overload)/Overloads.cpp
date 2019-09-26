#include <iostream>
#include "DayTime.h"

using namespace std;

void main()
{

	DayTime j(0,0,1);

	DayTime i(j);


	cout << i--;
	cout << endl << --i;

}