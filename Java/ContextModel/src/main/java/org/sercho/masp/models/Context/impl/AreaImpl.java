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
import org.sercho.masp.models.Context.Area;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Vector;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Area</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.AreaImpl#getOrigin <em>Origin
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.AreaImpl#getSpan <em>Span
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AreaImpl extends EObjectImpl implements Area {

    /**
     * The cached value of the '{@link #getOrigin() <em>Origin</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOrigin()
     * @generated
     * @ordered
     */
    protected Vector origin;

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
    protected AreaImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.AREA;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Vector getOrigin() {
        return origin;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetOrigin(Vector newOrigin, NotificationChain msgs) {
        Vector oldOrigin = origin;
        origin = newOrigin;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.AREA__ORIGIN, oldOrigin, newOrigin);
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
    public void setOrigin(Vector newOrigin) {
        if(newOrigin != origin) {
            NotificationChain msgs = null;
            if(origin != null)
                msgs = ((InternalEObject)origin).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.AREA__ORIGIN, null, msgs);
            if(newOrigin != null)
                msgs = ((InternalEObject)newOrigin).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.AREA__ORIGIN, null, msgs);
            msgs = basicSetOrigin(newOrigin, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.AREA__ORIGIN, newOrigin, newOrigin));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.AREA__SPAN, oldSpan, newSpan);
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
                msgs = ((InternalEObject)span).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.AREA__SPAN, null, msgs);
            if(newSpan != null)
                msgs = ((InternalEObject)newSpan).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.AREA__SPAN, null, msgs);
            msgs = basicSetSpan(newSpan, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.AREA__SPAN, newSpan, newSpan));
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final boolean contains(final Vector vector) {
        if(this.span.getX() > 0) {
            if(this.origin.getX() <= vector.getX() && vector.getX() <= this.origin.getX() + this.span.getX()) {
                // all OK
            } else {
                return false;
            }
        } else {
            if(this.origin.getX() + this.span.getX() <= vector.getX() && vector.getX() <= this.origin.getX()) {
                // all OK
            } else {
                return false;
            }
        }

        if(this.span.getY() > 0) {
            if(this.origin.getY() <= vector.getY() && vector.getY() <= this.origin.getY() + this.span.getY()) {
                // all OK
            } else {
                return false;
            }
        } else {
            if(this.origin.getY() + this.span.getY() <= vector.getY() && vector.getY() <= this.origin.getY()) {
                // all OK
            } else {
                return false;
            }
        }

        if(this.span.getZ() > 0) {
            if(this.origin.getZ() <= vector.getZ() && vector.getZ() <= this.origin.getZ() + this.span.getZ()) {
                // all OK
            } else {
                return false;
            }
        } else {
            if(this.origin.getZ() + this.span.getZ() <= vector.getZ() && vector.getZ() <= this.origin.getZ()) {
                // all OK
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setNewOrigin(final double originX, final double originY, final double originZ) {
        Vector newOrigin = getOrigin();
        if(newOrigin == null) {
            newOrigin = ContextFactory.eINSTANCE.createVector();
            setOrigin(newOrigin);
        }
        newOrigin.setCoordinates(originX, originY, originZ);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setNewSpan(final double spanX, final double spanY, final double spanZ) {
        Vector newSpan = getSpan();
        if(newSpan == null) {
            newSpan = ContextFactory.eINSTANCE.createVector();
            setSpan(newSpan);
        }
        newSpan.setCoordinates(spanX, spanY, spanZ);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.AREA__ORIGIN:
                return basicSetOrigin(null, msgs);
            case ContextPackage.AREA__SPAN:
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
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch(featureID) {
            case ContextPackage.AREA__ORIGIN:
                return getOrigin();
            case ContextPackage.AREA__SPAN:
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
            case ContextPackage.AREA__ORIGIN:
                setOrigin((Vector)newValue);
                return;
            case ContextPackage.AREA__SPAN:
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
            case ContextPackage.AREA__ORIGIN:
                setOrigin((Vector)null);
                return;
            case ContextPackage.AREA__SPAN:
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
            case ContextPackage.AREA__ORIGIN:
                return origin != null;
            case ContextPackage.AREA__SPAN:
                return span != null;
        }
        return super.eIsSet(featureID);
    }

} // AreaImpl
