#include "ClassTemplateVector.h"
#include "DayTime.h"
#include <iostream>
using namespace std;


int main()
{

	try
	{
	Vector<int, 26> zarajewo( 10, 2);
	Vector<double, 50> hiroshima( 20, 3.21);

	hiroshima.append( 1.9);
	zarajewo.insert( 4, 6);

	hiroshima.remove( 7); zarajewo.remove( 3);

	cout << hiroshima << zarajewo;

	// Klapppt bis hierher //////////////////////////////////////////////////////////////////////////

	Vector< DayTime, 50> lai(5, DayTime(1,1,1));

	cout << lai;
	
	DayTime lok( 13,57,33);

	lai.insert(lok,0);
	lai.append( DayTime(12,14,17));
	lai.remove( 3);

	cout << lai;
	
	cin.get();

	}
	catch( OutOfRange)
	{
		cout << "\nError: Out Of Range!\n";
		exit(1);
	}
	catch(BadIndex &err)
	{
		cout << "\nBadIndex Error: " << err.getBadIndex() << endl;
		exit(2);
	}

	return 0;


}