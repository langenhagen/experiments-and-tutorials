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
import org.sercho.masp.models.Context.Hob;

import de.dailab.masp.models.Properties.IntegerProperty;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Hob</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.HobImpl#getHeatLevel <em>Heat
 * Level</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class HobImpl extends PhysicalDeviceImpl implements Hob {

    /**
     * The cached value of the '{@link #getHeatLevel() <em>Heat Level</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getHeatLevel()
     * @generated
     * @ordered
     */
    protected IntegerProperty heatLevel;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected HobImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.HOB;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public IntegerProperty getHeatLevel() {
        return heatLevel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetHeatLevel(IntegerProperty newHeatLevel, NotificationChain msgs) {
        IntegerProperty oldHeatLevel = heatLevel;
        heatLevel = newHeatLevel;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.HOB__HEAT_LEVEL, oldHeatLevel, newHeatLevel);
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
    public void setHeatLevel(IntegerProperty newHeatLevel) {
        if(newHeatLevel != heatLevel) {
            NotificationChain msgs = null;
            if(heatLevel != null)
                msgs = ((InternalEObject)heatLevel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.HOB__HEAT_LEVEL, null, msgs);
            if(newHeatLevel != null)
                msgs = ((InternalEObject)newHeatLevel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.HOB__HEAT_LEVEL, null, msgs);
            msgs = basicSetHeatLevel(newHeatLevel, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.HOB__HEAT_LEVEL, newHeatLevel, newHeatLevel));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.HOB__HEAT_LEVEL:
                return basicSetHeatLevel(null, msgs);
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
            case ContextPackage.HOB__HEAT_LEVEL:
                return getHeatLevel();
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
            case ContextPackage.HOB__HEAT_LEVEL:
                setHeatLevel((IntegerProperty)newValue);
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
            case ContextPackage.HOB__HEAT_LEVEL:
                setHeatLevel((IntegerProperty)null);
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
            case ContextPackage.HOB__HEAT_LEVEL:
                return heatLevel != null;
        }
        return super.eIsSet(featureID);
    }

} // HobImpl
