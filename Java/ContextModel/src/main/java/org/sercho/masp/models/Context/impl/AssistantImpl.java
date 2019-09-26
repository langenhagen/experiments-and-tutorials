/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sercho.masp.models.Context.Activity;
import org.sercho.masp.models.Context.Assistant;
import org.sercho.masp.models.Context.AssistantConnection;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Domain;
import org.sercho.masp.models.Context.User;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Assistant</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.AssistantImpl#getActivities
 * <em>Activities</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.AssistantImpl#getCurrentUsers
 * <em>Current Users</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.AssistantImpl#getPastUsers
 * <em>Past Users</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.AssistantImpl#getDescription
 * <em>Description</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.AssistantImpl#getConnections
 * <em>Connections</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.AssistantImpl#getDomain <em>
 * Domain</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class AssistantImpl extends EnvironmentElementImpl implements Assistant {

    /**
     * The cached value of the '{@link #getActivities() <em>Activities</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getActivities()
     * @generated
     * @ordered
     */
    protected EList<Activity> activities;

    /**
     * The cached value of the '{@link #getCurrentUsers()
     * <em>Current Users</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getCurrentUsers()
     * @generated
     * @ordered
     */
    protected EList<User> currentUsers;

    /**
     * The cached value of the '{@link #getPastUsers() <em>Past Users</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPastUsers()
     * @generated
     * @ordered
     */
    protected EList<User> pastUsers;

    /**
     * The default value of the '{@link #getDescription() <em>Description</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
     * The cached value of the '{@link #getConnections() <em>Connections</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getConnections()
     * @generated
     * @ordered
     */
    protected EList<AssistantConnection> connections;

    /**
     * The default value of the '{@link #getDomain() <em>Domain</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDomain()
     * @generated
     * @ordered
     */
    protected static final Domain DOMAIN_EDEFAULT = Domain.OTHER;

    /**
     * The cached value of the '{@link #getDomain() <em>Domain</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDomain()
     * @generated
     * @ordered
     */
    protected Domain domain = DOMAIN_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected AssistantImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.ASSISTANT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Activity> getActivities() {
        if(activities == null) {
            activities = new EObjectContainmentEList<Activity>(Activity.class, this, ContextPackage.ASSISTANT__ACTIVITIES);
        }
        return activities;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<User> getCurrentUsers() {
        if(currentUsers == null) {
            currentUsers = new EObjectWithInverseResolvingEList.ManyInverse<User>(User.class, this, ContextPackage.ASSISTANT__CURRENT_USERS, ContextPackage.USER__CURRENT_ASSISTANTS);
        }
        return currentUsers;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<User> getPastUsers() {
        if(pastUsers == null) {
            pastUsers = new EObjectWithInverseResolvingEList.ManyInverse<User>(User.class, this, ContextPackage.ASSISTANT__PAST_USERS, ContextPackage.USER__PAST_ASSISTANTS);
        }
        return pastUsers;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.ASSISTANT__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<AssistantConnection> getConnections() {
        if(connections == null) {
            connections = new EObjectContainmentEList<AssistantConnection>(AssistantConnection.class, this, ContextPackage.ASSISTANT__CONNECTIONS);
        }
        return connections;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Domain getDomain() {
        return domain;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDomain(Domain newDomain) {
        Domain oldDomain = domain;
        domain = newDomain == null ? DOMAIN_EDEFAULT : newDomain;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.ASSISTANT__DOMAIN, oldDomain, domain));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.ASSISTANT__CURRENT_USERS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getCurrentUsers()).basicAdd(otherEnd, msgs);
            case ContextPackage.ASSISTANT__PAST_USERS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getPastUsers()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.ASSISTANT__ACTIVITIES:
                return ((InternalEList<?>)getActivities()).basicRemove(otherEnd, msgs);
            case ContextPackage.ASSISTANT__CURRENT_USERS:
                return ((InternalEList<?>)getCurrentUsers()).basicRemove(otherEnd, msgs);
            case ContextPackage.ASSISTANT__PAST_USERS:
                return ((InternalEList<?>)getPastUsers()).basicRemove(otherEnd, msgs);
            case ContextPackage.ASSISTANT__CONNECTIONS:
                return ((InternalEList<?>)getConnections()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch(featureID) {
            case ContextPackage.ASSISTANT__ACTIVITIES:
                return getActivities();
            case ContextPackage.ASSISTANT__CURRENT_USERS:
                return getCurrentUsers();
            case ContextPackage.ASSISTANT__PAST_USERS:
                return getPastUsers();
            case ContextPackage.ASSISTANT__DESCRIPTION:
                return getDescription();
            case ContextPackage.ASSISTANT__CONNECTIONS:
                return getConnections();
            case ContextPackage.ASSISTANT__DOMAIN:
                return getDomain();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch(featureID) {
            case ContextPackage.ASSISTANT__ACTIVITIES:
                getActivities().clear();
                getActivities().addAll((Collection<? extends Activity>)newValue);
                return;
            case ContextPackage.ASSISTANT__CURRENT_USERS:
                getCurrentUsers().clear();
                getCurrentUsers().addAll((Collection<? extends User>)newValue);
                return;
            case ContextPackage.ASSISTANT__PAST_USERS:
                getPastUsers().clear();
                getPastUsers().addAll((Collection<? extends User>)newValue);
                return;
            case ContextPackage.ASSISTANT__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case ContextPackage.ASSISTANT__CONNECTIONS:
                getConnections().clear();
                getConnections().addAll((Collection<? extends AssistantConnection>)newValue);
                return;
            case ContextPackage.ASSISTANT__DOMAIN:
                setDomain((Domain)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch(featureID) {
            case ContextPackage.ASSISTANT__ACTIVITIES:
                getActivities().clear();
                return;
            case ContextPackage.ASSISTANT__CURRENT_USERS:
                getCurrentUsers().clear();
                return;
            case ContextPackage.ASSISTANT__PAST_USERS:
                getPastUsers().clear();
                return;
            case ContextPackage.ASSISTANT__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case ContextPackage.ASSISTANT__CONNECTIONS:
                getConnections().clear();
                return;
            case ContextPackage.ASSISTANT__DOMAIN:
                setDomain(DOMAIN_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch(featureID) {
            case ContextPackage.ASSISTANT__ACTIVITIES:
                return activities != null && !activities.isEmpty();
            case ContextPackage.ASSISTANT__CURRENT_USERS:
                return currentUsers != null && !currentUsers.isEmpty();
            case ContextPackage.ASSISTANT__PAST_USERS:
                return pastUsers != null && !pastUsers.isEmpty();
            case ContextPackage.ASSISTANT__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null
                        : !DESCRIPTION_EDEFAULT.equals(description);
            case ContextPackage.ASSISTANT__CONNECTIONS:
                return connections != null && !connections.isEmpty();
            case ContextPackage.ASSISTANT__DOMAIN:
                return domain != DOMAIN_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if(eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (description: ");
        result.append(description);
        result.append(", domain: ");
        result.append(domain);
        result.append(')');
        return result.toString();
    }

} // AssistantImpl
