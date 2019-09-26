/**
 * File     OneDimensionalCallback.java
 * Package  org.sercho.masp.models.channel.api
 * Project  ContextModel
 * Date     12.03.2008
 * Web      http://www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.models.channel.api;

/**
 * <code>OneDimensionalCallback</code> allows a one-dimensional channel to send
 * events back to the MASP. The events describe the state of the channel - its
 * size and position.
 * 
 * @author Grzegorz Lehmann
 */
public interface OneDimensionalCallback extends Callback {

    /**
     * <code>newPosition</code> informs the MASP about a new position of a
     * one-dimensional channel.
     * 
     * @param newPosition
     *            new position of the channel
     */
    void newPosition(int newPosition);

    /**
     * <code>newLength</code> informs the MASP about a new size of a
     * one-dimensional channel.
     * 
     * @param newLength
     *            new length of the channel
     */
    void newLength(int newLength);
}