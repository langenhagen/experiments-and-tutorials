/**
 * File     DistributionDummy.java
 * Package  org.sercho.masp.models
 * Project  ContextModel
 * Date     02.12.2007
 * Web      http://www.dai-labor.de
 * 
 * @author  Dirk Roscher
 */
package org.sercho.masp.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.SingletonAdapterImpl;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.Place;
import org.sercho.masp.models.Context.User;
import org.sercho.masp.models.Context.Vector;

/**
 * <code>ContextObserver</code> observes an {@link Environment} for different
 * changes. See inner adapter classes for details.
 * 
 * @author Dirk Roscher
 */
public class ContextObserver {

    /**
     * <code>ClientObserver</code> observes {@link MASPClient}s and look if an
     * {@link InteractionResource} was added or removed. If an resources was
     * added {@link ContextObserver#newInteractionResource(InteractionResource)}
     * is called. If an resource was removed
     * {@link ContextObserver#interactionResourceGone(InteractionResource)} is
     * called.
     */
    private class ClientObserver extends SingletonAdapterImpl {

        /**
         * <code>ContextObserver.ClientObserver</code> constructor.
         */
        ClientObserver() {
            // increased visibility
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public synchronized void notifyChanged(final Notification notification) {
            // look if ir was added
            switch(notification.getFeatureID(Device.class)) {
                case ContextPackage.DEVICE__RESOURCES:
                    if(notification.getEventType() == Notification.ADD) {
                        InteractionResource resource = null;
                        try {
                            resource = (InteractionResource)notification.getNewValue();
                        }
                        catch(final ClassCastException e) {
                            System.err.println("RoomObserver.notifyChanged(): " + "added resource no MASPClient! e=" + e);
                            return;
                        }
                        newInteractionResource(resource);
                    } else if(notification.getEventType() == Notification.REMOVE) {
                        InteractionResource resource = null;
                        try {
                            resource = (InteractionResource)notification.getNewValue();
                        }
                        catch(final ClassCastException e) {
                            System.err.println("RoomObserver.notifyChanged(): " + "removed resource no MASPClient! e=" + e);
                            return;
                        }
                        interactionResourceGone(resource);
                    } else {
                        System.out.println("ClientObserver.notifyChanged(): " + "something changed within resources! notification=" + notification);
                    }
            }
        }
    }

    /**
     * <code>EnvironmentObserver</code> observes the environment for new
     * {@link MASPClient}s. If a client is added
     * {@link ContextObserver#newInteractionResource(InteractionResource)} is
     * called for every resource of the new client and {@link ClientObserver} is
     * added.
     */
    private class EnvironmentObserver extends SingletonAdapterImpl {

        /**
         * {@inheritDoc}
         */
        @Override
        public void notifyChanged(final Notification notification) {
            // look if client was added
            switch(notification.getFeatureID(Environment.class)) {
                case ContextPackage.ENVIRONMENT__DEVICES:
                    if(notification.getEventType() == Notification.ADD) {
                        Device client = null;
                        try {
                            client = (Device)notification.getNewValue();
                        }
                        catch(final ClassCastException e) {
                            System.err.println("EnvironmentObserver.notifyChanged(): added resource no MASPClient! exception=" + e);
                        }
                        initClient(client);
                    } else if(notification.getEventType() == Notification.REMOVE) {
                        // nothing needs to be done at the moment. The
                        // ClientDiscoverer has
                        // to remove all interaction resources and the masp
                        // client afterwards
                    }
            }
        }

        private void initClient(final Device client) {
            // add observer
            client.eAdapters().add(ContextObserver.this.clientObserver);
            final List<InteractionResource> resources = client.getResources();
            synchronized(resources) {
                // initial status of irs
                for(final InteractionResource ir : resources) {
                    newInteractionResource(ir);
                }
            }
        }
    }

    /**
     * <code>UserObserver</code> observes {@link User}s for changing position
     * and calls {@link ContextObserver#positionChanged(User)}.
     */
    private class UserObserver extends SingletonAdapterImpl {

        /**
         * {@inheritDoc}
         */
        @Override
        public synchronized void notifyChanged(final Notification notification) {
            // look if user pos changed
            switch(notification.getFeatureID(User.class)) {
                case ContextPackage.USER__POSITION:
                    if(notification.getEventType() == Notification.SET) {
                        positionChanged((User)notification.getNotifier());
                    } else {
                        System.out.println("UserObserver.notifyChanged(): " + "something changed within users' position! notification=" + notification);
                    }
            }
        }
    }

