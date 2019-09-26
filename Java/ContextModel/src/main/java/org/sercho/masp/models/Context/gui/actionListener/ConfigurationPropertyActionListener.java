/**
 * 
 */
package org.sercho.masp.models.Context.gui.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.gui.PopupInvoker;
import org.sercho.masp.models.Context.gui.dialogs.Dialog.Option;
import org.sercho.masp.models.Context.gui.dialogs.StringDialog;
import org.sercho.masp.models.Context.gui.enums.ActionType;

import de.dailab.masp.models.Properties.ConfigurationProperty;
import de.dailab.masp.models.Properties.Service;

/**
 * {@link ActionListener} for a {@link ConfigurationProperty}.
 * 
 * @author Andre Schulz
 * @since 1.2.47
 */
public class ConfigurationPropertyActionListener extends PopupActionListener implements
        ActionListener, StringDialogActionListener {

    /**
     * The logger.
     */
    private static final transient Log LOG = LogFactory.getLog(ConfigurationPropertyActionListener.class);

    private final Service service;

    private final ConfigurationProperty configurationProperty;

    /**
     * Creates a new {@link ConfigurationPropertyActionListener}.
     * 
     * @param service
     *            The {@link Service} related to the
     *            <code>configurationProperty</code>.
     * @param configurationProperty
     *            The {@link ConfigurationProperty} this {@link ActionListener}
     *            is for.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    public ConfigurationPropertyActionListener(final Service service,
            final ConfigurationProperty configurationProperty,
            final PopupInvoker popupInvoker) {
        super(popupInvoker);

        this.service = service;
        this.configurationProperty = configurationProperty;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final ActionType actionType = ActionType.getActionType(e.getActionCommand());

        if(actionType == null) {
            LOG.error("failed to get action type from action event " + e);
            return;
        }

        switch(actionType) {
            case DELETE_PROPERTY:
                // remove property
                this.service.removeConfigurationProperty(this.configurationProperty.getKey());
                break;
            case EDIT_KEY:
            case EDIT_VALUE:
                String currentString;
                String message;

                switch(actionType) {
                    case EDIT_KEY:
                        currentString = this.configurationProperty.getKey();
                        message = new StringBuilder("Please edit the key of the ConfigurationProperty ").append(this.configurationProperty.getKey()).append(":").toString();
                        new StringDialog(this.popupInvoker.getParentFrame(), "Edit " + actionType, message, currentString, JOptionPane.QUESTION_MESSAGE, this, ActionType.EDIT_KEY);
                        break;
                    case EDIT_VALUE:
                        currentString = this.configurationProperty.getValue();
                        message = new StringBuilder("Please edit the value of the ConfigurationProperty ").append(this.configurationProperty.getKey()).append(":").toString();
                        new StringDialog(this.popupInvoker.getParentFrame(), "Edit " + actionType, message, currentString, JOptionPane.QUESTION_MESSAGE, this, ActionType.EDIT_VALUE);
                        break;
                    default:
                        return;
                }// switch

                break;
        }// switch
    }// actionPerformed

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.sercho.masp.models.Context.gui.actionListener.StringDialogActionListener
     * #finishAction(org.sercho.masp.models.Context.gui.enums.ActionType,
     * org.sercho.masp.models.Context.gui.dialogs.StringDialog)
     */
    @Override
    public void finishAction(final Option option, final ActionType actionType, final StringDialog stringDialog) {
        if(option == null) {
            LOG.error("option can not be null!");
            throw new IllegalArgumentException("option can not be null!");
        } else if(actionType == null) {
            LOG.error("action type can not be null!");
            throw new IllegalArgumentException("action type can not be null!");
        } else if(stringDialog == null) {
            LOG.error("stringDialog can not be null!");
            throw new IllegalArgumentException("stringDialog can not be null!");

        }

        switch(option) {
            case OK:
                String text;
                boolean ok = false;

                text = stringDialog.getString();

                if(text == null) {
                    LOG.error("text can not be null");
                } else if(text.isEmpty()) {
                    ok = false;

                    switch(actionType) {
                        case EDIT_KEY:
                            JOptionPane.showMessageDialog(this.popupInvoker.getParentFrame(), "Key of configuration property can not be empty!", "Error while editing key of configuration property", JOptionPane.ERROR_MESSAGE);
                            break;
                        case EDIT_VALUE:
                            // value can be empty
                            ok = true;
                            break;
                    }
                } else {
                    ok = true;
                }

                if(ok) {
                    switch(actionType) {
                        case EDIT_KEY:
                            this.configurationProperty.setKey(text);
                            break;
                        case EDIT_VALUE:
                            this.configurationProperty.setNewValue(text);
                            break;
                    }
                } else {
                    new StringDialog(stringDialog);
                }

                break;
        }// switch
    }
}
