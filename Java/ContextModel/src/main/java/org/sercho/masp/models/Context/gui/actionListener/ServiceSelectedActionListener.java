/**
 * 
 */
package org.sercho.masp.models.Context.gui.actionListener;

import java.awt.event.ActionListener;

import de.dailab.masp.models.Properties.Service;

/**
 * An {@link ActionListener} for selcted {@link Service}s.
 * 
 * @author Andre Schulz
 * @since 1.3.7
 */
public interface ServiceSelectedActionListener extends ActionListener {

    public void finishAction(Service service);
}
