/**
 * 
 */
package org.sercho.masp.models.channel.api;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <code>LegacyMASPChannel</code> is for downward compatibility for the old
 * MASP.
 * 
 * @author Grzegorz Lehmann
 * @author Dirk Roscher
 */
public final class LegacyMASPChannel extends AbstractGraphicalOutputChannelAPI implements
        MessageInputChannelAPI, MessageOutputChannelAPI, PointingInputChannelAPI {

    private static final transient Log LOG = LogFactory.getLog(LegacyMASPChannel.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addButtonHook(final String elementID, final String lookAndFeelID, final int x, final int y, final int z, final int width, final int height, final String text) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addImageHook(final String elementID, final String lookAndFeelID, final String url, final int x, final int y, final int z, final int width, final int height) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addTextLabelHook(final String elementID, final String lookAndFeelID, final int x, final int y, final int z, final int width, final int height, final String text) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getHeight() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getPositionX() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getPositionY() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getWidth() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void newHeightHook(final String elementID, final int height) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void newPositionXHook(final String elementID, final int relativeX) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void newPositionYHook(final String elementID, final int relativeY) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void newPositionZHook(final String elementID, final int z) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void newTextHook(final String elementID, final String newText) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void newURLHook(final String elementID, final String newURL) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void newWidthHook(final String elementID, final int width) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void removeHook(final String elementID) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void removeLookAndFeelHook(final String lookAndFeelID) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setLookAndFeelHook(final String lookAndFeelID, final String backgroundColor, final String fontColor, final String fontName, final int fontSize, final String fontStyle, final String borderColor, final int borderWidth, final Set<String> elementIDs) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setLookAndFeelOfElementHook(final String elementID, final String lookAndFeelID) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void close() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void startHook(final Map<String, String> configurationMap) {
        final String clientId = configurationMap.get("clientId");
        this.log.debug("clientId=" + clientId);
        if(clientId != null) {
            synchronized(availableChannels) {
                for(final String resourceId : availableResources) {
                    if(clientId.startsWith(resourceId)) {
                        setAvailable(Boolean.TRUE);
                        break;
                    }
                }
                availableChannels.put(clientId, this);
            }
        } else {
            this.log.info("No clientId defined in LegacyMASPChannel!");
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newPointingX(final int relativeX) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newPointingY(final int relativeY) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCallback(final MessageCallback newCallback) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activate(final String elementID) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void focus(final String elementID) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void select(final String elementID) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(final String elementID, final String message) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCallback(final OneDimensionalCallback newCallback) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCallback(final PointingCallback callback) {
        // TODO Auto-generated method stub

    }

    private static final Map<String, LegacyMASPChannel> availableChannels = new HashMap<String, LegacyMASPChannel>();

    private static final Set<String> availableResources = new HashSet<String>();

    public static void registerClient(final String clientId) {
        LOG.debug("CALLED with id=" + clientId + "!");

        // go through all available channels because the registration of one
        // client can lead to the activation of several channels (=IRs for the
        // old MASP)
        synchronized(availableChannels) {
            availableResources.add(clientId);
            for(final String key : availableChannels.keySet()) {
                if(key.startsWith(clientId)) {
                    final LegacyMASPChannel channel = availableChannels.get(key);
                    channel.setAvailable(Boolean.TRUE);
                }
            }
        }
    }

    /**
     * <code>removeClient</code> TODO comment
     * 
     * @todo add missing JavaDoc
     * @param clientId
     */
    public static void removeClient(final String clientId) {
        LOG.debug("CALLED with id=" + clientId + "!");
        synchronized(availableChannels) {
            availableResources.remove(clientId);
            for(final String key : availableChannels.keySet()) {
                if(key.startsWith(clientId)) {
                    final LegacyMASPChannel channel = availableChannels.get(key);
                    channel.setAvailable(Boolean.FALSE);
                }
            }
        }
    }

    public static void reset() {
        synchronized(availableChannels) {
            availableResources.clear();
            availableChannels.clear();
        }
    }

    public static void interactionRequested(final String clientId) {
        LOG.debug("LegacyMASPChannel.interactionRequested(): CALLED with id=" + clientId + "!");

        synchronized(availableChannels) {
            availableResources.add(clientId);
            for(final String key : availableChannels.keySet()) {
                if(key.startsWith(clientId)) {
                    final LegacyMASPChannel channel = availableChannels.get(key);
                    channel.interactionRequested();
                    if(LOG.isDebugEnabled()) {
                        LOG.debug("Found channel for interaction request: " + key + " coming from client: " + clientId);
                    }
                    return;
                }
            }
        }
        if(LOG.isWarnEnabled()) {
            LOG.warn("Failed to find channel for interaction request coming from client: " + clientId);
        }
    }
}
