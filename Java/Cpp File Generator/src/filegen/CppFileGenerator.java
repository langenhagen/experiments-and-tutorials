package filegen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import logger.Logger;


/**
 * This class batch generates h/hpp and cpp files according to your
 * wishes/shemes, related to directories and comment-templates.
 * 
 * @author Barn
 * @version 2012110
 */
public class CppFileGenerator {

	// INSTANCE VARS //////////////////////////////////////////////////////////////////////////////
	
	/**
	 * The logger of this file generator.
	 */
	private Logger logger;
	
	/**
	 * Indicates, if something failed while generation.
	 */
	private boolean errorOccured;
	
	private String author;
	private String namespace;
	private String hPath;
	private String cppPath;
	private String hTemplateFile;
	private String hppTemplateFile;
	private String cppTemplateFile;
	
	/**	the list of header files */
	private List<String> headerFiles = new LinkedList<>();
	/** the list of file types h or hpp */
	private List<String> fileTypes = new LinkedList<>();
	/** whether or not to create a cpp to the corresponding h/hpp */
	private List<Boolean> createcpp = new LinkedList<>();
	
	// CONSTRUCTOR ////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructor #1
	 * @param loggerfile
	 * The file that contains the log.
	 */
	public CppFileGenerator( String loggerfile)
	{
		// clear loggerfile and create logger
		writeFile(loggerfile, "", false);
		logger = new Logger( loggerfile, "\n", new SimpleDateFormat("HH:mm:ss "));
	}
	
	/**
	 * Constructor #2.<br>
	 * The log file will be named "FileGen.log"-
	 */
	public CppFileGenerator()
	{
		this( "FileGen.log");
	}
	
	
	// PUBLIC METHODS /////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Parses the config file and creates the files.
	 * Does not overwrite existing files.
	 * If any template can not be found, 
	 * the method does effectively nothing.
	 * @param fname The path of the config file.
	 */
	public void process( String fname){
	
		errorOccured = false;
	
		parseConfigFile( fname);
	
		String hTemplate = readFile( hTemplateFile, true);
		String hppTemplate = readFile( hppTemplateFile, true);
		String cppTemplate = readFile( cppTemplateFile, true);
		
		if(		hTemplate == null ||
				hppTemplate == null || 
				cppTemplate == null){
			return;
		}
		
		// for every file
		for( int i=0; i < headerFiles.size(); i++){
		
			String template="";
		
			if(fileTypes.get(i).equalsIgnoreCase("h")) {
				template = hTemplate;
			}
			else if(fileTypes.get(i).equalsIgnoreCase("hpp")) {
				template = hppTemplate;
			}
		
			// generate h/hpp files!
			String hFile = headerFiles.get(i);
			File file = new File(  hPath + hFile);
		
			if( ! (file.exists())){
				file.getParentFile().mkdirs();
				writeFile( hPath + hFile, applyTemplate( template, hFile), true);
			}else{
				logger.log( file.getName() + " exists already and will not be created.");
				errorOccured = true;
			}
	
			// generate cpp-Files
			if( createcpp.get(i)){
	
				String cppFile = hFile.substring(0, hFile.lastIndexOf(".")) + ".cpp";
				file = new File(  cppPath + cppFile);
	
				if( ! ( file.exists())){
					file.getParentFile().mkdirs();
					writeFile( cppPath + cppFile, applyTemplate( cppTemplate, hFile), true);
				}else{
					logger.log( file.getName() + " exists already and will not be created.");
					errorOccured = true;
				}
			}
		}
	
		if( errorOccured){
			logger.log( "\n\nDONE. Some file(s) exist already.\n");
		}else{
			logger.log( "\n\nDONE. Everything OK.\n");
		}
		
		logger.closeStream();
	}


