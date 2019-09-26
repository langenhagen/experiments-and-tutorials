/**
 * 
 */
package org.sercho.masp.models.Context.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.sercho.masp.models.Disposeable;
import org.sercho.masp.models.Context.Assistant;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.Place;
import org.sercho.masp.models.Context.Room;
import org.sercho.masp.models.Context.ThirdPartyAssistant;
import org.sercho.masp.models.Context.User;
import org.sercho.masp.models.Context.util.EnvironmentUtility.PlaceObserver;

/**
 * <code>EnvironmentUtilityTest</code> tests the {@link EnvironmentUtility}.
 * 
 * @author Grzegorz Lehmann
 * @since 5.0.3
 */
public final class EnvironmentUtilityTest extends TestCase {

    public void testGetUser() {
        // set up environment
        final Environment environment = ContextFactory.eINSTANCE.createEnvironment();
        final Room room = ContextFactory.eINSTANCE.createRoom();
        environment.getPlaces().add(room);
        final Room unknown = ContextFactory.eINSTANCE.createRoom();
        environment.getPlaces().add(unknown);

        // add EnvironmentElements
        // user1 in unknown
        final User user1 = ContextFactory.eINSTANCE.createUser();
        user1.setId("user1");
        environment.getUsers().add(user1);
        unknown.getElements().add(user1);

        // user2 in room
        final User user2 = ContextFactory.eINSTANCE.createUser();
        user2.setId("user2");
        environment.getUsers().add(user2);
        room.getElements().add(user2);

        assertSame(user1, EnvironmentUtility.getUser(environment, user1.getId()));
        assertSame(user2, EnvironmentUtility.getUser(environment, user2.getId()));

        assertNull(EnvironmentUtility.getUser(environment, ""));
    }

    public void testGetEnvironmentElement() {
        // set up environment
        final Environment environment = ContextFactory.eINSTANCE.createEnvironment();
        final Room room = ContextFactory.eINSTANCE.createRoom();
        environment.getPlaces().add(room);
        final Room unknown = ContextFactory.eINSTANCE.createRoom();
        environment.getPlaces().add(unknown);

        // add EnvironmentElements
        // user1 in unknown
        final User user1 = ContextFactory.eINSTANCE.createUser();
        user1.setId("user1");
        environment.getUsers().add(user1);
        unknown.getElements().add(user1);

        // user2 in room
        final User user2 = ContextFactory.eINSTANCE.createUser();
        user2.setId("user2");
        environment.getUsers().add(user2);
        room.getElements().add(user2);

        final Device device = ContextFactory.eINSTANCE.createDevice();
        environment.getDevices().add(device);
        // resource1 in unknown
        final InteractionResource resource1 = ContextFactory.eINSTANCE.createDisplay();
        device.getResources().add(resource1);
        resource1.setId("resource1");
        unknown.getElements().add(resource1);

        // resource2 in room
        final InteractionResource resource2 = ContextFactory.eINSTANCE.createDisplay();
        device.getResources().add(resource2);
        resource2.setId("resource2");
        room.getElements().add(resource2);

        // check if EnvironmentElements are found
        assertSame(user1, EnvironmentUtility.getEnvironmentElement(environment, user1.getId()));
        assertSame(user2, EnvironmentUtility.getEnvironmentElement(environment, user2.getId()));
        assertSame(resource1, EnvironmentUtility.getEnvironmentElement(environment, resource1.getId()));
        assertSame(resource2, EnvironmentUtility.getEnvironmentElement(environment, resource2.getId()));
    }

    private final class TestPlaceObserver implements PlaceObserver {

        final List<Place> places = new LinkedList<Place>();

        boolean disposed = false;

