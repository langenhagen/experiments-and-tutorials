/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Gesture IR</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.GestureIR#getGestureRecognition
 * <em>Gesture Recognition</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getGestureIR()
 * @model
 * @generated
 */
public interface GestureIR extends HapticalInteractionResources {

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
     * @see org.sercho.masp.models.Context.ContextPackage#getGestureIR_GestureRecognition()
     * @model containment="true"
     * @generated
     */
    MessageInputChannel getGestureRecognition();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.GestureIR#getGestureRecognition
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
} // GestureIR
