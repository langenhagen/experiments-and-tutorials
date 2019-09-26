/***********************************************************************
 * mt4j Copyright (c) 2008 - 2010 Christopher Ruff, Fraunhofer-Gesellschaft All rights reserved.
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

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import org.mt4j.MTApplication;
import org.mt4j.util.math.Tools3D;
import org.mt4j.util.math.ToolsMath;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.opengl.PGraphicsOpenGL;

import com.sun.opengl.util.BufferUtil;

/**
 * This class can only be used in combination with a OpenGL renderer.
 * It holds a texture which can be used by processing and OpenGL. It allows to load, configure and update the OpenGL texture object as well
 * as Processing's PImage superclass.
 * If the texture isnt neeeded anymore, the destroy() method has to be called.
 *
 * @author Christopher Ruff
 */
public class GLTexture extends PImage {
	
	public enum WRAP_MODE{
		REPEAT(GL.GL_REPEAT),
		CLAMP(GL.GL_CLAMP),
		CLAMP_TO_EDGE(GL.GL_CLAMP_TO_EDGE),
		CLAMP_TO_BORDER(GL.GL_CLAMP_TO_BORDER);
		
		private int glConstant;
        private WRAP_MODE(int glConstant) {
            this.glConstant = glConstant;
        }
        public int getGLConstant(){
            return glConstant;
        }
	}
	
	public enum SHRINKAGE_FILTER{
		/**
         * Nearest neighbor interpolation is the fastest and crudest filtering
         * method - it simply uses the color of the texel closest to the pixel
         * center for the pixel color. While fast, this results in aliasing and
         * shimmering during minification. (GL equivalent: GL_NEAREST)
         */
        NearestNeighborNoMipMaps(GL.GL_NEAREST, false),

        /**
         * In this method the four nearest texels to the pixel center are
         * sampled (at texture level 0), and their colors are combined by
         * weighted averages. Though smoother, without mipmaps it suffers the
         * same aliasing and shimmering problems as nearest
         * NearestNeighborNoMipMaps. (GL equivalent: GL_LINEAR)
         */
        BilinearNoMipMaps(GL.GL_LINEAR, false),

        /**
         * Same as NearestNeighborNoMipMaps except that instead of using samples
         * from texture level 0, the closest mipmap level is chosen based on
         * distance. This reduces the aliasing and shimmering significantly, but
         * does not help with blockiness. (GL equivalent: GL_NEAREST_MIPMAP_NEAREST)
         */
        NearestNeighborNearestMipMap(GL.GL_NEAREST_MIPMAP_NEAREST, true),

        
        /**
         * Same as BilinearNoMipMaps except that instead of using samples from
         * texture level 0, the closest mipmap level is chosen based on
         * distance. By using mipmapping we avoid the aliasing and shimmering
         * problems of BilinearNoMipMaps. (GL equivalent: GL_LINEAR_MIPMAP_NEAREST)
         */
        BilinearNearestMipMap(GL.GL_LINEAR_MIPMAP_NEAREST, true),

        /**
         * Similar to NearestNeighborNoMipMaps except that instead of using
         * samples from texture level 0, a sample is chosen from each of the
         * closest (by distance) two mipmap levels. A weighted average of these
         * two samples is returned. (GL equivalent: GL_NEAREST_MIPMAP_LINEAR)
         */
        NearestNeighborLinearMipMap(GL.GL_NEAREST_MIPMAP_LINEAR, true),

        /**
         * Trilinear filtering is a remedy to a common artifact seen in
         * mipmapped bilinearly filtered images: an abrupt and very noticeable
         * change in quality at boundaries where the renderer switches from one
         * mipmap level to the next. Trilinear filtering solves this by doing a
         * texture lookup and bilinear filtering on the two closest mipmap
         * levels (one higher and one lower quality), and then linearly
         * interpolating the results. This results in a smooth degradation of
         * texture quality as distance from the viewer increases, rather than a
         * series of sudden drops. Of course, closer than Level 0 there is only
         * one mipmap level available, and the algorithm reverts to bilinear
         * filtering (GL equivalent: GL_LINEAR_MIPMAP_LINEAR)
         */
        Trilinear(GL.GL_LINEAR_MIPMAP_LINEAR, true);

        private boolean usesMipMapLevels;

        private int glConstant;
        
        private SHRINKAGE_FILTER(int glConstant, boolean usesMipMapLevels) {
            this.usesMipMapLevels = usesMipMapLevels;
            this.glConstant = glConstant;
        }
        public int getGLConstant(){
            return glConstant;
        }
        public boolean usesMipMapLevels() {
            return usesMipMapLevels;
        }
	}
	
	public enum EXPANSION_FILTER{
		 /**
         * Nearest neighbor interpolation is the fastest and crudest filtering
         * mode - it simply uses the color of the texel closest to the pixel
         * center for the pixel color. While fast, this results in texture
         * 'blockiness' during magnification. (GL equivalent: GL_NEAREST)
         */
        NearestNeighbor(GL.GL_NEAREST),

