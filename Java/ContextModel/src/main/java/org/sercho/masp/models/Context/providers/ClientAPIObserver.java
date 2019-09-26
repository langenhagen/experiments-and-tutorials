/**
 * File     ClientAPIObserver.java
 * Package  org.sercho.masp.models.Context.providers
 * Project  ContextModel
 * Date     04.12.2007
 * Web      http://www.dai-labor.de
 * 
 * @author  Dirk Roscher
 */
package org.sercho.masp.models.Context.providers;

import org.sercho.masp.models.Context.Channel;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.UI.ConcreteInteractor;
import org.sercho.masp.models.channel.api.ChannelAPI;

/**
 * <code>ClientAPIObserver</code> informs about changes within the client. See
 * methods for details.
 * 
 * @author Dirk Roscher
 */
public interface ClientAPIObserver {

    /**
     * <code>interactionResourceGone</code> is called whenever an interaction
     * resource becomes unavailable.
     * 
     * @param resourceId
     *            ID of the no longer available interaction resource
     */
    void interactionResourceGone(String resourceId);

    /**
     * <code>channelGone</code> is called whenever a channel becomes
     * unavailable.
     * 
     * @param channelId
     *            ID of the no longer available channel
     */
    void channelGone(String channelId);

    /**
     * <code>newInteractionResource</code> is called whenever a new interaction
     * resource is available within the client.
     * 
     * @param resource
     *            new interaction resource to encode
     */
    void newInteractionResource(InteractionResource resource);

    /**
     * <code>newChannel</code> is called whenever a new channel is available
     * within the client.
     * 
     * @param <C>
     *            type of interactors on channel
     * @param resourceId
     *            ID of the interaction resource containing the channel
     * @param channel
     *            new channel to encode
     */
    <C extends ConcreteInteractor> void newChannel(String resourceId, Channel<C, ? extends ChannelAPI> channel);
}
