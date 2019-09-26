/**
 * 
 */
package org.sercho.masp.models.Context;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.sercho.masp.models.channel.api.ChannelAPI;
import org.sercho.masp.models.channel.api.MissingConfigurationProperty;

/**
 * <code>ConfigurationPropertyUtility</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 1.1.3
 * @generated NOT
 */
public final class ConfigurationPropertyUtility {

    /**
     * <code>getConfigurationMap</code> converts a list of
     * {@link ConfigurationProperty} elements into a {@link Map}.
     * 
     * @param configuration
     *            list of {@link ConfigurationProperty}
     * @return Map&lt;String, String&gt; - map extracted from
     *         <code>configuration</code>
     */
    @SuppressWarnings("unchecked")
    public static final Map<String, String> getConfigurationMap(final Object configuration) {
        if(!(configuration instanceof Collection)) {
            return java.util.Collections.emptyMap();
        }
        final Collection<?> configurationList = (Collection<?>)configuration;
        final Map<String, String> configurationMap = new HashMap<String, String>(configurationList.size());
        ConfigurationProperty configurationProperty;
        for(final Object object : configurationList) {
            if(object instanceof ConfigurationProperty) {
                configurationProperty = (ConfigurationProperty)object;
                configurationMap.put(configurationProperty.getKey(), configurationProperty.getValue());
            }
        }
        return configurationMap;
    }

    /**
     * <code>getPropertyValue</code> retrieves a property value for a
     * {@link ChannelAPI}.
     * 
     * @param configuration
     *            configuration to extract the property value from
     * @param channelAPI
     *            channel needing the value
     * @param property
     *            key of the property, <code>null</code> disallowed
     * @return String - value of <code>property</code> held in
     *         <code>properties</code>, never <code>null</code>
     * @throws IllegalArgumentException
     *             if any argument is <code>null</code>
     * @throws MissingConfigurationProperty
     *             if the property is not defined in <code>configuration</code>
     */
    public static String getPropertyValue(final Object configuration, final ChannelAPI channelAPI, final String property) {
        return getPropertyValue(getConfigurationMap(configuration), channelAPI, property);
    }

    /**
     * <code>getPropertyValue</code> retrieves a property value for a
     * {@link ChannelAPI}.
     * 
     * @param configurationMap
     *            configuration map to extract the property value from
     * @param channelAPI
     *            channel needing the value
     * @param property
     *            key of the property, <code>null</code> disallowed
     * @return String - value of <code>property</code> held in
     *         <code>properties</code>, never <code>null</code>
     * @throws IllegalArgumentException
     *             if any argument is <code>null</code>
     * @throws MissingConfigurationProperty
     *             if the property is not defined in
     *             <code>configurationMap</code>
     */
    public static String getPropertyValue(final Map<String, String> configurationMap, final ChannelAPI channelAPI, final String property) {
        if(configurationMap == null) {
            throw new IllegalArgumentException("configurationMap argument must not be null in method getPropertyValue");
        }
        if(channelAPI == null) {
            throw new IllegalArgumentException("channelAPI argument must not be null in method getPropertyValue");
        }
        if(property == null) {
            throw new IllegalArgumentException("property argument must not be null in method getPropertyValue");
        }
        final String propertyValue = configurationMap.get(property);
        if(propertyValue == null) {
            throw new MissingConfigurationProperty(channelAPI, property, configurationMap);
        }
        return propertyValue;
    }

    /**
     * <code>getPropertyValue</code> retrieves a property value for a
     * {@link ChannelAPI}.
     * 
     * @param configurationMap
     *            configuration map to extract the property value from
     * @param property
     *            key of the property, <code>null</code> disallowed
     * @return String - value of <code>property</code> held in
     *         <code>properties</code>, never <code>null</code>
     * @throws IllegalArgumentException
     *             if any argument is <code>null</code> or the property is not
     *             defined in <code>configurationMap</code>
     */
    public static String getPropertyValue(final Map<String, String> configurationMap, final String property) {
        if(configurationMap == null) {
            throw new IllegalArgumentException("configurationMap argument must not be null in method getPropertyValue");
        }
        if(property == null) {
            throw new IllegalArgumentException("property argument must not be null in method getPropertyValue");
        }
        final String propertyValue = configurationMap.get(property);
        if(propertyValue == null) {
            throw new IllegalArgumentException(property + " property is missing in configuration " + configurationMap);
        }
        return propertyValue;
    }

    /**
     * <code>getPropertyValueInteger</code> retrieves a property value as an
     * integer.
     * 
     * @param configurationMap
     *            configuration map to extract the property value from
     * @param property
     *            key of the property, <code>null</code> disallowed
     * @return int - value of <code>property</code> held in
     *         <code>properties</code>
     * @throws IllegalArgumentException
     *             if any argument is <code>null</code> or the property is not
     *             defined in <code>configurationMap</code> or its value is not
     *             an integer
     */
    public static int getPropertyValueInteger(final Map<String, String> configurationMap, final String property) {
        if(configurationMap == null) {
            throw new IllegalArgumentException("configurationMap argument must not be null in method getPropertyValue");
        }
        if(property == null) {
            throw new IllegalArgumentException("property argument must not be null in method getPropertyValue");
        }
        final String propertyValue = getPropertyValue(configurationMap, property);
        try {
            return Integer.parseInt(propertyValue);
        }
        catch(final NumberFormatException e) {
            throw new IllegalArgumentException(property + " property value " + propertyValue + " is not an integer");
        }
    }

    /**
     * <code>createConfigurationProperties</code> creates a new
     * {@link ConfigurationProperty} for a specified key and value and puts it
     * in a list.
     * 
     * @param key
     *            key for the property
     * @param value
     *            value for the property
     * @return EList&lt;ConfigurationProperty&gt; - configuration property with
     *         the specified key and containing the specified value
     */
    public static EList<ConfigurationProperty> createConfigurationProperties(final String key, final String value) {
        final EList<ConfigurationProperty> properties = new BasicEList<ConfigurationProperty>();
        properties.add(createConfigurationProperty(key, value));
        return properties;
    }

    /**
     * <code>createConfigurationProperty</code> creates a new
     * {@link ConfigurationProperty} for a specified key and value.
     * 
     * @param key
     *            key for the property
     * @param value
     *            value for the property
     * @return ConfigurationProperty - configuration property with the specified
     *         key and containing the specified value
     */
    public static ConfigurationProperty createConfigurationProperty(final String key, final String value) {
        final ConfigurationProperty property = ContextFactory.eINSTANCE.createConfigurationProperty();
        property.setKey(key);
        property.setValue(value);
        return property;
    }

    /**
     * <code>ConfigurationPropertyUtility</code> hidden constructor.
     */
    private ConfigurationPropertyUtility() {
        // hiding constructor of utility class
    }
}