/***********************************************************************
 * mt4j Copyright (c) 2008 - 2009 Christopher Ruff, Fraunhofer-Gesellschaft All rights reserved.
 *  
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 ***********************************************************************/
package org.mt4j.util.opengl;

import java.util.Stack;

import javax.media.opengl.GL;

import org.mt4j.components.visibleComponents.AbstractVisibleComponent;
import org.mt4j.util.math.Tools3D;

import processing.core.PGraphics;

/**
 * Abstracts the OpenGL stencil buffer for
 * clipping use.
 * 
 * @author Christopher Ruff
 */
public class GLStencilUtil {
	
	/** The instance. */
	public static GLStencilUtil instance;
	
	/** The initialized. */
	boolean initialized = false;
	
	/** The stencil value stack. */
	public static Stack<Integer> stencilValueStack = new Stack<Integer>();
	
	private static final int initialStencilValue = 6; //arbitrary value to save 1-5 for other operations..
	
	/** The current stencil value. */
	public static int currentStencilValue = initialStencilValue; 
	
	static{
		stencilValueStack.push(currentStencilValue);
	}
	
	//TODO record some methods/commands in displayLists?
	
	/**
	 * Gets the single instance of StencilStack.
	 * 
	 * @return single instance of StencilStack
	 */
	public static GLStencilUtil getInstance(){
		if (instance != null){
			return instance;
		}else{
			instance = new GLStencilUtil();
			return instance;
		}
	}
	
	
	/*
	 * Stencil comparison in stencil func explained:
	 * gl.glStencilFunc( <func comparison>,  <referenceValue>,  <mask>); 
	 * boolean result = (referenceValue & mask) <func comparison> (stencilValue & mask) 
	 */
	
	/**
	 * Sets up the stencil buffer.
	 * After calling this method, every draw command will be written into
	 * the stencil buffer only, marking the clipping area for later.
	 * 
	 * @param gl the gl
	 */
	public void beginDrawClipShape(GL gl){ //begin draw clip shape
//		gl.glPushAttrib(GL.GL_STENCIL_BUFFER_BIT | GL.GL_STENCIL_TEST); //FIXME do only at initialization??
		gl.glPushAttrib(GL.GL_STENCIL_BUFFER_BIT); //FIXME do only at initialization??
		
		if (!initialized){
//			gl.glPushAttrib(GL.GL_STENCIL_BUFFER_BIT | GL.GL_STENCIL_TEST);
			
			//Enable stencilbuffer
			gl.glClearStencil(stencilValueStack.peek());
			gl.glClear(GL.GL_STENCIL_BUFFER_BIT);
			gl.glEnable(GL.GL_STENCIL_TEST);
//			gl.glStencilMask (0x0000000D);
		}

		int currentStencilValue = stencilValueStack.peek();

		//Dont draw into the color or depth buffer while drawing the clip shape
		gl.glColorMask(false,false,false,false);
		gl.glDisable(GL.GL_BLEND);
		gl.glDepthMask(false);//remove..?

		if (!initialized){
			initialized = true;
			//If were at the top level = nothing written into stencil buffer yet
			//draw mask value into buffer regardless if stencilfunc suceeds 
			gl.glStencilFunc (GL.GL_ALWAYS, currentStencilValue, currentStencilValue);
		}else{
			//= draw mask value into stencil only where the current/last stencil value is equal to the current stencil value?
			//we may not write into the stencil somehwhere else -> the parent may have also clipped something
			//-> write only where the parent clip wrote its stencil clip mask and dont go beyond that
			gl.glStencilFunc (GL.GL_EQUAL, currentStencilValue, currentStencilValue); 
		}
		 
		//We write the current stencil value +1 ! into the stencil buffer where the 
		//stencil func succeeds
		//This marks the area where we are allowed to draw the clipped shape later
		gl.glStencilOp(GL.GL_KEEP, GL.GL_KEEP, GL.GL_INCR); //FIXME also write stencil value if depth test fails?
	    
	    //- we increment the value on the stencil stack 
	    //so it correlates with the incremented value in the stencil buffer
	    stencilValueStack.push(++currentStencilValue);
	}
	
	
	/**
	 * This method should be called after drawing the clipping shape into the stencil buffer 
	 * using <code>beginDrawClipShape</code>. 
	 * After invoking <code>beginDrawClipped</code>, we can now draw only where we drew the clipping shape.
	 * 
	 * @param gl the gl
	 */
	public void beginDrawClipped(GL gl){ //draw clipped
		int incrementedStencilValue = stencilValueStack.peek();
		//TODO instead of setting depth, blend etc, use glPush/Popattrib !?
		
		//Prepare draw where the value of the stencil buffer is 
		//the same as the current stencil stack value 
		//(the value which was written into the buffer at "beginDrawClippingShape()"
		gl.glDepthMask(true);
		gl.glEnable (GL.GL_BLEND); 
//		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);  //FIXME NEEDED?
		gl.glColorMask(true, true, true, true);
		gl.glStencilFunc(GL.GL_EQUAL, incrementedStencilValue, incrementedStencilValue);
		gl.glStencilOp(GL.GL_KEEP, GL.GL_KEEP, GL.GL_KEEP);
	}

	
	/**
	 * Ends the drawing to the clipped area only.
	 * 
	 * @param gl the gl
	 */
	public void endClipping(PGraphics g, GL gl){
		this.endClipping(g, gl, null);
	}
	
