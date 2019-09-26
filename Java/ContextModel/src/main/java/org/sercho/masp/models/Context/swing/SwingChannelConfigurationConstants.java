/**
 * 
 */
package org.sercho.masp.models.Context.swing;

import java.awt.Window;
import java.util.Map;

import org.sercho.masp.models.Context.ConfigurationPropertyUtility;
import org.sercho.masp.models.channel.api.ChannelAPI;

/**
 * <code>SwingChannelConfigurationConstants</code> holds configuration constants
 * for the swing channels.
 * 
 * @author Grzegorz Lehmann
 * @since 1.1.3
 */
public final class SwingChannelConfigurationConstants {

    /**
     * <code>PROPERTY_WINDOW_NAME</code> denotes the name of the configuration
     * property holding the name of a channel's {@link Window}.
     */
    public static final transient String PROPERTY_WINDOW_NAME = "WindowName";

    /**
     * <code>PROPERTY_WINDOW_WIDTH</code> denotes the name of the configuration
     * property holding the desired width of a channel's {@link Window}.
     */
    public static final transient String PROPERTY_WINDOW_WIDTH = "WindowWidth";

    /**
     * <code>PROPERTY_WINDOW_HEIGHT</code> denotes the name of the configuration
     * property holding the desired height of a channel's {@link Window}.
     */
    public static final transient String PROPERTY_WINDOW_HEIGHT = "WindowHeight";

    /**
     * <code>PROPERTY_WINDOW_POSITION_X</code> denotes the name of the
     * configuration property holding the desired X position of a channel's
     * {@link Window}.
     */
    public static final transient String PROPERTY_WINDOW_POSITION_X = "WindowPositionX";

    /**
     * <code>PROPERTY_WINDOW_POSITION_Y</code> denotes the name of the
     * configuration property holding the desired Y position of a channel's
     * {@link Window}.
     */
    public static final transient String PROPERTY_WINDOW_POSITION_Y = "WindowPositionY";

    /**
     * <code>getWindowWidth</code> returns the value of the
     * {@value #PROPERTY_WINDOW_WIDTH} property of <code>configuration</code>.
     * 
     * @param configuration
     *            configuration map, <code>null</code> disallowed
     * @param channelAPI
     *            channel needing the value
     * @return int - non negative integer value of the
     *         {@value #PROPERTY_WINDOW_WIDTH} property in
     *         <code>configuration</code>
     * @throws IllegalArgumentException
     *             if <code>configuration</code> is <code>null</code>, does not
     *             contain a value for the property or the value is not a
     *             non-negative integer
     */
    public static int getWindowWidth(final Map<String, String> configuration, final ChannelAPI channelAPI) {
        return getNonNegativeInt(configuration, channelAPI, PROPERTY_WINDOW_WIDTH);
    }

    /**
     * <code>getWindowHeight</code> returns the value of the
     * {@value #PROPERTY_WINDOW_HEIGHT} property of <code>configuration</code>.
     * 
     * @param configuration
     *            configuration map, <code>null</code> disallowed
     * @param channelAPI
     *            channel needing the value
     * @return int - non negative integer value of the
     *         {@value #PROPERTY_WINDOW_HEIGHT} property in
     *         <code>configuration</code>
     * @throws IllegalArgumentException
     *             if <code>configuration</code> is <code>null</code>, does not
     *             contain a value for the property or the value is not a
     *             non-negative integer
     */
    public static int getWindowHeight(final Map<String, String> configuration, final ChannelAPI channelAPI) {
        return getNonNegativeInt(configuration, channelAPI, PROPERTY_WINDOW_HEIGHT);
    }

    /**
     * <code>getWindowPositionX</code> returns the value of the
     * {@value #PROPERTY_WINDOW_POSITION_X} property of
     * <code>configuration</code>.
     * 
     * @param configuration
     *            configuration map, <code>null</code> disallowed
     * @param channelAPI
     *            channel needing the value
     * @return int - non negative integer value of the
     *         {@value #PROPERTY_WINDOW_POSITION_X} property in
     *         <code>configuration</code>
     * @throws IllegalArgumentException
     *             if <code>configuration</code> is <code>null</code>, does not
     *             contain a value for the property or the value is not a
     *             non-negative integer
     */
    public static int getWindowPositionX(final Map<String, String> configuration, final ChannelAPI channelAPI) {
        return getNonNegativeInt(configuration, channelAPI, PROPERTY_WINDOW_POSITION_X);
    }

    /**
     * <code>getWindowPositionY</code> returns the value of the
     * {@value #PROPERTY_WINDOW_POSITION_Y} property of
     * <code>configuration</code>.
     * 
     * @param configuration
     *            configuration map, <code>null</code> disallowed
     * @param channelAPI
     *            channel needing the value
     * @return int - non negative integer value of the
     *         {@value #PROPERTY_WINDOW_POSITION_Y} property in
     *         <code>configuration</code>
     * @throws IllegalArgumentException
     *             if <code>configuration</code> is <code>null</code>, does not
     *             contain a value for the property or the value is not a
     *             non-negative integer
     */
    public static int getWindowPositionY(final Map<String, String> configuration, final ChannelAPI channelAPI) {
        return getNonNegativeInt(configuration, channelAPI, PROPERTY_WINDOW_POSITION_Y);
    }

    private static int getNonNegativeInt(final Map<String, String> configuration, final ChannelAPI channelAPI, final String propertyName) {
        final String value = ConfigurationPropertyUtility.getPropertyValue(configuration, channelAPI, propertyName);
        final int intValue;
        try {
            intValue = Integer.parseInt(value);
        }
        catch(final NumberFormatException e) {
            throw new IllegalArgumentException("Value of configuration property '" + propertyName + "' is not an integer, but: " + value);
        }
        if(intValue < 0) {
            throw new IllegalArgumentException("Value of configuration property '" + propertyName + "' is a negative integer: " + value);
        }
        return intValue;
    }

    private SwingChannelConfigurationConstants() {
        // hiding constructor of utility class
    }
}