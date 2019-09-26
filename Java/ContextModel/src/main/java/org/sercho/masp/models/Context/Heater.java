/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import de.dailab.masp.models.Properties.DoubleProperty;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Heater</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Heater#getTemperatureDemand <em>
 * Temperature Demand</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Heater#getTemperatureCurrent <em>
 * Temperature Current</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Heater#getValvePosition <em>Valve
 * Position</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getHeater()
 * @model
 * @generated
 */
public interface Heater extends PhysicalDevice {

    /**
     * Returns the value of the '<em><b>Temperature Demand</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Temperature Demand</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Temperature Demand</em>' containment
     *         reference.
     * @see #setTemperatureDemand(DoubleProperty)
     * @see org.sercho.masp.models.Context.ContextPackage#getHeater_TemperatureDemand()
     * @model containment="true"
     * @generated
     */
    DoubleProperty getTemperatureDemand();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Heater#getTemperatureDemand
     * <em>Temperature Demand</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Temperature Demand</em>' containment
     *            reference.
     * @see #getTemperatureDemand()
     * @generated
     */
    void setTemperatureDemand(DoubleProperty value);

    /**
     * Returns the value of the '<em><b>Temperature Current</b></em>'
     * containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Temperature Current</em>' containment
     * reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Temperature Current</em>' containment
     *         reference.
     * @see #setTemperatureCurrent(DoubleProperty)
     * @see org.sercho.masp.models.Context.ContextPackage#getHeater_TemperatureCurrent()
     * @model containment="true"
     * @generated
     */
    DoubleProperty getTemperatureCurrent();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Heater#getTemperatureCurrent
     * <em>Temperature Current</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Temperature Current</em>'
     *            containment reference.
     * @see #getTemperatureCurrent()
     * @generated
     */
    void setTemperatureCurrent(DoubleProperty value);

    /**
     * Returns the value of the '<em><b>Valve Position</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Valve Position</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Valve Position</em>' containment reference.
     * @see #setValvePosition(DoubleProperty)
     * @see org.sercho.masp.models.Context.ContextPackage#getHeater_ValvePosition()
     * @model containment="true"
     * @generated
     */
    DoubleProperty getValvePosition();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Heater#getValvePosition
     * <em>Valve Position</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Valve Position</em>' containment
     *            reference.
     * @see #getValvePosition()
     * @generated
     */
    void setValvePosition(DoubleProperty value);

} // Heater
