/**
 * 
 */
package org.sercho.masp.models.Context.modelapi;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.sercho.masp.meta.api.ModelEventResponse;
import org.sercho.masp.meta.api.ModelObserver;
import org.sercho.masp.meta.api.QueryError;
import org.sercho.masp.meta.api.QueryRequest;
import org.sercho.masp.meta.api.QueryResponse;
import org.sercho.masp.meta.api.QuerySuccess;
import org.sercho.masp.meta.api.SubscribeError;
import org.sercho.masp.meta.api.SubscribeRequest;
import org.sercho.masp.meta.api.SubscribeResponse;
import org.sercho.masp.meta.api.SubscribeSuccess;
import org.sercho.masp.meta.api.UnsubscribeError;
import org.sercho.masp.meta.api.UnsubscribeRequest;
import org.sercho.masp.meta.api.UnsubscribeResponse;
import org.sercho.masp.meta.api.json.JSONModelAPIClient;
import org.sercho.masp.models.Context.ConfigurationPropertyUtility;

import de.dailab.masp.models.MetaMetaModel.ModelCallback;
import de.dailab.masp.models.Properties.AbstractSensorWrapper;
import de.dailab.masp.models.Properties.ConfigurationProperty;
import de.dailab.masp.models.Properties.PropertiesFactory;
import de.dailab.masp.models.Properties.PropertiesPackage;

/**
 * <code>ModelAPIPollingSensor</code> TODO add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 1.3.7
 */
public final class JSONModelAPISensor extends AbstractSensorWrapper {

    /**
     * <code>VALUE</code>
     */
    private static final String VALUE = "value";

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8151751982261570813L;

    /**
     * <code>LOG</code> for logging.
     */
    public static final transient Log LOG = LogFactory.getLog(JSONModelAPISensor.class);

    public static final transient String PROPERTY_KEY_MODEL_API_URL = "ModelAPIURL";

    public static final transient String PROPERTY_KEY_MODEL_ID = "ModelID";

    public static final transient String PROPERTY_VALUE_DEFAULT_MODEL_ID = "Context";

    public static final transient String PROPERTY_KEY_PROPERTY_QUERY = "PropertyQuery";

    private volatile String modelAPIURL;

    private volatile String propertyQuery;

    private volatile String modelID;

    private volatile String subscribeRequestID;

