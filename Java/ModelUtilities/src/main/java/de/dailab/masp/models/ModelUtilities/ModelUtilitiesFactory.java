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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.dailab.masp.models.ModelUtilities.ModelUtilitiesPackage
 * @generated
 */
public interface ModelUtilitiesFactory extends EFactory {

    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ModelUtilitiesFactory eINSTANCE = de.dailab.masp.models.ModelUtilities.impl.ModelUtilitiesFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Entry</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Entry</em>'.
     * @generated
     */
    Entry createEntry();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    ModelUtilitiesPackage getModelUtilitiesPackage();

} //ModelUtilitiesFactory
