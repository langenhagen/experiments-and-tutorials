/**
 * 
 */
package org.sercho.masp.models.channel.api;

/**
 * <code>MessageOutputChannelAPI</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 */
public interface MessageOutputChannelAPI extends OutputChannelAPI {

    /**
     * <code>activate</code> informs the channel that one of its elements has
     * been activated. If the element has previously been focused or selected it
     * goes back to the active state.
     * 
     * @param elementID
     *            ID of the activated element
     */
    void activate(String elementID);

    /**
     * <code>focus</code> informs the channel that one of its elements has been
     * focused. If the element has previously been selected, it goes back to the
     * focused state.
     * 
     * @param elementID
     *            ID of the focused element
     */
    void focus(String elementID);

    /**
     * <code>select</code> informs the channel that one of its elements has been
     * selected.
     * 
     * @param elementID
     *            ID of the selected element
     */
    void select(String elementID);

    /**
     * <code>add</code> adds a new element to the channel.
     * 
     * @param elementID
     *            ID of the element
     * @param message
     *            text message
     */
    void set(String elementID, String message);

    /**
     * <code>setCallback</code> sets a new callback for this channel.
     * 
     * @param newCallback
     *            new callback for state information
     */
    void setCallback(OneDimensionalCallback newCallback);
}