        /**
         * In this mode the four nearest texels to the pixel center are sampled
         * (at the closest mipmap level), and their colors are combined by
         * weighted average according to distance. This removes the 'blockiness'
         * seen during magnification, as there is now a smooth gradient of color
         * change from one texel to the next, instead of an abrupt jump as the
         * pixel center crosses the texel boundary. (GL equivalent: GL_LINEAR)
         */
        Bilinear(GL.GL_LINEAR);
        
        private int glConstant;
        private EXPANSION_FILTER(int glConstant) {
            this.glConstant = glConstant;
        }
        public int getGLConstant(){
            return glConstant;
        }
	}
	
	public enum TEXTURE_TARGET{
		TEXTURE_1D(GL.GL_TEXTURE_1D),
		
		TEXTURE_2D(GL.GL_TEXTURE_2D),
		
		RECTANGULAR(GL.GL_TEXTURE_RECTANGLE_ARB);
		
		private int glConstant;
        private TEXTURE_TARGET(int glConstant) {
            this.glConstant = glConstant;
        }
        public int getGLConstant(){
            return glConstant;
        }
	}
	
	/*
	//FIXME obsolete?  how to deal with the PImage.format and the glFormat??
	public enum INTERNAL_FORMAT{
//		RGB(GL.GL_RGB),
		
		RGBA(GL.GL_RGBA)
		
//		,BGRA(GL.GL_BGRA)
		
		;
		
		private int glConstant;
        private INTERNAL_FORMAT(int glConstant) {
            this.glConstant = glConstant;
        }
        public int getGLConstant(){
            return glConstant;
        }
	}
	*/
	/*
	//FIXME use instead of hardcoded GL_UNSIGNED_BYTE?
	public enum GL_TYPE{
		INTEGER(GL.GL_INT),
		
		UNSIGNED_BYTE(GL.GL_UNSIGNED_BYTE);
		
		private int glConstant;
        private GL_TYPE(int glConstant) {
            this.glConstant = glConstant;
        }
        public int getGLConstant(){
            return glConstant;
        }
	}
	*/
	
	private PApplet app;
	
	private PGraphicsOpenGL pgl;
	
	private GL gl;
	
	protected boolean fboSupported;
	
	private boolean glTextureInitialized;
	
	protected int[] glTextureID = { 0 } ;
	
	private GLTextureSettings glTextureSettings;
	
	private int internalFormat;
	
	private boolean forcedRectMipMaps = false;
	
//	private int width;
//	private int height;
	
	//FIXME too many isPowerOfTwo checks (see space3d example texture creation)
	
	//TODO implement PBO texture upload
	//TODO initialize so that only the GLTexture object is initialized or nothing
	
	//TODO if shape useOpenGL/useProcessing changes check if PImage or OpenGL texture object is initialized and do if it isnt - on demand!
	
	//TODO need constructor that doesent init super so that when we create fbos at runtime we dont have toallocate huge pixel array!
	//TODO or mannualy set the fbo texture's width/height settings before 
	
	//TODO mark the constructors which may only be used in the OpenGL/MT4j/Processing Thread 

	/**
	 * Instantiates a new gL texture.
	 *
	 * @param parent the parent
	 */
	public GLTexture(PApplet parent){
		//		this(parent, 2, 2, new GLTextureSettings()); //ORG
		this(parent, new GLTextureSettings());
	}

	/**
	 * Instantiates a new gL texture.
	 *
	 * @param parent the parent
	 * @param settings the settings
	 */
	public GLTexture(PApplet parent, GLTextureSettings settings){
		super(0, 0, ARGB); 

    	this.glTextureInitialized = false;
//    	this.pImageUpToDate = false;
    	this.glTextureSettings = settings;

    	this.app = parent;
    	this.parent = parent;
    	pgl = (PGraphicsOpenGL)parent.g;
    	gl = pgl.gl;
	}

	/**
	 * Instantiates a empty texture of the specified dimensions, using default GLTextureSettings.
	 * Image data can be uploaded by calling setTexture(), setGLTexture //TODO setPImageTexture
	 *
	 * @param parent the parent
	 * @param width the width
	 * @param height the height
	 */
	public GLTexture(PApplet parent, int width, int height){
		this(parent, width, height, new GLTextureSettings());
	}

	//TODO maybe make same constructor but with boolean initialzeGLTexture = true/false!? 
	//-> see swingtexrenderer -> need to init with dimensions but not init gltex

