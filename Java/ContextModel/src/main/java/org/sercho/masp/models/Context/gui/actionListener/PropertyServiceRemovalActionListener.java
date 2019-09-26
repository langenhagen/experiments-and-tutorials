package org.sercho.masp.models.Context.gui.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.EnvironmentElement;
import org.sercho.masp.models.Context.gui.ElementProperty;
import org.sercho.masp.models.Context.gui.PopupInvoker;
import org.sercho.masp.models.Context.gui.enums.ServiceType;

import de.dailab.masp.models.Properties.Actor;
import de.dailab.masp.models.Properties.Property;
import de.dailab.masp.models.Properties.Sensor;
import de.dailab.masp.models.Properties.Service;

/**
 * This {@link ActionListener} handles events to remove a {@link Actor} or
 * {@link Sensor} from a {@link Device}.
 * 
 * @author Andre Schulz
 * @since 1.2.17
 */
public class PropertyServiceRemovalActionListener extends PopupActionListener implements
        ActionListener {

    private final ElementProperty elementProperty;

    /**
     * The type of the {@link Service}.
     */
    private final ServiceType propertyService;

    /**
     * Creates a new {@link PropertyServiceRemovalActionListener}.
     * 
     * @param elementProperty
     *            The {@link ElementProperty} holding the
     *            {@link EnvironmentElement} and the {@link Property}.
     * @param serviceType
     *            The type of the {@link Service} to remove.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    public PropertyServiceRemovalActionListener(final ElementProperty elementProperty,
            final ServiceType serviceType, final PopupInvoker popupInvoker) {
        super(popupInvoker);
        this.elementProperty = elementProperty;
        this.propertyService = serviceType;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        switch(this.propertyService) {
            case ACTOR:
                this.elementProperty.getProperty().setNewActor(null);
                break;
            case SENSOR:
                this.elementProperty.getProperty().setNewSensor(null);
                break;
        }

        super.popupInvoker.disablePopup();
    }
}// PropertyServiceRemovalActionListener