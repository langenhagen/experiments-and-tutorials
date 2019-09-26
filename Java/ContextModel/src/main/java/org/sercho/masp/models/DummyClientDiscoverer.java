/**
 * File     DummyClientDiscoverer.java
 * Package  org.sercho.masp.models
 * Project  ContextModel
 * Date     05.12.2007
 * Web      http://www.dai-labor.de
 * 
 * @author  Dirk Roscher
 */
package org.sercho.masp.models;

import static org.sercho.masp.models.DummyLocalisationProvider.CLIENT_ID_DELL_30;
import static org.sercho.masp.models.DummyLocalisationProvider.CLIENT_ID_DRAGON;
import static org.sercho.masp.models.DummyLocalisationProvider.CLIENT_ID_GESTURE_DEVICE;
import static org.sercho.masp.models.DummyLocalisationProvider.CLIENT_ID_KITCHEN;
import static org.sercho.masp.models.DummyLocalisationProvider.CLIENT_ID_LIVING_ROOM_TOUCH;
import static org.sercho.masp.models.DummyLocalisationProvider.CLIENT_ID_N800;
import static org.sercho.masp.models.DummyLocalisationProvider.CLIENT_ID_SHARP_65;
import static org.sercho.masp.models.DummyLocalisationProvider.CLIENT_ID_SHEM_TOUCH;
import static org.sercho.masp.models.DummyLocalisationProvider.CLIENT_ID_WORKROOM;
import static org.sercho.masp.models.DummyLocalisationProvider.IR_ID_DELL_30_DISPLAY;
import static org.sercho.masp.models.DummyLocalisationProvider.IR_ID_DELL_30_KEYBOARD;
import static org.sercho.masp.models.DummyLocalisationProvider.IR_ID_DRAGON_MICROPHONE;
import static org.sercho.masp.models.DummyLocalisationProvider.IR_ID_GESTURE_DEVICE;
import static org.sercho.masp.models.DummyLocalisationProvider.IR_ID_KITCHEN_DISPLAY;
import static org.sercho.masp.models.DummyLocalisationProvider.IR_ID_KITCHEN_KEYBOARD;
import static org.sercho.masp.models.DummyLocalisationProvider.IR_ID_LIVING_ROOM_TOUCH_DISPLAY;
import static org.sercho.masp.models.DummyLocalisationProvider.IR_ID_LIVING_ROOM_TOUCH_KEYBOARD;
import static org.sercho.masp.models.DummyLocalisationProvider.IR_ID_N800_DISPLAY;
import static org.sercho.masp.models.DummyLocalisationProvider.IR_ID_N800_KEYBOARD;
import static org.sercho.masp.models.DummyLocalisationProvider.IR_ID_SHARP_65_DISPLAY;
import static org.sercho.masp.models.DummyLocalisationProvider.IR_ID_SHARP_65_KEYBOARD;
import static org.sercho.masp.models.DummyLocalisationProvider.IR_ID_SHEM_TOUCH_DISPLAY;
import static org.sercho.masp.models.DummyLocalisationProvider.IR_ID_SHEM_TOUCH_KEYBOARD;
import static org.sercho.masp.models.DummyLocalisationProvider.IR_ID_WORKROOM_DISPLAY;
import static org.sercho.masp.models.DummyLocalisationProvider.IR_ID_WORKROOM_KEYBOARD;
import static org.sercho.masp.models.DummyLocalisationProvider.USER_ID_MARCO;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.Display;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.GestureIR;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.Keyboard;
import org.sercho.masp.models.Context.Loudspeaker;
import org.sercho.masp.models.Context.Microphone;
import org.sercho.masp.models.Context.Room;
import org.sercho.masp.models.Context.providers.ClientAPI;
import org.sercho.masp.models.Context.providers.ClientDiscoveryObserver;
import org.sercho.masp.models.Context.providers.ClientDiscoveryProvider;
import org.sercho.masp.models.Context.providers.FixedInteractionResourcesClientAPI;

/**
 * <code>DummyClientDiscoverer</code>
 * 
 * @author Dirk Roscher
 */
public class DummyClientDiscoverer implements ClientDiscoveryProvider {

    /**
     * <code>Type</code> denotes the possible devices types from the "old" masp.
     * 
     * @author Dirk Roscher
     */
    public enum Type {
        /**
         * <code>COMPUTER</code>
         */
        COMPUTER,
        /**
         * <code>COMPUTER</code>
         */
        MOBILE,
        /**
         * <code>VOICE_IN</code>
         */
        VOICE_IN,
        /**
         * <code>VOICE_OUT</code>
         */
        VOICE_OUT,
        /**
         * <code>GESTURE</code>
         */
        GESTURE;
    }

