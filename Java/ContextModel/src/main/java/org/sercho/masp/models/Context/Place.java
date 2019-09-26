/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Place</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Place#getAreas <em>Areas</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Place#getElements <em>Elements
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Place#getEnvironment <em>
 * Environment</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Place#getDoors <em>Doors</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Place#getWindows <em>Windows</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Place#getFloor <em>Floor</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getPlace()
 * @model abstract="true"
 * @generated
 */
public interface Place extends EnvironmentElement {

    /**
     * Returns the value of the '<em><b>Areas</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.sercho.masp.models.Context.Area}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Areas</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Areas</em>' containment reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getPlace_Areas()
     * @model containment="true" required="true"
     * @generated
     */
    EList<Area> getAreas();

    /**
     * Returns the value of the '<em><b>Elements</b></em>' reference list. The
     * list contents are of type
     * {@link org.sercho.masp.models.Context.ElementWithPosition}. It is
     * bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.ElementWithPosition#getPlace
     * <em>Place</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Elements</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Elements</em>' reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getPlace_Elements()
     * @see org.sercho.masp.models.Context.ElementWithPosition#getPlace
     * @model opposite="place"
     * @generated
     */
    EList<ElementWithPosition> getElements();

    /**
     * Returns the value of the '<em><b>Environment</b></em>' container
     * reference. It is bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.Environment#getPlaces
     * <em>Places</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Environment</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Environment</em>' container reference.
     * @see #setEnvironment(Environment)
     * @see org.sercho.masp.models.Context.ContextPackage#getPlace_Environment()
     * @see org.sercho.masp.models.Context.Environment#getPlaces
     * @model opposite="places" transient="false"
     * @generated
     */
    Environment getEnvironment();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Place#getEnvironment
     * <em>Environment</em>}' container reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Environment</em>' container
     *            reference.
     * @see #getEnvironment()
     * @generated
     */
    void setEnvironment(Environment value);

    /**
     * Returns the value of the '<em><b>Doors</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.sercho.masp.models.Context.Door}. It is bidirectional and its
     * opposite is '{@link org.sercho.masp.models.Context.Door#getSource
     * <em>Source</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Doors</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Doors</em>' containment reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getPlace_Doors()
     * @see org.sercho.masp.models.Context.Door#getSource
     * @model opposite="source" containment="true"
     * @generated
     */
    EList<Door> getDoors();

    /**
     * Returns the value of the '<em><b>Windows</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.sercho.masp.models.Context.Window}. It is bidirectional and
     * its opposite is '{@link org.sercho.masp.models.Context.Window#getSource
     * <em>Source</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Windows</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Windows</em>' containment reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getPlace_Windows()
     * @see org.sercho.masp.models.Context.Window#getSource
     * @model opposite="source" containment="true"
     * @generated
     */
    EList<Window> getWindows();

    /**
     * Returns the value of the '<em><b>Floor</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Floor</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Floor</em>' attribute.
     * @see #setFloor(Integer)
     * @see org.sercho.masp.models.Context.ContextPackage#getPlace_Floor()
     * @model
     * @generated
     */
    Integer getFloor();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Place#getFloor <em>Floor</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Floor</em>' attribute.
     * @see #getFloor()
     * @generated
     */
    void setFloor(Integer value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    boolean contains(Vector position);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model kind="operation"
     * @generated
     */
    EList<InteractionResource> getInteractionResources();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model kind="operation"
     * @generated
     */
    EList<User> getUsers();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Adds a Area to the Place. The source and span are created by the given
     * parameters. <!-- end-model-doc -->
     * 
     * @model originXRequired="true" originYRequired="true"
     *        originZRequired="true" spanXRequired="true" spanYRequired="true"
     *        spanZRequired="true" annotation="Definition modifies='areas'"
     * @generated
     */
    Area addArea(double originX, double originY, double originZ, double spanX, double spanY, double spanZ);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model areaRequired="true" annotation="Definition modifies='areas'"
     * @generated
     */
    void removeArea(Area area);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model elementRequired="true" annotation="Definition modifies='elements'"
     * @generated
     */
    void addElement(ElementWithPosition element);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model elementRequired="true" annotation="Definition modifies='elements'"
     * @generated
     */
    void removeElement(ElementWithPosition element);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Adds a Door to the Place. The source and span are created by the given
     * parameters. <!-- end-model-doc -->
     * 
     * @model annotation="Definition modifies='doors'"
     * @generated
     */
    Door addDoor(String id, String name, double positionX, double positionY, double positionZ, double spanX, double spanY, double spanZ);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Adds a Window to the Place. The source and span are created by the given
     * parameters. <!-- end-model-doc -->
     * 
     * @model annotation="Definition modifies='windows'"
     * @generated
     */
    Window addWindow(String id, String name, double positionX, double positionY, double positionZ, double spanX, double spanY, double spanZ);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model annotation="Definition modifies='doors'"
     * @generated
     */
    void removeDoor(Door door);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model annotation="Definition modifies='windows'"
     * @generated
     */
    void removeWindow(Window window);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model newFloorRequired="true" annotation="Definition modifies='floor'"
     * @generated
     */
    void setNewFloor(Integer newFloor);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model kind="operation" required="true"
     * @generated
     */
    Double getTemperatureAverage();

} // Place
