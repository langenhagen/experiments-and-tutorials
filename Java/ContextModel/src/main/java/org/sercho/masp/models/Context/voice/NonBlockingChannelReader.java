package org.sercho.masp.models.Context.voice;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.SocketChannel;
import java.util.Observable;

/**
 * 
 * The <code>NonBlockingChannelReader</code> is a runnable, observable class
 * which will read incoming strings from a given {@link SocketChannel}
 * terminated by newlines and notifies its observers about the incoming strings.
 * 
 * @author Daniel Kaes
 * 
 */
public class NonBlockingChannelReader extends Observable implements Runnable {

    private boolean running = false;

    private SocketChannel clientChannel = null;

    /**
     * 
     * @param clientChannel
     *            connected <code>SocketChannel</code> to read from
     */
    public NonBlockingChannelReader(final SocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }

    /**
     * Read from {@link InputStream} and notify observers. If an error occurs
     * observers will just receive a null reference instead of the input string.
     */
    private void handleClientInput() {
        InputStream in = null;
        try {
            in = this.clientChannel.socket().getInputStream();
        }
        catch(final Exception e) {
            this.clientChannel = null;
        }
        if(in != null) {
            final byte[] tmp = new byte[1024];
            int bytesRead = 0;
            try {
                while((bytesRead = in.read(tmp, 0, 1024)) >= 0) {
                    final String input = new String(tmp, 0, bytesRead, "UTF-8");
                    this.setChanged();
                    this.notifyObservers(input.trim());
                }
            }
            catch(final IOException e) {
                this.clientChannel = null;
                this.setChanged();
                this.notifyObservers(null);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        this.running = true;
        while(this.running) {
            this.handleClientInput();
        }
    }

    /**
     * Stop reading
     */
    public void stop() {
        this.running = false;
    }

}
