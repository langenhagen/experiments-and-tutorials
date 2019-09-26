/////////////////////////////////////////////////////////////////////////////////////////
// OgreApp.h enthält die Klasse OgreApp zur Darstellung einer OgreApplication
/////////////////////////////////////////////////////////////////////////////////////////
#ifndef _OgreApp_
#define _OgreApp_

#include "Ogre.h"
#include "AppFrameListener.h"

#if defined(WIN32)
#	include <windows.h>
#endif

using namespace Ogre;

//############################################################################## class OgreApp
class OgreApp
{
private: // Private Variablen .......................................
	Root									*oRoot;
	ResourceGroupManager	*oRgm;
	RenderSystem					*oRenderSys;
	RenderWindow					*oWindow;
	SceneManager					*oSceneMgr;
	AppFrameListener			*oFrmListener;

	
	Camera								*oCam;
	SceneNode							*oCamNode;
	Viewport							*oVp0;

public: // Public Methoden ..........................................
	// ctor/dtor	
	OgreApp() { oRoot = 0; }			// Default-Konstruktor von OgreApp
	~OgreApp() { delete oRoot; }	// Destruktor

	// Setup für OgreApp _______________________________
	bool setup(	const String &rsName,													
						unsigned int wWidth, unsigned int wHeight,
						bool fullScreen, const String &FSAA);
	
	// startet die App
	void run();																								

	// get-Methoden _____________________________________________
	//Root&									getRoot()				{ return *oRoot; }
	//RenderSystem&					getRenderSys()	{ return *oRenderSys; }
	//ResourceGroupManager&	getRGM()				{ return *oRgm; }
	//RenderWindow&					getWindow()			{ return *oWindow; }
	//SceneManager&					getSceneMgr()		{ return *oSceneMgr; }

private: // Private Hilfsmethoden ...................................
	
	/* Setup-Funktionen */
	void loadPlugins();
	bool chooseRenderSys( const String& rsName);
	bool loadRes();					// Lädt Ressourcen
	void setupScene();				// Gestaltet die Szene

	/* FrameListener kreieren */
	void createFrameListener();

	/* Szenenfunktionen */
	void createSky();
	void createBox();
	void createLight();
	void createPlane1();
	void createPlane2();

	/* Run-Funktionen */
	void FirstTurnung(int &counter, unsigned long lastMS);
	void SecondTest(int &counter);
};

#endif