        TestPlaceObserver() {
            // increased visibility
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void newPlace(final Place place) {
            this.places.add(place);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void placeRemoved(final Place place) {
            this.places.remove(place);
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

    public void testObservePlaces() {
        final TestPlaceObserver observer = new TestPlaceObserver();

        final Environment environment = ContextFactory.eINSTANCE.createEnvironment();

        final Room room1 = ContextFactory.eINSTANCE.createRoom();
        room1.setId("room1");
        environment.getPlaces().add(room1);

        // add observer
        final Disposeable disposeable = EnvironmentUtility.observePlaces(environment, observer);
        // room1 should be added
        assertPlaces(environment, observer.places);

        final Room room2 = ContextFactory.eINSTANCE.createRoom();
        room2.setId("room2");
        environment.getPlaces().add(room2);
        assertPlaces(environment, observer.places);

        final Room unknown1 = ContextFactory.eINSTANCE.createRoom();
        unknown1.setId("unknown1");
        environment.getPlaces().add(unknown1);
        assertPlaces(environment, observer.places);

        environment.getPlaces().add(room1);
        assertPlaces(environment, observer.places);

        environment.getPlaces().remove(room1);
        assertPlaces(environment, observer.places);

        // add both at once
        final Collection<Room> rooms = new ArrayList<Room>(2);
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(unknown1);
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

    public final void testGetPastThirdPartyAssistants() {
        // set up environment
        final Environment environment = ContextFactory.eINSTANCE.createEnvironment();

        final Assistant menuBar = ContextFactory.eINSTANCE.createHomeOSAssistant();
        menuBar.setId("1");
        menuBar.setName("MenuBar");
        environment.getAssistants().add(menuBar);

        final Assistant metaUI = ContextFactory.eINSTANCE.createHomeOSAssistant();
        metaUI.setId("2");
        metaUI.setName("MetaUI");
        environment.getAssistants().add(metaUI);

        final Assistant shea = ContextFactory.eINSTANCE.createThirdPartyAssistant();
        shea.setId("2");
        shea.setName("SHEA");
        environment.getAssistants().add(shea);

        final Assistant vcook = ContextFactory.eINSTANCE.createThirdPartyAssistant();
        vcook.setId("3");
        vcook.setName("VCook");
        environment.getAssistants().add(vcook);

        // user
        final User user = ContextFactory.eINSTANCE.createUser();
        user.setId("user");
        environment.getUsers().add(user);

        user.addCurrentAssistant(metaUI);
        user.removeCurrentAssistant(metaUI);
        user.addCurrentAssistant(menuBar);
        user.addCurrentAssistant(vcook);

        Set<ThirdPartyAssistant> pastThirdPartyAssistants = EnvironmentUtility.getPastThirdPartyAssistants(environment, user.getId());
        assertNotNull(pastThirdPartyAssistants);
        assertTrue(pastThirdPartyAssistants.isEmpty());

        user.removeCurrentAssistant(vcook);
        user.addCurrentAssistant(shea);
        pastThirdPartyAssistants = EnvironmentUtility.getPastThirdPartyAssistants(environment, user.getId());
        assertNotNull(pastThirdPartyAssistants);
        assertEquals(1, pastThirdPartyAssistants.size());
        assertTrue(pastThirdPartyAssistants.contains(vcook));

        user.removeCurrentAssistant(shea);
        pastThirdPartyAssistants = EnvironmentUtility.getPastThirdPartyAssistants(environment, user.getId());
        assertNotNull(pastThirdPartyAssistants);
        assertEquals(2, pastThirdPartyAssistants.size());
        assertTrue(pastThirdPartyAssistants.contains(vcook));
        assertTrue(pastThirdPartyAssistants.contains(shea));

        user.addCurrentAssistant(shea);
        pastThirdPartyAssistants = EnvironmentUtility.getPastThirdPartyAssistants(environment, user.getId());
        assertNotNull(pastThirdPartyAssistants);
        assertEquals(1, pastThirdPartyAssistants.size());
        assertTrue(pastThirdPartyAssistants.contains(vcook));
    }

    public final void testGetCurrentThirdPartyAssistants() {
        // set up environment
        final Environment environment = ContextFactory.eINSTANCE.createEnvironment();

        final Assistant menuBar = ContextFactory.eINSTANCE.createHomeOSAssistant();
        menuBar.setId("1");
        menuBar.setName("MenuBar");
        environment.getAssistants().add(menuBar);

        final Assistant metaUI = ContextFactory.eINSTANCE.createHomeOSAssistant();
        metaUI.setId("2");
        metaUI.setName("MetaUI");
        environment.getAssistants().add(metaUI);

        final Assistant shea = ContextFactory.eINSTANCE.createThirdPartyAssistant();
        shea.setId("2");
        shea.setName("SHEA");
        environment.getAssistants().add(shea);

        final Assistant vcook = ContextFactory.eINSTANCE.createThirdPartyAssistant();
        vcook.setId("3");
        vcook.setName("VCook");
        environment.getAssistants().add(vcook);

        // user
        final User user = ContextFactory.eINSTANCE.createUser();
        user.setId("user");
        environment.getUsers().add(user);

        user.addCurrentAssistant(metaUI);
        user.removeCurrentAssistant(metaUI);
        user.addCurrentAssistant(menuBar);
        user.addCurrentAssistant(vcook);

        Set<ThirdPartyAssistant> currentThirdPartyAssistants = EnvironmentUtility.getCurrentThirdPartyAssistants(environment, user.getId());
        assertNotNull(currentThirdPartyAssistants);
        assertEquals(1, currentThirdPartyAssistants.size());
        assertTrue(currentThirdPartyAssistants.contains(vcook));

        user.removeCurrentAssistant(vcook);
        user.addCurrentAssistant(shea);
        currentThirdPartyAssistants = EnvironmentUtility.getCurrentThirdPartyAssistants(environment, user.getId());
        assertNotNull(currentThirdPartyAssistants);
        assertEquals(1, currentThirdPartyAssistants.size());
        assertTrue(currentThirdPartyAssistants.contains(shea));

        user.removeCurrentAssistant(shea);
        currentThirdPartyAssistants = EnvironmentUtility.getCurrentThirdPartyAssistants(environment, user.getId());
        assertNotNull(currentThirdPartyAssistants);
        assertTrue(currentThirdPartyAssistants.isEmpty());

        user.addCurrentAssistant(shea);
        currentThirdPartyAssistants = EnvironmentUtility.getCurrentThirdPartyAssistants(environment, user.getId());
        assertNotNull(currentThirdPartyAssistants);
        assertEquals(1, currentThirdPartyAssistants.size());
        assertTrue(currentThirdPartyAssistants.contains(shea));
    }
}