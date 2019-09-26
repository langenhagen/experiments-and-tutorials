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
import org.sercho.masp.models.Context.Lamp;
import org.sercho.masp.models.Context.LampType;

import de.dailab.masp.models.Properties.IntegerProperty;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Lamp</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.LampImpl#getDimmingLevel <em>
 * Dimming Level</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.LampImpl#getLampType <em>Lamp
 * Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class LampImpl extends PhysicalDeviceImpl implements Lamp {

    /**
     * The cached value of the '{@link #getDimmingLevel()
     * <em>Dimming Level</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getDimmingLevel()
     * @generated
     * @ordered
     */
    protected IntegerProperty dimmingLevel;

    /**
     * The default value of the '{@link #getLampType() <em>Lamp Type</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLampType()
     * @generated
     * @ordered
     */
    protected static final LampType LAMP_TYPE_EDEFAULT = LampType.UNSPECIFIED;

    /**
     * The cached value of the '{@link #getLampType() <em>Lamp Type</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLampType()
     * @generated
     * @ordered
     */
    protected LampType lampType = LAMP_TYPE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected LampImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.LAMP;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public IntegerProperty getDimmingLevel() {
        return dimmingLevel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetDimmingLevel(IntegerProperty newDimmingLevel, NotificationChain msgs) {
        IntegerProperty oldDimmingLevel = dimmingLevel;
        dimmingLevel = newDimmingLevel;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.LAMP__DIMMING_LEVEL, oldDimmingLevel, newDimmingLevel);
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
    public void setDimmingLevel(IntegerProperty newDimmingLevel) {
        if(newDimmingLevel != dimmingLevel) {
            NotificationChain msgs = null;
            if(dimmingLevel != null)
                msgs = ((InternalEObject)dimmingLevel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.LAMP__DIMMING_LEVEL, null, msgs);
            if(newDimmingLevel != null)
                msgs = ((InternalEObject)newDimmingLevel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.LAMP__DIMMING_LEVEL, null, msgs);
            msgs = basicSetDimmingLevel(newDimmingLevel, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.LAMP__DIMMING_LEVEL, newDimmingLevel, newDimmingLevel));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public LampType getLampType() {
        return lampType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setLampType(LampType newLampType) {
        LampType oldLampType = lampType;
        lampType = newLampType == null ? LAMP_TYPE_EDEFAULT : newLampType;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.LAMP__LAMP_TYPE, oldLampType, lampType));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public void setNewLampeType(final LampType lampType) {
        if(lampType == null) {
            throw new IllegalArgumentException("Argument lampType must not be null in method setNewLampeType!");
        }

        this.setLampType(lampType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.LAMP__DIMMING_LEVEL:
                return basicSetDimmingLevel(null, msgs);
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
            case ContextPackage.LAMP__DIMMING_LEVEL:
                return getDimmingLevel();
            case ContextPackage.LAMP__LAMP_TYPE:
                return getLampType();
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
            case ContextPackage.LAMP__DIMMING_LEVEL:
                setDimmingLevel((IntegerProperty)newValue);
                return;
            case ContextPackage.LAMP__LAMP_TYPE:
                setLampType((LampType)newValue);
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
            case ContextPackage.LAMP__DIMMING_LEVEL:
                setDimmingLevel((IntegerProperty)null);
                return;
            case ContextPackage.LAMP__LAMP_TYPE:
                setLampType(LAMP_TYPE_EDEFAULT);
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
            case ContextPackage.LAMP__DIMMING_LEVEL:
                return dimmingLevel != null;
            case ContextPackage.LAMP__LAMP_TYPE:
                return lampType != LAMP_TYPE_EDEFAULT;
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
        result.append(" (lampType: ");
        result.append(lampType);
        result.append(')');
        return result.toString();
    }

} // LampImpl
