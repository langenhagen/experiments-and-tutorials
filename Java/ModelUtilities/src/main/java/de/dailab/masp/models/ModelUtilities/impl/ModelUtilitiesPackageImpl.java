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

import de.dailab.masp.models.ModelUtilities.Entry;
import de.dailab.masp.models.ModelUtilities.ModelUtilitiesFactory;
import de.dailab.masp.models.ModelUtilities.ModelUtilitiesPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelUtilitiesPackageImpl extends EPackageImpl implements
        ModelUtilitiesPackage {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass entryEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see de.dailab.masp.models.ModelUtilities.ModelUtilitiesPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ModelUtilitiesPackageImpl() {
        super(eNS_URI, ModelUtilitiesFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link ModelUtilitiesPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ModelUtilitiesPackage init() {
        if(isInited)
            return (ModelUtilitiesPackage)EPackage.Registry.INSTANCE.getEPackage(ModelUtilitiesPackage.eNS_URI);

        // Obtain or create and register package
        ModelUtilitiesPackageImpl theModelUtilitiesPackage = (ModelUtilitiesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ModelUtilitiesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                : new ModelUtilitiesPackageImpl());

        isInited = true;

        // Create package meta-data objects
        theModelUtilitiesPackage.createPackageContents();

        // Initialize created meta-data
        theModelUtilitiesPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theModelUtilitiesPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ModelUtilitiesPackage.eNS_URI, theModelUtilitiesPackage);
        return theModelUtilitiesPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEntry() {
        return entryEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEntry_Key() {
        return (EAttribute)entryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEntry_Value() {
        return (EAttribute)entryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModelUtilitiesFactory getModelUtilitiesFactory() {
        return (ModelUtilitiesFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if(isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        entryEClass = createEClass(ENTRY);
        createEAttribute(entryEClass, ENTRY__KEY);
        createEAttribute(entryEClass, ENTRY__VALUE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if(isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes and features; add operations and parameters
        initEClass(entryEClass, Entry.class, "Entry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEntry_Value(), ecorePackage.getEString(), "value", null, 1, 1, Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //ModelUtilitiesPackageImpl
