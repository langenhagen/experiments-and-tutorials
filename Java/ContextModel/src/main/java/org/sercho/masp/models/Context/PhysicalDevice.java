/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import org.eclipse.emf.common.util.EList;

import de.dailab.masp.models.Properties.ActorServiceCallException;
import de.dailab.masp.models.Properties.BooleanProperty;
import de.dailab.masp.models.Properties.DoubleProperty;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Physical Device</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.PhysicalDevice#getOn <em>On</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.PhysicalDevice#getPowerUsage <em>
 * Power Usage</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.PhysicalDevice#getSubDevice <em>Sub
 * Device</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.PhysicalDevice#getParentDevice <em>
 * Parent Device</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getPhysicalDevice()
 * @model abstract="true"
 * @generated
 */
public interface PhysicalDevice extends Device {

    /**
     * Returns the value of the '<em><b>On</b></em>' containment reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>On</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>On</em>' containment reference.
     * @see #setOn(BooleanProperty)
     * @see org.sercho.masp.models.Context.ContextPackage#getPhysicalDevice_On()
     * @model containment="true"
     * @generated
     */
    BooleanProperty getOn();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.PhysicalDevice#getOn <em>On</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>On</em>' containment reference.
     * @see #getOn()
     * @generated
     */
    void setOn(BooleanProperty value);

    /**
     * Returns the value of the '<em><b>Power Usage</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Power Usage</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Power Usage</em>' containment reference.
     * @see #setPowerUsage(DoubleProperty)
     * @see org.sercho.masp.models.Context.ContextPackage#getPhysicalDevice_PowerUsage()
     * @model containment="true"
     * @generated
     */
    DoubleProperty getPowerUsage();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.PhysicalDevice#getPowerUsage
     * <em>Power Usage</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Power Usage</em>' containment
     *            reference.
     * @see #getPowerUsage()
     * @generated
     */
    void setPowerUsage(DoubleProperty value);

    /**
     * Returns the value of the '<em><b>Sub Device</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.sercho.masp.models.Context.PhysicalDevice}. It is
     * bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.PhysicalDevice#getParentDevice
     * <em>Parent Device</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sub Device</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Sub Device</em>' containment reference
     *         list.
     * @see org.sercho.masp.models.Context.ContextPackage#getPhysicalDevice_SubDevice()
     * @see org.sercho.masp.models.Context.PhysicalDevice#getParentDevice
     * @model opposite="parentDevice" containment="true"
     * @generated
     */
    EList<PhysicalDevice> getSubDevice();

    /**
     * Returns the value of the '<em><b>Parent Device</b></em>' container
     * reference. It is bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.PhysicalDevice#getSubDevice
     * <em>Sub Device</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent Device</em>' container reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Parent Device</em>' container reference.
     * @see #setParentDevice(PhysicalDevice)
     * @see org.sercho.masp.models.Context.ContextPackage#getPhysicalDevice_ParentDevice()
     * @see org.sercho.masp.models.Context.PhysicalDevice#getSubDevice
     * @model opposite="subDevice" transient="false"
     * @generated
     */
    PhysicalDevice getParentDevice();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.PhysicalDevice#getParentDevice
     * <em>Parent Device</em>}' container reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Parent Device</em>' container
     *            reference.
     * @see #getParentDevice()
     * @generated
     */
    void setParentDevice(PhysicalDevice value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Attempts to set the value 'true' to the 'on' property of this device. The
     * ActorServiceCallException is thrown if the 'on' property is not set, its
     * actor is not set or the actor throws an exception. <!-- end-model-doc -->
     * 
     * @model 
     *        exceptions="de.dailab.masp.models.Properties.ActorServiceCallException"
     *        annotation="Situation modifies='on'"
     * @generated
     */
    void turnOn() throws ActorServiceCallException;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Attempts to set the value 'false' to the 'on' property of this device.
     * The ActorServiceCallException is thrown if the 'on' property is not set,
     * its actor is not set or the actor throws an exception. <!-- end-model-doc
     * -->
     * 
     * @model 
     *        exceptions="de.dailab.masp.models.Properties.ActorServiceCallException"
     *        annotation="Situation modifies='on'"
     * @generated
     */
    void turnOff() throws ActorServiceCallException;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model kind="operation" required="true"
     * @generated
     */
    boolean getOnValue();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The id must be set only if the id of the subDevice is <code>null</code>.
     * <!-- end-model-doc -->
     * 
     * @model subDeviceRequired="true"
     *        annotation="Definition modifies='subDevice'"
     * @generated
     */
    void addSubDevice(PhysicalDevice subDevice, String id);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model subDeviceRequired="true"
     *        annotation="Definition modifies='subDevice'"
     * @generated
     */
    void removeSubDevice(PhysicalDevice subDevice);

} // PhysicalDevice
