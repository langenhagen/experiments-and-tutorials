package org.sercho.masp.models.Context.gui.enums;

import de.dailab.masp.models.Properties.Actor;
import de.dailab.masp.models.Properties.Sensor;
import de.dailab.masp.models.Properties.Service;
import de.dailab.masp.models.Properties.ServiceContainer;

/**
 * The types a {@link Service} could be.
 * 
 * @author Andre Schulz
 * @since 1.2.17
 */
public enum ServiceType {

    /**
     * Indicates {@link Sensor}.
     */
    SENSOR,

    /**
     * Indicates {@link Actor}.
     */
    ACTOR,

    /**
     * Indicates {@link ServiceContainer}.
     */
    SERVICE_CONTAINER,

    /**
     * Indicates {@link Service}.
     */
    SERVICE
}// enum ServiceType

