/**
 * <code>EnvironmentUtility.java</code> 
 * 
 * @author Stephen Prochnow
 */
package org.sercho.masp.models.Context.util;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.SingletonAdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.sercho.masp.models.Disposeable;
import org.sercho.masp.models.Context.Area;
import org.sercho.masp.models.Context.Assistant;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.Door;
import org.sercho.masp.models.Context.ElementWithPosition;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.EnvironmentElement;
import org.sercho.masp.models.Context.HomeOSAssistant;
import org.sercho.masp.models.Context.PhysicalDevice;
import org.sercho.masp.models.Context.Place;
import org.sercho.masp.models.Context.Room;
import org.sercho.masp.models.Context.ThirdPartyAssistant;
import org.sercho.masp.models.Context.User;
import org.sercho.masp.models.Context.Vector;
import org.sercho.masp.models.Context.Window;
import org.sercho.masp.models.Context.gui.ContextModelVisualizer;

import de.dailab.masp.models.Properties.DoubleProperty;
import de.dailab.masp.models.Properties.Service;
import de.dailab.masp.models.Properties.ServiceContainer;

/**
 * The Class EnvironmentUtility.
 */
public class EnvironmentUtility {

    /**
     * <code>PlaceObserver</code> describes a entity receiving notifications
     * about {@link Place}s in an {@link Environment}.
     * 
     * @author Grzegorz Lehmann
     * @since 5.0.3
     */
    public static interface PlaceObserver {

        /**
         * <code>disposed</code> informs this observer, that it will receive no
         * more notifications.
         */
        void disposed();

        /**
         * <code>newPlace</code> informs this observer about a new {@link Place}
         * added to the {@link Environment}.
         * 
         * @param place
         *            new place
         */
        void newPlace(Place place);

        /**
         * <code>placeRemoved</code> informs this observer about a {@link Place}
         * that has been removed from the {@link Environment}.
         * 
         * @param place
         *            removed place
         */
        void placeRemoved(Place place);
    }

    private static final class DisposeableAdapter implements Disposeable {

        private final SingletonAdapterImpl adapter;

        private boolean disposed = false;

        /**
         * <code>DisposeableAdapter</code> constructor.
         * 
         * @param disposeableAdapter
         *            adapter that can be disposed
         */
        DisposeableAdapter(final SingletonAdapterImpl disposeableAdapter) {
            this.adapter = disposeableAdapter;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public synchronized void dispose() {
            if(this.disposed) {
                return;
            }
            this.adapter.dispose();
            this.disposed = true;
        }
    }

    private static final class PlaceAdapter extends SingletonAdapterImpl {

        private final PlaceObserver observer;

