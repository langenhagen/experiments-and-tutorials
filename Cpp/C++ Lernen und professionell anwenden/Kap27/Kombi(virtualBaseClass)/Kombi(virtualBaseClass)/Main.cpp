#include "Kfz.h"

void main()
{
	Transporter a( "Nigger", 561456, 585.2);

	a.display();

	Kombi b( "Schniekl", "jess", 777777, "Volkswagon", 500, 6);

	b.display();

	cout << sizeof(Kombi) << endl << sizeof(b);
}
