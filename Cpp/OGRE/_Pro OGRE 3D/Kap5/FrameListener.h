/////////////////////////////////////////////////////////////////////////////////////////
// FrameListener.h
// Enthält die von der FrameListener abgeleitete Klasse MyFrameListener
/////////////////////////////////////////////////////////////////////////////////////////
#include "OgreFrameListener.h"
using namespace Ogre;

//########################################################################################################### MyFrameListener
class MyFrameListener : public FrameListener
{
private:
	float m_timeElapsed;
public:
	MyFrameListener() { m_timeElapsed = 0.0f; }									// ctor

	bool frameStarted(const FrameEvent &evt);
	bool frameEnded(const FrameEvent &evt) { return true; }
};

/////////////////////////////////////////////////
// frameStarted Methode
bool MyFrameListener::frameStarted(const FrameEvent &evt)
{
	m_timeElapsed += evt.timeSinceLastFrame;

	if (m_timeElapsed > 15.0f) 
		return false;
	else
		return true;
}