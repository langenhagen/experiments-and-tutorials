/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.Channel;
import org.sercho.masp.models.Context.ConfigurationProperty;
import org.sercho.masp.models.Context.ConfigurationPropertyUtility;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.GraphicalOutputChannel;
import org.sercho.masp.models.Context.MessageInputChannel;
import org.sercho.masp.models.Context.MessageOutputChannel;
import org.sercho.masp.models.Context.PointingInputChannel;
import org.sercho.masp.models.Context.console.SystemStreamOutputChannel;
import org.sercho.masp.models.UI.Button;
import org.sercho.masp.models.UI.Command;
import org.sercho.masp.models.UI.Constant;
import org.sercho.masp.models.UI.GraphicalPropertySet;
import org.sercho.masp.models.UI.Image;
import org.sercho.masp.models.UI.LookAndFeel;
import org.sercho.masp.models.UI.MessageOutput;
import org.sercho.masp.models.UI.Pointing;
import org.sercho.masp.models.UI.PredefinedMessage;
import org.sercho.masp.models.UI.UIFactory;
import org.sercho.masp.models.UI.UIModel;
import org.sercho.masp.models.UI.UIPackage;
import org.sercho.masp.models.channel.api.AbstractGraphicalOutputChannelAPI;

import de.dailab.masp.models.MetaMetaModel.MetaModel;
import de.dailab.masp.models.MetaMetaModel.ecore.EcoreMetaModelConverter;

/**
 * <code>SwingGraphicalOutputChannel</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 */
public final class SwingGraphicalOutputChannel extends AbstractGraphicalOutputChannelAPI {

    private static final class LookAndFeelDescriptor {

        private static final Color convert(final String rgbHex) {
            if(rgbHex == null || rgbHex.length() < 6) {
                throw new IllegalArgumentException("Not a valid RGB hex: " + rgbHex);
            }
            final String red = rgbHex.substring(0, 2);
            final String green = rgbHex.substring(2, 4);
            final String blue = rgbHex.substring(4, 6);
            return new Color(Integer.parseInt(red, 16), Integer.parseInt(green, 16), Integer.parseInt(blue, 16));
        }

        final String id;

        volatile Color backgroundColor;

        volatile Color fontColor;

        volatile Color borderColor;

        volatile String fontName;

        volatile int fontSize;

        volatile String fontStyle;

        volatile int borderWidth;

        /**
         * <code>LookAndFeelDescriptor</code> constructor.
         * 
         * @param identifier
         *            L&F identifier
         * @param backgroundColor
         * @param fontColor
         * @param fontName
         * @param fontSize
         * @param fontStyle
         * @param borderColor
         * @param borderWidth
         */
        public LookAndFeelDescriptor(final String identifier,
                final String backgroundColor, final String fontColor,
                final String fontName, final int fontSize, final String fontStyle,
                final String borderColor, final int borderWidth) {
            if(identifier == null) {
                throw new IllegalArgumentException("identifier is null");
            }
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
            this.id = identifier;
            setBackgroundColor(backgroundColor);
            setFontColor(fontColor);
            setBorderColor(borderColor);
            this.fontName = fontName;
            this.fontSize = fontSize;
            this.fontStyle = fontStyle;
            this.borderWidth = borderWidth;
        }

        /**
         * <code>setBackgroundColor</code> setter
         * 
         * @param rgbHex
         *            RGB hexadecimal string
         */
        public void setBackgroundColor(final String rgbHex) {
            this.backgroundColor = convert(rgbHex);
        }

        /**
         * <code>setBorderColor</code> setter
         * 
         * @param rgbHex
         *            RGB hexadecimal string
         */
        public void setBorderColor(final String rgbHex) {
            this.borderColor = rgbHex == null ? null : convert(rgbHex);
        }

        /**
         * <code>setFontColor</code> setter
         * 
         * @param rgbHex
         *            RGB hexadecimal string
         */
        public void setFontColor(final String rgbHex) {
            this.fontColor = convert(rgbHex);
        }
    }

    private final class RemoveRunnable implements Runnable {

        private final JComponent c;

