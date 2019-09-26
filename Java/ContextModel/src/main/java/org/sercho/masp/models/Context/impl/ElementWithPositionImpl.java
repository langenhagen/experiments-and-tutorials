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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.ElementWithPosition;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.LocalizationTag;
import org.sercho.masp.models.Context.Place;
import org.sercho.masp.models.Context.Vector;
import org.sercho.masp.models.Context.util.EnvironmentUtility;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Element With Position</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.ElementWithPositionImpl#getTags
 * <em>Tags</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.ElementWithPositionImpl#getPosition
 * <em>Position</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.ElementWithPositionImpl#getPositionTimeStamp
 * <em>Position Time Stamp</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.ElementWithPositionImpl#getPlace
 * <em>Place</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class ElementWithPositionImpl extends EnvironmentElementImpl implements
        ElementWithPosition {

    /**
     * The cached value of the '{@link #getTags() <em>Tags</em>}' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTags()
     * @generated
     * @ordered
     */
    protected EList<LocalizationTag> tags;

    /**
     * The cached value of the '{@link #getPosition() <em>Position</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPosition()
     * @generated NOT
     * @ordered
     */
    protected volatile Vector position;

    /**
     * The default value of the '{@link #getPositionTimeStamp()
     * <em>Position Time Stamp</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getPositionTimeStamp()
     * @generated
     * @ordered
     */
    protected static final long POSITION_TIME_STAMP_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getPositionTimeStamp()
     * <em>Position Time Stamp</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getPositionTimeStamp()
     * @generated
     * @ordered
     */
    protected long positionTimeStamp = POSITION_TIME_STAMP_EDEFAULT;

    /**
     * The cached value of the '{@link #getPlace() <em>Place</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPlace()
     * @generated
     * @ordered
     */
    protected Place place;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ElementWithPositionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.ELEMENT_WITH_POSITION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<LocalizationTag> getTags() {
        if(tags == null) {
            tags = new EObjectWithInverseResolvingEList<LocalizationTag>(LocalizationTag.class, this, ContextPackage.ELEMENT_WITH_POSITION__TAGS, ContextPackage.LOCALIZATION_TAG__ELEMENT);
        }
        return tags;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Vector getPosition() {
        return position;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetPosition(Vector newPosition, NotificationChain msgs) {
        Vector oldPosition = position;
        position = newPosition;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.ELEMENT_WITH_POSITION__POSITION, oldPosition, newPosition);
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
    public void setPosition(Vector newPosition) {
        if(newPosition != position) {
            NotificationChain msgs = null;
            if(position != null)
                msgs = ((InternalEObject)position).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.ELEMENT_WITH_POSITION__POSITION, null, msgs);
            if(newPosition != null)
                msgs = ((InternalEObject)newPosition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.ELEMENT_WITH_POSITION__POSITION, null, msgs);
            msgs = basicSetPosition(newPosition, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.ELEMENT_WITH_POSITION__POSITION, newPosition, newPosition));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    /*
     * public synchronized NotificationChain basicSetRoom(Room newRoom,
     * NotificationChain msgs) { Room oldRoom = room; room = newRoom;
     * if(eNotificationRequired()) { ENotificationImpl notification = new
     * ENotificationImpl(this, Notification.SET,
     * ContextPackage.ELEMENT_WITH_POSITION__ROOM, oldRoom, newRoom); if(msgs ==
     * null) { msgs = notification; } else { msgs.add(notification); } } return
     * msgs; }
     */

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public long getPositionTimeStamp() {
        return positionTimeStamp;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setPositionTimeStamp(long newPositionTimeStamp) {
        long oldPositionTimeStamp = positionTimeStamp;
        positionTimeStamp = newPositionTimeStamp;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.ELEMENT_WITH_POSITION__POSITION_TIME_STAMP, oldPositionTimeStamp, positionTimeStamp));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Place getPlace() {
        if(place != null && place.eIsProxy()) {
            InternalEObject oldPlace = (InternalEObject)place;
            place = (Place)eResolveProxy(oldPlace);
            if(place != oldPlace) {
                if(eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ContextPackage.ELEMENT_WITH_POSITION__PLACE, oldPlace, place));
            }
        }
        return place;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Place basicGetPlace() {
        return place;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetPlace(Place newPlace, NotificationChain msgs) {
        Place oldPlace = place;
        place = newPlace;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.ELEMENT_WITH_POSITION__PLACE, oldPlace, newPlace);
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
    public void setPlace(Place newPlace) {
        if(newPlace != place) {
            NotificationChain msgs = null;
            if(place != null)
                msgs = ((InternalEObject)place).eInverseRemove(this, ContextPackage.PLACE__ELEMENTS, Place.class, msgs);
            if(newPlace != null)
                msgs = ((InternalEObject)newPlace).eInverseAdd(this, ContextPackage.PLACE__ELEMENTS, Place.class, msgs);
            msgs = basicSetPlace(newPlace, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.ELEMENT_WITH_POSITION__PLACE, newPlace, newPlace));
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final synchronized void setPosition(final double x, final double y, final double z) {
        final long timestamp = System.currentTimeMillis();
        if(this.position == null) {
            setPosition(ContextFactory.eINSTANCE.createVector());
        }
        this.position.setCoordinates(x, y, z);
        setPositionTimeStamp(timestamp);

        // correct place
        if((this.place == null) || !this.place.contains(this.position)) {
            // set place
            setPlace(EnvironmentUtility.getPlaceContainingPosition(getEnvironment(), this.position));
        }

        setPositionHook(x, y, z);
        this.log.debug("Set position: " + getPosition() + " for object with id=" + getId());
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public abstract Environment getEnvironment();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     * @author Andre Schulz
     */
    @Override
    public void addTag(final LocalizationTag tag) {
        if(tag == null) {
            throw new IllegalArgumentException("tag argument must not be null in method addTag!");
        }

        synchronized(getTags()) {
            getTags().add(tag);
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     * @author Andre Schulz
     */
    @Override
    public void removeTag(final LocalizationTag tag) {
        if(tag == null) {
            throw new IllegalArgumentException("tag argument must not be null in method removeTag!");
        }

        synchronized(getTags()) {
            getTags().remove(tag);
        }
    }

    /**
     * <code>setPositionHook</code> is called after a new position has been set
     * in {@link #setPosition(double, double, double)}. Overriding this method
     * allows the element to react to a new position. Calls to this method are
     * only made from {@link #setPosition(double, double, double)} and are
     * synchronized with {@link #getPosition()} and {@link #setPosition(Vector)}
     * .
     * 
     * @param x
     *            new X coordinate
     * @param y
     *            new Y coordinate
     * @param z
     *            new Z coordinate
     */
    protected void setPositionHook(final double x, final double y, final double z) {
        // override if necessary
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
            case ContextPackage.ELEMENT_WITH_POSITION__TAGS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getTags()).basicAdd(otherEnd, msgs);
            case ContextPackage.ELEMENT_WITH_POSITION__PLACE:
                if(place != null)
                    msgs = ((InternalEObject)place).eInverseRemove(this, ContextPackage.PLACE__ELEMENTS, Place.class, msgs);
                return basicSetPlace((Place)otherEnd, msgs);
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
            case ContextPackage.ELEMENT_WITH_POSITION__TAGS:
                return ((InternalEList<?>)getTags()).basicRemove(otherEnd, msgs);
            case ContextPackage.ELEMENT_WITH_POSITION__POSITION:
                return basicSetPosition(null, msgs);
            case ContextPackage.ELEMENT_WITH_POSITION__PLACE:
                return basicSetPlace(null, msgs);
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
            case ContextPackage.ELEMENT_WITH_POSITION__TAGS:
                return getTags();
            case ContextPackage.ELEMENT_WITH_POSITION__POSITION:
                return getPosition();
            case ContextPackage.ELEMENT_WITH_POSITION__POSITION_TIME_STAMP:
                return getPositionTimeStamp();
            case ContextPackage.ELEMENT_WITH_POSITION__PLACE:
                if(resolve)
                    return getPlace();
                return basicGetPlace();
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
            case ContextPackage.ELEMENT_WITH_POSITION__TAGS:
                getTags().clear();
                getTags().addAll((Collection<? extends LocalizationTag>)newValue);
                return;
            case ContextPackage.ELEMENT_WITH_POSITION__POSITION:
                setPosition((Vector)newValue);
                return;
            case ContextPackage.ELEMENT_WITH_POSITION__POSITION_TIME_STAMP:
                setPositionTimeStamp((Long)newValue);
                return;
            case ContextPackage.ELEMENT_WITH_POSITION__PLACE:
                setPlace((Place)newValue);
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
            case ContextPackage.ELEMENT_WITH_POSITION__TAGS:
                getTags().clear();
                return;
            case ContextPackage.ELEMENT_WITH_POSITION__POSITION:
                setPosition((Vector)null);
                return;
            case ContextPackage.ELEMENT_WITH_POSITION__POSITION_TIME_STAMP:
                setPositionTimeStamp(POSITION_TIME_STAMP_EDEFAULT);
                return;
            case ContextPackage.ELEMENT_WITH_POSITION__PLACE:
                setPlace((Place)null);
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
            case ContextPackage.ELEMENT_WITH_POSITION__TAGS:
                return tags != null && !tags.isEmpty();
            case ContextPackage.ELEMENT_WITH_POSITION__POSITION:
                return position != null;
            case ContextPackage.ELEMENT_WITH_POSITION__POSITION_TIME_STAMP:
                return positionTimeStamp != POSITION_TIME_STAMP_EDEFAULT;
            case ContextPackage.ELEMENT_WITH_POSITION__PLACE:
                return place != null;
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
        result.append(" (positionTimeStamp: ");
        result.append(positionTimeStamp);
        result.append(')');
        return result.toString();
    }

} // ElementWithPositionImpl
