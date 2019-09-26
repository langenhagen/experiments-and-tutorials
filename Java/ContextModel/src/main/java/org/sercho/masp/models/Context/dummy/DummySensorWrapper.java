/**
 * 
 */
package org.sercho.masp.models.Context.dummy;

import java.util.Map;

import org.sercho.masp.models.Context.dummy.DummyValueRegistry.DummyValueObserver;

import de.dailab.masp.models.Properties.AbstractSensorWrapper;

/**
 * <code>DummySensorWrapper</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 3.0
 */
public final class DummySensorWrapper extends AbstractSensorWrapper implements
        DummyValueObserver {

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -630619166781266658L;

    private String serviceID;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void startHook(final Map<String, String> configurationMap) {
        this.serviceID = DummyServiceConstants.getVariableName(configurationMap);
        DummyValueRegistry.addObserver(this);
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
        DummyValueRegistry.removeObserver(this);
        setAvailable(Boolean.FALSE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newValue(final String sensorID, final String newValue) {
        if(sensorID.equals(this.serviceID)) {
            newValue(newValue);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final Object o) {
        return o == this ? 0 : -1;
    }
}