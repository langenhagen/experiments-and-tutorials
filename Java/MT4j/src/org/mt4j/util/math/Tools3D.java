/***********************************************************************
 * mt4j Copyright (c) 2008 - 2009 C.Ruff, Fraunhofer-Gesellschaft All rights reserved.
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
package org.mt4j.util.math;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;
import java.util.StringTokenizer;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import org.mt4j.MTApplication;
import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.components.visibleComponents.StyleInfo;
import org.mt4j.components.visibleComponents.shapes.GeometryInfo;
import org.mt4j.input.inputData.InputCursor;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.camera.IFrustum;
import org.mt4j.util.camera.Icamera;
import org.mt4j.util.opengl.GLTexture;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PGraphics3D;
import processing.core.PImage;
import processing.core.PMatrix3D;
import processing.opengl.PGraphicsOpenGL;


/**
 * Class containing mostly static convenience utility methods.
 * 
 * @author Christopher Ruff
 */
public class Tools3D {
	//Declared here and static so it wont have to be initialize at every call to unproject
	/** The fb. */
	private static FloatBuffer fb = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asFloatBuffer();
	
	/** The fb un. */
	private static FloatBuffer fbUn = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asFloatBuffer();
	
	/** The model. */
	private static DoubleBuffer model;
	
	/** The proj. */
	private static DoubleBuffer proj;
	
	/** The view. */
	private static IntBuffer view;
	
	/** The win pos. */
	private static DoubleBuffer winPos;
	
	static{
		model 	= DoubleBuffer.allocate(16);
		proj 	= DoubleBuffer.allocate(16);
		view 	= IntBuffer.allocate(4);
		winPos 	= DoubleBuffer.allocate(3);
	}
	
	
	/**
	 * Unprojects screen coordinates from 2D into 3D world space and returns a point that
	 * can be used to construct a ray form the camera to that point and check
	 * for intersections with objects.
	 * <p><b>NOTE</b>: if using openGL mode, the openGL context has to be valid at the time of calling this method.
	 * 
	 * @param applet the applet
	 * @param camera the camera
	 * @param screenX the screen x
	 * @param screenY the screen y
	 * 
	 * @return the vector3d
	 */
	public static Vector3D unprojectScreenCoords(PApplet applet, Icamera camera, float screenX, float screenY ){
		Vector3D ret;
		applet.pushMatrix();
		camera.update();
		ret = Tools3D.unprojectScreenCoords(applet, screenX, screenY);
		applet.popMatrix();
		return ret;
	}
	
