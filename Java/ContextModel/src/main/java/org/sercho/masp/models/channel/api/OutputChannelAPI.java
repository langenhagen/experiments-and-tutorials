/**
 * 
 */
package org.sercho.masp.models.channel.api;

/**
 * <code>OutputChannelAPI</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 */
public interface OutputChannelAPI extends ChannelAPI {

    /**
     * <code>remove</code> removes an element from the channel
     * 
     * @param elementID
     *            ID of element to remove
     */
    void remove(String elementID);
}
