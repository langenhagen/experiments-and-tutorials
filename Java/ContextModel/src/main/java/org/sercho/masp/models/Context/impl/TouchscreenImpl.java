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
import org.sercho.masp.models.Context.MessageInputChannel;
import org.sercho.masp.models.Context.PointingInputChannel;
import org.sercho.masp.models.Context.Touchscreen;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Touchscreen</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.TouchscreenImpl#getTouchSurface
 * <em>Touch Surface</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.TouchscreenImpl#getGestureRecognition
 * <em>Gesture Recognition</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class TouchscreenImpl extends DisplayImpl implements Touchscreen {

    /**
     * The cached value of the '{@link #getTouchSurface()
     * <em>Touch Surface</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTouchSurface()
     * @generated
     * @ordered
     */
    protected PointingInputChannel touchSurface;

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
    protected TouchscreenImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.TOUCHSCREEN;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public PointingInputChannel getTouchSurface() {
        return touchSurface;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetTouchSurface(PointingInputChannel newTouchSurface, NotificationChain msgs) {
        PointingInputChannel oldTouchSurface = touchSurface;
        touchSurface = newTouchSurface;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.TOUCHSCREEN__TOUCH_SURFACE, oldTouchSurface, newTouchSurface);
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
    public void setTouchSurface(PointingInputChannel newTouchSurface) {
        if(newTouchSurface != touchSurface) {
            NotificationChain msgs = null;
            if(touchSurface != null)
                msgs = ((InternalEObject)touchSurface).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.TOUCHSCREEN__TOUCH_SURFACE, null, msgs);
            if(newTouchSurface != null)
                msgs = ((InternalEObject)newTouchSurface).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.TOUCHSCREEN__TOUCH_SURFACE, null, msgs);
            msgs = basicSetTouchSurface(newTouchSurface, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.TOUCHSCREEN__TOUCH_SURFACE, newTouchSurface, newTouchSurface));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.TOUCHSCREEN__GESTURE_RECOGNITION, oldGestureRecognition, newGestureRecognition);
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
                msgs = ((InternalEObject)gestureRecognition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.TOUCHSCREEN__GESTURE_RECOGNITION, null, msgs);
            if(newGestureRecognition != null)
                msgs = ((InternalEObject)newGestureRecognition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.TOUCHSCREEN__GESTURE_RECOGNITION, null, msgs);
            msgs = basicSetGestureRecognition(newGestureRecognition, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.TOUCHSCREEN__GESTURE_RECOGNITION, newGestureRecognition, newGestureRecognition));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.TOUCHSCREEN__TOUCH_SURFACE:
                return basicSetTouchSurface(null, msgs);
            case ContextPackage.TOUCHSCREEN__GESTURE_RECOGNITION:
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
            case ContextPackage.TOUCHSCREEN__TOUCH_SURFACE:
                return getTouchSurface();
            case ContextPackage.TOUCHSCREEN__GESTURE_RECOGNITION:
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
            case ContextPackage.TOUCHSCREEN__TOUCH_SURFACE:
                setTouchSurface((PointingInputChannel)newValue);
                return;
            case ContextPackage.TOUCHSCREEN__GESTURE_RECOGNITION:
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
            case ContextPackage.TOUCHSCREEN__TOUCH_SURFACE:
                setTouchSurface((PointingInputChannel)null);
                return;
            case ContextPackage.TOUCHSCREEN__GESTURE_RECOGNITION:
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
            case ContextPackage.TOUCHSCREEN__TOUCH_SURFACE:
                return touchSurface != null;
            case ContextPackage.TOUCHSCREEN__GESTURE_RECOGNITION:
                return gestureRecognition != null;
        }
        return super.eIsSet(featureID);
    }

} // TouchscreenImpl
