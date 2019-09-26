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
import org.sercho.masp.models.Context.Fridge;

import de.dailab.masp.models.Properties.IntegerProperty;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Fridge</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.FridgeImpl#getTemperature <em>
 * Temperature</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class FridgeImpl extends PhysicalDeviceImpl implements Fridge {

    /**
     * The cached value of the '{@link #getTemperature() <em>Temperature</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTemperature()
     * @generated
     * @ordered
     */
    protected IntegerProperty temperature;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected FridgeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.FRIDGE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public IntegerProperty getTemperature() {
        return temperature;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetTemperature(IntegerProperty newTemperature, NotificationChain msgs) {
        IntegerProperty oldTemperature = temperature;
        temperature = newTemperature;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.FRIDGE__TEMPERATURE, oldTemperature, newTemperature);
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
    public void setTemperature(IntegerProperty newTemperature) {
        if(newTemperature != temperature) {
            NotificationChain msgs = null;
            if(temperature != null)
                msgs = ((InternalEObject)temperature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.FRIDGE__TEMPERATURE, null, msgs);
            if(newTemperature != null)
                msgs = ((InternalEObject)newTemperature).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.FRIDGE__TEMPERATURE, null, msgs);
            msgs = basicSetTemperature(newTemperature, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.FRIDGE__TEMPERATURE, newTemperature, newTemperature));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.FRIDGE__TEMPERATURE:
                return basicSetTemperature(null, msgs);
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
            case ContextPackage.FRIDGE__TEMPERATURE:
                return getTemperature();
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
            case ContextPackage.FRIDGE__TEMPERATURE:
                setTemperature((IntegerProperty)newValue);
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
            case ContextPackage.FRIDGE__TEMPERATURE:
                setTemperature((IntegerProperty)null);
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
            case ContextPackage.FRIDGE__TEMPERATURE:
                return temperature != null;
        }
        return super.eIsSet(featureID);
    }

} // FridgeImpl
