package org.sercho.masp.models.Context.gui.observer;

import org.sercho.masp.models.Context.EnvironmentElement;

/**
 * @author Antonio Fernandez Zaragoza
 */
public class VisualizerEvent {

    public final static String DRAGGED = "dragged";

    private final EnvironmentElement element;

    private final String eventType;

    private final String elementId;

    private final String elementName;

    public VisualizerEvent(final EnvironmentElement element, final String eventType) {
        this.element = element;
        this.eventType = eventType;
        this.elementId = element.getId();
        this.elementName = element.getName();
    }

    public EnvironmentElement getElement() {
        return this.element;
    }

    public String getEventType() {
        return this.eventType;
    }

    public String getElementId() {
        return this.elementId;
    }

    public String getElementName() {
        return this.elementName;
    }

}
