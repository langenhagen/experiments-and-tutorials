package org.sercho.masp.models.Context.gui.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.gui.ElementProperty;
import org.sercho.masp.models.Context.gui.PopupInvoker;
import org.sercho.masp.models.Context.gui.VisualizerManager;
import org.sercho.masp.models.Context.gui.enums.ServiceType;
import org.sercho.masp.models.Context.gui.frames.ServiceDiscoveryFrame;

import de.dailab.masp.models.Properties.Property;
import de.dailab.masp.models.Properties.Service;
import de.dailab.masp.models.Properties.ServiceContainer;

/**
 * This {@link ActionListener} displays the {@link ServiceDiscoveryFrame}.
 * 
 * @author Andre Schulz
 * @since 1.2.29
 */
public class DiscoverServiceActionListener extends ElementAddServiceActionListener
        implements ServiceSelectedActionListener {

    /**
     * The logger.
     */
    private static final transient Log LOG = LogFactory.getLog(DiscoverServiceActionListener.class);

    private final Environment environment;

    private final VisualizerManager visualizerManager;

    /**
     * Creates a new {@link DiscoverServiceActionListener}.
     * 
     * @param elementProperty
     *            The {@link ElementProperty} the {@link JFrame} is for.
     * @param serviceContainer
     *            The {@link ServiceContainer} where to add a new
     *            {@link Service}.
     * @param propertyName
     *            The name of the {@link Property}.
     * @param type
     *            The type of the {@link Service}.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     * @param environment
     *            The {@link Environment}.
     */
    public DiscoverServiceActionListener(final ElementProperty elementProperty,
            final String propertyName, final ServiceContainer serviceContainer,
            final Environment environment, final VisualizerManager visualizerManager,
            final ServiceType type, final PopupInvoker popupInvoker) {
        super(elementProperty, serviceContainer, propertyName, type, popupInvoker);
        this.environment = environment;
        this.visualizerManager = visualizerManager;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        // TODO set multiple correct
        new ServiceDiscoveryFrame(this.visualizerManager, this.serviceType, true, this);
    }// actionPerformed

    @Override
    public void finishAction(final Service service) {
        if(service == null) {
            throw new IllegalArgumentException("service argument must not be null in method finishAction!");
        }

        if(LOG.isDebugEnabled()) {
            LOG.debug("finishAction " + service);
        }

        if(super.serviceContainer != null) {
            // TODO check id of service
            super.serviceContainer.addService(service);
        } else if(this.environment != null) {
            if(service instanceof ServiceContainer) {
                // TODO check id of service container
                this.environment.addServiceContainer((ServiceContainer)service, null);
            } else {
                throw new IllegalArgumentException("Failed to add service: Can not add simple Service to environment!");
            }
        } else {
            throw new IllegalStateException("Failed to add Service: Either Environment or ServiceContainer must not be null!");
        }
    }

    public Environment getEnvironment() {
        return this.environment;
    }

    public ServiceContainer getServiceContainer() {
        return super.serviceContainer;
    }
}