    /**
	 * Instantiates a new gL texture.
	 *
	 * @param parent the parent
	 * @param width the width
	 * @param height the height
	 * @param settings the settings
	 */
	public GLTexture(PApplet parent, int width, int height, GLTextureSettings settings){
    	//    	this(parent, width, height, new GLTextureSettings());
    	super(width, height, ARGB); //FIXME original! 
//    	super(2,2,ARGB);

    	this.glTextureInitialized = false;
//    	this.pImageUpToDate = false;
    	this.glTextureSettings = settings;

        //FIXME test - set target to RECTANGULAR_ARB if loaded image is NPOT
        if ( !(ToolsMath.isPowerOfTwo(width) && ToolsMath.isPowerOfTwo(height)) ){
       	 	this.glTextureSettings.target = TEXTURE_TARGET.RECTANGULAR;
        }

    	this.app = parent;
    	this.parent = parent;
    	pgl = (PGraphicsOpenGL)parent.g;
    	gl = pgl.gl;

//    	 		setTextureParams(params);

//    	 		if (initGLTextureObject){
//    	 			initTexture(width, height);
//    	 		}

//    	if (app.isRenderThreadCurrent()){ //FIXME really allow to delay this? what if other methods are invoked afterwards which depend on this being called?
    		setupGLTexture(width, height);
//    	}else{
//    		app.invokeLater(new Runnable() {
//    			public void run() {
//    				setupGLTexture(MTTexture.this.width, MTTexture.this.height); //FIXME check for initTexture calls in mt4j and remove them!
//    			}
//    		});
//    	}
    }

    /**
     * Instantiates a new gL texture.
     *
     * @param parent the parent
     * @param fileName the file name
     */
    public GLTexture(PApplet parent, String fileName){
    	this(parent, fileName, new GLTextureSettings());
    }

    /**
     * Instantiates a new gL texture.
     *
     * @param parent the parent
     * @param fileName the file name
     * @param settings the settings
     */
    public GLTexture(PApplet parent, String fileName, GLTextureSettings settings){
    	super(2, 2, ARGB);  //will get correct dimensions later at setTexture(PImage img, GLTextureSettings settings) -> init(..) call 

    	this.glTextureInitialized = false;
//    	this.pImageUpToDate = false;

    	this.app = parent;
    	this.parent = parent;
    	pgl = (PGraphicsOpenGL)app.g;
    	gl = pgl.gl;
    	this.glTextureSettings = settings;
    	this.loadTexture(fileName, this.glTextureSettings);
    } 

	
    /**
     * Instantiates a new gL texture.
     *
     * @param parent the parent
     * @param pImage the image
     */
    public GLTexture(PApplet parent, PImage pImage){
    	this(parent, pImage, new GLTextureSettings());
    }
    
    
    /**
     * Instantiates a new texture using the specified settings and image data.
     * 
     * <br><b>NOTE: </b>This will make this texture share the specified PImage's pixel
     * array. So changes to the original PImage may change this texture.
     * 
     * @param parent the parent
     * @param pImage the image
     * @param settings the settings
     */
    public GLTexture(PApplet parent, PImage pImage, GLTextureSettings settings){
    	this(parent, pImage.width, pImage.height, settings);
    	
    	if (pImage.pixels == null || pImage.pixels.length == 0){
    		pImage.loadPixels();
    	}
    	this.pixels = pImage.pixels; //Dont copy the pixels for performance
    	this.width 	= pImage.width;
    	this.height = pImage.height;
    	
    	//this.loadPixels(); //FIXME neccessary? if we assigned the pixel array it should be loaded already!
        updateGLTextureFromPImage(); //TODO invokelater if not gl thread
        updatePixels();
    } 
    
	
//    private void init(int width, int height){
//    	if (this.glTextureSettings == null){
//    		 this.init(width, height, new GLTextureSettings());
//    	}else{
//    		 this.init(width, height, this.glTextureSettings);
//    	}
//    }


    /**
     * Initializes the empty PImage AND the OpenGL texture with the given dimension.
     * This can be used to change the texture dimension and reload a different texture into it.
     *
     * @param width the width
     * @param height the height
     * @param texSettings the tex settings
     */
    private void init(int width, int height, GLTextureSettings texSettings){
    	//        super.init(1, 1, ARGB);
    	super.init(width, height, ARGB);

//    	pImageUpToDate = false;

    	this.glTextureSettings = texSettings;

    	boolean POT = (ToolsMath.isPowerOfTwo(width) && ToolsMath.isPowerOfTwo(height)) ;
    	if (POT){
    		this.glTextureSettings.target = TEXTURE_TARGET.TEXTURE_2D;
    	}else{
    		this.glTextureSettings.target = TEXTURE_TARGET.RECTANGULAR;
    	}

    	//FIXME TEST -> only init GL texture if in opengl thread!
    	if (app instanceof MTApplication && ((MTApplication)app).isRenderThreadCurrent()) {
			setupGLTexture(width, height);
		}
    }	

    
//	protected void apply(GLTextureSettings settings){ //TODO
//		if (this.glTextureSettings == null || !this.glTextureSettings.equals(settings)){
//			
//		}
//	}

