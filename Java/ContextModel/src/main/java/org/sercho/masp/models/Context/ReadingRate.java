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
 * <em><b>Reading Rate</b></em>', and utility methods for working with them.
 * <!-- end-user-doc -->
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getReadingRate()
 * @model
 * @generated
 */
public enum ReadingRate implements Enumerator {
    /**
     * The '<em><b>Total</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #TOTAL_VALUE
     * @generated
     * @ordered
     */
    TOTAL(0, "Total", "Total"),

    /**
     * The '<em><b>Rate1</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #RATE1_VALUE
     * @generated
     * @ordered
     */
    RATE1(1, "Rate1", "Rate 1"),

    /**
     * The '<em><b>Rate2</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #RATE2_VALUE
     * @generated
     * @ordered
     */
    RATE2(2, "Rate2", "Rate 2");

    /**
     * The '<em><b>Total</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Total</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #TOTAL
     * @model name="Total"
     * @generated
     * @ordered
     */
    public static final int TOTAL_VALUE = 0;

    /**
     * The '<em><b>Rate1</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Rate1</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #RATE1
     * @model name="Rate1" literal="Rate 1"
     * @generated
     * @ordered
     */
    public static final int RATE1_VALUE = 1;

    /**
     * The '<em><b>Rate2</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Rate2</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #RATE2
     * @model name="Rate2" literal="Rate 2"
     * @generated
     * @ordered
     */
    public static final int RATE2_VALUE = 2;

    /**
     * An array of all the '<em><b>Reading Rate</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final ReadingRate[] VALUES_ARRAY = new ReadingRate[]{TOTAL, RATE1,
            RATE2,};

    /**
     * A public read-only list of all the '<em><b>Reading Rate</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<ReadingRate> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Reading Rate</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ReadingRate get(String literal) {
        for(int i = 0; i < VALUES_ARRAY.length; ++i) {
            ReadingRate result = VALUES_ARRAY[i];
            if(result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Reading Rate</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ReadingRate getByName(String name) {
        for(int i = 0; i < VALUES_ARRAY.length; ++i) {
            ReadingRate result = VALUES_ARRAY[i];
            if(result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Reading Rate</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ReadingRate get(int value) {
        switch(value) {
            case TOTAL_VALUE:
                return TOTAL;
            case RATE1_VALUE:
                return RATE1;
            case RATE2_VALUE:
                return RATE2;
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
    private ReadingRate(int value, String name, String literal) {
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

} // ReadingRate
