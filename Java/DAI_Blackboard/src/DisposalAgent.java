/**
 * DisposalAgent.java<br><br>
 * 
 * This class provides the disposal-agent, which clears processed Message-objects out of a TupleSpace.<br>
 * It is an extension of Thread and is used by calling <i>start()</i>.<br><br> 
 *
 * @author Barn
 * @version 20101125
 */

import org.sercho.masp.space.TupleSpace;

/**
 * DisposalAgent.java<br><br>
 * 
 * This class provides the disposal-agent, which clears processed Message-objects out of a TupleSpace.<br>
 * It is an extension of Thread and is used by calling <i>start()</i>.<br><br> 
 *
 * @author Barn
 * @version 20101125
 */
public class DisposalAgent extends Thread {

	private TupleSpace<Message> bb;		// TupleSpace which is used as reference to a blackboard
	
	/**
	 * Main Constructor<br>
	 * @param blackBoard
	 * The blackboard on which the Messages are to be found as a 
	 * <i>TupleSpace</i> of the type <i>Message</i>.
	 */
	public DisposalAgent(TupleSpace<Message> blackBoard){
		
		System.out.println("Invoking Slave3...");
		bb = blackBoard;
	}
	
	
	/**
	 * run()<br>
	 * 
	 * Runs the disposal-agent that deletes all processed <i>Messages</i> from the blackboard.<br>
	 * The agent erases processed Messages every ten seconds.
	 */
	@Override
	public void run(){
		
		Message dummyMsg = new Message( null , null, true);	// permanent dummy instance, avoids RAM-junking

		try{
			while(true){
				sleep( 10000);
				System.out.println( "\t\t\t\t\t\t\t\tDisposal-agent: " + bb.removeAll( dummyMsg).size() + 
									" processed Messages removed.");
			}
			
		} catch(InterruptedException e){
    		System.err.println("\nERROR IN DISPOSAL-AGENT THREAD\n");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
} // END Class
