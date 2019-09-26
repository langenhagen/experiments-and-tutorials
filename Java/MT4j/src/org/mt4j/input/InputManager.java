/***********************************************************************
 * mt4j Copyright (c) 2008 - 2009, C.Ruff, Fraunhofer-Gesellschaft All rights reserved.
 *  
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 ***********************************************************************/
package org.mt4j.input;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.mt4j.MTApplication;
import org.mt4j.input.inputProcessors.globalProcessors.AbstractGlobalInputProcessor;
import org.mt4j.input.inputSources.AbstractInputSource;
import org.mt4j.input.inputSources.IinputSourceListener;
import org.mt4j.input.inputSources.KeyboardInputSource;
import org.mt4j.input.inputSources.MouseInputSource;
import org.mt4j.input.inputSources.MultipleMiceInputSource;
import org.mt4j.input.inputSources.TuioInputSource;
import org.mt4j.input.inputSources.Win7NativeTouchSource;
import org.mt4j.sceneManagement.Iscene;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.logging.ILogger;
import org.mt4j.util.logging.MTLoggerFactory;



/**
 * Manages the InputSources and Inputprocessors for each scene.
 * Starts up the default input sources.
 * 
 * @author Christopher Ruff
 */
public class InputManager {
	/** The Constant logger. */
	private static final ILogger logger = MTLoggerFactory.getLogger(InputManager.class.getName());
	static{
//		logger.setLevel(ILogger.ERROR);
//		logger.setLevel(ILogger.DEBUG);
		logger.setLevel(ILogger.INFO);
	}
	
	/** The registered input sources. */
	private List<AbstractInputSource> registeredInputSources;
	
	/** The In processor to scene. */
	private Map<AbstractGlobalInputProcessor, Iscene> inputProcessorsToScene;
	
