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
import org.sercho.masp.models.Context.PhysicalDevice;

import de.dailab.masp.models.Properties.ActorServiceCallException;
import de.dailab.masp.models.Properties.BooleanProperty;
import de.dailab.masp.models.Properties.DoubleProperty;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Physical Device</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.PhysicalDeviceImpl#getOn <em>
 * On</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.PhysicalDeviceImpl#getPowerUsage
 * <em>Power Usage</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.PhysicalDeviceImpl#getSubDevice
 * <em>Sub Device</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.PhysicalDeviceImpl#getParentDevice
 * <em>Parent Device</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class PhysicalDeviceImpl extends DeviceImpl implements PhysicalDevice {

    /**
     * The cached value of the '{@link #getOn() <em>On</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOn()
     * @generated
     * @ordered
     */
    protected BooleanProperty on;

    /**
     * The cached value of the '{@link #getPowerUsage() <em>Power Usage</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPowerUsage()
     * @generated
     * @ordered
     */
    protected DoubleProperty powerUsage;

    /**
     * The cached value of the '{@link #getSubDevice() <em>Sub Device</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSubDevice()
     * @generated
     * @ordered
     */
    protected EList<PhysicalDevice> subDevice;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected PhysicalDeviceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.PHYSICAL_DEVICE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public BooleanProperty getOn() {
        return on;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetOn(BooleanProperty newOn, NotificationChain msgs) {
        BooleanProperty oldOn = on;
        on = newOn;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.PHYSICAL_DEVICE__ON, oldOn, newOn);
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
    public void setOn(BooleanProperty newOn) {
        if(newOn != on) {
            NotificationChain msgs = null;
            if(on != null)
                msgs = ((InternalEObject)on).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.PHYSICAL_DEVICE__ON, null, msgs);
            if(newOn != null)
                msgs = ((InternalEObject)newOn).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.PHYSICAL_DEVICE__ON, null, msgs);
            msgs = basicSetOn(newOn, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.PHYSICAL_DEVICE__ON, newOn, newOn));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DoubleProperty getPowerUsage() {
        return powerUsage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetPowerUsage(DoubleProperty newPowerUsage, NotificationChain msgs) {
        DoubleProperty oldPowerUsage = powerUsage;
        powerUsage = newPowerUsage;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.PHYSICAL_DEVICE__POWER_USAGE, oldPowerUsage, newPowerUsage);
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
    public void setPowerUsage(DoubleProperty newPowerUsage) {
        if(newPowerUsage != powerUsage) {
            NotificationChain msgs = null;
            if(powerUsage != null)
                msgs = ((InternalEObject)powerUsage).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.PHYSICAL_DEVICE__POWER_USAGE, null, msgs);
            if(newPowerUsage != null)
                msgs = ((InternalEObject)newPowerUsage).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.PHYSICAL_DEVICE__POWER_USAGE, null, msgs);
            msgs = basicSetPowerUsage(newPowerUsage, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.PHYSICAL_DEVICE__POWER_USAGE, newPowerUsage, newPowerUsage));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<PhysicalDevice> getSubDevice() {
        if(subDevice == null) {
            subDevice = new EObjectContainmentWithInverseEList<PhysicalDevice>(PhysicalDevice.class, this, ContextPackage.PHYSICAL_DEVICE__SUB_DEVICE, ContextPackage.PHYSICAL_DEVICE__PARENT_DEVICE);
        }
        return subDevice;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public PhysicalDevice getParentDevice() {
        if(eContainerFeatureID() != ContextPackage.PHYSICAL_DEVICE__PARENT_DEVICE)
            return null;
        return (PhysicalDevice)eContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetParentDevice(PhysicalDevice newParentDevice, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newParentDevice, ContextPackage.PHYSICAL_DEVICE__PARENT_DEVICE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setParentDevice(PhysicalDevice newParentDevice) {
        if(newParentDevice != eInternalContainer() || (eContainerFeatureID() != ContextPackage.PHYSICAL_DEVICE__PARENT_DEVICE && newParentDevice != null)) {
            if(EcoreUtil.isAncestor(this, newParentDevice))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if(eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if(newParentDevice != null)
                msgs = ((InternalEObject)newParentDevice).eInverseAdd(this, ContextPackage.PHYSICAL_DEVICE__SUB_DEVICE, PhysicalDevice.class, msgs);
            msgs = basicSetParentDevice(newParentDevice, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.PHYSICAL_DEVICE__PARENT_DEVICE, newParentDevice, newParentDevice));
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void turnOn() throws ActorServiceCallException {
        setOnValue(true);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void turnOff() throws ActorServiceCallException {
        setOnValue(false);
    }

    /**
     * <code>setOnValue</code> sets a new value to the <code>on</code> property.
     * 
     * @param value
     *            new value to set in the <code>on</code> property
     * @throws ActorServiceCallException
     *             if anything goes wrong (the property is not set, has no actor
     *             or the actor throws an exception)
     * @generated NOT
     */
    private final void setOnValue(final boolean value) throws ActorServiceCallException {
        final BooleanProperty onProperty = getOn();
        if(onProperty == null) {
            throw new ActorServiceCallException("'on' property not set");
        }
        final de.dailab.masp.models.Properties.Actor actor = onProperty.getActor();
        if(actor == null) {
            throw new ActorServiceCallException("No actor set in 'on' property");
        }
        actor.set(Boolean.toString(value));
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final boolean getOnValue() {
        final BooleanProperty onProperty = getOn();
        if(onProperty == null) {
            return false;
        }
        final Boolean onValue = onProperty.getValue();
        return onValue != null && onValue.booleanValue();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public void addSubDevice(final PhysicalDevice subDevice, final String id) {
        if(subDevice == null) {
            throw new IllegalArgumentException("subDevice argument must not be null in method addSubDevice");
        }

        if(subDevice.getId() == null && id == null) {
            throw new IllegalArgumentException("id argument must not be null in method addSubDevice if id of device is null");
        }

        synchronized(this.getSubDevice()) {
            this.subDevice.add(subDevice);
        }

        // set new id only if subDevice has no id
        if(subDevice.getId() == null) {
            subDevice.setId(id);
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public void removeSubDevice(final PhysicalDevice subDevice) {
        if(subDevice == null) {
            throw new IllegalArgumentException("subDevice argument must not be null in method removeSubDevice");
        }

        synchronized(this.resources) {
            this.subDevice.remove(subDevice);
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
            case ContextPackage.PHYSICAL_DEVICE__SUB_DEVICE:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getSubDevice()).basicAdd(otherEnd, msgs);
            case ContextPackage.PHYSICAL_DEVICE__PARENT_DEVICE:
                if(eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetParentDevice((PhysicalDevice)otherEnd, msgs);
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
            case ContextPackage.PHYSICAL_DEVICE__ON:
                return basicSetOn(null, msgs);
            case ContextPackage.PHYSICAL_DEVICE__POWER_USAGE:
                return basicSetPowerUsage(null, msgs);
            case ContextPackage.PHYSICAL_DEVICE__SUB_DEVICE:
                return ((InternalEList<?>)getSubDevice()).basicRemove(otherEnd, msgs);
            case ContextPackage.PHYSICAL_DEVICE__PARENT_DEVICE:
                return basicSetParentDevice(null, msgs);
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
            case ContextPackage.PHYSICAL_DEVICE__PARENT_DEVICE:
                return eInternalContainer().eInverseRemove(this, ContextPackage.PHYSICAL_DEVICE__SUB_DEVICE, PhysicalDevice.class, msgs);
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
            case ContextPackage.PHYSICAL_DEVICE__ON:
                return getOn();
            case ContextPackage.PHYSICAL_DEVICE__POWER_USAGE:
                return getPowerUsage();
            case ContextPackage.PHYSICAL_DEVICE__SUB_DEVICE:
                return getSubDevice();
            case ContextPackage.PHYSICAL_DEVICE__PARENT_DEVICE:
                return getParentDevice();
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
            case ContextPackage.PHYSICAL_DEVICE__ON:
                setOn((BooleanProperty)newValue);
                return;
            case ContextPackage.PHYSICAL_DEVICE__POWER_USAGE:
                setPowerUsage((DoubleProperty)newValue);
                return;
            case ContextPackage.PHYSICAL_DEVICE__SUB_DEVICE:
                getSubDevice().clear();
                getSubDevice().addAll((Collection<? extends PhysicalDevice>)newValue);
                return;
            case ContextPackage.PHYSICAL_DEVICE__PARENT_DEVICE:
                setParentDevice((PhysicalDevice)newValue);
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
            case ContextPackage.PHYSICAL_DEVICE__ON:
                setOn((BooleanProperty)null);
                return;
            case ContextPackage.PHYSICAL_DEVICE__POWER_USAGE:
                setPowerUsage((DoubleProperty)null);
                return;
            case ContextPackage.PHYSICAL_DEVICE__SUB_DEVICE:
                getSubDevice().clear();
                return;
            case ContextPackage.PHYSICAL_DEVICE__PARENT_DEVICE:
                setParentDevice((PhysicalDevice)null);
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
            case ContextPackage.PHYSICAL_DEVICE__ON:
                return on != null;
            case ContextPackage.PHYSICAL_DEVICE__POWER_USAGE:
                return powerUsage != null;
            case ContextPackage.PHYSICAL_DEVICE__SUB_DEVICE:
                return subDevice != null && !subDevice.isEmpty();
            case ContextPackage.PHYSICAL_DEVICE__PARENT_DEVICE:
                return getParentDevice() != null;
        }
        return super.eIsSet(featureID);
    }

} // PhysicalDeviceImpl
