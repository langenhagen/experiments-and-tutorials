#include "Ogre.h"

#if defined(WIN32)
#	include <windows.h>
#endif

using namespace Ogre;

void p1loadPlugins( Root* root);

// Main-Funktionsart nach Betriebssystem auswählen...
#if defined (WIN32)
INT WINAPI WinMain ( HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow )
#else
int main (int argc, char *argv[])
#endif
{
	Root *root = new Root("", "");								// Root ohne jedes Plugin laden

	p1loadPlugins( root);

	// Auswahl des Rendersystems, als ob der User wat eingegeben hätte...
	String rsName("OpenGL Rendering Subsystem");
	RenderSystemList *rsList = root->getAvailableRenderers();
	RenderSystem *rSys;
	RenderSystemList::iterator it = rsList->begin();

	while( it != rsList->end() )							//Schleife durchläuft die Liste der Rendersysteme,
	{																					// sucht nach rsName
		rSys = *(it++);
		if( rSys->getName() == rsName )
		{
			root->setRenderSystem( rSys);
			break;
		}
	}
	if( root->getRenderSystem() == NULL)			// wenn rsName nich gefunden wurde, App beenden
	{
		delete root;
		return -1;
	}

	// Resources klarmachen
	ResourceGroupManager *rgm = ResourceGroupManager::getSingletonPtr();
	rgm->addResourceLocation
		("D:/Andreas_jr/Andi/Programmieren/Eigenes/C++/OGRE/01 Project/media", "FileSystem", "resOne");
	rgm->addResourceLocation
		("D:/Andreas_jr/Andi/Programmieren/Eigenes/C++/OGRE/01 Project/media/cubemap.zip", "Zip", "skyRes");
	rgm->addResourceLocation
		("D:/Andreas_jr/Andi/Programmieren/Eigenes/C++/OGRE/01 Project/media/Offset Mapping", "FileSystem", "PRLXMap");
	rgm->initialiseAllResourceGroups();
	rgm->loadResourceGroup("resOne");
	rgm->loadResourceGroup("skyRes");
	rgm->loadResourceGroup("PRLXMap");


	root->initialise(false);									// root ohne Fenster zu Fenster zu kreiern initialisieren

	NameValuePairList wndwArgs;								// Spezielle Eigenschaften für das RenderWindow
	wndwArgs["left"] = "100";									// look@ OgreRenderSystem.h 4 further info
	wndwArgs["top"] = "100";
	wndwArgs["title"] = "Barns Hello World #3 (Complex) @ Ogre";
	wndwArgs["border"] = "fixed";
	wndwArgs["FSAA"] = "2";

	RenderWindow *window = rSys->createRenderWindow						// RenderWindow manuell übers RenderSystem kreieren
		("Barns Hello World #3", 800, 600, false, &wndwArgs);


	// Scene Manager kreieren
	SceneManager *sceneMgr = root->createSceneManager(ST_GENERIC, "SceneManager1");
	sceneMgr->setAmbientLight( ColourValue( .3, .3,.3));

	// Cam und ViewPorts
	Camera *cam = sceneMgr->createCamera("Cam1");
	Viewport *vpMain;

	// ViewPorts einstellen
	vpMain = window->addViewport( cam, 0);
	vpMain->setBackgroundColour( ColourValue( .4, .3, .2));

	// Cam Einstellen
	cam->setAspectRatio( (float)vpMain->getActualWidth() / (float)vpMain->getActualHeight() );
	cam->setNearClipDistance(5);
	cam->setFarClipDistance(10000);
	cam->setFOVy( Degree(30.0));
	cam->setPosition( 0,0, 500);
	cam->lookAt( 0,0,0);
	cam->setLodBias(100.0);

	// Background klären AAAAHH KLAPPT NICH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	// sceneMgr->setSkyBox( true, "SkyBox2");

	// Objekt klären
	Entity *ent = sceneMgr->createEntity( "Box", "Box.mesh");
	SceneNode *node = sceneMgr->getRootSceneNode()->createChildSceneNode();
	node->attachObject( ent);
	node->setPosition( 200, -0, -600);
	node->scale( Vector3( .3,.3,.3));

	// Licht klären
	Light *lgt = sceneMgr->createLight("lgt1");
	lgt->setDiffuseColour( .8, .6, .9); 
	lgt->setSpecularColour( .3, .4, .3);
	lgt->setType( Light::LightTypes::LT_POINT);
	lgt->setDirection( 0, -100, -100);
	lgt->setPosition( 400,400, 100);

	// Manuelle RenderLoop vorbereiten...
	Timer *tim = new Timer();
	unsigned long rlt;
	int intCntr = 0;

	while( window->isActive())					// und die RenderLoop
	{
		
		tim->reset();

		root->renderOneFrame();

		rlt = tim->getMilliseconds();

			if( intCntr < 400)
			{
				node->pitch( Radian( .01) / rlt );

				node->yaw( Radian( .02) / rlt );
				intCntr++;
			}
			else if( intCntr++ == 400)
			{
				node->resetOrientation();
				node->setScale( Vector3( 4,4,4));
				node->yaw( Degree(20) );
				node->setPosition( -200,-250,-1200);
				ent->setMaterialName("Examples/OffsetMapping/Specular");
				lgt->setPosition( -100,100,0);
				lgt->setDirection( Vector3( -300, 250, -450));
				lgt->setAttenuation(10000,1,1,1);
				lgt->setPosition(cam->getPosition());
			}
			else
				node->yaw( Radian (-0.007) / rlt  );


		WindowEventUtilities::messagePump();		// um auch noch Infos von zu erhalten (sonst SANDUHR) ...
	}

	delete root;
	return 0;
}