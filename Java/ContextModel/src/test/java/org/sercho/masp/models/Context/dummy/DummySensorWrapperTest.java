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

import de.dailab.masp.models.MetaMetaModel.ModelCallback;
import de.dailab.masp.models.Properties.ConfigurationProperty;

/**
 * <code>DummySensorWrapperTest</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 3.0
 */
public final class DummySensorWrapperTest extends TestCase {

    public void testDummySensorWrapper() {
        final DummySensorWrapper wrapper = new DummySensorWrapper();

        final String serviceID = "Service";

        final EList<ConfigurationProperty> properties = new BasicEList<ConfigurationProperty>();
        properties.add(DummyServiceConstants.createVariableNameProperty(serviceID));
        properties.add(DummyServiceConstants.createInitialValueProperty("Blub"));

        final Map<String, Object> values = new HashMap<String, Object>();
        final ModelCallback callback = new ModelCallback() {

            /**
             * {@inheritDoc}
             */
            @Override
            public Set<String> getExecutableElementNames() {
                // TODO Auto-generated method stub
                return null;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void execute(final String executableElementName, final Object... arguments) throws InvocationTargetException {
                if(arguments.length == 1) {
                    values.put(executableElementName, arguments[0]);
                }
            }
        };

        wrapper.start(properties, callback);

        String value = "Test";
        DummyValueRegistry.set(serviceID, value);
        assertEquals(value, values.get("setNewValue"));
        assertEquals(Boolean.TRUE, values.get("setNewAvailable"));

        value = "Test2";
        DummyValueRegistry.set(serviceID, value);
        assertEquals(value, values.get("setNewValue"));
        assertEquals(Boolean.TRUE, values.get("setNewAvailable"));

        value = "Test3";
        DummyValueRegistry.set(serviceID, value);
        assertEquals(value, values.get("setNewValue"));
        assertEquals(Boolean.TRUE, values.get("setNewAvailable"));

        wrapper.stop();
        assertEquals(Boolean.FALSE, values.get("setNewAvailable"));
    }
}
