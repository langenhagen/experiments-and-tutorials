/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import de.dailab.masp.models.Properties.IntegerProperty;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Lamp</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Lamp#getDimmingLevel <em>Dimming
 * Level</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Lamp#getLampType <em>Lamp Type
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getLamp()
 * @model
 * @generated
 */
public interface Lamp extends PhysicalDevice {

    /**
     * Returns the value of the '<em><b>Dimming Level</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Dimming Level</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Dimming Level</em>' containment reference.
     * @see #setDimmingLevel(IntegerProperty)
     * @see org.sercho.masp.models.Context.ContextPackage#getLamp_DimmingLevel()
     * @model containment="true"
     * @generated
     */
    IntegerProperty getDimmingLevel();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Lamp#getDimmingLevel
     * <em>Dimming Level</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Dimming Level</em>' containment
     *            reference.
     * @see #getDimmingLevel()
     * @generated
     */
    void setDimmingLevel(IntegerProperty value);

    /**
     * Returns the value of the '<em><b>Lamp Type</b></em>' attribute. The
     * literals are from the enumeration
     * {@link org.sercho.masp.models.Context.LampType}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Lamp Type</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Lamp Type</em>' attribute.
     * @see org.sercho.masp.models.Context.LampType
     * @see #setLampType(LampType)
     * @see org.sercho.masp.models.Context.ContextPackage#getLamp_LampType()
     * @model
     * @generated
     */
    LampType getLampType();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Lamp#getLampType
     * <em>Lamp Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Lamp Type</em>' attribute.
     * @see org.sercho.masp.models.Context.LampType
     * @see #getLampType()
     * @generated
     */
    void setLampType(LampType value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model lampTypeRequired="true"
     *        annotation="Definition modifies='lampType'"
     * @generated
     */
    void setNewLampeType(LampType lampType);

} // Lamp
