/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Area</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Area#getOrigin <em>Origin</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Area#getSpan <em>Span</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getArea()
 * @model
 * @generated
 */
public interface Area extends EObject {

    /**
     * Returns the value of the '<em><b>Origin</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Origin</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Origin</em>' containment reference.
     * @see #setOrigin(Vector)
     * @see org.sercho.masp.models.Context.ContextPackage#getArea_Origin()
     * @model containment="true" required="true"
     * @generated
     */
    Vector getOrigin();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Area#getOrigin <em>Origin</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Origin</em>' containment reference.
     * @see #getOrigin()
     * @generated
     */
    void setOrigin(Vector value);

    /**
     * Returns the value of the '<em><b>Span</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Span</em>' reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Span</em>' containment reference.
     * @see #setSpan(Vector)
     * @see org.sercho.masp.models.Context.ContextPackage#getArea_Span()
     * @model containment="true" required="true"
     * @generated
     */
    Vector getSpan();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Area#getSpan <em>Span</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Span</em>' containment reference.
     * @see #getSpan()
     * @generated
     */
    void setSpan(Vector value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    boolean contains(Vector position);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model originXRequired="true" originYRequired="true"
     *        originZRequired="true" annotation="Definition modifies='origin'"
     * @generated
     */
    void setNewOrigin(double originX, double originY, double originZ);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model spanXRequired="true" spanYRequired="true" spanZRequired="true"
     *        annotation="Definition modifies='span'"
     * @generated
     */
    void setNewSpan(double spanX, double spanY, double spanZ);

} // Area
