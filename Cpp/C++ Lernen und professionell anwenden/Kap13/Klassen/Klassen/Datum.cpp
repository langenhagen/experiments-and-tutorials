#include "stdafx.h"
#include "Datum.h"

using namespace std;

void Datum::init(short tag, short monat, short jahr)
{
	day		= tag;
	month	= monat;
	year	= jahr;
}

void Datum::init()
{
	tm *zeit;
	time_t sek;
	time(&sek);

	zeit = localtime(&sek);

	day		= zeit->tm_mday;
	month	=	zeit->tm_mon + 1;
	year	= zeit->tm_year + 1900;
}

void Datum::print()
{
	cout << day << '.' << month << '.' << year;
}