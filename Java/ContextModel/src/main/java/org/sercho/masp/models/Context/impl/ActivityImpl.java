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
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sercho.masp.models.Context.Activity;
import org.sercho.masp.models.Context.ConfigurationProperty;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Tool;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Activity</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.ActivityImpl#getName <em>Name
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.ActivityImpl#getDescription
 * <em>Description</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.ActivityImpl#getUsedWith <em>
 * Used With</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.ActivityImpl#getConfiguration
 * <em>Configuration</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ActivityImpl extends EObjectImpl implements Activity {

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

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
     * The cached value of the '{@link #getUsedWith() <em>Used With</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getUsedWith()
     * @generated
     * @ordered
     */
    protected EList<Tool> usedWith;

    /**
     * The cached value of the '{@link #getConfiguration()
     * <em>Configuration</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getConfiguration()
     * @generated
     * @ordered
     */
    protected EList<ConfigurationProperty> configuration;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ActivityImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.ACTIVITY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.ACTIVITY__NAME, oldName, name));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.ACTIVITY__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Tool> getUsedWith() {
        if(usedWith == null) {
            usedWith = new EObjectResolvingEList<Tool>(Tool.class, this, ContextPackage.ACTIVITY__USED_WITH);
        }
        return usedWith;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<ConfigurationProperty> getConfiguration() {
        if(configuration == null) {
            configuration = new EObjectContainmentEList<ConfigurationProperty>(ConfigurationProperty.class, this, ContextPackage.ACTIVITY__CONFIGURATION);
        }
        return configuration;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.ACTIVITY__CONFIGURATION:
                return ((InternalEList<?>)getConfiguration()).basicRemove(otherEnd, msgs);
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
            case ContextPackage.ACTIVITY__NAME:
                return getName();
            case ContextPackage.ACTIVITY__DESCRIPTION:
                return getDescription();
            case ContextPackage.ACTIVITY__USED_WITH:
                return getUsedWith();
            case ContextPackage.ACTIVITY__CONFIGURATION:
                return getConfiguration();
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
            case ContextPackage.ACTIVITY__NAME:
                setName((String)newValue);
                return;
            case ContextPackage.ACTIVITY__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case ContextPackage.ACTIVITY__USED_WITH:
                getUsedWith().clear();
                getUsedWith().addAll((Collection<? extends Tool>)newValue);
                return;
            case ContextPackage.ACTIVITY__CONFIGURATION:
                getConfiguration().clear();
                getConfiguration().addAll((Collection<? extends ConfigurationProperty>)newValue);
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
            case ContextPackage.ACTIVITY__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ContextPackage.ACTIVITY__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case ContextPackage.ACTIVITY__USED_WITH:
                getUsedWith().clear();
                return;
            case ContextPackage.ACTIVITY__CONFIGURATION:
                getConfiguration().clear();
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
            case ContextPackage.ACTIVITY__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ContextPackage.ACTIVITY__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null
                        : !DESCRIPTION_EDEFAULT.equals(description);
            case ContextPackage.ACTIVITY__USED_WITH:
                return usedWith != null && !usedWith.isEmpty();
            case ContextPackage.ACTIVITY__CONFIGURATION:
                return configuration != null && !configuration.isEmpty();
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
        result.append(" (name: ");
        result.append(name);
        result.append(", description: ");
        result.append(description);
        result.append(')');
        return result.toString();
    }

} // ActivityImpl
