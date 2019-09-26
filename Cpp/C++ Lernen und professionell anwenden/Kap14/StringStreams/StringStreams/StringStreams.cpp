/* Siehe Übungen Kapitel 14 (Seite 308) zu StringStreams*/

#include <iostream>
#include <sstream>
#include <iomanip>

using namespace std;

void main()
{
double x=03;
string str;

stringstream iostr;

int day   = 27,
		month = 1,
		year  = 2007;

iostr << setfill('0') << setw(2) << day << '.'
			<< setw(2) << month << '.'
			<< setw(4) << year;

iostr >> str;

cout << str;
}