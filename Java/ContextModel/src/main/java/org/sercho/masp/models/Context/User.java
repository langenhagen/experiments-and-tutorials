/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import java.util.Date;
import java.util.Map;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>User</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> User elements represent users in the environment.
 * Each user has a position, is in a place and has a set of properties, e.g.
 * birth date. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.User#getBirthDate <em>Birth Date
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.User#getSurname <em>Surname</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.User#isLeftHanded <em>Left Handed
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.User#getResources <em>Resources
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.User#getLineOfVision <em>Line Of
 * Vision</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.User#getIrExperience <em>Ir
 * Experience</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.User#isFollowMe <em>Follow Me</em>}
 * </li>
 * <li>{@link org.sercho.masp.models.Context.User#getEnvironment <em>Environment
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.User#getCurrentAssistants <em>
 * Current Assistants</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.User#getPastAssistants <em>Past
 * Assistants</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.User#getInstalledAssistants <em>
 * Installed Assistants</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.User#getPasswordHash <em>Password
 * Hash</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getUser()
 * @model
 * @generated
 */
public interface User extends ElementWithPosition {

    /**
     * Returns the value of the '<em><b>Resources</b></em>' reference list. The
     * list contents are of type
     * {@link org.sercho.masp.models.Context.InteractionResource}. It is
     * bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.InteractionResource#getUser
     * <em>User</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resources</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Resources</em>' reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getUser_Resources()
     * @see org.sercho.masp.models.Context.InteractionResource#getUser
     * @model opposite="user"
     * @generated
     */
    EList<InteractionResource> getResources();

    /**
     * Returns the value of the '<em><b>Line Of Vision</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Line Of Vision</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Line Of Vision</em>' containment reference.
     * @see #setLineOfVision(Vector)
     * @see org.sercho.masp.models.Context.ContextPackage#getUser_LineOfVision()
     * @model containment="true" required="true"
     * @generated
     */
    Vector getLineOfVision();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.User#getLineOfVision
     * <em>Line Of Vision</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Line Of Vision</em>' containment
     *            reference.
     * @see #getLineOfVision()
     * @generated
     */
    void setLineOfVision(Vector value);

    /**
     * Returns the value of the '<em><b>Ir Experience</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ir Experience</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Ir Experience</em>' attribute.
     * @see #setIrExperience(Map)
     * @see org.sercho.masp.models.Context.ContextPackage#getUser_IrExperience()
     * @model transient="true"
     * @generated
     */
    Map<Class<? extends InteractionResource>, String> getIrExperience();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.User#getIrExperience
     * <em>Ir Experience</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Ir Experience</em>' attribute.
     * @see #getIrExperience()
     * @generated
     */
    void setIrExperience(Map<Class<? extends InteractionResource>, String> value);

    /**
     * Returns the value of the '<em><b>Follow Me</b></em>' attribute. The
     * default value is <code>"false"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Follow Me</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Follow Me</em>' attribute.
     * @see #setFollowMe(boolean)
     * @see org.sercho.masp.models.Context.ContextPackage#getUser_FollowMe()
     * @model default="false" required="true"
     * @generated
     */
    boolean isFollowMe();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.User#isFollowMe <em>Follow Me</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Follow Me</em>' attribute.
     * @see #isFollowMe()
     * @generated
     */
    void setFollowMe(boolean value);

    /**
     * Returns the value of the '<em><b>Environment</b></em>' container
     * reference. It is bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.Environment#getUsers
     * <em>Users</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Environment</em>' container reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Environment</em>' container reference.
     * @see #setEnvironment(Environment)
     * @see org.sercho.masp.models.Context.ContextPackage#getUser_Environment()
     * @see org.sercho.masp.models.Context.Environment#getUsers
     * @model opposite="users" transient="false"
     * @generated
     */
    @Override
    Environment getEnvironment();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.User#getEnvironment
     * <em>Environment</em>}' container reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Environment</em>' container
     *            reference.
     * @see #getEnvironment()
     * @generated
     */
    void setEnvironment(Environment value);

    /**
     * Returns the value of the '<em><b>Current Assistants</b></em>' reference
     * list. The list contents are of type
     * {@link org.sercho.masp.models.Context.Assistant}. It is bidirectional and
     * its opposite is '
     * {@link org.sercho.masp.models.Context.Assistant#getCurrentUsers
     * <em>Current Users</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Current Assistants</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Current Assistants</em>' reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getUser_CurrentAssistants()
     * @see org.sercho.masp.models.Context.Assistant#getCurrentUsers
     * @model opposite="currentUsers"
     * @generated
     */
    EList<Assistant> getCurrentAssistants();

    /**
     * Returns the value of the '<em><b>Past Assistants</b></em>' reference
     * list. The list contents are of type
     * {@link org.sercho.masp.models.Context.Assistant}. It is bidirectional and
     * its opposite is '
     * {@link org.sercho.masp.models.Context.Assistant#getPastUsers
     * <em>Past Users</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Past Assistants</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Past Assistants</em>' reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getUser_PastAssistants()
     * @see org.sercho.masp.models.Context.Assistant#getPastUsers
     * @model opposite="pastUsers"
     * @generated
     */
    EList<Assistant> getPastAssistants();

    /**
     * Returns the value of the '<em><b>Installed Assistants</b></em>' reference
     * list. The list contents are of type
     * {@link org.sercho.masp.models.Context.Assistant}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Installed Assistants</em>' reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Installed Assistants</em>' reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getUser_InstalledAssistants()
     * @model
     * @generated
     */
    EList<Assistant> getInstalledAssistants();

    /**
     * Returns the value of the '<em><b>Password Hash</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Password Hash</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Password Hash</em>' attribute.
     * @see #setPasswordHash(String)
     * @see org.sercho.masp.models.Context.ContextPackage#getUser_PasswordHash()
     * @model
     * @generated
     */
    String getPasswordHash();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.User#getPasswordHash
     * <em>Password Hash</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Password Hash</em>' attribute.
     * @see #getPasswordHash()
     * @generated
     */
    void setPasswordHash(String value);

    /**
     * Returns the value of the '<em><b>Left Handed</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Left Handed</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Left Handed</em>' attribute.
     * @see #setLeftHanded(boolean)
     * @see org.sercho.masp.models.Context.ContextPackage#getUser_LeftHanded()
     * @model
     * @generated
     */
    boolean isLeftHanded();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.User#isLeftHanded
     * <em>Left Handed</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Left Handed</em>' attribute.
     * @see #isLeftHanded()
     * @generated
     */
    void setLeftHanded(boolean value);

    /**
     * Returns the value of the '<em><b>Birth Date</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Birth Date</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Birth Date</em>' attribute.
     * @see #setBirthDate(String)
     * @see org.sercho.masp.models.Context.ContextPackage#getUser_BirthDate()
     * @model
     * @generated
     */
    String getBirthDate();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.User#getBirthDate
     * <em>Birth Date</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Birth Date</em>' attribute.
     * @see #getBirthDate()
     * @generated
     */
    void setBirthDate(String value);

    /**
     * Returns the value of the '<em><b>Surname</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Surname</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Surname</em>' attribute.
     * @see #setSurname(String)
     * @see org.sercho.masp.models.Context.ContextPackage#getUser_Surname()
     * @model
     * @generated
     */
    String getSurname();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.User#getSurname <em>Surname</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Surname</em>' attribute.
     * @see #getSurname()
     * @generated
     */
    void setSurname(String value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model kind="operation"
     * @generated
     */
    EList<InteractionResource> getUsableResources();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model assistantRequired="true"
     *        annotation="Situation modifies='currentAssistants,pastAssistants'"
     * @generated
     */
    void addCurrentAssistant(Assistant assistant);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model assistantRequired="true"
     *        annotation="Situation modifies='currentAssistants,pastAssistants'"
     * @generated
     */
    void removeCurrentAssistant(Assistant assistant);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model newBirthDateRequired="true"
     *        annotation="Definition modifies='birthDate'"
     * @generated
     */
    void setNewBirthDate(String newBirthDate);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model kind="operation"
     * @generated
     */
    Date getBirthDateObject();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model kind="operation" required="true"
     * @generated
     */
    boolean isAdult();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model annotation="Definition modifies='leftHanded'"
     * @generated
     */
    void setLeftHanded();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model annotation="Definition modifies='leftHanded'"
     * @generated
     */
    void setRightHanded();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model assistantIdRequired="true"
     *        annotation="Situation modifies='installedAssistants'"
     * @generated
     */
    void installAssistant(String assistantId);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model assistantIdRequired="true"
     *        annotation="Situation modifies='installedAssistants'"
     * @generated
     */
    void uninstallAssistant(String assistantId);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model passwordHashRequired="true"
     *        annotation="Definition modifies='passwordHash'"
     * @generated
     */
    void setNewPasswordHash(String passwordHash);

} // User