    private static DummyClientDiscoverer INSTANCE = new DummyClientDiscoverer();

    ClientDiscoveryObserver observer;

    static ContextFactory factory = ContextPackage.eINSTANCE.getContextFactory();

    static Map<String, Set<InteractionResource>> irs = new HashMap<String, Set<InteractionResource>>();

    static Map<String, ClientAPI> clientAPIs = new HashMap<String, ClientAPI>();

    static Map<String, Set<InteractionResource>> configuredIRs = new HashMap<String, Set<InteractionResource>>();

    private DummyClientDiscoverer() {
        // hiding constructor of singleton
    }

    static Display createDisplay(final String id, final String name, final int size, final int xPixels, final int yPixels) {
        final Display display = factory.createDisplay();
        display.setId(id);
        display.setSize(size);
        display.setXPixels(xPixels);
        display.setYPixels(yPixels);
        display.setName(name);
        return display;
    }

    static Keyboard createKeyboard(final String id, final String name) {
        final Keyboard keyboard = factory.createKeyboard();
        keyboard.setId(id);
        keyboard.setName(name);
        return keyboard;
    }

    static Loudspeaker createLoudspeaker(final String id, final String name) {
        final Loudspeaker loudspeaker = factory.createLoudspeaker();
        loudspeaker.setId(id);
        loudspeaker.setName(name);
        return loudspeaker;
    }

    static Microphone createMicrophone(final String id, final String name) {
        final Microphone microphone = factory.createMicrophone();
        microphone.setId(id);
        microphone.setName(name);
        return microphone;
    }

    static GestureIR createGestureIR(final String id, final String name) {
        final GestureIR gestureIR = factory.createGestureIR();
        gestureIR.setId(id);
        gestureIR.setName(name);
        return gestureIR;
    }

