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
 * <em><b>Device</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Device#getResources <em>Resources
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Device#isMobile <em>Mobile</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Device#getEnvironment <em>
 * Environment</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Device#getManufacturer <em>
 * Manufacturer</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Device#getModelName <em>Model Name
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getDevice()
 * @model
 * @generated
 */
public interface Device extends ElementWithPosition {

    /**
     * Returns the value of the '<em><b>Resources</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.sercho.masp.models.Context.InteractionResource}. It is
     * bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.InteractionResource#getDevice
     * <em>Device</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resources</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Resources</em>' containment reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getDevice_Resources()
     * @see org.sercho.masp.models.Context.InteractionResource#getDevice
     * @model opposite="device" containment="true"
     * @generated
     */
    EList<InteractionResource> getResources();

    /**
     * Returns the value of the '<em><b>Mobile</b></em>' attribute. The default
     * value is <code>"false"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mobile</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Mobile</em>' attribute.
     * @see #setMobile(boolean)
     * @see org.sercho.masp.models.Context.ContextPackage#getDevice_Mobile()
     * @model default="false"
     * @generated
     */
    boolean isMobile();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Device#isMobile <em>Mobile</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Mobile</em>' attribute.
     * @see #isMobile()
     * @generated
     */
    void setMobile(boolean value);

    /**
     * Returns the value of the '<em><b>Environment</b></em>' container
     * reference. It is bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.Environment#getDevices
     * <em>Devices</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Environment</em>' container reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Environment</em>' container reference.
     * @see #setEnvironment(Environment)
     * @see org.sercho.masp.models.Context.ContextPackage#getDevice_Environment()
     * @see org.sercho.masp.models.Context.Environment#getDevices
     * @model opposite="devices" transient="false"
     * @generated
     */
    @Override
    Environment getEnvironment();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Device#getEnvironment
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
     * Returns the value of the '<em><b>Manufacturer</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Manufacturer</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Manufacturer</em>' attribute.
     * @see #setManufacturer(String)
     * @see org.sercho.masp.models.Context.ContextPackage#getDevice_Manufacturer()
     * @model
     * @generated
     */
    String getManufacturer();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Device#getManufacturer
     * <em>Manufacturer</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Manufacturer</em>' attribute.
     * @see #getManufacturer()
     * @generated
     */
    void setManufacturer(String value);

    /**
     * Returns the value of the '<em><b>Model Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Model Name</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Model Name</em>' attribute.
     * @see #setModelName(String)
     * @see org.sercho.masp.models.Context.ContextPackage#getDevice_ModelName()
     * @model
     * @generated
     */
    String getModelName();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Device#getModelName
     * <em>Model Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Model Name</em>' attribute.
     * @see #getModelName()
     * @generated
     */
    void setModelName(String value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The id must be set only if the id of the interactionResource is
     * <code>null</code>. <!-- end-model-doc -->
     * 
     * @model interactionResourceRequired="true"
     *        annotation="Definition modifies='resources'"
     * @generated
     */
    void addInteractionResource(InteractionResource interactionResource, String id);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model interactionResourceRequired="true"
     *        annotation="Definition modifies='resources'"
     * @generated
     */
    void removeInteractionResource(InteractionResource interactionResource);

} // Device
