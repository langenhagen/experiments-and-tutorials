/**
 * 
 */
package org.sercho.masp.models.Context.dummy;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.Fan;

import de.dailab.masp.models.MegaModel.MegaModelFactory;
import de.dailab.masp.models.MegaModel.ModelResourceIdentifier;
import de.dailab.masp.models.Properties.Actor;
import de.dailab.masp.models.Properties.BooleanProperty;
import de.dailab.masp.models.Properties.PropertiesFactory;
import de.dailab.masp.models.Properties.Sensor;
import de.dailab.masp.models.Properties.ServiceContainer;

/**
 * <code>DummyVariableSynchronizationComponentTest</code> TODO add missing
 * JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since TODO
 */
public class DummyVariableSynchronizationComponentTest extends TestCase {

    public final void testValueSynchronization() {
        // construct a test model
        final Environment environment = ContextFactory.eINSTANCE.createEnvironment();

        final ServiceContainer container = PropertiesFactory.eINSTANCE.createServiceContainer();
        environment.getServiceContainers().add(container);

        final Sensor sensor = PropertiesFactory.eINSTANCE.createSensor();
        container.getServices().add(sensor);
        final Actor actor = PropertiesFactory.eINSTANCE.createActor();
        container.getServices().add(actor);

        // simple device
        final Fan fan = ContextFactory.eINSTANCE.createFan();
        environment.getDevices().add(fan);
        fan.setId("testfan");
        final BooleanProperty on = PropertiesFactory.eINSTANCE.createBooleanProperty();
        on.setValue(Boolean.TRUE);
        fan.setOn(on);

        // start the component
        final DummyVariableSynchronizationComponent component = new DummyVariableSynchronizationComponent();

        final Map<String, String> configuration = new HashMap<String, String>();

        final String variable = "Yo!";

        // configure dishwasher
        configuration.put(DummyVariableSynchronizationComponent.PROPERTY_KEY_VARIABLE, variable);
        configuration.put(DummyVariableSynchronizationComponent.PROPERTY_KEY_PROPERTY_QUERY, "devices[id='" + fan.getId() + "']/on");

        component.startHook(configuration);

        // pass the models
        ModelResourceIdentifier identifier = MegaModelFactory.eINSTANCE.createModelResourceIdentifier();
        identifier.setRepositoryName("test repo");
        identifier.setResourceID("wrong model");
        final Map<ModelResourceIdentifier, Object> models = new HashMap<ModelResourceIdentifier, Object>();
        models.put(identifier, MegaModelFactory.eINSTANCE.createModelResource());
        identifier = MegaModelFactory.eINSTANCE.createModelResourceIdentifier();
        identifier.setRepositoryName("test repo");
        identifier.setResourceID("correct model");
        models.put(identifier, environment);

        component.setModelResources(models);

        // start testing
        // on was true, so the value should be in the registry after start
        assertEquals(Boolean.toString(on.getValue().booleanValue()), DummyValueRegistry.getValue(variable));

        // set new value
        on.setValue(Boolean.FALSE);
        assertEquals(Boolean.toString(on.getValue().booleanValue()), DummyValueRegistry.getValue(variable));
    }
}