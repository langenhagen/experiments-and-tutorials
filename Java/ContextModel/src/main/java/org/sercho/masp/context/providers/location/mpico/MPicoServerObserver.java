package org.sercho.masp.context.providers.location.mpico;

import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 * Creates PollThread which retrieves and parses every given "interval" ms the
 * data.xml containing card information. If card information has changed it will
 * look up the current responsible router for that card (where card is currently
 * assigned to). This information has to be extracted from the shortTable.xml.
 * Now he can inform every MPicoChannel and MPicoLocalisationProvider that
 * listen to the specific router.
 * 
 * @author Sebastian Garn
 * 
 */
public class MPicoServerObserver {

    static final transient Log LOG = LogFactory.getLog(MPicoServerObserver.class);

    static MPicoServerObserver instance;

    // each channel and localisation provider registers on a MPicoRouter
    static HashMap<Integer, MPicoLocalisationProvider> locProviders = new HashMap<Integer, MPicoLocalisationProvider>();

    static HashMap<Integer, MPicoChannel> channels = new HashMap<Integer, MPicoChannel>();

    static HashMap<Integer, MPicoCard> cards = new HashMap<Integer, MPicoCard>();

    // poll thread stuff
    static boolean pollThreadIsRunning = false;

    static String requestURLData;

    static String requestURLShortTable;

    static int interval;

    static boolean stopThread = false;

    public static MPicoServerObserver getInstance() {
        if(instance == null) {
            instance = new MPicoServerObserver();
        }
        return instance;
    }

