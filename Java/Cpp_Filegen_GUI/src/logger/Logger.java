package logger;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Klasse Logger zur Speicherung von Informationen in eine Datei, eignet sich zum Debuggen.					//
// Logger kann Mengen von Daten in verschiedenen Formaten speichern, sodass die Daten z.B. durch eine		//
// Tabellenkalkulation visualisiert werden k�nnen.															//
//                                                                                                          //
// Author: Barn																								//
// Version: 20121010                                                                                        //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
import java.io.*;
import java.text.DateFormat;
import java.util.Date;

/**
 * Class Logger for saving arbitrary information into a file. Works just fine for debugging.
 * Logger can store sets of data in different formats, so that these data can, for example,
 * later be viewed in a spreadsheed programm.
 * 
 * @author Barn
 * @version 20121008
 */
public class Logger{

	// INSTANCE VARS //////////////////////////////////////////////////////////////////////////////
	
	private FileOutputStream wrtStream;		// Stream-Object
	private String sep;						// Log-Entry separator
	private File file;						// The file-Object
	private DateFormat dateFormat;			// the format of the date/time

	// CONSTRUCTORS ///////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructor #1.
	 * @param path
	 * The path and filename of the Log-File.
	 * @param separator
	 * The separator that splits different logs. 
	 * Set to null if you dont want the time to be logged.
	 */
	public Logger( String path, String separator, DateFormat dateFormat){
		
		file = new File(path);
		sep = separator;
		this.dateFormat = dateFormat;
		
		try{
			wrtStream = new FileOutputStream(file);
		}catch (FileNotFoundException e){
			System.err.println("Cannot instantiate Logger-Object correctly: FileNotFoundException");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Constructor #2
	 * @param path
	 * The path and filename of the Log-File
	 * @param separator
	 * The separator that splits different logs.
	 */
	public Logger( String path, String separator){
	
		this( path, separator, null);
	}
	
	// PUBLIC METHODS /////////////////////////////////////////////////////////////////////////////

	/**
	 * Writes information into the log file & appends the standard Separator-String.
	 * Caution: This Method does not close the write-Stream used!
	 * @param info
	 * The Information to write as a <i>String</i>.
	 */
	public void log(String info){
		
		log(info, sep);
	}
	
	/**
	 * Writes information into the log file & appends a individual Separator-String.
	 * While not using the specified separator defined at construction time.
	 * Caution: This Method does not close the write-Stream used!
	 * @param info
	 * The Information to write as a <i>String</i>.
	 * @param sep
	 * A separator used in this logging action as a <i>String</i>.
	 */
	public void log(String info, String sep){
		
		String datestring ="";
		if( dateFormat != null) {
			datestring = dateFormat.format( new Date()) + ": ";
		}
		
		try{
			// date / time
			for( int i=0; i<datestring.length(); i++) {
				wrtStream.write( datestring.charAt(i));
			}
			
			// info
			for(int i=0; i<info.length(); i++) {
				wrtStream.write(info.charAt(i));
			}
			
			// separator
			for(int i=0; i<sep.length(); i++) {
				wrtStream.write(sep.charAt(i));
			}
		
			wrtStream.flush();
		
		}catch(IOException e){
			System.err.println("Error while logging information to\n" + file.getPath() + ": IOException.");
			e.printStackTrace();
		}	
	}
	
	/**
	 * Clears the log file so that it is empty.
	 * <Strong>Caution:</i> Currently not implemented.
	 */
	public void clearLogFile()
	{
		closeStream();
		
		try{
			wrtStream = new FileOutputStream(file);
		}catch (FileNotFoundException e){
			System.err.println("Cannot instantiate Logger-Object correctly: FileNotFoundException");
			e.printStackTrace();
		}
	}

	
	/**
	 * Closes the FileOutputstream.
	 */
	public void closeStream(){
		try{
			wrtStream.close();
		}catch (IOException e){
			System.err.println("Closing of the WriteStream for Logger didn't work: IOException");
			e.printStackTrace();
		}
	}
	
	// GETTERS & SETTERS //////////////////////////////////////////////////////////////////////////

	/**
	 * Retrieves the file in which to write the log.
	 * @return The log <i>File</i>.
	 */
	public File getFile(){
		return file;
	}
	
	/**
	 * Retrieves the out Stream that is used to write the log.
	 * @return The used <i>FileOutputStream</i>.
	 * 
	 */
	public FileOutputStream getWriteStream()
	{
		return wrtStream;
	}
	
	/**
	 * Retrieves the specified separator.
	 * @return The separator as a <i>String</i>.
	 */
	public String getSeparator()
	{
		return sep;
	}
	
	/**
	 * Retrieves the Format that is used to format the date/time values.
	 * @return The <i>DateFormat</i>.
	 */
	public DateFormat getDateFormat()
	{
		return dateFormat;
	}
}