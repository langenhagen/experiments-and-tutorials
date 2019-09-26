/**
 * File     MessageInputChannelAPIProxy.java
 * Package  org.sercho.masp.models.channel.api.proxy
 * Project  ContextModel
 * Date     Mar 12, 2008
 * Web      www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.models.channel.api.proxy;

import org.sercho.masp.models.UI.MessageInput;
import org.sercho.masp.models.channel.api.MessageCallback;
import org.sercho.masp.models.channel.api.MessageInputChannelAPI;
import org.sercho.masp.util.comm.MethodStringParser;

/**
 * <code>MessageInputChannelAPIProxy</code> encapsulates a remotely working
 * {@link MessageInputChannelAPI}.
 * 
 * @author Grzegorz Lehmann
 */
public final class MessageInputChannelAPIProxy extends
        ChannelAPIProxy<MessageInput, MessageCallback> implements MessageInputChannelAPI {

    /**
     * <code>MessageInputChannelAPIProxy</code> constructor.
     */
    public MessageInputChannelAPIProxy() {
        // increased visibility
    }

    private static final transient String METHOD_NAME_NEW_MESSAGE = "newMessage";

    private static final transient MethodStringParser CALLBACK_NEW_MESSAGE = createCallbackParser(METHOD_NAME_NEW_MESSAGE);

    private static MethodStringParser createCallbackParser(final String methodName) {
        return new MethodStringParser(getMethod(methodName, MessageCallback.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MethodStringParser getCallbackMessageParser(final String methodName) {
        if(METHOD_NAME_NEW_MESSAGE.equals(methodName)) {
            return CALLBACK_NEW_MESSAGE;
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