import singletonPackage.Singleton;
import singletonPackage.SingletonChild;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args){
		
		System.out.println("Changing Singleton-Instance to " + Singleton.class.getName() + "...\n");
		
		Singleton.setup( Singleton.class);
		System.out.println( "Value of Singleton.instance.getSomeValue():  " + Singleton.instance.getSomeValue() );
		System.out.println( "Value of Singleton.instance.getSomeValue2(): " + Singleton.instance.getSomeValue2() );

		System.out.println();
		System.out.println("Changing Singleton-Instance to " + SingletonChild.class.getName() + "...\n");

		Singleton.setup( SingletonChild.class);
		System.out.println( "Value of Singleton.instance.getSomeValue():  " + Singleton.instance.getSomeValue() );
		System.out.println( "Value of Singleton.instance.getSomeValue2(): " + Singleton.instance.getSomeValue2() );

	}
}
