/**
 * File     ChannelAPIProxy.java
 * Package  org.sercho.masp.models.channel.remote
 * Project  ContextModel
 * Date     12.03.2008
 * Web      http://www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.models.channel.api.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.ConfigurationPropertyUtility;
import org.sercho.masp.models.UI.ConcreteInteractor;
import org.sercho.masp.models.channel.api.Callback;
import org.sercho.masp.models.channel.api.ChannelAPI;
import org.sercho.masp.util.comm.CommunicationPath;
import org.sercho.masp.util.comm.CommunicationPathException;
import org.sercho.masp.util.comm.CommunicationPathHandler;
import org.sercho.masp.util.comm.CommunicationPathProxyWithCache;
import org.sercho.masp.util.comm.MethodStringParser;
import org.sercho.masp.util.comm.MethodStringParser.MethodStringDecodeException;
import org.sercho.masp.util.patterns.event.Observer;

import de.dailab.masp.models.MetaMetaModel.AbstractExternalProcess;
import de.dailab.masp.models.MetaMetaModel.ModelCallback;

/**
 * <code>ChannelAPIProxy</code> is a server-side proxy {@link ChannelAPI}, that
 * communicates with a remote {@link ChannelAPI} using a
 * {@link CommunicationPath}. <br />
 * This class is thread-safe.
 * 
 * @param <E>
 *            type of elements on the channel
 * @param <C>
 *            type of callback
 * @author Grzegorz Lehmann
 */
