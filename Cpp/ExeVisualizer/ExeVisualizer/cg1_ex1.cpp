/* ----------------------------------------------------------------
   name:           cg1_ex1.c
   purpose:        robot in OpenGL with scenegraph traversal
                   entry point, cg1_ws05/06 assignment 1
   version:        SKELETON CODE
   author:         andy nealen
                   computer graphics
                   tu berlin
   ------------------------------------------------------------- */
#include <iostream>
#include "glut.h"
#include "glutcallbacks.h"
#include "robot.h"

using namespace std;

void init();

int main(int argc, char** argv)
{
    
    glutInit(&argc, argv);
    glutInitDisplayMode( GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH );
    glutInitWindowSize(600, 600);
    glutInitWindowPosition(100, 100);
    glutCreateWindow("CG1_HA01 - Robot - Langenhagen, Amrhein");

	registerCallbacks();
    // initialize OpenGL (see glutcallbacks.c)
    init();

	buildRobot();
		
	// start GLUT event loop
    glutMainLoop();


    return 0;
}

