/**
 * 
 */
package org.sercho.masp.models.Context.gui;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.dailab.masp.models.MetaMetaModel.MetaModel;
import de.dailab.masp.models.MetaMetaModel.ecore.EcoreMetaModelConverter;
import de.dailab.masp.models.Properties.PropertiesFactory;
import de.dailab.masp.models.Properties.PropertiesPackage;
import de.dailab.masp.models.Properties.Service;
import de.dailab.masp.models.Properties.ServiceDiscovererProxy;
import de.dailab.masp.models.Properties.util.ServiceDiscoverer;

/**
 * <code>ServiceDiscovererManager</code> manages predefined
 * {@link ServiceDiscoverer}s. This manager uses a set of predefined discoverers
 * without referencing their classes directly. Instead, it uses a set of the
 * class names which are instantiated at runtime. If the instantiation of a
 * discoverer class does not work (because the class is not available in the
 * classpath), the discoverer is ignored.
 * 
 * @author Grzegorz Lehmann
 * @since 1.3.2
 */
public final class ServiceDiscovererManager {

    /**
     * <code>LOG</code> for logging.
     */
    public static final transient Log LOG = LogFactory.getLog(ServiceDiscovererManager.class);

    /**
     * <code>create</code> factory method.
     * 
     * @return ServiceDiscovererManager - manager with some predefined
     *         discoverers
     */
    public static final ServiceDiscovererManager create() {
        final Set<ServiceDiscovererProxy> discoverers = new HashSet<ServiceDiscovererProxy>();

        // add UPnP discoverer
        final ServiceDiscovererProxy discoverer = PropertiesFactory.eINSTANCE.createServiceDiscovererProxy();
        discoverer.setDiscovererClassName("org.sercho.masp.models.Context.upnp.UPnPServiceDiscoverer");
        discoverers.add(discoverer);
        return new ServiceDiscovererManager(discoverers);
    }

    private final Set<ServiceDiscovererProxy> discoverers;

    /**
     * <code>ServiceDiscovererManager</code> constructor.
     */
    private ServiceDiscovererManager(final Set<ServiceDiscovererProxy> serviceDiscoverers) {
        // hidden constructor
        this.discoverers = serviceDiscoverers;

        // start all discoverers
        final MetaModel metaModel = EcoreMetaModelConverter.convert(PropertiesPackage.eINSTANCE);
        metaModel.getMetaType().getMetaModel().start(metaModel);
        ServiceDiscovererProxy serviceDiscovererProxy;
        for(final Iterator<ServiceDiscovererProxy> iterator = serviceDiscoverers.iterator(); iterator.hasNext();) {
            serviceDiscovererProxy = iterator.next();
            try {
                metaModel.start(serviceDiscovererProxy);
                LOG.debug("Successfully started ServiceDiscoverer " + serviceDiscovererProxy);
            }
            catch(final Throwable e) {
                LOG.warn("Failed to start ServiceDiscoverer " + serviceDiscovererProxy + ", error: " + e.getMessage(), e);
                // unable to start, remove
                iterator.remove();
            }
        }
    }

    /**
     * <code>getAllDiscoveredServices</code> returns all discovered
     * {@link Service}s.
     * 
     * @return Set&lt;Service&gt; returns all services discovered by the
     *         discoverers managed by this manager
     */
    public Set<Service> getAllDiscoveredServices() {
        final Set<Service> allServices = new HashSet<Service>();
        for(final ServiceDiscovererProxy discoverer : this.discoverers) {
            allServices.addAll(discoverer.discoverServices());
        }
        return allServices;
    }
}