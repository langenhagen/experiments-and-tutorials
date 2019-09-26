/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Configuration Property</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.ConfigurationProperty#getKey <em>
 * Key</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.ConfigurationProperty#getValue <em>
 * Value</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getConfigurationProperty()
 * @model
 * @generated
 */
public interface ConfigurationProperty extends EObject {

    /**
     * Returns the value of the '<em><b>Key</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Key</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Key</em>' attribute.
     * @see #setKey(String)
     * @see org.sercho.masp.models.Context.ContextPackage#getConfigurationProperty_Key()
     * @model
     * @generated
     */
    String getKey();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.ConfigurationProperty#getKey
     * <em>Key</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Key</em>' attribute.
     * @see #getKey()
     * @generated
     */
    void setKey(String value);

    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(String)
     * @see org.sercho.masp.models.Context.ContextPackage#getConfigurationProperty_Value()
     * @model
     * @generated
     */
    String getValue();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.ConfigurationProperty#getValue
     * <em>Value</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue(String value);

} // ConfigurationProperty
