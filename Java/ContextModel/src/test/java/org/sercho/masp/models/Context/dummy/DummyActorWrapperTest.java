/**
 * 
 */
package org.sercho.masp.models.Context.dummy;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.sercho.masp.models.Context.dummy.DummyValueRegistry.DummyValueObserver;

import de.dailab.masp.models.MetaMetaModel.ModelCallback;
import de.dailab.masp.models.Properties.ActorServiceCallException;
import de.dailab.masp.models.Properties.ConfigurationProperty;

/**
 * <code>DummyActorWrapperTest</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since TODO
 */
public class DummyActorWrapperTest extends TestCase {

    /**
     * Test method for
     * {@link de.dailab.masp.cf.Appliances.dummy.DummyActorWrapper#set(java.lang.String)}
     * .
     */
    public final void testSet() {
        final DummyActorWrapper wrapper = new DummyActorWrapper();

        final String serviceID = "Service";

        final EList<ConfigurationProperty> properties = new BasicEList<ConfigurationProperty>();
        properties.add(DummyServiceConstants.createVariableNameProperty(serviceID));
        properties.add(DummyServiceConstants.createInitialValueProperty("Blub"));

        final Map<String, Object> values = new HashMap<String, Object>();

        final DummyValueObserver observer = new DummyValueObserver() {

            /**
             * {@inheritDoc}
             */
            @Override
            public int compareTo(final Object o) {
                return o == this ? 0 : -1;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void newValue(final String sensorID, final String newValue) {
                values.put(serviceID, newValue);
            }
        };
        DummyValueRegistry.addObserver(observer);

        wrapper.start(properties, new ModelCallback() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void execute(final String arg0, final Object... arg1) throws InvocationTargetException {
                // nothing to do here
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public Set<String> getExecutableElementNames() {
                return null;
            }
        });

        String value = "Test";
        try {
            wrapper.set(value);
        }
        catch(final ActorServiceCallException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertEquals(1, values.size());
        assertEquals(value, values.get(serviceID));
        assertEquals(value, DummyValueRegistry.getValue(serviceID));

        value = "Test1";
        try {
            wrapper.set(value);
        }
        catch(final ActorServiceCallException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertEquals(1, values.size());
        assertEquals(value, values.get(serviceID));
        assertEquals(value, DummyValueRegistry.getValue(serviceID));

        value = "Blub";
        try {
            wrapper.set(value);
        }
        catch(final ActorServiceCallException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertEquals(1, values.size());
        assertEquals(value, values.get(serviceID));
        assertEquals(value, DummyValueRegistry.getValue(serviceID));
    }

}
