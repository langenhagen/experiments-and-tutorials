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
 * <em><b>Fan</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Fan#getSpeed <em>Speed</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getFan()
 * @model
 * @generated
 */
public interface Fan extends PhysicalDevice {

    /**
     * Returns the value of the '<em><b>Speed</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Speed</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Speed</em>' containment reference.
     * @see #setSpeed(IntegerProperty)
     * @see org.sercho.masp.models.Context.ContextPackage#getFan_Speed()
     * @model containment="true"
     * @generated
     */
    IntegerProperty getSpeed();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Fan#getSpeed <em>Speed</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Speed</em>' containment reference.
     * @see #getSpeed()
     * @generated
     */
    void setSpeed(IntegerProperty value);

} // Fan
