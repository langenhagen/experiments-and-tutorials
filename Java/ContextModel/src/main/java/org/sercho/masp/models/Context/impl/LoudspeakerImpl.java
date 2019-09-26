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
import org.sercho.masp.models.Context.Loudspeaker;
import org.sercho.masp.models.Context.MessageOutputChannel;
import org.sercho.masp.models.Context.Modality;
import org.sercho.masp.models.Context.User;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Loudspeaker</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.LoudspeakerImpl#getVoice <em>
 * Voice</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class LoudspeakerImpl extends OutputInteractionResourceImpl implements Loudspeaker {

    /**
     * The cached value of the '{@link #getVoice() <em>Voice</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getVoice()
     * @generated
     * @ordered
     */
    protected MessageOutputChannel voice;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected LoudspeakerImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.LOUDSPEAKER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public MessageOutputChannel getVoice() {
        return voice;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetVoice(MessageOutputChannel newVoice, NotificationChain msgs) {
        MessageOutputChannel oldVoice = voice;
        voice = newVoice;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.LOUDSPEAKER__VOICE, oldVoice, newVoice);
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
    public void setVoice(MessageOutputChannel newVoice) {
        if(newVoice != voice) {
            NotificationChain msgs = null;
            if(voice != null)
                msgs = ((InternalEObject)voice).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.LOUDSPEAKER__VOICE, null, msgs);
            if(newVoice != null)
                msgs = ((InternalEObject)newVoice).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.LOUDSPEAKER__VOICE, null, msgs);
            msgs = basicSetVoice(newVoice, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.LOUDSPEAKER__VOICE, newVoice, newVoice));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.LOUDSPEAKER__VOICE:
                return basicSetVoice(null, msgs);
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
            case ContextPackage.LOUDSPEAKER__VOICE:
                return getVoice();
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
            case ContextPackage.LOUDSPEAKER__VOICE:
                setVoice((MessageOutputChannel)newValue);
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
            case ContextPackage.LOUDSPEAKER__VOICE:
                setVoice((MessageOutputChannel)null);
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
            case ContextPackage.LOUDSPEAKER__VOICE:
                return voice != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Modality getModalityHook() {
        return Modality.AUDIO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<? extends InteractionResource> getTypeHook() {
        return Loudspeaker.class;
    }

    @Override
    public double getContextRatingHook(final User user) {
        // System.out.println("LoudspeakerImpl.getContextRatingHook(): this.getId:"
        // + this.getId() + "; user.getRoom=" + user.getRoom() +
        // "; this.getRoom=" + this.getRoom());
        if(this.getPlace() == null) {
            if(this.getUser() == null) {
                System.out.println("\n\nLoudspeakerImpl.getContextRatingHook(): loudspeaker has no user and no room....!!!\n\n ");
            } else {
                if(this.getUser() == user) {
                    return MAXIMUM_CONTEXT_RATING;
                } else {
                    System.out.println("LoudspeakerImpl.getContextRatingHook(): loudspeaker belongs to other user!");
                }
            }
        } else {
            if(user.getPlace() == this.getPlace()) {
                return MAXIMUM_CONTEXT_RATING;
            }
        }
        return MINIMUM_CONTEXT_RATING;
    }
} // LoudspeakerImpl
