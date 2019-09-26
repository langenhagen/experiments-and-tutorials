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
import org.sercho.masp.models.Context.Microphone;
import org.sercho.masp.models.Context.Modality;
import org.sercho.masp.models.Context.User;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Microphone</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.MicrophoneImpl#getVoiceRecognition
 * <em>Voice Recognition</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MicrophoneImpl extends InputInteractionResourceImpl implements Microphone {

    /**
     * The cached value of the '{@link #getVoiceRecognition()
     * <em>Voice Recognition</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getVoiceRecognition()
     * @generated
     * @ordered
     */
    protected MessageInputChannel voiceRecognition;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected MicrophoneImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.MICROPHONE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public MessageInputChannel getVoiceRecognition() {
        return voiceRecognition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetVoiceRecognition(MessageInputChannel newVoiceRecognition, NotificationChain msgs) {
        MessageInputChannel oldVoiceRecognition = voiceRecognition;
        voiceRecognition = newVoiceRecognition;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.MICROPHONE__VOICE_RECOGNITION, oldVoiceRecognition, newVoiceRecognition);
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
    public void setVoiceRecognition(MessageInputChannel newVoiceRecognition) {
        if(newVoiceRecognition != voiceRecognition) {
            NotificationChain msgs = null;
            if(voiceRecognition != null)
                msgs = ((InternalEObject)voiceRecognition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.MICROPHONE__VOICE_RECOGNITION, null, msgs);
            if(newVoiceRecognition != null)
                msgs = ((InternalEObject)newVoiceRecognition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.MICROPHONE__VOICE_RECOGNITION, null, msgs);
            msgs = basicSetVoiceRecognition(newVoiceRecognition, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.MICROPHONE__VOICE_RECOGNITION, newVoiceRecognition, newVoiceRecognition));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.MICROPHONE__VOICE_RECOGNITION:
                return basicSetVoiceRecognition(null, msgs);
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
            case ContextPackage.MICROPHONE__VOICE_RECOGNITION:
                return getVoiceRecognition();
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
            case ContextPackage.MICROPHONE__VOICE_RECOGNITION:
                setVoiceRecognition((MessageInputChannel)newValue);
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
            case ContextPackage.MICROPHONE__VOICE_RECOGNITION:
                setVoiceRecognition((MessageInputChannel)null);
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
            case ContextPackage.MICROPHONE__VOICE_RECOGNITION:
                return voiceRecognition != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Modality getModalityHook() {
        return Modality.VOCAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<? extends InteractionResource> getTypeHook() {
        return Microphone.class;
    }

    @Override
    public double getContextRatingHook(final User user) {
        if(user.getPlace() == this.getPlace()) {
            return MAXIMUM_CONTEXT_RATING;
        }
        return MINIMUM_CONTEXT_RATING;
    }

} // MicrophoneImpl
