package org.sercho.masp.models.channel.api;

/**
 * <code>PointingCallback</code> receives new messages from a channel accessible
 * through {@link PointingInputChannelAPI}.
 */
public interface PointingCallback extends TwoDimensionalCallback {

    /**
     * <code>newPointing</code> informs about a new pointing from the channel.
     * 
     * @param relativeX
     *            X coordinate of the pointing relative to the channel
     * @param relativeY
     *            Y coordinate of the pointing relative to the channel
     */
    void newPointing(int relativeX, int relativeY);
}