package src;

import org.mt4j.MTApplication;



public class StartHelloWorld extends MTApplication {

	public static void main(String[] args) {
		initialize();
		
		
	}
 
	@Override
	public void startUp() {
		addScene(new HelloWorldScene(this, "Hello World Scene"));
	}
}