    static {
        Set<InteractionResource> irs = new HashSet<InteractionResource>();
        irs.add(createDisplay(IR_ID_SHARP_65_DISPLAY, DummyLocalisationProvider.IR_NAME_SHARP_65_DISPLAY, 65, 1360, 768));
        irs.add(createKeyboard(IR_ID_SHARP_65_KEYBOARD, IR_ID_SHARP_65_KEYBOARD));
        configuredIRs.put(CLIENT_ID_SHARP_65, irs);

        irs = new HashSet<InteractionResource>();
        irs.add(createDisplay(IR_ID_DELL_30_DISPLAY, DummyLocalisationProvider.IR_NAME_DELL_30_DISPLAY, 30, 2560, 1600));
        irs.add(createKeyboard(IR_ID_DELL_30_KEYBOARD, IR_ID_DELL_30_KEYBOARD));
        configuredIRs.put(CLIENT_ID_DELL_30, irs);

        irs = new HashSet<InteractionResource>();
        irs.add(createDisplay(IR_ID_LIVING_ROOM_TOUCH_DISPLAY, DummyLocalisationProvider.IR_NAME_LIVING_ROOM_TOUCH_DISPLAY, 15, 1024, 768));
        irs.add(createKeyboard(IR_ID_LIVING_ROOM_TOUCH_KEYBOARD, IR_ID_LIVING_ROOM_TOUCH_KEYBOARD));
        configuredIRs.put(CLIENT_ID_LIVING_ROOM_TOUCH, irs);

        irs = new HashSet<InteractionResource>();
        irs.add(createDisplay(IR_ID_KITCHEN_DISPLAY, DummyLocalisationProvider.IR_NAME_KITCHEN_DISPLAY, 18, 1280, 1024));
        irs.add(createKeyboard(IR_ID_KITCHEN_KEYBOARD, IR_ID_KITCHEN_KEYBOARD));
        configuredIRs.put(CLIENT_ID_KITCHEN, irs);

        irs = new HashSet<InteractionResource>();
        irs.add(createDisplay(IR_ID_SHEM_TOUCH_DISPLAY, DummyLocalisationProvider.IR_NAME_SHEM_TOUCH_DISPLAY, 15, 1024, 768));
        irs.add(createKeyboard(IR_ID_SHEM_TOUCH_KEYBOARD, IR_ID_SHEM_TOUCH_KEYBOARD));
        configuredIRs.put(CLIENT_ID_SHEM_TOUCH, irs);

        irs = new HashSet<InteractionResource>();
        irs.add(createDisplay(IR_ID_WORKROOM_DISPLAY, DummyLocalisationProvider.IR_NAME_WORKROOM_DISPLAY, 32, 1360, 768));
        irs.add(createKeyboard(IR_ID_WORKROOM_KEYBOARD, IR_ID_WORKROOM_KEYBOARD));
        configuredIRs.put(CLIENT_ID_WORKROOM, irs);

        irs = new HashSet<InteractionResource>();
        irs.add(createDisplay(DummyLocalisationProvider.IR_ID_WORKROOM_DOOR_DISPLAY, DummyLocalisationProvider.IR_NAME_WORKROOM_DOOR_DISPLAY, 32, 1360, 768));
        irs.add(createKeyboard(DummyLocalisationProvider.IR_ID_WORKROOM_DOOR_KEYBOARD, DummyLocalisationProvider.IR_ID_WORKROOM_DOOR_KEYBOARD));
        configuredIRs.put(DummyLocalisationProvider.CLIENT_ID_WORKROOM_DOOR, irs);

        irs = new HashSet<InteractionResource>();
        InteractionResource resource = createDisplay(IR_ID_N800_DISPLAY, DummyLocalisationProvider.IR_NAME_N800_DISPLAY, 6, 800, 480);
        resource.setMobile(Boolean.TRUE);
        irs.add(resource);
        resource = createKeyboard(IR_ID_N800_KEYBOARD, IR_ID_N800_KEYBOARD);
        resource.setMobile(Boolean.TRUE);
        irs.add(resource);
        configuredIRs.put(CLIENT_ID_N800, irs);

        irs = new HashSet<InteractionResource>();
        resource = createMicrophone(IR_ID_DRAGON_MICROPHONE, DummyLocalisationProvider.IR_NAME_DRAGON_MICROPHONE);
        resource.setPersonal(USER_ID_MARCO);
        irs.add(resource);
        configuredIRs.put(CLIENT_ID_DRAGON, irs);

        irs = new HashSet<InteractionResource>();
        resource = createGestureIR(IR_ID_GESTURE_DEVICE, DummyLocalisationProvider.IR_NAME_GESTURE_DEVICE);
        resource.setPersonal(USER_ID_MARCO);
        irs.add(resource);
        configuredIRs.put(CLIENT_ID_GESTURE_DEVICE, irs);

        irs = new HashSet<InteractionResource>();
        resource = createLoudspeaker(DummyLocalisationProvider.IR_ID_KITCHEN_LOUDSPEAKER, DummyLocalisationProvider.IR_NAME_KITCHEN_LOUDSPEAKER);
        irs.add(resource);
        configuredIRs.put(DummyLocalisationProvider.CLIENT_ID_KITCHEN_LOUDSPEAKER, irs);

        irs = new HashSet<InteractionResource>();
        resource = createLoudspeaker(DummyLocalisationProvider.IR_ID_LIVING_ROOM_LOUDSPEAKER, DummyLocalisationProvider.IR_NAME_LIVING_ROOM_LOUDSPEAKER);
        irs.add(resource);
        configuredIRs.put(DummyLocalisationProvider.CLIENT_ID_LIVING_ROOM_LOUDSPEAKER, irs);
    }

    /**
     * <code>main</code> for tests.
     * 
     * @param args
     *            program arguments, ignored
     */
    public static void main(final String[] args) {
        final Environment environment = ContextFactory.eINSTANCE.createEnvironment();
        final Room room = ContextFactory.eINSTANCE.createRoom();
        environment.getPlaces().add(room);
        Device device;
        Entry<String, Set<InteractionResource>> entry;
        for(final Iterator<Entry<String, Set<InteractionResource>>> i = configuredIRs.entrySet().iterator(); i.hasNext();) {
            entry = i.next();
            device = ContextFactory.eINSTANCE.createDevice();
            device.setId(entry.getKey());
            device.setName(entry.getKey());
            device.getResources().addAll(entry.getValue());
            room.getElements().addAll(entry.getValue());
            environment.getDevices().add(device);
        }

        System.out.println(XMIUtility.convert(environment));
    }

    private static ClientAPI createIRsForNormalComputer(final String id) {
        Set<InteractionResource> irSet = configuredIRs.get(id);
        System.out.println("DummyClientDiscoverer.createIRsForNormalComputer(): configuredIRSet for id=" + id + " is " + irSet);
        if(irSet == null) {
            // create interaction resources
            irSet = new HashSet<InteractionResource>();
            final Display display = factory.createDisplay();
            display.setId(id + "_DISPLAY");
            display.setDirection(factory.createVector());
            // TODO set all needed values
            irSet.add(display);
        }
        irs.put(id, irSet);
        // create api for client
        return FixedInteractionResourcesClientAPI.create(irSet.toArray(new InteractionResource[irSet.size()]));
    }

