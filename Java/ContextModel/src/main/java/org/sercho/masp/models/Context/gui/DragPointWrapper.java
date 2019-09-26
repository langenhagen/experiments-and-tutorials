/**
 * 
 */
package org.sercho.masp.models.Context.gui;

import org.sercho.masp.models.Context.Area;
import org.sercho.masp.models.Context.gui.enums.DragPointType;

/**
 * A wrapper class to wrap an {@link Area} and a {@link DragPointType}. It is
 * used as an element for the visualization.
 * 
 * @author Andre Schulz
 * @since 1.2.49
 */
public class DragPointWrapper {

    /**
     * The wrapped {@link Area}.
     */
    public Area area;

    /**
     * The wrapped {@link DragPointType}.
     */
    public DragPointType dragPointType;

    public DragPointWrapper(final Area area, final DragPointType dragPointType) {
        this.area = area;
        this.dragPointType = dragPointType;
    }

    /**
     * Returns the wrapped {@link Area}.
     * 
     * @return The wrapped {@link Area}.
     */
    public Area getArea() {
        return this.area;
    }

    /**
     * Returns the wrapped {@link DragPointType}.
     * 
     * @return The wrapped {@link DragPointType}.
     */
    public DragPointType getDragPointType() {
        return this.dragPointType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.dragPointType);
        sb.append(" of ");
        sb.append(this.area);
        return sb.toString();
    }
}