	/**
	 * Parses a file and retrieves the variables, but doesn't build anything.
	 * <strong>Note:</strong> It is not necessary to invoke this method if you want to
	 * process the config with process(). Instead, this function may be useful to be
	 * invoked separately, if you want to gain access to the variables in the config file through
	 * the CppFileGenerator.get*() methods.
	 * @param filename
	 * The file to be parsed.
	 */
	public void parseConfigFile( String filename)
	{
	
		// (re)initialize the lists
		this.createcpp.clear();
		this.fileTypes.clear();
		this.headerFiles.clear();
		
		logger.log( "Parsing config file \"" + filename + "\"... \t");
		
		try{
		
			BufferedReader reader = new BufferedReader( new FileReader( filename));
		
			// process every line
			String val = reader.readLine();		
			while( val != null){
			
				// process comments
				if( val.length()==0 || "#".equalsIgnoreCase(val.substring(0, 1 ))){
					val = reader.readLine();
					continue;
				}
			
				// get keys and values
				String key;
				if( val.indexOf(":")!=-1){
					key = val.substring(0, val.indexOf(":")).trim().toLowerCase();
					val = val.substring(val.indexOf(":")+1, val.length()).trim();
				}else{
					key = "FILES_TO_CREATE";
				}
			
			
				// process lines for the keys
				if( key.equalsIgnoreCase("author")){				// author name
			
					author = val;
			
				}else if( key.equalsIgnoreCase( "namespace")){
			
					namespace = val;
			
				}else if( key.equalsIgnoreCase("Hpath")){			// h/hpp standard path
			
					hPath = makeBackSlashesForwardSlashes( val, true);					
			
				}else if( key.equalsIgnoreCase("CppPath")){			// cpp standard path
			
					cppPath = makeBackSlashesForwardSlashes( val, true);
			
				}else if( key.equalsIgnoreCase("HTemplate")){		// H Template path
			
					hTemplateFile = makeBackSlashesForwardSlashes( val, false);
			
				}else if( key.equalsIgnoreCase("HppTemplate")){		// Hpp Template path
			
					hppTemplateFile = makeBackSlashesForwardSlashes( val, false);
			
				}else if( key.equalsIgnoreCase("CppTemplate")){		// Cpp Template path
			
					cppTemplateFile = makeBackSlashesForwardSlashes( val, false);
			
			
			
				}else if( key.equalsIgnoreCase("FILES_TO_CREATE")){		// normal .h/.hpp files
			
					// -nocpp
					createcpp.add( ! val.toLowerCase().contains("-nocpp"));
			
					val = makeBackSlashesForwardSlashes( val.replaceAll("-nocpp", ""), false);
					headerFiles.add(val);
			
					fileTypes.add( val.substring(val.lastIndexOf(".")+1).toLowerCase());
				}
			
				val = reader.readLine();	
			}
		
			reader.close();
		}catch(IOException e){
		
			logger.log( "Failed!");		
			logger.log( e.getMessage());
			logger.log( e.getStackTrace().toString());
		
			System.exit( -1);
		}
		
		logger.log( "Done.");
	}
	

	// GETTERS ////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Retrieves the logger of this file generator.
	 * @return
	 * The <i>Logger</i>
	 */
	public Logger getLogger(){
	
		return logger;
	}
	
	/**
	 * Retrieves the author defined in the config file.
	 * @return The alias of the author as a <i>String</i> or null, if nothing has been parsed so far.
	 */
	public String getAuthor(){
		return author;
	}
	
	/**
	 * Retrieves the namespace defined in the config file.
	 * @return The namespace as a <i>String</i> or null, if nothing has been parsed so far.
	 */
	public String getNamespace(){
		return namespace;
	}
	
	/**
	 * Retrieves the HPath defined in the config file.
	 * @return The HPath of as a <i>String</i> or null, if nothing has been parsed so far.
	 */
	public String getHPath(){
		return hPath;
	}
	
	/**
	 * Retrieves the CppPath defined in the config file.
	 * @return The CppPath of as a <i>String</i> or null, if nothing has been parsed so far.
	 */
	public String getCppPath(){
		return cppPath;
	}

	/**
	 * Retrieves the path to the HTemplate file defined in the config file.
	 * @return The path of the HTemplate as a <i>String</i> or null, if nothing has been parsed so far.
	 */
	public String getHTemplateFilePath(){
		return hTemplateFile;
	}

	/**
	 * Retrieves the path to the HppTemplate file defined in the config file.
	 * @return The path of the HppTemplate as a <i>String</i> or null, if nothing has been parsed so far.
	 */
	public String getHppTemplateFilePath(){
		return hppTemplateFile;
	}

	/**
	 * Retrieves the path to the CppTemplate file defined in the config file.
	 * @return The path of the CppTemplate as a <i>String</i> or null, if nothing has been parsed so far.
	 */
	public String getCppTemplateFilePath(){
		return cppTemplateFile;
	}


