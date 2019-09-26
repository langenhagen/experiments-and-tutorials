package org.sercho.masp.models;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.sercho.masp.context.providers.location.AbstractLocalisationProvider;
import org.sercho.masp.context.providers.location.Vector;

/**
 * <code>DummyLocalisationProvider</code> TODO comment
 * 
 * @author Dirk Roscher
 */
public class DummyLocalisationProvider extends AbstractLocalisationProvider {

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8708062776973327587L;

    Map<String, Vector> objects = new HashMap<String, Vector>();

    private static DummyLocalisationProvider INSTANCE;

    /**
     * <code>DummyLocalisationProvider</code> constructor
     */
    public DummyLocalisationProvider() {
        loadObjects();
        INSTANCE = this;
    }

    static final String USER_ID_MARCO = "Marco";

    static final String USER_ID_GRZEGORZ = "Grzegorz";

    static final Vector POS_KITCHEN_PC = new Vector(750, 250, 0);

    static final Vector POS_SHEM_TOUCH = new Vector(600, 250, 0);

    static final Vector POS_NEAR_KITCHEN_PC = new Vector(600, 300, 0);

    static final Vector POS_LIVING_ROOM_SHARP_65 = new Vector(50, 200, 0);

    static final Vector POS_LIVING_ROOM_DELL_30 = new Vector(550, 50, 0);

    static final Vector POS_LIVING_ROOM_TOUCH = new Vector(275, 10, 0);

    static final Vector POS_WORKROOM = new Vector(-100, 100, 0);

    static final Vector POS_WORKROOM_DOOR = new Vector(-100, 300, 0);

    // APPENDIX

    static final String KEYBOARD_APPENDIX = "_KEYBOARD";

    static final String DISPLAY_APPENDIX = "_DISPLAY";

    static final String MICROPHONE_APPENDIX = "_MICROPHONE";

    static final String LOUDSPEAKER_APPENDIX = "_LOUDSPEAKER";

    static final String GESTURE_IR_APPENDIX = "_GESTURE_IR";

    // CLIENT_IDs

    static final String CLIENT_ID_LIVING_ROOM_TOUCH = "LIVING_ROOM_TOUCH";

    static final String CLIENT_ID_N800 = "N800";

    static final String CLIENT_ID_KITCHEN = "KITCHEN";

    static final String CLIENT_ID_DELL_30 = "DELL_30";

    static final String CLIENT_ID_SHARP_65 = "SHARP_65";

    static final String CLIENT_ID_DRAGON = "DRAGON";

    static final String CLIENT_ID_KITCHEN_LOUDSPEAKER = "1337";

    static final String CLIENT_ID_LIVING_ROOM_LOUDSPEAKER = "1338";

    static final String CLIENT_ID_SHEM_TOUCH = "SHEM_TOUCH";

    static final String CLIENT_ID_WORKROOM = "WORKROOM";

    static final String CLIENT_ID_WORKROOM_DOOR = "WORKROOM_DOOR";

    static final String CLIENT_ID_GESTURE_DEVICE = "GESTURE_DEVICE";

    // IR_IDs

    static final String IR_ID_SHARP_65_KEYBOARD = CLIENT_ID_SHARP_65 + KEYBOARD_APPENDIX;

    static final String IR_ID_SHARP_65_DISPLAY = CLIENT_ID_SHARP_65 + DISPLAY_APPENDIX;

    static final String IR_ID_DELL_30_KEYBOARD = CLIENT_ID_DELL_30 + KEYBOARD_APPENDIX;

    static final String IR_ID_DELL_30_DISPLAY = CLIENT_ID_DELL_30 + DISPLAY_APPENDIX;

    static final String IR_ID_KITCHEN_KEYBOARD = CLIENT_ID_KITCHEN + KEYBOARD_APPENDIX;

    static final String IR_ID_KITCHEN_DISPLAY = CLIENT_ID_KITCHEN + DISPLAY_APPENDIX;

    static final String IR_ID_N800_DISPLAY = CLIENT_ID_N800 + DISPLAY_APPENDIX;

    static final String IR_ID_N800_KEYBOARD = CLIENT_ID_N800 + KEYBOARD_APPENDIX;

    static final String IR_ID_DRAGON_MICROPHONE = CLIENT_ID_DRAGON + MICROPHONE_APPENDIX;

    static final String IR_ID_KITCHEN_LOUDSPEAKER = CLIENT_ID_KITCHEN_LOUDSPEAKER + LOUDSPEAKER_APPENDIX;

    static final String IR_ID_LIVING_ROOM_LOUDSPEAKER = CLIENT_ID_LIVING_ROOM_LOUDSPEAKER + LOUDSPEAKER_APPENDIX;

    static final String IR_ID_LIVING_ROOM_TOUCH_DISPLAY = CLIENT_ID_LIVING_ROOM_TOUCH + DISPLAY_APPENDIX;

    static final String IR_ID_LIVING_ROOM_TOUCH_KEYBOARD = CLIENT_ID_LIVING_ROOM_TOUCH + KEYBOARD_APPENDIX;

    static final String IR_ID_SHEM_TOUCH_DISPLAY = CLIENT_ID_SHEM_TOUCH + DISPLAY_APPENDIX;

    static final String IR_ID_SHEM_TOUCH_KEYBOARD = CLIENT_ID_SHEM_TOUCH + KEYBOARD_APPENDIX;

    static final String IR_ID_WORKROOM_DISPLAY = CLIENT_ID_WORKROOM + DISPLAY_APPENDIX;

