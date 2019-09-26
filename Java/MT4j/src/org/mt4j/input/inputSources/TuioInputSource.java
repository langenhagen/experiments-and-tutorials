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
package org.mt4j.input.inputSources;


import java.util.HashMap;
import java.util.Map;

import org.mt4j.MTApplication;
import org.mt4j.input.inputData.ActiveCursorPool;
import org.mt4j.input.inputData.InputCursor;
import org.mt4j.input.inputData.MTFiducialInputEvt;
import org.mt4j.input.inputData.MTFingerInputEvt;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.logging.ILogger;
import org.mt4j.util.logging.MTLoggerFactory;

import TUIO.TuioClient;
import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;


/**
 * This class implements the TuioListener interface
 * and generates TouchEvents with associated Cursors.
 * It then passes them on to the registered Listeners
 * that have to implement the <code>IinputSourceListener</code>
 * interface.
 * 
 * @author Christopher Ruff
 */
public class TuioInputSource extends AbstractInputSource implements TuioListener {	
	private static final ILogger logger = MTLoggerFactory.getLogger(TuioInputSource.class.getName());
	static{
		logger.setLevel(ILogger.INFO);
	}

	/** The port. */
	private int port;
	
	/** The tuio client. */
	private TuioClient tuioClient;
	
	/** The window width. */
	private int windowWidth;
	
	/** The window height. */
	private int windowHeight;
	
	/** this is needed to track which events got fired as a finger down event already. */
	private Map<Long, Long> tuioIDToCursorID;

	/** this is needed to track which events got fired as a fiducial down event already. */
	private Map<Long, Long> tuioFiducialIDMap;
	
	/**
	 * Instantiates a new tuio input source.
	 * 
	 * @param pa the pa
	 */
	public TuioInputSource(MTApplication pa){
		this(pa, 3333);
	}
	
	/**
	 * Instantiates a new tuio input source.
	 * 
	 * @param pa the pa
	 * @param port the port
	 */
	public TuioInputSource(MTApplication pa, int port){
		super(pa);
		
		this.port 	= port;
		tuioClient 	= new TuioClient(this.port);
		logger.info("Initializing TUIO input on port: " + this.port);
//		tuioClient.connect();
		tuioClient.addTuioListener(this);
		
		tuioIDToCursorID = new HashMap<Long, Long>();
		tuioFiducialIDMap = new HashMap<Long, Long>();
		
		windowWidth = MT4jSettings.getInstance().getWindowWidth();
		windowHeight = MT4jSettings.getInstance().getWindowHeight();
	}
	
	
	@Override
	public void onRegistered() {
		super.onRegistered();
		tuioClient.connect();
	}

	@Override
	public void onUnregistered() {
		super.onUnregistered();
		tuioClient.disconnect();
	}
	
