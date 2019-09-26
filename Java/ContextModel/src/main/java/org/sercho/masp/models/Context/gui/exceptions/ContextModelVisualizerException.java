package org.sercho.masp.models.Context.gui.exceptions;

/**
 * The root exception.
 * 
 * 
 */
public abstract class ContextModelVisualizerException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 6202390525268170809L;

    protected ContextModelVisualizerException() {
    }

    protected ContextModelVisualizerException(final String message) {
        super(message);
    }

}
