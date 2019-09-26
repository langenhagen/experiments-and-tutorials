package org.sercho.masp.models.Context.gui.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.sercho.masp.models.Context.gui.ElementProperty;
import org.sercho.masp.models.Context.gui.PopupInvoker;

import de.dailab.masp.models.Properties.Actor;
import de.dailab.masp.models.Properties.PropertiesFactory;
import de.dailab.masp.models.Properties.Property;
import de.dailab.masp.models.Properties.Sensor;

/**
 * An {@link ActionListener} which set an {@link Actor} and or {@link Sensor}
 * for a {@link Property}.
 * 
 * @author Andre Schulz
 * @since 1.2.17
 */
public class PropertySetServiceActionListener extends PopupActionListener implements
        ActionListener {

    /**
     * The {@link ElementProperty} the {@link PropertySetServiceActionListener}
     * is for.
     */
    private final ElementProperty elementProperty;

    /**
     * The {@link Sensor} to set.
     */
    private final Sensor sensor;

    /**
     * The {@link Actor} to set.
     */
    private final Actor actor;

    /**
     * Standard constructor.
     * 
     * @param elementProperty
     *            The {@link ElementProperty} to set an {@link Actor} and or
     *            {@link Sensor}.
     * @param sensor
     *            The {@link Sensor} to set for the given {@link Property}. If
     *            it is <code>null</code> nothing is modified.
     * @param actor
     *            The {@link Actor} to set for the given {@link Property}. If it
     *            is <code>null</code> nothing is modified.
     */
    public PropertySetServiceActionListener(final ElementProperty elementProperty,
            final Sensor sensor, final Actor actor, final PopupInvoker popupInvoker) {
        super(popupInvoker);
        this.elementProperty = elementProperty;
        this.sensor = sensor;
        this.actor = actor;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if((this.sensor != null || this.actor != null) && this.elementProperty.getProperty() == null) {
            this.elementProperty.setProperty((Property<?>)PropertiesFactory.eINSTANCE.create(this.elementProperty.getPropertyReference().getEReferenceType()));
            this.elementProperty.getElement().eSet(this.elementProperty.getPropertyReference(), this.elementProperty.getProperty());
        }

        if(this.sensor != null) {
            this.elementProperty.getProperty().setNewSensor(this.sensor);
        }

        if(this.actor != null) {
            this.elementProperty.getProperty().setNewActor(this.actor);
        }

        super.popupInvoker.disablePopup();
    }
}// class PropertySetServiceActionListener
