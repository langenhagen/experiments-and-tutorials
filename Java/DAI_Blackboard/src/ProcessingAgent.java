/**
 * ProcessingAgent.java<br><br>
 * 
 * This class provides the printer-agent, which processes appropriate Message-objects 
 * found in a TupleSpace.
 * At creation time, it can be configured to be either Slave1 or Slave2, 
 * according to the problem definition.
 * It is an extension of Thread and is used by calling <i>start()</i>.<br><br> 
 *
 * @author Barn
 * @version 20101125
 */

import org.sercho.masp.space.TupleSpace;

/**
 * ProcessingAgent.java<br><br>
 * 
 * This class provides the processing-agent, which processes appropriate Message-objects 
 * found in a TupleSpace.
 * At creation time, it can be configured to be either Slave1 or Slave2, 
 * according to the problem definition.
 * It is an extension of Thread and is used by calling <i>start()</i>.<br><br> 
 *
 * @author Barn
 * @version 20101125
 */

public class ProcessingAgent extends Thread {

	private boolean type;				// Indicates the type of the Slave-Agent
	private TupleSpace<Message> bb;		// TupleSpace which is used as reference to a blackboard
	

	/**
	 * Main Constructor<br>
	 * @param type
	 * The type of slave-agent as a <i>boolean</i> primitive.<br><br>
	 * 
	 * <i>false</i> - assigns to 'Slave1'<br>
	 * <i>true</i> - assigns to 'Slave2'
	 * @param blackBoard
	 * The blackboard on which the Messages are to be found as a 
	 * <i>TupleSpace</i> of the type <i>Message</i>.
	 */
	public ProcessingAgent(boolean type, TupleSpace<Message> blackBoard){
		
		System.out.println("Invoking processing-agent : type = Slave" + (type ? "2" : "1") + "...");
		this.type = type;
		bb = blackBoard;
	}
	
	/**
	 * run()<br>
	 * 
	 * Runs the processing-agent, that printouts all appropriate <i>Messages</i> found on the blackboard.
	 */
	@Override
	public void run(){
	
		// (permanent) Message instances, avoid RAM-junking & garbage collection necessity
		Message dummyMsg = new Message( null , type, false);	// dummy instance
		Message rdMsg;											// read Message
		Message wrtMsg = new Message( null, null, true);		// updated read Message
		
		
		if(type)								// if this is an Slave2-instance
			while(true){
				rdMsg = bb.read( dummyMsg, 10000);
				if( rdMsg == null)
					continue;
				// ***one Message read successfully***
				System.err.println("Slave2: " + rdMsg.msg);
				bb.update( rdMsg, wrtMsg);
			}
		else									// if this is an Slave1-instance
			while(true){
				rdMsg = bb.read( dummyMsg, 10000);
				if( rdMsg == null)
					continue;
				// ***one Message read successfully***
				System.out.println("Slave1: " + rdMsg.msg);
				bb.update( rdMsg, wrtMsg);
			}
	}

} // END Class
