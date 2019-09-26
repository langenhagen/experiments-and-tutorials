/**
 * 
 */
package org.sercho.masp.models.Context.modelapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.sercho.masp.models.MASPDirectory;
import org.sercho.masp.models.XMIUtility;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Environment;

import de.dailab.masp.models.MetaMetaModel.MetaModel;
import de.dailab.masp.models.MetaMetaModel.ecore.EcoreMetaModelConverter;
import de.dailab.masp.models.Properties.Actor;
import de.dailab.masp.models.Properties.ConfigurationProperty;
import de.dailab.masp.models.Properties.PropertiesFactory;
import de.dailab.masp.models.Properties.PropertiesPackage;
import de.dailab.masp.models.Properties.Property;
import de.dailab.masp.models.Properties.Sensor;
import de.dailab.masp.models.Properties.ServiceContainer;

/**
 * <code>JSONModelAPIServiceConverter</code> helps creating context models with
 * {@link JSONModelAPISensor}s and {@link JSONModelAPIActor}s, by creating
 * copies of existing models and redirecting its sensors and actors to a the
 * Model-API.
 * 
 * @author Grzegorz Lehmann
 * @since 1.3.26
 */
public final class JSONModelAPIServiceConverter {

    /**
     * <code>LOG</code> for logging.
     */
    public static final transient Log LOG = LogFactory.getLog(JSONModelAPIServiceConverter.class);

    private JSONModelAPIServiceConverter() {
        // hidden constructor
    }

    private static final Actor createActor(final String modelAPIURL, final String modelID, final String propertyQuery) {
        // create service
        final Actor actor = PropertiesFactory.eINSTANCE.createActor();
        actor.setActorWrapperClassName(JSONModelAPIActor.class.getName());
        // construct id
        final StringBuffer id;
        if(modelID == null) {
            id = new StringBuffer(modelAPIURL).append(':').append(JSONModelAPIActor.PROPERTY_VALUE_DEFAULT_MODEL_ID).append(':').append(propertyQuery);
        } else {
            id = new StringBuffer(modelAPIURL).append(':').append(modelID).append(':').append(propertyQuery);
        }
        actor.setId(id.toString());

        // add configuration properties
        // model API URL
        ConfigurationProperty property = PropertiesFactory.eINSTANCE.createConfigurationProperty();
        property.setKey(JSONModelAPIActor.PROPERTY_KEY_MODEL_API_URL);
        property.setValue(modelAPIURL);
        actor.getConfiguration().add(property);

        // property query
        property = PropertiesFactory.eINSTANCE.createConfigurationProperty();
        property.setKey(JSONModelAPIActor.PROPERTY_KEY_PROPERTY_QUERY);
        property.setValue(propertyQuery);
        actor.getConfiguration().add(property);

        // model ID
        if(modelID != null) {
            property = PropertiesFactory.eINSTANCE.createConfigurationProperty();
            property.setKey(JSONModelAPIActor.PROPERTY_KEY_MODEL_ID);
            property.setValue(modelID);
            actor.getConfiguration().add(property);
        }
        return actor;
    }

    private static final Sensor createSensor(final String modelAPIURL, final String modelID, final String propertyQuery) {
        // create service
        final Sensor sensor = PropertiesFactory.eINSTANCE.createSensor();
        sensor.setSensorWrapperClassName(JSONModelAPISensor.class.getName());
        // construct id
        final StringBuffer id;
        if(modelID == null) {
            id = new StringBuffer(modelAPIURL).append(':').append(JSONModelAPISensor.PROPERTY_VALUE_DEFAULT_MODEL_ID).append(':').append(propertyQuery);
        } else {
            id = new StringBuffer(modelAPIURL).append(':').append(modelID).append(':').append(propertyQuery);
        }
        sensor.setId(id.toString());

        // add configuration properties
        // model API URL
        ConfigurationProperty property = PropertiesFactory.eINSTANCE.createConfigurationProperty();
        property.setKey(JSONModelAPISensor.PROPERTY_KEY_MODEL_API_URL);
        property.setValue(modelAPIURL);
        sensor.getConfiguration().add(property);

        // property query
        property = PropertiesFactory.eINSTANCE.createConfigurationProperty();
        property.setKey(JSONModelAPISensor.PROPERTY_KEY_PROPERTY_QUERY);
        property.setValue(propertyQuery);
        sensor.getConfiguration().add(property);

        // model ID
        if(modelID != null) {
            property = PropertiesFactory.eINSTANCE.createConfigurationProperty();
            property.setKey(JSONModelAPISensor.PROPERTY_KEY_MODEL_ID);
            property.setValue(modelID);
            sensor.getConfiguration().add(property);
        }
        return sensor;
    }

