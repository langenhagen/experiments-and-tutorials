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
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.MeterReading;
import org.sercho.masp.models.Context.ReadingMeasurand;
import org.sercho.masp.models.Context.ReadingMeasurementMethod;
import org.sercho.masp.models.Context.ReadingRate;
import org.sercho.masp.models.Context.ReadingSubject;

import de.dailab.masp.models.Properties.DoubleProperty;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Meter Reading</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.MeterReadingImpl#getValue <em>
 * Value</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.MeterReadingImpl#getSubject
 * <em>Subject</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.MeterReadingImpl#getMeasurand
 * <em>Measurand</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.MeterReadingImpl#getMeasurementMethod
 * <em>Measurement Method</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.MeterReadingImpl#getRate <em>
 * Rate</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.MeterReadingImpl#getIdentifier
 * <em>Identifier</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MeterReadingImpl extends EObjectImpl implements MeterReading {

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected DoubleProperty value;

    /**
     * The default value of the '{@link #getSubject() <em>Subject</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSubject()
     * @generated
     * @ordered
     */
    protected static final ReadingSubject SUBJECT_EDEFAULT = ReadingSubject.ABSTRACT_OBJECT;

    /**
     * The cached value of the '{@link #getSubject() <em>Subject</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSubject()
     * @generated
     * @ordered
     */
    protected ReadingSubject subject = SUBJECT_EDEFAULT;

    /**
     * The default value of the '{@link #getMeasurand() <em>Measurand</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMeasurand()
     * @generated
     * @ordered
     */
    protected static final ReadingMeasurand MEASURAND_EDEFAULT = ReadingMeasurand.CONSUMPTION;

    /**
     * The cached value of the '{@link #getMeasurand() <em>Measurand</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMeasurand()
     * @generated
     * @ordered
     */
    protected ReadingMeasurand measurand = MEASURAND_EDEFAULT;

    /**
     * The default value of the '{@link #getMeasurementMethod()
     * <em>Measurement Method</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getMeasurementMethod()
     * @generated
     * @ordered
     */
    protected static final ReadingMeasurementMethod MEASUREMENT_METHOD_EDEFAULT = ReadingMeasurementMethod.INSTANTANEOUS_VALUE;

    /**
     * The cached value of the '{@link #getMeasurementMethod()
     * <em>Measurement Method</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getMeasurementMethod()
     * @generated
     * @ordered
     */
    protected ReadingMeasurementMethod measurementMethod = MEASUREMENT_METHOD_EDEFAULT;

    /**
     * The default value of the '{@link #getRate() <em>Rate</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getRate()
     * @generated
     * @ordered
     */
    protected static final ReadingRate RATE_EDEFAULT = ReadingRate.TOTAL;

    /**
     * The cached value of the '{@link #getRate() <em>Rate</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getRate()
     * @generated
     * @ordered
     */
    protected ReadingRate rate = RATE_EDEFAULT;

    /**
     * The default value of the '{@link #getIdentifier() <em>Identifier</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getIdentifier()
     * @generated
     * @ordered
     */
    protected static final String IDENTIFIER_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getIdentifier()
     * @generated
     * @ordered
     */
    protected String identifier = IDENTIFIER_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected MeterReadingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.METER_READING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DoubleProperty getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetValue(DoubleProperty newValue, NotificationChain msgs) {
        DoubleProperty oldValue = value;
        value = newValue;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.METER_READING__VALUE, oldValue, newValue);
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
    public void setValue(DoubleProperty newValue) {
        if(newValue != value) {
            NotificationChain msgs = null;
            if(value != null)
                msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.METER_READING__VALUE, null, msgs);
            if(newValue != null)
                msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.METER_READING__VALUE, null, msgs);
            msgs = basicSetValue(newValue, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.METER_READING__VALUE, newValue, newValue));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ReadingSubject getSubject() {
        return subject;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setSubject(ReadingSubject newSubject) {
        ReadingSubject oldSubject = subject;
        subject = newSubject == null ? SUBJECT_EDEFAULT : newSubject;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.METER_READING__SUBJECT, oldSubject, subject));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ReadingMeasurand getMeasurand() {
        return measurand;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setMeasurand(ReadingMeasurand newMeasurand) {
        ReadingMeasurand oldMeasurand = measurand;
        measurand = newMeasurand == null ? MEASURAND_EDEFAULT : newMeasurand;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.METER_READING__MEASURAND, oldMeasurand, measurand));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ReadingMeasurementMethod getMeasurementMethod() {
        return measurementMethod;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setMeasurementMethod(ReadingMeasurementMethod newMeasurementMethod) {
        ReadingMeasurementMethod oldMeasurementMethod = measurementMethod;
        measurementMethod = newMeasurementMethod == null ? MEASUREMENT_METHOD_EDEFAULT
                : newMeasurementMethod;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.METER_READING__MEASUREMENT_METHOD, oldMeasurementMethod, measurementMethod));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ReadingRate getRate() {
        return rate;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setRate(ReadingRate newRate) {
        ReadingRate oldRate = rate;
        rate = newRate == null ? RATE_EDEFAULT : newRate;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.METER_READING__RATE, oldRate, rate));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setIdentifier(String newIdentifier) {
        String oldIdentifier = identifier;
        identifier = newIdentifier;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.METER_READING__IDENTIFIER, oldIdentifier, identifier));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.METER_READING__VALUE:
                return basicSetValue(null, msgs);
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
            case ContextPackage.METER_READING__VALUE:
                return getValue();
            case ContextPackage.METER_READING__SUBJECT:
                return getSubject();
            case ContextPackage.METER_READING__MEASURAND:
                return getMeasurand();
            case ContextPackage.METER_READING__MEASUREMENT_METHOD:
                return getMeasurementMethod();
            case ContextPackage.METER_READING__RATE:
                return getRate();
            case ContextPackage.METER_READING__IDENTIFIER:
                return getIdentifier();
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
            case ContextPackage.METER_READING__VALUE:
                setValue((DoubleProperty)newValue);
                return;
            case ContextPackage.METER_READING__SUBJECT:
                setSubject((ReadingSubject)newValue);
                return;
            case ContextPackage.METER_READING__MEASURAND:
                setMeasurand((ReadingMeasurand)newValue);
                return;
            case ContextPackage.METER_READING__MEASUREMENT_METHOD:
                setMeasurementMethod((ReadingMeasurementMethod)newValue);
                return;
            case ContextPackage.METER_READING__RATE:
                setRate((ReadingRate)newValue);
                return;
            case ContextPackage.METER_READING__IDENTIFIER:
                setIdentifier((String)newValue);
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
            case ContextPackage.METER_READING__VALUE:
                setValue((DoubleProperty)null);
                return;
            case ContextPackage.METER_READING__SUBJECT:
                setSubject(SUBJECT_EDEFAULT);
                return;
            case ContextPackage.METER_READING__MEASURAND:
                setMeasurand(MEASURAND_EDEFAULT);
                return;
            case ContextPackage.METER_READING__MEASUREMENT_METHOD:
                setMeasurementMethod(MEASUREMENT_METHOD_EDEFAULT);
                return;
            case ContextPackage.METER_READING__RATE:
                setRate(RATE_EDEFAULT);
                return;
            case ContextPackage.METER_READING__IDENTIFIER:
                setIdentifier(IDENTIFIER_EDEFAULT);
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
            case ContextPackage.METER_READING__VALUE:
                return value != null;
            case ContextPackage.METER_READING__SUBJECT:
                return subject != SUBJECT_EDEFAULT;
            case ContextPackage.METER_READING__MEASURAND:
                return measurand != MEASURAND_EDEFAULT;
            case ContextPackage.METER_READING__MEASUREMENT_METHOD:
                return measurementMethod != MEASUREMENT_METHOD_EDEFAULT;
            case ContextPackage.METER_READING__RATE:
                return rate != RATE_EDEFAULT;
            case ContextPackage.METER_READING__IDENTIFIER:
                return IDENTIFIER_EDEFAULT == null ? identifier != null
                        : !IDENTIFIER_EDEFAULT.equals(identifier);
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
        result.append(" (subject: ");
        result.append(subject);
        result.append(", measurand: ");
        result.append(measurand);
        result.append(", measurementMethod: ");
        result.append(measurementMethod);
        result.append(", rate: ");
        result.append(rate);
        result.append(", identifier: ");
        result.append(identifier);
        result.append(')');
        return result.toString();
    }

} // MeterReadingImpl
