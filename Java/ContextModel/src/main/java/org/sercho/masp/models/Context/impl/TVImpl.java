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
import org.sercho.masp.models.Context.TV;

import de.dailab.masp.models.Properties.IntegerProperty;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>TV</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.TVImpl#getCurrentProgram <em>
 * Current Program</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class TVImpl extends PhysicalDeviceImpl implements TV {

    /**
     * The cached value of the '{@link #getCurrentProgram()
     * <em>Current Program</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getCurrentProgram()
     * @generated
     * @ordered
     */
    protected IntegerProperty currentProgram;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TVImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.TV;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public IntegerProperty getCurrentProgram() {
        return currentProgram;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetCurrentProgram(IntegerProperty newCurrentProgram, NotificationChain msgs) {
        IntegerProperty oldCurrentProgram = currentProgram;
        currentProgram = newCurrentProgram;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.TV__CURRENT_PROGRAM, oldCurrentProgram, newCurrentProgram);
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
    public void setCurrentProgram(IntegerProperty newCurrentProgram) {
        if(newCurrentProgram != currentProgram) {
            NotificationChain msgs = null;
            if(currentProgram != null)
                msgs = ((InternalEObject)currentProgram).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.TV__CURRENT_PROGRAM, null, msgs);
            if(newCurrentProgram != null)
                msgs = ((InternalEObject)newCurrentProgram).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.TV__CURRENT_PROGRAM, null, msgs);
            msgs = basicSetCurrentProgram(newCurrentProgram, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.TV__CURRENT_PROGRAM, newCurrentProgram, newCurrentProgram));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.TV__CURRENT_PROGRAM:
                return basicSetCurrentProgram(null, msgs);
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
            case ContextPackage.TV__CURRENT_PROGRAM:
                return getCurrentProgram();
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
            case ContextPackage.TV__CURRENT_PROGRAM:
                setCurrentProgram((IntegerProperty)newValue);
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
            case ContextPackage.TV__CURRENT_PROGRAM:
                setCurrentProgram((IntegerProperty)null);
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
            case ContextPackage.TV__CURRENT_PROGRAM:
                return currentProgram != null;
        }
        return super.eIsSet(featureID);
    }

} // TVImpl
