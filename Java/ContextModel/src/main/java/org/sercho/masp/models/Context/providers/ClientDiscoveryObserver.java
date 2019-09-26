/**
 * File     DeviceDiscoveryObserver.java
 * Package  org.sercho.masp.context.providers.discovery
 * Project  ContextProviderAPIs
 * Date     03.12.2007
 * Web      http://www.dai-labor.de
 * 
 * @author  Dirk Roscher
 */
package org.sercho.masp.models.Context.providers;

import java.io.Serializable;

import org.sercho.masp.models.Context.InteractionResource;

/**
 * <code>DeviceDiscoveryObserver</code> receives information about clients.
 * 
 * @author Dirk Roscher
 */

public interface ClientDiscoveryObserver extends Serializable {

    /**
     * <code>newClient</code> informs the observer about the appearance of new
     * clients. The observer has to set the given arguments.
     * 
     * @param clientId
     *            identifier of the client
     * @param api
     *            API for communication with the client
     */
    void newClient(String clientId, ClientAPI api);

    /**
     * <code>clientGone</code> inform the observer about the disappearance of
     * clients. The Observer has to remove all {@link InteractionResource}s from
     * the client.
     * 
     * @param clientId
     *            identifier of the client.
     */
    void clientGone(String clientId);

}
