/**
 * 
 */
package org.sercho.masp.models.Context.gui;

import org.sercho.masp.models.Context.Vector;

/**
 * A wrapper to wrap a {@link Vector} ant its name.
 * 
 * @author Andre Schulz
 * @since 1.2.47
 */
public class VectorWrapper {

    /**
     * The {@link Vector}.
     */
    private final Vector vector;

    /**
     * The name of the {@link Vector}.
     */
    private final String name;

    public VectorWrapper(final Vector vector, final String name) {
        this.vector = vector;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Vector getVector() {
        return this.vector;
    }
}
