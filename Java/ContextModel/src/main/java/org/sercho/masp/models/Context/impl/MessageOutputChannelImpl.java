/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.MessageOutputChannel;
import org.sercho.masp.models.UI.MessageOutput;
import org.sercho.masp.models.UI.OneDimensional;
import org.sercho.masp.models.UI.UIPackage;
import org.sercho.masp.models.channel.api.MessageOutputAdapter;
import org.sercho.masp.models.channel.api.MessageOutputChannelAPI;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Message Output Channel</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.MessageOutputChannelImpl#getPosition
 * <em>Position</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.MessageOutputChannelImpl#getLength
 * <em>Length</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MessageOutputChannelImpl extends
        ChannelImpl<MessageOutput, MessageOutputChannelAPI> implements
        MessageOutputChannel {

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
    protected MessageOutputChannelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.MESSAGE_OUTPUT_CHANNEL;
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
     * <code>adapter</code> handling the state of this channel's elements.
     * 
     * @generated NOT
     */
    private MessageOutputAdapter adapter;

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setApi(final MessageOutputChannelAPI channelAPI) {
        if(channelAPI == null) {
            throw new IllegalArgumentException("channelAPI is null");
        }
        if(this.api == null) {
            this.api = channelAPI;
            setAdapter(new MessageOutputAdapter(channelAPI));
        }
    }

    /**
     * <code>setAdapter</code> sets a new adapter for all elements currently on
     * channel. If there was another adapter previously, it is removed.
     * 
     * @param newAdapter
     *            new state adapter to set
     * @generated NOT
     */
    private synchronized void setAdapter(final MessageOutputAdapter newAdapter) {
        final List<MessageOutput> outputs = new ArrayList<MessageOutput>(getElements());
        if(this.adapter == null) {
            if(newAdapter == null) {
                return;
            }
            for(final MessageOutput messageOutput : outputs) {
                newAdapter.messageOutputAdded(messageOutput);
            }
        } else {
            this.adapter.dispose();
            if(newAdapter == null) {
                for(final MessageOutput messageOutput : outputs) {
                    this.adapter.messageOutputRemoved(messageOutput);
                }
            } else {
                for(final MessageOutput messageOutput : outputs) {
                    this.adapter.messageOutputRemoved(messageOutput);
                    newAdapter.messageOutputAdded(messageOutput);
                }
            }
        }
        this.adapter = newAdapter;
    }

    /**
     * <code>getAPI</code> returns the api object of this channel (
     * {@link #getApi()}) already casted.
     * 
     * @return MessageOutputChannelAPI - API of this channel
     * @generated NOT
     */
    private MessageOutputChannelAPI getAPI() {
        return this.api;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    protected final void addHook(final MessageOutput element) {
        if(this.adapter != null) {
            this.adapter.messageOutputAdded(element);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    protected final void removeHook(final MessageOutput element) {
        if(this.adapter != null) {
            this.adapter.messageOutputRemoved(element);
        }
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
                eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.MESSAGE_OUTPUT_CHANNEL__POSITION, old, newPosition));
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
                eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.MESSAGE_OUTPUT_CHANNEL__LENGTH, old, newLength));
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
            case ContextPackage.MESSAGE_OUTPUT_CHANNEL__POSITION:
                return getPosition();
            case ContextPackage.MESSAGE_OUTPUT_CHANNEL__LENGTH:
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
            case ContextPackage.MESSAGE_OUTPUT_CHANNEL__POSITION:
                setPosition((Integer)newValue);
                return;
            case ContextPackage.MESSAGE_OUTPUT_CHANNEL__LENGTH:
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
            case ContextPackage.MESSAGE_OUTPUT_CHANNEL__POSITION:
                setPosition(POSITION_EDEFAULT);
                return;
            case ContextPackage.MESSAGE_OUTPUT_CHANNEL__LENGTH:
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
            case ContextPackage.MESSAGE_OUTPUT_CHANNEL__POSITION:
                return position != POSITION_EDEFAULT;
            case ContextPackage.MESSAGE_OUTPUT_CHANNEL__LENGTH:
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
                case ContextPackage.MESSAGE_OUTPUT_CHANNEL__POSITION:
                    return UIPackage.ONE_DIMENSIONAL__POSITION;
                case ContextPackage.MESSAGE_OUTPUT_CHANNEL__LENGTH:
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
                    return ContextPackage.MESSAGE_OUTPUT_CHANNEL__POSITION;
                case UIPackage.ONE_DIMENSIONAL__LENGTH:
                    return ContextPackage.MESSAGE_OUTPUT_CHANNEL__LENGTH;
                default:
                    return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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

} // MessageOutputChannelImpl
