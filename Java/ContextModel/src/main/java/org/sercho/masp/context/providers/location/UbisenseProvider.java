package org.sercho.masp.context.providers.location;

import java.util.Date;
import java.util.Map;

import org.sercho.masp.models.Context.ConfigurationPropertyUtility;

/**
 * <code>UbisenseProvider</code> TODO comment
 * 
 * @todo add missing JavaDoc
 */
public class UbisenseProvider extends AbstractLocalisationProvider implements
        JUbiCallInterface {

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8315818983670654409L;

    /**
     * <code>PROPERTY_IP</code> denotes the name of the configuration property
     * holding the IP address of the Ubisense server.
     */
    public static final transient String PROPERTY_IP = "IP";

    /**
     * <code>PROPERTY_PORT</code> denotes the name of the configuration property
     * holding the port number of the Ubisense server.
     */
    public static final transient String PROPERTY_PORT = "Port";

    private String ip = "192.168.1.52";

    private int port = 10587;

    // after this period of time tags are marked as gone
    final long delaytime = 1000 * 60 * 5;

    JUbiClient myUbiClient;

    LocalisationObserver myObserver = null;

    Tag[] allTags;

    private Thread myCheckThread;

    checkActive myCheckActive;

    /**
     * <code>UbisenseProvider</code> constructor.
     */
    public UbisenseProvider() {
        // parameterless constructor
    }

    /**
     * <code>UbisenseProvider</code> constructor.
     * 
     * @param _ip
     *            IP address of the Ubisense server
     * @param _port
     *            port of the Ubisense server
     */
    public UbisenseProvider(final String _ip, final int _port) {
        this.ip = _ip;
        this.port = _port;
        this.myUbiClient = new JUbiClient(this.ip, this.port);
        this.myUbiClient.registerCallback(this);
        this.myCheckThread = new Thread(new checkActive());
        this.myCheckThread.start();
    }

    public void start(final LocalisationObserver observer) {
        this.myObserver = observer;
        this.myCheckThread.start();
        if(this.allTags != null) {
            for(int i = 0; i < this.allTags.length; i++) {
                if(this.allTags[i].lastActive != null) {
                    if(System.currentTimeMillis() - this.allTags[i].lastActive.getTime() < this.delaytime) {
                        this.myObserver.newPosition(this.allTags[i].name, this.allTags[i].position, 1);
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void close() {
        this.myObserver = null;
        this.myCheckActive.keeprunning = false;
        this.myUbiClient.stopIt();
    }

    /** {@inheritDoc} */
    @Override
    public boolean register(final String tagID) {
        final String[] allObjects = this.myUbiClient.getObjects();
        for(int i = 0; i < allObjects.length; i++) {
            if(allObjects[i].compareTo(tagID) == 0) {
                this.myUbiClient.pushCoordinatesOf(tagID);
                return true;
            }
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void unregister(final String tagID) {
        this.myUbiClient.unPushCoordinatesOf(tagID);

    }

    /** {@inheritDoc} */
    @Override
    public Vector getPosition(final String tagID) {
        final Poss position = this.myUbiClient.getPositionOf(tagID);
        if(position != null) {
            return new Vector(position.x, position.y, position.z);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void on_coordinate_update(final String ObjectName, final Poss positions, final Args angles) {
        if(this.myObserver != null) {
            this.myObserver.newPosition(ObjectName, new Vector(positions.x, positions.y, positions.z), 1);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void on_person_entering(final String person, final String area) {
        // nothing to do here yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void on_person_leaving(final String person, final String area) {
        // nothing to do here yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void on_area_entered(final String area, final String person) {
        // nothing to do here yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void on_area_left(final String area, final String person) {
        // nothing to do here yet
    }

    final class checkActive implements Runnable {

        volatile boolean keeprunning;

        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
            this.keeprunning = true;
            final String[] allObjects = UbisenseProvider.this.myUbiClient.getObjects();
            while(this.keeprunning) {
                if(UbisenseProvider.this.allTags == null) {
                    UbisenseProvider.this.allTags = new Tag[allObjects.length];
                    for(int i = 0; i < allObjects.length; i++) {
                        UbisenseProvider.this.allTags[i] = new Tag();
                        UbisenseProvider.this.allTags[i].name = allObjects[i];
                        UbisenseProvider.this.allTags[i].lastActive = null;
                        final Poss p = UbisenseProvider.this.myUbiClient.getPositionOf(allObjects[i]);
                        System.out.println("Ubisense available object= " + allObjects[i]);
                        if(p != null) {
                            UbisenseProvider.this.allTags[i].position = new Vector(p.x, p.y, p.z);
                        } else {
                            UbisenseProvider.this.allTags[i].position = new Vector(0, 0, 0);
                        }
                    }

                } else {
                    for(int i = 0; i < allObjects.length; i++) {
                        final Poss p = UbisenseProvider.this.myUbiClient.getPositionOf(UbisenseProvider.this.allTags[i].name);
                        if(p != null) {
                            if((p.x != UbisenseProvider.this.allTags[i].position.getX()) || (p.y != UbisenseProvider.this.allTags[i].position.getY()) || (p.z != UbisenseProvider.this.allTags[i].position.getZ())) {
                                System.out.println("AKTIVE" + UbisenseProvider.this.allTags[i].name);
                                if(UbisenseProvider.this.allTags[i].lastActive == null) {
                                    UbisenseProvider.this.allTags[i].lastActive = new Date();
                                    if(UbisenseProvider.this.myObserver != null) {
                                        UbisenseProvider.this.myObserver.newPosition(UbisenseProvider.this.allTags[i].name, new Vector(p.x, p.y, p.z), -1);
                                    }
                                } else {
                                    final Date tempdate = new Date();
                                    if((tempdate.getTime() - UbisenseProvider.this.allTags[i].lastActive.getTime()) < UbisenseProvider.this.delaytime) {
                                        UbisenseProvider.this.allTags[i].lastActive = tempdate;
                                    } else {
                                        if(UbisenseProvider.this.myObserver != null) {
                                            UbisenseProvider.this.myObserver.newPosition(UbisenseProvider.this.allTags[i].name, new Vector(p.x, p.y, p.z), -1);
                                        }
                                    }
                                }
                                UbisenseProvider.this.allTags[i].position = new Vector(p.x, p.y, p.z);
                            } else {
                                final Date tempdate = new Date();
                                if(UbisenseProvider.this.allTags[i].lastActive != null) {
                                    if((tempdate.getTime() - UbisenseProvider.this.allTags[i].lastActive.getTime()) < UbisenseProvider.this.delaytime) {

                                    } else {
                                        UbisenseProvider.this.myObserver.tagGone(UbisenseProvider.this.allTags[i].name);
                                    }
                                }
                            }
                        }
                    }
                    try {
                        // look for new clients after this period of time
                        Thread.sleep(1000 * 30);
                    }
                    catch(final InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }

        void stopIt() {
            this.keeprunning = false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void start(final Map<String, String> configuration) {
        if(this.myUbiClient != null) {
            close();
        }
        this.ip = ConfigurationPropertyUtility.getPropertyValue(configuration, PROPERTY_IP);
        this.port = ConfigurationPropertyUtility.getPropertyValueInteger(configuration, PROPERTY_PORT);
        this.myUbiClient = new JUbiClient(this.ip, this.port);
        this.myUbiClient.registerCallback(this);
        this.myCheckActive = new checkActive();
        this.myCheckThread = new Thread(this.myCheckActive);
    }
}