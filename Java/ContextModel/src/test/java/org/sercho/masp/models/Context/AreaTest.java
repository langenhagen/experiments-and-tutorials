/**
 * 
 */
package org.sercho.masp.models.Context;

import junit.framework.TestCase;

/**
 * {@link TestCase} to test {@link Area}.
 * 
 * @author Andre Schulz
 * @since 1.3.7
 */
public abstract class AreaTest extends TestCase {

    /**
     * The factory.
     */
    private final ContextFactory factory;

    /**
     * <code>CookerTest</code> constructor.
     */
    protected AreaTest(final ContextFactory testFactory) {
        if(testFactory == null) {
            throw new IllegalArgumentException("testFactory is null");
        }

        this.factory = testFactory;
    }

    /**
     * Test method for {@link Area#contains(Vector)} if {@link Area} has a
     * positive span.
     */
    public void testContainsWithPositiveSpan() {
        final Area area = this.factory.createArea();
        area.setNewOrigin(0, 0, 0);
        area.setNewSpan(10, 10, 10);

        final Vector validVector = this.factory.createVector();
        validVector.setCoordinates(5, 5, 5);

        final Vector invalidVector = this.factory.createVector();
        invalidVector.setCoordinates(-5, -5, -5);

        assertTrue("valid vector", area.contains(validVector));
        assertTrue("invalid vector", !area.contains(invalidVector));
    }

    /**
     * Test method for {@link Area#contains(Vector)} if {@link Area} has a
     * negative span.
     */
    public void testContainsWithNegativeSpan() {
        final Area area = this.factory.createArea();
        area.setNewOrigin(0, 0, 0);
        area.setNewSpan(-10, -10, -10);

        final Vector validVector = this.factory.createVector();
        validVector.setCoordinates(-5, -5, -5);

        final Vector invalidVector = this.factory.createVector();
        invalidVector.setCoordinates(5, 5, 5);

        assertTrue("valid vector", area.contains(validVector));
        assertTrue("invalid vector", !area.contains(invalidVector));
    }
}
