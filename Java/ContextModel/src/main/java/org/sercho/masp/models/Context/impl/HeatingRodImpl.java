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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.HeatingRod;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Heating Rod</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.HeatingRodImpl#getMaximumPowerWatts
 * <em>Maximum Power Watts</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class HeatingRodImpl extends PhysicalDeviceImpl implements HeatingRod {

    /**
     * The default value of the '{@link #getMaximumPowerWatts()
     * <em>Maximum Power Watts</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getMaximumPowerWatts()
     * @generated
     * @ordered
     */
    protected static final int MAXIMUM_POWER_WATTS_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getMaximumPowerWatts()
     * <em>Maximum Power Watts</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getMaximumPowerWatts()
     * @generated
     * @ordered
     */
    protected int maximumPowerWatts = MAXIMUM_POWER_WATTS_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected HeatingRodImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.HEATING_ROD;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getMaximumPowerWatts() {
        return maximumPowerWatts;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setMaximumPowerWatts(int newMaximumPowerWatts) {
        int oldMaximumPowerWatts = maximumPowerWatts;
        maximumPowerWatts = newMaximumPowerWatts;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.HEATING_ROD__MAXIMUM_POWER_WATTS, oldMaximumPowerWatts, maximumPowerWatts));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch(featureID) {
            case ContextPackage.HEATING_ROD__MAXIMUM_POWER_WATTS:
                return getMaximumPowerWatts();
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
            case ContextPackage.HEATING_ROD__MAXIMUM_POWER_WATTS:
                setMaximumPowerWatts((Integer)newValue);
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
            case ContextPackage.HEATING_ROD__MAXIMUM_POWER_WATTS:
                setMaximumPowerWatts(MAXIMUM_POWER_WATTS_EDEFAULT);
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
            case ContextPackage.HEATING_ROD__MAXIMUM_POWER_WATTS:
                return maximumPowerWatts != MAXIMUM_POWER_WATTS_EDEFAULT;
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
        result.append(" (maximumPowerWatts: ");
        result.append(maximumPowerWatts);
        result.append(')');
        return result.toString();
    }

} // HeatingRodImpl
