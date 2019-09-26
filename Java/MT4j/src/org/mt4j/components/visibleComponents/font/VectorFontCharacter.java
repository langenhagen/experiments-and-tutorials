/***********************************************************************
 * mt4j Copyright (c) 2008 - 2009, C.Ruff, Fraunhofer-Gesellschaft All rights reserved.
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
package org.mt4j.components.visibleComponents.font;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

import javax.media.opengl.GL;

import org.mt4j.components.bounds.IBoundingShape;
import org.mt4j.components.visibleComponents.shapes.GeometryInfo;
import org.mt4j.components.visibleComponents.shapes.mesh.MTTriangleMesh;
import org.mt4j.util.math.Tools3D;
import org.mt4j.util.math.ToolsGeometry;
import org.mt4j.util.math.Vertex;
import org.mt4j.util.opengl.GLTexture;
import org.mt4j.util.opengl.GluTrianglulator;

import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * A class representing the character of a vector font.
 * 
 * @author Christopher Ruff
 */
public class VectorFontCharacter extends 
		MTTriangleMesh 
//		MTComplexPolygon
		implements IFontCharacter {
	
	/** The unicode. */
	private String unicode;
	
	/** The horizontal dist. */
	private int horizontalDist;
	
	
	//TODO make constructor with leftoffset, unicode, horzindalAdv
	
	/**
	 * A vector font character class.
	 * The specified contour vertices are assumed to lie in the z=0 plane.
	 * @param pApplet the applet
	 * @param contours the contours
	 */
	public VectorFontCharacter(PApplet pApplet, /*Vertex[] innerVertices,*/ List<Vertex[]> contours) {
//		super(innerVertices, outlines, pApplet);
//		/*
		 //Create dummy vertices, will be replaced later in the constructor
		super(pApplet, new GeometryInfo(pApplet, new Vertex[]{}), false);
		
		//Caluculate vertices from bezierinformation
		int segments = 10; 
		List<Vertex[]> bezierContours = ToolsGeometry.createVertexArrFromBezierVertexArrays(contours, segments);
		
		//Triangulate bezier contours
		GluTrianglulator triangulator = new GluTrianglulator(pApplet);
		List<Vertex> tris = triangulator.tesselate(bezierContours);
		//Set new geometry info with triangulated vertices
		super.setGeometryInfo(new GeometryInfo(pApplet, tris.toArray(new Vertex[tris.size()])));
		//Set Mesh outlines
		this.setOutlineContours(bezierContours);
		//Delete triangulator (C++ object)
		triangulator.deleteTess(); 
//		*/
		
		this.setPickable(false);
	}
	
	//Draw methods are overriden to not take the character's own color into account 
	//so we can set the color once in the 
	
	@Override
	public void drawComponent(PGraphics g) {
		if (this.isUseDirectGL()){
			super.drawComponent(g);
		}else{
			g.strokeWeight(1.5f);
			if (!this.isNoFill()){
				g.noStroke();
				g.noSmooth();
				g.fill = true;
				this.drawWithProcessing(g, this.getVerticesLocal(), PGraphics.TRIANGLES, true);
			}

			if (!this.isNoStroke() && this.isDrawSmooth()){
				g.noFill(); 
				g.stroke = true;
				g.smooth();

				for (Vertex[] outline : this.outlineContours){
					this.drawWithProcessing(g, outline, PGraphics.POLYGON, false);
				}
				g.fill = true;
			}
		}
	}


	@Override
	protected void drawPureGl(GL gl) {
//		super.drawPureGl(gl);
		//Get display array/buffer pointers
		FloatBuffer tbuff 			= this.getGeometryInfo().getTexBuff();
		FloatBuffer vertBuff 		= this.getGeometryInfo().getVertBuff();
		IntBuffer indexBuff 		= this.getGeometryInfo().getIndexBuff(); //null if not indexed
		
		//Enable Pointers, set vertex array pointer
		gl.glEnableClientState(GL.GL_VERTEX_ARRAY);
		
		if (this.isUseVBOs()){//Vertices
			gl.glBindBuffer(GL.GL_ARRAY_BUFFER, this.getGeometryInfo().getVBOVerticesName());
			gl.glVertexPointer(3, GL.GL_FLOAT, 0, 0);
		}else{
			gl.glVertexPointer(3, GL.GL_FLOAT, 0, vertBuff);
		}
		
		//Default texture target
		int textureTarget = GL.GL_TEXTURE_2D;
		
		/////// DRAW SHAPE ///////
		if (!this.isNoFill()){ 
			boolean textureDrawn = false;
			if (this.isTextureEnabled()
				&& this.getTexture() != null 
				&& this.getTexture() instanceof GLTexture) //Bad for performance?
			{
				GLTexture tex = (GLTexture)this.getTexture();
				textureTarget = tex.getTextureTarget();
				
				//tells opengl which texture to reference in following calls from now on!
				//the first parameter is eigher GL.GL_TEXTURE_2D or ..1D
				gl.glEnable(textureTarget);
				gl.glBindTexture(textureTarget, tex.getTextureID());
				gl.glEnableClientState(GL.GL_TEXTURE_COORD_ARRAY);
				
				if (this.isUseVBOs()){//Texture
					gl.glBindBuffer(GL.GL_ARRAY_BUFFER, this.getGeometryInfo().getVBOTextureName());
					gl.glTexCoordPointer(2, GL.GL_FLOAT, 0, 0);
				}else{
					gl.glTexCoordPointer(2, GL.GL_FLOAT, 0, tbuff);
				}
				textureDrawn = true;
			}
			
			// Normals
			if (this.getGeometryInfo().isContainsNormals()){
				gl.glEnableClientState(GL.GL_NORMAL_ARRAY);
				if (this.isUseVBOs()){
					gl.glBindBuffer(GL.GL_ARRAY_BUFFER, this.getGeometryInfo().getVBONormalsName());
					gl.glNormalPointer(GL.GL_FLOAT, 0, 0); 
				}else{
					gl.glNormalPointer(GL.GL_FLOAT, 0, this.getGeometryInfo().getNormalsBuff());
				}
			}
			
			//DRAW with drawElements if geometry is indexed, else draw with drawArrays!
			if (this.getGeometryInfo().isIndexed()){
				gl.glDrawElements(this.getFillDrawMode(), indexBuff.capacity(), GL.GL_UNSIGNED_INT, indexBuff); //limit() oder capacity()??
			}else{
				gl.glDrawArrays(this.getFillDrawMode(), 0, vertBuff.capacity()/3);
			}
			
			if (this.getGeometryInfo().isContainsNormals()){
				gl.glDisableClientState(GL.GL_NORMAL_ARRAY);
			}

			if (textureDrawn){
				gl.glBindTexture(textureTarget, 0);//Unbind texture
				gl.glDisableClientState(GL.GL_TEXTURE_COORD_ARRAY);
				gl.glDisable(textureTarget); //weiter nach unten?
			}
		}
		
		////////// DRAW OUTLINE ////////
		if (!this.isNoStroke()
				&& this.outlineBuffers != null //FIXME EXPERIMENT
				&& this.outlineContours != null
		){ 
			Tools3D.setLineSmoothEnabled(gl, true);
			//SET LINE STIPPLE
			short lineStipple = this.getLineStipple();
			if (lineStipple != 0){
				gl.glLineStipple(1, lineStipple);
				gl.glEnable(GL.GL_LINE_STIPPLE);
			}
			
			if (this.getStrokeWeight() > 0)
				gl.glLineWidth(this.getStrokeWeight());
			
//			//Dont use geometryinfo strokecolor buffer because its useless in a trianglemesh 
//			//instead we use a single, simple stroke color and custom outlines, if provided 
//			gl.glColor4f(strokeR, strokeG, strokeB, strokeA);
			
			//Always use just buffes and drawarrays instead of vbos..too complicated for a simple outline..
			for(FloatBuffer outlineBuffer : this.outlineBuffers){ //FIXME EXPERIMENTAL
				gl.glVertexPointer(3, GL.GL_FLOAT, 0, outlineBuffer); 
				gl.glDrawArrays(GL.GL_LINE_STRIP, 0, outlineBuffer.capacity()/3);
			}
			
			//RESET LINE STIPPLE
			if (lineStipple != 0){
				gl.glDisable(GL.GL_LINE_STIPPLE);
			}
			Tools3D.setLineSmoothEnabled(gl, false);
		}
		
		gl.glDisableClientState(GL.GL_VERTEX_ARRAY);
		
		if (this.isUseVBOs()){
			gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);
			gl.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, 0);
		}
	}
	
	
	@Override
	protected int generateContoursDisplayList(boolean useColor) {
		return super.generateContoursDisplayList(false);
	}

	
	/**
	 * Gets the contours.
	 * 
	 * @return the contours
	 */
	public List<Vertex[]> getContours(){
		return this.getOutlineContours();
	}
	
	
	@Override
	protected void setDefaultGestureActions() {
		//no gestures
	}
	
	
	@Override
	protected IBoundingShape computeDefaultBounds(){
//		return new BoundsZPlaneRectangle(this);
		//We assume that font characters never get picked or anything 
		//and hope the creation speeds up through not calculating a bounding shape
		return null;
	}


	public String getUnicode() {
		return unicode;
	}
	
	/**
	 * Sets the unicode.
	 * @param unicode the new unicode
	 */
	public void setUnicode(String unicode) {
		this.unicode = unicode;
	}

	/**
	 * The horizontal advancement distance specifies, how many units
	 * to the right, after this character the following character may be placed.
	 * 
	 * @return the horizontal dist
	 */
	public int getHorizontalDist() {
		return horizontalDist;
	}

	/**
	 * This shouldnt be set manually, except by the font parser/creator.
	 * 
	 * @param horizontalDist the horizontal dist
	 */
	public void setHorizontalDist(int horizontalDist) {
		this.horizontalDist = horizontalDist;
	}
	
	
	@Override
	protected void destroyDisplayLists() {
		super.destroyDisplayLists();
		
		//this should actually be called explicitly since a fontchar is
		//usually not child of a component
		//So we have to destroy the list if we explicitly destroy a font
		//E.g. when we remove it from the cache
	}


}
