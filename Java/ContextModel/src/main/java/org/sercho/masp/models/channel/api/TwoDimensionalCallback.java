package org.sercho.masp.models.channel.api;

/**
 * <code>TwoDimensionalCallback</code> allows a two-dimensional channel to send
 * events back to the MASP. The events describe the state of the channel - its
 * size and position.
 * 
 * @author Grzegorz Lehmann
 */
public interface TwoDimensionalCallback extends Callback {

    /**
     * <code>newSize</code> informs the MASP about a new size of a
     * two-dimensional channel.
     * 
     * @param newWidth
     *            new width of the channel
     * @param newHeight
     *            new height of the channel
     */
    void newSize(int newWidth, int newHeight);

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
    void newPosition(int relativeX, int relativeY);
}