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
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Need</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> A need states the requirement of an execution of a
 * special Activity <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Need#getFulfilledBy <em>Fulfilled
 * By</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Need#getName <em>Name</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Need#getDescription <em>Description
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Need#getConfiguration <em>
 * Configuration</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getNeed()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Need extends EObject {

    /**
     * Returns the value of the '<em><b>Fulfilled By</b></em>' reference list.
     * The list contents are of type
     * {@link org.sercho.masp.models.Context.Activity}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Fulfilled By</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Fulfilled By</em>' reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getNeed_FulfilledBy()
     * @model
     * @generated
     */
    EList<Activity> getFulfilledBy();

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
     * @see org.sercho.masp.models.Context.ContextPackage#getNeed_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Need#getName <em>Name</em>}'
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
     * @see org.sercho.masp.models.Context.ContextPackage#getNeed_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Need#getDescription
     * <em>Description</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Configuration</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Configuration</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Configuration</em>' containment reference.
     * @see #setConfiguration(ConfigurationProperty)
     * @see org.sercho.masp.models.Context.ContextPackage#getNeed_Configuration()
     * @model containment="true"
     * @generated
     */
    ConfigurationProperty getConfiguration();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Need#getConfiguration
     * <em>Configuration</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Configuration</em>' containment
     *            reference.
     * @see #getConfiguration()
     * @generated
     */
    void setConfiguration(ConfigurationProperty value);

} // Need
