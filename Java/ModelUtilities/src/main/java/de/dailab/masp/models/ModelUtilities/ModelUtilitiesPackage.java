/**
 * Copyright (c) 2010 DAI-Labor.
 * All rights reserved.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Grzegorz Lehmann (DAI-Labor) - Initial API and implementation
 */
package de.dailab.masp.models.ModelUtilities;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.dailab.masp.models.ModelUtilities.ModelUtilitiesFactory
 * @model kind="package"
 * @generated
 */
public interface ModelUtilitiesPackage extends EPackage {

    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "ModelUtilities";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.dai-labor.de/~masp/ModelUtilities.ecore";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "ModelUtilities";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ModelUtilitiesPackage eINSTANCE = de.dailab.masp.models.ModelUtilities.impl.ModelUtilitiesPackageImpl.init();

    /**
     * The meta object id for the '{@link de.dailab.masp.models.ModelUtilities.impl.EntryImpl <em>Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.dailab.masp.models.ModelUtilities.impl.EntryImpl
     * @see de.dailab.masp.models.ModelUtilities.impl.ModelUtilitiesPackageImpl#getEntry()
     * @generated
     */
    int ENTRY = 0;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTRY__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTRY__VALUE = 1;

    /**
     * The number of structural features of the '<em>Entry</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTRY_FEATURE_COUNT = 2;

    /**
     * Returns the meta object for class '{@link de.dailab.masp.models.ModelUtilities.Entry <em>Entry</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Entry</em>'.
     * @see de.dailab.masp.models.ModelUtilities.Entry
     * @generated
     */
    EClass getEntry();

    /**
     * Returns the meta object for the attribute '{@link de.dailab.masp.models.ModelUtilities.Entry#getKey <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see de.dailab.masp.models.ModelUtilities.Entry#getKey()
     * @see #getEntry()
     * @generated
     */
    EAttribute getEntry_Key();

    /**
     * Returns the meta object for the attribute '{@link de.dailab.masp.models.ModelUtilities.Entry#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see de.dailab.masp.models.ModelUtilities.Entry#getValue()
     * @see #getEntry()
     * @generated
     */
    EAttribute getEntry_Value();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ModelUtilitiesFactory getModelUtilitiesFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {

        /**
         * The meta object literal for the '{@link de.dailab.masp.models.ModelUtilities.impl.EntryImpl <em>Entry</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.dailab.masp.models.ModelUtilities.impl.EntryImpl
         * @see de.dailab.masp.models.ModelUtilities.impl.ModelUtilitiesPackageImpl#getEntry()
         * @generated
         */
        EClass ENTRY = eINSTANCE.getEntry();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ENTRY__KEY = eINSTANCE.getEntry_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ENTRY__VALUE = eINSTANCE.getEntry_Value();

    }

} //ModelUtilitiesPackage
