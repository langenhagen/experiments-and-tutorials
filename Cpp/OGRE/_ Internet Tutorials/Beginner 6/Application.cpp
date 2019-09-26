/////////////////////////////////////////////////////////////////////////////////////////
// Application.cpp
// enthält die Methoden der Klassen Application und der ExitListener
/////////////////////////////////////////////////////////////////////////////////////////
#include "Application.h"

//####################################################################################### class Application

/////////////////////////////////////////////////////////////////////
// Führt die Application aus
void Application::go()
{
	createRoot();
	defineResources();
	setupRenderSystem( "OpenGL Rendering Subsystem");
	createRenderWindow( 800,600, false, "2");
	initializeResourceGroups();
	setupScene();
	setupInputSystem();
	setupCEGUI();
	createFrameListener();
	startRenderLoop();
}

/////////////////////////////////////////////////////////////////////
// Destruktor von Application
Application::~Application()
{
	// clean up CEGUI
	//delete mRenderer;
	//delete mSystem;

	// clean up OIS
	mInputManager->destroyInputObject(mKeyboard);
	OIS::InputManager::destroyInputSystem(mInputManager);

	// und OGRE itself
	delete mListener;
	delete mRoot;
}

/////////////////////////////////////////////////////////////////////
// Erstellt ein Ogre-Rootelement
void Application::createRoot()
{
	mRoot = new Root();
}

/////////////////////////////////////////////////////////////////////
// Definiert die Ressourcen
void Application::defineResources()
{
	ResourceGroupManager *rgm = ResourceGroupManager::getSingletonPtr();
	rgm->addResourceLocation
		("D:/Andreas_jr/Andi/Programmieren/Eigenes/C++/OGRE/2nd/02 Project/media", "FileSystem", "resOne");
	rgm->addResourceLocation
		("D:/Andreas_jr/Andi/Programmieren/Eigenes/C++/OGRE/2nd/02 Project/media/cubemap.zip", "Zip", "skyRes");
	rgm->addResourceLocation
		("D:/Andreas_jr/Andi/Programmieren/Eigenes/C++/OGRE/2nd/02 Project/media/Offset Mapping", "FileSystem", "PRLXMap");
}

/////////////////////////////////////////////////////////////////////
// Richtet das RenderSystem ein      !!!
void Application::setupRenderSystem(const String& rsName)
{
	RenderSystemList *rsList = mRoot->getAvailableRenderers();
	RenderSystemList::iterator it = rsList->begin();
	RenderSystem *rs;

	while( it != rsList->end() )			//Schleife durchläuft die Liste der Rendersysteme,
	{																	// sucht nach rsName
		rs = *(it++);
		if( rs->getName() == rsName )
			mRoot->setRenderSystem( rs);
	}
}

/////////////////////////////////////////////////////////////////////
// Erstellt ein RenderWindow
void Application::createRenderWindow(unsigned int wWidth, unsigned int wHeight,
																		 bool fullScreen, const String& FSAA)
{
	mRoot->initialise(false);					// root ohne Fenster zu Fenster zu kreieren initialisieren

	// RenderWindow Eigenschafen --> look@ OgreRenderSystem.h 4 further info
	NameValuePairList wndwArgs;
	wndwArgs["title"] = "Barns Beginner 6 /::\\ Stight";
	wndwArgs["left"] = "100";				wndwArgs["top"] = "100";
	wndwArgs["border"] = "fixed";		wndwArgs["FSAA"] = FSAA;

	// RenderWindow manuell übers RenderSystem initialisieren
	mWin = mRoot->createRenderWindow
		("Barns Beginner 6 /::\\ Stight", wWidth, wHeight, fullScreen, &wndwArgs);
}

/////////////////////////////////////////////////////////////////////
// Initialisiert alle Ressourcen
void Application::initializeResourceGroups()
{
	// Legt die Anzahl der MipMaps für jede kommende Textur fest
	TextureManager::getSingletonPtr()->setDefaultNumMipmaps(5);

	ResourceGroupManager::getSingletonPtr()->initialiseAllResourceGroups();
}

/////////////////////////////////////////////////////////////////////
// Setup der Szene
void Application::setupScene()
{
	SceneManager *mgr = mRoot->createSceneManager( ST_GENERIC, "Default SceneMgr");
	Camera *cam = mgr->createCamera( "MainCam");
	Viewport *vp = mWin->addViewport( cam, 0);

	mgr->setSkyBox(true, "SkyBox", 5000, false, Quaternion::IDENTITY, "skyRes");
}

/////////////////////////////////////////////////////////////////////
// Stellt das InputSystem ein
void Application::setupInputSystem()
{

	// Setzt das OIS auf
	size_t windowHnd = 0;
	std::ostringstream windowHndStr;
	OIS::ParamList pl;
	mWin->getCustomAttribute( "WINDOW", &windowHnd);
	windowHndStr << windowHnd;

	pl.insert( std::make_pair(std::string("WINDOW"), windowHndStr.str()) );
	mInputManager = OIS::InputManager::createInputSystem(pl);

	try
	{
		mKeyboard = static_cast<OIS::Keyboard*>(mInputManager->createInputObject(OIS::OISKeyboard, false));
		//mMouse = static_cast<OIS::Mouse*>(mInputManager->createInputObject(OIS::OISMouse, false));
		//mJoy = static_cast<OIS::JoyStick*>(mInputManager->createInputObject(OIS::OISJoyStick, false));
	}
	catch (const OIS::Exception &e)
	{ throw new Exception(42, e.eText, "Application::setupInputSystem"); }
}

/////////////////////////////////////////////////////////////////////
// Richtet die GUI (CEGUI) ein
void Application::setupCEGUI()
{
	SceneManager *mgr = mRoot->getSceneManager("Default SceneMgr");
}

/////////////////////////////////////////////////////////////////////
// Erstellt einen FrameListener
void Application::createFrameListener()
{
	mListener = new ExitListener( mKeyboard);
	mRoot->addFrameListener( mListener);
}

/////////////////////////////////////////////////////////////////////
// Startet die Render Loop
void Application::startRenderLoop()
{
	mRoot->startRendering(); // is softer since FrameListeners
}

//####################################################################################### class Application

/////////////////////////////////////////////////////////////////////
// gibt FALSE bei Drücken der Escape-Taste zurück
bool ExitListener::frameStarted(const FrameEvent& evt)
{
	mKeyboard->capture();
	return !mKeyboard->isKeyDown(OIS::KC_ESCAPE);
}