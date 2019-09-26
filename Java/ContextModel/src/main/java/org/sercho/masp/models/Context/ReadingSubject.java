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
 * <em><b>Reading Subject</b></em>', and utility methods for working with them.
 * <!-- end-user-doc -->
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getReadingSubject()
 * @model
 * @generated
 */
public enum ReadingSubject implements Enumerator {
    /**
     * The '<em><b>Abstract Object</b></em>' literal object. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #ABSTRACT_OBJECT_VALUE
     * @generated
     * @ordered
     */
    ABSTRACT_OBJECT(0, "abstractObject", "abstract Object"),

    /**
     * The '<em><b>Electricity</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #ELECTRICITY_VALUE
     * @generated
     * @ordered
     */
    ELECTRICITY(1, "Electricity", "Electricity"),

    /**
     * The '<em><b>Heat Cost Allocation</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #HEAT_COST_ALLOCATION_VALUE
     * @generated
     * @ordered
     */
    HEAT_COST_ALLOCATION(4, "HeatCostAllocation", "Heat Cost Allocation"),

    /**
     * The '<em><b>Chillness</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #CHILLNESS_VALUE
     * @generated
     * @ordered
     */
    CHILLNESS(5, "Chillness", "Chillness"),

    /**
     * The '<em><b>Heat</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #HEAT_VALUE
     * @generated
     * @ordered
     */
    HEAT(6, "Heat", "Heat"),

    /**
     * The '<em><b>Gas</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #GAS_VALUE
     * @generated
     * @ordered
     */
    GAS(7, "Gas", "Gas"),

    /**
     * The '<em><b>Water Cold</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #WATER_COLD_VALUE
     * @generated
     * @ordered
     */
    WATER_COLD(8, "WaterCold", "Water (cold)"),

    /**
     * The '<em><b>Water Hot</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #WATER_HOT_VALUE
     * @generated
     * @ordered
     */
    WATER_HOT(9, "WaterHot", "Water (hot)");

    /**
     * The '<em><b>Abstract Object</b></em>' literal value. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Abstract Object</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #ABSTRACT_OBJECT
     * @model name="abstractObject" literal="abstract Object"
     * @generated
     * @ordered
     */
    public static final int ABSTRACT_OBJECT_VALUE = 0;

    /**
     * The '<em><b>Electricity</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Electricity</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #ELECTRICITY
     * @model name="Electricity"
     * @generated
     * @ordered
     */
    public static final int ELECTRICITY_VALUE = 1;

    /**
     * The '<em><b>Heat Cost Allocation</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Heat Cost Allocation</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #HEAT_COST_ALLOCATION
     * @model name="HeatCostAllocation" literal="Heat Cost Allocation"
     * @generated
     * @ordered
     */
    public static final int HEAT_COST_ALLOCATION_VALUE = 4;

    /**
     * The '<em><b>Chillness</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Chillness</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #CHILLNESS
     * @model name="Chillness"
     * @generated
     * @ordered
     */
    public static final int CHILLNESS_VALUE = 5;

    /**
     * The '<em><b>Heat</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Heat</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #HEAT
     * @model name="Heat"
     * @generated
     * @ordered
     */
    public static final int HEAT_VALUE = 6;

    /**
     * The '<em><b>Gas</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Gas</b></em>' literal object isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #GAS
     * @model name="Gas"
     * @generated
     * @ordered
     */
    public static final int GAS_VALUE = 7;

    /**
     * The '<em><b>Water Cold</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Water Cold</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #WATER_COLD
     * @model name="WaterCold" literal="Water (cold)"
     * @generated
     * @ordered
     */
    public static final int WATER_COLD_VALUE = 8;

    /**
     * The '<em><b>Water Hot</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Water Hot</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #WATER_HOT
     * @model name="WaterHot" literal="Water (hot)"
     * @generated
     * @ordered
     */
    public static final int WATER_HOT_VALUE = 9;

    /**
     * An array of all the '<em><b>Reading Subject</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final ReadingSubject[] VALUES_ARRAY = new ReadingSubject[]{
            ABSTRACT_OBJECT, ELECTRICITY, HEAT_COST_ALLOCATION, CHILLNESS, HEAT, GAS,
            WATER_COLD, WATER_HOT,};

    /**
     * A public read-only list of all the '<em><b>Reading Subject</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<ReadingSubject> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Reading Subject</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ReadingSubject get(String literal) {
        for(int i = 0; i < VALUES_ARRAY.length; ++i) {
            ReadingSubject result = VALUES_ARRAY[i];
            if(result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Reading Subject</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ReadingSubject getByName(String name) {
        for(int i = 0; i < VALUES_ARRAY.length; ++i) {
            ReadingSubject result = VALUES_ARRAY[i];
            if(result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Reading Subject</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ReadingSubject get(int value) {
        switch(value) {
            case ABSTRACT_OBJECT_VALUE:
                return ABSTRACT_OBJECT;
            case ELECTRICITY_VALUE:
                return ELECTRICITY;
            case HEAT_COST_ALLOCATION_VALUE:
                return HEAT_COST_ALLOCATION;
            case CHILLNESS_VALUE:
                return CHILLNESS;
            case HEAT_VALUE:
                return HEAT;
            case GAS_VALUE:
                return GAS;
            case WATER_COLD_VALUE:
                return WATER_COLD;
            case WATER_HOT_VALUE:
                return WATER_HOT;
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
    private ReadingSubject(int value, String name, String literal) {
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

} // ReadingSubject
