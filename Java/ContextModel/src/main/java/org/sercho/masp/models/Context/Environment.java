/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import de.dailab.masp.models.Properties.ServiceContainer;
import de.dailab.masp.models.Properties.ServiceDiscovererProxy;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Environment</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> The Environment element is the root element of the
 * context model. It described the context as the combination of places, users
 * and devices. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Environment#getPlaces <em>Places
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Environment#getProviders <em>
 * Providers</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Environment#getDevices <em>Devices
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Environment#getUsers <em>Users
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Environment#getAssistants <em>
 * Assistants</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Environment#getInitialAssistant
 * <em>Initial Assistant</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Environment#getServiceContainers
 * <em>Service Containers</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Environment#getMeters <em>Meters
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Environment#getDiscoverers <em>
 * Discoverers</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Environment#getActivities <em>
 * Activities</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Environment#getTools <em>Tools
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Environment#getName <em>Name</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Environment#getDescription <em>
 * Description</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getEnvironment()
 * @model
 * @generated
 */
public interface Environment extends EObject {

    /**
     * Returns the value of the '<em><b>Places</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.sercho.masp.models.Context.Place}. It is bidirectional and its
     * opposite is '{@link org.sercho.masp.models.Context.Place#getEnvironment
     * <em>Environment</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Places</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> Places in the environment.
     * <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Places</em>' containment reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getEnvironment_Places()
     * @see org.sercho.masp.models.Context.Place#getEnvironment
     * @model opposite="environment" containment="true"
     * @generated
     */
    EList<Place> getPlaces();

    /**
     * Returns the value of the '<em><b>Providers</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.sercho.masp.models.Context.ContextProvider}. It is
     * bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.ContextProvider#getEnvironment
     * <em>Environment</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Providers</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Providers</em>' containment reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getEnvironment_Providers()
     * @see org.sercho.masp.models.Context.ContextProvider#getEnvironment
     * @model opposite="environment" containment="true"
     * @generated
     */
    EList<ContextProvider> getProviders();

    /**
     * Returns the value of the '<em><b>Devices</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.sercho.masp.models.Context.Device}. It is bidirectional and
     * its opposite is '
     * {@link org.sercho.masp.models.Context.Device#getEnvironment
     * <em>Environment</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Devices</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> Devices in the
     * environment. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Devices</em>' containment reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getEnvironment_Devices()
     * @see org.sercho.masp.models.Context.Device#getEnvironment
     * @model opposite="environment" containment="true"
     * @generated
     */
    EList<Device> getDevices();

    /**
     * Returns the value of the '<em><b>Users</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.sercho.masp.models.Context.User}. It is bidirectional and its
     * opposite is '{@link org.sercho.masp.models.Context.User#getEnvironment
     * <em>Environment</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Users</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> Users in the environment.
     * <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Users</em>' containment reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getEnvironment_Users()
     * @see org.sercho.masp.models.Context.User#getEnvironment
     * @model opposite="environment" containment="true"
     * @generated
     */
    EList<User> getUsers();

    /**
     * Returns the value of the '<em><b>Assistants</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.sercho.masp.models.Context.Assistant}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Assistants</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Assistants</em>' containment reference
     *         list.
     * @see org.sercho.masp.models.Context.ContextPackage#getEnvironment_Assistants()
     * @model containment="true"
     * @generated
     */
    EList<Assistant> getAssistants();

    /**
     * Returns the value of the '<em><b>Initial Assistant</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Initial Assistant</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Initial Assistant</em>' reference.
     * @see #setInitialAssistant(HomeOSAssistant)
     * @see org.sercho.masp.models.Context.ContextPackage#getEnvironment_InitialAssistant()
     * @model
     * @generated
     */
    HomeOSAssistant getInitialAssistant();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Environment#getInitialAssistant
     * <em>Initial Assistant</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Initial Assistant</em>' reference.
     * @see #getInitialAssistant()
     * @generated
     */
    void setInitialAssistant(HomeOSAssistant value);

    /**
     * Returns the value of the '<em><b>Service Containers</b></em>' containment
     * reference list. The list contents are of type
     * {@link de.dailab.masp.models.Properties.ServiceContainer}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Service Containers</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Service Containers</em>' containment
     *         reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getEnvironment_ServiceContainers()
     * @model containment="true"
     * @generated
     */
    EList<ServiceContainer> getServiceContainers();

