/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc --> <!-- begin-model-doc --> The Context package enables
 * the definition of runtime context models. The root element of a context model
 * is the Environment. <!-- end-model-doc -->
 * 
 * @see org.sercho.masp.models.Context.ContextFactory
 * @model kind="package"
 * @generated
 */
public interface ContextPackage extends EPackage {

    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "Context";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.dai-labor.de/~masp/Context-1.3.ecore";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "Context";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    ContextPackage eINSTANCE = org.sercho.masp.models.Context.impl.ContextPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.EnvironmentElementImpl
     * <em>Environment Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.EnvironmentElementImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getEnvironmentElement()
     * @generated
     */
    int ENVIRONMENT_ELEMENT = 1;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.InteractionResourceImpl
     * <em>Interaction Resource</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.InteractionResourceImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getInteractionResource()
     * @generated
     */
    int INTERACTION_RESOURCE = 2;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.EnvironmentImpl
     * <em>Environment</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.sercho.masp.models.Context.impl.EnvironmentImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getEnvironment()
     * @generated
     */
    int ENVIRONMENT = 0;

    /**
     * The feature id for the '<em><b>Places</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT__PLACES = 0;

    /**
     * The feature id for the '<em><b>Providers</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT__PROVIDERS = 1;

    /**
     * The feature id for the '<em><b>Devices</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT__DEVICES = 2;

    /**
     * The feature id for the '<em><b>Users</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT__USERS = 3;

    /**
     * The feature id for the '<em><b>Assistants</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT__ASSISTANTS = 4;

    /**
     * The feature id for the '<em><b>Initial Assistant</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT__INITIAL_ASSISTANT = 5;

    /**
     * The feature id for the '<em><b>Service Containers</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT__SERVICE_CONTAINERS = 6;

    /**
     * The feature id for the '<em><b>Meters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT__METERS = 7;

    /**
     * The feature id for the '<em><b>Discoverers</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT__DISCOVERERS = 8;

    /**
     * The feature id for the '<em><b>Activities</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT__ACTIVITIES = 9;

    /**
     * The feature id for the '<em><b>Tools</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT__TOOLS = 10;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT__NAME = 11;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT__DESCRIPTION = 12;

    /**
     * The number of structural features of the '<em>Environment</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT_FEATURE_COUNT = 13;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT_ELEMENT__ID = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT_ELEMENT__NAME = 1;

    /**
     * The number of structural features of the '<em>Environment Element</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT_ELEMENT_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.ElementWithPositionImpl
     * <em>Element With Position</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.ElementWithPositionImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getElementWithPosition()
     * @generated
     */
    int ELEMENT_WITH_POSITION = 9;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_WITH_POSITION__ID = ENVIRONMENT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_WITH_POSITION__NAME = ENVIRONMENT_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_WITH_POSITION__TAGS = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_WITH_POSITION__POSITION = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_WITH_POSITION__POSITION_TIME_STAMP = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_WITH_POSITION__PLACE = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Element With Position</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_WITH_POSITION_FEATURE_COUNT = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_RESOURCE__ID = ELEMENT_WITH_POSITION__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_RESOURCE__NAME = ELEMENT_WITH_POSITION__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_RESOURCE__TAGS = ELEMENT_WITH_POSITION__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_RESOURCE__POSITION = ELEMENT_WITH_POSITION__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_RESOURCE__POSITION_TIME_STAMP = ELEMENT_WITH_POSITION__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_RESOURCE__PLACE = ELEMENT_WITH_POSITION__PLACE;

    /**
     * The feature id for the '<em><b>User</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_RESOURCE__USER = ELEMENT_WITH_POSITION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Device</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_RESOURCE__DEVICE = ELEMENT_WITH_POSITION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Available</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_RESOURCE__AVAILABLE = ELEMENT_WITH_POSITION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Modality</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_RESOURCE__MODALITY = ELEMENT_WITH_POSITION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_RESOURCE__MOBILE = ELEMENT_WITH_POSITION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Personal</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_RESOURCE__PERSONAL = ELEMENT_WITH_POSITION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Interaction Status</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_RESOURCE__INTERACTION_STATUS = ELEMENT_WITH_POSITION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Interaction Status Time Stamp</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_RESOURCE__INTERACTION_STATUS_TIME_STAMP = ELEMENT_WITH_POSITION_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '<em>Interaction Resource</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_RESOURCE_FEATURE_COUNT = ELEMENT_WITH_POSITION_FEATURE_COUNT + 8;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.UserImpl <em>User</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.UserImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getUser()
     * @generated
     */
    int USER = 3;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER__ID = ELEMENT_WITH_POSITION__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER__NAME = ELEMENT_WITH_POSITION__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER__TAGS = ELEMENT_WITH_POSITION__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER__POSITION = ELEMENT_WITH_POSITION__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER__POSITION_TIME_STAMP = ELEMENT_WITH_POSITION__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER__PLACE = ELEMENT_WITH_POSITION__PLACE;

    /**
     * The feature id for the '<em><b>Birth Date</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER__BIRTH_DATE = ELEMENT_WITH_POSITION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Surname</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER__SURNAME = ELEMENT_WITH_POSITION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Left Handed</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER__LEFT_HANDED = ELEMENT_WITH_POSITION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Resources</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER__RESOURCES = ELEMENT_WITH_POSITION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Line Of Vision</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER__LINE_OF_VISION = ELEMENT_WITH_POSITION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Ir Experience</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER__IR_EXPERIENCE = ELEMENT_WITH_POSITION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Follow Me</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER__FOLLOW_ME = ELEMENT_WITH_POSITION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER__ENVIRONMENT = ELEMENT_WITH_POSITION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Current Assistants</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER__CURRENT_ASSISTANTS = ELEMENT_WITH_POSITION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Past Assistants</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER__PAST_ASSISTANTS = ELEMENT_WITH_POSITION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Installed Assistants</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER__INSTALLED_ASSISTANTS = ELEMENT_WITH_POSITION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Password Hash</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER__PASSWORD_HASH = ELEMENT_WITH_POSITION_FEATURE_COUNT + 11;

    /**
     * The number of structural features of the '<em>User</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_FEATURE_COUNT = ELEMENT_WITH_POSITION_FEATURE_COUNT + 12;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.AssistantImpl
     * <em>Assistant</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.AssistantImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getAssistant()
     * @generated
     */
    int ASSISTANT = 4;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ASSISTANT__ID = ENVIRONMENT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ASSISTANT__NAME = ENVIRONMENT_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Activities</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ASSISTANT__ACTIVITIES = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Current Users</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ASSISTANT__CURRENT_USERS = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Past Users</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ASSISTANT__PAST_USERS = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ASSISTANT__DESCRIPTION = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Connections</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ASSISTANT__CONNECTIONS = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Domain</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ASSISTANT__DOMAIN = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Assistant</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ASSISTANT_FEATURE_COUNT = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.ActivityImpl
     * <em>Activity</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.ActivityImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getActivity()
     * @generated
     */
    int ACTIVITY = 5;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ACTIVITY__NAME = 0;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ACTIVITY__DESCRIPTION = 1;

    /**
     * The feature id for the '<em><b>Used With</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ACTIVITY__USED_WITH = 2;

    /**
     * The feature id for the '<em><b>Configuration</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ACTIVITY__CONFIGURATION = 3;

    /**
     * The number of structural features of the '<em>Activity</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ACTIVITY_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.HomeOSAssistantImpl
     * <em>Home OS Assistant</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.HomeOSAssistantImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getHomeOSAssistant()
     * @generated
     */
    int HOME_OS_ASSISTANT = 6;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOME_OS_ASSISTANT__ID = ASSISTANT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOME_OS_ASSISTANT__NAME = ASSISTANT__NAME;

    /**
     * The feature id for the '<em><b>Activities</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOME_OS_ASSISTANT__ACTIVITIES = ASSISTANT__ACTIVITIES;

    /**
     * The feature id for the '<em><b>Current Users</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOME_OS_ASSISTANT__CURRENT_USERS = ASSISTANT__CURRENT_USERS;

    /**
     * The feature id for the '<em><b>Past Users</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOME_OS_ASSISTANT__PAST_USERS = ASSISTANT__PAST_USERS;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOME_OS_ASSISTANT__DESCRIPTION = ASSISTANT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Connections</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOME_OS_ASSISTANT__CONNECTIONS = ASSISTANT__CONNECTIONS;

    /**
     * The feature id for the '<em><b>Domain</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOME_OS_ASSISTANT__DOMAIN = ASSISTANT__DOMAIN;

    /**
     * The number of structural features of the '<em>Home OS Assistant</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOME_OS_ASSISTANT_FEATURE_COUNT = ASSISTANT_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.ThirdPartyAssistantImpl
     * <em>Third Party Assistant</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.ThirdPartyAssistantImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getThirdPartyAssistant()
     * @generated
     */
    int THIRD_PARTY_ASSISTANT = 7;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int THIRD_PARTY_ASSISTANT__ID = ASSISTANT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int THIRD_PARTY_ASSISTANT__NAME = ASSISTANT__NAME;

    /**
     * The feature id for the '<em><b>Activities</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int THIRD_PARTY_ASSISTANT__ACTIVITIES = ASSISTANT__ACTIVITIES;

    /**
     * The feature id for the '<em><b>Current Users</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int THIRD_PARTY_ASSISTANT__CURRENT_USERS = ASSISTANT__CURRENT_USERS;

    /**
     * The feature id for the '<em><b>Past Users</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int THIRD_PARTY_ASSISTANT__PAST_USERS = ASSISTANT__PAST_USERS;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int THIRD_PARTY_ASSISTANT__DESCRIPTION = ASSISTANT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Connections</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int THIRD_PARTY_ASSISTANT__CONNECTIONS = ASSISTANT__CONNECTIONS;

    /**
     * The feature id for the '<em><b>Domain</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int THIRD_PARTY_ASSISTANT__DOMAIN = ASSISTANT__DOMAIN;

    /**
     * The number of structural features of the '<em>Third Party Assistant</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int THIRD_PARTY_ASSISTANT_FEATURE_COUNT = ASSISTANT_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.LocalizationTagImpl
     * <em>Localization Tag</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.LocalizationTagImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getLocalizationTag()
     * @generated
     */
    int LOCALIZATION_TAG = 8;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCALIZATION_TAG__ID = ELEMENT_WITH_POSITION__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCALIZATION_TAG__NAME = ELEMENT_WITH_POSITION__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCALIZATION_TAG__TAGS = ELEMENT_WITH_POSITION__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCALIZATION_TAG__POSITION = ELEMENT_WITH_POSITION__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCALIZATION_TAG__POSITION_TIME_STAMP = ELEMENT_WITH_POSITION__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCALIZATION_TAG__PLACE = ELEMENT_WITH_POSITION__PLACE;

    /**
     * The feature id for the '<em><b>Detected</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCALIZATION_TAG__DETECTED = ELEMENT_WITH_POSITION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Register</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCALIZATION_TAG__REGISTER = ELEMENT_WITH_POSITION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Provider</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCALIZATION_TAG__PROVIDER = ELEMENT_WITH_POSITION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Element</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCALIZATION_TAG__ELEMENT = ELEMENT_WITH_POSITION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Localization Tag</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCALIZATION_TAG_FEATURE_COUNT = ELEMENT_WITH_POSITION_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.PlaceImpl <em>Place</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.PlaceImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getPlace()
     * @generated
     */
    int PLACE = 10;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PLACE__ID = ENVIRONMENT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PLACE__NAME = ENVIRONMENT_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Areas</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PLACE__AREAS = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Elements</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PLACE__ELEMENTS = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PLACE__ENVIRONMENT = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Doors</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PLACE__DOORS = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Windows</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PLACE__WINDOWS = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Floor</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PLACE__FLOOR = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Place</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PLACE_FEATURE_COUNT = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.RoomImpl <em>Room</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.RoomImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getRoom()
     * @generated
     */
    int ROOM = 11;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROOM__ID = PLACE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROOM__NAME = PLACE__NAME;

    /**
     * The feature id for the '<em><b>Areas</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROOM__AREAS = PLACE__AREAS;

    /**
     * The feature id for the '<em><b>Elements</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROOM__ELEMENTS = PLACE__ELEMENTS;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROOM__ENVIRONMENT = PLACE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Doors</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROOM__DOORS = PLACE__DOORS;

    /**
     * The feature id for the '<em><b>Windows</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROOM__WINDOWS = PLACE__WINDOWS;

    /**
     * The feature id for the '<em><b>Floor</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROOM__FLOOR = PLACE__FLOOR;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROOM__TYPE = PLACE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Room</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROOM_FEATURE_COUNT = PLACE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.ContextProviderImpl
     * <em>Provider</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.ContextProviderImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getContextProvider()
     * @generated
     */
    int CONTEXT_PROVIDER = 15;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.LocalisationProviderProxyImpl
     * <em>Localisation Provider Proxy</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.LocalisationProviderProxyImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getLocalisationProviderProxy()
     * @generated
     */
    int LOCALISATION_PROVIDER_PROXY = 16;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.InputInteractionResourceImpl
     * <em>Input Interaction Resource</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.InputInteractionResourceImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getInputInteractionResource()
     * @generated
     */
    int INPUT_INTERACTION_RESOURCE = 20;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.HapticalInteractionResourcesImpl
     * <em>Haptical Interaction Resources</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.HapticalInteractionResourcesImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getHapticalInteractionResources()
     * @generated
     */
    int HAPTICAL_INTERACTION_RESOURCES = 63;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.MouseImpl <em>Mouse</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.MouseImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getMouse()
     * @generated
     */
    int MOUSE = 17;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.KeyboardImpl
     * <em>Keyboard</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.KeyboardImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getKeyboard()
     * @generated
     */
    int KEYBOARD = 18;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.OutputInteractionResourceImpl
     * <em>Output Interaction Resource</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.OutputInteractionResourceImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getOutputInteractionResource()
     * @generated
     */
    int OUTPUT_INTERACTION_RESOURCE = 21;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.DisplayImpl <em>Display</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.DisplayImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getDisplay()
     * @generated
     */
    int DISPLAY = 19;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.GestureIRImpl
     * <em>Gesture IR</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.sercho.masp.models.Context.impl.GestureIRImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getGestureIR()
     * @generated
     */
    int GESTURE_IR = 22;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.TouchpadImpl
     * <em>Touchpad</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.TouchpadImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getTouchpad()
     * @generated
     */
    int TOUCHPAD = 23;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.DeviceImpl <em>Device</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.DeviceImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getDevice()
     * @generated
     */
    int DEVICE = 25;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.ChannelImpl <em>Channel</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.ChannelImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getChannel()
     * @generated
     */
    int CHANNEL = 54;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.MessageInputChannelImpl
     * <em>Message Input Channel</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.MessageInputChannelImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getMessageInputChannel()
     * @generated
     */
    int MESSAGE_INPUT_CHANNEL = 55;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.PointingInputChannelImpl
     * <em>Pointing Input Channel</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.PointingInputChannelImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getPointingInputChannel()
     * @generated
     */
    int POINTING_INPUT_CHANNEL = 57;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.MessageOutputChannelImpl
     * <em>Message Output Channel</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.MessageOutputChannelImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getMessageOutputChannel()
     * @generated
     */
    int MESSAGE_OUTPUT_CHANNEL = 56;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.GraphicalOutputChannelImpl
     * <em>Graphical Output Channel</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.GraphicalOutputChannelImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getGraphicalOutputChannel()
     * @generated
     */
    int GRAPHICAL_OUTPUT_CHANNEL = 58;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.LoudspeakerImpl
     * <em>Loudspeaker</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.sercho.masp.models.Context.impl.LoudspeakerImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getLoudspeaker()
     * @generated
     */
    int LOUDSPEAKER = 59;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.MicrophoneImpl
     * <em>Microphone</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.sercho.masp.models.Context.impl.MicrophoneImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getMicrophone()
     * @generated
     */
    int MICROPHONE = 60;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.VectorImpl <em>Vector</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.VectorImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getVector()
     * @generated
     */
    int VECTOR = 61;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.AreaImpl <em>Area</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.AreaImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getArea()
     * @generated
     */
    int AREA = 62;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.ConfigurationPropertyImpl
     * <em>Configuration Property</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.ConfigurationPropertyImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getConfigurationProperty()
     * @generated
     */
    int CONFIGURATION_PROPERTY = 64;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.TouchscreenImpl
     * <em>Touchscreen</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.sercho.masp.models.Context.impl.TouchscreenImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getTouchscreen()
     * @generated
     */
    int TOUCHSCREEN = 24;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.DoorImpl <em>Door</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.DoorImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getDoor()
     * @generated
     */
    int DOOR = 13;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.OutdoorsImpl
     * <em>Outdoors</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.OutdoorsImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getOutdoors()
     * @generated
     */
    int OUTDOORS = 12;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTDOORS__ID = PLACE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTDOORS__NAME = PLACE__NAME;

    /**
     * The feature id for the '<em><b>Areas</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTDOORS__AREAS = PLACE__AREAS;

    /**
     * The feature id for the '<em><b>Elements</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTDOORS__ELEMENTS = PLACE__ELEMENTS;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTDOORS__ENVIRONMENT = PLACE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Doors</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTDOORS__DOORS = PLACE__DOORS;

    /**
     * The feature id for the '<em><b>Windows</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTDOORS__WINDOWS = PLACE__WINDOWS;

    /**
     * The feature id for the '<em><b>Floor</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTDOORS__FLOOR = PLACE__FLOOR;

    /**
     * The number of structural features of the '<em>Outdoors</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTDOORS_FEATURE_COUNT = PLACE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOOR__ID = ELEMENT_WITH_POSITION__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOOR__NAME = ELEMENT_WITH_POSITION__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOOR__TAGS = ELEMENT_WITH_POSITION__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOOR__POSITION = ELEMENT_WITH_POSITION__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOOR__POSITION_TIME_STAMP = ELEMENT_WITH_POSITION__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOOR__PLACE = ELEMENT_WITH_POSITION__PLACE;

    /**
     * The feature id for the '<em><b>Open</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOOR__OPEN = ELEMENT_WITH_POSITION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOOR__SOURCE = ELEMENT_WITH_POSITION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOOR__TARGET = ELEMENT_WITH_POSITION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Span</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOOR__SPAN = ELEMENT_WITH_POSITION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Door</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOOR_FEATURE_COUNT = ELEMENT_WITH_POSITION_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.WindowImpl <em>Window</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.WindowImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getWindow()
     * @generated
     */
    int WINDOW = 14;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WINDOW__ID = ELEMENT_WITH_POSITION__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WINDOW__NAME = ELEMENT_WITH_POSITION__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WINDOW__TAGS = ELEMENT_WITH_POSITION__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WINDOW__POSITION = ELEMENT_WITH_POSITION__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WINDOW__POSITION_TIME_STAMP = ELEMENT_WITH_POSITION__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WINDOW__PLACE = ELEMENT_WITH_POSITION__PLACE;

