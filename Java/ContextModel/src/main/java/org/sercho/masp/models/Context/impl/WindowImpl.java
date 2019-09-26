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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.Place;
import org.sercho.masp.models.Context.Vector;
import org.sercho.masp.models.Context.Window;

import de.dailab.masp.models.Properties.BooleanProperty;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Window</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.WindowImpl#getOpen <em>Open
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.WindowImpl#getSource <em>
 * Source</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.WindowImpl#getTarget <em>
 * Target</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.WindowImpl#getSpan <em>Span
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class WindowImpl extends ElementWithPositionImpl implements Window {

    /**
     * The cached value of the '{@link #getOpen() <em>Open</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOpen()
     * @generated
     * @ordered
     */
    protected BooleanProperty open;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected Place target;

    /**
     * The cached value of the '{@link #getSpan() <em>Span</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSpan()
     * @generated
     * @ordered
     */
    protected Vector span;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected WindowImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.WINDOW;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public BooleanProperty getOpen() {
        return open;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetOpen(BooleanProperty newOpen, NotificationChain msgs) {
        BooleanProperty oldOpen = open;
        open = newOpen;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.WINDOW__OPEN, oldOpen, newOpen);
            if(msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setOpen(BooleanProperty newOpen) {
        if(newOpen != open) {
            NotificationChain msgs = null;
            if(open != null)
                msgs = ((InternalEObject)open).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.WINDOW__OPEN, null, msgs);
            if(newOpen != null)
                msgs = ((InternalEObject)newOpen).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.WINDOW__OPEN, null, msgs);
            msgs = basicSetOpen(newOpen, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.WINDOW__OPEN, newOpen, newOpen));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Place getSource() {
        if(eContainerFeatureID() != ContextPackage.WINDOW__SOURCE)
            return null;
        return (Place)eContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetSource(Place newSource, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newSource, ContextPackage.WINDOW__SOURCE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setSource(Place newSource) {
        if(newSource != eInternalContainer() || (eContainerFeatureID() != ContextPackage.WINDOW__SOURCE && newSource != null)) {
            if(EcoreUtil.isAncestor(this, newSource))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if(eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if(newSource != null)
                msgs = ((InternalEObject)newSource).eInverseAdd(this, ContextPackage.PLACE__WINDOWS, Place.class, msgs);
            msgs = basicSetSource(newSource, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.WINDOW__SOURCE, newSource, newSource));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Place getTarget() {
        if(target != null && target.eIsProxy()) {
            InternalEObject oldTarget = (InternalEObject)target;
            target = (Place)eResolveProxy(oldTarget);
            if(target != oldTarget) {
                if(eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ContextPackage.WINDOW__TARGET, oldTarget, target));
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Place basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setTarget(Place newTarget) {
        Place oldTarget = target;
        target = newTarget;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.WINDOW__TARGET, oldTarget, target));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Vector getSpan() {
        return span;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetSpan(Vector newSpan, NotificationChain msgs) {
        Vector oldSpan = span;
        span = newSpan;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.WINDOW__SPAN, oldSpan, newSpan);
            if(msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setSpan(Vector newSpan) {
        if(newSpan != span) {
            NotificationChain msgs = null;
            if(span != null)
                msgs = ((InternalEObject)span).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.WINDOW__SPAN, null, msgs);
            if(newSpan != null)
                msgs = ((InternalEObject)newSpan).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.WINDOW__SPAN, null, msgs);
            msgs = basicSetSpan(newSpan, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.WINDOW__SPAN, newSpan, newSpan));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.WINDOW__SOURCE:
                if(eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetSource((Place)otherEnd, msgs);
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
            case ContextPackage.WINDOW__OPEN:
                return basicSetOpen(null, msgs);
            case ContextPackage.WINDOW__SOURCE:
                return basicSetSource(null, msgs);
            case ContextPackage.WINDOW__SPAN:
                return basicSetSpan(null, msgs);
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
            case ContextPackage.WINDOW__SOURCE:
                return eInternalContainer().eInverseRemove(this, ContextPackage.PLACE__WINDOWS, Place.class, msgs);
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
            case ContextPackage.WINDOW__OPEN:
                return getOpen();
            case ContextPackage.WINDOW__SOURCE:
                return getSource();
            case ContextPackage.WINDOW__TARGET:
                if(resolve)
                    return getTarget();
                return basicGetTarget();
            case ContextPackage.WINDOW__SPAN:
                return getSpan();
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
            case ContextPackage.WINDOW__OPEN:
                setOpen((BooleanProperty)newValue);
                return;
            case ContextPackage.WINDOW__SOURCE:
                setSource((Place)newValue);
                return;
            case ContextPackage.WINDOW__TARGET:
                setTarget((Place)newValue);
                return;
            case ContextPackage.WINDOW__SPAN:
                setSpan((Vector)newValue);
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
            case ContextPackage.WINDOW__OPEN:
                setOpen((BooleanProperty)null);
                return;
            case ContextPackage.WINDOW__SOURCE:
                setSource((Place)null);
                return;
            case ContextPackage.WINDOW__TARGET:
                setTarget((Place)null);
                return;
            case ContextPackage.WINDOW__SPAN:
                setSpan((Vector)null);
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
            case ContextPackage.WINDOW__OPEN:
                return open != null;
            case ContextPackage.WINDOW__SOURCE:
                return getSource() != null;
            case ContextPackage.WINDOW__TARGET:
                return target != null;
            case ContextPackage.WINDOW__SPAN:
                return span != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public Environment getEnvironment() {
        final Place sourcePlace = getSource();
        if(sourcePlace != null) {
            return sourcePlace.getEnvironment();
        }
        return null;
    }

} // WindowImpl
