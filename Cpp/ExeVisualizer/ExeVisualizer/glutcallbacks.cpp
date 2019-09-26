/* ----------------------------------------------------------------
   name:           glutcallbacks.c
   purpose:        init, display and everything else needed by GLUT
   version:		   SKELETON CODE
   author:         andy nealen
				   computer graphics
				   tu berlin
   ------------------------------------------------------------- */

#include <stdio.h>
#include <stdlib.h>

#include <iostream>
#include <fstream>

using namespace std;

#include "glut.h"
#include "scenegraph.h"
#include "robot.h"


// some stateholders for mouse motion
// last mouse position in window
int posx = 0, posy = 0;
// is left mouse button pressed
int lbutton = 0;										// WARUM int?

// pointer to currently selected node for transformations
// start with root node (torso)
Node* selected = &torso;

/* ------------------------------------------------ 
    implemented glutCallbacks:
    init        - initialize OpenGL
    display     - display scene
    reshape     - reshape window
    keyboard    - keyboard callbacks
    mouseMenu   - right mouse button menu
    mouseMotion - apply rotation when dragging
	mouse       - register mouse clicks
	registerCallbacks - 
	              register all callbacks with GLUT
   ------------------------------------------------ */

const int anz = 10000000;

#define filename "WinRar.exe"

char picture[anz];

void loadPicture()
{
	for( int i = 0; i<anz; i++)
		picture[i]=-128;


	ifstream inFile( filename, ios::in | ios::binary);
	if( !inFile )
	{
		cerr << "Fehler beim Oeffnen der Datei!\n";
	}
	else
	{
		for( int i=0; i<anz; i++ )
		{
			if(	!inFile.read( &picture[i], sizeof(char) ))
			;//	cout << "Error";
		}

		inFile.close();
		
	}

}




void init(void)
{

	loadPicture();

//	for(int i = 0 ; i<anz; i++)
//		cout << picture[i];

	glutFullScreen();											// Fullscreen

    // light and material
    GLfloat mat_ambient[] = {0.5, 0.5, 0.5, 1.0};
    GLfloat mat_specular[] = {0.6, 0.6, 0.6, 1.0};
    GLfloat mat_shininess[] = { 3.0 };
    GLfloat model_ambient[] = { 0.17, 0.17, 0.17 };
    GLfloat light_position[] = { 5.0, 5.0, 5.0, 0.0 };
    glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient);
    glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
    glMaterialfv(GL_FRONT, GL_SHININESS, mat_shininess);
    glLightfv(GL_LIGHT0, GL_POSITION, light_position);
    glLightModelfv(GL_LIGHT_MODEL_AMBIENT, model_ambient);
    glEnable(GL_LIGHTING);
    glEnable(GL_LIGHT0);

    // shading
    glShadeModel(GL_SMOOTH);				// macht alles schön weich und unkantig

    // clear background to black and clear depth buffer
    glClearColor(0.0,0.0,0.0,1.0);
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		glEnable(GL_COLOR_MATERIAL);

		// enable depth test (z-buffer)
		glDepthFunc(GL_LESS);
		glEnable(GL_DEPTH_TEST);

		// enable normalization of vertex normals
		glEnable(GL_NORMALIZE);

		// initial view definitions
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		// perspective projection
		gluPerspective(40.0, 1.0, 1.0, 10.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		// some output to console
		printf("--------------------------------------------\n");
		printf(" cg1_ex1 opengl robot scenegraph            \n");
		printf("                                            \n");
		printf(" keyboard:                                  \n");
		printf(" q/Q: quit program                          \n");
		printf(" x/X,y/Y,z/Z: rotate node                   \n");
		printf(" t/T: switch to rotate torso                \n");
		printf(" Space: toggle rotate axes                  \n");
		printf("                                            \n");
		printf(" mouse:                                     \n");
		printf(" right click: select node menu              \n");
		printf(" left click+drag: roatate node              \n");
		printf("--------------------------------------------\n");
}


