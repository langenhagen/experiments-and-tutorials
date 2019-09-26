/**
 * 
 */
package org.sercho.masp.models.channel.api;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * <code>AbstractGraphicalOutputChannelAPI</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 */
@ThreadSafe
public abstract class AbstractGraphicalOutputChannelAPI extends AbstractOutputChannelAPI
        implements GraphicalOutputChannelAPI {

    /**
     * <code>AbstractGraphicalOutputChannelAPI</code> constructor.
     */
    protected AbstractGraphicalOutputChannelAPI() {
        super();
    }

    private final Map<String, String> elementID2LookAndFeelID = new HashMap<String, String>();

    protected final String getLookAndFeelID(final String elementID) {
        return this.elementID2LookAndFeelID.get(elementID);
    }

    private final Set<String> lookAndFeelIDs = new HashSet<String>();

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setLookAndFeel(final String lookAndFeelID, final String backgroundColor, final String fontColor, final String fontName, final int fontSize, final String fontStyle, final String borderColor, final int borderWidth) {
        if(backgroundColor == null) {
            throw new IllegalArgumentException("backgroundColor is null");
        }
        if(fontColor == null) {
            throw new IllegalArgumentException("fontColor is null");
        }
        if(fontName == null) {
            throw new IllegalArgumentException("fontName is null");
        }
        if(fontStyle == null) {
            throw new IllegalArgumentException("fontStyle is null");
        }
        if(lookAndFeelID == null) {
            throw new IllegalArgumentException("lookAndFeelId is null");
        }
        assertAvailable();
        if(this.lookAndFeelIDs.add(lookAndFeelID)) {
            this.log.trace("Got new Look&Feel: " + lookAndFeelID);
        } else {
            this.log.trace("Look&Feel updated: " + lookAndFeelID);
        }
        setLookAndFeelHook(lookAndFeelID, backgroundColor, fontColor, fontName, fontSize, fontStyle, borderColor, borderWidth, getElementsWithLookAndFeel(lookAndFeelID));
    }

    protected abstract void setLookAndFeelHook(String lookAndFeelID, String backgroundColor, String fontColor, String fontName, int fontSize, String fontStyle, String borderColor, int borderWidth, Set<String> elementIDs);

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized final void removeLookAndFeel(final String lookAndFeelID) {
        if(lookAndFeelID == null) {
            throw new IllegalArgumentException("lookAndFeelID is null");
        }
        assertAvailable();
        if(!getElementsWithLookAndFeel(lookAndFeelID).isEmpty()) {
            throw new IllegalStateException("There are stille elements with Look & Feel: " + lookAndFeelID);
        }
        this.lookAndFeelIDs.remove(lookAndFeelID);
        removeLookAndFeelHook(lookAndFeelID);
    }

    protected abstract void removeLookAndFeelHook(String lookAndFeelID);

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized final void setLookAndFeelOfElement(final String elementID, final String lookAndFeelID) {
        assertAvailable();
        assertElementExists(elementID);
        assertLookAndFeelExists(lookAndFeelID);
        setLookAndFeelOfElementHook(elementID, lookAndFeelID);
    }

    protected abstract void setLookAndFeelOfElementHook(String elementID, String lookAndFeelID);

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized final void addButton(final String elementID, final String lookAndFeelID, final int x, final int y, final int z, final int width, final int height, final String text) {
        if(elementID == null) {
            throw new IllegalArgumentException("elementID is null");
        }
        assertAvailable();
        assertLookAndFeelExists(lookAndFeelID);
        if(text == null) {
            throw new IllegalArgumentException("text is null");
        }
        this.elementID2LookAndFeelID.put(elementID, lookAndFeelID);
        addButtonHook(elementID, lookAndFeelID, x, y, z, width, height, text);
    }

    protected abstract void addButtonHook(String elementID, String lookAndFeelID, int x, int y, int z, int width, int height, String text);

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized final void addTextLabel(final String elementID, final String lookAndFeelID, final int x, final int y, final int z, final int width, final int height, final String text) {
        if(elementID == null) {
            throw new IllegalArgumentException("elementID is null");
        }
        assertAvailable();
        assertLookAndFeelExists(lookAndFeelID);
        if(text == null) {
            throw new IllegalArgumentException("text is null");
        }
        this.elementID2LookAndFeelID.put(elementID, lookAndFeelID);
        addTextLabelHook(elementID, lookAndFeelID, x, y, z, width, height, text);
    }

    protected abstract void addTextLabelHook(String elementID, String lookAndFeelID, int x, int y, int z, int width, int height, String text);

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized final void addImage(final String elementID, final String lookAndFeelID, final String url, final int x, final int y, final int z, final int width, final int height) {
        if(elementID == null) {
            throw new IllegalArgumentException("elementID is null");
        }
        assertAvailable();
        assertLookAndFeelExists(lookAndFeelID);
        if(url == null) {
            throw new IllegalArgumentException("url is null");
        }
        this.elementID2LookAndFeelID.put(elementID, lookAndFeelID);
        addImageHook(elementID, lookAndFeelID, url, x, y, z, width, height);
    }

    protected abstract void addImageHook(String elementID, String lookAndFeelID, String url, int x, int y, int z, int width, int height);

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized final void remove(final String elementID) {
        if(elementID == null) {
            throw new IllegalArgumentException("elementID is null");
        }
        assertAvailable();
        if(this.elementID2LookAndFeelID.remove(elementID) == null) {
            this.log.warn("remove called for an element not on this channel: " + elementID);
            return;
        }
        removeHook(elementID);
    }

    protected abstract void removeHook(String elementID);

    private synchronized void assertLookAndFeelExists(final String lookAndFeelID) {
        if(lookAndFeelID == null) {
            throw new IllegalArgumentException("lookAndFeelID is null");
        }
        if(!this.lookAndFeelIDs.contains(lookAndFeelID)) {
            throw new IllegalArgumentException("Unknown Look & Feel: " + lookAndFeelID);
        }
    }

    private synchronized void assertElementExists(final String elementID) {
        if(elementID == null) {
            throw new IllegalArgumentException("elementID is null");
        }
        if(this.elementID2LookAndFeelID.get(elementID) == null) {
            throw new IllegalArgumentException("Unknown element: " + elementID);
        }
    }

    protected final Set<String> getElementsWithLookAndFeel(final String lookAndFeelID) {
        if(lookAndFeelID == null) {
            this.log.warn("Got null lookAndFeelID");
            return null;
        }
        final Set<String> elementIDs = new HashSet<String>();
        for(final Entry<String, String> entry : this.elementID2LookAndFeelID.entrySet()) {
            if(lookAndFeelID.equals(entry.getValue())) {
                elementIDs.add(entry.getKey());
            }
        }
        return elementIDs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void newHeight(final String elementID, final int height) {
        assertAvailable();
        assertElementExists(elementID);
        newHeightHook(elementID, height);
    }

    protected abstract void newHeightHook(String elementID, int height);

    /**
     * {@inheritDoc}
     */
    @Override
    public final void newWidth(final String elementID, final int width) {
        assertAvailable();
        assertElementExists(elementID);
        newWidthHook(elementID, width);
    }

    protected abstract void newWidthHook(String elementID, int width);

    /**
     * {@inheritDoc}
     */
    @Override
    public final void newPositionX(final String elementID, final int relativeX) {
        assertAvailable();
        assertElementExists(elementID);
        newPositionXHook(elementID, relativeX);
    }

    protected abstract void newPositionXHook(String elementID, int relativeX);

    /**
     * {@inheritDoc}
     */
    @Override
    public final void newPositionY(final String elementID, final int relativeY) {
        assertAvailable();
        assertElementExists(elementID);
        newPositionYHook(elementID, relativeY);
    }

    protected abstract void newPositionYHook(String elementID, int relativeY);

    /**
     * {@inheritDoc}
     */
    @Override
    public final void newPositionZ(final String elementID, final int z) {
        assertAvailable();
        assertElementExists(elementID);
        newPositionZHook(elementID, z);
    }

    protected abstract void newPositionZHook(String elementID, int z);

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized final void newText(final String elementID, final String newText) {
        assertAvailable();
        assertElementExists(elementID);
        if(newText == null) {
            throw new IllegalArgumentException("newText is null");
        }
        newTextHook(elementID, newText);
    }

    protected abstract void newTextHook(String elementID, String newText);

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized final void newURL(final String elementID, final String newURL) {
        assertAvailable();
        assertElementExists(elementID);
        if(newURL == null) {
            throw new IllegalArgumentException("newURL is null");
        }
        newURLHook(elementID, newURL);
    }

    protected abstract void newURLHook(String elementID, String newURL);

    /**
     * <code>callback</code>
     */
    @GuardedBy("callbackLock")
    private volatile TwoDimensionalCallback callback;

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized final void setCallback(final TwoDimensionalCallback newCallback) {
        if(newCallback == null) {
            throw new IllegalArgumentException("newCallback is null");
        }
        this.log.trace("Got new callback");
        this.callback = newCallback;
        if(isAvailable()) {
            this.callback.newPosition(getPositionX(), getPositionY());
            this.callback.newSize(getWidth(), getHeight());
        }
    }

    /**
     * <code>newSize</code> informs the MASP about a new size of a
     * two-dimensional channel.
     * 
     * @param width
     *            new width of the channel
     * @param height
     *            new height of the channel
     */
    public synchronized final void newSize(final int width, final int height) {
        if(this.callback == null) {
            this.log.warn("No callback to send new size");
            return;
        }
        this.callback.newSize(width, height);
    }

    /**
     * <code>newPosition</code> informs the MASP about a new position of a
     * two-dimensional channel.
     * 
     * @param relativeX
     *            new X-coordinate of the channel relative to its interaction
     *            resource
     * @param relativeY
     *            new Y-coordinate of the channel relative to its interaction
     *            resource
     */
    public synchronized final void newPosition(final int relativeX, final int relativeY) {
        if(this.callback == null) {
            this.log.warn("No callback to send new position");
            return;
        }
        this.callback.newPosition(relativeX, relativeY);
    }

    /**
     * <code>getPositionX</code> returns the current X coordinate of this
     * channel relative to its interaction resource.
     * 
     * @return int - X coordinate of the channel relative to its interaction
     *         resource
     */
    protected abstract int getPositionX();

    /**
     * <code>getPositionY</code> returns the current Y coordinate of this
     * channel relative to its interaction resource.
     * 
     * @return int - Y coordinate of the channel relative to its interaction
     *         resource
     */
    protected abstract int getPositionY();

    /**
     * <code>getWidth</code> returns the current width of this channel.
     * 
     * @return int - width of this channel
     */
    protected abstract int getWidth();

    /**
     * <code>getHeight</code> returns the current height of this channel.
     * 
     * @return int - height of this channel
     */
    protected abstract int getHeight();
}