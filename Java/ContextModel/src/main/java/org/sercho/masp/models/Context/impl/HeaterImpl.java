/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Heater;

import de.dailab.masp.models.Properties.DoubleProperty;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Heater</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.HeaterImpl#getTemperatureDemand
 * <em>Temperature Demand</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.HeaterImpl#getTemperatureCurrent
 * <em>Temperature Current</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.HeaterImpl#getValvePosition
 * <em>Valve Position</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class HeaterImpl extends PhysicalDeviceImpl implements Heater {

    /**
     * The cached value of the '{@link #getTemperatureDemand()
     * <em>Temperature Demand</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getTemperatureDemand()
     * @generated
     * @ordered
     */
    protected DoubleProperty temperatureDemand;

    /**
     * The cached value of the '{@link #getTemperatureCurrent()
     * <em>Temperature Current</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getTemperatureCurrent()
     * @generated
     * @ordered
     */
    protected DoubleProperty temperatureCurrent;

    /**
     * The cached value of the '{@link #getValvePosition()
     * <em>Valve Position</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getValvePosition()
     * @generated
     * @ordered
     */
    protected DoubleProperty valvePosition;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected HeaterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.HEATER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DoubleProperty getTemperatureDemand() {
        return temperatureDemand;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetTemperatureDemand(DoubleProperty newTemperatureDemand, NotificationChain msgs) {
        DoubleProperty oldTemperatureDemand = temperatureDemand;
        temperatureDemand = newTemperatureDemand;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.HEATER__TEMPERATURE_DEMAND, oldTemperatureDemand, newTemperatureDemand);
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
    @Override
    public void setTemperatureDemand(DoubleProperty newTemperatureDemand) {
        if(newTemperatureDemand != temperatureDemand) {
            NotificationChain msgs = null;
            if(temperatureDemand != null)
                msgs = ((InternalEObject)temperatureDemand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.HEATER__TEMPERATURE_DEMAND, null, msgs);
            if(newTemperatureDemand != null)
                msgs = ((InternalEObject)newTemperatureDemand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.HEATER__TEMPERATURE_DEMAND, null, msgs);
            msgs = basicSetTemperatureDemand(newTemperatureDemand, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.HEATER__TEMPERATURE_DEMAND, newTemperatureDemand, newTemperatureDemand));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DoubleProperty getTemperatureCurrent() {
        return temperatureCurrent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetTemperatureCurrent(DoubleProperty newTemperatureCurrent, NotificationChain msgs) {
        DoubleProperty oldTemperatureCurrent = temperatureCurrent;
        temperatureCurrent = newTemperatureCurrent;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.HEATER__TEMPERATURE_CURRENT, oldTemperatureCurrent, newTemperatureCurrent);
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
    @Override
    public void setTemperatureCurrent(DoubleProperty newTemperatureCurrent) {
        if(newTemperatureCurrent != temperatureCurrent) {
            NotificationChain msgs = null;
            if(temperatureCurrent != null)
                msgs = ((InternalEObject)temperatureCurrent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.HEATER__TEMPERATURE_CURRENT, null, msgs);
            if(newTemperatureCurrent != null)
                msgs = ((InternalEObject)newTemperatureCurrent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.HEATER__TEMPERATURE_CURRENT, null, msgs);
            msgs = basicSetTemperatureCurrent(newTemperatureCurrent, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.HEATER__TEMPERATURE_CURRENT, newTemperatureCurrent, newTemperatureCurrent));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DoubleProperty getValvePosition() {
        return valvePosition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetValvePosition(DoubleProperty newValvePosition, NotificationChain msgs) {
        DoubleProperty oldValvePosition = valvePosition;
        valvePosition = newValvePosition;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.HEATER__VALVE_POSITION, oldValvePosition, newValvePosition);
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
    @Override
    public void setValvePosition(DoubleProperty newValvePosition) {
        if(newValvePosition != valvePosition) {
            NotificationChain msgs = null;
            if(valvePosition != null)
                msgs = ((InternalEObject)valvePosition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.HEATER__VALVE_POSITION, null, msgs);
            if(newValvePosition != null)
                msgs = ((InternalEObject)newValvePosition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.HEATER__VALVE_POSITION, null, msgs);
            msgs = basicSetValvePosition(newValvePosition, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.HEATER__VALVE_POSITION, newValvePosition, newValvePosition));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.HEATER__TEMPERATURE_DEMAND:
                return basicSetTemperatureDemand(null, msgs);
            case ContextPackage.HEATER__TEMPERATURE_CURRENT:
                return basicSetTemperatureCurrent(null, msgs);
            case ContextPackage.HEATER__VALVE_POSITION:
                return basicSetValvePosition(null, msgs);
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
            case ContextPackage.HEATER__TEMPERATURE_DEMAND:
                return getTemperatureDemand();
            case ContextPackage.HEATER__TEMPERATURE_CURRENT:
                return getTemperatureCurrent();
            case ContextPackage.HEATER__VALVE_POSITION:
                return getValvePosition();
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
            case ContextPackage.HEATER__TEMPERATURE_DEMAND:
                setTemperatureDemand((DoubleProperty)newValue);
                return;
            case ContextPackage.HEATER__TEMPERATURE_CURRENT:
                setTemperatureCurrent((DoubleProperty)newValue);
                return;
            case ContextPackage.HEATER__VALVE_POSITION:
                setValvePosition((DoubleProperty)newValue);
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
            case ContextPackage.HEATER__TEMPERATURE_DEMAND:
                setTemperatureDemand((DoubleProperty)null);
                return;
            case ContextPackage.HEATER__TEMPERATURE_CURRENT:
                setTemperatureCurrent((DoubleProperty)null);
                return;
            case ContextPackage.HEATER__VALVE_POSITION:
                setValvePosition((DoubleProperty)null);
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
            case ContextPackage.HEATER__TEMPERATURE_DEMAND:
                return temperatureDemand != null;
            case ContextPackage.HEATER__TEMPERATURE_CURRENT:
                return temperatureCurrent != null;
            case ContextPackage.HEATER__VALVE_POSITION:
                return valvePosition != null;
        }
        return super.eIsSet(featureID);
    }

} // HeaterImpl
