package org.sercho.masp.context.providers.location.mpico;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.context.providers.location.AbstractLocalisationProvider;
import org.sercho.masp.context.providers.location.Vector;

/**
 * Starts PollThread and retrieves instance of card which is observed by
 * MPicoServerObserver. Events triggered by the card itself are announced to
 * this class.
 * 
 * @author Sebastian Garn
 * 
 */
public class MPicoLocalisationProvider extends AbstractLocalisationProvider {

    /**
     * 
     */
    private static final long serialVersionUID = -2918695143458761481L;

    static final transient Log LOG = LogFactory.getLog(MPicoLocalisationProvider.class);

    int routerId = -1;

    int xEntrance, yEntrance;

    int xOutdoors, yOutdoors;

    int xUserPos, yUserPos;

    boolean concierge = true;

    MPicoServerObserver observer;

    @Override
    public boolean register(final String tagID) {
        return true;
    }

    @Override
    public void unregister(final String tagID) {

    }

    @Override
    public Vector getPosition(final String tagID) {
        return null;
    }

    @Override
    protected void start(final Map<String, String> configuration) {
        final String ipConf = configuration.get("Ip");
        final String portConf = configuration.get("Port");
        final String intervalConf = configuration.get("Interval");
        final String usernameConf = configuration.get("Username");
        final String passwordConf = configuration.get("Password");
        final String xEntranceConf = configuration.get("xEntrance");
        final String yEntranceConf = configuration.get("yEntrance");
        final String xOutdoorsConf = configuration.get("xOutdoors");
        final String yOutdoorsConf = configuration.get("yOutdoors");
        final String xUserPosConf = configuration.get("xUserPos");
        final String yUserPosConf = configuration.get("yUserPos");
        final String routerIdConf = configuration.get("RouterId");

        // default values
        String ip = "127.0.0.1";
        int port = 80;
        int interval = 50;
        String username = "";
        String password = "";

        try {
            if(xEntranceConf == null || yEntranceConf == null || xOutdoorsConf == null || yOutdoorsConf == null) {
                this.concierge = false;
            }

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
            if(xEntranceConf != null) {
                this.xEntrance = Integer.parseInt(xEntranceConf);
            }
            if(yEntranceConf != null) {
                this.yEntrance = Integer.parseInt(yEntranceConf);
            }
            if(xOutdoorsConf != null) {
                this.xOutdoors = Integer.parseInt(xOutdoorsConf);
            }
            if(yOutdoorsConf != null) {
                this.yOutdoors = Integer.parseInt(yOutdoorsConf);
            }
            if(xUserPosConf != null) {
                this.xUserPos = Integer.parseInt(xUserPosConf);
            }
            if(yUserPosConf != null) {
                this.yUserPos = Integer.parseInt(yUserPosConf);
            }
            if(routerIdConf == null) {
                LOG.error("RouterId for MPicoLocalisationProvider not specified in configuration. Abort");
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
        this.observer.addMPicoLocalisationProvider(this);
    }

    @Override
    protected void close() {
        this.observer.removeMPicoLocalisationProvider(this);
    }

    public void buttonPressed(final int cardId) {
        LOG.debug("Button of card " + cardId + " pressed! RouterId: " + this.routerId + "; xUserPos=" + this.xUserPos + "; yUserPos=" + this.yUserPos);
        newPosition("" + cardId, this.xUserPos, this.yUserPos, 0, 1);
    }

    public void fingerprint(final int cardId) {
        if(this.concierge) {
            LOG.info("(1) Fingerprint detected! Setting owner of card " + cardId + " on predefined position (Outside) x: " + this.xOutdoors + " y: " + this.yOutdoors);
            newPosition("" + cardId, this.xOutdoors, this.yOutdoors, 0, 1);

            LOG.info("(2) Fingerprint detected! Setting owner of card " + cardId + " on predefined position (Inside) x: " + this.xEntrance + " y: " + this.yEntrance);
            newPosition("" + cardId, this.xEntrance, this.yEntrance, 0, 1);
        } else {
            LOG.info("Fingerprint detected but ignored since there are some configuration properties missing! (xEntrance,...xOutdoors)");
        }
    }

    public int getRouterId() {
        return this.routerId;
    }
}
