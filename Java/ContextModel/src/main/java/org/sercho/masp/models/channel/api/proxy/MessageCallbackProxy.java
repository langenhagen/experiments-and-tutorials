/**
 * File     MessageCallbackProxy.java
 * Package  org.sercho.masp.models.channel.api.proxy
 * Project  ContextModel
 * Date     Mar 18, 2008
 * Web      www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.models.channel.api.proxy;

import org.sercho.masp.models.Context.MessageInputChannel;
import org.sercho.masp.models.channel.api.MessageCallback;
import org.sercho.masp.models.channel.api.MessageInputChannelAPI;
import org.sercho.masp.util.comm.MethodStringParser;

/**
 * <code>MessageCallbackProxy</code> sends new message input from user to a
 * {@link MessageInputChannelAPIProxy} on a MASP server.
 * 
 * @author Grzegorz Lehmann
 */
public final class MessageCallbackProxy extends CallbackProxy<MessageInputChannelAPI>
        implements MessageCallback {

    private static final transient MethodStringParser NEW_MESSAGE = createParser("newMessage", MessageCallback.class);

    /**
     * <code>MessageCallbackProxy</code> constructor
     * 
     * @param channel
     *            channel to call
     */
    public MessageCallbackProxy(final MessageInputChannel channel) {
        super(channel);
    }

    /**
     * <code>MessageCallbackProxy</code> constructor
     * 
     * @param api
     *            channel API
     * @param channelID
     *            ID of the channel and thus the communication path
     */
    public MessageCallbackProxy(final MessageInputChannelAPI api, final String channelID) {
        super(api, channelID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newMessage(final String message) {
        send(NEW_MESSAGE.encode(message));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MethodStringParser getCallbackMessageParser(final String methodName) {
        throw new IllegalArgumentException("Failed to find parser for unknown method name: " + methodName);
    }
}