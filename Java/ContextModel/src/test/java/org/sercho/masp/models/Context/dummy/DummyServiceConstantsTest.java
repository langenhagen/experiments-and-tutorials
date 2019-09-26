/**
 * 
 */
package org.sercho.masp.models.Context.dummy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import de.dailab.masp.models.Properties.ConfigurationProperty;

/**
 * <code>DummyServiceConstantsTest</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 3.0
 */
public class DummyServiceConstantsTest extends TestCase {

    /**
     * Test method for
     * {@link de.dailab.masp.cf.Appliances.dummy.DummyServiceConstants#getVariableName(java.util.Map)}
     * .
     */
    public final void testGetServiceID() {
        try {
            DummyServiceConstants.getVariableName(Collections.<String, String> emptyMap());
            fail("Error expected but it has not been reported");
        }
        catch(final IllegalArgumentException e) {
            // okay, expected
        }

        try {
            assertEquals("Blub", DummyServiceConstants.getVariableName(Collections.<String, String> singletonMap(DummyServiceConstants.PROPERTY_KEY_VariableName, "Blub")));
        }
        catch(final IllegalArgumentException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link de.dailab.masp.cf.Appliances.dummy.DummyServiceConstants#setVariableName(java.util.Map, java.lang.String)}
     * .
     */
    public final void testSetServiceID() {
        final Map<String, String> configurationMap = new HashMap<String, String>();
        assertTrue(configurationMap.isEmpty());
        final String serviceID = "Service";
        DummyServiceConstants.setVariableName(configurationMap, serviceID);
        assertEquals(1, configurationMap.size());
        assertEquals(serviceID, DummyServiceConstants.getVariableName(configurationMap));
    }

    /**
     * Test method for
     * {@link de.dailab.masp.cf.Appliances.dummy.DummyServiceConstants#createVariableNameProperty(java.lang.String)}
     * .
     */
    public final void testCreateServiceIDProperty() {
        final String serviceID = "Service";
        final ConfigurationProperty property = DummyServiceConstants.createVariableNameProperty(serviceID);
        assertNotNull(property);
        assertEquals(DummyServiceConstants.PROPERTY_KEY_VariableName, property.getKey());
        assertEquals(serviceID, property.getValue());
    }
}