	// PRIVATE HELPERS ////////////////////////////////////////////////////////////////////////////
	
	
	/**
		 * Parses a template-string and subtitutes placeholders through concrete values.
		 * @param template
		 * The template as a <i>String</i>.
		 * @param filename
		 * The filename of the actual header file with path from root of includedir or whatever to create as a <i>String</i>.
		 * @return
		 * The parsed template as a <i>String</i>.
	 */
	private String applyTemplate( String template, String filename){
		
		
		/* ****************************
		 * key strings are:
		 * 
		 * %filename% 
		 * %author%
		 * %date%
		 * %ifndef%	
		 * %define%
		 * %endif%
		 * %includeheader%
		 * 
		 *************************** */
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		
		// calc preprocFileAlias	(stuff like __FooBar_HPP__ )
		int startPos = filename.lastIndexOf("/") == -1 ? 0 : filename.lastIndexOf("/")+1;
		String preprocFileAlias = "__" + filename.substring( startPos, filename.lastIndexOf(".")) + "_";
		String preprocFileExtension = filename.substring( filename.lastIndexOf(".")+1, filename.length());
		preprocFileAlias = preprocFileAlias.replaceAll(" ", "_") + preprocFileExtension.toUpperCase() + "__";
		
		// replace placeholders
		String ret = template.replaceAll("%ifndef%", "#ifndef " + preprocFileAlias);
		ret = ret.replaceAll("%define%", "#define " + preprocFileAlias);
		ret = ret.replaceAll("%endif%", "#endif /* " + preprocFileAlias + " */");
		
		ret = ret.replaceAll( "%filename%", filename.substring( startPos, filename.lastIndexOf(".")));
		ret = ret.replaceAll("%author%", author);
		ret = ret.replaceAll("%includeheader%", "#include \"" + filename + "\"");
		ret = ret.replaceAll( "%date%", dateFormat.format(new Date()));
		ret = ret.replaceAll( "%namespace%", namespace);
		
		return ret;
	}
	
	/**
	 * Reads a file and returns it as a String.
	 * @param filename
	 * The file to be read.
	 * @return
	 * A <i>String</i>.
	 * @param logIt
	 * Indicates, if this action shall be logged or not.<br>
	 * TRUE - log the action<br>
	 * FALSE - do not log the action.
	 */
	private String readFile( String filename, boolean logIt)
	{
		if( logIt){
			logger.log( "Reading template file \"" + filename + "\"... \t");
		}
		
		String ret = new String();
		
		try{
		BufferedReader reader = new BufferedReader( new FileReader( filename));
		String line = reader.readLine();
		while(line != null){
			ret = ret + line + "\n";
		
			line = reader.readLine();
		}
		
		reader.close();
		
		}catch (Exception e){
			if( logIt){
				logger.log( "Failed!");
				String eString1 = e.getMessage();
				String eString2 = e.getStackTrace().toString();
				
				if( eString1 != null){
					logger.log( eString1);
				}
				if( eString2 != null){
					logger.log( eString2);
				}
				
			}
			return null;
		}
		
		if( logIt){
			logger.log( "Done.");
		}
		
		return ret;
	}
	
	/**
	 * Writes the specified text into a file.
	 * @param filename
	 * The name of the file with path as a <i>String</i>.
	 * @param text
	 * The content of the file as a <i>String</i>.
	 * @param logIt
	 * Indicates, if this action shall be logged or not.<br>
	 * TRUE - log the action<br>
	 * FALSE - do not log the action.
	 */
	private void writeFile( String filename, String text, boolean logIt){
	
		if( logIt){
			logger.log( "Writing file \"" + filename + "\"... \t");
		}
		
		try{
			BufferedWriter out = new BufferedWriter(  new FileWriter(filename));
			out.write(text);
			out.close();
		}
		catch (Exception e){
		
			if( logIt){
				logger.log( "Failed!");
				logger.log( e.getMessage());
				logger.log( e.getStackTrace().toString());
			}
			System.exit( -1);
		}
		
		if( logIt){
			logger.log( "Done.");
		}
	}
	
	/**
	 * Processes a string to change backslashes into forward slashes.
	 * If specified, and not already there, also adds a trailing slash. 
	 * @param str
	 * The string to process
	 * @param addTrailingSlash
	 * If set to true, adds a trailing slash, does not, if set to FALSE.
	 * Note, that already existing trailing slashes won't be erased if set to true.
	 * @return
	 * Returns a <i>String</i> with no backslashes.
	 */
	private String makeBackSlashesForwardSlashes( String str, boolean addTrailingSlash){
	
		str = str.trim().replaceAll("\\\\", "/");
		if( addTrailingSlash && str.lastIndexOf("/") != str.length()-1) {
			str = str + "/";
		}
		return str;
	}

} // END class CppFileGenerator