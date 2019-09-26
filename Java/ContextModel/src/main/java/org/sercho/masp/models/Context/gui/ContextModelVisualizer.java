/**
 * 
 */
package org.sercho.masp.models.Context.gui;

import java.awt.AWTEvent;
import java.awt.Adjustable;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.SingletonAdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sercho.masp.models.Disposeable;
import org.sercho.masp.models.ListAdapterUtility;
import org.sercho.masp.models.ListAdapterUtility.ListObserver;
import org.sercho.masp.models.Context.Area;
import org.sercho.masp.models.Context.Assistant;
import org.sercho.masp.models.Context.Channel;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.ContextProvider;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.Display;
import org.sercho.masp.models.Context.Door;
import org.sercho.masp.models.Context.ElementWithPosition;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.EnvironmentElement;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.Keyboard;
import org.sercho.masp.models.Context.LocalisationProviderProxy;
import org.sercho.masp.models.Context.LocalizationTag;
import org.sercho.masp.models.Context.Mouse;
import org.sercho.masp.models.Context.Outdoors;
import org.sercho.masp.models.Context.PhysicalDevice;
import org.sercho.masp.models.Context.Place;
import org.sercho.masp.models.Context.Room;
import org.sercho.masp.models.Context.User;
import org.sercho.masp.models.Context.Vector;
import org.sercho.masp.models.Context.Window;
import org.sercho.masp.models.Context.gui.dialogs.TextMessageDialog;
import org.sercho.masp.models.Context.gui.enums.DragPointType;
import org.sercho.masp.models.Context.gui.enums.ElementType;
import org.sercho.masp.models.Context.gui.enums.ServiceType;
import org.sercho.masp.models.Context.gui.exceptions.VisualizerException;
import org.sercho.masp.models.Context.gui.pathRecording.ElementPath;
import org.sercho.masp.models.Context.gui.pathRecording.Path;
import org.sercho.masp.models.Context.util.AppliancesUtility;
import org.sercho.masp.models.Context.util.EnvironmentUtility;

import de.dailab.masp.models.Properties.BooleanProperty;
import de.dailab.masp.models.Properties.PropertiesPackage;
import de.dailab.masp.models.Properties.Property;
import de.dailab.masp.models.Properties.ServiceContainer;

/**
 * <code>ContextModelVisualizer</code>
 * 
 * This class and its sub-classes renders an environment and its contend. Also
 * it is possible to modify the elements at the environment.<br>
 * 
 * @author Grzegorz Lehmann
 * @author Antonio Fernandez Zaragoza
 * @author Andre Schulz
 * @since TODO
 */
