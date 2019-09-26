/**
 * 
 */
package org.sercho.masp.models.Context.gui.actionListener;

import java.awt.event.ActionListener;

import org.sercho.masp.models.Context.gui.dialogs.Dialog.Option;
import org.sercho.masp.models.Context.gui.dialogs.KeyValueDialog;
import org.sercho.masp.models.Context.gui.enums.ActionType;

/**
 * An {@link ActionListener} for {@link KeyValueDialog}s.
 * 
 * @author Andre Schulz
 * @since 1.2.47
 */
public interface KeyValueActionListener extends ActionListener {

    /**
     * Forces the {@link KeyValueActionListener} to finish the action on an
     * element.
     * 
     * @param option
     *            The option chosen by the user.
     * @param actionType
     *            The {@link ActionType}.
     * @param keyValueDialog
     *            The {@link KeyValueDialog} to get the key and value
     *            {@link String} from.
     */
    public void finishAction(Option option, ActionType actionType, KeyValueDialog keyValueDialog);
}
