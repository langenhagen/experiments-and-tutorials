/**
 * 
 */
package org.sercho.masp.models.Context.gui.actionListener;

import java.awt.event.ActionListener;

import org.eclipse.emf.ecore.EObject;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.gui.frames.ValueHolder;

/**
 * Interface for {@link ActionListener}s on the {@link Environment} and its
 * containing elements.
 * 
 * @author Andre Schulz
 * @since 1.2.46
 */
public interface EnvActionListener extends ActionListener {

    /**
     * Forces the {@link EnvActionListener} to finish the action on an element.
     * 
     * @param element
     *            The element to handle.
     * @param valueHolder
     *            The {@link ValueHolder} of the action.
     * 
     */
    public void finishAction(EObject element, ValueHolder valueHolder);
}