    /**
     * Creates and sets up an empty OpenGL texture object with the specified dimensions and
     * this texture's GLTextureSettings.
     * <br><b>NOTE: </b>
     *
     * @param width the width
     * @param height the height
     */
    public void setupGLTexture(int width, int height){ //TODO make private/protected
    	if (this.glTextureID[0] != 0)
    		destroy();
    	
    	//FIXME if check done here, we can remove the check elsewhere?
    	boolean POT = (ToolsMath.isPowerOfTwo(width) && ToolsMath.isPowerOfTwo(height));
    	if (this.glTextureSettings.target != TEXTURE_TARGET.RECTANGULAR && !POT){
    		this.glTextureSettings.target = TEXTURE_TARGET.RECTANGULAR;
    	}
    	
    	//FIXME TEST gluBuild2DMimaps with NPOT TEXTURE -> stretches the images to POT -> we can use normal TEXTURE2D target then
    	if (this.glTextureSettings.target == TEXTURE_TARGET.RECTANGULAR && this.glTextureSettings.shrinkFilter.usesMipMapLevels()){
    		System.err.println("INFO: A non-power-of-two dimension texture should ideally not be used with Mip Map minification filter. -> Result can be blurred/streched." );
    		this.glTextureSettings.target = TEXTURE_TARGET.TEXTURE_2D;
    		this.forcedRectMipMaps = true;
    	}

    	//check if fbo and thus the glGenerateMipmapEXT(GL_TEXTURE_2D);
    	this.fboSupported = GLFBO.isSupported(app);

    	// Target (GL_TEXTURE1D, GL_TEXTURE2D, GL_RECTANGLE_ARB ..)
		int textureTarget = glTextureSettings.target.getGLConstant();

		//GL_REPEAT with a GL_RECTANGLE_ARB texture target are not supported! => use GL_CLAMP then.
		if (glTextureSettings.target == TEXTURE_TARGET.RECTANGULAR){
			//FIXME gluBuildmipmaps staucht NPOT texture auf pot zusammen?
			//BEi clamp komischer wasser fbo error
			if (glTextureSettings.wrappingHorizontal == WRAP_MODE.REPEAT){
				glTextureSettings.wrappingHorizontal = WRAP_MODE.CLAMP_TO_EDGE; //        		this.wrap_s = GL.GL_CLAMP;
			}
			if (glTextureSettings.wrappingVertical == WRAP_MODE.REPEAT){
				glTextureSettings.wrappingVertical = WRAP_MODE.CLAMP_TO_EDGE; //        		this.wrap_t = GL.GL_CLAMP;
			}
			
			//NPOT texture dont support mipmaps!
			if (glTextureSettings.shrinkFilter.usesMipMapLevels()){
				this.glTextureSettings.shrinkFilter = SHRINKAGE_FILTER.BilinearNoMipMaps;
			}
		}
		// Wrapping
		int wrap_s = glTextureSettings.wrappingHorizontal.getGLConstant();
		int wrap_t = glTextureSettings.wrappingVertical.getGLConstant();

		//Filtering
		int minFilter = glTextureSettings.shrinkFilter.getGLConstant();
		int magFilter = glTextureSettings.expansionFilter.getGLConstant();
		
		// Texture internal format
		switch (this.format) {
		case PConstants.RGB:
			this.internalFormat = GL.GL_RGB;
			break;
		case PConstants.ARGB:
			this.internalFormat = GL.GL_RGBA;
			break;
		default:
			this.internalFormat = GL.GL_RGBA;
			break;
		}

		//Create the texture object
		gl.glGenTextures(1, glTextureID, 0);
		//Bind the texture
		gl.glBindTexture(textureTarget, glTextureID[0]);
		//SET texture mag/min FILTER mode
		gl.glTexParameteri(textureTarget, GL.GL_TEXTURE_MIN_FILTER, minFilter);
		gl.glTexParameteri(textureTarget, GL.GL_TEXTURE_MAG_FILTER, magFilter);
		//Set texture wrapping mode
		gl.glTexParameteri(textureTarget, GL.GL_TEXTURE_WRAP_S, wrap_s);
		gl.glTexParameteri(textureTarget, GL.GL_TEXTURE_WRAP_T, wrap_t);

		switch (glTextureSettings.target) {
		case TEXTURE_1D:
			gl.glTexImage1D(textureTarget, 0, internalFormat, width, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, null); 
			break;
		default:
			gl.glTexImage2D(textureTarget, 0, internalFormat, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, null); //FIXME always use GL_RGBA as glformat??
//			gl.glTexImage2D(textureTarget, 0, internalFormat, width, height, 0, GL.GL_BGRA, GL.GL_UNSIGNED_BYTE, null);//ORIGINAL
			break;
		}
		gl.glBindTexture(textureTarget, 0); 
		this.glTextureInitialized = true;
	}

	
	
	/**
     * Loads the image from the specifed file and the specified settings.
     * Then this PImage AND an OpenGL texture object are set up with the image data.
     * Re-initializes this texture if the old dimensions dont match the new image.
     *
     * @param filename the filename
     * @param settings the settings
     */
    public void loadTexture(String filename, GLTextureSettings settings){ 
    	PImage img = app.loadImage(filename);
    	this.glTextureSettings = settings;
        if (!(ToolsMath.isPowerOfTwo(img.width) && ToolsMath.isPowerOfTwo(img.height)) ){
       	 	this.glTextureSettings.target = TEXTURE_TARGET.RECTANGULAR;
        }
    	this.loadTexture(img, this.glTextureSettings);
    }

    
    
