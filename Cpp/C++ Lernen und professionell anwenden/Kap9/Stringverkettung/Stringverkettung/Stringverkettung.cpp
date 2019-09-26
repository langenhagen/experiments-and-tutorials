#include "stdafx.h"
#include <iostream>
#include <string>

using namespace std;


int main()
{

	string	s1 = "Alle Jahre kommt ...",
					s2 = "wieder.";
	cout << s1 << endl << s2 << endl << endl;

	s1.insert(s1.find("kommt"), s2);
	cout << s1 << endl << endl;

	s1.erase(s1.rfind("kommt"));
	cout << s1 << endl << endl;

	s1.replace(s1.find("Jahre"),5, "kommen");
	cout << s1 << endl << endl;
}