    /**
     * The feature id for the '<em><b>Open</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WINDOW__OPEN = ELEMENT_WITH_POSITION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WINDOW__SOURCE = ELEMENT_WITH_POSITION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WINDOW__TARGET = ELEMENT_WITH_POSITION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Span</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WINDOW__SPAN = ELEMENT_WITH_POSITION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Window</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WINDOW_FEATURE_COUNT = ELEMENT_WITH_POSITION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTEXT_PROVIDER__ENVIRONMENT = 0;

    /**
     * The number of structural features of the '<em>Provider</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTEXT_PROVIDER_FEATURE_COUNT = 1;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCALISATION_PROVIDER_PROXY__ENVIRONMENT = CONTEXT_PROVIDER__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Api Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCALISATION_PROVIDER_PROXY__API_CLASS = CONTEXT_PROVIDER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Api</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCALISATION_PROVIDER_PROXY__API = CONTEXT_PROVIDER_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Tags</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCALISATION_PROVIDER_PROXY__TAGS = CONTEXT_PROVIDER_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Configuration</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCALISATION_PROVIDER_PROXY__CONFIGURATION = CONTEXT_PROVIDER_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '
     * <em>Localisation Provider Proxy</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCALISATION_PROVIDER_PROXY_FEATURE_COUNT = CONTEXT_PROVIDER_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INPUT_INTERACTION_RESOURCE__ID = INTERACTION_RESOURCE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INPUT_INTERACTION_RESOURCE__NAME = INTERACTION_RESOURCE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INPUT_INTERACTION_RESOURCE__TAGS = INTERACTION_RESOURCE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INPUT_INTERACTION_RESOURCE__POSITION = INTERACTION_RESOURCE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INPUT_INTERACTION_RESOURCE__POSITION_TIME_STAMP = INTERACTION_RESOURCE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INPUT_INTERACTION_RESOURCE__PLACE = INTERACTION_RESOURCE__PLACE;

    /**
     * The feature id for the '<em><b>User</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INPUT_INTERACTION_RESOURCE__USER = INTERACTION_RESOURCE__USER;

    /**
     * The feature id for the '<em><b>Device</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INPUT_INTERACTION_RESOURCE__DEVICE = INTERACTION_RESOURCE__DEVICE;

    /**
     * The feature id for the '<em><b>Available</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INPUT_INTERACTION_RESOURCE__AVAILABLE = INTERACTION_RESOURCE__AVAILABLE;

    /**
     * The feature id for the '<em><b>Modality</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INPUT_INTERACTION_RESOURCE__MODALITY = INTERACTION_RESOURCE__MODALITY;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INPUT_INTERACTION_RESOURCE__MOBILE = INTERACTION_RESOURCE__MOBILE;

    /**
     * The feature id for the '<em><b>Personal</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INPUT_INTERACTION_RESOURCE__PERSONAL = INTERACTION_RESOURCE__PERSONAL;

    /**
     * The feature id for the '<em><b>Interaction Status</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INPUT_INTERACTION_RESOURCE__INTERACTION_STATUS = INTERACTION_RESOURCE__INTERACTION_STATUS;

    /**
     * The feature id for the '<em><b>Interaction Status Time Stamp</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INPUT_INTERACTION_RESOURCE__INTERACTION_STATUS_TIME_STAMP = INTERACTION_RESOURCE__INTERACTION_STATUS_TIME_STAMP;

    /**
     * The number of structural features of the '
     * <em>Input Interaction Resource</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INPUT_INTERACTION_RESOURCE_FEATURE_COUNT = INTERACTION_RESOURCE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HAPTICAL_INTERACTION_RESOURCES__ID = INPUT_INTERACTION_RESOURCE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HAPTICAL_INTERACTION_RESOURCES__NAME = INPUT_INTERACTION_RESOURCE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HAPTICAL_INTERACTION_RESOURCES__TAGS = INPUT_INTERACTION_RESOURCE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HAPTICAL_INTERACTION_RESOURCES__POSITION = INPUT_INTERACTION_RESOURCE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HAPTICAL_INTERACTION_RESOURCES__POSITION_TIME_STAMP = INPUT_INTERACTION_RESOURCE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HAPTICAL_INTERACTION_RESOURCES__PLACE = INPUT_INTERACTION_RESOURCE__PLACE;

    /**
     * The feature id for the '<em><b>User</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HAPTICAL_INTERACTION_RESOURCES__USER = INPUT_INTERACTION_RESOURCE__USER;

    /**
     * The feature id for the '<em><b>Device</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HAPTICAL_INTERACTION_RESOURCES__DEVICE = INPUT_INTERACTION_RESOURCE__DEVICE;

    /**
     * The feature id for the '<em><b>Available</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HAPTICAL_INTERACTION_RESOURCES__AVAILABLE = INPUT_INTERACTION_RESOURCE__AVAILABLE;

    /**
     * The feature id for the '<em><b>Modality</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HAPTICAL_INTERACTION_RESOURCES__MODALITY = INPUT_INTERACTION_RESOURCE__MODALITY;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HAPTICAL_INTERACTION_RESOURCES__MOBILE = INPUT_INTERACTION_RESOURCE__MOBILE;

    /**
     * The feature id for the '<em><b>Personal</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HAPTICAL_INTERACTION_RESOURCES__PERSONAL = INPUT_INTERACTION_RESOURCE__PERSONAL;

    /**
     * The feature id for the '<em><b>Interaction Status</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HAPTICAL_INTERACTION_RESOURCES__INTERACTION_STATUS = INPUT_INTERACTION_RESOURCE__INTERACTION_STATUS;

    /**
     * The feature id for the '<em><b>Interaction Status Time Stamp</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HAPTICAL_INTERACTION_RESOURCES__INTERACTION_STATUS_TIME_STAMP = INPUT_INTERACTION_RESOURCE__INTERACTION_STATUS_TIME_STAMP;

    /**
     * The number of structural features of the '
     * <em>Haptical Interaction Resources</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HAPTICAL_INTERACTION_RESOURCES_FEATURE_COUNT = INPUT_INTERACTION_RESOURCE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOUSE__ID = HAPTICAL_INTERACTION_RESOURCES__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOUSE__NAME = HAPTICAL_INTERACTION_RESOURCES__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOUSE__TAGS = HAPTICAL_INTERACTION_RESOURCES__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOUSE__POSITION = HAPTICAL_INTERACTION_RESOURCES__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOUSE__POSITION_TIME_STAMP = HAPTICAL_INTERACTION_RESOURCES__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOUSE__PLACE = HAPTICAL_INTERACTION_RESOURCES__PLACE;

    /**
     * The feature id for the '<em><b>User</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOUSE__USER = HAPTICAL_INTERACTION_RESOURCES__USER;

    /**
     * The feature id for the '<em><b>Device</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOUSE__DEVICE = HAPTICAL_INTERACTION_RESOURCES__DEVICE;

    /**
     * The feature id for the '<em><b>Available</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOUSE__AVAILABLE = HAPTICAL_INTERACTION_RESOURCES__AVAILABLE;

    /**
     * The feature id for the '<em><b>Modality</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOUSE__MODALITY = HAPTICAL_INTERACTION_RESOURCES__MODALITY;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOUSE__MOBILE = HAPTICAL_INTERACTION_RESOURCES__MOBILE;

    /**
     * The feature id for the '<em><b>Personal</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOUSE__PERSONAL = HAPTICAL_INTERACTION_RESOURCES__PERSONAL;

    /**
     * The feature id for the '<em><b>Interaction Status</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOUSE__INTERACTION_STATUS = HAPTICAL_INTERACTION_RESOURCES__INTERACTION_STATUS;

    /**
     * The feature id for the '<em><b>Interaction Status Time Stamp</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOUSE__INTERACTION_STATUS_TIME_STAMP = HAPTICAL_INTERACTION_RESOURCES__INTERACTION_STATUS_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Motion Sensor</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOUSE__MOTION_SENSOR = HAPTICAL_INTERACTION_RESOURCES_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Buttons</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOUSE__BUTTONS = HAPTICAL_INTERACTION_RESOURCES_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Mouse</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOUSE_FEATURE_COUNT = HAPTICAL_INTERACTION_RESOURCES_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int KEYBOARD__ID = HAPTICAL_INTERACTION_RESOURCES__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int KEYBOARD__NAME = HAPTICAL_INTERACTION_RESOURCES__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int KEYBOARD__TAGS = HAPTICAL_INTERACTION_RESOURCES__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int KEYBOARD__POSITION = HAPTICAL_INTERACTION_RESOURCES__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int KEYBOARD__POSITION_TIME_STAMP = HAPTICAL_INTERACTION_RESOURCES__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int KEYBOARD__PLACE = HAPTICAL_INTERACTION_RESOURCES__PLACE;

    /**
     * The feature id for the '<em><b>User</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int KEYBOARD__USER = HAPTICAL_INTERACTION_RESOURCES__USER;

    /**
     * The feature id for the '<em><b>Device</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int KEYBOARD__DEVICE = HAPTICAL_INTERACTION_RESOURCES__DEVICE;

    /**
     * The feature id for the '<em><b>Available</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int KEYBOARD__AVAILABLE = HAPTICAL_INTERACTION_RESOURCES__AVAILABLE;

    /**
     * The feature id for the '<em><b>Modality</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int KEYBOARD__MODALITY = HAPTICAL_INTERACTION_RESOURCES__MODALITY;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int KEYBOARD__MOBILE = HAPTICAL_INTERACTION_RESOURCES__MOBILE;

    /**
     * The feature id for the '<em><b>Personal</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int KEYBOARD__PERSONAL = HAPTICAL_INTERACTION_RESOURCES__PERSONAL;

    /**
     * The feature id for the '<em><b>Interaction Status</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int KEYBOARD__INTERACTION_STATUS = HAPTICAL_INTERACTION_RESOURCES__INTERACTION_STATUS;

    /**
     * The feature id for the '<em><b>Interaction Status Time Stamp</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int KEYBOARD__INTERACTION_STATUS_TIME_STAMP = HAPTICAL_INTERACTION_RESOURCES__INTERACTION_STATUS_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Keys</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int KEYBOARD__KEYS = HAPTICAL_INTERACTION_RESOURCES_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Keyboard</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int KEYBOARD_FEATURE_COUNT = HAPTICAL_INTERACTION_RESOURCES_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTPUT_INTERACTION_RESOURCE__ID = INTERACTION_RESOURCE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTPUT_INTERACTION_RESOURCE__NAME = INTERACTION_RESOURCE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTPUT_INTERACTION_RESOURCE__TAGS = INTERACTION_RESOURCE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTPUT_INTERACTION_RESOURCE__POSITION = INTERACTION_RESOURCE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTPUT_INTERACTION_RESOURCE__POSITION_TIME_STAMP = INTERACTION_RESOURCE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTPUT_INTERACTION_RESOURCE__PLACE = INTERACTION_RESOURCE__PLACE;

    /**
     * The feature id for the '<em><b>User</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTPUT_INTERACTION_RESOURCE__USER = INTERACTION_RESOURCE__USER;

    /**
     * The feature id for the '<em><b>Device</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTPUT_INTERACTION_RESOURCE__DEVICE = INTERACTION_RESOURCE__DEVICE;

    /**
     * The feature id for the '<em><b>Available</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTPUT_INTERACTION_RESOURCE__AVAILABLE = INTERACTION_RESOURCE__AVAILABLE;

    /**
     * The feature id for the '<em><b>Modality</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTPUT_INTERACTION_RESOURCE__MODALITY = INTERACTION_RESOURCE__MODALITY;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTPUT_INTERACTION_RESOURCE__MOBILE = INTERACTION_RESOURCE__MOBILE;

    /**
     * The feature id for the '<em><b>Personal</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTPUT_INTERACTION_RESOURCE__PERSONAL = INTERACTION_RESOURCE__PERSONAL;

    /**
     * The feature id for the '<em><b>Interaction Status</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTPUT_INTERACTION_RESOURCE__INTERACTION_STATUS = INTERACTION_RESOURCE__INTERACTION_STATUS;

    /**
     * The feature id for the '<em><b>Interaction Status Time Stamp</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTPUT_INTERACTION_RESOURCE__INTERACTION_STATUS_TIME_STAMP = INTERACTION_RESOURCE__INTERACTION_STATUS_TIME_STAMP;

    /**
     * The number of structural features of the '
     * <em>Output Interaction Resource</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OUTPUT_INTERACTION_RESOURCE_FEATURE_COUNT = INTERACTION_RESOURCE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY__ID = OUTPUT_INTERACTION_RESOURCE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY__NAME = OUTPUT_INTERACTION_RESOURCE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY__TAGS = OUTPUT_INTERACTION_RESOURCE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY__POSITION = OUTPUT_INTERACTION_RESOURCE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY__POSITION_TIME_STAMP = OUTPUT_INTERACTION_RESOURCE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY__PLACE = OUTPUT_INTERACTION_RESOURCE__PLACE;

    /**
     * The feature id for the '<em><b>User</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY__USER = OUTPUT_INTERACTION_RESOURCE__USER;

    /**
     * The feature id for the '<em><b>Device</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY__DEVICE = OUTPUT_INTERACTION_RESOURCE__DEVICE;

    /**
     * The feature id for the '<em><b>Available</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY__AVAILABLE = OUTPUT_INTERACTION_RESOURCE__AVAILABLE;

    /**
     * The feature id for the '<em><b>Modality</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY__MODALITY = OUTPUT_INTERACTION_RESOURCE__MODALITY;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY__MOBILE = OUTPUT_INTERACTION_RESOURCE__MOBILE;

    /**
     * The feature id for the '<em><b>Personal</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY__PERSONAL = OUTPUT_INTERACTION_RESOURCE__PERSONAL;

    /**
     * The feature id for the '<em><b>Interaction Status</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY__INTERACTION_STATUS = OUTPUT_INTERACTION_RESOURCE__INTERACTION_STATUS;

    /**
     * The feature id for the '<em><b>Interaction Status Time Stamp</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY__INTERACTION_STATUS_TIME_STAMP = OUTPUT_INTERACTION_RESOURCE__INTERACTION_STATUS_TIME_STAMP;

    /**
     * The feature id for the '<em><b>XPixels</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY__XPIXELS = OUTPUT_INTERACTION_RESOURCE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>YPixels</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY__YPIXELS = OUTPUT_INTERACTION_RESOURCE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Direction</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY__DIRECTION = OUTPUT_INTERACTION_RESOURCE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Screen</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY__SCREEN = OUTPUT_INTERACTION_RESOURCE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY__SIZE = OUTPUT_INTERACTION_RESOURCE_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Display</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISPLAY_FEATURE_COUNT = OUTPUT_INTERACTION_RESOURCE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GESTURE_IR__ID = HAPTICAL_INTERACTION_RESOURCES__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GESTURE_IR__NAME = HAPTICAL_INTERACTION_RESOURCES__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GESTURE_IR__TAGS = HAPTICAL_INTERACTION_RESOURCES__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GESTURE_IR__POSITION = HAPTICAL_INTERACTION_RESOURCES__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GESTURE_IR__POSITION_TIME_STAMP = HAPTICAL_INTERACTION_RESOURCES__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GESTURE_IR__PLACE = HAPTICAL_INTERACTION_RESOURCES__PLACE;

    /**
     * The feature id for the '<em><b>User</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GESTURE_IR__USER = HAPTICAL_INTERACTION_RESOURCES__USER;

    /**
     * The feature id for the '<em><b>Device</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GESTURE_IR__DEVICE = HAPTICAL_INTERACTION_RESOURCES__DEVICE;

    /**
     * The feature id for the '<em><b>Available</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GESTURE_IR__AVAILABLE = HAPTICAL_INTERACTION_RESOURCES__AVAILABLE;

    /**
     * The feature id for the '<em><b>Modality</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GESTURE_IR__MODALITY = HAPTICAL_INTERACTION_RESOURCES__MODALITY;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GESTURE_IR__MOBILE = HAPTICAL_INTERACTION_RESOURCES__MOBILE;

    /**
     * The feature id for the '<em><b>Personal</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GESTURE_IR__PERSONAL = HAPTICAL_INTERACTION_RESOURCES__PERSONAL;

    /**
     * The feature id for the '<em><b>Interaction Status</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GESTURE_IR__INTERACTION_STATUS = HAPTICAL_INTERACTION_RESOURCES__INTERACTION_STATUS;

    /**
     * The feature id for the '<em><b>Interaction Status Time Stamp</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GESTURE_IR__INTERACTION_STATUS_TIME_STAMP = HAPTICAL_INTERACTION_RESOURCES__INTERACTION_STATUS_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Gesture Recognition</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GESTURE_IR__GESTURE_RECOGNITION = HAPTICAL_INTERACTION_RESOURCES_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Gesture IR</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GESTURE_IR_FEATURE_COUNT = HAPTICAL_INTERACTION_RESOURCES_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHPAD__ID = HAPTICAL_INTERACTION_RESOURCES__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHPAD__NAME = HAPTICAL_INTERACTION_RESOURCES__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHPAD__TAGS = HAPTICAL_INTERACTION_RESOURCES__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHPAD__POSITION = HAPTICAL_INTERACTION_RESOURCES__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHPAD__POSITION_TIME_STAMP = HAPTICAL_INTERACTION_RESOURCES__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHPAD__PLACE = HAPTICAL_INTERACTION_RESOURCES__PLACE;

    /**
     * The feature id for the '<em><b>User</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHPAD__USER = HAPTICAL_INTERACTION_RESOURCES__USER;

    /**
     * The feature id for the '<em><b>Device</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHPAD__DEVICE = HAPTICAL_INTERACTION_RESOURCES__DEVICE;

    /**
     * The feature id for the '<em><b>Available</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHPAD__AVAILABLE = HAPTICAL_INTERACTION_RESOURCES__AVAILABLE;

    /**
     * The feature id for the '<em><b>Modality</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHPAD__MODALITY = HAPTICAL_INTERACTION_RESOURCES__MODALITY;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHPAD__MOBILE = HAPTICAL_INTERACTION_RESOURCES__MOBILE;

    /**
     * The feature id for the '<em><b>Personal</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHPAD__PERSONAL = HAPTICAL_INTERACTION_RESOURCES__PERSONAL;

    /**
     * The feature id for the '<em><b>Interaction Status</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHPAD__INTERACTION_STATUS = HAPTICAL_INTERACTION_RESOURCES__INTERACTION_STATUS;

    /**
     * The feature id for the '<em><b>Interaction Status Time Stamp</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHPAD__INTERACTION_STATUS_TIME_STAMP = HAPTICAL_INTERACTION_RESOURCES__INTERACTION_STATUS_TIME_STAMP;

    /**
     * The number of structural features of the '<em>Touchpad</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHPAD_FEATURE_COUNT = HAPTICAL_INTERACTION_RESOURCES_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__ID = DISPLAY__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__NAME = DISPLAY__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__TAGS = DISPLAY__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__POSITION = DISPLAY__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__POSITION_TIME_STAMP = DISPLAY__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__PLACE = DISPLAY__PLACE;

    /**
     * The feature id for the '<em><b>User</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__USER = DISPLAY__USER;

    /**
     * The feature id for the '<em><b>Device</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__DEVICE = DISPLAY__DEVICE;

    /**
     * The feature id for the '<em><b>Available</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__AVAILABLE = DISPLAY__AVAILABLE;

    /**
     * The feature id for the '<em><b>Modality</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__MODALITY = DISPLAY__MODALITY;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__MOBILE = DISPLAY__MOBILE;

    /**
     * The feature id for the '<em><b>Personal</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__PERSONAL = DISPLAY__PERSONAL;

    /**
     * The feature id for the '<em><b>Interaction Status</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__INTERACTION_STATUS = DISPLAY__INTERACTION_STATUS;

    /**
     * The feature id for the '<em><b>Interaction Status Time Stamp</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__INTERACTION_STATUS_TIME_STAMP = DISPLAY__INTERACTION_STATUS_TIME_STAMP;

    /**
     * The feature id for the '<em><b>XPixels</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__XPIXELS = DISPLAY__XPIXELS;

    /**
     * The feature id for the '<em><b>YPixels</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__YPIXELS = DISPLAY__YPIXELS;

    /**
     * The feature id for the '<em><b>Direction</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__DIRECTION = DISPLAY__DIRECTION;

    /**
     * The feature id for the '<em><b>Screen</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__SCREEN = DISPLAY__SCREEN;

    /**
     * The feature id for the '<em><b>Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__SIZE = DISPLAY__SIZE;

    /**
     * The feature id for the '<em><b>Touch Surface</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__TOUCH_SURFACE = DISPLAY_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Gesture Recognition</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN__GESTURE_RECOGNITION = DISPLAY_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Touchscreen</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOUCHSCREEN_FEATURE_COUNT = DISPLAY_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEVICE__ID = ELEMENT_WITH_POSITION__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEVICE__NAME = ELEMENT_WITH_POSITION__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEVICE__TAGS = ELEMENT_WITH_POSITION__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEVICE__POSITION = ELEMENT_WITH_POSITION__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEVICE__POSITION_TIME_STAMP = ELEMENT_WITH_POSITION__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEVICE__PLACE = ELEMENT_WITH_POSITION__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEVICE__RESOURCES = ELEMENT_WITH_POSITION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEVICE__MOBILE = ELEMENT_WITH_POSITION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEVICE__ENVIRONMENT = ELEMENT_WITH_POSITION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEVICE__MANUFACTURER = ELEMENT_WITH_POSITION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEVICE__MODEL_NAME = ELEMENT_WITH_POSITION_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Device</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEVICE_FEATURE_COUNT = ELEMENT_WITH_POSITION_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.PhysicalDeviceImpl
     * <em>Physical Device</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.PhysicalDeviceImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getPhysicalDevice()
     * @generated
     */
    int PHYSICAL_DEVICE = 26;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE__ID = DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE__NAME = DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE__TAGS = DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE__POSITION = DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE__POSITION_TIME_STAMP = DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE__PLACE = DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE__RESOURCES = DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE__MOBILE = DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE__ENVIRONMENT = DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE__MANUFACTURER = DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE__MODEL_NAME = DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE__ON = DEVICE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE__POWER_USAGE = DEVICE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE__SUB_DEVICE = DEVICE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE__PARENT_DEVICE = DEVICE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Physical Device</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE_FEATURE_COUNT = DEVICE_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.PhysicalDeviceWithProgramImpl
     * <em>Physical Device With Program</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.PhysicalDeviceWithProgramImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getPhysicalDeviceWithProgram()
     * @generated
     */
    int PHYSICAL_DEVICE_WITH_PROGRAM = 27;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE_WITH_PROGRAM__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE_WITH_PROGRAM__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE_WITH_PROGRAM__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE_WITH_PROGRAM__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE_WITH_PROGRAM__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE_WITH_PROGRAM__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE_WITH_PROGRAM__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE_WITH_PROGRAM__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE_WITH_PROGRAM__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE_WITH_PROGRAM__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE_WITH_PROGRAM__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE_WITH_PROGRAM__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE_WITH_PROGRAM__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE_WITH_PROGRAM__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE_WITH_PROGRAM__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The feature id for the '<em><b>Program</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE_WITH_PROGRAM__PROGRAM = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Seconds Remaining</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE_WITH_PROGRAM__SECONDS_REMAINING = PHYSICAL_DEVICE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>Physical Device With Program</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_DEVICE_WITH_PROGRAM_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.LampImpl <em>Lamp</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.LampImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getLamp()
     * @generated
     */
    int LAMP = 28;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAMP__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAMP__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAMP__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAMP__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAMP__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAMP__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAMP__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAMP__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAMP__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAMP__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAMP__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAMP__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAMP__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAMP__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAMP__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The feature id for the '<em><b>Dimming Level</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAMP__DIMMING_LEVEL = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Lamp Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAMP__LAMP_TYPE = PHYSICAL_DEVICE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Lamp</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAMP_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.NotebookImpl
     * <em>Notebook</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.NotebookImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getNotebook()
     * @generated
     */
    int NOTEBOOK = 29;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTEBOOK__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTEBOOK__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTEBOOK__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTEBOOK__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTEBOOK__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTEBOOK__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTEBOOK__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTEBOOK__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTEBOOK__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTEBOOK__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTEBOOK__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTEBOOK__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTEBOOK__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTEBOOK__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTEBOOK__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The number of structural features of the '<em>Notebook</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTEBOOK_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.HoodImpl <em>Hood</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.HoodImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getHood()
     * @generated
     */
    int HOOD = 30;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOOD__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOOD__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOOD__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOOD__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOOD__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOOD__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOOD__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOOD__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOOD__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOOD__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOOD__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOOD__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOOD__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOOD__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOOD__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The number of structural features of the '<em>Hood</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOOD_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.PCImpl <em>PC</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.PCImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getPC()
     * @generated
     */
    int PC = 31;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PC__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PC__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PC__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PC__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PC__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PC__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PC__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PC__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PC__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PC__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PC__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PC__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PC__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PC__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PC__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The number of structural features of the '<em>PC</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PC_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.FanImpl <em>Fan</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.FanImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getFan()
     * @generated
     */
    int FAN = 32;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FAN__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FAN__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FAN__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FAN__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FAN__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FAN__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FAN__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FAN__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FAN__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FAN__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FAN__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FAN__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FAN__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FAN__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FAN__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The feature id for the '<em><b>Speed</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FAN__SPEED = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Fan</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FAN_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.RemoteControlImpl
     * <em>Remote Control</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.RemoteControlImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getRemoteControl()
     * @generated
     */
    int REMOTE_CONTROL = 33;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOTE_CONTROL__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOTE_CONTROL__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOTE_CONTROL__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOTE_CONTROL__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOTE_CONTROL__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOTE_CONTROL__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOTE_CONTROL__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOTE_CONTROL__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOTE_CONTROL__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOTE_CONTROL__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOTE_CONTROL__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOTE_CONTROL__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOTE_CONTROL__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOTE_CONTROL__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOTE_CONTROL__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The number of structural features of the '<em>Remote Control</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOTE_CONTROL_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.TVImpl <em>TV</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.TVImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getTV()
     * @generated
     */
    int TV = 34;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TV__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TV__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TV__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TV__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TV__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TV__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TV__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TV__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TV__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TV__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TV__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TV__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TV__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TV__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TV__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The feature id for the '<em><b>Current Program</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TV__CURRENT_PROGRAM = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>TV</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TV_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.OvenImpl <em>Oven</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.OvenImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getOven()
     * @generated
     */
    int OVEN = 35;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OVEN__ID = PHYSICAL_DEVICE_WITH_PROGRAM__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OVEN__NAME = PHYSICAL_DEVICE_WITH_PROGRAM__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OVEN__TAGS = PHYSICAL_DEVICE_WITH_PROGRAM__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OVEN__POSITION = PHYSICAL_DEVICE_WITH_PROGRAM__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OVEN__POSITION_TIME_STAMP = PHYSICAL_DEVICE_WITH_PROGRAM__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OVEN__PLACE = PHYSICAL_DEVICE_WITH_PROGRAM__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OVEN__RESOURCES = PHYSICAL_DEVICE_WITH_PROGRAM__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OVEN__MOBILE = PHYSICAL_DEVICE_WITH_PROGRAM__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OVEN__ENVIRONMENT = PHYSICAL_DEVICE_WITH_PROGRAM__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OVEN__MANUFACTURER = PHYSICAL_DEVICE_WITH_PROGRAM__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OVEN__MODEL_NAME = PHYSICAL_DEVICE_WITH_PROGRAM__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OVEN__ON = PHYSICAL_DEVICE_WITH_PROGRAM__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OVEN__POWER_USAGE = PHYSICAL_DEVICE_WITH_PROGRAM__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OVEN__SUB_DEVICE = PHYSICAL_DEVICE_WITH_PROGRAM__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OVEN__PARENT_DEVICE = PHYSICAL_DEVICE_WITH_PROGRAM__PARENT_DEVICE;

