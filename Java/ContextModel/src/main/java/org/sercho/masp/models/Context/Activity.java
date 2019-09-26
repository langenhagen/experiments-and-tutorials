/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Activity</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> Represents a short task that is (or must be)
 * executed by a user <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Activity#getName <em>Name</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Activity#getDescription <em>
 * Description</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Activity#getUsedWith <em>Used With
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Activity#getConfiguration <em>
 * Configuration</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getActivity()
 * @model
 * @generated
 */
public interface Activity extends EObject {

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.sercho.masp.models.Context.ContextPackage#getActivity_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Activity#getName <em>Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.sercho.masp.models.Context.ContextPackage#getActivity_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Activity#getDescription
     * <em>Description</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Used With</b></em>' reference list. The
     * list contents are of type {@link org.sercho.masp.models.Context.Tool}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Used With</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Used With</em>' reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getActivity_UsedWith()
     * @model
     * @generated
     */
    EList<Tool> getUsedWith();

    /**
     * Returns the value of the '<em><b>Configuration</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.sercho.masp.models.Context.ConfigurationProperty}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Configuration</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Configuration</em>' containment reference
     *         list.
     * @see org.sercho.masp.models.Context.ContextPackage#getActivity_Configuration()
     * @model containment="true"
     * @generated
     */
    EList<ConfigurationProperty> getConfiguration();

} // Activity
