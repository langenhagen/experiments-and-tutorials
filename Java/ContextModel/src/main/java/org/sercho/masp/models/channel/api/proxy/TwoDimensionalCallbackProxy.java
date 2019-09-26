/**
 * File     TwoDimensionalCallbackProxy.java
 * Package  org.sercho.masp.models.channel.api.proxy
 * Project  ContextModel
 * Date     Mar 18, 2008
 * Web      www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.models.channel.api.proxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.sercho.masp.models.Context.GraphicalOutputChannel;
import org.sercho.masp.models.channel.api.GraphicalOutputChannelAPI;
import org.sercho.masp.models.channel.api.PointingCallback;
import org.sercho.masp.models.channel.api.TwoDimensionalCallback;
import org.sercho.masp.util.comm.MethodStringParser;

/**
 * <code>TwoDimensionalCallbackProxy</code> informs the local
 * {@link GraphicalOutputChannelAPI} about commands from a
 * {@link GraphicalOutputChannelAPIProxy} on a MASP server.
 * 
 * @author Grzegorz Lehmann
 */
public final class TwoDimensionalCallbackProxy extends
        CallbackProxy<GraphicalOutputChannelAPI> implements TwoDimensionalCallback {

    private static final transient MethodStringParser NEW_POSITION = createParser("newPosition", PointingCallback.class);

    private static final transient MethodStringParser NEW_SIZE = createParser("newSize", PointingCallback.class);

    /**
     * <code>TRANSLATORS</code> holds translators for commands from MASP server
     */
    private static transient final Map<String, MethodStringParser> PARSERS = new HashMap<String, MethodStringParser>();
    static {
        String methodName;
        for(final Method graphicalOutputChannelAPImethod : GraphicalOutputChannelAPI.class.getMethods()) {
            methodName = graphicalOutputChannelAPImethod.getName();
            try {
                PARSERS.put(methodName, createParser(methodName, GraphicalOutputChannelAPI.class));
            }
            catch(final IllegalArgumentException e) {
                // method not supported by parser
            }
        }
    }

    /**
     * <code>TwoDimensionalCallbackProxy</code> constructor
     * 
     * @param channel
     *            channel to call
     */
    public TwoDimensionalCallbackProxy(final GraphicalOutputChannel channel) {
        super(channel);
    }

    /**
     * <code>TwoDimensionalCallbackProxy</code> constructor
     * 
     * @param api
     *            channel API
     * @param channelID
     *            ID of the channel and thus the communication path
     */
    public TwoDimensionalCallbackProxy(final GraphicalOutputChannelAPI api,
            final String channelID) {
        super(api, channelID);
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
        return PARSERS.get(methodName);
    }
}