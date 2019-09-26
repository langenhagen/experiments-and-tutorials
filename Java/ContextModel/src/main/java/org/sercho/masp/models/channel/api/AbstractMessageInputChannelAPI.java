/**
 * 
 */
package org.sercho.masp.models.channel.api;

import net.jcip.annotations.ThreadSafe;

/**
 * <code>AbstractMessageInputChannelAPI</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 */
@ThreadSafe
public abstract class AbstractMessageInputChannelAPI extends AbstractChannelAPI implements
        MessageInputChannelAPI {

    /**
     * <code>AbstractMessageInputChannelAPI</code> constructor.
     */
    protected AbstractMessageInputChannelAPI() {
        super();
    }

    /**
     * <code>callback</code>
     */
    private volatile MessageCallback callback;

    /**
     * {@inheritDoc}
     */
    @Override
    public final synchronized void setCallback(final MessageCallback newCallback) {
        if(newCallback == null) {
            throw new IllegalArgumentException("newCallback is null");
        }
        this.log.trace("Got new callback");
        this.callback = newCallback;
        setCallbackHook();
    }

    /**
     * <code>setCallbackHook</code> allows to get informed that a
     * {@link MessageCallback} has been set for this channel.
     */
    protected void setCallbackHook() {
        // override if necessary
    }

    /**
     * <code>newMessage</code> sends a message to this channel's callback.
     * 
     * @param message
     *            message to send to the callback
     * @return boolean - <code>true</code> if the message has been sent,
     *         <code>false</code> if the callback is not set and the message has
     *         not been sent
     * @see #setCallback(org.sercho.masp.models.channel.api.MessageCallback)
     */
    public final synchronized boolean newMessage(final String message) {
        if(this.callback == null) {
            this.log.warn("No callback to send message: " + message);
            return false;
        }
        // no need to lock with setCallback because a non-null callback will
        // never be set to null again. Visibility assured through volatile.
        this.log.debug(message);
        this.callback.newMessage(message);
        return true;
    }
}
