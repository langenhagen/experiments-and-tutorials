/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.impl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sercho.masp.models.Context.Activity;
import org.sercho.masp.models.Context.Assistant;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.ContextProvider;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.Door;
import org.sercho.masp.models.Context.ElementWithPosition;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.HomeOSAssistant;
import org.sercho.masp.models.Context.LocalisationProviderProxy;
import org.sercho.masp.models.Context.LocalizationTag;
import org.sercho.masp.models.Context.Meter;
import org.sercho.masp.models.Context.Outdoors;
import org.sercho.masp.models.Context.Place;
import org.sercho.masp.models.Context.PlaceInfo;
import org.sercho.masp.models.Context.Room;
import org.sercho.masp.models.Context.RoomType;
import org.sercho.masp.models.Context.TemperatureSensor;
import org.sercho.masp.models.Context.Tool;
import org.sercho.masp.models.Context.User;
import org.sercho.masp.models.Context.Window;
import org.sercho.masp.models.Context.dummy.DummySensorWrapper;
import org.sercho.masp.models.Context.dummy.DummyServiceConstants;
import org.sercho.masp.models.Context.util.EnvironmentUtility;

import de.dailab.masp.models.MetaMetaModel.MetaModel;
import de.dailab.masp.models.MetaMetaModel.ecore.EcoreMetaModelConverter;
import de.dailab.masp.models.Properties.DoubleProperty;
import de.dailab.masp.models.Properties.PropertiesFactory;
import de.dailab.masp.models.Properties.Sensor;
import de.dailab.masp.models.Properties.Service;
import de.dailab.masp.models.Properties.ServiceContainer;
import de.dailab.masp.models.Properties.ServiceDiscovererProxy;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Environment</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.EnvironmentImpl#getPlaces <em>
 * Places</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.EnvironmentImpl#getProviders
 * <em>Providers</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.EnvironmentImpl#getDevices
 * <em>Devices</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.EnvironmentImpl#getUsers <em>
 * Users</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.EnvironmentImpl#getAssistants
 * <em>Assistants</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.EnvironmentImpl#getInitialAssistant
 * <em>Initial Assistant</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.EnvironmentImpl#getServiceContainers
 * <em>Service Containers</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.EnvironmentImpl#getMeters <em>
 * Meters</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.EnvironmentImpl#getDiscoverers
 * <em>Discoverers</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.EnvironmentImpl#getActivities
 * <em>Activities</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.EnvironmentImpl#getTools <em>
 * Tools</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.EnvironmentImpl#getName <em>
 * Name</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.EnvironmentImpl#getDescription
 * <em>Description</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class EnvironmentImpl extends EObjectImpl implements Environment {

    /**
     * The cached value of the '{@link #getPlaces() <em>Places</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPlaces()
     * @generated
     * @ordered
     */
    protected EList<Place> places;

    /**
     * <code>LOG</code>
     * 
     * @generated NOT
     */
    private static final transient Log LOG = LogFactory.getLog(EnvironmentImpl.class);

    /**
     * The cached value of the '{@link #getProviders() <em>Providers</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getProviders()
     * @generated
     * @ordered
     */
    protected EList<ContextProvider> providers;

    /**
     * The cached value of the '{@link #getDevices() <em>Devices</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDevices()
     * @generated
     * @ordered
     */
    protected EList<Device> devices;

    /**
     * The cached value of the '{@link #getUsers() <em>Users</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getUsers()
     * @generated
     * @ordered
     */
    protected EList<User> users;

    /**
     * The cached value of the '{@link #getAssistants() <em>Assistants</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getAssistants()
     * @generated
     * @ordered
     */
    protected EList<Assistant> assistants;

    /**
     * The cached value of the '{@link #getInitialAssistant()
     * <em>Initial Assistant</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getInitialAssistant()
     * @generated
     * @ordered
     */
    protected HomeOSAssistant initialAssistant;

    /**
     * The cached value of the '{@link #getServiceContainers()
     * <em>Service Containers</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getServiceContainers()
     * @generated
     * @ordered
     */
    protected EList<ServiceContainer> serviceContainers;

    /**
     * The cached value of the '{@link #getMeters() <em>Meters</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMeters()
     * @generated
     * @ordered
     */
    protected EList<Meter> meters;

    /**
     * The cached value of the '{@link #getDiscoverers() <em>Discoverers</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDiscoverers()
     * @generated
     * @ordered
     */
    protected EList<ServiceDiscovererProxy> discoverers;

    /**
     * The cached value of the '{@link #getActivities() <em>Activities</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getActivities()
     * @generated
     * @ordered
     */
    protected EList<Activity> activities;

    /**
     * The cached value of the '{@link #getTools() <em>Tools</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTools()
     * @generated
     * @ordered
     */
    protected EList<Tool> tools;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getDescription() <em>Description</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
     * <code>EnvironmentImpl</code> constructor.
     * 
     * @generated
     */
    protected EnvironmentImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.ENVIRONMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Place> getPlaces() {
        if(places == null) {
            places = new EObjectContainmentWithInverseEList<Place>(Place.class, this, ContextPackage.ENVIRONMENT__PLACES, ContextPackage.PLACE__ENVIRONMENT);
        }
        return places;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ContextProvider> getProviders() {
        if(providers == null) {
            providers = new EObjectContainmentWithInverseEList<ContextProvider>(ContextProvider.class, this, ContextPackage.ENVIRONMENT__PROVIDERS, ContextPackage.CONTEXT_PROVIDER__ENVIRONMENT);
        }
        return providers;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Device> getDevices() {
        if(devices == null) {
            devices = new EObjectContainmentWithInverseEList<Device>(Device.class, this, ContextPackage.ENVIRONMENT__DEVICES, ContextPackage.DEVICE__ENVIRONMENT);
        }
        return devices;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<User> getUsers() {
        if(users == null) {
            users = new EObjectContainmentWithInverseEList<User>(User.class, this, ContextPackage.ENVIRONMENT__USERS, ContextPackage.USER__ENVIRONMENT);
        }
        return users;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Assistant> getAssistants() {
        if(assistants == null) {
            assistants = new EObjectContainmentEList<Assistant>(Assistant.class, this, ContextPackage.ENVIRONMENT__ASSISTANTS);
        }
        return assistants;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public HomeOSAssistant getInitialAssistant() {
        if(initialAssistant != null && initialAssistant.eIsProxy()) {
            InternalEObject oldInitialAssistant = (InternalEObject)initialAssistant;
            initialAssistant = (HomeOSAssistant)eResolveProxy(oldInitialAssistant);
            if(initialAssistant != oldInitialAssistant) {
                if(eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ContextPackage.ENVIRONMENT__INITIAL_ASSISTANT, oldInitialAssistant, initialAssistant));
            }
        }
        return initialAssistant;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public HomeOSAssistant basicGetInitialAssistant() {
        return initialAssistant;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setInitialAssistant(HomeOSAssistant newInitialAssistant) {
        HomeOSAssistant oldInitialAssistant = initialAssistant;
        initialAssistant = newInitialAssistant;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.ENVIRONMENT__INITIAL_ASSISTANT, oldInitialAssistant, initialAssistant));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ServiceContainer> getServiceContainers() {
        if(serviceContainers == null) {
            serviceContainers = new EObjectContainmentEList<ServiceContainer>(ServiceContainer.class, this, ContextPackage.ENVIRONMENT__SERVICE_CONTAINERS);
        }
        return serviceContainers;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Meter> getMeters() {
        if(meters == null) {
            meters = new EObjectContainmentEList<Meter>(Meter.class, this, ContextPackage.ENVIRONMENT__METERS);
        }
        return meters;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<ServiceDiscovererProxy> getDiscoverers() {
        if(discoverers == null) {
            discoverers = new EObjectContainmentEList<ServiceDiscovererProxy>(ServiceDiscovererProxy.class, this, ContextPackage.ENVIRONMENT__DISCOVERERS);
        }
        return discoverers;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Activity> getActivities() {
        if(activities == null) {
            activities = new EObjectContainmentEList<Activity>(Activity.class, this, ContextPackage.ENVIRONMENT__ACTIVITIES);
        }
        return activities;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Tool> getTools() {
        if(tools == null) {
            tools = new EObjectContainmentEList<Tool>(Tool.class, this, ContextPackage.ENVIRONMENT__TOOLS);
        }
        return tools;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.ENVIRONMENT__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.ENVIRONMENT__DESCRIPTION, oldDescription, description));
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public void addUser(final String id, final String name, final String surname, final String birthdate) {
        if(id == null) {
            throw new IllegalArgumentException("id argument must not be null in method addUser");
        } else if(name == null) {
            throw new IllegalArgumentException("name argument must not be null in method addUser");
        } else if(surname == null) {
            throw new IllegalArgumentException("surname argument must not be null in method addUser");
        } else if(birthdate == null) {
            throw new IllegalArgumentException("birthdate argument must not be null in method addUser");
        }

        synchronized(getUsers()) {
            for(final User user : getUsers()) {
                if(id.equals(user.getId())) {
                    throw new IllegalArgumentException("id " + id + " for User already exists!");
                }
            }
        }

        final User user = ContextFactory.eINSTANCE.createUser();
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setNewBirthDate(birthdate);
        synchronized(getUsers()) {
            getUsers().add(user);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public void removeUser(final User user) {
        if(user == null) {
            throw new IllegalArgumentException("user argument must not be null in method removeUser");
        }
        synchronized(getUsers()) {
            getUsers().remove(user);
        }
        final Place placeOfUser = user.getPlace();
        if(placeOfUser != null) {
            synchronized(placeOfUser.getElements()) {
                placeOfUser.getElements().remove(user);
            }
        }
    }

    /**
     * <!-- begin-user-doc --> The id attribute is only necessary if the Service
     * Container has no id, also it is applied only if it has no id. <!--
     * end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public void addServiceContainer(final ServiceContainer container, final String id) {
        if(container == null) {
            throw new IllegalArgumentException("container argument must not be null in method addServiceContainer");
        } else if((container.getId() == null) && (id == null)) {
            throw new IllegalArgumentException("newServiceContainerId argument must not be null in method addServiceContainer if id of container is null");
        } else if((container.getId() == null) && !EnvironmentUtility.checkIdForService(this, id)) {
            throw new IllegalArgumentException("id '" + id + "' for ServiceContainer already used at Environment!");
        }

        // set new id only if container has no id
        if(container.getId() == null) {
            container.setId(id);
        }

        synchronized(getServiceContainers()) {
            getServiceContainers().add(container);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void removeServiceContainer(final ServiceContainer container) {
        if(container == null) {
            throw new IllegalArgumentException("container argument must not be null in method removeServiceContainer");
        }
        synchronized(getServiceContainers()) {
            getServiceContainers().remove(container);
        }

        cleanUpServiceContainer(container);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final EList<PlaceInfo> getPlaceInfos() {
        final EList<PlaceInfo> placeInfos = new BasicEList<PlaceInfo>();
        final DoubleProperty temperatureProperty;
        List<Device> devicesInPlace;
        synchronized(getPlaces()) {
            for(final Place place : getPlaces()) {
                // get devices of the place
                devicesInPlace = new LinkedList<Device>();
                synchronized(place.getElements()) {
                    for(final ElementWithPosition element : place.getElements()) {
                        if(element instanceof Device) {
                            devicesInPlace.add((Device)element);
                        }
                    }
                }
                // create place info
                placeInfos.add(new PlaceInfo(place.getId(), EnvironmentUtility.getTemperature(place), devicesInPlace));
            }
        }
        return placeInfos;
    }

    /**
     * <!-- begin-user-doc --> The id attribute is optional. <!-- end-user-doc
     * -->
     * 
     * @generated NOT
     * @author Andre Schulz
     */
    @Override
    public void addRoom(final String id, final String name, final RoomType roomType, final Integer floor) {
        if(id == null) {
            throw new IllegalArgumentException("id argument must not be null in method addRoom");
        } else if(name == null) {
            throw new IllegalArgumentException("name argument must not be null in method addRoom");
        } else if(roomType == null) {
            throw new IllegalArgumentException("roomType argument must not be null in method addRoom");
        }

        synchronized(getPlaces()) {
            for(final Place place : getPlaces()) {
                if(id.equals(place.getId())) {
                    throw new IllegalArgumentException("id " + id + " for Place already exists at Environment!");
                }
            }
        }

        final Room room = ContextFactory.eINSTANCE.createRoom();
        room.setId(id);
        room.setName(name);
        room.setRoomType(roomType);
        room.setFloor(floor);

        synchronized(getPlaces()) {
            getPlaces().add(room);
        }
    }

    /**
     * <!-- begin-user-doc --> The id attribute is optional. <!-- end-user-doc
     * -->
     * 
     * @generated NOT
     * @author Andre Schulz
     */
    @Override
    public void addOutdoors(final String id, final String name, final Integer floor) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("addOutdoors " + id + " " + name);
        }

        if(id == null) {
            throw new IllegalArgumentException("id argument must not be null in method addOutdoors");
        } else if(name == null) {
            throw new IllegalArgumentException("name argument must not be null in method addOutdoors");
        }

        synchronized(getPlaces()) {
            for(final Place place : getPlaces()) {
                if(id.equals(place.getId())) {
                    throw new IllegalArgumentException("id " + id + " for Place already exists at Environment!");
                }
            }
        }

        final Outdoors outdoors = ContextFactory.eINSTANCE.createOutdoors();
        outdoors.setId(id);
        outdoors.setName(name);
        outdoors.setFloor(floor);

        synchronized(getPlaces()) {
            getPlaces().add(outdoors);
        }

        if(LOG.isDebugEnabled()) {
            LOG.debug("addOutdoors successfully");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     * @author Andre Schulz
     */
    @Override
    public void removePlace(final Place place) {
        if(place == null) {
            throw new IllegalArgumentException("place argument must not be null in method removePlace");
        }

        LinkedList<ElementWithPosition> elements;

        // +++++ remove all doors +++++
        synchronized(place.getDoors()) {
            elements = new LinkedList<ElementWithPosition>();

            for(final Door door : place.getDoors()) {
                elements.add(door);
            }
        }

        for(final ElementWithPosition element : elements) {
            if(element instanceof Door) {
                synchronized(place.getDoors()) {
                    place.getDoors().remove(element);
                }
            }
        }
        // ----- remove all doors -----

        // +++++ remove all windows +++++
        synchronized(place.getDoors()) {
            elements = new LinkedList<ElementWithPosition>();

            for(final Window window : place.getWindows()) {
                elements.add(window);
            }
        }

        for(final ElementWithPosition element : elements) {
            if(element instanceof Window) {
                synchronized(place.getWindows()) {
                    place.getWindows().remove(element);
                }
            }
        }
        // ----- remove all windows -----

        // +++++ remove place tag of all containing elements +++++
        synchronized(place.getElements()) {
            elements = new LinkedList<ElementWithPosition>();

            // get all elements
            for(final ElementWithPosition element : place.getElements()) {
                elements.add(element);
            }
        }

        for(final ElementWithPosition element : elements) {
            element.setPlace(null);
        }
        // ----- remove place tag of all containing elements -----

        // remove place from environment
        synchronized(getPlaces()) {
            getPlaces().remove(place);
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public void addDevice(final Device device, final String id) {
        if(device == null) {
            throw new IllegalArgumentException("device argument must not be null in method removeDevice");
        } else if((device.getId() == null) && (id == null)) {
            throw new IllegalArgumentException("newDeviceId argument must not be null in method removeDevice if id of device is null");
        }

        if(device.getId() == null) {
            // check new id only if Device has no id
            synchronized(getDevices()) {
                for(final Device d : getDevices()) {
                    if(id.equals(d.getId())) {
                        throw new IllegalArgumentException("id " + id + " for Device already exists at Environment!");
                    }
                }
            }

            // set id only if device has no id
            device.setId(id);
        }

        synchronized(this.getDevices()) {
            this.getDevices().add(device);
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public void removeDevice(final Device device) {
        synchronized(this.getDevices()) {
            this.getDevices().remove(device);
        }

        cleanUpElement(device);
    }

    public static void main(final String[] args) {
        final Environment environment = ContextFactory.eINSTANCE.createEnvironment();
        final Place place = ContextFactory.eINSTANCE.createOutdoors();
        environment.getPlaces().add(place);

        // Erstellen des Sensors
        final TemperatureSensor temperatureSensorDevice = ContextFactory.eINSTANCE.createTemperatureSensor();
        temperatureSensorDevice.setId("id");

        // Konfiguration des Values
        final DoubleProperty value = PropertiesFactory.eINSTANCE.createDoubleProperty();
        // Sensor Proxy fuer die Property
        final Sensor valueSensor = PropertiesFactory.eINSTANCE.createSensor();
        // Klassennamen mit Deinem Sensor ersetzen
        valueSensor.setNewSensorWrapperClassName(DummySensorWrapper.class.getName());
        // ggf. Konfigurationsproperties
        valueSensor.addConfigurationProperty(DummyServiceConstants.PROPERTY_KEY_VariableName, "temperatur");
        valueSensor.addConfigurationProperty(DummyServiceConstants.PROPERTY_KEY_InitialValue, "23");
        // valueSensor.addConfigurationProperty("Schluessel", "Wert");

        value.setSensor(valueSensor);
        temperatureSensorDevice.setValue(value);

        // das ist wichtig - der neue Sensor muss gestartet werden
        final MetaModel metamodel = EcoreMetaModelConverter.convert(ContextPackage.eINSTANCE);
        metamodel.getMetaType().getMetaModel().start(metamodel);
        metamodel.start(valueSensor);

        // Hinzufuegen ins Environment
        temperatureSensorDevice.setPlace(place);
        environment.addDevice(temperatureSensorDevice, null);

        System.out.println(EnvironmentUtility.getTemperature(place));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     * @author Andre Schulz
     */
    @Override
    public void addProvider(final ContextProvider provider) {
        if(provider == null) {
            throw new IllegalArgumentException("provider argument must not be null in method addProvider!");
        }

        synchronized(getProviders()) {
            getProviders().add(provider);
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     * @author Andre Schulz
     */
    @Override
    public void removeProvider(final ContextProvider provider) {
        if(provider == null) {
            throw new IllegalArgumentException("provider argument must not be null in method addProvider!");
        }

        if(provider instanceof LocalisationProviderProxy) {
            cleanUpLocalisationProviderProxy((LocalisationProviderProxy)provider);
        }

        synchronized(getProviders()) {
            getProviders().remove(provider);
        }
    }

    /**
     * Recursive cleaning of {@link ServiceContainer}s.
     * 
     * @param serviceContainer
     *            The {@link ServiceContainer} to clean.
     * @author Andre Schulz
     */
    private static void cleanUpServiceContainer(final ServiceContainer serviceContainer) {
        if(serviceContainer.getServices().size() > 0) {
            synchronized(serviceContainer.getServices()) {
                for(final Service service : serviceContainer.getServices()) {
                    if(service instanceof ServiceContainer) {
                        cleanUpServiceContainer((ServiceContainer)service);
                    }
                }

                serviceContainer.getServices().clear();
            }
        }
    }

    /**
     * Cleans a {@link LocalisationProviderProxy} by removing all
     * {@link LocalizationTag}s.
     * 
     * @param localisationProviderProxy
     *            The {@link LocalisationProviderProxy} to clean.
     * 
     * @author Andre Schulz
     * @since 1.3.15
     */
    private static void cleanUpLocalisationProviderProxy(final LocalisationProviderProxy localisationProviderProxy) {
        final LinkedList<LocalizationTag> localizationTags = new LinkedList<LocalizationTag>();

        for(final LocalizationTag localizationTag : localisationProviderProxy.getTags()) {
            localizationTags.add(localizationTag);
        }

        for(final LocalizationTag localizationTag : localizationTags) {
            localisationProviderProxy.removeTag(localizationTag);
        }
    }

    private static void cleanUpElement(final ElementWithPosition elementWithPosition) {
        elementWithPosition.setPosition(null);
        final Place place = elementWithPosition.getPlace();
        if(place != null) {
            place.removeElement(elementWithPosition);
        }
        synchronized(elementWithPosition.getTags()) {
            elementWithPosition.getTags().clear();
        }

        final TreeIterator<EObject> iterator = elementWithPosition.eAllContents();
        EObject element;
        final LinkedList<EObject> elements = new LinkedList<EObject>();

        while(iterator.hasNext()) {
            element = iterator.next();

            if(element instanceof ElementWithPosition) {
                cleanUpElement((ElementWithPosition)element);
            }

            elements.add(element);
        }

        for(final EObject e : elements) {
            EcoreUtil.remove(e);
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.ENVIRONMENT__PLACES:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getPlaces()).basicAdd(otherEnd, msgs);
            case ContextPackage.ENVIRONMENT__PROVIDERS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getProviders()).basicAdd(otherEnd, msgs);
            case ContextPackage.ENVIRONMENT__DEVICES:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getDevices()).basicAdd(otherEnd, msgs);
            case ContextPackage.ENVIRONMENT__USERS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getUsers()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.ENVIRONMENT__PLACES:
                return ((InternalEList<?>)getPlaces()).basicRemove(otherEnd, msgs);
            case ContextPackage.ENVIRONMENT__PROVIDERS:
                return ((InternalEList<?>)getProviders()).basicRemove(otherEnd, msgs);
            case ContextPackage.ENVIRONMENT__DEVICES:
                return ((InternalEList<?>)getDevices()).basicRemove(otherEnd, msgs);
            case ContextPackage.ENVIRONMENT__USERS:
                return ((InternalEList<?>)getUsers()).basicRemove(otherEnd, msgs);
            case ContextPackage.ENVIRONMENT__ASSISTANTS:
                return ((InternalEList<?>)getAssistants()).basicRemove(otherEnd, msgs);
            case ContextPackage.ENVIRONMENT__SERVICE_CONTAINERS:
                return ((InternalEList<?>)getServiceContainers()).basicRemove(otherEnd, msgs);
            case ContextPackage.ENVIRONMENT__METERS:
                return ((InternalEList<?>)getMeters()).basicRemove(otherEnd, msgs);
            case ContextPackage.ENVIRONMENT__DISCOVERERS:
                return ((InternalEList<?>)getDiscoverers()).basicRemove(otherEnd, msgs);
            case ContextPackage.ENVIRONMENT__ACTIVITIES:
                return ((InternalEList<?>)getActivities()).basicRemove(otherEnd, msgs);
            case ContextPackage.ENVIRONMENT__TOOLS:
                return ((InternalEList<?>)getTools()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch(featureID) {
            case ContextPackage.ENVIRONMENT__PLACES:
                return getPlaces();
            case ContextPackage.ENVIRONMENT__PROVIDERS:
                return getProviders();
            case ContextPackage.ENVIRONMENT__DEVICES:
                return getDevices();
            case ContextPackage.ENVIRONMENT__USERS:
                return getUsers();
            case ContextPackage.ENVIRONMENT__ASSISTANTS:
                return getAssistants();
            case ContextPackage.ENVIRONMENT__INITIAL_ASSISTANT:
                if(resolve)
                    return getInitialAssistant();
                return basicGetInitialAssistant();
            case ContextPackage.ENVIRONMENT__SERVICE_CONTAINERS:
                return getServiceContainers();
            case ContextPackage.ENVIRONMENT__METERS:
                return getMeters();
            case ContextPackage.ENVIRONMENT__DISCOVERERS:
                return getDiscoverers();
            case ContextPackage.ENVIRONMENT__ACTIVITIES:
                return getActivities();
            case ContextPackage.ENVIRONMENT__TOOLS:
                return getTools();
            case ContextPackage.ENVIRONMENT__NAME:
                return getName();
            case ContextPackage.ENVIRONMENT__DESCRIPTION:
                return getDescription();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch(featureID) {
            case ContextPackage.ENVIRONMENT__PLACES:
                getPlaces().clear();
                getPlaces().addAll((Collection<? extends Place>)newValue);
                return;
            case ContextPackage.ENVIRONMENT__PROVIDERS:
                getProviders().clear();
                getProviders().addAll((Collection<? extends ContextProvider>)newValue);
                return;
            case ContextPackage.ENVIRONMENT__DEVICES:
                getDevices().clear();
                getDevices().addAll((Collection<? extends Device>)newValue);
                return;
            case ContextPackage.ENVIRONMENT__USERS:
                getUsers().clear();
                getUsers().addAll((Collection<? extends User>)newValue);
                return;
            case ContextPackage.ENVIRONMENT__ASSISTANTS:
                getAssistants().clear();
                getAssistants().addAll((Collection<? extends Assistant>)newValue);
                return;
            case ContextPackage.ENVIRONMENT__INITIAL_ASSISTANT:
                setInitialAssistant((HomeOSAssistant)newValue);
                return;
            case ContextPackage.ENVIRONMENT__SERVICE_CONTAINERS:
                getServiceContainers().clear();
                getServiceContainers().addAll((Collection<? extends ServiceContainer>)newValue);
                return;
            case ContextPackage.ENVIRONMENT__METERS:
                getMeters().clear();
                getMeters().addAll((Collection<? extends Meter>)newValue);
                return;
            case ContextPackage.ENVIRONMENT__DISCOVERERS:
                getDiscoverers().clear();
                getDiscoverers().addAll((Collection<? extends ServiceDiscovererProxy>)newValue);
                return;
            case ContextPackage.ENVIRONMENT__ACTIVITIES:
                getActivities().clear();
                getActivities().addAll((Collection<? extends Activity>)newValue);
                return;
            case ContextPackage.ENVIRONMENT__TOOLS:
                getTools().clear();
                getTools().addAll((Collection<? extends Tool>)newValue);
                return;
            case ContextPackage.ENVIRONMENT__NAME:
                setName((String)newValue);
                return;
            case ContextPackage.ENVIRONMENT__DESCRIPTION:
                setDescription((String)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch(featureID) {
            case ContextPackage.ENVIRONMENT__PLACES:
                getPlaces().clear();
                return;
            case ContextPackage.ENVIRONMENT__PROVIDERS:
                getProviders().clear();
                return;
            case ContextPackage.ENVIRONMENT__DEVICES:
                getDevices().clear();
                return;
            case ContextPackage.ENVIRONMENT__USERS:
                getUsers().clear();
                return;
            case ContextPackage.ENVIRONMENT__ASSISTANTS:
                getAssistants().clear();
                return;
            case ContextPackage.ENVIRONMENT__INITIAL_ASSISTANT:
                setInitialAssistant((HomeOSAssistant)null);
                return;
            case ContextPackage.ENVIRONMENT__SERVICE_CONTAINERS:
                getServiceContainers().clear();
                return;
            case ContextPackage.ENVIRONMENT__METERS:
                getMeters().clear();
                return;
            case ContextPackage.ENVIRONMENT__DISCOVERERS:
                getDiscoverers().clear();
                return;
            case ContextPackage.ENVIRONMENT__ACTIVITIES:
                getActivities().clear();
                return;
            case ContextPackage.ENVIRONMENT__TOOLS:
                getTools().clear();
                return;
            case ContextPackage.ENVIRONMENT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ContextPackage.ENVIRONMENT__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch(featureID) {
            case ContextPackage.ENVIRONMENT__PLACES:
                return places != null && !places.isEmpty();
            case ContextPackage.ENVIRONMENT__PROVIDERS:
                return providers != null && !providers.isEmpty();
            case ContextPackage.ENVIRONMENT__DEVICES:
                return devices != null && !devices.isEmpty();
            case ContextPackage.ENVIRONMENT__USERS:
                return users != null && !users.isEmpty();
            case ContextPackage.ENVIRONMENT__ASSISTANTS:
                return assistants != null && !assistants.isEmpty();
            case ContextPackage.ENVIRONMENT__INITIAL_ASSISTANT:
                return initialAssistant != null;
            case ContextPackage.ENVIRONMENT__SERVICE_CONTAINERS:
                return serviceContainers != null && !serviceContainers.isEmpty();
            case ContextPackage.ENVIRONMENT__METERS:
                return meters != null && !meters.isEmpty();
            case ContextPackage.ENVIRONMENT__DISCOVERERS:
                return discoverers != null && !discoverers.isEmpty();
            case ContextPackage.ENVIRONMENT__ACTIVITIES:
                return activities != null && !activities.isEmpty();
            case ContextPackage.ENVIRONMENT__TOOLS:
                return tools != null && !tools.isEmpty();
            case ContextPackage.ENVIRONMENT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ContextPackage.ENVIRONMENT__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null
                        : !DESCRIPTION_EDEFAULT.equals(description);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if(eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: ");
        result.append(name);
        result.append(", description: ");
        result.append(description);
        result.append(')');
        return result.toString();
    }
} // EnvironmentImpl
