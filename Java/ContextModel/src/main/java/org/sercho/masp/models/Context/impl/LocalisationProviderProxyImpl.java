/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.impl;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sercho.masp.context.providers.location.LocalisationProvider;
import org.sercho.masp.models.Context.ConfigurationProperty;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.LocalisationProviderProxy;
import org.sercho.masp.models.Context.LocalizationTag;
import org.sercho.masp.models.Context.Vector;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Localisation Provider Proxy</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.LocalisationProviderProxyImpl#getApiClass
 * <em>Api Class</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.LocalisationProviderProxyImpl#getApi
 * <em>Api</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.LocalisationProviderProxyImpl#getTags
 * <em>Tags</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.LocalisationProviderProxyImpl#getConfiguration
 * <em>Configuration</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class LocalisationProviderProxyImpl extends ContextProviderImpl implements
        LocalisationProviderProxy {

    /**
     * <code>LOG</code> is the logger for this class
     * 
     * @generated NOT
     */
    public static final transient Log LOG = LogFactory.getLog(LocalisationProviderProxyImpl.class);

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
     * The default value of the '{@link #getApi() <em>Api</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getApi()
     * @generated
     * @ordered
     */
    protected static final LocalisationProvider API_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getApi() <em>Api</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getApi()
     * @generated
     * @ordered
     */
    protected LocalisationProvider api = API_EDEFAULT;

    /**
     * The cached value of the '{@link #getTags() <em>Tags</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTags()
     * @generated
     * @ordered
     */
    protected EList<LocalizationTag> tags;

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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected LocalisationProviderProxyImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.LOCALISATION_PROVIDER_PROXY;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.LOCALISATION_PROVIDER_PROXY__API_CLASS, oldApiClass, apiClass));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public LocalisationProvider getApi() {
        return api;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setApi(LocalisationProvider newApi) {
        LocalisationProvider oldApi = api;
        api = newApi;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.LOCALISATION_PROVIDER_PROXY__API, oldApi, api));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<LocalizationTag> getTags() {
        if(tags == null) {
            tags = new EObjectContainmentWithInverseEList<LocalizationTag>(LocalizationTag.class, this, ContextPackage.LOCALISATION_PROVIDER_PROXY__TAGS, ContextPackage.LOCALIZATION_TAG__PROVIDER);
        }
        return tags;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ConfigurationProperty> getConfiguration() {
        if(configuration == null) {
            configuration = new EObjectContainmentEList<ConfigurationProperty>(ConfigurationProperty.class, this, ContextPackage.LOCALISATION_PROVIDER_PROXY__CONFIGURATION);
        }
        return configuration;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final boolean register(final String id) {
        // return api != null && api.register(id);
        boolean result = false;
        if(this.api != null) {
            result = this.api.register(id);
        }
        LOG.debug("reverseId=" + id + "; result=" + result + "; api=" + (this.api == null ? null
                : this.api.getClass()));
        return result;
    }

    /**
     * Converts vector <code>newPos</code> to model vector format. Incooperates
     * <code>scaleFactor</code>.
     * 
     * @param newPos
     *            Vector
     * @return converted vector.
     * @generated NOT
     */
    Vector convert(final org.sercho.masp.context.providers.location.Vector newPos) {
        if(newPos == null) {
            return null;
        }
        final Vector newVector = ContextPackage.eINSTANCE.getContextFactory().createVector();
        newVector.setCoordinates(newPos.getX(), newPos.getY(), newPos.getZ());
        return newVector;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final Vector getPosition(final String id) {
        Vector position = null;
        if(this.api != null) {
            position = convert(this.api.getPosition(id));
            LOG.debug("ContextModelLocalisationProviderImpl.getPosition(): position from api for id=" + id + " is=" + position + "; providerClass=" + this.api.getClass().getName());
        }
        return position;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public void unregister(final String id) {
        LOG.debug("\n\nContextModelLocalisationProviderImpl.unregister(): called with id=" + id);
        if(this.api != null) {
            this.api.unregister(id);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void newPosition(final String tagID, final double x, final double y, final double z) {
        if(tagID == null) {
            throw new IllegalArgumentException("tagID is null");
        }
        synchronized(getTags()) {
            for(final LocalizationTag tag : getTags()) {
                if(tagID.equals(tag.getId())) {
                    // tag already exists, set position
                    tag.setPosition(x, y, z);
                    tag.setDetected(true);
                    return;
                }
            }
        }
        // no such tag exists
        LOG.warn("Unknown tag " + tagID);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void tagGone(final String tagID) {
        if(tagID == null) {
            throw new IllegalArgumentException("tagID is null");
        }
        synchronized(getTags()) {
            LocalizationTag tag;
            for(final Iterator<LocalizationTag> i = getTags().iterator(); i.hasNext();) {
                tag = i.next();
                if(tagID.equals(tag.getId())) {
                    // report as undetected
                    tag.setDetected(false);
                    return;
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public synchronized void setApiInstance(final LocalisationProvider apiInstance) {
        if(this.api != null) {
            // unregister for tags
            synchronized(getTags()) {
                for(final LocalizationTag tag : getTags()) {
                    this.api.unregister(tag.getId());
                }
            }
        }
        setApi(apiInstance);
        if(this.api != null) {
            // register for tags in a new thread
            new Thread() {

                /**
                 * {@inheritDoc}
                 */
                @Override
                public void run() {
                    synchronized(getTags()) {
                        for(final LocalizationTag tag : getTags()) {
                            LocalisationProviderProxyImpl.this.api.register(tag.getId());
                        }
                    }
                }
            }.start();
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     * @author Andre Schulz
     */
    @Override
    public void addTag(final LocalizationTag tag, final String id) {
        if(tag == null) {
            throw new IllegalArgumentException("tag argument must not be null in method addTag!");
        } else if(tag.getId() == null && id == null) {
            throw new IllegalArgumentException("id argument must not be null in method addTag if tag.id is null!");
        }

        if(id != null) {
            tag.setId(id);
        }

        synchronized(getTags()) {
            getTags().add(tag);
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     * @author Andre Schulz
     */
    @Override
    public void removeTag(final LocalizationTag tag) {
        if(tag == null) {
            throw new IllegalArgumentException("tag argument must not be null in method removeTag!");
        }

        synchronized(getTags()) {
            getTags().remove(tag);
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.LOCALISATION_PROVIDER_PROXY__TAGS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getTags()).basicAdd(otherEnd, msgs);
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
            case ContextPackage.LOCALISATION_PROVIDER_PROXY__TAGS:
                return ((InternalEList<?>)getTags()).basicRemove(otherEnd, msgs);
            case ContextPackage.LOCALISATION_PROVIDER_PROXY__CONFIGURATION:
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
            case ContextPackage.LOCALISATION_PROVIDER_PROXY__API_CLASS:
                return getApiClass();
            case ContextPackage.LOCALISATION_PROVIDER_PROXY__API:
                return getApi();
            case ContextPackage.LOCALISATION_PROVIDER_PROXY__TAGS:
                return getTags();
            case ContextPackage.LOCALISATION_PROVIDER_PROXY__CONFIGURATION:
                return getConfiguration();
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
            case ContextPackage.LOCALISATION_PROVIDER_PROXY__API_CLASS:
                setApiClass((String)newValue);
                return;
            case ContextPackage.LOCALISATION_PROVIDER_PROXY__API:
                setApi((LocalisationProvider)newValue);
                return;
            case ContextPackage.LOCALISATION_PROVIDER_PROXY__TAGS:
                getTags().clear();
                getTags().addAll((Collection<? extends LocalizationTag>)newValue);
                return;
            case ContextPackage.LOCALISATION_PROVIDER_PROXY__CONFIGURATION:
                getConfiguration().clear();
                getConfiguration().addAll((Collection<? extends ConfigurationProperty>)newValue);
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
            case ContextPackage.LOCALISATION_PROVIDER_PROXY__API_CLASS:
                setApiClass(API_CLASS_EDEFAULT);
                return;
            case ContextPackage.LOCALISATION_PROVIDER_PROXY__API:
                setApi(API_EDEFAULT);
                return;
            case ContextPackage.LOCALISATION_PROVIDER_PROXY__TAGS:
                getTags().clear();
                return;
            case ContextPackage.LOCALISATION_PROVIDER_PROXY__CONFIGURATION:
                getConfiguration().clear();
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
            case ContextPackage.LOCALISATION_PROVIDER_PROXY__API_CLASS:
                return API_CLASS_EDEFAULT == null ? apiClass != null
                        : !API_CLASS_EDEFAULT.equals(apiClass);
            case ContextPackage.LOCALISATION_PROVIDER_PROXY__API:
                return API_EDEFAULT == null ? api != null : !API_EDEFAULT.equals(api);
            case ContextPackage.LOCALISATION_PROVIDER_PROXY__TAGS:
                return tags != null && !tags.isEmpty();
            case ContextPackage.LOCALISATION_PROVIDER_PROXY__CONFIGURATION:
                return configuration != null && !configuration.isEmpty();
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
        result.append(" (apiClass: ");
        result.append(apiClass);
        result.append(", api: ");
        result.append(api);
        result.append(')');
        return result.toString();
    }

} // LocalisationProviderProxyImpl
