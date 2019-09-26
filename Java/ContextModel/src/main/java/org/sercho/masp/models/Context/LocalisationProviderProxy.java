/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import org.eclipse.emf.common.util.EList;
import org.sercho.masp.context.providers.location.LocalisationProvider;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Localisation Provider Proxy</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.LocalisationProviderProxy#getApiClass
 * <em>Api Class</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.LocalisationProviderProxy#getApi
 * <em>Api</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.LocalisationProviderProxy#getTags
 * <em>Tags</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.LocalisationProviderProxy#getConfiguration
 * <em>Configuration</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getLocalisationProviderProxy()
 * @model annotation=
 *        "Proxy proxyReference='api' proxyClassName='apiClass' proxySetter='setApiInstance' proxyConfiguration='configuration' callbacks='newPosition,tagGone'"
 * @generated
 */
public interface LocalisationProviderProxy extends ContextProvider {

    /**
     * Returns the value of the '<em><b>Api Class</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Api Class</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Api Class</em>' attribute.
     * @see #setApiClass(String)
     * @see org.sercho.masp.models.Context.ContextPackage#getLocalisationProviderProxy_ApiClass()
     * @model required="true"
     * @generated
     */
    String getApiClass();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.LocalisationProviderProxy#getApiClass
     * <em>Api Class</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Api Class</em>' attribute.
     * @see #getApiClass()
     * @generated
     */
    void setApiClass(String value);

    /**
     * Returns the value of the '<em><b>Api</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Api</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Api</em>' attribute.
     * @see #setApi(LocalisationProvider)
     * @see org.sercho.masp.models.Context.ContextPackage#getLocalisationProviderProxy_Api()
     * @model dataType="org.sercho.masp.models.Context.LocalisationProvider"
     *        transient="true"
     * @generated
     */
    LocalisationProvider getApi();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.LocalisationProviderProxy#getApi
     * <em>Api</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Api</em>' attribute.
     * @see #getApi()
     * @generated
     */
    void setApi(LocalisationProvider value);

    /**
     * Returns the value of the '<em><b>Tags</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.sercho.masp.models.Context.LocalizationTag}. It is
     * bidirectional and its opposite is '
     * {@link org.sercho.masp.models.Context.LocalizationTag#getProvider
     * <em>Provider</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tags</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Tags</em>' containment reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getLocalisationProviderProxy_Tags()
     * @see org.sercho.masp.models.Context.LocalizationTag#getProvider
     * @model opposite="provider" containment="true"
     * @generated
     */
    EList<LocalizationTag> getTags();

    /**
     * Returns the value of the '<em><b>Configuration</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.sercho.masp.models.Context.ConfigurationProperty}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Configuration</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Configuration</em>' containment reference
     *         list.
     * @see org.sercho.masp.models.Context.ContextPackage#getLocalisationProviderProxy_Configuration()
     * @model containment="true"
     * @generated
     */
    EList<ConfigurationProperty> getConfiguration();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model unique="false"
     * @generated
     */
    boolean register(String id);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model unique="false"
     * @generated
     */
    Vector getPosition(String id);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    void unregister(String id);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model tagIDRequired="true" xRequired="true" yRequired="true"
     *        zRequired="true"
     * @generated
     */
    void newPosition(String tagID, double x, double y, double z);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model tagIDRequired="true"
     * @generated
     */
    void tagGone(String tagID);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model apiInstanceDataType=
     *        "org.sercho.masp.models.Context.LocalisationProvider"
     *        apiInstanceRequired="true" annotation="Situation modifies='api'"
     * @generated
     */
    void setApiInstance(LocalisationProvider apiInstance);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Method is used to add LocalizationTags to the LocalisationProviderProxy.
     * If the LocalizationTag has no id the id argument must not be null. <!--
     * end-model-doc -->
     * 
     * @model tagRequired="true" idRequired="true"
     *        annotation="Definition modifies='tags'"
     * @generated
     */
    void addTag(LocalizationTag tag, String id);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model tagRequired="true" annotation="Definition modifies='tags'"
     * @generated
     */
    void removeTag(LocalizationTag tag);

} // LocalisationProviderProxy