@ThreadSafe
public abstract class ChannelAPIProxy<E extends ConcreteInteractor, C extends Callback>
        extends AbstractExternalProcess implements ChannelAPI, CommunicationPathHandler,
        Observer<String> {

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
    protected static Method getMethod(final String name, final Class<?> methodClass) {
        for(final Method method : methodClass.getMethods()) {
            if(method.getName().equals(name)) {
                return method;
            }
        }
        throw new IllegalStateException(methodClass.getName() + " does not define a method " + name);
    }

    /**
     * <code>LOG</code> used for logging
     */
    static final transient Log LOG = LogFactory.getLog(ChannelAPIProxy.class);

    private volatile ModelCallbackWrapper callbackWrapper = new CachingCallback();

    static final String SET_AVAILABLE_CALLBACK = "setNewAvailable";

    /**
     * <code>PROPERTY_CHANNEL_ID</code> denotes the name of the configuration
     * property holding the ID of the channel encapsulated by this proxy.
     */
    public static final transient String PROPERTY_CHANNEL_ID = "ChannelID";

    private static interface ModelCallbackWrapper {

        void setAvailable(Boolean newAvailable);

        boolean isAvailable();
    }

    private static final class CachingCallback implements ModelCallbackWrapper {

        /**
         * <code>CachingCallback</code> constructor.
         */
        CachingCallback() {
            // increased visibility
        }

        Boolean available;

        /**
         * {@inheritDoc}
         */
        @Override
        public synchronized void setAvailable(final Boolean newAvailable) {
            this.available = newAvailable;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public synchronized boolean isAvailable() {
            return this.available != null && this.available.booleanValue();
        }
    }

    private static final class StartedCallback implements ModelCallbackWrapper {

        private final ModelCallback callback;

        Boolean available;

        /**
         * <code>StartedCallback</code> constructor.
         * 
         * @param modelCallback
         *            callback to model
         */
        StartedCallback(final ModelCallback modelCallback) {
            if(modelCallback == null) {
                throw new IllegalArgumentException("modelCallback is null");
            }
            this.callback = modelCallback;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public synchronized void setAvailable(final Boolean newAvailable) {
            this.available = newAvailable;
            try {
                this.callback.execute(SET_AVAILABLE_CALLBACK, newAvailable);
            }
            catch(final InvocationTargetException e) {
                ChannelAPIProxy.LOG.error("Failed to report new available " + newAvailable + " through ModelCallback " + this.callback, e);
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public synchronized boolean isAvailable() {
            return this.available != null && this.available.booleanValue();
        }
    }

    /**
     * <code>path</code> used for remote communication.
     */
    private CommunicationPathProxyWithCache path;

    /**
     * <code>callback</code> of this API. <br />
     * This variable is guarded by {@link #callbackLock}.
     */
    @GuardedBy("callbackLock")
    private C callback;

    /**
     * <code>callbackLock</code> locks {@link #callback}
     */
    private final Lock callbackLock = new ReentrantLock();

    /**
     * <code>channelID</code> is the ID of the channel for which this proxy has
     * been created.
     */
    private String channelID;

    /**
     * <code>ChannelAPIProxy</code> constructor.
     */
    protected ChannelAPIProxy() {
        // nothing to do here yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void startHook(final Object configuration, final ModelCallback modelCallback) {
        ChannelAPIProxy.LOG.info("Setting up Channel...");
        Boolean available = null;
        // check for cached callback values
        if(this.callbackWrapper instanceof CachingCallback) {
            available = ((CachingCallback)this.callbackWrapper).available;
        }
        // create new callback
        this.callbackWrapper = new StartedCallback(modelCallback);
        // give new callback cached values
        if(available != null) {
            this.callbackWrapper.setAvailable(available);
            ChannelAPIProxy.LOG.debug("Restoring cached value (available = " + available + ")");
        }

        this.channelID = ConfigurationPropertyUtility.getPropertyValue(configuration, this, PROPERTY_CHANNEL_ID);
        this.path = new CommunicationPathProxyWithCache(this.channelID);
        ChannelAPIProxy.LOG.info("Started.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final synchronized void stopHook() {
        // report no availability
        setAvailable(Boolean.FALSE);
        // set up caching of callback values
        this.callbackWrapper = new CachingCallback();
        // close
        close();
        LOG.info("Stopped");
    }

    /**
     * <code>close</code> closes this channel and cleans it up before a possible
     * restart.
     */
    protected abstract void close();

    /**
     * {@inheritDoc}
     */
    @Override
    @GuardedBy("pathLock")
    public final Observer<String> handle(final CommunicationPath communicationPath) {
        if(communicationPath == null) {
            ChannelAPIProxy.LOG.error("communicationPath is null");
            throw new IllegalArgumentException("communicationPath is null");
        }
        // check if path is for this proxy
        if(!this.channelID.equals(communicationPath.getType())) {
            // path not for this proxy
            ChannelAPIProxy.LOG.debug("Path not for this proxy: " + communicationPath.getType());
            return null;
        }
        try {
            this.path.setCommunicationPath(communicationPath);
        }
        catch(final CommunicationPathException e) {
            ChannelAPIProxy.LOG.warn("Cannot handle new path due to error: " + e.getMessage(), e);
            return null;
        }
        catch(final IllegalStateException e) {
            ChannelAPIProxy.LOG.warn("Cannot handle new path due to error: " + e.getMessage(), e);
            return this;
        }
        ChannelAPIProxy.LOG.debug("Got new path: " + communicationPath.getType());
        return this;
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
            ChannelAPIProxy.LOG.warn("Failed to send " + message, e);
            e.printStackTrace();
        }
    }

    /**
     * <code>setCallback</code> sets the {@link Callback} to which the events
     * from the remote channel will be sent.
     * 
     * @param newCallback
     *            new call-back for channel events
     */
    @GuardedBy("callbackLock")
    public final void setCallback(final C newCallback) {
        this.callbackLock.lock();
        try {
            this.callback = newCallback;
        }
        finally {
            this.callbackLock.unlock();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GuardedBy("callbackLock")
    public final void update(final String message) {
        this.callbackLock.lock();
        try {
            if(this.callback == null) {
                ChannelAPIProxy.LOG.error("No callback set, skipping message: " + message);
                return;
            }
            final int spaceIndex = message.indexOf(' ');
            final String methodName = spaceIndex < 0 ? message
                    : message.substring(0, spaceIndex);
            // get parser
            final MethodStringParser parser = getCallbackMessageParser(methodName);
            if(parser == null) {
                // warn
                ChannelAPIProxy.LOG.warn("No parser found for message: " + message);
                return;
            }
            // execute callback
            try {
                parser.decode(this.callback, message);
            }
            catch(final MethodStringDecodeException e) {
                ChannelAPIProxy.LOG.error("Failed to parse message: " + message, e);
            }
        }
        finally {
            this.callbackLock.unlock();
        }
    }

    /**
     * <code>setAvailable</code> reports new availability of the channel
     * 
     * @param newAvailable
     *            new availability status of this channel
     */
    public synchronized final void setAvailable(final Boolean newAvailable) {
        this.callbackWrapper.setAvailable(newAvailable);
        LOG.debug(isAvailable() ? "Available" : "Not available");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isAvailable() {
        return this.callbackWrapper.isAvailable();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return getClass().getSimpleName() + " for channel " + this.channelID;
    }
}