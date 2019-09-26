/**
 * 
 */
package org.sercho.masp.models.Context.gui.actionListener;

import java.awt.event.ActionListener;

import org.sercho.masp.models.Context.gui.dialogs.Dialog.Option;
import org.sercho.masp.models.Context.gui.dialogs.StringDialog;
import org.sercho.masp.models.Context.gui.enums.ActionType;

/**
 * An {@link ActionListener} for {@link StringDialog}s.
 * 
 * @author Andre Schulz
 * @since 1.2.47
 */
public interface StringDialogActionListener extends ActionListener {

    /**
     * Forces the {@link StringDialogActionListener} to finish the action on an
     * element.
     * 
     * @param option
     *            The option chosen by the user.
     * @param actionType
     *            The {@link ActionType}.
     * @param stringDialog
     *            The {@link StringDialog} to get the {@link String} from.
     */
    public void finishAction(Option option, ActionType actionType, StringDialog stringDialog);
}
