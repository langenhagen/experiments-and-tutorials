package org.mt4j.test.css;

import org.mt4j.MTApplication;

public class StartIntegrationTestApp extends MTApplication{

	public static void main(String[] args) {
		initialize();
	}
	
	
	@Override
	public void startUp() {
		// TODO Auto-generated method stub
		addScene(new IntegrationTestApp(this, "Integration  Test Scene"));
	}

}
