/**
 * 
 */
package org.sercho.masp.models.channel.api;

/**
 * <code>MessageInputChannelAPI</code> allows to connect
 * {@link org.sercho.masp.models.Context.MessageInputChannel}s with the MASP.
 * 
 * @author Grzegorz Lehmann
 */
public interface MessageInputChannelAPI extends ChannelAPI {

    /**
     * <code>setCallback</code> sets the callback for this API, which will
     * receive new messages from the channel. Once a callback is set, it can be
     * unset only by replacing it with another callback.
     * 
     * @param newCallback
     *            observer for new messages, <code>null</code> disallowed
     * @throws IllegalArgumentException
     *             if <code>newCallback</code> is <code>null</code>
     */
    void setCallback(MessageCallback newCallback);
}