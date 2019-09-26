package singletonPackage;


public class SingletonChild extends Singleton {
	
	// neuer Wert 
	//(someValue aus Singleton wird nicht ueberschrieben, auch wenn er nicht private waere)
	public int someValue = 3;
	
	// Protected Konstruktor, ist noetig, sodass Mutterklasse das Kind erstellen kann
	protected SingletonChild(){}
	
	// Ueberschreiben einer geerbten Methode.
	public int getSomeValue(){
		return someValue;
	}
	
}
