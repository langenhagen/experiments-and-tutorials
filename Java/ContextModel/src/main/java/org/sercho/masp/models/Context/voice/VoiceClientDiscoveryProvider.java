package org.sercho.masp.models.Context.voice;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.Channel;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.MessageInputChannel;
import org.sercho.masp.models.Context.providers.AbstractClientDiscoveryProvider;
import org.sercho.masp.models.Context.providers.ClientAPI;
import org.sercho.masp.models.Context.providers.ClientAPIObserver;
import org.sercho.masp.models.Context.providers.ClientDiscoveryObserver;
import org.sercho.masp.models.Context.providers.ClientDiscoveryProvider;
import org.sercho.masp.models.UI.ConcreteInteractor;
import org.sercho.masp.models.channel.api.ChannelAPI;
import org.sercho.masp.models.channel.api.MessageCallback;

/**
 * <code>VoiceClientDiscoveryProvider</code>
 * 
 * This class is listening on the voice input and output ports for connecting
 * VoiceServerInputClients/VoiceServerOutputClients.
 * 
 * @author Daniel Kaes
 * @author Grzegorz Lehmann
 * @since 1.0
 */
public class VoiceClientDiscoveryProvider extends AbstractClientDiscoveryProvider
        implements Observer {

    public static int VOICE_INPUT_PORT = 15140;

    public static int VOICE_OUTPUT_PORT = 15141;

    public final String VOICE_INPUT_ID = "voiceIn";

    public final String VOICE_OUTPUT_ID = "voiceOut";

    private NonBlockingListener inputListener;

    private NonBlockingListener outputListener;

    private final ArrayList<ClientAPI> clients = new ArrayList<ClientAPI>();

    /**
     * Write a log message using <code>LogFactory</code> or stderr if no logger
     * is available.
     * 
     * @param msg
     */
    private void writeLog(final String msg) {
        try {
            LogFactory.getLog(VoiceClientDiscoveryProvider.class).error(msg);
        }
        catch(final LogConfigurationException e) {
            System.err.println(msg);
        }
    }

    @Override
    protected void started() {
        System.out.println("VoiceClientDiscoveryProvider.started()");
        try {
            this.inputListener = new NonBlockingListener(VoiceClientDiscoveryProvider.VOICE_INPUT_PORT);
            this.inputListener.addObserver(this);
            new Thread(this.inputListener).start();
        }
        catch(final IOException e) {
            this.writeLog("Cannot start input listener on port " + VoiceClientDiscoveryProvider.VOICE_INPUT_PORT + ": " + e.getMessage());
        }
        try {
            this.outputListener = new NonBlockingListener(VoiceClientDiscoveryProvider.VOICE_OUTPUT_PORT);
            this.outputListener.addObserver(this);
            new Thread(this.outputListener).start();
        }
        catch(final IOException e) {
            this.writeLog("Cannot start output listener on port " + VoiceClientDiscoveryProvider.VOICE_OUTPUT_PORT + ": " + e.getMessage());
        }
        this.log.debug("Started");
    }

    @Override
    protected void stopped() {
        if(this.inputListener != null) {
            this.inputListener.stop();
        }
        if(this.outputListener != null) {
            this.outputListener.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Observable o, final Object arg) {
        // a client connects to channel
        if(o.equals(this.inputListener)) {
            // when your input channel arrives, call inputChannelAvailable()
            final SocketChannel clientSocketChannel = (SocketChannel)arg;
            final VoiceInputClient client = new VoiceInputClient(clientSocketChannel, this, this.VOICE_INPUT_ID + clientSocketChannel.socket().getRemoteSocketAddress());
            this.onClientConnect(client);
        } else if(o.equals(this.outputListener)) {
            // when your input channel arrives, call inputChannelAvailable()
            final SocketChannel clientSocketChannel = (SocketChannel)arg;

            final VoiceOutputClient client = new VoiceOutputClient(clientSocketChannel, this, this.VOICE_OUTPUT_ID + clientSocketChannel.socket().getRemoteSocketAddress());
            this.onClientConnect(client);
        }
    }

    /**
     * This method is called when a client connects to the input channel.
     * 
     * @param clientSocketChannel
     *            socket channel of the client
     */
    public void onClientConnect(final VoiceClientAPI client) {
        this.clients.add(client);
        this.newClient(client.getID(), client);
    }

    /**
     * This method is called when a client disconnects from the input channel.
     * 
     * @param client
     *            reference to the caller
     */
    public void onClientGone(final VoiceClientAPI client) {
        if(this.clients.remove(client) == false) {
            this.writeLog("client gone, but could not find client in clients list: " + client);
        }
        this.clientGone(client.getID());
    }

    /**
     * This main method is for testing purposes only
     * 
     * @param args
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        final ClientDiscoveryProvider provider = new VoiceClientDiscoveryProvider();

        final ClientAPIObserver observer = new ClientAPIObserver() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void channelGone(final String channelId) {
                System.out.println(".channelGone() " + channelId);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void interactionResourceGone(final String resourceId) {
                System.out.println(".interactionResourceGone() " + resourceId);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public <C extends ConcreteInteractor> void newChannel(final String resourceId, final Channel<C, ? extends ChannelAPI> channel) {
                System.out.println(".newChannel() " + channel.getId());
                if(channel instanceof MessageInputChannel) {
                    final MessageInputChannel inputChannel = (MessageInputChannel)channel;
                    inputChannel.getApi().setCallback(new MessageCallback() {

                        /**
                         * {@inheritDoc}
                         */
                        @Override
                        public void newMessage(final String message) {
                            System.out.println("newMessage() " + message);
                        }
                    });
                }
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void newInteractionResource(final InteractionResource resource) {
                System.out.println(".newInteractionResource() " + resource.getId());
            }
        };

        provider.start(new ClientDiscoveryObserver() {

            /**
             * <code>serialVersionUID</code>
             */
            private static final long serialVersionUID = -7818237193334996984L;

            /**
             * {@inheritDoc}
             */
            @Override
            public void clientGone(final String clientId) {
                System.out.println(".clientGone() " + clientId);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void newClient(final String clientId, final ClientAPI api) {
                System.out.println(".newClient() " + clientId);
                api.setObserver(observer);
            }
        });
    }
}
