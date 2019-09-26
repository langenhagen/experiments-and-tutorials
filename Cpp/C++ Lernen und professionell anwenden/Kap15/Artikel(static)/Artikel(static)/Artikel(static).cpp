#include "stdafx.h"
#include "Artikel.h"

Artikel* test(Artikel& berg);

Artikel luger;

void main()
{
	Artikel lulatsch(344,"One",5.7);
	lulatsch.setBez("tWo");

	test(lulatsch)->print();
}

Artikel* test(Artikel& berg)
{
 berg.setBez("Half");
	static Artikel tronk(berg);
 tronk.setBez("tHrEE");
	return &tronk;
}