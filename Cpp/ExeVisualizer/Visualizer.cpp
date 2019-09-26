#include <iostream>
#include "glut.h"
#include "glutcallbacks.h"

using namespace std;

void main(int argc, char** argv)
{
    glutInit(&argc, argv);
    glutInitDisplayMode( GLUT_SINGLE | 0 | GLUT_DEPTH );
    glutInitWindowSize(600, 600);
    glutInitWindowPosition(0, 0);
    glutCreateWindow("Visualizer");

	registerCallbacks();
    
	// initialize OpenGL (see glutcallbacks.c)
    init();
			
	// start GLUT event loop
    glutMainLoop();
}