    /**
     * <code>InteractionResourceObserver</code> observes
     * {@link InteractionResource}s for changing position and calls
     * {@link ContextObserver#positionChanged(InteractionResource)}.
     */
    private class InteractionResourceObserver extends SingletonAdapterImpl {

        /**
         * {@inheritDoc}
         */
        @Override
        public synchronized void notifyChanged(final Notification notification) {
            // look if interaction resource pos changed
            switch(notification.getFeatureID(InteractionResource.class)) {
                case ContextPackage.INTERACTION_RESOURCE__POSITION:
                    if(notification.getEventType() == Notification.SET) {
                        positionChanged((InteractionResource)notification.getNotifier());
                    } else {
                        System.out.println("UserObserver.notifyChanged(): " + "something changed within InteractionResources' position! notification=" + notification);
                    }
            }
        }
    }

    // environment to be observed
    Environment environment;

    ClientObserver clientObserver = new ClientObserver();

    private final EnvironmentObserver environmentObserver = new EnvironmentObserver();

    private final UserObserver userObserver = new UserObserver();

    private final InteractionResourceObserver resourceObserver = new InteractionResourceObserver();

    private final ContextObserverObserver observer;

    /**
     * <code>ContextObserver</code> constructor. Add needed observers to
     * <code>environment</code>.
     * 
     * @param myEnvironment
     *            environment to observe.
     * @param myObserver
     *            observer to notify on changes
     */
    public ContextObserver(final Environment myEnvironment,
            final ContextObserverObserver myObserver) {
        this.environment = myEnvironment;
        this.observer = myObserver;
        registerNewIRObserver();
        registerUserObserver();
    }

    /**
     * <code>registerUserObserver</code> registers <code>userObserver</code> for
     * every user within <code>environment</code>.
     */
    private void registerUserObserver() {
        final List<Place> allPlaces = new ArrayList<Place>(this.environment.getPlaces());
        for(final Place place : allPlaces) {
            for(final User user : new ArrayList<User>(place.getUsers())) {
                // calculate initial situation
                calculateSituation(user);
                // add observer
                System.out.println("ContextObserver.registerUserObserver(): register observer to user with id=" + user.getId());
                user.eAdapters().add(this.userObserver);
            }
        }
    }

    /**
     * <code>registerNewIRObserver</code> registers
     * <code>environmentObserver</code> to <code>environment</code>.
     */
    private void registerNewIRObserver() {
        this.environment.eAdapters().add(this.environmentObserver);
        for(final Device client : new ArrayList<Device>(this.environment.getDevices())) {
            // add observer
            client.eAdapters().add(this.clientObserver);
            // initial status of irs
            for(final InteractionResource ir : new ArrayList<InteractionResource>(client.getResources())) {
                newInteractionResource(ir);
            }
        }
    }

    /**
     * <code>newInteractionResource</code> is called when the observer finds a
     * new interaction resource.
     * 
     * @param resource
     *            the found resource
     */
    public void newInteractionResource(final InteractionResource resource) {
        // System.out.println("ContextObserver.newInteractionResource()=" +
        // resource);
        synchronized(resource) {
            // initial situation calculation
            if(resource.getPosition() != null) {
                calculateSituation(resource);
            }
            // add observer
            resource.eAdapters().add(this.resourceObserver);
        }
    }

    /**
     * <code>interactionResourceGone</code> is called by
     * {@link InteractionResourceObserver} when observer noticed that an IR was
     * lost.
     * 
     * @param resource
     *            the lost resource
     */
    public void interactionResourceGone(final InteractionResource resource) {
        synchronized(resource) {
            this.observer.updateUsers(resource, new LinkedList<User>());
        }
    }

    /**
     * <code>positionChanged</code> is called by observer when position of
     * <code>user</code> changed.
     * 
     * @param user
     *            user position has changed.
     */
    public void positionChanged(final User user) {
        if(user.isFollowMe()) {
            this.calculateSituation(user);
        } else {
            System.out.println("ContextObserver.userPosChanged(): followMe is not activated --> no recalculation of situation");
        }
    }

