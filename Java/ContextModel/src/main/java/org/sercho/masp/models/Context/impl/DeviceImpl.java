/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.InteractionResource;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Device</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.DeviceImpl#getResources <em>
 * Resources</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.DeviceImpl#isMobile <em>Mobile
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.DeviceImpl#getEnvironment <em>
 * Environment</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.DeviceImpl#getManufacturer
 * <em>Manufacturer</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.DeviceImpl#getModelName <em>
 * Model Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DeviceImpl extends ElementWithPositionImpl implements Device {

    /**
     * The cached value of the '{@link #getResources() <em>Resources</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getResources()
     * @generated
     * @ordered
     */
    protected EList<InteractionResource> resources;

    /**
     * The default value of the '{@link #isMobile() <em>Mobile</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isMobile()
     * @generated
     * @ordered
     */
    protected static final boolean MOBILE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isMobile() <em>Mobile</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isMobile()
     * @generated
     * @ordered
     */
    protected boolean mobile = MOBILE_EDEFAULT;

    /**
     * The default value of the '{@link #getManufacturer()
     * <em>Manufacturer</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getManufacturer()
     * @generated
     * @ordered
     */
    protected static final String MANUFACTURER_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getManufacturer() <em>Manufacturer</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getManufacturer()
     * @generated
     * @ordered
     */
    protected String manufacturer = MANUFACTURER_EDEFAULT;

    /**
     * The default value of the '{@link #getModelName() <em>Model Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getModelName()
     * @generated
     * @ordered
     */
    protected static final String MODEL_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getModelName() <em>Model Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getModelName()
     * @generated
     * @ordered
     */
    protected String modelName = MODEL_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DeviceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.DEVICE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<InteractionResource> getResources() {
        if(resources == null) {
            resources = new EObjectContainmentWithInverseEList<InteractionResource>(InteractionResource.class, this, ContextPackage.DEVICE__RESOURCES, ContextPackage.INTERACTION_RESOURCE__DEVICE);
        }
        return resources;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isMobile() {
        return mobile;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setMobile(boolean newMobile) {
        boolean oldMobile = mobile;
        mobile = newMobile;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.DEVICE__MOBILE, oldMobile, mobile));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Environment getEnvironment() {
        if(eContainerFeatureID() != ContextPackage.DEVICE__ENVIRONMENT)
            return null;
        return (Environment)eContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetEnvironment(Environment newEnvironment, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newEnvironment, ContextPackage.DEVICE__ENVIRONMENT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setEnvironment(Environment newEnvironment) {
        if(newEnvironment != eInternalContainer() || (eContainerFeatureID() != ContextPackage.DEVICE__ENVIRONMENT && newEnvironment != null)) {
            if(EcoreUtil.isAncestor(this, newEnvironment))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if(eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if(newEnvironment != null)
                msgs = ((InternalEObject)newEnvironment).eInverseAdd(this, ContextPackage.ENVIRONMENT__DEVICES, Environment.class, msgs);
            msgs = basicSetEnvironment(newEnvironment, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.DEVICE__ENVIRONMENT, newEnvironment, newEnvironment));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setManufacturer(String newManufacturer) {
        String oldManufacturer = manufacturer;
        manufacturer = newManufacturer;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.DEVICE__MANUFACTURER, oldManufacturer, manufacturer));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getModelName() {
        return modelName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setModelName(String newModelName) {
        String oldModelName = modelName;
        modelName = newModelName;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.DEVICE__MODEL_NAME, oldModelName, modelName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public void addInteractionResource(final InteractionResource interactionResource, final String id) {
        if(interactionResource == null) {
            throw new IllegalArgumentException("interactionResource argument must not be null in method addInteractionResource");
        }

        if(interactionResource.getId() == null && id == null) {
            throw new IllegalArgumentException("id argument must not be null in method addInteractionResource if id of interactionResource is null");
        }

        synchronized(this.getResources()) {
            this.resources.add(interactionResource);
        }

        // set new id only if interactionResource has no id
        if(interactionResource.getId() == null) {
            interactionResource.setId(id);
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public void removeInteractionResource(final InteractionResource interactionResource) {
        if(interactionResource == null) {
            throw new IllegalArgumentException("interactionResource argument must not be null in method removeInteractionResource");
        }

        synchronized(this.resources) {
            this.resources.remove(interactionResource);
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
            case ContextPackage.DEVICE__RESOURCES:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getResources()).basicAdd(otherEnd, msgs);
            case ContextPackage.DEVICE__ENVIRONMENT:
                if(eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetEnvironment((Environment)otherEnd, msgs);
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
            case ContextPackage.DEVICE__RESOURCES:
                return ((InternalEList<?>)getResources()).basicRemove(otherEnd, msgs);
            case ContextPackage.DEVICE__ENVIRONMENT:
                return basicSetEnvironment(null, msgs);
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
            case ContextPackage.DEVICE__ENVIRONMENT:
                return eInternalContainer().eInverseRemove(this, ContextPackage.ENVIRONMENT__DEVICES, Environment.class, msgs);
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
            case ContextPackage.DEVICE__RESOURCES:
                return getResources();
            case ContextPackage.DEVICE__MOBILE:
                return isMobile();
            case ContextPackage.DEVICE__ENVIRONMENT:
                return getEnvironment();
            case ContextPackage.DEVICE__MANUFACTURER:
                return getManufacturer();
            case ContextPackage.DEVICE__MODEL_NAME:
                return getModelName();
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
            case ContextPackage.DEVICE__RESOURCES:
                getResources().clear();
                getResources().addAll((Collection<? extends InteractionResource>)newValue);
                return;
            case ContextPackage.DEVICE__MOBILE:
                setMobile((Boolean)newValue);
                return;
            case ContextPackage.DEVICE__ENVIRONMENT:
                setEnvironment((Environment)newValue);
                return;
            case ContextPackage.DEVICE__MANUFACTURER:
                setManufacturer((String)newValue);
                return;
            case ContextPackage.DEVICE__MODEL_NAME:
                setModelName((String)newValue);
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
            case ContextPackage.DEVICE__RESOURCES:
                getResources().clear();
                return;
            case ContextPackage.DEVICE__MOBILE:
                setMobile(MOBILE_EDEFAULT);
                return;
            case ContextPackage.DEVICE__ENVIRONMENT:
                setEnvironment((Environment)null);
                return;
            case ContextPackage.DEVICE__MANUFACTURER:
                setManufacturer(MANUFACTURER_EDEFAULT);
                return;
            case ContextPackage.DEVICE__MODEL_NAME:
                setModelName(MODEL_NAME_EDEFAULT);
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
            case ContextPackage.DEVICE__RESOURCES:
                return resources != null && !resources.isEmpty();
            case ContextPackage.DEVICE__MOBILE:
                return mobile != MOBILE_EDEFAULT;
            case ContextPackage.DEVICE__ENVIRONMENT:
                return getEnvironment() != null;
            case ContextPackage.DEVICE__MANUFACTURER:
                return MANUFACTURER_EDEFAULT == null ? manufacturer != null
                        : !MANUFACTURER_EDEFAULT.equals(manufacturer);
            case ContextPackage.DEVICE__MODEL_NAME:
                return MODEL_NAME_EDEFAULT == null ? modelName != null
                        : !MODEL_NAME_EDEFAULT.equals(modelName);
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
        result.append(" (mobile: ");
        result.append(mobile);
        result.append(", manufacturer: ");
        result.append(manufacturer);
        result.append(", modelName: ");
        result.append(modelName);
        result.append(')');
        return result.toString();
    }
} // DeviceImpl
