/**
 * 
 */
package org.sercho.masp.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.util.EContentAdapter;

/**
 * <code>DisposeableEContentAdapter</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 1.0.6
 */
public abstract class DisposeableEContentAdapter extends EContentAdapter implements
        Disposeable {

    private final Set<Notifier> notifiers = Collections.synchronizedSet(new HashSet<Notifier>());

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void addAdapter(final Notifier notifier) {
        super.addAdapter(notifier);
        this.notifiers.add(notifier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void removeAdapter(final Notifier notifier) {
        super.removeAdapter(notifier);
        this.notifiers.remove(notifier);
    }

    /**
     * {@inheritDoc}
     */
    public final void dispose() {
        synchronized(this.notifiers) {
            for(final Notifier notifier : this.notifiers) {
                notifier.eAdapters().remove(this);
            }
        }
        disposed();
    }

    protected void disposed() {
        // overwrite if necessary
    }
}