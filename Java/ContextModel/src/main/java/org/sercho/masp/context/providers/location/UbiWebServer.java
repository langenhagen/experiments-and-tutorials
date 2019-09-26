package org.sercho.masp.context.providers.location;

import javax.swing.JOptionPane;
import javax.xml.ws.Endpoint;

public class UbiWebServer {

    /**
     * @param args
     */
    public static void main(final String[] args) {

        final UbiWebService myWebservice = new UbiWebService();

        if(args.length > 0) {
            myWebservice.Connect(args[0]);
        } else {
            myWebservice.Connect("10.0.123.64");
        }
        String IPEndpoint = "localhost:8080";
        if(args.length > 1) {
            IPEndpoint = args[1];
        }
        if(myWebservice.isConnected()) {
            final Endpoint endpoint = Endpoint.publish("http://" + IPEndpoint + "/UbiSense", myWebservice);
            JOptionPane.showMessageDialog(null, "Server beenden");
            endpoint.stop();
        } else {
            JOptionPane.showMessageDialog(null, "could not reach UbiServer", "Fehler", JOptionPane.ERROR_MESSAGE);
        }

    }
}
