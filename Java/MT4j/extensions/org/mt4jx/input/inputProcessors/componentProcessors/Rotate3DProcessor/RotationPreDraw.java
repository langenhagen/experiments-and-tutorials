package org.mt4jx.input.inputProcessors.componentProcessors.Rotate3DProcessor;

import org.mt4j.sceneManagement.IPreDrawAction;
import org.mt4jx.input.gestureAction.Rotate3DAction;

public class RotationPreDraw implements IPreDrawAction {

	private Rotate3DAction action;
	
	public RotationPreDraw(Rotate3DAction action)
	{
		this.action = action;
	}
	
	public boolean isLoop() {
		// TODO Auto-generated method stub
		return true;
	}

	public void processAction() {
		System.out.println("process predraw aciton");
		action.draw();
	}

}
