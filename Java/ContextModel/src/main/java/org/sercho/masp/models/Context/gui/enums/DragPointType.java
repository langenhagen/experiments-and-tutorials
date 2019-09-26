/**
 * 
 */
package org.sercho.masp.models.Context.gui.enums;

import org.sercho.masp.models.Context.Area;
import org.sercho.masp.models.Context.Place;

/**
 * Indicates where a dragged {@link Area} or {@link Place} is touched by the
 * mouse.
 * 
 * @author Andre Schulz
 * @since 1.2.33
 */
public enum DragPointType {
    /**
     * Top right corner.
     */
    NE,

    /**
     * Top left corner.
     */
    NW,

    /**
     * Bottom right corner.
     */
    SE,

    /**
     * Bottom left corner.
     */
    SW,

    /**
     * Center.
     */
    CENTER;
}
