/**
 * File     VectorTest.java
 * Package  org.sercho.masp.context.providers.location
 * Project  ContextProviderAPIs
 * Date     May 16, 2008
 * Web      www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.context.providers.location;

import junit.framework.TestCase;

/**
 * <code>VectorTest</code> TODO comment
 * 
 * @author Grzegorz Lehmann
 * @since FIXME
 */
public class VectorTest extends TestCase {

    /**
     * Test method for
     * {@link org.sercho.masp.context.providers.location.Vector#add(org.sercho.masp.context.providers.location.Vector)}
     * .
     */
    public final void testAdd() {
        final Vector vector = new Vector(0, 0, 1);
        vector.add(vector);
        assertEquals(2, vector.length(), 0);
        vector.add(new Vector(0, 0, -1));
        assertEquals(1, vector.length(), 0);
        vector.add(new Vector(0, 0, -1));
        assertEquals(0, vector.length(), 0);
    }

    /**
     * Test method for
     * {@link org.sercho.masp.context.providers.location.Vector#copy()}.
     */
    public final void testCopy() {
        final Vector vector = new Vector(0, 0, 1);
        assertEqualVectors(vector, vector.copy());
    }

    private void assertEqualVectors(final Vector v1, final Vector v2) {
        assertEquals(v1.getX(), v2.getX(), 0);
        assertEquals(v1.getY(), v2.getY(), 0);
        assertEquals(v1.getZ(), v2.getZ(), 0);
    }

    /**
     * Test method for
     * {@link org.sercho.masp.context.providers.location.Vector#length()}.
     */
    public final void testLength() {
        assertEquals(0, new Vector(0, 0, 0).length(), 0);
        assertEquals(1, new Vector(1, 0, 0).length(), 0);
        assertEquals(1, new Vector(0, 1, 0).length(), 0);
        assertEquals(1, new Vector(0, 0, 1).length(), 0);
        assertEquals(2, new Vector(0, 0, 2).length(), 0);
        final Vector vector = new Vector(0, 0, 1);
        vector.add(vector);
        assertEquals(2, vector.length(), 0);
    }
}