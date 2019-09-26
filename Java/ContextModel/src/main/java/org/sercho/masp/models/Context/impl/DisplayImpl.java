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
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Display;
import org.sercho.masp.models.Context.GraphicalOutputChannel;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.Modality;
import org.sercho.masp.models.Context.User;
import org.sercho.masp.models.Context.Vector;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Display</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.DisplayImpl#getXPixels <em>
 * XPixels</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.DisplayImpl#getYPixels <em>
 * YPixels</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.DisplayImpl#getDirection <em>
 * Direction</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.DisplayImpl#getScreen <em>
 * Screen</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.DisplayImpl#getSize <em>Size
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DisplayImpl extends OutputInteractionResourceImpl implements Display {

    /**
     * The default value of the '{@link #getXPixels() <em>XPixels</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getXPixels()
     * @generated
     * @ordered
     */
    protected static final int XPIXELS_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getXPixels() <em>XPixels</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getXPixels()
     * @generated
     * @ordered
     */
    protected int xPixels = XPIXELS_EDEFAULT;

    /**
     * The default value of the '{@link #getYPixels() <em>YPixels</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getYPixels()
     * @generated
     * @ordered
     */
    protected static final int YPIXELS_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getYPixels() <em>YPixels</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getYPixels()
     * @generated
     * @ordered
     */
    protected int yPixels = YPIXELS_EDEFAULT;

    /**
     * The cached value of the '{@link #getDirection() <em>Direction</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDirection()
     * @generated
     * @ordered
     */
    protected Vector direction;

    /**
     * The cached value of the '{@link #getScreen() <em>Screen</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getScreen()
     * @generated
     * @ordered
     */
    protected GraphicalOutputChannel screen;

    /**
     * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSize()
     * @generated
     * @ordered
     */
    protected static final int SIZE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSize()
     * @generated
     * @ordered
     */
    protected int size = SIZE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DisplayImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.DISPLAY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getXPixels() {
        return xPixels;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setXPixels(int newXPixels) {
        int oldXPixels = xPixels;
        xPixels = newXPixels;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.DISPLAY__XPIXELS, oldXPixels, xPixels));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getYPixels() {
        return yPixels;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setYPixels(int newYPixels) {
        int oldYPixels = yPixels;
        yPixels = newYPixels;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.DISPLAY__YPIXELS, oldYPixels, yPixels));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Vector getDirection() {
        return direction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetDirection(Vector newDirection, NotificationChain msgs) {
        Vector oldDirection = direction;
        direction = newDirection;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.DISPLAY__DIRECTION, oldDirection, newDirection);
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
    public void setDirection(Vector newDirection) {
        if(newDirection != direction) {
            NotificationChain msgs = null;
            if(direction != null)
                msgs = ((InternalEObject)direction).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.DISPLAY__DIRECTION, null, msgs);
            if(newDirection != null)
                msgs = ((InternalEObject)newDirection).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.DISPLAY__DIRECTION, null, msgs);
            msgs = basicSetDirection(newDirection, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.DISPLAY__DIRECTION, newDirection, newDirection));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public GraphicalOutputChannel getScreen() {
        return screen;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetScreen(GraphicalOutputChannel newScreen, NotificationChain msgs) {
        GraphicalOutputChannel oldScreen = screen;
        screen = newScreen;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.DISPLAY__SCREEN, oldScreen, newScreen);
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
    public void setScreen(GraphicalOutputChannel newScreen) {
        if(newScreen != screen) {
            NotificationChain msgs = null;
            if(screen != null)
                msgs = ((InternalEObject)screen).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.DISPLAY__SCREEN, null, msgs);
            if(newScreen != null)
                msgs = ((InternalEObject)newScreen).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.DISPLAY__SCREEN, null, msgs);
            msgs = basicSetScreen(newScreen, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.DISPLAY__SCREEN, newScreen, newScreen));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setSize(int newSize) {
        int oldSize = size;
        size = newSize;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.DISPLAY__SIZE, oldSize, size));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.DISPLAY__DIRECTION:
                return basicSetDirection(null, msgs);
            case ContextPackage.DISPLAY__SCREEN:
                return basicSetScreen(null, msgs);
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
            case ContextPackage.DISPLAY__XPIXELS:
                return getXPixels();
            case ContextPackage.DISPLAY__YPIXELS:
                return getYPixels();
            case ContextPackage.DISPLAY__DIRECTION:
                return getDirection();
            case ContextPackage.DISPLAY__SCREEN:
                return getScreen();
            case ContextPackage.DISPLAY__SIZE:
                return getSize();
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
            case ContextPackage.DISPLAY__XPIXELS:
                setXPixels((Integer)newValue);
                return;
            case ContextPackage.DISPLAY__YPIXELS:
                setYPixels((Integer)newValue);
                return;
            case ContextPackage.DISPLAY__DIRECTION:
                setDirection((Vector)newValue);
                return;
            case ContextPackage.DISPLAY__SCREEN:
                setScreen((GraphicalOutputChannel)newValue);
                return;
            case ContextPackage.DISPLAY__SIZE:
                setSize((Integer)newValue);
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
            case ContextPackage.DISPLAY__XPIXELS:
                setXPixels(XPIXELS_EDEFAULT);
                return;
            case ContextPackage.DISPLAY__YPIXELS:
                setYPixels(YPIXELS_EDEFAULT);
                return;
            case ContextPackage.DISPLAY__DIRECTION:
                setDirection((Vector)null);
                return;
            case ContextPackage.DISPLAY__SCREEN:
                setScreen((GraphicalOutputChannel)null);
                return;
            case ContextPackage.DISPLAY__SIZE:
                setSize(SIZE_EDEFAULT);
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
            case ContextPackage.DISPLAY__XPIXELS:
                return xPixels != XPIXELS_EDEFAULT;
            case ContextPackage.DISPLAY__YPIXELS:
                return yPixels != YPIXELS_EDEFAULT;
            case ContextPackage.DISPLAY__DIRECTION:
                return direction != null;
            case ContextPackage.DISPLAY__SCREEN:
                return screen != null;
            case ContextPackage.DISPLAY__SIZE:
                return size != SIZE_EDEFAULT;
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
        result.append(" (xPixels: ");
        result.append(xPixels);
        result.append(", yPixels: ");
        result.append(yPixels);
        result.append(", size: ");
        result.append(size);
        result.append(')');
        return result.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Modality getModalityHook() {
        return Modality.GRAPHIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<? extends InteractionResource> getTypeHook() {
        return Display.class;
    }

    private static final double USAGE_DISTANCE = 100;

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    protected double getContextRatingHook(final User user) {
        // System.out.println("DisplayImpl.getContextRatingHook(): TODO determine rating based on field of vision of user!");
        final double distance = this.getPosition().distance(user.getPosition());
        return distance;
        // Vector lineOfVision = user.getLineOfVision();
        // if(lineOfVision != null && this.direction != null) {
        // // TODO determine rating based on field of vision of user
        //
        // } else {
        // System.out.println("DisplayImpl.getContextRatingHook(): lineOfVision
        // of user or direction of display is null (lineOfVision=" +
        // lineOfVision + "; direction=" + this.direction);
        // return MINIMUM_CONTEXT_RATING;
        // }
    }

} // DisplayImpl
