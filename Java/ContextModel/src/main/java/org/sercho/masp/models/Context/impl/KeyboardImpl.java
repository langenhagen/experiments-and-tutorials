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
import org.sercho.masp.models.Context.Keyboard;
import org.sercho.masp.models.Context.MessageInputChannel;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Keyboard</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.KeyboardImpl#getKeys <em>Keys
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class KeyboardImpl extends HapticalInteractionResourcesImpl implements Keyboard {

    /**
     * The cached value of the '{@link #getKeys() <em>Keys</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getKeys()
     * @generated
     * @ordered
     */
    protected MessageInputChannel keys;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected KeyboardImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.KEYBOARD;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public MessageInputChannel getKeys() {
        return keys;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetKeys(MessageInputChannel newKeys, NotificationChain msgs) {
        MessageInputChannel oldKeys = keys;
        keys = newKeys;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.KEYBOARD__KEYS, oldKeys, newKeys);
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
    public void setKeys(MessageInputChannel newKeys) {
        if(newKeys != keys) {
            NotificationChain msgs = null;
            if(keys != null)
                msgs = ((InternalEObject)keys).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.KEYBOARD__KEYS, null, msgs);
            if(newKeys != null)
                msgs = ((InternalEObject)newKeys).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.KEYBOARD__KEYS, null, msgs);
            msgs = basicSetKeys(newKeys, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.KEYBOARD__KEYS, newKeys, newKeys));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.KEYBOARD__KEYS:
                return basicSetKeys(null, msgs);
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
            case ContextPackage.KEYBOARD__KEYS:
                return getKeys();
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
            case ContextPackage.KEYBOARD__KEYS:
                setKeys((MessageInputChannel)newValue);
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
            case ContextPackage.KEYBOARD__KEYS:
                setKeys((MessageInputChannel)null);
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
            case ContextPackage.KEYBOARD__KEYS:
                return keys != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<? extends InteractionResource> getTypeHook() {
        return Keyboard.class;
    }

} // KeyboardImpl