    /**
     * Sets this texture to the data of the specified PImage.
     * Re-sets this texture's PImage data AND OpenGL texture object data.
     * <br><b>NOTE: </b>This will make this texture share the specified PImage's pixel
     * array. So changes to the original PImage may change this texture.
     * 
     * @param img the img
     * @param settings the settings
     */
    public void loadTexture(PImage img, GLTextureSettings settings) {
      img.loadPixels();

      this.glTextureSettings = settings;
      if (!(ToolsMath.isPowerOfTwo(img.width) && ToolsMath.isPowerOfTwo(img.height)) ){
     	 	this.glTextureSettings.target = TEXTURE_TARGET.RECTANGULAR;
      }
      
      if ((img.width != this.width) || (img.height != this.height) || glTextureID[0] == 0) {
    	  this.init(img.width, img.height, settings);
      }
//      PApplet.arrayCopy(img.pixels, pixels); //TODO use same pixel array, avoid copy?
      this.pixels = img.pixels;
      
      this.updateGLTextureFromPImage();
      this.updatePixels();
    }

    
    /**
     * Sets this texture to the data of the specified image.
     * Re-sets ONLY this texture's OpenGL texture object data! 
     * <br><b>NOTE: </b> This texture object should then only be rendered directly by OpenGL (not Processing)
     * <br>To also update the PImage's pixel data used by Processing, use <code>loadPImageTexture(...)</code> or
     * <code>updatePImageFromGLTexture()</code>
     *
     * @param img the new gL texture
     */
    public void loadGLTexture(PImage img) {
    	 if (!Tools3D.isPowerOfTwoDimension(img)){
       	  this.glTextureSettings.target = TEXTURE_TARGET.RECTANGULAR;
         }
    	 
      if ((img.width != width) || (img.height != height) ) {
        init(img.width, img.height, this.glTextureSettings);
      }
      this.updateGLTexture(img.pixels); 
    }
    
    
    /**
     * Sets this texture's PImage pixel data to the data of the specified PImage.
     * Re-sets only this texture's PImage data, not the OpenGL texture object! 
     * <br><b>NOTE: </b> This texture object should then only be rendered by Processing and not directly by OpenGL!
     * <br>To also update the OpenGL texture object, use <code>loadGLTexture(...)</code>or
     * <code>updateGLTextureFromPImage()</code>
     * 
     * @param img the new p image texture
     */
    public void loadPImageTexture(PImage img){
    	img.loadPixels();
    	
    	this.format = img.format;
        if ((img.width != width) || (img.height != height)){
//        	System.out.println("loadPImageTexture ..dimensions are different from former texture!");
            this.init(img.width, img.height, this.glTextureSettings); // original
//        	 super.init(img.width, img.height, img.format);
        }
//      PApplet.arrayCopy(img.pixels, pixels); //TODO use same pixel array, avoid copy?
        this.pixels = img.pixels;
        this.updatePixels();
    }
    
    

	/**
	 * Updates only the OpenGL texture object with the data from the specified image data array.
	 * <br><b>NOTE:</b> The data has to match this texture's width/height dimensions! If it doesen't - 
	 * call <code>texture.init(newWidth, newHeight)</code> first!
	 *
	 * @param intArray the int array
	 */
	public void updateGLTexture(int[] intArray){ 
		this.updateGLTexture(IntBuffer.wrap(intArray));
	}


