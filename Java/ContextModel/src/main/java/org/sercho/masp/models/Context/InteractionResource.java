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
 * <em><b>Interaction Resource</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.InteractionResource#getUser <em>
 * User</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.InteractionResource#getDevice <em>
 * Device</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.InteractionResource#isAvailable
 * <em>Available</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.InteractionResource#getModality
 * <em>Modality</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.InteractionResource#getMobile <em>
 * Mobile</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.InteractionResource#getPersonal
 * <em>Personal</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.InteractionResource#isInteractionStatus
 * <em>Interaction Status</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.InteractionResource#getInteractionStatusTimeStamp
 * <em>Interaction Status Time Stamp</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getInteractionResource()
 * @model abstract="true"
 * @generated
 */
public interface InteractionResource extends ElementWithPosition {

    /**
     * Returns the value of the '<em><b>User</b></em>' reference. It is
     * bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.User#getResources
     * <em>Resources</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>User</em>' reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>User</em>' reference.
     * @see #setUser(User)
     * @see org.sercho.masp.models.Context.ContextPackage#getInteractionResource_User()
     * @see org.sercho.masp.models.Context.User#getResources
     * @model opposite="resources"
     * @generated
     */
    User getUser();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.InteractionResource#getUser
     * <em>User</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>User</em>' reference.
     * @see #getUser()
     * @generated
     */
    void setUser(User value);

    /**
     * Returns the value of the '<em><b>Device</b></em>' container reference. It
     * is bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.Device#getResources
     * <em>Resources</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Device</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Device</em>' container reference.
     * @see #setDevice(Device)
     * @see org.sercho.masp.models.Context.ContextPackage#getInteractionResource_Device()
     * @see org.sercho.masp.models.Context.Device#getResources
     * @model opposite="resources" required="true" transient="false"
     * @generated
     */
    Device getDevice();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.InteractionResource#getDevice
     * <em>Device</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Device</em>' reference.
     * @see #getDevice()
     * @generated
     */
    void setDevice(Device value);

    /**
     * Returns the value of the '<em><b>Available</b></em>' attribute. The
     * default value is <code>"false"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Available</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Available</em>' attribute.
     * @see #setAvailable(boolean)
     * @see org.sercho.masp.models.Context.ContextPackage#getInteractionResource_Available()
     * @model default="false" required="true" transient="true" derived="true"
     * @generated
     */
    boolean isAvailable();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.InteractionResource#isAvailable
     * <em>Available</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Available</em>' attribute.
     * @see #isAvailable()
     * @generated
     */
    void setAvailable(boolean value);

    /**
     * Returns the value of the '<em><b>Modality</b></em>' attribute. The
     * default value is <code>"Graphic"</code>. The literals are from the
     * enumeration {@link org.sercho.masp.models.Context.Modality}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Modality</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Modality</em>' attribute.
     * @see org.sercho.masp.models.Context.Modality
     * @see org.sercho.masp.models.Context.ContextPackage#getInteractionResource_Modality()
     * @model default="Graphic" required="true" changeable="false"
     * @generated
     */
    Modality getModality();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.InteractionResource#getModality
     * <em>Modality</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Modality</em>' attribute.
     * @see org.sercho.masp.models.Context.Modality
     * @see #getModality()
     * @generated
     */
    void setModality(Modality value);

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
     * @see #setMobile(Boolean)
     * @see org.sercho.masp.models.Context.ContextPackage#getInteractionResource_Mobile()
     * @model default="false"
     * @generated
     */
    Boolean getMobile();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.InteractionResource#getMobile
     * <em>Mobile</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Mobile</em>' attribute.
     * @see #getMobile()
     * @generated
     */
    void setMobile(Boolean value);

    /**
     * Returns the value of the '<em><b>Personal</b></em>' attribute. The
     * default value is <code>""</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Personal</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Personal</em>' attribute.
     * @see #setPersonal(String)
     * @see org.sercho.masp.models.Context.ContextPackage#getInteractionResource_Personal()
     * @model default=""
     * @generated
     */
    String getPersonal();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.InteractionResource#getPersonal
     * <em>Personal</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Personal</em>' attribute.
     * @see #getPersonal()
     * @generated
     */
    void setPersonal(String value);

    /**
     * Returns the value of the '<em><b>Interaction Status</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Interaction Status</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Interaction Status</em>' attribute.
     * @see #setInteractionStatus(boolean)
     * @see org.sercho.masp.models.Context.ContextPackage#getInteractionResource_InteractionStatus()
     * @model
     * @generated
     */
    boolean isInteractionStatus();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.InteractionResource#isInteractionStatus
     * <em>Interaction Status</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Interaction Status</em>' attribute.
     * @see #isInteractionStatus()
     * @generated
     */
    void setInteractionStatus(boolean value);

    /**
     * Returns the value of the '<em><b>Interaction Status Time Stamp</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Interaction Status Time Stamp</em>' attribute
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Interaction Status Time Stamp</em>'
     *         attribute.
     * @see #setInteractionStatusTimeStamp(long)
     * @see org.sercho.masp.models.Context.ContextPackage#getInteractionResource_InteractionStatusTimeStamp()
     * @model
     * @generated
     */
    long getInteractionStatusTimeStamp();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.InteractionResource#getInteractionStatusTimeStamp
     * <em>Interaction Status Time Stamp</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Interaction Status Time Stamp</em>'
     *            attribute.
     * @see #getInteractionStatusTimeStamp()
     * @generated
     */
    void setInteractionStatusTimeStamp(long value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    double getContextRating(User user);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model kind="operation"
     * @generated
     */
    EList<Channel<?, ?>> getAllChannels();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    void setInteractionStatusWithTimeStamp(boolean status);

    /**
     * @generated NOT
     */
    public static final double MAXIMUM_CONTEXT_RATING = 1.0;

    /**
     * @generated NOT
     */
    public static final double MINIMUM_CONTEXT_RATING = 0.0;

} // InteractionResource
