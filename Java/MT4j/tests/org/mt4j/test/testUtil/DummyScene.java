package org.mt4j.test.testUtil;

import org.mt4j.MTApplication;
import org.mt4j.sceneManagement.AbstractScene;

public class DummyScene extends AbstractScene {

	public DummyScene(MTApplication mtApplication, String name) {
		super(mtApplication, name);
	}

	public void onEnter() {}
	
	public void onLeave() {}

}
