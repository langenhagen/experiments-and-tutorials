//http://www.ogre3d.org/wiki/index.php/Basic_Tutorial_3

#include "ExampleApplication.h"

class TutorialApplication : public ExampleApplication
{
protected:
public:
    TutorialApplication()
    {
    }

    ~TutorialApplication() 
    {
    }
protected:
    void chooseSceneManager(void)
    {
			mSceneMgr = mRoot->createSceneManager(ST_EXTERIOR_CLOSE);
    }

    void createScene(void)
    {
			ColourValue fadeColour(0.1, 0.1, 0.1);
			mSceneMgr->setFog(FOG_LINEAR, fadeColour, 0.0, 10, 150);
			mWindow->getViewport(0)->setBackgroundColour(fadeColour);

			Plane plane;
			plane.d = 10;
			plane.normal = Vector3::NEGATIVE_UNIT_Y;

			mSceneMgr->setWorldGeometry("terrain.cfg");

			// mSceneMgr->setSkyPlane(true, plane, "Examples/SpaceSkyPlane", 100, 45, true, 0.5, 150, 150);

			
			 mSceneMgr->setSkyBox(true, "Examples/SpaceSkyBox", 100, false);
			// mSceneMgr->setSkyDome(true, "Examples/CloudySky", 1, 8, 6000,false);
			
			// Plane plane;
			// plane.d = 1000;
			// plane.normal = Vector3::NEGATIVE_UNIT_Y;

			// mSceneMgr->setSkyPlane(true, plane, "Examples/CloudySky", 1500, 50, true, 1.0f, 150, 150);


			
    }
};

#if OGRE_PLATFORM == PLATFORM_WIN32 || OGRE_PLATFORM == OGRE_PLATFORM_WIN32
#define WIN32_LEAN_AND_MEAN
#include "windows.h"

INT WINAPI WinMain(HINSTANCE hInst, HINSTANCE, LPSTR strCmdLine, INT)
#else
int main(int argc, char **argv)
#endif
{
    // Create application object
    TutorialApplication app;

    try {
        app.go();
    } catch(Exception& e) {
#if OGRE_PLATFORM == PLATFORM_WIN32 || OGRE_PLATFORM == OGRE_PLATFORM_WIN32
        //MessageBox(NULL, e.getFullDescription().c_str(), "An exception has occurred!", MB_OK | MB_ICONERROR | MB_TASKMODAL);
#else
        //fprintf(stderr, "An exception has occurred: %s\n",
        //        e.getFullDescription().c_str());
#endif
    }

    return 0;
}