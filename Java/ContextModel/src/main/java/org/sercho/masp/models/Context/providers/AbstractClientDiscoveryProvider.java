/**
 * 
 */
package org.sercho.masp.models.Context.providers;

import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <code>AbstractClientDiscoveryProvider</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * @author Grzegorz Lehmann
 */
@ThreadSafe
public abstract class AbstractClientDiscoveryProvider implements ClientDiscoveryProvider {

    /**
     * <code>log</code> for logging
     */
    public final transient Log log = LogFactory.getLog(getClass());

    /**
     * <code>clientObserver</code>
     */
    @GuardedBy("clientObserverLock")
    private ClientDiscoveryObserver clientObserver;

    /**
     * <code>clientObserverLock</code> locks {@link #clientObserver}
     */
    private final transient ReadWriteLock clientObserverLock = new ReentrantReadWriteLock();

    @GuardedBy("clients")
    private final Map<String, ClientAPI> clients = new Hashtable<String, ClientAPI>();

    /**
     * <code>clientGone</code> should be used by subclasses to inform about a no
     * longer available client. This method behaves according to {@link #stop()}
     * so subclasses can call it in any state of this discoverer, also if this
     * discoverer has not been started before a call of this method.
     * 
     * @param clientID
     *            identifier of the client
     * @throws IllegalArgumentException
     *             if <code>clientID</code> is <code>null</code>
     */
    protected final void clientGone(final String clientID) {
        if(clientID == null) {
            throw new IllegalArgumentException("clientID is null");
        }

        this.clientObserverLock.readLock().lock();
        try {
            if(this.clients.remove(clientID) != null && this.clientObserver != null) {
                this.clientObserver.clientGone(clientID);
            }
        }
        finally {
            this.clientObserverLock.readLock().unlock();
        }
    }

    /**
     * <code>getClient</code> returns the API of a client
     * 
     * @param clientID
     *            identifier of the client
     * @return API of client
     */
    protected final ClientAPI getClient(final String clientID) {
        ClientAPI back;
        if(clientID == null) {
            throw new IllegalArgumentException("clientID is null");
        }

        this.clientObserverLock.readLock().lock();
        back = this.clients.get(clientID);
        this.clientObserverLock.readLock().unlock();

        return back;
    }

    /**
     * <code>newClient</code> should be used by subclasses to inform about a new
     * client. This method behaves according to
     * {@link #start(ClientDiscoveryObserver)} so subclasses can call it in any
     * state of this discoverer. If this discoverer has not been started before
     * a call of this method, the new client will be cached and passed to the
     * observer as soon as this discoverer is started.
     * 
     * @param clientID
     *            identifier of the client
     * @param api
     *            API for communication with the client
     * @throws IllegalArgumentException
     *             if any argument is <code>null</code>
     * @see ClientDiscoveryObserver#newClient(String, ClientAPI)
     */
    protected final void newClient(final String clientID, final ClientAPI client) {
        if(clientID == null) {
            throw new IllegalArgumentException("clientID is null");
        }
        if(client == null) {
            throw new IllegalArgumentException("client is null");
        }
        this.clientObserverLock.readLock().lock();
        try {
            // store new client
            this.clients.put(clientID, client);
            // inform observer if present
            if(this.clientObserver != null) {
                this.clientObserver.newClient(clientID, client);
            }
        }
        finally {
            this.clientObserverLock.readLock().unlock();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void start(final ClientDiscoveryObserver observer) {
        if(observer == null) {
            throw new IllegalArgumentException("observer is null");
        }
        this.clientObserverLock.writeLock().lock();
        try {
            if(this.clientObserver == null) {
                this.clientObserver = observer;
                synchronized(this.clients) {
                    for(final Entry<String, ClientAPI> entry : this.clients.entrySet()) {
                        this.clientObserver.newClient(entry.getKey(), entry.getValue());
                    }
                }
                started();
            }
        }
        finally {
            this.clientObserverLock.writeLock().unlock();
        }
    }

    /**
     * <code>started</code> is called whenever this provider has been started
     * according to semantics of {@link #start(ClientDiscoveryObserver)}.
     */
    protected abstract void started();

    /**
     * {@inheritDoc}
     */
    @Override
    public final void stop() {
        this.clientObserverLock.writeLock().lock();
        try {
            if(this.clientObserver != null) {
                // remove all clients
                synchronized(this.clients) {
                    for(final String clientID : this.clients.keySet()) {
                        this.clientObserver.clientGone(clientID);
                    }
                }
                // reset
                this.clientObserver = null;
                stopped();
            }
        }
        finally {
            this.clientObserverLock.writeLock().unlock();
        }
    }

    /**
     * <code>stopped</code> is called whenever this provider has been stopped
     * according to semantics of {@link #stop()}.
     */
    protected abstract void stopped();
}