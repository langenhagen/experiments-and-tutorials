package org.sercho.masp.models.Context.gui.enums;

import org.sercho.masp.models.Context.Environment;

/**
 * The types of elements on the {@link Environment}.
 * 
 * @author Andre Schulz
 * @since 1.2.18
 */
public enum RootElementType {

    /**
     * 
     */
    USERS("USERS", "User_big", 0),

    /**
     * 
     */
    PLACES("PLACES", "Place_big", 1),

    /**
     * 
     */
    DEVICES("DEVICES", "Device_big", 3),

    /**
     * 
     */
    ASSISTANTS("ASSISTANTS", "Assistant_big", 4),

    /**
     * 
     */
    PROVIDERS("PROVIDERS", "Provider_big", 5),

    /**
     * 
     */
    SERVICE_CONTAINERS("ServiceContainers", "ServiceContainer_big", 6);

    private String text;

    private String iconKey;

    private int orderID;

    private RootElementType(final String text, final String iconKey, final int orderId) {
        this.text = text;
        this.iconKey = iconKey;
        this.orderID = orderId;
    }

    public String getIconKey() {
        return this.iconKey;
    }

    public int getOrderID() {
        return this.orderID;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
