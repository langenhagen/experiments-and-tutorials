/**
 * 
 */
package org.sercho.masp.models.Context.providers;

import org.sercho.masp.models.Context.InteractionResource;

/**
 * <code>FixedInteractionResourcesClientAPI</code> is a {@link ClientAPI}
 * implementation for clients with a fixed set of {@link InteractionResource}s.
 * 
 * @author Grzegorz Lehmann
 * @since 5.0.3
 */
public final class FixedInteractionResourcesClientAPI extends AbstractClientAPI {

    /**
     * <code>create</code> creates a {@link ClientAPI} for a fixed set of
     * interaction resources. The returned {@link ClientAPI} behaves as required
     * by the specification and thus all {@link InteractionResource}s specified
     * in <code>resources</code> are passed to any new observer set in
     * {@link #setObserver(ClientAPIObserver)} using
     * {@link ClientAPIObserver#newInteractionResource(InteractionResource)}.
     * 
     * @param resources
     *            all resources of the client
     * @return ClientAPI - a client API with all interaction resources
     */
    public static ClientAPI create(final InteractionResource... resources) {
        final FixedInteractionResourcesClientAPI clientAPI = new FixedInteractionResourcesClientAPI();
        for(final InteractionResource resource : resources) {
            clientAPI.newInteractionResource(resource);
        }
        return clientAPI;
    }

    /**
     * <code>FixedInteractionResourcesClientAPI</code> constructor.
     */
    private FixedInteractionResourcesClientAPI() {
        // hiding constructor
    }
}