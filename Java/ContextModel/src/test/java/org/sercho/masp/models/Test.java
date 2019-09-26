/**
 * File     Test.java
 * Package  org.sercho.masp.models
 * Project  ContextModel
 * Date     26.09.2007
 * Web      http://www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.models;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.junit.Ignore;
import org.sercho.masp.models.Context.Area;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.EnvironmentElement;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.LocalisationProviderProxy;
import org.sercho.masp.models.Context.Place;
import org.sercho.masp.models.Context.Room;
import org.sercho.masp.models.Context.User;
import org.sercho.masp.models.Context.Vector;

import de.dailab.masp.models.MetaMetaModel.MetaModel;
import de.dailab.masp.models.MetaMetaModel.ecore.EcoreMetaModelConverter;

/**
 * <code>Test</code> is a simple class for tests.
 * 
 * @author Grzegorz Lehmann
 * @author Dirk Roscher
 * @since 1.0-alpha
 */
@Ignore
public final class Test {

    /**
     * <code>main</code> for tests
     * 
     * @param args
     *            program arguments, ignored
     */
    public static void main(final String[] args) {
        final ContextFactory factory = ContextPackage.eINSTANCE.getContextFactory();

        final Environment environment = factory.createEnvironment();

        environment.eAdapters().add(new EContentAdapter() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void notifyChanged(final Notification notification) {
                super.notifyChanged(notification);
                // printSituation(environment);
            }
        });

        // TEST STUFF
        loadRoomModel(environment, factory);
        loadUsers(environment, factory);

        new ContextObserver(environment, new ContextObserverObserver() {

            @Override
            public void updateContextRating(final User user, final List<InteractionResource> resources) {
                System.out.println("ContextObserverObserver.updateContextRating(): userId =" + user.getId());
                for(final InteractionResource resource : resources) {
                    System.out.println("contextRating for resource with id=" + resource.getId() + " is " + resource.getContextRating(user));
                }
            }

            @Override
            public void updateResources(final User user, final List<InteractionResource> resources) {
                System.out.println("ContextObserverObserver.updateResources(): user.getId()=" + user.getId() + "; resources=" + resources);
                for(final InteractionResource resource : resources) {
                    System.out.println("contextRating for resource with id=" + resource.getId() + " is " + resource.getContextRating(user));
                }
            }

            @Override
            public void updateUsers(final InteractionResource resource, final List<User> users) {
                System.out.println("ContextObserverObserver.updateUsers(): resource.getId()=" + resource.getId() + "; users=" + users);
                for(final User user : users) {
                    System.out.println("contextRating for user with id=" + user.getId() + " is " + resource.getContextRating(user));
                }
            }

        });

        loadContextProvider(environment, factory);

        // start the model
        final MetaModel metaModel = EcoreMetaModelConverter.convert(ContextPackage.eINSTANCE);
        metaModel.getMetaType().getMetaModel().start(metaModel);
        metaModel.start(environment);

        DummyClientDiscoverer.registerClient("SHARP_65", DummyClientDiscoverer.Type.COMPUTER);
        DummyClientDiscoverer.registerClient("KITCHEN", DummyClientDiscoverer.Type.COMPUTER);

