/**
 * File     SwingPointingInputChannel.java
 * Package  org.sercho.masp.models.Context.swing
 * Project  ContextModel
 * Date     16.10.2007
 * Web      http://www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.models.Context.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseEvent;
import java.util.Map;

import org.sercho.masp.models.Context.ConfigurationPropertyUtility;
import org.sercho.masp.models.Context.swing.WindowUtility.WindowObserver;
import org.sercho.masp.models.channel.api.AbstractPointingInputChannelAPI;

/**
 * <code>SwingPointingInputChannel</code> captures pointing events from a Swing
 * {@link Window}. This channel registers itself for events in one of the
 * available {@link Window}s, retrieved using {@link Dialog#getWindows()}. The
 * channel registers itself for the events of the window with the name matching
 * the {@value SwingChannelConfigurationConstants#PROPERTY_WINDOW_NAME}
 * configuration property.
 * 
 * @author Grzegorz Lehmann
 */
public final class SwingPointingInputChannel extends AbstractPointingInputChannelAPI {

    final FlexiblePanel.ExtendedMouseAdapter pointingAdapter = new FlexiblePanel.ExtendedMouseAdapter() {

        /**
         * {@inheritDoc}
         */

        @Override
        public void mouseDragged(final MouseEvent event) {
            if(event.getComponent() == SwingPointingInputChannel.this.window) {
                newPointing(event.getX(), event.getY());
            } else {
                newPointing(event.getX() + event.getComponent().getX(), event.getY() + event.getComponent().getY());
            }
        }

        /**
         * {@inheritDoc}
         */

        @Override
        public void mouseMoved(final MouseEvent event) {
            if(event.getComponent() == SwingPointingInputChannel.this.window) {
                newPointing(event.getX(), event.getY());
            } else {
                newPointing(event.getX() + event.getComponent().getX(), event.getY() + event.getComponent().getY());
            }
        }

        /**
         * {@inheritDoc}
         */

        @Override
        public void mouseEntered(final MouseEvent event) {
            if(event.getComponent() == SwingPointingInputChannel.this.window) {
                newPointing(event.getX(), event.getY());
            } else {
                newPointing(event.getX() + event.getComponent().getX(), event.getY() + event.getComponent().getY());
            }
        }

        /**
         * {@inheritDoc}
         */

        @Override
        public void mouseExited(final MouseEvent event) {
            newPointing(-1, -1);
        }
    };

    final transient ComponentAdapter windowSizeAndPositionAdapter = new ComponentAdapter() {

        /**
         * {@inheritDoc}
         */

        @Override
        public void componentResized(final ComponentEvent e) {
            newSize(SwingPointingInputChannel.this.window.getWidth(), SwingPointingInputChannel.this.window.getHeight());
        }

        /**
         * {@inheritDoc}
         */

        @Override
        public void componentMoved(final ComponentEvent e) {
            final Point newPosition = SwingPointingInputChannel.this.window.getLocationOnScreen();
            newPosition(newPosition.x, newPosition.y);
        }
    };

    final transient ContainerAdapter windowChildrenAdapter = new ContainerAdapter() {

        /**
         * {@inheritDoc}
         */

        @Override
        public void componentAdded(final ContainerEvent e) {
            final Component component = e.getChild();
            if(component instanceof Container) {
                for(final Component child : WindowUtility.getAllComponents((Container)component)) {
                    child.addMouseListener(SwingPointingInputChannel.this.pointingAdapter);
                    child.addMouseMotionListener(SwingPointingInputChannel.this.pointingAdapter);
                    if(child instanceof Container) {
                        ((Container)child).addContainerListener(SwingPointingInputChannel.this.windowChildrenAdapter);
                    }
                }
            } else {
                component.addMouseListener(SwingPointingInputChannel.this.pointingAdapter);
                component.addMouseMotionListener(SwingPointingInputChannel.this.pointingAdapter);
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
                    child.removeMouseListener(SwingPointingInputChannel.this.pointingAdapter);
                    child.removeMouseMotionListener(SwingPointingInputChannel.this.pointingAdapter);
                    if(child instanceof Container) {
                        ((Container)child).removeContainerListener(SwingPointingInputChannel.this.windowChildrenAdapter);
                    }
                }
            } else {
                component.removeMouseListener(SwingPointingInputChannel.this.pointingAdapter);
                component.removeMouseMotionListener(SwingPointingInputChannel.this.pointingAdapter);
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
                SwingPointingInputChannel.this.window = w;

                // register listeners
                w.addComponentListener(SwingPointingInputChannel.this.windowSizeAndPositionAdapter);
                for(final Component component : WindowUtility.getAllComponents(w)) {
                    component.addMouseListener(SwingPointingInputChannel.this.pointingAdapter);
                    component.addMouseMotionListener(SwingPointingInputChannel.this.pointingAdapter);
                    if(component instanceof Container) {
                        ((Container)component).addContainerListener(SwingPointingInputChannel.this.windowChildrenAdapter);
                    }
                }

                newSize(w.getWidth(), w.getHeight());
                newPosition(w.getX(), w.getY());
                setAvailable(Boolean.TRUE);
            }
        };
        WindowUtility.addWindowObserver(this.windowObserver);
    }

    /**
     * {@inheritDoc}
     */

    @Override
    protected int getHeight() {
        return this.window == null ? 0 : this.window.getHeight();
    }

    /**
     * {@inheritDoc}
     */

    @Override
    protected int getPositionX() {
        return this.window == null ? 0 : this.window.getLocationOnScreen().x;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    protected int getPositionY() {
        return this.window == null ? 0 : this.window.getLocationOnScreen().y;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    protected int getWidth() {
        return this.window == null ? 0 : this.window.getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void close() {
        if(this.window != null) {
            // remove listeners
            this.window.removeComponentListener(this.windowSizeAndPositionAdapter);
            for(final Component component : WindowUtility.getAllComponents(this.window)) {
                component.removeMouseListener(this.pointingAdapter);
                component.removeMouseMotionListener(this.pointingAdapter);

                if(component instanceof Container) {
                    ((Container)component).removeContainerListener(SwingPointingInputChannel.this.windowChildrenAdapter);
                }
            }
            this.window = null;
        }
        if(this.windowObserver != null) {
            WindowUtility.removeWindowObserver(this.windowObserver);
            this.windowObserver = null;
        }
    }
}