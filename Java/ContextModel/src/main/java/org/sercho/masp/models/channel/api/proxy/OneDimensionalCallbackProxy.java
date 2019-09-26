/**
 * File     OneDimensionalCallbackProxy.java
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

import org.sercho.masp.models.Context.MessageOutputChannel;
import org.sercho.masp.models.channel.api.MessageOutputChannelAPI;
import org.sercho.masp.models.channel.api.OneDimensionalCallback;
import org.sercho.masp.util.comm.MethodStringParser;

/**
 * <code>OneDimensionalCallbackProxy</code> informs a local
 * {@link org.sercho.masp.models.channel.api.MessageOutputChannelAPI} about
 * commands from a {@link MessageOutputChannelAPIProxy} on a MASP server.
 * 
 * @author Grzegorz Lehmann
 */
public final class OneDimensionalCallbackProxy extends
        CallbackProxy<MessageOutputChannelAPI> implements OneDimensionalCallback {

    /**
     * <code>TRANSLATORS</code> holds translators for commands from MASP server
     */
    private static transient final Map<String, MethodStringParser> PARSERS = new HashMap<String, MethodStringParser>();
    static {
        String methodName;
        for(final Method messageOutputChannelAPImethod : MessageOutputChannelAPI.class.getMethods()) {
            methodName = messageOutputChannelAPImethod.getName();
            try {
                PARSERS.put(methodName, createParser(methodName, MessageOutputChannelAPI.class));
            }
            catch(final IllegalArgumentException e) {
                // method not supported by parser
            }
        }
    }

    private static final transient MethodStringParser NEW_POSITION = createParser("newPosition", OneDimensionalCallback.class);

    private static final transient MethodStringParser NEW_LENGTH = createParser("newLength", OneDimensionalCallback.class);

    /**
     * <code>OneDimensionalCallbackProxy</code> constructor
     * 
     * @param channel
     *            channel to call
     */
    public OneDimensionalCallbackProxy(final MessageOutputChannel channel) {
        super(channel);
    }

    /**
     * <code>OneDimensionalCallbackProxy</code> constructor
     * 
     * @param api
     *            channel API
     * @param channelID
     *            ID of the channel and thus the communication path
     */
    public OneDimensionalCallbackProxy(final MessageOutputChannelAPI api,
            final String channelID) {
        super(api, channelID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newLength(final int newLength) {
        send(NEW_LENGTH.encode(Integer.valueOf(newLength)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newPosition(final int newPosition) {
        send(NEW_POSITION.encode(Integer.valueOf(newPosition)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MethodStringParser getCallbackMessageParser(final String methodName) {
        return PARSERS.get(methodName);
    }
}