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
package org.mt4j.util.opengl;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;

import org.mt4j.MTApplication;
import org.mt4j.util.logging.ILogger;
import org.mt4j.util.logging.MTLoggerFactory;
import org.mt4j.util.math.Tools3D;
import org.mt4j.util.math.ToolsBuffers;
import org.mt4j.util.math.ToolsMath;
import org.mt4j.util.opengl.GLTexture.EXPANSION_FILTER;
import org.mt4j.util.opengl.GLTexture.SHRINKAGE_FILTER;
import org.mt4j.util.opengl.GLTexture.TEXTURE_TARGET;
import org.mt4j.util.opengl.GLTexture.WRAP_MODE;

import processing.core.PApplet;
import processing.opengl.PGraphicsOpenGL;

/**
 * This class abstracts a opengl frame buffer object for easier usage.
 * This can mainly be used to draw to an offscreen buffer and use that buffer 
 * as a texture later.
 * 
 * @author Christopher Ruff
 */
public class GLFBO {
	/** The Constant logger. */
	private static final ILogger logger = MTLoggerFactory.getLogger(GLFBO.class.getName());
	static{
//		logger.setLevel(ILogger.ERROR);
//		logger.setLevel(ILogger.WARN);
//		logger.setLevel(ILogger.DEBUG);
		logger.setLevel(ILogger.INFO);
	}
	
	private GL gl;
	
//	private int[] fboID;
	private int fboID;

	private int depthRBID;

	private int width;
	private int height;
	
	private PApplet pa;
	
	private List<GLTexture> textures;
	
	private int viewportX;
	private int viewportY;
	private int viewportWidth;
	private int viewportHeight;
	
	private boolean stencilBufferAttached;

	private GLFboStack fboStack;
	
	
	/**
	 * Instantiates a new GL FBO.
	 * 
	 * @param pa the pa
	 * @param width the width
	 * @param height the height
	 */
	public GLFBO(PApplet pa, int width, int height) {
		this(pa, width, height, true);
	}
	
	
	/**
	 * Instantiates a new GL FBO.
	 * 
	 * @param pa the pa
	 * @param width the width
	 * @param height the height
	 * @param attachStencilBuffer the attach stencil buffer
	 */
	public GLFBO(PApplet pa, int width, int height, boolean attachStencilBuffer) {
		super();
		this.pa = pa;
		this.gl = ((PGraphicsOpenGL)pa.g).gl;
		
		this.stencilBufferAttached = attachStencilBuffer;
		
		this.fboID = 0;
		this.depthRBID = 0;
		
		this.viewportX 		= 0;
		this.viewportY 		= 0;
		this.viewportWidth 	= width;
		this.viewportHeight = height;
		
		this.width = width;
		this.height = height;

		this.textures = new ArrayList<GLTexture>();
		
		//FIXME FBO STACK TEST!!
		this.fboStack = GLFboStack.getInstance(); 
		
		this.initFBO();
	}
	
	
	private void initFBO(){
		IntBuffer buffer = ToolsBuffers.createIntBuffer(1);
		gl.glGenFramebuffersEXT(1, buffer);
		this.fboID = buffer.get(0);
		gl.glBindFramebufferEXT(GL.GL_FRAMEBUFFER_EXT, fboID);
		
		//Create depth buffer
		IntBuffer buffer2 = ToolsBuffers.createIntBuffer(1);
		gl.glGenRenderbuffersEXT(1, buffer2);
		this.depthRBID = buffer2.get(0);
		gl.glBindRenderbufferEXT(GL.GL_RENDERBUFFER_EXT, depthRBID);
		
		if (this.isStencilBufferAttached()){
			//THIS CREATES A FBO WITH A STENCIL BUFFER! HAS TO BE SUPPORTED ON THE PLATFORM!
			gl.glRenderbufferStorageEXT(GL.GL_RENDERBUFFER_EXT, GL.GL_DEPTH24_STENCIL8_EXT, this.width, this.height);
		}else{
			//Creates a fbo with a depth but without a stencil buffer
			gl.glRenderbufferStorageEXT(GL.GL_RENDERBUFFER_EXT, GL.GL_DEPTH_COMPONENT, this.width, this.height); //orginal	
		}
		
		//Attach depth buffer to FBO
		gl.glFramebufferRenderbufferEXT(GL.GL_FRAMEBUFFER_EXT, GL.GL_DEPTH_ATTACHMENT_EXT, GL.GL_RENDERBUFFER_EXT, depthRBID);
		
		if (this.isStencilBufferAttached()){
			//Attach stencil buffer to FBO - HAS TO BE SUPPORTED ON THE PLATFORM!
			gl.glFramebufferRenderbufferEXT(GL.GL_FRAMEBUFFER_EXT, GL.GL_STENCIL_ATTACHMENT_EXT, GL.GL_RENDERBUFFER_EXT, depthRBID);			
		}
		
		gl.glBindFramebufferEXT(GL.GL_FRAMEBUFFER_EXT, 0);
	}
	
	