    private static ClientAPI createIRsForPersonalizedMobileComputer(final String deviceId, final String userId, final int width, final int height) {
        Set<InteractionResource> irSet = configuredIRs.get(deviceId);
        System.out.println("DummyClientDiscoverer.createIRsForNormalComputer(): configuredIRSet for id=" + deviceId + " is " + irSet);
        if(irSet == null) {
            // create interaction resources
            irSet = new HashSet<InteractionResource>();
            final Display display = factory.createDisplay();
            display.setId(deviceId + "_DISPLAY");
            display.setMobile(true);
            display.setPersonal(userId);
            display.setXPixels(width);
            display.setYPixels(height);
            display.setDirection(factory.createVector());
            // TODO set all needed values
            irSet.add(display);
        }
        irs.put(deviceId, irSet);
        // create api for client
        return FixedInteractionResourcesClientAPI.create(irSet.toArray(new InteractionResource[irSet.size()]));
    }

    private static ClientAPI createIRsForMobileComputer(final String id, final int width, final int height) {
        Set<InteractionResource> irSet = configuredIRs.get(id);
        System.out.println("DummyClientDiscoverer.createIRsForNormalComputer(): configuredIRSet for id=" + id + " is " + irSet);
        if(irSet == null) {
            // create interaction resources
            irSet = new HashSet<InteractionResource>();
            final Display display = factory.createDisplay();
            display.setId(id + "_DISPLAY");
            display.setMobile(true);
            display.setXPixels(width);
            display.setYPixels(height);
            display.setDirection(factory.createVector());
            // TODO set all needed values
            irSet.add(display);
        }
        irs.put(id, irSet);
        // create api for client
        return FixedInteractionResourcesClientAPI.create(irSet.toArray(new InteractionResource[irSet.size()]));
    }

    private static ClientAPI createIRForVoiceOut(final String id) {
        Set<InteractionResource> irSet = configuredIRs.get(id);
        System.out.println("DummyClientDiscoverer.createIRsForVoiceOut(): configuredIRSet for id=" + id + " is " + irSet);

        if(irSet == null) {
            irSet = new HashSet<InteractionResource>();
            final Loudspeaker loudspeaker = factory.createLoudspeaker();
            loudspeaker.setId(id + "_LOUDSPEAKER");
            irSet.add(loudspeaker);
        }

        irs.put(id, irSet);

        return FixedInteractionResourcesClientAPI.create(irSet.toArray(new InteractionResource[irSet.size()]));
    }

    private static ClientAPI createIRForVoiceIn(final String id) {
        Set<InteractionResource> irSet = configuredIRs.get(id);
        System.out.println("DummyClientDiscoverer.createIRsForVoiceIn(): configuredIRSet for id=" + id + " is " + irSet);

        if(irSet == null) {
            irSet = new HashSet<InteractionResource>();
            final Microphone microphone = factory.createMicrophone();
            microphone.setId(id + "_MICROPHONE");
            irSet.add(microphone);
        }

        irs.put(id, irSet);

        return FixedInteractionResourcesClientAPI.create(irSet.toArray(new InteractionResource[irSet.size()]));
    }