    /**
     * The feature id for the '<em><b>Program</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OVEN__PROGRAM = PHYSICAL_DEVICE_WITH_PROGRAM__PROGRAM;

    /**
     * The feature id for the '<em><b>Seconds Remaining</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OVEN__SECONDS_REMAINING = PHYSICAL_DEVICE_WITH_PROGRAM__SECONDS_REMAINING;

    /**
     * The feature id for the '<em><b>Temperature</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OVEN__TEMPERATURE = PHYSICAL_DEVICE_WITH_PROGRAM_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Oven</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OVEN_FEATURE_COUNT = PHYSICAL_DEVICE_WITH_PROGRAM_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.DishwasherImpl
     * <em>Dishwasher</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.sercho.masp.models.Context.impl.DishwasherImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getDishwasher()
     * @generated
     */
    int DISHWASHER = 36;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISHWASHER__ID = PHYSICAL_DEVICE_WITH_PROGRAM__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISHWASHER__NAME = PHYSICAL_DEVICE_WITH_PROGRAM__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISHWASHER__TAGS = PHYSICAL_DEVICE_WITH_PROGRAM__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISHWASHER__POSITION = PHYSICAL_DEVICE_WITH_PROGRAM__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISHWASHER__POSITION_TIME_STAMP = PHYSICAL_DEVICE_WITH_PROGRAM__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISHWASHER__PLACE = PHYSICAL_DEVICE_WITH_PROGRAM__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISHWASHER__RESOURCES = PHYSICAL_DEVICE_WITH_PROGRAM__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISHWASHER__MOBILE = PHYSICAL_DEVICE_WITH_PROGRAM__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISHWASHER__ENVIRONMENT = PHYSICAL_DEVICE_WITH_PROGRAM__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISHWASHER__MANUFACTURER = PHYSICAL_DEVICE_WITH_PROGRAM__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISHWASHER__MODEL_NAME = PHYSICAL_DEVICE_WITH_PROGRAM__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISHWASHER__ON = PHYSICAL_DEVICE_WITH_PROGRAM__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISHWASHER__POWER_USAGE = PHYSICAL_DEVICE_WITH_PROGRAM__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISHWASHER__SUB_DEVICE = PHYSICAL_DEVICE_WITH_PROGRAM__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISHWASHER__PARENT_DEVICE = PHYSICAL_DEVICE_WITH_PROGRAM__PARENT_DEVICE;

