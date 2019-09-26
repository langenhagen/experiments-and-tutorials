import filegen.CppFileGenerator;

/**
 * Startup class
 * 
 * @author Barn
 * @version 20120609
 */
public class Main {
	
	public static void main(String[] args)
	{	
		String file = args.length > 0 ? args[0] : "Config.txt";
					
		new CppFileGenerator().process( file );
		
		System.out.println("DONE!");
	}
	
}
