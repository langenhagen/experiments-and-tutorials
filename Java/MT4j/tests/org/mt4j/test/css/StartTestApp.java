package org.mt4j.test.css;

import org.mt4j.MTApplication;



public class StartTestApp extends MTApplication{
	public static void main(String[] args) {
		initialize();
	}
	
	public void initApp() {
		initialize();
	}
	
	@Override
	public void startUp() {
		initialize();
		addScene(new TestApp(this, "Test Scene"));
		
	}

}
