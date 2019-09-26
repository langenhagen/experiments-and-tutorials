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
package org.sercho.masp.models.Context.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.DeviceTool;
import org.sercho.masp.models.Context.PhysicalDevice;

import de.dailab.masp.models.Properties.Property;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Device Tool</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.DeviceToolImpl#getControls
 * <em>Controls</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.DeviceToolImpl#getAppliesTo
 * <em>Applies To</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DeviceToolImpl extends ToolImpl implements DeviceTool {

    /**
     * The cached value of the '{@link #getControls() <em>Controls</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getControls()
     * @generated
     * @ordered
     */
    protected EList<Property<?>> controls;

    /**
     * The cached value of the '{@link #getAppliesTo() <em>Applies To</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getAppliesTo()
     * @generated
     * @ordered
     */
    protected PhysicalDevice appliesTo;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DeviceToolImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.DEVICE_TOOL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Property<?>> getControls() {
        if(controls == null) {
            controls = new EObjectResolvingEList<Property<?>>(Property.class, this, ContextPackage.DEVICE_TOOL__CONTROLS);
        }
        return controls;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PhysicalDevice getAppliesTo() {
        if(appliesTo != null && appliesTo.eIsProxy()) {
            InternalEObject oldAppliesTo = (InternalEObject)appliesTo;
            appliesTo = (PhysicalDevice)eResolveProxy(oldAppliesTo);
            if(appliesTo != oldAppliesTo) {
                if(eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ContextPackage.DEVICE_TOOL__APPLIES_TO, oldAppliesTo, appliesTo));
            }
        }
        return appliesTo;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PhysicalDevice basicGetAppliesTo() {
        return appliesTo;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setAppliesTo(PhysicalDevice newAppliesTo) {
        PhysicalDevice oldAppliesTo = appliesTo;
        appliesTo = newAppliesTo;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.DEVICE_TOOL__APPLIES_TO, oldAppliesTo, appliesTo));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch(featureID) {
            case ContextPackage.DEVICE_TOOL__CONTROLS:
                return getControls();
            case ContextPackage.DEVICE_TOOL__APPLIES_TO:
                if(resolve)
                    return getAppliesTo();
                return basicGetAppliesTo();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch(featureID) {
            case ContextPackage.DEVICE_TOOL__CONTROLS:
                getControls().clear();
                getControls().addAll((Collection<? extends Property<?>>)newValue);
                return;
            case ContextPackage.DEVICE_TOOL__APPLIES_TO:
                setAppliesTo((PhysicalDevice)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch(featureID) {
            case ContextPackage.DEVICE_TOOL__CONTROLS:
                getControls().clear();
                return;
            case ContextPackage.DEVICE_TOOL__APPLIES_TO:
                setAppliesTo((PhysicalDevice)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch(featureID) {
            case ContextPackage.DEVICE_TOOL__CONTROLS:
                return controls != null && !controls.isEmpty();
            case ContextPackage.DEVICE_TOOL__APPLIES_TO:
                return appliesTo != null;
        }
        return super.eIsSet(featureID);
    }

} // DeviceToolImpl
