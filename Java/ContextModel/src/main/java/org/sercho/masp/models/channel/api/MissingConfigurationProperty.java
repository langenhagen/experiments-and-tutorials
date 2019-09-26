/**
 * 
 */
package org.sercho.masp.models.channel.api;

import java.util.Map;

/**
 * <code>MissingConfigurationProperty</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 1.1.3
 */
public final class MissingConfigurationProperty extends ChannelAPIException {

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6521691638607786399L;

    /**
     * <code>MissingConfigurationProperty</code> constructor.
     * 
     * @param channelAPI
     *            source channel API in which this exception occurred
     * @param property
     *            key of the missing property
     * @param configurationMap
     *            map with configuration properties
     */
    public MissingConfigurationProperty(final ChannelAPI channelAPI,
            final String property, final Map<String, String> configurationMap) {
        super(channelAPI, property + " property is missing in configuration " + configurationMap);
    }

}
