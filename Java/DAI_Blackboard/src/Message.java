/**
 * Message.java<br><br>
 * 
 * This class represents Messages as described in the assignment of tasks.<br>
 * It consists of no logic, it has only the purpose of being a container.<br><br>
 * 
 * 
 * PLEASE NOTE:<br>
 * The instance vars must be public, otherwise the retrievement by SimpleSpace will be erratic.<br>
 * Therefore I do not care about data encapsulation. <br>
 * I recommend to take a look at the db4o-Project (http://www.db4o.com/) as an useful alternative.<br>
 * 
 * @author Barn
 * @version 20101125
 */

public class Message{

	public String msg;			// the message text 
	public Boolean priority;	// flag indicating which agent shall be assigned to this Message
	public Boolean jobDone;		// indicates wether this Message is processed or not
	
	/**
	 * Main Constructor<br>
	 * @param message
	 * The actual message that shall be stored as a <i>String</i>.
	 * 
	 * @param priority
	 * This <i>Boolean</i> value indicates, which of the two agents - Slave1 or Slave2 - has to attend 
	 * to the job of printing this message.<br><br>
	 * 
	 * false - Slave1 will attend to this job<br>
	 * true  - Slave2 will attend to this job
	 * 
	 * @param jobDone
	 * This <i>Boolean</i> value indicates, wether the Message was sent or not.
	 */
	public Message(String message, Boolean priority, Boolean jobDone){
		this.msg = message;
		this.priority = priority;
		this.jobDone = jobDone;
	}
	
} // END Class
