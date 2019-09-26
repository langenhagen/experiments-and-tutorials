/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import de.dailab.masp.models.Properties.IntegerProperty;
import de.dailab.masp.models.Properties.TextProperty;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Physical Device With Program</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.PhysicalDeviceWithProgram#getProgram
 * <em>Program</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.PhysicalDeviceWithProgram#getSecondsRemaining
 * <em>Seconds Remaining</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getPhysicalDeviceWithProgram()
 * @model abstract="true"
 * @generated
 */
public interface PhysicalDeviceWithProgram extends PhysicalDevice {

    /**
     * Returns the value of the '<em><b>Program</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Program</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Program</em>' containment reference.
     * @see #setProgram(TextProperty)
     * @see org.sercho.masp.models.Context.ContextPackage#getPhysicalDeviceWithProgram_Program()
     * @model containment="true"
     * @generated
     */
    TextProperty getProgram();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.PhysicalDeviceWithProgram#getProgram
     * <em>Program</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Program</em>' containment reference.
     * @see #getProgram()
     * @generated
     */
    void setProgram(TextProperty value);

    /**
     * Returns the value of the '<em><b>Seconds Remaining</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Seconds Remaining</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Seconds Remaining</em>' containment
     *         reference.
     * @see #setSecondsRemaining(IntegerProperty)
     * @see org.sercho.masp.models.Context.ContextPackage#getPhysicalDeviceWithProgram_SecondsRemaining()
     * @model containment="true"
     * @generated
     */
    IntegerProperty getSecondsRemaining();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.PhysicalDeviceWithProgram#getSecondsRemaining
     * <em>Seconds Remaining</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Seconds Remaining</em>' containment
     *            reference.
     * @see #getSecondsRemaining()
     * @generated
     */
    void setSecondsRemaining(IntegerProperty value);

} // PhysicalDeviceWithProgram
