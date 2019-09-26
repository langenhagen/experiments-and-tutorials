package org.sercho.masp.models.Context.gui.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.gui.PopupInvoker;
import org.sercho.masp.models.Context.gui.VisualizerManager;
import org.sercho.masp.models.Context.gui.dialogs.Dialog.Option;
import org.sercho.masp.models.Context.gui.dialogs.StringDialog;
import org.sercho.masp.models.Context.gui.enums.ActionType;
import org.sercho.masp.models.Context.util.EnvironmentUtility;

import de.dailab.masp.models.Properties.Actor;
import de.dailab.masp.models.Properties.ConfigurationProperty;
import de.dailab.masp.models.Properties.PropertiesFactory;
import de.dailab.masp.models.Properties.Sensor;
import de.dailab.masp.models.Properties.Service;
import de.dailab.masp.models.Properties.ServiceContainer;

/**
 * This {@link ActionListener} handles events related to
 * {@link ServiceContainer}s.
 * 
 * @author Andre Schulz
 * @since 1.2.17
 */
public class ServiceContainerActionListener extends PopupActionListener implements
        ActionListener, StringDialogActionListener {

    private static final transient Log LOG = LogFactory.getLog(ServiceContainerActionListener.class);

    public static boolean checkIdOfConfigurationProperty(final Service service, final String key) {

        for(final ConfigurationProperty property : service.getConfiguration()) {
            if(property.getKey().equals(key)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Recursive cleaning of {@link ServiceContainer}s.
     * 
     * @param serviceContainer
     *            The {@link ServiceContainer} to clean.
     * @author Andre Schulz
     */
    private static void cleanUpServiceContainer(final ServiceContainer serviceContainer) {
        if(serviceContainer.getServices().size() > 0) {
            synchronized(serviceContainer.getServices()) {
                for(final Service service : serviceContainer.getServices()) {
                    if(service instanceof ServiceContainer) {
                        cleanUpServiceContainer((ServiceContainer)service);
                    }
                }

                serviceContainer.getServices().clear();
            }
        }
    }

    private final Environment environment;

    private final ServiceContainer serviceContainer;

    /**
     * Creates a new {@link ServiceContainerActionListener}.
     * 
     * @param environment
     *            The {@link Environment} where the {@link ServiceContainer}
     *            belongs to.
     * @param serviceContainer
     *            The {@link ServiceContainer} this {@link ActionListener} is
     *            for.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    public ServiceContainerActionListener(final Environment environment,
            final ServiceContainer serviceContainer, final PopupInvoker popupInvoker) {
        super(popupInvoker);

        this.environment = environment;
        this.serviceContainer = serviceContainer;
        this.popupInvoker = popupInvoker;
    }

    private final PopupInvoker popupInvoker;

    @Override
    public void actionPerformed(final ActionEvent e) {
        final ActionType actionType = ActionType.getActionType(e.getActionCommand());

        if(actionType == null) {
            LOG.error("failed to get action type from action event " + e);
            return;
        }

        if(LOG.isDebugEnabled()) {
            LOG.debug("actionPerformed: " + actionType.name());
        }

        switch(actionType) {
            case NEW_ACTOR:
                new StringDialog(this.popupInvoker.getParentFrame(), "New Actor on " + this.serviceContainer.getId(), "Please enter ID of new Actor:", "Actor_new", JOptionPane.QUESTION_MESSAGE, this, ActionType.NEW_ACTOR);
                break;
            case NEW_SENSOR:
                new StringDialog(this.popupInvoker.getParentFrame(), "New Sensor on " + this.serviceContainer.getId(), "Please enter ID of new Sensor:", "Sensor_new", JOptionPane.QUESTION_MESSAGE, this, ActionType.NEW_SENSOR);
                break;
            case NEW_SERVICE_CONTAINER:
                EnvironmentActionListener.runNewServiceContainerDialog(this.environment, this.serviceContainer, super.popupInvoker, this);
                break;
            case DELETE_SERVICE_CONTAINER:
                boolean delete;

                if(this.serviceContainer.getServices().size() > 0) {
                    // service container has services => ask before delete it
                    final int option = JOptionPane.showConfirmDialog(this.popupInvoker.getParentFrame(), "<html>The ServiceContainer has sub-services. Do you realy want to delete it?</html>", "Deletion of sub Services", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if(option == JOptionPane.YES_OPTION) {
                        delete = true;
                    } else {
                        delete = false;
                    }
                } else {
                    delete = true;
                }

                if(delete) {
                    // remove the ServiceContainer
                    if(this.serviceContainer.getContainer() != null) {
                        // service container is sub service container
                        this.serviceContainer.getContainer().removeService(this.serviceContainer);
                    }

                    this.environment.removeServiceContainer(this.serviceContainer);
                    cleanUpServiceContainer(this.serviceContainer);
                }
                break;// DELETE_SERVICE_CONTAINER
            case EDIT_ID:
                new StringDialog(this.popupInvoker.getParentFrame(), "Edit ID of " + this.serviceContainer.getId(), "Please enter a new ID for " + this.serviceContainer.getId(), this.serviceContainer.getId(), JOptionPane.QUESTION_MESSAGE, this, ActionType.EDIT_ID);
                break;
            default:
                LOG.warn("unknown action type " + actionType);
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

        switch(actionType) {
            case NEW_SERVICE_CONTAINER:
                if(this.serviceContainer != null) {
                    final String text = stringDialog.getString();

                    if(text != null) {
                        if(EnvironmentUtility.checkIdForService(this.environment, text)) {
                            final ServiceContainer newServiceContainer = PropertiesFactory.eINSTANCE.createServiceContainer();
                            newServiceContainer.setId(text);

                            this.serviceContainer.addService(newServiceContainer);
                        } else {
                            final StringBuilder sb = new StringBuilder();
                            sb.append("id ");
                            sb.append(text);
                            sb.append(" for a new ServiceContainer already exists at ");
                            sb.append(this.serviceContainer);
                            VisualizerManager.error(this.popupInvoker.getParentFrame(), sb.toString());
                            new StringDialog(stringDialog);
                        }
                    }
                }
                break;
            case NEW_ACTOR:
            case NEW_SENSOR:
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
            }

            // rerun dialog
            new StringDialog(stringDialog);
            return;
        }

        ok = EnvironmentUtility.checkIdForService(this.environment, text);

        if(LOG.isDebugEnabled()) {
            LOG.debug("id " + text + " for Service " + (ok ? "valid" : "invalid"));
        }

        if(ok) {
            switch(actionType) {
                case NEW_ACTOR:
                    final Actor actor = PropertiesFactory.eINSTANCE.createActor();
                    actor.setId(text);
                    this.serviceContainer.addService(actor);
                    break;
                case NEW_SENSOR:
                    final Sensor sensor = PropertiesFactory.eINSTANCE.createSensor();
                    sensor.setId(text);
                    this.serviceContainer.addService(sensor);
                    break;
                case EDIT_ID:
                    this.serviceContainer.setId(text);
                    break;
            }
        } else {
            switch(actionType) {
                case NEW_ACTOR:
                case NEW_SENSOR:
                    JOptionPane.showMessageDialog(this.popupInvoker.getParentFrame(), "ID " + text + " already exists at Environment!", "Error while creating new Service", JOptionPane.ERROR_MESSAGE);
                    break;
                case EDIT_ID:
                    JOptionPane.showMessageDialog(this.popupInvoker.getParentFrame(), "ID " + text + " already exists at Environment!", "Error while editing id of Service", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }
}// ServiceContainerActionListener

