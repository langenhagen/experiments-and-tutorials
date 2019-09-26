/////////////////////////////////////////////////////////////////////////////////////////
// App.cpp enthält die Klasse OgreApp zur Darstellung einer OgreApplication
/////////////////////////////////////////////////////////////////////////////////////////
#include "Ogre.h"

#if defined(WIN32)
#	include <windows.h>
#endif

using namespace Ogre;

/////////////////////////////////////////////////////////////////////
// Plugins durch ein Root-Objekt Laden
void p1loadPlugins( Root *root)
{
	// Plugins separat laden    (!!! ATTENZIONE: Debug (_d) Modus !!!!)
	root->loadPlugin("Plugin_CgProgramManager_d");
	root->loadPlugin("Plugin_OctreeSceneManager_d");
	root->loadPlugin("RenderSystem_GL_d");
	try																													// Try/Catch-Block weil zB auf Linux kein 
	{ root->loadPlugin("RenderSystem_Direct3D9_d"); }						// Direct3D existiert; Plattformspezifische 
	catch( ...) {}																							// Exception abfangen, hirney!   ...softe
}
	
	

	

