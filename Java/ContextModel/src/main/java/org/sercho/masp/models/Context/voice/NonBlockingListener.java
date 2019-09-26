package org.sercho.masp.models.Context.voice;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Observable;

/**
 * The <code>NonBlockingListener</code> is a <code>Runnable</code> class which
 * listens on a TCP port and notifies observers on connecting clients.
 * 
 * @author Daniel Kaes
 * 
 */
public class NonBlockingListener extends Observable implements Runnable {

    private final int port;

    private final ServerSocketChannel serverSocketChannel;

    private boolean running;

    /**
     * 
     * 
     * @param port
     *            TCP port number
     * @throws IOException
     *             if server cannot be bind to the given port
     */
    public NonBlockingListener(final int port) throws IOException {
        this.port = port;
        this.serverSocketChannel = ServerSocketChannel.open();
        this.serverSocketChannel.socket().bind(new InetSocketAddress(this.port));
        this.serverSocketChannel.configureBlocking(false);
        this.running = false;
    }

    /**
     * Listens non-blocking for connecting clients. If a client connects the
     * observers will be notified.
     */
    private void listenNonBlocking() {
        SocketChannel channel = null;
        try {
            channel = this.serverSocketChannel.accept();
        }
        catch(final IOException e) {
            e.printStackTrace();
        }
        if(channel == null) {
            this.sleep(2000);
        } else {
            this.setChanged();
            this.notifyObservers(channel);
        }
    }

    /**
     * Sleep a specific amount when running of time without raising any
     * exceptions.
     * 
     * @param milliseconds
     *            amount of milliseconds to sleep
     */
    private void sleep(final int milliseconds) {
        if(this.running) {
            try {
                Thread.sleep(milliseconds);
            }
            catch(final InterruptedException e) {
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
            this.listenNonBlocking();
        }
    }

    /**
     * Stop listening, this will terminate the thread
     */
    public void stop() {
        this.running = false;
    }

}
