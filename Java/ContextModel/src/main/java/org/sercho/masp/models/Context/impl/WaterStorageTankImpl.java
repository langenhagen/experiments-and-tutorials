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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.TemperatureSensor;
import org.sercho.masp.models.Context.WaterStorageTank;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Water Storage Tank</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.WaterStorageTankImpl#getTemperatureSensor
 * <em>Temperature Sensor</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.WaterStorageTankImpl#getCapacity
 * <em>Capacity</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class WaterStorageTankImpl extends PhysicalDeviceImpl implements WaterStorageTank {

    /**
     * The cached value of the '{@link #getTemperatureSensor()
     * <em>Temperature Sensor</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getTemperatureSensor()
     * @generated
     * @ordered
     */
    protected TemperatureSensor temperatureSensor;

    /**
     * The default value of the '{@link #getCapacity() <em>Capacity</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCapacity()
     * @generated
     * @ordered
     */
    protected static final int CAPACITY_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getCapacity() <em>Capacity</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCapacity()
     * @generated
     * @ordered
     */
    protected int capacity = CAPACITY_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected WaterStorageTankImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.WATER_STORAGE_TANK;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TemperatureSensor getTemperatureSensor() {
        return temperatureSensor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetTemperatureSensor(TemperatureSensor newTemperatureSensor, NotificationChain msgs) {
        TemperatureSensor oldTemperatureSensor = temperatureSensor;
        temperatureSensor = newTemperatureSensor;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.WATER_STORAGE_TANK__TEMPERATURE_SENSOR, oldTemperatureSensor, newTemperatureSensor);
            if(msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTemperatureSensor(TemperatureSensor newTemperatureSensor) {
        if(newTemperatureSensor != temperatureSensor) {
            NotificationChain msgs = null;
            if(temperatureSensor != null)
                msgs = ((InternalEObject)temperatureSensor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.WATER_STORAGE_TANK__TEMPERATURE_SENSOR, null, msgs);
            if(newTemperatureSensor != null)
                msgs = ((InternalEObject)newTemperatureSensor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.WATER_STORAGE_TANK__TEMPERATURE_SENSOR, null, msgs);
            msgs = basicSetTemperatureSensor(newTemperatureSensor, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.WATER_STORAGE_TANK__TEMPERATURE_SENSOR, newTemperatureSensor, newTemperatureSensor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setCapacity(int newCapacity) {
        int oldCapacity = capacity;
        capacity = newCapacity;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.WATER_STORAGE_TANK__CAPACITY, oldCapacity, capacity));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.WATER_STORAGE_TANK__TEMPERATURE_SENSOR:
                return basicSetTemperatureSensor(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch(featureID) {
            case ContextPackage.WATER_STORAGE_TANK__TEMPERATURE_SENSOR:
                return getTemperatureSensor();
            case ContextPackage.WATER_STORAGE_TANK__CAPACITY:
                return getCapacity();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch(featureID) {
            case ContextPackage.WATER_STORAGE_TANK__TEMPERATURE_SENSOR:
                setTemperatureSensor((TemperatureSensor)newValue);
                return;
            case ContextPackage.WATER_STORAGE_TANK__CAPACITY:
                setCapacity((Integer)newValue);
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
            case ContextPackage.WATER_STORAGE_TANK__TEMPERATURE_SENSOR:
                setTemperatureSensor((TemperatureSensor)null);
                return;
            case ContextPackage.WATER_STORAGE_TANK__CAPACITY:
                setCapacity(CAPACITY_EDEFAULT);
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
            case ContextPackage.WATER_STORAGE_TANK__TEMPERATURE_SENSOR:
                return temperatureSensor != null;
            case ContextPackage.WATER_STORAGE_TANK__CAPACITY:
                return capacity != CAPACITY_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if(eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (capacity: ");
        result.append(capacity);
        result.append(')');
        return result.toString();
    }

} // WaterStorageTankImpl
