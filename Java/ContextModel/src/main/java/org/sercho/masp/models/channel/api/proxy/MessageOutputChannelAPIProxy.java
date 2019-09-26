/**
 * File     MessageOutputChannelAPIProxy.java
 * Package  org.sercho.masp.models.channel.remote
 * Project  ContextModel
 * Date     12.03.2008
 * Web      http://www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.models.channel.api.proxy;

import org.sercho.masp.models.UI.MessageOutput;
import org.sercho.masp.models.channel.api.MessageOutputChannelAPI;
import org.sercho.masp.models.channel.api.OneDimensionalCallback;
import org.sercho.masp.util.comm.MethodStringParser;

/**
 * <code>MessageOutputChannelAPIProxy</code> encapsulates a remotely working
 * {@link MessageOutputChannelAPI}.
 * 
 * @author Grzegorz Lehmann
 */
public final class MessageOutputChannelAPIProxy extends
        OutputChannelAPIProxy<MessageOutput, OneDimensionalCallback> implements
        MessageOutputChannelAPI {

    private static final transient String METHOD_NAME_NEW_LENGTH = "newLength";

    private static final transient String METHOD_NAME_NEW_POSITION = "newPosition";

    private static final transient String METHOD_NAME_ACTIVATE = "activate";

    private static final transient String METHOD_NAME_SET = "set";

    private static final transient String METHOD_NAME_FOCUS = "focus";

    private static final transient String METHOD_NAME_SELECT = "select";

    private static final transient MethodStringParser CALLBACK_NEW_LENGTH = createCallbackParser(METHOD_NAME_NEW_LENGTH);

    private static final transient MethodStringParser CALLBACK_NEW_POSITION = createCallbackParser(METHOD_NAME_NEW_POSITION);

    private static final transient MethodStringParser ACTIVATE = createParser(METHOD_NAME_ACTIVATE);

    private static final transient MethodStringParser SET = createParser(METHOD_NAME_SET);

    private static final transient MethodStringParser FOCUS = createParser(METHOD_NAME_FOCUS);

    private static final transient MethodStringParser SELECT = createParser(METHOD_NAME_SELECT);

    /**
     * <code>MessageOutputChannelAPIProxy</code> constructor.
     */
    public MessageOutputChannelAPIProxy() {
        // increased visibility
    }

    private static MethodStringParser createParser(final String methodName) {
        return new MethodStringParser(getMethod(methodName, MessageOutputChannelAPI.class));
    }

    private static MethodStringParser createCallbackParser(final String methodName) {
        return new MethodStringParser(getMethod(methodName, OneDimensionalCallback.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activate(final String elementID) {
        send(ACTIVATE.encode(elementID));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(final String elementID, final String message) {
        send(SET.encode(elementID, message));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void focus(final String elementID) {
        send(FOCUS.encode(elementID));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void select(final String elementID) {
        send(SELECT.encode(elementID));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MethodStringParser getCallbackMessageParser(final String methodName) {
        if(METHOD_NAME_NEW_POSITION.equals(methodName)) {
            return CALLBACK_NEW_POSITION;
        }
        if(METHOD_NAME_NEW_LENGTH.equals(methodName)) {
            return CALLBACK_NEW_LENGTH;
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