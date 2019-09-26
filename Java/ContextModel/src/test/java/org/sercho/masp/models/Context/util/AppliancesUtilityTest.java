/**
 * 
 */
package org.sercho.masp.models.Context.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.sercho.masp.models.XMIUtility;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.Hob;
import org.sercho.masp.models.Context.Lamp;

import de.dailab.masp.models.Properties.Actor;
import de.dailab.masp.models.Properties.ActorServiceCallException;
import de.dailab.masp.models.Properties.PropertiesFactory;
import de.dailab.masp.models.Properties.Property;
import de.dailab.masp.models.Properties.impl.ActorImpl;

/**
 * <code>AppliancesUtilityTest</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since TODO
 */
public class AppliancesUtilityTest extends TestCase {

    /**
     * Test method for
     * {@link de.dailab.masp.cf.Appliances.util.AppliancesUtility#getDoubleValueOrZeroIfNull(Property)}
     * .
     */
    public final void testGetValueOrZeroIfNullPropertyOfDouble() {
        assertEquals(0, AppliancesUtility.getDoubleValueOrZeroIfNull((Property<Double>)null), 0);
        final Property<Double> property = PropertiesFactory.eINSTANCE.createDoubleProperty();
        assertEquals(0, AppliancesUtility.getDoubleValueOrZeroIfNull(property), 0);
        final double value = 234.234;
        property.setValue(value);
        assertNotNull(property.getValue());
        assertEquals(value, property.getValue().doubleValue(), 0);
        assertEquals(value, AppliancesUtility.getDoubleValueOrZeroIfNull(property), 0);
    }

    /**
     * Test method for
     * {@link de.dailab.masp.cf.Appliances.util.AppliancesUtility#getIntegerValueOrZeroIfNull(Property)}
     * .
     */
    public final void testGetValueOrZeroIfNullPropertyOfInteger() {
        assertEquals(0, AppliancesUtility.getIntegerValueOrZeroIfNull((Property<Integer>)null), 0);
        final Property<Integer> property = PropertiesFactory.eINSTANCE.createIntegerProperty();
        assertEquals(0, AppliancesUtility.getIntegerValueOrZeroIfNull(property), 0);
        final int value = 234;
        property.setValue(value);
        assertNotNull(property.getValue());
        assertEquals(value, property.getValue().intValue());
        assertEquals(value, AppliancesUtility.getIntegerValueOrZeroIfNull(property), 0);
    }

    /**
     * Test method for
     * {@link de.dailab.masp.cf.Appliances.util.AppliancesUtility#getValueOrFalseIfNull(de.dailab.masp.cf.Appliances.Property)}
     * .
     */
    public final void testGetValueOrFalseIfNull() {
        assertFalse(AppliancesUtility.getValueOrFalseIfNull((Property<Boolean>)null));
        final Property<Boolean> property = PropertiesFactory.eINSTANCE.createBooleanProperty();
        assertFalse(AppliancesUtility.getValueOrFalseIfNull(property));
        property.setValue(true);
        assertNotNull(property.getValue());
        assertTrue(property.getValue().booleanValue());
        assertTrue(AppliancesUtility.getValueOrFalseIfNull(property));
    }

    /**
     * Test method for
     * {@link de.dailab.masp.cf.Appliances.util.AppliancesUtility#getValue(de.dailab.masp.cf.Appliances.Property)}
     * .
     */
    public final void testGetValue() {
        assertNull(AppliancesUtility.getValue((Property<Integer>)null));
        final Property<Integer> property = PropertiesFactory.eINSTANCE.createIntegerProperty();
        assertNull(AppliancesUtility.getValue(property));
        final int value = 234;
        property.setValue(value);
        assertNotNull(property.getValue());
        assertEquals(value, property.getValue().intValue());
        assertEquals(value, AppliancesUtility.getValue(property).intValue());
    }

    /**
     * Test method for
     * {@link de.dailab.masp.cf.Appliances.util.AppliancesUtility#set(de.dailab.masp.cf.Appliances.Property, java.lang.String)}
     * .
     */
    public final void testSet() {
        try {
            AppliancesUtility.set(null, null);
            fail("set worked unexpectedly");
        }
        catch(final ActorServiceCallException e) {
            // expected, okay
        }
        final Property<Integer> property = PropertiesFactory.eINSTANCE.createIntegerProperty();
        try {
            AppliancesUtility.set(property, null);
            fail("set worked unexpectedly");
        }
        catch(final ActorServiceCallException e) {
            // expected, okay
        }

        assertSet(property, null);
        assertSet(property, "Hello world!");
    }

    private static final void assertSet(final Property<?> property, final String value) {
        final List<String> newValueList = new ArrayList<String>(1);
        final Actor actor = new ActorImpl() {

            /**
             * {@inheritDoc}
             */
            @Override
            public synchronized void set(final String newValue) throws ActorServiceCallException {
                newValueList.add(newValue);
            }
        };
        property.setActor(actor);

        try {
            AppliancesUtility.set(property, value);
        }
        catch(final ActorServiceCallException e) {
            // not expected
            fail(e.getMessage());
        }
        assertEquals(1, newValueList.size());
        assertEquals(value, newValueList.get(0));
        newValueList.clear();
    }

    private Environment getEnvironment() {
        try {
            return (Environment)XMIUtility.convert(AppliancesUtilityTest.class.getClassLoader().getResourceAsStream("org/sercho/masp/models/Context/util/AppliancesUtilityTest.xmi"), ContextPackage.eINSTANCE).get(0);
        }
        catch(final IOException e) {
            e.printStackTrace();
            fail("Failed to load test home: " + e.getMessage());
            return null;
        }
    }

    /**
     * Test method for
     * {@link de.dailab.masp.cf.Appliances.util.AppliancesUtility#getDevice(SmartHome, String)}
     * .
     */
    public final void testGetAppliance() {
        final Environment environment = getEnvironment();
        Device device = AppliancesUtility.getDevice(environment, "Kochfeld1");
        assertNotNull(device);
        assertTrue(device instanceof Hob);

        device = AppliancesUtility.getDevice(environment, "MeineTolleLampe");
        assertNotNull(device);
        assertTrue(device instanceof Lamp);
    }
}
