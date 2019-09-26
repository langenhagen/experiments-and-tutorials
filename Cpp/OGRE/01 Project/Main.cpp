#include "Ogre.h"

#if defined(WIN32)
#	include <windows.h>
#endif

using namespace Ogre;

void p1loadPlugins( Root* root);

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
	wndwArgs["left"] = "100";									// look@ OgreRenderSystem.h 4 further info
	wndwArgs["top"] = "100";
	wndwArgs["title"] = "Barns Hello World #3 (Complex) @ Ogre";
	wndwArgs["border"] = "fixed";
	wndwArgs["FSAA"] = "2";

	RenderWindow *window = rSys->createRenderWindow						// RenderWindow manuell übers RenderSystem kreieren
		("Barns Hello World #3", 800, 600, false, &wndwArgs);

	// Resources klarmachen
	ResourceGroupManager *rgm = ResourceGroupManager::getSingletonPtr();
	rgm->addResourceLocation
		("D:/Andreas_jr/Andi/Programmieren/Eigenes/C++/OGRE/01 Project/media", "FileSystem", "resOne");
	rgm->addResourceLocation
		("D:/Andreas_jr/Andi/Programmieren/Eigenes/C++/OGRE/01 Project/media/cubemap.zip", "Zip", "skyRes");
	rgm->initialiseAllResourceGroups();
	rgm->loadResourceGroup("resOne");
	rgm->loadResourceGroup("skyRes");

	// Scene Manager kreieren
	SceneManager *sceneMgr = root->createSceneManager(ST_GENERIC, "SceneManager1");
	sceneMgr->setAmbientLight( ColourValue( .3, .3,.3));

	// Cam und ViewPorts
	Camera *cam = sceneMgr->createCamera("Cam1");
	Viewport *vpMain;

	// ViewPorts einstellen
	vpMain = window->addViewport( cam, 0);
	vpMain->setBackgroundColour( ColourValue( .4, .3, .2));

	// Cam Einstellen
	cam->setAspectRatio( (float)vpMain->getActualWidth() / (float)vpMain->getActualHeight() );
	cam->setNearClipDistance(5);
	cam->setFarClipDistance(10000);
	cam->setFOVy( Degree(30.0));
	cam->setPosition( 0,0, 500);
	cam->lookAt( 0,0,0);
	cam->setLodBias(100.0);

	// Licht klären
	Light *lgt = sceneMgr->createLight("lgt1");
	lgt->setDiffuseColour( .8, .6, .9); 
	lgt->setSpecularColour( .3, .4, .3);
	lgt->setType( Light::LightTypes::LT_POINT);
	lgt->setDirection( 0, -100, -100);
	lgt->setPosition( 400,400, 100);

	//Background klären AAAAHH KLAPPT NICH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	sceneMgr->setSkyBox( true, "01_-_Default");

	while( window->isActive())					// und die RenderLoop
	{
		root->renderOneFrame();

		WindowEventUtilities::messagePump();		// um auch noch Infos von zu erhalten (sonst SANDUHR) ...
	}

	delete root;
	return 0;
}