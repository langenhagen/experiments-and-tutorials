package org.sercho.masp.models;

/**
 * <code>Disposeable</code> describes an entity, which can be disposed.
 * 
 * @author Grzegorz Lehmann
 * @since 1.0.5
 */
public interface Disposeable {

    /**
     * <code>dispose</code> disposes this entity causing it to release all
     * resources.
     */
    void dispose();
}