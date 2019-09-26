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
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.MessageInputChannel;
import org.sercho.masp.models.Context.Mouse;
import org.sercho.masp.models.Context.PointingInputChannel;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Mouse</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.MouseImpl#getMotionSensor <em>
 * Motion Sensor</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.MouseImpl#getButtons <em>
 * Buttons</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MouseImpl extends HapticalInteractionResourcesImpl implements Mouse {

    /**
     * The cached value of the '{@link #getMotionSensor()
     * <em>Motion Sensor</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getMotionSensor()
     * @generated
     * @ordered
     */
    protected PointingInputChannel motionSensor;

    /**
     * The cached value of the '{@link #getButtons() <em>Buttons</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getButtons()
     * @generated
     * @ordered
     */
    protected MessageInputChannel buttons;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected MouseImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.MOUSE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public PointingInputChannel getMotionSensor() {
        return motionSensor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetMotionSensor(PointingInputChannel newMotionSensor, NotificationChain msgs) {
        PointingInputChannel oldMotionSensor = motionSensor;
        motionSensor = newMotionSensor;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.MOUSE__MOTION_SENSOR, oldMotionSensor, newMotionSensor);
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
    public void setMotionSensor(PointingInputChannel newMotionSensor) {
        if(newMotionSensor != motionSensor) {
            NotificationChain msgs = null;
            if(motionSensor != null)
                msgs = ((InternalEObject)motionSensor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.MOUSE__MOTION_SENSOR, null, msgs);
            if(newMotionSensor != null)
                msgs = ((InternalEObject)newMotionSensor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.MOUSE__MOTION_SENSOR, null, msgs);
            msgs = basicSetMotionSensor(newMotionSensor, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.MOUSE__MOTION_SENSOR, newMotionSensor, newMotionSensor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public MessageInputChannel getButtons() {
        return buttons;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetButtons(MessageInputChannel newButtons, NotificationChain msgs) {
        MessageInputChannel oldButtons = buttons;
        buttons = newButtons;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.MOUSE__BUTTONS, oldButtons, newButtons);
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
    public void setButtons(MessageInputChannel newButtons) {
        if(newButtons != buttons) {
            NotificationChain msgs = null;
            if(buttons != null)
                msgs = ((InternalEObject)buttons).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.MOUSE__BUTTONS, null, msgs);
            if(newButtons != null)
                msgs = ((InternalEObject)newButtons).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.MOUSE__BUTTONS, null, msgs);
            msgs = basicSetButtons(newButtons, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.MOUSE__BUTTONS, newButtons, newButtons));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.MOUSE__MOTION_SENSOR:
                return basicSetMotionSensor(null, msgs);
            case ContextPackage.MOUSE__BUTTONS:
                return basicSetButtons(null, msgs);
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
            case ContextPackage.MOUSE__MOTION_SENSOR:
                return getMotionSensor();
            case ContextPackage.MOUSE__BUTTONS:
                return getButtons();
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
            case ContextPackage.MOUSE__MOTION_SENSOR:
                setMotionSensor((PointingInputChannel)newValue);
                return;
            case ContextPackage.MOUSE__BUTTONS:
                setButtons((MessageInputChannel)newValue);
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
            case ContextPackage.MOUSE__MOTION_SENSOR:
                setMotionSensor((PointingInputChannel)null);
                return;
            case ContextPackage.MOUSE__BUTTONS:
                setButtons((MessageInputChannel)null);
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
            case ContextPackage.MOUSE__MOTION_SENSOR:
                return motionSensor != null;
            case ContextPackage.MOUSE__BUTTONS:
                return buttons != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<? extends InteractionResource> getTypeHook() {
        return Mouse.class;
    }

} // MouseImpl
