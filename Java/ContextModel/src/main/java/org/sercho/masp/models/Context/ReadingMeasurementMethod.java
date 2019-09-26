/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Reading Measurement Method</b></em>', and utility methods for working
 * with them. <!-- end-user-doc -->
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getReadingMeasurementMethod()
 * @model
 * @generated
 */
public enum ReadingMeasurementMethod implements Enumerator {
    /**
     * The '<em><b>Instantaneous Value</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #INSTANTANEOUS_VALUE_VALUE
     * @generated
     * @ordered
     */
    INSTANTANEOUS_VALUE(7, "InstantaneousValue", "Instantaneous Value"),

    /**
     * The '<em><b>Meter Reading</b></em>' literal object. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #METER_READING_VALUE
     * @generated
     * @ordered
     */
    METER_READING(8, "MeterReading", "Meter Reading");

    /**
     * The '<em><b>Instantaneous Value</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Instantaneous Value</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #INSTANTANEOUS_VALUE
     * @model name="InstantaneousValue" literal="Instantaneous Value"
     * @generated
     * @ordered
     */
    public static final int INSTANTANEOUS_VALUE_VALUE = 7;

    /**
     * The '<em><b>Meter Reading</b></em>' literal value. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Meter Reading</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #METER_READING
     * @model name="MeterReading" literal="Meter Reading"
     * @generated
     * @ordered
     */
    public static final int METER_READING_VALUE = 8;

    /**
     * An array of all the '<em><b>Reading Measurement Method</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final ReadingMeasurementMethod[] VALUES_ARRAY = new ReadingMeasurementMethod[]{
            INSTANTANEOUS_VALUE, METER_READING,};

    /**
     * A public read-only list of all the '
     * <em><b>Reading Measurement Method</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<ReadingMeasurementMethod> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Reading Measurement Method</b></em>' literal with the
     * specified literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ReadingMeasurementMethod get(String literal) {
        for(int i = 0; i < VALUES_ARRAY.length; ++i) {
            ReadingMeasurementMethod result = VALUES_ARRAY[i];
            if(result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Reading Measurement Method</b></em>' literal with the
     * specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ReadingMeasurementMethod getByName(String name) {
        for(int i = 0; i < VALUES_ARRAY.length; ++i) {
            ReadingMeasurementMethod result = VALUES_ARRAY[i];
            if(result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Reading Measurement Method</b></em>' literal with the
     * specified integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ReadingMeasurementMethod get(int value) {
        switch(value) {
            case INSTANTANEOUS_VALUE_VALUE:
                return INSTANTANEOUS_VALUE;
            case METER_READING_VALUE:
                return METER_READING;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    private ReadingMeasurementMethod(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getLiteral() {
        return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string
     * representation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }

} // ReadingMeasurementMethod
