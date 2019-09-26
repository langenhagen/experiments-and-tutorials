/*
 * author Barn
 * version 20121004
 */

#include "TutorialApplication.h"

class CompositorListener1 : public Ogre::CompositorInstance::Listener
{
private: // vars

	float _number;
	bool _changed;

public: // constructor

	CompositorListener1() : _number(75.0f), _changed(false)
	{}



public: // methods
	
	void notifyMaterialSetup( Ogre::uint32 passId, Ogre::MaterialPtr &mat)
	{
		mat->getBestTechnique()->getPass( passId)->
			getFragmentProgramParameters()->setNamedConstant("numpixels", _number);
	}

	void notifyMaterialRender(Ogre::uint32 passId, Ogre::MaterialPtr &mat)
	{
		if(_changed)
		{
			mat->getBestTechnique()->getPass( passId)->
			getFragmentProgramParameters()->setNamedConstant("numpixels", _number);
			_changed = false;
		}
		
	}

public: // getters & setters

	void setNumber(float num)
	{
		_number = num;
		_changed = true;
	} 

	float getNumber()
	{
		return _number;
	}

};