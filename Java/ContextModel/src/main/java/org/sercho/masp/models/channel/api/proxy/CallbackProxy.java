/**
 * File     CallbackProxy.java
 * Package  org.sercho.masp.models.channel.remote
 * Project  ContextModel
 * Date     Mar 17, 2008
 * Web      www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.models.channel.api.proxy;

import java.lang.reflect.Method;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Disposeable;
import org.sercho.masp.models.Context.Channel;
import org.sercho.masp.models.channel.api.ChannelAPI;
import org.sercho.masp.util.comm.CommunicationPath;
import org.sercho.masp.util.comm.CommunicationPathException;
import org.sercho.masp.util.comm.CommunicationPathHandler;
import org.sercho.masp.util.comm.CommunicationPathProxyWithCache;
import org.sercho.masp.util.comm.MethodStringParser;
import org.sercho.masp.util.comm.MethodStringParser.MethodStringDecodeException;
import org.sercho.masp.util.patterns.event.Observer;

/**
 * <code>CallbackProxy</code> works on a remote client and communicates with a
 * {@link ChannelAPIProxy} on a MASP server using a {@link CommunicationPath}.
 * All commands from the proxy on the server side are decoded and forwarded to a
 * local channel. Simultaneously all call-back events from the local channel are
 * encoded and sent to the MASP server.
 * 
 * @param <C>
 *            type of channel API for translated commands
 * @author Grzegorz Lehmann
 */
@ThreadSafe
public abstract class CallbackProxy<C extends ChannelAPI> implements
        CommunicationPathHandler, Observer<String>, Disposeable {

    /**
     * <code>getMethod</code> returns a first method with a specified name
     * encountered in a class. If multiple methods with the name are defined in
     * the class this method returns the first one found in the
     * {@link Class#getMethods()} array.
     * 
     * @param name
     *            name of method
     * @param methodClass
     *            class to search for method (can be an interface)
     * @return Method - first method found with name, or <code>null</code> if no
     *         such method found
     */
    private static Method getMethod(final String name, final Class<?> methodClass) {
        if(name == null) {
            throw new IllegalArgumentException("name is null");
        }
        if(methodClass == null) {
            throw new IllegalArgumentException("methodClass is null");
        }
        for(final Method method : methodClass.getMethods()) {
            if(method.getName().equals(name)) {
                return method;
            }
        }
        throw new IllegalStateException(methodClass.getName() + " does not define a method " + name);
    }

    /**
     * <code>createParser</code> creates a parser for a method of a specified
     * class.
     * 
     * @param methodName
     *            name of the method
     * @param c
     *            c defining the method
     * @return MethodStringParser - parser for the specified method
     * @throws IllegalArgumentException
     *             if any parameter is <code>null</code> or no method exists
     *             with the specified name in the specified class
     */
    protected static MethodStringParser createParser(final String methodName, final Class<?> c) {
        return new MethodStringParser(getMethod(methodName, c));
    }

    /**
     * <code>log</code> used for logging
     */
    protected final transient Log log = LogFactory.getLog(getClass());

    /**
     * <code>path</code> used for remote communication.
     */
    private CommunicationPathProxyWithCache path;

    /**
     * <code>channelAPI</code> is the local channel to call.
     */
    protected final C channelAPI;

    /**
     * <code>CallbackProxy</code> constructor
     * 
     * @param channel
     *            channel to call
     * @throws NullPointerException
     *             if <code>channel</code> is <code>null</code>
     */
    protected CallbackProxy(final Channel<?, C> channel) {
        this(channel.getApi(), channel.getId());
    }

    /**
     * <code>CallbackProxy</code> constructor.
     * 
     * @param api
     *            channel API
     * @param channelID
     *            ID of the channel and thus the communication path
     */
    protected CallbackProxy(final C api, final String channelID) {
        if(api == null) {
            throw new IllegalArgumentException("channel is null");
        }
        this.channelAPI = api;
        this.path = new CommunicationPathProxyWithCache(channelID);
        this.log.debug("Created for channel: " + channelID);
    }

    /**
     * <code>send</code> sends a message through this proxy's path if it is set.
     * 
     * @param message
     *            message to send
     */
    @GuardedBy("pathLock")
    protected final void send(final String message) {
        try {
            this.path.send(message);
        }
        catch(final CommunicationPathException e) {
            this.log.warn("Failed to send message " + message, e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GuardedBy("pathLock")
    public final Observer<String> handle(final CommunicationPath communicationPath) {
        if(communicationPath == null) {
            throw new IllegalArgumentException("communicationPath is null");
        }
        if(this.path.getType().equals(communicationPath.getType())) {
            try {
                this.path.setCommunicationPath(communicationPath);
            }
            catch(final CommunicationPathException e) {
                this.log.warn("Cannot handle new path due to error: " + e.getMessage(), e);
                return null;
            }
            return this;
        }
        this.log.trace(communicationPath.getType() + " path is not for this proxy " + this.path.getType());
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void update(final String message) {
        final int spaceIndex = message.indexOf(' ');
        final String methodName = spaceIndex < 0 ? message
                : message.substring(0, spaceIndex);
        // get parser
        final MethodStringParser parser = getCallbackMessageParser(methodName);
        if(parser == null) {
            // warn
            this.log.warn("No parser found for message: " + message);
            return;
        }
        // execute callback
        try {
            parser.decode(this.channelAPI, message);
        }
        catch(final MethodStringDecodeException e) {
            this.log.error("Failed to parse message: " + message, e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return getClass().getSimpleName() + " for " + this.path.getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void dispose() {
        this.path.close();
    }

    /**
     * <code>getCallbackMessageParser</code> returns a parser for a call-back
     * message.
     * 
     * @param methodName
     *            command for call-back
     * @return MethodStringParser - parser for call-back message
     */
    protected abstract MethodStringParser getCallbackMessageParser(String methodName);
}