/**
 * 
 */
package org.sercho.masp.models.Context.util;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.PhysicalDevice;

import de.dailab.masp.models.Properties.Actor;
import de.dailab.masp.models.Properties.ActorServiceCallException;
import de.dailab.masp.models.Properties.BooleanProperty;
import de.dailab.masp.models.Properties.PropertiesPackage;
import de.dailab.masp.models.Properties.Property;

/**
 * <code>AppliancesUtility</code> provides some convenient methods for working
 * with {@link Appliance}s.
 * 
 * @author Grzegorz Lehmann
 * @since 3.0
 * @generated NOT
 */
public final class AppliancesUtility {

    private static final double ZERO_DOUBLE = 0;

    private static final int ZERO_INT = 0;

    private static final String BOOLEAN_FALSE = "false";

    private static final String BOOLEAN_TRUE = "true";

    /**
     * <code>getDoubleValueOrZeroIfNull</code> returns the value of a property.
     * 
     * @param property
     *            property to retrieve the value of, <code>null</code> allowed
     * @return double - value of <code>property</code> or <code>0.0</code> if
     *         <code>property</code> or its value are <code>null</code>
     */
    public static double getDoubleValueOrZeroIfNull(final Property<Double> property) {
        final Double value = getValue(property);
        return value == null ? ZERO_DOUBLE : value.doubleValue();
    }

    /**
     * <code>getIntegerValueOrZeroIfNull</code> returns the value of a property.
     * 
     * @param property
     *            property to retrieve the value of, <code>null</code> allowed
     * @return int - value of <code>property</code> or <code>0</code> if
     *         <code>property</code> or its value are <code>null</code>
     */
    public static int getIntegerValueOrZeroIfNull(final Property<Integer> property) {
        final Integer value = getValue(property);
        return value == null ? ZERO_INT : value.intValue();
    }

    /**
     * <code>getValueOrZeroIfNull</code> returns the value of a property.
     * 
     * @param property
     *            property to retrieve the value of, <code>null</code> allowed
     * @return double - value of <code>property</code> or <code>0.0</code> if
     *         <code>property</code> or its value are <code>null</code>
     * @deprecated Replaced by {@link #getDoubleValueOrZeroIfNull(Property)}
     */
    /*
     * @Deprecated public static double getValueOrZeroIfNull(final
     * Property<Double> property) { final Double value = getValue(property);
     * return value == null ? ZERO_DOUBLE : value.doubleValue(); }
     */

    /**
     * <code>getValueOrZeroIfNull</code> returns the value of a property.
     * 
     * @param property
     *            property to retrieve the value of, <code>null</code> allowed
     * @return int - value of <code>property</code> or <code>0</code> if
     *         <code>property</code> or its value are <code>null</code>
     * @deprecated Replaced by {@link #getIntegerValueOrZeroIfNull(Property)}
     */
    @Deprecated
    public static int getValueOrZeroIfNull(final Property<Integer> property) {
        final Integer value = getValue(property);
        return value == null ? ZERO_INT : value.intValue();
    }

    /**
     * <code>getValueOrFalseIfNull</code> returns the value of a boolean
     * property.
     * 
     * @param property
     *            property to retrieve the value of, <code>null</code> allowed
     * @return boolean - value of <code>property</code> or <code>false</code> if
     *         <code>property</code> or its value are <code>null</code>
     */
    public static boolean getValueOrFalseIfNull(final Property<Boolean> property) {
        final Boolean value = getValue(property);
        return value == null ? false : value.booleanValue();
    }

    /**
     * <code>getValue</code> returns the value of a {@link Property}.
     * 
     * @param <E>
     *            type of property's value
     * @param property
     *            property to retrieve the value of, <code>null</code> allowed
     * @return E - value of <code>property</code> or <code>null</code> if
     *         <code>property</code> is <code>null</code>
     */
    public static <E> E getValue(final Property<E> property) {
        return property == null ? null : property.getValue();
    }

    /**
     * <code>set</code> sets a new value to a {@link Property}.
     * 
     * @param property
     *            property to set the new value to, <code>null</code> disallowed
     * @param newValue
     *            new value to set
     * @throws ActorServiceCallException
     *             if the actor of the property encounters an error
     * @throws IllegalArgumentException
     *             if <code>property</code> is <code>null</code>
     */
    public static void set(final Property<?> property, final String newValue) throws ActorServiceCallException {
        if(property == null) {
            throw new ActorServiceCallException("No property set");
        }
        final Actor a = property.getActor();
        if(a == null) {
            throw new ActorServiceCallException("No actor set in " + property);
        }
        a.set(newValue);
        // if actor call worked, set property value
        // TODO this is not always desirable, sensors should detect the new
        // value
        // property.setValueAndStoreInHistory(newValue);
    }

    /**
     * <code>setFalse</code> sets a <code>false</code> value to a
     * {@link Property}.
     * 
     * @param property
     *            property to set the new value to, <code>null</code> disallowed
     * @throws ActorServiceCallException
     *             if the actor of the property encounters an error
     * @throws IllegalArgumentException
     *             if <code>property</code> is <code>null</code>
     */
    public static void setFalse(final Property<Boolean> property) throws ActorServiceCallException {
        set(property, BOOLEAN_FALSE);
    }

