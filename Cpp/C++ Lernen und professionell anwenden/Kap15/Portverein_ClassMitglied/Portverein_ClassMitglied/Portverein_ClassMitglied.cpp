#include "stdafx.h"
#include "Mitglied.h"

int main()
{

	Mitglied Hans(1,"Hans Peter Wurst",17,2,1988);
	Hans.print();

	Mitglied Tork(2,"Tork",17,11,2008);
	Tork.print();

	Mitglied::setVorstand(&Hans);

	Mitglied *ptr = Mitglied::getVorstand();

	cout << "\nund der Vorstand ist...";
	if(Mitglied::getVorstand())
		ptr->print();
	else
		cout << "\nkein Vorstand am Start\n";

}

