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
 * <em><b>Distribution State</b></em>', and utility methods for working with
 * them. <!-- end-user-doc -->
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getDistributionState()
 * @model
 * @generated
 */
public enum DistributionState implements Enumerator {
    /**
     * The '<em><b>CALCULATING</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #CALCULATING_VALUE
     * @generated
     * @ordered
     */
    CALCULATING(0, "CALCULATING", "CALCULATING"),

    /**
     * The '<em><b>DONE</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #DONE_VALUE
     * @generated
     * @ordered
     */
    DONE(1, "DONE", "DONE");

    /**
     * The '<em><b>CALCULATING</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>CALCULATING</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #CALCULATING
     * @model
     * @generated
     * @ordered
     */
    public static final int CALCULATING_VALUE = 0;

    /**
     * The '<em><b>DONE</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>DONE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #DONE
     * @model
     * @generated
     * @ordered
     */
    public static final int DONE_VALUE = 1;

    /**
     * An array of all the '<em><b>Distribution State</b></em>' enumerators.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final DistributionState[] VALUES_ARRAY = new DistributionState[]{
            CALCULATING, DONE,};

    /**
     * A public read-only list of all the '<em><b>Distribution State</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<DistributionState> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Distribution State</b></em>' literal with the
     * specified literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static DistributionState get(String literal) {
        for(int i = 0; i < VALUES_ARRAY.length; ++i) {
            DistributionState result = VALUES_ARRAY[i];
            if(result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Distribution State</b></em>' literal with the
     * specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static DistributionState getByName(String name) {
        for(int i = 0; i < VALUES_ARRAY.length; ++i) {
            DistributionState result = VALUES_ARRAY[i];
            if(result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Distribution State</b></em>' literal with the
     * specified integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static DistributionState get(int value) {
        switch(value) {
            case CALCULATING_VALUE:
                return CALCULATING;
            case DONE_VALUE:
                return DONE;
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
    private DistributionState(int value, String name, String literal) {
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

} // DistributionState