    static final String getPropertyQuery(final Property<?> property) {
        if(property == null) {
            return null;
        }
        final EStructuralFeature containingFeature = property.eContainingFeature();
        if(containingFeature == null) {
            LOG.warn("Cannot create property query, because no containing feature is set for property: " + property);
            return null;
        }
        final EObject container = property.eContainer();
        if(container == null) {
            LOG.warn("Cannot create property query, because property has no container: " + property);
            return null;
        }
        final String containerQuery = getElementQuery(container);
        if(containerQuery == null) {
            LOG.warn("Cannot create property query, because failed to create query for property's container. Property: " + property);
            return null;
        }
        final StringBuffer query = new StringBuffer(containerQuery);
        query.append('/');
        query.append(containingFeature.getName());
        return query.toString();
    }

    /**
     * <code>IDENTIFYING_ATTRIBUTES</code> holds attributes that identify
     * elements of a type. This is used for constructing queries to model
     * elements.<br/>
     * Please extend this map if the converter should deal with properties not
     * contained in currently identifiable elements.
     */
    private static final transient Map<EClass, EAttribute> IDENTIFYING_ATTRIBUTES;
    static {
        final Map<EClass, EAttribute> identifyingAttributes = new HashMap<EClass, EAttribute>();
        identifyingAttributes.put(ContextPackage.Literals.ENVIRONMENT_ELEMENT, ContextPackage.Literals.ENVIRONMENT_ELEMENT__ID);
        identifyingAttributes.put(ContextPackage.Literals.METER_READING, ContextPackage.Literals.METER_READING__IDENTIFIER);

        // this map should not be modified! Be paranoid about it!
        IDENTIFYING_ATTRIBUTES = Collections.unmodifiableMap(identifyingAttributes);
    }

    private static final EAttribute getIdentifyingAttribute(final EClass eClass) {
        if(eClass == null) {
            return null;
        }
        // check if eClass or its super classes have an identifying attribute
        for(final Entry<EClass, EAttribute> entry : IDENTIFYING_ATTRIBUTES.entrySet()) {
            if(entry.getKey().isSuperTypeOf(eClass)) {
                return entry.getValue();
            }
        }
        // no identifying attribute found
        return null;
    }

    /**
     * <code>getEnvironmentElementQuery</code> constructs a query to an
     * {@link EObject} by traversing its containers up to the root
     * {@link Environment} node. Only elements with identifying attributes
     * (defined in {@link #IDENTIFYING_ATTRIBUTES}) are allowed on the path to
     * the root node.
     * 
     * @param element
     *            element of which to construct the query
     * @return String - calculated query or <code>null</code> if query
     *         calculation failed
     */
    private static final String getElementQuery(final EObject element) {
        EObject currentElement = element;
        String currentElementID;

        final StringBuffer query = new StringBuffer();
        StringBuffer elementQuery;
        EObject container;
        EStructuralFeature containingFeature;
        EAttribute identifyingAttribute;
        while(currentElement != null) {
            containingFeature = currentElement.eContainingFeature();
            if(containingFeature == null) {
                // root reached?
                LOG.warn("Cannot create query, because no container is set in element: " + currentElement);
                return null;
            }
            identifyingAttribute = getIdentifyingAttribute(currentElement.eClass());
            if(identifyingAttribute == null) {
                // unable to identify the element
                LOG.warn("Cannot create query, because the converter does not know any identifying attribute for the element: " + currentElement);
                return null;
            }
            currentElementID = (String)currentElement.eGet(identifyingAttribute);
            if(currentElementID == null) {
                LOG.warn("Cannot create query, because the identifying attribute '" + identifyingAttribute.getName() + "' is null in element: " + currentElement);
                return null;
            }
            elementQuery = new StringBuffer("/");
            elementQuery.append(containingFeature.getName());
            elementQuery.append('[');
            elementQuery.append(identifyingAttribute.getName());
            elementQuery.append("='");
            elementQuery.append(currentElementID);
            elementQuery.append("']");

            query.insert(0, elementQuery);

            container = currentElement.eContainer();
            if(container instanceof Environment) {
                // we are at the root, end loop
                return query.toString();
            }
            currentElement = container;
        }
        // if we get here, Environment root node has not been reached in loop
        LOG.warn("Cannot create query, because the root of the element tree was reached and it was not Environment, but: " + currentElement);
        return null;
    }