        locProvider.setPosition(USER_ID_MARCO, DummyLocalisationProvider.POS_KITCHEN_PC);
        locProvider.setPosition(USER_ID_MARCO, new org.sercho.masp.context.providers.location.Vector(650, 500, 0));
    }

    private static final DummyLocalisationProvider locProvider = new DummyLocalisationProvider();

    static final String USER_ID_MARCO = "Marco";

    static final String USER_ID_GRZEGORZ = "Grzegorz";

    static final String USER_ID_SEBASTIAN = "Sebastian";

    static final String USER_ID_VEIT = "Veit";

    static final String USER_ID_DIRK = "Dirk";

    private static final Vector NO_POSITION;

    private static final Room NO_POSITION_ROOM;

    static {
        final ContextFactory factory = ContextPackage.eINSTANCE.getContextFactory();
        NO_POSITION = createVector(factory, -1, -1, -1);
        NO_POSITION_ROOM = factory.createRoom();
        NO_POSITION_ROOM.setId("NOTHING");
        NO_POSITION_ROOM.getAreas().add(createArea(factory, NO_POSITION, createVector(factory, 0, 0, 0)));
    }

    // width is along x axis
    private static final int WORKROOM_WIDTH = 300;

    private static final int WORKROOM_LENGTH = 600;

    private static final int WORKROOM_ORIGIN_X = -300;

    private static final int WORKROOM_ORIGIN_Y = 0;

    private static final int WORKROOM_ORIGIN_Z = 0;

    private static final int LIVING_ROOM_WIDTH = 580;

    private static final int LIVING_ROOM_LENGTH = 610;

    private static final int LIVING_ROOM_ORIGIN_X = 0;

    private static final int LIVING_ROOM_ORIGIN_Y = -50;

    private static final int LIVING_ROOM_ORIGIN_Z = 0;

    private static final int KITCHEN_WIDTH = 230;

    private static final int KITCHEN_LENGTH = 610;

    private static final int KITCHEN_ORIGIN_X = LIVING_ROOM_WIDTH;

    private static final int KITCHEN_ORIGIN_Y = 0;

    private static final int KITCHEN_ORIGIN_Z = 0;

    private static final int DEFAULT_HEIGHT = 220;

    private static void loadContextProvider(final Environment environment, final ContextFactory factory) {
        final LocalisationProviderProxy localisationProvider = factory.createLocalisationProviderProxy();
        localisationProvider.setApi(locProvider);
        environment.getProviders().add(localisationProvider);

        // Map<String, String> mapping = new HashMap<String, String>();
        // mapping.put("Kind", USER_ID_MARCO);
        // mapping.put("Robot", USER_ID_GRZEGORZ);
        // ContextModelLocalisationProvider localisationProviderUbisense =
        // factory.createContextModelLocalisationProvider();
        // localisationProviderUbisense.setApi(new UbisenseProvider());
        // localisationProviderUbisense.setIdMapping(mapping);
        // localisationProviderUbisense.setScaleFactor(100.0);
        // environment.getProviders().add(localisationProviderUbisense);

        // MASPClientDiscoverer clientDiscoverer =
        // factory.createMASPClientDiscoverer();
        // clientDiscoverer.setApi(DummyClientDiscoverer.getInstance());
        // clientDiscoverer.setIdMapping(new HashMap<String, String>());
        // environment.getProviders().add(clientDiscoverer);
    }

    private static void loadUsers(final Environment environment, final ContextFactory factory) {
        final User marco = factory.createUser();
        marco.setId(USER_ID_MARCO);
        marco.setPosition(NO_POSITION);
        marco.setFollowMe(true);
        NO_POSITION_ROOM.getElements().add(marco);

        final User grzegorz = factory.createUser();
        grzegorz.setId(USER_ID_GRZEGORZ);
        grzegorz.setPosition(NO_POSITION);
        grzegorz.setFollowMe(true);
        NO_POSITION_ROOM.getElements().add(grzegorz);

        final User sebastian = factory.createUser();
        sebastian.setId(USER_ID_SEBASTIAN);
        sebastian.setPosition(NO_POSITION);
        sebastian.setFollowMe(true);
        NO_POSITION_ROOM.getElements().add(sebastian);

        final User dirk = factory.createUser();
        dirk.setId(USER_ID_DIRK);
        dirk.setPosition(NO_POSITION);
        dirk.setFollowMe(true);
        NO_POSITION_ROOM.getElements().add(dirk);

        final User veit = factory.createUser();
        veit.setId(USER_ID_VEIT);
        veit.setPosition(NO_POSITION);
        veit.setFollowMe(true);
        NO_POSITION_ROOM.getElements().add(veit);
    }

    private static void loadRoomModel(final Environment environment, final ContextFactory factory) {
        // KITCHEN
        final Room kitchen = factory.createRoom();
        kitchen.setId("KITCHEN");
        kitchen.getAreas().add(createArea(factory, createVector(factory, KITCHEN_ORIGIN_X, KITCHEN_ORIGIN_Y, KITCHEN_ORIGIN_Z), createVector(factory, KITCHEN_WIDTH, KITCHEN_LENGTH, DEFAULT_HEIGHT)));
        environment.getPlaces().add(kitchen);
        // LIVING_ROOM
        final Room livingRoom = factory.createRoom();
        livingRoom.setId("LIVING_ROOM");
        livingRoom.getAreas().add(createArea(factory, createVector(factory, LIVING_ROOM_ORIGIN_X, LIVING_ROOM_ORIGIN_Y, LIVING_ROOM_ORIGIN_Z), createVector(factory, LIVING_ROOM_WIDTH, LIVING_ROOM_LENGTH, DEFAULT_HEIGHT)));
        environment.getPlaces().add(livingRoom);
        // WORKROOM
        final Room workRoom = factory.createRoom();
        workRoom.setId("WORKROOM");
        workRoom.getAreas().add(createArea(factory, createVector(factory, WORKROOM_ORIGIN_X, WORKROOM_ORIGIN_Y, WORKROOM_ORIGIN_Z), createVector(factory, WORKROOM_WIDTH, WORKROOM_LENGTH, DEFAULT_HEIGHT)));
        environment.getPlaces().add(workRoom);
    }

    private static Area createArea(final ContextFactory factory, final Vector origin, final Vector span) {
        final Area result = factory.createArea();
        result.setOrigin(origin);
        result.setSpan(span);
        return result;
    }

    private static Vector createVector(final ContextFactory factory, final int x, final int y, final int z) {
        final Vector result = factory.createVector();
        result.setCoordinates(x, y, z);
        return result;
    }

    static void printSituation(final Environment e) {
        final StringBuffer buffer = new StringBuffer("-------------------------\n");
        for(final Place r : e.getPlaces()) {
            buffer.append(toString(r));
            for(final User u : r.getUsers()) {
                buffer.append("\n\t");
                buffer.append(toString(u));
                if(!u.getResources().isEmpty()) {
                    buffer.append(" is using");
                    for(final InteractionResource ir : u.getResources()) {
                        buffer.append(' ');
                        buffer.append(toString(ir));
                    }
                }
            }
            for(final InteractionResource ir : r.getInteractionResources()) {
                if(ir.getUser() == null) {
                    buffer.append("\n\t");
                    buffer.append(toString(ir));
                    buffer.append(" is unused");
                }
            }
            buffer.append('\n');
        }
        System.out.println(buffer.toString());
    }

    private static String toString(final EnvironmentElement element) {
        return element.eClass().getName() + " \"" + element.getId() + '"';
    }
}