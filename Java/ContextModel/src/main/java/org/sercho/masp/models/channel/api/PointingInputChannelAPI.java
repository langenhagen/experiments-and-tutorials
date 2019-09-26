package org.sercho.masp.models.channel.api;

/**
 * <code>PointingInputChannelAPI</code> allows to connect
 * {@link org.sercho.masp.models.Context.PointingInputChannel}s to the MASP.
 * 
 * @author Grzegorz Lehmann
 */
public interface PointingInputChannelAPI extends ChannelAPI {

    /**
     * <code>setPointingCallback</code> sets the callback for this API, which
     * will receive pointing information from the channel. Once a callback is
     * set, it can be unset only by replacing it with another callback.
     * 
     * @param callback
     *            observer for pointing information, <code>null</code>
     *            disallowed
     * @throws IllegalArgumentException
     *             if <code>newCallback</code> is <code>null</code>
     */
    void setCallback(PointingCallback callback);
}