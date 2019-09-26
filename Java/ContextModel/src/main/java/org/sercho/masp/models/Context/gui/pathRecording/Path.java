package org.sercho.masp.models.Context.gui.pathRecording;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * This class represents a path, with information about the initial coordinates
 * and the target coordinates. Each path is identified by a unique pathId. Each
 * path belongs to an element which identifier is elementId.
 * 
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public class Path {

    private String elementId;

    private int pathId;

    private int startTime;

    private int initialX;

    private int targetX;

    private int initialY;

    private int targetY;

    public Path(final String elementId, final int pathId, final int startTime,
            final int initialX, final int targetX, final int initialY, final int targetY) {
        this.elementId = elementId;
        this.startTime = startTime;
        this.initialX = initialX;
        this.targetX = targetX;
        this.initialY = initialY;
        this.targetY = targetY;
        this.pathId = pathId;
    }

    public Path() {
    }

    @XmlAttribute
    public int getPathId() {
        return this.pathId;
    }

    public void setPathId(final int pathId) {
        this.pathId = pathId;
    }

    public String getElementId() {
        return this.elementId;
    }

    public void setElementId(final String elementId) {
        this.elementId = elementId;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public void setStartTime(final int startTime) {
        this.startTime = startTime;
    }

    public int getInitialX() {
        return this.initialX;
    }

    public void setInitialX(final int initialX) {
        this.initialX = initialX;
    }

    public int getTargetX() {
        return this.targetX;
    }

    public void setTargetX(final int targetX) {
        this.targetX = targetX;
    }

    public int getInitialY() {
        return this.initialY;
    }

    public void setInitialY(final int initialY) {
        this.initialY = initialY;
    }

    public int getTargetY() {
        return this.targetY;
    }

    public void setTargetY(final int targetY) {
        this.targetY = targetY;
    }

    @Override
    public String toString() {
        return new String("Element id: " + this.elementId + " | Path id: " + this.pathId + " | Start time: " + this.startTime + " | (" + this.initialX + " | " + this.initialY + ")->(" + this.targetX + " | " + this.targetY + ") ");
    }
}
