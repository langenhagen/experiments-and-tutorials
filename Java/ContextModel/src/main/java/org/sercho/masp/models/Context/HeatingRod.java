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
 * <em><b>Heating Rod</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.HeatingRod#getMaximumPowerWatts
 * <em>Maximum Power Watts</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getHeatingRod()
 * @model
 * @generated
 */
public interface HeatingRod extends PhysicalDevice {

    /**
     * Returns the value of the '<em><b>Maximum Power Watts</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Maximum Power Watts</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Maximum Power Watts</em>' attribute.
     * @see #setMaximumPowerWatts(int)
     * @see org.sercho.masp.models.Context.ContextPackage#getHeatingRod_MaximumPowerWatts()
     * @model required="true"
     * @generated
     */
    int getMaximumPowerWatts();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.HeatingRod#getMaximumPowerWatts
     * <em>Maximum Power Watts</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Maximum Power Watts</em>' attribute.
     * @see #getMaximumPowerWatts()
     * @generated
     */
    void setMaximumPowerWatts(int value);

} // HeatingRod
