/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import de.dailab.masp.models.Properties.BooleanProperty;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Door</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Door#getOpen <em>Open</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Door#getSource <em>Source</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Door#getTarget <em>Target</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Door#getSpan <em>Span</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getDoor()
 * @model
 * @generated
 */
public interface Door extends ElementWithPosition {

    /**
     * Returns the value of the '<em><b>Open</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Open</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Open</em>' containment reference.
     * @see #setOpen(BooleanProperty)
     * @see org.sercho.masp.models.Context.ContextPackage#getDoor_Open()
     * @model containment="true"
     * @generated
     */
    BooleanProperty getOpen();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Door#getOpen <em>Open</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Open</em>' containment reference.
     * @see #getOpen()
     * @generated
     */
    void setOpen(BooleanProperty value);

    /**
     * Returns the value of the '<em><b>Source</b></em>' container reference. It
     * is bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.Place#getDoors <em>Doors</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Source</em>' container reference.
     * @see #setSource(Place)
     * @see org.sercho.masp.models.Context.ContextPackage#getDoor_Source()
     * @see org.sercho.masp.models.Context.Place#getDoors
     * @model opposite="doors" required="true" transient="false"
     * @generated
     */
    Place getSource();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Door#getSource <em>Source</em>}'
     * container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Source</em>' container reference.
     * @see #getSource()
     * @generated
     */
    void setSource(Place value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(Place)
     * @see org.sercho.masp.models.Context.ContextPackage#getDoor_Target()
     * @model required="true"
     * @generated
     */
    Place getTarget();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Door#getTarget <em>Target</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(Place value);

    /**
     * Returns the value of the '<em><b>Span</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Span</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Span</em>' containment reference.
     * @see #setSpan(Vector)
     * @see org.sercho.masp.models.Context.ContextPackage#getDoor_Span()
     * @model containment="true" required="true"
     * @generated
     */
    Vector getSpan();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Door#getSpan <em>Span</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Span</em>' containment reference.
     * @see #getSpan()
     * @generated
     */
    void setSpan(Vector value);

} // Door
