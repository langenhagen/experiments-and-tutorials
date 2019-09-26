/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Touchscreen</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Touchscreen#getTouchSurface <em>
 * Touch Surface</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Touchscreen#getGestureRecognition
 * <em>Gesture Recognition</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getTouchscreen()
 * @model
 * @generated
 */
public interface Touchscreen extends Display, Touchpad {

    /**
     * Returns the value of the '<em><b>Touch Surface</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Touch Surface</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Touch Surface</em>' containment reference.
     * @see #setTouchSurface(PointingInputChannel)
     * @see org.sercho.masp.models.Context.ContextPackage#getTouchscreen_TouchSurface()
     * @model containment="true"
     * @generated
     */
    PointingInputChannel getTouchSurface();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Touchscreen#getTouchSurface
     * <em>Touch Surface</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Touch Surface</em>' containment
     *            reference.
     * @see #getTouchSurface()
     * @generated
     */
    void setTouchSurface(PointingInputChannel value);

    /**
     * Returns the value of the '<em><b>Gesture Recognition</b></em>'
     * containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Gesture Recognition</em>' containment
     * reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Gesture Recognition</em>' containment
     *         reference.
     * @see #setGestureRecognition(MessageInputChannel)
     * @see org.sercho.masp.models.Context.ContextPackage#getTouchscreen_GestureRecognition()
     * @model containment="true"
     * @generated
     */
    MessageInputChannel getGestureRecognition();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Touchscreen#getGestureRecognition
     * <em>Gesture Recognition</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Gesture Recognition</em>'
     *            containment reference.
     * @see #getGestureRecognition()
     * @generated
     */
    void setGestureRecognition(MessageInputChannel value);

} // Touchscreen
