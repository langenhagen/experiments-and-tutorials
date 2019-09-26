/////////////////////////////////////////////////////////////////////////////////////////
// SetupFunctions.cpp enthält Funktionen die während des SetUps ausgeführt werden
/////////////////////////////////////////////////////////////////////////////////////////
#include "OgreApp.h"

/////////////////////////////////////////////////////////////////////
// Setzt die Skybox
void OgreApp::createSky()
{
	oSceneMgr->setSkyBox(true, "SkyBox", 5000, false, Quaternion::IDENTITY, "skyRes");
}

/////////////////////////////////////////////////////////////////////
// Setzt die Box in die Szene
void OgreApp::createBox()
{
	Entity *ent = oSceneMgr->createEntity( "Box", "Box.mesh");
	SceneNode *node= oSceneMgr->getRootSceneNode()->createChildSceneNode("node1");
	node->attachObject( ent);
	node->setPosition( 200, 300, -600);
	node->scale( Vector3( .3,.3,.3));
}

/////////////////////////////////////////////////////////////////////
// Setzt ein Licht in die Szene
void OgreApp::createLight()
{
	Light *lgt = oSceneMgr->createLight("lgt1");
	lgt->setDiffuseColour( .8, .6, .9); 
	lgt->setSpecularColour( .3, .4, .3);
	lgt->setType( Light::LightTypes::LT_POINT);
	lgt->setDirection( 0, -100, -100);
	lgt->setPosition( 400,400, 100);
}
/////////////////////////////////////////////////////////////////////
// Kreiert die Plane
void OgreApp::createPlane1()
{   
	MovablePlane *plane = new MovablePlane("ReflectPlane");
	Entity *entPlane;
	plane->d = 0;
	plane->normal = Vector3::UNIT_Y;

	MeshManager::getSingleton().createPlane
		("ReflectionPlane", "resOne", *plane,
		2000, 2000, 1, 1, true, 1, 1, 1, Vector3::UNIT_Z);

  entPlane = oSceneMgr->createEntity( "Plane", "ReflectionPlane" );
	entPlane->setMaterialName("matPlane");

	oSceneMgr->getRootSceneNode()->createChildSceneNode()->attachObject( entPlane);
}

/////////////////////////////////////////////////////////////////////
// Kreiert auch die Plane
void OgreApp::createPlane2()
{
	MovablePlane *plane = new MovablePlane("ReflectPlane");
	Entity *entPlane;
	plane->d = 0;
	plane->normal = Vector3::UNIT_Y;

	 MeshManager::getSingleton().createPlane
		 ("ReflectionPlane", ResourceGroupManager::DEFAULT_RESOURCE_GROUP_NAME, *plane,
		 2000, 2000, 1, 1, true, 1, 1, 1, Vector3::UNIT_Z);

   entPlane = oSceneMgr->createEntity( "Plane", "ReflectionPlane" );
	 entPlane->setMaterialName("matPlane");
}