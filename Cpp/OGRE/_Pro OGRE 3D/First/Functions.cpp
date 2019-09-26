#include "Ogre.h"
using namespace Ogre;

bool loadRes( ResourceGroupManager* oRgm)
{
	try
	{
		oRgm = ResourceGroupManager::getSingletonPtr();
		oRgm->addResourceLocation
			("D:/Andreas_jr/Andi/Programmieren/Eigenes/C++/OGRE/2nd/02 Project/media", "FileSystem", "resOne");
		oRgm->addResourceLocation
			("D:/Andreas_jr/Andi/Programmieren/Eigenes/C++/OGRE/2nd/02 Project/media/cubemap.zip", "Zip", "skyRes");
		oRgm->addResourceLocation
			("D:/Andreas_jr/Andi/Programmieren/Eigenes/C++/OGRE/2nd/02 Project/media/Offset Mapping", "FileSystem", "PRLXMap");
		oRgm->initialiseAllResourceGroups();
		oRgm->loadResourceGroup("resOne");
		oRgm->loadResourceGroup("skyRes");
		oRgm->loadResourceGroup("PRLXMap");
		return true;
	}
	catch (...) { return false; }
}