	/**
	 * Updates only the OpenGL texture object with the data from the specified image data buffer.
	 * <br><b>NOTE:</b> The data has to match this texture's width/height dimensions! If it doesen't - 
	 * call <code>texture.init(newWidth, newHeight)</code> first!
	 * 
	 * @param buffer the buffer
	 */
	public void updateGLTexture(IntBuffer buffer){
		if (this.glTextureID[0] == 0 || !this.glTextureInitialized){
			setupGLTexture(this.width, this.height); 
			System.out.println("calling setupGLTexture()" + " in " + "updateGLTexture() since texture wasnt initialized!" );
		}      

		//      int glFormat = glTextureSettings.glType.getGLConstant();
		
		int glFormat 	= GL.GL_BGRA; 				//FIXME DONT HARDCODE!?
		int type 		= GL.GL_UNSIGNED_BYTE; 		//FIXME DONT HARDCODE!?

		int textureTarget = glTextureSettings.target.getGLConstant();
		// int internalFormat = glTextureSettings.textureInternalFormat.getGLConstant();
		
		//FIXME TEST gluBuild2DMimaps with NPOT TEXTURE -> stretches the images to POT -> we can use normal TEXTURE2D target then
    	if (this.glTextureSettings.target == TEXTURE_TARGET.RECTANGULAR && this.glTextureSettings.shrinkFilter.usesMipMapLevels()){
    		this.glTextureSettings.target = TEXTURE_TARGET.TEXTURE_2D;
    		this.forcedRectMipMaps = true;
    	}
		
		//NPOT texture targets dont support mipmaps!
		if (glTextureSettings.target == TEXTURE_TARGET.RECTANGULAR){
			if (glTextureSettings.shrinkFilter.usesMipMapLevels()){
				this.glTextureSettings.shrinkFilter = SHRINKAGE_FILTER.BilinearNoMipMaps;
			}
		}
		
		switch (this.format) {
		case PConstants.RGB:
			this.internalFormat = GL.GL_RGB;
			break;
		case PConstants.ARGB:
			this.internalFormat = GL.GL_RGBA;
			break;
		default:
			this.internalFormat = GL.GL_RGBA;
			break;
		}

		gl.glBindTexture(textureTarget, this.glTextureID[0]);

		switch (glTextureSettings.target) {
		case TEXTURE_1D:
			if (glFormat == GL.GL_BGRA){ 
				glFormat = GL.GL_RGBA;
			}
			gl.glTexSubImage1D(textureTarget, 0, 0, this.width, glFormat, type, buffer);
			break;
		case TEXTURE_2D:
		case RECTANGULAR:
		default:
			//MipMapping wont work with RECTANGLE_ARB TARGET !
			if (glTextureSettings.shrinkFilter.usesMipMapLevels() 
				&& this.glTextureSettings.target != TEXTURE_TARGET.RECTANGULAR
			){
				//deprectated in opengl 3.0 -will always create mipmaps automatically if lvl 0 changes
//				gl.glTexParameteri( textureTarget, GL.GL_GENERATE_MIPMAP, GL.GL_TRUE ); 
				if (this.forcedRectMipMaps){
					//Resizes NPOT textures to POT
					GLU glu = ((PGraphicsOpenGL)this.parent.g).glu;
					glu.gluBuild2DMipmaps(textureTarget, internalFormat, this.width, this.height, glFormat, type, buffer);
				}else{
					if (this.fboSupported){ //Naive check if glGenerateMipmapEXT command is supported
						gl.glTexSubImage2D(textureTarget, 0, 0, 0, this.width, this.height, glFormat, type, buffer);
						gl.glGenerateMipmapEXT(textureTarget);  //newer OpenGL 3.x method of creating mip maps //TODO problems on ATI? use gl.glEnable(textureTarget) first? 
					}else{
						//Old school software method, will resize a NPOT texture to a POT texture
						GLU glu = ((PGraphicsOpenGL)this.parent.g).glu;
						glu.gluBuild2DMipmaps(textureTarget, internalFormat, this.width, this.height, glFormat, type, buffer);
					}
				}
			}
			else{
				gl.glTexSubImage2D(textureTarget, 0, 0, 0, width, height, glFormat, type, buffer); //ORG
//				gl.glTexSubImage2D(textureTarget, 0, 0, 0, width, height, GL.GL_RGB, type, buffer);
			}
			break;
		}
		gl.glBindTexture(textureTarget, 0);
	}


	
    /**
     * Updates the OpenGL texture object with the data from this PImage.pixels pixel
     * array. This method should be called if the pixel data has changed and the change
     * has to be reflected in the OpenGL texture object (probably because direct OpenGL texture rendering is used)
     * <b>NOTE:</b>The PImage pixel data dimensions have to match the OpenGL texture dimension! If not, use loadTexture()
     */
    public void updateGLTextureFromPImage(){ 
    	updateGLTexture(this.pixels);
    }
    
	
    /**
     * Updates the PImage pixel data from the texture's OpenGL texture object.
     * This method should be called if the OpenGL texture was changed and the change
     * has to be reflected in the PImage used by Processing 
     * (probably because Processings rendering pipeling is used instead of direct OpenGL)
     *
     */
    public void updatePImageFromGLTexture(){
        IntBuffer buff = BufferUtil.newIntBuffer(this.width * this.height);
        int textureTarget = this.glTextureSettings.target.getGLConstant();
        gl.glBindTexture(textureTarget, this.glTextureID[0]);
        gl.glGetTexImage(textureTarget, 0, GL.GL_BGRA, GL.GL_UNSIGNED_BYTE, buff);
        gl.glBindTexture(textureTarget, 0);
        buff.get(pixels);
    }
    
    

	/**
     * Deletes the opengl texture object.
     */
    public void destroy(){
    	if (this.glTextureID[0] != 0){
	        gl.glDeleteTextures(1, this.glTextureID, 0);  
	        this.glTextureID[0] = 0;
    	}
    	
//    	releasePBO(); //FIXME implement
    }
    
    
	/**
	 * Sets the texture wrap mode.
	 *
	 * @param wrappingHorizontal the wrapping horizontal
	 * @param wrappingVertical the wrapping vertical
	 */
	public void setWrapMode(WRAP_MODE wrappingHorizontal, WRAP_MODE wrappingVertical){
		this.glTextureSettings.wrappingHorizontal = wrappingHorizontal;
		this.glTextureSettings.wrappingVertical = wrappingVertical;
		
		if (this.isGLTexObjectInitialized()){
			gl.glBindTexture(this.getTextureTarget(), this.getTextureID());
			gl.glTexParameteri(this.getTextureTarget(), GL.GL_TEXTURE_WRAP_S, this.glTextureSettings.wrappingHorizontal.getGLConstant());
			gl.glTexParameteri(this.getTextureTarget(), GL.GL_TEXTURE_WRAP_T, this.glTextureSettings.wrappingVertical.getGLConstant());
			gl.glBindTexture(this.getTextureTarget(), 0);
		}
	}
	
