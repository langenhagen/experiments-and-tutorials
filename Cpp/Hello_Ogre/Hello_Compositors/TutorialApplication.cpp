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
#include "CompositorListener1.h"
#include "My_FrameListener1.h"

#define COMPOSITOR

//-------------------------------------------------------------------------------------
TutorialApplication::TutorialApplication(void)
	: _compListener(NULL), FrameListener(NULL)
{
}
//-------------------------------------------------------------------------------------
TutorialApplication::~TutorialApplication(void)
{
	delete _compListener;
	delete FrameListener;
}

//-------------------------------------------------------------------------------------
void TutorialApplication::createScene(void)
{
    // create your scene here :)

	mCamera->getViewport()->setBackgroundColour( Ogre::ColourValue( 0.3, 0, 0, 1));

	Ogre::SceneNode* node = mSceneMgr->getRootSceneNode()->createChildSceneNode( "Node1", Ogre::Vector3(0, 0, 45));
	Ogre::Entity* ent = mSceneMgr->createEntity( "Entity1", "Cube.mesh");
	node->attachObject( ent);
	node->setScale(10,10,10);
	ent->setMaterialName( "Abstract1_1/dodecahedron1_au");

	node = mSceneMgr->getRootSceneNode()->createChildSceneNode( "Node1_1", Ogre::Vector3(100, 0, 45));
	node->attachObject( ent->clone( "Entity1_1"));

	node = mSceneMgr->getRootSceneNode()->createChildSceneNode( "Node2", Ogre::Vector3( 0,0, -80));
	Ogre::Light* light = mSceneMgr->createLight( "Light0");
	light->setType( Ogre::Light::LT_POINT);
	light->setDiffuseColour( 1, 1, 0.4);
	light->setSpecularColour( 1, 0, 1);
	light->setSpecularColour( Ogre::ColourValue::White);
	light->setAttenuation(1200, 1, 1, 1);
	node->attachObject(light);

#ifdef COMPOSITOR

	Ogre::CompositorManager::getSingleton().addCompositor( mCamera->getViewport(), "Compositor8");
	//Ogre::CompositorManager::getSingleton().setCompositorEnabled( mCamera->getViewport(), "Compositor8", true);
	

	Ogre::CompositorManager::getSingleton().addCompositor( mCamera->getViewport(), "barn/simple_texture_compositor");
	Ogre::CompositorManager::getSingleton().setCompositorEnabled( mCamera->getViewport(), "barn/simple_texture_compositor", true);

	

	/* chaining compositors
	Ogre::CompositorManager::getSingleton().addCompositor( mCamera->getViewport(), "Compositor1", 1);
	Ogre::CompositorManager::getSingleton().setCompositorEnabled( mCamera->getViewport(), "Compositor1", true);
	*/

	
	Ogre::CompositorInstance* comp = 
		Ogre::CompositorManager::getSingleton().getCompositorChain(mCamera->getViewport())->
		getCompositor("Compositor8");

	_compListener = new CompositorListener1();
	comp->addListener( _compListener);

#endif

}

void TutorialApplication::createFrameListener(void)
{
	BaseApplication::createFrameListener();

	//Step 4
	FrameListener = new My_FrameListener1(mWindow, _compListener);
	mRoot->addFrameListener( FrameListener);
/**/
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
