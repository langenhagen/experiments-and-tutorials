#include "Ogre.h"
#include <iostream>

#if defined(WIN32)
#	include <windows.h>
#endif

using namespace Ogre;

// Main-Funktionsart nach Betriebssystem auswählen...
#if defined (WIN32)
INT WINAPI WinMain ( HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow )
#else
int main (int argc, char *argv[])
#endif
{
	Root *root = new Root("", "");								// Root ohne jedes Plugin laden

	// Plugins separat laden    (!!! ATTENZIONE: Debug (_d) Modus !!!!)
	root->loadPlugin("Plugin_CgProgramManager_d");
	root->loadPlugin("Plugin_OctreeSceneManager_d");
	root->loadPlugin("RenderSystem_GL_d");
	try																													// Try/Catch-Block weil zB auf Linux kein 
	{ root->loadPlugin("RenderSystem_Direct3D9_d"); }						// Direct3D existiert; Plattformspezifische 
	catch( ...) {}																							// Exception abfangen, hirney!   ...softe

	// Auswahl des Rendersystems, als ob der User wat eingegeben hätte...
	String rsName("OpenGL Rendering Subsystem");
	RenderSystemList *rsList = root->getAvailableRenderers();
	RenderSystem *rSys;
	RenderSystemList::iterator it = rsList->begin();

	while( it != rsList->end() )							//Schleife durchläuft die Liste der Rendersysteme,
	{																					// sucht nach rsName
		rSys = *(it++);
		if( rSys->getName() == rsName )
		{
			root->setRenderSystem( rSys);
			break;
		}
	}
	if( root->getRenderSystem() == NULL)			// wenn rsName nich gefunden wurde, App beenden
	{
		delete root;
		return -1;
	}

	root->initialise(false);									// root ohne Fenster zu Fenster zu kreiern initialisieren
	
	NameValuePairList wndwArgs;								// Spezielle Eigenschaften für das RenderWindow
	wndwArgs["left"] = "20";									// look@ OgreRenderSystem.h 4 further info
	wndwArgs["top"] = "10";
	wndwArgs["title"] = "Barns Hello World #2 (Complex) @ Ogre";

	RenderWindow *window = rSys->createRenderWindow(			// RenderWindow manuell übers RenderSystem kreieren
		"Barns Hello World #2", 800, 600, false, &wndwArgs);

	// Scene Manager kreieren
	SceneManager *sceneMgr = root->createSceneManager(ST_GENERIC, "SceneManager1");

	// Cam und ViewPorts
	Camera *cam = sceneMgr->createCamera("Cam1");
	Viewport *vpBottom, *vpTop;

	// ViewPorts einstellen
	vpBottom = window->addViewport( cam, 0);
	vpTop = window->addViewport( cam, 1, 0.375f, 0.375f, 0.25f, 0.25f);
	vpTop->setBackgroundColour( ColourValue( .0, .0, 1.0));
	vpTop->setOverlaysEnabled(false);
	vpTop->setSkiesEnabled(false);
	vpTop->setShadowsEnabled(true);

	// Cam Einstellen
	cam->setAspectRatio( (float)vpBottom->getActualWidth() / (float)vpBottom->getActualHeight() );
	cam->setNearClipDistance(1);
	cam->setFarClipDistance(1000);
	cam->setFOVy( Degree(30.0));

	// Manuelle RenderLoop vorbereiten...
	bool keepRunning = true;
	Timer *tim = new Timer();
	
	float cntr = 0.0f; 

	while(keepRunning && window->isActive() )					// und die RenderLoop
	{
		tim->reset();
		root->renderOneFrame();
		cntr += ((float)tim->getMilliseconds() / 1000.0f);		// nach 7 sek abbrechen
		
		WindowEventUtilities::messagePump();		// um auch noch Infos von zu erhalten (sonst SANDUHR) ...

		if( cntr > 15.0f )				// Schleife nach 15 Sek verlassen
			keepRunning = false;
	}

	delete root;
	return 0;
}