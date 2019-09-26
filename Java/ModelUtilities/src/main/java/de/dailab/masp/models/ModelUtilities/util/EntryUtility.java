/**
 * 
 */
package de.dailab.masp.models.ModelUtilities.util;

import java.util.HashMap;
import java.util.Map;

import de.dailab.masp.models.ModelUtilities.Entry;
import de.dailab.masp.models.ModelUtilities.ModelUtilitiesFactory;

/**
 * <code>EntryUtility</code> simplifies working with {@link Entry} objects.
 * 
 * @author Grzegorz Lehmann
 * @since 1.1.5
 */
public final class EntryUtility {

    /**
     * <code>getMap</code> converts a set of entries into a map.
     * 
     * @param entries
     *            entries to convert into a map
     * @return Map&lt;String, String&gt; - map of key value pairs held in
     *         <code>entries</code>
     */
    public static Map<String, String> getMap(final Iterable<Entry> entries) {
        if(entries == null) {
            throw new IllegalArgumentException("entries argument in method getMap must not be null");
        }
        final Map<String, String> entryMap = new HashMap<String, String>();
        synchronized(entries) {
            for(final Entry entry : entries) {
                entryMap.put(entry.getKey(), entry.getValue());
            }
        }
        return entryMap;
    }

    /**
     * <code>getEntry</code> creates an {@link Entry} for a given key/value
     * pair.
     * 
     * @param key
     *            kex for the entry
     * @param value
     *            value for the entry
     * @return Entry with the given key/value pair
     */
    public static Entry getEntry(final String key, final String value) {
        if(key == null) {
            throw new IllegalArgumentException("key argument must not be null in method getEntry");
        }
        final Entry entry = ModelUtilitiesFactory.eINSTANCE.createEntry();
        entry.setKey(key);
        entry.setValue(value);
        return entry;
    }
}