    /**
     * Returns the value of the '<em><b>Meters</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.sercho.masp.models.Context.Meter}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Meters</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Meters</em>' containment reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getEnvironment_Meters()
     * @model containment="true"
     * @generated
     */
    EList<Meter> getMeters();

    /**
     * Returns the value of the '<em><b>Discoverers</b></em>' containment
     * reference list. The list contents are of type
     * {@link de.dailab.masp.models.Properties.ServiceDiscovererProxy}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Service
     * discovery proxies are used to discover new actors and sensors available
     * in the environment. Each proxy must be connected to a service container
     * in which it will place the discovered services. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Discoverers</em>' containment reference
     *         list.
     * @see org.sercho.masp.models.Context.ContextPackage#getEnvironment_Discoverers()
     * @model containment="true"
     * @generated
     */
    EList<ServiceDiscovererProxy> getDiscoverers();

    /**
     * Returns the value of the '<em><b>Activities</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.sercho.masp.models.Context.Activity}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Activities</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Activities</em>' containment reference
     *         list.
     * @see org.sercho.masp.models.Context.ContextPackage#getEnvironment_Activities()
     * @model containment="true"
     * @generated
     */
    EList<Activity> getActivities();

    /**
     * Returns the value of the '<em><b>Tools</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.sercho.masp.models.Context.Tool}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tools</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Tools</em>' containment reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getEnvironment_Tools()
     * @model containment="true"
     * @generated
     */
    EList<Tool> getTools();

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * informative name of this environment <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.sercho.masp.models.Context.ContextPackage#getEnvironment_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Environment#getName <em>Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * informative description of this environment <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.sercho.masp.models.Context.ContextPackage#getEnvironment_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Environment#getDescription
     * <em>Description</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model idRequired="true" nameRequired="true" surnameRequired="true"
     *        birthdateRequired="true" annotation="Definition modifies='users'"
     * @generated
     */
    void addUser(String id, String name, String surname, String birthdate);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model annotation="Definition modifies='users'"
     * @generated
     */
    void removeUser(User user);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Adds a ServiceContainer to the Environment. The id attribute is only
     * necessary if the Service Container has no id, also it is applied only if
     * it has no id. <!-- end-model-doc -->
     * 
     * @model containerRequired="true"
     *        annotation="Definition modifies='serviceContainers'"
     *        annotation="Description Value='The id must be set only if the id
     *        of the container is <code>null</code>.'"
     * @generated
     */
    void addServiceContainer(ServiceContainer container, String id);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model containerRequired="true"
     *        annotation="Definition modifies='serviceContainers'"
     * @generated
     */
    void removeServiceContainer(ServiceContainer container);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model kind="operation"
     *        dataType="org.sercho.masp.models.Context.PlaceInfo"
     * @generated
     */
    EList<PlaceInfo> getPlaceInfos();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Adds a Room to the Environment. The floor attribute is optional. <!--
     * end-model-doc -->
     * 
     * @model annotation="Definition modifies='places'"
     * @generated
     */
    void addRoom(String id, String name, RoomType roomType, Integer floor);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Adds an Outdoors to the Environment. The floor attribute is optional.
     * <!-- end-model-doc -->
     * 
     * @model annotation="Definition modifies='places'"
     * @generated
     */
    void addOutdoors(String id, String name, Integer floor);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model annotation="Definition modifies='places'"
     * @generated
     */
    void removePlace(Place place);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The id must be set only if the id of the device is <code>null</code>.
     * <!-- end-model-doc -->
     * 
     * @model deviceRequired="true" annotation="Definition modifies='devices'"
     * @generated
     */
    void addDevice(Device device, String id);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model deviceRequired="true" annotation="Definition modifies='devices'"
     * @generated
     */
    void removeDevice(Device device);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model providerRequired="true"
     *        annotation="Definition modifies='providers'"
     * @generated
     */
    void addProvider(ContextProvider provider);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model providerRequired="true"
     *        annotation="Definition modifies='providers'"
     * @generated
     */
    void removeProvider(ContextProvider provider);

} // Environment