    /**
     * Starts poll thread with given parameters
     * 
     * @param ip
     *            - String
     * @param port
     *            - int
     * @param interval
     *            - int
     * @param username
     *            - String
     * @param password
     *            - String
     */
    public void start(final String ip, final int port, final int interval, final String username, final String password) {
        if(pollThreadIsRunning) {
            return;
        }
        try {
            MPicoServerObserver.interval = interval;

            MPicoServerObserver.requestURLData = "http://" + ip + ":" + port + "/data/data.xml";
            if(username != "" && password != "") {
                MPicoServerObserver.requestURLData += "?user=" + username + "&pass=" + password;
            }

            MPicoServerObserver.requestURLShortTable = "http://" + ip + ":" + port + "/data/shortTable.xml";
            if(username != "" && password != "") {
                MPicoServerObserver.requestURLShortTable += "?user=" + username + "&pass=" + password;
            }

            LOG.info("Starting poll thread! Requesting " + MPicoServerObserver.requestURLData + " every " + MPicoServerObserver.interval + " ms");

            final Thread pollThread = new Thread() {

                // Contains cardId (hex) - routerId (hex) mapping. If a card is
                // in shortTable.xml it will be inside this map.
                HashMap<Integer, Integer> activeEpids = new HashMap<Integer, Integer>();

                @Override
                public void run() {

                    while(!stopThread) {
                        Document doc;

                        // load and parse document
                        try {
                            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                            final DocumentBuilder db = dbf.newDocumentBuilder();
                            doc = db.parse(MPicoServerObserver.requestURLData);
                        }
                        catch(final Exception e) {
                            LOG.warn("Unable to retrieve " + MPicoServerObserver.requestURLData + "! Retry in 5 seconds.");
                            try {
                                sleep(5000);
                            }
                            catch(final InterruptedException iE) {
                                LOG.warn("Error while waiting for connection");
                            }
                            continue;
                        }

                        // search for tags in document
                        final NodeList root = doc.getElementsByTagName("markers");

                        if(root.getLength() == 0) {
                            LOG.warn("Error! <markers> tag not found in .xml! File empty or malformed?");
                            continue;
                        } else {
                            final NodeList markers = root.item(0).getChildNodes();

                            for(int i = 0; i < markers.getLength(); i++) {
                                if(markers.item(i).getAttributes() == null) {
                                    continue;
                                }

                                // get value of id attribute of this marker
                                final int cardId = Integer.parseInt(markers.item(i).getAttributes().getNamedItem("id").getNodeValue());

                                MPicoCard currCard = cards.get(cardId);

                                if(currCard == null) {
                                    cards.put(cardId, new MPicoCard());
                                    currCard = cards.get(cardId);
                                }

                                final NamedNodeMap attr = markers.item(i).getAttributes();

                                // just store information of <marker> tag
                                if(markers.item(i).getNodeName().compareTo("marker") == 0) {
                                    currCard.updateMarker(attr.getNamedItem("lastActive").getNodeValue(), attr.getNamedItem("name").getNodeValue());
                                }

                                // evaluate <panic> tag
                                else if(markers.item(i).getNodeName().compareTo("panic") == 0) {

                                    // update and check whether something has
                                    // changed
                                    if(currCard.updatePanic(attr.getNamedItem("lastPanic").getNodeValue(), Integer.parseInt(attr.getNamedItem("panic").getNodeValue())) && currCard.identification == 1) {
                                        LOG.info("Button of card " + cardId + " pressed. Determining responsible router");

                                        if(inRangeOfRouter(cardId)) {
                                            final MPicoLocalisationProvider provider = locProviders.get(this.activeEpids.get(cardId));

                                            if(provider != null) {
                                                provider.buttonPressed(cardId);
                                            } else {
                                                LOG.info("No MPicoLocalisationProvider found registered on RouterId " + this.activeEpids.get(cardId));
                                            }

                                            final MPicoChannel channel = channels.get(this.activeEpids.get(cardId));
                                            if(channel != null) {
                                                final boolean type = currCard.getAndSwitchRequestType();
                                                if(type) {
                                                    channel.requestInteraction();
                                                } else {
                                                    channel.finishInteraction();
                                                }
                                            } else {
                                                LOG.info("No MPicoChannel found registered on RouterId " + this.activeEpids.get(cardId));
                                            }
                                        } else {
                                            LOG.info("Card not found in shortTable.xml");
                                        }
                                    }
                                }

                                // evaluate <fingerprint> tag
                                else if(markers.item(i).getNodeName().compareTo("fingerprint") == 0) {

                                    // update and check whether something has
                                    // changed
                                    if(currCard.updateFingerprint(Integer.parseInt(attr.getNamedItem("enrolment").getNodeValue()), Integer.parseInt(attr.getNamedItem("identification").getNodeValue()))) {
                                        LOG.info("Identification! Card " + cardId + ", identification=" + currCard.identification + ", name=" + currCard.currentActiveUser);

                                        if(inRangeOfRouter(cardId)) {
                                            final MPicoLocalisationProvider provider = locProviders.get(this.activeEpids.get(cardId));
                                            if(provider != null) {
                                                provider.fingerprint(cardId);
                                            } else {
                                                LOG.info("No MPicoLocalisationProvider found registered on RouterId " + this.activeEpids.get(cardId));
                                            }
                                        } else {
                                            LOG.info("Card not found in shortTable.xml");
                                        }
                                    }
                                }
                            }
                        }

                        try {
                            sleep(MPicoServerObserver.interval);
                        }
                        catch(final InterruptedException e) {
                            LOG.warn("Error while waiting for next retrieve cycle");
                        }
                    }
                }

                private boolean inRangeOfRouter(final int cardId) {
                    updateActiveEpids();

                    if(this.activeEpids.containsKey(cardId)) {
                        return true;
                    } else {
                        // lets give parser a second chance
                        try {
                            sleep(500);
                            updateActiveEpids();
                        }
                        catch(final Exception e) {
                            LOG.error("Error while waiting for shortTable.xml");
                        }
                        if(this.activeEpids.containsKey(cardId)) {
                            return true;
                        }
                    }

                    return false;
                }

                // parse shortTable.xml and put discovered cardIds in
                // activeEpids-mapping
                private void updateActiveEpids() {
                    this.activeEpids.clear();
                    Document doc;

                    try {
                        // TODO change url
                        final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                        final DocumentBuilder db = dbf.newDocumentBuilder();
                        doc = db.parse(MPicoServerObserver.requestURLShortTable);
                    }
                    catch(final Exception e) {
                        LOG.warn("Unable to retrieve " + MPicoServerObserver.requestURLShortTable + "! Unable to map between router and card!");
                        return;
                    }

                    final NodeList root = doc.getElementsByTagName("ShortRangeRoutersTable");

                    if(root.getLength() == 0) {
                        LOG.warn("Error! <ShortRangeRoutersTable> tag not found in .xml! File empty or malformed?");
                        return;
                    }

                    final NodeList routers = root.item(0).getChildNodes();

                    for(int i = 0; i < routers.getLength(); i++) {
                        final NamedNodeMap currRouter = routers.item(i).getAttributes();
                        if(currRouter == null) {
                            continue;
                        }
                        if(currRouter.getNamedItem("id") == null) {
                            continue;
                        }

                        if(currRouter.getNamedItem("id").getNodeValue() == null) {
                            continue;
                        }
                        final int routerId = Integer.parseInt(currRouter.getNamedItem("id").getNodeValue(), 16);

                        // check epids0,epids1 and epids2 attribute for cardIds
                        for(int j = 0; j <= 2; j++) {
                            final String currEpidAttr = currRouter.getNamedItem("epids" + j).getNodeValue();

                            final String binaryCardIds = Integer.toBinaryString(Integer.parseInt(currEpidAttr));
                            if(binaryCardIds.length() > 8) {
                                LOG.warn("Converted binary value of epids tag is longer than eight! Wrong format!");
                            }
                            int counter = 0;
                            for(int k = binaryCardIds.length(); k > 0; k--) {
                                counter++;
                                if(binaryCardIds.substring(k - 1, k).equals("1")) {
                                    LOG.info("cardid=" + (counter + 8 * j) + " active for routerid=" + routerId);
                                    this.activeEpids.put(counter + 8 * j, routerId);
                                }
                            }

                        }
                    }
                }

            };

            pollThread.start();
            pollThreadIsRunning = true;

        }
        catch(final Exception e) {
            System.out.println(e.toString());
        }
    }

