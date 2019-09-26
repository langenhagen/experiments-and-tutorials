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
 * <em><b>Room Type</b></em>', and utility methods for working with them. <!--
 * end-user-doc -->
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getRoomType()
 * @model
 * @generated
 */
public enum RoomType implements Enumerator {
    /**
     * The '<em><b>Kitchen</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #KITCHEN_VALUE
     * @generated
     * @ordered
     */
    KITCHEN(0, "Kitchen", "Kitchen"),

    /**
     * The '<em><b>Bath</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #BATH_VALUE
     * @generated
     * @ordered
     */
    BATH(1, "Bath", "Bath"),

    /**
     * The '<em><b>Bedroom</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #BEDROOM_VALUE
     * @generated
     * @ordered
     */
    BEDROOM(2, "Bedroom", "Bedroom"),

    /**
     * The '<em><b>Living Room</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #LIVING_ROOM_VALUE
     * @generated
     * @ordered
     */
    LIVING_ROOM(3, "LivingRoom", "Living Room"), /**
     * The '
     * <em><b>Fitness Room</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #FITNESS_ROOM_VALUE
     * @generated
     * @ordered
     */
    FITNESS_ROOM(4, "FitnessRoom", "Fitness Room"), /**
     * The '
     * <em><b>Office</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #OFFICE_VALUE
     * @generated
     * @ordered
     */
    OFFICE(5, "Office", "Office");

    /**
     * The '<em><b>Kitchen</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Kitchen</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #KITCHEN
     * @model name="Kitchen"
     * @generated
     * @ordered
     */
    public static final int KITCHEN_VALUE = 0;

    /**
     * The '<em><b>Bath</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Bath</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #BATH
     * @model name="Bath"
     * @generated
     * @ordered
     */
    public static final int BATH_VALUE = 1;

    /**
     * The '<em><b>Bedroom</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Bedroom</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #BEDROOM
     * @model name="Bedroom"
     * @generated
     * @ordered
     */
    public static final int BEDROOM_VALUE = 2;

    /**
     * The '<em><b>Living Room</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Living Room</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #LIVING_ROOM
     * @model name="LivingRoom" literal="Living Room"
     * @generated
     * @ordered
     */
    public static final int LIVING_ROOM_VALUE = 3;

    /**
     * The '<em><b>Fitness Room</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Fitness Room</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #FITNESS_ROOM
     * @model name="FitnessRoom" literal="Fitness Room"
     * @generated
     * @ordered
     */
    public static final int FITNESS_ROOM_VALUE = 4;

    /**
     * The '<em><b>Office</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Office</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #OFFICE
     * @model name="Office"
     * @generated
     * @ordered
     */
    public static final int OFFICE_VALUE = 5;

    /**
     * An array of all the '<em><b>Room Type</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final RoomType[] VALUES_ARRAY = new RoomType[]{KITCHEN, BATH, BEDROOM,
            LIVING_ROOM, FITNESS_ROOM, OFFICE,};

    /**
     * A public read-only list of all the '<em><b>Room Type</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<RoomType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Room Type</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static RoomType get(String literal) {
        for(int i = 0; i < VALUES_ARRAY.length; ++i) {
            RoomType result = VALUES_ARRAY[i];
            if(result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Room Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static RoomType getByName(String name) {
        for(int i = 0; i < VALUES_ARRAY.length; ++i) {
            RoomType result = VALUES_ARRAY[i];
            if(result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Room Type</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static RoomType get(int value) {
        switch(value) {
            case KITCHEN_VALUE:
                return KITCHEN;
            case BATH_VALUE:
                return BATH;
            case BEDROOM_VALUE:
                return BEDROOM;
            case LIVING_ROOM_VALUE:
                return LIVING_ROOM;
            case FITNESS_ROOM_VALUE:
                return FITNESS_ROOM;
            case OFFICE_VALUE:
                return OFFICE;
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
    private RoomType(int value, String name, String literal) {
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

} // RoomType
