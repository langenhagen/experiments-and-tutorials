/**
 * 
 */
package org.sercho.masp.models.Context.gui.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.gui.PopupInvoker;
import org.sercho.masp.models.Context.gui.dialogs.Dialog.Option;
import org.sercho.masp.models.Context.gui.dialogs.KeyValueDialog;
import org.sercho.masp.models.Context.gui.dialogs.StringDialog;
import org.sercho.masp.models.Context.gui.enums.ActionType;
import org.sercho.masp.models.Context.util.EnvironmentUtility;

import de.dailab.masp.models.Properties.Service;

/**
 * {@link ActionListener} for a {@link Service}.
 * 
 * @author Andre Schulz
 * @since 1.2.47
 */
public class ServiceActionListener extends PopupActionListener implements
        KeyValueActionListener, StringDialogActionListener {

    private static final transient Log LOG = LogFactory.getLog(ServiceActionListener.class);

    private final Environment environment;

    private final Service service;

    /**
     * Creates a new {@link ServiceActionListener}.
     * 
     * @param service
     *            The {@link Service} the {@link ActionListener} will be for.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    public ServiceActionListener(final Service service, final Environment environment,
            final PopupInvoker popupInvoker) {
        super(popupInvoker);

        this.service = service;
        this.environment = environment;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final ActionType actionType = ActionType.getActionType(e.getActionCommand());

        if(actionType == null) {
            LOG.error("failed to get action type from action event " + e);
            return;
        }

        switch(actionType) {
            case NEW_CONFIGURATION_PROPERTY:
                new KeyValueDialog(this.popupInvoker.getParentFrame(), "New configuration property", "Please enter the key and value of a new property of Service", "", "", this, actionType);
                break;
            case EDIT_ID:
                new StringDialog(this.popupInvoker.getParentFrame(), "Edit ID of " + this.service.getId(), "Please enter a new ID for " + this.service.getId(), this.service.getId(), JOptionPane.QUESTION_MESSAGE, this, ActionType.EDIT_ID);
                break;
            case DELETE_SERVICE:
                this.service.getContainer().removeService(this.service);
                break;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.sercho.masp.models.Context.gui.actionListener.KeyValueActionListener
     * #finishAction(org.sercho.masp.models.Context.gui.dialogs.Dialog.Option,
     * org.sercho.masp.models.Context.gui.enums.ActionType,
     * org.sercho.masp.models.Context.gui.dialogs.KeyValueDialog)
     */
    @Override
    public void finishAction(final Option option, final ActionType actionType, final KeyValueDialog keyValueDialog) {
        if(option == null) {
            LOG.error("option can not be null!");
            throw new IllegalArgumentException("option can not be null!");
        } else if(actionType == null) {
            LOG.error("action type can not be null!");
            throw new IllegalArgumentException("action type can not be null!");
        } else if(keyValueDialog == null) {
            LOG.error("stringDialog can not be null!");
            throw new IllegalArgumentException("stringDialog can not be null!");

        }

        switch(actionType) {
            case NEW_CONFIGURATION_PROPERTY:
                // // +++++ new COnfigurationProperty +++++
                final String key = keyValueDialog.getKey();
                final String value = keyValueDialog.getValue();

                if(key == null || value == null) {
                    return;
                }

                if(key.isEmpty()) {
                    LOG.error("key of ne configuration property can not be empty");
                    JOptionPane.showMessageDialog(this.popupInvoker.getParentFrame(), "Key can not be empty!", "Error while creating new ConfigurationProperty", JOptionPane.ERROR_MESSAGE);
                    new KeyValueDialog(keyValueDialog);
                    return;
                }

                this.service.addConfigurationProperty(key, value);
                break;
            default:
                LOG.error("unknown action type " + actionType);
                return;
        }
    }

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

        switch(actionType) {
            case EDIT_ID:
                break;
            default:
                LOG.error("unknown action type " + actionType);
                return;
        }
        boolean ok = false;
        String text;

        text = stringDialog.getString();

        if(text == null) {
            return;
        } else if(text.isEmpty()) {
            switch(actionType) {
                case NEW_ACTOR:
                    JOptionPane.showMessageDialog(this.popupInvoker.getParentFrame(), "Id of Actor can not be empty!", "Error while creating Actor", JOptionPane.ERROR_MESSAGE);
                    break;
                case NEW_SENSOR:
                    JOptionPane.showMessageDialog(this.popupInvoker.getParentFrame(), "Id of Sensor can not be empty!", "Error while creating Sensor", JOptionPane.ERROR_MESSAGE);
                    break;
                case EDIT_ID:
                    JOptionPane.showMessageDialog(this.popupInvoker.getParentFrame(), "Id of Service can not be empty!", "Error while editing id of Service", JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    LOG.error("unknown action type " + actionType);
                    return;
            }

            // rerun dialog
            new StringDialog(stringDialog);
            return;
        }

        ok = EnvironmentUtility.checkIdForService(this.environment, text);

        if(!ok) {
            switch(actionType) {
                case EDIT_ID:
                    JOptionPane.showMessageDialog(this.popupInvoker.getParentFrame(), "ID " + text + " already exists at Environment!", "Error while editing id of Service", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }

        switch(actionType) {
            case EDIT_ID:
                this.service.setId(text);
                break;
        }

    }
}
