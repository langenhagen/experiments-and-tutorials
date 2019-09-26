package org.sercho.masp.models.Context.gui.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EObject;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.Outdoors;
import org.sercho.masp.models.Context.Room;
import org.sercho.masp.models.Context.gui.PopupInvoker;
import org.sercho.masp.models.Context.gui.dialogs.Dialog.Option;
import org.sercho.masp.models.Context.gui.dialogs.StringDialog;
import org.sercho.masp.models.Context.gui.enums.ActionType;
import org.sercho.masp.models.Context.gui.frames.ElementCreationFrame;
import org.sercho.masp.models.Context.gui.frames.ValueHolder;
import org.sercho.masp.models.Context.util.EnvironmentUtility;

import de.dailab.masp.models.Properties.PropertiesFactory;
import de.dailab.masp.models.Properties.ServiceContainer;

/**
 * {@link ActionListener} for actions on the {@link Environment}.
 * 
 * @author Andre Schulz
 * @since 1.2.46
 */
public class EnvironmentActionListener extends PopupActionListener implements
        EnvActionListener, ActionListener, StringDialogActionListener {

    private static final transient Log LOG = LogFactory.getLog(EnvironmentActionListener.class);

    /**
     * Opens a {@link JDialog} to create a new {@link ServiceContainer}.
     * 
     * @param environment
     *            The {@link Environment} where to add the new
     *            {@link ServiceContainer}.
     * @param serviceContainer
     *            The {@link ServiceContainer} if the new
     *            {@link ServiceContainer} will be a sub-
     *            {@link ServiceContainer}. (Can be null.)
     * @param popupInvoker
     *            The {@link PopupInvoker};
     */
    public static void runNewServiceContainerDialog(final Environment environment, final ServiceContainer serviceContainer, final PopupInvoker popupInvoker, final StringDialogActionListener stringDialogActionListener) {
        new StringDialog(popupInvoker.getParentFrame(), "Create new ServiceContainer", "Please enter the ID for a new ServiceContainer", "ServiceContainer_new", JOptionPane.QUESTION_MESSAGE, stringDialogActionListener, ActionType.NEW_SERVICE_CONTAINER);
    }

    private final Environment environment;

    /**
     * Creates a new {@link EnvironmentActionListener}.
     * 
     * @param environment
     *            The {@link Environment} where the actions take place.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    public EnvironmentActionListener(final Environment environment,
            final PopupInvoker popupInvoker) {
        super(popupInvoker);

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
            case NEW_SERVICE_CONTAINER:
                runNewServiceContainerDialog(this.environment, null, super.popupInvoker, this);
                break;
            case NEW_ROOM:
                new ElementCreationFrame(this, ContextFactory.eINSTANCE.createRoom(), this.environment, this.environment, actionType);
                break;
            case NEW_OUTDOORS:
                new ElementCreationFrame(this, ContextFactory.eINSTANCE.createOutdoors(), this.environment, this.environment, actionType);
                break;
            default:
                LOG.warn("missing action for " + actionType);
                break;
        }// switch
    }// actionPerformed

    @Override
    public void finishAction(final EObject element, final ValueHolder valueHolder) {
        if(element == null) {
            throw new IllegalArgumentException("element can not be null");
        }

        if(LOG.isDebugEnabled()) {
            LOG.debug("finishAction with element " + element);
        }

        switch(valueHolder.getActionType()) {
            case NEW_ROOM:
                final Room room = (Room)element;
                this.environment.addRoom(room.getId(), room.getName(), room.getType(), null);
                break;
            case NEW_OUTDOORS:
                final Outdoors outdoors = (Outdoors)element;
                this.environment.addOutdoors(outdoors.getId(), outdoors.getName(), null);
                break;
            default:
                throw new IllegalArgumentException("unknown action " + valueHolder.getActionType());
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
            throw new IllegalArgumentException("option can not be null!");
        } else if(actionType == null) {
            throw new IllegalArgumentException("action type can not be null!");
        } else if(stringDialog == null) {
            throw new IllegalArgumentException("stringDialog can not be null!");
        }

        if(option == Option.OK) {
            if(actionType == ActionType.NEW_SERVICE_CONTAINER) {

                final String text = stringDialog.getString();
                boolean ok = false;

                if(text == null) {
                    // creation aborted => leave
                    return;
                } else if(text.isEmpty()) {
                    LOG.error("text for the id of the new service container is empty!");
                    JOptionPane.showMessageDialog(this.popupInvoker.getParentFrame(), "The ID of a ServiceContainer can not be empty!", "Error while creating new ServiceContainer", JOptionPane.ERROR_MESSAGE);
                    new StringDialog(stringDialog);
                    return;
                }

                ok = EnvironmentUtility.checkIdForService(this.environment, text);

                if(!ok) {
                    LOG.error("failed to create service container: a container with the specific id alread exists");
                    JOptionPane.showMessageDialog(this.popupInvoker.getParentFrame(), "Can not create ServiceContainer: The ID already exists!", "Error while creating new ServiceContainer", JOptionPane.ERROR_MESSAGE);
                    new StringDialog(stringDialog);
                    return;
                }

                final ServiceContainer newServiceContainer = PropertiesFactory.eINSTANCE.createServiceContainer();
                newServiceContainer.setId(text);

                this.environment.addServiceContainer(newServiceContainer, text);
            }
        }
    }
}
