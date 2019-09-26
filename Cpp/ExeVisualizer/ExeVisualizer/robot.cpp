/* ----------------------------------------------------------------
   name:           robot.c
   purpose:        implementation of the scenegraph construction
   version:		   SKELETON CODE
   author:         andy nealen
				   computer graphics
				   tu berlin
   ------------------------------------------------------------- */

#include "robot.h"

// global nodes of the scenegraph
// we need this information to set individual node transformations
Node		torso,  
				neck, head, armRUp, armLUp, legRUp, legLUp, 
				armRLow, armLLow, legRLow, legLLow,  
				footR, footL, handR, handL, 
				teapot, sabre;

// build the robot scenegraph
void buildRobot()
{
	// torso attributes and initial position.																	// Eigenschaften der Koerperteile
	// for struct members and their meanings,
	// see file scenegraph.h
	torso.type=0;
	torso.height = 1.0f; torso.width = 1.0f; torso.depth = 1.0f;
	torso.ox = 0.0f; torso.oy = 0.0f; torso.oz = 0.0f;
	torso.x = 0.0f; torso.y = 0.0f; torso.z = 0.0;
	torso.rx = 12.0f; torso.ry = 16.0f; torso.rz = 10.0f;

	// neck
	neck.type=1;
	neck.height = 0.1f; neck.width = 0.03f; neck.depth = 0.03f;
	neck.ox = 0.0f; neck.oy = -0.05f; neck.oz = 0.0f;
	neck.x = 0.0f; neck.y = 0.37f; neck.z = 0.0f;
	neck.rx = 00.0f; neck.ry = -15.0f; neck.rz = 0.0f;

	// head
	head.type=2;
	head.height = 0.17f; head.width = 0.2f; head.depth = 0.1f;
	head.ox = 0.0f; head.oy = 0.0f; head.oz = 0.0f;
	head.x = 0.0f; head.y = 0.21f; head.z = 0.0f;
	head.rx = 0.0f; head.ry = 0.0f; head.rz = 0.0f;

	// armRUp
	armRUp.type=3;
	armRUp.height = 1.0f; armRUp.width = 1.0f; armRUp.depth = 1.0f;
	armRUp.ox = 0.0f; armRUp.oy = 0.17f; armRUp.oz = 0.0f;
	armRUp.x = -0.27f; armRUp.y = 0.08f; armRUp.z = 0.0f;
	armRUp.rx = -30.0f; armRUp.ry = 0.0f; armRUp.rz = -25.0f;

	// armLUp
	armLUp.type=3;
	armLUp.height = 1.0f; armLUp.width = 1.0f; armLUp.depth = 1.0f;
	armLUp.ox = 0.0f; armLUp.oy = 0.17f; armLUp.oz = 0.0f;
	armLUp.x = 0.27f; armLUp.y = 0.08f; armLUp.z = 0.0f;
	armLUp.rx = 40.0f; armLUp.ry = 0.0f; armLUp.rz = 20.0f;

	// armRLow
	armRLow.type=4;
	armRLow.height = 1.0f; armRLow.width = 1.0f; armRLow.depth = 1.0f;
	armRLow.ox = 0.0f; armRLow.oy = 0.13f; armRLow.oz = 0.0f;
	armRLow.x = 0.0f; armRLow.y = -0.24f; armRLow.z = 0.0f;
	armRLow.rx = -35.0f; armRLow.ry = 0.0f; armRLow.rz = 25.0f;

	// armLLow
	armLLow.type=4;
	armLLow.height = 1.0f; armLLow.width = 1.0f; armLLow.depth = 1.0f;
	armLLow.ox = 0.0f; armLLow.oy = 0.13f; armLLow.oz = 0.0f;
	armLLow.x = 0.0f; armLLow.y = -0.24f; armLLow.z = 0.0f;
	armLLow.rx = -50.0f; armLLow.ry = 0.0f; armLLow.rz = -10.0f;

	// legRUp
	legRUp.type=5;
	legRUp.height = 1.0f; legRUp.width = 1.0f; legRUp.depth = 1.0f;
	legRUp.ox = 0.0f; legRUp.oy = 0.0f; legRUp.oz = 0.0f;
	legRUp.x = -0.1f; legRUp.y = -0.35f; legRUp.z = 0.0f;
	legRUp.rx = 40.0f; legRUp.ry = 10.0f; legRUp.rz = 0.0f;

	// legLUp
	legLUp.type=5;
	legLUp.height = 1.0f; legLUp.width = 1.0f; legLUp.depth = 1.0f;
	legLUp.ox = 0.0f; legLUp.oy = 0.0f; legLUp.oz = 0.0f;
	legLUp.x = 0.1f; legLUp.y = -0.35f; legLUp.z = 0.0f;
	legLUp.rx = -50.0f; legLUp.ry = 0.0f; legLUp.rz = 5.0f;

	// legRLow
	legRLow.type=6;
	legRLow.height = 1.0f; legRLow.width = 1.0f; legRLow.depth = 1.0f;
	legRLow.ox = 0.0f; legRLow.oy = 0.0f; legRLow.oz = 0.0f;
	legRLow.x = 0.0f; legRLow.y = -0.3f; legRLow.z = 0.0f;
	legRLow.rx = 30.0f; legRLow.ry = 0.0f; legRLow.rz = 15.0f;

	// legLLow
	legLLow.type=6;
	legLLow.height = 1.0f; legLLow.width = 1.0f; legLLow.depth = 1.0f;
	legLLow.ox = 0.0f; legLLow.oy = 0.0f; legLLow.oz = 0.0f;
	legLLow.x = 0.0f; legLLow.y = -0.3f; legLLow.z = 0.0f;
	legLLow.rx = 30.0f; legLLow.ry = 0.0f; legLLow.rz = -10.0f;

	// footR
	footR.type=7;
	footR.height = 1.0f; footR.width = 1.0f; footR.depth = 1.0f;
	footR.ox = 0.0f; footR.oy = 0.0f; footR.oz = 0.0f;
	footR.x = 0.0f; footR.y = -0.3f; footR.z = 0.0f;
	footR.rx = 30.0f; footR.ry = 0.0f; footR.rz = 0.0f;

	// footL
	footL.type=7;
	footL.height = 1.0f; footL.width = 1.0f; footL.depth = 1.0f;
	footL.ox = 0.0f; footL.oy = 0.0f; footL.oz = 0.0f;
	footL.x = 0.0f; footL.y = -0.3f; footL.z = 0.0f;
	footL.rx = 30.0f; footL.ry = 0.0f; footL.rz = 0.0f;
	
	// handR
	handR.type=8;
	handR.height = 1.0f; handR.width = 1.0f; handR.depth = 1.0f;
	handR.ox = 0.0f; handR.oy = 0.0f; handR.oz = 0.0f;
	handR.x = 0.0f; handR.y = -0.13f; handR.z = 0.0f;
	handR.rx = -25.0f; handR.ry = 0.0f; handR.rz = 0.0f;

	// handL
	handL.type=8;
	handL.height = 1.0f; handL.width = 1.0f; handL.depth = 1.0f;
	handL.ox = 0.0f; handL.oy = 0.0f; handL.oz = 0.0f;
	handL.x = 0.0f; handL.y = -0.13f; handL.z = 0.0f;
	handL.rx = -45.0f; handL.ry = 20.0f; handL.rz = 0.0f;

	// teapot
	teapot.type=9;
	teapot.height = 1.0f; teapot.width = 1.0f; teapot.depth = 1.0f;
	teapot.ox = 0.0f; teapot.oy = 0.0f; teapot.oz = 0.0f;
	teapot.x = 0.0f; teapot.y = -0.0; teapot.z = 0.0f;
	teapot.rx = 20.0f; teapot.ry = 15.0f; teapot.rz = 10.0f;

		// sabre
	sabre.type=10;
	sabre.height = 1.0f; sabre.width = 1.0f; sabre.depth = 1.0f;
	sabre.ox = 0.0f; sabre.oy = 0.0f; sabre.oz = 0.0f;
	sabre.x = 0.0f; sabre.y = -0.0; sabre.z = 0.0f;
	sabre.rx = 0.0f; sabre.ry = 0.0; sabre.rz = 0.0f;

	// finally, attach the robot/model parts																	// Szenengraphen aufstellen
	// example: torso.child = &leftArm, etc...
	// (note, that the torso currently has no children and/or siblings)
	// BUILD THE SCENEGRAPH
	torso.sibling = 0;						// Level 0
	torso.child = &neck;
	
	neck.sibling = &armRUp;				// 1
	neck.child = &head;						

	armRUp.sibling = &armLUp;			// 1
	armRUp.child = &armRLow;

	armLUp.sibling = &legRUp;			// 1
	armLUp.child = &armLLow;

	legRUp.sibling = &legLUp;			// 1
	legRUp.child = &legRLow;

	legLUp.sibling = 0;						// 1
	legLUp.child = &legLLow;

	head.sibling = 0;							// 2
	head.child = 0;

	armRLow.sibling = 0;					// 2
	armRLow.child = &handR;				

	armLLow.sibling = 0;					// 2
	armLLow.child = &handL;

	legRLow.sibling = 0;					// 2
	legRLow.child = &footR;

	legLLow.sibling = 0;					// 2
	legLLow.child = &footL;

	handR.sibling = 0;						// 3
	handR.child = &teapot;

	handL.sibling = 0;						// 3
	handL.child = &sabre;

	footR.sibling = 0;						// 3
	footR.child = 0;

	footL.sibling = 0;						// 3
	footL.child = 0;

	teapot.sibling = 0;						// 4
	teapot.child = 0;

	sabre.sibling = 0;						// 4
	sabre.child = 0;
}
