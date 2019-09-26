/**
 * 
 */
package org.sercho.masp.models.Context.dummy;

import java.util.Map;

import de.dailab.masp.models.Properties.ConfigurationProperty;
import de.dailab.masp.models.Properties.PropertiesFactory;

/**
 * <code>DummyServiceConstants</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 3.0
 */
public final class DummyServiceConstants {

    /**
     * <code>PROPERTY_KEY_VariableName</code> denotes the configuration property
     * key holding the name of a dummy variable.
     */
    public static final transient String PROPERTY_KEY_VariableName = "VariableName";

    public static final transient String PROPERTY_KEY_InitialValue = "InitialValue";

    public static String getInitialValue(final Map<String, String> configurationMap) {
        return configurationMap.get(PROPERTY_KEY_InitialValue);
    }

    public static void setInitialValue(final Map<String, String> configurationMap, final String value) {
        configurationMap.put(PROPERTY_KEY_InitialValue, value);
    }

    public static String getVariableName(final Map<String, String> configurationMap) {
        return getProperty(configurationMap, PROPERTY_KEY_VariableName);
    }

    public static void setVariableName(final Map<String, String> configurationMap, final String variableName) {
        configurationMap.put(PROPERTY_KEY_VariableName, variableName);
    }

    public static ConfigurationProperty createVariableNameProperty(final String variableName) {
        return createProperty(PROPERTY_KEY_VariableName, variableName);
    }

    public static ConfigurationProperty createInitialValueProperty(final String serviceID) {
        return createProperty(PROPERTY_KEY_InitialValue, serviceID);
    }

    private static ConfigurationProperty createProperty(final String key, final String value) {
        final ConfigurationProperty property = PropertiesFactory.eINSTANCE.createConfigurationProperty();
        property.setKey(key);
        property.setValue(value);
        return property;
    }

    private static String getProperty(final Map<String, String> configurationMap, final String propertyKey) {
        final String serviceID = configurationMap.get(propertyKey);
        if(serviceID == null) {
            throw new IllegalArgumentException(propertyKey + " property is missing in " + configurationMap);
        }
        return serviceID;
    }
}
