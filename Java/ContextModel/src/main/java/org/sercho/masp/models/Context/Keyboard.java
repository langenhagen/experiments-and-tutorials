/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Keyboard</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Keyboard#getKeys <em>Keys</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getKeyboard()
 * @model
 * @generated
 */
public interface Keyboard extends HapticalInteractionResources {

    /**
     * Returns the value of the '<em><b>Keys</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Keys</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Keys</em>' containment reference.
     * @see #setKeys(MessageInputChannel)
     * @see org.sercho.masp.models.Context.ContextPackage#getKeyboard_Keys()
     * @model containment="true"
     * @generated
     */
    MessageInputChannel getKeys();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Keyboard#getKeys <em>Keys</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Keys</em>' containment reference.
     * @see #getKeys()
     * @generated
     */
    void setKeys(MessageInputChannel value);
} // Keyboard
