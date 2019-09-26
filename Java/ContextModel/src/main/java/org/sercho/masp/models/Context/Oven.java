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
 * <em><b>Oven</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Oven#getTemperature <em>Temperature
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getOven()
 * @model
 * @generated
 */
public interface Oven extends PhysicalDeviceWithProgram {

    /**
     * Returns the value of the '<em><b>Temperature</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Temperature</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Temperature</em>' containment reference.
     * @see #setTemperature(IntegerProperty)
     * @see org.sercho.masp.models.Context.ContextPackage#getOven_Temperature()
     * @model containment="true"
     * @generated
     */
    IntegerProperty getTemperature();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Oven#getTemperature
     * <em>Temperature</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Temperature</em>' containment
     *            reference.
     * @see #getTemperature()
     * @generated
     */
    void setTemperature(IntegerProperty value);

} // Oven