	/**
	 * Unprojects screen coordinates from 2D into 3D world space and returns a point that
	 * can be used to construct a ray form the camera to that point and check
	 * for intersections with objects.
	 * <p><b>NOTE</b>: if using openGL mode, the openGL context has to be valid at the time of calling this method.
	 * 
	 * @param applet processings PApplet object
	 * @param screenX x coordinate on the screen
	 * @param screenY y coordinate on the screen
	 * 
	 * @return a point that lies on the line from the screen coordinates
	 * to the 3d world coordinates
	 */
	public static Vector3D unprojectScreenCoords(PApplet applet, float screenX, float screenY ){ //FIXME MAKE PRIVATE AGAIN! 
		Vector3D returnVect = new Vector3D(-999,-999,-999); //null?
		
//		MT4jSettings.getInstance().setRendererMode(MT4jSettings.P3D_MODE);
		
		switch (MT4jSettings.getInstance().getRendererMode()) {
		case MT4jSettings.OPENGL_MODE:
			int viewport[] = new int[4];
			double[] proj  = new double[16];
			double[] model = new double[16];
			double[] mousePosArr = new double[4];
			
			try{
			PGraphicsOpenGL pgl = ((PGraphicsOpenGL)applet.g); 
			GL gl = pgl.beginGL();  
			GLU glu = pgl.glu;
			
				  gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);
				  gl.glGetDoublev(GL.GL_PROJECTION_MATRIX, proj, 0);
				  gl.glGetDoublev(GL.GL_MODELVIEW_MATRIX, model, 0);
				  
				  /*
				  System.out.println("OpenGL ProjectionMatrix: ");
				  for (int i = 0; i < proj.length; i++) {
						double p = proj[i];
						System.out.print(p + ", ");
						//if (i%4 == 0 && i==3)
						if (i==3 || i== 7 || i== 11 || i==15) {
							System.out.println();
						}
					  }
				  */
				  
				  /*
				  System.out.println("OpenGL ModelviewMatrix: ");
				  for (int i = 0; i < model.length; i++) {
						double p = model[i];
						System.out.print(p + ", ");
						//if (i%4 == 0 && i==3)
						if (i==3 || i== 7 || i== 11 || i==15) {
							System.out.println();
						}
					  }
				  System.out.println();
				  System.out.println("\n");
				  */
				  
				  /*
				  fbUn.clear();
				  gl.glReadPixels((int)screenX, applet.height - (int)screenY, 1, 1, GL.GL_DEPTH_COMPONENT, GL.GL_FLOAT, fbUn);
				  fbUn.rewind();
				  glu.gluUnProject((double)screenX, applet.height - (double)screenY, (double)fbUn.get(0), model, 0, proj, 0, viewport, 0, mousePosArr, 0);
				  */
				  
				  //FIXME test not using glReadpixel to get the depth at the location
				  //instead we have to build a ray with the result, from the camera location going through the resulst and check for hits ourselves
				  glu.gluUnProject((double)screenX, applet.height - (double)screenY, 0, model, 0, proj, 0, viewport, 0, mousePosArr, 0);
			  pgl.endGL();
			  
			  returnVect = new Vector3D((float)mousePosArr[0], (float)mousePosArr[1], (float)mousePosArr[2]);
			}catch(Exception e){
				e.printStackTrace();
				//System.out.println("Use method getWorldForScreenCoords only when drawing with openGL! And dont put negative screen values in!");
			}
			break;
		case MT4jSettings.P3D_MODE:
//			/*!
			try{
				Vector3D testpoint = new Vector3D(screenX, screenY, 1); //TEST! is 1 as winZ correct? -> or read from depth buffer at that pixel!
				
//				PMatrix modelView = new PMatrix(applet.g.getmodelview);
//				PMatrix projectionM = new PMatrix(applet.g.projection);
				
				//projectionsmatrix mit modelview multiplizieren 
//				projectionM.apply(modelView);
				
				//Ergebnis invertieren
//				PMatrix inv = projectionM.invert();
				
				PMatrix3D modelView 	= new PMatrix3D(applet.g.getMatrix());
				PMatrix3D projectionM 	= new PMatrix3D(((PGraphics3D)applet.g).projection);
				
				projectionM.apply(modelView);
				projectionM.invert();
				
				float[] result = new float[4];
				float[] factor = new float[]{  ((2 * testpoint.getX())  / applet.width)  -1,
											   ((2 * testpoint.getY())  / applet.height) -1, //screenH - y?
												(2 * testpoint.getZ()) -1 ,
												 1,};
				//Matrix mit Vector multiplizieren
				projectionM.mult(factor, result);
				
				//System.out.println("\nResult2: ");
				for (int i = 0; i < result.length; i++) {
					//W auf 1 machen!?
					result[i] /= result[result.length-1]; //normalize so w(4th coord) is 1
					//System.out.print(result[i] + " ");
				}
				
				//aus Result Vector3D machen
				returnVect = new Vector3D(result[0],result[1],result[2]);
			}catch(Exception e){
				e.printStackTrace();
			}
			break;
//			*/
		default:
			break;
		} 
//		System.out.println(returnVect);
		return returnVect;
	} 
	
	
	/**
	 * Gets the ray to pick this component.
	 *
	 * @param applet the applet
	 * @param component the component
	 * @param cursor the cursor
	 * @return the camera pick ray
	 */
	public static Ray getCameraPickRay(PApplet applet, IMTComponent3D component, InputCursor cursor){
		return Tools3D.getCameraPickRay(applet, component.getViewingCamera(), cursor.getCurrentEvtPosX(), cursor.getCurrentEvtPosY());
	}
	
	/**
	 * Constructs a picking ray from the components viewing camera position
	 * through the specified screen coordinates.
	 * The viewing camera of the object may not be null!
	 * <p><b>NOTE</b>: the openGL context has to be valid at the time of calling this method.
	 * 
	 * @param applet the applet
	 * @param component the component
	 * @param screenX the screen x
	 * @param screenY the screen y
	 * 
	 * @return the pick ray
	 */
	public static Ray getCameraPickRay(PApplet applet, IMTComponent3D component, float screenX, float screenY ){
		return Tools3D.getCameraPickRay(applet, component.getViewingCamera(), screenX, screenY);
	}
	
	/**
	 * Constructs a picking ray from the components viewing camera position
	 * through the specified screen coordinates.
	 * <p><b>NOTE</b>: the openGL context has to be valid at the time of calling this method.
	 * 
	 * @param applet the applet
	 * @param screenX the screen x
	 * @param screenY the screen y
	 * @param camera the camera
	 * 
	 * @return the pick ray
	 */
	public static Ray getCameraPickRay(PApplet applet, Icamera camera, float screenX, float screenY ){
		Vector3D rayStartPoint 		= camera.getPosition();
		Vector3D newPointInRayDir 	=  Tools3D.unprojectScreenCoords(applet, camera, screenX, screenY);
		return new Ray(rayStartPoint, newPointInRayDir);
	}
	
	
	
	/**
	 * Projects the given point to screenspace.
	 * <br>Shows where on the screen the point in 3d-Space will appear according
	 * to the current viewport, model and projection matrices.
	 * <p><b>NOTE</b>: the openGL context has to be valid at the time of calling this method.
	 * 
	 * @param gl the gl
	 * @param glu the glu
	 * @param point the point to project to the screen
	 * 
	 * @return the vector3 d
	 */
	public static Vector3D projectGL(GL gl, GLU glu, Vector3D point){
		return projectGL(gl, glu, point, null);
	}
	
	/**
	 * Projects the given point to screenspace.
	 * <br>Shows where on the screen the point in 3d-Space will appear according
	 * to the current viewport, model and projection matrices.
	 * <br><strong>Note</strong>: this method has to be called between a call to <code>processingApplet.beginGL()</code>
	 * and <code>processingApplet.endGL()</code>
	 * <p><b>NOTE</b>: the openGL context has to be valid at the time of calling this method.
	 * 
	 * @param gl the gl
	 * @param glu the glu
	 * @param point the point
	 * @param store the store - vector to store the result in or null to get a new vector
	 * 
	 * @return the vector3 d
	 */
	public static Vector3D projectGL(GL gl, GLU glu, Vector3D point, Vector3D store){
		if (store == null){
			store = new Vector3D();
		}
		
		model.clear();
		gl.glGetDoublev(GL.GL_MODELVIEW_MATRIX, model);
		
		proj.clear();
		gl.glGetDoublev(GL.GL_PROJECTION_MATRIX, proj);
		
		view.clear();
		gl.glGetIntegerv(GL.GL_VIEWPORT, view);
		float viewPortHeight = (float)view.get(3);
		
		winPos.clear();
		glu.gluProject(point.x, point.y, point.z, model, proj, view, winPos);
		
		winPos.rewind();
		float x = (float) winPos.get();
		float y = (float) winPos.get();
		y = viewPortHeight - y;			// Subtract The Current Y Coordinate From The Screen Height.
		
		store.setXYZ(x, y, 0);
		return store;
//		return new Vector3D(x, y, 0);
	}
	
	
	/**
	 * Projects the given 3D point to screenspace.
	 * <br>Shows where on the screen the point in 3d-Space will appear according
	 * to the supplied camera, viewport, and projection matrices.
	 * The modelview is temporarily changed to match the supplied camera matrix.
	 * 
	 * @param applet the applet
	 * @param cam the cam
	 * @param point the point
	 * 
	 * @return the vector3 d
	 */
	public static Vector3D project(PApplet applet, Icamera cam, Vector3D point){
		Vector3D ret;
		applet.pushMatrix();
		cam.update();
		ret = Tools3D.project(applet, point);	
		applet.popMatrix();
		return ret;
	}
	
	
	/**
	 * Projects the given point to screenspace. Uses the current modelview, and projection matrices - so update
	 * them accordingly before calling!
	 * <br>Shows where on the screen the point in 3d-Space will appear according
	 * to the current viewport, model and projection matrices.
	 * <p><b>NOTE</b>: if using openGL mode, the openGL context has to be valid at the time of calling this method.
	 * 
	 * @param applet the applet
	 * @param point the point
	 * 
	 * @return a new projected vector3d
	 */
	public static Vector3D project(PApplet applet, Vector3D point){
		switch (MT4jSettings.getInstance().getRendererMode()) {
		case MT4jSettings.OPENGL_MODE:
			try{ 
				PGraphicsOpenGL pgl = ((PGraphicsOpenGL)applet.g); 
				GL gl 	= pgl.beginGL();  
				GLU glu = pgl.glu;
				Vector3D returnVect = projectGL(gl, glu, point);
				pgl.endGL();
				return returnVect;
			}catch(Exception e){
				e.printStackTrace();
				//System.out.println("Use method getWorldForScreenCoords only when drawing with openGL! And dont put negative screen values in!");
			}
			break;
		case MT4jSettings.P3D_MODE:
//			/*!
			try{
				float x = applet.screenX(point.x, point.y, point.z);
				float y = applet.screenY(point.x, point.y, point.z);
				float z = applet.screenZ(point.x, point.y, point.z);
				return new Vector3D(x,y,z);
			}catch(Exception e){
				e.printStackTrace();
			}
			break;
//			*/
		default:
			return new Vector3D(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
		} 
		return new Vector3D(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
	}
	
	/**
	 * Start drawing in on TOP of everything drawn previously,
	 * also resets the camera, so that something drawn at 0,0,0
	 * will be drawn at the top left corner of the screen regardless
	 * of the camera used
	 * 
	 * You could say this allows you to draw directly on the screen, and
	 * on top of everything else. (at the near clipping plane?)
	 * 
	 * NOTE: you have to CALL endDrawOnTopStayOnScreen() if finished!
	 * 
	 * @param pa the pa
	 */
	public static void beginDrawOnTopStayOnScreen(PApplet pa){
		switch (MT4jSettings.getInstance().getRendererMode()) {
		case MT4jSettings.OPENGL_MODE:
			GL gl = ((PGraphicsOpenGL)pa.g).gl; 
			gl.glDepthFunc(javax.media.opengl.GL.GL_ALWAYS); //turn off Z buffering
			//reset to the default camera
			pa.camera(); 
			break;
		case MT4jSettings.P3D_MODE:
			for(int i=0;i<((PGraphics3D)pa.g).zbuffer.length;i++){
			  ((PGraphics3D)pa.g).zbuffer[i]=Float.MAX_VALUE;
			}
			pa.camera();
			break;
		default:
			break;
		}
	}
	
	/**
	 * Stop drawing in 2D after calling begin2D().
	 * 
	 * @param pa the pa
	 * @param camera the camera
	 */
	public static void endDrawOnTopStayOnScreen(PApplet pa, Icamera camera){
		switch (MT4jSettings.getInstance().getRendererMode()) {
		case MT4jSettings.OPENGL_MODE:
			GL gl = ((PGraphicsOpenGL)pa.g).gl; 
			gl.glDepthFunc(GL.GL_LEQUAL); //This is used by standart processing..
			//Change camera back to current 3d camera
			camera.update();
			break;
		case MT4jSettings.P3D_MODE:
			camera.update();
			break;
		default:
			break;
		}
	}
	
	/**
	 * Allows to draw ontop of everything, regardless of the values in the z-buffer.
	 * To stop doing that, call <code>endDrawOnTop(PApplet pa)</code> .
	 * 
	 * @param g the g
	 */
	public static void disableDepthBuffer(PGraphics g){ 
		switch (MT4jSettings.getInstance().getRendererMode()) {
		case MT4jSettings.OPENGL_MODE:
//			GL gl = ((PGraphicsOpenGL)pa.g).gl;
			GL gl = ((PGraphicsOpenGL)g).gl;
			gl.glPushAttrib(GL.GL_DEPTH_BUFFER_BIT);//FIXME TEST
			gl.glDepthFunc(javax.media.opengl.GL.GL_ALWAYS); //turn off Z buffering
			break;
		case MT4jSettings.P3D_MODE:
//			/*
//			for(int i=0;i<((PGraphics3D)pa.g).zbuffer.length;i++){
//			  ((PGraphics3D)pa.g).zbuffer[i]=Float.MAX_VALUE;
//			}
			for(int i=0;i<((PGraphics3D)g).zbuffer.length;i++){
				  ((PGraphics3D)g).zbuffer[i]=Float.MAX_VALUE;
				}
//			*/ 
			break;
		default:
			break;
		}
	}
	
	/**
	 * End draw on top.
	 * 
	 * @param g the g
	 */
	public static void restoreDepthBuffer(PGraphics g){ 
		switch (MT4jSettings.getInstance().getRendererMode()) {
		case MT4jSettings.OPENGL_MODE:
			GL gl = ((PGraphicsOpenGL)g).gl;
//			gl.glDepthFunc(GL.GL_LEQUAL); //This is used by standart processing..
			//FIXME TEST
			gl.glPopAttrib(); 
			break;
		case MT4jSettings.P3D_MODE:
			break;
		default:
			break;
		}
	}
	
	


	//////////////////////////////////////////////////////////
	//  OPENGL STUFF										//
	//////////////////////////////////////////////////////////
	
	
	
	/**
	 * Prints some available openGL extensions to the console.
	 * <p><b>NOTE</b>: the openGL context has to be valid at the time of calling this method.
	 * 
	 * @param pa the pa
	 */
	public static void printGLExtensions(PApplet pa){
		if (!MT4jSettings.getInstance().isOpenGlMode())
			return;
		GL gl =((PGraphicsOpenGL)pa.g).beginGL();
		String ext = gl.glGetString(GL.GL_EXTENSIONS);
		StringTokenizer tok = new StringTokenizer( ext, " " );
		while (tok.hasMoreTokens()) {
			System.out.println(tok.nextToken());
		}
		 int[] redBits 		= new int[1];
         int[] greenBits 	= new int[1];
         int[] blueBits 	= new int[1];
         int[] alphaBits 	= new int[1];
         int[] stencilBits 	= new int[1];
         int[] depthBits 	= new int[1];
         gl.glGetIntegerv(GL.GL_RED_BITS, redBits,0);
         gl.glGetIntegerv(GL.GL_GREEN_BITS, greenBits,0);
         gl.glGetIntegerv(GL.GL_BLUE_BITS, blueBits,0);
         gl.glGetIntegerv(GL.GL_ALPHA_BITS, alphaBits,0);
         gl.glGetIntegerv(GL.GL_STENCIL_BITS, stencilBits,0);
         gl.glGetIntegerv(GL.GL_DEPTH_BITS, depthBits,0);
		System.out.println("Red bits: " + redBits[0]);
		System.out.println("Green bits: " + greenBits[0]);
		System.out.println("Blue bits: " + blueBits[0]);
		System.out.println("Alpha bits: " + blueBits[0]);
		System.out.println("Depth Buffer bits: " + depthBits[0]);
		System.out.println("Stencil Buffer bits: " + stencilBits[0]);
		((PGraphicsOpenGL)pa.g).endGL();
	}
	
		
		/**
		 * Check for gl error.
		 * 
		 * @param gl the gl
		 */
		public static int getGLError(GL gl){
			int error = gl.glGetError();
			if (error != GL.GL_NO_ERROR){
				System.out.println("GL Error: " + error);
			}else{
	//			System.out.println("No gl error.");
			}
			return error;
		}


		/**
		 * Gets the openGL context.
		 * <br>NOTE: If you want to invoke any opengl drawing commands (or other commands influencing or depending on the current modelview matrix)
		 * you have to call GL <code>Tools3D.beginGL(PApplet pa)</code> instead!
		 * <br>NOTE: the openGL context is only valid and current when the rendering thread is the current thread.
		 * <br>
		 * This only gets the opengl context if started in opengl mode using the opengl renderer.
		 * 
		 * @param pa the pa
		 * 
		 * @return the gL
		 */
		public static GL getGL(PApplet pa){
			return ((PGraphicsOpenGL)pa.g).gl;
		}
		
		
		public static GL getGL(PGraphics g){
			return ((PGraphicsOpenGL)g).gl;
		}

	
	/**
	 * Begin gl.
	 * 
	 * @param pa the pa
	 * @return the gL
	 */
	public static GL beginGL(PApplet pa){
		return ((PGraphicsOpenGL)pa.g).beginGL();
	}
	
	/**
	 * Begin gl.
	 *
	 * @param g the g
	 * @return the gL
	 */
	public static GL beginGL(PGraphics g){
		return ((PGraphicsOpenGL)g).beginGL();
	}

	
	/**
	 * End gl.
	 * 
	 * @param pa the pa
	 */
	public static void endGL(PApplet pa){
		((PGraphicsOpenGL)pa.g).endGL();
	}
	
	/**
	 * End gl.
	 *
	 * @param g the g
	 */
	public static void endGL(PGraphics g){
		((PGraphicsOpenGL)g).endGL();
	}



	/**
	 * Checks whether the given extension is supported by the current opengl context.
	 * <p><b>NOTE</b>: the openGL context has to be valid at the time of calling this method.
	 * 
	 * @param pa the pa
	 * @param extensionName the extension name
	 * 
	 * @return true, if checks if is gl extension supported
	 */
	public static boolean isGLExtensionSupported(PApplet pa, String extensionName){
		if (!MT4jSettings.getInstance().isOpenGlMode())
			return false;
		
		GL gl =((PGraphicsOpenGL)pa.g).gl;
		boolean avail = gl.isExtensionAvailable(extensionName);
		/*
		String ext = gl.glGetString(GL.GL_EXTENSIONS);
		*/
		return(avail);
	}
	
	/**
	 * Checks whether non power of two texture dimensions are natively supported
	 * by the gfx hardware.
	 * 
	 * @param pa the pa
	 * 
	 * @return true, if supports non power of two texture
	 */
	public static boolean supportsNonPowerOfTwoTexture(PApplet pa){
		boolean supports = false;
		if (	Tools3D.isGLExtensionSupported(pa, "GL_TEXTURE_RECTANGLE_ARB")
			|| 	Tools3D.isGLExtensionSupported(pa, "GL_ARB_texture_non_power_of_two")
			|| 	Tools3D.isGLExtensionSupported(pa, "GL_ARB_texture_rectangle")
			|| 	Tools3D.isGLExtensionSupported(pa, "GL_NV_texture_rectangle")
			|| 	Tools3D.isGLExtensionSupported(pa, "GL_TEXTURE_RECTANGLE_EXT")
			|| 	Tools3D.isGLExtensionSupported(pa, "GL_EXT_texture_rectangle")
		){
			supports = true;
		}
			return supports;
	}


	/**
	 * Sets the opengl vertical syncing on or off.
	 * 
	 * @param pa the pa
	 * @param on the on
	 */
	public static void setVSyncing(PApplet pa, boolean on){
		if (MT4jSettings.getInstance().getRendererMode() == MT4jSettings.OPENGL_MODE){
			GL gl = getGL(pa); 
			if (on){
				gl.setSwapInterval(1);
			}else{
				gl.setSwapInterval(0);
			}
		}
	}


	public static void setLineSmoothEnabled(GL gl, boolean enable){
	//    	/*
	    	//DO this if we use multisampling and enable line_smooth from the beginning 
	    	//and use multisampling -> we turn off multisampling then before using line_smooth for best restult
	    	if (enable){
	    		if (MT4jSettings.getInstance().isMultiSampling()){
					gl.glDisable(GL.GL_MULTISAMPLE);
				}
	    		//TODO Eventually even dont do that since enabled form the beginning!
	    		gl.glEnable(GL.GL_LINE_SMOOTH); 
	    	}else{
	    		if (MT4jSettings.getInstance().isMultiSampling()){
					gl.glEnable(GL.GL_MULTISAMPLE);
				}
	//    		gl.glDisable(GL.GL_LINE_SMOOTH); //Actually never disable line smooth
	    	}
	//    	*/
	    	
	    	//DO nothing if we use Multisampling but disable line_smooth from the beginning
	    	// -> do all anti aliasing only through multisampling!
	    	//
	    	/*
	    	if (enable){
	    		if (MT4jSettings.getInstance().isMultiSampling()){
					gl.glDisable(GL.GL_MULTISAMPLE);
				}
	    		//TODO Eventually even dont do that since enabled form the beginning!
	    		gl.glEnable(GL.GL_LINE_SMOOTH); 
	    	}else{
	    		if (MT4jSettings.getInstance().isMultiSampling()){
					gl.glEnable(GL.GL_MULTISAMPLE);
				}
	//    		gl.glDisable(GL.GL_LINE_SMOOTH); //Actually never disable line smooth
	    	}
	    	*/
	    }


	/**
	 * Gen stencil display list gradient.
	 * <p><b>NOTE</b>: the openGL context has to be valid at the time of calling this method.
	 * 
	 * @param pa the pa
	 * @param vertBuff the vert buff
	 * @param tbuff the tbuff
	 * @param colorBuff the color buff
	 * @param strokeColBuff the stroke col buff
	 * @param indexBuff the index buff
	 * @param drawSmooth the draw smooth
	 * @param strokeWeight the stroke weight
	 * @param vertexArr the vertex arr
	 * @param outLines the out lines
	 * @param x1R the x1 r
	 * @param x1G the x1 g
	 * @param x1B the x1 b
	 * @param x1A the x1 a
	 * @param x2R the x2 r
	 * @param x2G the x2 g
	 * @param x2B the x2 b
	 * @param x2A the x2 a
	 * @param x3R the x3 r
	 * @param x3G the x3 g
	 * @param x3B the x3 b
	 * @param x3A the x3 a
	 * @param x4R the x4 r
	 * @param x4G the x4 g
	 * @param x4B the x4 b
	 * @param x4A the x4 a
	 * @param useGradient the use gradient
	 * 
	 * @return the int[]
	 */
	public static int[] genStencilDisplayListGradient(PApplet pa, FloatBuffer vertBuff, FloatBuffer tbuff, 
			FloatBuffer colorBuff, FloatBuffer strokeColBuff, IntBuffer indexBuff, 
			boolean drawSmooth, float strokeWeight, Vertex[] vertexArr, List<Vertex[]> outLines,
			float x1R, float x1G, float x1B, float x1A, float x2R, float x2G, float x2B, float x2A,
			float x3R, float x3G, float x3B, float x3A, float x4R, float x4G, float x4B, float x4A,
			boolean useGradient
		)
	{
		GL gl=((PGraphicsOpenGL)pa.g).beginGL();
		
		/*
		//Unbind any VBOs first
		gl.glBindBufferARB(GL.GL_ARRAY_BUFFER_ARB, 0);
		gl.glBindBufferARB(GL.GL_ELEMENT_ARRAY_BUFFER_ARB, 0);
		*/
		
		//Generate new list IDs
		int[] returnVal = new int[2];
		int listIDFill = gl.glGenLists(1);
		if (listIDFill == 0){
			System.err.println("Failed to create display list");
			returnVal[0] = -1;
			returnVal[1] = -1;
			return returnVal;
		}
		int listIDOutline = gl.glGenLists(1);
		if (listIDOutline == 0){
			System.err.println("Failed to create display list");
			returnVal[0] = -1;
			returnVal[1] = -1;
			return returnVal;
		}
		
	    float[] minMax = ToolsGeometry.getMinXYMaxXY(vertexArr);
	    float minX = minMax[0]-10;
	    float minY = minMax[1]-10;
	    float maxX = minMax[2]+10;
	    float maxY = minMax[3]+10;
	    
	    gl.glColor4d (0.0, 0.0, 0.0, 1.0);
	    
	    gl.glEnableClientState(GL.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL.GL_COLOR_ARRAY);
		
		gl.glVertexPointer(3, GL.GL_FLOAT, 0, vertBuff);
		gl.glColorPointer(4, GL.GL_FLOAT, 0, colorBuff);
		
		//Using the strokecolor buffer strokecolor AND fill!
		
		//Generate List
		gl.glNewList(listIDFill, GL.GL_COMPILE);
			/////////////////////////////////////
			// Clear stencil and disable color //
		    // Draw with STENCIL			   //
		    /////////////////////////////////////
//			/*
			gl.glClearStencil(0);
			gl.glColorMask(false,false,false,false);
			gl.glDisable(GL.GL_BLEND);
			
			gl.glDepthMask(false);//remove..?
			
			//FIXME do this for non-zero rule?
//			gl.glColorMask(true,true,true,true);
//			gl.glEnable (GL.GL_BLEND);
//			gl.glDepthMask(true);//remove..?
			
			//Enable stencilbuffer
			gl.glEnable(GL.GL_STENCIL_TEST);
//		    gl.glStencilMask (0x01);
		    gl.glStencilOp(GL.GL_KEEP, GL.GL_KEEP, GL.GL_INVERT);
		    gl.glStencilFunc (GL.GL_ALWAYS, 0, ~0);
		    
		    //Stecilfunc bestimmt ob in den stencil geschrieben wird oder nicht
		    //1.param: die vergleichsart der werte, 
		    //2.param: reference value, wird bei op reingeschrieben bei replace(?)
		    //3.prama: mask
		    //ref is & anded with mask and the result with the value in the stencil buffer
		    //mask is & with ref, mask is & stencil => vergleich
//		    gl.glStencilFunc(GL.GL_ALWAYS, 0x1, 0x1);
//		    gl.glStencilOp(GL.GL_KEEP, GL.GL_INVERT, GL.GL_INVERT);
		    
		    //TODO notice, "stencilOP" zum wert in stencilbuffer reinschreiben
		    //"stencilfunc" vergleicht framebuffer mit stencilbuffer und macht stencilOP wenn bedingung stimmt
		    
		    gl.glColor4d (colorBuff.get(0), colorBuff.get(1), colorBuff.get(2), colorBuff.get(3));
		    
			//DRAW //FIXME why does this not work?
			if (indexBuff == null){
				gl.glDrawArrays(GL.GL_TRIANGLE_FAN, 0, vertBuff.capacity()/3);
			}else{
				gl.glDrawElements(GL.GL_TRIANGLE_FAN, indexBuff.capacity(), GL.GL_UNSIGNED_INT, indexBuff);
			}
			
//		    gl.glBegin (GL.GL_TRIANGLE_FAN);
//		    for (int i = 0; i < vertexArr.length; i++) {
//				Vertex vertex = vertexArr[i];
//				gl.glVertex3f (vertex.getX(), vertex.getY(),  vertex.getZ());
//			}
//	    	gl.glEnd();
		    
//		    gl.glDrawArrays(GL.GL_TRIANGLE_FAN, 0, vertBuff.capacity()/3); 
//			*/
			//////////////////////////////////////
			gl.glDepthMask(true);
			
		    gl.glEnable (GL.GL_BLEND);
		    gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
			//////////////////////
			// Draw fill		//
		    ////////////////////// 
//		    /*
			gl.glColorMask(true, true, true, true);
			gl.glEnable (GL.GL_BLEND);
			
		    gl.glStencilOp (GL.GL_ZERO, GL.GL_ZERO, GL.GL_ZERO); //org
		    gl.glStencilFunc(GL.GL_EQUAL, 0x01, 0x01);
			
//		    gl.glStencilOp (GL.GL_KEEP, GL.GL_REPLACE, GL.GL_ZERO);
//		    gl.glStencilFunc(GL.GL_EQUAL, 0x01, 0x01);
		    
		    if (useGradient){
			    gl.glBegin (GL.GL_QUADS);
				    gl.glColor4f(x1R, x1G, x1B, x1A);
				    gl.glVertex3d (minX, minY, 0.0);
				    gl.glColor4f(x2R, x2G, x2B, x2A);
				    gl.glVertex3d (maxX, minY, 0.0); 
				    gl.glColor4f(x3R, x3G, x3B, x3A);
				    gl.glVertex3d (maxX, maxY, 0.0); 
				    gl.glColor4f(x4R, x4G, x4B, x4A);
				    gl.glVertex3d (minX, maxY, 0.0); 
			    gl.glEnd ();
		    }else{
			    gl.glBegin (GL.GL_QUADS);
				    gl.glVertex3d (minX, minY, 0.0); 
				    gl.glVertex3d (maxX, minY, 0.0); 
				    gl.glVertex3d (maxX, maxY, 0.0); 
				    gl.glVertex3d (minX, maxY, 0.0); 
			    gl.glEnd ();
		    }
//		    */
		    ////////////////////////////////////
//		    gl.glDepthMask(true); //Disabled to avoid too many state switches, 
		    gl.glDisable (GL.GL_STENCIL_TEST);	 //Disabled to avoid too many state switches
		gl.glEndList();
		returnVal[0] = listIDFill;
		    
		//////////////////////////////
		// Draw aliased outline		//
		//////////////////////////////
		gl.glColorPointer(4, GL.GL_FLOAT, 0, strokeColBuff);
		
		gl.glNewList(listIDOutline, GL.GL_COMPILE);
//		  	gl.glEnable(GL.GL_STENCIL_TEST); 
		  	
		  	
//			gl.glColorMask(true, true, true, true);
//			gl.glDepthMask(false); //FIXME enable? disable?
		  	
//		    // Draw aliased off-pixels to real
//		    gl.glEnable (GL.GL_BLEND);
//		    gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
			
//		    /*
//		    gl.glStencilOp (GL.GL_KEEP, GL.GL_KEEP, GL.GL_KEEP);
//		    gl.glStencilFunc (GL.GL_EQUAL, 0x00, 0x01); //THIS IS THE ORIGINAL!
		   
//		  	gl.glStencilOp (GL.GL_KEEP, GL.GL_KEEP, GL.GL_KEEP);
//		    gl.glStencilFunc (GL.GL_EQUAL, 0x00, ~1); 
		    
//		    gl.glEnable(GL.GL_LINE_SMOOTH);
	    	//FIXME TEST
			Tools3D.setLineSmoothEnabled(gl, true);
			
		    gl.glLineWidth(strokeWeight);
		    
		    //DRAW 
//			gl.glDrawElements(GL.GL_LINE_STRIP, indexBuff.capacity(), GL.GL_UNSIGNED_INT, indexBuff);
//			gl.glDrawArrays(GL.GL_LINE_STRIP, 0, vertexArr.length);
		    
		    /////TEST/// //TODO make vertex pointer arrays?
		    gl.glColor4d (strokeColBuff.get(0), strokeColBuff.get(1), strokeColBuff.get(2), strokeColBuff.get(3));
		    for (Vertex[] outline : outLines){
				 gl.glBegin (GL.GL_LINE_STRIP);
				 	for (Vertex vertex : outline){
				 		gl.glVertex3f (vertex.getX(), vertex.getY(), vertex.getZ());
				 	}
			    gl.glEnd();
			}
			////
//			gl.glDisable (GL.GL_LINE_SMOOTH);
	    	//FIXME TEST
			Tools3D.setLineSmoothEnabled(gl, false);
			//////////////////////////////////
//		*/
//			gl.glDisable (GL.GL_STENCIL_TEST);	
			
//		    gl.glDepthMask(true);
		gl.glEndList();
		
		returnVal[1] = listIDOutline;
		
		//Disable client states
		gl.glDisableClientState(GL.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL.GL_COLOR_ARRAY);
		
		((PGraphicsOpenGL)pa.g).endGL();
		////////////////
		
		return returnVal;
	}
	
	/**
	 * Generate stencil display list.
	 * 
	 * @param pa the pa
	 * @param vertBuff the vert buff
	 * @param tbuff the tbuff
	 * @param colorBuff the color buff
	 * @param strokeColBuff the stroke col buff
	 * @param indexBuff the index buff
	 * @param drawSmooth the draw smooth
	 * @param strokeWeight the stroke weight
	 * @param vertexArr the vertex arr
	 * @param outLines the out lines
	 * 
	 * @return the int[]
	 */
	public static int[] generateStencilDisplayList(PApplet pa, FloatBuffer vertBuff, FloatBuffer tbuff, 
								FloatBuffer colorBuff, FloatBuffer strokeColBuff, IntBuffer indexBuff, 
								boolean drawSmooth, float strokeWeight, Vertex[] vertexArr, List<Vertex[]> outLines)
	{
		return Tools3D.genStencilDisplayListGradient(pa, vertBuff, tbuff, colorBuff, strokeColBuff, indexBuff, drawSmooth, strokeWeight, vertexArr, outLines, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,  1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,  1.0f, 1.0f, 1.0f, 1.0f, false);
	}
	
	//////////////////////////////////////////////////////
	// Generate Display Lists and get their IDs			//
	//////////////////////////////////////////////////////
	/**
	 * Creates 2 displaylists for drawing static geometry very fast.
	 * Returns the IDs (names) of the display lists generated with the given info.
	 * 
	 * @param pa the pa
	 * @param geometryInfo the geometry info
	 * @param useTexture the use texture
	 * @param texture the texture
	 * @param styleInfo the style info
	 * 
	 * @return the int[]
	 * 
	 * Returns the IDs (names) of the display lists generated with the given info.
	 */
	public static int[] generateDisplayLists(PApplet pa, GeometryInfo geometryInfo, boolean useTexture, PImage texture, StyleInfo styleInfo){
		return generateDisplayLists(pa, styleInfo.getFillDrawMode(), geometryInfo, useTexture, texture, styleInfo.isDrawSmooth(), styleInfo.getStrokeWeight());
	}
	
	
	/**
	 * Returns the IDs (names) of the display lists generated with the given info.
	 * 
	 * @param pa the pa
	 * @param fillDrawMode the fill draw mode
	 * @param geometryInfo the geometry info
	 * @param useTexture the use texture
	 * @param texture the texture
	 * @param drawSmooth the draw smooth
	 * @param strokeWeight the stroke weight
	 * 
	 * @return int[2] array where [0] is the list of the fill
	 * and [1] the list of the outline drawing list
	 */
	public static int[] generateDisplayLists(PApplet pa, int fillDrawMode, GeometryInfo geometryInfo,
									boolean useTexture, PImage texture, boolean drawSmooth, float strokeWeight
	){
		FloatBuffer tbuff 			= geometryInfo.getTexBuff();
		FloatBuffer vertBuff 		= geometryInfo.getVertBuff();
		FloatBuffer colorBuff 		= geometryInfo.getColorBuff();
		FloatBuffer strokeColBuff 	= geometryInfo.getStrokeColBuff();
		IntBuffer indexBuff 		= geometryInfo.getIndexBuff(); //null if not indexed
		
		GL gl;
		gl =((PGraphicsOpenGL)pa.g).gl;
		
		//Generate new list IDs
		int[] returnVal = new int[2];
		int listIDFill = gl.glGenLists(1);
		if (listIDFill == 0){
			System.err.println("Failed to create fill display list");
			returnVal[0] = -1;
			returnVal[1] = -1;
			return returnVal;
		}
		int listIDOutline = gl.glGenLists(1);
		if (listIDOutline == 0){
			System.err.println("Failed to create stroke display list");
			returnVal[0] = -1;
			returnVal[1] = -1;
			return returnVal;
		}
		
		gl.glEnableClientState(GL.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL.GL_COLOR_ARRAY);
		
		gl.glVertexPointer(3, GL.GL_FLOAT, 0, vertBuff);
		gl.glColorPointer(4, GL.GL_FLOAT, 0, colorBuff);
		
		//Default target
		int textureTarget = GL.GL_TEXTURE_2D;
		
		/////// DO FILL LIST/////////////////////////////////
		
		/////////
		boolean textureDrawn = false;
		int usedTextureID = -1;
		if (useTexture
			&& texture != null 
			&& texture instanceof GLTexture) //Bad for performance?
		{
			GLTexture tex = (GLTexture)texture;
			textureTarget = tex.getTextureTarget();
			
			//tells opengl which texture to reference in following calls from now on!
			//the first parameter is eigher GL.GL_TEXTURE_2D or ..1D
			gl.glEnable(textureTarget);
			usedTextureID = tex.getTextureID();
			gl.glBindTexture(textureTarget, tex.getTextureID());
			
			gl.glEnableClientState(GL.GL_TEXTURE_COORD_ARRAY);
			gl.glTexCoordPointer(2, GL.GL_FLOAT, 0, tbuff);
			textureDrawn = true;
		}
		
		// Normals
		if (geometryInfo.isContainsNormals()){
			gl.glEnableClientState(GL.GL_NORMAL_ARRAY);
			gl.glNormalPointer(GL.GL_FLOAT, 0, geometryInfo.getNormalsBuff());
		}
		
		gl.glColorPointer(4, GL.GL_FLOAT, 0, colorBuff);
		
		// START recording display list and DRAW////////////////////
		gl.glNewList(listIDFill, GL.GL_COMPILE);
			if (textureDrawn){
				gl.glEnable(textureTarget); //muss texture in der liste gebinded werden? anscheinend JA!
				gl.glBindTexture(textureTarget, usedTextureID);
			}
			
			//DRAW with drawElements if geometry is indexed, else draw with drawArrays!
			if (geometryInfo.isIndexed()){
				gl.glDrawElements(fillDrawMode, indexBuff.capacity(), GL.GL_UNSIGNED_INT, indexBuff); //limit() oder capacity()??
			}else{
				gl.glDrawArrays(fillDrawMode, 0, vertBuff.capacity()/3);
			}
			
			if (textureDrawn){
				gl.glBindTexture(textureTarget, 0);
				gl.glDisable(textureTarget); 
			}
		gl.glEndList();
		//// STOP recording display list and DRAW////////////////////
		
		if (geometryInfo.isContainsNormals()){
			gl.glDisableClientState(GL.GL_NORMAL_ARRAY);
		}

		if (textureDrawn){
			gl.glDisableClientState(GL.GL_TEXTURE_COORD_ARRAY);
		}
		returnVal[0] = listIDFill;
		
		/////// DO OUTLINE LIST////////////////////////////
		gl.glColorPointer(4, GL.GL_FLOAT, 0, strokeColBuff);
		//Start recording display list
		gl.glNewList(listIDOutline, GL.GL_COMPILE);
		
//			if (drawSmooth)
//				gl.glEnable(GL.GL_LINE_SMOOTH);
			//FIXME TEST
			Tools3D.setLineSmoothEnabled(gl, true);
			
			if (strokeWeight > 0)
				gl.glLineWidth(strokeWeight);
			
			//DRAW
			if (geometryInfo.isIndexed()){
				gl.glDrawElements(GL.GL_LINE_STRIP, indexBuff.capacity(), GL.GL_UNSIGNED_INT, indexBuff); ////indices.limit()?
			}else{
				gl.glDrawArrays(GL.GL_LINE_STRIP, 0, vertBuff.capacity()/3);
			}
			
//			if (drawSmooth)
//				gl.glDisable(GL.GL_LINE_SMOOTH);
			//FIXME TEST
			Tools3D.setLineSmoothEnabled(gl, false);
			
		gl.glEndList();
		returnVal[1] = listIDOutline;
		////////////////////////////////////////////////////
		
		//Disable client states
		gl.glDisableClientState(GL.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL.GL_COLOR_ARRAY);
		return returnVal;
	}
	
	
	//TODO make only 1 function gendisplaylist mit boolean generate outline/fill
	/**
	 * Returns the ID (name) of the display list
	 * If you dont want to use a line stipple pattern, use '0' for the parameter.
	 * 
	 * @param pa the pa
	 * @param vertBuff the vert buff
	 * @param strokeColBuff the stroke col buff
	 * @param indexBuff the index buff
	 * @param drawSmooth the draw smooth
	 * @param strokeWeight the stroke weight
	 * @param lineStipple the line stipple
	 * 
	 * @return int id of outline drawing list
	 */
	public static int generateOutLineDisplayList(PApplet pa, FloatBuffer vertBuff, FloatBuffer strokeColBuff, IntBuffer indexBuff, 
												boolean drawSmooth, float strokeWeight, short lineStipple){
		GL gl;
		gl = beginGL(pa.g);
		
		//Generate new list IDs
		int returnVal = -1;
		int listIDOutline = gl.glGenLists(1);
		if (listIDOutline == 0){
			System.err.println("Failed to create display list");
			return returnVal;
		}
		gl.glEnableClientState(GL.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL.GL_COLOR_ARRAY);
		gl.glVertexPointer(3, GL.GL_FLOAT, 0, vertBuff);
		gl.glColorPointer(4, GL.GL_FLOAT, 0, strokeColBuff);
		
		//Start recording display list
		gl.glNewList(listIDOutline, GL.GL_COMPILE);
//			if (drawSmooth)
//				gl.glEnable(GL.GL_LINE_SMOOTH);
			//FIXME TEST for multisample
			Tools3D.setLineSmoothEnabled(gl, true);
		
			if (strokeWeight > 0)
				gl.glLineWidth(strokeWeight);
			if (lineStipple != 0){
				gl.glLineStipple(1, lineStipple);
				gl.glEnable(GL.GL_LINE_STIPPLE);
			}
			
			if (indexBuff == null){
				gl.glDrawArrays(GL.GL_LINE_STRIP, 0, vertBuff.capacity()/3);
			}else{
				gl.glDrawElements(GL.GL_LINE_STRIP, indexBuff.capacity(), GL.GL_UNSIGNED_INT, indexBuff); ////indices.limit()?
			}
			
			//RESET LINE STIPPLE
			if (lineStipple != 0)
				gl.glDisable(GL.GL_LINE_STIPPLE); 
			
//			if (drawSmooth)
//				gl.glDisable(GL.GL_LINE_SMOOTH);
			//FIXME TEST for multisample
			Tools3D.setLineSmoothEnabled(gl, false);
			
		gl.glEndList();
		returnVal = listIDOutline;
		
		//Disable client states
		gl.glDisableClientState(GL.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL.GL_COLOR_ARRAY);
		((PGraphicsOpenGL)pa.g).endGL();
		return returnVal;
	}
	
	/**
	 * Draws a filled 2d bezier shape in immediate mode with help of
	 * the stencil buffer to allow concave geometry.
	 * Beziervertices are allowerd in the vertex array.
	 * 
	 * @param pa the pa
	 * @param vertexArr the vertex arr
	 */
	public static void drawFilledBezierShape(PApplet pa, Vertex[] vertexArr){
		GL gl=((PGraphicsOpenGL)pa.g).beginGL();
		float[] minMax = ToolsGeometry.getMinXYMaxXY(vertexArr);
//		/*
		 // Draw to stencil
		    gl.glDisable (GL.GL_BLEND);
		    gl.glEnable (GL.GL_STENCIL_TEST);
		    gl.glStencilOp (GL.GL_KEEP, GL.GL_KEEP, GL.GL_INVERT);
		    gl.glStencilFunc (GL.GL_ALWAYS, 0, ~0);
		    gl.glColorMask (false, false, false, false);
//		*/
		    //Change beziervertices to normal vertices - THIS IS EXPENSIVE!
		    Vertex[] allVertsBezierResolved = ToolsGeometry.createVertexArrFromBezierArr(vertexArr, 15);
		           
		   //DRAW RAW FILL 
		   gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		   //gl.glBegin (GL.GL_LINE_STRIP);
		     gl.glBegin (GL.GL_TRIANGLE_FAN);
        for (Vertex vertex : allVertsBezierResolved) {
            gl.glVertex3f(vertex.x, vertex.y, vertex.z);
        }
			 gl.glEnd ();

			 //Draw aliased off-pixels to real
		    gl.glColorMask (true, true, true, true);
		    gl.glEnable (GL.GL_BLEND);
		    gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

//			/*		    
		    gl.glStencilFunc (GL.GL_EQUAL, 0x00, 0x01);
		    gl.glStencilOp (GL.GL_KEEP, GL.GL_KEEP, GL.GL_KEEP);
		    
		    //DRAW OUTLINE
		   gl.glEnable(GL.GL_LINE_SMOOTH);
		   gl.glLineWidth(1.0f);
		   gl.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
		    gl.glBegin (GL.GL_LINES);
        for (Vertex vertex : allVertsBezierResolved) {
            gl.glVertex3f(vertex.x, vertex.y, vertex.z);
        }
		    gl.glEnd ();
		    gl.glDisable (GL.GL_LINE_SMOOTH);
//		*/
//		/*
		    // Draw FILL
		    gl.glStencilFunc (GL.GL_EQUAL, 0x01, 0x01);
		    gl.glStencilOp (GL.GL_ZERO, GL.GL_ZERO, GL.GL_ZERO);
		   
		    gl.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
		    gl.glBegin (GL.GL_QUADS);
		      gl.glVertex3f (minMax[0], minMax[1], 0.0f); 
		      gl.glVertex3f (minMax[2], minMax[1], 0.0f); 
		      gl.glVertex3f (minMax[2], minMax[3], 0.0f); 
		      gl.glVertex3f (minMax[0],  minMax[3], 0.0f); 
		    gl.glEnd ();

		    gl.glDisable(GL.GL_STENCIL_TEST);
//		*/
		    ((PGraphicsOpenGL)pa.g).endGL();
	}
	
	
	/**
	 * Reverses an array.
	 * 
	 * @param b the b
	 * @return the vector3 d[]
	 */
	public static Vector3D[] reverse(Vector3D[] b) {
		   int left  = 0;          // index of leftmost element
		   int right = b.length-1; // index of rightmost element
		  
		   while (left < right) {
		      // exchange the left and right elements
			  Vector3D temp = b[left]; 
		      b[left]  = b[right]; 
		      b[right] = temp;
		     
		      // move the bounds toward the center
		      left++;
		      right--;
		   }
		   return b;
		}//endmethod reverse


	/**
	 * Checks whether the given image is of power of 2 dimensions.
	 * 
	 * @param image the image
	 * 
	 * @return true, if checks if is power of two dimension
	 */
	public static boolean isPowerOfTwoDimension(PImage image){
		return ToolsMath.isPowerOfTwo(image.width) && ToolsMath.isPowerOfTwo(image.height);
	}
	
	/**
     * For non power of two textures, the texture coordinates
     * have to be in the range from 0..texture_width instead of from 0.0 to 1.0.
     * <br>So we try to scale the texture coords to the width/height of the texture
     * 
     * @param texture the texture
     * @param verts the verts
     */
    public static void scaleTextureCoordsForRectModeFromNormalized(PImage texture, Vertex[] verts){
        for (Vertex vertex : verts) {
            if (vertex.getTexCoordU() <= 1.0f && vertex.getTexCoordU() >= 0.0f) {
                vertex.setTexCoordU(vertex.getTexCoordU() * texture.width);
            }
            if (vertex.getTexCoordV() <= 1.0f && vertex.getTexCoordV() >= 0.0f) {
                vertex.setTexCoordV(vertex.getTexCoordV() * texture.height);
            }
        }
    }
    
    /**
	 * projects a specific point on a plane with a specific depth
	 * @param gl
	 * @param point
	 * @param frustum
	 * @param z
	 * @return
	 */
	public static Vector3D projectPointToPlaneInPerspectiveMode(Vector3D point,IFrustum frustum,float z,MTApplication mtApp)
	{
		float heightOfPlaneAtZ = frustum.getHeightOfPlane(z);
		float widthOfPlaneAtZ = frustum.getWidthOfPlane(z);
		
		float heightOfPlaneAtPoint = frustum.getHeightOfPlane(point.z);
		float widthOfPlaneAtPoint = frustum.getWidthOfPlane(point.z);
		
		//float centerX = mtApp.width/2;
		//float centerY = mtApp.height/2;
		
		Vector3D ntl = frustum.getNearTopLeft();
		
		//subtract getWidthofNearPlane, because frustum is upside down
		float centerX = ntl.x - frustum.getWidthOfNearPlane() + frustum.getWidthOfNearPlane()/2f;
		float centerY = ntl.y + frustum.getHeightOfNearPlane()/2f;
		
		float percentWidth = (point.x - (centerX-(widthOfPlaneAtPoint/2.f)))/widthOfPlaneAtPoint;
		float percentHeight = (point.y - (centerY-(heightOfPlaneAtPoint/2.f)))/heightOfPlaneAtPoint;
		
		Vector3D projectedPoint = new Vector3D();
		projectedPoint.x = (centerX - (widthOfPlaneAtZ/2.f))+widthOfPlaneAtZ*percentWidth;
		projectedPoint.y = (centerY - (heightOfPlaneAtZ/2.f))+heightOfPlaneAtZ*percentHeight;
		projectedPoint.z = z;
		
		return projectedPoint;
	}
    

}
