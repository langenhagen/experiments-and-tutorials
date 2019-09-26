/**
 * 
 */
package org.sercho.masp.models.channel.api;

import org.sercho.masp.models.Context.Channel;
import org.sercho.masp.models.UI.Interactor;

/**
 * <code>NotSupportedInteractorException</code> is thrown when an element is
 * added to a {@link Channel} that is not supported by its {@link ChannelAPI}.
 * 
 * @author Grzegorz Lehmann
 * @see Channel#getApi()
 * @since 5.0.3
 */
public final class NotSupportedInteractorException extends IllegalArgumentException {

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4961936240098385639L;

    /**
     * <code>NotSupportedInteractorException</code> constructor.
     * 
     * @param interactor
     *            not supported interactor
     * @param channelAPI
     *            Channel API implementation encapsulating the channel
     */
    public NotSupportedInteractorException(final Interactor interactor,
            final ChannelAPI channelAPI) {
        super(channelAPI.getClass().getName() + " does not support interactor: " + interactor);
    }
}