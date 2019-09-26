/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.impl;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Vector;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Vector</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.VectorImpl#getX <em>X</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.VectorImpl#getY <em>Y</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.VectorImpl#getZ <em>Z</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class VectorImpl extends EObjectImpl implements Vector {

    /**
     * The default value of the '{@link #getX() <em>X</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getX()
     * @generated
     * @ordered
     */
    protected static final double X_EDEFAULT = -1.0;

    /**
     * The cached value of the '{@link #getX() <em>X</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getX()
     * @ordered
     */
    protected double x = X_EDEFAULT;

    /**
     * The default value of the '{@link #getY() <em>Y</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getY()
     * @generated
     * @ordered
     */
    protected static final double Y_EDEFAULT = -1.0;

    /**
     * The cached value of the '{@link #getY() <em>Y</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getY()
     * @ordered
     */
    protected double y = Y_EDEFAULT;

    /**
     * The default value of the '{@link #getZ() <em>Z</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getZ()
     * @generated
     * @ordered
     */
    protected static final double Z_EDEFAULT = -1.0;

    /**
     * The cached value of the '{@link #getZ() <em>Z</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getZ()
     * @ordered
     */
    protected double z = Z_EDEFAULT;

    /**
     * <code>coordinatesLock</code> guards x, y and z.
     * 
     * @generated NOT
     */
    private final ReadWriteLock coordinatesLock = new ReentrantReadWriteLock();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected VectorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.VECTOR;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final double getX() {
        this.coordinatesLock.readLock().lock();
        try {
            return this.x;
        }
        finally {
            this.coordinatesLock.readLock().unlock();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setX(final double newX) {
        final double oldX;
        this.coordinatesLock.writeLock().lock();
        try {
            oldX = this.x;
            if(oldX == newX) {
                return;
            }
            this.x = newX;
        }
        finally {
            this.coordinatesLock.writeLock().unlock();
        }
        if(eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.VECTOR__X, oldX, this.x));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final double getY() {
        this.coordinatesLock.readLock().lock();
        try {
            return this.y;
        }
        finally {
            this.coordinatesLock.readLock().unlock();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setY(final double newY) {
        final double oldY;
        this.coordinatesLock.writeLock().lock();
        try {
            oldY = this.y;
            if(oldY == newY) {
                return;
            }
            this.y = newY;
        }
        finally {
            this.coordinatesLock.writeLock().unlock();
        }
        if(eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.VECTOR__Y, oldY, this.y));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final double getZ() {
        this.coordinatesLock.readLock().lock();
        try {
            return this.z;
        }
        finally {
            this.coordinatesLock.readLock().unlock();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setZ(final double newZ) {
        final double oldZ;
        this.coordinatesLock.writeLock().lock();
        try {
            oldZ = this.z;
            if(oldZ == newZ) {
                return;
            }
            this.z = newZ;
        }
        finally {
            this.coordinatesLock.writeLock().unlock();
        }
        if(eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.VECTOR__Z, oldZ, this.z));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public double distance(final Vector vector) {
        final double diffX = vector.getX() - this.x;
        final double diffY = vector.getY() - this.y;
        final double diffZ = vector.getZ() - this.z;
        return Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setCoordinates(final double newX, final double newY, final double newZ) {

        final double oldX;
        final double oldY;
        final double oldZ;
        boolean notifyX = false;
        boolean notifyY = false;
        boolean notifyZ = false;
        this.coordinatesLock.writeLock().lock();
        try {
            oldX = this.x;
            if(oldX != newX) {
                this.x = newX;
                notifyX = true;
            }

            oldY = this.y;
            if(oldY != newY) {
                this.y = newY;
                notifyY = true;
            }

            oldZ = this.z;
            if(oldZ != newZ) {
                this.z = newZ;
                notifyZ = true;
            }
        }
        finally {
            this.coordinatesLock.writeLock().unlock();
        }
        // send notifications outside of the lock

        if(eNotificationRequired()) {
            if(notifyX) {
                eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.VECTOR__X, oldX, this.x));
            }
            if(notifyY) {
                eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.VECTOR__Y, oldY, this.y));
            }
            if(notifyZ) {
                eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.VECTOR__Z, oldZ, this.z));
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public Vector copy() {
        // copy the vector
        final VectorImpl copy = new VectorImpl();
        // acquire read lock once for all coordinates
        this.coordinatesLock.readLock().lock();
        try {
            copy.x = this.x;
            copy.y = this.y;
            copy.z = this.z;
        }
        finally {
            this.coordinatesLock.readLock().unlock();
        }
        return copy;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch(featureID) {
            case ContextPackage.VECTOR__X:
                return getX();
            case ContextPackage.VECTOR__Y:
                return getY();
            case ContextPackage.VECTOR__Z:
                return getZ();
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
            case ContextPackage.VECTOR__X:
                setX((Double)newValue);
                return;
            case ContextPackage.VECTOR__Y:
                setY((Double)newValue);
                return;
            case ContextPackage.VECTOR__Z:
                setZ((Double)newValue);
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
            case ContextPackage.VECTOR__X:
                setX(X_EDEFAULT);
                return;
            case ContextPackage.VECTOR__Y:
                setY(Y_EDEFAULT);
                return;
            case ContextPackage.VECTOR__Z:
                setZ(Z_EDEFAULT);
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
            case ContextPackage.VECTOR__X:
                return x != X_EDEFAULT;
            case ContextPackage.VECTOR__Y:
                return y != Y_EDEFAULT;
            case ContextPackage.VECTOR__Z:
                return z != Z_EDEFAULT;
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
        result.append(" (x: ");
        result.append(x);
        result.append(", y: ");
        result.append(y);
        result.append(", z: ");
        result.append(z);
        result.append(')');
        return result.toString();
    }

} // VectorImpl
