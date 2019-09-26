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
 * <em><b>Assistant</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Assistant#getActivities <em>
 * Activities</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Assistant#getCurrentUsers <em>
 * Current Users</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Assistant#getPastUsers <em>Past
 * Users</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Assistant#getDescription <em>
 * Description</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Assistant#getConnections <em>
 * Connections</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Assistant#getDomain <em>Domain
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getAssistant()
 * @model abstract="true"
 * @generated
 */
public interface Assistant extends EnvironmentElement {

    /**
     * Returns the value of the '<em><b>Activities</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.sercho.masp.models.Context.Activity}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Activities</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Activities</em>' containment reference
     *         list.
     * @see org.sercho.masp.models.Context.ContextPackage#getAssistant_Activities()
     * @model containment="true"
     * @generated
     */
    EList<Activity> getActivities();

    /**
     * Returns the value of the '<em><b>Current Users</b></em>' reference list.
     * The list contents are of type {@link org.sercho.masp.models.Context.User}
     * . It is bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.User#getCurrentAssistants
     * <em>Current Assistants</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Current Users</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Current Users</em>' reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getAssistant_CurrentUsers()
     * @see org.sercho.masp.models.Context.User#getCurrentAssistants
     * @model opposite="currentAssistants"
     * @generated
     */
    EList<User> getCurrentUsers();

    /**
     * Returns the value of the '<em><b>Past Users</b></em>' reference list. The
     * list contents are of type {@link org.sercho.masp.models.Context.User}. It
     * is bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.User#getPastAssistants
     * <em>Past Assistants</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Past Users</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Past Users</em>' reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getAssistant_PastUsers()
     * @see org.sercho.masp.models.Context.User#getPastAssistants
     * @model opposite="pastAssistants"
     * @generated
     */
    EList<User> getPastUsers();

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
     * @see org.sercho.masp.models.Context.ContextPackage#getAssistant_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Assistant#getDescription
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
     * Returns the value of the '<em><b>Connections</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.sercho.masp.models.Context.AssistantConnection}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Connections</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Connections</em>' containment reference
     *         list.
     * @see org.sercho.masp.models.Context.ContextPackage#getAssistant_Connections()
     * @model containment="true"
     * @generated
     */
    EList<AssistantConnection> getConnections();

    /**
     * Returns the value of the '<em><b>Domain</b></em>' attribute. The default
     * value is <code>"Other"</code>. The literals are from the enumeration
     * {@link org.sercho.masp.models.Context.Domain}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Domain</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Domain</em>' attribute.
     * @see org.sercho.masp.models.Context.Domain
     * @see #setDomain(Domain)
     * @see org.sercho.masp.models.Context.ContextPackage#getAssistant_Domain()
     * @model default="Other"
     * @generated
     */
    Domain getDomain();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Assistant#getDomain
     * <em>Domain</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Domain</em>' attribute.
     * @see org.sercho.masp.models.Context.Domain
     * @see #getDomain()
     * @generated
     */
    void setDomain(Domain value);

} // Assistant
