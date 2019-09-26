/**
 * File     ClientAPI.java
 * Package  org.sercho.masp.context.providers.discovery
 * Project  ContextProviderAPIs
 * Date     04.12.2007
 * Web      http://www.dai-labor.de
 * 
 * @author  Dirk Roscher
 */
package org.sercho.masp.models.Context.providers;

import org.omg.CORBA.Environment;

/**
 * <code>ClientAPI</code> provides access to capabilities of a found device.
 * Used to register new devices to the {@link Environment}. <br />
 * Every
 * 
 * @author Dirk Roscher
 * @author Grzegorz Lehmann
 */
public interface ClientAPI {

    /**
     * <code>setObserver</code> sets a new observer for this
     * <code>ClientAPI</code>. If <code>newObserver</code> is <code>null</code>,
     * then this <code>ClientAPI</code> will no longer send events to the
     * previously set observer. After the observer is set it is immediately
     * notified about all interaction resources of this <code>ClientAPI</code>
     * by means of
     * {@link ClientAPIObserver#newInteractionResource(org.sercho.masp.models.Context.InteractionResource)}
     * calls. <br />
     * Subsequent calls of this method with the same <code>newObserver</code>
     * value bear no effect.
     * 
     * @param newObserver
     *            new observer to set, <code>null</code> allowed
     */
    void setObserver(ClientAPIObserver newObserver);
}
