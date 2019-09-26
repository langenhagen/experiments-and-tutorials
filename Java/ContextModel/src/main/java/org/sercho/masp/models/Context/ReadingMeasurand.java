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
 * <em><b>Reading Measurand</b></em>', and utility methods for working with
 * them. <!-- end-user-doc -->
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getReadingMeasurand()
 * @model
 * @generated
 */
public enum ReadingMeasurand implements Enumerator {
    /**
     * The '<em><b>Consumption</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #CONSUMPTION_VALUE
     * @generated
     * @ordered
     */
    CONSUMPTION(15, "Consumption", "Consumption"),

    /**
     * The '<em><b>Active Power</b></em>' literal object. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #ACTIVE_POWER_VALUE
     * @generated
     * @ordered
     */
    ACTIVE_POWER(1, "ActivePower", "Active Power");

    /**
     * The '<em><b>Consumption</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Consumption</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #CONSUMPTION
     * @model name="Consumption"
     * @generated
     * @ordered
     */
    public static final int CONSUMPTION_VALUE = 15;

    /**
     * The '<em><b>Active Power</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Active Power</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #ACTIVE_POWER
     * @model name="ActivePower" literal="Active Power"
     * @generated
     * @ordered
     */
    public static final int ACTIVE_POWER_VALUE = 1;

    /**
     * An array of all the '<em><b>Reading Measurand</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final ReadingMeasurand[] VALUES_ARRAY = new ReadingMeasurand[]{
            CONSUMPTION, ACTIVE_POWER,};

    /**
     * A public read-only list of all the '<em><b>Reading Measurand</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<ReadingMeasurand> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Reading Measurand</b></em>' literal with the
     * specified literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ReadingMeasurand get(String literal) {
        for(int i = 0; i < VALUES_ARRAY.length; ++i) {
            ReadingMeasurand result = VALUES_ARRAY[i];
            if(result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Reading Measurand</b></em>' literal with the
     * specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ReadingMeasurand getByName(String name) {
        for(int i = 0; i < VALUES_ARRAY.length; ++i) {
            ReadingMeasurand result = VALUES_ARRAY[i];
            if(result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Reading Measurand</b></em>' literal with the
     * specified integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ReadingMeasurand get(int value) {
        switch(value) {
            case CONSUMPTION_VALUE:
                return CONSUMPTION;
            case ACTIVE_POWER_VALUE:
                return ACTIVE_POWER;
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
    private ReadingMeasurand(int value, String name, String literal) {
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

} // ReadingMeasurand
