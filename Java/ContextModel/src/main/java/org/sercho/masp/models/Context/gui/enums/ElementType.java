package org.sercho.masp.models.Context.gui.enums;

import org.eclipse.emf.common.util.Enumerator;

/**
 * 
 * @author Antonio Fernandez Zaragoza
 * @since 1.1.10
 */
public enum ElementType implements Enumerator {

    // TODO remove unused parameters

    USERS(0, "USERS", "USERS"),

    DEVICES(1, "DEVICES", "DEVICES"),

    IRS(2, "IRS", "IRS"), TOUCHPAD(3, "touchpad", "touchpad"),

    TOUCHSCREEN(4, "touchscreen", "touchscreen"),

    MOUSE(5, "mouse", "mouse"),

    KEYBOARD(6, "keyboard", "keyboard"),

    MICROPHONE(7, "microphone", "microphone"),

    DISPLAY(8, "display", "display"),

    LOUDSPEAKER(9, "loudspeaker", "loudspeaker"),

    ASSISTANTS(10, "ASSISTANTS", "ASSISTANTS"),

    PLACES(11, "PLACES", "PLACES"),

    DEVICE(12, "device", "device"),

    GESTUREIR(13, "gestureire", "gestureire"),

    USER(14, "user", "user"),

    ROOM(15, "room", "room"),

    ASSISTANT(16, "assistant", "assistant"),

    DEFAULT_RESOURCE(17, "defaultResource", "defaultResource"),

    DEFAULT_ELEMENT(18, "defaultElement", "defaultElement"),

    LTAGS(19, "LTAGS", "LTAGS"),

    LTAG(20, "LTAG", "LTAG"),

    PROVIDER(21, "PROVIDER", "PROVIDER"),

    PROVIDERS(22, "PROVIDERS", "PROVIDERS"),

    PLACE(23, "place", "place"),

    OUTDOOR(24, "outdoor", "outdoor"),

    ELEMENTS(25, "ELEMENTS", "ELEMENTS");

    private final int value;

    private final String name;

    private final String literal;

    private ElementType(final int value, final String name, final String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getLiteral() {
        return this.literal;
    }

}
