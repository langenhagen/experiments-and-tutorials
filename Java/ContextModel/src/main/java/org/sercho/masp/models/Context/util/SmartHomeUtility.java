/**
 * 
 */
package org.sercho.masp.models.Context.util;

import org.sercho.masp.models.Context.Environment;

import de.dailab.masp.models.Properties.Service;
import de.dailab.masp.models.Properties.ServiceContainer;

/**
 * <code>SmartHomeUtility</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 3.0.19
 */
public final class SmartHomeUtility {

    /**
     * <code>getServiceContainer</code> retrieves a {@link ServiceContainer}
     * with a specified ID.
     * 
     * @param environment
     *            Environment object to search
     * @param containerID
     *            ID of the container to retrieve
     * @return ServiceContainer - container with the ID, or <code>null</code> if
     *         no such container exists
     */
    public static ServiceContainer getServiceContainer(final Environment environment, final String containerID) {
        if(environment == null) {
            throw new IllegalArgumentException("smartHome argument must not be null in method getServiceContainer");
        }
        if(containerID == null) {
            throw new IllegalArgumentException("containerID argument must not be null in method getServiceContainer");
        }
        synchronized(environment.getServiceContainers()) {
            for(final ServiceContainer currContainer : environment.getServiceContainers()) {
                final ServiceContainer container = getServiceContainer(currContainer, containerID);
                if(container != null) {
                    return container;
                }
            }
        }
        return null;
    }

    private static ServiceContainer getServiceContainer(final ServiceContainer container, final String containerID) {
        if(containerID.equals(container.getId())) {
            return container;
        }
        ServiceContainer childContainer;
        for(final Service service : container.getServices()) {
            if(service instanceof ServiceContainer) {
                childContainer = getServiceContainer((ServiceContainer)service, containerID);
                if(childContainer != null) {
                    return childContainer;
                }
            }
        }
        return null;
    }

    /**
     * <code>SmartHomeUtility</code> constructor.
     */
    private SmartHomeUtility() {
        // hiding constructor of utility class
    }
}
