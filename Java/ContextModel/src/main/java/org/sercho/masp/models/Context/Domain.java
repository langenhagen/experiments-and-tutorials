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
 * <em><b>Domain</b></em>', and utility methods for working with them. <!--
 * end-user-doc -->
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getDomain()
 * @model
 * @generated
 */
public enum Domain implements Enumerator {
    /**
     * The '<em><b>Other</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #OTHER_VALUE
     * @generated
     * @ordered
     */
    OTHER(0, "Other", "Other"),

    /**
     * The '<em><b>Living</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #LIVING_VALUE
     * @generated
     * @ordered
     */
    LIVING(1, "Living", "Living"),

    /**
     * The '<em><b>Comfort</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #COMFORT_VALUE
     * @generated
     * @ordered
     */
    COMFORT(2, "Comfort", "Comfort"),

    /**
     * The '<em><b>Energy</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #ENERGY_VALUE
     * @generated
     * @ordered
     */
    ENERGY(3, "Energy", "Energy");

    /**
     * The '<em><b>Other</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Other</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #OTHER
     * @model name="Other"
     * @generated
     * @ordered
     */
    public static final int OTHER_VALUE = 0;

    /**
     * The '<em><b>Living</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Living</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #LIVING
     * @model name="Living"
     * @generated
     * @ordered
     */
    public static final int LIVING_VALUE = 1;

    /**
     * The '<em><b>Comfort</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Comfort</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #COMFORT
     * @model name="Comfort"
     * @generated
     * @ordered
     */
    public static final int COMFORT_VALUE = 2;

    /**
     * The '<em><b>Energy</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Energy</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #ENERGY
     * @model name="Energy"
     * @generated
     * @ordered
     */
    public static final int ENERGY_VALUE = 3;

    /**
     * An array of all the '<em><b>Domain</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final Domain[] VALUES_ARRAY = new Domain[]{OTHER, LIVING, COMFORT,
            ENERGY,};

    /**
     * A public read-only list of all the '<em><b>Domain</b></em>' enumerators.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<Domain> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Domain</b></em>' literal with the specified literal
     * value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static Domain get(String literal) {
        for(int i = 0; i < VALUES_ARRAY.length; ++i) {
            Domain result = VALUES_ARRAY[i];
            if(result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Domain</b></em>' literal with the specified name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static Domain getByName(String name) {
        for(int i = 0; i < VALUES_ARRAY.length; ++i) {
            Domain result = VALUES_ARRAY[i];
            if(result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Domain</b></em>' literal with the specified integer
     * value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static Domain get(int value) {
        switch(value) {
            case OTHER_VALUE:
                return OTHER;
            case LIVING_VALUE:
                return LIVING;
            case COMFORT_VALUE:
                return COMFORT;
            case ENERGY_VALUE:
                return ENERGY;
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
    private Domain(int value, String name, String literal) {
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

} // Domain
