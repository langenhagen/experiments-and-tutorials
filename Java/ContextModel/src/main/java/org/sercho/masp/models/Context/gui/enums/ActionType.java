/**
 * 
 */
package org.sercho.masp.models.Context.gui.enums;

import org.sercho.masp.models.Context.Area;
import org.sercho.masp.models.Context.Door;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.EnvironmentElement;
import org.sercho.masp.models.Context.Outdoors;
import org.sercho.masp.models.Context.Place;
import org.sercho.masp.models.Context.Room;
import org.sercho.masp.models.Context.Window;

import de.dailab.masp.models.Properties.Actor;
import de.dailab.masp.models.Properties.ConfigurationProperty;
import de.dailab.masp.models.Properties.Sensor;
import de.dailab.masp.models.Properties.Service;
import de.dailab.masp.models.Properties.ServiceContainer;

/**
 * @author Andre Schulz
 * @since 1.2.46
 */
public enum ActionType {
    /**
     * Indicates to remove a {@link ConfigurationProperty} from a
     * {@link Service}.
     */
    DELETE_PROPERTY("Remove property"),

    /**
     * Indicates to remove a {@link Service} from a {@link ServiceContainer} .
     * (A <code>Service</code> could be an {@link Actor} or a {@link Sensor}.)
     */
    DELETE_SERVICE("Remove Service"),

    /**
     * Indicates to remove a {@link ServiceContainer} from the
     * {@link Environment}.
     */
    DELETE_SERVICE_CONTAINER("Remove ServiceContainer"),

    /**
     * Indicated to discover a {@link Service}.
     */
    DISCOVER_SERVICE("Discover Service"),

    /**
     * Indicates to discover a new UPnP Actor.
     */
    DISCOVER_UPNP_ACTOR("Discover an UPnP Actor"),

    /**
     * Indicates to discover a new UPnP Sensor.
     */
    DISCOVER_UPNP_SENSOR("Discover an UPnP Sensor"),

    /**
     * Indicates to edit the whole element.
     */
    EDIT("Edit"),

    /**
     * Indicates to edit the ID of the element.
     */
    EDIT_ID("Edit ID"),

    /**
     * Indicates to edit the key of a {@link ConfigurationProperty}.
     */
    EDIT_KEY("Edit key"),

    /**
     * Indicates to edit the name of the element.
     */
    EDIT_NAME("Edit name"),

    /**
     * Indicates to edit the span of the element.
     */
    EDIT_SPAN("Edit span"),

    /**
     * Indicates to edit the value of a {@link ConfigurationProperty}.
     */
    EDIT_VALUE("Edit value"),

    /**
     * Indicates to create an new {@link Actor} on a {@link ServiceContainer}.
     */
    NEW_ACTOR("Create new Actor"),

    /**
     * Indicates to add a new {@link Area} to a {@link Place}.
     */
    NEW_AREA("Add Area"),

    /**
     * Indicates to create a new {@link ConfigurationProperty} for a
     * {@link Service}.
     */
    NEW_CONFIGURATION_PROPERTY("Create new property"),

    /**
     * Indicates to create a new {@link Door}.
     */
    NEW_DOOR("Add Door"),
    /**
     * Indicates to create an {@link Outdoors}.
     */
    NEW_OUTDOORS("Add Outdoors"),

    /**
     * Indicated to create a {@link Room}.
     */
    NEW_ROOM("Add Room"),

    /**
     * Indicates to crate a new {@link Sensor} on a {@link ServiceContainer} .
     */
    NEW_SENSOR("Create new Sensor"),

    /**
     * Indicates to create a new {@link ServiceContainer} on the
     * {@link Environment}.
     */
    NEW_SERVICE_CONTAINER("Create new ServiceContainer"),

    /**
     * Indicates to create a new {@link Window}.
     */
    NEW_WINDOW("Add Window"),

    /**
     * Indicates to remove an {@link Area}.
     */
    REMOVE_AREA("Remove Area"),

    /**
     * Indicates to remove the {@link EnvironmentElement}.
     */
    REMOVE_ENVIRONMENT_ELEMENT("Remove"),

    /**
     * Indicates to remove a {@link Place}.
     */
    REMOVE_PLACE("Remove Place");

    /**
     * Returns the {@link ActionType} in relation to the <code>text</code>.
     * 
     * @param text
     *            The text of the {@link ActionType}.
     * @return The matched {@link ActionType}
     */
    public static ActionType getActionType(final String text) {
        for(final ActionType actionType : ActionType.values()) {
            if(actionType.text.startsWith(text)) {
                return actionType;
            }
        }

        return null;
    }

    private String text;

    private ActionType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }

}
