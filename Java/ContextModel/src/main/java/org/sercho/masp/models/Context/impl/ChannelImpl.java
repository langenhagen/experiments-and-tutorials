/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.impl;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sercho.masp.models.Context.Channel;
import org.sercho.masp.models.Context.ConfigurationProperty;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.DistributionState;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.UI.ConcreteInteractor;
import org.sercho.masp.models.channel.api.ChannelAPI;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Channel</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.ChannelImpl#getDistributionState
 * <em>Distribution State</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.ChannelImpl#getElements <em>
 * Elements</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.ChannelImpl#getApi <em>Api
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.ChannelImpl#getConfiguration
 * <em>Configuration</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.ChannelImpl#isAvailable <em>
 * Available</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.ChannelImpl#getApiClass <em>
 * Api Class</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class ChannelImpl<C extends ConcreteInteractor, A extends ChannelAPI>
        extends EnvironmentElementImpl implements Channel<C, A> {

    /**
     * The default value of the '{@link #getDistributionState()
     * <em>Distribution State</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDistributionState()
     * @generated
     * @ordered
     */
    protected static final DistributionState DISTRIBUTION_STATE_EDEFAULT = DistributionState.CALCULATING;

    /**
     * The cached value of the '{@link #getDistributionState()
     * <em>Distribution State</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDistributionState()
     * @generated
     * @ordered
     */
    protected DistributionState distributionState = DISTRIBUTION_STATE_EDEFAULT;

    /**
     * The cached value of the '{@link #getElements() <em>Elements</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getElements()
     * @generated
     * @ordered
     */
    protected EList<C> elements;

    /**
     * The cached value of the '{@link #getApi() <em>Api</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getApi()
     * @generated
     * @ordered
     */
    protected A api;

    /**
     * The cached value of the '{@link #getConfiguration()
     * <em>Configuration</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getConfiguration()
     * @generated
     * @ordered
     */
    protected EList<ConfigurationProperty> configuration;

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
     * The default value of the '{@link #getApiClass() <em>Api Class</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getApiClass()
     * @generated
     * @ordered
     */
    protected static final String API_CLASS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getApiClass() <em>Api Class</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getApiClass()
     * @generated
     * @ordered
     */
    protected String apiClass = API_CLASS_EDEFAULT;

    /**
     * <code>ChannelImpl</code> constructor.
     * 
     * @generated
     */
    protected ChannelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.CHANNEL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DistributionState getDistributionState() {
        return distributionState;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDistributionState(DistributionState newDistributionState) {
        DistributionState oldDistributionState = distributionState;
        distributionState = newDistributionState == null ? DISTRIBUTION_STATE_EDEFAULT
                : newDistributionState;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.CHANNEL__DISTRIBUTION_STATE, oldDistributionState, distributionState));
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final InteractionResource getInteractionResource() {
        final EObject container = eContainer();
        return container instanceof InteractionResource ? (InteractionResource)container
                : null;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void interactionRequested() {
        getInteractionResource().setInteractionStatusWithTimeStamp(true);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void interactionFinished() {
        getInteractionResource().setInteractionStatusWithTimeStamp(false);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<C> getElements() {
        if(elements == null) {
            elements = new EObjectResolvingEList<C>(ConcreteInteractor.class, this, ContextPackage.CHANNEL__ELEMENTS);
        }
        return elements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public A getApi() {
        return api;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public abstract void setApi(A newApi);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ConfigurationProperty> getConfiguration() {
        if(configuration == null) {
            configuration = new EObjectContainmentEList<ConfigurationProperty>(ConfigurationProperty.class, this, ContextPackage.CHANNEL__CONFIGURATION);
        }
        return configuration;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.CHANNEL__AVAILABLE, oldAvailable, available));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getApiClass() {
        return apiClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setApiClass(String newApiClass) {
        String oldApiClass = apiClass;
        apiClass = newApiClass;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.CHANNEL__API_CLASS, oldApiClass, apiClass));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public final void setNewDistributionState(final DistributionState newDistributionState) {
        if(newDistributionState == null || newDistributionState == this.distributionState) {
            // nothing to do
            return;
        }
        final DistributionState oldState = this.distributionState;
        this.distributionState = newDistributionState;
        if(eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.CHANNEL__DISTRIBUTION_STATE, oldState, newDistributionState));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public synchronized final void add(final C element) {
        final A channelAPI = getApi();
        if(channelAPI == null) {
            throw new IllegalStateException("api not set");
        }
        // if(!channelAPI.supports(element)) {
        // throw new NotSupportedInteractorException(element, channelAPI);
        // }
        if(getElements().contains(element)) {
            return;
        }
        this.elements.add(element);
        addHook(element);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public synchronized final void remove(final C element) {
        if(element == null) {
            throw new IllegalArgumentException("element is null");
        }
        final String elementID = element.getId();
        if(elementID == null) {
            throw new IllegalArgumentException("ID is null in element: " + element);
        }
        for(final Iterator<C> i = getElements().iterator(); i.hasNext();) {
            if(elementID.equals(i.next().getId())) {
                i.remove();
                removeHook(element);
                return;
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public synchronized final void setApiInstance(final A apiInstance) {
        setApi(apiInstance);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setNewAvailable(final boolean newAvailable) {
        setAvailable(newAvailable);
        // check if parent IR's availability changes
        final InteractionResource ir = getInteractionResource();
        if(ir == null) {
            // no IR to check for availability
            return;
        }
        if(newAvailable) {
            for(final Channel<?, ?> channel : ir.getAllChannels()) {
                if(!channel.isAvailable()) {
                    // not all channels of IR available
                    return;
                }
            }
            // all channels of IR are available => IR is available
            ir.setAvailable(true);
        } else {
            // not all channels of IR are available => IR is not available
            ir.setAvailable(false);
        }
    }

    /**
     * <code>addHook</code> called whenever a new element has been added to this
     * channel.
     * 
     * @param element
     *            new element
     * @generated NOT
     */
    protected abstract void addHook(C element);

    /**
     * <code>removeHook</code> is called whenever an element is removed from
     * this channel.
     * 
     * @param element
     *            element removed from this channel
     * @generated NOT
     */
    protected abstract void removeHook(C element);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.CHANNEL__CONFIGURATION:
                return ((InternalEList<?>)getConfiguration()).basicRemove(otherEnd, msgs);
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
            case ContextPackage.CHANNEL__DISTRIBUTION_STATE:
                return getDistributionState();
            case ContextPackage.CHANNEL__ELEMENTS:
                return getElements();
            case ContextPackage.CHANNEL__API:
                return getApi();
            case ContextPackage.CHANNEL__CONFIGURATION:
                return getConfiguration();
            case ContextPackage.CHANNEL__AVAILABLE:
                return isAvailable();
            case ContextPackage.CHANNEL__API_CLASS:
                return getApiClass();
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
            case ContextPackage.CHANNEL__DISTRIBUTION_STATE:
                setDistributionState((DistributionState)newValue);
                return;
            case ContextPackage.CHANNEL__ELEMENTS:
                getElements().clear();
                getElements().addAll((Collection<? extends C>)newValue);
                return;
            case ContextPackage.CHANNEL__API:
                setApi((A)newValue);
                return;
            case ContextPackage.CHANNEL__CONFIGURATION:
                getConfiguration().clear();
                getConfiguration().addAll((Collection<? extends ConfigurationProperty>)newValue);
                return;
            case ContextPackage.CHANNEL__AVAILABLE:
                setAvailable((Boolean)newValue);
                return;
            case ContextPackage.CHANNEL__API_CLASS:
                setApiClass((String)newValue);
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
            case ContextPackage.CHANNEL__DISTRIBUTION_STATE:
                setDistributionState(DISTRIBUTION_STATE_EDEFAULT);
                return;
            case ContextPackage.CHANNEL__ELEMENTS:
                getElements().clear();
                return;
            case ContextPackage.CHANNEL__API:
                setApi((A)null);
                return;
            case ContextPackage.CHANNEL__CONFIGURATION:
                getConfiguration().clear();
                return;
            case ContextPackage.CHANNEL__AVAILABLE:
                setAvailable(AVAILABLE_EDEFAULT);
                return;
            case ContextPackage.CHANNEL__API_CLASS:
                setApiClass(API_CLASS_EDEFAULT);
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
            case ContextPackage.CHANNEL__DISTRIBUTION_STATE:
                return distributionState != DISTRIBUTION_STATE_EDEFAULT;
            case ContextPackage.CHANNEL__ELEMENTS:
                return elements != null && !elements.isEmpty();
            case ContextPackage.CHANNEL__API:
                return api != null;
            case ContextPackage.CHANNEL__CONFIGURATION:
                return configuration != null && !configuration.isEmpty();
            case ContextPackage.CHANNEL__AVAILABLE:
                return available != AVAILABLE_EDEFAULT;
            case ContextPackage.CHANNEL__API_CLASS:
                return API_CLASS_EDEFAULT == null ? apiClass != null
                        : !API_CLASS_EDEFAULT.equals(apiClass);
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
        result.append(" (distributionState: ");
        result.append(distributionState);
        result.append(", api: ");
        result.append(api);
        result.append(", available: ");
        result.append(available);
        result.append(", apiClass: ");
        result.append(apiClass);
        result.append(')');
        return result.toString();
    }

} // ChannelImpl
