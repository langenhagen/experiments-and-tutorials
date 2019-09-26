/**
 * 
 */
package org.sercho.masp.models.Context.providers;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import org.sercho.masp.models.Context.Channel;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.UI.ConcreteInteractor;
import org.sercho.masp.models.channel.api.ChannelAPI;

/**
 * <code>AbstractClientAPI</code> simplifies the implementation of
 * {@link ClientAPI}s. This class assures thread safety and also makes sure each
 * new {@link ClientAPIObserver} is notified about existing interaction
 * resources immediately after a {@link #setObserver(ClientAPIObserver)} call
 * (which is required by the {@link ClientAPI} specification).
 * 
 * @author Grzegorz Lehmann
 * @since 5.0.3
 */
@ThreadSafe
public abstract class AbstractClientAPI implements ClientAPI {

    /**
     * <code>clientObserver</code>
     */
    @GuardedBy("clientObserverLock")
    private ClientAPIObserver clientObserver;

    /**
     * <code>clientObserverLock</code> guards {@link #clientObserver}
     */
    private final transient ReadWriteLock clientObserverLock = new ReentrantReadWriteLock();

    /**
     * <code>resources</code> holds the interaction resources handled by this
     * {@link ClientAPI}. This is needed in order to notify new
     * {@link ClientAPIObserver}s about resources that existed before the
     * observer was set (this is required by the {@link ClientAPI}
     * specification). <br />
     * The intrinsic lock of the <code>Hashtable</code> is used to guard this
     * map.
     */
    @GuardedBy("itself")
    private transient final Map<String, InteractionResource> resources = new Hashtable<String, InteractionResource>();

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setObserver(final ClientAPIObserver newObserver) {
        this.clientObserverLock.writeLock().lock();
        try {
            // assign new observer
            this.clientObserver = newObserver;
            // if not null notify about existing interaction resources
            if(this.clientObserver != null) {
                synchronized(this.resources) {
                    for(final InteractionResource resource : this.resources.values()) {
                        this.clientObserver.newInteractionResource(resource);
                    }
                }
                setObserverHook();
            }
        }
        finally {
            this.clientObserverLock.writeLock().unlock();
        }
    }

    /**
     * <code>setObserverHook</code> override in order to be informed about an
     * {@link ClientAPIObserver} set.
     */
    protected void setObserverHook() {
        // override if necessary
    }

    /**
     * <code>interactionResourceGone</code> is called whenever an interaction
     * resource becomes unavailable.
     * 
     * @param resourceId
     *            ID of the no longer available interaction resource,
     *            <code>null</code> disallowed
     * @throws IllegalArgumentException
     *             if <code>resourceId</code> is <code>null</code> or no
     *             interaction resource exists with such id
     */
    protected final void interactionResourceGone(final String resourceId) {
        if(resourceId == null) {
            throw new IllegalArgumentException("resourceId is null");
        }
        if(this.resources.remove(resourceId) == null) {
            throw new IllegalArgumentException("No InteractionResource exists with Id: " + resourceId);
        }
        this.clientObserverLock.readLock().lock();
        try {
            if(this.clientObserver != null) {
                this.clientObserver.interactionResourceGone(resourceId);
            }
        }
        finally {
            this.clientObserverLock.readLock().unlock();
        }
    }

    /**
     * <code>channelGone</code> is called whenever a channel becomes
     * unavailable.
     * 
     * @param channelId
     *            ID of the no longer available channel, <code>null</code>
     *            disallowed
     * @throws IllegalArgumentException
     *             if <code>channelId</code> is <code>null</code>
     */
    protected final void channelGone(final String channelId) {
        if(channelId == null) {
            throw new IllegalArgumentException("channelId is null");
        }
        this.clientObserverLock.readLock().lock();
        try {
            if(this.clientObserver != null) {
                this.clientObserver.channelGone(channelId);
            }
        }
        finally {
            this.clientObserverLock.readLock().unlock();
        }
    }

    /**
     * <code>newInteractionResource</code> is called whenever a new interaction
     * resource is available within the client.
     * 
     * @param resource
     *            new interaction resource to encode, <code>null</code>
     *            disallowed
     * @throws IllegalArgumentException
     *             if <code>resource</code> is <code>null</code> or its Id is
     *             <code>null</code>
     */
    protected final void newInteractionResource(final InteractionResource resource) {
        if(resource == null) {
            throw new IllegalArgumentException("resource is null");
        }
        final String resourceId = resource.getId();
        if(resourceId == null) {
            throw new IllegalArgumentException("null Id of resource: " + resource);
        }
        this.resources.put(resourceId, resource);
        this.clientObserverLock.readLock().lock();
        try {
            if(this.clientObserver != null) {
                this.clientObserver.newInteractionResource(resource);
            }
        }
        finally {
            this.clientObserverLock.readLock().unlock();
        }
    }

    /**
     * <code>newChannel</code> is called whenever a new channel is available
     * within the client.
     * 
     * @param resourceId
     *            ID of the interaction resource containing the channel,
     *            <code>null</code> disallowed
     * @param channel
     *            new channel to encode, <code>null</code> disallowed
     * @throws IllegalArgumentException
     *             if any argument is <code>null</code>
     */
    protected final <C extends ConcreteInteractor> void newChannel(final String resourceId, final Channel<C, ? extends ChannelAPI> channel) {
        if(resourceId == null) {
            throw new IllegalArgumentException("resourceId is null");
        }
        if(channel == null) {
            throw new IllegalArgumentException("channel is null");
        }
        this.clientObserverLock.readLock().lock();
        try {
            if(this.clientObserver != null) {
                this.clientObserver.newChannel(resourceId, channel);
            }
        }
        finally {
            this.clientObserverLock.readLock().unlock();
        }
    }
}