	public boolean isClipActive(){
		return stencilValueStack.size() > 1;
	}

	/**
	 * Ends the drawing to the clipped area and restores the previously used
	 * clip area if there is one.
	 * 
	 * @param gl the gl
	 * @param clipShape the clip shape
	 */
	public void endClipping(PGraphics g, GL gl, AbstractVisibleComponent clipShape){ //stop clipping
		//Remove the top/last used stencil mask value from the stack
		int currentStencilValue = stencilValueStack.pop();
		
		//At the end of every stencil clip hierarchy recursion 
		//set this so that at the next hierarchy, 
		//the stencil is completely cleared again
		if (stencilValueStack.size() == 1){
			initialized = false;
//			//Restore stencil attributes, disables stencil test and restores stencil buffer bit
//			gl.glPopAttrib();
		}else if (stencilValueStack.isEmpty()){
			System.err.println("Too many calls to " + this.getClass().getName() + ".endClipping() !");
			stencilValueStack.push(initialStencilValue);
		}else{
			if (clipShape != null){
				gl.glColorMask(false,false,false,false);
				gl.glDepthMask(false);//remove..?
				
//				/*
				//ORIGINAL
				//Decrease stencil value again where we increased it at drawing the clipping shape 
				//(so the stencil values are same as before drawing the clip shape)
				gl.glStencilFunc (GL.GL_EQUAL, currentStencilValue, currentStencilValue); 
				gl.glStencilOp(GL.GL_KEEP, GL.GL_KEEP, GL.GL_DECR);
				//FIXME this can be bad for performance if the clipshape is complex
				clipShape.drawComponent(clipShape.getRenderer().g); 
//				*/
				gl.glDepthMask(true);
				gl.glColorMask(true, true, true, true);
			}else{
				
				//TODO use full screen quad technique if clipshape vertices > 10 ?
//				/*
				//Draw fullscreen quad to restore stencil
				//->peek last value and draw fullscreen quad writing last peek value everywhere 
				//where lastPeekValue lesser to stencil value? 
				
				//Option 1, replace stencil value with previous value if stencil is higher than previous value
				int last = stencilValueStack.peek();
				gl.glStencilFunc (GL.GL_LESS, last, 0xFF); 
				gl.glStencilOp(GL.GL_KEEP, GL.GL_KEEP, GL.GL_REPLACE);
				//Option 2, decrement at the last pushed value
//				gl.glStencilFunc (GL.GL_EQUAL, currentStencilValue, currentStencilValue);
//				gl.glStencilOp(GL.GL_KEEP, GL.GL_KEEP, GL.GL_DECR);
				
				Tools3D.beginGL(g);
				gl.glMatrixMode(GL.GL_PROJECTION);
				gl.glPushMatrix();
				gl.glLoadIdentity();
				
				gl.glMatrixMode(GL.GL_MODELVIEW);
				gl.glPushMatrix();
				gl.glLoadIdentity();
				
				gl.glBegin(GL.GL_QUADS);
				gl.glVertex2f(-1, -1);
				gl.glVertex2f(1, -1);
				gl.glVertex2f(1, 1);
				gl.glVertex2f(-1, 1);
				gl.glEnd();
				
				gl.glPopMatrix();

				gl.glMatrixMode(GL.GL_PROJECTION);
				gl.glPopMatrix();

				gl.glMatrixMode(GL.GL_MODELVIEW);
				Tools3D.endGL(g);
//				*/
				
			}
		}
		
		//Restore stencil attributes, disables stencil test and restores stencil buffer bit
		gl.glPopAttrib(); //FIXME do this only when stack is emtpied?
	}
	

}
