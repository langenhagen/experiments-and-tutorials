/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sercho.masp.models.Context.Channel;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.Modality;
import org.sercho.masp.models.Context.User;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Interaction Resource</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.InteractionResourceImpl#getUser
 * <em>User</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.InteractionResourceImpl#getDevice
 * <em>Device</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.InteractionResourceImpl#isAvailable
 * <em>Available</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.InteractionResourceImpl#getModality
 * <em>Modality</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.InteractionResourceImpl#getMobile
 * <em>Mobile</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.InteractionResourceImpl#getPersonal
 * <em>Personal</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.InteractionResourceImpl#isInteractionStatus
 * <em>Interaction Status</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.InteractionResourceImpl#getInteractionStatusTimeStamp
 * <em>Interaction Status Time Stamp</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class InteractionResourceImpl extends ElementWithPositionImpl implements
        InteractionResource {

    /**
     * <code>LOG</code>
     * 
     * @generated NOT
     */
    private static final transient Log LOG = LogFactory.getLog(InteractionResourceImpl.class);

    /**
     * The cached value of the '{@link #getUser() <em>User</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getUser()
     * @generated
     * @ordered
     */
    protected User user;

    /**
     * The default value of the '{@link #isAvailable() <em>Available</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isAvailable()
     * @generated
     * @ordered
     */
    protected static final boolean AVAILABLE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isAvailable() <em>Available</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isAvailable()
     * @generated
     * @ordered
     */
    protected boolean available = AVAILABLE_EDEFAULT;

    /**
     * The default value of the '{@link #getModality() <em>Modality</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getModality()
     * @generated
     * @ordered
     */
    protected static final Modality MODALITY_EDEFAULT = Modality.GRAPHIC;

    /**
     * The cached value of the '{@link #getModality() <em>Modality</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getModality()
     * @generated NOT
     * @ordered
     */
    protected Modality modality;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getType()
     * @generated NOT
     * @ordered
     */
    protected Class<? extends InteractionResource> type;

    /**
     * The default value of the '{@link #getMobile() <em>Mobile</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMobile()
     * @generated NOT
     * @ordered
     */
    protected static final Boolean MOBILE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMobile() <em>Mobile</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMobile()
     * @generated
     * @ordered
     */
    protected Boolean mobile = MOBILE_EDEFAULT;

    /**
     * The default value of the '{@link #getPersonal() <em>Personal</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPersonal()
     * @generated
     * @ordered
     */
    protected static final String PERSONAL_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getPersonal() <em>Personal</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPersonal()
     * @generated
     * @ordered
     */
    protected String personal = PERSONAL_EDEFAULT;

    /**
     * The default value of the '{@link #isInteractionStatus()
     * <em>Interaction Status</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isInteractionStatus()
     * @generated
     * @ordered
     */
    protected static final boolean INTERACTION_STATUS_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isInteractionStatus()
     * <em>Interaction Status</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isInteractionStatus()
     * @generated
     * @ordered
     */
    protected boolean interactionStatus = INTERACTION_STATUS_EDEFAULT;

    /**
     * The default value of the '{@link #getInteractionStatusTimeStamp()
     * <em>Interaction Status Time Stamp</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getInteractionStatusTimeStamp()
     * @generated
     * @ordered
     */
    protected static final long INTERACTION_STATUS_TIME_STAMP_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getInteractionStatusTimeStamp()
     * <em>Interaction Status Time Stamp</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getInteractionStatusTimeStamp()
     * @generated
     * @ordered
     */
    protected long interactionStatusTimeStamp = INTERACTION_STATUS_TIME_STAMP_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    protected InteractionResourceImpl() {
        super();
        this.modality = getModalityHook();
        this.type = getTypeHook();
        this.position = ContextFactory.eINSTANCE.createVector();
        this.position.setCoordinates(-1, -1, -1);
    }

    /**
     * <code>getModalityHook</code>
     * 
     * @return Modality of InteractionResource the extending class implements.
     */
    protected abstract Modality getModalityHook();

    /**
     * <code>getTypeHook</code>
     * 
     * @return Class of InteractionResource the extending class implements.
     */
    protected abstract Class<? extends InteractionResource> getTypeHook();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.INTERACTION_RESOURCE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public User getUser() {
        if(user != null && user.eIsProxy()) {
            InternalEObject oldUser = (InternalEObject)user;
            user = (User)eResolveProxy(oldUser);
            if(user != oldUser) {
                if(eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ContextPackage.INTERACTION_RESOURCE__USER, oldUser, user));
            }
        }
        return user;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public User basicGetUser() {
        return user;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetUser(User newUser, NotificationChain msgs) {
        User oldUser = user;
        user = newUser;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.INTERACTION_RESOURCE__USER, oldUser, newUser);
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
    public void setUser(User newUser) {
        if(newUser != user) {
            NotificationChain msgs = null;
            if(user != null)
                msgs = ((InternalEObject)user).eInverseRemove(this, ContextPackage.USER__RESOURCES, User.class, msgs);
            if(newUser != null)
                msgs = ((InternalEObject)newUser).eInverseAdd(this, ContextPackage.USER__RESOURCES, User.class, msgs);
            msgs = basicSetUser(newUser, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.INTERACTION_RESOURCE__USER, newUser, newUser));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Device getDevice() {
        if(eContainerFeatureID() != ContextPackage.INTERACTION_RESOURCE__DEVICE)
            return null;
        return (Device)eContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetDevice(Device newDevice, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newDevice, ContextPackage.INTERACTION_RESOURCE__DEVICE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDevice(Device newDevice) {
        if(newDevice != eInternalContainer() || (eContainerFeatureID() != ContextPackage.INTERACTION_RESOURCE__DEVICE && newDevice != null)) {
            if(EcoreUtil.isAncestor(this, newDevice))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if(eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if(newDevice != null)
                msgs = ((InternalEObject)newDevice).eInverseAdd(this, ContextPackage.DEVICE__RESOURCES, Device.class, msgs);
            msgs = basicSetDevice(newDevice, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.INTERACTION_RESOURCE__DEVICE, newDevice, newDevice));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isAvailable() {
        return available;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setAvailable(boolean newAvailable) {
        boolean oldAvailable = available;
        available = newAvailable;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.INTERACTION_RESOURCE__AVAILABLE, oldAvailable, available));
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final Environment getEnvironment() {
        final Device device = getDevice();
        return device == null ? null : device.getEnvironment();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Modality getModality() {
        return modality;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setModality(Modality newModality) {
        Modality oldModality = modality;
        modality = newModality == null ? MODALITY_EDEFAULT : newModality;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.INTERACTION_RESOURCE__MODALITY, oldModality, modality));
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public Boolean getMobile() {
        if(this.mobile != null) {
            return this.mobile;
        }
        if(this.getDevice() != null) {
            return Boolean.valueOf(this.getDevice().isMobile());
        }

        if(LOG.isDebugEnabled()) {
            LOG.debug("InteractionResourceImpl.getMobile(): no client found in this resource (id=" + this.getId() + ")");
        }
        return Boolean.FALSE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setMobile(Boolean newMobile) {
        Boolean oldMobile = mobile;
        mobile = newMobile;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.INTERACTION_RESOURCE__MOBILE, oldMobile, mobile));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getPersonal() {
        return personal;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setPersonal(String newPersonal) {
        String oldPersonal = personal;
        personal = newPersonal;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.INTERACTION_RESOURCE__PERSONAL, oldPersonal, personal));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isInteractionStatus() {
        return interactionStatus;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setInteractionStatus(boolean newInteractionStatus) {
        boolean oldInteractionStatus = interactionStatus;
        interactionStatus = newInteractionStatus;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.INTERACTION_RESOURCE__INTERACTION_STATUS, oldInteractionStatus, interactionStatus));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public long getInteractionStatusTimeStamp() {
        return interactionStatusTimeStamp;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setInteractionStatusTimeStamp(long newInteractionStatusTimeStamp) {
        long oldInteractionStatusTimeStamp = interactionStatusTimeStamp;
        interactionStatusTimeStamp = newInteractionStatusTimeStamp;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.INTERACTION_RESOURCE__INTERACTION_STATUS_TIME_STAMP, oldInteractionStatusTimeStamp, interactionStatusTimeStamp));
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final double getContextRating(final User user) {
        return getContextRatingHook(user);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final EList<Channel<?, ?>> getAllChannels() {
        final TreeIterator<EObject> allContents = eAllContents();
        EObject content;
        final EList<Channel<?, ?>> allChannels = new BasicEList<Channel<?, ?>>();
        while(allContents.hasNext()) {
            content = allContents.next();
            if(content instanceof Channel<?, ?>) {
                allChannels.add((Channel<?, ?>)content);
            }
        }
        return allChannels;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public void setInteractionStatusWithTimeStamp(final boolean status) {
        setInteractionStatusTimeStamp(System.currentTimeMillis());
        setInteractionStatus(status);
    }

    protected abstract double getContextRatingHook(User user);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.INTERACTION_RESOURCE__USER:
                if(user != null)
                    msgs = ((InternalEObject)user).eInverseRemove(this, ContextPackage.USER__RESOURCES, User.class, msgs);
                return basicSetUser((User)otherEnd, msgs);
            case ContextPackage.INTERACTION_RESOURCE__DEVICE:
                if(eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetDevice((Device)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.INTERACTION_RESOURCE__USER:
                return basicSetUser(null, msgs);
            case ContextPackage.INTERACTION_RESOURCE__DEVICE:
                return basicSetDevice(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch(eContainerFeatureID()) {
            case ContextPackage.INTERACTION_RESOURCE__DEVICE:
                return eInternalContainer().eInverseRemove(this, ContextPackage.DEVICE__RESOURCES, Device.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch(featureID) {
            case ContextPackage.INTERACTION_RESOURCE__USER:
                if(resolve)
                    return getUser();
                return basicGetUser();
            case ContextPackage.INTERACTION_RESOURCE__DEVICE:
                return getDevice();
            case ContextPackage.INTERACTION_RESOURCE__AVAILABLE:
                return isAvailable();
            case ContextPackage.INTERACTION_RESOURCE__MODALITY:
                return getModality();
            case ContextPackage.INTERACTION_RESOURCE__MOBILE:
                return getMobile();
            case ContextPackage.INTERACTION_RESOURCE__PERSONAL:
                return getPersonal();
            case ContextPackage.INTERACTION_RESOURCE__INTERACTION_STATUS:
                return isInteractionStatus();
            case ContextPackage.INTERACTION_RESOURCE__INTERACTION_STATUS_TIME_STAMP:
                return getInteractionStatusTimeStamp();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch(featureID) {
            case ContextPackage.INTERACTION_RESOURCE__USER:
                setUser((User)newValue);
                return;
            case ContextPackage.INTERACTION_RESOURCE__DEVICE:
                setDevice((Device)newValue);
                return;
            case ContextPackage.INTERACTION_RESOURCE__AVAILABLE:
                setAvailable((Boolean)newValue);
                return;
            case ContextPackage.INTERACTION_RESOURCE__MODALITY:
                setModality((Modality)newValue);
                return;
            case ContextPackage.INTERACTION_RESOURCE__MOBILE:
                setMobile((Boolean)newValue);
                return;
            case ContextPackage.INTERACTION_RESOURCE__PERSONAL:
                setPersonal((String)newValue);
                return;
            case ContextPackage.INTERACTION_RESOURCE__INTERACTION_STATUS:
                setInteractionStatus((Boolean)newValue);
                return;
            case ContextPackage.INTERACTION_RESOURCE__INTERACTION_STATUS_TIME_STAMP:
                setInteractionStatusTimeStamp((Long)newValue);
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
            case ContextPackage.INTERACTION_RESOURCE__USER:
                setUser((User)null);
                return;
            case ContextPackage.INTERACTION_RESOURCE__DEVICE:
                setDevice((Device)null);
                return;
            case ContextPackage.INTERACTION_RESOURCE__AVAILABLE:
                setAvailable(AVAILABLE_EDEFAULT);
                return;
            case ContextPackage.INTERACTION_RESOURCE__MODALITY:
                setModality(MODALITY_EDEFAULT);
                return;
            case ContextPackage.INTERACTION_RESOURCE__MOBILE:
                setMobile(MOBILE_EDEFAULT);
                return;
            case ContextPackage.INTERACTION_RESOURCE__PERSONAL:
                setPersonal(PERSONAL_EDEFAULT);
                return;
            case ContextPackage.INTERACTION_RESOURCE__INTERACTION_STATUS:
                setInteractionStatus(INTERACTION_STATUS_EDEFAULT);
                return;
            case ContextPackage.INTERACTION_RESOURCE__INTERACTION_STATUS_TIME_STAMP:
                setInteractionStatusTimeStamp(INTERACTION_STATUS_TIME_STAMP_EDEFAULT);
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
            case ContextPackage.INTERACTION_RESOURCE__USER:
                return user != null;
            case ContextPackage.INTERACTION_RESOURCE__DEVICE:
                return getDevice() != null;
            case ContextPackage.INTERACTION_RESOURCE__AVAILABLE:
                return available != AVAILABLE_EDEFAULT;
            case ContextPackage.INTERACTION_RESOURCE__MODALITY:
                return modality != MODALITY_EDEFAULT;
            case ContextPackage.INTERACTION_RESOURCE__MOBILE:
                return MOBILE_EDEFAULT == null ? mobile != null
                        : !MOBILE_EDEFAULT.equals(mobile);
            case ContextPackage.INTERACTION_RESOURCE__PERSONAL:
                return PERSONAL_EDEFAULT == null ? personal != null
                        : !PERSONAL_EDEFAULT.equals(personal);
            case ContextPackage.INTERACTION_RESOURCE__INTERACTION_STATUS:
                return interactionStatus != INTERACTION_STATUS_EDEFAULT;
            case ContextPackage.INTERACTION_RESOURCE__INTERACTION_STATUS_TIME_STAMP:
                return interactionStatusTimeStamp != INTERACTION_STATUS_TIME_STAMP_EDEFAULT;
        }
        return super.eIsSet(featureID);
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
        result.append(" (available: ");
        result.append(available);
        result.append(", modality: ");
        result.append(modality);
        result.append(", mobile: ");
        result.append(mobile);
        result.append(", personal: ");
        result.append(personal);
        result.append(", interactionStatus: ");
        result.append(interactionStatus);
        result.append(", interactionStatusTimeStamp: ");
        result.append(interactionStatusTimeStamp);
        result.append(')');
        return result.toString();
    }

} // InteractionResourceImpl
