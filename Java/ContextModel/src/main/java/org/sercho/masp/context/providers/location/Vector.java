/**
 * File     Vector.java
 * Package  org.sercho.masp.models.Context.location
 * Project  ContextModel
 * Date     07.11.2007
 * Web      http://www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.context.providers.location;

import java.io.Serializable;

/**
 * <code>Vector</code> specifies a 3D vector.
 * 
 * @author Grzegorz Lehmann
 */
public final class Vector implements Serializable, Comparable<Vector> {

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3051953660529886666L;

    /**
     * <code>x</code> coordinate
     */
    private volatile double x;

    /**
     * <code>y</code> coordinate
     */
    private volatile double y;

    /**
     * <code>z</code> coordinate
     */
    private volatile double z;

    /**
     * <code>length</code> holds the length of this vector
     */
    private volatile double length;

    /**
     * <code>Vector</code> constructor
     * 
     * @param xCoordinate
     *            x of this vector
     * @param yCoordinate
     *            y of this vector
     * @param zCoordinate
     *            z of this vector
     */
    public Vector(final double xCoordinate, final double yCoordinate,
            final double zCoordinate) {
        this.x = xCoordinate;
        this.y = yCoordinate;
        this.z = zCoordinate;
        updateLength();
    }

    /**
     * <code>getX</code> getter
     * 
     * @return double - <code>x</code> of this <code>Vector</code>
     */
    public double getX() {
        return this.x;
    }

    /**
     * <code>getY</code> getter
     * 
     * @return double - <code>y</code> of this <code>Vector</code>
     */
    public double getY() {
        return this.y;
    }

    /**
     * <code>getZ</code> getter
     * 
     * @return double - <code>z</code> of this <code>Vector</code>
     */
    public double getZ() {
        return this.z;
    }

    /**
     * <code>add</code> adds a vector to this vector.
     * 
     * @param vector
     *            vector to add to this vector
     * @throws IllegalArgumentException
     *             if <code>vector</code> is <code>null</code>
     */
    public synchronized void add(final Vector vector) {
        if(vector == null) {
            throw new IllegalArgumentException("vector is null");
        }
        this.x += vector.x;
        this.y += vector.y;
        this.z += vector.z;
        updateLength();
    }

    /**
     * <code>copy</code> copies this vector.
     * 
     * @return Vector - a copy of this vector
     */
    public Vector copy() {
        return new Vector(this.x, this.y, this.z);
    }

    /**
     * <code>updateLength</code> updates the {@link #length}.
     */
    private void updateLength() {
        this.length = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    /**
     * <code>length</code> calculates the length of this vector.
     * 
     * @return double - length of this vector
     */
    public double length() {
        return this.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuffer result = new StringBuffer(super.toString());
        result.append(" (x: ");
        result.append(this.x);
        result.append(", y: ");
        result.append(this.y);
        result.append(", z: ");
        result.append(this.z);
        result.append(')');
        return result.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final Vector o) {
        return this.length < o.length ? -1 : 1;
    }
}