    /**
     * <code>convertAllProperties</code> completely converts a context model so
     * it does not use anz sensors or actors directly, but through a given
     * Model-API. traverses the model and for each found property sets a new
     * {@link JSONModelAPIActor} actor and a new {@link JSONModelAPISensor}
     * sensor, both pointing to a model API specified by the parameters. Please
     * note that this method also removes all existing service containers with
     * their sensors and actors from the model. For each property a new service
     * container is created with the Model-API sensors and actors.
     * 
     * @param environment
     *            environment to convert, <code>null</code> disallowed
     * @param modelAPIURL
     *            URL of the Model-API, <code>null</code> disallowed
     * @param modelID
     *            ID of the model to query for property, if <code>null</code>
     *            default model ID of the JSON Model-API sensors and actors is
     *            used
     * @throws IllegalArgumentException
     *             if <code>environment</code> or <code>modelAPIURL</code> is
     *             <code>null</code>
     */
    public static final void convertAllProperties(final Environment environment, final String modelAPIURL, final String modelID) {
        if(environment == null) {
            throw new IllegalArgumentException("environment argument in method convertAllProperties must not be null");
        }
        if(modelAPIURL == null) {
            throw new IllegalArgumentException("modelAPIURL argument in method convertAllProperties must not be null");
        }

        // clear all services first
        environment.getServiceContainers().clear();

        final TreeIterator<EObject> allContents = environment.eAllContents();
        EObject eObject;
        Property<?> property;
        Actor actor;
        Sensor sensor;
        String query;
        ServiceContainer container;
        while(allContents.hasNext()) {
            eObject = allContents.next();
            if(eObject instanceof Property) {
                property = (Property<?>)eObject;
                query = getPropertyQuery(property);
                if(query != null) {
                    // create and add container for property services
                    container = PropertiesFactory.eINSTANCE.createServiceContainer();
                    container.setId(query);
                    environment.getServiceContainers().add(container);
                    // create and set actor
                    actor = createActor(modelAPIURL, modelID, query);
                    container.getServices().add(actor);
                    property.setActor(actor);
                    // create and set sensor
                    sensor = createSensor(modelAPIURL, modelID, query);
                    container.getServices().add(sensor);
                    property.setSensor(sensor);
                } else {
                    throw new IllegalStateException("Conversion failed because failed to create query for property: " + eObject + ", with container: " + eObject.eContainer());
                }
            }
        }
    }

