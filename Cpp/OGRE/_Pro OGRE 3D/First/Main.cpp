#include "Ogre.h"
#include "FrameListener.h"

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
	// Hello World heut mal in OGRE... have phun!
	// dies ist der Main-Block
	
	Root *root = new Root;								// Root dynamisch erstellen
	MyFrameListener listener;							// FrameListener erstellen

	if (!root->restoreConfig())						// ggf Config-Dialog aufrufen
	{
		if (!root->showConfigDialog())			// schließen falls auf Cancel gedrückt wurde
		{
			delete root;
			return 0;
		}
	}

	// root initialisieren, per *window auf RenderWindow zeigen(Buch S.56)
	root->initialise(true, "Barns Hello World @ OGRE");
	RenderWindow *window = root->getAutoCreatedWindow();

	// Pointer auf nen neuen SceneManager (SceneType: Generic) setzen
	SceneManager *sceneMgr = root->createSceneManager(ST_GENERIC);

	// Neue Cam störzeln + Viewport ( =eigentliche Leinwand ) erschaffen
	Camera *cam = sceneMgr->createCamera("1st Cam");
	Viewport *vp = window->addViewport( cam);

	vp->setDimensions(.0,.0,1,1);
	vp->setBackgroundColour( ColourValue(.5,.5,.5));
	cam->setNearClipDistance(5);
	cam->setFarClipDistance(1000);
	cam->setAspectRatio( (float)vp->getActualWidth() / (float)vp->getActualHeight() );

		ResourceGroupManager *oRgm = ResourceGroupManager::getSingletonPtr();
	oRgm->addResourceLocation
		("D:/Andreas_jr/Andi/Programmieren/Eigenes/C++/OGRE/_res/skybox.zip", "Zip", "skyRes");
	oRgm->initialiseAllResourceGroups();
	oRgm->loadResourceGroup("skyRes");

	sceneMgr->setAmbientLight( ColourValue( .4,.4,.4));

	sceneMgr->setSkyBox(true, "SkyBox1", 5000, false, Quaternion::IDENTITY, "skyRes");
	
	// FrameListener beim Root anmelden und Szene rendern
	root->addFrameListener( &listener);
	root->startRendering();

	// Root beenden und wech!
	delete root;
	return 0;
}