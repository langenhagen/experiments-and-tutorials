package org.sercho.masp.context.providers.location.mpico;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.channel.api.AbstractMessageInputChannelAPI;

/**
 * Retrieves instance of card which is observed by MPicoServerObserver and
 * reacts on changes.
 * 
 * @author Sebastian Garn
 * 
 */
public class MPicoChannel extends AbstractMessageInputChannelAPI {

    static final transient Log LOG = LogFactory.getLog(MPicoChannel.class);

    private MPicoServerObserver observer;

    int routerId = -1;

    @Override
    public void startHook(final Map<String, String> configurationMap) {
        final String ipConf = configurationMap.get("Ip");
        final String portConf = configurationMap.get("Port");
        final String intervalConf = configurationMap.get("Interval");
        final String usernameConf = configurationMap.get("Username");
        final String passwordConf = configurationMap.get("Password");
        final String routerIdConf = configurationMap.get("RouterId");

        String ip = "127.0.0.1";
        int port = 80;
        int interval = 50;
        String username = "";
        String password = "";

        try {
            if(ipConf != null) {
                ip = ipConf;
            }
            if(portConf != null) {
                port = Integer.parseInt(portConf);
            }
            if(intervalConf != null) {
                interval = Integer.parseInt(intervalConf);
            }
            if(usernameConf != null) {
                username = usernameConf;
            }
            if(passwordConf != null) {
                password = passwordConf;
            }
            if(routerIdConf == null) {
                LOG.error("RouterId for MPicoChannel not specified in configuration. Abort");
                return;
            } else {
                this.routerId = Integer.parseInt(routerIdConf);
            }

        }
        catch(final Exception e) {
            LOG.warn(e.toString());
        }

        this.observer = MPicoServerObserver.getInstance();
        this.observer.start(ip, port, interval, username, password);
        this.observer.addMPicoChannel(this);
    }

    public void requestInteraction() {
        LOG.info("Button pressed: Interaction Requested");
        interactionFinished();
        interactionRequested();
    }

    public void finishInteraction() {
        LOG.info("Button pressed: Interaction Finished");
        interactionFinished();
    }

    @Override
    protected void close() {
        this.observer.removeMPicoChannel(this);
    }

    public int getRouterId() {
        return this.routerId;
    }
}