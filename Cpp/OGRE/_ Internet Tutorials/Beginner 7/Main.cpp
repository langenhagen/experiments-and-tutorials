#include "ExampleApplication.h"
#include <CEGUI/CEGUI.h>
#include <OIS/OIS.h>
#include <OgreCEGUIRenderer.h>

// MouseTastenCode Konvertierungsfunktion
CEGUI::MouseButton convertButton(OIS::MouseButtonID buttonID)
{
    switch (buttonID)
    {
    case OIS::MB_Left:
        return CEGUI::LeftButton;
    case OIS::MB_Right:
        return CEGUI::RightButton;
    case OIS::MB_Middle:
        return CEGUI::MiddleButton;
    default:
        return CEGUI::LeftButton;
    }
}

class TutorialListener : public ExampleFrameListener, public OIS::MouseListener, public OIS::KeyListener
{
private:
	bool mContinue;
public:
	TutorialListener(RenderWindow* win, Camera* cam)
			: ExampleFrameListener(win, cam, true, true)
	{
		 mContinue=true;
		 mMouse->setEventCallback(this);
		 mKeyboard->setEventCallback(this);
	} 

	// CEGUIDemoListener
	bool frameStarted(const FrameEvent &evt)
	{
			mKeyboard->capture();
			mMouse->capture();

			return mContinue && !mKeyboard->isKeyDown(OIS::KC_ESCAPE);
	}

	bool quit(const CEGUI::EventArgs &e)
	{
			mContinue = false;
			return true;
	}

	// MouseListener
	bool mouseMoved(const OIS::MouseEvent &arg)
	{
		CEGUI::System::getSingleton().injectMouseMove( arg.state.X.rel, arg.state.Y.rel);
		return true;
	}

	bool mousePressed(const OIS::MouseEvent &arg, OIS::MouseButtonID id)
	{
		CEGUI::System::getSingleton().injectMouseButtonDown( convertButton(id));
		return true;
	}

	bool mouseReleased(const OIS::MouseEvent &arg, OIS::MouseButtonID id)
	{
		CEGUI::System::getSingleton().injectMouseButtonUp(convertButton(id));
		return true;
	}

	// KeyListener
	bool keyPressed(const OIS::KeyEvent &arg)
	{
		// KEyEvents und MouseEvents müssen erst der CEGUI injiziert werden
		CEGUI::System *sys = CEGUI::System::getSingletonPtr();
		sys->injectKeyDown(arg.key);
		sys->injectChar(arg.text);
		return true;
	}

	bool keyReleased(const OIS::KeyEvent &arg)
	{
		CEGUI::System::getSingleton().injectKeyUp(arg.key);
		return true;
	}
};


class CEGUIDemoApplication : public ExampleApplication
{
protected:
	CEGUI::System *mSystem;
	CEGUI::OgreCEGUIRenderer *mRenderer;

public:
	CEGUIDemoApplication()
	: mSystem(0), mRenderer(0)
	{
	}

	~CEGUIDemoApplication() 
	{
	if (mSystem)
		delete mSystem;

	if (mRenderer)
		delete mRenderer;
	}

protected:
	void createScene(void)
	{
		// CEGUI einsatzbereit machen
		mRenderer = new CEGUI::OgreCEGUIRenderer(mWindow, Ogre::RENDER_QUEUE_OVERLAY, false, 3000, mSceneMgr);
		mSystem = new CEGUI::System(mRenderer);

		// Skin auswählen
		CEGUI::SchemeManager::getSingleton().loadScheme( (CEGUI::utf8*)"TaharezLookSkin.scheme");

		mSystem->setDefaultMouseCursor( (CEGUI::utf8*)"TaharezLook", (CEGUI::utf8*)"MouseArrow");
		mSystem->setDefaultFont((CEGUI::utf8*)"BlueHighway-12");
	}

	void createFrameListener(void)
	{
			mFrameListener= new TutorialListener(mWindow, mCamera);
			mFrameListener->showDebugOverlay(true);
			mRoot->addFrameListener(mFrameListener);
	}

};

#if OGRE_PLATFORM == OGRE_PLATFORM_WIN32
#define WIN32_LEAN_AND_MEAN
#include "windows.h"


INT WINAPI WinMain(HINSTANCE hInst, HINSTANCE, LPSTR strCmdLine, INT)
#else
int main(int argc, char **argv)
#endif
{
	// Create application object
	CEGUIDemoApplication app;

	app.go();

	return 0;
}
