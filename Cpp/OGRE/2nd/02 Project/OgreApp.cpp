/////////////////////////////////////////////////////////////////////////////////////////
// OgreApp.cpp enthält Methoden der Klasse OgreApp zur Darstellung einer OgreApplication
/////////////////////////////////////////////////////////////////////////////////////////
#include "OgreApp.h"

//############################################################################## OgreApp Methoden

/////////////////////////////////////////////////////////////////////
// OgreApp Einstellungen treffen
bool OgreApp::setup(	const String &rsName,
											unsigned int wWidth, unsigned int wHeight,
											bool fullScreen, const String &FSAA)
{
	oRoot = new Root("","", "Proj2.log");

	loadPlugins();

	if( !chooseRenderSys( rsName)) return false;

	oRoot->initialise(false);					// root ohne Fenster zu Fenster zu kreieren initialisieren

	// RenderWindow Eigenschafen --> look@ OgreRenderSystem.h 4 further info
	NameValuePairList wndwArgs;
	wndwArgs["title"] = "Barns Hello World #4 (Complex) avec OgreApp";
	wndwArgs["left"] = "100";				wndwArgs["top"] = "100";
	wndwArgs["border"] = "fixed";		wndwArgs["FSAA"] = FSAA;

	// RenderWindow manuell übers RenderSystem initialisieren
	oWindow = oRenderSys->createRenderWindow						
		("Barns Hello World #3", wWidth, wHeight, fullScreen, &wndwArgs);

	// loadRes() muss nach Kreation eines RenderWindows geschehen !!!
	if( !loadRes()) return false;

	// Scene Manager kreieren
	oSceneMgr = oRoot->createSceneManager(ST_GENERIC, "SceneManager1");
	oSceneMgr->setAmbientLight( ColourValue( .2, .2, .2));

	// Cam und ViewPort einstellen
	oCam = oSceneMgr->createCamera("MainCam");
	oVp0 = oWindow->addViewport( oCam, 0);
	
	oVp0->setBackgroundColour( ColourValue( .3, .5, .3));
	oCam->setAspectRatio( (float)oVp0->getActualWidth() / (float)oVp0->getActualHeight() );
	oCam->setNearClipDistance(5);
	oCam->setFarClipDistance(10000);
	oCam->setFOVy( Degree(30.0));
	oCam->setPosition( 0,0, 500);
	oCam->lookAt( 0,0,0);
	oCam->setLodBias(5.0);

	// Und die Cam per Bequemlichkeit an den Node anbinden
	oCamNode = oSceneMgr->getRootSceneNode()->createChildSceneNode("CamNode1");
	oCamNode->attachObject( oCam);

	// FrameListener aktivieren
	createFrameListener();

	setupScene();

	return true;
}

/////////////////////////////////////////////////////////////////////
// Startet die OgreApp
void OgreApp::run()
{
	// Manuelle RenderLoop vorbereiten...
	Timer *tim = new Timer();
	unsigned long rlt;
	int	intCntr1 = 0,
			intCntr2 = 0;

	while( oWindow->isActive())					// und die RenderLoop
	{
		tim->reset();
		oRoot->renderOneFrame();

		rlt = tim->getMilliseconds();

		FirstTurnung( intCntr1, rlt);
		SecondTest( intCntr2);

		WindowEventUtilities::messagePump();		// um auch noch Infos von zu erhalten (sonst SANDUHR) ...
	}
}

/////////////////////////////////////////////////////////////////////
// Plugins für Ogre laden
void OgreApp::loadPlugins()
{
	// Plugins separat laden    (!!! ATTENZIONE: Debug (_d) Modus !!!!)
	oRoot->loadPlugin("Plugin_CgProgramManager_d");
	oRoot->loadPlugin("Plugin_OctreeSceneManager_d");
	oRoot->loadPlugin("RenderSystem_GL_d");
	try																													// Try/Catch-Block weil zB auf Linux kein 
	{ oRoot->loadPlugin("RenderSystem_Direct3D9_d"); }						// Direct3D existiert; Plattformspezifische 
	catch( ...) {}																							// Exception abfangen, hirney!   ...softe
}

/////////////////////////////////////////////////////////////////////
// Wahl des Rendersystems treffen (D3D / OpenGL)
bool OgreApp::chooseRenderSys(const String &rsName)
{
	RenderSystemList *rsList = oRoot->getAvailableRenderers();
	RenderSystemList::iterator it = rsList->begin();

	while( it != rsList->end() )				//Schleife durchläuft die Liste der Rendersysteme,
	{																		// sucht nach rsName
		oRenderSys = *(it++);
		if( oRenderSys->getName() == rsName )
		{
			oRoot->setRenderSystem( oRenderSys);
			return true;
		}
	}

	return false;							// wenn rsName nich gefunden wurde,
														// False zurücksenden
}

/////////////////////////////////////////////////////////////////////
// Ressourcen bereitstellen
bool OgreApp::loadRes()
{
	try
	{
		oRgm = ResourceGroupManager::getSingletonPtr();
		oRgm->addResourceLocation
			("D:/Andreas_jr/Andi/Programmieren/Eigenes/C++/OGRE/2nd/02 Project/media", "FileSystem", "resOne");
		oRgm->addResourceLocation
			("D:/Andreas_jr/Andi/Programmieren/Eigenes/C++/OGRE/2nd/02 Project/media/cubemap.zip", "Zip", "skyRes");
		oRgm->addResourceLocation
			("D:/Andreas_jr/Andi/Programmieren/Eigenes/C++/OGRE/2nd/02 Project/media/Offset Mapping", "FileSystem", "PRLXMap");
		oRgm->initialiseAllResourceGroups();
		oRgm->loadResourceGroup("resOne");
		oRgm->loadResourceGroup("skyRes");
		oRgm->loadResourceGroup("PRLXMap");
		return true;
	}
	catch (...) { return false; }
}

/////////////////////////////////////////////////////////////////////
// Setzt Objekte in die Szene
void OgreApp::setupScene()
{
	createSky();
	createPlane1();

	createBox();
	createLight();
}

/////////////////////////////////////////////////////////////////////
// Krieiert den Frame Listener
void OgreApp::createFrameListener()
{
	oFrmListener = new AppFrameListener( oSceneMgr, oCam);
	oRoot->addFrameListener(oFrmListener);
}