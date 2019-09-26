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
 * <em><b>TV</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.TV#getCurrentProgram <em>Current
 * Program</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getTV()
 * @model
 * @generated
 */
public interface TV extends PhysicalDevice {

    /**
     * Returns the value of the '<em><b>Current Program</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Current Program</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Current Program</em>' containment
     *         reference.
     * @see #setCurrentProgram(IntegerProperty)
     * @see org.sercho.masp.models.Context.ContextPackage#getTV_CurrentProgram()
     * @model containment="true"
     * @generated
     */
    IntegerProperty getCurrentProgram();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.TV#getCurrentProgram
     * <em>Current Program</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Current Program</em>' containment
     *            reference.
     * @see #getCurrentProgram()
     * @generated
     */
    void setCurrentProgram(IntegerProperty value);

} // TV
