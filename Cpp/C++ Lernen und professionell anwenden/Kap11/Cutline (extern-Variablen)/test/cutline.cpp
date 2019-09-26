#include "stdafx.h"
#include <string>
using namespace std;

extern string line;             // extern-Deklaration

void cutline()
{
   int i = line.size();         // Position hinter 
                                // dem letzen Zeichen.
   while( i-- >= 0 )
     if( line[i] != ' ' && line[i] != '\t' )
       break;                   // -> Schleife beenden.

   line.resize(++i);            // neue Länge festlegen.
}
