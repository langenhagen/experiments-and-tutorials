/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Mouse</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Mouse#getMotionSensor <em>Motion
 * Sensor</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Mouse#getButtons <em>Buttons</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getMouse()
 * @model
 * @generated
 */
public interface Mouse extends HapticalInteractionResources {

    /**
     * Returns the value of the '<em><b>Motion Sensor</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Motion Sensor</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Motion Sensor</em>' containment reference.
     * @see #setMotionSensor(PointingInputChannel)
     * @see org.sercho.masp.models.Context.ContextPackage#getMouse_MotionSensor()
     * @model containment="true"
     * @generated
     */
    PointingInputChannel getMotionSensor();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Mouse#getMotionSensor
     * <em>Motion Sensor</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Motion Sensor</em>' containment
     *            reference.
     * @see #getMotionSensor()
     * @generated
     */
    void setMotionSensor(PointingInputChannel value);

    /**
     * Returns the value of the '<em><b>Buttons</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Buttons</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Buttons</em>' containment reference.
     * @see #setButtons(MessageInputChannel)
     * @see org.sercho.masp.models.Context.ContextPackage#getMouse_Buttons()
     * @model containment="true"
     * @generated
     */
    MessageInputChannel getButtons();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Mouse#getButtons <em>Buttons</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Buttons</em>' containment reference.
     * @see #getButtons()
     * @generated
     */
    void setButtons(MessageInputChannel value);
} // Mouse
