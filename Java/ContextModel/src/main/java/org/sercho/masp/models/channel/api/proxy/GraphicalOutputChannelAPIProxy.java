/**
 * File     GraphicalOutputChannelAPIProxy.java
 * Package  org.sercho.masp.models.channel.api.proxy
 * Project  ContextModel
 * Date     Mar 13, 2008
 * Web      www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.models.channel.api.proxy;

import org.sercho.masp.models.UI.GraphicalOutput;
import org.sercho.masp.models.channel.api.GraphicalOutputChannelAPI;
import org.sercho.masp.models.channel.api.TwoDimensionalCallback;
import org.sercho.masp.util.comm.MethodStringParser;

/**
 * <code>GraphicalOutputChannelAPIProxy</code> encapsulates a remotely working
 * {@link GraphicalOutputChannelAPI}.
 * 
 * @author Grzegorz Lehmann
 */
public final class GraphicalOutputChannelAPIProxy extends
        OutputChannelAPIProxy<GraphicalOutput, TwoDimensionalCallback> implements
        GraphicalOutputChannelAPI {

    private static final transient String METHOD_NAME_NEW_POSITION = "newPosition";

    private static final transient String METHOD_NAME_NEW_SIZE = "newSize";

    private static final transient MethodStringParser ADD_BUTTON = createParser("addButton");

    private static final transient MethodStringParser ADD_IMAGE = createParser("addImage");

    private static final transient MethodStringParser ADD_TEXT_LABEL = createParser("addTextLabel");

    private static final transient MethodStringParser NEW_HEIGHT = createParser("newHeight");

    private static final transient MethodStringParser NEW_POINTING_X = createParser("newPointingX");

    private static final transient MethodStringParser NEW_POINTING_Y = createParser("newPointingY");

    private static final transient MethodStringParser NEW_POSITION_X = createParser("newPositionX");

    private static final transient MethodStringParser NEW_POSITION_Y = createParser("newPositionY");

    private static final transient MethodStringParser NEW_POSITION_Z = createParser("newPositionZ");

    private static final transient MethodStringParser NEW_TEXT = createParser("newText");

    private static final transient MethodStringParser NEW_URL = createParser("newURL");

    private static final transient MethodStringParser NEW_WIDTH = createParser("newWidth");

    private static final transient MethodStringParser REMOVE_LOOK_AND_FEEL = createParser("removeLookAndFeel");

    private static final transient MethodStringParser SET_LOOK_AND_FEEL = createParser("setLookAndFeel");

    private static final transient MethodStringParser SET_LOOK_AND_FEEL_OF_ELEMENT = createParser("setLookAndFeelOfElement");

    private static final transient MethodStringParser CALLBACK_NEW_POSITION = createCallbackParser(METHOD_NAME_NEW_POSITION);

    private static final transient MethodStringParser CALLBACK_NEW_SIZE = createCallbackParser(METHOD_NAME_NEW_SIZE);

    /**
     * <code>GraphicalOutputChannelAPIProxy</code> constructor.
     */
    public GraphicalOutputChannelAPIProxy() {
        // increased visibility
    }

    private static MethodStringParser createParser(final String methodName) {
        return new MethodStringParser(getMethod(methodName, GraphicalOutputChannelAPI.class));
    }

    private static MethodStringParser createCallbackParser(final String methodName) {
        return new MethodStringParser(getMethod(methodName, TwoDimensionalCallback.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addButton(final String elementID, final String lookAndFeelID, final int x, final int y, final int z, final int width, final int height, final String text) {
        send(ADD_BUTTON.encode(elementID, lookAndFeelID, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z), Integer.valueOf(width), Integer.valueOf(height), text));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addImage(final String elementID, final String lookAndFeelID, final String url, final int x, final int y, final int z, final int width, final int height) {
        send(ADD_IMAGE.encode(elementID, lookAndFeelID, url, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z), Integer.valueOf(width), Integer.valueOf(height)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTextLabel(final String elementID, final String lookAndFeelID, final int x, final int y, final int z, final int width, final int height, final String text) {
        send(ADD_TEXT_LABEL.encode(elementID, lookAndFeelID, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z), Integer.valueOf(width), Integer.valueOf(height), text));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newHeight(final String elementID, final int newHeight) {
        send(NEW_HEIGHT.encode(elementID, Integer.valueOf(newHeight)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newPointingX(final int relativeX) {
        send(NEW_POINTING_X.encode(Integer.valueOf(relativeX)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newPointingY(final int relativeY) {
        send(NEW_POINTING_Y.encode(Integer.valueOf(relativeY)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newPositionX(final String elementID, final int relativeX) {
        send(NEW_POSITION_X.encode(elementID, Integer.valueOf(relativeX)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newPositionY(final String elementID, final int relativeY) {
        send(NEW_POSITION_Y.encode(elementID, Integer.valueOf(relativeY)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newPositionZ(final String elementID, final int z) {
        send(NEW_POSITION_Z.encode(elementID, Integer.valueOf(z)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newText(final String elementID, final String newText) {
        send(NEW_TEXT.encode(elementID, newText));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newURL(final String elementID, final String newURL) {
        send(NEW_URL.encode(elementID, newURL));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newWidth(final String elementID, final int newWidth) {
        send(NEW_WIDTH.encode(elementID, Integer.valueOf(newWidth)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeLookAndFeel(final String lookAndFeelID) {
        send(REMOVE_LOOK_AND_FEEL.encode(lookAndFeelID));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLookAndFeel(final String lookAndFeelID, final String backgroundColor, final String fontColor, final String fontName, final int fontSize, final String fontStyle, final String borderColor, final int borderWidth) {
        send(SET_LOOK_AND_FEEL.encode(lookAndFeelID, backgroundColor, fontColor, fontName, Integer.valueOf(fontSize), fontStyle, borderColor, Integer.valueOf(borderWidth)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLookAndFeelOfElement(final String elementID, final String lookAndFeelID) {
        send(SET_LOOK_AND_FEEL_OF_ELEMENT.encode(elementID, lookAndFeelID));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MethodStringParser getCallbackMessageParser(final String methodName) {
        if(METHOD_NAME_NEW_POSITION.equals(methodName)) {
            return CALLBACK_NEW_POSITION;
        }
        if(METHOD_NAME_NEW_SIZE.equals(methodName)) {
            return CALLBACK_NEW_SIZE;
        }
        throw new IllegalArgumentException("Failed to find parser for unknown method name: " + methodName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void close() {
        // nothing to do here
    }
}