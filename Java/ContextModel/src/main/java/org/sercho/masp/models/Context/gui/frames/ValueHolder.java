/**
 * 
 */
package org.sercho.masp.models.Context.gui.frames;

import org.sercho.masp.models.Context.Place;
import org.sercho.masp.models.Context.Vector;
import org.sercho.masp.models.Context.gui.enums.ActionType;

/**
 * An interface to provide data while creating an element.
 * 
 * @author Andre Schulz
 * @since 1.2.46
 */
public interface ValueHolder {

    /**
     * Return the source {@link Vector}.
     * 
     * @return The source {@link Vector}.
     */
    public Place getSource();

    /**
     * Return the target {@link Vector}.
     * 
     * @return The target {@link Vector}.
     */
    public Place getTarget();

    /**
     * Returns the {@link ActionType}.
     * 
     * @return The {@link ActionType}.
     */
    public ActionType getActionType();
}