	/** The pa. */
	private MTApplication app;
	
	
	/**
	 * Instantiates a new input manager.
	 * 
	 * @param pa the processing context
	 */
	public InputManager(MTApplication pa) {
		this(pa, true);
	}
	
	
	/**
	 * Instantiates a new input manager.
	 * 
	 * @param pa the processing context
	 */
	public InputManager(MTApplication pa, boolean registerDefaultSources) {
		super();
		this.registeredInputSources	= new ArrayList<AbstractInputSource>();
		this.inputProcessorsToScene = new HashMap<AbstractGlobalInputProcessor, Iscene>();
		this.app = pa;
		
		if (registerDefaultSources)
			this.registerDefaultInputSources();
	}
	
	
	/**
	 * Initialize default input sources.
	 */
	protected void registerDefaultInputSources(){
		boolean enableMultiMouse = false;
		Properties properties = new Properties();

		try {
			FileInputStream fi = new FileInputStream(MT4jSettings.getInstance().getDefaultSettingsPath() + "Settings.txt");
			properties.load(fi); 
			enableMultiMouse = Boolean.parseBoolean(properties.getProperty("MultiMiceEnabled", "false").trim());
		}catch (Exception e) {
			logger.debug("Failed to load Settings.txt from the File system. Trying to load it from classpath..");
			try {
				InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("Settings.txt");
				if (in != null){
					properties.load(in);
					enableMultiMouse = Boolean.parseBoolean(properties.getProperty("MultiMiceEnabled", "false").trim());
				}else{
					logger.debug("Couldnt load Settings.txt as a resource. Using defaults.");
				}
			} catch (IOException e1) {
				logger.error("Couldnt load Settings.txt. Using defaults.");
				e1.printStackTrace();
			}
		}

		if (enableMultiMouse){
			try {
				//Register single or multiple mice input source
				int connectedMice = MultipleMiceInputSource.getConnectedMouseCount();
				//	    		/*
				logger.info("Found mice: " + connectedMice);
				if (connectedMice >= 2){ //FIXME should be > 1, but manymouse often detects more that arent there!?
					logger.info("-> Multiple Mice detected!");
					MultipleMiceInputSource multipleMice = new MultipleMiceInputSource(app);
	    			multipleMice.setMTApp(app);
	    			this.registerInputSource(multipleMice);
	    			this.hideCursorInFrame();
	    		}else{
//	    			*/
	    			MouseInputSource mouseInput = new MouseInputSource(app);
	    			this.registerInputSource(mouseInput);
	    		}
//	    		*/
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		//Use default mouse input source
	    		MouseInputSource mouseInput = new MouseInputSource(app);
	    		this.registerInputSource(mouseInput);
	    	}
	    }
	    else{
//	    	*/
	    	MouseInputSource mouseInput = new MouseInputSource(app);
	    	this.registerInputSource(mouseInput);
	    }
//	    */

	    //Check if we run windows 7
	    if (System.getProperty("os.name").toLowerCase().contains("windows 7")){
	    	Win7NativeTouchSource win7NativeInput = new Win7NativeTouchSource(app);
	    	if (win7NativeInput.isSuccessfullySetup()){
	    		this.registerInputSource(win7NativeInput);
	    	}
	    }
	    
	    //check which versions it supports and only start there!
	    /*
	    if (System.getProperty("os.name").toLowerCase().contains("mac os x")){
	    	this.registerInputSource(new MacTrackpadSource(app));
	    }
	    */

	    KeyboardInputSource keyInput= new KeyboardInputSource(app);
		TuioInputSource tuioInput 	= new TuioInputSource(app);
//		MuitoInputSource muitoInput = new MuitoInputSource(pa, "localhost", 6666);
		
		this.registerInputSource(keyInput);
		this.registerInputSource(tuioInput);
	}
	
	
	/**
	 * Registers a new input source for the application.
	 * 
	 * @param newInputSource the new input source
	 */
	public void registerInputSource(AbstractInputSource newInputSource){
		if (!registeredInputSources.contains(newInputSource)){
			registeredInputSources.add(newInputSource);
			//Add all processors to the new input source
			Set<AbstractGlobalInputProcessor> set = inputProcessorsToScene.keySet();
            for (AbstractGlobalInputProcessor processor : set) {
                //newInputSource.addInputListener(processor);
                this.saveAddInputListenerToSource(newInputSource, processor);
            }
			
			//Inform the input source that it is now registered with the application
			newInputSource.onRegistered();
		}else{
			logger.error("input source already registered! - " + newInputSource);
		}
	}
	
	
	/**
	 * Unregisters a input source.
	 * @param is the input source
	 */
	public void unregisterInputSource(AbstractInputSource is){
		synchronized (registeredInputSources) {
			if (registeredInputSources.contains(is)){
				registeredInputSources.remove(is);
				
				//Inform the input source that it is now UN-registered from the application
				is.onUnregistered();
			}
		}
	}
	
	/**
	 * Gets the input sources.
	 * @return the input sources
	 */
	public AbstractInputSource[] getInputSources(){
		return this.registeredInputSources.toArray(new AbstractInputSource[this.registeredInputSources.size()]);
	}
	
	/**
	 * Gets the registered input sources.
	 * 
	 * @return the registered input sources
	 * @deprecated use getInputSources() instead
	 */
	public Collection<AbstractInputSource> getRegisteredInputSources(){
		return this.registeredInputSources;
	}
	
	
	/**
	 * Hides the mousecursor in multiple mice mode.
	 */
	private void hideCursorInFrame(){
		int[] pixels = new int[16 * 16];
		Image image = Toolkit.getDefaultToolkit().createImage(
		        new MemoryImageSource(16, 16, pixels, 0, 16));
		Cursor transparentCursor =
		        Toolkit.getDefaultToolkit().createCustomCursor
		             (image, new Point(0, 0), "invisibleCursor");
		app.frame.setCursor(transparentCursor);
	}
	
	
	/**
	 * Registers a new inputprocessor and adds it to the inputsources as listeners.
	 * 
	 * @param scene the scene
	 * @param inputprocessor the input processor
	 */
	public void registerGlobalInputProcessor(Iscene scene, AbstractGlobalInputProcessor inputprocessor){
		//By default disable the registered global input processor, so it doesent accidently
		//send events to a not even visible scene
		//-Only enable it if the scene is the currently active scene
		//-If a scene becomes active the processors will also be enabled
//		if (app.getCurrentScene() != null && app.getCurrentScene().equals(scene)){
		if (scene.equals(app.getCurrentScene())){
			inputprocessor.setDisabled(false);
		}else{
			inputprocessor.setDisabled(true);
		}
		
		inputProcessorsToScene.put(inputprocessor, scene);
		//Register the processor with all registered inputsources
		for (AbstractInputSource source: registeredInputSources){
			this.saveAddInputListenerToSource(source, inputprocessor);
		}
	}
	
	
	private void saveAddInputListenerToSource(AbstractInputSource source, AbstractGlobalInputProcessor inputprocessor){
		//Only add input processor to input sources 
		//that fire the event type that the processor is interested in
//		if (source.firesEventType(inputprocessor.getListenEventType())){
		
			List<IinputSourceListener> sourceListener = Arrays.asList(source.getInputListeners());
			if (!sourceListener.contains(inputprocessor)){ //Prevent adding same global input processor twice
				source.addInputListener(inputprocessor);
			}
//		}
	}
	
