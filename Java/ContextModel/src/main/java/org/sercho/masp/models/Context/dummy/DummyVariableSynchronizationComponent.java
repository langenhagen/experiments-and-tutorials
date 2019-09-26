/**
 * 
 */
package org.sercho.masp.models.Context.dummy;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.JXPathInvalidSyntaxException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.SingletonAdapterImpl;

import de.dailab.masp.models.MegaModel.Component;
import de.dailab.masp.models.MegaModel.ModelResourceIdentifier;
import de.dailab.masp.models.MetaMetaModel.NotStartedException;
import de.dailab.masp.models.Properties.PropertiesPackage;
import de.dailab.masp.models.Properties.Property;

/**
 * <code>DummyVariableSynchronizationComponent</code> TODO add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since TODO
 */
public final class DummyVariableSynchronizationComponent extends Component {

    public static final transient String PROPERTY_KEY_VARIABLE = "Variable";

    public static final transient String PROPERTY_KEY_PROPERTY_QUERY = "PropertyQuery";

    private String variable;

    private String propertyQuery;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setModelResources(final Map<ModelResourceIdentifier, Object> modelResources) {
        // get context model
        // pass the context models to the visualizer
        final String query = this.propertyQuery;
        if(query == null) {
            throw new NotStartedException(this);
        }
        Object object;
        Object model;
        for(final Entry<ModelResourceIdentifier, Object> modelResourceEntry : modelResources.entrySet()) {
            model = modelResourceEntry.getValue();
            try {
                object = getModelElement(model, query);
            }
            catch(final RuntimeException e) {
                LOG.warn("Failed to execute property query '" + query + "' in model '" + modelResourceEntry.getKey() + "' due to error: " + e.getMessage(), e);
                continue;
            }
            if(!(object instanceof Property)) {
                LOG.warn("Property query '" + query + "' did not return a property in model '" + modelResourceEntry.getKey() + "' but: " + object);
                continue;
            }
            final Property<?> property = (Property<?>)object;
            property.eAdapters().add(this.valueAdapter);
            newValue(property.getValue());
            if(LOG.isDebugEnabled()) {
                LOG.debug("Registered adapter at property referenced by query '" + query + "'.");
            }
        }
    }

    /**
     * <code>LOG</code> for logging.
     */
    public static final transient Log LOG = LogFactory.getLog(DummyVariableSynchronizationComponent.class);

    private final class PropertyValueAdapter extends SingletonAdapterImpl {

        /**
         * <code>PropertyValueAdapter</code> constructor.
         */
        PropertyValueAdapter() {
            // increased visibility
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void notifyChanged(final Notification msg) {
            if(msg.getFeature() == PropertiesPackage.Literals.PROPERTY__VALUE) {
                newValue(msg.getNewValue());
            }
        }

    }

    private final PropertyValueAdapter valueAdapter = new PropertyValueAdapter();

    void newValue(final Object value) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("Storing new value '" + value + "' in dummy registry");
        }
        DummyValueRegistry.set(this.variable, String.valueOf(value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void startHook(final Map<String, String> configuration) {
        this.variable = configuration.get(PROPERTY_KEY_VARIABLE);
        if(this.variable == null) {
            LOG.error("No " + PROPERTY_KEY_VARIABLE + " property defined in configuration " + configuration);
            throw new IllegalArgumentException("No " + PROPERTY_KEY_VARIABLE + " property defined in configuration " + configuration);
        }
        this.propertyQuery = configuration.get(PROPERTY_KEY_PROPERTY_QUERY);
        if(this.propertyQuery == null) {
            LOG.error("No " + PROPERTY_KEY_PROPERTY_QUERY + " property defined in configuration " + configuration);
            throw new IllegalArgumentException("No " + PROPERTY_KEY_PROPERTY_QUERY + " property defined in configuration " + configuration);
        }
    }

    private static Object getModelElement(final Object model, final String elementQuery) {
        // create jxpath context with the model
        final JXPathContext context = JXPathContext.newContext(model);
        try {
            // execute query
            return context.selectSingleNode(elementQuery);
        }
        catch(final JXPathInvalidSyntaxException e) {
            throw new IllegalArgumentException("query '" + elementQuery + "' is not a valid XPath query, error: " + e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void stopHook() {
        this.variable = null;
        this.valueAdapter.dispose();
    }
}