/**
 * 
 */
package org.sercho.masp.models.channel.api;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.ConfigurationPropertyUtility;

import de.dailab.masp.models.MetaMetaModel.AbstractExternalProcess;
import de.dailab.masp.models.MetaMetaModel.ModelCallback;
import de.dailab.masp.models.MetaMetaModel.NotStartedException;

/**
 * <code>AbstractChannelAPI</code> simplifies the implementation of the
 * {@link ChannelAPI} interface.
 * 
 * @author Grzegorz Lehmann
 * @author Martin Heller
 * @since 5.0.3
 */
public abstract class AbstractChannelAPI extends AbstractExternalProcess implements
        ChannelAPI {

    /**
     * <code>log</code> for logging
     */
    protected final transient Log log = LogFactory.getLog(getClass());

    private volatile ModelCallbackWrapper callbackWrapper = new CachingCallback();

    static final String SET_AVAILABLE_CALLBACK = "setNewAvailable";

    static final String INTERACTION_REQUESTED_CALLBACK = "interactionRequested";

    static final String INTERACTION_FINISHED_CALLBACK = "interactionFinished";

    private static interface ModelCallbackWrapper {

        void setAvailable(Boolean newAvailable);

        boolean isAvailable();
    }

    private final class CachingCallback implements ModelCallbackWrapper {

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
            AbstractChannelAPI.this.log.debug("Cached new available value: " + newAvailable);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public synchronized boolean isAvailable() {
            return this.available != null && this.available.booleanValue();
        }
    }

    private final class StartedCallback implements ModelCallbackWrapper {

        final ModelCallback callback;

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
                AbstractChannelAPI.this.log.debug("Reporting new available value: " + newAvailable);
                this.callback.execute(SET_AVAILABLE_CALLBACK, newAvailable);
                AbstractChannelAPI.this.log.debug("Reported new available value: " + newAvailable);
            }
            catch(final InvocationTargetException e) {
                AbstractChannelAPI.this.log.error("Failed to report new available " + newAvailable + " through ModelCallback " + this.callback, e);
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

    protected abstract void startHook(Map<String, String> configurationMap);

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized final void startHook(final Object configuration, final ModelCallback callback) {
        this.log.info("Setting up Channel...");
        Boolean available = null;
        // check for cached callback values
        if(this.callbackWrapper instanceof CachingCallback) {
            available = ((CachingCallback)this.callbackWrapper).available;
        }
        // create new callback
        this.callbackWrapper = new StartedCallback(callback);
        // give new callback cached values
        if(available != null) {
            this.callbackWrapper.setAvailable(available);
            this.log.debug("Restored cached available value " + available);
        }

        // run simplified startHook
        startHook(ConfigurationPropertyUtility.getConfigurationMap(configuration));
        this.log.info("Started.");
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
        this.log.info("Stopped");
    }

    /**
     * <code>close</code> closes this channel and cleans it up before a possible
     * restart.
     */
    protected abstract void close();

    /**
     * <code>setAvailable</code> reports new availability of the channel
     * 
     * @param newAvailable
     *            new availability status of this channel
     */
    public synchronized final void setAvailable(final Boolean newAvailable) {
        this.callbackWrapper.setAvailable(newAvailable);
        this.log.debug(isAvailable() ? "Available" : "Not available");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isAvailable() {
        return this.callbackWrapper.isAvailable();
    }

    /**
     * <code>assertAvailable</code> checks if this channel is available and
     * throws a {@link ChannelNotAvailableException} if not.
     */
    protected synchronized final void assertAvailable() {
        if(!isAvailable()) {
            throw new ChannelNotAvailableException(this);
        }
    }

    protected final void interactionRequested() {
        final ModelCallbackWrapper wrapper = this.callbackWrapper;
        if(wrapper instanceof StartedCallback) {
            try {
                ((StartedCallback)wrapper).callback.execute(INTERACTION_REQUESTED_CALLBACK);
            }
            catch(final InvocationTargetException e) {
                this.log.error("Failed to report interaction request, error: " + e.getTargetException().getMessage(), e);
            }
        } else {
            throw new NotStartedException(this);
        }
    }

    protected final void interactionFinished() {
        final ModelCallbackWrapper wrapper = this.callbackWrapper;
        if(wrapper instanceof StartedCallback) {
            try {
                ((StartedCallback)wrapper).callback.execute(INTERACTION_FINISHED_CALLBACK);
            }
            catch(final InvocationTargetException e) {
                this.log.error("Failed to report interaction finish, error: " + e.getTargetException().getMessage(), e);
            }
        } else {
            throw new NotStartedException(this);
        }
    }
}