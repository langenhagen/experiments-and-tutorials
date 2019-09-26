/**
 * 
 */
package org.sercho.masp.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

import org.sercho.masp.models.ListAdapterUtility.ListObserver;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.Place;
import org.sercho.masp.models.Context.Room;

/**
 * <code>ListAdapterUtilityTest</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since TODO
 */
public class ListAdapterUtilityTest extends TestCase {

    private final class TestListObserver implements ListObserver<Place> {

        final List<Place> places = new LinkedList<Place>();

        boolean disposed = false;

        TestListObserver() {
            // increased visibility
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void added(final Place element) {
            this.places.add(element);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void removed(final Place element) {
            this.places.remove(element);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void disposed() {
            if(this.disposed) {
                fail("Already disposed once!");
            }
            this.disposed = true;
        }
    }

    public void testObserve() {
        final TestListObserver observer = new TestListObserver();

        final Environment environment = ContextFactory.eINSTANCE.createEnvironment();

        final Room room1 = ContextFactory.eINSTANCE.createRoom();
        room1.setId("room1");
        environment.getPlaces().add(room1);

        // add observer
        final Disposeable disposeable = ListAdapterUtility.observe(environment, ContextPackage.Literals.ENVIRONMENT__PLACES, Place.class, observer);
        // room1 should be added
        assertPlaces(environment, observer.places);

        final Room room2 = ContextFactory.eINSTANCE.createRoom();
        room2.setId("room2");
        environment.getPlaces().add(room2);
        assertPlaces(environment, observer.places);

        environment.getPlaces().add(room1);
        assertPlaces(environment, observer.places);

        environment.getPlaces().remove(room1);
        assertPlaces(environment, observer.places);

        environment.getPlaces().remove(room2);
        assertPlaces(environment, observer.places);

        // add both at once
        final Collection<Room> rooms = new ArrayList<Room>(2);
        rooms.add(room1);
        rooms.add(room2);
        // add many
        environment.getPlaces().addAll(rooms);
        assertPlaces(environment, observer.places);
        // remove many
        environment.getPlaces().removeAll(rooms);
        assertPlaces(environment, observer.places);

        environment.getPlaces().add(room2);
        assertPlaces(environment, observer.places);

        // room2 is still there
        assertEquals(1, observer.places.size());
        assertTrue(observer.places.contains(room2));
        // dispose observer, no event about room2 should arrive
        disposeable.dispose();

        assertTrue(observer.disposed);

        // remove room2
        environment.getPlaces().remove(room2);

        // observer should not get notified
        assertEquals(1, observer.places.size());
        assertTrue(observer.places.contains(room2));
    }

    private static void assertPlaces(final Environment environment, final Collection<Place> places) {
        assertEquals("Environment has a wrong number of places", environment.getPlaces().size(), places.size());
        for(final Place place : places) {
            assertTrue("Environment is missing place: " + place, environment.getPlaces().contains(place));
        }
    }

}
