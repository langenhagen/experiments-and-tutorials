package org.sercho.masp.models.channel.api;

/**
 * <code>MessageCallback</code> receives new messages from a channel accessible
 * through {@link MessageInputChannelAPI}.
 */
public interface MessageCallback extends Callback {

    /**
     * <code>newMessage</code> informs about a new message from the channel.
     * 
     * @param message
     *            new message from channel
     */
    void newMessage(String message);
}