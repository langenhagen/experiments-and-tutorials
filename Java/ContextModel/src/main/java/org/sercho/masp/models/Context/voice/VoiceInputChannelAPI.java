/**
 * 
 */
package org.sercho.masp.models.Context.voice;

import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.channel.api.AbstractMessageInputChannelAPI;

/**
 * <code>VoiceInputChannelAPI</code> implements a specific input channel which
 * is able to communicate with the DNSSpeechInput tool.
 * 
 * @author Daniel Kaes
 * @author Grzegorz Lehmann
 * @since 1.1
 */
public final class VoiceInputChannelAPI extends AbstractMessageInputChannelAPI implements
        Observer {

    public static final String WORD_TOKEN = "WORD_";

    public static final String ACTIVATE_FIM_TOKEN = "ACTIVATE_FIM";

    public static final String DEACTIVATE_FIM_TOKEN = "DEACTIVATE_FIM";

    public static final String REGISTER_TOKEN = "REGISTER";

    private NonBlockingChannelReader channelReader;

    // This reference must be kept to delegate the disconnect
    // events to the voice client. Since VoiceInputChannelAPI
    // already has a parent class it's not possible to do this with
    // Java's internal Observer/Observable pattern.
    private VoiceClientAPI voiceClient;

    private boolean fimActivated = false;

    private final List<String> keywords = new ArrayList<String>();

    private boolean alreadyRegistered = false;

    /**
     * Standard constructor
     * 
     * @param clientChannel
     *            {@link SocketChannel} of the connected client
     * @param voiceClient
     *            {@link VoiceClient} who created the client's socket channel
     */
    public VoiceInputChannelAPI(final SocketChannel clientChannel,
            final VoiceInputClient voiceClient) {
        this.start(clientChannel, voiceClient);
        this.keywords.add("Dessert");
        this.keywords.add("Soup");
    }

    /**
     * Start the channel
     * 
     * @param clientChannel
     *            {@link SocketChannel} of the connected client
     * @param voiceClient
     *            {@link VoiceClient} who created the client's socket channel
     */
    public void start(final SocketChannel clientChannel, final VoiceInputClient voiceClient) {
        this.channelReader = new NonBlockingChannelReader(clientChannel);
        this.channelReader.addObserver(this);
        this.voiceClient = voiceClient;
        // TODO: remove test code when dynamic word lists are implemented:
        this.keywords.add("foo bar");
        this.keywords.add("foo");
        this.keywords.add("test");
        new Thread(this.channelReader).start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        this.channelReader.stop();
        super.finalize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Observable o, final Object arg) {
        if(arg == null) {
            // client disconnect
            this.channelReader.stop();
            this.voiceClient.onChannelDisconnect(this);
        } else if(arg instanceof String) {
            // client send message
            this.handleClientMessage((String)arg);
        }
    }

    /**
     * Post-process incoming message according to FIM status.
     * 
     * @param message
     *            incoming message
     */
    public void handleClientMessage(final String message) {
        if(this.alreadyRegistered) {
            if(this.fimActivated) {
                if(message.equals(DEACTIVATE_FIM_TOKEN)) {
                    this.fimActivated = false;
                } else {
                    this.newMessage(message);
                }
            } else {
                if(message.equals(ACTIVATE_FIM_TOKEN)) {
                    this.fimActivated = true;
                } else if(message.startsWith(VoiceInputChannelAPI.WORD_TOKEN)) {
                    final String word = message.substring(VoiceInputChannelAPI.WORD_TOKEN.length());
                    if(this.keywords.contains(word)) {
                        this.newMessage(word);
                    }
                } else {
                    final Log logger = LogFactory.getLog(VoiceInputChannelAPI.class);
                    logger.warn("Received invalid network data: " + message);
                    System.out.println("Received invalid network data: " + message);
                }
            }
        } else {
            if(message.startsWith(VoiceInputChannelAPI.REGISTER_TOKEN)) {
                final String channelId = message.substring(VoiceInputChannelAPI.REGISTER_TOKEN.length());
                this.voiceClient.onChannelConnect(channelId);
                this.alreadyRegistered = true;
            } else {
                final Log logger = LogFactory.getLog(VoiceInputChannelAPI.class);
                logger.warn("Invalid registeration! REGISTER_DEVICEID expected, but received: " + message);
                System.out.println("Invalid registeration! REGISTER_DEVICEID expected, but received: " + message);
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
