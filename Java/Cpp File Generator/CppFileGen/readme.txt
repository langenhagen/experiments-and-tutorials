C++ File Generator
autor: langenhagen
version: 20121008

Starten der jar mit Doppelklick. Für Rückgaben des Programms ist die log-Datei zu befragen.
Config.txt ist die Eingabeschnittstelle. Dort kann sowas wie Autor der Dateien festegelegt werden, und zu den File-Typen .cpp, .hpp und .h Templates zugeordnet werden. Auch können die root-Verzeichnisse für zu erstellende .cpps und .hpps festgelegt werden.

Weiterhin folgen die Header, die erstellt werden sollen. Unterordner sind möglich. Zu jedem Header wird automatisch an der richtigen Stelle eine cpp erstellt, es sei denn, man schreibt hinter den Header "-nocpp".

Bestehende Dateien werden nicht überschrieben.

===================================================================================================

In den Templates lassen sich folgende Keywords benutzen:

Links das Keyword und rechts, wie es interpretiert wird

%filename%			==>		Name der zu erstellenden Datei (ohne Dateinamenerweiterung)
%author%			==>		angegebener Autor in der config.txt
%date%				==>		aktuelles Datum im Format yyyyMMdd
%namespace%			==>		der Namespace
%ifndef%			==>		#ifndef __<DATEINAME>_H__ bzw. __<DATEINAME>_HPP__
%define%			==>		#define __<DATEINAME>_H__ bzw. __<DATEINAME>_HPP__
%endif%				==>		#endif /* __<DATEINAME>_H__ */ bzw. #endif /* __<DATEINAME>_HPP__ */
%includeheader%		==>		#include <header-datei>
