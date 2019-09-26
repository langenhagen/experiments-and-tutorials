/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Element With Position</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> ElementWithPosition represents environment entities
 * with a position and a place. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.ElementWithPosition#getTags <em>
 * Tags</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.ElementWithPosition#getPosition
 * <em>Position</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.ElementWithPosition#getPositionTimeStamp
 * <em>Position Time Stamp</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.ElementWithPosition#getPlace <em>
 * Place</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getElementWithPosition()
 * @model abstract="true"
 * @generated
 */
public interface ElementWithPosition extends EnvironmentElement {

    /**
     * Returns the value of the '<em><b>Tags</b></em>' reference list. The list
     * contents are of type
     * {@link org.sercho.masp.models.Context.LocalizationTag}. It is
     * bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.LocalizationTag#getElement
     * <em>Element</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tags</em>' reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Tags</em>' reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getElementWithPosition_Tags()
     * @see org.sercho.masp.models.Context.LocalizationTag#getElement
     * @model opposite="element"
     * @generated
     */
    EList<LocalizationTag> getTags();

    /**
     * Returns the value of the '<em><b>Position</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Position</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Position</em>' containment reference.
     * @see #setPosition(Vector)
     * @see org.sercho.masp.models.Context.ContextPackage#getElementWithPosition_Position()
     * @model containment="true"
     * @generated
     */
    Vector getPosition();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.ElementWithPosition#getPosition
     * <em>Position</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Position</em>' containment
     *            reference.
     * @see #getPosition()
     * @generated
     */
    void setPosition(Vector value);

    /**
     * Returns the value of the '<em><b>Position Time Stamp</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Position Time Stamp</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Position Time Stamp</em>' attribute.
     * @see #setPositionTimeStamp(long)
     * @see org.sercho.masp.models.Context.ContextPackage#getElementWithPosition_PositionTimeStamp()
     * @model
     * @generated
     */
    long getPositionTimeStamp();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.ElementWithPosition#getPositionTimeStamp
     * <em>Position Time Stamp</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Position Time Stamp</em>' attribute.
     * @see #getPositionTimeStamp()
     * @generated
     */
    void setPositionTimeStamp(long value);

    /**
     * Returns the value of the '<em><b>Place</b></em>' reference. It is
     * bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.Place#getElements
     * <em>Elements</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Place</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Place</em>' reference.
     * @see #setPlace(Place)
     * @see org.sercho.masp.models.Context.ContextPackage#getElementWithPosition_Place()
     * @see org.sercho.masp.models.Context.Place#getElements
     * @model opposite="elements"
     * @generated
     */
    Place getPlace();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.ElementWithPosition#getPlace
     * <em>Place</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Place</em>' reference.
     * @see #getPlace()
     * @generated
     */
    void setPlace(Place value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model xRequired="true" yRequired="true" zRequired="true"
     * @generated
     */
    void setPosition(double x, double y, double z);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model kind="operation"
     * @generated
     */
    Environment getEnvironment();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model tagRequired="true" annotation="Definition modifies='tags'"
     * @generated
     */
    void addTag(LocalizationTag tag);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model tagRequired="true" annotation="Definition modifies='tags'"
     * @generated
     */
    void removeTag(LocalizationTag tag);

} // ElementWithPosition