	//@Override
	public void addTuioCursor(TuioCursor cursor) {
		float absoluteX =	cursor.getX() * windowWidth;
		float abosulteY =	cursor.getY() * windowHeight;
		long sessionID = cursor.getSessionID();
		
//		logger.info("TUIO INPUT ADD FINGER - TUIO ID: " + sessionID);
		InputCursor c = new InputCursor();
		MTFingerInputEvt touchEvt = new MTFingerInputEvt(this, absoluteX, abosulteY, MTFingerInputEvt.INPUT_STARTED, c);
		long cursorID = c.getId();
		ActiveCursorPool.getInstance().putActiveCursor(cursorID, c);
		tuioIDToCursorID.put(sessionID, cursorID);
		this.enqueueInputEvent(touchEvt);
	}

	
	//@Override
	public void updateTuioCursor(TuioCursor cursor) {
		float absoluteX =	cursor.getX() * windowWidth;
		float abosulteY =	cursor.getY() * windowHeight;
		long sessionID = cursor.getSessionID();
		Long tuioID = tuioIDToCursorID.get(sessionID);
		if (tuioID != null ){
//			logger.info("TUIO INPUT UPDATE FINGER - TUIO ID: " + sessionID);
			InputCursor c = ActiveCursorPool.getInstance().getActiveCursorByID(tuioID);
			if (c != null){
				MTFingerInputEvt te = new MTFingerInputEvt(this, absoluteX, abosulteY, MTFingerInputEvt.INPUT_UPDATED, c);
				this.enqueueInputEvent(te);
			}else{
//				logger.error("CURSOR NOT IN ACTIVE CURSOR LIST! TUIO ID: " + cursor.getSessionID());
			}
		}
	}

	
	//@Override
	public void removeTuioCursor(TuioCursor cursor) {
			float absoluteX =	cursor.getX() * windowWidth;
			float abosulteY =	cursor.getY() * windowHeight;
			long sessionID = cursor.getSessionID();
//			logger.info("TUIO INPUT REMOVE FINGER - TUIO ID: " + sessionID);
			Long lCursorID = tuioIDToCursorID.get(sessionID);
			if (lCursorID != null){
				long cursorID = lCursorID;
				InputCursor c = ActiveCursorPool.getInstance().getActiveCursorByID(cursorID);
				if (c != null){
					MTFingerInputEvt te = new MTFingerInputEvt(this, absoluteX, abosulteY, MTFingerInputEvt.INPUT_ENDED, c);
					tuioIDToCursorID.remove(sessionID);
					ActiveCursorPool.getInstance().removeCursor(cursorID);
					this.enqueueInputEvent(te);
				}else{
//					logger.info("ERROR WHEN REMOVING FINGER - TUIO ID: " + cursor.getSessionID() + " - Cursor not in active cursor pool!");
					tuioIDToCursorID.remove(sessionID);
				}
			}else{
//				logger.info("ERROR WHEN REMOVING FINGER - TUIO ID: " + cursor.getSessionID() + " - Cursor not in tuioIDMap! - probably removed before an update event got fired!");
			}
	}
	
	
	//@Override
	public void refresh(TuioTime arg0) {
		
	}
	
	
	//@Override
	public void addTuioObject(TuioObject tuioObject) {
		long session_id = tuioObject.getSessionID();
		int fiducial_id = tuioObject.getSymbolID();
		float angle = tuioObject.getAngle();
		float x_speed = tuioObject.getXSpeed();
		float y_speed = tuioObject.getYSpeed();
		float r_speed = tuioObject.getRotationSpeed();
		float r_accel = tuioObject.getRotationAccel();
		float m_accel = tuioObject.getMotionAccel();
		float absoluteX =	tuioObject.getX() * windowWidth;
		float abosulteY =	tuioObject.getY() * windowHeight;
//		logger.info("Added TuioObj tuio object-> sessionID: " + session_id + " fiducialID: " + fiducial_id + " xpos:" + tuioObject.getX() + " ypos:" + tuioObject.getY() + " angle:" + angle + " x_speed:" + x_speed + " y_speed:" + y_speed + " r_speed:" + r_speed + " m_accel:" + m_accel + " r_accel:" + r_accel);
		
		InputCursor c = new InputCursor();
		MTFiducialInputEvt fiducialEvt = new MTFiducialInputEvt(this, absoluteX, abosulteY, MTFiducialInputEvt.INPUT_STARTED, c, fiducial_id, angle, x_speed, y_speed, r_speed, m_accel, r_accel);
		long cursorID = c.getId(); //TODO do implicitly somehow
		ActiveCursorPool.getInstance().putActiveCursor(cursorID, c);
		tuioFiducialIDMap.put(session_id, cursorID);
		this.enqueueInputEvent(fiducialEvt);
	}
	
	
	//@Override
	public void updateTuioObject(TuioObject tuioObject) {
		long session_id = tuioObject.getSessionID();
		int fiducial_id = tuioObject.getSymbolID();
		float angle = tuioObject.getAngle();
		float x_speed = tuioObject.getXSpeed();
		float y_speed = tuioObject.getYSpeed();
		float r_speed = tuioObject.getRotationSpeed();
		float r_accel = tuioObject.getRotationAccel();
		float m_accel = tuioObject.getMotionAccel();
//		logger.info("Update TuioObj tuio object-> sessionID: " + session_id + " fiducialID: " + fiducial_id + " xpos:" + tuioObject.getX() + " ypos:" + tuioObject.getY() + " angle:" + angle + " x_speed:" + x_speed + " y_speed:" + y_speed + " r_speed:" + r_speed + " m_accel:" + m_accel + " r_accel:" + r_accel);
		float absoluteX =	tuioObject.getX() * windowWidth;
		float abosulteY =	tuioObject.getY() * windowHeight;
		
		Long tuioID = tuioFiducialIDMap.get(session_id);
		if (tuioID != null ){
			InputCursor c = ActiveCursorPool.getInstance().getActiveCursorByID(tuioID);
			if (c != null){
				MTFiducialInputEvt fiducialEvt = new MTFiducialInputEvt(this, absoluteX, abosulteY, MTFiducialInputEvt.INPUT_UPDATED, c, fiducial_id, angle, x_speed, y_speed, r_speed, m_accel, r_accel);
				this.enqueueInputEvent(fiducialEvt);
			}
		}
	}
	

	//@Override
	public void removeTuioObject(TuioObject tuioObject) {
//		logger.info("Remove TuioObj tuio object-> sessionID: " +session_id + " fiducialID: " +fiducial_id);
		long session_id = tuioObject.getSessionID();
		int fiducial_id = tuioObject.getSymbolID();

		Long cursorIDL = tuioFiducialIDMap.get(session_id);
		if (cursorIDL != null){
			long cursorID = cursorIDL;
			InputCursor c = ActiveCursorPool.getInstance().getActiveCursorByID(cursorID);
			if (c != null){
				MTFiducialInputEvt te;
				if (c.getCurrentEvent() != null)
					te = new MTFiducialInputEvt(this, c.getCurrentEvent().getX(), c.getCurrentEvent().getY(), MTFiducialInputEvt.INPUT_ENDED, c, fiducial_id);
				else
					te = new MTFiducialInputEvt(this, 0,0, MTFiducialInputEvt.INPUT_ENDED, c, fiducial_id);

				tuioFiducialIDMap.remove(session_id);
				ActiveCursorPool.getInstance().removeCursor(cursorID);
				this.enqueueInputEvent(te);
			}else{
//				logger.info("ERROR WHEN REMOVING TUIOOBJECT - TUIO ID: " + session_id + " - Cursor not in active cursor pool!");
				tuioFiducialIDMap.remove(session_id);
			}
		}else{
			logger.info("ERROR WHEN REMOVING TUIOOBJECT - TUIO ID: " + session_id + " - Cursor not in tuioFiducialIDMap!");
		}
	}

	
	
	
//	//@Override
//	public boolean firesEventType(Class<? extends MTInputEvent> evtClass){
//		return (	evtClass == MTFingerInputEvt.class 
//				|| 	evtClass == MTFiducialInputEvt.class);
//	}

}