	/**
	 * Unregisters a inputprocessor from _all_ the registered inputsources.
	 * 
	 * @param inputprocessor the input processor
	 */
	public void unregisterGlobalInputProcessor(AbstractGlobalInputProcessor inputprocessor){
		/*
		Set set = InprocessorToScene.keySet(); 
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			AbstractInputprocessor processor = (AbstractInputprocessor) iter.next();
			
			//Check if the processor is registered here with a scene
			if (processor.equals(inputprocessor)){
				if (InprocessorToScene.get(processor).equals(scene)){
					for (AbstractInputSource source: registeredInputSources){
						source.removeInputListener(inputprocessor);
					}
				}
			}
		}
		*/
		
		//Remove the input processor from the processor->scene map
		if (inputProcessorsToScene.containsKey(inputprocessor)){
			inputProcessorsToScene.remove(inputprocessor);	
		}
		
		for (AbstractInputSource source: registeredInputSources){
			source.removeInputListener(inputprocessor);
		}
	}
	
	
	/**
	 * Gets the global inputprocessors associated with the specified scene.
	 * 
	 * @param scene the scene
	 * 
	 * @return the scene inputprocessors
	 */
	public AbstractGlobalInputProcessor[] getGlobalInputProcessors(Iscene scene){
		List<AbstractGlobalInputProcessor> processors = new ArrayList<AbstractGlobalInputProcessor>();
		
		Set<AbstractGlobalInputProcessor> set = inputProcessorsToScene.keySet();
        for (AbstractGlobalInputProcessor processor : set) {
            if (inputProcessorsToScene.get(processor).equals(scene)) {
                processors.add(processor);
            }
        }
		return processors.toArray(new AbstractGlobalInputProcessor[processors.size()]);
	}
	
	/**
	 * Enables the global inputprocessors that are associated with the given scene.
	 * 
	 * @param scene the scene
	 */
	public void enableGlobalInputProcessors(Iscene scene){
		Set<AbstractGlobalInputProcessor> set = inputProcessorsToScene.keySet();
        for (AbstractGlobalInputProcessor processor : set) {
            if (inputProcessorsToScene.get(processor).equals(scene)) {
                processor.setDisabled(false);
            }
        }
	}
	
	/**
	 * Disables the global inputprocessors that are associated with the given scene.
	 * 
	 * @param scene the scene
	 */
	public void disableGlobalInputProcessors(Iscene scene){
		Set<AbstractGlobalInputProcessor> set = inputProcessorsToScene.keySet();
        for (AbstractGlobalInputProcessor processor : set) {
            if (inputProcessorsToScene.get(processor).equals(scene)) {
                processor.setDisabled(true);
            }
        }
	}
	
	
	/**
	 * Removes input processors of the specified scene from listening to the registered input sources.
	 * 
	 * @param scene the scene
	 */
	public void removeGlobalInputProcessors(Iscene scene){
		AbstractGlobalInputProcessor[] sceneProcessors = this.getGlobalInputProcessors(scene);
        for (AbstractGlobalInputProcessor abstractGlobalInputProcessor : sceneProcessors) {
            this.unregisterGlobalInputProcessor(abstractGlobalInputProcessor);
        }
	}


	
}
