/**
 * Copyright (c) 2009 DAI-Labor.
 * All rights reserved.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Grzegorz Lehmann (DAI-Labor) - Initial API and implementation
 */
package org.sercho.masp.models.Context;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Lamp Type</b></em>', and utility methods for working with them. <!--
 * end-user-doc -->
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getLampType()
 * @model
 * @generated
 */
public enum LampType implements Enumerator {
    /**
     * The '<em><b>Unspecified</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #UNSPECIFIED_VALUE
     * @generated
     * @ordered
     */
    UNSPECIFIED(0, "Unspecified", "Unspecified"), /**
     * The '
     * <em><b>Ceiling Lamp</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #CEILING_LAMP_VALUE
     * @generated
     * @ordered
     */
    CEILING_LAMP(1, "CeilingLamp", "CeilingLamp"),

    /**
     * The '<em><b>Floor Lamp</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #FLOOR_LAMP_VALUE
     * @generated
     * @ordered
     */
    FLOOR_LAMP(2, "FloorLamp", "FloorLamp"),

    /**
     * The '<em><b>Table Lamp</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #TABLE_LAMP_VALUE
     * @generated
     * @ordered
     */
    TABLE_LAMP(3, "TableLamp", "TableLamp"),

    /**
     * The '<em><b>Wall Lamp</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #WALL_LAMP_VALUE
     * @generated
     * @ordered
     */
    WALL_LAMP(4, "WallLamp", "WallLamp"),

    /**
     * The '<em><b>Hanging Lamp</b></em>' literal object. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #HANGING_LAMP_VALUE
     * @generated
     * @ordered
     */
    HANGING_LAMP(5, "HangingLamp", "HangingLamp"),

    /**
     * The '<em><b>Device Lamp</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #DEVICE_LAMP_VALUE
     * @generated
     * @ordered
     */
    DEVICE_LAMP(6, "DeviceLamp", "DeviceLamp");

    /**
     * The '<em><b>Unspecified</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Unspecified</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #UNSPECIFIED
     * @model name="Unspecified"
     * @generated
     * @ordered
     */
    public static final int UNSPECIFIED_VALUE = 0;

    /**
     * The '<em><b>Ceiling Lamp</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Ceiling Lamp</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #CEILING_LAMP
     * @model name="CeilingLamp"
     * @generated
     * @ordered
     */
    public static final int CEILING_LAMP_VALUE = 1;

    /**
     * The '<em><b>Floor Lamp</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Floor Lamp</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #FLOOR_LAMP
     * @model name="FloorLamp"
     * @generated
     * @ordered
     */
    public static final int FLOOR_LAMP_VALUE = 2;

    /**
     * The '<em><b>Table Lamp</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Table Lamp</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #TABLE_LAMP
     * @model name="TableLamp"
     * @generated
     * @ordered
     */
    public static final int TABLE_LAMP_VALUE = 3;

    /**
     * The '<em><b>Wall Lamp</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Wall Lamp</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #WALL_LAMP
     * @model name="WallLamp"
     * @generated
     * @ordered
     */
    public static final int WALL_LAMP_VALUE = 4;

    /**
     * The '<em><b>Hanging Lamp</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Hanging Lamp</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #HANGING_LAMP
     * @model name="HangingLamp"
     * @generated
     * @ordered
     */
    public static final int HANGING_LAMP_VALUE = 5;

    /**
     * The '<em><b>Device Lamp</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Device Lamp</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #DEVICE_LAMP
     * @model name="DeviceLamp"
     * @generated
     * @ordered
     */
    public static final int DEVICE_LAMP_VALUE = 6;

    /**
     * An array of all the '<em><b>Lamp Type</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final LampType[] VALUES_ARRAY = new LampType[]{UNSPECIFIED,
            CEILING_LAMP, FLOOR_LAMP, TABLE_LAMP, WALL_LAMP, HANGING_LAMP, DEVICE_LAMP,};

    /**
     * A public read-only list of all the '<em><b>Lamp Type</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<LampType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Lamp Type</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static LampType get(String literal) {
        for(int i = 0; i < VALUES_ARRAY.length; ++i) {
            LampType result = VALUES_ARRAY[i];
            if(result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Lamp Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static LampType getByName(String name) {
        for(int i = 0; i < VALUES_ARRAY.length; ++i) {
            LampType result = VALUES_ARRAY[i];
            if(result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Lamp Type</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static LampType get(int value) {
        switch(value) {
            case UNSPECIFIED_VALUE:
                return UNSPECIFIED;
            case CEILING_LAMP_VALUE:
                return CEILING_LAMP;
            case FLOOR_LAMP_VALUE:
                return FLOOR_LAMP;
            case TABLE_LAMP_VALUE:
                return TABLE_LAMP;
            case WALL_LAMP_VALUE:
                return WALL_LAMP;
            case HANGING_LAMP_VALUE:
                return HANGING_LAMP;
            case DEVICE_LAMP_VALUE:
                return DEVICE_LAMP;
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
    private LampType(int value, String name, String literal) {
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

} // LampType
