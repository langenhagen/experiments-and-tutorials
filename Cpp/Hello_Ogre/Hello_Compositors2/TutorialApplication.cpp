/*
-----------------------------------------------------------------------------
Filename:    TutorialApplication.cpp
-----------------------------------------------------------------------------

This source file is part of the
   ___                 __    __ _ _    _ 
  /___\__ _ _ __ ___  / / /\ \ (_) | _(_)
 //  // _` | '__/ _ \ \ \/  \/ / | |/ / |
/ \_// (_| | | |  __/  \  /\  /| |   <| |
\___/ \__, |_|  \___|   \/  \/ |_|_|\_\_|
      |___/                              
      Tutorial Framework
      http://www.ogre3d.org/tikiwiki/
-----------------------------------------------------------------------------
*/
#include "TutorialApplication.h"

//-------------------------------------------------------------------------------------
TutorialApplication::TutorialApplication(void) 
	: //mCamera2(0),
	//mCamera3(0),
	//mCamera4(0)
	_vp(0),
	_vp2(0),
	_vp3(0),
	_vp4(0),
	_compListener(0),
	_compListener2(0),
	_compListener3(0),
	_compListener4(0)
{
}
//-------------------------------------------------------------------------------------
TutorialApplication::~TutorialApplication(void)
{
	/*
	if(mCamera2) delete mCamera2;
	if(mCamera3) delete mCamera3;
	if(mCamera4) delete mCamera4;
	*/

	if(_compListener) delete _compListener;
	if(_compListener2) delete _compListener2;
	if(_compListener3) delete _compListener3;
	if(_compListener4) delete _compListener4;
}

//-------------------------------------------------------------------------------------
void TutorialApplication::createScene(void)
{
    // create your scene here :)

	Ogre::SceneNode* node = mSceneMgr->getRootSceneNode()->createChildSceneNode( "Node1");
	Ogre::Entity* ent = mSceneMgr->createEntity( "Entity1", "Sinbad.mesh");
	node->attachObject( ent);
	

	Ogre::CompositorManager::getSingleton().addCompositor( _vp, "Compositor9");
	Ogre::CompositorManager::getSingleton().setCompositorEnabled( _vp, "Compositor9", true);

	

	Ogre::CompositorInstance* comp = Ogre::CompositorManager::getSingleton().
		getCompositorChain(_vp)->getCompositor("Compositor9");
	_compListener = new CompositorListener2();
	comp->addListener( _compListener);
	
	
	Ogre::CompositorManager::getSingleton().addCompositor( _vp2, "Compositor9");
	Ogre::CompositorManager::getSingleton().setCompositorEnabled( _vp2, "Compositor9", true);

	comp = Ogre::CompositorManager::getSingleton().
		getCompositorChain(_vp2)->getCompositor("Compositor9");
	_compListener2 = new CompositorListener2();
	comp->addListener( _compListener2);


	Ogre::CompositorManager::getSingleton().addCompositor( _vp3, "Compositor9");
	Ogre::CompositorManager::getSingleton().setCompositorEnabled( _vp3, "Compositor9", true);

	comp = Ogre::CompositorManager::getSingleton().
		getCompositorChain(_vp3)->getCompositor("Compositor9");
	_compListener3 = new CompositorListener2();
	comp->addListener( _compListener3);


	_compListener->setup( Ogre::Vector3(1,1,0));
	_compListener2->setup( Ogre::Vector3(0,1,1));
	_compListener3->setup( Ogre::Vector3(1,0,1));
}


void TutorialApplication::createCamera(void)
{
	mCamera = mSceneMgr->createCamera("PlayerCam");
	mCamera->setPosition( 0, 10, 20);
	mCamera->lookAt(0,0,0);
	mCamera->setNearClipDistance(5);

	/*	
	mCamera2 = mSceneMgr->createCamera("MyCamera2");
	mCamera2->setPosition( 20, 10, 0);
	mCamera2->lookAt(0,0,0);
	mCamera2->setNearClipDistance(5);

	mCamera3 = mSceneMgr->createCamera("MyCamera3");
	mCamera3->setPosition( 0, 10, -20);
	mCamera3->lookAt(0,0,0);
	mCamera3->setNearClipDistance(5);

	mCamera4 = mSceneMgr->createCamera("MyCamera4");
	mCamera4->setPosition( -20, 10, 0);
	mCamera4->lookAt(0,0,0);
	mCamera4->setNearClipDistance(5);
	*/	

}


void TutorialApplication::createViewports(void)
{
	// add viewports
	_vp = mWindow->addViewport( mCamera, 0, 0, 0, 0.5f, 0.5f);
	_vp->setBackgroundColour( Ogre::ColourValue( 0.2f, 0, 0, 1));

	_vp2 = mWindow->addViewport( mCamera, 1, 0.5f, 0, 0.5f, 0.5f);
	_vp2->setBackgroundColour( Ogre::ColourValue(0, 0, 0.2f, 1));
	
	_vp3 = mWindow->addViewport( mCamera, 2, 0 , 0.5f, 0.5f, 0.5f);
	_vp3->setBackgroundColour( Ogre::ColourValue(0, 0.2f, 0, 1));

	_vp4 = mWindow->addViewport( mCamera, 3, 0.5f, 0.5f, 0.5f, 0.5f);
	_vp4->setBackgroundColour( Ogre::ColourValue(0.2f, 0, 0.2f, 1));

	mCamera->setAspectRatio( Ogre::Real(_vp->getActualWidth()) / Ogre::Real(_vp->getActualHeight()));

	// necessary in order not to crash at startup O,ô
	mCameraMan = new OgreBites::SdkCameraMan(mCamera);   // create a default camera controller
	//new OgreBites::SdkCameraMan(mCamera2);
}



#if OGRE_PLATFORM == OGRE_PLATFORM_WIN32
#define WIN32_LEAN_AND_MEAN
#include "windows.h"
#endif

#ifdef __cplusplus
extern "C" {
#endif

#if OGRE_PLATFORM == OGRE_PLATFORM_WIN32
    INT WINAPI WinMain( HINSTANCE hInst, HINSTANCE, LPSTR strCmdLine, INT )
#else
    int main(int argc, char *argv[])
#endif
    {
        // Create application object
        TutorialApplication app;

        try {
            app.go();
        } catch( Ogre::Exception& e ) {
#if OGRE_PLATFORM == OGRE_PLATFORM_WIN32
            MessageBox( NULL, e.getFullDescription().c_str(), "An exception has occured!", MB_OK | MB_ICONERROR | MB_TASKMODAL);
#else
            std::cerr << "An exception has occured: " <<
                e.getFullDescription().c_str() << std::endl;
#endif
        }

        return 0;
    }

#ifdef __cplusplus
}
#endif
