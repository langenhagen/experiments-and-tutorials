/**
 * File     PointingCallbackProxy.java
 * Package  org.sercho.masp.models.channel.api.proxy
 * Project  ContextModel
 * Date     Mar 18, 2008
 * Web      www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.models.channel.api.proxy;

import org.sercho.masp.models.Context.PointingInputChannel;
import org.sercho.masp.models.channel.api.PointingCallback;
import org.sercho.masp.models.channel.api.PointingInputChannelAPI;
import org.sercho.masp.util.comm.MethodStringParser;

/**
 * <code>PointingCallbackProxy</code> sends new message input from user to a
 * {@link PointingInputChannelAPIProxy} on a MASP server.
 * 
 * @author Grzegorz Lehmann
 */
public final class PointingCallbackProxy extends CallbackProxy<PointingInputChannelAPI>
        implements PointingCallback {

    private static final transient MethodStringParser NEW_POINTING = createParser("newPointing", PointingCallback.class);

    private static final transient MethodStringParser NEW_POSITION = createParser("newPosition", PointingCallback.class);

    private static final transient MethodStringParser NEW_SIZE = createParser("newSize", PointingCallback.class);

    /**
     * <code>PointingCallbackProxy</code> constructor
     * 
     * @param channel
     *            channel to call
     */
    public PointingCallbackProxy(final PointingInputChannel channel) {
        super(channel);
    }

    /**
     * <code>PointingCallbackProxy</code> constructor
     * 
     * @param api
     *            channel API
     * @param channelID
     *            ID of the channel and thus the communication path
     */
    public PointingCallbackProxy(final PointingInputChannelAPI api, final String channelID) {
        super(api, channelID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newPointing(final int relativeX, final int relativeY) {
        send(NEW_POINTING.encode(Integer.valueOf(relativeX), Integer.valueOf(relativeY)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newPosition(final int relativeX, final int relativeY) {
        send(NEW_POSITION.encode(Integer.valueOf(relativeX), Integer.valueOf(relativeY)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newSize(final int newWidth, final int newHeight) {
        send(NEW_SIZE.encode(Integer.valueOf(newWidth), Integer.valueOf(newHeight)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MethodStringParser getCallbackMessageParser(final String methodName) {
        throw new IllegalArgumentException("Failed to find parser for unknown method name: " + methodName);
    }
}