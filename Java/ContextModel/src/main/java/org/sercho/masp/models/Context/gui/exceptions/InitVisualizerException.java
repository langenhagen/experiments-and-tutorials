package org.sercho.masp.models.Context.gui.exceptions;

import org.sercho.masp.models.Context.gui.VisualizerManager.DisplayType;

/**
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public class InitVisualizerException extends ContextModelVisualizerException {

    /**
     * 
     */
    private static final long serialVersionUID = 5755016725997870042L;

    private final DisplayType version;

    public InitVisualizerException(final String specificMessage, final DisplayType version) {

        super(specificMessage + "[ mode = " + version + "]");
        this.version = version;
    }

    public DisplayType getMode() {
        return this.version;
    }

}
