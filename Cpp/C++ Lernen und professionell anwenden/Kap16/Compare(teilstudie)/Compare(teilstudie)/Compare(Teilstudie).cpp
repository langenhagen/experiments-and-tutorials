#include <iostream>
#include <string>

using namespace std;

struct Element { string name, telNr; };	

void main()
{

	string str1="Hallo du Troll", str2="Hallo";

	int len = str2.length();
	cout << len << endl;		
	cout << str1.compare(0,	len, str2);		//len muss genau die Länge von str2 haben
																				//um den Return-Wert 0 zu erhalten.


}