        /**
         * <code>RemoveRunnable</code> constructor.
         * 
         * @param component
         *            component to remove, <code>null</code> disallowed
         */
        RemoveRunnable(final JComponent component) {
            this.c = component;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
            SwingGraphicalOutputChannel.this.panel.remove(this.c);
            SwingGraphicalOutputChannel.this.panel.repaint();
        }
    }

    /**
     * Resizes an image using a Graphics2D object backed by a BufferedImage.
     * 
     * @param srcImg
     *            - source image to scale
     * @param w
     *            - desired width
     * @param h
     *            - desired height
     * @return - the new resized image
     */
    private static BufferedImage getScaledImage(final java.awt.Image srcImg, final int w, final int h) {
        final BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    private static void addConfigurationProperty(final String key, final String value, final Channel<?, ?> channel) {
        final ConfigurationProperty property = ContextFactory.eINSTANCE.createConfigurationProperty();
        property.setKey(key);
        property.setValue(value);
        channel.getConfiguration().add(property);
    }

    /**
     * <code>main</code> for tests
     * 
     * @param args
     *            program arguments, ignored
     */
    public static void main(final String[] args) {
        final ContextFactory contextFactory = ContextPackage.eINSTANCE.getContextFactory();
        final UIFactory uiFactory = UIPackage.eINSTANCE.getUIFactory();

        final MetaModel metaModel = EcoreMetaModelConverter.convert(ContextPackage.eINSTANCE);
        metaModel.getMetaType().getMetaModel().start(metaModel);

        // graphical output channels
        final GraphicalOutputChannel graphicalOutputChannel = contextFactory.createGraphicalOutputChannel();
        graphicalOutputChannel.setApiClass(SwingGraphicalOutputChannel.class.getName());
        addConfigurationProperty(SwingChannelConfigurationConstants.PROPERTY_WINDOW_NAME, "Frame1", graphicalOutputChannel);
        addConfigurationProperty(SwingChannelConfigurationConstants.PROPERTY_WINDOW_HEIGHT, "400", graphicalOutputChannel);
        addConfigurationProperty(SwingChannelConfigurationConstants.PROPERTY_WINDOW_WIDTH, "600", graphicalOutputChannel);
        addConfigurationProperty(SwingChannelConfigurationConstants.PROPERTY_WINDOW_POSITION_X, "100", graphicalOutputChannel);
        addConfigurationProperty(SwingChannelConfigurationConstants.PROPERTY_WINDOW_POSITION_Y, "100", graphicalOutputChannel);
        metaModel.start(graphicalOutputChannel);

        // pointing input channels
        final PointingInputChannel pointingChannel = contextFactory.createPointingInputChannel();
        addConfigurationProperty(SwingChannelConfigurationConstants.PROPERTY_WINDOW_NAME, "Frame1", pointingChannel);
        pointingChannel.setApiClass(SwingPointingInputChannel.class.getName());
        metaModel.start(pointingChannel);

        // message input channel
        final MessageInputChannel messageInputChannel = contextFactory.createMessageInputChannel();
        addConfigurationProperty(SwingChannelConfigurationConstants.PROPERTY_WINDOW_NAME, "Frame1", messageInputChannel);
        messageInputChannel.setApiClass(SwingWindowMessageInputChannel.class.getName());
        metaModel.start(messageInputChannel);

        // message output channel
        final MessageOutputChannel messageOutputChannel = contextFactory.createMessageOutputChannel();
        messageOutputChannel.setApiClass(SystemStreamOutputChannel.class.getName());
        addConfigurationProperty(SystemStreamOutputChannel.PROPERTY_STREAM_TYPE, SystemStreamOutputChannel.STREAM_TYPE_OUT, messageOutputChannel);
        metaModel.start(messageOutputChannel);

        // define UI
        final UIModel uiModel = uiFactory.createUIModel();
        final Command okCommand = uiFactory.createCommand();
        uiModel.getAbstractInteractors().add(okCommand);
        final Command cancelCommand = uiFactory.createCommand();
        uiModel.getAbstractInteractors().add(cancelCommand);

        final Pointing okPointing = uiFactory.createPointing();
        okCommand.getFocusInput().add(okPointing);
        pointingChannel.add(okPointing);

        final Pointing cancelPointing = uiFactory.createPointing();
        cancelCommand.getFocusInput().add(cancelPointing);
        pointingChannel.add(cancelPointing);

        final Button okButton = uiFactory.createButton();
        okCommand.getGraphicalOutput().add(okButton);
        Constant variable = UIFactory.eINSTANCE.createConstant();
        variable.setText("OK");
        okButton.getTextVariables().add(variable);
        okButton.setName("OK");
        okButton.activate();
        okButton.setPosition(250, 150);
        okButton.setSize(100, 25);

        final Button cancelButton = uiFactory.createButton();
        cancelCommand.getGraphicalOutput().add(cancelButton);
        variable = UIFactory.eINSTANCE.createConstant();
        variable.setText("Cancel");
        cancelButton.getTextVariables().add(variable);
        cancelButton.setName("Cancel");
        cancelButton.activate();
        cancelButton.setPosition(550, 150);
        cancelButton.setSize(100, 25);

        final LookAndFeel lf = UIFactory.eINSTANCE.createLookAndFeel();
        GraphicalPropertySet set = UIFactory.eINSTANCE.createGraphicalPropertySet();
        // set.setBackgroundColor(UIFactory.eINSTANCE.createWhite());
        set.setFontColor(UIFactory.eINSTANCE.createRed());
        set.setBorderColor(UIFactory.eINSTANCE.createRed());
        set.setHasBorder(Boolean.TRUE);
        lf.setFocused(set);
        set = UIFactory.eINSTANCE.createGraphicalPropertySet();
        // set.setBackgroundColor(UIFactory.eINSTANCE.createWhite());
        set.setFontColor(UIFactory.eINSTANCE.createGreen());
        set.setHasBorder(Boolean.TRUE);
        set.setBorderColor(UIFactory.eINSTANCE.createGreen());
        lf.setSelected(set);
        set = UIFactory.eINSTANCE.createGraphicalPropertySet();
        // set.setBackgroundColor(UIFactory.eINSTANCE.createWhite());
        set.setFontColor(UIFactory.eINSTANCE.createBlack());
        set.setHasBorder(Boolean.TRUE);
        set.setBorderColor(UIFactory.eINSTANCE.createBlack());
        lf.setActive(set);

        uiModel.getLookAndFeels().add(lf);
        okButton.setLookAndFeel(lf);
        cancelButton.setLookAndFeel(lf);

        final Image image = uiFactory.createImage();
        image.setUrl("http://www.dai-labor.de/fileadmin/templates/PAGE-Elements/DAI_mit_News/images/dai-logo.gif");
        image.setSize(168, 33);
        image.setPosition(100, 145);
        image.setName("DAI Logo");

        final MessageOutput proceedMessageOutput = uiFactory.createMessageOutput();
        proceedMessageOutput.setMessage("Proceed?");
        okCommand.getMessageOutput().add(proceedMessageOutput);
        messageOutputChannel.add(proceedMessageOutput);
        final MessageOutput cancelMessageOutput = uiFactory.createMessageOutput();
        cancelMessageOutput.setMessage("Cancel?");
        cancelCommand.getMessageOutput().add(cancelMessageOutput);
        messageOutputChannel.add(cancelMessageOutput);

        final PredefinedMessage okMessage = uiFactory.createPredefinedMessage();
        okMessage.setMessage("ok");
        okCommand.getExecuteInput().add(okMessage);

        final PredefinedMessage okClickMessage = uiFactory.createSelection();
        okCommand.getExecuteInput().add(okClickMessage);
        messageInputChannel.add(okClickMessage);

        final PredefinedMessage proceedMessage = uiFactory.createPredefinedMessage();
        proceedMessage.setMessage("proceed");
        okCommand.getExecuteInput().add(proceedMessage);
        final PredefinedMessage dalejMessage = uiFactory.createPredefinedMessage();
        dalejMessage.setMessage("dalej");
        okCommand.getExecuteInput().add(dalejMessage);

        final PredefinedMessage cancelMessage = uiFactory.createPredefinedMessage();
        cancelMessage.setMessage("cancel");
        cancelCommand.getUnfocusInput().add(cancelMessage);

        final PredefinedMessage cancelClickMessage = uiFactory.createSelection();
        cancelCommand.getExecuteInput().add(cancelClickMessage);
        messageInputChannel.add(cancelClickMessage);

        okCommand.activate();
        cancelCommand.activate();

        // put okButton on all channels
        graphicalOutputChannel.add(okButton);

        // put image on all channels
        graphicalOutputChannel.add(image);

        // put cancelButton on all channels
        graphicalOutputChannel.add(cancelButton);

        okButton.select();
    }

    /**
     * <code>frame</code>
     */
    private final JFrame frame = new JFrame();

    /**
     * <code>panel</code>
     */
    FlexiblePanel panel = new FlexiblePanel();

    private final Map<String, JComponent> components = new HashMap<String, JComponent>();

    private final HashMap<String, String> icons = new HashMap<String, String>();

    private int pointingX = -1;

    private int pointingY = -1;

    private final Map<String, LookAndFeelDescriptor> looksAndFeels = new Hashtable<String, LookAndFeelDescriptor>();

    /**
     * <code>SwingGraphicalOutputChannel</code> constructor.
     */
    public SwingGraphicalOutputChannel() {
        this.frame.setUndecorated(true);
        this.frame.setContentPane(this.panel);
        this.panel.addComponentListener(new ComponentAdapter() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void componentResized(final ComponentEvent e) {
                newSize(SwingGraphicalOutputChannel.this.panel.getWidth(), SwingGraphicalOutputChannel.this.panel.getHeight());
            }
        });
        this.frame.addComponentListener(new ComponentAdapter() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void componentMoved(final ComponentEvent e) {
                final Point newPosition = SwingGraphicalOutputChannel.this.panel.getLocationOnScreen();
                newPosition(newPosition.x, newPosition.y);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addButtonHook(final String elementID, final String lookAndFeelID, final int x, final int y, final int z, final int width, final int height, final String text) {
        // create component
        final JComponent component = new JButton(text);
        component.setName(elementID);

        // set size
        final Dimension size = new Dimension(width, height);
        component.setMinimumSize(size);
        component.setMaximumSize(size);
        component.setSize(size);

        // set look and feel
        setLookAndFeel(component, lookAndFeelID);

        // put element on channel
        addComponent(elementID, component, x, y, z);
    }

    private synchronized void addComponent(final String elementID, final JComponent jComponent, final int x, final int y, final int z) {
        // add component
        this.components.put(elementID, jComponent);
        try {
            execute(new Runnable() {

                /**
                 * {@inheritDoc}
                 */
                @Override
                public void run() {
                    SwingGraphicalOutputChannel.this.panel.add(jComponent, x, y, z);
                }
            });
        }
        catch(final InterruptedException e) {
            this.components.remove(elementID);
            // can't do much :(
            // TODO re-throw interrupt in an unchecked exception?
            this.log.warn("Got interrupted while adding " + elementID);
        }
        catch(final InvocationTargetException e) {
            this.components.remove(elementID);
            this.log.warn("Failed to add " + elementID, e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addImageHook(final String elementID, final String lookAndFeelID, final String url, final int x, final int y, final int z, final int width, final int height) {
        // create component

        final BufferedImage image = load(url, width, height);
        this.icons.put(elementID, url);
        final JComponent component = image == null ? new JButton(elementID)
                : new JLabel(new ImageIcon(image));

        // set size
        final Dimension size = new Dimension(width, height);
        component.setMinimumSize(size);
        component.setMaximumSize(size);
        component.setSize(size);

        // set look and feel
        setLookAndFeel(component, lookAndFeelID);

        // put element on channel
        addComponent(elementID, component, x, y, z);
    }

    /**
     * <code>LOG</code> is the logger for this class.
     */
    public static final transient Log LOG = LogFactory.getLog(SwingGraphicalOutputChannel.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void startHook(final Map<String, String> configurationMap) {
        // get the window for this channel
        final String windowName = ConfigurationPropertyUtility.getPropertyValue(configurationMap, this, SwingChannelConfigurationConstants.PROPERTY_WINDOW_NAME);

        final Window window = WindowUtility.getWindow(windowName);
        if(window != null) {
            // channel already open
            LOG.error("Cannot open channel, window '" + windowName + "' already exists");
            throw new IllegalArgumentException("Cannot open channel, window '" + windowName + "' already exists");
        }

        final int windowWidth = SwingChannelConfigurationConstants.getWindowWidth(configurationMap, this);
        final int windowHeight = SwingChannelConfigurationConstants.getWindowHeight(configurationMap, this);
        final int windowX = SwingChannelConfigurationConstants.getWindowPositionX(configurationMap, this);
        final int windowY = SwingChannelConfigurationConstants.getWindowPositionY(configurationMap, this);
        this.frame.setName(windowName);
        this.frame.setTitle(windowName);
        this.frame.setSize(windowWidth, windowHeight);
        this.frame.setLocation(windowX, windowY);
        this.frame.setVisible(true);
        setAvailable(Boolean.TRUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void close() {
        // reset name of frame
        this.frame.setName(null);
        this.frame.setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTextLabelHook(final String elementID, final String lookAndFeelID, final int x, final int y, final int z, final int width, final int height, final String text) {
        // create component
        final JComponent component = new JLabel(text, SwingConstants.CENTER);
        component.setName(elementID);

        // set size
        final Dimension size = new Dimension(width, height);
        component.setMinimumSize(size);
        component.setMaximumSize(size);
        component.setSize(size);

        // set look and feel
        setLookAndFeel(component, lookAndFeelID);

        // put element on channel
        addComponent(elementID, component, x, y, z);
    }

    private void execute(final Runnable runnable) throws InterruptedException, InvocationTargetException {
        if(SwingUtilities.isEventDispatchThread()) {
            runnable.run();
        } else {
            SwingUtilities.invokeAndWait(runnable);
        }
    }

    /**
     * <code>getFrame</code> getter
     * 
     * @return JFrame - <code>frame</code> of this
     *         <code>SwingGraphicalOutputChannel</code>
     */
    public JFrame getFrame() {
        return this.frame;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getHeight() {
        return this.panel.getHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getPositionX() {
        return this.frame.isVisible() ? this.panel.getLocationOnScreen().x : 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getPositionY() {
        return this.frame.isVisible() ? this.panel.getLocationOnScreen().y : 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getWidth() {
        return this.panel.getWidth();
    }

    private BufferedImage load(final String url, final int width, final int height) {
        try {
            return getScaledImage(ImageIO.read(new URL(url)), width, height);
        }
        catch(final MalformedURLException e) {
            this.log.trace(e.getMessage(), e);
            InputStream in = getClass().getClassLoader().getResourceAsStream(url);
            if(in == null) {
                try {
                    in = new FileInputStream(url);
                }
                catch(final FileNotFoundException e1) {
                    // nothing to do here
                }
            }
            if(in != null) {
                try {
                    return getScaledImage(ImageIO.read(in), width, height);
                }
                catch(final IOException e1) {
                    this.log.debug(e1.getMessage(), e1);
                }
            }
        }
        catch(final IOException e) {
            this.log.debug("Failed to retrieve image: " + url, e);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newHeightHook(final String elementID, final int height) {
        final JComponent c = this.components.get(elementID);
        if(c != null) {
            final Dimension newSize = new Dimension(c.getWidth(), height);
            c.setMinimumSize(newSize);
            c.setMaximumSize(newSize);
            c.setSize(newSize);
            // resize included image
            if((c instanceof JLabel) && (((JLabel)c).getIcon() != null)) {
                ((JLabel)c).setIcon(new ImageIcon(load(this.icons.get(elementID), c.getWidth(), c.getHeight())));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newWidthHook(final String elementID, final int width) {
        final JComponent c = this.components.get(elementID);
        if(c != null) {
            final Dimension newSize = new Dimension(width, c.getHeight());
            c.setMinimumSize(newSize);
            c.setMaximumSize(newSize);
            c.setSize(newSize);
            // resize included image
            if((c instanceof JLabel) && (((JLabel)c).getIcon() != null)) {
                ((JLabel)c).setIcon(new ImageIcon(load(this.icons.get(elementID), c.getWidth(), c.getHeight())));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void newPointingX(final int relativeX) {
        if(relativeX < 0) {
            this.pointingX = -1;
            this.pointingY = -1;
            this.panel.resetPointing();
            return;
        }
        this.pointingX = relativeX;
        if(this.pointingY < 0) {
            return;
        }
        this.panel.setPointing(this.pointingX, this.pointingY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void newPointingY(final int relativeY) {
        if(relativeY < 0) {
            this.pointingX = -1;
            this.pointingY = -1;
            this.panel.resetPointing();
            return;
        }
        this.pointingY = relativeY;
        if(this.pointingX < 0) {
            return;
        }
        this.panel.setPointing(this.pointingX, this.pointingY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newPositionXHook(final String elementID, final int relativeX) {
        final JComponent c = this.components.get(elementID);
        if(c != null) {
            this.panel.setPosition(c, relativeX, c.getY());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newPositionYHook(final String elementID, final int relativeY) {
        final JComponent c = this.components.get(elementID);
        if(c != null) {
            this.panel.setPosition(c, c.getX(), relativeY);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newPositionZHook(final String elementID, final int z) {
        // TODO rework this
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newTextHook(final String elementID, final String newText) {
        final JComponent component = this.components.get(elementID);
        if(component instanceof JButton) {
            ((JButton)component).setText(newText);
        } else if(component instanceof JLabel) {
            ((JLabel)component).setText(newText);
        } else {
            this.log.error("Unknown component: " + component);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newURLHook(final String elementID, final String newURL) {
        final JComponent component = this.components.get(elementID);
        if(component instanceof JLabel) {
            final BufferedImage image = load(newURL, component.getWidth(), component.getHeight());
            if(image != null) {
                ((JLabel)component).setIcon(new ImageIcon(image));
                return;
            }
            this.log.warn("Failed to load image from new URL: " + newURL);
        } else {
            this.log.error("Unknown component: " + component);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeHook(final String elementID) {
        final JComponent c = this.components.get(elementID);
        if(c != null) {
            try {
                execute(new RemoveRunnable(c));
            }
            catch(final InterruptedException e) {
                // can't do much :(
                // TODO re-throw in an unchecked exception?
                this.log.warn("Got interrupted while removing " + elementID);
            }
            catch(final InvocationTargetException e) {
                this.log.warn("Failed to remove " + elementID, e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeLookAndFeelHook(final String lookAndFeelID) {
        this.looksAndFeels.remove(lookAndFeelID);
    }

    private void setLookAndFeel(final JComponent component, final String lookAndFeelID) {
        final LookAndFeelDescriptor lookAndFeel = this.looksAndFeels.get(lookAndFeelID);
        if(lookAndFeel == null) {
            this.log.error("Unknown LookAndFeel: " + lookAndFeelID);
            return;
        }
        // set look and feel
        component.setBackground(lookAndFeel.backgroundColor);
        component.setForeground(lookAndFeel.fontColor);

        final Color borderColor = lookAndFeel.borderColor;
        if(borderColor == null) {
            component.setBorder(null);
        } else {
            component.setBorder(new LineBorder(lookAndFeel.borderColor, lookAndFeel.borderWidth));
        }
        component.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLookAndFeelOfElementHook(final String elementID, final String lookAndFeelID) {
        final JComponent component = this.components.get(elementID);
        if(component == null) {
            this.log.error("Unknown elementID: " + elementID);
            return;
        }
        setLookAndFeel(component, lookAndFeelID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setLookAndFeelHook(final String lookAndFeelID, final String backgroundColor, final String fontColor, final String fontName, final int fontSize, final String fontStyle, final String borderColor, final int borderWidth, final Set<String> elementIDs) {
        LookAndFeelDescriptor lookAndFeel = this.looksAndFeels.get(lookAndFeelID);
        if(lookAndFeel == null) {
            lookAndFeel = new LookAndFeelDescriptor(lookAndFeelID, backgroundColor, fontColor, fontName, fontSize, fontStyle, borderColor, borderWidth);
            this.looksAndFeels.put(lookAndFeelID, lookAndFeel);
        } else {
            // update lookAndFeel
            lookAndFeel.setBackgroundColor(backgroundColor);
            lookAndFeel.setBorderColor(borderColor);
            lookAndFeel.setFontColor(fontColor);
            lookAndFeel.borderWidth = borderWidth;
            lookAndFeel.fontName = fontName;
            lookAndFeel.fontSize = fontSize;
            lookAndFeel.fontStyle = fontStyle;

            // update elements with this L&F
            for(final String elementID : elementIDs) {
                setLookAndFeel(this.components.get(elementID), lookAndFeelID);
            }
        }
    }
}