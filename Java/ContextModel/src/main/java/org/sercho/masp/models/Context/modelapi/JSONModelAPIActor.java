/**
 * 
 */
package org.sercho.masp.models.Context.modelapi;

import java.net.MalformedURLException;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.sercho.masp.meta.api.ActionError;
import org.sercho.masp.meta.api.ActionRequest;
import org.sercho.masp.meta.api.ActionResponse;
import org.sercho.masp.meta.api.ValueParameter;
import org.sercho.masp.meta.api.json.JSONModelAPIClient;
import org.sercho.masp.models.Context.ConfigurationPropertyUtility;
import org.sercho.masp.models.Context.ContextPackage;

import de.dailab.masp.models.MetaMetaModel.MetaModel;
import de.dailab.masp.models.MetaMetaModel.NotStartedException;
import de.dailab.masp.models.MetaMetaModel.ecore.EcoreMetaModelConverter;
import de.dailab.masp.models.Properties.AbstractActorWrapper;
import de.dailab.masp.models.Properties.Actor;
import de.dailab.masp.models.Properties.ActorServiceCallException;
import de.dailab.masp.models.Properties.ConfigurationProperty;
import de.dailab.masp.models.Properties.PropertiesFactory;

/**
 * <code>JSONModelAPIActor</code> TODO add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 1.3.7
 */
public final class JSONModelAPIActor extends AbstractActorWrapper {

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8151751982261570813L;

    public static final transient String PROPERTY_KEY_MODEL_API_URL = "ModelAPIURL";

    public static final transient String PROPERTY_KEY_PROPERTY_QUERY = "PropertyQuery";

    public static final transient String PROPERTY_KEY_MODEL_ID = "ModelID";

    public static final transient String PROPERTY_VALUE_DEFAULT_MODEL_ID = "Context";

    private static final transient String ACTOR_QUERY_POSTFIX = "/actor";

    private static final transient String ACTION_NAME_SET = "set";

    private volatile String modelAPIURL;

    private volatile String propertyActorQuery;

    private volatile String modelID;

    private volatile JSONModelAPIClient client;

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(final String newValue) throws ActorServiceCallException {
        final JSONModelAPIClient apiClient = this.client;
        if(apiClient == null) {
            throw new NotStartedException(this);
        }
        // construct ActionRequest with PropertyQuery + /actor
        final ActionRequest set = new ActionRequest("set " + System.currentTimeMillis(), this.modelID, this.propertyActorQuery, ACTION_NAME_SET, new ValueParameter(newValue));
        // execute request
        final ActionResponse response = apiClient.execute(set);
        System.out.println(response);
        if(response instanceof ActionError) {
            throw new ActorServiceCallException(((ActionError)response).error);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void startHook(final Map<String, String> configuration) {
        this.modelAPIURL = ConfigurationPropertyUtility.getPropertyValue(configuration, PROPERTY_KEY_MODEL_API_URL);
        this.propertyActorQuery = ConfigurationPropertyUtility.getPropertyValue(configuration, PROPERTY_KEY_PROPERTY_QUERY) + ACTOR_QUERY_POSTFIX;
        this.modelID = configuration.get(PROPERTY_KEY_MODEL_ID);
        if(this.modelID == null) {
            this.modelID = PROPERTY_VALUE_DEFAULT_MODEL_ID;
        }
        try {
            this.client = new JSONModelAPIClient(this.modelAPIURL);
        }
        catch(final MalformedURLException e) {
            throw new IllegalArgumentException(PROPERTY_KEY_MODEL_API_URL + " property is not a valid URL");
        }
        setAvailable(Boolean.TRUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void stopHook() {
        // nothing to do here
        if(this.client == null) {
            // nothing to do
            return;
        }
        // reset all field
        this.modelAPIURL = null;
        this.modelID = null;
        this.propertyActorQuery = null;
        this.client = null;
    }

    public static void main(final String[] args) throws ActorServiceCallException, InterruptedException {
        BasicConfigurator.configure();

        final EList<ConfigurationProperty> configuration = new BasicEList<ConfigurationProperty>();
        ConfigurationProperty property = PropertiesFactory.eINSTANCE.createConfigurationProperty();
        property.setKey(PROPERTY_KEY_MODEL_API_URL);
        // property.setValue("http://192.168.1.10/MASP/ModelAPI");
        property.setValue("http://localhost:8081");
        configuration.add(property);
        property = PropertiesFactory.eINSTANCE.createConfigurationProperty();
        property.setKey(PROPERTY_KEY_PROPERTY_QUERY);
        property.setValue("devices[id='CEILING LIGHT']/on");
        configuration.add(property);

        final Actor a = PropertiesFactory.eINSTANCE.createActor();
        a.getConfiguration().addAll(configuration);
        a.setActorWrapperClassName(JSONModelAPIActor.class.getName());

        final MetaModel metamodel = EcoreMetaModelConverter.INSTANCE.getCachedOrConvertMetaModel(ContextPackage.eINSTANCE);
        metamodel.getMetaType().getMetaModel().start(metamodel);
        metamodel.start(a);

        System.out.println("Starting");
        while(true) {

            a.set("true");
            Thread.sleep(2000);
            a.set("false");
            Thread.sleep(2000);
            System.out.println("Loop");
        }

        //
        // final JSONModelAPIActor actor = new JSONModelAPIActor();
        // actor.start(configuration, new ModelCallback() {
        //
        // @Override
        // public Set<String> getExecutableElementNames() {
        // final Set<String> executableElementNames = new HashSet<String>();
        // executableElementNames.add("set");
        // executableElementNames.add("setNewAvailable");
        // return executableElementNames;
        // }
        //
        // @Override
        // public void execute(final String arg0, final Object... arg1) throws
        // InvocationTargetException {
        // System.out.println(arg0 + ": " + arg1[0]);
        // }
        // });
        // System.out.println("Started");
        // actor.set("true");
        // System.out.println("Setting performed.");
        // try {
        // Thread.sleep(Long.MAX_VALUE);
        // }
        // catch(final InterruptedException e) {
        // e.printStackTrace();
        // }
    }
}