/**
 * File     OutputChannelAPIProxy.java
 * Package  org.sercho.masp.models.channel.remote
 * Project  ContextModel
 * Date     12.03.2008
 * Web      http://www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.models.channel.api.proxy;

import org.sercho.masp.models.UI.OutputInteractor;
import org.sercho.masp.models.channel.api.Callback;
import org.sercho.masp.models.channel.api.OutputChannelAPI;
import org.sercho.masp.util.comm.MethodStringParser;

/**
 * <code>OutputChannelAPIProxy</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @param <E>
 *            type of elements on the channel
 * @param <C>
 *            type of callback
 * @author Grzegorz Lehmann
 */
public abstract class OutputChannelAPIProxy<E extends OutputInteractor, C extends Callback>
        extends ChannelAPIProxy<E, C> implements OutputChannelAPI {

    private final MethodStringParser remove;

    /**
     * <code>OutputChannelAPIProxy</code> constructor.
     * 
     * @param channelIdentifier
     *            ID of the channel
     */
    protected OutputChannelAPIProxy() {
        this.remove = new MethodStringParser(getMethod("remove", OutputChannelAPI.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void remove(final String elementID) {
        send(this.remove.encode(elementID));
    }
}