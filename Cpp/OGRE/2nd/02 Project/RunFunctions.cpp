/////////////////////////////////////////////////////////////////////////////////////////
// RunFunctions.cpp enthält Funktionen die während der RenderLoop ausgeführt werden
/////////////////////////////////////////////////////////////////////////////////////////
#include "OgreApp.h"

/////////////////////////////////////////////////////////////////////
// Die Funktion die Für die Rotation der Box und der 
// späteren Materialgebung verantwortlich ist
void OgreApp::FirstTurnung(int &counter, unsigned long lastMS)
{
	Entity *ent = oSceneMgr->getEntity( "Box");
	Light *lgt = oSceneMgr->getLight("lgt1");
	SceneNode *node = oSceneMgr->getSceneNode("node1");

	if( counter < 400)
	{
		node->pitch( Radian( .01) / lastMS );
		node->yaw( Radian( .02) / lastMS );
		counter++;
	}
	else if( counter++ == 400)
	{
		node->resetOrientation();
		node->yaw( Degree(20) );
		node->setScale( Vector3( 4,4,4));
		node->setPosition( -200,-50,-1200);
		ent->setMaterialName("Examples/OffsetMapping/Specular");
		lgt->setPosition( -100,100,0);
		lgt->setDirection( Vector3( -300, 250, -450));
		lgt->setAttenuation(10000,1,1,1);
		lgt->setPosition(oCam->getPosition());
	}
	else
		node->yaw( Radian (-0.007) / lastMS  );
}

/////////////////////////////////////////////////////////////////////
// Dis is die 2te Bewegungsfunktion in der Run...
void OgreApp::SecondTest(int &counter)
{
	SceneNode *node = oSceneMgr->getSceneNode("node1");

	if( counter++ < 800)
	{ node->translate( Vector3(0, -1,0), Node::TransformSpace::TS_WORLD); }
}