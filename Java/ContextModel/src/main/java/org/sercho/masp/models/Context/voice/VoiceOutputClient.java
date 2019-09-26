package org.sercho.masp.models.Context.voice;

import java.nio.channels.SocketChannel;

import org.sercho.masp.models.Context.Channel;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.UI.MessageOutput;
import org.sercho.masp.models.channel.api.MessageOutputChannelAPI;

/**
 * 
 * <code>VoiceOutputClient</code> communicates with the MRCPClient tool and
 * forwards texts messages from the MASP to client, when activated.
 * 
 * @author Daniel Kaes
 * 
 */
public class VoiceOutputClient extends VoiceClientAPI {

    private final String voiceClientID;

    protected VoiceOutputChannelAPI channelAPI;

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
    public VoiceOutputClient(final SocketChannel clientChannel,
            final VoiceClientDiscoveryProvider provider, final String clientId) {
        this.provider = provider;
        // this.setIDFromStream();
        this.voiceClientID = clientId;
        this.clientChannel = clientChannel;
        final InteractionResource ir = ContextFactory.eINSTANCE.createLoudspeaker();
        this.deviceID = this.voiceClientID + "_" + ir.eClass().getName();
        ir.setId(this.deviceID);
        this.newInteractionResource(ir);
        this.channelAPI = new VoiceOutputChannelAPI(clientChannel, this);
    }

    @Override
    public String getID() {
        return this.voiceClientID;
    }

    @Override
    public void onChannelConnect(final String channelId) {
        this.messageChannelID = this.deviceID + channelId;
        final Channel<MessageOutput, MessageOutputChannelAPI> channel = ContextFactory.eINSTANCE.createMessageOutputChannel();
        channel.setId(this.messageChannelID);
        channel.setApi(this.channelAPI);
        this.newChannel(this.deviceID, channel);
    }
}
