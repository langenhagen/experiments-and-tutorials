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
import org.sercho.masp.models.Context.PhysicalDeviceWithProgram;

import de.dailab.masp.models.Properties.IntegerProperty;
import de.dailab.masp.models.Properties.TextProperty;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Physical Device With Program</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.PhysicalDeviceWithProgramImpl#getProgram
 * <em>Program</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.PhysicalDeviceWithProgramImpl#getSecondsRemaining
 * <em>Seconds Remaining</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class PhysicalDeviceWithProgramImpl extends PhysicalDeviceImpl implements
        PhysicalDeviceWithProgram {

    /**
     * The cached value of the '{@link #getProgram() <em>Program</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getProgram()
     * @generated
     * @ordered
     */
    protected TextProperty program;

    /**
     * The cached value of the '{@link #getSecondsRemaining()
     * <em>Seconds Remaining</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getSecondsRemaining()
     * @generated
     * @ordered
     */
    protected IntegerProperty secondsRemaining;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected PhysicalDeviceWithProgramImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.PHYSICAL_DEVICE_WITH_PROGRAM;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TextProperty getProgram() {
        return program;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetProgram(TextProperty newProgram, NotificationChain msgs) {
        TextProperty oldProgram = program;
        program = newProgram;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.PHYSICAL_DEVICE_WITH_PROGRAM__PROGRAM, oldProgram, newProgram);
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
    public void setProgram(TextProperty newProgram) {
        if(newProgram != program) {
            NotificationChain msgs = null;
            if(program != null)
                msgs = ((InternalEObject)program).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.PHYSICAL_DEVICE_WITH_PROGRAM__PROGRAM, null, msgs);
            if(newProgram != null)
                msgs = ((InternalEObject)newProgram).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.PHYSICAL_DEVICE_WITH_PROGRAM__PROGRAM, null, msgs);
            msgs = basicSetProgram(newProgram, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.PHYSICAL_DEVICE_WITH_PROGRAM__PROGRAM, newProgram, newProgram));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public IntegerProperty getSecondsRemaining() {
        return secondsRemaining;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetSecondsRemaining(IntegerProperty newSecondsRemaining, NotificationChain msgs) {
        IntegerProperty oldSecondsRemaining = secondsRemaining;
        secondsRemaining = newSecondsRemaining;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.PHYSICAL_DEVICE_WITH_PROGRAM__SECONDS_REMAINING, oldSecondsRemaining, newSecondsRemaining);
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
    public void setSecondsRemaining(IntegerProperty newSecondsRemaining) {
        if(newSecondsRemaining != secondsRemaining) {
            NotificationChain msgs = null;
            if(secondsRemaining != null)
                msgs = ((InternalEObject)secondsRemaining).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.PHYSICAL_DEVICE_WITH_PROGRAM__SECONDS_REMAINING, null, msgs);
            if(newSecondsRemaining != null)
                msgs = ((InternalEObject)newSecondsRemaining).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.PHYSICAL_DEVICE_WITH_PROGRAM__SECONDS_REMAINING, null, msgs);
            msgs = basicSetSecondsRemaining(newSecondsRemaining, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.PHYSICAL_DEVICE_WITH_PROGRAM__SECONDS_REMAINING, newSecondsRemaining, newSecondsRemaining));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.PHYSICAL_DEVICE_WITH_PROGRAM__PROGRAM:
                return basicSetProgram(null, msgs);
            case ContextPackage.PHYSICAL_DEVICE_WITH_PROGRAM__SECONDS_REMAINING:
                return basicSetSecondsRemaining(null, msgs);
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
            case ContextPackage.PHYSICAL_DEVICE_WITH_PROGRAM__PROGRAM:
                return getProgram();
            case ContextPackage.PHYSICAL_DEVICE_WITH_PROGRAM__SECONDS_REMAINING:
                return getSecondsRemaining();
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
            case ContextPackage.PHYSICAL_DEVICE_WITH_PROGRAM__PROGRAM:
                setProgram((TextProperty)newValue);
                return;
            case ContextPackage.PHYSICAL_DEVICE_WITH_PROGRAM__SECONDS_REMAINING:
                setSecondsRemaining((IntegerProperty)newValue);
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
            case ContextPackage.PHYSICAL_DEVICE_WITH_PROGRAM__PROGRAM:
                setProgram((TextProperty)null);
                return;
            case ContextPackage.PHYSICAL_DEVICE_WITH_PROGRAM__SECONDS_REMAINING:
                setSecondsRemaining((IntegerProperty)null);
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
            case ContextPackage.PHYSICAL_DEVICE_WITH_PROGRAM__PROGRAM:
                return program != null;
            case ContextPackage.PHYSICAL_DEVICE_WITH_PROGRAM__SECONDS_REMAINING:
                return secondsRemaining != null;
        }
        return super.eIsSet(featureID);
    }

} // PhysicalDeviceWithProgramImpl
