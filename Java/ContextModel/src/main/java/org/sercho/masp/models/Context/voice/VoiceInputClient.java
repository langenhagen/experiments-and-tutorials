package org.sercho.masp.models.Context.voice;

import java.nio.channels.SocketChannel;

import org.sercho.masp.models.Context.Channel;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.UI.MessageInput;
import org.sercho.masp.models.channel.api.MessageInputChannelAPI;

/**
 * <code>VoiceInputClient</code> communicates with the DNSSpeechInput tool and
 * forwards speech messages to the MASP.
 * 
 * @author Daniel Kaes
 * 
 */
public class VoiceInputClient extends VoiceClientAPI {

    private final String voiceClientID;

    private final VoiceInputChannelAPI channelAPI;

    /**
     * Standard constructor
     * 
     * @param clientChannel
     *            network channel the client is connected to
     * @param provider
     *            reference to the <code>VoiceClientDiscoveryProvider</code>
     *            needed for callbacks
     * @param clientId
     *            voice client id
     */
    public VoiceInputClient(final SocketChannel clientChannel,
            final VoiceClientDiscoveryProvider provider, final String clientId) {
        this.provider = provider;
        this.clientChannel = clientChannel;

        // this.setIDFromStream();
        this.voiceClientID = clientId;

        final InteractionResource ir = ContextFactory.eINSTANCE.createMicrophone();
        this.deviceID = this.voiceClientID + "_" + ir.eClass().getName();
        ir.setId(this.deviceID);
        this.newInteractionResource(ir);
        this.channelAPI = new VoiceInputChannelAPI(clientChannel, this);
    }

    @Override
    public String getID() {
        return this.voiceClientID;
    }

    /*
     * @Override public void onChannelDisconnect(AbstractChannelAPI channel) {
     * try { this.clientChannel.close(); } catch(Exception e) { }
     * this.channelAPI = null; try { this.channelGone(this.messageChannelID); }
     * catch (Exception e) {} this.interactionResourceGone(this.deviceID);
     * this.provider.onClientGone(this); }
     */
    @Override
    public void onChannelConnect(final String channelId) {
        this.messageChannelID = this.deviceID + channelId;
        final Channel<MessageInput, MessageInputChannelAPI> channel = ContextFactory.eINSTANCE.createMessageInputChannel();
        channel.setId(this.messageChannelID);
        channel.setApi(this.channelAPI);
        this.newChannel(this.deviceID, channel);
    }
}
