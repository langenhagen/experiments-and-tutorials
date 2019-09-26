#include "OgreApp.h"

#if defined(WIN32)
#	include <windows.h>
#endif

// Main-Funktionsart nach Betriebssystem auswählen...
#if defined (WIN32)
INT WINAPI WinMain ( HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow )
#else
int main (int argc, char *argv[])
#endif
{
	OgreApp app;

//	ObjectMgr b(&app);

	app.setup( "OpenGL Rendering Subsystem", 800, 600, false, "4");
	app.run();

	return 0;
}