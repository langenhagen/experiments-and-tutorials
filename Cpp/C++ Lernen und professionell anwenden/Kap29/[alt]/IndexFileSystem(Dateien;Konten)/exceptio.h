/////////////////////////////////////////////////////////////////////////////
// Enthält Fehlerklassen zur Dateiverarbeitung
/////////////////////////////////////////////////////////////////////////////
#ifndef _EXCEPTIO_H
#define _EXCEPTIO_H
#include <string>
#include <iostream>
using namespace std;

class file_error
{
private:
	string dateiname;
public:
	file_error( const string& file) : dateiname(file) {}
	string getName() const { return dateiname;}
};

class open_error : public file_error
{
public:
	open_error( const string& file) : file_error(file) {}
};

class read_error : public file_error
{
public:
	read_error( const string& file) : file_error(file) {}
};

class write_error : public file_error
{
public:
	write_error(const string& file) : file_error(file) {}
};

#endif