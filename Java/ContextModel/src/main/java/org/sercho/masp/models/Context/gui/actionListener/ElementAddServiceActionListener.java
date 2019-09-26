package org.sercho.masp.models.Context.gui.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.ElementWithPosition;
import org.sercho.masp.models.Context.EnvironmentElement;
import org.sercho.masp.models.Context.gui.ElementProperty;
import org.sercho.masp.models.Context.gui.PopupInvoker;
import org.sercho.masp.models.Context.gui.dialogs.Dialog.Option;
import org.sercho.masp.models.Context.gui.dialogs.StringDialog;
import org.sercho.masp.models.Context.gui.enums.ActionType;
import org.sercho.masp.models.Context.gui.enums.ServiceType;

import de.dailab.masp.models.Properties.Actor;
import de.dailab.masp.models.Properties.PropertiesFactory;
import de.dailab.masp.models.Properties.Property;
import de.dailab.masp.models.Properties.Sensor;
import de.dailab.masp.models.Properties.Service;
import de.dailab.masp.models.Properties.ServiceContainer;

/**
 * This {@link ActionListener} opens a frame to add a new {@link Service} to a
 * {@link Property} of an {@link ElementWithPosition}.
 * 
 * @author Andre Schulz
 * 
 */
public class ElementAddServiceActionListener extends PopupActionListener implements
        ActionListener, StringDialogActionListener {

    private static final transient Log LOG = LogFactory.getLog(ElementAddServiceActionListener.class);

    protected ElementProperty elementProperty;

    protected String propertyName;

    protected ServiceContainer serviceContainer;

    protected ServiceType serviceType;

    /**
     * Creates a new {@link ElementAddServiceActionListener} related to a
     * {@link DiscoverServiceActionListener}.
     * 
     * @param discoverServiceActionListener
     *            The {@link DiscoverServiceActionListener} to get the values
     *            from.
     */
    public ElementAddServiceActionListener(
            final DiscoverServiceActionListener discoverServiceActionListener) {
        super(discoverServiceActionListener.popupInvoker);
        this.elementProperty = discoverServiceActionListener.elementProperty;
        this.serviceContainer = discoverServiceActionListener.serviceContainer;
        this.serviceType = discoverServiceActionListener.serviceType;
        this.propertyName = discoverServiceActionListener.propertyName;
    }

    /**
     * Creates a new {@link ElementAddServiceActionListener}.
     * 
     * @param elementProperty
     *            The {@link ElementProperty} where the
     *            {@link EnvironmentElement} and the {@link Property} are hold.
     * @param serviceContainer
     *            The {@link ServiceContainer} where the new {@link Service}
     *            should be added.
     * @param propertyName
     *            The name of the {@link Property}.
     * @param type
     *            The type of the {@link Service}.
     * @param popupInvoker
     *            The {@link Property}.
     */
    public ElementAddServiceActionListener(final ElementProperty elementProperty,
            final ServiceContainer serviceContainer, final String propertyName,
            final ServiceType type, final PopupInvoker popupInvoker) {
        super(popupInvoker);
        this.elementProperty = elementProperty;
        this.serviceContainer = serviceContainer;
        this.serviceType = type;
        this.propertyName = propertyName;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        StringDialog sd = null;

        switch(this.serviceType) {
            case ACTOR:
                sd = new StringDialog(this.popupInvoker.getParentFrame(), "New Actor for " + this.propertyName, "Please enter ID of new Sensor:", "new Actor", JOptionPane.QUESTION_MESSAGE, this, ActionType.NEW_ACTOR);
                break;
            case SENSOR:
                sd = new StringDialog(this.popupInvoker.getParentFrame(), "New Sensor for " + this.propertyName, "Please enter ID of new Sensor:", "new Service", JOptionPane.QUESTION_MESSAGE, this, ActionType.NEW_SENSOR);
                break;
        }

        final String text = sd.getString();

        if(text != null) {
            if(this.elementProperty.getProperty() == null) {
                if(LOG.isDebugEnabled()) {
                    LOG.debug("create new property " + this.elementProperty.getPropertyReference().getEReferenceType());
                }

                this.elementProperty.setProperty((Property<?>)PropertiesFactory.eINSTANCE.create(this.elementProperty.getPropertyReference().getEReferenceType()));
                this.elementProperty.getElement().eSet(this.elementProperty.getPropertyReference(), this.elementProperty.getProperty());
            }

            switch(this.serviceType) {
                case ACTOR:
                    final Actor actor = PropertiesFactory.eINSTANCE.createActor();
                    actor.setId(text);
                    this.serviceContainer.addService(actor);
                    this.elementProperty.getProperty().setActor(actor);
                    break;
                case SENSOR:
                    final Sensor sensor = PropertiesFactory.eINSTANCE.createSensor();
                    sensor.setId(text);
                    this.serviceContainer.addService(sensor);
                    this.elementProperty.getProperty().setSensor(sensor);
                    break;
            }// switch
        }// if

        super.popupInvoker.disablePopup();
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

        // TODO
    }
}// class ElementAddServiceActionListener

