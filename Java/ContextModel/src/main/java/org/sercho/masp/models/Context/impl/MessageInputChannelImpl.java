/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.impl;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.MessageInputChannel;
import org.sercho.masp.models.UI.InteractorState;
import org.sercho.masp.models.UI.MessageInput;
import org.sercho.masp.models.UI.OneDimensional;
import org.sercho.masp.models.UI.UIPackage;
import org.sercho.masp.models.channel.api.MessageInputChannelAPI;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Message Input Channel</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.MessageInputChannelImpl#getPosition
 * <em>Position</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.MessageInputChannelImpl#getLength
 * <em>Length</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MessageInputChannelImpl extends
        ChannelImpl<MessageInput, MessageInputChannelAPI> implements MessageInputChannel {

    /**
     * The default value of the '{@link #getPosition() <em>Position</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPosition()
     * @generated
     * @ordered
     */
    protected static final int POSITION_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getPosition() <em>Position</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPosition()
     * @generated
     * @ordered
     */
    protected int position = POSITION_EDEFAULT;

    /**
     * The default value of the '{@link #getLength() <em>Length</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLength()
     * @generated
     * @ordered
     */
    protected static final int LENGTH_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getLength() <em>Length</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLength()
     * @generated
     * @ordered
     */
    protected int length = LENGTH_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected MessageInputChannelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.MESSAGE_INPUT_CHANNEL;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final int getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final int getLength() {
        return this.length;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setPosition(final int newPosition) {
        // new position
        if(newPosition != this.position) {
            final int old = this.position;
            this.position = newPosition;
            if(eNotificationRequired()) {
                eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.MESSAGE_INPUT_CHANNEL__POSITION, old, newPosition));
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setLength(final int newLength) {
        // new length
        if(newLength != this.length) {
            final int old = this.length;
            this.length = newLength;
            if(eNotificationRequired()) {
                eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.MESSAGE_INPUT_CHANNEL__LENGTH, old, newLength));
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setApi(final MessageInputChannelAPI channelAPI) {
        this.api = channelAPI;
        if(channelAPI != null) {
            channelAPI.setCallback(this);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void newMessage(final String message) {
        final List<MessageInput> inputs = getElements();
        MessageInput m;
        for(int i = 0; i < inputs.size(); i++) {
            m = inputs.get(i);
            // send message only to focused elements
            if(m.getState().getValue() > InteractorState.ACTIVE_VALUE && m.newMessage(message)) {
                return;
            }
        }
        // no focused input processed it, try active only
        for(int i = 0; i < inputs.size(); i++) {
            m = inputs.get(i);
            // send message only to active elements
            if(m.getState().getValue() == InteractorState.ACTIVE_VALUE && m.newMessage(message)) {
                return;
            }
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch(featureID) {
            case ContextPackage.MESSAGE_INPUT_CHANNEL__POSITION:
                return getPosition();
            case ContextPackage.MESSAGE_INPUT_CHANNEL__LENGTH:
                return getLength();
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
            case ContextPackage.MESSAGE_INPUT_CHANNEL__POSITION:
                setPosition((Integer)newValue);
                return;
            case ContextPackage.MESSAGE_INPUT_CHANNEL__LENGTH:
                setLength((Integer)newValue);
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
            case ContextPackage.MESSAGE_INPUT_CHANNEL__POSITION:
                setPosition(POSITION_EDEFAULT);
                return;
            case ContextPackage.MESSAGE_INPUT_CHANNEL__LENGTH:
                setLength(LENGTH_EDEFAULT);
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
            case ContextPackage.MESSAGE_INPUT_CHANNEL__POSITION:
                return position != POSITION_EDEFAULT;
            case ContextPackage.MESSAGE_INPUT_CHANNEL__LENGTH:
                return length != LENGTH_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if(baseClass == OneDimensional.class) {
            switch(derivedFeatureID) {
                case ContextPackage.MESSAGE_INPUT_CHANNEL__POSITION:
                    return UIPackage.ONE_DIMENSIONAL__POSITION;
                case ContextPackage.MESSAGE_INPUT_CHANNEL__LENGTH:
                    return UIPackage.ONE_DIMENSIONAL__LENGTH;
                default:
                    return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if(baseClass == OneDimensional.class) {
            switch(baseFeatureID) {
                case UIPackage.ONE_DIMENSIONAL__POSITION:
                    return ContextPackage.MESSAGE_INPUT_CHANNEL__POSITION;
                case UIPackage.ONE_DIMENSIONAL__LENGTH:
                    return ContextPackage.MESSAGE_INPUT_CHANNEL__LENGTH;
                default:
                    return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    protected final void addHook(@SuppressWarnings("unused") final MessageInput element) {
        // making final, because there is nothing to do in MessageInputChannels
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    protected final void removeHook(@SuppressWarnings("unused") final MessageInput element) {
        // making final, because there is nothing to do in MessageInputChannels
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
        result.append(" (position: ");
        result.append(position);
        result.append(", length: ");
        result.append(length);
        result.append(')');
        return result.toString();
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setNewLength(final int newLength) {
        setLength(newLength);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setNewPosition(final int newPosition) {
        setPosition(newPosition);
    }

} // MessageInputChannelImpl