	public WRAP_MODE getWrappingHorizontal(){
		return this.glTextureSettings.wrappingHorizontal;
	}
	
	public WRAP_MODE getWrappingVertical(){
		return this.glTextureSettings.wrappingVertical;
	}
	
	/**
	 * Sets the texture filtes.
	 *
	 * @param minFilter the min filter
	 * @param magFilter the mag filter
	 */
	public void setFilter(SHRINKAGE_FILTER minFilter, EXPANSION_FILTER magFilter){
		if (this.forcedRectMipMaps){
			//Because current target is TEXTURE_2D although it was a NPOT texture which was rescaled using glubuild2dmipmaps
			//and if another filter is chosen that doesent use mip maps and an updating method is called it would choose a RECTANGULAR target = conflict
			System.err.println("INFO: Changing the texture filter for NPOT texture in combination with MipMapping isnt allowed atm.");
		}
		boolean usedMipMapPreviously = this.glTextureSettings.shrinkFilter.usesMipMapLevels();
		
		this.glTextureSettings.shrinkFilter = minFilter;
		this.glTextureSettings.expansionFilter = magFilter;
		
		if (this.isGLTexObjectInitialized()){
			gl.glBindTexture(this.getTextureTarget(), this.getTextureID());
			 gl.glTexParameteri(this.getTextureTarget(), GL.GL_TEXTURE_MIN_FILTER, this.glTextureSettings.shrinkFilter.getGLConstant());
			 gl.glTexParameteri(this.getTextureTarget(), GL.GL_TEXTURE_MAG_FILTER, this.glTextureSettings.expansionFilter.getGLConstant());
			gl.glBindTexture(this.getTextureTarget(), 0);
		}
		
		//FIXME pixels may be empty/not current - just create mipmaps with gl code ourselves!!
		if (!usedMipMapPreviously && this.glTextureSettings.shrinkFilter.usesMipMapLevels()){
			this.updateGLTexture(this.pixels); 
		}
	}
	
	public SHRINKAGE_FILTER getShrinkageFilter(){
		return this.glTextureSettings.shrinkFilter;
	}
	
	public EXPANSION_FILTER getExpansionFilter(){
		return this.glTextureSettings.expansionFilter;
	}
	
	/**
	 * Gets the OpenGL texture id.
	 *
	 * @return the texture id
	 */
	public int getTextureID(){
		return this.glTextureID[0];
	}
	
	public int getTextureTarget(){
		return this.glTextureSettings.target.getGLConstant();
	}
	
	public TEXTURE_TARGET getTextureTargetEnum(){
		return this.glTextureSettings.target;
	}
	