    /**
     * The feature id for the '<em><b>Program</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISHWASHER__PROGRAM = PHYSICAL_DEVICE_WITH_PROGRAM__PROGRAM;

    /**
     * The feature id for the '<em><b>Seconds Remaining</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISHWASHER__SECONDS_REMAINING = PHYSICAL_DEVICE_WITH_PROGRAM__SECONDS_REMAINING;

    /**
     * The number of structural features of the '<em>Dishwasher</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DISHWASHER_FEATURE_COUNT = PHYSICAL_DEVICE_WITH_PROGRAM_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.BlindImpl <em>Blind</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.BlindImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getBlind()
     * @generated
     */
    int BLIND = 38;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.HobImpl <em>Hob</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.HobImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getHob()
     * @generated
     */
    int HOB = 39;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.CookerImpl <em>Cooker</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.CookerImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getCooker()
     * @generated
     */
    int COOKER = 40;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.CookTopImpl <em>Cook Top</em>}
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.CookTopImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getCookTop()
     * @generated
     */
    int COOK_TOP = 41;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.FridgeImpl <em>Fridge</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.FridgeImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getFridge()
     * @generated
     */
    int FRIDGE = 42;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.MixerImpl <em>Mixer</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.MixerImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getMixer()
     * @generated
     */
    int MIXER = 43;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.RadioImpl <em>Radio</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.RadioImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getRadio()
     * @generated
     */
    int RADIO = 44;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.SocketImpl <em>Socket</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.SocketImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getSocket()
     * @generated
     */
    int SOCKET = 45;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.HeaterImpl <em>Heater</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.HeaterImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getHeater()
     * @generated
     */
    int HEATER = 46;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.WashingMachineImpl
     * <em>Washing Machine</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.WashingMachineImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getWashingMachine()
     * @generated
     */
    int WASHING_MACHINE = 37;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WASHING_MACHINE__ID = PHYSICAL_DEVICE_WITH_PROGRAM__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WASHING_MACHINE__NAME = PHYSICAL_DEVICE_WITH_PROGRAM__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WASHING_MACHINE__TAGS = PHYSICAL_DEVICE_WITH_PROGRAM__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WASHING_MACHINE__POSITION = PHYSICAL_DEVICE_WITH_PROGRAM__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WASHING_MACHINE__POSITION_TIME_STAMP = PHYSICAL_DEVICE_WITH_PROGRAM__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WASHING_MACHINE__PLACE = PHYSICAL_DEVICE_WITH_PROGRAM__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WASHING_MACHINE__RESOURCES = PHYSICAL_DEVICE_WITH_PROGRAM__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WASHING_MACHINE__MOBILE = PHYSICAL_DEVICE_WITH_PROGRAM__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WASHING_MACHINE__ENVIRONMENT = PHYSICAL_DEVICE_WITH_PROGRAM__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WASHING_MACHINE__MANUFACTURER = PHYSICAL_DEVICE_WITH_PROGRAM__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WASHING_MACHINE__MODEL_NAME = PHYSICAL_DEVICE_WITH_PROGRAM__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WASHING_MACHINE__ON = PHYSICAL_DEVICE_WITH_PROGRAM__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WASHING_MACHINE__POWER_USAGE = PHYSICAL_DEVICE_WITH_PROGRAM__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WASHING_MACHINE__SUB_DEVICE = PHYSICAL_DEVICE_WITH_PROGRAM__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WASHING_MACHINE__PARENT_DEVICE = PHYSICAL_DEVICE_WITH_PROGRAM__PARENT_DEVICE;

    /**
     * The feature id for the '<em><b>Program</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WASHING_MACHINE__PROGRAM = PHYSICAL_DEVICE_WITH_PROGRAM__PROGRAM;

    /**
     * The feature id for the '<em><b>Seconds Remaining</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WASHING_MACHINE__SECONDS_REMAINING = PHYSICAL_DEVICE_WITH_PROGRAM__SECONDS_REMAINING;

    /**
     * The number of structural features of the '<em>Washing Machine</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WASHING_MACHINE_FEATURE_COUNT = PHYSICAL_DEVICE_WITH_PROGRAM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLIND__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLIND__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLIND__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLIND__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLIND__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLIND__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLIND__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLIND__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLIND__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLIND__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLIND__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLIND__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLIND__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLIND__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLIND__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The feature id for the '<em><b>Level</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLIND__LEVEL = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Blind</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLIND_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOB__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOB__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOB__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOB__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOB__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOB__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOB__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOB__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOB__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOB__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOB__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOB__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOB__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOB__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOB__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The feature id for the '<em><b>Heat Level</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOB__HEAT_LEVEL = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Hob</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HOB_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOKER__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOKER__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOKER__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOKER__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOKER__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOKER__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOKER__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOKER__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOKER__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOKER__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOKER__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOKER__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOKER__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOKER__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOKER__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The number of structural features of the '<em>Cooker</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOKER_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOK_TOP__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOK_TOP__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOK_TOP__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOK_TOP__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOK_TOP__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOK_TOP__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOK_TOP__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOK_TOP__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOK_TOP__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOK_TOP__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOK_TOP__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOK_TOP__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOK_TOP__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOK_TOP__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOK_TOP__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The number of structural features of the '<em>Cook Top</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COOK_TOP_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRIDGE__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRIDGE__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRIDGE__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRIDGE__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRIDGE__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRIDGE__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRIDGE__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRIDGE__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRIDGE__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRIDGE__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRIDGE__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRIDGE__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRIDGE__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRIDGE__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRIDGE__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The feature id for the '<em><b>Temperature</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRIDGE__TEMPERATURE = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Fridge</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRIDGE_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MIXER__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MIXER__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MIXER__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MIXER__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MIXER__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MIXER__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MIXER__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MIXER__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MIXER__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MIXER__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MIXER__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MIXER__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MIXER__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MIXER__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MIXER__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The number of structural features of the '<em>Mixer</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MIXER_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The number of structural features of the '<em>Radio</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOCKET__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOCKET__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOCKET__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOCKET__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOCKET__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOCKET__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOCKET__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOCKET__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOCKET__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOCKET__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOCKET__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOCKET__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOCKET__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOCKET__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOCKET__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The number of structural features of the '<em>Socket</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOCKET_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATER__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATER__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATER__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATER__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATER__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATER__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATER__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATER__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATER__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATER__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATER__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATER__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATER__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATER__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATER__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The feature id for the '<em><b>Temperature Demand</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATER__TEMPERATURE_DEMAND = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Temperature Current</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATER__TEMPERATURE_CURRENT = PHYSICAL_DEVICE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Valve Position</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATER__VALVE_POSITION = PHYSICAL_DEVICE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Heater</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATER_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.MeterImpl <em>Meter</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.MeterImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getMeter()
     * @generated
     */
    int METER = 47;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The feature id for the '<em><b>Readings</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER__READINGS = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Meter</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.WaterStorageTankImpl
     * <em>Water Storage Tank</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.WaterStorageTankImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getWaterStorageTank()
     * @generated
     */
    int WATER_STORAGE_TANK = 48;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WATER_STORAGE_TANK__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WATER_STORAGE_TANK__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WATER_STORAGE_TANK__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WATER_STORAGE_TANK__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WATER_STORAGE_TANK__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WATER_STORAGE_TANK__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WATER_STORAGE_TANK__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WATER_STORAGE_TANK__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WATER_STORAGE_TANK__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WATER_STORAGE_TANK__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WATER_STORAGE_TANK__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WATER_STORAGE_TANK__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WATER_STORAGE_TANK__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WATER_STORAGE_TANK__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WATER_STORAGE_TANK__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The feature id for the '<em><b>Temperature Sensor</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WATER_STORAGE_TANK__TEMPERATURE_SENSOR = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Capacity</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WATER_STORAGE_TANK__CAPACITY = PHYSICAL_DEVICE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Water Storage Tank</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WATER_STORAGE_TANK_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.HeatingRodImpl
     * <em>Heating Rod</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.sercho.masp.models.Context.impl.HeatingRodImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getHeatingRod()
     * @generated
     */
    int HEATING_ROD = 49;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATING_ROD__ID = PHYSICAL_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATING_ROD__NAME = PHYSICAL_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATING_ROD__TAGS = PHYSICAL_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATING_ROD__POSITION = PHYSICAL_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATING_ROD__POSITION_TIME_STAMP = PHYSICAL_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATING_ROD__PLACE = PHYSICAL_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATING_ROD__RESOURCES = PHYSICAL_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATING_ROD__MOBILE = PHYSICAL_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATING_ROD__ENVIRONMENT = PHYSICAL_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATING_ROD__MANUFACTURER = PHYSICAL_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATING_ROD__MODEL_NAME = PHYSICAL_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATING_ROD__ON = PHYSICAL_DEVICE__ON;

    /**
     * The feature id for the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATING_ROD__POWER_USAGE = PHYSICAL_DEVICE__POWER_USAGE;

    /**
     * The feature id for the '<em><b>Sub Device</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATING_ROD__SUB_DEVICE = PHYSICAL_DEVICE__SUB_DEVICE;

    /**
     * The feature id for the '<em><b>Parent Device</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATING_ROD__PARENT_DEVICE = PHYSICAL_DEVICE__PARENT_DEVICE;

    /**
     * The feature id for the '<em><b>Maximum Power Watts</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATING_ROD__MAXIMUM_POWER_WATTS = PHYSICAL_DEVICE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Heating Rod</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HEATING_ROD_FEATURE_COUNT = PHYSICAL_DEVICE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.MeterReadingImpl
     * <em>Meter Reading</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.sercho.masp.models.Context.impl.MeterReadingImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getMeterReading()
     * @generated
     */
    int METER_READING = 50;

    /**
     * The feature id for the '<em><b>Value</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER_READING__VALUE = 0;

    /**
     * The feature id for the '<em><b>Subject</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER_READING__SUBJECT = 1;

    /**
     * The feature id for the '<em><b>Measurand</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER_READING__MEASURAND = 2;

    /**
     * The feature id for the '<em><b>Measurement Method</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER_READING__MEASUREMENT_METHOD = 3;

    /**
     * The feature id for the '<em><b>Rate</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER_READING__RATE = 4;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER_READING__IDENTIFIER = 5;

    /**
     * The number of structural features of the '<em>Meter Reading</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METER_READING_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.PhysicalSensorDeviceImpl
     * <em>Physical Sensor Device</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.PhysicalSensorDeviceImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getPhysicalSensorDevice()
     * @generated
     */
    int PHYSICAL_SENSOR_DEVICE = 51;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_SENSOR_DEVICE__ID = DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_SENSOR_DEVICE__NAME = DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_SENSOR_DEVICE__TAGS = DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_SENSOR_DEVICE__POSITION = DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_SENSOR_DEVICE__POSITION_TIME_STAMP = DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_SENSOR_DEVICE__PLACE = DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_SENSOR_DEVICE__RESOURCES = DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_SENSOR_DEVICE__MOBILE = DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_SENSOR_DEVICE__ENVIRONMENT = DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_SENSOR_DEVICE__MANUFACTURER = DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_SENSOR_DEVICE__MODEL_NAME = DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>Value</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_SENSOR_DEVICE__VALUE = DEVICE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Physical Sensor Device</em>
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PHYSICAL_SENSOR_DEVICE_FEATURE_COUNT = DEVICE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.LuminanceSensorImpl
     * <em>Luminance Sensor</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.LuminanceSensorImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getLuminanceSensor()
     * @generated
     */
    int LUMINANCE_SENSOR = 52;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LUMINANCE_SENSOR__ID = PHYSICAL_SENSOR_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LUMINANCE_SENSOR__NAME = PHYSICAL_SENSOR_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LUMINANCE_SENSOR__TAGS = PHYSICAL_SENSOR_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LUMINANCE_SENSOR__POSITION = PHYSICAL_SENSOR_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LUMINANCE_SENSOR__POSITION_TIME_STAMP = PHYSICAL_SENSOR_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LUMINANCE_SENSOR__PLACE = PHYSICAL_SENSOR_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LUMINANCE_SENSOR__RESOURCES = PHYSICAL_SENSOR_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LUMINANCE_SENSOR__MOBILE = PHYSICAL_SENSOR_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LUMINANCE_SENSOR__ENVIRONMENT = PHYSICAL_SENSOR_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LUMINANCE_SENSOR__MANUFACTURER = PHYSICAL_SENSOR_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LUMINANCE_SENSOR__MODEL_NAME = PHYSICAL_SENSOR_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>Value</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LUMINANCE_SENSOR__VALUE = PHYSICAL_SENSOR_DEVICE__VALUE;

    /**
     * The number of structural features of the '<em>Luminance Sensor</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LUMINANCE_SENSOR_FEATURE_COUNT = PHYSICAL_SENSOR_DEVICE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.TemperatureSensorImpl
     * <em>Temperature Sensor</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.TemperatureSensorImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getTemperatureSensor()
     * @generated
     */
    int TEMPERATURE_SENSOR = 53;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPERATURE_SENSOR__ID = PHYSICAL_SENSOR_DEVICE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPERATURE_SENSOR__NAME = PHYSICAL_SENSOR_DEVICE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPERATURE_SENSOR__TAGS = PHYSICAL_SENSOR_DEVICE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPERATURE_SENSOR__POSITION = PHYSICAL_SENSOR_DEVICE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPERATURE_SENSOR__POSITION_TIME_STAMP = PHYSICAL_SENSOR_DEVICE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPERATURE_SENSOR__PLACE = PHYSICAL_SENSOR_DEVICE__PLACE;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPERATURE_SENSOR__RESOURCES = PHYSICAL_SENSOR_DEVICE__RESOURCES;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPERATURE_SENSOR__MOBILE = PHYSICAL_SENSOR_DEVICE__MOBILE;

    /**
     * The feature id for the '<em><b>Environment</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPERATURE_SENSOR__ENVIRONMENT = PHYSICAL_SENSOR_DEVICE__ENVIRONMENT;

    /**
     * The feature id for the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPERATURE_SENSOR__MANUFACTURER = PHYSICAL_SENSOR_DEVICE__MANUFACTURER;

    /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPERATURE_SENSOR__MODEL_NAME = PHYSICAL_SENSOR_DEVICE__MODEL_NAME;

    /**
     * The feature id for the '<em><b>Value</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPERATURE_SENSOR__VALUE = PHYSICAL_SENSOR_DEVICE__VALUE;

    /**
     * The number of structural features of the '<em>Temperature Sensor</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPERATURE_SENSOR_FEATURE_COUNT = PHYSICAL_SENSOR_DEVICE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHANNEL__ID = ENVIRONMENT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHANNEL__NAME = ENVIRONMENT_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Distribution State</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHANNEL__DISTRIBUTION_STATE = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Elements</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHANNEL__ELEMENTS = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Api</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHANNEL__API = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Configuration</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHANNEL__CONFIGURATION = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Available</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHANNEL__AVAILABLE = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Api Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHANNEL__API_CLASS = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Channel</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHANNEL_FEATURE_COUNT = ENVIRONMENT_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_INPUT_CHANNEL__ID = CHANNEL__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_INPUT_CHANNEL__NAME = CHANNEL__NAME;

    /**
     * The feature id for the '<em><b>Distribution State</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_INPUT_CHANNEL__DISTRIBUTION_STATE = CHANNEL__DISTRIBUTION_STATE;

    /**
     * The feature id for the '<em><b>Elements</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_INPUT_CHANNEL__ELEMENTS = CHANNEL__ELEMENTS;

    /**
     * The feature id for the '<em><b>Api</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_INPUT_CHANNEL__API = CHANNEL__API;

    /**
     * The feature id for the '<em><b>Configuration</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_INPUT_CHANNEL__CONFIGURATION = CHANNEL__CONFIGURATION;

    /**
     * The feature id for the '<em><b>Available</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_INPUT_CHANNEL__AVAILABLE = CHANNEL__AVAILABLE;

    /**
     * The feature id for the '<em><b>Api Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_INPUT_CHANNEL__API_CLASS = CHANNEL__API_CLASS;

    /**
     * The feature id for the '<em><b>Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_INPUT_CHANNEL__POSITION = CHANNEL_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Length</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_INPUT_CHANNEL__LENGTH = CHANNEL_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Message Input Channel</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_INPUT_CHANNEL_FEATURE_COUNT = CHANNEL_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_OUTPUT_CHANNEL__ID = CHANNEL__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_OUTPUT_CHANNEL__NAME = CHANNEL__NAME;

    /**
     * The feature id for the '<em><b>Distribution State</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_OUTPUT_CHANNEL__DISTRIBUTION_STATE = CHANNEL__DISTRIBUTION_STATE;

    /**
     * The feature id for the '<em><b>Elements</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_OUTPUT_CHANNEL__ELEMENTS = CHANNEL__ELEMENTS;

    /**
     * The feature id for the '<em><b>Api</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_OUTPUT_CHANNEL__API = CHANNEL__API;

    /**
     * The feature id for the '<em><b>Configuration</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_OUTPUT_CHANNEL__CONFIGURATION = CHANNEL__CONFIGURATION;

    /**
     * The feature id for the '<em><b>Available</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_OUTPUT_CHANNEL__AVAILABLE = CHANNEL__AVAILABLE;

    /**
     * The feature id for the '<em><b>Api Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_OUTPUT_CHANNEL__API_CLASS = CHANNEL__API_CLASS;

    /**
     * The feature id for the '<em><b>Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_OUTPUT_CHANNEL__POSITION = CHANNEL_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Length</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_OUTPUT_CHANNEL__LENGTH = CHANNEL_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Message Output Channel</em>
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_OUTPUT_CHANNEL_FEATURE_COUNT = CHANNEL_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POINTING_INPUT_CHANNEL__ID = CHANNEL__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POINTING_INPUT_CHANNEL__NAME = CHANNEL__NAME;

    /**
     * The feature id for the '<em><b>Distribution State</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POINTING_INPUT_CHANNEL__DISTRIBUTION_STATE = CHANNEL__DISTRIBUTION_STATE;

    /**
     * The feature id for the '<em><b>Elements</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POINTING_INPUT_CHANNEL__ELEMENTS = CHANNEL__ELEMENTS;

    /**
     * The feature id for the '<em><b>Api</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POINTING_INPUT_CHANNEL__API = CHANNEL__API;

    /**
     * The feature id for the '<em><b>Configuration</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POINTING_INPUT_CHANNEL__CONFIGURATION = CHANNEL__CONFIGURATION;

    /**
     * The feature id for the '<em><b>Available</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POINTING_INPUT_CHANNEL__AVAILABLE = CHANNEL__AVAILABLE;

    /**
     * The feature id for the '<em><b>Api Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POINTING_INPUT_CHANNEL__API_CLASS = CHANNEL__API_CLASS;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POINTING_INPUT_CHANNEL__WIDTH = CHANNEL_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POINTING_INPUT_CHANNEL__HEIGHT = CHANNEL_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POINTING_INPUT_CHANNEL__X = CHANNEL_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POINTING_INPUT_CHANNEL__Y = CHANNEL_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Z</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POINTING_INPUT_CHANNEL__Z = CHANNEL_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Pointing Input Channel</em>
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POINTING_INPUT_CHANNEL_FEATURE_COUNT = CHANNEL_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRAPHICAL_OUTPUT_CHANNEL__ID = CHANNEL__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRAPHICAL_OUTPUT_CHANNEL__NAME = CHANNEL__NAME;

    /**
     * The feature id for the '<em><b>Distribution State</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRAPHICAL_OUTPUT_CHANNEL__DISTRIBUTION_STATE = CHANNEL__DISTRIBUTION_STATE;

    /**
     * The feature id for the '<em><b>Elements</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRAPHICAL_OUTPUT_CHANNEL__ELEMENTS = CHANNEL__ELEMENTS;

    /**
     * The feature id for the '<em><b>Api</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRAPHICAL_OUTPUT_CHANNEL__API = CHANNEL__API;

    /**
     * The feature id for the '<em><b>Configuration</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRAPHICAL_OUTPUT_CHANNEL__CONFIGURATION = CHANNEL__CONFIGURATION;

    /**
     * The feature id for the '<em><b>Available</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRAPHICAL_OUTPUT_CHANNEL__AVAILABLE = CHANNEL__AVAILABLE;

    /**
     * The feature id for the '<em><b>Api Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRAPHICAL_OUTPUT_CHANNEL__API_CLASS = CHANNEL__API_CLASS;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRAPHICAL_OUTPUT_CHANNEL__WIDTH = CHANNEL_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRAPHICAL_OUTPUT_CHANNEL__HEIGHT = CHANNEL_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRAPHICAL_OUTPUT_CHANNEL__X = CHANNEL_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRAPHICAL_OUTPUT_CHANNEL__Y = CHANNEL_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Z</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRAPHICAL_OUTPUT_CHANNEL__Z = CHANNEL_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '
     * <em>Graphical Output Channel</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRAPHICAL_OUTPUT_CHANNEL_FEATURE_COUNT = CHANNEL_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOUDSPEAKER__ID = OUTPUT_INTERACTION_RESOURCE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOUDSPEAKER__NAME = OUTPUT_INTERACTION_RESOURCE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOUDSPEAKER__TAGS = OUTPUT_INTERACTION_RESOURCE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOUDSPEAKER__POSITION = OUTPUT_INTERACTION_RESOURCE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOUDSPEAKER__POSITION_TIME_STAMP = OUTPUT_INTERACTION_RESOURCE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOUDSPEAKER__PLACE = OUTPUT_INTERACTION_RESOURCE__PLACE;

    /**
     * The feature id for the '<em><b>User</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOUDSPEAKER__USER = OUTPUT_INTERACTION_RESOURCE__USER;

    /**
     * The feature id for the '<em><b>Device</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOUDSPEAKER__DEVICE = OUTPUT_INTERACTION_RESOURCE__DEVICE;

    /**
     * The feature id for the '<em><b>Available</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOUDSPEAKER__AVAILABLE = OUTPUT_INTERACTION_RESOURCE__AVAILABLE;

    /**
     * The feature id for the '<em><b>Modality</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOUDSPEAKER__MODALITY = OUTPUT_INTERACTION_RESOURCE__MODALITY;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOUDSPEAKER__MOBILE = OUTPUT_INTERACTION_RESOURCE__MOBILE;

    /**
     * The feature id for the '<em><b>Personal</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOUDSPEAKER__PERSONAL = OUTPUT_INTERACTION_RESOURCE__PERSONAL;

    /**
     * The feature id for the '<em><b>Interaction Status</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOUDSPEAKER__INTERACTION_STATUS = OUTPUT_INTERACTION_RESOURCE__INTERACTION_STATUS;

    /**
     * The feature id for the '<em><b>Interaction Status Time Stamp</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOUDSPEAKER__INTERACTION_STATUS_TIME_STAMP = OUTPUT_INTERACTION_RESOURCE__INTERACTION_STATUS_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Voice</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOUDSPEAKER__VOICE = OUTPUT_INTERACTION_RESOURCE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Loudspeaker</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOUDSPEAKER_FEATURE_COUNT = OUTPUT_INTERACTION_RESOURCE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MICROPHONE__ID = INPUT_INTERACTION_RESOURCE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MICROPHONE__NAME = INPUT_INTERACTION_RESOURCE__NAME;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MICROPHONE__TAGS = INPUT_INTERACTION_RESOURCE__TAGS;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MICROPHONE__POSITION = INPUT_INTERACTION_RESOURCE__POSITION;

    /**
     * The feature id for the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MICROPHONE__POSITION_TIME_STAMP = INPUT_INTERACTION_RESOURCE__POSITION_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MICROPHONE__PLACE = INPUT_INTERACTION_RESOURCE__PLACE;

    /**
     * The feature id for the '<em><b>User</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MICROPHONE__USER = INPUT_INTERACTION_RESOURCE__USER;

    /**
     * The feature id for the '<em><b>Device</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MICROPHONE__DEVICE = INPUT_INTERACTION_RESOURCE__DEVICE;

    /**
     * The feature id for the '<em><b>Available</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MICROPHONE__AVAILABLE = INPUT_INTERACTION_RESOURCE__AVAILABLE;

    /**
     * The feature id for the '<em><b>Modality</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MICROPHONE__MODALITY = INPUT_INTERACTION_RESOURCE__MODALITY;

    /**
     * The feature id for the '<em><b>Mobile</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MICROPHONE__MOBILE = INPUT_INTERACTION_RESOURCE__MOBILE;

    /**
     * The feature id for the '<em><b>Personal</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MICROPHONE__PERSONAL = INPUT_INTERACTION_RESOURCE__PERSONAL;

    /**
     * The feature id for the '<em><b>Interaction Status</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MICROPHONE__INTERACTION_STATUS = INPUT_INTERACTION_RESOURCE__INTERACTION_STATUS;

    /**
     * The feature id for the '<em><b>Interaction Status Time Stamp</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MICROPHONE__INTERACTION_STATUS_TIME_STAMP = INPUT_INTERACTION_RESOURCE__INTERACTION_STATUS_TIME_STAMP;

    /**
     * The feature id for the '<em><b>Voice Recognition</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MICROPHONE__VOICE_RECOGNITION = INPUT_INTERACTION_RESOURCE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Microphone</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MICROPHONE_FEATURE_COUNT = INPUT_INTERACTION_RESOURCE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VECTOR__X = 0;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VECTOR__Y = 1;

    /**
     * The feature id for the '<em><b>Z</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VECTOR__Z = 2;

    /**
     * The number of structural features of the '<em>Vector</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VECTOR_FEATURE_COUNT = 3;

    /**
     * The feature id for the '<em><b>Origin</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int AREA__ORIGIN = 0;

    /**
     * The feature id for the '<em><b>Span</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int AREA__SPAN = 1;

    /**
     * The number of structural features of the '<em>Area</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int AREA_FEATURE_COUNT = 2;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONFIGURATION_PROPERTY__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONFIGURATION_PROPERTY__VALUE = 1;

    /**
     * The number of structural features of the '<em>Configuration Property</em>
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONFIGURATION_PROPERTY_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.AssistantConnectionImpl
     * <em>Assistant Connection</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.AssistantConnectionImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getAssistantConnection()
     * @generated
     */
    int ASSISTANT_CONNECTION = 65;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ASSISTANT_CONNECTION__DESCRIPTION = 0;

    /**
     * The feature id for the '<em><b>Connected Assistant</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ASSISTANT_CONNECTION__CONNECTED_ASSISTANT = 1;

    /**
     * The number of structural features of the '<em>Assistant Connection</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ASSISTANT_CONNECTION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.ToolImpl <em>Tool</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.impl.ToolImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getTool()
     * @generated
     */
    int TOOL = 66;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL__NAME = 0;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL__DESCRIPTION = 1;

    /**
     * The number of structural features of the '<em>Tool</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.impl.DeviceToolImpl
     * <em>Device Tool</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.sercho.masp.models.Context.impl.DeviceToolImpl
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getDeviceTool()
     * @generated
     */
    int DEVICE_TOOL = 67;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEVICE_TOOL__NAME = TOOL__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEVICE_TOOL__DESCRIPTION = TOOL__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Controls</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEVICE_TOOL__CONTROLS = TOOL_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Applies To</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEVICE_TOOL__APPLIES_TO = TOOL_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Device Tool</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEVICE_TOOL_FEATURE_COUNT = TOOL_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.sercho.masp.models.Context.Need
     * <em>Need</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.Need
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getNeed()
     * @generated
     */
    int NEED = 68;

    /**
     * The feature id for the '<em><b>Fulfilled By</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NEED__FULFILLED_BY = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NEED__NAME = 1;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NEED__DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Configuration</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NEED__CONFIGURATION = 3;

    /**
     * The number of structural features of the '<em>Need</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NEED_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.DistributionState
     * <em>Distribution State</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.DistributionState
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getDistributionState()
     * @generated
     */
    int DISTRIBUTION_STATE = 74;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.Modality <em>Modality</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.Modality
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getModality()
     * @generated
     */
    int MODALITY = 75;

    /**
     * The meta object id for the '{@link org.sercho.masp.models.Context.Domain
     * <em>Domain</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.Domain
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getDomain()
     * @generated
     */
    int DOMAIN = 76;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.LampType <em>Lamp Type</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.LampType
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getLampType()
     * @generated
     */
    int LAMP_TYPE = 77;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.RoomType <em>Room Type</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.RoomType
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getRoomType()
     * @generated
     */
    int ROOM_TYPE = 69;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.ReadingSubject
     * <em>Reading Subject</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.ReadingSubject
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getReadingSubject()
     * @generated
     */
    int READING_SUBJECT = 70;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.ReadingMeasurand
     * <em>Reading Measurand</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.ReadingMeasurand
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getReadingMeasurand()
     * @generated
     */
    int READING_MEASURAND = 71;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.ReadingMeasurementMethod
     * <em>Reading Measurement Method</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.ReadingMeasurementMethod
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getReadingMeasurementMethod()
     * @generated
     */
    int READING_MEASUREMENT_METHOD = 72;

    /**
     * The meta object id for the '
     * {@link org.sercho.masp.models.Context.ReadingRate <em>Reading Rate</em>}'
     * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.ReadingRate
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getReadingRate()
     * @generated
     */
    int READING_RATE = 73;

    /**
     * The meta object id for the '<em>Channel API</em>' data type. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.channel.api.ChannelAPI
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getChannelAPI()
     * @generated
     */
    int CHANNEL_API = 78;

    /**
     * The meta object id for the '<em>Message Input Channel API</em>' data
     * type. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.channel.api.MessageInputChannelAPI
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getMessageInputChannelAPI()
     * @generated
     */
    int MESSAGE_INPUT_CHANNEL_API = 79;

    /**
     * The meta object id for the '<em>Pointing Input Channel API</em>' data
     * type. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.channel.api.PointingInputChannelAPI
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getPointingInputChannelAPI()
     * @generated
     */
    int POINTING_INPUT_CHANNEL_API = 80;

    /**
     * The meta object id for the '<em>Message Output Channel API</em>' data
     * type. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.channel.api.MessageOutputChannelAPI
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getMessageOutputChannelAPI()
     * @generated
     */
    int MESSAGE_OUTPUT_CHANNEL_API = 81;

    /**
     * The meta object id for the '<em>Graphical Output Channel API</em>' data
     * type. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.channel.api.GraphicalOutputChannelAPI
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getGraphicalOutputChannelAPI()
     * @generated
     */
    int GRAPHICAL_OUTPUT_CHANNEL_API = 82;

    /**
     * The meta object id for the '<em>Localisation Provider</em>' data type.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.context.providers.location.LocalisationProvider
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getLocalisationProvider()
     * @generated
     */
    int LOCALISATION_PROVIDER = 83;

    /**
     * The meta object id for the '<em>Place Info</em>' data type. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.sercho.masp.models.Context.PlaceInfo
     * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getPlaceInfo()
     * @generated
     */
    int PLACE_INFO = 84;

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.InteractionResource
     * <em>Interaction Resource</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Interaction Resource</em>'.
     * @see org.sercho.masp.models.Context.InteractionResource
     * @generated
     */
    EClass getInteractionResource();

    /**
     * Returns the meta object for the reference '
     * {@link org.sercho.masp.models.Context.InteractionResource#getUser
     * <em>User</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>User</em>'.
     * @see org.sercho.masp.models.Context.InteractionResource#getUser()
     * @see #getInteractionResource()
     * @generated
     */
    EReference getInteractionResource_User();

    /**
     * Returns the meta object for the container reference '
     * {@link org.sercho.masp.models.Context.InteractionResource#getDevice
     * <em>Device</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Device</em>'.
     * @see org.sercho.masp.models.Context.InteractionResource#getDevice()
     * @see #getInteractionResource()
     * @generated
     */
    EReference getInteractionResource_Device();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.InteractionResource#isAvailable
     * <em>Available</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Available</em>'.
     * @see org.sercho.masp.models.Context.InteractionResource#isAvailable()
     * @see #getInteractionResource()
     * @generated
     */
    EAttribute getInteractionResource_Available();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.InteractionResource#getModality
     * <em>Modality</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Modality</em>'.
     * @see org.sercho.masp.models.Context.InteractionResource#getModality()
     * @see #getInteractionResource()
     * @generated
     */
    EAttribute getInteractionResource_Modality();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.InteractionResource#getMobile
     * <em>Mobile</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Mobile</em>'.
     * @see org.sercho.masp.models.Context.InteractionResource#getMobile()
     * @see #getInteractionResource()
     * @generated
     */
    EAttribute getInteractionResource_Mobile();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.InteractionResource#getPersonal
     * <em>Personal</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Personal</em>'.
     * @see org.sercho.masp.models.Context.InteractionResource#getPersonal()
     * @see #getInteractionResource()
     * @generated
     */
    EAttribute getInteractionResource_Personal();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.InteractionResource#isInteractionStatus
     * <em>Interaction Status</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Interaction Status</em>'.
     * @see org.sercho.masp.models.Context.InteractionResource#isInteractionStatus()
     * @see #getInteractionResource()
     * @generated
     */
    EAttribute getInteractionResource_InteractionStatus();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.InteractionResource#getInteractionStatusTimeStamp
     * <em>Interaction Status Time Stamp</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Interaction Status Time Stamp</em>'.
     * @see org.sercho.masp.models.Context.InteractionResource#getInteractionStatusTimeStamp()
     * @see #getInteractionResource()
     * @generated
     */
    EAttribute getInteractionResource_InteractionStatusTimeStamp();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Environment <em>Environment</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Environment</em>'.
     * @see org.sercho.masp.models.Context.Environment
     * @generated
     */
    EClass getEnvironment();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.Environment#getPlaces
     * <em>Places</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Places</em>'.
     * @see org.sercho.masp.models.Context.Environment#getPlaces()
     * @see #getEnvironment()
     * @generated
     */
    EReference getEnvironment_Places();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.Environment#getProviders
     * <em>Providers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Providers</em>'.
     * @see org.sercho.masp.models.Context.Environment#getProviders()
     * @see #getEnvironment()
     * @generated
     */
    EReference getEnvironment_Providers();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.Environment#getDevices
     * <em>Devices</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Devices</em>'.
     * @see org.sercho.masp.models.Context.Environment#getDevices()
     * @see #getEnvironment()
     * @generated
     */
    EReference getEnvironment_Devices();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.Environment#getUsers
     * <em>Users</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Users</em>'.
     * @see org.sercho.masp.models.Context.Environment#getUsers()
     * @see #getEnvironment()
     * @generated
     */
    EReference getEnvironment_Users();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.Environment#getAssistants
     * <em>Assistants</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Assistants</em>'.
     * @see org.sercho.masp.models.Context.Environment#getAssistants()
     * @see #getEnvironment()
     * @generated
     */
    EReference getEnvironment_Assistants();

    /**
     * Returns the meta object for the reference '
     * {@link org.sercho.masp.models.Context.Environment#getInitialAssistant
     * <em>Initial Assistant</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Initial Assistant</em>'.
     * @see org.sercho.masp.models.Context.Environment#getInitialAssistant()
     * @see #getEnvironment()
     * @generated
     */
    EReference getEnvironment_InitialAssistant();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.Environment#getServiceContainers
     * <em>Service Containers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Service Containers</em>'.
     * @see org.sercho.masp.models.Context.Environment#getServiceContainers()
     * @see #getEnvironment()
     * @generated
     */
    EReference getEnvironment_ServiceContainers();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.Environment#getMeters
     * <em>Meters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Meters</em>'.
     * @see org.sercho.masp.models.Context.Environment#getMeters()
     * @see #getEnvironment()
     * @generated
     */
    EReference getEnvironment_Meters();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.Environment#getDiscoverers
     * <em>Discoverers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Discoverers</em>'.
     * @see org.sercho.masp.models.Context.Environment#getDiscoverers()
     * @see #getEnvironment()
     * @generated
     */
    EReference getEnvironment_Discoverers();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.Environment#getActivities
     * <em>Activities</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Activities</em>'.
     * @see org.sercho.masp.models.Context.Environment#getActivities()
     * @see #getEnvironment()
     * @generated
     */
    EReference getEnvironment_Activities();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.Environment#getTools
     * <em>Tools</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Tools</em>'.
     * @see org.sercho.masp.models.Context.Environment#getTools()
     * @see #getEnvironment()
     * @generated
     */
    EReference getEnvironment_Tools();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Environment#getName <em>Name</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.sercho.masp.models.Context.Environment#getName()
     * @see #getEnvironment()
     * @generated
     */
    EAttribute getEnvironment_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Environment#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.sercho.masp.models.Context.Environment#getDescription()
     * @see #getEnvironment()
     * @generated
     */
    EAttribute getEnvironment_Description();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.User <em>User</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>User</em>'.
     * @see org.sercho.masp.models.Context.User
     * @generated
     */
    EClass getUser();

    /**
     * Returns the meta object for the reference list '
     * {@link org.sercho.masp.models.Context.User#getResources
     * <em>Resources</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Resources</em>'.
     * @see org.sercho.masp.models.Context.User#getResources()
     * @see #getUser()
     * @generated
     */
    EReference getUser_Resources();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.User#getLineOfVision
     * <em>Line Of Vision</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Line Of Vision</em>'.
     * @see org.sercho.masp.models.Context.User#getLineOfVision()
     * @see #getUser()
     * @generated
     */
    EReference getUser_LineOfVision();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.User#getIrExperience
     * <em>Ir Experience</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Ir Experience</em>'.
     * @see org.sercho.masp.models.Context.User#getIrExperience()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_IrExperience();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.User#isFollowMe <em>Follow Me</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Follow Me</em>'.
     * @see org.sercho.masp.models.Context.User#isFollowMe()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_FollowMe();

    /**
     * Returns the meta object for the container reference '
     * {@link org.sercho.masp.models.Context.User#getEnvironment
     * <em>Environment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Environment</em>
     *         '.
     * @see org.sercho.masp.models.Context.User#getEnvironment()
     * @see #getUser()
     * @generated
     */
    EReference getUser_Environment();

    /**
     * Returns the meta object for the reference list '
     * {@link org.sercho.masp.models.Context.User#getCurrentAssistants
     * <em>Current Assistants</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Current Assistants</em>'.
     * @see org.sercho.masp.models.Context.User#getCurrentAssistants()
     * @see #getUser()
     * @generated
     */
    EReference getUser_CurrentAssistants();

    /**
     * Returns the meta object for the reference list '
     * {@link org.sercho.masp.models.Context.User#getPastAssistants
     * <em>Past Assistants</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Past Assistants</em>
     *         '.
     * @see org.sercho.masp.models.Context.User#getPastAssistants()
     * @see #getUser()
     * @generated
     */
    EReference getUser_PastAssistants();

    /**
     * Returns the meta object for the reference list '
     * {@link org.sercho.masp.models.Context.User#getInstalledAssistants
     * <em>Installed Assistants</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Installed Assistants</em>'.
     * @see org.sercho.masp.models.Context.User#getInstalledAssistants()
     * @see #getUser()
     * @generated
     */
    EReference getUser_InstalledAssistants();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.User#getPasswordHash
     * <em>Password Hash</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Password Hash</em>'.
     * @see org.sercho.masp.models.Context.User#getPasswordHash()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_PasswordHash();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.User#isLeftHanded
     * <em>Left Handed</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Left Handed</em>'.
     * @see org.sercho.masp.models.Context.User#isLeftHanded()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_LeftHanded();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.User#getBirthDate
     * <em>Birth Date</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Birth Date</em>'.
     * @see org.sercho.masp.models.Context.User#getBirthDate()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_BirthDate();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.User#getSurname <em>Surname</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Surname</em>'.
     * @see org.sercho.masp.models.Context.User#getSurname()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_Surname();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Assistant <em>Assistant</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Assistant</em>'.
     * @see org.sercho.masp.models.Context.Assistant
     * @generated
     */
    EClass getAssistant();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.Assistant#getActivities
     * <em>Activities</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Activities</em>'.
     * @see org.sercho.masp.models.Context.Assistant#getActivities()
     * @see #getAssistant()
     * @generated
     */
    EReference getAssistant_Activities();

    /**
     * Returns the meta object for the reference list '
     * {@link org.sercho.masp.models.Context.Assistant#getCurrentUsers
     * <em>Current Users</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Current Users</em>'.
     * @see org.sercho.masp.models.Context.Assistant#getCurrentUsers()
     * @see #getAssistant()
     * @generated
     */
    EReference getAssistant_CurrentUsers();

    /**
     * Returns the meta object for the reference list '
     * {@link org.sercho.masp.models.Context.Assistant#getPastUsers
     * <em>Past Users</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Past Users</em>'.
     * @see org.sercho.masp.models.Context.Assistant#getPastUsers()
     * @see #getAssistant()
     * @generated
     */
    EReference getAssistant_PastUsers();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Assistant#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.sercho.masp.models.Context.Assistant#getDescription()
     * @see #getAssistant()
     * @generated
     */
    EAttribute getAssistant_Description();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.Assistant#getConnections
     * <em>Connections</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Connections</em>'.
     * @see org.sercho.masp.models.Context.Assistant#getConnections()
     * @see #getAssistant()
     * @generated
     */
    EReference getAssistant_Connections();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Assistant#getDomain
     * <em>Domain</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Domain</em>'.
     * @see org.sercho.masp.models.Context.Assistant#getDomain()
     * @see #getAssistant()
     * @generated
     */
    EAttribute getAssistant_Domain();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Activity <em>Activity</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Activity</em>'.
     * @see org.sercho.masp.models.Context.Activity
     * @generated
     */
    EClass getActivity();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Activity#getName <em>Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.sercho.masp.models.Context.Activity#getName()
     * @see #getActivity()
     * @generated
     */
    EAttribute getActivity_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Activity#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.sercho.masp.models.Context.Activity#getDescription()
     * @see #getActivity()
     * @generated
     */
    EAttribute getActivity_Description();

    /**
     * Returns the meta object for the reference list '
     * {@link org.sercho.masp.models.Context.Activity#getUsedWith
     * <em>Used With</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Used With</em>'.
     * @see org.sercho.masp.models.Context.Activity#getUsedWith()
     * @see #getActivity()
     * @generated
     */
    EReference getActivity_UsedWith();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.Activity#getConfiguration
     * <em>Configuration</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Configuration</em>'.
     * @see org.sercho.masp.models.Context.Activity#getConfiguration()
     * @see #getActivity()
     * @generated
     */
    EReference getActivity_Configuration();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.HomeOSAssistant
     * <em>Home OS Assistant</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Home OS Assistant</em>'.
     * @see org.sercho.masp.models.Context.HomeOSAssistant
     * @generated
     */
    EClass getHomeOSAssistant();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.ThirdPartyAssistant
     * <em>Third Party Assistant</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Third Party Assistant</em>'.
     * @see org.sercho.masp.models.Context.ThirdPartyAssistant
     * @generated
     */
    EClass getThirdPartyAssistant();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.LocalizationTag
     * <em>Localization Tag</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Localization Tag</em>'.
     * @see org.sercho.masp.models.Context.LocalizationTag
     * @generated
     */
    EClass getLocalizationTag();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.LocalizationTag#isDetected
     * <em>Detected</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Detected</em>'.
     * @see org.sercho.masp.models.Context.LocalizationTag#isDetected()
     * @see #getLocalizationTag()
     * @generated
     */
    EAttribute getLocalizationTag_Detected();

    /**
     * Returns the meta object for the container reference '
     * {@link org.sercho.masp.models.Context.LocalizationTag#getProvider
     * <em>Provider</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Provider</em>'.
     * @see org.sercho.masp.models.Context.LocalizationTag#getProvider()
     * @see #getLocalizationTag()
     * @generated
     */
    EReference getLocalizationTag_Provider();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.LocalizationTag#isRegister
     * <em>Register</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Register</em>'.
     * @see org.sercho.masp.models.Context.LocalizationTag#isRegister()
     * @see #getLocalizationTag()
     * @generated
     */
    EAttribute getLocalizationTag_Register();

    /**
     * Returns the meta object for the reference '
     * {@link org.sercho.masp.models.Context.LocalizationTag#getElement
     * <em>Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Element</em>'.
     * @see org.sercho.masp.models.Context.LocalizationTag#getElement()
     * @see #getLocalizationTag()
     * @generated
     */
    EReference getLocalizationTag_Element();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.EnvironmentElement
     * <em>Environment Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Environment Element</em>'.
     * @see org.sercho.masp.models.Context.EnvironmentElement
     * @generated
     */
    EClass getEnvironmentElement();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.EnvironmentElement#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.sercho.masp.models.Context.EnvironmentElement#getId()
     * @see #getEnvironmentElement()
     * @generated
     */
    EAttribute getEnvironmentElement_Id();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.EnvironmentElement#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.sercho.masp.models.Context.EnvironmentElement#getName()
     * @see #getEnvironmentElement()
     * @generated
     */
    EAttribute getEnvironmentElement_Name();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.ElementWithPosition
     * <em>Element With Position</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Element With Position</em>'.
     * @see org.sercho.masp.models.Context.ElementWithPosition
     * @generated
     */
    EClass getElementWithPosition();

    /**
     * Returns the meta object for the reference list '
     * {@link org.sercho.masp.models.Context.ElementWithPosition#getTags
     * <em>Tags</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Tags</em>'.
     * @see org.sercho.masp.models.Context.ElementWithPosition#getTags()
     * @see #getElementWithPosition()
     * @generated
     */
    EReference getElementWithPosition_Tags();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.ElementWithPosition#getPosition
     * <em>Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Position</em>
     *         '.
     * @see org.sercho.masp.models.Context.ElementWithPosition#getPosition()
     * @see #getElementWithPosition()
     * @generated
     */
    EReference getElementWithPosition_Position();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.ElementWithPosition#getPositionTimeStamp
     * <em>Position Time Stamp</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Position Time Stamp</em>'.
     * @see org.sercho.masp.models.Context.ElementWithPosition#getPositionTimeStamp()
     * @see #getElementWithPosition()
     * @generated
     */
    EAttribute getElementWithPosition_PositionTimeStamp();

    /**
     * Returns the meta object for the reference '
     * {@link org.sercho.masp.models.Context.ElementWithPosition#getPlace
     * <em>Place</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Place</em>'.
     * @see org.sercho.masp.models.Context.ElementWithPosition#getPlace()
     * @see #getElementWithPosition()
     * @generated
     */
    EReference getElementWithPosition_Place();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Room <em>Room</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Room</em>'.
     * @see org.sercho.masp.models.Context.Room
     * @generated
     */
    EClass getRoom();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Room#getType <em>Type</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.sercho.masp.models.Context.Room#getType()
     * @see #getRoom()
     * @generated
     */
    EAttribute getRoom_Type();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.ContextProvider <em>Provider</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Provider</em>'.
     * @see org.sercho.masp.models.Context.ContextProvider
     * @generated
     */
    EClass getContextProvider();

    /**
     * Returns the meta object for the container reference '
     * {@link org.sercho.masp.models.Context.ContextProvider#getEnvironment
     * <em>Environment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Environment</em>
     *         '.
     * @see org.sercho.masp.models.Context.ContextProvider#getEnvironment()
     * @see #getContextProvider()
     * @generated
     */
    EReference getContextProvider_Environment();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.LocalisationProviderProxy
     * <em>Localisation Provider Proxy</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Localisation Provider Proxy</em>'.
     * @see org.sercho.masp.models.Context.LocalisationProviderProxy
     * @generated
     */
    EClass getLocalisationProviderProxy();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.LocalisationProviderProxy#getApiClass
     * <em>Api Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Api Class</em>'.
     * @see org.sercho.masp.models.Context.LocalisationProviderProxy#getApiClass()
     * @see #getLocalisationProviderProxy()
     * @generated
     */
    EAttribute getLocalisationProviderProxy_ApiClass();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.LocalisationProviderProxy#getApi
     * <em>Api</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Api</em>'.
     * @see org.sercho.masp.models.Context.LocalisationProviderProxy#getApi()
     * @see #getLocalisationProviderProxy()
     * @generated
     */
    EAttribute getLocalisationProviderProxy_Api();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.LocalisationProviderProxy#getTags
     * <em>Tags</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Tags</em>
     *         '.
     * @see org.sercho.masp.models.Context.LocalisationProviderProxy#getTags()
     * @see #getLocalisationProviderProxy()
     * @generated
     */
    EReference getLocalisationProviderProxy_Tags();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.LocalisationProviderProxy#getConfiguration
     * <em>Configuration</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Configuration</em>'.
     * @see org.sercho.masp.models.Context.LocalisationProviderProxy#getConfiguration()
     * @see #getLocalisationProviderProxy()
     * @generated
     */
    EReference getLocalisationProviderProxy_Configuration();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Mouse <em>Mouse</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Mouse</em>'.
     * @see org.sercho.masp.models.Context.Mouse
     * @generated
     */
    EClass getMouse();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Mouse#getMotionSensor
     * <em>Motion Sensor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Motion Sensor</em>'.
     * @see org.sercho.masp.models.Context.Mouse#getMotionSensor()
     * @see #getMouse()
     * @generated
     */
    EReference getMouse_MotionSensor();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Mouse#getButtons <em>Buttons</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Buttons</em>'.
     * @see org.sercho.masp.models.Context.Mouse#getButtons()
     * @see #getMouse()
     * @generated
     */
    EReference getMouse_Buttons();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Keyboard <em>Keyboard</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Keyboard</em>'.
     * @see org.sercho.masp.models.Context.Keyboard
     * @generated
     */
    EClass getKeyboard();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Keyboard#getKeys <em>Keys</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Keys</em>'.
     * @see org.sercho.masp.models.Context.Keyboard#getKeys()
     * @see #getKeyboard()
     * @generated
     */
    EReference getKeyboard_Keys();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Display <em>Display</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Display</em>'.
     * @see org.sercho.masp.models.Context.Display
     * @generated
     */
    EClass getDisplay();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Display#getXPixels
     * <em>XPixels</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>XPixels</em>'.
     * @see org.sercho.masp.models.Context.Display#getXPixels()
     * @see #getDisplay()
     * @generated
     */
    EAttribute getDisplay_XPixels();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Display#getYPixels
     * <em>YPixels</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>YPixels</em>'.
     * @see org.sercho.masp.models.Context.Display#getYPixels()
     * @see #getDisplay()
     * @generated
     */
    EAttribute getDisplay_YPixels();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Display#getDirection
     * <em>Direction</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Direction</em>
     *         '.
     * @see org.sercho.masp.models.Context.Display#getDirection()
     * @see #getDisplay()
     * @generated
     */
    EReference getDisplay_Direction();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Display#getScreen <em>Screen</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Screen</em>'.
     * @see org.sercho.masp.models.Context.Display#getScreen()
     * @see #getDisplay()
     * @generated
     */
    EReference getDisplay_Screen();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Display#getSize <em>Size</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Size</em>'.
     * @see org.sercho.masp.models.Context.Display#getSize()
     * @see #getDisplay()
     * @generated
     */
    EAttribute getDisplay_Size();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.InputInteractionResource
     * <em>Input Interaction Resource</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Input Interaction Resource</em>'.
     * @see org.sercho.masp.models.Context.InputInteractionResource
     * @generated
     */
    EClass getInputInteractionResource();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.OutputInteractionResource
     * <em>Output Interaction Resource</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Output Interaction Resource</em>'.
     * @see org.sercho.masp.models.Context.OutputInteractionResource
     * @generated
     */
    EClass getOutputInteractionResource();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.GestureIR <em>Gesture IR</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Gesture IR</em>'.
     * @see org.sercho.masp.models.Context.GestureIR
     * @generated
     */
    EClass getGestureIR();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.GestureIR#getGestureRecognition
     * <em>Gesture Recognition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Gesture Recognition</em>'.
     * @see org.sercho.masp.models.Context.GestureIR#getGestureRecognition()
     * @see #getGestureIR()
     * @generated
     */
    EReference getGestureIR_GestureRecognition();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Touchpad <em>Touchpad</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Touchpad</em>'.
     * @see org.sercho.masp.models.Context.Touchpad
     * @generated
     */
    EClass getTouchpad();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Device <em>Device</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Device</em>'.
     * @see org.sercho.masp.models.Context.Device
     * @generated
     */
    EClass getDevice();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.Device#getResources
     * <em>Resources</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Resources</em>'.
     * @see org.sercho.masp.models.Context.Device#getResources()
     * @see #getDevice()
     * @generated
     */
    EReference getDevice_Resources();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Device#isMobile <em>Mobile</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Mobile</em>'.
     * @see org.sercho.masp.models.Context.Device#isMobile()
     * @see #getDevice()
     * @generated
     */
    EAttribute getDevice_Mobile();

    /**
     * Returns the meta object for the container reference '
     * {@link org.sercho.masp.models.Context.Device#getEnvironment
     * <em>Environment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Environment</em>
     *         '.
     * @see org.sercho.masp.models.Context.Device#getEnvironment()
     * @see #getDevice()
     * @generated
     */
    EReference getDevice_Environment();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Device#getManufacturer
     * <em>Manufacturer</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Manufacturer</em>'.
     * @see org.sercho.masp.models.Context.Device#getManufacturer()
     * @see #getDevice()
     * @generated
     */
    EAttribute getDevice_Manufacturer();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Device#getModelName
     * <em>Model Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Model Name</em>'.
     * @see org.sercho.masp.models.Context.Device#getModelName()
     * @see #getDevice()
     * @generated
     */
    EAttribute getDevice_ModelName();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Channel <em>Channel</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Channel</em>'.
     * @see org.sercho.masp.models.Context.Channel
     * @generated
     */
    EClass getChannel();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Channel#getDistributionState
     * <em>Distribution State</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Distribution State</em>'.
     * @see org.sercho.masp.models.Context.Channel#getDistributionState()
     * @see #getChannel()
     * @generated
     */
    EAttribute getChannel_DistributionState();

    /**
     * Returns the meta object for the reference list '
     * {@link org.sercho.masp.models.Context.Channel#getElements
     * <em>Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Elements</em>'.
     * @see org.sercho.masp.models.Context.Channel#getElements()
     * @see #getChannel()
     * @generated
     */
    EReference getChannel_Elements();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Channel#getApi <em>Api</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Api</em>'.
     * @see org.sercho.masp.models.Context.Channel#getApi()
     * @see #getChannel()
     * @generated
     */
    EAttribute getChannel_Api();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.Channel#getConfiguration
     * <em>Configuration</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Configuration</em>'.
     * @see org.sercho.masp.models.Context.Channel#getConfiguration()
     * @see #getChannel()
     * @generated
     */
    EReference getChannel_Configuration();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Channel#isAvailable
     * <em>Available</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Available</em>'.
     * @see org.sercho.masp.models.Context.Channel#isAvailable()
     * @see #getChannel()
     * @generated
     */
    EAttribute getChannel_Available();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Channel#getApiClass
     * <em>Api Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Api Class</em>'.
     * @see org.sercho.masp.models.Context.Channel#getApiClass()
     * @see #getChannel()
     * @generated
     */
    EAttribute getChannel_ApiClass();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.PointingInputChannel
     * <em>Pointing Input Channel</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Pointing Input Channel</em>'.
     * @see org.sercho.masp.models.Context.PointingInputChannel
     * @generated
     */
    EClass getPointingInputChannel();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.MessageInputChannel
     * <em>Message Input Channel</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Message Input Channel</em>'.
     * @see org.sercho.masp.models.Context.MessageInputChannel
     * @generated
     */
    EClass getMessageInputChannel();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.MessageOutputChannel
     * <em>Message Output Channel</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Message Output Channel</em>'.
     * @see org.sercho.masp.models.Context.MessageOutputChannel
     * @generated
     */
    EClass getMessageOutputChannel();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.GraphicalOutputChannel
     * <em>Graphical Output Channel</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Graphical Output Channel</em>'.
     * @see org.sercho.masp.models.Context.GraphicalOutputChannel
     * @generated
     */
    EClass getGraphicalOutputChannel();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Loudspeaker <em>Loudspeaker</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Loudspeaker</em>'.
     * @see org.sercho.masp.models.Context.Loudspeaker
     * @generated
     */
    EClass getLoudspeaker();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Loudspeaker#getVoice
     * <em>Voice</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Voice</em>'.
     * @see org.sercho.masp.models.Context.Loudspeaker#getVoice()
     * @see #getLoudspeaker()
     * @generated
     */
    EReference getLoudspeaker_Voice();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Microphone <em>Microphone</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Microphone</em>'.
     * @see org.sercho.masp.models.Context.Microphone
     * @generated
     */
    EClass getMicrophone();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Microphone#getVoiceRecognition
     * <em>Voice Recognition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Voice Recognition</em>'.
     * @see org.sercho.masp.models.Context.Microphone#getVoiceRecognition()
     * @see #getMicrophone()
     * @generated
     */
    EReference getMicrophone_VoiceRecognition();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Vector <em>Vector</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Vector</em>'.
     * @see org.sercho.masp.models.Context.Vector
     * @generated
     */
    EClass getVector();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Vector#getX <em>X</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>X</em>'.
     * @see org.sercho.masp.models.Context.Vector#getX()
     * @see #getVector()
     * @generated
     */
    EAttribute getVector_X();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Vector#getY <em>Y</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see org.sercho.masp.models.Context.Vector#getY()
     * @see #getVector()
     * @generated
     */
    EAttribute getVector_Y();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Vector#getZ <em>Z</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Z</em>'.
     * @see org.sercho.masp.models.Context.Vector#getZ()
     * @see #getVector()
     * @generated
     */
    EAttribute getVector_Z();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Area <em>Area</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Area</em>'.
     * @see org.sercho.masp.models.Context.Area
     * @generated
     */
    EClass getArea();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Area#getOrigin <em>Origin</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Origin</em>'.
     * @see org.sercho.masp.models.Context.Area#getOrigin()
     * @see #getArea()
     * @generated
     */
    EReference getArea_Origin();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Area#getSpan <em>Span</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Span</em>'.
     * @see org.sercho.masp.models.Context.Area#getSpan()
     * @see #getArea()
     * @generated
     */
    EReference getArea_Span();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.HapticalInteractionResources
     * <em>Haptical Interaction Resources</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Haptical Interaction Resources</em>'.
     * @see org.sercho.masp.models.Context.HapticalInteractionResources
     * @generated
     */
    EClass getHapticalInteractionResources();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.ConfigurationProperty
     * <em>Configuration Property</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Configuration Property</em>'.
     * @see org.sercho.masp.models.Context.ConfigurationProperty
     * @generated
     */
    EClass getConfigurationProperty();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.ConfigurationProperty#getKey
     * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see org.sercho.masp.models.Context.ConfigurationProperty#getKey()
     * @see #getConfigurationProperty()
     * @generated
     */
    EAttribute getConfigurationProperty_Key();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.ConfigurationProperty#getValue
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.sercho.masp.models.Context.ConfigurationProperty#getValue()
     * @see #getConfigurationProperty()
     * @generated
     */
    EAttribute getConfigurationProperty_Value();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Heater <em>Heater</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Heater</em>'.
     * @see org.sercho.masp.models.Context.Heater
     * @generated
     */
    EClass getHeater();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Heater#getTemperatureDemand
     * <em>Temperature Demand</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Temperature Demand</em>'.
     * @see org.sercho.masp.models.Context.Heater#getTemperatureDemand()
     * @see #getHeater()
     * @generated
     */
    EReference getHeater_TemperatureDemand();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Heater#getTemperatureCurrent
     * <em>Temperature Current</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Temperature Current</em>'.
     * @see org.sercho.masp.models.Context.Heater#getTemperatureCurrent()
     * @see #getHeater()
     * @generated
     */
    EReference getHeater_TemperatureCurrent();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Heater#getValvePosition
     * <em>Valve Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Valve Position</em>'.
     * @see org.sercho.masp.models.Context.Heater#getValvePosition()
     * @see #getHeater()
     * @generated
     */
    EReference getHeater_ValvePosition();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Meter <em>Meter</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Meter</em>'.
     * @see org.sercho.masp.models.Context.Meter
     * @generated
     */
    EClass getMeter();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.Meter#getReadings
     * <em>Readings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Readings</em>'.
     * @see org.sercho.masp.models.Context.Meter#getReadings()
     * @see #getMeter()
     * @generated
     */
    EReference getMeter_Readings();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.WaterStorageTank
     * <em>Water Storage Tank</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Water Storage Tank</em>'.
     * @see org.sercho.masp.models.Context.WaterStorageTank
     * @generated
     */
    EClass getWaterStorageTank();

    /**
     * Returns the meta object for the reference '
     * {@link org.sercho.masp.models.Context.WaterStorageTank#getTemperatureSensor
     * <em>Temperature Sensor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Temperature Sensor</em>'.
     * @see org.sercho.masp.models.Context.WaterStorageTank#getTemperatureSensor()
     * @see #getWaterStorageTank()
     * @generated
     */
    EReference getWaterStorageTank_TemperatureSensor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.WaterStorageTank#getCapacity
     * <em>Capacity</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Capacity</em>'.
     * @see org.sercho.masp.models.Context.WaterStorageTank#getCapacity()
     * @see #getWaterStorageTank()
     * @generated
     */
    EAttribute getWaterStorageTank_Capacity();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.HeatingRod <em>Heating Rod</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Heating Rod</em>'.
     * @see org.sercho.masp.models.Context.HeatingRod
     * @generated
     */
    EClass getHeatingRod();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.HeatingRod#getMaximumPowerWatts
     * <em>Maximum Power Watts</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Maximum Power Watts</em>'.
     * @see org.sercho.masp.models.Context.HeatingRod#getMaximumPowerWatts()
     * @see #getHeatingRod()
     * @generated
     */
    EAttribute getHeatingRod_MaximumPowerWatts();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.MeterReading
     * <em>Meter Reading</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Meter Reading</em>'.
     * @see org.sercho.masp.models.Context.MeterReading
     * @generated
     */
    EClass getMeterReading();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.MeterReading#getValue
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Value</em>'.
     * @see org.sercho.masp.models.Context.MeterReading#getValue()
     * @see #getMeterReading()
     * @generated
     */
    EReference getMeterReading_Value();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.MeterReading#getSubject
     * <em>Subject</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Subject</em>'.
     * @see org.sercho.masp.models.Context.MeterReading#getSubject()
     * @see #getMeterReading()
     * @generated
     */
    EAttribute getMeterReading_Subject();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.MeterReading#getMeasurand
     * <em>Measurand</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Measurand</em>'.
     * @see org.sercho.masp.models.Context.MeterReading#getMeasurand()
     * @see #getMeterReading()
     * @generated
     */
    EAttribute getMeterReading_Measurand();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.MeterReading#getMeasurementMethod
     * <em>Measurement Method</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Measurement Method</em>'.
     * @see org.sercho.masp.models.Context.MeterReading#getMeasurementMethod()
     * @see #getMeterReading()
     * @generated
     */
    EAttribute getMeterReading_MeasurementMethod();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.MeterReading#getRate <em>Rate</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Rate</em>'.
     * @see org.sercho.masp.models.Context.MeterReading#getRate()
     * @see #getMeterReading()
     * @generated
     */
    EAttribute getMeterReading_Rate();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.MeterReading#getIdentifier
     * <em>Identifier</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Identifier</em>'.
     * @see org.sercho.masp.models.Context.MeterReading#getIdentifier()
     * @see #getMeterReading()
     * @generated
     */
    EAttribute getMeterReading_Identifier();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.PhysicalSensorDevice
     * <em>Physical Sensor Device</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Physical Sensor Device</em>'.
     * @see org.sercho.masp.models.Context.PhysicalSensorDevice
     * @generated
     */
    EClass getPhysicalSensorDevice();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.PhysicalSensorDevice#getValue
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Value</em>'.
     * @see org.sercho.masp.models.Context.PhysicalSensorDevice#getValue()
     * @see #getPhysicalSensorDevice()
     * @generated
     */
    EReference getPhysicalSensorDevice_Value();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.LuminanceSensor
     * <em>Luminance Sensor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Luminance Sensor</em>'.
     * @see org.sercho.masp.models.Context.LuminanceSensor
     * @generated
     */
    EClass getLuminanceSensor();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.TemperatureSensor
     * <em>Temperature Sensor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Temperature Sensor</em>'.
     * @see org.sercho.masp.models.Context.TemperatureSensor
     * @generated
     */
    EClass getTemperatureSensor();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.WashingMachine
     * <em>Washing Machine</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Washing Machine</em>'.
     * @see org.sercho.masp.models.Context.WashingMachine
     * @generated
     */
    EClass getWashingMachine();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.AssistantConnection
     * <em>Assistant Connection</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Assistant Connection</em>'.
     * @see org.sercho.masp.models.Context.AssistantConnection
     * @generated
     */
    EClass getAssistantConnection();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.AssistantConnection#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.sercho.masp.models.Context.AssistantConnection#getDescription()
     * @see #getAssistantConnection()
     * @generated
     */
    EAttribute getAssistantConnection_Description();

    /**
     * Returns the meta object for the reference '
     * {@link org.sercho.masp.models.Context.AssistantConnection#getConnectedAssistant
     * <em>Connected Assistant</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Connected Assistant</em>'.
     * @see org.sercho.masp.models.Context.AssistantConnection#getConnectedAssistant()
     * @see #getAssistantConnection()
     * @generated
     */
    EReference getAssistantConnection_ConnectedAssistant();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Tool <em>Tool</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Tool</em>'.
     * @see org.sercho.masp.models.Context.Tool
     * @generated
     */
    EClass getTool();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Tool#getName <em>Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.sercho.masp.models.Context.Tool#getName()
     * @see #getTool()
     * @generated
     */
    EAttribute getTool_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Tool#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.sercho.masp.models.Context.Tool#getDescription()
     * @see #getTool()
     * @generated
     */
    EAttribute getTool_Description();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.DeviceTool <em>Device Tool</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Device Tool</em>'.
     * @see org.sercho.masp.models.Context.DeviceTool
     * @generated
     */
    EClass getDeviceTool();

    /**
     * Returns the meta object for the reference list '
     * {@link org.sercho.masp.models.Context.DeviceTool#getControls
     * <em>Controls</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Controls</em>'.
     * @see org.sercho.masp.models.Context.DeviceTool#getControls()
     * @see #getDeviceTool()
     * @generated
     */
    EReference getDeviceTool_Controls();

    /**
     * Returns the meta object for the reference '
     * {@link org.sercho.masp.models.Context.DeviceTool#getAppliesTo
     * <em>Applies To</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Applies To</em>'.
     * @see org.sercho.masp.models.Context.DeviceTool#getAppliesTo()
     * @see #getDeviceTool()
     * @generated
     */
    EReference getDeviceTool_AppliesTo();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Need <em>Need</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Need</em>'.
     * @see org.sercho.masp.models.Context.Need
     * @generated
     */
    EClass getNeed();

    /**
     * Returns the meta object for the reference list '
     * {@link org.sercho.masp.models.Context.Need#getFulfilledBy
     * <em>Fulfilled By</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Fulfilled By</em>'.
     * @see org.sercho.masp.models.Context.Need#getFulfilledBy()
     * @see #getNeed()
     * @generated
     */
    EReference getNeed_FulfilledBy();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Need#getName <em>Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.sercho.masp.models.Context.Need#getName()
     * @see #getNeed()
     * @generated
     */
    EAttribute getNeed_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Need#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.sercho.masp.models.Context.Need#getDescription()
     * @see #getNeed()
     * @generated
     */
    EAttribute getNeed_Description();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Need#getConfiguration
     * <em>Configuration</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Configuration</em>'.
     * @see org.sercho.masp.models.Context.Need#getConfiguration()
     * @see #getNeed()
     * @generated
     */
    EReference getNeed_Configuration();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Door <em>Door</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Door</em>'.
     * @see org.sercho.masp.models.Context.Door
     * @generated
     */
    EClass getDoor();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Door#getOpen <em>Open</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Open</em>'.
     * @see org.sercho.masp.models.Context.Door#getOpen()
     * @see #getDoor()
     * @generated
     */
    EReference getDoor_Open();

    /**
     * Returns the meta object for the container reference '
     * {@link org.sercho.masp.models.Context.Door#getSource <em>Source</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Source</em>'.
     * @see org.sercho.masp.models.Context.Door#getSource()
     * @see #getDoor()
     * @generated
     */
    EReference getDoor_Source();

    /**
     * Returns the meta object for the reference '
     * {@link org.sercho.masp.models.Context.Door#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Target</em>'.
     * @see org.sercho.masp.models.Context.Door#getTarget()
     * @see #getDoor()
     * @generated
     */
    EReference getDoor_Target();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Door#getSpan <em>Span</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Span</em>'.
     * @see org.sercho.masp.models.Context.Door#getSpan()
     * @see #getDoor()
     * @generated
     */
    EReference getDoor_Span();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Window <em>Window</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Window</em>'.
     * @see org.sercho.masp.models.Context.Window
     * @generated
     */
    EClass getWindow();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Window#getOpen <em>Open</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Open</em>'.
     * @see org.sercho.masp.models.Context.Window#getOpen()
     * @see #getWindow()
     * @generated
     */
    EReference getWindow_Open();

    /**
     * Returns the meta object for the container reference '
     * {@link org.sercho.masp.models.Context.Window#getSource <em>Source</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Source</em>'.
     * @see org.sercho.masp.models.Context.Window#getSource()
     * @see #getWindow()
     * @generated
     */
    EReference getWindow_Source();

    /**
     * Returns the meta object for the reference '
     * {@link org.sercho.masp.models.Context.Window#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Target</em>'.
     * @see org.sercho.masp.models.Context.Window#getTarget()
     * @see #getWindow()
     * @generated
     */
    EReference getWindow_Target();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Window#getSpan <em>Span</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Span</em>'.
     * @see org.sercho.masp.models.Context.Window#getSpan()
     * @see #getWindow()
     * @generated
     */
    EReference getWindow_Span();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Place <em>Place</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Place</em>'.
     * @see org.sercho.masp.models.Context.Place
     * @generated
     */
    EClass getPlace();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.Place#getAreas <em>Areas</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Areas</em>'.
     * @see org.sercho.masp.models.Context.Place#getAreas()
     * @see #getPlace()
     * @generated
     */
    EReference getPlace_Areas();

    /**
     * Returns the meta object for the reference list '
     * {@link org.sercho.masp.models.Context.Place#getElements
     * <em>Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Elements</em>'.
     * @see org.sercho.masp.models.Context.Place#getElements()
     * @see #getPlace()
     * @generated
     */
    EReference getPlace_Elements();

    /**
     * Returns the meta object for the container reference '
     * {@link org.sercho.masp.models.Context.Place#getEnvironment
     * <em>Environment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Environment</em>
     *         '.
     * @see org.sercho.masp.models.Context.Place#getEnvironment()
     * @see #getPlace()
     * @generated
     */
    EReference getPlace_Environment();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.Place#getDoors <em>Doors</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Doors</em>'.
     * @see org.sercho.masp.models.Context.Place#getDoors()
     * @see #getPlace()
     * @generated
     */
    EReference getPlace_Doors();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.Place#getWindows <em>Windows</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Windows</em>'.
     * @see org.sercho.masp.models.Context.Place#getWindows()
     * @see #getPlace()
     * @generated
     */
    EReference getPlace_Windows();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Place#getFloor <em>Floor</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Floor</em>'.
     * @see org.sercho.masp.models.Context.Place#getFloor()
     * @see #getPlace()
     * @generated
     */
    EAttribute getPlace_Floor();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Outdoors <em>Outdoors</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Outdoors</em>'.
     * @see org.sercho.masp.models.Context.Outdoors
     * @generated
     */
    EClass getOutdoors();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Touchscreen <em>Touchscreen</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Touchscreen</em>'.
     * @see org.sercho.masp.models.Context.Touchscreen
     * @generated
     */
    EClass getTouchscreen();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Touchscreen#getTouchSurface
     * <em>Touch Surface</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Touch Surface</em>'.
     * @see org.sercho.masp.models.Context.Touchscreen#getTouchSurface()
     * @see #getTouchscreen()
     * @generated
     */
    EReference getTouchscreen_TouchSurface();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Touchscreen#getGestureRecognition
     * <em>Gesture Recognition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Gesture Recognition</em>'.
     * @see org.sercho.masp.models.Context.Touchscreen#getGestureRecognition()
     * @see #getTouchscreen()
     * @generated
     */
    EReference getTouchscreen_GestureRecognition();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.PhysicalDevice
     * <em>Physical Device</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Physical Device</em>'.
     * @see org.sercho.masp.models.Context.PhysicalDevice
     * @generated
     */
    EClass getPhysicalDevice();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.PhysicalDevice#getOn <em>On</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>On</em>'.
     * @see org.sercho.masp.models.Context.PhysicalDevice#getOn()
     * @see #getPhysicalDevice()
     * @generated
     */
    EReference getPhysicalDevice_On();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.PhysicalDevice#getPowerUsage
     * <em>Power Usage</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Power Usage</em>'.
     * @see org.sercho.masp.models.Context.PhysicalDevice#getPowerUsage()
     * @see #getPhysicalDevice()
     * @generated
     */
    EReference getPhysicalDevice_PowerUsage();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.sercho.masp.models.Context.PhysicalDevice#getSubDevice
     * <em>Sub Device</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Sub Device</em>'.
     * @see org.sercho.masp.models.Context.PhysicalDevice#getSubDevice()
     * @see #getPhysicalDevice()
     * @generated
     */
    EReference getPhysicalDevice_SubDevice();

    /**
     * Returns the meta object for the container reference '
     * {@link org.sercho.masp.models.Context.PhysicalDevice#getParentDevice
     * <em>Parent Device</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '
     *         <em>Parent Device</em>'.
     * @see org.sercho.masp.models.Context.PhysicalDevice#getParentDevice()
     * @see #getPhysicalDevice()
     * @generated
     */
    EReference getPhysicalDevice_ParentDevice();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.PhysicalDeviceWithProgram
     * <em>Physical Device With Program</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Physical Device With Program</em>
     *         '.
     * @see org.sercho.masp.models.Context.PhysicalDeviceWithProgram
     * @generated
     */
    EClass getPhysicalDeviceWithProgram();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.PhysicalDeviceWithProgram#getProgram
     * <em>Program</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Program</em>'.
     * @see org.sercho.masp.models.Context.PhysicalDeviceWithProgram#getProgram()
     * @see #getPhysicalDeviceWithProgram()
     * @generated
     */
    EReference getPhysicalDeviceWithProgram_Program();

    /**
     * Returns the meta object for the reference '
     * {@link org.sercho.masp.models.Context.PhysicalDeviceWithProgram#getSecondsRemaining
     * <em>Seconds Remaining</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Seconds Remaining</em>'.
     * @see org.sercho.masp.models.Context.PhysicalDeviceWithProgram#getSecondsRemaining()
     * @see #getPhysicalDeviceWithProgram()
     * @generated
     */
    EReference getPhysicalDeviceWithProgram_SecondsRemaining();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Lamp <em>Lamp</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Lamp</em>'.
     * @see org.sercho.masp.models.Context.Lamp
     * @generated
     */
    EClass getLamp();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Lamp#getDimmingLevel
     * <em>Dimming Level</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Dimming Level</em>'.
     * @see org.sercho.masp.models.Context.Lamp#getDimmingLevel()
     * @see #getLamp()
     * @generated
     */
    EReference getLamp_DimmingLevel();

    /**
     * Returns the meta object for the attribute '
     * {@link org.sercho.masp.models.Context.Lamp#getLampType
     * <em>Lamp Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Lamp Type</em>'.
     * @see org.sercho.masp.models.Context.Lamp#getLampType()
     * @see #getLamp()
     * @generated
     */
    EAttribute getLamp_LampType();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Notebook <em>Notebook</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Notebook</em>'.
     * @see org.sercho.masp.models.Context.Notebook
     * @generated
     */
    EClass getNotebook();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Hood <em>Hood</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Hood</em>'.
     * @see org.sercho.masp.models.Context.Hood
     * @generated
     */
    EClass getHood();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.PC <em>PC</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>PC</em>'.
     * @see org.sercho.masp.models.Context.PC
     * @generated
     */
    EClass getPC();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Fan <em>Fan</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Fan</em>'.
     * @see org.sercho.masp.models.Context.Fan
     * @generated
     */
    EClass getFan();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Fan#getSpeed <em>Speed</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Speed</em>'.
     * @see org.sercho.masp.models.Context.Fan#getSpeed()
     * @see #getFan()
     * @generated
     */
    EReference getFan_Speed();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.RemoteControl
     * <em>Remote Control</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Remote Control</em>'.
     * @see org.sercho.masp.models.Context.RemoteControl
     * @generated
     */
    EClass getRemoteControl();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.TV <em>TV</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>TV</em>'.
     * @see org.sercho.masp.models.Context.TV
     * @generated
     */
    EClass getTV();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.TV#getCurrentProgram
     * <em>Current Program</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Current Program</em>'.
     * @see org.sercho.masp.models.Context.TV#getCurrentProgram()
     * @see #getTV()
     * @generated
     */
    EReference getTV_CurrentProgram();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Oven <em>Oven</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Oven</em>'.
     * @see org.sercho.masp.models.Context.Oven
     * @generated
     */
    EClass getOven();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Oven#getTemperature
     * <em>Temperature</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Temperature</em>'.
     * @see org.sercho.masp.models.Context.Oven#getTemperature()
     * @see #getOven()
     * @generated
     */
    EReference getOven_Temperature();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Dishwasher <em>Dishwasher</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Dishwasher</em>'.
     * @see org.sercho.masp.models.Context.Dishwasher
     * @generated
     */
    EClass getDishwasher();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Blind <em>Blind</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Blind</em>'.
     * @see org.sercho.masp.models.Context.Blind
     * @generated
     */
    EClass getBlind();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Blind#getLevel <em>Level</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Level</em>'.
     * @see org.sercho.masp.models.Context.Blind#getLevel()
     * @see #getBlind()
     * @generated
     */
    EReference getBlind_Level();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Hob <em>Hob</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Hob</em>'.
     * @see org.sercho.masp.models.Context.Hob
     * @generated
     */
    EClass getHob();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Hob#getHeatLevel
     * <em>Heat Level</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Heat Level</em>'.
     * @see org.sercho.masp.models.Context.Hob#getHeatLevel()
     * @see #getHob()
     * @generated
     */
    EReference getHob_HeatLevel();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Cooker <em>Cooker</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Cooker</em>'.
     * @see org.sercho.masp.models.Context.Cooker
     * @generated
     */
    EClass getCooker();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.CookTop <em>Cook Top</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Cook Top</em>'.
     * @see org.sercho.masp.models.Context.CookTop
     * @generated
     */
    EClass getCookTop();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Fridge <em>Fridge</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Fridge</em>'.
     * @see org.sercho.masp.models.Context.Fridge
     * @generated
     */
    EClass getFridge();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.sercho.masp.models.Context.Fridge#getTemperature
     * <em>Temperature</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Temperature</em>'.
     * @see org.sercho.masp.models.Context.Fridge#getTemperature()
     * @see #getFridge()
     * @generated
     */
    EReference getFridge_Temperature();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Mixer <em>Mixer</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Mixer</em>'.
     * @see org.sercho.masp.models.Context.Mixer
     * @generated
     */
    EClass getMixer();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Radio <em>Radio</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Radio</em>'.
     * @see org.sercho.masp.models.Context.Radio
     * @generated
     */
    EClass getRadio();

    /**
     * Returns the meta object for class '
     * {@link org.sercho.masp.models.Context.Socket <em>Socket</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Socket</em>'.
     * @see org.sercho.masp.models.Context.Socket
     * @generated
     */
    EClass getSocket();

    /**
     * Returns the meta object for enum '
     * {@link org.sercho.masp.models.Context.DistributionState
     * <em>Distribution State</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Distribution State</em>'.
     * @see org.sercho.masp.models.Context.DistributionState
     * @generated
     */
    EEnum getDistributionState();

    /**
     * Returns the meta object for enum '
     * {@link org.sercho.masp.models.Context.Modality <em>Modality</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Modality</em>'.
     * @see org.sercho.masp.models.Context.Modality
     * @generated
     */
    EEnum getModality();

    /**
     * Returns the meta object for enum '
     * {@link org.sercho.masp.models.Context.Domain <em>Domain</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Domain</em>'.
     * @see org.sercho.masp.models.Context.Domain
     * @generated
     */
    EEnum getDomain();

    /**
     * Returns the meta object for enum '
     * {@link org.sercho.masp.models.Context.LampType <em>Lamp Type</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Lamp Type</em>'.
     * @see org.sercho.masp.models.Context.LampType
     * @generated
     */
    EEnum getLampType();

    /**
     * Returns the meta object for enum '
     * {@link org.sercho.masp.models.Context.RoomType <em>Room Type</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Room Type</em>'.
     * @see org.sercho.masp.models.Context.RoomType
     * @generated
     */
    EEnum getRoomType();

    /**
     * Returns the meta object for enum '
     * {@link org.sercho.masp.models.Context.ReadingSubject
     * <em>Reading Subject</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Reading Subject</em>'.
     * @see org.sercho.masp.models.Context.ReadingSubject
     * @generated
     */
    EEnum getReadingSubject();

    /**
     * Returns the meta object for enum '
     * {@link org.sercho.masp.models.Context.ReadingMeasurand
     * <em>Reading Measurand</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Reading Measurand</em>'.
     * @see org.sercho.masp.models.Context.ReadingMeasurand
     * @generated
     */
    EEnum getReadingMeasurand();

    /**
     * Returns the meta object for enum '
     * {@link org.sercho.masp.models.Context.ReadingMeasurementMethod
     * <em>Reading Measurement Method</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for enum '<em>Reading Measurement Method</em>'.
     * @see org.sercho.masp.models.Context.ReadingMeasurementMethod
     * @generated
     */
    EEnum getReadingMeasurementMethod();

    /**
     * Returns the meta object for enum '
     * {@link org.sercho.masp.models.Context.ReadingRate <em>Reading Rate</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Reading Rate</em>'.
     * @see org.sercho.masp.models.Context.ReadingRate
     * @generated
     */
    EEnum getReadingRate();

    /**
     * Returns the meta object for data type '
     * {@link org.sercho.masp.models.channel.api.ChannelAPI
     * <em>Channel API</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for data type '<em>Channel API</em>'.
     * @see org.sercho.masp.models.channel.api.ChannelAPI
     * @model instanceClass="org.sercho.masp.models.channel.api.ChannelAPI"
     * @generated
     */
    EDataType getChannelAPI();

    /**
     * Returns the meta object for data type '
     * {@link org.sercho.masp.models.channel.api.MessageInputChannelAPI
     * <em>Message Input Channel API</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for data type '<em>Message Input Channel API</em>
     *         '.
     * @see org.sercho.masp.models.channel.api.MessageInputChannelAPI
     * @model instanceClass=
     *        "org.sercho.masp.models.channel.api.MessageInputChannelAPI"
     * @generated
     */
    EDataType getMessageInputChannelAPI();

    /**
     * Returns the meta object for data type '
     * {@link org.sercho.masp.models.channel.api.PointingInputChannelAPI
     * <em>Pointing Input Channel API</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for data type '
     *         <em>Pointing Input Channel API</em>'.
     * @see org.sercho.masp.models.channel.api.PointingInputChannelAPI
     * @model instanceClass=
     *        "org.sercho.masp.models.channel.api.PointingInputChannelAPI"
     * @generated
     */
    EDataType getPointingInputChannelAPI();

    /**
     * Returns the meta object for data type '
     * {@link org.sercho.masp.models.channel.api.MessageOutputChannelAPI
     * <em>Message Output Channel API</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for data type '
     *         <em>Message Output Channel API</em>'.
     * @see org.sercho.masp.models.channel.api.MessageOutputChannelAPI
     * @model instanceClass=
     *        "org.sercho.masp.models.channel.api.MessageOutputChannelAPI"
     * @generated
     */
    EDataType getMessageOutputChannelAPI();

    /**
     * Returns the meta object for data type '
     * {@link org.sercho.masp.models.channel.api.GraphicalOutputChannelAPI
     * <em>Graphical Output Channel API</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for data type '
     *         <em>Graphical Output Channel API</em>'.
     * @see org.sercho.masp.models.channel.api.GraphicalOutputChannelAPI
     * @model instanceClass=
     *        "org.sercho.masp.models.channel.api.GraphicalOutputChannelAPI"
     * @generated
     */
    EDataType getGraphicalOutputChannelAPI();

    /**
     * Returns the meta object for data type '
     * {@link org.sercho.masp.context.providers.location.LocalisationProvider
     * <em>Localisation Provider</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for data type '<em>Localisation Provider</em>'.
     * @see org.sercho.masp.context.providers.location.LocalisationProvider
     * @model instanceClass=
     *        "org.sercho.masp.context.providers.location.LocalisationProvider"
     * @generated
     */
    EDataType getLocalisationProvider();

    /**
     * Returns the meta object for data type '
     * {@link org.sercho.masp.models.Context.PlaceInfo <em>Place Info</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for data type '<em>Place Info</em>'.
     * @see org.sercho.masp.models.Context.PlaceInfo
     * @model instanceClass="org.sercho.masp.models.Context.PlaceInfo"
     * @generated
     */
    EDataType getPlaceInfo();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ContextFactory getContextFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that
     * represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    interface Literals {

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.InteractionResourceImpl
         * <em>Interaction Resource</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.InteractionResourceImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getInteractionResource()
         * @generated
         */
        EClass INTERACTION_RESOURCE = eINSTANCE.getInteractionResource();

        /**
         * The meta object literal for the '<em><b>User</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference INTERACTION_RESOURCE__USER = eINSTANCE.getInteractionResource_User();

        /**
         * The meta object literal for the '<em><b>Device</b></em>' container
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference INTERACTION_RESOURCE__DEVICE = eINSTANCE.getInteractionResource_Device();

        /**
         * The meta object literal for the '<em><b>Available</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute INTERACTION_RESOURCE__AVAILABLE = eINSTANCE.getInteractionResource_Available();

        /**
         * The meta object literal for the '<em><b>Modality</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute INTERACTION_RESOURCE__MODALITY = eINSTANCE.getInteractionResource_Modality();

        /**
         * The meta object literal for the '<em><b>Mobile</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute INTERACTION_RESOURCE__MOBILE = eINSTANCE.getInteractionResource_Mobile();

        /**
         * The meta object literal for the '<em><b>Personal</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute INTERACTION_RESOURCE__PERSONAL = eINSTANCE.getInteractionResource_Personal();

        /**
         * The meta object literal for the '<em><b>Interaction Status</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute INTERACTION_RESOURCE__INTERACTION_STATUS = eINSTANCE.getInteractionResource_InteractionStatus();

        /**
         * The meta object literal for the '
         * <em><b>Interaction Status Time Stamp</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute INTERACTION_RESOURCE__INTERACTION_STATUS_TIME_STAMP = eINSTANCE.getInteractionResource_InteractionStatusTimeStamp();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.EnvironmentImpl
         * <em>Environment</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.EnvironmentImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getEnvironment()
         * @generated
         */
        EClass ENVIRONMENT = eINSTANCE.getEnvironment();

        /**
         * The meta object literal for the '<em><b>Places</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ENVIRONMENT__PLACES = eINSTANCE.getEnvironment_Places();

        /**
         * The meta object literal for the '<em><b>Providers</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference ENVIRONMENT__PROVIDERS = eINSTANCE.getEnvironment_Providers();

        /**
         * The meta object literal for the '<em><b>Devices</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ENVIRONMENT__DEVICES = eINSTANCE.getEnvironment_Devices();

        /**
         * The meta object literal for the '<em><b>Users</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ENVIRONMENT__USERS = eINSTANCE.getEnvironment_Users();

        /**
         * The meta object literal for the '<em><b>Assistants</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference ENVIRONMENT__ASSISTANTS = eINSTANCE.getEnvironment_Assistants();

        /**
         * The meta object literal for the '<em><b>Initial Assistant</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ENVIRONMENT__INITIAL_ASSISTANT = eINSTANCE.getEnvironment_InitialAssistant();

        /**
         * The meta object literal for the '<em><b>Service Containers</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference ENVIRONMENT__SERVICE_CONTAINERS = eINSTANCE.getEnvironment_ServiceContainers();

        /**
         * The meta object literal for the '<em><b>Meters</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ENVIRONMENT__METERS = eINSTANCE.getEnvironment_Meters();

        /**
         * The meta object literal for the '<em><b>Discoverers</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference ENVIRONMENT__DISCOVERERS = eINSTANCE.getEnvironment_Discoverers();

        /**
         * The meta object literal for the '<em><b>Activities</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference ENVIRONMENT__ACTIVITIES = eINSTANCE.getEnvironment_Activities();

        /**
         * The meta object literal for the '<em><b>Tools</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ENVIRONMENT__TOOLS = eINSTANCE.getEnvironment_Tools();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ENVIRONMENT__NAME = eINSTANCE.getEnvironment_Name();

        /**
         * The meta object literal for the '<em><b>Description</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ENVIRONMENT__DESCRIPTION = eINSTANCE.getEnvironment_Description();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.UserImpl <em>User</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.UserImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getUser()
         * @generated
         */
        EClass USER = eINSTANCE.getUser();

        /**
         * The meta object literal for the '<em><b>Resources</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference USER__RESOURCES = eINSTANCE.getUser_Resources();

        /**
         * The meta object literal for the '<em><b>Line Of Vision</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference USER__LINE_OF_VISION = eINSTANCE.getUser_LineOfVision();

        /**
         * The meta object literal for the '<em><b>Ir Experience</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute USER__IR_EXPERIENCE = eINSTANCE.getUser_IrExperience();

        /**
         * The meta object literal for the '<em><b>Follow Me</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute USER__FOLLOW_ME = eINSTANCE.getUser_FollowMe();

        /**
         * The meta object literal for the '<em><b>Environment</b></em>'
         * container reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference USER__ENVIRONMENT = eINSTANCE.getUser_Environment();

        /**
         * The meta object literal for the '<em><b>Current Assistants</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference USER__CURRENT_ASSISTANTS = eINSTANCE.getUser_CurrentAssistants();

        /**
         * The meta object literal for the '<em><b>Past Assistants</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference USER__PAST_ASSISTANTS = eINSTANCE.getUser_PastAssistants();

        /**
         * The meta object literal for the '<em><b>Installed Assistants</b></em>
         * ' reference list feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @generated
         */
        EReference USER__INSTALLED_ASSISTANTS = eINSTANCE.getUser_InstalledAssistants();

        /**
         * The meta object literal for the '<em><b>Password Hash</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute USER__PASSWORD_HASH = eINSTANCE.getUser_PasswordHash();

        /**
         * The meta object literal for the '<em><b>Left Handed</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute USER__LEFT_HANDED = eINSTANCE.getUser_LeftHanded();

        /**
         * The meta object literal for the '<em><b>Birth Date</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute USER__BIRTH_DATE = eINSTANCE.getUser_BirthDate();

        /**
         * The meta object literal for the '<em><b>Surname</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute USER__SURNAME = eINSTANCE.getUser_Surname();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.AssistantImpl
         * <em>Assistant</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.sercho.masp.models.Context.impl.AssistantImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getAssistant()
         * @generated
         */
        EClass ASSISTANT = eINSTANCE.getAssistant();

        /**
         * The meta object literal for the '<em><b>Activities</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference ASSISTANT__ACTIVITIES = eINSTANCE.getAssistant_Activities();

        /**
         * The meta object literal for the '<em><b>Current Users</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ASSISTANT__CURRENT_USERS = eINSTANCE.getAssistant_CurrentUsers();

        /**
         * The meta object literal for the '<em><b>Past Users</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ASSISTANT__PAST_USERS = eINSTANCE.getAssistant_PastUsers();

        /**
         * The meta object literal for the '<em><b>Description</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ASSISTANT__DESCRIPTION = eINSTANCE.getAssistant_Description();

        /**
         * The meta object literal for the '<em><b>Connections</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference ASSISTANT__CONNECTIONS = eINSTANCE.getAssistant_Connections();

        /**
         * The meta object literal for the '<em><b>Domain</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ASSISTANT__DOMAIN = eINSTANCE.getAssistant_Domain();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.ActivityImpl
         * <em>Activity</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.sercho.masp.models.Context.impl.ActivityImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getActivity()
         * @generated
         */
        EClass ACTIVITY = eINSTANCE.getActivity();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ACTIVITY__NAME = eINSTANCE.getActivity_Name();

        /**
         * The meta object literal for the '<em><b>Description</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ACTIVITY__DESCRIPTION = eINSTANCE.getActivity_Description();

        /**
         * The meta object literal for the '<em><b>Used With</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ACTIVITY__USED_WITH = eINSTANCE.getActivity_UsedWith();

        /**
         * The meta object literal for the '<em><b>Configuration</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference ACTIVITY__CONFIGURATION = eINSTANCE.getActivity_Configuration();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.HomeOSAssistantImpl
         * <em>Home OS Assistant</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.HomeOSAssistantImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getHomeOSAssistant()
         * @generated
         */
        EClass HOME_OS_ASSISTANT = eINSTANCE.getHomeOSAssistant();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.ThirdPartyAssistantImpl
         * <em>Third Party Assistant</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.ThirdPartyAssistantImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getThirdPartyAssistant()
         * @generated
         */
        EClass THIRD_PARTY_ASSISTANT = eINSTANCE.getThirdPartyAssistant();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.LocalizationTagImpl
         * <em>Localization Tag</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.LocalizationTagImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getLocalizationTag()
         * @generated
         */
        EClass LOCALIZATION_TAG = eINSTANCE.getLocalizationTag();

        /**
         * The meta object literal for the '<em><b>Detected</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LOCALIZATION_TAG__DETECTED = eINSTANCE.getLocalizationTag_Detected();

        /**
         * The meta object literal for the '<em><b>Provider</b></em>' container
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LOCALIZATION_TAG__PROVIDER = eINSTANCE.getLocalizationTag_Provider();

        /**
         * The meta object literal for the '<em><b>Register</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LOCALIZATION_TAG__REGISTER = eINSTANCE.getLocalizationTag_Register();

        /**
         * The meta object literal for the '<em><b>Element</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LOCALIZATION_TAG__ELEMENT = eINSTANCE.getLocalizationTag_Element();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.EnvironmentElementImpl
         * <em>Environment Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.EnvironmentElementImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getEnvironmentElement()
         * @generated
         */
        EClass ENVIRONMENT_ELEMENT = eINSTANCE.getEnvironmentElement();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ENVIRONMENT_ELEMENT__ID = eINSTANCE.getEnvironmentElement_Id();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ENVIRONMENT_ELEMENT__NAME = eINSTANCE.getEnvironmentElement_Name();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.ElementWithPositionImpl
         * <em>Element With Position</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.ElementWithPositionImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getElementWithPosition()
         * @generated
         */
        EClass ELEMENT_WITH_POSITION = eINSTANCE.getElementWithPosition();

        /**
         * The meta object literal for the '<em><b>Tags</b></em>' reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ELEMENT_WITH_POSITION__TAGS = eINSTANCE.getElementWithPosition_Tags();

        /**
         * The meta object literal for the '<em><b>Position</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference ELEMENT_WITH_POSITION__POSITION = eINSTANCE.getElementWithPosition_Position();

        /**
         * The meta object literal for the '<em><b>Position Time Stamp</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ELEMENT_WITH_POSITION__POSITION_TIME_STAMP = eINSTANCE.getElementWithPosition_PositionTimeStamp();

        /**
         * The meta object literal for the '<em><b>Place</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ELEMENT_WITH_POSITION__PLACE = eINSTANCE.getElementWithPosition_Place();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.RoomImpl <em>Room</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.RoomImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getRoom()
         * @generated
         */
        EClass ROOM = eINSTANCE.getRoom();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ROOM__TYPE = eINSTANCE.getRoom_Type();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.ContextProviderImpl
         * <em>Provider</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.sercho.masp.models.Context.impl.ContextProviderImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getContextProvider()
         * @generated
         */
        EClass CONTEXT_PROVIDER = eINSTANCE.getContextProvider();

        /**
         * The meta object literal for the '<em><b>Environment</b></em>'
         * container reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference CONTEXT_PROVIDER__ENVIRONMENT = eINSTANCE.getContextProvider_Environment();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.LocalisationProviderProxyImpl
         * <em>Localisation Provider Proxy</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.LocalisationProviderProxyImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getLocalisationProviderProxy()
         * @generated
         */
        EClass LOCALISATION_PROVIDER_PROXY = eINSTANCE.getLocalisationProviderProxy();

        /**
         * The meta object literal for the '<em><b>Api Class</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LOCALISATION_PROVIDER_PROXY__API_CLASS = eINSTANCE.getLocalisationProviderProxy_ApiClass();

        /**
         * The meta object literal for the '<em><b>Api</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LOCALISATION_PROVIDER_PROXY__API = eINSTANCE.getLocalisationProviderProxy_Api();

        /**
         * The meta object literal for the '<em><b>Tags</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LOCALISATION_PROVIDER_PROXY__TAGS = eINSTANCE.getLocalisationProviderProxy_Tags();

        /**
         * The meta object literal for the '<em><b>Configuration</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference LOCALISATION_PROVIDER_PROXY__CONFIGURATION = eINSTANCE.getLocalisationProviderProxy_Configuration();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.MouseImpl <em>Mouse</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.MouseImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getMouse()
         * @generated
         */
        EClass MOUSE = eINSTANCE.getMouse();

        /**
         * The meta object literal for the '<em><b>Motion Sensor</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference MOUSE__MOTION_SENSOR = eINSTANCE.getMouse_MotionSensor();

        /**
         * The meta object literal for the '<em><b>Buttons</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference MOUSE__BUTTONS = eINSTANCE.getMouse_Buttons();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.KeyboardImpl
         * <em>Keyboard</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.sercho.masp.models.Context.impl.KeyboardImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getKeyboard()
         * @generated
         */
        EClass KEYBOARD = eINSTANCE.getKeyboard();

        /**
         * The meta object literal for the '<em><b>Keys</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference KEYBOARD__KEYS = eINSTANCE.getKeyboard_Keys();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.DisplayImpl
         * <em>Display</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.sercho.masp.models.Context.impl.DisplayImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getDisplay()
         * @generated
         */
        EClass DISPLAY = eINSTANCE.getDisplay();

        /**
         * The meta object literal for the '<em><b>XPixels</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DISPLAY__XPIXELS = eINSTANCE.getDisplay_XPixels();

        /**
         * The meta object literal for the '<em><b>YPixels</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DISPLAY__YPIXELS = eINSTANCE.getDisplay_YPixels();

        /**
         * The meta object literal for the '<em><b>Direction</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DISPLAY__DIRECTION = eINSTANCE.getDisplay_Direction();

        /**
         * The meta object literal for the '<em><b>Screen</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DISPLAY__SCREEN = eINSTANCE.getDisplay_Screen();

        /**
         * The meta object literal for the '<em><b>Size</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DISPLAY__SIZE = eINSTANCE.getDisplay_Size();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.InputInteractionResourceImpl
         * <em>Input Interaction Resource</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.InputInteractionResourceImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getInputInteractionResource()
         * @generated
         */
        EClass INPUT_INTERACTION_RESOURCE = eINSTANCE.getInputInteractionResource();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.OutputInteractionResourceImpl
         * <em>Output Interaction Resource</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.OutputInteractionResourceImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getOutputInteractionResource()
         * @generated
         */
        EClass OUTPUT_INTERACTION_RESOURCE = eINSTANCE.getOutputInteractionResource();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.GestureIRImpl
         * <em>Gesture IR</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.GestureIRImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getGestureIR()
         * @generated
         */
        EClass GESTURE_IR = eINSTANCE.getGestureIR();

        /**
         * The meta object literal for the '<em><b>Gesture Recognition</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference GESTURE_IR__GESTURE_RECOGNITION = eINSTANCE.getGestureIR_GestureRecognition();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.TouchpadImpl
         * <em>Touchpad</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.sercho.masp.models.Context.impl.TouchpadImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getTouchpad()
         * @generated
         */
        EClass TOUCHPAD = eINSTANCE.getTouchpad();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.DeviceImpl
         * <em>Device</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.sercho.masp.models.Context.impl.DeviceImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getDevice()
         * @generated
         */
        EClass DEVICE = eINSTANCE.getDevice();

        /**
         * The meta object literal for the '<em><b>Resources</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DEVICE__RESOURCES = eINSTANCE.getDevice_Resources();

        /**
         * The meta object literal for the '<em><b>Mobile</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DEVICE__MOBILE = eINSTANCE.getDevice_Mobile();

        /**
         * The meta object literal for the '<em><b>Environment</b></em>'
         * container reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DEVICE__ENVIRONMENT = eINSTANCE.getDevice_Environment();

        /**
         * The meta object literal for the '<em><b>Manufacturer</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DEVICE__MANUFACTURER = eINSTANCE.getDevice_Manufacturer();

        /**
         * The meta object literal for the '<em><b>Model Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DEVICE__MODEL_NAME = eINSTANCE.getDevice_ModelName();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.ChannelImpl
         * <em>Channel</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.sercho.masp.models.Context.impl.ChannelImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getChannel()
         * @generated
         */
        EClass CHANNEL = eINSTANCE.getChannel();

        /**
         * The meta object literal for the '<em><b>Distribution State</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CHANNEL__DISTRIBUTION_STATE = eINSTANCE.getChannel_DistributionState();

        /**
         * The meta object literal for the '<em><b>Elements</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CHANNEL__ELEMENTS = eINSTANCE.getChannel_Elements();

        /**
         * The meta object literal for the '<em><b>Api</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CHANNEL__API = eINSTANCE.getChannel_Api();

        /**
         * The meta object literal for the '<em><b>Configuration</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference CHANNEL__CONFIGURATION = eINSTANCE.getChannel_Configuration();

        /**
         * The meta object literal for the '<em><b>Available</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CHANNEL__AVAILABLE = eINSTANCE.getChannel_Available();

        /**
         * The meta object literal for the '<em><b>Api Class</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CHANNEL__API_CLASS = eINSTANCE.getChannel_ApiClass();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.PointingInputChannelImpl
         * <em>Pointing Input Channel</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.PointingInputChannelImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getPointingInputChannel()
         * @generated
         */
        EClass POINTING_INPUT_CHANNEL = eINSTANCE.getPointingInputChannel();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.MessageInputChannelImpl
         * <em>Message Input Channel</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.MessageInputChannelImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getMessageInputChannel()
         * @generated
         */
        EClass MESSAGE_INPUT_CHANNEL = eINSTANCE.getMessageInputChannel();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.MessageOutputChannelImpl
         * <em>Message Output Channel</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.MessageOutputChannelImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getMessageOutputChannel()
         * @generated
         */
        EClass MESSAGE_OUTPUT_CHANNEL = eINSTANCE.getMessageOutputChannel();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.GraphicalOutputChannelImpl
         * <em>Graphical Output Channel</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.GraphicalOutputChannelImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getGraphicalOutputChannel()
         * @generated
         */
        EClass GRAPHICAL_OUTPUT_CHANNEL = eINSTANCE.getGraphicalOutputChannel();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.LoudspeakerImpl
         * <em>Loudspeaker</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.LoudspeakerImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getLoudspeaker()
         * @generated
         */
        EClass LOUDSPEAKER = eINSTANCE.getLoudspeaker();

        /**
         * The meta object literal for the '<em><b>Voice</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LOUDSPEAKER__VOICE = eINSTANCE.getLoudspeaker_Voice();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.MicrophoneImpl
         * <em>Microphone</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.MicrophoneImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getMicrophone()
         * @generated
         */
        EClass MICROPHONE = eINSTANCE.getMicrophone();

        /**
         * The meta object literal for the '<em><b>Voice Recognition</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference MICROPHONE__VOICE_RECOGNITION = eINSTANCE.getMicrophone_VoiceRecognition();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.VectorImpl
         * <em>Vector</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.sercho.masp.models.Context.impl.VectorImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getVector()
         * @generated
         */
        EClass VECTOR = eINSTANCE.getVector();

        /**
         * The meta object literal for the '<em><b>X</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VECTOR__X = eINSTANCE.getVector_X();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VECTOR__Y = eINSTANCE.getVector_Y();

        /**
         * The meta object literal for the '<em><b>Z</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VECTOR__Z = eINSTANCE.getVector_Z();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.AreaImpl <em>Area</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.AreaImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getArea()
         * @generated
         */
        EClass AREA = eINSTANCE.getArea();

        /**
         * The meta object literal for the '<em><b>Origin</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference AREA__ORIGIN = eINSTANCE.getArea_Origin();

        /**
         * The meta object literal for the '<em><b>Span</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference AREA__SPAN = eINSTANCE.getArea_Span();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.HapticalInteractionResourcesImpl
         * <em>Haptical Interaction Resources</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.HapticalInteractionResourcesImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getHapticalInteractionResources()
         * @generated
         */
        EClass HAPTICAL_INTERACTION_RESOURCES = eINSTANCE.getHapticalInteractionResources();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.ConfigurationPropertyImpl
         * <em>Configuration Property</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.ConfigurationPropertyImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getConfigurationProperty()
         * @generated
         */
        EClass CONFIGURATION_PROPERTY = eINSTANCE.getConfigurationProperty();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CONFIGURATION_PROPERTY__KEY = eINSTANCE.getConfigurationProperty_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CONFIGURATION_PROPERTY__VALUE = eINSTANCE.getConfigurationProperty_Value();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.HeaterImpl
         * <em>Heater</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.sercho.masp.models.Context.impl.HeaterImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getHeater()
         * @generated
         */
        EClass HEATER = eINSTANCE.getHeater();

        /**
         * The meta object literal for the '<em><b>Temperature Demand</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference HEATER__TEMPERATURE_DEMAND = eINSTANCE.getHeater_TemperatureDemand();

        /**
         * The meta object literal for the '<em><b>Temperature Current</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference HEATER__TEMPERATURE_CURRENT = eINSTANCE.getHeater_TemperatureCurrent();

        /**
         * The meta object literal for the '<em><b>Valve Position</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference HEATER__VALVE_POSITION = eINSTANCE.getHeater_ValvePosition();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.MeterImpl <em>Meter</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.MeterImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getMeter()
         * @generated
         */
        EClass METER = eINSTANCE.getMeter();

        /**
         * The meta object literal for the '<em><b>Readings</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference METER__READINGS = eINSTANCE.getMeter_Readings();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.WaterStorageTankImpl
         * <em>Water Storage Tank</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.WaterStorageTankImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getWaterStorageTank()
         * @generated
         */
        EClass WATER_STORAGE_TANK = eINSTANCE.getWaterStorageTank();

        /**
         * The meta object literal for the '<em><b>Temperature Sensor</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference WATER_STORAGE_TANK__TEMPERATURE_SENSOR = eINSTANCE.getWaterStorageTank_TemperatureSensor();

        /**
         * The meta object literal for the '<em><b>Capacity</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute WATER_STORAGE_TANK__CAPACITY = eINSTANCE.getWaterStorageTank_Capacity();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.HeatingRodImpl
         * <em>Heating Rod</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.HeatingRodImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getHeatingRod()
         * @generated
         */
        EClass HEATING_ROD = eINSTANCE.getHeatingRod();

        /**
         * The meta object literal for the '<em><b>Maximum Power Watts</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute HEATING_ROD__MAXIMUM_POWER_WATTS = eINSTANCE.getHeatingRod_MaximumPowerWatts();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.MeterReadingImpl
         * <em>Meter Reading</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.MeterReadingImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getMeterReading()
         * @generated
         */
        EClass METER_READING = eINSTANCE.getMeterReading();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference METER_READING__VALUE = eINSTANCE.getMeterReading_Value();

        /**
         * The meta object literal for the '<em><b>Subject</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute METER_READING__SUBJECT = eINSTANCE.getMeterReading_Subject();

        /**
         * The meta object literal for the '<em><b>Measurand</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute METER_READING__MEASURAND = eINSTANCE.getMeterReading_Measurand();

        /**
         * The meta object literal for the '<em><b>Measurement Method</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute METER_READING__MEASUREMENT_METHOD = eINSTANCE.getMeterReading_MeasurementMethod();

        /**
         * The meta object literal for the '<em><b>Rate</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute METER_READING__RATE = eINSTANCE.getMeterReading_Rate();

        /**
         * The meta object literal for the '<em><b>Identifier</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute METER_READING__IDENTIFIER = eINSTANCE.getMeterReading_Identifier();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.PhysicalSensorDeviceImpl
         * <em>Physical Sensor Device</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.PhysicalSensorDeviceImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getPhysicalSensorDevice()
         * @generated
         */
        EClass PHYSICAL_SENSOR_DEVICE = eINSTANCE.getPhysicalSensorDevice();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PHYSICAL_SENSOR_DEVICE__VALUE = eINSTANCE.getPhysicalSensorDevice_Value();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.LuminanceSensorImpl
         * <em>Luminance Sensor</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.LuminanceSensorImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getLuminanceSensor()
         * @generated
         */
        EClass LUMINANCE_SENSOR = eINSTANCE.getLuminanceSensor();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.TemperatureSensorImpl
         * <em>Temperature Sensor</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.TemperatureSensorImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getTemperatureSensor()
         * @generated
         */
        EClass TEMPERATURE_SENSOR = eINSTANCE.getTemperatureSensor();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.WashingMachineImpl
         * <em>Washing Machine</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.WashingMachineImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getWashingMachine()
         * @generated
         */
        EClass WASHING_MACHINE = eINSTANCE.getWashingMachine();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.AssistantConnectionImpl
         * <em>Assistant Connection</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.AssistantConnectionImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getAssistantConnection()
         * @generated
         */
        EClass ASSISTANT_CONNECTION = eINSTANCE.getAssistantConnection();

        /**
         * The meta object literal for the '<em><b>Description</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ASSISTANT_CONNECTION__DESCRIPTION = eINSTANCE.getAssistantConnection_Description();

        /**
         * The meta object literal for the '<em><b>Connected Assistant</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ASSISTANT_CONNECTION__CONNECTED_ASSISTANT = eINSTANCE.getAssistantConnection_ConnectedAssistant();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.ToolImpl <em>Tool</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.ToolImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getTool()
         * @generated
         */
        EClass TOOL = eINSTANCE.getTool();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TOOL__NAME = eINSTANCE.getTool_Name();

        /**
         * The meta object literal for the '<em><b>Description</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TOOL__DESCRIPTION = eINSTANCE.getTool_Description();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.DeviceToolImpl
         * <em>Device Tool</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.DeviceToolImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getDeviceTool()
         * @generated
         */
        EClass DEVICE_TOOL = eINSTANCE.getDeviceTool();

        /**
         * The meta object literal for the '<em><b>Controls</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DEVICE_TOOL__CONTROLS = eINSTANCE.getDeviceTool_Controls();

        /**
         * The meta object literal for the '<em><b>Applies To</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DEVICE_TOOL__APPLIES_TO = eINSTANCE.getDeviceTool_AppliesTo();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.Need <em>Need</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.Need
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getNeed()
         * @generated
         */
        EClass NEED = eINSTANCE.getNeed();

        /**
         * The meta object literal for the '<em><b>Fulfilled By</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference NEED__FULFILLED_BY = eINSTANCE.getNeed_FulfilledBy();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NEED__NAME = eINSTANCE.getNeed_Name();

        /**
         * The meta object literal for the '<em><b>Description</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NEED__DESCRIPTION = eINSTANCE.getNeed_Description();

        /**
         * The meta object literal for the '<em><b>Configuration</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference NEED__CONFIGURATION = eINSTANCE.getNeed_Configuration();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.DoorImpl <em>Door</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.DoorImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getDoor()
         * @generated
         */
        EClass DOOR = eINSTANCE.getDoor();

        /**
         * The meta object literal for the '<em><b>Open</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DOOR__OPEN = eINSTANCE.getDoor_Open();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' container
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DOOR__SOURCE = eINSTANCE.getDoor_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DOOR__TARGET = eINSTANCE.getDoor_Target();

        /**
         * The meta object literal for the '<em><b>Span</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DOOR__SPAN = eINSTANCE.getDoor_Span();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.WindowImpl
         * <em>Window</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.sercho.masp.models.Context.impl.WindowImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getWindow()
         * @generated
         */
        EClass WINDOW = eINSTANCE.getWindow();

        /**
         * The meta object literal for the '<em><b>Open</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference WINDOW__OPEN = eINSTANCE.getWindow_Open();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' container
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference WINDOW__SOURCE = eINSTANCE.getWindow_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference WINDOW__TARGET = eINSTANCE.getWindow_Target();

        /**
         * The meta object literal for the '<em><b>Span</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference WINDOW__SPAN = eINSTANCE.getWindow_Span();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.PlaceImpl <em>Place</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.PlaceImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getPlace()
         * @generated
         */
        EClass PLACE = eINSTANCE.getPlace();

        /**
         * The meta object literal for the '<em><b>Areas</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PLACE__AREAS = eINSTANCE.getPlace_Areas();

        /**
         * The meta object literal for the '<em><b>Elements</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PLACE__ELEMENTS = eINSTANCE.getPlace_Elements();

        /**
         * The meta object literal for the '<em><b>Environment</b></em>'
         * container reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PLACE__ENVIRONMENT = eINSTANCE.getPlace_Environment();

        /**
         * The meta object literal for the '<em><b>Doors</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PLACE__DOORS = eINSTANCE.getPlace_Doors();

        /**
         * The meta object literal for the '<em><b>Windows</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PLACE__WINDOWS = eINSTANCE.getPlace_Windows();

        /**
         * The meta object literal for the '<em><b>Floor</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PLACE__FLOOR = eINSTANCE.getPlace_Floor();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.OutdoorsImpl
         * <em>Outdoors</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.sercho.masp.models.Context.impl.OutdoorsImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getOutdoors()
         * @generated
         */
        EClass OUTDOORS = eINSTANCE.getOutdoors();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.TouchscreenImpl
         * <em>Touchscreen</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.TouchscreenImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getTouchscreen()
         * @generated
         */
        EClass TOUCHSCREEN = eINSTANCE.getTouchscreen();

        /**
         * The meta object literal for the '<em><b>Touch Surface</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TOUCHSCREEN__TOUCH_SURFACE = eINSTANCE.getTouchscreen_TouchSurface();

        /**
         * The meta object literal for the '<em><b>Gesture Recognition</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TOUCHSCREEN__GESTURE_RECOGNITION = eINSTANCE.getTouchscreen_GestureRecognition();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.PhysicalDeviceImpl
         * <em>Physical Device</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.PhysicalDeviceImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getPhysicalDevice()
         * @generated
         */
        EClass PHYSICAL_DEVICE = eINSTANCE.getPhysicalDevice();

        /**
         * The meta object literal for the '<em><b>On</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PHYSICAL_DEVICE__ON = eINSTANCE.getPhysicalDevice_On();

        /**
         * The meta object literal for the '<em><b>Power Usage</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PHYSICAL_DEVICE__POWER_USAGE = eINSTANCE.getPhysicalDevice_PowerUsage();

        /**
         * The meta object literal for the '<em><b>Sub Device</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PHYSICAL_DEVICE__SUB_DEVICE = eINSTANCE.getPhysicalDevice_SubDevice();

        /**
         * The meta object literal for the '<em><b>Parent Device</b></em>'
         * container reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PHYSICAL_DEVICE__PARENT_DEVICE = eINSTANCE.getPhysicalDevice_ParentDevice();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.PhysicalDeviceWithProgramImpl
         * <em>Physical Device With Program</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.PhysicalDeviceWithProgramImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getPhysicalDeviceWithProgram()
         * @generated
         */
        EClass PHYSICAL_DEVICE_WITH_PROGRAM = eINSTANCE.getPhysicalDeviceWithProgram();

        /**
         * The meta object literal for the '<em><b>Program</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PHYSICAL_DEVICE_WITH_PROGRAM__PROGRAM = eINSTANCE.getPhysicalDeviceWithProgram_Program();

        /**
         * The meta object literal for the '<em><b>Seconds Remaining</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PHYSICAL_DEVICE_WITH_PROGRAM__SECONDS_REMAINING = eINSTANCE.getPhysicalDeviceWithProgram_SecondsRemaining();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.LampImpl <em>Lamp</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.LampImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getLamp()
         * @generated
         */
        EClass LAMP = eINSTANCE.getLamp();

        /**
         * The meta object literal for the '<em><b>Dimming Level</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference LAMP__DIMMING_LEVEL = eINSTANCE.getLamp_DimmingLevel();

        /**
         * The meta object literal for the '<em><b>Lamp Type</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LAMP__LAMP_TYPE = eINSTANCE.getLamp_LampType();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.NotebookImpl
         * <em>Notebook</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.sercho.masp.models.Context.impl.NotebookImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getNotebook()
         * @generated
         */
        EClass NOTEBOOK = eINSTANCE.getNotebook();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.HoodImpl <em>Hood</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.HoodImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getHood()
         * @generated
         */
        EClass HOOD = eINSTANCE.getHood();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.PCImpl <em>PC</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.PCImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getPC()
         * @generated
         */
        EClass PC = eINSTANCE.getPC();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.FanImpl <em>Fan</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.FanImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getFan()
         * @generated
         */
        EClass FAN = eINSTANCE.getFan();

        /**
         * The meta object literal for the '<em><b>Speed</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference FAN__SPEED = eINSTANCE.getFan_Speed();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.RemoteControlImpl
         * <em>Remote Control</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.RemoteControlImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getRemoteControl()
         * @generated
         */
        EClass REMOTE_CONTROL = eINSTANCE.getRemoteControl();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.TVImpl <em>TV</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.TVImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getTV()
         * @generated
         */
        EClass TV = eINSTANCE.getTV();

        /**
         * The meta object literal for the '<em><b>Current Program</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TV__CURRENT_PROGRAM = eINSTANCE.getTV_CurrentProgram();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.OvenImpl <em>Oven</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.OvenImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getOven()
         * @generated
         */
        EClass OVEN = eINSTANCE.getOven();

        /**
         * The meta object literal for the '<em><b>Temperature</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference OVEN__TEMPERATURE = eINSTANCE.getOven_Temperature();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.DishwasherImpl
         * <em>Dishwasher</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.DishwasherImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getDishwasher()
         * @generated
         */
        EClass DISHWASHER = eINSTANCE.getDishwasher();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.BlindImpl <em>Blind</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.BlindImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getBlind()
         * @generated
         */
        EClass BLIND = eINSTANCE.getBlind();

        /**
         * The meta object literal for the '<em><b>Level</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference BLIND__LEVEL = eINSTANCE.getBlind_Level();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.HobImpl <em>Hob</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.HobImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getHob()
         * @generated
         */
        EClass HOB = eINSTANCE.getHob();

        /**
         * The meta object literal for the '<em><b>Heat Level</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference HOB__HEAT_LEVEL = eINSTANCE.getHob_HeatLevel();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.CookerImpl
         * <em>Cooker</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.sercho.masp.models.Context.impl.CookerImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getCooker()
         * @generated
         */
        EClass COOKER = eINSTANCE.getCooker();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.CookTopImpl
         * <em>Cook Top</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.sercho.masp.models.Context.impl.CookTopImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getCookTop()
         * @generated
         */
        EClass COOK_TOP = eINSTANCE.getCookTop();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.FridgeImpl
         * <em>Fridge</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.sercho.masp.models.Context.impl.FridgeImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getFridge()
         * @generated
         */
        EClass FRIDGE = eINSTANCE.getFridge();

        /**
         * The meta object literal for the '<em><b>Temperature</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference FRIDGE__TEMPERATURE = eINSTANCE.getFridge_Temperature();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.MixerImpl <em>Mixer</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.MixerImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getMixer()
         * @generated
         */
        EClass MIXER = eINSTANCE.getMixer();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.RadioImpl <em>Radio</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.impl.RadioImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getRadio()
         * @generated
         */
        EClass RADIO = eINSTANCE.getRadio();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.impl.SocketImpl
         * <em>Socket</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.sercho.masp.models.Context.impl.SocketImpl
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getSocket()
         * @generated
         */
        EClass SOCKET = eINSTANCE.getSocket();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.DistributionState
         * <em>Distribution State</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.DistributionState
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getDistributionState()
         * @generated
         */
        EEnum DISTRIBUTION_STATE = eINSTANCE.getDistributionState();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.Modality <em>Modality</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.Modality
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getModality()
         * @generated
         */
        EEnum MODALITY = eINSTANCE.getModality();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.Domain <em>Domain</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.Domain
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getDomain()
         * @generated
         */
        EEnum DOMAIN = eINSTANCE.getDomain();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.LampType <em>Lamp Type</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.LampType
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getLampType()
         * @generated
         */
        EEnum LAMP_TYPE = eINSTANCE.getLampType();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.RoomType <em>Room Type</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.RoomType
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getRoomType()
         * @generated
         */
        EEnum ROOM_TYPE = eINSTANCE.getRoomType();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.ReadingSubject
         * <em>Reading Subject</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.ReadingSubject
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getReadingSubject()
         * @generated
         */
        EEnum READING_SUBJECT = eINSTANCE.getReadingSubject();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.ReadingMeasurand
         * <em>Reading Measurand</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.ReadingMeasurand
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getReadingMeasurand()
         * @generated
         */
        EEnum READING_MEASURAND = eINSTANCE.getReadingMeasurand();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.ReadingMeasurementMethod
         * <em>Reading Measurement Method</em>}' enum. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.ReadingMeasurementMethod
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getReadingMeasurementMethod()
         * @generated
         */
        EEnum READING_MEASUREMENT_METHOD = eINSTANCE.getReadingMeasurementMethod();

        /**
         * The meta object literal for the '
         * {@link org.sercho.masp.models.Context.ReadingRate
         * <em>Reading Rate</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.ReadingRate
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getReadingRate()
         * @generated
         */
        EEnum READING_RATE = eINSTANCE.getReadingRate();

        /**
         * The meta object literal for the '<em>Channel API</em>' data type.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.channel.api.ChannelAPI
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getChannelAPI()
         * @generated
         */
        EDataType CHANNEL_API = eINSTANCE.getChannelAPI();

        /**
         * The meta object literal for the '<em>Message Input Channel API</em>'
         * data type. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.channel.api.MessageInputChannelAPI
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getMessageInputChannelAPI()
         * @generated
         */
        EDataType MESSAGE_INPUT_CHANNEL_API = eINSTANCE.getMessageInputChannelAPI();

        /**
         * The meta object literal for the '<em>Pointing Input Channel API</em>'
         * data type. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.channel.api.PointingInputChannelAPI
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getPointingInputChannelAPI()
         * @generated
         */
        EDataType POINTING_INPUT_CHANNEL_API = eINSTANCE.getPointingInputChannelAPI();

        /**
         * The meta object literal for the '<em>Message Output Channel API</em>'
         * data type. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.channel.api.MessageOutputChannelAPI
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getMessageOutputChannelAPI()
         * @generated
         */
        EDataType MESSAGE_OUTPUT_CHANNEL_API = eINSTANCE.getMessageOutputChannelAPI();

        /**
         * The meta object literal for the '
         * <em>Graphical Output Channel API</em>' data type. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.channel.api.GraphicalOutputChannelAPI
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getGraphicalOutputChannelAPI()
         * @generated
         */
        EDataType GRAPHICAL_OUTPUT_CHANNEL_API = eINSTANCE.getGraphicalOutputChannelAPI();

        /**
         * The meta object literal for the '<em>Localisation Provider</em>' data
         * type. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.context.providers.location.LocalisationProvider
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getLocalisationProvider()
         * @generated
         */
        EDataType LOCALISATION_PROVIDER = eINSTANCE.getLocalisationProvider();

        /**
         * The meta object literal for the '<em>Place Info</em>' data type. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.sercho.masp.models.Context.PlaceInfo
         * @see org.sercho.masp.models.Context.impl.ContextPackageImpl#getPlaceInfo()
         * @generated
         */
        EDataType PLACE_INFO = eINSTANCE.getPlaceInfo();

    }

} // ContextPackage
