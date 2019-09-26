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

import org.eclipse.emf.common.util.EList;

import de.dailab.masp.models.Properties.Property;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Device Tool</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> A special device tool that requires device-funtions
 * (Property) to support an Activity <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.DeviceTool#getControls <em>Controls
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.DeviceTool#getAppliesTo <em>Applies
 * To</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getDeviceTool()
 * @model
 * @generated
 */
public interface DeviceTool extends Tool {

    /**
     * Returns the value of the '<em><b>Controls</b></em>' reference list. The
     * list contents are of type
     * {@link de.dailab.masp.models.Properties.Property}&lt;?>. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Controls</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Controls</em>' reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getDeviceTool_Controls()
     * @model
     * @generated
     */
    EList<Property<?>> getControls();

    /**
     * Returns the value of the '<em><b>Applies To</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Applies To</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Applies To</em>' reference.
     * @see #setAppliesTo(PhysicalDevice)
     * @see org.sercho.masp.models.Context.ContextPackage#getDeviceTool_AppliesTo()
     * @model required="true"
     * @generated
     */
    PhysicalDevice getAppliesTo();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.DeviceTool#getAppliesTo
     * <em>Applies To</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Applies To</em>' reference.
     * @see #getAppliesTo()
     * @generated
     */
    void setAppliesTo(PhysicalDevice value);

} // DeviceTool
