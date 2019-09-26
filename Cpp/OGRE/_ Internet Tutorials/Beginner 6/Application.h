/////////////////////////////////////////////////////////////////////////////////////////
// Application.h enthält die Klassen Application, welche eine OgreApp darstellt,
// und ExitListener, welche Programme von Application-Elementen beenden kann
/////////////////////////////////////////////////////////////////////////////////////////
#ifndef _mOgreApplication_
#define _mOgreApplication_

#include <Ogre.h>
#include <OIS/OIS.h>
#include <CEGUI/CEGUI.h>
#include <OgreCEGUIRenderer.h>

using namespace Ogre;

//####################################################################################### class ExitListener

class ExitListener : public FrameListener
{
private:
	OIS::Keyboard *mKeyboard;
public:
	// ctor
	ExitListener(OIS::Keyboard *keyboard)
	: mKeyboard(keyboard) {}

	bool frameStarted(const FrameEvent& evt);
};

//####################################################################################### class Application

class Application
{
private:
	Root *mRoot;
	RenderWindow *mWin;
	
	OIS::Keyboard *mKeyboard;
	OIS::InputManager *mInputManager;
	
	CEGUI::OgreCEGUIRenderer *mRenderer;
	CEGUI::System *mSystem;

	ExitListener *mListener;
public:
	// dtor
	~Application();

	void go();

private:
	void createRoot();
	void defineResources();
	void setupRenderSystem(const String& rsName);
	void createRenderWindow(unsigned int wWidth, unsigned intHeight, bool fullScreen, const String& FSAA);
	void initializeResourceGroups();
	void setupScene();
	void setupInputSystem();
	void setupCEGUI();
	void createFrameListener();
	void startRenderLoop();
};

#endif