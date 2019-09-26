/* ----------------------------------------------------------------
   name:           glutcallbacks.h
   purpose:        prototypes of GLUT-Callbacks
   version:		   SKELETON CODE
   author:         andy nealen
				   computer graphics
				   tu berlin
   ------------------------------------------------------------- */

#ifndef GLUTCALL_H
#define GLUTCALL_H

// intialization                                             
void init(void);

// register callbacks with GLUT
void registerCallbacks(void);

// display scene
// register with glutDisplayFunc(display)
void display(void);

// redisplay scene after window reshape
// register with glutReshapeFunc(reshape)
void reshape(int width, int height);

// idle callback (for animations)
// register with glutIdleFunc(idle)
void idle(void);

// mouse callback
// register with glutMouseFunc(mouse)
void mouse(int btn, int state, int x, int y);

// kexboard callback
// register with glutKeyboardFunc(keyboard)
void keyboard(unsigned char key, int x, int y);

// mouse motion callback
// register with glutMotionFunc(mouseMotion)
void mouseMotion(int x, int y);

// mouse menu
// register with glutCreateMenu(mouseMenu)
// on right mouse button with glutAttachMenu(GLUT_RIGHT_BUTTON);
void mouseMenu(int id);

#endif /* GLUTCALLBACK */
