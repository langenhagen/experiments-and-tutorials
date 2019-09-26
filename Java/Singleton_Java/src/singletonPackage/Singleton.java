package singletonPackage;		// Separates Package um protected-Konstruktor zu verstecken


public class Singleton {
	
	// CLASS VARIABLES //////////////////////////////////////////////////////////////////////////////
	
	/** Singleton instance */
	protected static Singleton instance;
	
	public int someValue = 5;			// private Member, werden nicht Vererbt
	private int someValue2 = 15;		// (koennen auch als nicht-private nicht ueberladen werden)
	
	// CONSTRUCTOR //////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Main Construktor of Singleton.
	 * Protected; May be visible for children.
	 */
	protected Singleton(){}
	
	// PUBLIC STATIC METHODS ////////////////////////////////////////////////////////////////////////
	
	// setup-method for dynamic initialization
	/**
	 * setup()<br>
	 * Sets up the Singleton to an instance of of a given <i>Singleton</i> (Sub-)Class.
	 * @param clazz
	 * The <i>Class</i> <i>Singleton</i> or some <i>Class</i> which extends its interface.
	 */
	public final static void setup( Class<? extends Singleton> clazz){
		try{			
			
			instance = clazz.newInstance();
			
		} catch(Exception e){ e.printStackTrace(); }
	}
	
	/**
	 * Gets the singleton instance of <i>Singleton</i>.
	 * @return
	 * The singleton instance of <i>Singleton</i>.
	 */
	public static final Singleton instance(){
		return instance;
	}
	
	
	// eine Methode
	public int getSomeValue(){
		return someValue;
	}
	
	// eine andere Methode
	public int getSomeValue2(){
		return someValue2;
	}
}
