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
 * <em><b>Window</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Window#getOpen <em>Open</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Window#getSource <em>Source</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Window#getTarget <em>Target</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Window#getSpan <em>Span</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getWindow()
 * @model
 * @generated
 */
public interface Window extends ElementWithPosition {

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
     * @see org.sercho.masp.models.Context.ContextPackage#getWindow_Open()
     * @model containment="true"
     * @generated
     */
    BooleanProperty getOpen();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Window#getOpen <em>Open</em>}'
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
     * {@link org.sercho.masp.models.Context.Place#getWindows <em>Windows</em>}
     * '. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Source</em>' container reference.
     * @see #setSource(Place)
     * @see org.sercho.masp.models.Context.ContextPackage#getWindow_Source()
     * @see org.sercho.masp.models.Context.Place#getWindows
     * @model opposite="windows" required="true" transient="false"
     * @generated
     */
    Place getSource();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Window#getSource <em>Source</em>}'
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
     * @see org.sercho.masp.models.Context.ContextPackage#getWindow_Target()
     * @model required="true"
     * @generated
     */
    Place getTarget();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Window#getTarget <em>Target</em>}'
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
     * @see org.sercho.masp.models.Context.ContextPackage#getWindow_Span()
     * @model containment="true" required="true"
     * @generated
     */
    Vector getSpan();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Window#getSpan <em>Span</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Span</em>' containment reference.
     * @see #getSpan()
     * @generated
     */
    void setSpan(Vector value);

} // Window
