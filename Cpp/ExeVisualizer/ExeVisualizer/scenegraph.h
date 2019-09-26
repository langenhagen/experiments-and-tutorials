/* ----------------------------------------------------------------
   name:           scenegraph.h
   purpose:        the graph-node struct and scenegraph-functions
   version:		   SKELETON CODE
   author:         andy nealen
				   computer graphics
				   tu berlin
   ------------------------------------------------------------- */

#ifndef SCENEGRAPH_H
#define SCENEGRAPH_H

// NOTE: each node in the scenegraph can have multiple 
//       children/siblings by pointing to its first child via 
//       the 'child' pointer and then linking up the children 
//       using the 'sibling' pointers. 
//       in the following example diagram, N = node
//
//
//       the diagram/linking:
//
//                    N (root node of this example scenegraph)
//                   /
//               child
//                /
//               N - sibling - N - sibling - N
//              /             /
//          child         child        
//           /             /
//          N             N - sibling - N
// 
//
//       represents the following scenegraph/tree:
//
//
//                    N (root node of this example scenegraph)
//                   /|\
//                 /  |  \
//               N    N   N
//              /    /|
//            /    /  |
//           N    N   N
//


		#include "glut.h"


// indicates wether the rotation-axes shall be shown
extern bool showAxes;		

// define the shininess of the objects
GLfloat mat_teapot_shininess[];
GLfloat mat_shininess[];

// node for scenegraph construction
typedef struct node Node;
struct node {

		char type;				// representing the type of object as what this node shall be rendered

		float x;					// x,y,z position of
		float y;					// node center relative
		float z;					// to parent node center

		float rx;					// x,y,z rotation of
		float ry;					// node relative
		float rz;					// to parent node rotation

		float ox;					// x,y,z offset of 
		float oy;					// rotation axis
		float oz;					// from center of body part
											// i.e. for rotation about
											// a "shoulder" as opposed
											// to the center of mass
											// of a glutsolidube

		float height;			// height,
		float width;			// width
		float depth;			// and depth of this body part

		Node *sibling;		// sibling node the scenegraph
		Node *child;			// child node the scenegraph
};
  
// traverse and draw the scenegraph from a given node
void sceneGraphTraversal(Node *node);

// draw an individual object, the 'type' indicates what geometric structure it will have
void drawObj(Node* node);

// transform an individual node according 
// to x,y,z,rx,ry,rz,ox,oy and oz
void transformNode(Node* node);

// returns a random float between -1 and 1
inline float randPos();

// returns a random float between 0 and -1
inline float randTrans();

#endif
