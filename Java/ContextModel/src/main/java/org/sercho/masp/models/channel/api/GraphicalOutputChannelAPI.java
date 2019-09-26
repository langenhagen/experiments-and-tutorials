/**
 * 
 */
package org.sercho.masp.models.channel.api;

/**
 * <code>GraphicalOutputChannelAPI</code> defines how graphical output devices
 * communicate with the MASP. To make the device-side implementation of the
 * GraphicalOutputChannelAPI as simple and light weight as possible this
 * GraphicalOutputChannelAPI is based on primitive data types only.
 * <p>
 * New elements are put on the channel by calling one of the following methods:
 * <ol>
 * <li>{@link #addButton(String, String, int, int, int, int, int, String)}</li>
 * <li>{@link #addImage(String, String, String, int, int, int, int, int)}</li>
 * <li>{@link #addTextLabel(String, String, int, int, int, int, int, String)}</li>
 * </ol>
 * Elements are removed from the channel with {@link #remove(String)} calls.
 * <p>
 * Changes to the properties of the UI elements are propagated to the channel
 * using the following <i>new...</i> methods:
 * <ol>
 * <li>{@link #newWidth(String, int)}</li>
 * <li>{@link #newHeight(String, int)}</li>
 * <li>{@link #newPositionX(String, int)}</li>
 * <li>{@link #newPositionY(String, int)}</li>
 * <li>{@link #newPositionZ(String, int)}</li>
 * <li>{@link #newPointingX(int)}</li>
 * <li>{@link #newPointingY(int)}</li>
 * <li>{@link #newText(String, String)}</li>
 * </ol>
 * <p>
 * The MASP UI model allows to define several look & feels, which are added to
 * the channel using
 * {@link #setLookAndFeel(String, String, String, String, int, String, String, int)}
 * . This method is also used to update a look & feel if one of its properties
 * changes. Every UI element on the channel has a look & feel, which is
 * referenced in the <i>add...</i> methods. It is assured, that an appropriate
 * {@link #setLookAndFeel(String, String, String, String, int, String, String, int)}
 * call is made before any element with this look & feel is added to the
 * channel. When a property of a look & feel changes, the elements using the
 * look & feel must be re-rendered if the property affects their look (this is
 * not always the case, e.g. images are not affected by changes of font size).
 * <p>
 * There are different look & feels for different states of the UI elements.
 * These are managed by the MASP. If the state of an element changes and the
 * element should be re-rendered using different look & feel properties the MASP
 * will call the channel using {@link #setLookAndFeelOfElement(String, String)}.
 * After such a call the channel must re-render the element according to the
 * specified look & feel.
 * 
 * @author Grzegorz Lehmann
 * @since 1.1
 */
public interface GraphicalOutputChannelAPI extends OutputChannelAPI {

    /**
     * <code>setLookAndFeel</code> adds a new or updates an existing look & feel
     * on the channel. A look & feel consists of several properties, which
     * instruct the channel how to render UI elements. Every look & feel is
     * identified with an ID.
     * 
     * @param lookAndFeelID
     *            identifier of the look & feel
     * @param backgroundColor
     *            color of elements' background as RGB hex
     * @param fontColor
     *            color of elements' font as RGB hex
     * @param fontName
     *            name of elements' font
     * @param fontSize
     *            size of elements' font in pixel
     * @param fontStyle
     *            style of elements' font, one of <i>Normal</i>, <i>Italic</i>,
     *            <i>Bold</i> or <i>BoldItalic</i>
     * @param borderColor
     *            color of elements' border as RGB hex, if <code>null</code> no
     *            border should be painted
     * @param borderWidth
     *            width of elements' border in pixel
     */
    void setLookAndFeel(String lookAndFeelID, String backgroundColor, String fontColor, String fontName, int fontSize, String fontStyle, String borderColor, int borderWidth);

    /**
     * <code>removeLookAndFeel</code> removes a look & feel.
     * 
     * @param lookAndFeelID
     *            ID of look & feel to remove
     */
    void removeLookAndFeel(String lookAndFeelID);

