package org.sercho.masp.models.Context.gui.exceptions;

import java.util.List;

/**
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public class NoExistingElementException extends ContextModelVisualizerException {

    /**
     * 
     */
    private static final long serialVersionUID = -1036494152395229940L;

    private final List<String> elementsIds;

    public NoExistingElementException(final String specificMessage,
            final List<String> elementsIds) {
        super(specificMessage + elementsIds);
        this.elementsIds = elementsIds;
    }

    public List<String> getElementId() {
        return this.elementsIds;
    }

}
