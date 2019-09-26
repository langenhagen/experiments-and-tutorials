/**
 * 
 */
package org.sercho.masp.models.channel.api;

/**
 * <code>ChannelAPIException</code>s describe errors occurring in a
 * {@link ChannelAPI}.
 * 
 * @author Grzegorz Lehmann
 * @since 1.1.3
 */
public abstract class ChannelAPIException extends RuntimeException {

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7854625420061379515L;

    /**
     * <code>api</code> source channel API in which this exception occurred.
     */
    public final ChannelAPI api;

    /**
     * <code>ChannelAPIException</code> constructor.
     * 
     * @param channelAPI
     *            source channel API in which this exception occurred
     */
    protected ChannelAPIException(final ChannelAPI channelAPI) {
        if(channelAPI == null) {
            throw new IllegalArgumentException("channelAPI argument must not be null in method ChannelAPIException");
        }
        this.api = channelAPI;
    }

    /**
     * <code>ChannelAPIException</code> constructor.
     * 
     * @param channelAPI
     *            source channel API in which this exception occurred
     * @param message
     *            the detail message (which is saved for later retrieval by the
     *            {@link #getMessage()} method).
     */
    protected ChannelAPIException(final ChannelAPI channelAPI, final String message) {
        super(message);
        if(channelAPI == null) {
            throw new IllegalArgumentException("channelAPI argument must not be null in method ChannelAPIException");
        }
        this.api = channelAPI;
    }

    /**
     * <code>ChannelAPIException</code> constructor.
     * 
     * @param channelAPI
     *            source channel API in which this exception occurred
     * @param message
     *            the detail message (which is saved for later retrieval by the
     *            {@link #getMessage()} method).
     * @param cause
     *            the cause (which is saved for later retrieval by the
     *            {@link #getCause()} method). (A <tt>null</tt> value is
     *            permitted, and indicates that the cause is nonexistent or
     *            unknown.)
     */
    protected ChannelAPIException(final ChannelAPI channelAPI, final String message,
            final Throwable cause) {
        super(message, cause);
        if(channelAPI == null) {
            throw new IllegalArgumentException("channelAPI argument must not be null in method ChannelAPIException");
        }
        this.api = channelAPI;
    }
}