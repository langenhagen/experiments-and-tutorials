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
 * <em><b>Hob</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Hob#getHeatLevel <em>Heat Level
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getHob()
 * @model
 * @generated
 */
public interface Hob extends PhysicalDevice {

    /**
     * Returns the value of the '<em><b>Heat Level</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Heat Level</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Heat Level</em>' containment reference.
     * @see #setHeatLevel(IntegerProperty)
     * @see org.sercho.masp.models.Context.ContextPackage#getHob_HeatLevel()
     * @model containment="true"
     * @generated
     */
    IntegerProperty getHeatLevel();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Hob#getHeatLevel
     * <em>Heat Level</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Heat Level</em>' containment
     *            reference.
     * @see #getHeatLevel()
     * @generated
     */
    void setHeatLevel(IntegerProperty value);

} // Hob
