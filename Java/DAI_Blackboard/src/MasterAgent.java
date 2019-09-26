/**
 * MasterAgent.java<br><br>
 * 
 * This class provides the master-agent, which writes random Message-objects into a TupleSpace.<br>
 * It is an extension of Thread and is used by calling <i>start()</i>.<br><br> 
 *
 * @author Barn
 * @version 20101125
 */

import java.util.Random;
import org.sercho.masp.space.TupleSpace;

/**
 * MasterAgent.java<br><br>
 * 
 * This class provides the master-agent, which writes random Message-objects into a TupleSpace-<br>
 * It is an extension of Thread and is used by calling <i>start()</i>.<br><br>
 *   
 * @author Barn
 * @version 20101125
 */
public class MasterAgent extends Thread{

	private TupleSpace<Message> bb;		// TupleSpace which is used as reference to a blackboard
	
	/**
	 * Main Constructor<br>
	 * @param blackBoard
	 * The blackboard on which the Messages are to be written as a 
	 * <i>TupleSpace</i> of the type <i>Message</i>.
	 */
	public MasterAgent(TupleSpace<Message> blackBoard){
		
		System.out.println("Invoking master-agent...");
		bb = blackBoard;
	}
	

	/**
	 * run()<br>
	 * 
	 * Runs the master-agent that writes random <i>Messages</i> onto the blackboard 
	 * in random intervals from 100ms to 1500ms.
	 */
	@Override
	public void run(){
		
		short i=0;
		Random rnd = new Random();	
		
		try{
			while(true){
				bb.write( new Message( "Test Message #"+ i++ +" (" + rnd.nextLong() + ")", rnd.nextBoolean(), false) );
				sleep(10 + rnd.nextInt( 1400));	
			}			
			
		}catch(InterruptedException e){ 
			System.err.println("\nERROR IN MASTER THREAD\n");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
} // END Class 