    /**
     * <code>setTrue</code> sets a <code>true</code> value to a {@link Property}
     * .
     * 
     * @param property
     *            property to set the new value to, <code>null</code> disallowed
     * @throws ActorServiceCallException
     *             if the actor of the property encounters an error
     * @throws IllegalArgumentException
     *             if <code>property</code> is <code>null</code>
     */
    public static void setTrue(final Property<Boolean> property) throws ActorServiceCallException {
        set(property, BOOLEAN_TRUE);
    }

    /**
     * <code>togglePowerStatus</code> toggles the power status of an
     * {@link PhysicalDevice} (and its Subdevices). If the Device is on it will
     * be switched off. If it is off it will be switched on.
     * 
     * @param device
     *            PhysicalDevice of which the power status should be toggled
     * @throws ActorServiceCallException
     *             if anything goes wrong
     * @throws IllegalArgumentException
     *             if <code>appliance</code> is <code>null</code>
     */
    public static void togglePowerStatus(final PhysicalDevice device) throws ActorServiceCallException {
        if(device == null) {
            throw new IllegalArgumentException("device argument must not be null in method togglePowerStatus");
        }

        final BooleanProperty on = device.getOn();
        if(on == null || on.getValue() == null || !on.getValue().booleanValue()) {
            setPowerStatus(device, Boolean.FALSE.toString());
        } else {
            setPowerStatus(device, Boolean.TRUE.toString());
        }
    }

    /**
     * <code>setPowerStatus</code> sets a new power status for a
     * {@link Physical Device}. This methods sets the power status to all
     * sub-devices of the appliance if their power status property has an actor.
     * 
     * @param appliance
     *            appliance to set a new power status to
     * @param newStatus
     *            new power status to set
     * @throws ActorServiceCallException
     *             if anything goes wrong
     */
    private static void setPowerStatus(final PhysicalDevice device, final String newStatus) throws ActorServiceCallException {
        Property<Boolean> powerStatus;
        Actor actor;
        if(device.getSubDevice().size() >= 0) {
            for(final PhysicalDevice subdevice : device.getSubDevice()) {
                setPowerStatus(subdevice, newStatus);
            }
        }

        powerStatus = device.getOn();
        if(powerStatus != null) {
            actor = powerStatus.getActor();
            if(actor != null) {
                actor.set(newStatus);
            }
        }
    }

    /**
     * <code>getPropertyEReferences</code> returns all references to
     * {@link Property} elements defined in the {@link EClass} of a given
     * <code>eObject</code>.
     * 
     * @param eObject
     *            eObject with references to {@link Property} elements
     * @return Set&lt;EReference&gt; - all references to {@link Property}
     *         elements defined in the {@link EClass} of <code>eObject</code>
     */
    public static Set<EReference> getPropertyEReferences(final EObject eObject) {
        if(eObject == null) {
            throw new IllegalArgumentException("eObject argument must not be null in method getPropertyEReferences");
        }
        final Set<EReference> eReferences = new HashSet<EReference>();
        for(final EReference eReference : eObject.eClass().getEAllReferences()) {
            if(PropertiesPackage.Literals.PROPERTY.isSuperTypeOf(eReference.getEReferenceType())) {
                eReferences.add(eReference);
            }
        }
        return eReferences;
    }

    /**
     * <code>getDevice</code> searches a {@link Environment} for a
     * {@link Device} with a given name.
     * 
     * @param environment
     *            {@link Environment} to search for the device
     * @param deviceName
     *            name of the device to find ({@link Device#getName()})
     * @return Device - {@link Device} with the specified <code>name</code> name
     *         or <code>null</code> if no such {@link Device} exists
     * @throws IllegalArgumentException
     *             if any argument is <code>null</code>
     */
    public static Device getDevice(final Environment environment, final String deviceName) {
        if(environment == null) {
            throw new IllegalArgumentException("environment argument must not be null in method getDevice");
        }
        if(deviceName == null) {
            throw new IllegalArgumentException("applianceName argument must not be null in method getDevice");
        }
        return getDevice(environment.getDevices(), deviceName);
    }

    /**
     * <code>getDevice</code> searches for a {@link Device} with a given name.
     * 
     * @param devices
     *            a collection of devices to search
     * @param name
     *            name of the device to find ({@link Device#getName()})
     * @return Device - {@link Device} with the specified <code>name</code> name
     *         or <code>null</code> if no such {@link Device} exists
     */
    private static Device getDevice(final Iterable<Device> devices, final String name) {
        synchronized(devices) {
            Device subDevice;
            for(final Device device : devices) {
                if(name.equals(device.getName())) {
                    return device;
                }
                subDevice = getDevice(EcoreUtil.<Device> getObjectsByType(device.eContents(), ContextPackage.Literals.DEVICE), name);
                if(subDevice != null) {
                    return subDevice;
                }
            }
        }
        return null;
    }

    /**
     * <code>AppliancesUtility</code> hidden constructor.
     */
    private AppliancesUtility() {
        // hiding constructor of utility class
    }
}