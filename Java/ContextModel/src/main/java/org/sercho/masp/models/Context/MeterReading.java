/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import org.eclipse.emf.ecore.EObject;

import de.dailab.masp.models.Properties.DoubleProperty;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Meter Reading</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> A MeterReading describes a reading of meter data of
 * a specific type, described by the attributes of the MeterReading. <!--
 * end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.MeterReading#getValue <em>Value
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.MeterReading#getSubject <em>Subject
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.MeterReading#getMeasurand <em>
 * Measurand</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.MeterReading#getMeasurementMethod
 * <em>Measurement Method</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.MeterReading#getRate <em>Rate</em>}
 * </li>
 * <li>{@link org.sercho.masp.models.Context.MeterReading#getIdentifier <em>
 * Identifier</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getMeterReading()
 * @model
 * @generated
 */
public interface MeterReading extends EObject {

    /**
     * Returns the value of the '<em><b>Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Value</em>' containment reference.
     * @see #setValue(DoubleProperty)
     * @see org.sercho.masp.models.Context.ContextPackage#getMeterReading_Value()
     * @model containment="true" required="true"
     * @generated
     */
    DoubleProperty getValue();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.MeterReading#getValue
     * <em>Value</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Value</em>' containment reference.
     * @see #getValue()
     * @generated
     */
    void setValue(DoubleProperty value);

    /**
     * Returns the value of the '<em><b>Subject</b></em>' attribute. The
     * literals are from the enumeration
     * {@link org.sercho.masp.models.Context.ReadingSubject}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Subject</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Subject</em>' attribute.
     * @see org.sercho.masp.models.Context.ReadingSubject
     * @see #setSubject(ReadingSubject)
     * @see org.sercho.masp.models.Context.ContextPackage#getMeterReading_Subject()
     * @model
     * @generated
     */
    ReadingSubject getSubject();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.MeterReading#getSubject
     * <em>Subject</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Subject</em>' attribute.
     * @see org.sercho.masp.models.Context.ReadingSubject
     * @see #getSubject()
     * @generated
     */
    void setSubject(ReadingSubject value);

    /**
     * Returns the value of the '<em><b>Measurand</b></em>' attribute. The
     * literals are from the enumeration
     * {@link org.sercho.masp.models.Context.ReadingMeasurand}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Measurand</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Measurand</em>' attribute.
     * @see org.sercho.masp.models.Context.ReadingMeasurand
     * @see #setMeasurand(ReadingMeasurand)
     * @see org.sercho.masp.models.Context.ContextPackage#getMeterReading_Measurand()
     * @model
     * @generated
     */
    ReadingMeasurand getMeasurand();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.MeterReading#getMeasurand
     * <em>Measurand</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Measurand</em>' attribute.
     * @see org.sercho.masp.models.Context.ReadingMeasurand
     * @see #getMeasurand()
     * @generated
     */
    void setMeasurand(ReadingMeasurand value);

    /**
     * Returns the value of the '<em><b>Measurement Method</b></em>' attribute.
     * The literals are from the enumeration
     * {@link org.sercho.masp.models.Context.ReadingMeasurementMethod}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Measurement Method</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Measurement Method</em>' attribute.
     * @see org.sercho.masp.models.Context.ReadingMeasurementMethod
     * @see #setMeasurementMethod(ReadingMeasurementMethod)
     * @see org.sercho.masp.models.Context.ContextPackage#getMeterReading_MeasurementMethod()
     * @model
     * @generated
     */
    ReadingMeasurementMethod getMeasurementMethod();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.MeterReading#getMeasurementMethod
     * <em>Measurement Method</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Measurement Method</em>' attribute.
     * @see org.sercho.masp.models.Context.ReadingMeasurementMethod
     * @see #getMeasurementMethod()
     * @generated
     */
    void setMeasurementMethod(ReadingMeasurementMethod value);

    /**
     * Returns the value of the '<em><b>Rate</b></em>' attribute. The literals
     * are from the enumeration
     * {@link org.sercho.masp.models.Context.ReadingRate}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Rate</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Rate</em>' attribute.
     * @see org.sercho.masp.models.Context.ReadingRate
     * @see #setRate(ReadingRate)
     * @see org.sercho.masp.models.Context.ContextPackage#getMeterReading_Rate()
     * @model
     * @generated
     */
    ReadingRate getRate();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.MeterReading#getRate <em>Rate</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Rate</em>' attribute.
     * @see org.sercho.masp.models.Context.ReadingRate
     * @see #getRate()
     * @generated
     */
    void setRate(ReadingRate value);

    /**
     * Returns the value of the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * identifier of this reading, must be unique at least within the Meter.
     * <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Identifier</em>' attribute.
     * @see #setIdentifier(String)
     * @see org.sercho.masp.models.Context.ContextPackage#getMeterReading_Identifier()
     * @model
     * @generated
     */
    String getIdentifier();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.MeterReading#getIdentifier
     * <em>Identifier</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Identifier</em>' attribute.
     * @see #getIdentifier()
     * @generated
     */
    void setIdentifier(String value);

} // MeterReading
