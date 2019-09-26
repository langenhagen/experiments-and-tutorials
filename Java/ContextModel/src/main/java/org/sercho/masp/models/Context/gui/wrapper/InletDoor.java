/**
 * 
 */
package org.sercho.masp.models.Context.gui.wrapper;

import org.sercho.masp.models.Context.Door;
import org.sercho.masp.models.Context.Vector;

/**
 * {@link Inlet} wrapping a {@link Door}.
 * 
 * @author Andre Schulz
 * @since 1.3.8
 */
public class InletDoor implements Inlet {

    private final Door door;

    public InletDoor(final Door door) {
        if(door == null) {
            throw new IllegalArgumentException("door argument mus tnot be null in constructor OpenableDoor!");
        }

        this.door = door;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.sercho.masp.models.Context.gui.wrapper.Openable#getSpan()
     */
    @Override
    public Vector getSpan() {
        return this.door.getSpan();
    }

}