	public boolean isGLTexObjectInitialized(){
		return this.glTextureInitialized;
	}
	

/*
	private int[] toRGBA(int[] intArray, int arrayFormat) {
		int t = 0;
		int p = 0;
		int twidth = width;
		
		int[] tIntArray = new int[width * height];
		if (PGraphicsOpenGL.BIG_ENDIAN) {
			switch (arrayFormat) {
			case ALPHA:

				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						tIntArray[t++] = 0xFFFFFF00 | intArray[p++];
					}
					t += twidth - width;
				}
				break;
			case RGB:
				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						int pixel = intArray[p++];
						tIntArray[t++] = (pixel << 8) | 0xff;
					}
					t += twidth - width;
				}
				break;
			case ARGB:
				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						int pixel = intArray[p++];
						tIntArray[t++] = (pixel << 8) | ((pixel >>4) & 0xff);
					}
					t += twidth - width;
				}
				break;
			}
		} else {
			// LITTLE_ENDIAN
			// ARGB native, and RGBA opengl means ABGR on windows
			// for the most part just need to swap two components here
			// the sun.cpu.endian here might be "false", oddly enough..
			// (that's why just using an "else", rather than check for "little")
			switch (arrayFormat) {
			case ALPHA:
				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						tIntArray[t++] = (intArray[p++] <<4) | 0x00FFFFFF;
					}
					t += twidth - width;
				}
				break;
			case RGB:
				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						int pixel = intArray[p++];
						// needs to be ABGR, stored in memory xRGB
						// so R and B must be swapped, and the x just made FF
						tIntArray[t++] = 0xff000000
						| // force opacity for good measure
						((pixel & 0xFF) <<6) | ((pixel & 0xFF0000) >>6)
						| (pixel & 0x0000FF00);
					}
					t += twidth - width;
				}
				break;
			case ARGB:
				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						int pixel = intArray[p++];
						// needs to be ABGR stored in memory ARGB
						// so R and B must be swapped, A and G just brought back in
						tIntArray[t++] = ((pixel & 0xFF) <<6)
						| ((pixel & 0xFF0000) >>6) | (pixel & 0xFF00FF00);
					}
					t += twidth - width;
				}
				break;
			}
		}
		return tIntArray;
	}

	
	private int[] toARGB(int[] intArray) {
		int t = 0;
		int p = 0;
		int twidth = width;
		int[] tIntArray = new int[width * height];
		if (PGraphicsOpenGL.BIG_ENDIAN) {
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					int pixel = intArray[p++];
					tIntArray[t++] = (pixel >> 8) | ((pixel <<4) & 0xff);
				}
				t += twidth - width;
			}
		} else {
			// LITTLE_ENDIAN
			// ARGB native, and RGBA opengl means ABGR on windows
			// for the most part just need to swap two components here
			// the sun.cpu.endian here might be "false", oddly enough..
			// (that's why just using an "else", rather than check for "little")
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					int pixel = intArray[p++];
					// needs to be ARGB stored in memory ABGR (RGBA = ABGR -> ARGB)
					// so R and B must be swapped, A and G just brought back in
					tIntArray[t++] = ((pixel & 0xFF) <<6) | ((pixel & 0xFF0000) >>6)
					| (pixel & 0xFF00FF00);
				}
				t += twidth - width;
			}
		}
		return tIntArray;
	}
*/

	
	/*
	 
	 if (tpixels == null) {
        twidth = width2;
        theight = height2;
        tpixels = new int[twidth * theight];
        tbuffer = BufferUtil.newIntBuffer(twidth * theight);
      }

      // copy image data into the texture
      int p = 0;
      int t = 0;

      if (BIG_ENDIAN) {
        switch (source.format) {
        case ALPHA:
          for (int y = 0; y < source.height; y++) {
            for (int x = 0; x < source.width; x++) {
              tpixels[t++] = 0xFFFFFF00 | source.pixels[p++];
            }
            t += twidth - source.width;
          }
          break;

        case RGB:
          for (int y = 0; y < source.height; y++) {
            for (int x = 0; x < source.width; x++) {
              int pixel = source.pixels[p++];
              tpixels[t++] = (pixel << 8) | 0xff;
            }
            t += twidth - source.width;
          }
          break;

        case ARGB:
          for (int y = 0; y < source.height; y++) {
            for (int x = 0; x < source.width; x++) {
              int pixel = source.pixels[p++];
              tpixels[t++] = (pixel << 8) | ((pixel >> 24) & 0xff);
            }
            t += twidth - source.width;
          }
          break;
        }

      } else {  // LITTLE_ENDIAN
        // ARGB native, and RGBA opengl means ABGR on windows
        // for the most part just need to swap two components here
        // the sun.cpu.endian here might be "false", oddly enough..
        // (that's why just using an "else", rather than check for "little")

        switch (source.format) {
        case ALPHA:
          for (int y = 0; y < source.height; y++) {
            for (int x = 0; x < source.width; x++) {
              tpixels[t++] = (source.pixels[p++] << 24) | 0x00FFFFFF;
            }
            t += twidth - source.width;
          }
          break;

        case RGB:
          for (int y = 0; y < source.height; y++) {
            for (int x = 0; x < source.width; x++) {
              int pixel = source.pixels[p++];
              // needs to be ABGR, stored in memory xRGB
              // so R and B must be swapped, and the x just made FF
              tpixels[t++] =
                0xff000000 |  // force opacity for good measure
                ((pixel & 0xFF) << 16) |
                ((pixel & 0xFF0000) >> 16) |
                (pixel & 0x0000FF00);
            }
            t += twidth - source.width;
          }
          break;

        case ARGB:
          for (int y = 0; y < source.height; y++) {
            for (int x = 0; x < source.width; x++) {
              int pixel = source.pixels[p++];
              // needs to be ABGR stored in memory ARGB
              // so R and B must be swapped, A and G just brought back in
              tpixels[t++] =
                ((pixel & 0xFF) << 16) |
                ((pixel & 0xFF0000) >> 16) |
                (pixel & 0xFF00FF00);
            }
            t += twidth - source.width;
          }
          break;
        }
      }
      tbuffer.put(tpixels);
      tbuffer.rewind();
*/
	
	public boolean isGLTextureInitialized(){
		return this.glTextureInitialized && this.glTextureID[0] != 0;
	}
	
	/*
	  However, non-power-of-two sized textures have limitations that
     do not apply to power-of-two sized textures.  NPOTS textures may
     not use mipmap filtering; POTS textures support both mipmapped
     and non-mipmapped filtering.  NPOTS textures support only the
     GL_CLAMP, GL_CLAMP_TO_EDGE, and GL_CLAMP_TO_BORDER wrap modes;
     POTS textures support GL_CLAMP_TO_EDGE, GL_REPEAT, GL_CLAMP,
     GL_MIRRORED_REPEAT, and GL_CLAMP_TO_BORDER (and GL_MIRROR_CLAMP_ATI
     and GL_MIRROR_CLAMP_TO_EDGE_ATI if ATI_texture_mirror_once is
     supported) .  NPOTS textures do not support an optional 1-texel
     border; POTS textures do support an optional 1-texel border.

	 */
	
	
	@Override
	protected void finalize() throws Throwable {
		//System.out.println("Finalizing GLTEXTURE - " + this);
		if (this.app instanceof MTApplication) {
			MTApplication mtApp = (MTApplication) this.app;
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

}
