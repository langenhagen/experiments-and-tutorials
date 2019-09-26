/* ----------------------------------------------------------------
   name:           robot.h
   purpose:        the scenegraph/robot hierarchy/construction
   version:		   SKELETON CODE
   author:         andy nealen
				   computer graphics
				   tu berlin
   ------------------------------------------------------------- */

#ifndef ROBOT_H
#define ROBOT_H

#include "scenegraph.h"

// global nodes of the scenegraph
// we need this information to set individual node transformations
extern Node torso, neck, head, armRUp, armLUp, armRLow, armLLow, 
						legRUp, legLUp, legRLow, legLLow, footR, footL, 
						handR, handL, teapot, sabre;

// construct robot hierarchy
void buildRobot();

#endif
