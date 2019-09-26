package org.sercho.masp.models.Context.gui;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

/**
 * The graphical areas on the visualizer.
 * 
 * @author Antonio Fernandez Zaragoza
 * @author Andre Schulz
 */
public class GraphicalAreas {

    /**
     * A {@link Map} of a related element to an {@link Area}.
     */
    private final Map<Area, Object> areaElements = Collections.synchronizedMap(new HashMap<Area, Object>());

    private final Vector<Area> areas = new Vector<Area>();

    /**
     * A {@link Map} of an related {@link Area} to an element.
     */
    private final Map<Object, Area> elementAreas = Collections.synchronizedMap(new HashMap<Object, Area>());

    private final Map<Object, Integer> variations = Collections.synchronizedMap(new HashMap<Object, Integer>());

    private final Map<Object, Integer> variationsY = Collections.synchronizedMap(new HashMap<Object, Integer>());

    public synchronized void clear() {
        this.areas.clear();
        this.elementAreas.clear();
        this.areaElements.clear();
    }

    public synchronized boolean containsArea(final Object id, final Point point, final int width, final int height, final int variation, final int variationY, final boolean store) {
        Area area = null;
        final Iterator<Entry<Object, Area>> it = this.elementAreas.entrySet().iterator();
        Entry<Object, Area> tempE = null;
        Area paintedArea = null;

        while(it.hasNext()) {
            tempE = it.next();
            paintedArea = tempE.getValue();
            if(!tempE.getKey().equals(id)) {
                if(paintedArea.intersects((int)point.getX(), (int)point.getY(), width, height)) {
                    return false;
                }
            }

        }

        area = new Area(new Rectangle((int)point.getX(), (int)point.getY(), width, height));
        // // if(id == 10)
        // System.out.println("Creando area: " + area.getBounds());
        if(store) {
            // System.out.println(id);
            this.elementAreas.put(id, area);
        }

        this.variations.put(id, variation);
        this.variationsY.put(id, variationY);
        return true;
    }

    public synchronized Area createArea(final Object id, final int x, final int y, final int width, final int height) {
        final Rectangle rectangle = new Rectangle(x, y, width, height);
        final Area newArea = new Area(rectangle);
        this.elementAreas.put(id, newArea);
        this.areaElements.put(newArea, id);
        return newArea;
    }

    /**
     * Returns the {@link Map} of areas related to elements.
     * 
     * @return {@link Map} of areas.
     */
    public Map<Object, Area> getElementAreas() {
        return this.elementAreas;
    }

    /**
     * Returns a {@link List} of IDs on a specific location.
     * 
     * @param x
     *            The x coordinate.
     * @param y
     *            The y coordinate.
     * @return A {@link List} of IDs on the location.
     */
    public synchronized LinkedList<Object> getElements(final double x, final double y) {
        final Point point = new Point(Double.valueOf(x).intValue(), Double.valueOf(y).intValue());
        final Iterator<Entry<Object, Area>> it = this.elementAreas.entrySet().iterator();
        Entry<Object, Area> tempE = null;
        Area paintedArea = null;

        final LinkedList<Object> ids = new LinkedList<Object>();

        while(it.hasNext()) {
            tempE = it.next();
            paintedArea = tempE.getValue();
            if(paintedArea.contains(point)) {
                ids.add(this.areaElements.get(paintedArea));
            }
        }

        return ids;
    }

    public int getVariation(final String id) {
        if(id == null) {
            throw new IllegalArgumentException("Argument id must not be null in method getVariation!");
        }

        if(this.variations == null) {
            throw new IllegalStateException("varitaions are null");
        }

        final Integer variation = this.variations.get(id);

        if(variation == null) {
            throw new IllegalStateException("variation for id '" + id + "' is null");
        }

        return variation.intValue();
    }

    public int getVariationY(final String id) {
        if(id == null) {
            throw new IllegalArgumentException("Argument id must not be null in method getVariationY!");
        }

        if(this.variationsY == null) {
            throw new IllegalStateException("variationsY are null");
        }

        final Integer variation = this.variationsY.get(id);

        if(variation == null) {
            throw new IllegalStateException("variation for id '" + id + "' is null");
        }

        return variation.intValue();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("GraphicalAreas ");
        sb.append(this.elementAreas.size());
        sb.append(" ");
        sb.append(this.areaElements.size());
        return sb.toString();
    }
}
