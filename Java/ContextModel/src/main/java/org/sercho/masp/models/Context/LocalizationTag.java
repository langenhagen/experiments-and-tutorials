/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Localization Tag</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> LocalizationTag represents a physical tag, which can
 * be attached to environment elements and enables the localization of this
 * element in the environment. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.LocalizationTag#isDetected <em>
 * Detected</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.LocalizationTag#isRegister <em>
 * Register</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.LocalizationTag#getProvider <em>
 * Provider</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.LocalizationTag#getElement <em>
 * Element</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getLocalizationTag()
 * @model
 * @generated
 */
public interface LocalizationTag extends ElementWithPosition {

    /**
     * Returns the value of the '<em><b>Detected</b></em>' attribute. The
     * default value is <code>"false"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Detected</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Detected</em>' attribute.
     * @see #setDetected(boolean)
     * @see org.sercho.masp.models.Context.ContextPackage#getLocalizationTag_Detected()
     * @model default="false" required="true"
     * @generated
     */
    boolean isDetected();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.LocalizationTag#isDetected
     * <em>Detected</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Detected</em>' attribute.
     * @see #isDetected()
     * @generated
     */
    void setDetected(boolean value);

    /**
     * Returns the value of the '<em><b>Provider</b></em>' container reference.
     * It is bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.LocalisationProviderProxy#getTags
     * <em>Tags</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Provider</em>' container reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Provider</em>' container reference.
     * @see #setProvider(LocalisationProviderProxy)
     * @see org.sercho.masp.models.Context.ContextPackage#getLocalizationTag_Provider()
     * @see org.sercho.masp.models.Context.LocalisationProviderProxy#getTags
     * @model opposite="tags" required="true" transient="false"
     * @generated
     */
    LocalisationProviderProxy getProvider();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.LocalizationTag#getProvider
     * <em>Provider</em>}' container reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Provider</em>' container reference.
     * @see #getProvider()
     * @generated
     */
    void setProvider(LocalisationProviderProxy value);

    /**
     * Returns the value of the '<em><b>Register</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Register</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Register</em>' attribute.
     * @see #setRegister(boolean)
     * @see org.sercho.masp.models.Context.ContextPackage#getLocalizationTag_Register()
     * @model required="true"
     * @generated
     */
    boolean isRegister();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.LocalizationTag#isRegister
     * <em>Register</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Register</em>' attribute.
     * @see #isRegister()
     * @generated
     */
    void setRegister(boolean value);

    /**
     * Returns the value of the '<em><b>Element</b></em>' reference. It is
     * bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.ElementWithPosition#getTags
     * <em>Tags</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Element</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Element</em>' reference.
     * @see #setElement(ElementWithPosition)
     * @see org.sercho.masp.models.Context.ContextPackage#getLocalizationTag_Element()
     * @see org.sercho.masp.models.Context.ElementWithPosition#getTags
     * @model opposite="tags"
     * @generated
     */
    ElementWithPosition getElement();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.LocalizationTag#getElement
     * <em>Element</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Element</em>' reference.
     * @see #getElement()
     * @generated
     */
    void setElement(ElementWithPosition value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    void register();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    void unregister();

} // LocalizationTag
