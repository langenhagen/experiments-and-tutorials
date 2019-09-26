/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.ContextProvider;
import org.sercho.masp.models.Context.Environment;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Provider</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.ContextProviderImpl#getEnvironment
 * <em>Environment</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class ContextProviderImpl extends EObjectImpl implements ContextProvider {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ContextProviderImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.CONTEXT_PROVIDER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Environment getEnvironment() {
        if(eContainerFeatureID() != ContextPackage.CONTEXT_PROVIDER__ENVIRONMENT)
            return null;
        return (Environment)eContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.CONTEXT_PROVIDER__ENVIRONMENT:
                if(eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetEnvironment((Environment)otherEnd, msgs);
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
            case ContextPackage.CONTEXT_PROVIDER__ENVIRONMENT:
                return basicSetEnvironment(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch(eContainerFeatureID()) {
            case ContextPackage.CONTEXT_PROVIDER__ENVIRONMENT:
                return eInternalContainer().eInverseRemove(this, ContextPackage.ENVIRONMENT__PROVIDERS, Environment.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch(featureID) {
            case ContextPackage.CONTEXT_PROVIDER__ENVIRONMENT:
                return getEnvironment();
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
            case ContextPackage.CONTEXT_PROVIDER__ENVIRONMENT:
                setEnvironment((Environment)newValue);
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
            case ContextPackage.CONTEXT_PROVIDER__ENVIRONMENT:
                setEnvironment((Environment)null);
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
            case ContextPackage.CONTEXT_PROVIDER__ENVIRONMENT:
                return getEnvironment() != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetEnvironment(Environment newEnvironment, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newEnvironment, ContextPackage.CONTEXT_PROVIDER__ENVIRONMENT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setEnvironment(Environment newEnvironment) {
        if(newEnvironment != eInternalContainer() || (eContainerFeatureID() != ContextPackage.CONTEXT_PROVIDER__ENVIRONMENT && newEnvironment != null)) {
            if(EcoreUtil.isAncestor(this, newEnvironment))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if(eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if(newEnvironment != null)
                msgs = ((InternalEObject)newEnvironment).eInverseAdd(this, ContextPackage.ENVIRONMENT__PROVIDERS, Environment.class, msgs);
            msgs = basicSetEnvironment(newEnvironment, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.CONTEXT_PROVIDER__ENVIRONMENT, newEnvironment, newEnvironment));
    }

} // ContextProviderImpl
