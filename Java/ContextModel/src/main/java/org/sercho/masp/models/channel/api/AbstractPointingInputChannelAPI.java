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
public abstract class AbstractPointingInputChannelAPI extends AbstractChannelAPI
        implements PointingInputChannelAPI {

    /**
     * <code>AbstractMessageInputChannelAPI</code> constructor.
     */
    protected AbstractPointingInputChannelAPI() {
        super();
    }

    /**
     * <code>callback</code> for sending data to the MASP.
     */
    private volatile PointingCallback callback;

    /**
     * {@inheritDoc}
     */
    @Override
    public final synchronized void setCallback(final PointingCallback newCallback) {
        if(newCallback == null) {
            throw new IllegalArgumentException("newCallback is null");
        }
        this.log.trace("Got new callback");
        this.callback = newCallback;
        this.callback.newPosition(getPositionX(), getPositionY());
        this.callback.newSize(getWidth(), getHeight());
    }

    /**
     * <code>newMessage</code> sends a message to this channel's callback.
     * 
     * @param relativeX
     *            X coordinate of the pointing relative to the channel
     * @param relativeY
     *            Y coordinate of the pointing relative to the channel
     * @return boolean - <code>true</code> if the pointing has been sent,
     *         <code>false</code> if the callback is not set and the message has
     *         not been sent
     * @see #setCallback(org.sercho.masp.models.channel.api.PointingCallback)
     */
    public final synchronized boolean newPointing(final int relativeX, final int relativeY) {
        if(this.callback == null) {
            this.log.warn("No callback to send new pointing");
            return false;
        }
        this.callback.newPointing(relativeX, relativeY);
        return true;
    }

    /**
     * <code>newSize</code> informs the MASP about a new size of a
     * two-dimensional channel.
     * 
     * @param width
     *            new width of the channel
     * @param height
     *            new height of the channel
     */
    public final synchronized void newSize(final int width, final int height) {
        if(this.callback == null) {
            this.log.warn("No callback to send new size");
            return;
        }
        this.callback.newSize(width, height);
    }

    /**
     * <code>newPosition</code> informs the MASP about a new position of a
     * two-dimensional channel.
     * 
     * @param relativeX
     *            new X-coordinate of the channel relative to its interaction
     *            resource
     * @param relativeY
     *            new Y-coordinate of the channel relative to its interaction
     *            resource
     */
    public final synchronized void newPosition(final int relativeX, final int relativeY) {
        if(this.callback == null) {
            this.log.warn("No callback to send new position");
            return;
        }
        this.callback.newPosition(relativeX, relativeY);
    }

    /**
     * <code>getPositionX</code> returns the current X coordinate of this
     * channel relative to its interaction resource.
     * 
     * @return int - X coordinate of the channel relative to its interaction
     *         resource
     */
    protected abstract int getPositionX();

    /**
     * <code>getPositionY</code> returns the current Y coordinate of this
     * channel relative to its interaction resource.
     * 
     * @return int - Y coordinate of the channel relative to its interaction
     *         resource
     */
    protected abstract int getPositionY();

    /**
     * <code>getWidth</code> returns the current width of this channel.
     * 
     * @return int - width of this channel
     */
    protected abstract int getWidth();

    /**
     * <code>getHeight</code> returns the current height of this channel.
     * 
     * @return int - height of this channel
     */
    protected abstract int getHeight();
}
