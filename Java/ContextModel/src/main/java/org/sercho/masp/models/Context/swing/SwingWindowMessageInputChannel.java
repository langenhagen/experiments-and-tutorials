/**
 * File     SwingWindowMessageInputChannel.java
 * Package  org.sercho.masp.models.Context.swing
 * Project  ContextModel
 * Date     17.01.2008
 * Web      http://www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.models.Context.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Window;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.sercho.masp.models.Context.ConfigurationPropertyUtility;
import org.sercho.masp.models.Context.swing.WindowUtility.WindowObserver;
import org.sercho.masp.models.UI.UIFactory;
import org.sercho.masp.models.channel.api.AbstractMessageInputChannelAPI;

/**
 * <code>SwingWindowMessageInputChannel</code> captures mouse events from a
 * Swing {@link Window}. This channel registers itself for events in one of the
 * available {@link Window}s, retrieved using {@link Dialog#getWindows()}. The
 * channel registers itself for the events of the window with the name matching
 * the {@value SwingChannelConfigurationConstants#PROPERTY_WINDOW_NAME}
 * configuration property.
 * 
 * @author Grzegorz Lehmann
 * @since 1.1.3
 */
public final class SwingWindowMessageInputChannel extends AbstractMessageInputChannelAPI {

    static final String PRIMARY_SIGNAL_MESSAGE = UIFactory.eINSTANCE.createSelection().getMessage();

    final transient MouseListener clickListener = new MouseAdapter() {

        private final Runnable sendPrimaryMessage = new Runnable() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void run() {
                newMessage(PRIMARY_SIGNAL_MESSAGE);
            }
        };

        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseClicked(final MouseEvent e) {
            SwingUtilities.invokeLater(this.sendPrimaryMessage);
        }
    };

    final transient ContainerAdapter containerAdapter = new ContainerAdapter() {

        /**
         * {@inheritDoc}
         */
        @Override
        public void componentAdded(final ContainerEvent e) {
            final Component component = e.getChild();
            if(component instanceof Container) {
                for(final Component child : WindowUtility.getAllComponents((Container)component)) {
                    child.addMouseListener(SwingWindowMessageInputChannel.this.clickListener);
                    if(child instanceof Container) {
                        ((Container)child).addContainerListener(this);
                    }
                }
            } else {
                component.addMouseListener(SwingWindowMessageInputChannel.this.clickListener);
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void componentRemoved(final ContainerEvent e) {
            final Component component = e.getChild();
            if(component instanceof Container) {
                for(final Component child : WindowUtility.getAllComponents((Container)component)) {
                    child.removeMouseListener(SwingWindowMessageInputChannel.this.clickListener);
                    if(child instanceof Container) {
                        ((Container)child).removeContainerListener(this);
                    }
                }
            } else {
                component.removeMouseListener(SwingWindowMessageInputChannel.this.clickListener);
            }
        }
    };

    transient volatile Window window;

    private WindowObserver windowObserver;

    /**
     * {@inheritDoc}
     */
    @Override
    protected synchronized void startHook(final Map<String, String> configurationMap) {
        // get the window for this channel
        final String windowName = ConfigurationPropertyUtility.getPropertyValue(configurationMap, this, SwingChannelConfigurationConstants.PROPERTY_WINDOW_NAME);

        this.windowObserver = new WindowUtility.WindowObserver(windowName) {

            /**
             * {@inheritDoc}
             */
            @Override
            void newWindow(final Window w) {
                SwingWindowMessageInputChannel.this.window = w;
                // register listeners
                for(final Component component : WindowUtility.getAllComponents(w)) {
                    component.addMouseListener(SwingWindowMessageInputChannel.this.clickListener);
                    if(component instanceof Container) {
                        ((Container)component).addContainerListener(SwingWindowMessageInputChannel.this.containerAdapter);
                    }
                }
                setAvailable(Boolean.TRUE);
            }
        };

        WindowUtility.addWindowObserver(this.windowObserver);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected synchronized void close() {
        if(this.window != null) {
            for(final Component component : WindowUtility.getAllComponents(this.window)) {
                component.removeMouseListener(this.clickListener);
                if(component instanceof Container) {
                    ((Container)component).removeContainerListener(this.containerAdapter);
                }
            }
            this.window = null;
        }
        if(this.windowObserver != null) {
            WindowUtility.removeWindowObserver(this.windowObserver);
            this.windowObserver = null;
        }
    }

    /**
     * <code>main</code> for tests.
     * 
     * @param args
     *            program arguments, ignored
     */
    public static void main(final String[] args) {
        final JFrame f1 = new JFrame("Frame 1");
        f1.setName("f1");
        f1.setSize(300, 300);
        f1.setLocation(300, 300);
        f1.setVisible(true);

        final JFrame f2 = new JFrame("Frame 2");
        f2.setName("f2");
        f2.setSize(300, 300);
        f2.setVisible(true);

        final SwingWindowMessageInputChannel channel = new SwingWindowMessageInputChannel();
        final Map<String, String> configuration = new HashMap<String, String>();
        configuration.put(SwingChannelConfigurationConstants.PROPERTY_WINDOW_NAME, f2.getName());
        channel.startHook(configuration);
    }
}
