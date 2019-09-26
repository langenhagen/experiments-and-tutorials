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
package de.dailab.masp.models.ModelUtilities.impl;

import de.dailab.masp.models.ModelUtilities.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelUtilitiesFactoryImpl extends EFactoryImpl implements
        ModelUtilitiesFactory {

    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ModelUtilitiesFactory init() {
        try {
            ModelUtilitiesFactory theModelUtilitiesFactory = (ModelUtilitiesFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.dai-labor.de/~masp/ModelUtilities.ecore");
            if(theModelUtilitiesFactory != null) {
                return theModelUtilitiesFactory;
            }
        }
        catch(Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ModelUtilitiesFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModelUtilitiesFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch(eClass.getClassifierID()) {
            case ModelUtilitiesPackage.ENTRY:
                return createEntry();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Entry createEntry() {
        EntryImpl entry = new EntryImpl();
        return entry;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModelUtilitiesPackage getModelUtilitiesPackage() {
        return (ModelUtilitiesPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ModelUtilitiesPackage getPackage() {
        return ModelUtilitiesPackage.eINSTANCE;
    }

} //ModelUtilitiesFactoryImpl
