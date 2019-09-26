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
 * <em><b>Provider</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.ContextProvider#getEnvironment <em>
 * Environment</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getContextProvider()
 * @model abstract="true"
 * @generated
 */
public interface ContextProvider extends EObject {

    /**
     * Returns the value of the '<em><b>Environment</b></em>' container
     * reference. It is bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.Environment#getProviders
     * <em>Providers</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Environment</em>' container reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Environment</em>' container reference.
     * @see #setEnvironment(Environment)
     * @see org.sercho.masp.models.Context.ContextPackage#getContextProvider_Environment()
     * @see org.sercho.masp.models.Context.Environment#getProviders
     * @model opposite="providers" transient="false"
     * @generated
     */
    Environment getEnvironment();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.ContextProvider#getEnvironment
     * <em>Environment</em>}' container reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Environment</em>' container
     *            reference.
     * @see #getEnvironment()
     * @generated
     */
    void setEnvironment(Environment value);
} // ContextProvider
