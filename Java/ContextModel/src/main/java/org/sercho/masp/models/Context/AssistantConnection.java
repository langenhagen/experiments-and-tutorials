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
 * <em><b>Assistant Connection</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.AssistantConnection#getDescription
 * <em>Description</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.AssistantConnection#getConnectedAssistant
 * <em>Connected Assistant</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getAssistantConnection()
 * @model
 * @generated
 */
public interface AssistantConnection extends EObject {

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
     * @see org.sercho.masp.models.Context.ContextPackage#getAssistantConnection_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.AssistantConnection#getDescription
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
     * Returns the value of the '<em><b>Connected Assistant</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Connected Assistant</em>' reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Connected Assistant</em>' reference.
     * @see #setConnectedAssistant(Assistant)
     * @see org.sercho.masp.models.Context.ContextPackage#getAssistantConnection_ConnectedAssistant()
     * @model required="true"
     * @generated
     */
    Assistant getConnectedAssistant();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.AssistantConnection#getConnectedAssistant
     * <em>Connected Assistant</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Connected Assistant</em>' reference.
     * @see #getConnectedAssistant()
     * @generated
     */
    void setConnectedAssistant(Assistant value);

} // AssistantConnection
