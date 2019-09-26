// Arbeiten mit Namespaces, Namenskonflikte l�sen

#include "stdafx.h"
#include <iostream>
#include <iomanip>		// Nur f�r setw()
#include "tool1.h"		// integrierter Namespace

namespace TOOL2				// ganze Header-Datei nachtr�glich  
{											// mit Namespace TOOl2 'umklammern'			
#include "tool2.h"		// #include Direktive MUSS in eigener 
}											// Zeile und alleine stehen


using namespace std;

int main()
{
	cout << "TOOL1" << setw(10) << TOOL1::calculate(1.1,2.3) << endl
			 << "TOOL2" << setw(10) << TOOL2::calculate(1.1,2.3) << endl;
}

