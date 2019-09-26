/**
 * 
 */
package org.sercho.masp.models.Context.modelapi;

import junit.framework.TestCase;

import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.Fan;
import org.sercho.masp.models.Context.Meter;
import org.sercho.masp.models.Context.MeterReading;

import de.dailab.masp.models.Properties.Actor;
import de.dailab.masp.models.Properties.PropertiesFactory;
import de.dailab.masp.models.Properties.Sensor;
import de.dailab.masp.models.Properties.ServiceContainer;

/**
 * <code>JSONModelAPIServiceConverterTest</code> TODO add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since TODO
 */
public class JSONModelAPIServiceConverterTest extends TestCase {

    /**
     * Test method for
     * {@link org.sercho.masp.models.Context.modelapi.JSONModelAPIServiceConverter#convertAllProperties(org.sercho.masp.models.Context.Environment, java.lang.String, java.lang.String)}
     * .
     */
    public final void testConvertAllProperties() {
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
        fan.setOn(PropertiesFactory.eINSTANCE.createBooleanProperty());
        fan.getOn().setActor(actor);

        // simple meter with a reading
        final Meter meter = ContextFactory.eINSTANCE.createMeter();
        meter.setId("testmeter");
        environment.getMeters().add(meter);

        final MeterReading reading = ContextFactory.eINSTANCE.createMeterReading();
        meter.getReadings().add(reading);
        reading.setIdentifier("testreading");
        reading.setValue(PropertiesFactory.eINSTANCE.createDoubleProperty());
        reading.getValue().setSensor(sensor);

        // convert and test
        try {
            JSONModelAPIServiceConverter.convertAllProperties(environment, "some url", "some model");
        }
        catch(final RuntimeException e) {
            e.printStackTrace();
            fail("Unexpected error: " + e.getMessage());
        }
        // check if original services have been removed
        assertNotSame(reading.getValue().getSensor(), sensor);
        assertNotSame(fan.getOn().getActor(), actor);
        assertFalse(environment.getServiceContainers().contains(container));

        // check if model-api sensors have been created
        final Actor modelAPIActor = fan.getOn().getActor();
        assertNotNull(modelAPIActor);
        assertNotNull(modelAPIActor.getActorWrapperClassName());
        assertEquals(JSONModelAPIActor.class.getName(), modelAPIActor.getActorWrapperClassName());

        final Sensor modelAPISensor = reading.getValue().getSensor();
        assertNotNull(modelAPISensor);
        assertNotNull(modelAPISensor.getSensorWrapperClassName());
        assertEquals(JSONModelAPISensor.class.getName(), modelAPISensor.getSensorWrapperClassName());
    }
}