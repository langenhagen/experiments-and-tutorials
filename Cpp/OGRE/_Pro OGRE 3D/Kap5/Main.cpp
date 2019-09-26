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
	root->initialise(true, "Kap5");
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

	// Szene störzeln............................................
	sceneMgr->setAmbientLight( ColourValue( .3,.6,.6));

	//Licht
	Light *light1 = sceneMgr->createLight( "Light1");
	light1->setPosition( 20, 80, 50);
	light1->setDiffuseColour( 1.0,0.0,0.0);
	light1->setSpecularColour(1.0,0.8,1.0);

	// Entity          !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	Entity *ent = sceneMgr->createEntity( "OgreHead", "ogrehead.mesh");







	// FrameListener beim Root anmelden und Szene rendern
	root->addFrameListener( &listener);
	root->startRendering();

	// Root beenden und wech!
	delete root;
	return 0;
}