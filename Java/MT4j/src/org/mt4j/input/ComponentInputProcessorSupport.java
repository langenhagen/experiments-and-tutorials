/***********************************************************************
 * mt4j Copyright (c) 2008 - 2009 Christopher Ruff, Fraunhofer-Gesellschaft All rights reserved.
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mt4j.components.MTComponent;
import org.mt4j.input.inputData.MTInputEvent;
import org.mt4j.input.inputProcessors.componentProcessors.AbstractComponentProcessor;

import processing.core.PApplet;

/**
 * The Class ComponentInputProcessorSupport. Keeps the list of registered component input processors for
 * one component and sorts them by locking priority.
 * 
 * @author Christopher Ruff
 */
public class ComponentInputProcessorSupport implements IMTInputEventListener /*, IGestureEventListener*/ {
	
	/** The registered processors. */
	private List<AbstractComponentProcessor> registeredProcessors;
	
	/** The associated component. */
	private MTComponent associatedComponent;
	
	/**
	 * Instantiates a new component input processor support.
	 *
	 * @param graphicsContext the graphics context
	 * @param associatedComponent the associated component
	 */
	public ComponentInputProcessorSupport(PApplet graphicsContext, MTComponent associatedComponent) {
		super();
				
		this.associatedComponent = associatedComponent;
		this.registeredProcessors = new ArrayList<AbstractComponentProcessor>(5);
	}


	/* (non-Javadoc)
	 * @see org.mt4j.input.IMTInputEventListener#processInputEvent(org.mt4j.input.inputData.MTInputEvent)
	 */
	public boolean processInputEvent(MTInputEvent inEvt){
//		/*
		boolean handled = false;
		
		//Call preProcess on _all_ processors before calling processInputEvent on each of them
		//to allow each processor to take part in the locking mechanism before anyone actually locks something
		for (AbstractComponentProcessor p : registeredProcessors) {
			if (p.isInterestedIn(inEvt) && this.associatedComponent.isGestureAllowed(p.getClass())){
				p.preProcess(inEvt);
			}
		}
		
		for (int i = 0; i < registeredProcessors.size(); i++) {
			AbstractComponentProcessor inputProcessor = registeredProcessors.get(i);
			//Send events
			if (inputProcessor.isInterestedIn(inEvt) && this.associatedComponent.isGestureAllowed(inputProcessor.getClass())){
				handled = true;
				inputProcessor.processInputEvent(inEvt);
			}
		}
		return handled;
//		*/
	}
	
	
	/**
	 * Register input processor.
	 *
	 * @param inputProcessor the input processor
	 */
	public synchronized void registerInputProcessor(AbstractComponentProcessor inputProcessor){
		if (!this.registeredProcessors.contains(inputProcessor)){
			this.registeredProcessors.add(inputProcessor);
			
			//Sort the list so that the cursor processors
			//with the highest locking priority get served first
			Collections.sort(registeredProcessors,  Collections.reverseOrder());
			
			//-maybe put all this back to mtcomp?
			inputProcessor.addGestureListener(associatedComponent);
		}
	}
	
	/**
	 * Unregister input processor.
	 *
	 * @param inputProcessor the input processor
	 */
	public synchronized void unregisterInputProcessor(AbstractComponentProcessor inputProcessor){
		if (this.registeredProcessors.contains(inputProcessor)){
			this.registeredProcessors.remove(inputProcessor);
		}
	}
	
	/**
	 * Gets the input processors.
	 *
	 * @return the input processors
	 */
	public AbstractComponentProcessor[] getInputProcessors(){
		return this.registeredProcessors.toArray(new AbstractComponentProcessor[this.registeredProcessors.size()]);
	}
	
	

}
