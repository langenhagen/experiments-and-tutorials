/*
-----------------------------------------------------------------------------
Filename:    TutorialApplication.h
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
#ifndef __TutorialApplication_h_
#define __TutorialApplication_h_

#include "BaseApplication.h"
#include "CompositorListener2.h"

class TutorialApplication : public BaseApplication
{
private: // vars

//	Ogre::Camera*  mCamera2;
//	Ogre::Camera*  mCamera3;
//	Ogre::Camera*  mCamera4;

	Ogre::Viewport* _vp;
	Ogre::Viewport* _vp2;
	Ogre::Viewport* _vp3;
	Ogre::Viewport* _vp4;

	CompositorListener2* _compListener;
	CompositorListener2* _compListener2;
	CompositorListener2* _compListener3;
	CompositorListener2* _compListener4;

public: // constructor & destructor
    TutorialApplication(void);
    virtual ~TutorialApplication(void);

protected: // methods
    virtual void createScene(void);
	virtual void createCamera(void);
	virtual void createViewports(void);

};

#endif // #ifndef __TutorialApplication_h_
