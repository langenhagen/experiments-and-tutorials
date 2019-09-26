package org.sercho.masp.models.Context.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class PortObserver<T> {

    private static final transient Log LOG = LogFactory.getLog(PortObserver.class);

    volatile ServerSocket serverSocket;

    private boolean started;

    protected final HashMap<String, T> waitingChannels = new HashMap<String, T>();

    private final int port;

    /**
     * This method is called when a new client connects.
     * 
     * @param s
     */
    abstract protected void newClient(Socket s);

    public PortObserver(final int observedPort) {
        this.port = observedPort;
    }

    /**
     * {@inheritDoc}
     */
    protected synchronized void started() {
        stopped();

        final ServerSocket server;
        try {
            server = new ServerSocket(this.port);
        }
        catch(final IOException e) {
            e.printStackTrace();
            return;
        }
        this.serverSocket = server;
        // start server thread
        new Thread() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void run() {
                try {
                    while(PortObserver.this.serverSocket != null) {
                        newClient(server.accept());
                    }
                }
                catch(final IOException e1) {
                    // most probably server socket has been closed
                    PortObserver.LOG.error("Error on client connecting!");
                }
            }
        }.start();
        this.started = true;
    }

    /**
     * {@inheritDoc}
     */
    protected synchronized void stopped() {
        if(this.serverSocket != null) {
            try {
                this.serverSocket.close();
            }
            catch(final IOException e) {
                // Nothing to do here
                LOG.warn("Failed to close server socket", e);
            }
            finally {
                this.serverSocket = null;
            }
        }
        this.started = false;
    }

    public boolean isStarted() {
        return this.started;
    }

    public synchronized void startWaitingForClient(final String ipAddress, final T arGraphicalOutputChannelAPI) {
        final String targetIp = ipAddress;
        final T api = arGraphicalOutputChannelAPI;

        // check ipAddress
        if(targetIp == null) {
            throw new IllegalArgumentException("ipAddress is null!");
        }
        // check arGraphicalOutputChannelAPI
        if(api == null) {
            throw new IllegalArgumentException("arGraphicalOutputChannelAPI is null!");
        }

        // check if ipAddress already in pool
        if(this.waitingChannels.containsKey(targetIp)) {
            throw new IllegalArgumentException("another channel is already waiting for this ip address!");
        }

        // check if discoverer is running
        if(!this.isStarted()) {
            // start discover
            this.started();
        }

        // add to pool
        this.waitingChannels.put(targetIp, api);
    }

    public synchronized void stopWaitingForClient(final String ipAddress) {
        final String targetIp = ipAddress;
        // check ipAddress
        if(targetIp == null) {
            throw new IllegalArgumentException("ipAddress is null!");
        }
        // check if ipAddress in pool
        if(!this.waitingChannels.containsKey(targetIp)) {
            throw new IllegalArgumentException("no channel is waiting for ip: " + targetIp);
        }

        // remove ipAdress from pool
        this.waitingChannels.remove(targetIp);

        // check if pool is empty
        if(this.waitingChannels.isEmpty()) {
            // stop discoverer
            this.stopped();
        }
    }
}
