/*
 * author Barn
 * 20121005
 */

#include "TutorialApplication.h"

class CompositorListener2 : public Ogre::CompositorInstance::Listener
{
private: // vars

	Ogre::Vector3 _vec;

public: // methods

	void setup( Ogre::Vector3 vec)
	{
		_vec = vec;
	}

	void notifyMaterialSetup( Ogre::uint32 passId, Ogre::MaterialPtr& mat)
	{
		mat->getBestTechnique()->getPass( passId)->getFragmentProgramParameters()->
			setNamedConstant("factors", _vec);
	}
};