public final class ContextModelVisualizer extends JPanel implements Disposeable,
        AbstractContextModelVisualizer, MouseListener, PopupInvoker, ComponentListener {

    /**
     * A {@link JPanel} for dangling elements. ({@link EnvironmentElement}s
     * which are not contained at any {@link Place}.)
     * 
     * @author Andre Schulz
     * @since 1.3.8
     */
    private class DanglingElementsPanel extends JPanel implements ChangeListener,
            MouseWheelListener, MouseListener {

        /**
         * 
         */
        private static final long serialVersionUID = -6346671786376723001L;

        private final LinkedList<EnvironmentElement> danglingElements;

        private final JScrollBar scrollBar;

        public DanglingElementsPanel() {
            super();
            super.setBackground(BACKGROUND_COLOR);

            super.setPreferredSize(DANGLING_ELEMENTS_PANEL_DEFAULT_SIZE);

            final TitledBorder titledBorder = BorderFactory.createTitledBorder(DANGLING_ELEMENTS_BORDER_TEXT);
            titledBorder.setTitleColor(Color.white);
            super.setBorder(titledBorder);

            this.danglingElements = new LinkedList<EnvironmentElement>();

            this.scrollBar = new JScrollBar(Adjustable.VERTICAL, 0, this.getDisplayableElementCount(), 0, 0);
            this.scrollBar.setVisible(false);
            final BoundedRangeModel model = this.scrollBar.getModel();
            model.addChangeListener(this);

            super.addMouseWheelListener(this);
            super.enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
            super.addMouseListener(this);

            super.setLayout(new BorderLayout());
            super.add(this.scrollBar, BorderLayout.EAST);
            super.setVisible(true);
        }

        @Override
        public void doLayout() {
            if(this.danglingElements.size() > this.getDisplayableElementCount()) {
                // if(LOG.isDebugEnabled()) {
                // LOG.debug("reset maximum " + this.danglingElements.size());
                // }
                this.scrollBar.setMaximum(this.danglingElements.size() - this.getDisplayableElementCount());
            } else {
                this.scrollBar.setMaximum(0);
            }

            this.scrollBar.setVisible(this.scrollBar.getMaximum() > 0);

            super.setPreferredSize(new Dimension((int)Math.max(DANGLING_ELEMENTS_PANEL_MINIMUM_SIZE, ContextModelVisualizer.this.getWidth() / 10), DANGLING_ELEMENTS_PANEL_MINIMUM_SIZE));

            super.doLayout();
        }// doLayout

        /**
         * TODO relative to?
         * 
         * @param index
         * @return The position of a dangling element identified by its index.
         */
        public Point getDanglingElementPosition(final int index) {
            final Point point = new Point(0, 0);
            final int size = (int)(DANGLING_ELEMENTS_PANEL_DEFAULT_SIZE.getWidth());

            point.translate(0, size / 4);
            point.translate(super.getWidth() / 2, (int)(size / 2 * ContextModelVisualizer.this.scale));

            point.translate(0, (int)(index * size * ContextModelVisualizer.this.scale));

            return point;
        }

        public String getDanglingString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("dangling: ");
            sb.append(getIds(this.danglingElements));
            return sb.toString();
        }

        /**
         * Returns the amount of dangling elements.
         * 
         * @return The amount of dangling elements.
         */
        public int getElementCount() {
            return this.danglingElements.size();
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseClicked(final MouseEvent e) {
            ContextModelVisualizer.this.mouseClicked(e);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseEntered(final MouseEvent e) {
            ContextModelVisualizer.this.mouseEntered(e);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseExited(final MouseEvent e) {
            ContextModelVisualizer.this.mouseExited(e);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
         */
        @Override
        public void mousePressed(final MouseEvent e) {
            ContextModelVisualizer.this.mousePressed(e);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseReleased(final MouseEvent e) {
            ContextModelVisualizer.this.mouseReleased(e);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.
         * MouseWheelEvent)
         */
        @Override
        public void mouseWheelMoved(final MouseWheelEvent e) {
            if(this.scrollBar.getMaximum() > 0) {
                this.scrollBar.setValue(this.scrollBar.getValue() + e.getWheelRotation());
            }
        }

        @Override
        public void paint(final Graphics g) {
            super.paint(g);
            int index = -1;

            // paint all dangling elements
            for(final EnvironmentElement environmentElement : this.danglingElements) {
                index++;

                if(index < this.scrollBar.getValue()) {
                    continue;
                } else if(index + 1 > this.scrollBar.getValue() + this.getDisplayableElementCount()) {
                    break;
                }

                final EnvironmentElementRenderer<?> renderer = getRenderer(environmentElement);

                if(renderer != null) {
                    Point point;
                    // final Point location = super.getLocation();

                    // render element only if it is not dragged
                    if(ContextModelVisualizer.this.draggedElement != environmentElement) {
                        point = this.getDanglingElementPosition(index - this.scrollBar.getValue());
                        renderer.paintHook((Graphics2D)g, point);

                        final int size = (int)(DANGLING_ELEMENTS_SIZE / 4 * ContextModelVisualizer.this.scale);

                        // create selection area to make element dragable
                        ContextModelVisualizer.this.selectionAreas.createArea(environmentElement,//
                                point.x + super.getLocation().x - size,//
                                point.y + super.getLocation().y - size,//
                                2 * size,//
                                2 * size);
                    }
                }
            }// for environmentElement
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.
         * ChangeEvent)
         */
        @Override
        public void stateChanged(final ChangeEvent e) {
            super.repaint();
        }

        /**
         * Returns the amount of the displayable elements.
         * 
         * @return The amount of the displayable elements.
         */
        private int getDisplayableElementCount() {
            return Math.max(0, (int)Math.floor(((super.getHeight() - +DANGLING_ELEMENTS_PANEL_DEFAULT_SIZE.getWidth() / 4) / (DANGLING_ELEMENTS_PANEL_DEFAULT_SIZE.getWidth() * ContextModelVisualizer.this.scale))));
        }

        /**
         * Returns whether an {@link EnvironmentElement} is dangling or not.
         * 
         * @param element
         *            The {@link EnvironmentElement} to check.
         * @return <code>true</code> it the {@link EnvironmentElement} is
         *         dangling, <code>false</code> otherwise.
         */
        private boolean isDangling(final EnvironmentElement element) {
            return this.danglingElements.contains(element);
        }

        /**
         * Returns whether an {@link ElementWithPosition} is visible or not.
         * 
         * @param element
         *            The {@link ElementWithPosition} to check.
         * @return <code>true</code> if the element is visible,
         *         <code>false</code> otherwise.
         */
        private boolean isVisible(final ElementWithPosition element) {
            if(element.getPlace() == null || element.getPosition() == null) {
                return false;
            }

            return true;
        }

        /**
         * Updates the list dangling elements of the {@link Environment}.
         */
        private void updateDanglingElements() {
            this.danglingElements.clear();

            // add dangling users
            for(final User user : ContextModelVisualizer.this.environment.getUsers()) {
                if(!isVisible(user)) {
                    this.danglingElements.add(user);
                }
            }

            // add dangling devices
            for(final Device device : ContextModelVisualizer.this.environment.getDevices()) {
                if(!isVisible(device)) {
                    if(getRenderer(device) != null) {
                        // add device only if it has a renderer
                        this.danglingElements.add(device);
                    }
                }

                for(final InteractionResource interactionResource : device.getResources()) {
                    if(!isVisible(interactionResource)) {
                        if(getRenderer(interactionResource) != null) {
                            this.danglingElements.add(interactionResource);
                        }
                    }
                }
            }

            // add dangling doors and windows
            for(final Place place : ContextModelVisualizer.this.environment.getPlaces()) {
                for(final Door door : place.getDoors()) {
                    if(!isVisible(door)) {
                        this.danglingElements.add(door);
                    }
                }

                for(final Window window : place.getWindows()) {
                    if(!isVisible(window)) {
                        this.danglingElements.add(window);
                    }
                }
            }

            // add dangling localization tags
            for(final ContextProvider contextProvider : ContextModelVisualizer.this.environment.getProviders()) {
                if(contextProvider instanceof LocalisationProviderProxy) {
                    for(final LocalizationTag localizationTag : ((LocalisationProviderProxy)contextProvider).getTags()) {
                        if(!isVisible(localizationTag)) {
                            this.danglingElements.add(localizationTag);
                        }
                    }
                }
            }

            if(this.danglingElements.size() > this.getDisplayableElementCount()) {
                this.scrollBar.setMaximum(this.danglingElements.size() - this.getDisplayableElementCount());
            } else {
                this.scrollBar.setMaximum(0);
            }

            this.scrollBar.setVisible(this.scrollBar.getMaximum() > 0);

            if(LOG.isDebugEnabled()) {
                LOG.debug("displayable: " + this.getDisplayableElementCount() + " scrollbar.minimum:" + this.scrollBar.getMinimum() + " scrollbar.maximum: " + this.scrollBar.getMaximum());
            }

            initDanglingPanel();
        }

        @Override
        protected void processMouseMotionEvent(final MouseEvent e) {
            ContextModelVisualizer.this.processMouseMotionEvent(e);
        }
    }// DanglingElementsPanel

    private final class DevicesObserver implements ListObserver<Device> {

        DevicesObserver() {
            // increased visibility
        }

        @Override
        public void added(final Device element) {
            ListAdapterUtility.observe(element, ContextPackage.Literals.DEVICE__RESOURCES, InteractionResource.class, ContextModelVisualizer.this.irsObserver);
        }

        @Override
        public void disposed() {
            // empty
        }

        @Override
        public void removed(final Device element) {
            // nothing to do here?
        }
    }

    private final class DoorAdapter extends VectorAdapter<Door> implements
            ListObserver<Door> {

        DoorAdapter(final EnvironmentElementRenderer<Door> doorRenderer) {
            super(doorRenderer);
            doorRenderer.element.eAdapters().add(this);
            final Vector position = doorRenderer.element.getPosition();

            if(position != null) {
                position.eAdapters().add(this);
            }

            final Vector span = doorRenderer.element.getSpan();

            if(span != null) {
                span.eAdapters().add(this);
            }

            update();
        }

        @Override
        public void added(final Door element) {
            element.eAdapters().add(this);
            newVectorSet(null, element.getPosition());
            newVectorSet(null, element.getSpan());
        }

        @Override
        public void disposed() {
            // empty
        }

        @Override
        public void removed(final Door element) {
            element.eAdapters().remove(this);
        }

        @Override
        void notifyChangedHook(final Notification notification) {
            // TODO why area?
            if((notification.getFeature() == ContextPackage.Literals.AREA__ORIGIN) || (notification.getFeature() == ContextPackage.Literals.AREA__SPAN)) {
                newVectorSet((Vector)notification.getOldValue(), (Vector)notification.getNewValue());
            }
        }
    }// class DoorAdapter

    /**
     * This class observes a <code>Door</code>.
     * 
     * @author Andre Schulz
     */
    private final class DoorObserver implements ListObserver<Door> {

        @Override
        public void added(final Door door) {
            final DoorAdapter adapter = new DoorAdapter(new DoorRenderer(door));
            // final Map<Door, VectorAdapter<Door>> newDoors = new HashMap<Door,
            // VectorAdapter<Door>>(ContextModelVisualizer.this.doors);
            // newDoors.put(door, adapter);
            // ContextModelVisualizer.this.doors = newDoors;
            ContextModelVisualizer.this.doors.put(door, adapter);

            if(!scale()) {
                // repaint anyway
                repaint();
            }
        }

        @Override
        public void disposed() {
            // empty
        }

        @Override
        public void removed(final Door door) {
            if(LOG.isDebugEnabled()) {
                LOG.debug("removed Door " + door);
            }

            final Map<Door, VectorAdapter<Door>> newDoors = new HashMap<Door, VectorAdapter<Door>>(ContextModelVisualizer.this.doors);
            final VectorAdapter<?> adapter = newDoors.remove(door);

            if(adapter != null) {
                ContextModelVisualizer.this.doors = newDoors;
                adapter.dispose();
                repaint();
            }
        }
    }// class DoorObserver

    /**
     * This class renders a door.
     * 
     * @author Andre Schulz
     * 
     */
    private class DoorRenderer extends EnvironmentElementRenderer<Door> {

        /**
         * The thickness of the line which represents the door. (when scale is
         * 1)
         */
        private static final int LINE_THICKNESS = 10;

        /**
         * A vector pointing from the center to one of both opposite edge.
         */
        private final Point span;

        // /**
        // * The both rendering end points of the door.
        // */
        // private final Point[] points;

        private DoorRenderer(final Door door) {
            super(Color.BLACK, door);
            // this.points = new Point[]{new Point(), new Point()};
            this.span = new Point();
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.sercho.masp.models.Context.gui.ContextModelVisualizer.
         * EnvironmentElementRenderer#paintHook(java.awt.Graphics2D,
         * java.awt.Point)
         */
        @Override
        public void paintHook(final Graphics2D g, Point point) {
            if(point == null) {
                point = super.point;
            }

            final Color c = g.getColor();
            final Stroke stroke = g.getStroke();

            final BooleanProperty open = super.element.getOpen();
            if((open != null) && (open.getValue() != null)) {
                if(open.getValue().booleanValue()) {
                    g.setColor(DOOR_OPEN_COLOR);
                } else {
                    g.setColor(DOOR_CLOSED_COLOR);
                }
            } else {
                g.setColor(DOOR_UNKNOWN_COLOR);
            }

            g.setStroke(new BasicStroke((float)(LINE_THICKNESS * ContextModelVisualizer.this.scale)));
            // g.drawLine(point.x, point.y, point.x + this.points[1].x -
            // this.points[0].x, point.y - (this.points[1].y -
            // this.points[0].y));
            g.drawLine(point.x, point.y, point.x + this.span.x, point.y - this.span.y);

            g.setStroke(stroke);
            g.setColor(c);
        }

        @Override
        protected void drawID(final Graphics2D g, final String id, final String text) {

            // no id is drawn, but there will be a pop up...
            // update active area for the pop up
            final int lineWidthHalf = (int)Math.ceil((LINE_THICKNESS * ContextModelVisualizer.this.scale) / 2);

            // calculate points of surrounding rectangle
            // and ensure that span is positive
            final int x1 = Math.min(this.point.x, this.point.x + this.span.x) - lineWidthHalf;
            final int y1 = Math.min(this.point.y, this.point.y - this.span.y) - lineWidthHalf;
            final int x2 = Math.max(this.point.x, this.point.x + this.span.x) + lineWidthHalf;
            final int y2 = Math.max(this.point.y, this.point.y - this.span.y) + lineWidthHalf;

            ContextModelVisualizer.this.createArea(super.element, x1, y1, x2 - x1, y2 - y1);
        }

        @Override
        protected Color getDragAndDropOvalColor() {
            return Color.RED;
        }

        // @Override
        // void paintHook(final Graphics2D g) {
        //
        // // System.out.println("DoorRenderer.paintHook " + this.points[0] +
        // // " " + this.points[1]);
        //
        // final Color c = g.getColor();
        // final Stroke stroke = g.getStroke();
        //
        // final BooleanProperty open = super.element.getOpen();
        // if((open != null) && (open.getValue() != null)) {
        // if(open.getValue().booleanValue()) {
        // g.setColor(DOOR_OPEN_COLOR);
        // } else {
        // g.setColor(DOOR_CLOSED_COLOR);
        // }
        // } else {
        // g.setColor(DOOR_UNKNOWN_COLOR);
        // }
        //
        // g.setStroke(new BasicStroke((float)(LINE_THICKNESS *
        // ContextModelVisualizer.this.scale)));
        // g.drawLine(this.points[0].x, this.points[0].y, this.points[1].x,
        // this.points[1].y);
        //
        // g.setStroke(stroke);
        // g.setColor(c);
        // }// paintHook

        /**
         * {@inheritDoc}
         */
        @Override
        synchronized void updateCoordinates() {
            super.updateCoordinates();

            final Vector position = super.element.getPosition();
            final Vector span = super.element.getSpan();

            if((position != null) && (span != null)) {
                super.point.setLocation((int)((position.getX() + ContextModelVisualizer.this.xOffset) * ContextModelVisualizer.this.scale), ContextModelVisualizer.this.getCanvasHeight() - ((int)((position.getY() + ContextModelVisualizer.this.yOffset) * ContextModelVisualizer.this.scale)));
                this.span.setLocation(span.getX() * ContextModelVisualizer.this.scale, span.getY() * ContextModelVisualizer.this.scale);
                // this.points[1].x = (int)((position.getX() + span.getX() +
                // ContextModelVisualizer.this.xOffset) *
                // ContextModelVisualizer.this.scale);
                // this.points[1].y =
                // ContextModelVisualizer.this.getCanvasHeight() -
                // ((int)((position.getY() + span.getY() +
                // ContextModelVisualizer.this.yOffset) *
                // ContextModelVisualizer.this.scale));
            }

            // check if first point is upper left one...
            // if((this.points[0].x > this.points[1].x) || (this.points[0].y >
            // this.points[1].y)) {
            // // if not: switch both points
            // int temp;
            //
            // temp = this.points[0].x;
            // this.points[0].x = this.points[1].x;
            // this.points[1].x = temp;
            // temp = this.points[0].y;
            // this.points[0].y = this.points[1].y;
            // this.points[1].y = temp;
            // }

            if(this.span.x < 0 || this.span.y < 0) {
                this.span.setLocation(-this.span.x, -this.span.y);
            }

            // super.point.setLocation((this.points[0].x + this.points[1].x) /
            // 2, (this.points[0].y + this.points[1].y) / 2);

        }
    }// class DoorRenderer

    /**
     * Compares two elements of the {@link Environment}. It is used to sort them
     * by relevance when one should be selected by the mouse.
     * 
     * @author Andre Schulz
     * @since 1.2.47
     */
    private class ElementComparator implements Comparator<Object> {

        @Override
        public int compare(final Object id1, final Object id2) {
            int result = 0;

            // +++++ low preference on Door and Window +++++
            if(id1 instanceof Door || id1 instanceof Window) {
                result += 1;
            }

            if(id2 instanceof Door || id2 instanceof Window) {
                result -= 1;
            }
            // ----- low preference on Door and Window -----

            // +++++ lowest preference on Area and Place +++++
            if(id1 instanceof Place || id1 instanceof Area) {
                result += 2;
            }

            if(id2 instanceof Place || id2 instanceof Area) {
                result -= 2;
            }
            // ----- lowest preference on Area and Place -----

            // +++++ highest preference on Users +++++
            if(id1 instanceof User) {
                return result -= 3;
            }

            if(id2 instanceof User) {
                return result += 3;
            }
            // ----- highest preference on Users -----
            return result;
        }
    }

    private abstract class EnvironmentElementRenderer<E extends EnvironmentElement>
            extends SingletonAdapterImpl {

        private static final float FONT_SIZE = 16;

        private static final int ID_HEIGHT = 11;

        protected static final int IRR_OVAL_X_ORIENTATION = 40;

        protected static final int IRR_OVAL_Y_ORIENTATION = 40;

        protected static final int OVAL_SIZE = 15;

        protected static final int IRR_X_ORIENTATION = IRR_OVAL_X_ORIENTATION + OVAL_SIZE + 5;

        protected static final int IRR_Y_ORIENTATION = IRR_OVAL_Y_ORIENTATION + OVAL_SIZE;

        protected static final int USER_OVAL_X_ORIENTATION = 40;

        protected static final int USER_OVAL_Y_ORIENTATION = -40;

        protected static final int USER_X_ORIENTATION = USER_OVAL_X_ORIENTATION + OVAL_SIZE + 5;

        protected static final int USER_Y_ORIENTATION = USER_OVAL_Y_ORIENTATION + OVAL_SIZE;

        private float fontSize = FONT_SIZE;

        private final Color foreground;

        private int idHeight = ID_HEIGHT;

        private int variationX = 0;

        private int variationY = 0;

        // protected static final int USER_LINE_X_ORIENTATION =
        // USER_OVAL_X_ORIENTATION - OVAL_SIZE / 3;
        //
        // protected static final int USER_LINE_Y_ORIENTATION =
        // USER_OVAL_Y_ORIENTATION + OVAL_SIZE / 2;
        //
        // protected static final int IRR_LINE_X_ORIENTATION =
        // IRR_OVAL_X_ORIENTATION - OVAL_SIZE / 3;
        //
        // protected static final int IRR_LINE_Y_ORIENTATION =
        // IRR_OVAL_Y_ORIENTATION + OVAL_SIZE / 2;

        protected int ovalSize = OVAL_SIZE;

        protected int ovalXOrientation;

        protected int ovalYOrientation;

        /**
         * The location where to render the element. (In screen coordinates.)
         */
        protected Point point;

        protected int strategy = 0;

        protected int xOrientation;

        // protected int lineYOrientation;

        protected int yOrientation;

        final E element;

        // protected int lineXOrientation;

        boolean paint = false;

        int x;

        int y;

        /**
         * <code>EnvironmentElementRenderer</code> constructor.
         * 
         * @param foregroundColor
         *            foreground color
         * @param environmentElement
         *            environment element to render
         */
        EnvironmentElementRenderer(final Color foregroundColor, final E environmentElement) {
            if(foregroundColor == null) {
                throw new IllegalArgumentException("foregroundColor is null");
            }

            if(environmentElement == null) {
                throw new IllegalArgumentException("environmentElement is null");
            }

            this.foreground = foregroundColor;
            this.element = environmentElement;
            this.point = new Point();
        }

        public E getElement() {
            return this.element;
        }

        /**
         * Paints the element at a specific location. This is used for the
         * {@link DanglingElementsPanel}. Overwrite this method if necessary.
         * 
         * @param graphics
         *            The {@link Graphics2D} to draw on.
         * @param point
         *            The location where to draw.
         */
        public abstract void paintHook(final Graphics2D graphics, final Point point);

        /**
         * Calculates a point for a id.
         * 
         * @param point
         *            the point which x and y coordinate is calculated and set.
         */
        private void calculateIdPoint(final Point point) {
            final int xPoint = (this.x - this.variationX) + this.xOrientation;
            final int yPoint = this.y + this.variationY + this.yOrientation + this.idHeight;
            point.setLocation(xPoint, yPoint);
        }

        /**
         * Checks if a part of the id area will be no visible and correct it in
         * order to avoid this problem.
         * 
         * @param point
         *            the reference point for the id's area.
         * @param width
         *            the width for the area
         * @param height
         *            the height for the area
         */
        private void checkIdArea(final Point point, final int width, final int height) {
            int xPoint = (int)point.getX();
            int yPoint = (int)point.getY();
            if((xPoint + width) > getWidth()) {
                this.variationX = this.variationX - (getWidth() - (xPoint + width));
                xPoint = (this.x - this.variationX) + this.xOrientation;
            }
            if((yPoint) > getHeight()) {
                this.variationY = this.variationY - (-getHeight() + (yPoint));
                yPoint = this.y + this.variationY + this.yOrientation + this.idHeight;
            }
            point.setLocation(xPoint, yPoint);
        }

        /**
         * Checks if the point is inside the visualizer area.
         * 
         * @param point
         *            the point that be checked.
         */
        private void checkIdPoint(final Point point) {
            final Rectangle rec = new Rectangle(OVAL_SIZE, OVAL_SIZE, getWidth() - OVAL_SIZE, getHeight() - OVAL_SIZE);
            if((point.x != 0) && (!rec.contains(point))) {
                this.strategy++;
            }
        }

        /**
         * Depending of the strategy, the variations for coordinates X and Y are
         * calculated.
         */
        private void nextVariation() {
            if(this.strategy == 0) {
                this.variationX = (this.variationX + 1);
                this.variationY = (this.variationY + 1);
            } else if(this.strategy == 1) {
                this.variationX = (this.variationX - 1);
                this.variationY = (this.variationY - 1);
            } else if(this.strategy == 2) {
                this.variationX = (this.variationX - 1);
                this.variationY = (this.variationY + 1);
            } else if(this.strategy == 3) {
                this.variationX = (this.variationX + 1);
                this.variationY = (this.variationY - 1);
            }

        }

        protected abstract void drawID(Graphics2D g, String id, String text);

        /**
         * Draws the identifier + class name
         * 
         * @param g
         *            the Graphics2D instance
         */
        protected void drawIdentifier(final Graphics2D g, final String text) {
            g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.setFont(g.getFont().deriveFont(this.fontSize));
            // Draw identifier
            g.drawString(text, (this.x + this.xOrientation) - this.variationX, this.y + this.yOrientation + this.variationY);

        }

        /**
         * Draws the line between the element and the oval
         * 
         * @param g
         *            the Graphics2D instance
         */
        protected void drawLine(final Graphics2D g) {

            // Draw line between element and identifier
            // int newX = this.variationX;
            // int newY = this.variationY;
            // if(this.lineYOrientation < 0) {
            // if(this.variationX > 15) {
            // newX = this.variationX - 8;
            // newY = this.variationY + 8;
            // }
            // } else {
            // if(this.variationX > 15) {
            // newX = this.variationX - 8;
            // newY = this.variationY - 8;
            // }
            // }
            g.setStroke(new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawLine(((this.x + this.ovalXOrientation) - this.variationX) + (this.ovalSize / 2), this.y + this.ovalYOrientation + this.variationY + (this.ovalSize / 2), this.x, this.y);
            g.setColor(ROOM_AREA_COLOR);

            // g.drawLine(this.x + this.ovalXOrientation - this.variationX +
            // this.ovalSize / 2, this.y + this.ovalYOrientation +
            // this.variationY + this.ovalSize / 2, this.x +
            // this.ovalXOrientation - this.variationX + this.ovalSize, this.y +
            // this.ovalYOrientation + this.variationY + this.ovalSize);
            //
            // g.drawLine(this.x + this.ovalXOrientation - this.variationX +
            // this.ovalSize / 2, this.y + this.ovalYOrientation +
            // this.variationY + this.ovalSize / 2, this.x +
            // this.ovalXOrientation - this.variationX + this.ovalSize / 2,
            // this.y + this.ovalYOrientation + this.variationY + this.ovalSize
            // / 2);
            // g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
            // BasicStroke.JOIN_ROUND));
            // g.setColor(ROOM_AREA_COLOR);
            // g.drawLine(x1, y1, x2, y2)
            // g.drawLine(this.x + this.ovalXOrientation - this.variationX +
            // this.ovalSize / 2, this.y + this.ovalYOrientation +
            // this.variationY + this.ovalSize / 2, this.x +
            // this.ovalXOrientation - this.variationX, this.y +
            // this.ovalYOrientation + this.variationY);
            // g.drawLine(this.x + this.lineXOrientation - newX, this.y +
            // this.lineYOrientation + newY, this.x, this.y);

        }

        /**
         * Draws the oval to drag and drop elements
         * 
         * @param g
         *            the Graphics2D instance
         */
        protected void drawOval(final Graphics2D g, final String id) {
            final Color c = g.getColor();
            g.setColor(getDragAndDropOvalColor());
            g.drawOval((this.x + this.ovalXOrientation) - this.variationX, this.y + this.ovalYOrientation + this.variationY, this.ovalSize, this.ovalSize);
            // this.y + this.ovalYOrientation + this.variationY + 1,
            // this.ovalSize - 1, this.ovalSize - 1);
            // g.drawOval(this.x + this.ovalXOrientation - this.variationX,
            // this.y + this.ovalYOrientation + this.variationY, 6, 6);

            ContextModelVisualizer.this.createArea(this.element, (this.x + this.ovalXOrientation) - this.variationX, this.y + this.ovalYOrientation + this.variationY, this.ovalSize, this.ovalSize);
            g.setColor(c);
        }

        protected abstract Color getDragAndDropOvalColor();

        /**
         * Paints the identifiers of the elements and the rest is delegated for
         * each element implementation. In order to avoid overlapping between
         * different element's identifiers, each identifier has a reserved area
         * where the others are not allowed to use. This area depends of:
         * Current scale, font size, space between top and bottom of the
         * identifier, an estimated distance in the left of the identifier, the
         * orientation for the identifier and finally the variation. This
         * variation is increased in one each time the identifier is not able to
         * be printed because there is overlapping with other one. In this way,
         * the area is moved until a area without overlapping is found. This
         * method will be improved with the time.
         * 
         * 
         * @param g
         *            the Graphics2D instance
         */
        void paint(final Graphics2D g) {
            try {
                // paint the rest
                paintHook(g, null);

                // paint the ID
                g.setColor(this.foreground);
                final String id = this.element.getId();
                if((!ContextModelVisualizer.this.playMode) || ((ContextModelVisualizer.this.paths.get(id) != null) && (ContextModelVisualizer.this.playMode))) {
                    if(id != null) {
                        final FontMetrics fm = g.getFontMetrics(g.getFont());

                        String text = this.element.getName();

                        if(text == null) {
                            text = this.element.getId();
                        }

                        if(text == null) {
                            return;
                        }

                        final int height = this.idHeight * 2;

                        final int width = fm.stringWidth(text) + this.ovalSize + 5;
                        final Point currentPoint = new Point();
                        while(this.paint == false) {
                            // Calculate point
                            calculateIdPoint(currentPoint);

                            // Checking if area is inside visualizer (useful for
                            // the element that you are moving manually)
                            checkIdArea(currentPoint, width, height);
                            // Checking if the point is inside visualizer
                            checkIdPoint(currentPoint);
                            // All the posibilites were used, draw with
                            // overlapping
                            // problems
                            if(this.strategy == 4) {
                                drawID(g, id, text);
                                break;
                            }
                            // Checking if the area is free
                            this.paint = ContextModelVisualizer.this.paintedAreas.containsArea(this.element, currentPoint, width, height, this.variationX, this.variationY, true);
                            // Painting if is free
                            if(this.paint) {
                                drawID(g, id, text);
                            } else {
                                nextVariation();
                            }

                        }
                    }
                    this.variationX = 0;
                    this.variationY = 0;
                    this.strategy = 0;
                    this.paint = false;
                } else {
                    final FontMetrics fm = g.getFontMetrics(g.getFont());
                    final Point point = new Point();
                    final String text = id;

                    try {
                        this.variationX = ContextModelVisualizer.this.paintedAreas.getVariation(id);
                    }
                    catch(final IllegalStateException e) {
                        // XXX what is the variation used for?
                        LOG.warn(e);
                        this.variationX = 0;
                    }

                    try {
                        this.variationY = ContextModelVisualizer.this.paintedAreas.getVariationY(id);
                    }
                    catch(final IllegalStateException e) {
                        // XXX what is the variation used for?
                        LOG.warn(e);
                        this.variationY = 0;
                    }

                    final int height = this.idHeight * 2;
                    final int width = fm.stringWidth(text) + this.ovalSize + 5;

                    calculateIdPoint(point);
                    ContextModelVisualizer.this.paintedAreas.containsArea(this.element, point, width, height, this.variationX, this.variationY, true);
                    drawID(g, id, text);
                }
            }
            catch(final Exception e) {
                ContextModelVisualizer.this.manager.exception(new VisualizerException("Error painting element's identifier! (" + e.getClass() + ")"), VisualizerManager.WARN);
                e.printStackTrace();
            }
        }

        // abstract void paintHook(Graphics2D g);

        void updateCoordinates() {
            // Update font with the current scale
            this.fontSize = (float)(FONT_SIZE * ContextModelVisualizer.this.scale);
            // Update space for identifier with the current scale
            this.idHeight = (Double.valueOf(ID_HEIGHT * ContextModelVisualizer.this.scale).intValue());
        }
    }

    private class FloorPanel extends JPanel implements MouseListener {

        /**
         * 
         */
        private static final long serialVersionUID = -4656845477318208068L;

        private final Integer floor;

        public FloorPanel(final Integer floor) {
            super();
            this.floor = floor;
            super.setBackground(BACKGROUND_COLOR);
            super.addMouseListener(this);
            super.enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
            super.setVisible(true);
            super.setMinimumSize(new Dimension(200, 200));
        }

        public Integer getFloor() {
            return this.floor;
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseClicked(final MouseEvent e) {
            ContextModelVisualizer.this.mouseClicked(e);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseEntered(final MouseEvent e) {
            ContextModelVisualizer.this.mouseEntered(e);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseExited(final MouseEvent e) {
            ContextModelVisualizer.this.mouseExited(e);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
         */
        @Override
        public void mousePressed(final MouseEvent e) {
            ContextModelVisualizer.this.mousePressed(e);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseReleased(final MouseEvent e) {
            ContextModelVisualizer.this.mouseReleased(e);
        }

        @Override
        public void paint(final Graphics g) {
            // if(LOG.isDebugEnabled()) {
            // LOG.debug("FloorPanel " + super.hashCode() + " paint on " +
            // g.hashCode(), new Exception("trace"));
            // }

            super.paint(g);
            ContextModelVisualizer.this.paintEnvironment(g, this.floor);
        }

        @Override
        public void setBounds(final Rectangle rectangle) {
            super.setBounds(rectangle);
            if(LOG.isDebugEnabled()) {
                LOG.debug("FloorPanel.setBounds " + rectangle);
            }
        }

        @Override
        protected void processMouseMotionEvent(final MouseEvent e) {
            ContextModelVisualizer.this.processMouseMotionEvent(e);
        }
    }

    private class InteractionResourceObserver implements
            ListObserver<InteractionResource> {

        @Override
        public void added(final InteractionResource element) {
            final IRAdapter adapter = new IRAdapter(new IRRenderer(element));
            // final Map<InteractionResource,
            // VectorAdapter<InteractionResource>> newIRS = new
            // HashMap<InteractionResource,
            // VectorAdapter<InteractionResource>>(ContextModelVisualizer.this.irs);
            // newIRS.put(element, adapter);
            // ContextModelVisualizer.this.irs = newIRS;
            ContextModelVisualizer.this.irs.put(element, adapter);

            if(!scale()) {
                // repaint anyway
                repaint();
            }
        }

        @Override
        public void disposed() {
            // empty
        }

        @Override
        public void removed(final InteractionResource element) {
            final Map<InteractionResource, VectorAdapter<InteractionResource>> newIRS = new HashMap<InteractionResource, VectorAdapter<InteractionResource>>(ContextModelVisualizer.this.irs);
            final VectorAdapter<?> adapter = newIRS.remove(element);
            if(adapter != null) {
                ContextModelVisualizer.this.irs = newIRS;
                adapter.dispose();
            }
        }
    }// private class InteractionResourceObserver

    private final class IRAdapter extends VectorAdapter<InteractionResource> {

        /**
         * <code>IRAdapter</code> constructor.
         * 
         * @param irRenderer
         *            renderer to call
         */
        IRAdapter(final EnvironmentElementRenderer<InteractionResource> irRenderer) {
            super(irRenderer);
            irRenderer.element.eAdapters().add(this);
            newVectorSet(null, irRenderer.element.getPosition());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void notifyChangedHook(final Notification msg) {
            if(msg.getFeature() == ContextPackage.Literals.ELEMENT_WITH_POSITION__POSITION) {
                newVectorSet((Vector)msg.getOldValue(), (Vector)msg.getNewValue());
            }
        }
    }

    private class IRRenderer extends EnvironmentElementRenderer<InteractionResource> {

        private static final int HALF_WIDTH = 12;

        private int halfWidth = HALF_WIDTH;

        /**
         * <code>IRRenderer</code> constructor.
         * 
         * @param ir
         *            interaction resource to render
         */
        IRRenderer(final InteractionResource ir) {
            super(Color.DARK_GRAY, ir);
            this.xOrientation = IRR_X_ORIENTATION;
            this.yOrientation = IRR_Y_ORIENTATION;
            this.ovalXOrientation = IRR_OVAL_X_ORIENTATION;
            // this.lineXOrientation = IRR_LINE_X_ORIENTATION;
            this.ovalYOrientation = IRR_OVAL_Y_ORIENTATION;
            // this.lineYOrientation = IRR_LINE_Y_ORIENTATION;
        }

        @Override
        public void paintHook(final Graphics2D g, Point point) {
            if(point == null) {
                point = super.point;
            }

            if(this.element instanceof Display) {
                g.setColor(DISPLAY_COLOR);
                g.drawRect(point.x - this.halfWidth, point.y - this.halfWidth, 2 * this.halfWidth, 2 * this.halfWidth);
            } else {
                g.setColor(KEYBOARD_COLOR);
                g.drawRect((point.x - this.halfWidth) + 1, (point.y - this.halfWidth) + 1, 2 * this.halfWidth, 2 * this.halfWidth);
            }
        }

        @Override
        protected void drawID(final Graphics2D g, final String id, final String text) {
            drawIdentifier(g, text);
            drawLine(g);
            drawOval(g, id);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Color getDragAndDropOvalColor() {
            return this.element.isAvailable() ? IR_OVAL_AVAILABLE_COLOR
                    : IR_OVAL_NOT_AVAILABLE_COLOR;
        }

        // /**
        // * {@inheritDoc}
        // */
        // @Override
        // void paintHook(final Graphics2D g) {
        // if(this.element instanceof Display) {
        // g.setColor(DISPLAY_COLOR);
        // g.drawRect(this.x - this.halfWidth, this.y - this.halfWidth, 2 *
        // this.halfWidth, 2 * this.halfWidth);
        // } else {
        // g.setColor(KEYBOARD_COLOR);
        // g.drawRect((this.x - this.halfWidth) + 1, (this.y - this.halfWidth) +
        // 1, 2 * this.halfWidth, 2 * this.halfWidth);
        // }
        // }

        /**
         * {@inheritDoc}
         */
        @Override
        synchronized void updateCoordinates() {
            super.updateCoordinates();
            this.x = (int)((this.element.getPosition().getX() + ContextModelVisualizer.this.xOffset) * ContextModelVisualizer.this.scale);
            this.y = getCanvasHeight() - (int)((this.element.getPosition().getY() + ContextModelVisualizer.this.yOffset) * ContextModelVisualizer.this.scale);
            super.point.setLocation(super.x, super.y);
            this.halfWidth = (int)(HALF_WIDTH * ContextModelVisualizer.this.scale);
            this.xOrientation = (Double.valueOf(IRR_X_ORIENTATION * ContextModelVisualizer.this.scale).intValue());
            this.yOrientation = (Double.valueOf(IRR_Y_ORIENTATION * ContextModelVisualizer.this.scale).intValue());
            this.ovalXOrientation = (Double.valueOf(IRR_OVAL_X_ORIENTATION * ContextModelVisualizer.this.scale).intValue());
            // this.lineXOrientation = (Double.valueOf(IRR_LINE_X_ORIENTATION *
            // ContextModelVisualizer.this.scale).intValue());
            this.ovalYOrientation = (Double.valueOf(IRR_OVAL_Y_ORIENTATION * ContextModelVisualizer.this.scale).intValue());
            // this.lineYOrientation = (Double.valueOf(IRR_LINE_Y_ORIENTATION *
            // ContextModelVisualizer.this.scale).intValue());
            this.ovalSize = (Double.valueOf(OVAL_SIZE * ContextModelVisualizer.this.scale).intValue());
        }
    }

    private final class LocalizationTagAdapter extends VectorAdapter<LocalizationTag> {

        /**
         * <code>IRAdapter</code> constructor.
         * 
         * @param lTagRenderer
         *            The {@link EnvironmentElementRenderer} to observe.
         */
        LocalizationTagAdapter(
                final EnvironmentElementRenderer<LocalizationTag> lTagRenderer) {
            super(lTagRenderer);
            lTagRenderer.element.eAdapters().add(this);
            newVectorSet(null, lTagRenderer.element.getPosition());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void notifyChangedHook(final Notification msg) {
            if(msg.getFeature() == ContextPackage.Literals.ELEMENT_WITH_POSITION__POSITION) {

                newVectorSet((Vector)msg.getOldValue(), (Vector)msg.getNewValue());
            }
        }
    }

    private class LocalizationTagObserver implements ListObserver<LocalizationTag> {

        @Override
        public void added(final LocalizationTag element) {
            final LocalizationTagAdapter adapter = new LocalizationTagAdapter(new LocalizationTagRenderer(element));
            // final Map<LocalizationTag, VectorAdapter<LocalizationTag>>
            // newTags = new HashMap<LocalizationTag,
            // VectorAdapter<LocalizationTag>>(ContextModelVisualizer.this.tags);
            // newTags.put(element, adapter);
            // ContextModelVisualizer.this.tags = newTags;
            ContextModelVisualizer.this.tags.put(element, adapter);

            if(!scale()) {
                // repaint anyway
                repaint();
            }

            ContextModelVisualizer.this.danglingElementsPanel.updateDanglingElements();
        }

        @Override
        public void disposed() {
            // empty
        }

        @Override
        public void removed(final LocalizationTag localizationTag) {
            final Map<LocalizationTag, VectorAdapter<LocalizationTag>> newTags = new HashMap<LocalizationTag, VectorAdapter<LocalizationTag>>(ContextModelVisualizer.this.tags);
            final VectorAdapter<?> adapter = newTags.remove(localizationTag);

            if(adapter != null) {
                ContextModelVisualizer.this.tags = newTags;
                adapter.dispose();
            }

            ContextModelVisualizer.this.danglingElementsPanel.updateDanglingElements();
        }
    }// class LocalizationTagObserver

    private class LocalizationTagRenderer extends
            EnvironmentElementRenderer<LocalizationTag> {

        private static final int HALF_WIDTH = 12;

        private int halfWidth = HALF_WIDTH;

        /**
         * <code>IRRenderer</code> constructor.
         * 
         * @param lTag
         *            The {@link LocalizationTag} to render.
         */
        LocalizationTagRenderer(final LocalizationTag lTag) {
            super(Color.DARK_GRAY, lTag);
            this.xOrientation = IRR_X_ORIENTATION;
            this.yOrientation = IRR_Y_ORIENTATION;
            this.ovalXOrientation = IRR_OVAL_X_ORIENTATION;
            // this.lineXOrientation = IRR_LINE_X_ORIENTATION;
            this.ovalYOrientation = IRR_OVAL_Y_ORIENTATION;
            // this.lineYOrientation = IRR_LINE_Y_ORIENTATION;
        }

        @Override
        public void paintHook(final Graphics2D g, Point point) {
            if(point == null) {
                point = super.point;
            }

            final int[] xPoints = {point.x - this.halfWidth, point.x,
                    point.x + this.halfWidth, point.x};
            final int[] yPoints = {point.y, point.y - this.halfWidth, point.y,
                    point.y + this.halfWidth};
            g.setColor(LOCALIZATION_TAG_COLOR);
            // g.drawRect(this.x - this.halfWidth, this.y - this.halfWidth, 2 *
            // this.halfWidth, 2 * this.halfWidth);

            g.drawPolygon(xPoints, yPoints, 4);
        }

        @Override
        protected void drawID(final Graphics2D g, final String id, final String text) {
            drawIdentifier(g, text);
            drawLine(g);
            drawOval(g, id);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Color getDragAndDropOvalColor() {
            if(this.element.isDetected()) {
                return LOCALIZATION_TAG_DETECTED_OVAL_COLOR;
            } else {
                return LOCALIZATION_TAG_OVAL_COLOR;
            }

        }

        // /**
        // * {@inheritDoc}
        // */
        // @Override
        // void paintHook(final Graphics2D g) {
        // final int[] xPoints = {this.x - this.halfWidth, this.x,
        // this.x + this.halfWidth, this.x};
        // final int[] yPoints = {this.y, this.y - this.halfWidth, this.y,
        // this.y + this.halfWidth};
        // g.setColor(LOCALIZATION_TAG_COLOR);
        // // g.drawRect(this.x - this.halfWidth, this.y - this.halfWidth, 2 *
        // // this.halfWidth, 2 * this.halfWidth);
        //
        // g.drawPolygon(xPoints, yPoints, 4);
        // //
        // }

        /**
         * {@inheritDoc}
         */
        @Override
        synchronized void updateCoordinates() {
            try {
                super.updateCoordinates();

                if(this.element.getPosition() != null) {
                    this.x = (int)((this.element.getPosition().getX() + ContextModelVisualizer.this.xOffset) * ContextModelVisualizer.this.scale);
                    this.y = getCanvasHeight() - (int)((this.element.getPosition().getY() + ContextModelVisualizer.this.yOffset) * ContextModelVisualizer.this.scale);
                } else {
                    this.x = 0;
                    this.y = 0;
                }

                super.point.setLocation(super.x, super.y);

                this.halfWidth = (int)(HALF_WIDTH * ContextModelVisualizer.this.scale);
                this.xOrientation = (Double.valueOf(IRR_X_ORIENTATION * ContextModelVisualizer.this.scale).intValue());
                this.yOrientation = (Double.valueOf(IRR_Y_ORIENTATION * ContextModelVisualizer.this.scale).intValue());
                this.ovalXOrientation = (Double.valueOf(IRR_OVAL_X_ORIENTATION * ContextModelVisualizer.this.scale).intValue());
                this.ovalYOrientation = (Double.valueOf(IRR_OVAL_Y_ORIENTATION * ContextModelVisualizer.this.scale).intValue());
                this.ovalSize = (Double.valueOf(OVAL_SIZE * ContextModelVisualizer.this.scale).intValue());
            }
            catch(final Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adapter for the {@link PhysicalDevice}s.
     * 
     * @author Andre Schulz
     * 
     */
    private final class PhysicalDeviceAdapter extends VectorAdapter<PhysicalDevice>
            implements ListObserver<PhysicalDevice> {

        PhysicalDeviceAdapter(
                final EnvironmentElementRenderer<PhysicalDevice> physicalDeviceRenderer) {
            super(physicalDeviceRenderer);
            physicalDeviceRenderer.element.eAdapters().add(this);
            final Vector position = physicalDeviceRenderer.element.getPosition();

            if(position != null) {
                position.eAdapters().add(this);
            }

            update();
        }

        @Override
        public void added(final PhysicalDevice element) {
            element.eAdapters().add(this);
            newVectorSet(null, element.getPosition());
        }

        @Override
        public void disposed() {
            // empty
        }

        @Override
        public void removed(final PhysicalDevice element) {
            element.eAdapters().remove(this);
        }

        @Override
        void notifyChangedHook(final Notification notification) {
            // if(LOG.isDebugEnabled()) {
            // LOG.debug("notification: " + notification);
            // LOG.debug("trace", new Exception("trace"));
            // }

            if(notification.getFeature() == ContextPackage.Literals.ELEMENT_WITH_POSITION__POSITION) {
                newVectorSet((Vector)notification.getOldValue(), (Vector)notification.getNewValue());
            }
        }

    }// class PhysicalDeviceAdapter

    /**
     * This class observes the <code>PhysicalDevices</code>.
     * 
     * @author Andre Schulz
     * 
     */
    private final class PhysicalDeviceObserver implements ListObserver<Device> {

        @Override
        public void added(final Device device) {

            if(device instanceof PhysicalDevice) {
                final PhysicalDevice physicalDevice = (PhysicalDevice)device;

                if(physicalDevice.getPosition() == null) {
                    LOG.warn("Position is null for PhysicalDevice to add: " + physicalDevice);

                    if(ContextModelVisualizer.this.environmentPointing != null && ContextModelVisualizer.this.lastClickLocation != null) {
                        final Point point = new Point();
                        try {
                            ContextModelVisualizer.this.updateEnvironmentLocation(point, ContextModelVisualizer.this.lastClickLocation);
                            physicalDevice.setPosition(point.x, point.y, getZForProjection(point.x, point.y));
                        }
                        catch(IllegalStateException e) {
                            LOG.warn("Failed to set position for PhysicalDevice " + physicalDevice, e);
                        }
                    }
                }

                final PhysicalDeviceAdapter adapter = new PhysicalDeviceAdapter(new PhysicalDeviceRenderer(physicalDevice));
                // final Map<PhysicalDevice, VectorAdapter<PhysicalDevice>>
                // newPhysicalDevices = new HashMap<PhysicalDevice,
                // VectorAdapter<PhysicalDevice>>(ContextModelVisualizer.this.physicalDevices);
                // newPhysicalDevices.put(physicalDevice, adapter);
                // ContextModelVisualizer.this.physicalDevices =
                // newPhysicalDevices;
                ContextModelVisualizer.this.physicalDevices.put(physicalDevice, adapter);

                ContextModelVisualizer.this.danglingElementsPanel.updateDanglingElements();
                ContextModelVisualizer.this.scale();
                ContextModelVisualizer.this.repaint();
            }// if
        }

        @Override
        public void disposed() {
            // empty
        }

        @Override
        public void removed(final Device device) {
            if(LOG.isDebugEnabled()) {
                LOG.debug("removed Device " + device);
                // LOG.debug("trace", new Exception("trace"));
            }

            final Map<PhysicalDevice, VectorAdapter<PhysicalDevice>> newPhysicalDevices = new HashMap<PhysicalDevice, VectorAdapter<PhysicalDevice>>(ContextModelVisualizer.this.physicalDevices);
            final VectorAdapter<?> adapter = newPhysicalDevices.remove(device);

            if(adapter != null) {
                ContextModelVisualizer.this.physicalDevices = newPhysicalDevices;
                adapter.dispose();
            }

            ContextModelVisualizer.this.danglingElementsPanel.updateDanglingElements();
            // initDanglingPanel();
            repaint();
        }

    }// class PhysicalDeviceObserver

    /**
     * This renderer renders a physical device.
     * 
     * @author Andre Schulz
     * 
     */
    private class PhysicalDeviceRenderer extends
            EnvironmentElementRenderer<PhysicalDevice> {

        private static final int HALF_IMG_SIZE = 60;

        private double halfImgSize;

        private final Image imageOff;

        private final Image imageOn;

        PhysicalDeviceRenderer(final PhysicalDevice physicalDevice) {
            super(Color.BLACK, physicalDevice);

            this.halfImgSize = HALF_IMG_SIZE;

            final EClass eClass = physicalDevice.eClass();

            // get images for the activated and deactivated mode
            this.imageOn = Images.getPhysicalDeviceImageOn(eClass != null ? eClass.getName()
                    : "default");
            this.imageOff = Images.getPhysicalDeviceImageOff(eClass != null ? eClass.getName()
                    : "default");
        }

        @Override
        public void paintHook(final Graphics2D g, Point point) {
            if(point == null) {
                point = super.point;
            }

            if((this.imageOn == null) || (this.imageOff == null)) {
                return;
            }

            // if(LOG.isDebugEnabled()) {
            // LOG.debug("paintHook " + super.getElement().getName());
            // }

            if(super.element.getOnValue()) {
                g.drawImage(this.imageOn, (int)(point.x - this.halfImgSize), (int)(point.y - this.halfImgSize), (int)(2 * this.halfImgSize), (int)(2 * this.halfImgSize), null);
            } else {
                g.drawImage(this.imageOff, (int)(point.x - this.halfImgSize), (int)(point.y - this.halfImgSize), (int)(2 * this.halfImgSize), (int)(2 * this.halfImgSize), null);
            }
        }

        @Override
        protected void drawID(final Graphics2D g, final String id, final String text) {
            // TODO fit size of area in relation to displayed image
            // (depending on contend of the image)
            ContextModelVisualizer.this.createArea(super.element, (int)(super.x - (this.halfImgSize / 2)), (int)(super.y - (this.halfImgSize / 2)), (int)(this.halfImgSize), (int)(this.halfImgSize));
        }

        @Override
        protected Color getDragAndDropOvalColor() {
            return Color.RED;
        }

        // @Override
        // void paintHook(final Graphics2D g) {
        // if((this.imageOn == null) || (this.imageOff == null)) {
        // return;
        // }
        //
        // if(super.element.getOnValue()) {
        // g.drawImage(this.imageOn, (int)(super.x - this.halfImgSize),
        // (int)(super.y - this.halfImgSize), (int)(2 * this.halfImgSize),
        // (int)(2 * this.halfImgSize), null);
        // } else {
        // g.drawImage(this.imageOff, (int)(super.x - this.halfImgSize),
        // (int)(super.y - this.halfImgSize), (int)(2 * this.halfImgSize),
        // (int)(2 * this.halfImgSize), null);
        // }
        // }// paintHook

        /**
         * {@inheritDoc}
         */
        @Override
        synchronized void updateCoordinates() {
            super.updateCoordinates();

            this.halfImgSize = HALF_IMG_SIZE * ContextModelVisualizer.this.scale;

            final Vector position = super.element.getPosition();

            if(position != null) {
                super.x = (int)((position.getX() + ContextModelVisualizer.this.xOffset) * ContextModelVisualizer.this.scale);
                super.y = getCanvasHeight() - ((int)((position.getY() + ContextModelVisualizer.this.yOffset) * ContextModelVisualizer.this.scale));

                super.xOrientation = (Double.valueOf(USER_X_ORIENTATION * ContextModelVisualizer.this.scale).intValue());
                super.yOrientation = (Double.valueOf(USER_Y_ORIENTATION * ContextModelVisualizer.this.scale).intValue());
                super.ovalXOrientation = (Double.valueOf(USER_OVAL_X_ORIENTATION * ContextModelVisualizer.this.scale).intValue());
                super.ovalYOrientation = (Double.valueOf(USER_OVAL_Y_ORIENTATION * ContextModelVisualizer.this.scale).intValue());
                super.ovalSize = (Double.valueOf(OVAL_SIZE * ContextModelVisualizer.this.scale).intValue());
            }

            super.point.setLocation(super.x, super.y);
        }// updateCoordinates
    }// class PhysicalDeviceRenderer

    private final class PlaceAdapter extends VectorAdapter<Place> implements
            ListObserver<Area> {

        // TODO why Area and not Place?

        /**
         * <code>PlaceAdapter</code> constructor.
         * 
         * @param placeRenderer
         *            renderer to call
         */
        PlaceAdapter(final EnvironmentElementRenderer<Place> placeRenderer) {
            super(placeRenderer);
            update();
            placeRenderer.getElement().eAdapters().add(this);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void added(final Area element) {
            element.eAdapters().add(this);
            newVectorSet(null, element.getOrigin());
            newVectorSet(null, element.getSpan());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void disposed() {
            // nothing to do here yet
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void removed(final Area element) {
            // element.eAdapters().remove(this);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        void notifyChangedHook(final Notification notification) {
            if(LOG.isDebugEnabled()) {
                LOG.debug("notification: " + notification);
            }

            if(notification.getEventType() == Notification.REMOVING_ADAPTER) {
                // ignore notification
                return;
            }

            if(notification.getNotifier() instanceof Place) {
                switch(notification.getEventType()) {
                    case Notification.ADD:
                        if(notification.getNewValue() instanceof Area) {
                            final Place place = (Place)notification.getNotifier();
                            ContextModelVisualizer.this.renewAreasOfPlace(place);
                            return;
                        }
                        break;
                    case Notification.SET:
                        if(notification.getFeature() == ContextPackage.Literals.ENVIRONMENT_ELEMENT__ID) {
                            ContextModelVisualizer.this.renewAreasOfPlace((Place)notification.getNotifier());
                            return;
                        } else if(notification.getFeature() == ContextPackage.Literals.PLACE__FLOOR) {
                            ContextModelVisualizer.this.initComponentsAndLayout();
                            return;
                        } else if(notification.getFeature() == ContextPackage.Literals.PLACE__ENVIRONMENT) {
                            // ignore notification
                            return;
                        }
                        break;
                    case Notification.REMOVE:
                        if(notification.getOldValue() instanceof Area) {
                            final Place place = (Place)notification.getNotifier();
                            ContextModelVisualizer.this.renewAreasOfPlace(place);

                            final VectorAdapter<Place> placeAdapter = ContextModelVisualizer.this.places.get(place);

                            if(placeAdapter != null) {
                                placeAdapter.getRenderer().updateCoordinates();
                                ContextModelVisualizer.this.repaint();
                            } else {
                                LOG.warn(new StringBuilder("failed to get place adapter for place ").append(place).append(" while removing area, cannot update coordinates"));
                            }

                            EnvironmentUtility.fixPlace(ContextModelVisualizer.this.environment, place);
                            ContextModelVisualizer.this.danglingElementsPanel.updateDanglingElements();
                            return;
                        } else if(notification.getOldValue() instanceof ElementWithPosition) {
                            ContextModelVisualizer.this.updateCurrentElements(null);
                            // nothing to do
                            return;
                        }
                        break;
                }
            } else if(notification.getOldValue() instanceof Door || notification.getOldValue() instanceof Window) {
                // nothing to do
                return;
            }

            if((notification.getFeature() == ContextPackage.Literals.AREA__ORIGIN) || (notification.getFeature() == ContextPackage.Literals.AREA__SPAN)) {
                newVectorSet((Vector)notification.getOldValue(), (Vector)notification.getNewValue());

                if(LOG.isDebugEnabled()) {
                    LOG.debug("area edited");
                }
                return;
            }

            LOG.warn("unhandled notification " + notification);
            // LOG.warn("trace", new Exception("trace"));
        }
    }// class PlaceAdapter

    private final class PlaceObserver implements ListObserver<Place> {

        Map<Place, Disposeable> doorDisposables = new HashMap<Place, Disposeable>();

        Map<Place, Disposeable> windowDisposables = new HashMap<Place, Disposeable>();

        @Override
        public void added(final Place place) {
            final PlaceAdapter adapter = create(new PlaceRenderer(place));
            ContextModelVisualizer.this.places.put(place, adapter);

            this.doorDisposables.put(place, ListAdapterUtility.observe(place, ContextPackage.Literals.PLACE__DOORS, Door.class, new DoorObserver()));
            this.windowDisposables.put(place, ListAdapterUtility.observe(place, ContextPackage.Literals.PLACE__WINDOWS, Window.class, new WindowObserver()));

            ContextModelVisualizer.this.renewAreasOfPlace(place);
            ContextModelVisualizer.this.initComponentsAndLayout();

            if(!scale()) {
                // repaint anyway
                repaint();
            }
        }

        @Override
        public void disposed() {
            // empty
        }

        @Override
        public void removed(final Place place) {
            if(LOG.isDebugEnabled()) {
                LOG.debug("removed " + place);
            }

            this.doorDisposables.remove(place).dispose();
            this.windowDisposables.remove(place).dispose();

            final Map<Place, VectorAdapter<Place>> newPlaces = new HashMap<Place, VectorAdapter<Place>>(ContextModelVisualizer.this.places);
            final VectorAdapter<Place> adapter = newPlaces.remove(place);
            if(adapter != null) {
                ContextModelVisualizer.this.doors.remove(place);
                ContextModelVisualizer.this.windows.remove(place);
                ContextModelVisualizer.this.places = newPlaces;
                adapter.dispose();

                ContextModelVisualizer.this.danglingElementsPanel.updateDanglingElements();
                ContextModelVisualizer.this.initComponentsAndLayout();
                ContextModelVisualizer.this.repaint();
            }
        }
    }// class PlaceObserver

    /**
     * This renderer renders a {@link Place}.
     */
    private class PlaceRenderer extends EnvironmentElementRenderer<Place> {

        private final Map<Area, Rectangle> rectangles = new Hashtable<Area, Rectangle>();

        private final java.awt.geom.Area wholeArea = new java.awt.geom.Area();

        /**
         * <code>PlaceRenderer</code> constructor.
         * 
         * @param place
         *            place to render
         */
        public PlaceRenderer(final Place place) {
            super(Color.BLACK, place);
            this.ovalSize = 0;

            for(final Area area : super.element.getAreas()) {
                if((area.getSpan() != null) && (area.getSpan().getX() == -1) && (area.getSpan().getY() == -1)) {
                    area.setNewSpan(1, 1, area.getSpan().getZ());
                }
            }
        }

        /**
         * Checks whether the {@link Area} is small, so that the drag points are
         * displayed out of the {@link Area}.
         * 
         * @param area
         *            The {@link Area} to check.
         * @return <code>true<code> if the area is mall, <code>false</code>
         *         otherwise.
         * @since 1.2.47
         */
        public boolean isAreaSmall(final Area area) {
            if(area.getSpan() != null) {
                if((((area.getSpan().getX() * ContextModelVisualizer.this.scale) < (2 * DRAG_POINT_OFFSET))//
                || ((area.getSpan().getY() * ContextModelVisualizer.this.scale) < (2 * DRAG_POINT_OFFSET)))) {
                    return true;
                }
            }

            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void paintHook(final Graphics2D g, final Point point) {
            synchronized(this.rectangles) {
                // configure graphics
                final Color c = g.getColor();
                final Stroke stroke = g.getStroke();

                if(super.element instanceof Room) {
                    g.setColor(ROOM_AREA_COLOR);
                } else if(super.element instanceof Outdoors) {
                    g.setColor(OUTDOORS_AREA_COLOR);
                } else {
                    g.setColor(PLACE_AREA_COLOR);
                }

                // paint area
                if(ContextModelVisualizer.this.areaEditMode) {
                    g.setStroke(new BasicStroke((float)(10 * ContextModelVisualizer.this.scale), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g.fill(this.wholeArea);
                    g.setColor(PLACE_HIGHLIGHT_COLOR);
                    g.draw(this.wholeArea);
                } else {
                    g.setStroke(new BasicStroke((float)(5 * ContextModelVisualizer.this.scale), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g.fill(this.wholeArea);
                    g.setColor(Color.white);
                    g.draw(this.wholeArea);
                }

                if(ContextModelVisualizer.this.areaEditMode) {
                    final Color color = g.getColor();
                    g.setStroke(new BasicStroke((float)(2 * ContextModelVisualizer.this.scale), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    g.setColor(AREA_DRAG_COLOR);

                    int x = 0;
                    int y = 0;
                    int maxDragpointSize = Integer.MIN_VALUE;
                    final LinkedList<Point> areaCenters = new LinkedList<Point>();

                    int i = 0;

                    for(final Area area : super.element.getAreas()) {
                        final Rectangle rectangle = this.getRectangle(area);

                        // determine size of drag-point
                        int dragpointSize = Math.max(20, Math.min(rectangle.height, rectangle.width) / 5);
                        dragpointSize = Math.min(50, dragpointSize);

                        maxDragpointSize = Math.max(maxDragpointSize, dragpointSize);

                        boolean smallAreaOverride = false;

                        if(this.isAreaSmall(area)) {
                            smallAreaOverride = this.isNextToDragPoint(area, ContextModelVisualizer.this.environmentPointing);
                        }

                        // paint only drag-point if the mouse is on the specific
                        // area
                        if(ContextModelVisualizer.this.environmentPointing == null || containsProjection(area, ContextModelVisualizer.this.environmentPointing) || smallAreaOverride) {
                            // draw all drag-points
                            g.setColor(AREA_DRAG_COLOR);
                            this.drawDragPoint(g, rectangle.x, rectangle.y, dragpointSize, DragPointType.NW, i, area);
                            this.drawDragPoint(g, rectangle.x + rectangle.width, rectangle.y, dragpointSize, DragPointType.NE, i, area);
                            this.drawDragPoint(g, rectangle.x, rectangle.y + rectangle.height, dragpointSize, DragPointType.SW, i, area);
                            this.drawDragPoint(g, rectangle.x + rectangle.width, rectangle.y + rectangle.height, dragpointSize, DragPointType.SE, i, area);
                            this.drawDragPoint(g, rectangle.x + (rectangle.width / 2), rectangle.y + (rectangle.height / 2), dragpointSize, DragPointType.CENTER, i, area);

                            g.setColor(AREA_HIGHLIGHT_COLOR);
                            g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                        }

                        areaCenters.add(new Point(rectangle.x + (rectangle.width / 2), rectangle.y + (rectangle.height / 2)));

                        x += areaCenters.getLast().x;
                        y += areaCenters.getLast().y;

                        i++;
                    }// for Area

                    // draw drag-point for whole Place if it contains some Areas
                    if(super.element.getAreas().size() > 1) {
                        g.setColor(PLACE_DRAG_COLOR);
                        this.drawDragPoint(g, x / this.element.getAreas().size(), y / this.element.getAreas().size(), maxDragpointSize, DragPointType.CENTER, -1, null);

                        for(final Point center : areaCenters) {
                            g.drawLine(x / this.element.getAreas().size(), y / this.element.getAreas().size(), center.x, center.y);
                        }
                    }// if

                    g.setColor(color);
                } else {
                    // area edit mode OFF

                    // create selection area for every area of the place
                    for(final Area area : super.element.getAreas()) {
                        final Rectangle rectangle = this.getRectangle(area);
                        ContextModelVisualizer.this.createArea(this.element, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                        ContextModelVisualizer.this.createArea(area, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                    }
                }

                g.setColor(c);
                g.setStroke(stroke);
            }
        }// paintHook

        /**
         * Returns whether an {@link Area} contains a projection.
         * 
         * @param area
         * @param projection
         * @return <code>true</code> if the {@link Area} contains the
         *         projection, <code>false</code> otherwise.
         */
        private boolean containsProjection(final Area area, final Point projection) {
            if(area.getSpan().getX() > 0) {
                if(area.getOrigin().getX() <= projection.x && projection.x < area.getOrigin().getX() + area.getSpan().getX()) {
                    // all OK
                } else {
                    return false;
                }
            } else {
                // area.span.x <= 0
                if(projection.x <= area.getOrigin().getX() && area.getOrigin().getX() + area.getSpan().getX() <= projection.x) {
                    // all OK
                } else {
                    return false;
                }
            }

            if(area.getSpan().getY() > 0) {
                if(area.getOrigin().getY() <= projection.y && projection.y < area.getOrigin().getY() + area.getSpan().getY()) {
                    // all OK
                } else {
                    return false;
                }
            } else {
                // area.span.y <= 0
                if(projection.y <= area.getOrigin().getY() && area.getOrigin().getY() + area.getSpan().getY() <= projection.y) {
                    // all OK
                } else {
                    return false;
                }
            }

            return true;
        }

        /**
         * This method draws a drag-point to move something according to the
         * position in related to the object.
         * 
         * @param g
         *            The {@link Graphics} to draw on.
         * @param x
         *            The x-place of the drag-point.
         * @param y
         *            The y-place of the drag-point;
         * @param size
         *            The size of the drag-point drawing.
         * @param dragPointType
         *            The type of the drag-point.
         * @param index
         *            The index of the object if it is an {@link Area}.
         */
        private void drawDragPoint(final Graphics g, int x, int y, final int size, final DragPointType dragPointType, final int index, final Area area) {

            // if area is small => move dragpoints ot of area
            if(area != null && this.isAreaSmall(area)) {
                switch(dragPointType) {
                    case NE:
                        x += DRAG_POINT_OFFSET;
                        y -= DRAG_POINT_OFFSET;
                        break;
                    case NW:
                        x -= DRAG_POINT_OFFSET;
                        y -= DRAG_POINT_OFFSET;
                        break;
                    case SE:
                        x += DRAG_POINT_OFFSET;
                        y += DRAG_POINT_OFFSET;
                        break;
                    case SW:
                        x -= DRAG_POINT_OFFSET;
                        y += DRAG_POINT_OFFSET;
                        break;
                }
            }

            switch(dragPointType) {
                case CENTER:
                    ContextModelVisualizer.drawArrowCross(g, x, y, size);

                    g.drawRect(x - (size / 2), y - (size / 2), size, size);

                    if(index != -1) {
                        ContextModelVisualizer.this.createArea(new DragPointWrapper(area, dragPointType), x - size, y - size, 2 * size, 2 * size);
                    } else {
                        ContextModelVisualizer.this.createArea(super.element, x - (size / 2), y - (size / 2), size, size);
                    }
                    break;
                case NE:
                    ContextModelVisualizer.drawBiArrow(g, x - (size / 4), y + (size / 4), x - ((5 * size) / 4), y + ((5 * size) / 4), DRAG_POINT_ARROW_RATIO);

                    g.drawLine(x, y, x - size, y);
                    g.drawLine(x, y, x, y + size);
                    g.drawArc(x - size, y - size, 2 * size, 2 * size, 180, 90);
                    ContextModelVisualizer.this.createArea(new DragPointWrapper(area, dragPointType), x - size, y, size, size);
                    break;
                case NW:
                    ContextModelVisualizer.drawBiArrow(g, x + (size / 4), y + (size / 4), x + ((5 * size) / 4), y + ((5 * size) / 4), DRAG_POINT_ARROW_RATIO);

                    g.drawLine(x, y, x + size, y);
                    g.drawLine(x, y, x, y + size);
                    g.drawArc(x - size, y - size, 2 * size, 2 * size, 270, 90);

                    ContextModelVisualizer.this.createArea(new DragPointWrapper(area, dragPointType), x, y, size, size);
                    break;
                case SE:
                    ContextModelVisualizer.drawBiArrow(g, x - (size / 4), y - (size / 4), x - ((5 * size) / 4), y - ((5 * size) / 4), DRAG_POINT_ARROW_RATIO);

                    g.drawLine(x, y, x - size, y);
                    g.drawLine(x, y, x, y - size);
                    g.drawArc(x - size, y - size, 2 * size, 2 * size, 90, 90);

                    ContextModelVisualizer.this.createArea(new DragPointWrapper(area, dragPointType), x - size, y - size, size, size);
                    break;
                case SW:
                    ContextModelVisualizer.drawBiArrow(g, x + (size / 4), y - (size / 4), x + ((5 * size) / 4), y - ((5 * size) / 4), DRAG_POINT_ARROW_RATIO);

                    g.drawLine(x, y, x + size, y);
                    g.drawLine(x, y, x, y - size);
                    g.drawArc(x - size, y - size, 2 * size, 2 * size, 0, 90);

                    ContextModelVisualizer.this.createArea(new DragPointWrapper(area, dragPointType), x, y - size, size, size);
                    break;
            }// switch
        }// drawDragPoint

        /**
         * Converts an {@link Area} of the {@link Environment} to a
         * {@link Rectangle} to draw on screen.
         * 
         * @param area
         *            The {@link Area} to get the {@link Rectangle} for.
         * @return A {@link Rectangle} for the given {@link Area}.
         */
        private Rectangle getRectangle(final Area area) {
            final Vector span = area.getSpan();
            final Vector origin = area.getOrigin();

            if((span == null) || (origin == null)) {
                return null;
            }

            Rectangle rectangle = this.rectangles.get(area);
            if(rectangle == null) {
                // create rectangle for new area
                rectangle = new Rectangle();
                this.rectangles.put(area, rectangle);
            }

            if(span.getX() >= 0) {
                rectangle.x = (int)((origin.getX() + ContextModelVisualizer.this.xOffset) * ContextModelVisualizer.this.scale);
            } else {
                rectangle.x = (int)((origin.getX() + span.getX() + ContextModelVisualizer.this.xOffset) * ContextModelVisualizer.this.scale);
            }

            rectangle.y = getCanvasHeight() - ((int)((origin.getY() + ContextModelVisualizer.this.yOffset) * ContextModelVisualizer.this.scale));

            if(span.getY() >= 0) {
                rectangle.y -= span.getY() * ContextModelVisualizer.this.scale;
            }

            // calculate new size
            rectangle.width = (int)Math.ceil((Math.abs(span.getX()) * ContextModelVisualizer.this.scale));
            rectangle.height = (int)Math.ceil((Math.abs(span.getY()) * ContextModelVisualizer.this.scale));

            return rectangle;
        }// getRectangle

        /**
         * Checks whether a given {@link Point} is next to an {@link Area}.
         * 
         * @param area
         *            The {@link Area}.
         * @param environmentPointing
         *            The {@link Point}.
         * @return <code>true</code> if the {@link Point} is next to the
         *         {@link Area}, <code>false</code> otherwise.
         */
        private boolean isNextToDragPoint(final Area area, final Point environmentPointing) {
            final Point areaCorner = new Point();

            for(int i = 0; i < 4; i++) {
                switch(i) {
                    case 0:
                        areaCorner.setLocation(area.getOrigin().getX(), area.getOrigin().getY());
                        break;
                    case 1:
                        areaCorner.setLocation(area.getOrigin().getX() + area.getSpan().getX(), area.getOrigin().getY());
                        break;
                    case 2:
                        areaCorner.setLocation(area.getOrigin().getX(), area.getOrigin().getY() + area.getSpan().getY());
                        break;
                    case 3:
                        areaCorner.setLocation(area.getOrigin().getX() + area.getSpan().getX(), area.getOrigin().getY() + area.getSpan().getY());
                        break;
                }// switch

                if(environmentPointing.distance(areaCorner) < 3 * DRAG_POINT_OFFSET) {
                    return true;
                }
            }// for

            return false;
        }

        @Override
        protected void drawID(final Graphics2D g, final String id, final String text) {
            drawIdentifier(g, text);
            // drawLine(g);
            // drawOval(g, id);
        }// drawID;

        /**
         * {@inheritDoc}
         */
        @Override
        protected Color getDragAndDropOvalColor() {
            return Color.BLACK;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        synchronized void updateCoordinates() {
            super.updateCoordinates();
            Rectangle rectangle;
            synchronized(this.rectangles) {
                this.wholeArea.reset();
                // remove rectangles for no longer existing areas
                this.rectangles.keySet().retainAll(super.element.getAreas());
                // recalculate rectangles for existing areas

                super.x = Integer.MAX_VALUE;
                super.y = Integer.MAX_VALUE;

                for(final Area area : super.element.getAreas()) {
                    // calculate new position

                    rectangle = this.getRectangle(area);

                    // determine position of ID (most upper area of the most
                    // left areas)
                    if(((rectangle.x + 10) < super.x) && ((rectangle.y + 20) < super.y)) {
                        super.x = (int)(rectangle.x + (15 * ContextModelVisualizer.this.scale));
                        super.y = (int)(rectangle.y + (30 * ContextModelVisualizer.this.scale));
                    }
                }// for (area)

                for(final Shape area : this.rectangles.values()) {
                    this.wholeArea.add(new java.awt.geom.Area(area));
                }
            }
        }

    }// class PlaceRenderer

    private final class ProviderObserver implements ListObserver<ContextProvider> {

        ProviderObserver() {
            // increased visibility
        }

        @Override
        public void added(final ContextProvider element) {
            ListAdapterUtility.observe(element, ContextPackage.Literals.LOCALISATION_PROVIDER_PROXY__TAGS, LocalizationTag.class, ContextModelVisualizer.this.localizationTagObserver);
        }

        @Override
        public void disposed() {
            // empty
        }

        @Override
        public void removed(final ContextProvider contextProvider) {
            // nothing to do here
        }
    }// class ProviderObserver

    private final class UserAdapter extends VectorAdapter<User> {

        /**
         * <code>UserAdapter</code> constructor.
         * 
         * @param userRenderer
         *            renderer to call
         */
        UserAdapter(final EnvironmentElementRenderer<User> userRenderer) {
            super(userRenderer);
            userRenderer.element.eAdapters().add(this);
            final Vector position = userRenderer.element.getPosition();
            if(position != null) {
                position.eAdapters().add(this);
            }
            update();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void notifyChangedHook(final Notification msg) {
            if(msg.getFeature() == ContextPackage.Literals.ELEMENT_WITH_POSITION__POSITION) {
                newVectorSet((Vector)msg.getOldValue(), (Vector)msg.getNewValue());
            }
        }
    }// class UserAdapter

    private class UserRenderer extends EnvironmentElementRenderer<User> {

        private static final int HALF_IMG_SIZE = 40;

        private static final int HALF_RADIUS = 25;

        private double halfImgSize;

        private int halfRadius = HALF_RADIUS;

        private final Image image;

        /**
         * <code>UserRenderer</code> constructor.
         * 
         * @param user
         *            user to render
         */
        UserRenderer(final User user) {
            super(Color.RED, user);
            super.point = new Point();
            this.xOrientation = USER_X_ORIENTATION;
            this.yOrientation = USER_Y_ORIENTATION;
            this.ovalXOrientation = USER_OVAL_X_ORIENTATION;
            // this.lineXOrientation = USER_LINE_X_ORIENTATION;
            this.ovalYOrientation = USER_OVAL_Y_ORIENTATION;
            // this.lineYOrientation = USER_LINE_Y_ORIENTATION;

            this.image = Images.getUserImage(user.getName());
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.sercho.masp.models.Context.gui.ContextModelVisualizer.
         * EnvironmentElementRenderer#paintHook(java.awt.Graphics2D,
         * java.awt.Point)
         */
        @Override
        public void paintHook(final Graphics2D g, Point point) {
            if(point == null) {
                point = super.point;
            }

            if(this.image != null) {
                // draw image if user has one...
                g.drawImage(this.image, (int)(point.x - this.halfImgSize), (int)(point.y - this.halfImgSize), (int)(2 * this.halfImgSize), (int)(2 * this.halfImgSize), null);

                ContextModelVisualizer.this.createArea(this.element, (int)(point.x - this.halfImgSize), (int)(point.y - this.halfImgSize), (int)(2 * this.halfImgSize), (int)(2 * this.halfImgSize));
            } else {
                // ...otherwise draw shape
                g.setColor(USER_COLOR);
                g.drawOval(point.x - this.halfRadius, point.y - this.halfRadius, 2 * this.halfRadius, 2 * this.halfRadius);
                g.setFont(g.getFont().deriveFont((float)11));
            }
        }

        @Override
        protected void drawID(final Graphics2D g, final String id, final String text) {
            if(this.image == null) {
                drawIdentifier(g, text);
                drawLine(g);
                drawOval(g, id);
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Color getDragAndDropOvalColor() {
            return USER_OVAL_COLOR;
        }

        // /**
        // * {@inheritDoc}
        // */
        // @Override
        // void paintHook(final Graphics2D g) {
        // this.paintHook(g, super.point);
        // }

        /**
         * {@inheritDoc}
         */
        @Override
        synchronized void updateCoordinates() {
            super.updateCoordinates();

            this.halfImgSize = HALF_IMG_SIZE * ContextModelVisualizer.this.scale;

            final Vector position = super.element.getPosition();

            if(position != null) {
                this.x = (int)((position.getX() + ContextModelVisualizer.this.xOffset) * ContextModelVisualizer.this.scale);
                this.y = getCanvasHeight() - ((int)((position.getY() + ContextModelVisualizer.this.yOffset) * ContextModelVisualizer.this.scale));
                super.point.setLocation(super.x, super.y);

                this.halfRadius = (int)(HALF_RADIUS * ContextModelVisualizer.this.scale);
                this.xOrientation = (Double.valueOf(USER_X_ORIENTATION * ContextModelVisualizer.this.scale).intValue());
                this.yOrientation = (Double.valueOf(USER_Y_ORIENTATION * ContextModelVisualizer.this.scale).intValue());
                this.ovalXOrientation = (Double.valueOf(USER_OVAL_X_ORIENTATION * ContextModelVisualizer.this.scale).intValue());
                // this.lineXOrientation =
                // (Double.valueOf(USER_LINE_X_ORIENTATION *
                // ContextModelVisualizer.this.scale).intValue());
                this.ovalYOrientation = (Double.valueOf(USER_OVAL_Y_ORIENTATION * ContextModelVisualizer.this.scale).intValue());
                // this.lineYOrientation =
                // (Double.valueOf(USER_LINE_Y_ORIENTATION *
                // ContextModelVisualizer.this.scale).intValue());
                this.ovalSize = (Double.valueOf(OVAL_SIZE * ContextModelVisualizer.this.scale).intValue());
            }
        }
    }// class UserRenderer

    private final class UsersObserver implements ListObserver<User> {

        @Override
        public void added(final User element) {
            final UserAdapter adapter = new UserAdapter(new UserRenderer(element));
            // final Map<User, VectorAdapter<User>> newUsers = new HashMap<User,
            // VectorAdapter<User>>(ContextModelVisualizer.this.users);
            // newUsers.put(element, adapter);
            // ContextModelVisualizer.this.users = newUsers;
            ContextModelVisualizer.this.users.put(element, adapter);

            if(!scale()) {
                // repaint anyway
                repaint();
            }
        }

        @Override
        public void disposed() {
            // empty
        }

        @Override
        public void removed(final User element) {
            final Map<User, VectorAdapter<User>> newUsers = new HashMap<User, VectorAdapter<User>>(ContextModelVisualizer.this.users);
            final VectorAdapter<?> adapter = newUsers.remove(element);
            if(adapter != null) {
                ContextModelVisualizer.this.users = newUsers;
                adapter.dispose();
                repaint();
            }
        }
    }// class UsersObserver

    private abstract class VectorAdapter<E extends EnvironmentElement> extends
            SingletonAdapterImpl {

        final EnvironmentElementRenderer<E> renderer;

        /**
         * <code>VectorAdapter</code> constructor.
         * 
         * @param renderer
         *            renderer to call
         */
        VectorAdapter(final EnvironmentElementRenderer<E> renderer) {
            if(renderer == null) {
                throw new IllegalArgumentException("userRenderer is null");
            }
            this.renderer = renderer;
        }

        public EnvironmentElementRenderer<E> getRenderer() {
            return this.renderer;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public final void notifyChanged(final Notification msg) {
            if((msg.getFeature() == ContextPackage.Literals.VECTOR__X) || (msg.getFeature() == ContextPackage.Literals.VECTOR__Y) || (msg.getFeature() == ContextPackage.Literals.VECTOR__Z)) {
                update();
            } else {
                notifyChangedHook(msg);
            }
        }

        protected final void newVectorSet(final Vector oldVector, final Vector newVector) {
            if(oldVector != null) {
                oldVector.eAdapters().remove(this);
            }
            if(newVector != null) {
                newVector.eAdapters().add(this);
                update();
            }
        }

        abstract void notifyChangedHook(Notification msg);

        final void update() {
            this.renderer.updateCoordinates();
            if(!scale()) {
                // repaint anyway
                repaint();
            }
        }
    }// class VectorAdapter

    /**
     * An {@link Adapter} for {@link Window}s.
     * 
     * @author Andre Schulz
     * @since 1.2.47
     */
    private final class WindowAdapter extends VectorAdapter<Window> implements
            ListObserver<Window> {

        WindowAdapter(final EnvironmentElementRenderer<Window> windowRenderer) {
            super(windowRenderer);
            windowRenderer.element.eAdapters().add(this);
            final Vector position = windowRenderer.element.getPosition();

            if(position != null) {
                position.eAdapters().add(this);
            }

            final Vector span = windowRenderer.element.getSpan();

            if(span != null) {
                span.eAdapters().add(this);
            }

            update();
        }

        @Override
        public void added(final Window window) {
            window.eAdapters().add(this);
            newVectorSet(null, window.getPosition());
            newVectorSet(null, window.getSpan());
        }

        @Override
        public void disposed() {
            // empty
        }

        @Override
        public void removed(final Window window) {
            window.eAdapters().remove(this);
        }

        @Override
        void notifyChangedHook(final Notification notification) {
            // TOD why area?
            if((notification.getFeature() == ContextPackage.Literals.AREA__ORIGIN) || (notification.getFeature() == ContextPackage.Literals.AREA__SPAN)) {
                newVectorSet((Vector)notification.getOldValue(), (Vector)notification.getNewValue());
            }
        }
    }// class WindowAdapter

    /**
     * This class observes a {@link Window}.
     * 
     * @author Andre Schulz
     * @since 1.2.47
     */
    private final class WindowObserver implements ListObserver<Window> {

        @Override
        public void added(final Window window) {
            final WindowAdapter adapter = new WindowAdapter(new WindowRenderer(window));
            // final Map<Door, VectorAdapter<Door>> newDoors = new HashMap<Door,
            // VectorAdapter<Door>>(ContextModelVisualizer.this.doors);
            // newDoors.put(door, adapter);
            // ContextModelVisualizer.this.doors = newDoors;
            ContextModelVisualizer.this.windows.put(window, adapter);

            if(!scale()) {
                // repaint anyway
                repaint();
            }
        }

        @Override
        public void disposed() {
            // empty
        }

        @Override
        public void removed(final Window window) {
            if(LOG.isDebugEnabled()) {
                LOG.debug("removed Window " + window);
            }

            final Map<Window, VectorAdapter<Window>> newWindows = new HashMap<Window, VectorAdapter<Window>>(ContextModelVisualizer.this.windows);
            final VectorAdapter<?> adapter = newWindows.remove(window);

            if(adapter != null) {
                ContextModelVisualizer.this.windows = newWindows;
                adapter.dispose();
                repaint();
            }
        }
    }// class DoorObserver

    /**
     * This class renders a {@link Window}.
     * 
     * @author Andre Schulz
     * @since 1.2.47
     */
    private class WindowRenderer extends EnvironmentElementRenderer<Window> {

        /**
         * The thickness of the line which represents the door. (when scale is
         * 1)
         */
        private static final int LINE_THICKNESS = 10;

        /**
         * The both rendering end points of the door.
         */
        private final Point[] points;

        private WindowRenderer(final Window window) {
            super(Color.BLACK, window);
            this.points = new Point[]{new Point(), new Point()};
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.sercho.masp.models.Context.gui.ContextModelVisualizer.
         * EnvironmentElementRenderer#paintHook(java.awt.Graphics2D,
         * java.awt.Point)
         */
        @Override
        public void paintHook(final Graphics2D g, Point point) {
            if(point == null) {
                point = super.point;
            }

            final Color c = g.getColor();
            final Stroke stroke = g.getStroke();

            final BooleanProperty open = super.element.getOpen();
            if((open != null) && (open.getValue() != null)) {
                if(open.getValue().booleanValue()) {
                    g.setColor(WINDOW_OPEN_COLOR);
                } else {
                    g.setColor(WINDOW_CLOSED_COLOR);
                }
            } else {
                g.setColor(WINDOW_UNKNOWN_COLOR);
            }

            g.setStroke(new BasicStroke((float)(LINE_THICKNESS * ContextModelVisualizer.this.scale)));
            g.drawLine(point.x, point.y, point.x + this.points[1].x - this.points[0].x, point.y + this.points[1].y - this.points[0].y);

            g.setStroke(stroke);
            g.setColor(c);
        }

        @Override
        protected void drawID(final Graphics2D g, final String id, final String text) {

            // no id is drawn, but there is a pop up...
            // update active area for the pop up
            final int lineWidthHalf = (int)Math.ceil((LINE_THICKNESS * ContextModelVisualizer.this.scale) / 2);

            // calculate points of surrounding rectangle
            // and ensure that span is positive
            final int x1 = Math.min(this.points[0].x, this.points[1].x) - lineWidthHalf;
            final int y1 = Math.min(this.points[0].y, this.points[1].y) - lineWidthHalf;
            final int x2 = Math.max(this.points[0].x, this.points[1].x) + lineWidthHalf;
            final int y2 = Math.max(this.points[0].y, this.points[1].y) + lineWidthHalf;

            ContextModelVisualizer.this.createArea(super.element, x1, y1, x2 - x1, y2 - y1);
        }

        @Override
        protected Color getDragAndDropOvalColor() {
            return Color.RED;
        }

        // @Override
        // void paintHook(final Graphics2D g) {
        //
        // // System.out.println("DoorRenderer.paintHook " + this.points[0] +
        // // " " + this.points[1]);
        //
        // final Color c = g.getColor();
        // final Stroke stroke = g.getStroke();
        //
        // final BooleanProperty open = super.element.getOpen();
        // if((open != null) && (open.getValue() != null)) {
        // if(open.getValue().booleanValue()) {
        // g.setColor(WINDOW_OPEN_COLOR);
        // } else {
        // g.setColor(WINDOW_CLOSED_COLOR);
        // }
        // } else {
        // g.setColor(WINDOW_UNKNOWN_COLOR);
        // }
        //
        // g.setStroke(new BasicStroke((float)(LINE_THICKNESS *
        // ContextModelVisualizer.this.scale)));
        // g.drawLine(this.points[0].x, this.points[0].y, this.points[1].x,
        // this.points[1].y);
        //
        // g.setStroke(stroke);
        // g.setColor(c);
        // }// paintHook

        /**
         * {@inheritDoc}
         */
        @Override
        synchronized void updateCoordinates() {
            super.updateCoordinates();

            final Vector position = super.element.getPosition();
            final Vector span = super.element.getSpan();

            if((position != null) && (span != null)) {
                this.points[0].x = (int)((position.getX() + ContextModelVisualizer.this.xOffset) * ContextModelVisualizer.this.scale);
                this.points[0].y = ContextModelVisualizer.this.getCanvasHeight() - ((int)((position.getY() + ContextModelVisualizer.this.yOffset) * ContextModelVisualizer.this.scale));

                this.points[1].x = (int)((position.getX() + span.getX() + ContextModelVisualizer.this.xOffset) * ContextModelVisualizer.this.scale);
                this.points[1].y = ContextModelVisualizer.this.getCanvasHeight() - ((int)((position.getY() + span.getY() + ContextModelVisualizer.this.yOffset) * ContextModelVisualizer.this.scale));
            }

            // check if first point is upper left one...
            if((this.points[0].x > this.points[1].x) || (this.points[0].y > this.points[1].y)) {
                // if not: switch both points
                int temp;

                temp = this.points[0].x;
                this.points[0].x = this.points[1].x;
                this.points[1].x = temp;
                temp = this.points[0].y;
                this.points[0].y = this.points[1].y;
                this.points[1].y = temp;
            }

            super.point.setLocation((this.points[0].x + this.points[1].x) / 2, (this.points[0].y + this.points[1].y) / 2);
        }
    }// class WindowRenderer

    /**
     * The {@link Log}.
     */
    public static final transient Log LOG = LogFactory.getLog(ContextModelVisualizer.class);

    /**
     * The color of an dragged {@link Area}.
     */
    private static Color AREA_DRAG_COLOR = Color.BLUE;

    /**
     * The color to highlight an {@link Area}.
     */
    private static Color AREA_HIGHLIGHT_COLOR = Color.BLUE;

    /**
     * The {@link Color} of the background.
     */
    private static Color BACKGROUND_COLOR = new Color(98, 98, 98);

    private static final String DANGLING_ELEMENTS_BORDER_TEXT = "Dangling elements";

    private static final int DANGLING_ELEMENTS_PANEL_MINIMUM_SIZE = 150;

    private static final Dimension DANGLING_ELEMENTS_PANEL_DEFAULT_SIZE = new Dimension(150, 150);

    /**
     * The size (width and height) of a dangling element relative to scale 1.
     */
    private static final int DANGLING_ELEMENTS_SIZE = 200;

    /**
     * The {@link Color} of a {@link Display}.
     */
    private static Color DISPLAY_COLOR = Color.YELLOW;

    /**
     * The color of closed {@link Door}s.
     */
    private static Color DOOR_CLOSED_COLOR = Color.RED;

    // public void repaint() {
    // if(LOG.isDebugEnabled()) {
    // LOG.debug("repaint");
    // }
    // super.repaint();
    // }
    //
    // protected void paintComponent(Graphics g) {
    // if(LOG.isDebugEnabled()) {
    // LOG.debug("paintComponent");
    // }
    // super.paintComponent(g);
    // }
    //
    // protected void paintBorder(Graphics g) {
    // if(LOG.isDebugEnabled()) {
    // LOG.debug("paintBorder");
    // }
    // super.paintBorder(g);
    // }
    //
    // protected void paintChildren(Graphics g) {
    // if(LOG.isDebugEnabled()) {
    // LOG.debug("paintChildren");
    // }
    // super.paintChildren(g);
    // }

    /**
     * The color of open {@link Door}s.
     */
    private static Color DOOR_OPEN_COLOR = new Color(0, 128, 0);

    /**
     * The color of {@link Door}s with unknown open state.
     */
    private static Color DOOR_UNKNOWN_COLOR = Color.GRAY;

    /**
     * The ratio of the arrowhead in relation to the whole arrow length.
     */
    private static double DRAG_POINT_ARROW_RATIO = 0.2;

    private static int DRAG_POINT_OFFSET = 30;

    /**
     * The offset by which the location strig is translated from the relating
     * location.
     */
    private static int DRAGPOINT_OFFSET = 15;

    /**
     * The offset by which the coordinates are rendered in relation to the mouse
     * pointer. (Orientation: As at {@link Graphics}.)
     */
    private static Point ENVIRONMENT_POINTING_OFFSET = new Point(5, -5);

    /**
     * The {@link Color} of
     */
    private static Color IR_OVAL_AVAILABLE_COLOR = Color.GREEN;

    /**
     * The {@link Color} of
     */
    private static Color IR_OVAL_NOT_AVAILABLE_COLOR = Color.RED;

    /**
     * The {@link Color} of a {@link Keyboard}.
     */
    private static Color KEYBOARD_COLOR = Color.BLUE;

    /**
     * The {@link Color} of a {@link LocalizationTag}.
     */
    private static Color LOCALIZATION_TAG_COLOR = Color.GREEN;

    /**
     * The {@link Color} of a detected {@link LocalizationTag}.
     */
    private static Color LOCALIZATION_TAG_DETECTED_OVAL_COLOR = Color.GREEN;

    /**
     * The {@link Color} of the oval of a {@link LocalizationTag}.
     */
    private static Color LOCALIZATION_TAG_OVAL_COLOR = Color.MAGENTA;

    /**
     * The maximum amount of elements at the pop-up menu to append the menu
     * elements od the {@link Environment}.
     */
    private static final int MAXIMUM_POPUP_MENU_ELEMENTS_FOR_ENVIRONMENT_ELEMENTS = 10;

    /**
     * The {@link Color} of a {@link Mouse}.
     */
    private static Color MOUSE_COLOR = Color.BLACK;

    /**
     * The {@link Color} of a {@link Outdoors}.
     */
    private static Color OUTDOORS_AREA_COLOR = new Color(200, 200, 200);

    /**
     * The {@link Color} of a {@link Place}.
     */
    private static Color PLACE_AREA_COLOR = new Color(255, 0, 0);

    /**
     * The color of a dragged {@link Place}.
     */
    private static Color PLACE_DRAG_COLOR = new Color(0, 192, 0);

    /**
     * The color to highlight a {@link Place}.
     */
    private static Color PLACE_HIGHLIGHT_COLOR = PLACE_DRAG_COLOR;

    private static float POPUP_FONT_SIZE = 12;

    /**
     * The {@link Color} of the headline in a pop-up.
     */
    private static Color POPUP_HEADLINE_TEXT_COLOR = Color.YELLOW;

    /**
     * The {@link Color} of the border of the rectangle which represents a
     * pop-up.
     */
    private static Color POPUP_RECTANGLE_BORDER_COLOR = Color.WHITE;

    /**
     * The {@link Color} of the rectangle which represents a pop-up.
     */
    private static Color POPUP_RECTANGLE_COLOR = new Color(98, 98, 98);// new

    /**
     * The {@link Color} of the text in a pop-up.
     */
    private static Color POPUP_TEXT_COLOR = Color.WHITE;

    /**
     * The {@link Color} of a {@link Room}.
     */
    private static Color ROOM_AREA_COLOR = new Color(215, 205, 158);

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8072027872420358356L;

    private static int TEXT_BACKGROUND_BORDER = 2;

    private static final String UNKNOWN_FLOOR_TAB_TEXT = "?";

    /**
     * The {@link Color} of a {@link User}.
     */
    private static Color USER_COLOR = Color.RED;

    /**
     * The {@link Color} of
     */
    private static Color USER_OVAL_COLOR = Color.BLUE;

    /**
     * The color of closed {@link Door}s.
     */
    private static Color WINDOW_CLOSED_COLOR = new Color(128, 0, 0);

    /**
     * The color of open {@link Door}s.
     */
    private static Color WINDOW_OPEN_COLOR = new Color(0, 64, 0);

    /**
     * The color of {@link Door}s with unknown open state.
     */
    private static Color WINDOW_UNKNOWN_COLOR = Color.BLACK;

    public static Map<String, Property<?>> getCurrentProperties(final ElementWithPosition element) {
        final HashMap<String, Property<?>> propertiesMap = new HashMap<String, Property<?>>();

        final Collection<Property<?>> properties = EcoreUtil.getObjectsByType(element.eContents(), PropertiesPackage.Literals.PROPERTY);
        EStructuralFeature feature;
        if(properties != null) {
            for(final Property<?> property : properties) {
                feature = property.eContainingFeature();
                propertiesMap.put(feature.getName(), property);
            }
        }// if

        return propertiesMap;
    }

    /**
     * List all the IDs.
     * 
     * @param elements
     * @return {@link String}-representation to the elements IDs if they are
     *         {@link EnvironmentElement}s.
     */
    public static String getIds(final Collection<?> elements) {
        if(elements == null) {
            return null;
        }

        final StringBuilder sb = new StringBuilder();

        sb.append("[");

        for(final Object object : elements) {

            if(object instanceof EnvironmentElement) {
                sb.append(((EnvironmentElement)object).getId());
                sb.append(", ");
            }
        }

        if(sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append("]");

        return sb.toString();
    }// getIds

    public static String toString(final Point point) {
        if(point == null) {
            return "null";
        }

        return toLocationString(point.x, point.y);
    }

    private static void dispose(final Map<?, ? extends VectorAdapter<?>> adapters) {
        for(final VectorAdapter<?> adapter : adapters.values()) {
            adapter.dispose();
        }
    }

    /**
     * Draws an arrow.
     * 
     * @param g
     *            The {@link Graphics} to draw on.
     * @param x1
     *            Source point x.
     * @param y1
     *            Source point y.
     * @param x2
     *            Target point x.
     * @param y2
     *            Target point y.
     * @param ratio
     *            Indicating the length of the arrowhead in relation the the
     *            arrow length.
     */
    private static void drawArrow(final Graphics g, final int x1, final int y1, final int x2, final int y2, final double ratio) {
        final int xc = (int)(x2 - ((x2 - x1) * ratio));
        final int yc = (int)(y2 - ((y2 - y1) * ratio));

        final int a = (int)(((y2 - y1) * ratio) / 2);
        final int b = (int)(((x2 - x1) * ratio) / 2);

        final int x3 = (xc - a);
        final int y3 = (yc + b);

        final int x4 = (xc + a);
        final int y4 = (yc - b);

        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, x3, y3);
        g.drawLine(x2, y2, x4, y4);
    }// drawArrow

    private static void drawArrowCross(final Graphics g, final int x, final int y, final int length) {
        final double ratio = 0.2;
        drawArrow(g, x, y, x + length, y, ratio);
        drawArrow(g, x, y, x - length, y, ratio);
        drawArrow(g, x, y, x, y + length, ratio);
        drawArrow(g, x, y, x, y - length, ratio);
    }

    private static void drawBiArrow(final Graphics g, final int x1, final int y1, final int x2, final int y2, final double ratio) {
        g.drawLine(x1, y1, x2, y2);

        int xc = (int)(x2 - ((x2 - x1) * ratio));
        int yc = (int)(y2 - ((y2 - y1) * ratio));

        int x3 = (int)(xc - (((y2 - y1) * ratio) / 2));
        int y3 = (int)(yc + (((x2 - x1) * ratio) / 2));
        g.drawLine(x2, y2, x3, y3);

        x3 = (int)(xc + (((y2 - y1) * ratio) / 2));
        y3 = (int)(yc - (((x2 - x1) * ratio) / 2));
        g.drawLine(x2, y2, x3, y3);

        xc = (int)(x1 + ((x2 - x1) * ratio));
        yc = (int)(y1 + ((y2 - y1) * ratio));

        x3 = (int)(xc + (((y2 - y1) * ratio) / 2));
        y3 = (int)(yc - (((x2 - x1) * ratio) / 2));
        g.drawLine(x1, y1, x3, y3);

        x3 = (int)(xc - (((y2 - y1) * ratio) / 2));
        y3 = (int)(yc + (((x2 - x1) * ratio) / 2));
        g.drawLine(x1, y1, x3, y3);
    }// drawArrow

    private static final String getTagsString(final ElementWithPosition element) {
        final StringBuffer tagIDs = new StringBuffer();
        synchronized(element.getTags()) {
            if((element.getTags() != null) && (!element.getTags().isEmpty())) {
                for(int i = element.getTags().size() - 1; i > 0; i--) {
                    tagIDs.append(element.getTags().get(i).getId());
                    tagIDs.append(" | ");
                }
                tagIDs.append(element.getTags().get(0).getId());
            } else {
                tagIDs.append("none");
            }
        }
        return tagIDs.toString();
    }

    private static String toLocationString(final double x, final double y) {
        return new StringBuilder().append("(").append((int)x).append(":").append((int)y).append(")").toString();
    }

    protected static String getCreateServiceString(final ServiceType type, final ServiceContainer serviceContainer) {
        switch(type) {
            case ACTOR:
                return "Create new Actor at " + serviceContainer.getId();
            case SENSOR:
                return "Create new Sensor at " + serviceContainer.getId();
            default:
                return null;
        }
    }

    /**
     * The actual selected {@link FloorPanel} from the
     * {@link ContextModelVisualizer#floorTabs}.
     */
    private FloorPanel currentFloorPanel;

    private boolean areaEditMode;

    private final ContextMenuUtil contextMenuUtil;

    /**
     * The {@link Door} next to the mouse pointer. (<code>null</code> id there
     * if no {@link Door}.)
     */
    private Door currentDoor;

    /**
     * The {@link InteractionResource} next to the mouse pointer. (
     * <code>null</code> if there is no {@link InteractionResource}.)
     */
    private InteractionResource currentIR;

    /**
     * The {@link LocalizationTag} next to the mouse pointer. (<code>null</code>
     * if there is no {@link LocalizationTag}.)
     */
    private LocalizationTag currentLocalizationTag;

    /**
     * The {@link PhysicalDevice} next to the mouse pointer. (<code>null</code>
     * if there is no {@link PhysicalDevice}.)
     */
    private PhysicalDevice currentPhysicalDevice;

    /**
     * The {@link User} next to the mouse pointer. (<code>null</code> if there
     * is no {@link User}.)
     */
    private User currentUser;

    /**
     * The {@link Window} next to the mouse pointer. (<code>null</code> id there
     * if no {@link Window}.)
     */
    private Window currentWindow;

    private DanglingElementsPanel danglingElementsPanel;

    private final Set<Disposeable> disposeables = Collections.synchronizedSet(new HashSet<Disposeable>());

    /**
     * All {@link VectorAdapter}s of {@link Door}s.
     */
    private volatile Map<Door, VectorAdapter<Door>> doors;

    /**
     * The dragged {@link Area} or a {@link Place} with all its areas.
     */
    private volatile Object draggedArea;

    /**
     * The currently dragged {@link ElementWithPosition}. (<code>null</code> if
     * no element is dragged.)
     */
    private volatile ElementWithPosition draggedElement;

    /**
     * The elements which are dragged with an {@link Area} or {@link Place}.
     */
    private volatile LinkedList<ElementWithPosition> draggedElements;

    /**
     * The offset vector pointing from the mouse cursor to the point to move.
     * The coordinates are related to the {@link Environment}.
     */
    private Point draggingOffset;

    /**
     * Indicating where the dragged {@link Area} or {@link Place} is touched by
     * the mouse.
     */
    private volatile DragPointType dragPointType;

    /**
     * The {@link ElementComparator} to compare elements of the
     * {@link Environment}.
     */
    private final ElementComparator elementComparator = new ElementComparator();

    /**
     * The {@link Environment} which is displayed.
     */
    private Environment environment;

    /**
     * The location of the mouse cursor in environment coordinates.
     */
    private final Point environmentPointing;

    /**
     * A list of filtered IDs.
     */
    private final LinkedList<Object> filterList;

    /**
     * A {@link JTabbedPane} for the floors of the {@link Environment}.
     */
    private JTabbedPane floorTabs;

    /**
     * All {@link VectorAdapter}s of {@link InteractionResource}s.
     */
    private volatile Map<InteractionResource, VectorAdapter<InteractionResource>> irs;

    private final ListObserver<InteractionResource> irsObserver = new InteractionResourceObserver();

    private Point lastClickLocation;

    private Dimension lastFrameSize;

    /**
     * The last position or origin of an dragged element.
     */
    private Vector lastPosition;

    /**
     * The last position or origin of a list of dragged elements.
     */
    private LinkedList<Vector> lastPositions;

    /**
     * The last span of an dragged element.
     */
    private Vector lastSpan;

    private final ListObserver<LocalizationTag> localizationTagObserver = new LocalizationTagObserver();

    /**
     * Indicates to filter localization tags. (<code>true</code> => no
     * localization tag is displayed.)
     */
    private boolean lTagsFilter = false;

    private final VisualizerManager manager;

    /**
     * The element which is moved.
     */
    private Object objectToMove;

    /**
     * TODO what did they do exactly?
     */
    private final GraphicalAreas paintedAreas = new GraphicalAreas();

    private final Map<String, Collection<Path>> paths = new Hashtable<String, Collection<Path>>();

    /**
     * All {@link VectorAdapter}s of {@link PhysicalDevice}s.
     */
    private volatile Map<PhysicalDevice, VectorAdapter<PhysicalDevice>> physicalDevices;

    /**
     * A {@link Map} of the {@link Place} of an {@link Area}.
     */
    private Map<Area, Place> placeAreas;

    /**
     * All {@link VectorAdapter}s of {@link Place}s.
     */
    private volatile Map<Place, VectorAdapter<Place>> places;

    /**
     * Indicates to filter {@link Place}s. (<code>true</code> => no
     * {@link Place} is displayed.)
     */
    private boolean placesFilter;

    private boolean playMode = false;

    /**
     * The location of the mouse cursor in coordinates relative to the
     * {@link ContextModelVisualizer} {@link JPanel}.
     */
    private Point pointing;

    private Point popupLocation;

    /**
     * Represents a context-menu. If it is
     * <code>null</null> no menu is displayed.
     */
    private JPopupMenu popupMenu;

    private boolean recordMode = false;

    private volatile double scale = 10;

    /**
     * The {@link GraphicalAreas} to represent active areas for the painted
     * elements to select them. The locations are relative to the
     * {@link ContextModelVisualizer} {@link JPanel}.
     */
    private final GraphicalAreas selectionAreas = new GraphicalAreas();

    private boolean showPaths = true;

    /**
     * All {@link VectorAdapter}s of {@link LocalizationTag}s.
     */
    private volatile Map<LocalizationTag, VectorAdapter<LocalizationTag>> tags;

    private boolean thereWasMovement = false;

    /**
     * All {@link VectorAdapter}s of {@link Door}s.
     */
    private volatile Map<User, VectorAdapter<User>> users;

    /**
     * Indicates to filter {@link User}s. (<code>true</code> => no {@link User}
     * is displayed.)
     */
    private boolean usersFilter;

    /**
     * All {@link VectorAdapter}s of {@link Window}s.
     */
    private volatile Map<Window, VectorAdapter<Window>> windows;

    private volatile int xOffset = 0;

    private volatile int yOffset = 0;

    /**
     * <code>ContextModelVisualizer</code> constructor.
     * 
     * @param environment
     *            context model to visualize
     */
    public ContextModelVisualizer(final Environment environment,
            final VisualizerManager manager) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("ContextModelVisualizer " + environment);
        }

        if(environment != null) {
            Thread thread = new Thread() {

                public void run() {
                    fixEnvironment(environment);
                }
            };
            thread.setDaemon(true);
            thread.setName("FixEnvironment Thread");
            thread.start();
        }

        this.filterList = new LinkedList<Object>();
        this.manager = manager;
        this.areaEditMode = false;
        this.environmentPointing = new Point();
        this.renewMaps();
        this.contextMenuUtil = manager.getContextMenuUtil();
        this.addMouseListener(this);
        this.loadRoomTextures();

        if(environment != null) {
            this.environment = environment;
            // EnvironmentUtility.fixEnvironment(this.environment);
            this.danglingElementsPanel = new DanglingElementsPanel();
            this.initObservers();
            this.danglingElementsPanel.updateDanglingElements();
            super.enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
        }

        initComponentsAndLayout();

        super.addComponentListener(this);
    }

    /**
     * Removes all places, users, irs, filters and the environment.
     */
    @Override
    public void clearEnvironment() throws VisualizerException {
        try {
            dispose();
            this.renewMaps();
            this.environment = null;
            this.usersFilter = false;
            this.placesFilter = false;
            this.lTagsFilter = false;
            this.filterList.clear();
            updateUI();
        }
        catch(final Exception e) {
            throw new VisualizerException("Error clearing environment");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearPaths() {
        this.paths.clear();
        updateUI();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public void componentHidden(final ComponentEvent e) {
        // nothing to do
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ComponentListener#componentMoved(java.awt.event.ComponentEvent
     * )
     */
    @Override
    public void componentMoved(final ComponentEvent e) {
        // nothing to do
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ComponentListener#componentResized(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public void componentResized(final ComponentEvent e) {
        ContextModelVisualizer.this.scale();
        ContextModelVisualizer.this.danglingElementsPanel.doLayout();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent
     * )
     */
    @Override
    public void componentShown(final ComponentEvent e) {
        // nothing to do
    }

    @Override
    public void disablePopup() {
        this.popupMenu = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        LOG.info("Disposing");
        synchronized(this.disposeables) {
            for(final Disposeable disposeable : this.disposeables) {
                try {
                    disposeable.dispose();
                }
                catch(final Exception e) {
                    LOG.warn("Failed to dispose " + disposeable, e);
                }
            }
            this.disposeables.clear();
        }
        dispose(this.places);
        this.places = new HashMap<Place, VectorAdapter<Place>>();
        dispose(this.doors);
        this.doors = new HashMap<Door, ContextModelVisualizer.VectorAdapter<Door>>();
        dispose(this.windows);
        this.windows = new HashMap<Window, ContextModelVisualizer.VectorAdapter<Window>>();
        dispose(this.users);
        this.users = new HashMap<User, VectorAdapter<User>>();
        dispose(this.irs);
        this.irs = new HashMap<InteractionResource, VectorAdapter<InteractionResource>>();
        dispose(this.tags);
        this.tags = new HashMap<LocalizationTag, VectorAdapter<LocalizationTag>>();
        dispose(this.physicalDevices);
        this.physicalDevices = new HashMap<PhysicalDevice, ContextModelVisualizer.VectorAdapter<PhysicalDevice>>();
        LOG.info("Disposed");
    }

    @Override
    public void doLayout() {
        super.doLayout();
        // if(LOG.isDebugEnabled()) {
        // LOG.debug("doLayout");
        // }
    }

    /**
     * Configures the current filters. If the identifier is currently filtered,
     * then will be removed from the filters, in other case, will be included in
     * the filters. Currently there are two kind of filters. One is filtering
     * with a concrete identifier of an element, the other kind filter is by
     * groups, like users or elements.
     */
    @Override
    public void filter(final Object element, final boolean isChecked) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("filter " + element);
        }

        if(element == null) {
            return;
        }

        if(!isChecked) {
            if(!this.filterList.contains(element)) {
                this.filterList.add(element);
            }
        } else {
            this.filterList.remove(element);
        }

        if(element.equals(ElementType.USERS)) {
            this.usersFilter = isChecked == false;
        } else if(element.equals(ElementType.PROVIDERS)) {
            this.lTagsFilter = isChecked == false;
        } else if(element.equals(ElementType.PLACES)) {
            this.placesFilter = isChecked == false;
        }

        repaint();
    }

    @Override
    public Frame getParentFrame() {
        return this.manager.getMainFrame();
    }

    @Override
    public Point getPopupLocation() {
        return this.popupLocation;
    }

    @Override
    public void hidePath(final String elementId) {
        this.paths.remove(elementId);
        repaint();
    }

    @Override
    public void hidePaths() {
        this.showPaths = false;
        repaint();
    }

    @Override
    public boolean isRecordModeActive() {
        return this.recordMode;
    }

    /**
     * Loads an specific context model and call repaint() for visualize the new
     * context model.
     */
    @Override
    public void loadEnvironment(final Environment environment) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("loadEnvironment " + environment);
        }

        if(environment != null) {
            fixEnvironment(environment);

            this.renewMaps();

            this.environment = environment;
            // EnvironmentUtility.fixEnvironment(this.environment);
            this.contextMenuUtil.setEnvironment(environment);
            this.areaEditMode = false;

            this.initObservers();
            super.enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);

            this.danglingElementsPanel.updateDanglingElements();
            if(LOG.isDebugEnabled()) {
                LOG.debug(this.danglingElementsPanel.getDanglingString());
            }

            this.initComponentsAndLayout();

            super.doLayout();
            this.floorTabs.doLayout();

            super.repaint();
        }
    }

    /**
     * Fixes some issues at the {@link Environment}.
     * 
     * @param environment
     *            The {@link Environment} to fix.
     */
    private void fixEnvironment(Environment environment) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("fixEnvironment...");
        }

        LinkedList<String> messages = new LinkedList<String>();

        messages.addAll(EnvironmentUtility.fixPlaceInRealtionToPosition(environment, false));
        messages.addAll(EnvironmentUtility.fixMissingPositions(environment));

        if(messages.size() > 0) {
            if(LOG.isInfoEnabled()) {
                LOG.info("Fixed Environment: " + messages.toString());
            }

            Collections.sort(messages);

            StringBuilder sb = new StringBuilder();

            for(String message : messages) {
                sb.append(message);
                sb.append("\n");
            }

            TextMessageDialog textMessageDialog = new TextMessageDialog(this.getParentFrame(), "Fixed Environment", "Some Elements of the Environment where changed:", sb.toString());
            textMessageDialog.setAlwaysOnTop(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(final MouseEvent e) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("ContextmodelVisualizer.mouseClicked " + e);
            LOG.debug("point: " + toString(e.getPoint()) + " location: " + toString(e.getLocationOnScreen()) + " pointing: " + toString(this.pointing) + " environmentPointing: " + toString(this.environmentPointing));
            LOG.debug("source: " + e.getSource());
        }

        // disable context menu
        if(this.popupMenu != null) {
            this.popupMenu.setVisible(false);
            this.popupMenu.setEnabled(false);
            this.popupMenu = null;
        }

        final Point point = getLocation(e);
        this.lastClickLocation = point;

        // translate point if event comes from FloorPanel
        // if(e.getSource() instanceof FloorPanel) {
        // point.translate(this.actualFloorPanel.getLocation().x,
        // this.actualFloorPanel.getLocation().y);
        // } else if(e.getSource() == this.danglingElementsPanel) {
        // point.translate(this.danglingElementsPanel.getLocation().x,
        // this.danglingElementsPanel.getLocation().y);
        // }

        if(e.getButton() == MouseEvent.BUTTON3) {
            // right click => display context menu
            final LinkedList<Object> elements;

            // if(e.getSource() == this.actualFloorPanel) {
            // elements = this.selectionAreas.getElements(e.getPoint().x,
            // e.getPoint().y);
            // } else {
            // elements = this.selectionAreas.getElements(point.x, point.y);
            // }

            elements = this.selectionAreas.getElements(point.x, point.y);

            Collections.sort(elements, this.elementComparator);
            elements.add(this.environment);

            if(LOG.isDebugEnabled()) {
                LOG.debug("ids: " + getIds(elements));
                LOG.debug(this.selectionAreas);
            }

            if(elements.size() > 0) {
                final JMenu menu = new JMenu("temp");

                for(final Object element : elements) {
                    if(LOG.isDebugEnabled()) {
                        LOG.debug("create menu for " + element);
                    }

                    if(element instanceof Area) {
                        final Area area = (Area)element;
                        Place place = null;

                        if(LOG.isDebugEnabled()) {
                            LOG.debug("id represents area");
                            place = this.placeAreas.get(area);
                            LOG.debug("place: " + place);

                            if(place == null) {
                                LOG.debug(this.placeAreas);
                            }
                        }

                        final JMenu areaMenu = new JMenu("Area");

                        ContextModelVisualizer.this.contextMenuUtil.appendMenuItems(areaMenu, area, place, this);
                        menu.add(areaMenu);

                        // final JMenu placeMenu = new
                        // JMenu(this.placeAreas.get(area).getName());
                        // ContextMenuUtil.appendMenuItems(placeMenu,
                        // this.placeAreas.get(area), this.environment,
                        // this.environment, this);
                        // menu.add(placeMenu);
                    } else if(element instanceof EnvironmentElement) {
                        final EnvironmentElement environmentElement = (EnvironmentElement)element;
                        {
                            Object parent = this.environment;

                            if(environmentElement instanceof Door) {
                                parent = ((Door)environmentElement).getPlace();
                            } else if(environmentElement instanceof Window) {
                                parent = ((Window)environmentElement).getPlace();
                            } else if(environmentElement instanceof InteractionResource) {
                                parent = ((InteractionResource)environmentElement).getDevice();

                                if(parent == null) {
                                    LOG.warn("failed to get device from interaction resource " + environmentElement);
                                }
                            }

                            if(parent == null) {
                                LOG.warn("Parent element of " + environmentElement.getId() + " does'nt exist");
                                parent = this.environment;
                            }

                            if(elements.size() > 1) {
                                final JMenu elementMenu = new JMenu(ContextMenuUtil.getMenuText(environmentElement));
                                ContextModelVisualizer.this.contextMenuUtil.appendMenuItems(elementMenu, environmentElement, parent, this);
                                menu.add(elementMenu);
                            } else {
                                ContextModelVisualizer.this.contextMenuUtil.appendMenuItems(menu, environmentElement, parent, this);
                            }
                        }
                    } else if(element instanceof Environment) {
                        final int elementCount = menu.getSubElements().length;

                        if(elementCount < MAXIMUM_POPUP_MENU_ELEMENTS_FOR_ENVIRONMENT_ELEMENTS) {
                            if(elementCount > 0) {
                                menu.addSeparator();
                            }

                            ContextModelVisualizer.this.contextMenuUtil.appendEnvironmentMenuItems(menu, null, this);
                        }
                    }
                    // ContextMenuUtil.appendEObjectMenuItems(menu,
                    // environmentElement, this.environment);
                }

                this.popupMenu = ContextMenuUtil.getPopupMenu(menu);
                this.popupMenu.setLocation(point);
                this.popupMenu.show(this, point.x, point.y);
            } else {
                // clicked somewhere => display menu for the environment
                this.popupMenu = ContextModelVisualizer.this.contextMenuUtil.getPopupMenu(this.environment, null, this);
                this.popupMenu.setLocation(point);
                this.popupMenu.show(this, point.x, point.y);
            }

            super.repaint();
        } else {
            // TODO what's this?
            final int x = (int)(point.x / this.scale) - this.xOffset;
            final int y = (int)((getCanvasHeight() - point.y) / this.scale) - this.yOffset;
            this.manager.notifyClick(x, y);
        }
    }// mouseClicked

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered(final MouseEvent arg0) {
        // nothing to do here yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExited(final MouseEvent arg0) {
        // nothing to do here yet
    }

    /**
     * {@inheritDoc}
     * 
     * Checks if the current point of the mouse is contained in one of the oval
     * painted for every element in order to be moved over the visualizer. If is
     * the case, the identifier of the element is stored in order to be moved
     * when the mouse is later released in another position
     */
    @Override
    public void mousePressed(final MouseEvent e) {
        if(LOG.isDebugEnabled()) {
            LOG.debug(new StringBuilder("mousePressed at ").append(toString(this.environmentPointing)).append(" ").append(e.toString()));
            LOG.debug("point: " + toString(e.getPoint()) + " location: " + toString(e.getLocationOnScreen()) + " pointing: " + toString(this.pointing) + " environmentPointing: " + toString(this.environmentPointing));
            LOG.debug("floorTabs: " + toString(this.floorTabs.getLocation()) + " floorPanel:  " + toString(this.currentFloorPanel.getLocation()) + " danglingPanel: " + toString(this.danglingElementsPanel.getLocation()));
        }

        if(e.getButton() == 1) {
            final Point point = getLocation(e);

            // if(e.getSource() == this) {
            // // nothing to do
            // } else if(e.getSource() == this.actualFloorPanel) {
            // // point.translate(this.actualFloorPanel.getLocation().x,
            // // this.actualFloorPanel.getLocation().y);
            // } else if(e.getSource() == this.danglingElementsPanel) {
            // point.translate(this.danglingElementsPanel.getLocation().x,
            // this.danglingElementsPanel.getLocation().y);
            // } else {
            // LOG.warn("invalid source of envent " + e);
            // }

            // get id of element that is dragged
            final LinkedList<Object> elements = this.selectionAreas.getElements(point.x, point.y);
            this.objectToMove = getElement(elements);

            if(LOG.isDebugEnabled()) {
                LOG.debug(new StringBuilder("elements: ").append(getIds(elements)).append(" choosen: ").append(this.objectToMove));
            }

            // store the element that is dragged, no need to search for it
            // later!

            if(this.objectToMove != null) {
                if(this.objectToMove instanceof EnvironmentElement) {
                    // ++++++++++ EnvironmentElement ++++++++++
                    final EnvironmentElement element = (EnvironmentElement)this.objectToMove;

                    if(element instanceof Door || element instanceof Window) {
                        if(!this.areaEditMode && !this.danglingElementsPanel.isDangling(element)) {
                            // deny moving of Doors an Windows if area edit mode
                            // is OFF and element is not dangling
                            this.objectToMove = null;
                            return;
                        }
                    }

                    if(element instanceof ElementWithPosition) {
                        final ElementWithPosition elementWithPosition = (ElementWithPosition)element;
                        this.manager.elementSelected(element.getId());

                        // +++++ move ElementWithPosition +++++
                        this.draggedElement = elementWithPosition;

                        if(this.danglingElementsPanel.isDangling(this.draggedElement)) {
                            // this.draggingOffset =
                            // this.danglingElementsPanel.getDanglingElementPosition(this.draggedElement);
                            // final Point location =
                            // this.danglingElementsPanel.getLocation();
                            // this.draggingOffset.translate(location.x,
                            // location.y);
                            // this.draggingOffset.translate(-this.pointing.x,
                            // -this.pointing.y);
                            // //
                            // this.draggingOffset.translate(-this.actualFloorPanel.getLocation().x,
                            // // -this.actualFloorPanel.getLocation().y);
                            //
                            // if(LOG.isDebugEnabled()) {
                            // LOG.debug("absolute offset: " +
                            // this.draggingOffset);
                            // }
                            //
                            // this.draggingOffset.setLocation(this.draggingOffset.x
                            // / this.scale, -this.draggingOffset.y /
                            // this.scale);

                            // no offset for dangling elements
                            this.draggingOffset = new Point(0, 0);
                        } else if(this.draggedElement.getPosition() != null) {
                            this.lastPosition = this.draggedElement.getPosition();
                            this.draggingOffset = new Point(this.environmentPointing);
                            this.draggingOffset.setLocation(-this.draggingOffset.x, -this.draggingOffset.y);
                            this.draggingOffset.translate((int)this.lastPosition.getX(), (int)this.lastPosition.getY());

                            if(LOG.isDebugEnabled()) {
                                LOG.debug("lastPos: " + toString2D(this.lastPosition));
                            }
                        } else {
                            // element has no position => no offset calculable
                            this.draggingOffset = new Point();
                        }
                        // ----- move ElementWithPosition -----
                    } else if(element instanceof Place) {
                        if(this.areaEditMode) {
                            // +++++ move Place +++++
                            this.draggedArea = element;

                            if((((Place)element).getAreas() == null) || (((Place)element).getAreas().get(0) == null) || (((Place)element).getAreas().get(0).getOrigin() == null)) {
                                return;
                            }

                            this.lastPositions = new LinkedList<Vector>();

                            for(final Area area : ((Place)element).getAreas()) {
                                if(area.getOrigin() != null) {
                                    this.lastPositions.add(area.getOrigin().copy());
                                }
                            }// for

                            EnvironmentUtility.fixPlace(this.environment, (Place)element);

                            this.lastPosition = ((Room)element).getAreas().get(0).getOrigin().copy();
                            this.draggingOffset = new Point(this.environmentPointing);
                            this.draggingOffset.setLocation(-this.draggingOffset.x, -this.draggingOffset.y);
                            this.draggingOffset.translate((int)this.lastPosition.getX(), (int)this.lastPosition.getY());
                            // ----- move Place -----
                        }
                    }
                    // ---------- EnvironmentElement ----------
                } else if(this.objectToMove instanceof DragPointWrapper) {
                    if(this.areaEditMode) {
                        // +++++ move Area +++++
                        final DragPointWrapper areaWrapper = (DragPointWrapper)this.objectToMove;
                        final Area area = areaWrapper.getArea();

                        if((area == null) || (area.getOrigin() == null) || (area.getSpan() == null)) {
                            return;
                        }

                        if(LOG.isDebugEnabled()) {
                            final StringBuilder sb = new StringBuilder();
                            sb.append("moving area ");
                            sb.append(this.objectToMove);
                            sb.append(" ");
                            sb.append(toString2D(area.getOrigin()));
                            sb.append(" ");
                            sb.append(toString2D(area.getSpan()));
                            LOG.debug(sb.toString());
                        }

                        this.lastPosition = area.getOrigin().copy();
                        this.lastSpan = area.getSpan().copy();

                        this.draggingOffset = new Point(this.environmentPointing);
                        this.draggingOffset.setLocation(-this.draggingOffset.x, -this.draggingOffset.y);
                        // this.draggingOffset.translate((int)this.lastPosition.getX(),
                        // (int)this.lastPosition.getY());

                        EnvironmentUtility.fixPlace(this.environment, EnvironmentUtility.getPlaceOfArea(this.environment, area));

                        // save elements of moved area
                        // if place contains more areas
                        // to shift them after dropping area
                        this.draggedElements = new LinkedList<ElementWithPosition>();
                        applyElementsToDrag(EnvironmentUtility.getPlaceOfArea(this.environment, area), area);

                        this.dragPointType = areaWrapper.getDragPointType();

                        switch(this.dragPointType) {
                            case CENTER:
                                this.draggingOffset.translate((int)this.lastPosition.getX(), (int)this.lastPosition.getY());
                                break;
                            case NE:
                                this.draggingOffset.translate((int)(this.lastPosition.getX() + this.lastSpan.getX()), (int)(this.lastPosition.getY() + this.lastSpan.getY()));
                                break;
                            case NW:
                                this.draggingOffset.translate((int)this.lastPosition.getX(), (int)(this.lastPosition.getY() + this.lastSpan.getY()));
                                break;
                            case SE:
                                this.draggingOffset.translate((int)(this.lastPosition.getX() + this.lastSpan.getX()), (int)this.lastPosition.getY());
                                break;
                            case SW:
                                this.draggingOffset.translate((int)this.lastPosition.getX(), (int)this.lastPosition.getY());
                                break;
                        }// switch

                        if(LOG.isDebugEnabled()) {
                            LOG.debug(new StringBuilder("draggingOffset: ").append(toString(this.draggingOffset)).append(" ").append(this.dragPointType).toString());
                        }

                        this.draggedArea = area;
                        // this.manager.elementSelected(this.placeAreas.get(area).getId());
                        // ----- move Area -----
                    }
                } else {
                    LOG.warn("unknown element " + this.objectToMove);
                }

                if(LOG.isDebugEnabled()) {
                    final StringBuilder sb = new StringBuilder();

                    if(this.draggedElement != null) {
                        sb.append("dragged element: ");
                        sb.append(this.draggedElement.getId());
                        sb.append(" ");
                    }

                    if(this.draggedArea != null) {
                        sb.append("dragged area: ");

                        if(this.draggedArea instanceof Place) {
                            sb.append(((Place)this.draggedArea).getId());
                        } else {
                            sb.append(this.draggedArea);
                        }

                        sb.append(" ");
                    }

                    if(this.draggedElements != null) {
                        sb.append("dragged elements: ");
                        sb.append(getIds(this.draggedElements));
                        sb.append(" ");
                    }

                    if(sb.length() > 0) {
                        sb.append("with offset: ");
                        sb.append(toString(this.draggingOffset));
                        LOG.debug(sb.toString());
                    }
                }
            }// object not null
        } else {
            if(this.draggedElement != null) {
                this.draggedElement.setPosition(this.lastPosition);
                this.objectToMove = null;
            }
        }
    }// mousePressed

    /**
     * Checks if any element must be moved and then update some point values.
     */
    @Override
    public void mouseReleased(final MouseEvent e) {
        checkRecord(e);
        checkMovementMouseReleased(e);
        this.danglingElementsPanel.updateDanglingElements();
        // initDanglingPanel();
        this.thereWasMovement = false;
        this.draggedElement = null;
        this.draggedArea = null;
        this.objectToMove = null;
        this.lastPosition = null;
        this.lastSpan = null;
    }

    @Override
    public void paint(final Graphics g) {
        // if(LOG.isDebugEnabled()) {
        // LOG.debug("ContextModelVisualizer.paint " + this.environment, new
        // Exception("trace"));
        // LOG.debug("places: " + this.places);
        // LOG.debug("actual floor panel: " + this.actualFloorPanel.hashCode());
        // }

        try {
            // super.paintChildren(g);
            ContextModelVisualizer.this.selectionAreas.clear();
            super.paint(g);

            final Graphics2D graphics = (Graphics2D)g;

            // +++++ draw dragged element +++++
            if(this.draggedElement != null) {
                final EnvironmentElementRenderer<?> renderer = getRenderer(this.draggedElement);

                if(renderer != null) {
                    final Point point = new Point(this.pointing);
                    // point.translate(this.actualFloorPanel.getLocation().x,
                    // this.actualFloorPanel.getLocation().y);
                    point.translate((int)(this.draggingOffset.x * this.scale), -(int)(this.draggingOffset.y * this.scale));

                    renderer.paintHook(graphics, point);
                }
            }
            // ----- draw dragged element -----

            // remove disabled pop-ups
            if(ContextModelVisualizer.this.popupMenu != null) {
                // TODO why set it null?
                ContextModelVisualizer.this.currentPhysicalDevice = null;

                if(!ContextModelVisualizer.this.popupMenu.isVisible()) {
                    ContextModelVisualizer.this.popupMenu = null;
                }
            }

            // +++++ draw Information pop-ups +++++
            if(this.popupMenu == null && this.draggedElement == null && this.pointing != null) {
                final Point point = new Point(this.pointing);
                // point.translate(this.actualFloorPanel.getLocation().x,
                // this.actualFloorPanel.getLocation().y);

                drawUserPopUp(graphics, point, ContextModelVisualizer.this.currentUser, getWidth() / 2, getCanvasHeight() / 2);
                drawIRPopUp(graphics, point, ContextModelVisualizer.this.currentIR, getWidth() / 2, getCanvasHeight() / 2);
                drawLocalizationTagPopUP(graphics, point, ContextModelVisualizer.this.currentLocalizationTag, getWidth() / 2, getCanvasHeight() / 2);
                drawDoorPopUp(graphics, point, ContextModelVisualizer.this.currentDoor, getWidth() / 2, getCanvasHeight() / 2);
                drawWindowPopUp(graphics, point, ContextModelVisualizer.this.currentWindow, getWidth() / 2, getCanvasHeight() / 2);
                drawPhysicalDevicePopUp(graphics, point, ContextModelVisualizer.this.currentPhysicalDevice, getWidth() / 2, getCanvasHeight() / 2);
            }
            // ----- draw Information pop-ups -----

            // +++++ draw location of mouse +++++
            if((ContextModelVisualizer.this.pointing != null) && (ContextModelVisualizer.this.environmentPointing != null)) {

                // only draw position if no area is dragged
                if(ContextModelVisualizer.this.draggedArea == null) {
                    g.setColor(MOUSE_COLOR);
                    g.drawString(ContextModelVisualizer.toString(ContextModelVisualizer.this.environmentPointing),//
                            ContextModelVisualizer.this.pointing.x + ENVIRONMENT_POINTING_OFFSET.x,//
                            ContextModelVisualizer.this.pointing.y + ENVIRONMENT_POINTING_OFFSET.y);
                }
            }

            if((ContextModelVisualizer.this.environmentPointing != null) && (ContextModelVisualizer.this.draggedArea != null) && (ContextModelVisualizer.this.dragPointType != null)) {
                drawDragPointCoordinates(g);
            }
            // ----- draw location of mouse -----

            // // display selection areas
            // graphics.setStroke(new BasicStroke());
            // graphics.setColor(Color.magenta);
            // for(java.util.Map.Entry<Object, java.awt.geom.Area> entry :
            // this.selectionAreas.getElementAreas().entrySet()) {
            // Rectangle bounds = entry.getValue().getBounds();
            // graphics.drawRect(bounds.x, bounds.y, bounds.width,
            // bounds.height);
            // }
        }
        catch(final Throwable t) {
            LOG.warn("error while painting", t);
        }

        // if(LOG.isDebugEnabled()) {
        // LOG.debug("ContextModelVisualizer.paint done");
        // }
    }

    /**
     * Paints the {@link Environment} on the actual {@link FloorPanel}.
     * 
     * @param g
     *            The {@link Graphics} to paint on.
     * @param floor
     *            The floor index to paint.
     */
    public void paintEnvironment(final Graphics g, final Integer floor) {
        // if(LOG.isDebugEnabled()) {
        // LOG.debug("paintEnvironment floor: " + floor + " on " + g.hashCode()
        // + " with scale " + this.scale);
        // LOG.debug("actualFloorPanel: " + this.actualFloorPanel.hashCode());
        // LOG.debug("places: " + ContextModelVisualizer.this.places);
        // }

        try {
            // ContextModelVisualizer.this.selectionAreas.clear();

            if(!ContextModelVisualizer.this.playMode) {
                ContextModelVisualizer.this.paintedAreas.clear();
            }

            if(ContextModelVisualizer.this.environment != null) {
                final Graphics2D graphics = (Graphics2D)g;
                updateCurrentValidPlaces();

                // ++++++++++ paint places ++++++++++
                if(!ContextModelVisualizer.this.placesFilter) {

                    // +++++ paint areas +++++
                    for(final Place place : currentValidPlaces) {

                        if(!ContextModelVisualizer.this.filterList.contains(place)) {
                            getRenderer(place).paint(graphics);
                        }
                    }
                    // ----- paint areas -----

                    // +++++ paint doors and windows +++++
                    for(final Place place : currentValidPlaces) {
                        if(!ContextModelVisualizer.this.filterList.contains(place)) {
                            // paint doors
                            for(final VectorAdapter<Door> adapter : ContextModelVisualizer.this.doors.values()) {
                                if(!this.filterList.contains(adapter.renderer.element) && !this.danglingElementsPanel.isDangling(adapter.renderer.element)) {
                                    adapter.renderer.paint(graphics);
                                }
                            }

                            // paint windows
                            for(final VectorAdapter<Window> adapter : ContextModelVisualizer.this.windows.values()) {
                                if(!this.filterList.contains(adapter.renderer.element) && !this.danglingElementsPanel.isDangling(adapter.renderer.element)) {
                                    adapter.renderer.paint(graphics);
                                }
                            }
                        }
                    }
                    // ----- paint doors and windows -----
                }
                // ---------- paint places ----------

                if(!this.areaEditMode) {
                    // ++++++++++ paint users ++++++++++
                    if(!ContextModelVisualizer.this.usersFilter) {
                        graphics.setColor(USER_COLOR);

                        for(final VectorAdapter<User> adapter : ContextModelVisualizer.this.users.values()) {
                            if(isElementVisible(adapter.renderer.element, currentValidPlaces)) {
                                adapter.renderer.paint(graphics);
                            }
                        }
                    }
                    // ---------- paint users ----------

                    // ++++++++++ paint localization tags ++++++++++
                    if(!ContextModelVisualizer.this.lTagsFilter) {
                        graphics.setColor(LOCALIZATION_TAG_COLOR);

                        for(final VectorAdapter<LocalizationTag> adapter : ContextModelVisualizer.this.tags.values()) {
                            if(isElementVisible(adapter.renderer.element, currentValidPlaces)) {
                                adapter.renderer.paint(graphics);
                            }
                        }
                    }
                    // ---------- paint localization tags ----------

                    // ++++++++++ paint devices ++++++++++
                    if(!this.filterList.contains(ElementType.DEVICES)) {
                        // +++++ paint physical devices -----
                        for(final VectorAdapter<PhysicalDevice> adapter : ContextModelVisualizer.this.physicalDevices.values()) {
                            if(isElementVisible(adapter.renderer.element, currentValidPlaces)) {
                                if(adapter.renderer.element.getParentDevice() != null) {
                                    if(isElementVisible(adapter.renderer.element.getParentDevice(), currentValidPlaces)) {
                                        adapter.renderer.paint(graphics);
                                    }
                                } else {
                                    // paint element if parent element is not
                                    // set
                                    adapter.renderer.paint(graphics);
                                }
                            }
                        }
                        // ----- paint physical devices -----

                        // +++++ paint interaction resources +++++
                        for(final VectorAdapter<InteractionResource> adapter : ContextModelVisualizer.this.irs.values()) {
                            if(isElementVisible(adapter.renderer.element, currentValidPlaces)) {
                                adapter.renderer.paint(graphics);
                                // if(adapter.renderer.element.getDevice() !=
                                // null)
                                // {
                                // if(isElementVisible(adapter.renderer.element.getDevice(),
                                // validPlaces)) {
                                // LOG.debug("parent device is visible: " +
                                // adapter.renderer.element.getDevice() +
                                // " \nplace: " +
                                // adapter.renderer.element.getDevice().getPlace());
                                // adapter.renderer.paint(graphics);
                                // } else {
                                // LOG.warn("parent device is not visible: " +
                                // adapter.renderer.element.getDevice() +
                                // " \nplace: " +
                                // adapter.renderer.element.getDevice().getPlace());
                                // }
                                // } else {
                                // // paint element if parent element is not set
                                // adapter.renderer.paint(graphics);
                                // }
                            }
                        }
                        // ----- paint interaction resources -----
                    }
                    // ---------- paint devices ----------
                }
                // +++++ draw paths +++++
                int i = 0;

                if((ContextModelVisualizer.this.paths != null) && (ContextModelVisualizer.this.showPaths)) {
                    for(final Collection<Path> list : ContextModelVisualizer.this.paths.values()) {
                        i = i + 80;

                        if(i > 255) {
                            i = 28;
                        }

                        for(final Path path : list) {
                            // Line of the path
                            final float[] patronSegmento = {
                                    (float)(ContextModelVisualizer.this.scale * 20),
                                    (float)ContextModelVisualizer.this.scale * 20,
                                    (float)ContextModelVisualizer.this.scale * 20,
                                    (float)ContextModelVisualizer.this.scale * 20};
                            final int size = (int)(ContextModelVisualizer.this.scale * 5);
                            ((Graphics2D)g).setStroke(new BasicStroke(size, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5, patronSegmento, 2));

                            g.setColor(new Color(0 + i, 0 + i, 255 - i));
                            g.drawLine((int)((path.getInitialX() + ContextModelVisualizer.this.xOffset) * ContextModelVisualizer.this.scale), (int)((getCanvasHeight() - ((path.getInitialY() + ContextModelVisualizer.this.yOffset) * ContextModelVisualizer.this.scale))), (int)((path.getTargetX() + ContextModelVisualizer.this.xOffset) * ContextModelVisualizer.this.scale), (int)((getCanvasHeight() - ((path.getTargetY() + ContextModelVisualizer.this.yOffset) * ContextModelVisualizer.this.scale))));
                            // Starting time of the path
                            g.setColor(Color.black);
                            g.drawString(String.valueOf(path.getStartTime()), (int)((path.getInitialX() + ContextModelVisualizer.this.xOffset) * ContextModelVisualizer.this.scale), (int)((getCanvasHeight() - ((path.getInitialY() + ContextModelVisualizer.this.yOffset) * ContextModelVisualizer.this.scale))));
                        }
                    }

                    ((Graphics2D)g).setStroke(new BasicStroke());
                }
                // ----- draw paths -----
            }
        }
        catch(final Exception e) {
            LOG.error("Exception while painting", e);
            e.printStackTrace();
        }
    }// paint

    @Override
    public void setAreaEditMode(final boolean areaEditMode) {

        boolean repaint = false;

        if(this.areaEditMode != areaEditMode) {
            repaint = true;
        }

        this.areaEditMode = areaEditMode;

        if(this.areaEditMode) {
            this.usersFilter = true;
            this.lTagsFilter = true;
            this.danglingElementsPanel.setVisible(false);
        } else {
            this.usersFilter = false;
            this.lTagsFilter = false;
            this.danglingElementsPanel.setVisible(true);
        }

        if(repaint) {
            this.initDanglingPanel();
            this.scale();
            super.doLayout();
            super.repaint();
        }
    }// setAreaEditMode

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBounds(final int x, final int y, final int width, final int height) {
        super.setBounds(x, y, width, height);
        // if(LOG.isDebugEnabled()) {
        // LOG.debug("setBounds " + x + " " + y + " " + width + " " + height,
        // new Exception("trace"));
        // }
        super.doLayout();
        scale();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnvironment(final Environment newEnvironment) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("setEnvironment " + newEnvironment);
        }
        this.environment = newEnvironment;
    }

    @Override
    public void showPath(final String elementId, final ElementPath elementPath) {

        this.paths.put(elementId, elementPath.getPaths());
        repaint();

    }

    @Override
    public void showPaths() {
        this.showPaths = true;
        repaint();
    }

    @Override
    public void startPlayMode() {
        this.playMode = true;
    }

    @Override
    public void startRecordMode() {
        this.recordMode = true;
    }

    @Override
    public void stopPlayMode() {
        this.playMode = false;
    }

    @Override
    public void stopRecordMode() {
        this.recordMode = false;

    }

    private void addAssistantsInfo(final List<String> userInfo, String tittle, final List<Assistant> assistantsList, final String defaultInfo, final boolean first, final Graphics g) {
        String assistants = "";
        final FontMetrics fm = g.getFontMetrics(g.getFont());
        int cuts = 0;
        for(final Assistant assistant : assistantsList) {
            assistants = assistants + (assistant.getName()) + ", ";
            if(fm.stringWidth(assistants + tittle) > (getWidth() - 400)) {
                assistants = assistants.substring(0, assistants.length() - 2);
                // addInfo(userInfo, tittle, assistants, "----", true, g);

                if(cuts == 0) {
                    final int tittleLenght = tittle.length();
                    addInfo(userInfo, tittle, assistants, "----", true, g);
                    tittle = " ";
                    for(int i = 0; i < tittleLenght; i++) {
                        tittle = tittle + "  ";
                    }
                    cuts++;
                } else {
                    addInfo(userInfo, tittle, assistants, "----", false, g);
                    cuts++;
                }
                assistants = "";
            }
            // Si supera 1 vez, hay que hacer ya addInfo con el titulo
            // Si supera segunda vez, no
        }
        if(assistants.length() > 1) {
            assistants = assistants.substring(0, assistants.length() - 2);
        }

        if(cuts == 0) {

            addInfo(userInfo, tittle, assistants, "----", true, g);

        } else {
            if(assistants.length() > 0) {
                addInfo(userInfo, tittle, assistants, "----", false, g);
            }
        }
    }// addAssistantsInfo

    private void addInfo(final List<String> irInfo, String title, final Object info, final String defaultInfo, final boolean first, final Graphics g) {
        String infoString = null;
        if(info != null) {
            infoString = info.toString();
        }
        // calculo si va ser un exceso, pero claro, si este separado por comas,
        // tendre que cortar por comas, un jaleo
        if(first) {
            title = "> " + title;
        }

        if((infoString == null) || (infoString.length() == 0)) {
            irInfo.add(title + defaultInfo);
        } else {
            irInfo.add(title + infoString);
        }
    }

    private void addSpecificIrInfo(final InteractionResource ir, final List<String> irInfo, final Graphics g) {
        if(ir instanceof Display) {
            addInfo(irInfo, "Display: ", ((Display)ir).getXPixels() + "x" + ((Display)ir).getYPixels(), "----", true, g);
        }
    }

    /**
     * Puts all {@link ElementWithPosition} into a list to move them after
     * moving an {@link Area}.
     * 
     * @param place
     *            The {@link Place} of the {@link Area}.
     * @param area
     *            The {@link Area} to move.
     */
    private void applyElementsToDrag(final Place place, final Area area) {
        for(final ElementWithPosition element : place.getElements()) {
            if((element.getPosition() != null) && area.contains(element.getPosition())) {
                this.draggedElements.add(element);
            }
        }
    }// applyElementsToDrag

    /**
     * Checks if an identifier of one element was selected to be moved to the
     * current mouse location.
     * 
     * @param e
     *            The {@link MouseEvent} with the current coordinates
     * @param drop
     *            Indicated whether an element was dropped (<code>true</code>)
     *            or not (<code>false</code>).
     */
    private void checkMovement(final MouseEvent e, final boolean drop) {
        final Point point = getLocation(e);

        // translate point if event comes from FloorPanel
        // if(e.getSource() instanceof FloorPanel) {
        // point.translate(-this.actualFloorPanel.getLocation().x,
        // -this.actualFloorPanel.getLocation().y);
        // point.translate(-this.floorTabs.getLocation().x,
        // -this.floorTabs.getLocation().y);
        // // if(LOG.isDebugEnabled()) {
        // // LOG.debug(toString(this.actualFloorPanel.getLocation()));
        // // }
        // }

        // move one single element
        if(this.draggedElement != null) {
            // if(LOG.isDebugEnabled()) {
            // LOG.debug("source " + e.getSource());
            // }

            if(drop) {
                double x = 0, y = 0, z = 0;

                if(e.getSource() == this.danglingElementsPanel) {
                    // this.draggingOffset = new Point((int)(-5 * this.scale),
                    // (int)(-10 * this.scale));
                }

                try {
                    if(this.draggingOffset != null) {
                        x = this.environmentPointing.x + this.draggingOffset.x;
                        y = this.environmentPointing.y + this.draggingOffset.y;
                        z = this.draggedElement.getPosition().getZ();
                    } else {
                        x = this.environmentPointing.x;
                        y = this.environmentPointing.y;
                        z = this.draggedElement.getPosition().getZ();
                    }
                }
                catch(final Exception exception) {
                    LOG.error("exception " + this.draggedElement, exception);
                }

                boolean updateZ = false;

                if(this.danglingElementsPanel.isDangling(this.draggedElement)) {
                    updateZ = true;
                } else if(this.draggedElement.getPlace() != null) {
                    final Vector vector = ContextFactory.eINSTANCE.createVector();
                    vector.setCoordinates(x, y, z);

                    if(!this.draggedElement.getPlace().contains(vector)) {
                        updateZ = true;
                    }
                } else {
                    updateZ = true;
                }

                if(updateZ) {
                    z = getZForProjection(x, y);
                }// updateZ

                this.draggedElement.setPosition(x, y, z);
            }// if drop
        }// if

        // move a whole Place or Area
        if(this.draggedArea != null) {
            if(this.draggedArea instanceof Area) {
                // ++++++++++ move one Area of a Place ++++++++++
                final Place place = EnvironmentUtility.getPlaceOfArea(this.environment, (Area)this.draggedArea);

                // the old z-coordinates
                final double originZOld = ((Area)this.draggedArea).getOrigin().getZ();

                if(this.dragPointType == null) {
                    LOG.warn("could not get DragPointType from id: " + this.objectToMove);
                    return;
                }

                double originX = Double.NaN;
                double originY = Double.NaN;
                double spanX = Double.NaN;
                double spanY = Double.NaN;

                // modify position and span of Area according to the drag-point
                // and the mouse movement
                switch(this.dragPointType) {
                    case CENTER:
                        // ((Area)this.draggedArea).setNewOrigin((e.getPoint().x
                        // / this.scale) - this.xOffset + this.draggingOffset.x,
                        // ((getCanvasHeight() - e.getPoint().y) / this.scale) -
                        // this.yOffset + this.draggingOffset.y, originZ);
                        ((Area)this.draggedArea).setNewOrigin(this.environmentPointing.x + this.draggingOffset.x, this.environmentPointing.y + this.draggingOffset.y, originZOld);
                        break;
                    case NE:
                        spanX = (this.environmentPointing.x + this.draggingOffset.x) - this.lastPosition.getX();
                        spanY = (this.environmentPointing.y + this.draggingOffset.y) - this.lastPosition.getY();
                        break;
                    case NW:
                        originX = this.environmentPointing.x + this.draggingOffset.x;
                        originY = this.lastPosition.getY();
                        spanX = this.lastSpan.getX() + (this.lastPosition.getX() - (this.environmentPointing.x + this.draggingOffset.x));
                        spanY = (this.environmentPointing.y + this.draggingOffset.y) - this.lastPosition.getY();
                        break;
                    case SE:
                        originX = this.lastPosition.getX();
                        originY = this.environmentPointing.y + this.draggingOffset.y;
                        spanX = (this.environmentPointing.x + this.draggingOffset.x) - this.lastPosition.getX();
                        spanY = this.lastSpan.getY() + (this.lastPosition.getY() - (this.environmentPointing.y + this.draggingOffset.y));
                        break;
                    case SW:
                        originX = this.environmentPointing.x + this.draggingOffset.x;
                        originY = this.environmentPointing.y + this.draggingOffset.y;
                        spanX = this.lastSpan.getX() + (this.lastPosition.getX() - (this.environmentPointing.x + this.draggingOffset.x));
                        spanY = this.lastSpan.getY() + (this.lastPosition.getY() - (this.environmentPointing.y + this.draggingOffset.y));
                        break;
                }// switch

                // +++++ check whether span is new +++++
                if(!Double.isNaN(spanX) && !Double.isNaN(spanY)) {
                    // check for non negative span
                    if(!((((Area)this.draggedArea).getSpan().getX() == 0) && (((Area)this.draggedArea).getSpan().getY() == 0) && ((spanX < 0) || (spanY < 0)))) {

                        // check for equal signs of old and new span
                        if(Math.signum(((Area)this.draggedArea).getSpan().getX()) == Math.signum(spanX)) {
                            // equal signs => no folding

                            ((Area)this.draggedArea).setNewSpan(spanX, spanY, ((Area)this.draggedArea).getSpan().getZ());

                            if(!Double.isNaN(originX) && !Double.isNaN(originY)) {
                                ((Area)this.draggedArea).setNewOrigin(originX, ((Area)this.draggedArea).getSpan().getY(), ((Area)this.draggedArea).getOrigin().getZ());
                            }
                        }

                        if(Math.signum(((Area)this.draggedArea).getSpan().getY()) == Math.signum(spanY)) {
                            ((Area)this.draggedArea).setNewSpan(((Area)this.draggedArea).getSpan().getX(), spanY, ((Area)this.draggedArea).getSpan().getZ());

                            if(!Double.isNaN(originX) && !Double.isNaN(originY)) {
                                ((Area)this.draggedArea).setNewOrigin(originX, originY, ((Area)this.draggedArea).getOrigin().getZ());
                            }
                        }
                    } else {
                        if(LOG.isDebugEnabled()) {
                            LOG.debug("avoid negative span");
                        }
                    }
                }// if
                 // ----- check whether span is new -----

                // +++++ move all containing elements if Area was dropped +++++
                if((this.dragPointType == DragPointType.CENTER) && drop) {

                    if(LOG.isDebugEnabled()) {
                        LOG.debug(new StringBuilder("area dropped => move elements, id: ").append(this.objectToMove).append(" place: ").append(place).toString());
                    }

                    // move all containing elements
                    if(place != null) {
                        if(place.getAreas().size() == 1) {
                            // move all elements of the place
                            this.moveWholePlace(place, this.environmentPointing);
                        } else {
                            // move only the elements on the area
                            this.moveDraggedElements(this.environmentPointing);
                        }

                        if(LOG.isDebugEnabled()) {
                            LOG.debug("fixEnvironment");
                        }

                        this.initDanglingPanel();

                        this.draggedElements = null;
                    }// if
                }// if
                 // ----- move all containing elements if Area was dropped -----
                if(drop) {
                    // this.assignElementsToPlace();
                    if(this.draggedElements != null) {
                        for(final ElementWithPosition elementWithPosition : this.draggedElements) {
                            if(!place.contains(elementWithPosition.getPosition())) {
                                elementWithPosition.setPlace(null);
                            }
                        }
                    }
                }
                // ---------- move one Area of a Place ----------
            } else if(this.draggedArea instanceof Place) {
                // ++++++++++ move a whole Place with all its Areas ++++++++++
                final Place place = (Place)this.draggedArea;
                // the new reference position
                final Point pos = this.getPositionOnEnvironment(point);

                // the old z-coordinate
                double originZOld;

                int i = 0;

                // move all Areas
                for(final Vector vector : this.lastPositions) {
                    originZOld = place.getAreas().get(i).getOrigin().getZ();

                    place.getAreas().get(i).setNewOrigin(((pos.x + this.draggingOffset.x) - this.lastPosition.getX()) + vector.getX(), ((pos.y + this.draggingOffset.y) - this.lastPosition.getY()) + vector.getY(), originZOld);
                    i++;
                }// for

                // +++++ move all containing elements +++++
                if(drop) {
                    if(LOG.isDebugEnabled()) {
                        LOG.debug(new StringBuilder("place dropped => move elements, id: ").append(this.objectToMove).append(" place: ").append(this.draggedArea).toString());
                    }

                    this.moveWholePlace(place, pos);
                    this.draggedElements = null;

                    if(LOG.isDebugEnabled()) {
                        LOG.debug("fixEnvironment");
                    }

                    if(this.draggedElements != null) {
                        for(final ElementWithPosition elementWithPosition : this.draggedElements) {
                            if(!place.contains(elementWithPosition.getPosition())) {
                                elementWithPosition.setPlace(null);
                            }
                        }
                    }
                    this.initDanglingPanel();
                }// if
                 // ----- move all containing elements -----
                 // ---------- move a whole Place with all its Areas ----------
            }// else

            if(drop) {
                this.draggedArea = null;
                this.scale();
            }
        }// if

        this.thereWasMovement = false;
    }// checkMovement

    private void checkMovementMouseReleased(final MouseEvent e) {
        if((e.getButton() == 1) && (this.thereWasMovement)) {
            this.checkMovement(e, true);
        }// if
    }// checkMovemenMouseReleased

    /**
     * Checks if an identifier of one element was selected to be moved to the
     * current mouse location and must be recorded for a path.
     * 
     * @param e
     *            The {@link MouseEvent}.
     */
    private void checkRecord(final MouseEvent e) {
        final Point point = e.getPoint();

        // translate point if event comes from FloorPanel
        if(e.getSource() instanceof FloorPanel) {
            // point.translate(this.actualFloorPanel.getLocation().x,
            // this.actualFloorPanel.getLocation().y);
        }

        if((this.thereWasMovement) && (!this.playMode) && (this.draggedArea == null)) {
            final int targetX = (Double.valueOf((point.x / this.scale) - this.xOffset)).intValue();
            final int targetY = (Double.valueOf(((getCanvasHeight() - point.y) / this.scale) - this.yOffset)).intValue();

            if(this.objectToMove != null && this.objectToMove instanceof EnvironmentElement && this.recordMode) {
                this.manager.setPath(((EnvironmentElement)this.objectToMove).getId(), targetX, targetY);
            }
        }
    }

    private boolean containsProjection(final Area area, final double x, final double y) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("containsProjection " + area + " " + x + " " + y);
        }

        if(area.getSpan().getX() > 0) {
            if(area.getOrigin().getX() <= x && x <= area.getOrigin().getX() + area.getSpan().getX()) {
                // all OK
            } else {
                return false;
            }
        } else {
            if(area.getOrigin().getX() + area.getSpan().getX() <= x && x <= area.getOrigin().getX()) {
                // all OK
            } else {
                return false;
            }
        }

        if(area.getSpan().getY() > 0) {
            if(area.getOrigin().getY() <= y && y <= area.getOrigin().getY() + area.getSpan().getY()) {
                // all OK
            } else {
                return false;
            }
        } else {
            if(area.getOrigin().getY() + area.getSpan().getY() <= y && y <= area.getOrigin().getY()) {
                // all OK
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * Creates an area in relation to the
     * {@link ContextModelVisualizer#currentFloorPanel}.
     * 
     * @param element
     *            The element.
     * @param x
     *            The x-coordinate of the element.
     * @param y
     *            The y-coordinate of the element.
     * @param width
     *            The width of the element.
     * @param height
     *            The height of the element.
     */
    private void createArea(final Object element, final int x, final int y, final int width, final int height) {
        this.selectionAreas.createArea(element, x + this.floorTabs.getLocation().x + this.currentFloorPanel.getLocation().x, y + this.floorTabs.getLocation().y + this.currentFloorPanel.getLocation().y, width, height);
    }

    /**
     * Draws the pop up of a <code>Door</code>.
     */
    private void drawDoorPopUp(final Graphics2D graphics, final Point point, final Door door, final int refWidth, final int refHeight) {
        if(door == null) {
            return;
        }

        graphics.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        final BooleanProperty open = door.getOpen();
        final Place source = door.getSource();
        final Place target = door.getTarget();

        // Adding all the information that we want for an Door
        final List<String> doorInfos = new LinkedList<String>();

        // new
        // StringBuilder(door.eClass().getName()).append(" ").append(door.getId()).toString()
        addInfo(doorInfos, "", door.eClass().getName(), "----", false, graphics);

        addInfo(doorInfos, "Name: ", door.getName(), "----", true, graphics);

        if(open != null) {
            addInfo(doorInfos, "Open: ", open.getValue(), "----", true, graphics);
        }

        if(source != null) {
            addInfo(doorInfos, "Source: ", source.getName(), "----", true, graphics);
        }

        if(target != null) {
            addInfo(doorInfos, "Target: ", target.getName(), "----", true, graphics);
        }

        final String[] lines = new String[doorInfos.size()];
        doorInfos.toArray(lines);
        drawPopUp(graphics, point, refWidth, refHeight, lines);
    }// drawDoorPopUp

    /**
     * Draws the coordinates of an {@link Area} or {@link Place} according to
     * the current {@link DragPointType}.
     */
    private void drawDragPointCoordinates(final Graphics g) {
        if(this.draggedArea instanceof Area) {
            this.drawDragPointCoordinates(g, (Area)this.draggedArea, this.dragPointType);
        } else if(this.draggedArea instanceof Place) {
            for(final Area area : ((Place)this.draggedArea).getAreas()) {
                this.drawDragPointCoordinates(g, area, this.dragPointType);
            }
        }
    }// drawDragPointCoordinates

    /**
     * Draws the coordinates of the area according to the current
     * {@link DragPointType}.
     * 
     * @param area
     */
    private void drawDragPointCoordinates(final Graphics g, final Area area, final DragPointType dragPointType) {
        if(dragPointType == DragPointType.CENTER) {
            // drad the coordinates of all corners
            this.drawDragPointCoordinates(g, area, DragPointType.NW);
            this.drawDragPointCoordinates(g, area, DragPointType.NE);
            this.drawDragPointCoordinates(g, area, DragPointType.SW);
            this.drawDragPointCoordinates(g, area, DragPointType.SE);
            return;
        }

        g.setFont(g.getFont().deriveFont(POPUP_FONT_SIZE));
        double x;
        double y;
        Point pos = null;
        String text = null;

        switch(dragPointType) {
            case CENTER:
            case SW:
                pos = this.getPositionOnDisplay(area.getOrigin());
                text = toString2D(area.getOrigin());
                pos.translate((int)(DRAGPOINT_OFFSET * this.scale), (int)(-DRAGPOINT_OFFSET * this.scale));
                break;
            case NE:
                x = area.getOrigin().getX() + area.getSpan().getX();
                y = area.getOrigin().getY() + area.getSpan().getY();
                pos = this.getPositionOnDisplay(x, y);
                text = toLocationString(x, y);
                pos.translate((int)(-DRAGPOINT_OFFSET * this.scale), (int)(DRAGPOINT_OFFSET * this.scale));
                pos.translate(-g.getFontMetrics().stringWidth(text), g.getFontMetrics().getAscent());
                break;
            case NW:
                x = area.getOrigin().getX();
                y = area.getOrigin().getY() + area.getSpan().getY();
                pos = this.getPositionOnDisplay(x, y);
                text = toLocationString(x, y);
                pos.translate((int)(+DRAGPOINT_OFFSET * this.scale), (int)(DRAGPOINT_OFFSET * this.scale));
                pos.translate(0, g.getFontMetrics().getAscent());
                break;
            case SE:
                x = area.getOrigin().getX() + area.getSpan().getX();
                y = area.getOrigin().getY();
                pos = this.getPositionOnDisplay(x, y);
                text = toLocationString(x, y);
                pos.translate((int)(-DRAGPOINT_OFFSET * this.scale), (int)(-DRAGPOINT_OFFSET * this.scale));
                pos.translate(-g.getFontMetrics().stringWidth(text), 0);
                break;
        }// switch

        pos.translate((int)(this.scale * 5), 0);

        if((pos != null) && (text != null)) {
            final Rectangle2D r = g.getFontMetrics().getStringBounds(text, g);
            g.setColor(POPUP_RECTANGLE_COLOR);
            g.fillRect((pos.x + (int)r.getX()) - TEXT_BACKGROUND_BORDER, (pos.y + (int)r.getY()) - TEXT_BACKGROUND_BORDER, (int)r.getWidth() + (2 * TEXT_BACKGROUND_BORDER), (int)r.getHeight() + (2 * TEXT_BACKGROUND_BORDER));
            g.setColor(POPUP_TEXT_COLOR);
            g.drawString(text, pos.x, pos.y);
        }
    }// drawDragPointCoordinates

    private void drawIRPopUp(final Graphics2D graphics, final Point point, final InteractionResource ir, final int refWidth, final int refHeight) {
        if(ir == null) {
            // do nothing
            return;
        }
        graphics.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Adding all the information that we want for a INTERACTION RESOURCE
        final List<String> irInfo = new LinkedList<String>();
        addInfo(irInfo, "", ir.eClass().getName(), "env. element", false, graphics);
        addInfo(irInfo, "Tags: ", getTagsString(ir), "", true, graphics);
        addInfo(irInfo, "Mobile: ", ir.getMobile().toString(), "----", true, graphics);
        addInfo(irInfo, "Personal: ", ir.getPersonal(), "----", true, graphics);
        addInfo(irInfo, "Modality: ", ir.getModality(), "----", true, graphics);
        // Adding specific information that we want for each specific
        // INTERACTION RESOURCE
        addSpecificIrInfo(ir, irInfo, graphics);

        Channel<?, ?> channel;
        for(final EReference reference : ir.eClass().getEAllReferences()) {
            if(ContextPackage.Literals.CHANNEL.isSuperTypeOf(reference.getEReferenceType())) {
                channel = (Channel<?, ?>)ir.eGet(reference);

                if(channel == null) {
                    addInfo(irInfo, reference.getName(), ": not configured", "----", true, graphics);
                } else {
                    addInfo(irInfo, reference.getName(), (channel.isAvailable() ? ": available"
                            : ": not available"), "----", true, graphics);
                }
            }
        }
        final String[] lines = new String[irInfo.size()];
        irInfo.toArray(lines);
        drawPopUp(graphics, point, refWidth, refHeight, lines);
    }

    private void drawLocalizationTagPopUP(final Graphics2D graphics, final Point point, final LocalizationTag tag, final int refWidth, final int refHeight) {
        if(tag == null) {
            // do nothing
            return;
        }
        graphics.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        final List<String> tagInfo = new LinkedList<String>();
        addInfo(tagInfo, "", tag.eClass().getName(), "env. element", false, graphics);
        addInfo(tagInfo, "Tag ID: ", tag.getId(), "----", true, graphics);

        if(tag.getElement() != null) {
            addInfo(tagInfo, "Associated element ID: ", tag.getElement().getId(), "----", true, graphics);
        } else {
            addInfo(tagInfo, "no associated element", null, "", true, graphics);
        }

        final String[] lines = new String[tagInfo.size()];
        tagInfo.toArray(lines);
        drawPopUp(graphics, point, refWidth, refHeight, lines);
    }

    /**
     * Draws the pop up of a <code>Door</code>.
     */
    private void drawPhysicalDevicePopUp(final Graphics2D graphics, final Point point, final PhysicalDevice physicalDevice, final int refWidth, final int refHeight) {
        if(physicalDevice == null) {
            return;
        }

        graphics.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Adding all the information that we want for an Door
        final List<String> infos = new LinkedList<String>();
        addInfo(infos, "", physicalDevice.eClass().getName(), "----", false, graphics);

        addInfo(infos, "Name: ", physicalDevice.getName(), "----", true, graphics);

        Property<?> p;

        for(final EReference propertyReference : AppliancesUtility.getPropertyEReferences(physicalDevice)) {
            p = (Property<?>)physicalDevice.eGet(propertyReference);

            if(p == null) {
                addInfo(infos, propertyReference.getName() + ": ", "unset", "----", true, graphics);
            } else {
                addInfo(infos, propertyReference.getName() + ": ", p.getValue(), "----", true, graphics);
            }
        }

        final String[] lines = new String[infos.size()];
        infos.toArray(lines);
        drawPopUp(graphics, point, refWidth, refHeight, lines);
    }// drawPhysicalDevicePopUp

    private void drawPopUp(final Graphics g, final Point point, final int refWidth, final int refHeight, final String... lines) {
        int lineHeight = (int)POPUP_FONT_SIZE + 3;
        g.setFont(g.getFont().deriveFont(POPUP_FONT_SIZE));
        final int xPointOffset;
        final int yPointOffset;

        FontMetrics fm = g.getFontMetrics(g.getFont());
        int boxWidth = 0;
        int boxHeight = lineHeight * lines.length;
        final Point referencePoint = new Point(point.x, point.y);
        int maxLineLength = 0;
        int i = 0;
        while(i < 10) {
            for(final String line : lines) {
                if(line.length() > maxLineLength) {
                    boxWidth = fm.stringWidth(line) + 30;
                    maxLineLength = line.length();
                }
            }
            if(boxWidth > getWidth()) {
                i++;
                g.setFont(g.getFont().deriveFont(POPUP_FONT_SIZE - i));
                lineHeight = ((int)POPUP_FONT_SIZE - i) + 3;
                fm = g.getFontMetrics(g.getFont());
                maxLineLength = 0;
                boxHeight = lineHeight * lines.length;
            } else {
                break;
            }
        }

        if(point.x > refWidth) {
            // draw pop-up on left hand side of mouse cursor
            referencePoint.x = referencePoint.x - boxWidth;
            xPointOffset = 12;
        } else {
            // draw pop-up on right hand side of mouse cursor
            xPointOffset = 12;
        }

        if(boxHeight > getCanvasHeight()) {
            // Reduce size or change structure
        }

        if(referencePoint.x < getBounds().x) {
            referencePoint.x = getBounds().x + 3;
        }
        if((referencePoint.x + boxWidth) > (getWidth() - 3)) {
            referencePoint.x = getWidth() - 3 - boxWidth;
        }

        if(point.y < refHeight) {
            // draw pop-up below mouse cursor
            referencePoint.y = referencePoint.y + boxHeight;
            yPointOffset = 18;
        } else {
            // drwa pop-up above mouse cursor
            yPointOffset = -23;
        }

        g.setColor(POPUP_RECTANGLE_COLOR);
        g.fillRect(referencePoint.x, (referencePoint.y - boxHeight) + yPointOffset, boxWidth, boxHeight + 4);

        g.setColor(POPUP_RECTANGLE_BORDER_COLOR);
        g.drawRect(referencePoint.x, (referencePoint.y - boxHeight) + yPointOffset, boxWidth, boxHeight + 4);
        g.drawLine(referencePoint.x, ((2 + referencePoint.y) - boxHeight) + yPointOffset + (1 * lineHeight), boxWidth + referencePoint.x, ((4 + referencePoint.y) - boxHeight) + yPointOffset + (1 * lineHeight));

        // draw the headline
        g.setColor(POPUP_HEADLINE_TEXT_COLOR);
        g.drawString(lines[0], referencePoint.x + xPointOffset, (referencePoint.y - boxHeight) + yPointOffset + (1 * lineHeight));

        // draw the other lines
        g.setColor(POPUP_TEXT_COLOR);
        for(i = 1; i < lines.length;) {
            g.drawString(lines[i], referencePoint.x + xPointOffset, (referencePoint.y - boxHeight) + yPointOffset + (++i * lineHeight));
        }
    }

    private void drawUserPopUp(final Graphics2D graphics, final Point point, final User user, final int refWidth, final int refHeight) {
        if(user == null) {
            // do nothing
            return;
        }

        graphics.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Adding all the information that we want for an USER
        final List<String> userInfo = new LinkedList<String>();
        addInfo(userInfo, "", user.eClass().getName() + ": " + user.getName(), "----", false, graphics);
        addInfo(userInfo, "Date of birth: ", user.getBirthDate(), "----", true, graphics);
        addInfo(userInfo, "Adult: ", (user.isAdult() ? "yes" : "no"), "----", true, graphics);
        addInfo(userInfo, "Hand: ", (user.isLeftHanded() ? "Left-handed" : "Right-handed"), "----", true, graphics);
        addInfo(userInfo, "Tags: ", getTagsString(user), "", true, graphics);
        addAssistantsInfo(userInfo, "Current assistants: ", user.getCurrentAssistants(), "----", true, graphics);

        addAssistantsInfo(userInfo, "Past assistants: ", user.getPastAssistants(), "----", true, graphics);

        final String[] lines = new String[userInfo.size()];
        userInfo.toArray(lines);
        drawPopUp(graphics, point, refWidth, refHeight, lines);
    }

    /**
     * Draws the pop up of a <code>Door</code>.
     */
    private void drawWindowPopUp(final Graphics2D graphics, final Point point, final Window window, final int refWidth, final int refHeight) {
        if(window == null) {
            return;
        }

        graphics.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        final BooleanProperty open = window.getOpen();
        final Place source = window.getSource();
        final Place target = window.getTarget();

        // Adding all the information that we want for an Door
        final List<String> doorInfos = new LinkedList<String>();

        // new
        // StringBuilder(door.eClass().getName()).append(" ").append(door.getId()).toString()
        addInfo(doorInfos, "", window.eClass().getName(), "----", false, graphics);

        addInfo(doorInfos, "Name: ", window.getName(), "----", true, graphics);

        if(open != null) {
            addInfo(doorInfos, "Open: ", open.getValue(), "----", true, graphics);
        }

        if(source != null) {
            addInfo(doorInfos, "Source: ", source.getName(), "----", true, graphics);
        }

        if(target != null) {
            addInfo(doorInfos, "Target: ", target.getName(), "----", true, graphics);
        }

        final String[] lines = new String[doorInfos.size()];
        doorInfos.toArray(lines);
        drawPopUp(graphics, point, refWidth, refHeight, lines);
    }

    private int getCanvasHeight() {
        if(this.currentFloorPanel != null) {
            return this.currentFloorPanel.getHeight();
        } else {
            return -1;
        }
    }

    private Object getElement(final LinkedList<Object> ids) {
        if(ids.size() > 1) {
            Collections.sort(ids, this.elementComparator);
        }

        if(ids.isEmpty()) {
            return null;
        } else {
            return ids.getFirst();
        }
    }

    /**
     * Returns a {@link List} of {@link Integer}s representing the floor
     * numbers.
     * 
     * @return {@link List} of floor numbers as {@link Integer}.
     */
    private List<Integer> getFloors() {
        final LinkedList<Integer> floors = new LinkedList<Integer>();

        boolean addNull = false;

        for(final Place place : this.environment.getPlaces()) {
            // append each number only once
            if(place.getFloor() == null) {
                addNull = true;
            } else if(place.getFloor() != null && !floors.contains(place.getFloor())) {
                floors.add(place.getFloor());
            }
        }

        Collections.sort(floors);

        if(addNull || floors.size() == 0) {
            floors.addFirst(null);
        }

        return floors;
    }

    /**
     * Returns the location relative to the {@link ContextModelVisualizer}
     * {@link JPanel}.
     * 
     * @param e
     * @return The location of the {@link MouseEvent} relative to the
     *         {@link ContextModelVisualizer} {@link JPanel}.
     */
    private Point getLocation(final MouseEvent e) {
        final Point point = e.getPoint();

        if(e.getSource() == this) {
            // nothing to do
        } else if(e.getSource() == this.currentFloorPanel) {
            point.translate(this.floorTabs.getLocation().x, this.floorTabs.getLocation().y);
            point.translate(this.currentFloorPanel.getLocation().x, this.currentFloorPanel.getLocation().y);
        } else if(e.getSource() == this.danglingElementsPanel) {
            point.translate(this.danglingElementsPanel.getLocation().x, this.danglingElementsPanel.getLocation().y);
        } else {
            LOG.warn("invalid source of envent " + e);
        }

        return point;
    }

    /**
     * Returns the position of an element in screen coordinates.
     * 
     * @param x
     *            The x-coordinate of the point.
     * @param y
     *            The y-coordinate of the point.
     * @return The position in screen coordinates.
     */
    private Point getPositionOnDisplay(final double x, final double y) {
        final Point position = new Point((int)((x + this.xOffset) * this.scale),//
        (int)(this.getCanvasHeight() - ((y + this.yOffset) * this.scale)));
        position.translate(-this.floorTabs.getLocation().x, this.floorTabs.getLocation().y);
        position.translate(-this.currentFloorPanel.getLocation().x, this.currentFloorPanel.getLocation().y);

        return position;
    }

    /**
     * Returns the position of an element in screen coordinates.
     * 
     * @param vector
     *            The {@link Vector} to get the position from.
     * @return The position in screen coordinates.
     */
    private Point getPositionOnDisplay(final Vector vector) {
        if(vector != null) {
            final Point position = new Point((int)((this.xOffset + vector.getX()) * this.scale),//
            getCanvasHeight() - (int)((this.yOffset + vector.getY()) * this.scale));
            position.translate(-this.floorTabs.getLocation().x, this.floorTabs.getLocation().y);
            position.translate(-this.currentFloorPanel.getLocation().x, this.currentFloorPanel.getLocation().y);

            return position;
        }

        return null;
    }

    /**
     * Converts a screen location to an environment location.
     * 
     * @param point
     * @return A {@link Point} representing a position on the
     *         {@link Environment}.
     */
    private Point getPositionOnEnvironment(final Point point) {
        return new Point((int)(point.x / this.scale) - this.xOffset,//
        (int)((super.getHeight() - point.y) / this.scale) - this.yOffset);
    }

    /**
     * Returns the {@link EnvironmentElementRenderer} for a given
     * {@link EnvironmentElement}. If the element has no renderer (i.e. it has
     * no physical representation) then the result will be <code>null</code>.
     * 
     * @param environmentElement
     *            The {@link EnvironmentElement} to get the renderer for.
     * @return The {@link EnvironmentElementRenderer} of the
     *         {@link EnvironmentElement}.
     * @since 1.3.8
     */
    private EnvironmentElementRenderer<?> getRenderer(final EnvironmentElement environmentElement) {
        VectorAdapter<?> adapter = null;

        if(environmentElement instanceof PhysicalDevice) {
            adapter = this.physicalDevices.get(environmentElement);
        } else if(environmentElement instanceof User) {
            adapter = this.users.get(environmentElement);
        } else if(environmentElement instanceof Door) {
            adapter = this.doors.get(environmentElement);
        } else if(environmentElement instanceof Window) {
            adapter = this.windows.get(environmentElement);
        } else if(environmentElement instanceof InteractionResource) {
            adapter = this.irs.get(environmentElement);
        } else if(environmentElement instanceof LocalizationTag) {
            adapter = this.tags.get(environmentElement);
        } else if(environmentElement instanceof Place) {
            adapter = this.places.get(environmentElement);
        }

        if(adapter != null) {
            return adapter.renderer;
        }

        return null;
    }

    private LinkedList<Place> currentValidPlaces;

    /**
     * Updates the {@link ContextModelVisualizer#currentValidPlaces}
     * {@link List} of valid {@link Place}s to be rendered in relation to the
     * current displayed floor.
     * 
     * @return A {@link List} of valid {@link Place}s to be rendered.
     */
    private void updateCurrentValidPlaces() {
        if(currentValidPlaces == null) {
            this.currentValidPlaces = new LinkedList<Place>();
        }

        this.currentValidPlaces.clear();

        if(this.currentFloorPanel == null) {
            return;
        }

        for(final Place place : this.environment.getPlaces()) {
            if(this.currentFloorPanel.getFloor() == null) {
                if(place.getFloor() == null) {
                    currentValidPlaces.add(place);
                }
            } else if(this.currentFloorPanel.getFloor().equals(place.getFloor())) {
                currentValidPlaces.add(place);
            }
        }
    }

    /**
     * Returns a valid z-coordinate for a given projection. All currently valid
     * {@link Place}s and all {@link Area}s of them are checked fo find an
     * {@link Area} which contains the projection. The valid {@link Place}s are
     * taken from {@link ContextModelVisualizer#updateCurrentValidPlaces()} in
     * ralation to the {@link ContextModelVisualizer#currentFloorPanel}. The
     * z-coordinate will be the z-coordinate of the origin of the matched
     * {@link Area}.
     * 
     * @param x
     *            The x-coordinate of the projection
     * @param y
     *            The y-coordinate of the projection
     * @return A valid z-coordinate of the given cordinates.
     */
    private double getZForProjection(final double x, final double y) {
        double z = Integer.MIN_VALUE;

        // look for actual place and set z-coordinate
        updateCurrentValidPlaces();

        if(LOG.isDebugEnabled()) {
            LOG.debug("look for place in " + getIds(currentValidPlaces));
        }

        for(final Place place : currentValidPlaces) {
            for(final Area area : place.getAreas()) {
                if(containsProjection(area, x, y)) {
                    // adopt z-coordinate to the current pointed
                    // area
                    z = area.getOrigin().getZ();
                }
            }
        }

        return z;
    }

    /**
     * 
     */
    private void initComponentsAndLayout() {
        this.setBackground(BACKGROUND_COLOR);

        super.removeAll();

        if(this.environment != null) {
            initFloorTabs();
        }

        super.setLayout(new BorderLayout());

        if(this.floorTabs != null) {
            super.add(this.floorTabs, BorderLayout.CENTER);
        }

        this.initDanglingPanel();
    }

    /**
     * 
     */
    private void initDanglingPanel() {
        if(this.danglingElementsPanel == null) {
            this.danglingElementsPanel = new DanglingElementsPanel();
        }

        if(LOG.isDebugEnabled()) {
            LOG.debug(this.danglingElementsPanel.getDanglingString());
        }

        if(this.danglingElementsPanel.getElementCount() > 0) {
            if(!super.isAncestorOf(this.danglingElementsPanel)) {
                if(LOG.isDebugEnabled()) {
                    LOG.debug("add danglingElementsPanel");
                }

                super.add(this.danglingElementsPanel, BorderLayout.EAST);
            }
        } else {
            if(LOG.isDebugEnabled()) {
                LOG.debug("remove danglingElementsPanel");
            }

            super.remove(this.danglingElementsPanel);
        }

        super.doLayout();

        if(this.floorTabs != null) {
            this.floorTabs.doLayout();
        }

        this.scale();
        this.danglingElementsPanel.doLayout();
        super.repaint();
    }

    /**
     * Initializes some observers.
     */
    private void initObservers() {
        synchronized(this.disposeables) {
            this.disposeables.add(ListAdapterUtility.observe(this.environment, ContextPackage.Literals.ENVIRONMENT__PROVIDERS, ContextProvider.class, new ProviderObserver()));
            this.disposeables.add(ListAdapterUtility.observe(this.environment, ContextPackage.Literals.ENVIRONMENT__PLACES, Place.class, new PlaceObserver()));
            this.disposeables.add(ListAdapterUtility.observe(this.environment, ContextPackage.Literals.ENVIRONMENT__USERS, User.class, new UsersObserver()));
            this.disposeables.add(ListAdapterUtility.observe(this.environment, ContextPackage.Literals.ENVIRONMENT__DEVICES, Device.class, new DevicesObserver()));
            this.disposeables.add(ListAdapterUtility.observe(this.environment, ContextPackage.Literals.ENVIRONMENT__DEVICES, Device.class, new PhysicalDeviceObserver()));
        }
    }

    /**
     * Initializes the tabs for the {@link ContextModelVisualizer#floorTabs} in
     * relation to the floors at the {@link Environment}.
     */
    private void initFloorTabs() {
        if(LOG.isDebugEnabled()) {
            LOG.debug("initFloorTabs " + this.getFloors());
        }

        Integer oldFloor = null;

        if(this.currentFloorPanel != null) {
            oldFloor = this.currentFloorPanel.getFloor();
        }

        this.floorTabs = new JTabbedPane();
        int index = 0;

        for(final Integer floor : this.getFloors()) {
            this.floorTabs.add(floor == null ? UNKNOWN_FLOOR_TAB_TEXT : floor.toString(), new FloorPanel(floor));

            if(floor != null && floor.equals(oldFloor)) {
                this.floorTabs.setSelectedIndex(index);
            }

            index++;
        }

        ContextModelVisualizer.this.currentFloorPanel = (FloorPanel)this.floorTabs.getSelectedComponent();

        this.floorTabs.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(final ChangeEvent e) {
                final JTabbedPane pane = (JTabbedPane)e.getSource();
                ContextModelVisualizer.this.currentFloorPanel = (FloorPanel)pane.getSelectedComponent();
            }
        });
    }

    /**
     * Indicates whether an {@link ElementWithPosition} is visible or not. (In
     * the meaning of normal rendering, so that currently dragged elements are
     * not visible.)
     * 
     * @param element
     *            The {@link EnvironmentElement} to check.
     * @param validPlaces
     *            A {@link List} of valid {@link Place}s.
     * @return <code>true</code> if element is visible, <code>false</code>
     *         otherwise.
     */
    private boolean isElementVisible(final ElementWithPosition element, final List<Place> validPlaces) {
        // if(LOG.isDebugEnabled()) {
        // StringBuilder sb = new StringBuilder();
        // sb.append("isElementVisible ");
        // sb.append(isElementVisible(adapter.renderer.element, validPlaces));
        // sb.append(": ");
        // sb.append(adapter.renderer.element);
        // sb.append(" \nplace: ");
        // sb.append(adapter.renderer.element.getPlace());
        // sb.append(" \nvalidPlaces: ");
        // sb.append(validPlaces);
        // LOG.debug(sb);
        // }

        final boolean debug = false;

        if(this.draggedElement == element) {
            if(debug) {
                LOG.debug("element is dragged " + element);
            }
            return false;
        } else if(this.filterList.contains(element)) {
            if(debug) {
                LOG.debug("element is filtered " + element);
            }
            return false;
        } else if(this.danglingElementsPanel.isDangling(element)) {
            if(debug) {
                LOG.debug("element is dangling " + element);
            }
            return false;
        } else if(element.getPlace() != null && this.filterList.contains(element.getPlace())) {
            if(debug) {
                LOG.debug("place of element is filtered " + element);
            }
            return false;
        } else if(element.getPlace() != null && validPlaces.contains(element.getPlace())) {
            if(debug) {
                LOG.debug("element is visible at place " + element.getPlace().getId() + " " + element);
            }
            return true;
        } else if((element.getPlace() == null && this.currentFloorPanel.getFloor() == null)) {
            if(debug) {
                LOG.debug("element is visible too " + element);
            }
            return true;
        }

        if(element.getPosition() != null) {
            for(final Place place : validPlaces) {
                if(place.contains(element.getPosition())) {
                    if(debug) {
                        LOG.info("element is visible (second) " + element);
                    }
                    return true;
                }
            }
        }

        if(debug) {
            LOG.debug("element is not visible " + element);
        }
        return false;
    }

    private void loadRoomTextures() {
        // final InputStream image1 =
        // getClass().getClassLoader().getResourceAsStream(OFFICE_TEXTURE_PATH);
        // final InputStream image2 =
        // getClass().getClassLoader().getResourceAsStream(BEDROOM_TEXTURE_PATH);
        // final InputStream image3 =
        // getClass().getClassLoader().getResourceAsStream(FITNESS_TEXTURE_PATH);
        // final InputStream image4 =
        // getClass().getClassLoader().getResourceAsStream(KITCHEN_TEXTURE_PATH);
        // final InputStream image5 =
        // getClass().getClassLoader().getResourceAsStream(BATH_TEXTURE_PATH);
        // final InputStream image6 =
        // getClass().getClassLoader().getResourceAsStream(LIVING_TEXTURE_PATH);
        // try {
        // this.officeImageTexture = ImageIO.read(image1);
        // this.bedroomImageTexture = ImageIO.read(image2);
        // this.fitnessImageTexture = ImageIO.read(image3);
        // this.kitchenImageTexture = ImageIO.read(image4);
        // this.bathImageTexture = ImageIO.read(image5);
        // this.livingImageTexture = ImageIO.read(image6);
        // }
        // catch(final IOException e) {
        // e.printStackTrace();
        // }

    }

    /**
     * Moves the containing elements of an {@link Area}.
     * 
     * @param shift
     *            The shift to move the elements.
     */
    private void moveDraggedElements(final Point shift) {
        if(LOG.isDebugEnabled()) {
            LOG.debug(new StringBuilder("moveDraggedElements ").append(getIds(this.draggedElements)).append(" by ").append(toString(shift)).toString());
        }

        if(this.draggedElements != null) {
            for(final ElementWithPosition element : this.draggedElements) {
                this.moveElement(element, shift);
            }
        }
    }// moveWholeArea

    /**
     * Moves an element relating to the translation.
     * 
     * @param element
     * @param shift
     */
    private void moveElement(final ElementWithPosition element, final Point shift) {
        if(element.getPosition() != null) {
            element.setPosition(//
            ((shift.x + this.draggingOffset.x) - this.lastPosition.getX()) + element.getPosition().getX(),//
                    ((shift.y + this.draggingOffset.y) - this.lastPosition.getY()) + element.getPosition().getY(),//
                    element.getPosition().getZ());
        }
    }

    /**
     * Moves the containing elements of a {@link Place}.
     * 
     * @param place
     *            The {@link Place} to move.
     * @param shift
     *            The shift by which the elements should be moved.
     */
    private void moveWholePlace(final Place place, final Point shift) {
        if(LOG.isDebugEnabled()) {
            LOG.debug(new StringBuilder("moveWholePlace ").append(place).append(" by ").append(toString(shift)).toString());
        }

        final LinkedList<ElementWithPosition> elements = new LinkedList<ElementWithPosition>();

        // get the elements to move
        for(final ElementWithPosition element : place.getElements()) {
            elements.add(element);
        }

        // move the elements
        for(final ElementWithPosition element : elements) {
            this.moveElement(element, shift);
        }// for
    }// moveWholePlace

    /**
     * Renews the relations betwenn an {@link Area} and its {@link Place}.
     * 
     * @param place
     *            The {@link Place} to update.
     */
    private void renewAreasOfPlace(final Place place) {
        // +++++ add new IDs +++++
        for(final Area area : place.getAreas()) {
            this.placeAreas.put(area, place);
        }
        // ----- add new IDs -----
    }

    /**
     * Renews the <code>Maps</code> of the containing elements.
     */
    private void renewMaps() {
        this.places = new HashMap<Place, VectorAdapter<Place>>();
        this.placeAreas = new HashMap<Area, Place>();
        this.doors = new HashMap<Door, VectorAdapter<Door>>();
        this.windows = new HashMap<Window, ContextModelVisualizer.VectorAdapter<Window>>();
        this.users = new HashMap<User, VectorAdapter<User>>();
        this.irs = new HashMap<InteractionResource, VectorAdapter<InteractionResource>>();
        this.tags = new HashMap<LocalizationTag, VectorAdapter<LocalizationTag>>();
        this.physicalDevices = new HashMap<PhysicalDevice, ContextModelVisualizer.VectorAdapter<PhysicalDevice>>();
    }

    private String toString2D(final Vector vector) {
        return toLocationString(vector.getX(), vector.getY());
    }

    private void updateCoordinates(final Map<?, ? extends VectorAdapter<?>> renderers) {
        synchronized(renderers) {
            for(final VectorAdapter<?> adapter : renderers.values()) {
                adapter.renderer.updateCoordinates();
            }
        }
    }

    private void updateCurrentElements(final List<Object> elements) {
        // reset current elements
        this.currentUser = null;
        this.currentIR = null;
        this.currentLocalizationTag = null;
        this.currentDoor = null;
        this.currentWindow = null;
        this.currentPhysicalDevice = null;

        if(elements == null) {
            // only reset current elements
            return;
        }

        Collections.sort(elements, this.elementComparator);

        // set current element (only one)
        for(final Object element : elements) {
            if(element instanceof User) {
                this.currentUser = (User)element;
                break;
            } else if(element instanceof InteractionResource) {
                this.currentIR = (InteractionResource)element;
                break;
            } else if(element instanceof LocalizationTag) {
                this.currentLocalizationTag = (LocalizationTag)element;
                break;
            } else if(element instanceof Door) {
                this.currentDoor = (Door)element;
                break;
            } else if(element instanceof Window) {
                this.currentWindow = (Window)element;
                break;
            } else if(element instanceof PhysicalDevice) {
                this.currentPhysicalDevice = (PhysicalDevice)element;
                break;
            } else if(element instanceof Place) {
                // nothing to do
            } else if(element instanceof Area) {
                // nothing to do
            } else if(element instanceof DragPointWrapper) {
                // nothing to do
            } else {
                LOG.warn("pointing on unknown type of element " + element);
            }
        }// for elements
    }

    /**
     * Updates the location of a {@link Point} at the {@link Environment} by a
     * location of a {@link Point} from the screen.
     * 
     * @param environmentPoint
     *            The {@link Point} at the {@link Environment} to update.
     * @param screenPoint
     *            The {@link Point} on screen to take into account.
     */
    private void updateEnvironmentLocation(Point environmentPoint, final Point screenPoint) {
        if(environmentPoint == null) {
            environmentPoint = new Point();
        } else if(screenPoint == null) {
            throw new IllegalArgumentException("Argument 'screenPoint' must not be null in methdo updateEnvironmentLocation!");
        }

        if(this.floorTabs == null || this.currentFloorPanel == null) {
            LOG.warn("Failed to update environment location: Visualization is not initialized!");
            throw new IllegalStateException("Failed to update environment location: Visualization is not initialized!");
        }

        environmentPoint.setLocation(((screenPoint.x - this.floorTabs.getLocation().x - this.currentFloorPanel.getLocation().x) / this.scale) - this.xOffset, ((super.getHeight() - (screenPoint.y + 2)) / this.scale) - this.yOffset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processMouseMotionEvent(final MouseEvent e) {
        final Point point = getLocation(e);

        // translate point if event comes from FloorPanel
        // if(e.getSource() instanceof FloorPanel) {
        // point.translate(-this.actualFloorPanel.getLocation().x,
        // -this.actualFloorPanel.getLocation().y);
        // }

        // Checking if an element is being moved
        if(!this.recordMode) {
            checkMovement(e, false);
        }

        // Indicating that an element was moved
        if(this.objectToMove != null) {
            this.thereWasMovement = true;
        }

        super.processMouseMotionEvent(e);

        // if(LOG.isDebugEnabled()) {
        // LOG.debug("environmentPointing: " +
        // toString(this.environmentPointing));
        // }

        this.pointing = point;

        try {
            updateEnvironmentLocation(this.environmentPointing, point);
            // this.environmentPointing.setLocation(x, y);

            // if(LOG.isDebugEnabled()) {
            // LOG.debug("pointing: " + toString(this.pointing));
            // }
            // point.translate(-this.actualFloorPanel.getLocation().x,
            // -this.actualFloorPanel.getLocation().y);

            this.updateCurrentElements(this.selectionAreas.getElements(point.x, point.y));

            repaint();
        }
        catch(IllegalStateException e2) {
            LOG.warn("Failed to update current elements", e2);
        }
    }// processMouseMotionEvent

    PlaceAdapter create(final PlaceRenderer placeRenderer) {
        final PlaceAdapter adapter = new PlaceAdapter(placeRenderer);

        synchronized(this.disposeables) {
            this.disposeables.add(ListAdapterUtility.observe(placeRenderer.element, ContextPackage.Literals.PLACE__AREAS, Area.class, adapter));
        }

        return adapter;
    }

    /**
     * <code>scale</code> calculates {@link #scale} as a ratio between the size
     * of this panel and the size of the environment. This method calls
     * {@link #repaint()} in case the scale changes.
     * 
     * @return boolean - <code>true</code> if scale changed and this panel has
     *         been repainted, otherwise <code>false</code>
     */
    boolean scale() {
        if(this.lastFrameSize == null) {
            this.lastFrameSize = new Dimension(-1, -1);
        }

        if(this.areaEditMode && this.draggedArea != null) {
            if(LOG.isDebugEnabled()) {
                LOG.debug("don't scale while areaEditMode is on and an area is dragged!");
            }
            return false;
        }

        try {
            double minimumX = 0;
            double maximumX = 0;
            double minimumY = 0;
            double maximumY = 0;
            double temp;
            synchronized(this.places) {
                Vector span;
                Vector origin;
                for(final Place place : this.places.keySet()) {
                    for(final Area area : place.getAreas()) {
                        origin = area.getOrigin();
                        span = area.getSpan();
                        if((origin == null) || (span == null)) {
                            continue;
                        }
                        temp = origin.getX();
                        if(temp < minimumX) {
                            minimumX = temp;
                        } else if(temp > maximumX) {
                            maximumX = temp;
                        }
                        temp = origin.getY();
                        if(temp < minimumY) {
                            minimumY = temp;
                        } else if(temp > maximumY) {
                            maximumY = temp;
                        }
                        temp = origin.getX() + span.getX();
                        if(temp < minimumX) {
                            minimumX = temp;
                        } else if(temp > maximumX) {
                            maximumX = temp;
                        }
                        temp = origin.getY() + span.getY();
                        if(temp < minimumY) {
                            minimumY = temp;
                        } else if(temp > maximumY) {
                            maximumY = temp;
                        }
                    }
                }
            }

            Vector position;
            for(final User user : this.users.keySet()) {
                position = user.getPosition();
                if(position == null) {
                    // skip
                    continue;
                } else if(this.danglingElementsPanel.isDangling(user)) {
                    continue;
                }
                temp = user.getPosition().getX();
                if(temp < minimumX) {
                    minimumX = temp;
                } else if(temp > maximumX) {
                    maximumX = temp;
                }
                temp = user.getPosition().getY();
                if(temp < minimumY) {
                    minimumY = temp;
                } else if(temp > maximumY) {
                    maximumY = temp;
                }
            }

            for(final PhysicalDevice device : this.physicalDevices.keySet()) {
                position = device.getPosition();
                if(position == null) {
                    // skip
                    continue;
                } else if(this.danglingElementsPanel.isDangling(device)) {
                    continue;
                }
                temp = device.getPosition().getX();
                if(temp < minimumX) {
                    minimumX = temp;
                } else if(temp > maximumX) {
                    maximumX = temp;
                }
                temp = device.getPosition().getY();
                if(temp < minimumY) {
                    minimumY = temp;
                } else if(temp > maximumY) {
                    maximumY = temp;
                }
            }

            for(final InteractionResource ir : this.irs.keySet()) {
                position = ir.getPosition();
                if(position == null) {
                    // skip
                    continue;
                } else if(this.danglingElementsPanel.isDangling(ir)) {
                    continue;
                }
                temp = ir.getPosition().getX();
                if(temp < minimumX) {
                    minimumX = temp;
                } else if(temp > maximumX) {
                    maximumX = temp;
                }
                temp = ir.getPosition().getY();
                if(temp < minimumY) {
                    minimumY = temp;
                } else if(temp > maximumY) {
                    maximumY = temp;
                }
            }

            Vector pos = null;
            temp = 0;
            for(final LocalizationTag ir : this.tags.keySet()) {
                if(this.danglingElementsPanel.isDangling(ir)) {
                    continue;
                }
                if((pos = ir.getPosition()) != null) {
                    temp = pos.getX();
                }
                if(temp < minimumX) {
                    minimumX = temp;
                } else if(temp > maximumX) {
                    maximumX = temp;
                }
                temp = 0;
                if(pos != null) {
                    temp = pos.getY();
                }

                // temp = ir.getPosition().getY();
                if(temp < minimumY) {
                    minimumY = temp;
                } else if(temp > maximumY) {
                    maximumY = temp;
                }
            }

            final int border = 50;

            // indicates whether to update all coordinates
            boolean updateCoordinates = false;

            if(this.yOffset != ((int)-minimumY + border)) {
                updateCoordinates = true;
            } else if(this.xOffset != ((int)-minimumX + border)) {
                updateCoordinates = true;
            }

            final int yMove = 0;// (int)(-(this.canvasPanel.getHeight()));

            this.getHeight();

            this.xOffset = (int)-minimumX;
            this.yOffset = ((int)-minimumY) - yMove;
            // 10 pixels border
            this.xOffset += border;
            this.yOffset += border;

            // move interval to 0
            maximumX -= minimumX;
            maximumY -= minimumY;

            maximumX += 2 * border;
            maximumY += 2 * border;

            if(this.currentFloorPanel == null) {
                LOG.warn("cannot scale: actualFloorPanel is null " + this.scale);
                return false;
            }

            // if(LOG.isDebugEnabled()) {
            // LOG.debug("actualFloorPanel size: " +
            // this.actualFloorPanel.getWidth() + " " +
            // this.actualFloorPanel.getHeight());
            // LOG.debug("actualFloorPanel visible=" +
            // this.actualFloorPanel.isVisible() + ", showing=" +
            // this.actualFloorPanel.isShowing());
            // }

            double newScale = Math.min(this.currentFloorPanel.getWidth() / maximumX, this.currentFloorPanel.getHeight() / maximumY);

            if(newScale == 0) {
                newScale = .5;
            } else if(newScale < 0) {
                newScale = 0.1;
            }

            if(newScale != this.scale) {
                updateCoordinates = true;
            }

            if(updateCoordinates || !this.lastFrameSize.equals(this.currentFloorPanel.getSize())) {
                // update needed
                this.scale = newScale;

                // update all renderer
                updateCoordinates(this.places);
                updateCoordinates(this.doors);
                updateCoordinates(this.windows);
                updateCoordinates(this.users);
                updateCoordinates(this.irs);
                updateCoordinates(this.tags);
                updateCoordinates(this.physicalDevices);

                // repaint UI
                repaint();
                // if(LOG.isDebugEnabled()) {
                // LOG.debug("scale: true " + this.scale);
                // }
                return true;
            }
        }
        catch(final Exception e) {
            e.printStackTrace();
        }

        if(this.currentFloorPanel != null) {
            this.lastFrameSize = this.currentFloorPanel.getSize();
        }

        return false;
    }
}