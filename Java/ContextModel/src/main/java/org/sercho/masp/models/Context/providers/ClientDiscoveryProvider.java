/**
 * File     DeviceDiscoveryProvider.java
 * Package  org.sercho.masp.context.providers.discovery
 * Project  ContextProviderAPIs
 * Date     03.12.2007
 * Web      http://www.dai-labor.de
 * 
 * @author  Dirk Roscher
 */
package org.sercho.masp.models.Context.providers;

/**
 * <code>DeviceDiscoveryObserver</code> is responsible for providing information
 * about active clients.
 * 
 * @author Dirk Roscher
 */
public interface ClientDiscoveryProvider {

    /**
     * <code>start</code> starts the provider. After a call of this method, the
     * provider sends initial device data to the <code>observer</code> by
     * calling {@link ClientDiscoveryObserver#newClient(String, ClientAPI)} once
     * and only once for each currently present client. <br />
     * If a client disappears this provider calls the
     * {@link ClientDiscoveryObserver#clientGone(String)} method with the ID of
     * the client. If the client reappears or a new client appears the
     * <code>observer</code> is notified using
     * {@link ClientDiscoveryObserver#newClient(String, ClientAPI)}. <br />
     * A {@link #stop()} call resets the behavior of this method, in other words
     * after a {@link #stop()} call <code>start</code> behaves as if it were
     * never called before. Subsequent calls of <code>start</code> without an
     * intermediate {@link #stop()} call have no effect.
     * 
     * @param observer
     *            callback for events
     * @throws IllegalArgumentException
     *             if <code>observer</code> is <code>null</code>
     * @see #stop()
     */
    void start(ClientDiscoveryObserver observer);

    /**
     * <code>stop</code> stops the delivery client information to the callback
     * observer set in {@link #start(ClientDiscoveryObserver)}. After a
     * <code>stop</code> call the observer should no longer be called. <br />
     * Please note, that when being stopped this provider will report all its
     * clients as gone to its {@link ClientDiscoveryObserver} using
     * {@link ClientDiscoveryObserver#clientGone(String)}. It may be said, that
     * the <code>stop</code> method completely resets this provider.
     */
    void stop();
}
