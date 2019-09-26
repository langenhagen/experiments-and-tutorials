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
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sercho.masp.models.Context.Area;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Door;
import org.sercho.masp.models.Context.ElementWithPosition;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.Place;
import org.sercho.masp.models.Context.TemperatureSensor;
import org.sercho.masp.models.Context.User;
import org.sercho.masp.models.Context.Vector;
import org.sercho.masp.models.Context.Window;

import de.dailab.masp.models.Properties.DoubleProperty;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Place</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.PlaceImpl#getAreas <em>Areas
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.PlaceImpl#getElements <em>
 * Elements</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.PlaceImpl#getEnvironment <em>
 * Environment</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.PlaceImpl#getDoors <em>Doors
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.PlaceImpl#getWindows <em>
 * Windows</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.PlaceImpl#getFloor <em>Floor
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class PlaceImpl extends EnvironmentElementImpl implements Place {

    /**
     * The cached value of the '{@link #getAreas() <em>Areas</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getAreas()
     * @generated
     * @ordered
     */
    protected EList<Area> areas;

    /**
     * The cached value of the '{@link #getElements() <em>Elements</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getElements()
     * @generated
     * @ordered
     */
    protected EList<ElementWithPosition> elements;

    /**
     * The cached value of the '{@link #getDoors() <em>Doors</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDoors()
     * @generated
     * @ordered
     */
    protected EList<Door> doors;

    /**
     * The cached value of the '{@link #getWindows() <em>Windows</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getWindows()
     * @generated
     * @ordered
     */
    protected EList<Window> windows;

    /**
     * The default value of the '{@link #getFloor() <em>Floor</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFloor()
     * @generated
     * @ordered
     */
    protected static final Integer FLOOR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFloor() <em>Floor</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFloor()
     * @generated
     * @ordered
     */
    protected Integer floor = FLOOR_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected PlaceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.PLACE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Area> getAreas() {
        if(this.areas == null) {
            this.areas = new EObjectContainmentEList<Area>(Area.class, this, ContextPackage.PLACE__AREAS);
        }
        return this.areas;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ElementWithPosition> getElements() {
        if(this.elements == null) {
            this.elements = new EObjectWithInverseResolvingEList<ElementWithPosition>(ElementWithPosition.class, this, ContextPackage.PLACE__ELEMENTS, ContextPackage.ELEMENT_WITH_POSITION__PLACE);
        }
        return this.elements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Environment getEnvironment() {
        if(eContainerFeatureID() != ContextPackage.PLACE__ENVIRONMENT) {
            return null;
        }
        return (Environment)eContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetEnvironment(final Environment newEnvironment, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newEnvironment, ContextPackage.PLACE__ENVIRONMENT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setEnvironment(final Environment newEnvironment) {
        if((newEnvironment != eInternalContainer()) || ((eContainerFeatureID() != ContextPackage.PLACE__ENVIRONMENT) && (newEnvironment != null))) {
            if(EcoreUtil.isAncestor(this, newEnvironment)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            }
            NotificationChain msgs = null;
            if(eInternalContainer() != null) {
                msgs = eBasicRemoveFromContainer(msgs);
            }
            if(newEnvironment != null) {
                msgs = ((InternalEObject)newEnvironment).eInverseAdd(this, ContextPackage.ENVIRONMENT__PLACES, Environment.class, msgs);
            }
            msgs = basicSetEnvironment(newEnvironment, msgs);
            if(msgs != null) {
                msgs.dispatch();
            }
        } else if(eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.PLACE__ENVIRONMENT, newEnvironment, newEnvironment));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Door> getDoors() {
        if(this.doors == null) {
            this.doors = new EObjectContainmentWithInverseEList<Door>(Door.class, this, ContextPackage.PLACE__DOORS, ContextPackage.DOOR__SOURCE);
        }
        return this.doors;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Window> getWindows() {
        if(this.windows == null) {
            this.windows = new EObjectContainmentWithInverseEList<Window>(Window.class, this, ContextPackage.PLACE__WINDOWS, ContextPackage.WINDOW__SOURCE);
        }
        return this.windows;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Integer getFloor() {
        return this.floor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setFloor(final Integer newFloor) {
        final Integer oldFloor = this.floor;
        this.floor = newFloor;
        if(eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.PLACE__FLOOR, oldFloor, this.floor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public boolean contains(final Vector position) {
        for(final Area area : this.getAreas()) {
            if(area.contains(position)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public final EList<InteractionResource> getInteractionResources() {
        final EList<InteractionResource> interactionsResources = new BasicEList<InteractionResource>();
        synchronized(getElements()) {
            for(final ElementWithPosition element : getElements()) {
                if(element instanceof InteractionResource) {
                    interactionsResources.add((InteractionResource)element);
                }
            }
        }
        return interactionsResources;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public final EList<User> getUsers() {
        final EList<User> users = new BasicEList<User>();
        synchronized(getElements()) {
            for(final ElementWithPosition element : getElements()) {
                if(element instanceof User) {
                    users.add((User)element);
                }
            }
        }
        return users;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public final Area addArea(final double originX, final double originY, final double originZ, final double spanX, final double spanY, final double spanZ) {
        final Area area = ContextFactory.eINSTANCE.createArea();
        area.setNewOrigin(originX, originY, originZ);
        area.setNewSpan(spanX, spanY, spanZ);

        synchronized(getAreas()) {
            getAreas().add(area);
        }

        return area;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public final void removeArea(final Area area) {
        if(area == null) {
            throw new IllegalArgumentException("area argument must not be null in method removeArea");
        }
        synchronized(getAreas()) {
            getAreas().remove(area);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void addElement(final ElementWithPosition element) {
        if(element == null) {
            throw new IllegalArgumentException("element argument must not be null in method addElement");
        }
        synchronized(getElements()) {
            getElements().add(element);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void removeElement(final ElementWithPosition element) {
        if(element == null) {
            throw new IllegalArgumentException("element argument must not be null in method removeElement");
        }
        synchronized(getElements()) {
            getElements().remove(element);
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public Door addDoor(final String id, final String name, final double positionX, final double positionY, final double positionZ, final double spanX, final double spanY, final double spanZ) {
        if(id == null) {
            throw new IllegalArgumentException("id argument must not be null in method addDoor");
        }

        if(name == null) {
            throw new IllegalArgumentException("name argument must not be null in method addDoor");
        }

        final Door door = ContextFactory.eINSTANCE.createDoor();
        door.setId(id);
        door.setName(name);
        door.setPosition(positionX, positionY, positionZ);
        final Vector span = ContextFactory.eINSTANCE.createVector();
        span.setCoordinates(spanX, spanY, spanZ);
        door.setSpan(span);

        synchronized(getDoors()) {
            getDoors().add(door);
        }

        return door;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public Window addWindow(final String id, final String name, final double positionX, final double positionY, final double positionZ, final double spanX, final double spanY, final double spanZ) {
        if(id == null) {
            throw new IllegalArgumentException("id argument must not be null in method addWindow");
        }
        if(name == null) {
            throw new IllegalArgumentException("name argument must not be null in method addWindow");
        }

        final Window window = ContextFactory.eINSTANCE.createWindow();
        window.setId(id);
        window.setName(name);
        window.setPosition(positionX, positionY, positionZ);
        final Vector span = ContextFactory.eINSTANCE.createVector();
        span.setCoordinates(spanX, spanY, spanZ);
        window.setSpan(span);

        synchronized(getWindows()) {
            getWindows().add(window);
        }

        return window;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public void removeDoor(final Door door) {
        if(door == null) {
            throw new IllegalArgumentException("door argument must not be null in method removeDoor");
        }
        synchronized(getDoors()) {
            getDoors().remove(door);
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public void removeWindow(final Window window) {
        if(window == null) {
            throw new IllegalArgumentException("window argument must not be null in method removeWindow");
        }
        synchronized(getWindows()) {
            getWindows().remove(window);
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     * @author Andre Schulz
     * @since 1.3.8
     */
    @Override
    public void setNewFloor(final Integer newFloor) {
        if(newFloor == null) {
            throw new IllegalArgumentException("newFloor attribute must not be null in mehtod setNewFloor!");
        }

        this.setFloor(newFloor);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    public Double getTemperatureAverage() {
        // get temperature sensors of the room
        int numberOfSensors = 0;
        double temperatureSum = 0;
        DoubleProperty valueProperty;
        Double temperature;
        synchronized(getElements()) {
            for(final ElementWithPosition element : getElements()) {
                if(element instanceof TemperatureSensor) {
                    valueProperty = ((TemperatureSensor)element).getValue();
                    if(valueProperty == null) {
                        // no value, skip sensor
                        continue;
                    }
                    temperature = valueProperty.getValue();
                    if(temperature == null) {
                        // no value, skip sensor
                        continue;
                    }
                    numberOfSensors++;
                    temperatureSum += temperature.doubleValue();
                }
            }
        }
        if(numberOfSensors == 0) {
            return null;
        }
        final double temperatureAverage = temperatureSum / numberOfSensors;
        // return rounded with one comma-digit
        return Double.valueOf(Math.round(temperatureAverage * 10) / 10d);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.PLACE__ELEMENTS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getElements()).basicAdd(otherEnd, msgs);
            case ContextPackage.PLACE__ENVIRONMENT:
                if(eInternalContainer() != null) {
                    msgs = eBasicRemoveFromContainer(msgs);
                }
                return basicSetEnvironment((Environment)otherEnd, msgs);
            case ContextPackage.PLACE__DOORS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getDoors()).basicAdd(otherEnd, msgs);
            case ContextPackage.PLACE__WINDOWS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getWindows()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(final InternalEObject otherEnd, final int featureID, final NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.PLACE__AREAS:
                return ((InternalEList<?>)getAreas()).basicRemove(otherEnd, msgs);
            case ContextPackage.PLACE__ELEMENTS:
                return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
            case ContextPackage.PLACE__ENVIRONMENT:
                return basicSetEnvironment(null, msgs);
            case ContextPackage.PLACE__DOORS:
                return ((InternalEList<?>)getDoors()).basicRemove(otherEnd, msgs);
            case ContextPackage.PLACE__WINDOWS:
                return ((InternalEList<?>)getWindows()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(final NotificationChain msgs) {
        switch(eContainerFeatureID()) {
            case ContextPackage.PLACE__ENVIRONMENT:
                return eInternalContainer().eInverseRemove(this, ContextPackage.ENVIRONMENT__PLACES, Environment.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch(featureID) {
            case ContextPackage.PLACE__AREAS:
                return getAreas();
            case ContextPackage.PLACE__ELEMENTS:
                return getElements();
            case ContextPackage.PLACE__ENVIRONMENT:
                return getEnvironment();
            case ContextPackage.PLACE__DOORS:
                return getDoors();
            case ContextPackage.PLACE__WINDOWS:
                return getWindows();
            case ContextPackage.PLACE__FLOOR:
                return getFloor();
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
    public void eSet(final int featureID, final Object newValue) {
        switch(featureID) {
            case ContextPackage.PLACE__AREAS:
                getAreas().clear();
                getAreas().addAll((Collection<? extends Area>)newValue);
                return;
            case ContextPackage.PLACE__ELEMENTS:
                getElements().clear();
                getElements().addAll((Collection<? extends ElementWithPosition>)newValue);
                return;
            case ContextPackage.PLACE__ENVIRONMENT:
                setEnvironment((Environment)newValue);
                return;
            case ContextPackage.PLACE__DOORS:
                getDoors().clear();
                getDoors().addAll((Collection<? extends Door>)newValue);
                return;
            case ContextPackage.PLACE__WINDOWS:
                getWindows().clear();
                getWindows().addAll((Collection<? extends Window>)newValue);
                return;
            case ContextPackage.PLACE__FLOOR:
                setFloor((Integer)newValue);
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
    public void eUnset(final int featureID) {
        switch(featureID) {
            case ContextPackage.PLACE__AREAS:
                getAreas().clear();
                return;
            case ContextPackage.PLACE__ELEMENTS:
                getElements().clear();
                return;
            case ContextPackage.PLACE__ENVIRONMENT:
                setEnvironment((Environment)null);
                return;
            case ContextPackage.PLACE__DOORS:
                getDoors().clear();
                return;
            case ContextPackage.PLACE__WINDOWS:
                getWindows().clear();
                return;
            case ContextPackage.PLACE__FLOOR:
                setFloor(FLOOR_EDEFAULT);
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
    public boolean eIsSet(final int featureID) {
        switch(featureID) {
            case ContextPackage.PLACE__AREAS:
                return (this.areas != null) && !this.areas.isEmpty();
            case ContextPackage.PLACE__ELEMENTS:
                return (this.elements != null) && !this.elements.isEmpty();
            case ContextPackage.PLACE__ENVIRONMENT:
                return getEnvironment() != null;
            case ContextPackage.PLACE__DOORS:
                return (this.doors != null) && !this.doors.isEmpty();
            case ContextPackage.PLACE__WINDOWS:
                return (this.windows != null) && !this.windows.isEmpty();
            case ContextPackage.PLACE__FLOOR:
                return FLOOR_EDEFAULT == null ? this.floor != null
                        : !FLOOR_EDEFAULT.equals(this.floor);
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
        if(eIsProxy()) {
            return super.toString();
        }

        final StringBuffer result = new StringBuffer(super.toString());
        result.append(" (floor: ");
        result.append(this.floor);
        result.append(')');
        return result.toString();
    }

} // PlaceImpl
