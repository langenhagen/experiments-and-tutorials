/**
 * 
 */
package org.sercho.masp.models;

/**
 * <code>DisposeableProxy</code> holds multiple {@link Disposeable}s and
 * disposes them upon a {@link #dispose()} call.
 * 
 * @author Grzegorz Lehmann
 * @since 1.0.6
 */
public final class DisposeableProxy implements Disposeable {

    private final Disposeable[] d;

    /**
     * <code>DisposeableProxy</code> constructor.
     * 
     * @param disposeables
     *            {@link Disposeable}s to dispose
     */
    public DisposeableProxy(final Disposeable... disposeables) {
        if(disposeables == null) {
            throw new IllegalArgumentException("disposeables is null");
        }
        this.d = disposeables;
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        for(int i = 0; i < this.d.length; i++) {
            if(this.d[i] != null) {
                this.d[i].dispose();
            }
        }
    }
}