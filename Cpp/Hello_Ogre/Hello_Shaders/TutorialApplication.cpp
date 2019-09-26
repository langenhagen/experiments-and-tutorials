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
{
}
//-------------------------------------------------------------------------------------
TutorialApplication::~TutorialApplication(void)
{
}

//-------------------------------------------------------------------------------------
void TutorialApplication::createScene(void)
{
    // create your scene here :)

	// create quad
	Ogre::ManualObject* quad = mSceneMgr->createManualObject( "Quad");
	quad->begin( "MyMaterial15"); //*

	// create four points of the quad
	quad->position( 5, 0, 0); // lower right
	//quad->textureCoord( 0, 1.2);
	quad->colour( 1, 0, 0);
	quad->position( -5, 10, 0); // upper left
	//quad->textureCoord( 1.4, 0);
	quad->colour( 0, 1, 0);
	quad->position( -5, 0, 0); // lower left
	//quad->textureCoord( 1.4, 1.2);
	quad->colour( 0, 0, 1);
	quad->position( 5, 10, 0); // upper right
	//quad->textureCoord( 0, 0);
	quad->colour( 1, 1, 0);

	// use indices to describe the quad via triangles (ogl style)
	quad->index(0);
	quad->index(1);
	quad->index(2);

	quad->index(0);
	quad->index(3);
	quad->index(1);

	// finish the manual object and convert it into a mesh
	quad->end();
	quad->convertToMesh( "Quad");

	// first entity
	Ogre::Entity* ent = mSceneMgr->createEntity("Sinbad", "Sinbad.mesh");
	Ogre::SceneNode* node = mSceneMgr->getRootSceneNode()->createChildSceneNode("Node1");
	ent->setMaterialName( "MyMaterial18");
	node->attachObject(ent);
	

	// second entity
	/*
	ent = mSceneMgr->createEntity("Quad");
	ent->setMaterialName( "MyMaterial12");
	node = mSceneMgr->getRootSceneNode()->createChildSceneNode("Node2", Ogre::Vector3( 15,0,0));
	node->attachObject(ent);
	*/

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