        /**
         * <code>PlaceAdapter</code> constructor.
         * 
         * @param environment
         *            environment to observe
         * @param placeObserver
         *            callback
         * @throws IllegalArgumentException
         *             if any argument is <code>null</code>
         */
        PlaceAdapter(final Environment environment, final PlaceObserver placeObserver) {
            if(environment == null) {
                throw new IllegalArgumentException("environment is null");
            }
            if(placeObserver == null) {
                throw new IllegalArgumentException("placeObserver is null");
            }
            environment.eAdapters().add(this);
            this.observer = placeObserver;
            synchronized(environment.getPlaces()) {
                addedMany(environment.getPlaces());
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void dispose() {
            super.dispose();
            this.observer.disposed();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @SuppressWarnings("unchecked")
        public synchronized void notifyChanged(final Notification msg) {
            // handle environment
            if(msg.getFeature() == ContextPackage.Literals.ENVIRONMENT__PLACES) {
                if(msg.getEventType() == Notification.ADD) {
                    // a new place has been added
                    this.observer.newPlace((Place)msg.getNewValue());
                } else if(msg.getEventType() == Notification.ADD_MANY) {
                    addedMany((Collection<Place>)msg.getNewValue());
                } else if(msg.getEventType() == Notification.REMOVE) {
                    // a place has been removed
                    this.observer.placeRemoved((Place)msg.getOldValue());
                } else if(msg.getEventType() == Notification.REMOVE_MANY) {
                    removedMany((Collection<Place>)msg.getOldValue());
                } else if(msg.getEventType() == Notification.SET) {
                    // remove all from old list
                    Collection<Place> elements = (Collection<Place>)msg.getOldValue();
                    if(elements != null) {
                        removedMany(elements);
                    }
                    // add all from new list
                    elements = (Collection<Place>)msg.getNewValue();
                    if(elements != null) {
                        addedMany(elements);
                    }
                }
            }
        }

        private void addedMany(final Collection<Place> places) {
            for(final Place place : places) {
                this.observer.newPlace(place);
            }
        }

        private void removedMany(final Collection<Place> places) {
            for(final Place place : places) {
                this.observer.placeRemoved(place);
            }
        }
    }

    public static boolean addCurrentAssistantToUser(final Environment environment, final String userId, final String assistantId) {
        final User user = getUser(environment, userId);
        if(user == null) {
            return false;
        }
        final Assistant assistant = getAssistant(environment, assistantId);
        if(assistant == null) {
            return false;
        }
        user.addCurrentAssistant(assistant);
        return true;
    }

    /**
     * Checks whether the given ID is not used as an ID of an
     * {@link ServiceContainer}.
     * 
     * @param service
     *            The list of {@link ServiceContainer}.
     * @param id
     *            The ID of a {@link ServiceContainer} to check.
     * @return <code>true</code> if ID is valid, <code>false</code> otherwise.
     * @author Andre Schulz
     * @since 1.3.15
     */
    public static boolean checkIdForService(final EList<Service> services, final String id) {
        synchronized(services) {
            for(final Service service : services) {
                if(service.getId().equals(id)) {
                    return false;
                }

                if(service instanceof ServiceContainer) {
                    boolean ok = true;
                    ok = checkIdForService(((ServiceContainer)service).getServices(), id);

                    if(!ok) {
                        return false;
                    }
                }
            }// for
        }

        return true;
    }// checkIdOfServiceContainer

    /**
     * 
     * @param environment
     *            The {@link Environment}.
     * @param id
     *            The id to check.
     * @return <code>true</code> if the id is not used, <code>false</code>
     *         otherwise.
     * @author Andre Schulz
     * @since 1.3.15
     */
    public static boolean checkIdForService(final Environment environment, final String id) {
        synchronized(environment.getServiceContainers()) {
            for(final ServiceContainer serviceContainer : environment.getServiceContainers()) {
                if(serviceContainer.getId().equals(id)) {
                    return false;
                }

                if(serviceContainer.getServices().size() > 0) {
                    boolean ok = true;
                    ok = EnvironmentUtility.checkIdForService(serviceContainer.getServices(), id);

                    if(!ok) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Fixes missing positions at {@link PhysicalDevice}s and their sub
     * {@link PhysicalDevice}s.
     * 
     * @param physicalDevice
     *            The {@link PhysicalDevice} to fix.
     * @param environment
     *            The {@link Environment} to get the valid {@link Place}s from.
     * @return A {@link List} of messages describing the fixes.
     */
    public static List<String> fixMissingPosition(PhysicalDevice physicalDevice, Environment environment) {
        LinkedList<String> messages = new LinkedList<String>();
        String message;

        message = fixMissingPosition(physicalDevice, environment, "PhysicalDevice");

        if(!message.isEmpty()) {
            messages.add(message);
        }

        for(PhysicalDevice subDevice : physicalDevice.getSubDevice()) {
            message = fixMissingPosition(subDevice, environment, "PhysicalDevice");

            if(!message.isEmpty()) {
                messages.add(message);
            }
        }

        return messages;
    }

    /**
     * Fixes <code>null</code> position of {@link ElementWithPosition} of the
     * {@link Environment}. If an {@link ElementWithPosition} has a
     * {@link Place} but no position, then a position at the {@link Place} is
     * assigned to the {@link ElementWithPosition}.
     * 
     * @param environment
     *            The {@link Environment} to fix.
     * @return A {@link List} of messages describing the fixes.
     * @since 1.3.27
     */
    public static List<String> fixMissingPositions(Environment environment) {
        LinkedList<String> messages = new LinkedList<String>();
        String message;

        synchronized(environment.getDevices()) {
            for(final Device device : environment.getDevices()) {
                if(device instanceof PhysicalDevice) {
                    List<String> deviceMessages = fixMissingPosition((PhysicalDevice)device, environment);

                    if(deviceMessages.size() > 0) {
                        messages.addAll(deviceMessages);
                    }
                } else {
                    message = fixMissingPosition(device, environment, "Device");

                    if(!message.isEmpty()) {
                        messages.add(message);
                    }
                }
            }
        }

        synchronized(environment.getUsers()) {
            for(final User user : environment.getUsers()) {
                message = fixMissingPosition(user, environment, "User");

                if(!message.isEmpty()) {
                    messages.add(message);
                }
            }
        }

        return messages;
    }

    /**
     * Fixes {@link Place}s by adding containing {@link Door}s and
     * {@link ElementWithPosition} to it. Also it removes elements which should
     * be on the <code>place</code> but are not on the <code>place</code>. And
     * it removes {@link Door}s from place where <code>source</code> is not
     * <code>place</code>.
     * 
     * @param environment
     *            The surrounding {@link Environment}.
     * @param place
     *            The {@link Place} to normalize.
     * @author Andre Schulz
     */
    public static void fixPlace(final Environment environment, final Place place) {

        final LinkedList<ElementWithPosition> elements = new LinkedList<ElementWithPosition>();

        // get all elements
        for(final ElementWithPosition element : place.getElements()) {
            elements.add(element);
        }

        // remove elements with wrong location from place
        for(final ElementWithPosition element : elements) {

            if((element.getPosition() != null) && !place.contains(element.getPosition())) {
                // element is not on place => remove it
                place.removeElement(element);
                element.setPlace(null);
            }
        }// for

        // process all elements of the environment
        for(final ElementWithPosition element : EnvironmentUtility.getAllElementsWithPosition(environment)) {

            // // skip doors
            // if(element instanceof Door) {
            // continue;
            // }

            // check if no place is assigned
            // or element has lost its place
            if((element.getPlace() == null) || !((element.getPosition() != null) && element.getPlace().contains(element.getPosition()))) {

                // check if element is on current place
                if((element.getPosition() != null) && place.contains(element.getPosition())) {

                    // skip if source of door is not this place
                    if((element instanceof Door) && (((Door)element).getSource() != place)) {
                        continue;
                    }

                    // assign element to place
                    if(!place.getElements().contains(element)) {
                        place.addElement(element);
                    }

                    // assign place to element
                    if(element.getPlace() != place) {
                        element.setPlace(place);
                    }
                }// if
            }// if
        }// for

        // add doors to place
        for(final Door door : place.getDoors()) {
            if((door.getSource() != null) && (door.getSource() == place) && place.getElements().contains(door)) {
                place.addElement(door);
            }
        }

        // add windows to place
        for(final Window window : place.getWindows()) {
            if((window.getSource() != null) && (window.getSource() == place) && place.getElements().contains(window)) {
                place.addElement(window);
            }
        }
    }// normalizePlace

    /**
     * Sets the {@link Place} of a {@link Device}s and {@link User}s to the
     * {@link Place} containing the position of the element if the position and
     * {@link Place} does'nt match.
     * 
     * @param environment
     *            The {@link Environment}.
     * @param fixNullPlaces
     *            Indicates to set the {@link Place} of a {@link Device} if it
     *            is <code>null</code> (<code>true</code>) or to leave the
     *            {@link Place} untouched (<code>false</code>).
     * @author Andre Schulz
     * @return A {@link List} of messages describing the fixes.s
     * @since 1.3.27
     */
    public static List<String> fixPlaceInRealtionToPosition(final Environment environment, boolean fixNullPlaces) {
        LinkedList<String> messages = new LinkedList<String>();
        String message;

        synchronized(environment.getDevices()) {
            for(final Device device : environment.getDevices()) {
                if(device instanceof PhysicalDevice) {
                    fixPlaceInRealtionToPosition((PhysicalDevice)device, environment, fixNullPlaces);
                } else {
                    message = fixPlaceInRealtionToPosition(device, fixNullPlaces, environment, "Device");

                    if(!message.isEmpty()) {
                        messages.add(message);
                    }
                }
            }
        }

        synchronized(environment.getUsers()) {
            for(final User user : environment.getUsers()) {
                message = fixPlaceInRealtionToPosition(user, fixNullPlaces, environment, "User");

                if(!message.isEmpty()) {
                    messages.add(message);
                }
            }
        }

        return messages;
    }

    /**
     * Fixes the {@link Place} of a {@link PhysicalDevice} in relation to its
     * position. This method works recursive and fixes the positions of all sub
     * devices.
     * 
     * @param physicalDevice
     *            The {@link PhysicalDevice} to fix.
     * @param environment
     *            The {@link Environment} to get valid {@link Place}s from.
     * @param fixNullPlaces
     *            Indicates to set the {@link Place} of a {@link Device} if it
     *            is <code>null</code> (<code>true</code>) or to leave the
     *            {@link Place} untouched (<code>false</code>).
     * @return A {@link List} of messages describing the fixes.
     * @since 1.3.27
     */
    public static List<String> fixPlaceInRealtionToPosition(PhysicalDevice physicalDevice, final Environment environment, boolean fixNullPlaces) {
        LinkedList<String> messages = new LinkedList<String>();
        String message;
        message = fixPlaceInRealtionToPosition(physicalDevice, fixNullPlaces, environment, "PhysicalDevice");

        if(!message.isEmpty()) {
            messages.add(message);
        }

        for(PhysicalDevice subDevice : physicalDevice.getSubDevice()) {
            message = fixPlaceInRealtionToPosition(subDevice, fixNullPlaces, environment, "PhysicalDevice");

            if(!message.isEmpty()) {
                messages.add(message);
            }
        }

        return messages;
    }

    /**
     * 
     * @param environment
     * @return
     * @author Andre Schulz
     */
    public static List<ElementWithPosition> getAllElementsWithPosition(final Environment environment) {
        if(environment == null) {
            throw new IllegalArgumentException("environment is null");
        }

        final List<ElementWithPosition> elements = new LinkedList<ElementWithPosition>();

        final TreeIterator<EObject> allContents = environment.eAllContents();
        EObject content;

        while(allContents.hasNext()) {
            content = allContents.next();

            if(content instanceof ElementWithPosition) {
                elements.add((ElementWithPosition)content);
            }
        }

        return elements;
    }// getAllElementsWithPosition

    /**
     * Gets all rooms.
     * 
     * @param environment
     *            the environment
     * @return the all rooms or a empty list if there are no rooms
     */
    public static List<Room> getAllRooms(final Environment environment) {
        if(environment == null) {
            throw new IllegalArgumentException("environment argument must not be null in method getAllRooms ");
        }
        final Iterable<Place> places = environment.getPlaces();
        final List<Room> rooms = new LinkedList<Room>();
        synchronized(places) {
            for(final Place place : places) {
                if(place instanceof Room) {
                    rooms.add((Room)place);
                }
            }
        }
        return rooms;
    }

    public static Assistant getAssistant(final Environment environment, final String assistantId) {
        if(environment == null) {
            throw new IllegalArgumentException("environment argument must not be null in method getAssistant");
        }
        return getEnvironmentElement(environment.getAssistants(), assistantId);
    }

    /**
     * <code>getAssistantsOfType</code> returns all {@link Assistant}s of a
     * given type found in a {@link Collection}.
     * 
     * @param <A>
     *            type of assistants to find
     * @param assistants
     *            collection to search for assistants
     * @param assistantType
     *            type of assistants to find
     * @return Set&lt;A&gt; - all assistants of the requested type found in
     *         <code>assistants</code>
     */
    public static <A extends Assistant> Set<A> getAssistantsOfType(final Collection<Assistant> assistants, final Class<A> assistantType) {
        if(assistantType == null) {
            throw new IllegalArgumentException("assistantType argument must not be null in method getAssistantsOfType");
        }
        if((assistants == null) || assistants.isEmpty()) {
            return Collections.emptySet();
        }
        final Set<A> assistantsOfType = new HashSet<A>();
        synchronized(assistants) {
            for(final Assistant assistant : assistants) {
                if(assistantType.isInstance(assistant)) {
                    assistantsOfType.add(assistantType.cast(assistant));
                }
            }
        }
        return assistantsOfType;
    }

    /**
     * <code>getRoomContainingPosition</code> returns a {@link Room} of a
     * {@link Environment} containing a specified position.
     * 
     * @param environment
     *            environment to search through, <code>null</code> allowed
     * @param position
     *            position to find a room for, <code>null</code> disallowed
     * @return Room - room containing <code>position</code> or <code>null</code>
     *         if no such room exists or <code>environment</code> is
     *         <code>null</code>
     * @throws IllegalArgumentException
     *             if <code>position</code> is <code>null</code>
     * 
     */
    /*
     * public static Room getRoomContainingPosition(final Environment
     * environment, final Vector position) { if(position == null) { throw new
     * IllegalArgumentException
     * ("position argument must not be null in method getRoomContainingPosition"
     * ); } if(environment == null) { return null; }
     * synchronized(environment.getPlaces()) { for(final Place place :
     * environment.getPlaces()) { if(place instanceof Room &&
     * place.contains(position)) { return (Room)place; } } } return null; }
     */

    /**
     * <code>getAssistantsOfType</code> returns all {@link Assistant}s of a
     * given type found in an {@link Environment}.
     * 
     * @param <A>
     *            type of assistants to find
     * @param environment
     *            environment to search for assistants
     * @param assistantType
     *            type of assistants to find
     * @return Set&lt;A&gt; - all assistants of the requested type found in
     *         <code>environment</code>
     */
    public static <A extends Assistant> Set<A> getAssistantsOfType(final Environment environment, final Class<A> assistantType) {
        if(environment == null) {
            throw new IllegalArgumentException("environment argument must not be null in method getAssistantsOfType");
        }
        return getAssistantsOfType(environment.getAssistants(), assistantType);
    }

    /**
     * <code>getCurrentThirdPartyAssistants</code> returns assistants currently
     * used by a {@link User}. This method searches for the user in the given
     * <code>environment</code> and returns all {@link ThirdPartyAssistant}
     * stored in {@link User#getCurrentAssistants()}.
     * 
     * @param environment
     *            environment to search
     * @param userID
     *            user identifier
     * @return Set&lt;ThirdPartyAssistant&gt; - all {@link ThirdPartyAssistant}s
     *         currently used by the user
     */
    public static Set<ThirdPartyAssistant> getCurrentThirdPartyAssistants(final Environment environment, final String userID) {
        if(environment == null) {
            throw new IllegalArgumentException("environment argument must not be null in method getCurrentThirdPartyAssistants");
        }
        if(userID == null) {
            throw new IllegalArgumentException("userID argument must not be null in method getCurrentThirdPartyAssistants");
        }
        final User user = getEnvironmentElement(environment.getUsers(), userID);
        if(user == null) {
            return Collections.emptySet();
        }
        return getAssistantsOfType(user.getCurrentAssistants(), ThirdPartyAssistant.class);
    }

    /**
     * Gets the device.
     * 
     * @param deviceName
     *            the device name
     * @param environment
     *            the environment
     * @return the device or null
     */
    public static Device getDevice(final String deviceName, final Environment environment) {
        return getEnvironmentElement(environment.getDevices(), deviceName);
    }

    /**
     * Retrieves a {@link Door} with a specified ID from an {@link Environment}.
     * 
     * @param environment
     *            Environment to search
     * @param identifier
     *            ID of the door
     * @return Door - {@link Door} with the specified ID or <code>null</code> if
     *         no such door has been found
     * @throws IllegalArgumentException
     *             If any argument is <code>null</code>
     */
    public static Door getDoor(final Environment environment, final String identifier) {
        if(environment == null) {
            throw new IllegalArgumentException("environment argument must not be null in method getUser");
        }
        if(identifier == null) {
            throw new IllegalArgumentException("identifier argument must not be null in method getUser");
        }

        final Iterable<Place> places = environment.getPlaces();
        if(places == null) {
            // no places where a door could be
            return null;
        }

        Iterable<Door> doors;

        synchronized(places) {
            for(final Place place : places) {
                doors = place.getDoors();

                if(doors == null) {
                    // no door in this current place
                    continue;
                }

                synchronized(doors) {
                    for(final Door door : doors) {
                        if(identifier.equals(door.getId())) {
                            return door;
                        }
                    }
                }// synchronized(doors)
            }// for places
        }// synchronized(places)

        // not found
        return null;
    }

    /**
     * <code>getEnvironmentElement</code> searches an {@link Environment} for an
     * element with a given Id. This method searches among the users and
     * resources of the places of the environment (
     * {@link Environment#getPlaces()} ). <br />
     * This method is thread-safe. It copies the list that it iterates through.
     * To search individual places it uses
     * {@link #getEnvironmentElement(Place, String)}, which is thread-safe.
     * 
     * @param environment
     *            environment to search
     * @param id
     *            identifier of the element to find, <code>null</code>
     *            disallowed
     * @return EnvironmentElement - element with the specified Id, or
     *         <code>null</code> if no such element was found
     * @throws IllegalArgumentException
     *             if <code>id</code> is <code>null</code>
     */
    public static EnvironmentElement getEnvironmentElement(final Environment environment, final String id) {
        if(environment == null) {
            throw new IllegalArgumentException("environment is null");
        }
        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }

        final TreeIterator<EObject> allContents = environment.eAllContents();
        EObject content;
        EnvironmentElement element;
        while(allContents.hasNext()) {
            content = allContents.next();
            if(content instanceof EnvironmentElement) {
                element = ((EnvironmentElement)content);
                if(id.equals(element.getId())) {
                    return element;
                }
            }
        }
        // not found
        return null;
    }

    /**
     * <code>getEnvironmentElement</code> searches for an
     * {@link EnvironmentElement} within a list. This method locks the list
     * using its intrinsic lock for the duration of the iteration.
     * 
     * @param <E>
     *            type of {@link EnvironmentElement} to find
     * @param id
     *            identifier of the element to find, <code>null</code>
     *            disallowed
     * @param elements
     *            list of elements to search, <code>null</code> disallowed
     * @return E - element with the specified Id, or <code>null</code> if no
     *         such element was found
     * @throws NullPointerException
     *             if any argument is <code>null</code>
     */
    public static <E extends EnvironmentElement> E getEnvironmentElement(final List<E> elements, final String id) {
        // lock
        synchronized(elements) {
            // iterate
            for(final E element : elements) {
                if(id.equals(element.getId())) {
                    // got it!
                    return element;
                }
            }
        }
        // not found
        return null;
    }

    /**
     * <code>getEnvironmentElement</code> searches a {@link Place} for an
     * element with a given Id. This method first searches among the users and
     * then among the resources of the specified place. <br />
     * This method is thread-safe. It uses the intrinsic locks of collections
     * that it iterates through.
     * 
     * @param place
     *            place to search, <code>null</code> disallowed
     * @param id
     *            identifier of the element to find, <code>null</code>
     *            disallowed
     * @return EnvironmentElement - element with the specified Id, or
     *         <code>null</code> if no such element was found
     * @throws IllegalArgumentException
     *             if any argument is <code>null</code>
     */
    public static EnvironmentElement getEnvironmentElement(final Place place, final String id) {
        if(place == null) {
            throw new IllegalArgumentException("place is null");
        }
        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }
        // search among users and then resources of the place
        return getEnvironmentElement(place.getElements(), id);
    }

    /**
     * <code>getHomeOSAssistants</code> returns all {@link HomeOSAssistant}s
     * found in an {@link Environment}.
     * 
     * @param environment
     *            environment to search for assistants
     * @return Set&lt;HomeOSAssistant&gt; - all {@link HomeOSAssistant}s found
     *         in <code>environment</code>
     */
    public static Set<HomeOSAssistant> getHomeOSAssistants(final Environment environment) {
        return getAssistantsOfType(environment, HomeOSAssistant.class);
    }

    /**
     * <code>getPastThirdPartyAssistants</code> returns assistants used by a
     * {@link User} in the past. This method searches for the user in the given
     * <code>environment</code> and returns all {@link ThirdPartyAssistant}
     * stored in {@link User#getPastAssistants()}.
     * 
     * @param environment
     *            environment to search
     * @param userID
     *            user identifier
     * @return Set&lt;ThirdPartyAssistant&gt; - all {@link ThirdPartyAssistant}s
     *         used by the user in the past
     */
    public static Set<ThirdPartyAssistant> getPastThirdPartyAssistants(final Environment environment, final String userID) {
        if(environment == null) {
            throw new IllegalArgumentException("environment argument must not be null in method getPastThirdPartyAssistants");
        }
        if(userID == null) {
            throw new IllegalArgumentException("userID argument must not be null in method getPastThirdPartyAssistants");
        }
        final User user = getEnvironmentElement(environment.getUsers(), userID);
        if(user == null) {
            return Collections.emptySet();
        }
        return getAssistantsOfType(user.getPastAssistants(), ThirdPartyAssistant.class);
    }

    /**
     * Retrieves a {@link Door} with a specified ID from an {@link Environment}.
     * 
     * @param environment
     *            Environment to search
     * @param identifier
     *            ID of the user
     * @return PhysicalDevice - {@link PhysicalDevice} with the specified ID or
     *         <code>null</code> if no such door has been found
     * @throws IllegalArgumentException
     *             If any argument is <code>null</code>
     */
    public static PhysicalDevice getPhysicalDevice(final Environment environment, final String identifier) {
        if(environment == null) {
            throw new IllegalArgumentException("environment argument must not be null in method getUser");
        }
        if(identifier == null) {
            throw new IllegalArgumentException("identifier argument must not be null in method getUser");
        }
        final Iterable<Device> devices = environment.getDevices();
        if(devices == null) {
            // no such user exist
            return null;
        }
        synchronized(devices) {
            for(final Device device : devices) {
                if(identifier.equals(device.getId())) {
                    if(device instanceof PhysicalDevice) {
                        // got the user
                        return (PhysicalDevice)device;
                    } else {
                        return null;
                    }
                }
            }
        }
        // not found
        return null;
    }

    /**
     * Gets the physical device.
     * 
     * @param deviceName
     *            the device name
     * @param place
     *            the place
     * @return the physical device or null
     */
    public static PhysicalDevice getPhysicalDevice(final String deviceName, final Place place) {
        if(deviceName == null) {
            throw new IllegalArgumentException("deviceName argument must not be null in method getPhysicalDevice");
        }
        if(place == null) {
            throw new IllegalArgumentException("place argument must not be null in method getPhysicalDevice");
        }
        final EList<ElementWithPosition> elements = place.getElements();
        PhysicalDevice device = null;
        for(final ElementWithPosition elem : elements) {
            if(elem.getName().equals(deviceName)) {
                if(elem instanceof PhysicalDevice) {
                    device = (PhysicalDevice)elem;
                    break;
                }
            }
        }
        return device;
    }

    /**
     * <code>getPlaceContainingPosition</code> returns a {@link Place} of a
     * {@link Environment} containing a specified position.
     * 
     * @param environment
     *            environment to search through, <code>null</code> allowed
     * @param position
     *            position to find a place for, <code>null</code> disallowed
     * @return Place - place containing <code>position</code> or
     *         <code>null</code> if no such place exists or
     *         <code>environment</code> is <code>null</code>
     * @throws IllegalArgumentException
     *             if <code>position</code> is <code>null</code>
     * 
     */
    public static Place getPlaceContainingPosition(final Environment environment, final Vector position) {
        if(position == null) {
            throw new IllegalArgumentException("position argument must not be null in method getPlaceContainingPosition");
        } else if(environment == null) {
            return null;
        }

        synchronized(environment.getPlaces()) {
            for(final Place place : environment.getPlaces()) {
                if(place.contains(position)) {
                    return place;
                }
            }
        }

        return null;
    }

    /**
     * Returns the {@link Place} of an {@link Area}.
     * 
     * @param area
     * @return
     * @author Andre Schulz
     */
    public static Place getPlaceOfArea(final Environment environment, final Area area) {
        for(final Place p : environment.getPlaces()) {
            for(final Area a : p.getAreas()) {
                if(a == area) {
                    return p;
                }
            }// for
        }// for

        return null;
    }

    /**
     * Delivers the power usage value of a device (including sub-devices).
     * 
     * @param device
     *            device to retrieve the power usage of
     * @return Integer - power usage of the device, including the power usage of
     *         its sub-devices. <code>null</code> if no power usage sensors are
     *         defined in the device and its sub-devices.
     */
    public static Integer getPowerUsage(final Device device) {
        // power usage sum of the device and its sub-devices
        int powerUsageSum = 0;

        // flag for checking if the power usage is unknown
        // remains true only if the device and all of its subdevice have unknown
        // power usage
        boolean unknown = true;
        // check if device is a physical device
        if(device instanceof PhysicalDevice) {
            final PhysicalDevice physicalDevice = (PhysicalDevice)device;
            final DoubleProperty powerUsageProperty = physicalDevice.getPowerUsage();
            if((powerUsageProperty != null) && (powerUsageProperty.getSensor() != null) && (powerUsageProperty.getValue() != null)) {
                unknown = false;
                powerUsageSum = powerUsageProperty.getValue().intValue();
            }

            Integer subDevicePowerUsage;
            synchronized(physicalDevice.getSubDevice()) {
                for(final PhysicalDevice subDevice : physicalDevice.getSubDevice()) {
                    subDevicePowerUsage = getPowerUsage(subDevice);
                    if(subDevicePowerUsage != null) {
                        // power usage known, add to sum
                        powerUsageSum += subDevicePowerUsage.intValue();
                        unknown = false;
                    }
                }
            }
        }
        if(unknown) {
            // unknown, return null
            return null;
        }
        // at least one power usage sensor set, return the sum
        return Integer.valueOf(powerUsageSum);
    }

    /**
     * Gets the total power usage.
     * 
     * @param environment
     *            the environment
     * @return the total power usage of all devices in the environment
     */
    public static double getPowerUsage(final Environment environment) {
        if(environment == null) {
            throw new IllegalArgumentException("environment argument must not be null in method getPowerUsage");
        }
        final EList<Device> devices = environment.getDevices();
        if(devices.isEmpty()) {
            return 0;
        }
        double totalPowerUsage = 0;

        Integer devicePowerUsage;
        synchronized(devices) {
            for(final Device device : devices) {
                devicePowerUsage = getPowerUsage(device);
                if(devicePowerUsage != null) {
                    totalPowerUsage += devicePowerUsage.intValue();
                }
            }
        }
        return totalPowerUsage;
    }

    /**
     * Returns the power usage of all devices in a place.
     * 
     * @param place
     *            place to sum power usage
     * @return double - power usage sum of all devices within a place
     */
    public static int getPowerUsage(final Place place) {
        if(place == null) {
            return 0;
        }
        // iterate through elements of the place
        int powerUsageSum = 0;
        Integer devicePowerUsage;
        synchronized(place.getElements()) {
            for(final ElementWithPosition element : place.getElements()) {
                // if element is a device, add its power usage to sum
                if(element instanceof Device) {
                    devicePowerUsage = EnvironmentUtility.getPowerUsage((Device)element);
                    if(devicePowerUsage != null) {
                        powerUsageSum += devicePowerUsage.intValue();
                    }
                }
            }
        }
        return powerUsageSum;
    }

    /**
     * Gets the powerusage value of the corresponding device.
     * 
     * @param device
     *            the device
     * @return the power usage value
     */
    public static double getPowerUsageValue(final PhysicalDevice device) {
        if(device == null) {
            throw new IllegalArgumentException("device argument must not be null in method getPowerUsageValue ");
        }
        final DoubleProperty value = device.getPowerUsage();
        if(value != null) {
            return value.getValue();
        } else {
            return 0;
        }

    }

    /**
     * Gets the average temperature of a place.
     * 
     * @param place
     *            the place, <code>null</code> disallowed
     * @return Double - average temperature in the place or <code>null</code> if
     *         no temperature sensor exists in the place
     * */
    public static Double getTemperature(final Place place) {
        if(place == null) {
            throw new IllegalArgumentException("place argument must not be null in method getTemperature");
        }
        return place.getTemperatureAverage();
    }

    /**
     * <code>getThirdPartyAssistants</code> returns all
     * {@link ThirdPartyAssistant}s found in an {@link Environment}.
     * 
     * @param environment
     *            environment to search for assistants
     * @return Set&lt;ThirdPartyAssistant&gt; - all {@link ThirdPartyAssistant}s
     *         found in <code>environment</code>
     */
    public static Set<ThirdPartyAssistant> getThirdPartyAssistants(final Environment environment) {
        return getAssistantsOfType(environment, ThirdPartyAssistant.class);
    }

    /**
     * Gets the total power usage.
     * 
     * @param environment
     *            the environment
     * @return the total power usage
     */
    public static double getTotalPowerUsage(final Environment environment) {
        if(environment == null) {
            throw new IllegalArgumentException("environment argument must not be null in method getTotalPowerUsage");
        }
        final EList<Device> devices = environment.getDevices();

        double totalPowerUsage = 0;

        for(final Device device : devices) {
            if(device instanceof PhysicalDevice) {
                final DoubleProperty power = ((PhysicalDevice)device).getPowerUsage();
                if(power != null) {
                    final double value = ((PhysicalDevice)device).getPowerUsage().getValue();
                    totalPowerUsage += value;
                }

            }
        }
        return totalPowerUsage;
    }

    /**
     * <code>getUser</code> retrieves a {@link User} with a specified ID from an
     * {@link Environment}.
     * 
     * @param environment
     *            environment to search
     * @param identifier
     *            id of the user
     * @return User - {@link User} with the specified ID or <code>null</code> if
     *         no such user has been found
     * @throws IllegalArgumentException
     *             if any argument is <code>null</code>
     */
    public static User getUser(final Environment environment, final String identifier) {
        return getEnvironmentElement(environment.getUsers(), identifier);
    }

    /**
     * Returns a {@link Window} with the specific id.
     * 
     * @param environment
     *            The {@link Environment} where the to get the {@link Window}
     *            from.
     * @param windowId
     *            The id of the {@link Window}.
     * @return A {@link Window} with the id <code>windowId</code>.
     */
    public static Window getWindow(final Environment environment, final String windowId) {
        if(environment == null) {
            throw new IllegalArgumentException("environment argument must not be null in method getUser");
        }

        final Iterable<Place> places = environment.getPlaces();

        if(places == null) {
            // no places where a door could be
            return null;
        }

        Collection<Window> windows;

        synchronized(places) {
            for(final Place place : places) {
                windows = place.getWindows();

                if((windows == null) || windows.isEmpty()) {
                    // no door in this current place
                    continue;
                }

                synchronized(windows) {
                    for(final Window window : windows) {
                        if(windowId.equals(window.getId())) {
                            return window;
                        }
                    }
                }// synchronized(doors)
            }// for places
        }// synchronized(places)

        // not found
        return null;
    }

    /**
     * <code>observePlaces</code> observes an {@link Environment} and informs
     * the <code>placeObserver</code> about new and removed {@link Place}s. To
     * stop the observing the returned {@link Disposeable} should be disposed
     * {@link Disposeable#dispose()}.
     * 
     * @param environment
     *            environment to observe
     * @param placeObserver
     *            observer to be notified about places
     * @return Disposeable - object to dispose when <code>environment</code>
     *         should no longer be observed
     * @throws IllegalArgumentException
     *             if any argument is <code>null</code>
     */
    public static Disposeable observePlaces(final Environment environment, final PlaceObserver placeObserver) {
        return new DisposeableAdapter(new PlaceAdapter(environment, placeObserver));
    }

    public static boolean removeCurrentAssistantFromUser(final Environment environment, final String userId, final String assistantId) {
        final User user = getUser(environment, userId);
        if(user == null) {
            return false;
        }
        final Assistant assistant = getAssistant(environment, assistantId);
        if(assistant == null) {
            return false;
        }
        user.removeCurrentAssistant(assistant);
        return true;
    }

    /**
     * Fixes <code>null</code> position of {@link ElementWithPosition} of the
     * {@link Environment}. If an {@link ElementWithPosition} has a
     * {@link Place} but no position, then a position at the {@link Place} is
     * assigned to the {@link ElementWithPosition}.
     * 
     * @param elementWithPosition
     *            The {@link ElementWithPosition} to fix.
     * @param environment
     *            The {@link Environment} of the {@link ElementWithPosition} to
     *            get the {@link Place}s from.
     * @param elementClass
     *            The class of the element, used for the message.
     * @return A {@link List} of messages describing the fixes.
     * @since 1.3.27
     */
    private static String fixMissingPosition(ElementWithPosition elementWithPosition, Environment environment, String elementClass) {
        StringBuilder sb = new StringBuilder();

        if(elementWithPosition.getPosition() == null) {
            if(elementWithPosition.getPlace() != null) {
                int areaIndex = 0;

                for(Area area : elementWithPosition.getPlace().getAreas()) {
                    Vector origin = area.getOrigin();

                    if(origin != null) {
                        sb.append("set missing position of ");
                        sb.append(elementClass);
                        sb.append(" ");
                        sb.append(elementWithPosition.getId());
                        sb.append(" to origin of Area with index ");
                        sb.append(areaIndex);
                        sb.append(" of Place ");
                        sb.append(elementWithPosition.getPlace().getId());
                        elementWithPosition.setPosition(origin.getX(), origin.getY(), origin.getZ());
                        return sb.toString();
                    }

                    areaIndex++;
                }

                sb.append("failed to fix missing position of ");
                sb.append(elementClass);
                sb.append(" ");
                sb.append(elementWithPosition.getId());
                sb.append(": Place ");
                sb.append(elementWithPosition.getPlace().getId());
                sb.append(" has no Areas or all Areas have no origin");
            } else {
                sb.append("failed to fix missing position of ");
                sb.append(elementClass);
                sb.append(" ");
                sb.append(elementWithPosition.getId());
                sb.append(": No Place is set");
            }
        }

        return sb.toString();
    }

    /**
     * Fixes the {@link Place} of an {@link ElementWithPosition} if is is not
     * set (correct).
     * 
     * @param elementWithPosition
     *            The {@link ElementWithPosition} to fix.
     * @param fixNullPlaces
     *            Indicates to set the {@link Place} of a {@link Device} if it
     *            is <code>null</code> (<code>true</code>) or to leave the
     *            {@link Place} untouched (<code>false</code>).
     * @param environment
     *            The {@link Environment} of the {@link ElementWithPosition}.
     * @param elementClass
     *            A {@link String} indication the class of the
     *            {@link EnvironmentElement}, which is used for the message.
     * @since 1.3.27
     */
    private static String fixPlaceInRealtionToPosition(ElementWithPosition elementWithPosition, boolean fixNullPlaces, Environment environment, String elementClass) {
        if(ContextModelVisualizer.LOG.isDebugEnabled()) {
            ContextModelVisualizer.LOG.debug("fix element " + elementWithPosition);
        }

        StringBuilder sb = new StringBuilder();

        if(elementWithPosition.getPosition() != null) {
            if(elementWithPosition.getPlace() != null) {
                if(!elementWithPosition.getPlace().contains(elementWithPosition.getPosition())) {
                    // Place of device is not a position of Device =>
                    // fix it
                    Place place = EnvironmentUtility.getPlaceContainingPosition(environment, elementWithPosition.getPosition());

                    if(place != null) {
                        sb.append("fix Place of ");
                        sb.append(elementClass);
                        sb.append(" with id ");
                        sb.append(elementWithPosition.getId());
                        sb.append(" from ");
                        sb.append(elementWithPosition.getPlace().getId());
                        sb.append(" to ");
                        sb.append(place.getId());
                        elementWithPosition.setPlace(place);
                    } else {
                        sb.append("failed to fix Place of ");
                        sb.append(elementClass);
                        sb.append(" with id ");
                        sb.append(elementWithPosition.getId());
                        sb.append(": The position of the Device does'nt belong to any Place of the Environment");
                    }
                }
            } else if(fixNullPlaces) {
                // place of Device is null => fix it
                Place place = EnvironmentUtility.getPlaceContainingPosition(environment, elementWithPosition.getPosition());

                if(place != null) {
                    sb.append("failed to fix Place of ");
                    sb.append(elementClass);
                    sb.append(" with id ");
                    sb.append(elementWithPosition.getId());
                    sb.append(" for null Place to ");
                    sb.append(place.getId());
                    elementWithPosition.setPlace(place);
                } else {
                    sb.append("failed to fix Place of ");
                    sb.append(elementClass);
                    sb.append(" with id ");
                    sb.append(elementWithPosition.getId());
                    sb.append(": The position of the Device doe'nt belong to any Place of the Environment");
                }
            }
        }

        return sb.toString();
    }

    /**
     * <code>EnvironmentUtility</code> constructor.
     */
    private EnvironmentUtility() {
        // hiding constructor of utility class
    }
}
