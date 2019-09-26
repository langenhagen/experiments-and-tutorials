/////////////////////////////////////////////////////////////////////////////////////////
// AppFrameListener.h enthält die
// Klasse AppFrameListener zur Überwachung und Bearbeitung des Inputs
/////////////////////////////////////////////////////////////////////////////////////////
#ifndef _AppFrameListener_
#define _AppFrameListener_

#include "Ogre.h"

//Use this define to signify OIS will be used as a DLL
//(so that dll import/export macros are in effect)
// STRRRRRRPA ?
#define OIS_DYNAMIC_LIB
#include <OIS/OIS.h>

using namespace Ogre;
using namespace OIS;


class AppFrameListener :	public FrameListener,
													public OIS::MouseListener , public OIS::KeyListener
{
private:
	// Und Rotations- und Movespeed, Richtung des Auges wird in V3 gespeichert
	Real EyeRot;		// beie Veriablen legen die Rotations, bzw Movementgeschwindigkeit fest
	Real EyeMove;
	Vector3 EyeDir;

	// OIS Input Devices
	InputManager *oInputMgr;
	OIS::Mouse			*oMouse;
	OIS::Keyboard	*oKeyboard;

	// Damit Verbindung zur OgreApp existiert...
	SceneManager	*oSceneMgr;
	SceneNode			*oEyeNode;

	// Legt den Shutdown fest...
	bool oContinue;

public:
	// ctor / dtor
	AppFrameListener(SceneManager *sceneMgr, Camera *Eye);

	// frameStarted & frameEnded
	bool frameStarted( const FrameEvent &evt);
	// bool frameEnded( const FrameEvent &evt); // wird heut nich gebraucht....

	// MouseListener
	bool mouseMoved( const OIS::MouseEvent &e);
	bool mousePressed(const OIS::MouseEvent &e, OIS::MouseButtonID id);
	bool mouseReleased(const OIS::MouseEvent &e, OIS::MouseButtonID id);

	// KeyboardListener
	bool keyPressed(const OIS::KeyEvent &e);
	bool keyReleased(const OIS::KeyEvent &e);
};

#endif