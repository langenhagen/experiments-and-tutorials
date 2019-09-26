/**
 * 
 */
package org.sercho.masp.models.Context.gui.wrapper;

import org.sercho.masp.models.Context.Vector;
import org.sercho.masp.models.Context.Window;

/**
 * {@link Inlet} wrapping a {@link Window}.
 * 
 * @author Andre Schulz
 * @since 1.3.8
 */
public class InletWindow implements Inlet {

    private final Window window;

    public InletWindow(final Window window) {
        if(window == null) {
            throw new IllegalArgumentException("window argument mus tnot be null in constructor OpenableWindow!");
        }

        this.window = window;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.sercho.masp.models.Context.gui.wrapper.Openable#getSpan()
     */
    @Override
    public Vector getSpan() {
        return this.window.getSpan();
    }

}
