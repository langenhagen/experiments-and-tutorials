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
 * <em><b>Vector</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> 3D vector <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Vector#getX <em>X</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Vector#getY <em>Y</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Vector#getZ <em>Z</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getVector()
 * @model
 * @generated
 */
public interface Vector extends EObject {

    /**
     * Returns the value of the '<em><b>X</b></em>' attribute. The default value
     * is <code>"-1.0"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>X</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> X coordinate <!--
     * end-model-doc -->
     * 
     * @return the value of the '<em>X</em>' attribute.
     * @see #setX(double)
     * @see org.sercho.masp.models.Context.ContextPackage#getVector_X()
     * @model default="-1.0"
     * @generated
     */
    double getX();

    /**
     * Sets the value of the '{@link org.sercho.masp.models.Context.Vector#getX
     * <em>X</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>X</em>' attribute.
     * @see #getX()
     * @generated
     */
    void setX(double value);

    /**
     * Returns the value of the '<em><b>Y</b></em>' attribute. The default value
     * is <code>"-1.0"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Y</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> Y coordinate <!--
     * end-model-doc -->
     * 
     * @return the value of the '<em>Y</em>' attribute.
     * @see #setY(double)
     * @see org.sercho.masp.models.Context.ContextPackage#getVector_Y()
     * @model default="-1.0"
     * @generated
     */
    double getY();

    /**
     * Sets the value of the '{@link org.sercho.masp.models.Context.Vector#getY
     * <em>Y</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Y</em>' attribute.
     * @see #getY()
     * @generated
     */
    void setY(double value);

    /**
     * Returns the value of the '<em><b>Z</b></em>' attribute. The default value
     * is <code>"-1.0"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Z</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> Z coordinate <!--
     * end-model-doc -->
     * 
     * @return the value of the '<em>Z</em>' attribute.
     * @see #setZ(double)
     * @see org.sercho.masp.models.Context.ContextPackage#getVector_Z()
     * @model default="-1.0"
     * @generated
     */
    double getZ();

    /**
     * Sets the value of the '{@link org.sercho.masp.models.Context.Vector#getZ
     * <em>Z</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Z</em>' attribute.
     * @see #getZ()
     * @generated
     */
    void setZ(double value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * calculates the distance to a point described by another vector <!--
     * end-model-doc -->
     * 
     * @model
     * @generated
     */
    double distance(Vector vector);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * updates the cooridnates of this vector <!-- end-model-doc -->
     * 
     * @model newXRequired="true" newYRequired="true" newZRequired="true"
     *        annotation="Definition modifies='x,y,z'"
     * @generated
     */
    void setCoordinates(double newX, double newY, double newZ);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * copies this vector <!-- end-model-doc -->
     * 
     * @model required="true"
     * @generated
     */
    Vector copy();

} // Vector
