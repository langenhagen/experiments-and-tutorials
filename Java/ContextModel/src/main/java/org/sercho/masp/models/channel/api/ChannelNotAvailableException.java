/**
 * 
 */
package org.sercho.masp.models.channel.api;

/**
 * <code>ChannelNotAvailableException</code> is thrown when a functionality of a
 * not available {@link ChannelAPI} is called.
 * 
 * @author Grzegorz Lehmann
 * @since 1.1.3
 */
public final class ChannelNotAvailableException extends ChannelAPIException {

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8010292702129482951L;

    /**
     * <code>ChannelNotAvailableException</code> constructor.
     * 
     * @param channelAPI
     *            source channel API in which this exception occurred
     */
    public ChannelNotAvailableException(final ChannelAPI channelAPI) {
        super(channelAPI);
    }
}