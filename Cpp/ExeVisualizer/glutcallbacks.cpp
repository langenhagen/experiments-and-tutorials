#include <stdio.h>
#include <stdlib.h>

#include <iostream>
#include <fstream>

#include "glut.h"

using namespace std;

#define anz			10000000
#define filename	"file.exe"
//#define SHOW_ASCII_FILE

char picture[anz];

void loadPicture()
{
	// init picture with black pixels
	for( int i = 0; i<anz; i++)
		picture[i]=-128;

	ifstream inFile( filename, ios::in | ios::binary);
	if( !inFile )
	{
		cerr << "Fehler beim Oeffnen der Datei!\n";
		cin.sync();
		cin.get();
		exit(0);
	}
	else
	{
		for( int i=0; i<anz; i++ )
		{
			if(	!inFile.read( &picture[i], sizeof(char) ))
				;//cout << "Error";
		}
		inFile.close();
	}
}

void init(void)
{
	loadPicture();

#ifdef SHOW_ASCII_FILE
	for(int i = 0 ; i<anz; i++)
		cout << picture[i];
#endif

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

	glShadeModel(GL_SMOOTH);

    glClearColor(0.0,0.0,0.0,0.0);
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
}

// display callback for GLUT
void display(void)
{
	// clear color and depth buffer
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	// switch to opengl modelview matrix
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

	gluLookAt(0.0, 0.0, 6, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);

	const float scale = 300;
	const float minpx = -3.5*scale;
	const float minpy = 2 *scale;
	const int sizex = 3 *scale;

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
}

// reshape-Callback for GLUT
void reshape(int w, int h)
{
	// reshaped window aspect ratio
	float aspect = (float) w / (float) h;

    // viewport
    glViewport(0,0, (GLsizei) w, (GLsizei) h);

	// clear background and depth buffer
    glClearColor(0.0,0.0,0.0,0.0);
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
// renders a new picture even in noevent case
void idle()
{
}

// keyboard callback
void keyboard(unsigned char key, int x, int y)
{
	switch( key )
	{
		case 'q':
		case 'Q': exit(0);
	}
}

// mouse callback
// register with glutMouseFunc(mouse)
void mouse(int btn, int state, int x, int y)
{
}

// mouse motion callback
// register with glutMotionFunc(mouseMotion)
void mouseMotion(int x, int y)
{
}

// the right button mouse menu
void mouseMenu(int id)
{
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
}