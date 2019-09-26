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
 * <em><b>Modality</b></em>', and utility methods for working with them. <!--
 * end-user-doc -->
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getModality()
 * @model
 * @generated
 */
public enum Modality implements Enumerator {
    /**
     * The '<em><b>Vocal</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #VOCAL_VALUE
     * @generated
     * @ordered
     */
    VOCAL(0, "Vocal", "Vocal"), /**
     * The '<em><b>Graphic</b></em>' literal object.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #GRAPHIC_VALUE
     * @generated
     * @ordered
     */
    GRAPHIC(1, "Graphic", "Graphic"), /**
     * The '<em><b>Haptic</b></em>' literal
     * object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #HAPTIC_VALUE
     * @generated
     * @ordered
     */
    HAPTIC(2, "Haptic", "Haptic"), /**
     * The '<em><b>Audio</b></em>' literal
     * object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #AUDIO_VALUE
     * @generated
     * @ordered
     */
    AUDIO(3, "Audio", "Audio");

    /**
     * The '<em><b>Vocal</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Vocal</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #VOCAL
     * @model name="Vocal"
     * @generated
     * @ordered
     */
    public static final int VOCAL_VALUE = 0;

    /**
     * The '<em><b>Graphic</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Graphic</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #GRAPHIC
     * @model name="Graphic"
     * @generated
     * @ordered
     */
    public static final int GRAPHIC_VALUE = 1;

    /**
     * The '<em><b>Haptic</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Haptic</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #HAPTIC
     * @model name="Haptic"
     * @generated
     * @ordered
     */
    public static final int HAPTIC_VALUE = 2;

    /**
     * The '<em><b>Audio</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Audio</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #AUDIO
     * @model name="Audio"
     * @generated
     * @ordered
     */
    public static final int AUDIO_VALUE = 3;

    /**
     * An array of all the '<em><b>Modality</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final Modality[] VALUES_ARRAY = new Modality[]{VOCAL, GRAPHIC, HAPTIC,
            AUDIO,};

    /**
     * A public read-only list of all the '<em><b>Modality</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<Modality> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Modality</b></em>' literal with the specified literal
     * value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static Modality get(String literal) {
        for(int i = 0; i < VALUES_ARRAY.length; ++i) {
            Modality result = VALUES_ARRAY[i];
            if(result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Modality</b></em>' literal with the specified name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static Modality getByName(String name) {
        for(int i = 0; i < VALUES_ARRAY.length; ++i) {
            Modality result = VALUES_ARRAY[i];
            if(result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Modality</b></em>' literal with the specified integer
     * value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static Modality get(int value) {
        switch(value) {
            case VOCAL_VALUE:
                return VOCAL;
            case GRAPHIC_VALUE:
                return GRAPHIC;
            case HAPTIC_VALUE:
                return HAPTIC;
            case AUDIO_VALUE:
                return AUDIO;
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
    private Modality(int value, String name, String literal) {
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

} // Modality
