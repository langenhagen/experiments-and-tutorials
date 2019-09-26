/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import de.dailab.masp.models.Properties.DoubleProperty;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Physical Sensor Device</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.PhysicalSensorDevice#getValue <em>
 * Value</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getPhysicalSensorDevice()
 * @model abstract="true"
 * @generated
 */
public interface PhysicalSensorDevice extends Device {

    /**
     * Returns the value of the '<em><b>Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Value</em>' containment reference.
     * @see #setValue(DoubleProperty)
     * @see org.sercho.masp.models.Context.ContextPackage#getPhysicalSensorDevice_Value()
     * @model containment="true"
     * @generated
     */
    DoubleProperty getValue();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.PhysicalSensorDevice#getValue
     * <em>Value</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Value</em>' containment reference.
     * @see #getValue()
     * @generated
     */
    void setValue(DoubleProperty value);

} // PhysicalSensorDevice
