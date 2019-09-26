#include "Cell.h"
#include "List.h"

void main()
{
 BaseEl a(NULL, "NUKCLE");
 DerivedEl b(NULL, "NUKLE", "STINKENDFAUL");

 InhomList t;

 t.insert( "HOHO");
 t.insert( "HELLOW!");
 t.insert( "ART" );
 t.insert( "TOXIC");

 InhomList k(t);

 t.displayAll();
 cout << endl << endl;
 t.erase( "TOXIC");
 t.displayAll();
 cout << endl << endl;
 t.erase( "ART");
 cout << "\n t nach Löschen von Toxic und Art\n";
 t.displayAll();
 
 cout << endl << endl << "Inhomlist k:\n";
 k.displayAll();

 cout << "Test nach =::\n";
 k = t;
 cout << endl << endl << "Inhomlist k:\n";
 k.displayAll();

  
}