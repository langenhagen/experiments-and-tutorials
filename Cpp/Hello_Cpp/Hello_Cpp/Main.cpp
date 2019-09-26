#include <iostream>

#include "A.hpp"

using namespace std;
using namespace Moef;

A createA()
{
	return A(11);
}

int main( int argc, char** argv)
{
	// wenn keine using anweisung
	std::cout << "Hallo LEILY";
	/*
	// klappt nicht ohne die using direktive
	cout << endl << "ICH WERDE DIE WELT ZERSTOEEEREEEEN!\n";

	int i = 23;

	cout << "i: " << i << endl << "Und jetzt?";
	cin >> i;
	cout << "i: " << i;

	// buffer syncen
	cin.sync();
	*/

	A a1 = A();
	A a2 = A(5);

	a1.getI();

	A* a3 = &a1; // pointet auf die speicherstelle von a1

	a3->getI();

	//&l: platz 1 //  l: leily's wert: 2 // *l: platz 2
	//&l l *l

	int* leily;
	int andi = 42;

	leily = &andi;


	cout << a1.getI() << endl << a2.getI() << endl << (*a3).getI();

	// einfache tastenabfrage, damit hier nicht alles durch rattert und automatisch beendet
	std::cin.get();


	cout << "A& a = createA(); ...    // A createA() { return A(11); }\n";
	A& a = createA();
	cout << "a.getI() = " << a.getI() << endl;


	std::cin.get();
}

