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
import org.sercho.masp.models.Context.GestureIR;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.MessageInputChannel;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Gesture IR</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.GestureIRImpl#getGestureRecognition
 * <em>Gesture Recognition</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class GestureIRImpl extends HapticalInteractionResourcesImpl implements GestureIR {

    /**
     * The cached value of the '{@link #getGestureRecognition()
     * <em>Gesture Recognition</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getGestureRecognition()
     * @generated
     * @ordered
     */
    protected MessageInputChannel gestureRecognition;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected GestureIRImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.GESTURE_IR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public MessageInputChannel getGestureRecognition() {
        return gestureRecognition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetGestureRecognition(MessageInputChannel newGestureRecognition, NotificationChain msgs) {
        MessageInputChannel oldGestureRecognition = gestureRecognition;
        gestureRecognition = newGestureRecognition;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.GESTURE_IR__GESTURE_RECOGNITION, oldGestureRecognition, newGestureRecognition);
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
    public void setGestureRecognition(MessageInputChannel newGestureRecognition) {
        if(newGestureRecognition != gestureRecognition) {
            NotificationChain msgs = null;
            if(gestureRecognition != null)
                msgs = ((InternalEObject)gestureRecognition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.GESTURE_IR__GESTURE_RECOGNITION, null, msgs);
            if(newGestureRecognition != null)
                msgs = ((InternalEObject)newGestureRecognition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.GESTURE_IR__GESTURE_RECOGNITION, null, msgs);
            msgs = basicSetGestureRecognition(newGestureRecognition, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.GESTURE_IR__GESTURE_RECOGNITION, newGestureRecognition, newGestureRecognition));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.GESTURE_IR__GESTURE_RECOGNITION:
                return basicSetGestureRecognition(null, msgs);
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
            case ContextPackage.GESTURE_IR__GESTURE_RECOGNITION:
                return getGestureRecognition();
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
            case ContextPackage.GESTURE_IR__GESTURE_RECOGNITION:
                setGestureRecognition((MessageInputChannel)newValue);
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
            case ContextPackage.GESTURE_IR__GESTURE_RECOGNITION:
                setGestureRecognition((MessageInputChannel)null);
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
            case ContextPackage.GESTURE_IR__GESTURE_RECOGNITION:
                return gestureRecognition != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<? extends InteractionResource> getTypeHook() {
        return GestureIR.class;
    }

} // GestureIRImpl
