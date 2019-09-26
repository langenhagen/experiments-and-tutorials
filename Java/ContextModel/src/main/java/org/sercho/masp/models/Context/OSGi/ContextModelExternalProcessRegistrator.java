/**
 * 
 */
package org.sercho.masp.models.Context.OSGi;

import org.osgi.framework.BundleContext;
import org.sercho.masp.context.providers.location.ubisense.UBLocProvider;
import org.sercho.masp.models.Context.gui.ContextModelVisualizerPageFactory;
import org.sercho.masp.util.osgi.ExternalProcessRegistrator;

/**
 * <code>ContextModelExternalProcessRegistrator</code> is a
 * {@link ExternalProcessRegistrator} for the ContextModel external process
 * classes.
 * 
 * @author Grzegorz Lehmann
 * @since 1.3.24
 */
public final class ContextModelExternalProcessRegistrator extends
        ExternalProcessRegistrator {

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void start(final BundleContext context) throws Exception {
        registerClasses(context, ContextModelVisualizerPageFactory.class, UBLocProvider.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(final BundleContext arg0) throws Exception {
        unregisterClasses();
    }
}