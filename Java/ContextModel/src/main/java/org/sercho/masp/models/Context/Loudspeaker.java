/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Loudspeaker</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Loudspeaker#getVoice <em>Voice
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getLoudspeaker()
 * @model
 * @generated
 */
public interface Loudspeaker extends OutputInteractionResource {

    /**
     * Returns the value of the '<em><b>Voice</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Voice</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Voice</em>' containment reference.
     * @see #setVoice(MessageOutputChannel)
     * @see org.sercho.masp.models.Context.ContextPackage#getLoudspeaker_Voice()
     * @model containment="true"
     * @generated
     */
    MessageOutputChannel getVoice();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Loudspeaker#getVoice
     * <em>Voice</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Voice</em>' containment reference.
     * @see #getVoice()
     * @generated
     */
    void setVoice(MessageOutputChannel value);
} // Loudspeaker