    public void addMPicoLocalisationProvider(final MPicoLocalisationProvider provider) {
        if(!locProviders.containsKey(provider.getRouterId())) {
            LOG.debug("New MPicoLocalisationProvider with id " + provider.getRouterId() + " added!");
            locProviders.put(provider.getRouterId(), provider);
        }
    }

    public void addMPicoChannel(final MPicoChannel channel) {
        if(!channels.containsKey(channel.getRouterId())) {
            LOG.debug("New MPicoChannel with id " + channel.getRouterId() + " added!");
            channels.put(channel.getRouterId(), channel);
        }
    }

    public void removeMPicoLocalisationProvider(final MPicoLocalisationProvider provider) {
        if(!locProviders.containsKey(provider.getRouterId())) {
            LOG.debug("Removing MPicoLocalisationProvider with id " + provider.getRouterId() + "!");
            locProviders.remove(provider);
        }
    }

    public void removeMPicoChannel(final MPicoChannel channel) {
        if(channels.containsKey(channel.getRouterId())) {
            LOG.debug("Removing MPicoChannel with id " + channel.getRouterId() + "!");
            channels.remove(channel);
        }
    }

    public static void main(final String[] args) {
        final String binaryCardIds = Integer.toBinaryString(Integer.parseInt("3"));
        System.out.println(binaryCardIds);
        if(binaryCardIds.length() > 8) {
            LOG.warn("Converted binary value of epids tag is longer than eight! Wrong format!");
        }
        int counter = 0;
        for(int k = binaryCardIds.length(); k > 0; k--) {
            counter++;
            if(binaryCardIds.substring(k - 1, k).equals("1")) {
                System.out.println("cardid=" + (counter + 8));
            }
        }
    }

}