    /**
     * <code>addButton</code> adds a button element.
     * 
     * @param elementID
     *            identifier of the element
     * @param lookAndFeelID
     *            identifier of the look & feel
     * @param x
     *            X coordinate
     * @param y
     *            Y coordinate
     * @param z
     *            Z coordinate
     * @param width
     *            width in pixel
     * @param height
     *            height in pixel
     * @param text
     *            text of the button
     */
    void addButton(String elementID, String lookAndFeelID, int x, int y, int z, int width, int height, String text);

    /**
     * <code>addTextLabel</code> adds a text label.
     * 
     * @param elementID
     *            identifier of the element
     * @param lookAndFeelID
     *            identifier of the look & feel
     * @param x
     *            X coordinate
     * @param y
     *            Y coordinate
     * @param z
     *            Z coordinate
     * @param width
     *            width in pixel
     * @param height
     *            height in pixel
     * @param text
     *            text of the label
     */
    void addTextLabel(String elementID, String lookAndFeelID, int x, int y, int z, int width, int height, String text);

    /**
     * <code>addImage</code> adds an image to the UI.
     * 
     * @param elementID
     *            identifier of the element
     * @param lookAndFeelID
     *            identifier of the look & feel
     * @param url
     *            url of the image
     * @param x
     *            X coordinate
     * @param y
     *            Y coordinate
     * @param z
     *            Z coordinate
     * @param width
     *            width in pixel
     * @param height
     *            height in pixel
     */
    void addImage(String elementID, String lookAndFeelID, String url, int x, int y, int z, int width, int height);

    /**
     * <code>setLookAndFeel</code> informs the channel about a new look & feel
     * of an element.
     * 
     * @param elementID
     *            identifier of the element
     * @param lookAndFeelID
     *            new look & feel of the element
     */
    void setLookAndFeelOfElement(String elementID, String lookAndFeelID);

    /**
     * <code>newPointingX</code> informs the channel about a new X coordinate of
     * the pointing. This pointing is relative to the channels origin.
     * 
     * @param relativeX
     *            new X coordinate of the pointing, or -1 if the pointing is no
     *            longer on the channel
     */
    void newPointingX(int relativeX);

    /**
     * <code>newPointingY</code> informs the channel about a new Y coordinate of
     * the pointing. This pointing is relative to the channels origin.
     * 
     * @param relativeY
     *            new Y coordinate of the pointing, or -1 if the pointing is no
     *            longer on the channel
     */
    void newPointingY(int relativeY);

    /**
     * <code>newPositionX</code> informs the channel about a new X coordinate of
     * an element relative to the channel.
     * 
     * @param elementID
     *            ID of the element
     * @param relativeX
     *            new X coordinate relative to this channel
     */
    void newPositionX(String elementID, int relativeX);

    /**
     * <code>newPositionY</code> informs the channel about a new Y coordinate of
     * an element relative to the channel.
     * 
     * @param elementID
     *            ID of the element
     * @param relativeY
     *            new Y coordinate relative to this channel
     */
    void newPositionY(String elementID, int relativeY);

    /**
     * <code>newPositionZ</code> informs the channel about a new Z coordinate of
     * an element.
     * 
     * @param elementID
     *            ID of the element
     * @param z
     *            new Z coordinate of the element
     */
    void newPositionZ(String elementID, int z);

    /**
     * <code>newWidth</code> informs the channel about a new width of an
     * element.
     * 
     * @param elementID
     *            ID of the element
     * @param newWidth
     *            new width of the element
     */
    void newWidth(String elementID, int newWidth);

    /**
     * <code>newText</code> informs the channel about a new text of an element,
     * e.g. a button of a text label.
     * 
     * @param elementID
     *            ID of the element
     * @param newText
     *            new text for the element
     */
    void newText(String elementID, String newText);

    /**
     * <code>newURL</code> informs the channel about a new URL of an image.
     * 
     * @param elementID
     *            ID of the element
     * @param newURL
     *            new URL for the element
     */
    void newURL(String elementID, String newURL);

    /**
     * <code>newHeight</code> informs the channel about a new height of an
     * element.
     * 
     * @param elementID
     *            ID of the element
     * @param newHeight
     *            new height of the element
     */
    void newHeight(String elementID, int newHeight);

    /**
     * <code>setCallback</code> sets a new callback for this channel.
     * 
     * @param newCallback
     *            callback for messages from this channel
     */
    void setCallback(TwoDimensionalCallback newCallback);
}
