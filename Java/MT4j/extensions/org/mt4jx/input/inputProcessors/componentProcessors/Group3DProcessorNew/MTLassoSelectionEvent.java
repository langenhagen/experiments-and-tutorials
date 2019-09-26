package org.mt4jx.input.inputProcessors.componentProcessors.Group3DProcessorNew;

import java.util.ArrayList;

import org.mt4j.components.MTComponent;
import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.input.inputProcessors.componentProcessors.lassoProcessor.IdragClusterable;

public class MTLassoSelectionEvent extends MTSelectionEvent {
	
	/** The selection poly. */
	private MTPolygon selectionPoly;
	
	private Cluster cluster;
	
	public MTLassoSelectionEvent(Object source) {
		super(source);		
	}
	
	public MTLassoSelectionEvent(Object source,		
			int id, ArrayList<MTComponent> selectedComps,MTPolygon selectionPoly,Cluster cluster)
	{
		super(source,id,selectedComps);
		this.selectionPoly = selectionPoly;
		this.setCluster(cluster);
		
	}

	public void setSelectionPoly(MTPolygon selectionPoly) {
		this.selectionPoly = selectionPoly;
	}

	public MTPolygon getSelectionPoly() {
		return selectionPoly;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public Cluster getCluster() {
		return cluster;
	}

}
