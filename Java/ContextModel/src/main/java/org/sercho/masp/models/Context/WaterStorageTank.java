/**
 * Copyright (c) 2009 DAI-Labor.
 * All rights reserved.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Grzegorz Lehmann (DAI-Labor) - Initial API and implementation
 */
package org.sercho.masp.models.Context;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Water Storage Tank</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.WaterStorageTank#getTemperatureSensor
 * <em>Temperature Sensor</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.WaterStorageTank#getCapacity <em>
 * Capacity</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getWaterStorageTank()
 * @model
 * @generated
 */
public interface WaterStorageTank extends PhysicalDevice {

    /**
     * Returns the value of the '<em><b>Temperature Sensor</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Temperature Sensor</em>' reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Temperature Sensor</em>' containment
     *         reference.
     * @see #setTemperatureSensor(TemperatureSensor)
     * @see org.sercho.masp.models.Context.ContextPackage#getWaterStorageTank_TemperatureSensor()
     * @model containment="true"
     * @generated
     */
    TemperatureSensor getTemperatureSensor();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.WaterStorageTank#getTemperatureSensor
     * <em>Temperature Sensor</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Temperature Sensor</em>' containment
     *            reference.
     * @see #getTemperatureSensor()
     * @generated
     */
    void setTemperatureSensor(TemperatureSensor value);

    /**
     * Returns the value of the '<em><b>Capacity</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Capacity</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Capacity</em>' attribute.
     * @see #setCapacity(int)
     * @see org.sercho.masp.models.Context.ContextPackage#getWaterStorageTank_Capacity()
     * @model
     * @generated
     */
    int getCapacity();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.WaterStorageTank#getCapacity
     * <em>Capacity</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Capacity</em>' attribute.
     * @see #getCapacity()
     * @generated
     */
    void setCapacity(int value);

} // WaterStorageTank