	/**
	 * Attaches a gl texture object to the frame buffer object.
	 * The texture will contain everything drawn when the fbo is bound.
	 * 
	 * @return the gL texture
	 */
	public GLTexture addNewTexture(){
		return this.addNewTexture(false);
	}
	
	/**
	 * This creates a GLTexture object with the FBO dimensions and 
	 * attaches it to this frame buffer object.
	 * The texture can later be used to texture a component.
	 * 
	 * @return the gL texture
	 */
	public GLTexture addNewTexture(boolean useMipMap){
		this.bind();

		boolean isPowerOfTwoDimension = ToolsMath.isPowerOfTwo(this.width) && ToolsMath.isPowerOfTwo(this.height);
		
		GLTextureSettings texSettings = new GLTextureSettings();
		texSettings.wrappingHorizontal = WRAP_MODE.CLAMP_TO_EDGE;
		texSettings.wrappingVertical = WRAP_MODE.CLAMP_TO_EDGE;
		texSettings.shrinkFilter = SHRINKAGE_FILTER.BilinearNoMipMaps;
		texSettings.expansionFilter = EXPANSION_FILTER.Bilinear;
		
//		GLTextureParameters tp = new GLTextureParameters();
//		//Set texture FILTER MODE
////		tp.minFilter = GLTextureParameters.LINEAR_MIPMAP_LINEAR;
////		tp.minFilter = GLTextureParameters.LINEAR_MIPMAP_NEAREST;
//		tp.minFilter = GLTextureParameters.LINEAR;
////		tp.minFilter = GLTextureParameters.NEAREST;
////		if (useMipMap)
////			tp.minFilter = GLTextureParameters.LINEAR_MIPMAP_NEAREST; //Seems to not display the fbo texture when POT
////		else{
////			tp.minFilter = GLTextureParameters.LINEAR;			
////		}
//		tp.magFilter = GLTextureParameters.LINEAR;	
//		
//		//Set texture WRAP MODE
//		tp.wrap_s = GL.GL_CLAMP_TO_EDGE;
//		tp.wrap_t = GL.GL_CLAMP_TO_EDGE;
////		tp.wrap_s = GL.GL_CLAMP;
////		tp.wrap_t = GL.GL_CLAMP;
		
		//Set texture TARGET
		if (isPowerOfTwoDimension){
//			tp.target = GLTextureParameters.NORMAL;
			texSettings.target = TEXTURE_TARGET.TEXTURE_2D;
			logger.debug("Power of 2 FBO texture created");
		}else{
//			tp.target = GLTextureParameters.RECTANGULAR;	
			texSettings.target = TEXTURE_TARGET.RECTANGULAR;
			logger.debug("Rectangular FBO texture created");
		}
		
//		GLTexture tex = new GLTexture(pa, this.width, this.height, tp, true, 0);
		GLTexture tex = new GLTexture(this.pa, texSettings);
		tex.setupGLTexture(this.width, this.height);
		tex.width = this.width;
		tex.height = this.height; //To prevent init() call in loadGLTexture().. not even neccessary with fbo usage?
		
		gl.glBindTexture(tex.getTextureTarget(), tex.getTextureID());
		
		//Use extension to automatically generate mipmaps for the fbo textures 
		//TODO Is this always needed? supported? only working in power of two dimensions!?
//		if (useMipMap && isPowerOfTwoDimension) //only for target GL_TEXTURE_2D allowed
//			gl.glGenerateMipmapEXT(tex.getTextureTarget()); //FIXME seems to crash JVM after app close! //FIXME do this only after drawing finished to fbo texture

		//Attach texture to FBO
		gl.glFramebufferTexture2DEXT(
				GL.GL_FRAMEBUFFER_EXT, 
				GL.GL_COLOR_ATTACHMENT0_EXT,
				tex.getTextureTarget(), tex.getTextureID(), 0);

		gl.glBindTexture(tex.getTextureTarget(), 0);

		this.checkFBOComplete(gl, fboID);

		this.unBind();
		
		//Add to list
		this.textures.add(tex);
		return tex;
	}
	
	
	//TEXTURE HAS TO HAVE FBO DIMENSIONS TO WORK! THIS REPLACED THE OLD ATTACHED TEXTURE!
	public boolean add(GLTexture tex) {
		this.bind();
		gl.glBindTexture(tex.getTextureTarget(), tex.getTextureID());
		/*
		//F�r OHNE mipmapping
		gl.glTexParameteri(tex.getTextureTarget(),GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		gl.glTexParameteri(tex.getTextureTarget(),GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		*/
//		/*
		//F�r MIIPMAPPING
//		gl.glTexParameteri(tex.getTextureTarget(), GL.GL_GENERATE_MIPMAP, GL.GL_TRUE); // automatic mipmap
		gl.glTexParameterf(tex.getTextureTarget(), GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP_TO_EDGE);
		gl.glTexParameterf(tex.getTextureTarget(), GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP_TO_EDGE);
		gl.glTexParameteri(tex.getTextureTarget(), GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		gl.glTexParameteri(tex.getTextureTarget(), GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
		gl.glGenerateMipmapEXT(tex.getTextureTarget());
//		 */
		//Attach texture to FBO
		gl.glFramebufferTexture2DEXT(
				GL.GL_FRAMEBUFFER_EXT, 
				GL.GL_COLOR_ATTACHMENT0_EXT,
				tex.getTextureTarget(), tex.getTextureID(), 0);
		
		gl.glBindTexture(tex.getTextureTarget(), 0);
		
		this.checkFBOComplete(gl, fboID);
		this.unBind();
		return textures.add(tex);
	}
	
	
	public boolean contains(GLTexture arg0) {
		return textures.contains(arg0);
	}

	public boolean remove(GLTexture arg0) {
		return textures.remove(arg0);
	}

	/*
	 In this instance we are creating a normal RGBA image of the same width and height as the renderbuffer we created earlier; 
	 this is important as ALL attachments to a FBO have to be the same width and height. 
	 Note that we don�t upload any data, the space is just reserved by OpenGL so we can use it later.
	 */
	
	/**
	 * If clearColorBuffer is set to true this clears the colorbuffer with the specified color values.
	 * <br>If clearDepthBuffer is set to true this clears the depth buffer of the framebuffer object.
	 * <br>NOTE: It seems that a texture has to be attached to the FBO first, for this to work as expected.
	 * <br>NOTE: This method will bind and unbind the FBO! So use this method only outside of startRenderToTexture() or bind()
	 * @param clearColorBuffer the color to clear the color buffer with
	 * @param r the r
	 * @param g the g
	 * @param b the b
	 * @param a the a
	 * @param clearDepthBuffer clear the depth buffer
	 */
	public void clear(boolean clearColorBuffer, float r, float g, float b, float a, boolean clearDepthBuffer){
		//FIXME make it so we can specify 0..255 colors, and not openGL 0..1 !
		
		//GL gl = Tools3D.getGL(app); 
		this.bind();
		if (clearColorBuffer){
			gl.glClearColor(r, g, b, a);
			gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		}
		if (clearDepthBuffer){
			gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
		}
		this.unBind();
	}
	
	
	protected void bind(){
		gl.glBindFramebufferEXT(GL.GL_FRAMEBUFFER_EXT, fboID);
		//gl.glBindRenderbufferEXT(GL.GL_RENDERBUFFER_EXT, depthRBID);
	}
	
	/**
	 * Sets up this frame buffer object to render all following rendering commands 
	 * into the offscreen textures which are attached to the frame buffer object.
	 */
	public void startRenderToTexture(){
		//FIXME FBO STACK TEST
//		this.bind();
		this.fboStack.pushFBO();
		this.fboStack.useFBO(this);
		
		/*
		//TODO f�r mehrere texturen,
		 - check wieviele buffers (color attachments) m�glich
		 - f�r jede texture ein color attachment binden 
		  => gl.glFramebufferTexture2DEXT(
				GL.GL_FRAMEBUFFER_EXT, 
				GL.GL_COLOR_ATTACHMENT0_EXT, //GL.GL_COLOR_ATTACHMENT1_EXT, ..
				tex.getTextureTarget(), tex.getTextureID(), 0);
		 - glDrawBuffers aufrufen //und glReadBuffers
        setDrawBuffer(GL.GL_NONE); //wenn kein mutliple texture draw m�glich
        setReadBuffer(GL.GL_NONE);
		*/
		
		gl.glPushAttrib(GL.GL_VIEWPORT_BIT);
//		gl.glDrawBuffer(GL.GL_NONE);
//		gl.glViewport(0, 0, width, height);
//		gl.glViewport(-50,-50, pa.width+100, pa.height+100);
		
		gl.glViewport(this.viewportX,this.viewportY, this.viewportWidth, this.viewportHeight);
	}
	

	
	/**
	 * Stops this FBO from rendering in to the attached texture(s).
	 * <p>NOTE: To use the texture we rendered into, it might me necessary to bind the
	 * texture and then call gl.glGenerateMipmapEXT(texture.getTextureTarget());
	 * for mipmap generation. Else we might get a black texture! (not yet confirmed)
	 * 
	 */
	public void stopRenderToTexture(){
		gl.glPopAttrib();

		//FIXME FBO STACK TEST
//		this.unBind();
		this.fboStack.popFBO();
	}
	
	
	protected void unBind() {
		//gl.glBindRenderbufferEXT(GL.GL_RENDERBUFFER_EXT, 0);
		gl.glBindFramebufferEXT(GL.GL_FRAMEBUFFER_EXT, 0);
	}
	
	

	private void setReadBuffer(int attachVal) {
		gl.glReadBuffer(attachVal);
	}

	private void setDrawBuffer(int attachVal) {
		gl.glDrawBuffer(attachVal);
	}

	
	
	/**
	 * Destroys and deallocates this FBO.
	 */
	public void destroy() {
		if (fboID > 0) {
			final IntBuffer id = ToolsBuffers.createIntBuffer(1);
			id.put(fboID);
			id.rewind();
			gl.glDeleteFramebuffersEXT(id.limit(), id);
			fboID = 0;
		}

		if (depthRBID > 0) {
			final IntBuffer id = ToolsBuffers.createIntBuffer(1);
			id.put(depthRBID);
			id.rewind();
			gl.glDeleteRenderbuffersEXT(id.limit(), id);
			depthRBID = 0;
		}
		
		this.textures.clear();
	}
	
	@Override
	protected void finalize() throws Throwable {
		logger.debug("Finalizing - " + this);
		if (this.pa instanceof MTApplication) {
			MTApplication mtApp = (MTApplication) this.pa;
			mtApp.invokeLater(new Runnable() {
				public void run() {
					destroy();
				}
			});
		}else{
			//TODO use registerPre()?
			//is the object even valid after finalize() is called??
		}
		super.finalize();
	}

	public boolean isStencilBufferAttached() {
		return stencilBufferAttached;
	}

	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}
	
	public int getName(){
		return this.fboID;
	}

	//TODO for several render targets IMPLEMENT!
	/*
	public void setDrawBuffers(GLTexture[] drawTextures, int n){
		numDrawBuffersInUse = PApplet.min(n, drawTextures.length);

		colorDrawBuffers = new int[numDrawBuffersInUse];
		textureIDs = new int[numDrawBuffersInUse];
		textureTargets = new int[numDrawBuffersInUse];

		for (int i = 0; i < numDrawBuffersInUse; i++)
		{
			colorDrawBuffers[i] = GL.GL_COLOR_ATTACHMENT0_EXT + i;
			textureTargets[i] = drawTextures[i].getTextureTarget();
			textureIDs[i] = drawTextures[i].getTextureID();

			gl.glFramebufferTexture2DEXT(GL.GL_FRAMEBUFFER_EXT, colorDrawBuffers[i], textureTargets[i], textureIDs[i], 0);
		}

		checkFBO();

		gl.glDrawBuffers(numDrawBuffersInUse, IntBuffer.wrap(colorDrawBuffers));
	}
	 */
	
	//TODO for  stencil enabled fbo! IMPLEMENT!
	/*
	 // Allocating space for multisampled depth buffer
	 gl.glRenderbufferStorageMultisampleEXT(GL.GL_RENDERBUFFER_EXT, multisampleLevel, GL_DEPTH24_STENCIL8, width, height);

	 // Creating handle for multisampled FBO
	 glstate.pushFramebuffer();
	 glstate.setFramebuffer(multisampleFBO);

	 gl.glFramebufferRenderbufferEXT(GL.GL_FRAMEBUFFER_EXT, GL.GL_COLOR_ATTACHMENT0_EXT, GL.GL_RENDERBUFFER_EXT, colorBufferMulti[0]);
	 gl.glFramebufferRenderbufferEXT(GL.GL_FRAMEBUFFER_EXT, GL.GL_DEPTH_ATTACHMENT_EXT, GL.GL_RENDERBUFFER_EXT, depthStencilBuffer[0]);
	 gl.glFramebufferRenderbufferEXT(GL.GL_FRAMEBUFFER_EXT, GL.GL_STENCIL_ATTACHMENT_EXT, GL.GL_RENDERBUFFER_EXT, depthStencilBuffer[0]);
	 */

	public void checkFBOComplete(GL gl, int fboID) {
		final int framebuffer = gl.glCheckFramebufferStatusEXT(GL.GL_FRAMEBUFFER_EXT);
		switch (framebuffer) {
		case GL.GL_FRAMEBUFFER_COMPLETE_EXT:
			logger.debug("FRAMEBUFFER STATUS COMPLETE!");
			break;
		case GL.GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT_EXT:
			doError(", has caused a GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT_EXT exception", fboID);
			break;
		case GL.GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT_EXT:
			doError(", has caused a GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT_EXT exception", fboID);
			break;
		case GL.GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS_EXT:
			doError(", has caused a GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS_EXT exception", fboID);
			break;
		case GL.GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER_EXT:
			doError(", has caused a GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER_EXT exception", fboID);
			break;
		case GL.GL_FRAMEBUFFER_INCOMPLETE_FORMATS_EXT:
			doError(", has caused a GL_FRAMEBUFFER_INCOMPLETE_FORMATS_EXT exception", fboID);
			break;
		case GL.GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER_EXT:
			doError(", has caused a GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER_EXT exception", fboID);
			break;
		case GL.GL_FRAMEBUFFER_UNSUPPORTED_EXT:
			doError(", has caused a GL_FRAMEBUFFER_UNSUPPORTED_EXT exception", fboID);
			break;
		default:
			doError(", Unexpected reply from glCheckFramebufferStatusEXT: ", fboID);
			break;
		}
	}

	
	private void doError(String msg, int fboID){
//		throw new RuntimeException("FrameBuffer: " + fboID	+ msg);
		logger.error("FrameBuffer: " + fboID + msg);
	}
	


	public void setViewportX(int viewportX,int viewportY, int viewportWidth, int viewportHeight) {
		this.viewportX = viewportX;
		this.viewportY = viewportY;
		this.viewportWidth = viewportWidth;
		this.viewportHeight = viewportHeight;
	}
	
	public int[] getViewport(){
		return new int[]{
				this.viewportX,
				this.viewportY,
				this.viewportWidth,
				this.viewportHeight};
	}


	
	/**
	 * Checks if the FrameBufferObject is supported on this platform.
	 * 
	 * @param app the PApplet
	 * 
	 * @return true, if is supported
	 */
	public static boolean isSupported(PApplet app){
		return Tools3D.isGLExtensionSupported(app, "GL_EXT_framebuffer_object");
	}
	

	
	
}
