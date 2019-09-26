package org.mt4jx.input.inputProcessors.componentProcessors.Group3DProcessorNew.GroupVisualizations;

import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4jx.input.inputProcessors.componentProcessors.Group3DProcessorNew.Cluster;
import org.mt4jx.input.inputProcessors.componentProcessors.Group3DProcessorNew.IVisualizeMethodProvider;

public class ActivateVisualizationAction implements IGestureEventListener {

	private Cluster cluster;
	
	private IVisualizeMethodProvider methodProvider;
	
	public ActivateVisualizationAction(Cluster cluster,IVisualizeMethodProvider methodProvider)
	{		
		this.cluster = cluster;
		this.methodProvider = methodProvider;
		this.cluster.setVisualizeProvider(null);
	}
	public boolean processGestureEvent(MTGestureEvent ge) {
		if(ge instanceof DragEvent)
		{
			DragEvent tapEv = (DragEvent)ge;
			switch(tapEv.getId())
			{
			case DragEvent.GESTURE_STARTED:
				cluster.setVisualizeProvider(methodProvider);
				break;
			case DragEvent.GESTURE_ENDED:				
				cluster.setVisualizeProvider(null);
				break;
			default: break;
			}
		}
		return false;
	}

}
