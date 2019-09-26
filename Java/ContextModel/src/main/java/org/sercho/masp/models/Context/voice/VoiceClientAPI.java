package org.sercho.masp.models.Context.voice;

import java.nio.channels.SocketChannel;

import org.sercho.masp.models.Context.providers.AbstractClientAPI;
import org.sercho.masp.models.channel.api.AbstractChannelAPI;

/**
 * <code>VoiceClientAPI</code> is a specialized API for voice clients. It
 * provides methods which are needed by the
 * <code>VoiceClientDiscoveryProvider</code>
 * 
 * @author Daniel Kaes
 * 
 */
public abstract class VoiceClientAPI extends AbstractClientAPI {

    protected String deviceID;

    protected String messageChannelID;

    protected SocketChannel clientChannel;

    protected VoiceClientDiscoveryProvider provider;

    /**
     * Returns the id of the voice client.
     * 
     * @return client id
     */
    public abstract String getID();

    /**
     * This method is called by the <code>VoiceInputChannelAPI</code> and
     * <code>VoiceOutputChannelAPI</code> whenever a new channel is connected to
     * the client.
     * 
     * @param channelId
     *            channel id
     * 
     */
    public abstract void onChannelConnect(String channelId);

    /**
     * This method is called by the <code>VoiceInputChannelAPI</code> and
     * <code>VoiceOutputChannelAPI</code> whenever a new channel is disconnected
     * from the client.
     * 
     * @param channel
     *            reference to channel object which will be disconnected
     */
    public void onChannelDisconnect(final AbstractChannelAPI channel) {
        try {
            this.clientChannel.close();
        }
        catch(final Exception e) {
        }
        try {
            this.channelGone(this.messageChannelID);
        }
        catch(final Exception e) {
        }
        this.interactionResourceGone(this.deviceID);
        this.provider.onClientGone(this);
    }

}
