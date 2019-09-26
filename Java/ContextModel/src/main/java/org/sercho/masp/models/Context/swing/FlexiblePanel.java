/**
 * 
 */
package org.sercho.masp.models.Context.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * <code>FlexiblePanel</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * @author Grzegorz Lehmann
 */
public final class FlexiblePanel extends JPanel {

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5507159262802109287L;

    private static final int FOCUS_GROWTH = 15;

    private static transient final int DOUBLE_FOCUS_GROWTH = 2 * FOCUS_GROWTH;

    /**
     * <code>ExtendedMouseAdapter</code> extends {@link MouseAdapter} with
     * {@link MouseMotionListener} interface.
     * 
     * @author Grzegorz Lehmann
     */
    public static abstract class ExtendedMouseAdapter extends MouseAdapter implements
            MouseMotionListener {

        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseDragged(final MouseEvent e) {
            // overwrite if necessary
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseMoved(final MouseEvent e) {
            // overwrite if necessary
        }
    }

    /**
     * <code>adapter</code>
     */
    private final ExtendedMouseAdapter adapter = new ExtendedMouseAdapter() {

        private volatile Point start;

        private volatile Point last;

        private static final transient long THROW_TIMEOUT_MILLIS = 300;

        /**
         * {@inheritDoc}
         */
        @Override
        public void mousePressed(final MouseEvent event) {
            if(event.getButton() == MouseEvent.BUTTON3) {
                this.start = event.getPoint();
                this.last = this.start;
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseReleased(final MouseEvent event) {
            if(this.start != null) {
                final long timeout = System.currentTimeMillis() - this.lastDrag;
                if(timeout < THROW_TIMEOUT_MILLIS) {
                    final Point delta = div(sub(this.last, this.start), 2);

                    new Thread() {

                        /**
                         * {@inheritDoc}
                         */
                        @Override
                        public void run() {
                            final Component component = event.getComponent();
                            Point p = component.getLocation();
                            while(true) {
                                try {
                                    sleep(25);
                                }
                                catch(final InterruptedException e) {
                                    e.printStackTrace();
                                }
                                p = add(p, delta);
                                if(p.x < 0 || p.y < 0) {
                                    break;
                                }
                                if(p.x/* + component.getWidth() */< getSize().width + 50 && p.y/*
                                                                                                * +
                                                                                                * component
                                                                                                * .
                                                                                                * getHeight
                                                                                                * (
                                                                                                * )
                                                                                                */< getSize().height + 50) {
                                    setPosition(component, p.x, p.y);
                                } else {
                                    System.out.println(p.x + " " + getSize().width + " " + p.y + " " + getSize().height);
                                    break;
                                }
                            }
                            System.out.println("Thrown");
                        }
                    }.start();
                }
                this.start = null;
            }
        }

        private Point sub(final Point p1, final Point p2) {
            return new Point(p1.x - p2.x, p1.y - p2.y);
        }

        private Point add(final Point p1, final Point p2) {
            return new Point(p1.x + p2.x, p1.y + p2.y);
        }

        private Point div(final Point p1, final double dividend) {
            return new Point((int)(p1.x / dividend), (int)(p1.y / dividend));
        }

        private volatile long lastDrag = 0;

        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseDragged(final MouseEvent event) {
            if(this.start == null) {
                return;
            }
            this.last = event.getPoint();
            this.lastDrag = System.currentTimeMillis();
            final Point end = event.getPoint();
            final Point position = event.getComponent().getLocation();
            int deltaX = position.x + end.x - this.start.x;
            int deltaY = position.y + end.y - this.start.y;
            if(deltaX < 0) {
                deltaX = 0;
            }
            if(deltaY < 0) {
                deltaY = 0;
            }
            setPosition(event.getComponent(), deltaX, deltaY);
        }
    };

    private Point pointing;

    private final Map<Component, Point> components = new HashMap<Component, Point>();

    /**
     * <code>FlexiblePanel</code> constructor.
     */
    public FlexiblePanel() {
        // default constructor
        setLayout(null);
        setBackground(Color.WHITE);
    }

    private Component lastFocused;

    public synchronized void focus(final Component c) {
        if(this.lastFocused == c) {
            return;
        }
        final Point point = this.components.get(c);
        if(point == null) {
            throw new IllegalArgumentException("c is not on panel");
        }
        // unfocus();
        point.setLocation(point.x - FOCUS_GROWTH, point.y - FOCUS_GROWTH);
        final Dimension newSize = c.getSize();
        newSize.width += DOUBLE_FOCUS_GROWTH;
        newSize.height += DOUBLE_FOCUS_GROWTH;
        c.setMinimumSize(newSize);
        this.lastFocused = c;
        relayout();
    }

    public synchronized void unfocus() {
        if(this.lastFocused == null) {
            return;
        }
        final Point point = this.components.get(this.lastFocused);
        if(point == null) {
            return;
        }
        point.setLocation(point.x + FOCUS_GROWTH, point.y + FOCUS_GROWTH);
        final Dimension newSize = this.lastFocused.getSize();
        newSize.width -= DOUBLE_FOCUS_GROWTH;
        newSize.height -= DOUBLE_FOCUS_GROWTH;
        this.lastFocused.setMinimumSize(newSize);
        this.lastFocused.setMaximumSize(newSize);
        relayout();
        this.lastFocused = null;
    }

    private final Map<Component, Integer> zMap = new HashMap<Component, Integer>();

    public synchronized void add(final Component c, final int x, final int y, final int z) {
        if(c == null) {
            throw new IllegalArgumentException("c is null");
        }

        add(c, calculateComponentZOrder(z));
        this.zMap.put(c, Integer.valueOf(z));

        final Point point = new Point(x, y);
        this.components.put(c, point);
        c.addMouseListener(this.adapter);
        c.addMouseMotionListener(this.adapter);
        relayout();
    }

    private int calculateComponentZOrder(final int z) {
        for(final Component component : getComponents()) {
            if(this.zMap.get(component).intValue() >= z) {
                // return order of component to place c over it
                return getComponentZOrder(component);
            }
        }
        // place at end
        return getComponentCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void remove(final Component comp) {
        // synchronized
        super.remove(comp);
    }

    public synchronized void setPosition(final Component c, final int x, final int y) {
        final Point2D point = this.components.get(c);
        if(point == null) {
            throw new IllegalArgumentException("c is not on panel");
        }
        point.setLocation(x, y);
        c.setBounds(x, y, c.getWidth(), c.getHeight());
    }

    private TexturePaint paint;

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        if(this.paint == null) {
            BufferedImage image = null;
            try {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("org/sercho/masp/models/Context/swing/MASP_Grid.png"));
                /* img is the background tile */
                final int height = image.getHeight(this);
                final int width = image.getWidth(this);
                final BufferedImage backgrounImage = (BufferedImage)createImage(width, height);

                final Graphics2D bim_g2d = (Graphics2D)backgrounImage.getGraphics();
                bim_g2d.drawImage(image, 0, 0, Color.black, this);

                this.paint = new TexturePaint(backgrounImage, new Rectangle(0, 0, width, height));
            }
            catch(final Exception e) {
                // simply do not render background
            }
        }
        // if(this.paint != null) {
        // final Graphics2D g2d = (Graphics2D)g;
        // g2d.setPaint(this.paint);
        // g2d.fill(g2d.getClip());
        // }

        if(this.pointing == null) {
            return;
        }
        g.drawOval(this.pointing.x - 3, this.pointing.y - 3, 7, 7);
    }

    public void resetPointing() {
        FlexiblePanel.this.pointing = null;
        repaint();
    }

    public void setPointing(final int x, final int y) {
        if(x < 0 || y < 0) {
            new Exception().printStackTrace();
        }
        if(this.pointing == null) {
            this.pointing = new Point(x, y);
        } else {
            this.pointing.setLocation(x, y);
        }
        repaint();
    }

    private void relayout() {
        Point p;
        Component c;
        for(final Entry<Component, Point> entry : this.components.entrySet()) {
            c = entry.getKey();
            p = entry.getValue();
            c.setBounds(p.x, p.y, c.getMinimumSize().width, c.getMinimumSize().height);
        }
    }

    /**
     * <code>main</code> for tests.
     * 
     * @param args
     *            program arguments, ignored
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        final FlexiblePanel fp = new FlexiblePanel();

        final JButton jButton1 = new javax.swing.JButton("Button1");
        final JButton jButton2 = new javax.swing.JButton("Button2");
        jButton2.setMinimumSize(new Dimension(100, 100));

        fp.add(jButton1, 1, 1, 3);
        fp.add(jButton2, 100, 150, 3);
        fp.add(new JLabel("Hello"), 10, 0, 12);

        final JFrame frame = new JFrame("Test");
        frame.setSize(400, 400);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(fp, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}