// display callback for GLUT
void display(void)
{


		// clear color and depth buffer
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		// switch to opengl modelview matrix
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
		// position the camera at (0,0,3) looking down the
		// negative z-axis at (0,0,0)
    gluLookAt(0.0, 0.0, 6, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
	
		glClearColor(0.0,0.0,0.0,0.0);				// setBGColor

		float scale = 300;

		float minpx = -3.5*scale;
		float minpy = 2 *scale;
		int sizex = 3 *scale;

		float px=minpx;
		float py=minpy;
		
		glPointSize(1.0);
		glBegin(GL_POINTS);
		for( int i = 0; i<anz; i=i+3)
		{
			if( (int)px % sizex == 0 && px>0)
			{
				px=minpx;
				py--;
			}


			glColor3b(picture[i+0],picture[i+1],picture[i+2]);
			glVertex2f(px++/scale,py/scale);
			
		}
		glEnd();
	


		// display back buffer
    glutSwapBuffers();
}

// reshape-Callback for GLUT
void reshape(int w, int h)
{
	// reshaped window aspect ratio
	float aspect = (float) w / (float) h;

    // viewport
    glViewport(0,0, (GLsizei) w, (GLsizei) h);

	// clear background and depth buffer
    glClearColor(0.0,0.0,0.0,1.0);
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    // restore view definition after window reshape
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
	// perspective projection
    gluPerspective(40.0, aspect, 1.0, 1000.0);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

	display();
}

// keyboard callback
void keyboard(unsigned char key, int x, int y)
{
		// rotate selected node around 
		// x,y and z axes with keypresses
		switch( key ) {
				case 'q':
				case 'Q': exit(0);
				case 'z':
						selected->rz += 2.0;		break;
				case 'Z': 
						selected->rz -= 2.0;		break;
				case 'x':
						selected->rx += 2.0;		break;
				case 'X': 
						selected->rx -= 2.0;		break;
				case 'y':
						selected->ry += 2.0;		break;
				case 'Y': 
						selected->ry -= 2.0;		break;
				case ' ': 
						showAxes = !showAxes;		break;
				case 't':
				case 'T':
						selected = &torso;			break;
				default:
						break;
		}
	display();
}

// the right button mouse menu
void mouseMenu(int id)
{
    switch (id) {
		case 1: exit(0);
		case 2:	selected = &torso; break;				// select torso for bla & so on...						
		case 3: selected = &neck; break;
		case 4: selected = &head; break;
		case 5: selected = &armRUp; break;
		case 6: selected = &armLUp; break;
		case 7: selected = &armRLow; break;
		case 8: selected = &armLLow; break;
		case 9: selected = &legRUp; break;
		case 10: selected = &legLUp; break;
		case 11: selected = &legRLow; break;
		case 12: selected = &legLLow; break;
		case 13: selected = &handR; break;
		case 14: selected = &handL; break;
		case 15: selected = &footR; break;
		case 16: selected = &footL; break;
		case 17: selected = &teapot; break;
		case 18: selected = &sabre; break;
		default: break;
	}
}

// mouse motion
void mouseMotion(int x, int y)
{
	// rotate selected node when left mouse button is pressed
	if (lbutton) {
		selected->rx += (float) (y-posy);
		posy = y;
		selected->ry += (float) (x-posx);
		posx = x;
		display();
	}
}

// mouse callback
void mouse(int btn, int state, int x, int y)
{
	if (btn == GLUT_LEFT) {
		if (state == GLUT_UP) {
			lbutton = 0;
		}
		if (state == GLUT_DOWN) {
			lbutton = 1;
			posx = x; posy = y;
		}
	}
}

// renders a new picture even in noevent case
void idle(){

		display();
}

// register callbacks with GLUT
void registerCallbacks(void)
{
    glutDisplayFunc(display);
    glutKeyboardFunc(keyboard);
    glutReshapeFunc(reshape);
    glutMotionFunc(mouseMotion);
    glutMouseFunc(mouse);
		glutIdleFunc(idle);
		glutCreateMenu(mouseMenu);
		{
				glutAddMenuEntry("quit",1);
				glutAddMenuEntry("torso",2);
				glutAddMenuEntry("neck",3);
				glutAddMenuEntry("head",4);
				glutAddMenuEntry("right upper arm",5);
				glutAddMenuEntry("left upper arm",6);
				glutAddMenuEntry("right lower arm",7);
				glutAddMenuEntry("left lower arm",8);
				glutAddMenuEntry("right upper leg",9);
				glutAddMenuEntry("left upper leg",10);
				glutAddMenuEntry("right lower leg",11);
				glutAddMenuEntry("left lower leg",12);
				glutAddMenuEntry("right hand",13);
				glutAddMenuEntry("left hand",14);
				glutAddMenuEntry("right foot",15);
				glutAddMenuEntry("left foot",16);
				glutAddMenuEntry("teapot",17);
				glutAddMenuEntry("lightsabre",18);
		}

    glutAttachMenu(GLUT_RIGHT_BUTTON);
    return;
}
