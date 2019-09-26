/**
 * 
 */
package org.sercho.masp.models.Context.voice;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.channel.api.AbstractMessageOutputChannelAPI;
import org.sercho.masp.models.channel.api.OneDimensionalCallback;

/**
 * <code>VoiceOutputChannelAPI</code> implements a specific output channel which
 * is able to communicate with the MRCPClient tool.
 * 
 * @todo add missing JavaDoc
 * 
 * @author Daniel Kaes
 * @author Grzegorz Lehmann
 * @since 1.1
 */
public final class VoiceOutputChannelAPI extends AbstractMessageOutputChannelAPI
        implements Observer {

    public static final String REGISTER_TOKEN = "REGISTER";

    private boolean alreadyRegistered = false;

    private SocketChannel clientChannel;

    private VoiceClientAPI voiceClient;

    private NonBlockingChannelReader reader;

    public VoiceOutputChannelAPI(final SocketChannel clientChannel,
            final VoiceOutputClient voiceClient) {
        this.start(clientChannel, voiceClient);
    }

    public void start(final SocketChannel clientChannel, final VoiceOutputClient voiceClient) {
        this.clientChannel = clientChannel;
        this.voiceClient = voiceClient;
        this.reader = new NonBlockingChannelReader(this.clientChannel);
        this.reader.addObserver(this);
        new Thread(this.reader).start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void activated(final String elementID, final String text) {
        if(this.clientChannel.isConnected()) {
            final ByteBuffer buffer = ByteBuffer.allocate(text.length() + 1);
            buffer.position(0);
            for(final byte b : text.getBytes()) {
                buffer.put(b);
            }
            buffer.put((byte)'\n');
            try {
                this.clientChannel.write(buffer);
            }
            catch(final IOException e) {
                this.voiceClient.onChannelDisconnect(this);
            }
        } else {
            this.voiceClient.onChannelDisconnect(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void focused(final String elementID, final String text) {
        // nothing to do here
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void removed(final String elementID) {
        // nothing to do here
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void selected(final String elementID, final String text) {
        // nothing to do here
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCallback(final OneDimensionalCallback newCallback) {
        // nothing to do here
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Observable o, final Object arg) {
        if(arg == null) {
            this.reader.stop();
            this.voiceClient.onChannelDisconnect(this);
        } else if(arg instanceof String) {
            final String message = (String)arg;
            if(this.alreadyRegistered == false) {
                if(message.startsWith(VoiceOutputChannelAPI.REGISTER_TOKEN)) {
                    final String channelId = message.substring(VoiceOutputChannelAPI.REGISTER_TOKEN.length());
                    this.voiceClient.onChannelConnect(channelId);
                    this.alreadyRegistered = true;
                } else {
                    final Log logger = LogFactory.getLog(VoiceOutputChannelAPI.class);
                    logger.warn("Invalid registeration! REGISTER_DEVICEID expected, but received: " + message);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void close() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void startHook(final Map<String, String> configurationMap) {
        // TODO Auto-generated method stub

    }
}