    private volatile JSONModelAPIClient client;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void startHook(final Map<String, String> configuration) {
        this.modelAPIURL = ConfigurationPropertyUtility.getPropertyValue(configuration, PROPERTY_KEY_MODEL_API_URL);
        this.propertyQuery = ConfigurationPropertyUtility.getPropertyValue(configuration, PROPERTY_KEY_PROPERTY_QUERY);
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
        this.subscribeRequestID = "Subscribe " + System.currentTimeMillis();
        final SubscribeRequest subscribe = new SubscribeRequest(this.subscribeRequestID, this.modelID, this.propertyQuery, new ModelObserver() {

            @Override
            public void unsubscribed() {
                setAvailable(Boolean.FALSE);
            }

            @Override
            public void subscribed() {
                setAvailable(Boolean.TRUE);
            }

            @Override
            public void notify(final ModelEventResponse event) {
                if(event == null) {
                    LOG.error("Received null ModelEventResponse, ignoring it");
                    return;
                }
                final Object newValue = event.newValue;
                try {
                    final JSONArray array;
                    if(newValue instanceof String) {
                        // parse array from string
                        array = new JSONArray(new JSONTokener((String)event.newValue));
                    } else if(newValue instanceof JSONArray) {
                        // just use the value as array
                        array = (JSONArray)newValue;
                    } else if(newValue instanceof Object[]) {
                        // create JSON array from object array
                        array = new JSONArray(newValue);
                    } else {
                        LOG.error("Cannot process newValue of ModelEventResponse, because it has an unexpected type: " + newValue);
                        return;
                    }
                    if(array.length() < 1) {
                        LOG.error("Got empty newValue array");
                        return;
                    }
                    if(array.length() > 1) {
                        // warn BUT proceed
                        LOG.warn("newValue of ModelEventResponse contains more than one element. Using first element of JSON array: " + array);
                        // proceed after warning!
                    }
                    final Object property = array.get(0);
                    if(property instanceof JSONObject) {
                        final Object value = ((JSONObject)property).get(VALUE);
                        if(value == null) {
                            LOG.error(VALUE + " is null in newValue property JSON object: " + property);
                            return;
                        }
                        newValue(String.valueOf(value));
                        if(LOG.isDebugEnabled()) {
                            LOG.debug("Processed and reported new property value " + value);
                        }
                    } else {
                        LOG.error("Failed to process new ModelEventResponse because a property JSONObject was expected in newValue array, but got: " + property);
                    }
                }
                catch(final JSONException e) {
                    LOG.error("JSON error caused a failure of handling the ModelEventReponse with newValue " + event.newValue, e);
                }
                catch(final Exception e) {
                    LOG.warn("Unexpectedly failed to handle ModelEventReponse with newValue " + event.newValue, e);
                }
            }
        });
        final SubscribeResponse response = this.client.execute(subscribe);
        if(response instanceof SubscribeError) {
            LOG.warn("Failed to subscribe for " + this.propertyQuery + " in Model-API " + this.modelAPIURL + ", error: " + ((SubscribeError)response).error);
        } else if(response instanceof SubscribeSuccess) {
            if(LOG.isDebugEnabled()) {
                LOG.debug(new StringBuffer("Subscribed for ").append(this.propertyQuery).append(" in Model-API ").append(this.modelAPIURL));
            }
        }
        // query initial value
        final QueryRequest queryRequest = new QueryRequest(this.propertyQuery + System.currentTimeMillis(), this.modelID, this.propertyQuery + "/" + PropertiesPackage.Literals.PROPERTY__VALUE.getName());
        final QueryResponse queryResponse = this.client.execute(queryRequest);
        if(queryResponse instanceof QueryError) {
            LOG.warn("Failed to query initial value for " + this.propertyQuery + " in Model-API " + this.modelAPIURL + ", error: " + ((QueryError)queryResponse).error);
        } else if(queryResponse instanceof QuerySuccess) {
            final Object[] results = ((QuerySuccess)queryResponse).results;
            if(results.length == 0) {
                LOG.warn("Initial value query returned empty results array");
            } else {
                final Object result = results[0];
                if(result != null) {
                    newValue(result.toString());
                } else {
                    LOG.warn("Got null result in initial query");
                }
                if(LOG.isDebugEnabled()) {
                    LOG.debug(new StringBuffer("Initial query success for ").append(this.propertyQuery).append(" in Model-API ").append(this.modelAPIURL).append(", result: ").append(result));
                }
                setAvailable(Boolean.TRUE);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void stopHook() {
        final JSONModelAPIClient apiClient = this.client;
        if(apiClient == null) {
            // nothing to do
            return;
        }
        final UnsubscribeRequest unsubscribe = new UnsubscribeRequest("Unsubscribe " + System.currentTimeMillis(), this.modelID, this.subscribeRequestID);
        final UnsubscribeResponse response = apiClient.execute(unsubscribe);
        if(response instanceof UnsubscribeError) {
            LOG.warn("Failed to unsubscribe, error: " + ((UnsubscribeError)response).error);
        }
        // reset all field
        this.modelAPIURL = null;
        this.propertyQuery = null;
        this.modelID = null;
        this.subscribeRequestID = null;
        this.client = null;
    }

    public static void main(final String[] args) throws MalformedURLException {
        BasicConfigurator.configure();
        /*
         * final JSONModelAPIClient client = new
         * JSONModelAPIClient("http://192.168.1.10/MASP/ModelAPI"); final
         * QueryResponse response = client.execute(new QueryRequest("x",
         * "Context", "devices/subDevice[id='Hood Fan']/on"));
         * System.out.println(response);
         */

        final EList<ConfigurationProperty> configuration = new BasicEList<ConfigurationProperty>();
        ConfigurationProperty property = PropertiesFactory.eINSTANCE.createConfigurationProperty();
        property.setKey(PROPERTY_KEY_MODEL_API_URL);
        property.setValue("http://192.168.1.10/MASP/ModelAPI");
        configuration.add(property);
        property = PropertiesFactory.eINSTANCE.createConfigurationProperty();
        property.setKey(PROPERTY_KEY_PROPERTY_QUERY);
        property.setValue("devices[id='WALLIGHT LIVING ROOM']/on");
        configuration.add(property);

        final JSONModelAPISensor sensor = new JSONModelAPISensor();
        sensor.start(configuration, new ModelCallback() {

            @Override
            public Set<String> getExecutableElementNames() {
                final Set<String> executableElementNames = new HashSet<String>();
                executableElementNames.add("setNewValue");
                executableElementNames.add("setNewAvailable");
                return executableElementNames;
            }

            @Override
            public void execute(final String arg0, final Object... arg1) throws InvocationTargetException {
                System.out.println("[First] " + arg0 + ": " + arg1[0]);
            }
        });
        second();
        System.out.println("Started");
        try {
            Thread.sleep(Long.MAX_VALUE);
        }
        catch(final InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void second() {
        final EList<ConfigurationProperty> configuration = new BasicEList<ConfigurationProperty>();
        ConfigurationProperty property = PropertiesFactory.eINSTANCE.createConfigurationProperty();
        property.setKey(PROPERTY_KEY_MODEL_API_URL);
        property.setValue("http://192.168.1.10/MASP/ModelAPI");
        configuration.add(property);
        property = PropertiesFactory.eINSTANCE.createConfigurationProperty();
        property.setKey(PROPERTY_KEY_PROPERTY_QUERY);
        property.setValue("devices/subDevice[id='Hood Fan']/on");
        configuration.add(property);

        final JSONModelAPISensor sensor = new JSONModelAPISensor();
        sensor.start(configuration, new ModelCallback() {

            @Override
            public Set<String> getExecutableElementNames() {
                final Set<String> executableElementNames = new HashSet<String>();
                executableElementNames.add("setNewValue");
                executableElementNames.add("setNewAvailable");
                return executableElementNames;
            }

            @Override
            public void execute(final String arg0, final Object... arg1) throws InvocationTargetException {
                System.out.println("[Second] " + arg0 + ": " + arg1[0]);
            }
        });
    }
}