    private static ClientAPI createIRForGesture(final String id) {
        Set<InteractionResource> irSet = configuredIRs.get(id);
        System.out.println("DummyClientDiscoverer.createIRsForGesture(): configuredIRSet for id=" + id + " is " + irSet);

        if(irSet == null) {
            irSet = new HashSet<InteractionResource>();
            final GestureIR microphone = factory.createGestureIR();
            microphone.setId(id + "_GESTURE_IR");
            irSet.add(microphone);
        }

        irs.put(id, irSet);

        return FixedInteractionResourcesClientAPI.create(irSet.toArray(new InteractionResource[irSet.size()]));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final ClientDiscoveryObserver newObserver) {
        if(newObserver == null) {
            throw new IllegalArgumentException("newObserver is null");
        }
        System.out.println("DummyClientDiscoverer.start() called");
        if(this.observer == null) {
            this.observer = newObserver;
            for(final String id : clientAPIs.keySet()) {
                this.observer.newClient(id, clientAPIs.get(id));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        this.observer = null;
    }

    /**
     * <code>registerClient</code> is used to register clients from the
     * initializers within the channels.
     * 
     * @param id
     *            id of the new client
     * @param type
     *            type of the client
     */
    public static void registerClient(final String id, final Type type) {
        System.out.println("DummyClientDiscoverer.registerClient(): id=" + id + "; type=" + type);
        ClientAPI api = null;
        switch(type) {
            case COMPUTER:
                api = DummyClientDiscoverer.createIRsForNormalComputer(id);
                break;
            case VOICE_IN:
                api = DummyClientDiscoverer.createIRForVoiceIn(id);
                break;
            case VOICE_OUT:
                api = DummyClientDiscoverer.createIRForVoiceOut(id);
                break;
            case GESTURE:
                api = DummyClientDiscoverer.createIRForGesture(id);
                break;
            default:
                System.err.println("DummyClientDiscoverer.registerClient(): parameter type cannot be handled! type=" + type);
                return;
        }
        if(api != null) {
            System.out.println("DummyClientDiscoverer.registerClient(): api=" + api);
            if(INSTANCE.observer != null) {
                INSTANCE.observer.newClient(id, api);
            } else {
                DummyClientDiscoverer.clientAPIs.put(id, api);
            }
        } else {
            System.err.println("DummyClientDiscoverer.registerClient(): no ClientAPI created for type=" + type);
        }
    }

    /**
     * <code>registerGraphicalClient</code> is used to register clients from the
     * initializers within the channels.
     * 
     * @param id
     *            id of the new client
     * @param type
     *            type of the client
     * @param width
     *            actual width of client
     * @param height
     *            actual height of client
     */
    public static void registerGraphicalClient(final String id, final Type type, final int width, final int height) {
        System.out.println("DummyClientDiscoverer.registerClient(): id=" + id + "; type=" + type);
        ClientAPI api = null;

        switch(type) {
            case COMPUTER:
                api = DummyClientDiscoverer.createIRsForNormalComputer(id);
                break;
            case MOBILE:
                api = DummyClientDiscoverer.createIRsForMobileComputer(id, width, height);
                break;
            default:
                System.err.println("DummyClientDiscoverer.registerClient(): parameter type cannot be handled! type=" + type);
                return;
        }
        if(api != null) {
            System.out.println("DummyClientDiscoverer.registerClient(): api=" + api);
            if(INSTANCE.observer != null) {
                INSTANCE.observer.newClient(id, api);
            } else {
                DummyClientDiscoverer.clientAPIs.put(id, api);
            }
        } else {
            System.err.println("DummyClientDiscoverer.registerClient(): no ClientAPI created for type=" + type);
        }
    }

    /**
     * <code>registerGraphicalClient</code> is used to register clients from the
     * initializers within the channels.
     * 
     * @param deviceId
     *            id of the new client
     * @param userId
     *            id of the new client
     * @param type
     *            type of the client
     * @param width
     *            actual width of client
     * @param height
     *            actual height of client
     */
    public static void registerPersonalizedGraphicalClient(final String deviceId, final String userId, final Type type, final int width, final int height) {
        System.out.println("DummyClientDiscoverer.registerClient(): id=" + deviceId + "; type=" + type);
        ClientAPI api = null;

        switch(type) {
            case COMPUTER:
                api = DummyClientDiscoverer.createIRsForNormalComputer(deviceId);
                break;
            case MOBILE:
                api = DummyClientDiscoverer.createIRsForPersonalizedMobileComputer(deviceId, userId, width, height);
                break;
            default:
                System.err.println("DummyClientDiscoverer.registerClient(): parameter type cannot be handled! type=" + type);
                return;
        }
        if(api != null) {
            System.out.println("DummyClientDiscoverer.registerClient(): api=" + api);
            if(INSTANCE.observer != null) {
                INSTANCE.observer.newClient(deviceId, api);
            } else {
                DummyClientDiscoverer.clientAPIs.put(deviceId, api);
            }
        } else {
            System.err.println("DummyClientDiscoverer.registerClient(): no ClientAPI created for type=" + type);
        }
    }

    /**
     * <code>removeClient</code> is used to remove clients from the context
     * model.
     * 
     * @param id
     *            id of the removed client
     */
    public static void removeClient(final String id) {
        System.out.println("DummyClientDiscoverer.removeClient(): called with id=" + id);
        if(INSTANCE.observer != null) {
            INSTANCE.observer.clientGone(id);
        } else {
            DummyClientDiscoverer.clientAPIs.remove(id);
        }
    }

    /**
     * @return singleton instance
     */
    public static DummyClientDiscoverer getInstance() {
        return INSTANCE;
    }

}
