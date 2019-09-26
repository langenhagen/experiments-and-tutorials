/**
 * 
 */
package org.sercho.masp.models.Context;

import java.util.List;
import java.util.Set;

import junit.framework.TestCase;
import de.dailab.masp.models.MetaMetaModel.MetaMetaModelUtility;
import de.dailab.masp.models.MetaMetaModel.MetaModel;
import de.dailab.masp.models.MetaMetaModel.ModelCallback;
import de.dailab.masp.models.MetaMetaModel.ProxyMetaClass;
import de.dailab.masp.models.MetaMetaModel.ecore.EcoreMetaModelConverter;
import de.dailab.masp.models.Properties.ConfigurationProperty;
import de.dailab.masp.models.Properties.PropertiesFactory;
import de.dailab.masp.models.Properties.PropertiesPackage;
import de.dailab.masp.models.Properties.Sensor;
import de.dailab.masp.models.Properties.SensorWrapper;
import de.dailab.masp.models.Properties.Service;

/**
 * <code>SensorTest</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 3.0
 */
public final class SensorTest extends TestCase {

    /**
     * <code>TestSensorWrapper</code> is a dummy {@link SensorWrapper} for
     * tests.
     * 
     * @author Grzegorz Lehmann
     */
    public static final class TestSensorWrapper implements SensorWrapper {

        /**
         * <code>serialVersionUID</code>
         */
        private static final long serialVersionUID = 1125063946024847645L;

        Object sensorConfiguration;

        ModelCallback modelCallback;

        /**
         * {@inheritDoc}
         */
        @Override
        public synchronized void start(final Object configuration, final ModelCallback callback) {
            this.sensorConfiguration = configuration;
            this.modelCallback = callback;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void stop() {
            // nothing to do here yet
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isStarted() {
            // nothing to do here yet
            return false;
        }
    }

    private static final String CONFIGURATION_KEY_DEVICE_UDN = "udn";

    private static final String CONFIGURATION_KEY_SERVICE_ID = "serviceId";

    private static final String CONFIGURATION_KEY_VARIABLE_NAME = "name";

    /**
     * <code>testMetaClass</code> tests the correctness of the Sensor
     * {@link ProxyMetaClass}.
     */
    @SuppressWarnings("unchecked")
    public void testMetaClass() {
        // get Appliances metamodel
        final MetaModel appliancesMetaModel = EcoreMetaModelConverter.convert(ContextPackage.eINSTANCE);
        // start the metamodel
        appliancesMetaModel.getMetaType().getMetaModel().start(appliancesMetaModel);

        // configure sensor
        final String sensorID = "Hello world!";
        final Sensor sensor = PropertiesFactory.eINSTANCE.createSensor();
        setConfigurationProperty(sensor, CONFIGURATION_KEY_DEVICE_UDN, "UDN");
        setConfigurationProperty(sensor, CONFIGURATION_KEY_SERVICE_ID, "SERVICE_ID");
        setConfigurationProperty(sensor, CONFIGURATION_KEY_VARIABLE_NAME, "VARIABLE_NAME");
        sensor.setId(sensorID);
        sensor.setSensorWrapperClassName(TestSensorWrapper.class.getName());
        assertNull(sensor.getSensorWrapper());

        // start the sensor
        final ProxyMetaClass sensorMetaClass = (ProxyMetaClass)MetaMetaModelUtility.getMetaElement(appliancesMetaModel.getTypes(), PropertiesPackage.Literals.SENSOR.getName());
        sensorMetaClass.start(sensor);
        assertNotNull(sensor.getSensorWrapper());
        assertTrue(sensor.getSensorWrapper() instanceof TestSensorWrapper);
        final TestSensorWrapper sensorWrapper = (TestSensorWrapper)sensor.getSensorWrapper();

        assertTrue(sensorWrapper.sensorConfiguration instanceof List);
        final List<ConfigurationProperty> configuration = (List<ConfigurationProperty>)sensorWrapper.sensorConfiguration;
        assertEquals(3, configuration.size());
        assertConfigurationPropertyValue(configuration, CONFIGURATION_KEY_DEVICE_UDN, "UDN");
        assertConfigurationPropertyValue(configuration, CONFIGURATION_KEY_SERVICE_ID, "SERVICE_ID");
        assertConfigurationPropertyValue(configuration, CONFIGURATION_KEY_VARIABLE_NAME, "VARIABLE_NAME");

        assertNotNull(sensorWrapper.modelCallback);
        final Set<String> executableElementNames = sensorWrapper.modelCallback.getExecutableElementNames();
        assertNotNull(executableElementNames);
        assertEquals(executableElementNames.toString(), 2, executableElementNames.size());
        assertTrue(executableElementNames.contains("setNewValue"));
        assertTrue(executableElementNames.contains("setNewAvailable"));
    }

    private static void assertConfigurationPropertyValue(final List<ConfigurationProperty> configuration, final String key, final String value) {
        for(final ConfigurationProperty configurationProperty : configuration) {
            if(key.equals(configurationProperty.getKey())) {
                assertEquals(value, configurationProperty.getValue());
                return;
            }
        }
        fail("Missing property " + key);
    }

    private static void setConfigurationProperty(final Service service, final String key, final String value) {
        final de.dailab.masp.models.Properties.ConfigurationProperty configurationProperty = PropertiesFactory.eINSTANCE.createConfigurationProperty();
        configurationProperty.setKey(key);
        configurationProperty.setValue(value);
        service.getConfiguration().add(configurationProperty);
    }
}
