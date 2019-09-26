package org.mt4jx.input.inputProcessors.componentProcessors.Group3DProcessorNew;

import org.mt4j.input.MTEvent;

public class MTClusterEvent extends MTEvent {

	public static final int CLUSTER_CREATED = 1;
	
	public static final int CLUSTER_UPDATED = 2;
	
	public static final int CLUSTER_DELETED = 3;
	
	public static final int CLUSTER_SELECTED = 4;
	
	private Cluster cluster;
	
	private int id;
	
	public MTClusterEvent(Object source,int id,Cluster cluster) {
		super(source);
		this.cluster = cluster;
		this.id = id;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public Cluster getCluster() {
		return cluster;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
