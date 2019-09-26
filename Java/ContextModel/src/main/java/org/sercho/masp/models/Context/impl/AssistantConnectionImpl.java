/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.sercho.masp.models.Context.Assistant;
import org.sercho.masp.models.Context.AssistantConnection;
import org.sercho.masp.models.Context.ContextPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Assistant Connection</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.AssistantConnectionImpl#getDescription
 * <em>Description</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.AssistantConnectionImpl#getConnectedAssistant
 * <em>Connected Assistant</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AssistantConnectionImpl extends EObjectImpl implements AssistantConnection {

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
     * The cached value of the '{@link #getConnectedAssistant()
     * <em>Connected Assistant</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getConnectedAssistant()
     * @generated
     * @ordered
     */
    protected Assistant connectedAssistant;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected AssistantConnectionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.ASSISTANT_CONNECTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.ASSISTANT_CONNECTION__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Assistant getConnectedAssistant() {
        if(connectedAssistant != null && connectedAssistant.eIsProxy()) {
            InternalEObject oldConnectedAssistant = (InternalEObject)connectedAssistant;
            connectedAssistant = (Assistant)eResolveProxy(oldConnectedAssistant);
            if(connectedAssistant != oldConnectedAssistant) {
                if(eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ContextPackage.ASSISTANT_CONNECTION__CONNECTED_ASSISTANT, oldConnectedAssistant, connectedAssistant));
            }
        }
        return connectedAssistant;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Assistant basicGetConnectedAssistant() {
        return connectedAssistant;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setConnectedAssistant(Assistant newConnectedAssistant) {
        Assistant oldConnectedAssistant = connectedAssistant;
        connectedAssistant = newConnectedAssistant;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.ASSISTANT_CONNECTION__CONNECTED_ASSISTANT, oldConnectedAssistant, connectedAssistant));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch(featureID) {
            case ContextPackage.ASSISTANT_CONNECTION__DESCRIPTION:
                return getDescription();
            case ContextPackage.ASSISTANT_CONNECTION__CONNECTED_ASSISTANT:
                if(resolve)
                    return getConnectedAssistant();
                return basicGetConnectedAssistant();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch(featureID) {
            case ContextPackage.ASSISTANT_CONNECTION__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case ContextPackage.ASSISTANT_CONNECTION__CONNECTED_ASSISTANT:
                setConnectedAssistant((Assistant)newValue);
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
            case ContextPackage.ASSISTANT_CONNECTION__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case ContextPackage.ASSISTANT_CONNECTION__CONNECTED_ASSISTANT:
                setConnectedAssistant((Assistant)null);
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
            case ContextPackage.ASSISTANT_CONNECTION__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null
                        : !DESCRIPTION_EDEFAULT.equals(description);
            case ContextPackage.ASSISTANT_CONNECTION__CONNECTED_ASSISTANT:
                return connectedAssistant != null;
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
        result.append(')');
        return result.toString();
    }

} // AssistantConnectionImpl