    /**
     * Calculates the new environment situation when position of
     * <code>user</code> has changed. Calculates if {@link Place} has changed
     * and if usable {@link InteractionResource}s have changed.
     * 
     * @param user
     *            user to calculate situation for
     */
    protected void calculateSituation(final User user) {
        synchronized(user) {
            if(placeCalculation(user)) {
                final List<InteractionResource> newIRs = resourceCalculation(user);
                this.observer.updateResources(user, newIRs);
            } else {
                this.observer.updateContextRating(user, user.getPlace().getInteractionResources());
            }
        }
    }

    private List<InteractionResource> resourceCalculation(final User user) {
        // look if usable IRs have changed
        final List<InteractionResource> usableIRs = new ArrayList<InteractionResource>(5);

        final Place placeOfUser = user.getPlace();

        if(placeOfUser != null) {
            final List<InteractionResource> resources = placeOfUser.getInteractionResources();
            synchronized(resources) {
                for(final InteractionResource resource : resources) {
                    // IR already used?
                    if(resource.getUser() == null) {
                        usableIRs.add(resource);
                    }
                }
            }
        }
        return usableIRs;
    }

    /**
     * <code>roomCalculation</code> calculates the {@link Room} for a
     * {@link User} based on her position.
     * 
     * @param user
     *            user calculation will be done for
     * @return true if user changes the room otherwise false
     */
    /*
     * private boolean roomCalculation(final User user) { // look if user has
     * changed room if(!user.getRoom().contains(user.getPosition())) { // look
     * for other rooms user.setRoom(getRoom(this.environment.getRooms(),
     * user.getPosition()));
     * System.out.println("ContextObserver.roomCalculation(): Added user " +
     * user.getId() + " to room " + user.getRoom()); return true; }
     * System.out.println("ContextObserver.roomCalculation(): User " +
     * user.getId() + " stays in room " + user.getRoom()); return false; }
     */

    /**
     * <code>placeCalculation</code> calculates the {@link Place} for a
     * {@link User} based on her position.
     * 
     * @param user
     *            user calculation will be done for
     * @return true if user changes the place otherwise false
     */
    private boolean placeCalculation(final User user) {
        // look if user has changed place
        if(!user.getPlace().contains(user.getPosition())) {
            // look for other places
            user.setPlace(getPlace(this.environment.getPlaces(), user.getPosition()));
            System.out.println("ContextObserver.placeCalculation(): Added user " + user.getId() + " to place " + user.getPlace());
            return true;
        }
        System.out.println("ContextObserver.placeCalculation(): User " + user.getId() + " stays at place " + user.getPlace());
        return false;
    }

    /**
     * <code>positionChanged</code> is called by observer when position of
     * <code>resource</code> changed.
     * 
     * @param resource
     *            resource which position changed.
     */
    protected void positionChanged(final InteractionResource resource) {
        // calculate new situation
        calculateSituation(resource);
    }

    /**
     * Calculates the environment situation of <code>resource</code>. Calculates
     * if {@link Place} has changed or new {@link User}s can use this resource.
     * 
     * @param resource
     */
    protected void calculateSituation(final InteractionResource resource) {
        // resource belong to user?
        final User irUser = resource.getUser();
        if(irUser != null) {
            if(irUser.getPlace().contains(resource.getPosition())) {
                return;
            }
        }
        Place irPlace = resource.getPlace();
        if(irPlace == null) {
            // calculate place
            final Place place = getPlace(this.environment.getPlaces(), resource.getPosition());
            if(place != null) {
                resource.setPlace(place);
                irPlace = place;
            }
        } else {
            // room changed?
            if(!irPlace.contains(resource.getPosition())) {
                // room changed --> calculate new room
                final Place place = getPlace(this.environment.getPlaces(), resource.getPosition());
                if(place != null) {
                    resource.setPlace(place);
                    irPlace = place;
                }
            }
        }
        if(irPlace != null) {
            this.observer.updateUsers(resource, new ArrayList<User>(resource.getPlace().getUsers()));
        }
    }

    private Place getPlace(final List<Place> places, final Vector position) {
        synchronized(places) {
            for(final Place place : places) {
                if(place.contains(position)) {
                    return place;
                }
            }
        }
        return null;
    }
}
