/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sercho.masp.models.TwoDimensionalUtility;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.PointingInputChannel;
import org.sercho.masp.models.UI.Pointing;
import org.sercho.masp.models.UI.TwoDimensional;
import org.sercho.masp.models.UI.UIModel;
import org.sercho.masp.models.UI.UIPackage;
import org.sercho.masp.models.channel.api.PointingInputChannelAPI;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Pointing Input Channel</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.PointingInputChannelImpl#getWidth
 * <em>Width</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.PointingInputChannelImpl#getHeight
 * <em>Height</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.PointingInputChannelImpl#getX
 * <em>X</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.PointingInputChannelImpl#getY
 * <em>Y</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.PointingInputChannelImpl#getZ
 * <em>Z</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PointingInputChannelImpl extends
        ChannelImpl<Pointing, PointingInputChannelAPI> implements PointingInputChannel {

    /**
     * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected static final int WIDTH_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected int width = WIDTH_EDEFAULT;

    /**
     * The default value of the '{@link #getHeight() <em>Height</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected static final int HEIGHT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected int height = HEIGHT_EDEFAULT;

    /**
     * The default value of the '{@link #getX() <em>X</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getX()
     * @generated
     * @ordered
     */
    protected static final int X_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getX() <em>X</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getX()
     * @generated
     * @ordered
     */
    protected int x = X_EDEFAULT;

    /**
     * The default value of the '{@link #getY() <em>Y</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getY()
     * @generated
     * @ordered
     */
    protected static final int Y_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getY() <em>Y</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getY()
     * @generated
     * @ordered
     */
    protected int y = Y_EDEFAULT;

    /**
     * The default value of the '{@link #getZ() <em>Z</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getZ()
     * @generated
     * @ordered
     */
    protected static final int Z_EDEFAULT = 1;

    /**
     * The cached value of the '{@link #getZ() <em>Z</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getZ()
     * @generated
     * @ordered
     */
    protected int z = Z_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected PointingInputChannelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.POINTING_INPUT_CHANNEL;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final int getX() {
        return this.x;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final int getY() {
        return this.y;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public void setPosition(final int newX, final int newY) {
        setX(newX);
        setY(newY);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    public void setPosition(final int newX, final int newY, final int newZ) {
        setX(newX);
        setY(newY);
        setZ(newZ);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public void setSize(final int newWidth, final int newHeight) {
        // new width
        setWidth(newWidth);
        // new height
        setHeight(newHeight);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final int getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final int getHeight() {
        return this.height;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void newPointing(final int localX, final int localY) {
        if(localX < 0 || localY < 0) {
            // no pointing
            return;
        }
        if(localX >= this.width || localY >= this.height) {
            System.err.println(toString() + ": Wrong pointing, X: " + localX + ", Y: " + localY);
            return;
        }
        final EList<Pointing> pointings = getElements();
        if(pointings.isEmpty()) {
            return;
        }
        Pointing p = null;
        final int globalX = this.x + localX;
        final int globalY = this.y + localY;
        for(int i = 0; i < pointings.size(); i++) {
            p = pointings.get(i);
            if(p.newPointing(globalX, globalY)) {
                // no need to proceed
                return;
            }
        }
        // set the global pointing, because no Pointing did
        ((UIModel)EcoreUtil.getRootContainer(p)).setPointing(globalX, globalY);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setApi(final PointingInputChannelAPI channelAPI) {
        this.api = channelAPI;
        if(channelAPI != null) {
            channelAPI.setCallback(this);
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch(featureID) {
            case ContextPackage.POINTING_INPUT_CHANNEL__WIDTH:
                return getWidth();
            case ContextPackage.POINTING_INPUT_CHANNEL__HEIGHT:
                return getHeight();
            case ContextPackage.POINTING_INPUT_CHANNEL__X:
                return getX();
            case ContextPackage.POINTING_INPUT_CHANNEL__Y:
                return getY();
            case ContextPackage.POINTING_INPUT_CHANNEL__Z:
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
            case ContextPackage.POINTING_INPUT_CHANNEL__WIDTH:
                setWidth((Integer)newValue);
                return;
            case ContextPackage.POINTING_INPUT_CHANNEL__HEIGHT:
                setHeight((Integer)newValue);
                return;
            case ContextPackage.POINTING_INPUT_CHANNEL__X:
                setX((Integer)newValue);
                return;
            case ContextPackage.POINTING_INPUT_CHANNEL__Y:
                setY((Integer)newValue);
                return;
            case ContextPackage.POINTING_INPUT_CHANNEL__Z:
                setZ((Integer)newValue);
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
            case ContextPackage.POINTING_INPUT_CHANNEL__WIDTH:
                setWidth(WIDTH_EDEFAULT);
                return;
            case ContextPackage.POINTING_INPUT_CHANNEL__HEIGHT:
                setHeight(HEIGHT_EDEFAULT);
                return;
            case ContextPackage.POINTING_INPUT_CHANNEL__X:
                setX(X_EDEFAULT);
                return;
            case ContextPackage.POINTING_INPUT_CHANNEL__Y:
                setY(Y_EDEFAULT);
                return;
            case ContextPackage.POINTING_INPUT_CHANNEL__Z:
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
            case ContextPackage.POINTING_INPUT_CHANNEL__WIDTH:
                return width != WIDTH_EDEFAULT;
            case ContextPackage.POINTING_INPUT_CHANNEL__HEIGHT:
                return height != HEIGHT_EDEFAULT;
            case ContextPackage.POINTING_INPUT_CHANNEL__X:
                return x != X_EDEFAULT;
            case ContextPackage.POINTING_INPUT_CHANNEL__Y:
                return y != Y_EDEFAULT;
            case ContextPackage.POINTING_INPUT_CHANNEL__Z:
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
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if(baseClass == TwoDimensional.class) {
            switch(derivedFeatureID) {
                case ContextPackage.POINTING_INPUT_CHANNEL__WIDTH:
                    return UIPackage.TWO_DIMENSIONAL__WIDTH;
                case ContextPackage.POINTING_INPUT_CHANNEL__HEIGHT:
                    return UIPackage.TWO_DIMENSIONAL__HEIGHT;
                case ContextPackage.POINTING_INPUT_CHANNEL__X:
                    return UIPackage.TWO_DIMENSIONAL__X;
                case ContextPackage.POINTING_INPUT_CHANNEL__Y:
                    return UIPackage.TWO_DIMENSIONAL__Y;
                case ContextPackage.POINTING_INPUT_CHANNEL__Z:
                    return UIPackage.TWO_DIMENSIONAL__Z;
                default:
                    return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if(baseClass == TwoDimensional.class) {
            switch(baseFeatureID) {
                case UIPackage.TWO_DIMENSIONAL__WIDTH:
                    return ContextPackage.POINTING_INPUT_CHANNEL__WIDTH;
                case UIPackage.TWO_DIMENSIONAL__HEIGHT:
                    return ContextPackage.POINTING_INPUT_CHANNEL__HEIGHT;
                case UIPackage.TWO_DIMENSIONAL__X:
                    return ContextPackage.POINTING_INPUT_CHANNEL__X;
                case UIPackage.TWO_DIMENSIONAL__Y:
                    return ContextPackage.POINTING_INPUT_CHANNEL__Y;
                case UIPackage.TWO_DIMENSIONAL__Z:
                    return ContextPackage.POINTING_INPUT_CHANNEL__Z;
                default:
                    return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    protected final void addHook(@SuppressWarnings("unused") final Pointing element) {
        // making final, because there is nothing to do in PointingInputChannels
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    protected final void removeHook(@SuppressWarnings("unused") final Pointing element) {
        // making final, because there is nothing to do in PointingInputChannels
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
        result.append(" (width: ");
        result.append(width);
        result.append(", height: ");
        result.append(height);
        result.append(", x: ");
        result.append(x);
        result.append(", y: ");
        result.append(y);
        result.append(", z: ");
        result.append(z);
        result.append(')');
        return result.toString();
    }

    /**
     * <code>getAPI</code> returns the api object of this channel (
     * {@link #getApi()}) already casted.
     * 
     * @return GraphicalOutputChannelAPI - API of this channel
     * @generated NOT
     */
    private PointingInputChannelAPI getAPI() {
        return this.api;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public void newPosition(final int relativeX, final int relativeY) {
        setPosition(relativeX, relativeY);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public void newSize(final int newWidth, final int newHeight) {
        setSize(newWidth, newHeight);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setHeight(final int newHeight) {
        // new height
        if(newHeight != this.height) {
            final int old = this.height;
            this.height = newHeight;
            if(eNotificationRequired()) {
                eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.POINTING_INPUT_CHANNEL__HEIGHT, old, newHeight));
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setWidth(final int newWidth) {
        // new width
        if(newWidth != this.width) {
            final int old = this.width;
            this.width = newWidth;
            if(eNotificationRequired()) {
                eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.POINTING_INPUT_CHANNEL__WIDTH, old, newWidth));
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setX(final int value) {
        // X coordinate
        if(value != this.x) {
            final int old = this.x;
            this.x = value;
            if(eNotificationRequired()) {
                eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.POINTING_INPUT_CHANNEL__X, old, value));
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setY(final int value) {
        // Y coordinate
        if(value != this.y) {
            final int old = this.y;
            this.y = value;
            if(eNotificationRequired()) {
                eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.POINTING_INPUT_CHANNEL__Y, old, value));
            }
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getZ() {
        return z;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setZ(int newZ) {
        int oldZ = z;
        z = newZ;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.POINTING_INPUT_CHANNEL__Z, oldZ, z));
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final boolean contains(final int xCoordinate, final int yCoordinate) {
        return TwoDimensionalUtility.contains(this, xCoordinate, yCoordinate);
    }
} // PointingInputChannelImpl
