// Arbeiten mit Namespaces, Namenskonflikte lösen

#include "stdafx.h"
#include <iostream>
#include <iomanip>		// Nur für setw()
#include "tool1.h"		// integrierter Namespace

namespace TOOL2				// ganze Header-Datei nachträglich  
{											// mit Namespace TOOl2 'umklammern'			
#include "tool2.h"		// #include Direktive MUSS in eigener 
}											// Zeile und alleine stehen


using namespace std;

int main()
{
	cout << "TOOL1" << setw(10) << TOOL1::calculate(1.1,2.3) << endl
			 << "TOOL2" << setw(10) << TOOL2::calculate(1.1,2.3) << endl;
}

