/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Microphone</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Microphone#getVoiceRecognition <em>
 * Voice Recognition</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getMicrophone()
 * @model
 * @generated
 */
public interface Microphone extends InputInteractionResource {

    /**
     * Returns the value of the '<em><b>Voice Recognition</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Voice Recognition</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Voice Recognition</em>' containment
     *         reference.
     * @see #setVoiceRecognition(MessageInputChannel)
     * @see org.sercho.masp.models.Context.ContextPackage#getMicrophone_VoiceRecognition()
     * @model containment="true"
     * @generated
     */
    MessageInputChannel getVoiceRecognition();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Microphone#getVoiceRecognition
     * <em>Voice Recognition</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Voice Recognition</em>' containment
     *            reference.
     * @see #getVoiceRecognition()
     * @generated
     */
    void setVoiceRecognition(MessageInputChannel value);
} // Microphone
