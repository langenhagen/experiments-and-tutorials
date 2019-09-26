/**
 * 
 */
package org.sercho.masp.models.Context.dummy;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * <code>DummyValueRegistry</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 3.0
 */
public final class DummyValueRegistry {

    /**
     * <code>DummyValueObserver</code> allows observing the
     * {@link DummyValueRegistry} and become notified on value changes.
     * 
     * @author Grzegorz Lehmann
     */
    public static interface DummyValueObserver extends Comparable<Object> {

        /**
         * <code>newValue</code> informs this observer about a new value of a
         * sensor.
         * 
         * @param sensorID
         *            identifier of the sensor
         * @param newValue
         *            new value of the sensor
         */
        void newValue(String sensorID, String newValue);
    }

    private static final Set<DummyValueObserver> OBSERVERS = new ConcurrentSkipListSet<DummyValueObserver>();

    private static final Map<String, String> VALUES = new Hashtable<String, String>();

    /**
     * <code>getAllValues</code> returns an unmodifiable view of all current
     * values held in this registry.
     * 
     * @return Map&lt;String, String&gt; - unmodifiable view of all current
     *         values held in this registry
     */
    public static Map<String, String> getAllValues() {
        return Collections.unmodifiableMap(VALUES);
    }

    /**
     * <code>addObserver</code> adds a new {@link DummyValueObserver} to this
     * registry.
     * 
     * @param observer
     *            new observer
     */
    public static void addObserver(final DummyValueObserver observer) {
        OBSERVERS.add(observer);
    }

    /**
     * <code>getValue</code> returns, for a specified sensor, the value held in
     * this registry.
     * 
     * @param sensorID
     *            identifier of the sensor
     * @return String - value
     */
    public static String getValue(final String sensorID) {
        return VALUES.get(sensorID);
    }

    /**
     * <code>removeObserver</code> removes a {@link DummyValueObserver} from
     * this registry.
     * 
     * @param observer
     *            observer to remove
     */
    public static void removeObserver(final DummyValueObserver observer) {
        OBSERVERS.remove(observer);
    }

    /**
     * <code>set</code> sets a new value for a dummy sensor. This method should
     * be used by dummy actors.
     * 
     * @param sensorID
     *            ID of the dummy sensor
     * @param newValue
     *            new value for the dummy sensor
     */
    public static void set(final String sensorID, final String newValue) {
        VALUES.put(sensorID, newValue);
        synchronized(OBSERVERS) {
            for(final DummyValueObserver observer : OBSERVERS) {
                observer.newValue(sensorID, newValue);
            }
        }
    }

    /**
     * <code>DummyValueRegistry</code> constructor.
     */
    private DummyValueRegistry() {
        // hiding constructor of utility class
    }
}
