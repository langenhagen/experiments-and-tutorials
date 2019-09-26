#include <iostream>

using namespace std;

void main(int argc, char *argv[], char *env[])
{
	cout << "argc: " << argc << endl << endl;

	cout << "\nARGUMENTS----------------------------------------------------------------------\n\n";

	for(int i=0; i<argc; i++)
		cout << "argv[" << i << "]\n" << argv[i] << endl;

	cout << "\nENVIRONMENT--------------------------------------------------------------------\n\n";

	for(int i=0; env[i] != NULL; i++)
		cout << "env[" << i << "]\n" << env[i] << endl << endl;

	cout << "\n\n*********************************PROGRAMM ENDE**********************************";

	cin.get();
}