/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Meter</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Meter#getReadings <em>Readings
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getMeter()
 * @model
 * @generated
 */
public interface Meter extends PhysicalDevice {

    /**
     * Returns the value of the '<em><b>Readings</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.sercho.masp.models.Context.MeterReading}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Readings</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Readings</em>' containment reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getMeter_Readings()
     * @model containment="true"
     * @generated
     */
    EList<MeterReading> getReadings();

} // Meter
