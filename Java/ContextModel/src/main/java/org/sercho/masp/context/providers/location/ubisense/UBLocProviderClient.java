package org.sercho.masp.context.providers.location.ubisense;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UBLocProviderClient extends Thread {

    private static final transient Log LOG = LogFactory.getLog(UBLocProviderClient.class);

    static final String UBLOCPROVIDER_BYE_MSG = "Bye";

    static final String UBLOCPROVIDER_DEFAULT_HOST = "localhost";

    static final int UBLOCPROVIDER_DEFAULT_PORT = 4444;

    public UBLocProviderClient(final String _host, final int _port) {
        super(UBLocProviderClient.class.getSimpleName() + ' ' + _host + ':' + _port);
        this.host = _host;
        this.port = _port;

    }

    @Override
    public void run() {

        if(!this.connected) {
            return;
        }

        LOG.debug("Starting");

        try {
            final BufferedReader in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            String inputLine, outputLine;
            outputLine = "start";
            while((inputLine = in.readLine()) != null) {

                if(inputLine.equals(UBLOCPROVIDER_BYE_MSG)) {

                    LOG.info("Got Bye message, closing");
                    break;

                }

                LOG.debug("Got new input: " + inputLine);
                buffer.push(inputLine);
                outputLine = inputLine;

                try {
                    sleep(10);
                }
                catch(final InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            in.close();

        }
        catch(final IOException e) {
            LOG.warn("I/O exception occurred: " + e.getMessage(), e);
        }
        finally {
            // close the socket in any case!
            deinit();
        }

    }

    public void deinit() {

        try {

            this.clientSocket.close();
            this.connected = false;

        }
        catch(final IOException e) {

            System.err.println("Error while closing socket");
            e.printStackTrace();
        }

    }

    public String get_info() {

        return buffer.pop();

    }

    /**
     * <code>connect</code> connects to a Ubisense server.
     * 
     * @return boolean - <code>true</code> if successfully connected, otherwise
     *         <code>false</code>
     */
    public boolean connect() {
        try {
            LOG.debug("Attempting to connect to " + this.host + ":" + this.port);
            this.clientSocket = new Socket(this.host, this.port);
            this.connected = true;
            LOG.info("Connected to " + this.host + ":" + this.port);
            return true;
        }

        catch(final IOException e) {
            LOG.warn("Failed to connect to " + this.host + ":" + this.port + ", error: " + e.getMessage(), e);
            this.connected = false;
            return false;
        }
    }

    private Socket clientSocket = null;

    private boolean connected = true;

    private String host = UBLOCPROVIDER_DEFAULT_HOST;

    private int port = UBLOCPROVIDER_DEFAULT_PORT;

    static LinkedListThSafe buffer = new LinkedListThSafe();

}