    /**
     * <code>Command</code> represents a command supported by this converter.
     * This enum simplifies the parsing and executing of the command in the
     * {@link JSONModelAPIServiceConverter#main(String[])}.
     * 
     * @author Grzegorz Lehmann
     * @since 1.3.26
     */
    private enum Command {
        convert {

            /**
             * {@inheritDoc}
             */
            @Override
            void execute(final Map<String, String> arguments) {
                final String inputPath;
                if(!arguments.containsKey(ARGUMENT_INPUT_FILE)) {
                    inputPath = MASPDirectory.getMASPDirectoryPath() + "ContextModel.xmi";
                } else {
                    inputPath = arguments.get(ARGUMENT_INPUT_FILE);
                }
                final String outputPath = arguments.get(ARGUMENT_OUTPUT_FILE);

                final Environment environment;
                try {
                    environment = (Environment)XMIUtility.convert(new FileInputStream(inputPath), ContextPackage.eINSTANCE).get(0);
                }
                catch(final IOException e) {
                    throw new IllegalArgumentException("Failed to access '" + inputPath + "' due to error: " + e.getMessage(), e);
                }
                convertAllProperties(environment, arguments.get(ARGUMENT_MODEL_API_URL), arguments.get(ARGUMENT_MODEL_ID));

                try {
                    XMIUtility.saveAsXMI(environment, new File(outputPath));
                }
                catch(final IOException e) {
                    throw new IllegalArgumentException("Failed to store context model in  '" + outputPath + "' due to error: " + e.getMessage(), e);
                }
            }

            /**
             * {@inheritDoc}
             */
            @Override
            Collection<String> getMandatoryArgumentNames() {
                return Arrays.asList(ARGUMENT_MODEL_API_URL, ARGUMENT_MODEL_ID, ARGUMENT_OUTPUT_FILE);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            Collection<String> getOptionalArgumentNames() {
                return Arrays.asList(ARGUMENT_INPUT_FILE);
            }
        },

        test {

            /**
             * {@inheritDoc}
             */
            @Override
            void execute(final Map<String, String> arguments) {

                final String inputPath;
                if(!arguments.containsKey(ARGUMENT_INPUT_FILE)) {
                    inputPath = MASPDirectory.getMASPDirectoryPath() + "ContextModel.xmi";
                } else {
                    inputPath = arguments.get(ARGUMENT_INPUT_FILE);
                }
                Environment environment;
                try {
                    environment = (Environment)XMIUtility.convert(new FileInputStream(inputPath), ContextPackage.eINSTANCE).get(0);
                }
                catch(final IOException e) {
                    throw new IllegalArgumentException("Failed to access '" + inputPath + "' due to error: " + e.getMessage(), e);
                }
                convertAllProperties(environment, arguments.get(ARGUMENT_MODEL_API_URL), arguments.get(ARGUMENT_MODEL_ID));

                final MetaModel metamodel = EcoreMetaModelConverter.INSTANCE.getCachedOrConvertMetaModel(ContextPackage.eINSTANCE);
                metamodel.getMetaType().getMetaModel().start(metamodel);
                metamodel.start(environment);

                LOG.info("Context model started");

                environment.eAdapters().add(new EContentAdapter() {

                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    public void notifyChanged(final Notification notification) {
                        super.notifyChanged(notification);
                        if(notification.getFeature() == PropertiesPackage.Literals.PROPERTY__VALUE) {
                            final Property<?> property = (Property<?>)notification.getNotifier();
                            LOG.info(getPropertyQuery(property) + ": " + notification.getOldValue() + " -> " + notification.getNewValue());
                        }
                    }
                });

                try {
                    Thread.sleep(Long.MAX_VALUE);
                }
                catch(final InterruptedException e) {
                    // end
                }
            }

            /**
             * {@inheritDoc}
             */
            @Override
            Collection<String> getMandatoryArgumentNames() {
                return Arrays.asList(ARGUMENT_MODEL_API_URL, ARGUMENT_MODEL_ID);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            Collection<String> getOptionalArgumentNames() {
                return Arrays.asList(ARGUMENT_INPUT_FILE);
            }
        };

        final void execute(final List<String> arguments) {
            final Map<String, String> argumentsMap = new HashMap<String, String>();
            String argument;
            // convert argument list into map
            for(int i = 0; i < arguments.size(); i++) {
                argument = arguments.get(i);
                if(argument.startsWith("-") && ((i + 1) < arguments.size())) {
                    argumentsMap.put(argument.substring(1), arguments.get(++i));
                }
            }
            // check if all mandatory arguments are provided
            for(final String mandatoryArgumentName : getMandatoryArgumentNames()) {
                if(!argumentsMap.containsKey(mandatoryArgumentName)) {
                    throw new IllegalArgumentException("'" + mandatoryArgumentName + "' argument is missing");
                }
            }
            execute(argumentsMap);
        }

        abstract void execute(Map<String, String> arguments);

        final void print() {
            final StringBuffer usage = new StringBuffer(name());
            for(final String mandatoryArgumentName : getMandatoryArgumentNames()) {
                usage.append(" -");
                usage.append(mandatoryArgumentName);
                usage.append(" <value>");
            }
            for(final String optionalArgumentName : getOptionalArgumentNames()) {
                usage.append(" [-");
                usage.append(optionalArgumentName);
                usage.append(" <value>]");
            }
            LOG.info(usage);
        }

        abstract Collection<String> getMandatoryArgumentNames();

        abstract Collection<String> getOptionalArgumentNames();
    }

    private static void printUsage() {
        LOG.info("Commands available in " + JSONModelAPIServiceConverter.class.getSimpleName() + ':');
        for(final Command command : Command.values()) {
            command.print();
        }
    }

    static final transient String ARGUMENT_INPUT_FILE = "input-file";

    static final transient String ARGUMENT_OUTPUT_FILE = "output-file";

    static final transient String ARGUMENT_MODEL_API_URL = "url";

    static final transient String ARGUMENT_MODEL_ID = "modelId";

    /**
     * <code>main</code> for executing the converter.
     * 
     * @param args
     *            program arguments
     */
    public static void main(final String[] args) {
        Logger.getRootLogger().addAppender(new ConsoleAppender(new SimpleLayout(), ConsoleAppender.SYSTEM_OUT));
        List<String> arguments = Arrays.asList(args);

        if(arguments.isEmpty()) {
            // print usage
            printUsage();
            return;
        }

        final String commandName = arguments.get(0);
        arguments = arguments.subList(1, arguments.size());

        final Command command;
        try {
            command = Command.valueOf(commandName);
        }
        catch(final IllegalArgumentException e) {
            LOG.error("Unknown command " + commandName);
            printUsage();
            return;
        }

        LOG.info("Executing command " + command + " with arguments " + arguments);
        try {
            command.execute(arguments);
            LOG.info("Successfully executed command " + command);
        }
        catch(final RuntimeException e) {
            LOG.error("Executing command " + command + " failed due to error: " + e.getMessage());
            printUsage();
        }
    }
}