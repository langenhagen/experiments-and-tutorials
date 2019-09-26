// author barn
// 20121004

//#ifndef __My_FrameListener1_h_
//#define __My_FrameListener1_h_

#include "BaseApplication.h"


class My_FrameListener1 : public Ogre::FrameListener
{
	
private: // vars
	OIS::InputManager* _man;
	OIS::Keyboard* _key;
	CompositorListener1* _listener;

public: // constructor & destructor

	My_FrameListener1(Ogre::RenderWindow* win, CompositorListener1* listener)
	{
		_listener = listener;

		size_t windowHnd = 0;
		std::stringstream windowHndStr;
	
		win->getCustomAttribute("WINDOW", &windowHnd);
		windowHndStr << windowHnd;
	
		OIS::ParamList pl;
		pl.insert(std::make_pair(std::string("WINDOW"), windowHndStr.str()));
		_man = OIS::InputManager::createInputSystem( pl );
		_key = static_cast<OIS::Keyboard*>(_man->createInputObject( OIS::OISKeyboard, false ));
	}

	~My_FrameListener1()
	{
		_man->destroyInputObject(_key);
		OIS::InputManager::destroyInputSystem(_man);
	}

public: // methods

	virtual bool frameRenderingQueued(const Ogre::FrameEvent &evt)
	{
		_key->capture();

		if(_key->isKeyDown(OIS::KC_Z))
		{
			float num = _listener->getNumber();
			num++;
			_listener->setNumber(num);
		}

		if(_key->isKeyDown(OIS::KC_X))
		{
			float num = _listener->getNumber();
			num--;
			_listener->setNumber(num);
			
		}
		
		int i;

		std::cout << "!" << std::endl;

		return true;

	}


};


//#endif // __MyFrameListener1_h_