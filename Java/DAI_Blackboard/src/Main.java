/**
 * Main.java<br><br>
 * 
 * This class provides the test setup for evaluating the solution to the blackboard-assignment.<br>
 * The agents get information from the blackboard via pull behaviour, they act proactive.<br>
 * Agents who read data from the blackboard, are not running the whole time.<br>
 * Slave1 & Slave2 fall asleep as long as they don't get any new messages.<br>
 * Also disposal_agent has a sleep time period: ten-seconds, after which it erases all processed messages,
 * then falling asleep again.<br>
 * 
 * @author Barn
 * @version 20101125
 */
import org.sercho.masp.space.SimpleObjectSpace;
import org.sercho.masp.space.TupleSpace;


/**
 * Main.java<br><br>
 * 
 * This class provides the test setup for evaluating the solution to the blackboard-assignment.<br>
 * The agents get information from the blackboard via pull behaviour, they act proactive.<br>
 * Agents who read data from the blackboard, are not running the whole time.<br>
 * Slave1 & Slave2 fall asleep as long as they don't get any new messages.<br>
 * Also disposal_agent has a sleep time period: ten-seconds, after which it erases all processed messages,
 * then falls asleep again.<br>
 * 
 * @author Barn
 * @version 20101125
 */
public class Main{
	
	public static void main(String[] args){

		System.out.println("Starting Blackboard-Test...");	
		TupleSpace<Message> bb = new SimpleObjectSpace<Message>("Blackboard");
		
		
		System.out.println( "Initializing the agents...");
		MasterAgent master 				= new MasterAgent( bb);
		DisposalAgent slave3			= new DisposalAgent( bb);
		ProcessingAgent slave1			= new ProcessingAgent( false, bb);
		ProcessingAgent slave2			= new ProcessingAgent( true, bb);
		
		
		System.out.println("Starting the agents...");
		master.start();
		slave3.start();
		slave1.start();
		slave2.start();
	}
}
