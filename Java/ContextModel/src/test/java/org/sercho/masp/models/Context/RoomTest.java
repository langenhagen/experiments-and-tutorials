/**
 * 
 */
package org.sercho.masp.models.Context;

import java.util.List;

import org.sercho.masp.models.EMFTestCase;

/**
 * <code>RoomTest</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 1.2
 */
public abstract class RoomTest extends EMFTestCase<ContextFactory> {

    /**
     * <code>RoomTest</code> constructor.
     * 
     * @param testFactory
     *            {@link ContextFactory} for this test
     */
    protected RoomTest(final ContextFactory testFactory) {
        super(testFactory);
    }

    /**
     * <code>testGetUsers</code> tests {@link Room#getUsers()}.
     */
    public void testGetUsers() {
        final Room room = this.factory.createRoom();
        final User user1 = this.factory.createUser();
        final User user2 = this.factory.createUser();
        room.addElement(user1);
        room.addElement(user2);
        final LocalizationTag tag = this.factory.createLocalizationTag();
        room.addElement(tag);
        final InteractionResource ir = this.factory.createDisplay();
        room.addElement(ir);

        assertNotNull(room.getElements());
        assertEquals(4, room.getElements().size());

        assertTrue(room.getElements().contains(user1));
        assertTrue(room.getElements().contains(user2));
        assertTrue(room.getElements().contains(tag));
        assertTrue(room.getElements().contains(ir));

        final List<User> users = room.getUsers();
        assertNotNull(users);
        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }

    /**
     * <code>testAddArea</code> tests
     * {@link Room#addArea(double, double, double, double, double, double)} and
     * {@link Room#removeArea(Area)}.
     */
    public void testAddArea() {
        final Room room = this.factory.createRoom();
        room.addArea(0, 0, 0, 100, 100, 100);
        room.addArea(100, 0, 0, 100, 100, 100);

        assertEquals(2, room.getAreas().size());
        final Area area1 = room.getAreas().get(0);
        final Area area2 = room.getAreas().get(1);
        assertArea(area1, 0, 0, 0, 100, 100, 100);
        assertArea(area2, 100, 0, 0, 100, 100, 100);

        room.removeArea(area1);
        assertEquals(1, room.getAreas().size());
        assertTrue(!room.getAreas().contains(area1));
        assertTrue(room.getAreas().contains(area2));

        // try again
        room.removeArea(area1);
        assertEquals(1, room.getAreas().size());
        assertTrue(!room.getAreas().contains(area1));
        assertTrue(room.getAreas().contains(area2));

        room.removeArea(area2);
        assertTrue(room.getAreas().isEmpty());
    }

    private static void assertArea(final Area area, final double originX, final double originY, final double originZ, final double spanX, final double spanY, final double spanZ) {
        assertNotNull(area);
        final Vector origin = area.getOrigin();
        assertNotNull(origin);
        assertEquals(originX, origin.getX(), 0);
        assertEquals(originY, origin.getY(), 0);
        assertEquals(originZ, origin.getZ(), 0);
        final Vector span = area.getSpan();
        assertNotNull(span);
        assertEquals(spanX, span.getX(), 0);
        assertEquals(spanY, span.getY(), 0);
        assertEquals(spanZ, span.getZ(), 0);
    }
}
