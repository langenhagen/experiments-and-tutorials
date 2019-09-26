/**
 * 
 */
package org.sercho.masp.models.Context.gui.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.Vector;
import org.sercho.masp.models.Context.gui.PopupInvoker;
import org.sercho.masp.models.Context.gui.VectorWrapper;
import org.sercho.masp.models.Context.gui.dialogs.VectorDialog;
import org.sercho.masp.models.Context.gui.enums.ActionType;

/**
 * {@link ActionListener} for actions on {@link Vector}s.
 * 
 * @author Andre Schulz
 * @since 1.2.47
 */
public class VectorActionListener extends PopupActionListener implements ActionListener {

    /**
     * The logger.
     */
    private static final transient Log LOG = LogFactory.getLog(VectorActionListener.class);

    private final VectorWrapper vectorWrapper;

    /**
     * Constructor.
     * 
     * @param vectorWrapper
     *            The wrapped {@link Vector} and its name.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    public VectorActionListener(final VectorWrapper vectorWrapper,
            final PopupInvoker popupInvoker) {
        super(popupInvoker);
        this.vectorWrapper = vectorWrapper;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        ActionType actionType = ActionType.getActionType(e.getActionCommand());

        if(actionType == null) {
            if(e.getActionCommand().startsWith(ActionType.EDIT.toString())) {
                actionType = ActionType.EDIT;
            }
        }

        if(actionType != null) {
            switch(actionType) {
                case EDIT:
                    new VectorDialog(super.popupInvoker.getParentFrame(), "Edit " + this.vectorWrapper.getName() + " vector", "Edit the vetor", this.vectorWrapper, JOptionPane.QUESTION_MESSAGE);
                    break;
                case EDIT_SPAN:
                    new VectorDialog(super.popupInvoker.getParentFrame(), "Edit span vector", "Edit the vetor", this.vectorWrapper, JOptionPane.QUESTION_MESSAGE);
                    break;
                default:
                    LOG.warn("unknown action " + actionType.name());
            }
        }
    }
}
