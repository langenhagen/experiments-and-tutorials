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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Tool</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> A tool that supports the progress of an Activity
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Tool#getName <em>Name</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Tool#getDescription <em>Description
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getTool()
 * @model
 * @generated
 */
public interface Tool extends EObject {

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.sercho.masp.models.Context.ContextPackage#getTool_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Tool#getName <em>Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.sercho.masp.models.Context.ContextPackage#getTool_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Tool#getDescription
     * <em>Description</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

} // Tool
