package org.mt4jx.input.inputProcessors.componentProcessors.Group3DProcessorNew.GroupVisualizations;

import java.util.ArrayList;

import javax.media.opengl.GL;

import org.mt4j.components.MTComponent;
import org.mt4j.components.visibleComponents.shapes.MTLine;
import org.mt4j.components.visibleComponents.shapes.mesh.MTTriangleMesh;
import org.mt4j.input.IMTEventListener;
import org.mt4j.input.MTEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.lassoProcessor.IdragClusterable;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Tools3D;
import org.mt4j.util.math.Vector3D;
import org.mt4jx.input.inputProcessors.componentProcessors.Group3DProcessorNew.Cluster;
import org.mt4jx.input.inputProcessors.componentProcessors.Group3DProcessorNew.IVisualizeMethodProvider;
import org.mt4jx.input.inputProcessors.componentProcessors.Group3DProcessorNew.MTClusterEvent;
import org.mt4jx.util.extension3D.ComponentHelper;
import org.mt4jx.util.extension3D.MergeHelper;

import processing.core.PApplet;
import processing.core.PImage;

public class LineVisualizationWithOutlinesAction implements IMTEventListener,IVisualizeMethodProvider {

	private PApplet pApplet;
	private long startTime; 
	
	public LineVisualizationWithOutlinesAction(PApplet pApplet)
	{
		this.pApplet = pApplet;
		startTime = System.currentTimeMillis();
	}
	public void processMTEvent(MTEvent mtEvent) {
		if(mtEvent instanceof MTClusterEvent)
		{
			MTClusterEvent clEvent = (MTClusterEvent)mtEvent;		
			switch(clEvent.getId())
			{
			case MTClusterEvent.CLUSTER_CREATED:
				clEvent.getCluster().setVisualizeProvider(this);
				clEvent.getCluster().addGestureListener(DragProcessor.class,new BlinkingEmissionActivateVisualizationAction(clEvent.getCluster(),this));
				break;			
			case MTClusterEvent.CLUSTER_SELECTED:
				if(clEvent.getCluster().getVisualizeProvider()!=this)
				{	
					startTime = System.currentTimeMillis();
					clEvent.getCluster().addGestureListener(DragProcessor.class,new ActivateVisualizationAction(clEvent.getCluster(),this));
					clEvent.getCluster().setVisualizeProvider(this);
				}
				break;
			}
		}

	}
	
	public void visualize(Cluster cluster)
	{
		
		for(MTComponent groups : cluster.getChildren())
		{
			for(MTComponent comp : groups.getChildren())
			{
				if(comp instanceof MTTriangleMesh)
				{
					MTTriangleMesh mesh = (MTTriangleMesh)comp;
					if((long)((((double)(System.currentTimeMillis()-startTime))/500.0))%2==0)
					{
						mesh.getMaterial().setEmission(new float[]{255.f,0.f,0.f});
					}else
					{
						mesh.getMaterial().setEmission(new float[]{0.f,0.f,0.f});
					}					
				}
			}
		}
		GL gl = Tools3D.getGL(pApplet);
		Tools3D.beginGL(pApplet);
		gl.glBegin(gl.GL_LINES);
		MTLine[] lines = getVisualizationLines(cluster.getChildren());
		for(MTLine line : lines)
		{
			gl.glVertex3f(line.getVerticesLocal()[0].x,line.getVerticesLocal()[0].y,line.getVerticesLocal()[0].z);
			gl.glVertex3f(line.getVerticesLocal()[1].x,line.getVerticesLocal()[1].y,line.getVerticesLocal()[1].z);
		}
		gl.glEnd();
		Tools3D.endGL(pApplet);
		
	}
	
	private MTLine[] getVisualizationLines(MTComponent[] selectedComps)
	{
		ArrayList<Vector3D> centerPoints = new ArrayList<Vector3D>();
				
		for(MTComponent comp : selectedComps)
		{
			MTComponent mtcomp = (MTComponent)comp;
			
			centerPoints.add(ComponentHelper.getCenterPointGlobal(comp));
		}
		
		ArrayList<Vector3D> sortedCenterPoints = getSortedListForShortedDistance(centerPoints);
			
		MTLine[] lines = null;
		if(sortedCenterPoints.size()>2)
		{
			lines = new MTLine[sortedCenterPoints.size()];
		}else
		{
			lines = new MTLine[sortedCenterPoints.size()-1];
		}
		
		int j=0;
		
		for(int i=0;i<sortedCenterPoints.size()-1;i++)
		{
			MTLine line = new MTLine(pApplet, sortedCenterPoints.get(i).x,sortedCenterPoints.get(i).y,sortedCenterPoints.get(i).z,
					sortedCenterPoints.get(i+1).x,sortedCenterPoints.get(i+1).y,sortedCenterPoints.get(i+1).z);
			//line.setStrokeWeight(5.0f);
			//line.setStrokeColor(new MTColor(1.0f,1.0f,1.0f,0.7f));//TODO make color configurable
			lines[i]= line;
		}
		
		//close circle
		int size = sortedCenterPoints.size();
		if(sortedCenterPoints.size()>2)
		{
			MTLine line = new MTLine(pApplet, sortedCenterPoints.get(size-1).x,sortedCenterPoints.get(size-1).y,sortedCenterPoints.get(size-1).z,
					sortedCenterPoints.get(0).x,sortedCenterPoints.get(0).y,sortedCenterPoints.get(0).z);
			//line.setStrokeWeight(5.0f);	
			//line.setStrokeColor(new MTColor(1.0f,1.0f,1.0f,0.7f));//TODO make color configurable
			lines[lines.length-1] = line;
		}
		return lines;
	}
	
	private ArrayList<Vector3D> getSortedListForShortedDistance(ArrayList<Vector3D> centerPoints)
	{
		Vector3D startPoint = null;
		
		ArrayList<Vector3D> sortedVectors = new ArrayList<Vector3D>();
		
		startPoint = centerPoints.get(0);
		sortedVectors.add(startPoint);
		centerPoints.remove(startPoint);
		
		Vector3D lastPoint = startPoint;
		
		while(centerPoints.size()>0)
		{
			Vector3D nextPoint  = getNextPoint(centerPoints,lastPoint);		
			sortedVectors.add(nextPoint);
			centerPoints.remove(nextPoint);
			lastPoint = nextPoint;
		}
				
		return sortedVectors;
		
	}
	
	private Vector3D getNextPoint(ArrayList<Vector3D> centerPoints,Vector3D lastPoint)
	{
		Vector3D currentShortestDistancePoint = null;
		float minLength = 99999999.0f;
		
		for(int i=0;i<centerPoints.size();i++)
		{
			float currentLength = lastPoint.getSubtracted(centerPoints.get(i)).length();
			if(currentLength<minLength)
			{
				minLength = currentLength;
				currentShortestDistancePoint = centerPoints.get(i);
			}			
		}
		return currentShortestDistancePoint;
	}

}