    static final String IR_ID_WORKROOM_KEYBOARD = CLIENT_ID_WORKROOM + KEYBOARD_APPENDIX;

    static final String IR_ID_WORKROOM_DOOR_DISPLAY = CLIENT_ID_WORKROOM_DOOR + DISPLAY_APPENDIX;

    static final String IR_ID_WORKROOM_DOOR_KEYBOARD = CLIENT_ID_WORKROOM_DOOR + KEYBOARD_APPENDIX;

    static final String IR_ID_GESTURE_DEVICE = CLIENT_ID_GESTURE_DEVICE + GESTURE_IR_APPENDIX;

    // CLIENT_NAMES

    static final String IR_NAME_LIVING_ROOM_TOUCH_DISPLAY = "Touchscreen Wohnzimmer";

    static final String IR_NAME_N800_DISPLAY = "Mobiles Ger\u00E4t";

    static final String IR_NAME_KITCHEN_DISPLAY = "Touchscreen K\u00FCche";

    static final String IR_NAME_DELL_30_DISPLAY = "Bildschirm Wohnzimmerecke";

    static final String IR_NAME_SHARP_65_DISPLAY = "Fernseher Wohnzimmer";

    static final String IR_NAME_DRAGON_MICROPHONE = "Tragbares Mikrofon";

    static final String IR_NAME_KITCHEN_LOUDSPEAKER = "Lautsprecher K\u00FCche";

    static final String IR_NAME_LIVING_ROOM_LOUDSPEAKER = "Lautsprecher Wohnzimmer";

    static final String IR_NAME_SHEM_TOUCH_DISPLAY = "Kleiner Touchscreen K\u00FCche";

    static final String IR_NAME_WORKROOM_DISPLAY = "Fernseher Arbeitszimmer";

    static final String IR_NAME_WORKROOM_DOOR_DISPLAY = "Fernseher Studio";

    static final String IR_NAME_GESTURE_DEVICE = "Gestikger\u00E4t";

    private void loadObjects() {
        // objects.put(Test.USER_ID_GRZEGORZ, POS_KITCHEN_PC);
        // objects.put(Test.USER_ID_MARCO, POS_LIVING_ROOM_SHARP_65);

        this.objects.put(IR_ID_DELL_30_DISPLAY, POS_LIVING_ROOM_DELL_30);
        this.objects.put(IR_ID_DELL_30_KEYBOARD, POS_LIVING_ROOM_DELL_30);
        this.objects.put(IR_ID_SHARP_65_DISPLAY, POS_LIVING_ROOM_SHARP_65);
        this.objects.put(IR_ID_SHARP_65_KEYBOARD, POS_LIVING_ROOM_SHARP_65);
        this.objects.put(IR_ID_LIVING_ROOM_TOUCH_DISPLAY, POS_LIVING_ROOM_TOUCH);
        this.objects.put(IR_ID_LIVING_ROOM_TOUCH_KEYBOARD, POS_LIVING_ROOM_TOUCH);
        this.objects.put(IR_ID_SHEM_TOUCH_DISPLAY, POS_SHEM_TOUCH);
        this.objects.put(IR_ID_SHEM_TOUCH_KEYBOARD, POS_SHEM_TOUCH);
        this.objects.put(IR_ID_KITCHEN_DISPLAY, POS_KITCHEN_PC);
        this.objects.put(IR_ID_KITCHEN_KEYBOARD, POS_KITCHEN_PC);
        // objects.put(IR_ID_DRAGON_MICROPHONE, POS_KITCHEN_PC);
        this.objects.put(IR_ID_KITCHEN_LOUDSPEAKER, POS_KITCHEN_PC);
        this.objects.put(IR_ID_LIVING_ROOM_LOUDSPEAKER, POS_LIVING_ROOM_SHARP_65);
        this.objects.put(IR_ID_N800_DISPLAY, POS_NEAR_KITCHEN_PC);
        this.objects.put(IR_ID_N800_KEYBOARD, POS_NEAR_KITCHEN_PC);
        this.objects.put(IR_ID_WORKROOM_DISPLAY, POS_WORKROOM);
        this.objects.put(IR_ID_WORKROOM_KEYBOARD, POS_WORKROOM);
        this.objects.put(IR_ID_WORKROOM_DOOR_DISPLAY, POS_WORKROOM_DOOR);
        this.objects.put(IR_ID_WORKROOM_DOOR_KEYBOARD, POS_WORKROOM_DOOR);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector getPosition(final String id) {
        return this.objects.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean register(final String arg0) {
        System.out.println("DummyLocalisationProvider.register(): called with argument=" + arg0);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unregister(final String arg0) {
        System.out.println("DummyLocalisationProvider.unregister(): called with argument=" + arg0);
    }

    public void setPosition(final String id, final Vector position) {
        newPosition(id, position.getX(), position.getY(), position.getZ(), 1);
    }

    public static void setPos(final String id, final Vector position) {
        if(INSTANCE != null) {
            INSTANCE.setPosition(id, position);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void close() {
        // nothing to do here yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void start(final Map<String, String> configuration) {
        // report all objects
        Entry<String, Vector> entry;
        for(final Iterator<Entry<String, Vector>> i = this.objects.entrySet().iterator(); i.hasNext();) {
            entry = i.next();
            newPosition(entry.getKey(), entry.getValue().getX(), entry.getValue().getY(), entry.getValue().getZ(), 1);
        }
    }
}