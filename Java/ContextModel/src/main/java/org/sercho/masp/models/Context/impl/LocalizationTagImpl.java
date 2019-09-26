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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.ElementWithPosition;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.LocalisationProviderProxy;
import org.sercho.masp.models.Context.LocalizationTag;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Localization Tag</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.LocalizationTagImpl#isDetected
 * <em>Detected</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.LocalizationTagImpl#isRegister
 * <em>Register</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.LocalizationTagImpl#getProvider
 * <em>Provider</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.LocalizationTagImpl#getElement
 * <em>Element</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class LocalizationTagImpl extends ElementWithPositionImpl implements
        LocalizationTag {

    /**
     * The default value of the '{@link #isDetected() <em>Detected</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isDetected()
     * @generated
     * @ordered
     */
    protected static final boolean DETECTED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isDetected() <em>Detected</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isDetected()
     * @generated
     * @ordered
     */
    protected boolean detected = DETECTED_EDEFAULT;

    /**
     * The default value of the '{@link #isRegister() <em>Register</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isRegister()
     * @generated
     * @ordered
     */
    protected static final boolean REGISTER_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isRegister() <em>Register</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isRegister()
     * @generated
     * @ordered
     */
    protected boolean register = REGISTER_EDEFAULT;

    /**
     * The cached value of the '{@link #getElement() <em>Element</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getElement()
     * @generated
     * @ordered
     */
    protected ElementWithPosition element;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected LocalizationTagImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.LOCALIZATION_TAG;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public synchronized boolean isDetected() {
        return this.detected;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public synchronized final void setDetected(final boolean newDetected) {
        if(newDetected == this.detected) {
            return;
        }
        final boolean oldDetected = this.detected;
        this.detected = newDetected;
        if(eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.LOCALIZATION_TAG__DETECTED, oldDetected, this.detected));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public LocalisationProviderProxy getProvider() {
        if(eContainerFeatureID() != ContextPackage.LOCALIZATION_TAG__PROVIDER)
            return null;
        return (LocalisationProviderProxy)eContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public final NotificationChain basicSetProvider(final LocalisationProviderProxy newProvider, NotificationChain msgs) {
        // unregister from current provider (does nothing if no provider set)
        unregister();
        // no longer detected
        setDetected(false);

        msgs = eBasicSetContainer((InternalEObject)newProvider, ContextPackage.LOCALIZATION_TAG__PROVIDER, msgs);

        // register at current provider
        if(newProvider != null) {
            register();
        }

        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setProvider(LocalisationProviderProxy newProvider) {
        if(newProvider != eInternalContainer() || (eContainerFeatureID() != ContextPackage.LOCALIZATION_TAG__PROVIDER && newProvider != null)) {
            if(EcoreUtil.isAncestor(this, newProvider))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if(eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if(newProvider != null)
                msgs = ((InternalEObject)newProvider).eInverseAdd(this, ContextPackage.LOCALISATION_PROVIDER_PROXY__TAGS, LocalisationProviderProxy.class, msgs);
            msgs = basicSetProvider(newProvider, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.LOCALIZATION_TAG__PROVIDER, newProvider, newProvider));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isRegister() {
        return register;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setRegister(boolean newRegister) {
        boolean oldRegister = register;
        register = newRegister;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.LOCALIZATION_TAG__REGISTER, oldRegister, register));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ElementWithPosition getElement() {
        if(element != null && element.eIsProxy()) {
            InternalEObject oldElement = (InternalEObject)element;
            element = (ElementWithPosition)eResolveProxy(oldElement);
            if(element != oldElement) {
                if(eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ContextPackage.LOCALIZATION_TAG__ELEMENT, oldElement, element));
            }
        }
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ElementWithPosition basicGetElement() {
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public synchronized final NotificationChain basicSetElement(final ElementWithPosition newElement, NotificationChain msgs) {
        final ElementWithPosition oldElement = this.element;
        this.element = newElement;
        if(newElement != null) {
            final org.sercho.masp.models.Context.Vector position = getPosition();
            if(position != null) {
                newElement.setPosition(position.getX(), position.getY(), position.getZ());
            }
        }
        if(eNotificationRequired()) {
            final ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.LOCALIZATION_TAG__ELEMENT, oldElement, newElement);
            if(msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setElement(ElementWithPosition newElement) {
        if(newElement != element) {
            NotificationChain msgs = null;
            if(element != null)
                msgs = ((InternalEObject)element).eInverseRemove(this, ContextPackage.ELEMENT_WITH_POSITION__TAGS, ElementWithPosition.class, msgs);
            if(newElement != null)
                msgs = ((InternalEObject)newElement).eInverseAdd(this, ContextPackage.ELEMENT_WITH_POSITION__TAGS, ElementWithPosition.class, msgs);
            msgs = basicSetElement(newElement, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.LOCALIZATION_TAG__ELEMENT, newElement, newElement));
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final synchronized void register() {
        if(this.register) {
            // nothing to do
            return;
        }
        final LocalisationProviderProxy localisationProvider = getProvider();
        if(localisationProvider == null) {
            throw new IllegalStateException("No provider set in tag " + this);
        }
        localisationProvider.register(getId());
        this.register = true;
        this.log.debug("Registered " + getId());
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final synchronized void unregister() {
        if(!this.register) {
            // nothing to do
            return;
        }
        this.register = false;
        // try to unregister in provider
        final LocalisationProviderProxy localisationProvider = getProvider();
        if(localisationProvider == null) {
            this.log.warn("Cannot unregister, no provider set in tag " + this);
            return;
        }
        localisationProvider.unregister(getId());
        this.log.debug("Uregistered " + getId());
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.LOCALIZATION_TAG__PROVIDER:
                if(eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetProvider((LocalisationProviderProxy)otherEnd, msgs);
            case ContextPackage.LOCALIZATION_TAG__ELEMENT:
                if(element != null)
                    msgs = ((InternalEObject)element).eInverseRemove(this, ContextPackage.ELEMENT_WITH_POSITION__TAGS, ElementWithPosition.class, msgs);
                return basicSetElement((ElementWithPosition)otherEnd, msgs);
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
            case ContextPackage.LOCALIZATION_TAG__PROVIDER:
                return basicSetProvider(null, msgs);
            case ContextPackage.LOCALIZATION_TAG__ELEMENT:
                return basicSetElement(null, msgs);
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
            case ContextPackage.LOCALIZATION_TAG__PROVIDER:
                return eInternalContainer().eInverseRemove(this, ContextPackage.LOCALISATION_PROVIDER_PROXY__TAGS, LocalisationProviderProxy.class, msgs);
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
            case ContextPackage.LOCALIZATION_TAG__DETECTED:
                return isDetected();
            case ContextPackage.LOCALIZATION_TAG__REGISTER:
                return isRegister();
            case ContextPackage.LOCALIZATION_TAG__PROVIDER:
                return getProvider();
            case ContextPackage.LOCALIZATION_TAG__ELEMENT:
                if(resolve)
                    return getElement();
                return basicGetElement();
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
            case ContextPackage.LOCALIZATION_TAG__DETECTED:
                setDetected((Boolean)newValue);
                return;
            case ContextPackage.LOCALIZATION_TAG__REGISTER:
                setRegister((Boolean)newValue);
                return;
            case ContextPackage.LOCALIZATION_TAG__PROVIDER:
                setProvider((LocalisationProviderProxy)newValue);
                return;
            case ContextPackage.LOCALIZATION_TAG__ELEMENT:
                setElement((ElementWithPosition)newValue);
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
            case ContextPackage.LOCALIZATION_TAG__DETECTED:
                setDetected(DETECTED_EDEFAULT);
                return;
            case ContextPackage.LOCALIZATION_TAG__REGISTER:
                setRegister(REGISTER_EDEFAULT);
                return;
            case ContextPackage.LOCALIZATION_TAG__PROVIDER:
                setProvider((LocalisationProviderProxy)null);
                return;
            case ContextPackage.LOCALIZATION_TAG__ELEMENT:
                setElement((ElementWithPosition)null);
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
            case ContextPackage.LOCALIZATION_TAG__DETECTED:
                return detected != DETECTED_EDEFAULT;
            case ContextPackage.LOCALIZATION_TAG__REGISTER:
                return register != REGISTER_EDEFAULT;
            case ContextPackage.LOCALIZATION_TAG__PROVIDER:
                return getProvider() != null;
            case ContextPackage.LOCALIZATION_TAG__ELEMENT:
                return element != null;
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
        result.append(" (detected: ");
        result.append(detected);
        result.append(", register: ");
        result.append(register);
        result.append(')');
        return result.toString();
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    protected void setPositionHook(final double x, final double y, final double z) {
        final ElementWithPosition elementWithPosition = getElement();
        if(elementWithPosition != null) {
            elementWithPosition.setPosition(x, y, z);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public Environment getEnvironment() {
        final LocalisationProviderProxy provider = getProvider();
        return provider == null ? null : provider.getEnvironment();
    }

} // LocalizationTagImpl
