/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import de.dailab.masp.models.Properties.IntegerProperty;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Blind</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Blind#getLevel <em>Level</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getBlind()
 * @model
 * @generated
 */
public interface Blind extends PhysicalDevice {

    /**
     * Returns the value of the '<em><b>Level</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Level</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Level</em>' containment reference.
     * @see #setLevel(IntegerProperty)
     * @see org.sercho.masp.models.Context.ContextPackage#getBlind_Level()
     * @model containment="true"
     * @generated
     */
    IntegerProperty getLevel();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Blind#getLevel <em>Level</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Level</em>' containment reference.
     * @see #getLevel()
     * @generated
     */
    void setLevel(IntegerProperty value);

} // Blind
