/**
 * 
 */
package de.dailab.masp.models.ModelUtilities.util;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;
import de.dailab.masp.models.ModelUtilities.Entry;
import de.dailab.masp.models.ModelUtilities.ModelUtilitiesFactory;

/**
 * <code>EntryUtilityTest</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since TODO
 */
public class EntryUtilityTest extends TestCase {

    /**
     * Test method for
     * {@link de.dailab.masp.models.ModelUtilities.util.EntryUtility#getMap(java.lang.Iterable)}
     * .
     */
    public final void testGetMap() {
        final Set<Entry> entrySet = new HashSet<Entry>();
        Map<String, String> entryMap;

        // check empty map
        entryMap = EntryUtility.getMap(entrySet);
        assertNotNull(entryMap);
        assertTrue(entryMap.isEmpty());

        // check with one entry
        Entry entry = ModelUtilitiesFactory.eINSTANCE.createEntry();
        entry.setKey("a");
        entry.setValue("1");
        entrySet.add(entry);
        entryMap = EntryUtility.getMap(entrySet);
        assertNotNull(entryMap);
        assertEquals(1, entryMap.size());
        assertEquals(entry.getValue(), entryMap.get(entry.getKey()));

        // check with two entries
        entry = ModelUtilitiesFactory.eINSTANCE.createEntry();
        entry.setKey("b");
        entry.setValue("2");
        entrySet.add(entry);
        entryMap = EntryUtility.getMap(entrySet);
        assertNotNull(entryMap);
        assertEquals(2, entryMap.size());
        assertEquals(entry.getValue(), entryMap.get(entry.getKey()));
    }

    /**
     * <code>testGetEntry</code> tests
     * {@link EntryUtility#getEntry(String, String)}.
     */
    public final void testGetEntry() {
        String key = "k";
        String value = null;

        Entry entry = EntryUtility.getEntry(key, value);
        assertNotNull(entry);
        assertSame(key, entry.getKey());
        assertSame(value, entry.getValue());

        key = "k2";
        value = "some value";
        entry = EntryUtility.getEntry(key, value);
        assertNotNull(entry);
        assertSame(key, entry.getKey());
        assertSame(value, entry.getValue());
    }
}