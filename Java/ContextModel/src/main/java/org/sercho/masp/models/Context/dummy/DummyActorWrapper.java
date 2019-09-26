/**
 * 
 */
package org.sercho.masp.models.Context.dummy;

import java.util.Map;

import de.dailab.masp.models.Properties.AbstractActorWrapper;
import de.dailab.masp.models.Properties.ActorServiceCallException;

/**
 * <code>DummyActorWrapper</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 3.0
 */
public final class DummyActorWrapper extends AbstractActorWrapper {

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 7860365234007840643L;

    private String serviceID;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void startHook(final Map<String, String> configurationMap) {
        this.serviceID = DummyServiceConstants.getVariableName(configurationMap);
        final String initialValue = DummyServiceConstants.getInitialValue(configurationMap);
        if(initialValue != null) {
            DummyValueRegistry.set(this.serviceID, initialValue);
        }
        setAvailable(Boolean.TRUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void stopHook() {
        setAvailable(Boolean.FALSE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void set(final String newValue) throws ActorServiceCallException {
        if(this.serviceID == null) {
            throw new ActorServiceCallException("Not started");
        }
        DummyValueRegistry.set(this.serviceID, newValue);
    }
}