/////////////////////////////////////////////////////////////////////////////////////////
// AppFrameListener.cpp enthält die Methoden der 
// Klasse AppFrameListener zur Überwachung und Bearbeitung des Inputs
/////////////////////////////////////////////////////////////////////////////////////////
#include "AppFrameListener.h"

/////////////////////////////////////////////////////////////////////
// Konstruktor von AppFrameListener
AppFrameListener::AppFrameListener(SceneManager *sceneMgr, Camera *Eye)
{
	oContinue = true;

	// Objects initialisieren, sind ja bloß Pointer^^
	oSceneMgr = sceneMgr;
	oEyeNode = Eye->getParentSceneNode();

	// Und Rot und Movespeed festlegen, Richtung in V3 speichern
	EyeDir = Vector3::ZERO;
	EyeRot = 0.15;
	EyeMove = 300;

	// Den Listener für Maus und Keyboard proklamieren

	oMouse->setEventCallback(this);
	oKeyboard->setEventCallback(this);
}

/////////////////////////////////////////////////////////////////////
// FrameStartedMethod
bool AppFrameListener::frameStarted( const FrameEvent &evt)
{
	// wird jedes Frame ausgeführt....

	if(oMouse)
		oMouse->capture();
	if(oKeyboard)
	{
		oKeyboard->capture();
		oEyeNode->translate( EyeDir * evt.timeSinceLastFrame, Node::TS_LOCAL);
	}

	return oContinue;
}

/////////////////////////////////////////////////////////////////////
// mouseMoved Methode
bool AppFrameListener::mouseMoved(const OIS::MouseEvent &e)
{
	// über das MouseEvent die Rotation der Cam steuern...
	oEyeNode->yaw( Degree(-EyeRot * e.state.X.rel), Node::TS_WORLD);
	oEyeNode->pitch( Degree(-EyeRot * e.state.Y.rel), Node::TS_LOCAL);

	return true;
}
/////////////////////////////////////////////////////////////////////
// mousePressed Methode
bool AppFrameListener::mousePressed(const OIS::MouseEvent &e, OIS::MouseButtonID id)
{
	return true;
}

/////////////////////////////////////////////////////////////////////
// mouseReleased Methode
bool AppFrameListener::mouseReleased(const OIS::MouseEvent &e, OIS::MouseButtonID id)
{
	return true;
}

/////////////////////////////////////////////////////////////////////
// keyPressed Methode
bool AppFrameListener::keyPressed(const OIS::KeyEvent &e)
{
	switch( e.key)
	{
	case KC_ESCAPE:
		oContinue = false;
		break;
	case OIS::KC_UP:
	case OIS::KC_W:
		EyeDir.z -= EyeMove;
		break;
	case OIS::KC_DOWN:
	case OIS::KC_S:
		EyeDir.z += EyeMove;
		break;
	case OIS::KC_LEFT:
	case OIS::KC_A:
		EyeDir.x -= EyeMove;
		break;
	case OIS::KC_RIGHT:
	case OIS::KC_D:
		EyeDir.x += EyeMove;
		break;
	}
	return true;
}

/////////////////////////////////////////////////////////////////////
// keyReleased Methode
bool AppFrameListener::keyReleased(const OIS::KeyEvent &e)
{
	// eigentlich wie keyPressed(), nur wird hier der Wert
	// wieder aus dem Bewegungsvektor genommen
	switch( e.key)
	{
	case OIS::KC_UP:
	case OIS::KC_W:
		EyeDir.z += EyeMove;
		break;
	case OIS::KC_DOWN:
	case OIS::KC_S:
		EyeDir.z -= EyeMove;
		break;
	case OIS::KC_LEFT:
	case OIS::KC_A:
		EyeDir.x += EyeMove;
		break;
	case OIS::KC_RIGHT:
	case OIS::KC_D:
		EyeDir.x -= EyeMove;
		break;
	}
	return true;
}