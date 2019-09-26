/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import org.eclipse.emf.common.util.EList;
import org.sercho.masp.models.UI.ConcreteInteractor;
import org.sercho.masp.models.channel.api.ChannelAPI;
import org.sercho.masp.models.channel.api.NotSupportedInteractorException;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Channel</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Channel#getDistributionState <em>
 * Distribution State</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Channel#getElements <em>Elements
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Channel#getApi <em>Api</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Channel#getConfiguration <em>
 * Configuration</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Channel#isAvailable <em>Available
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Channel#getApiClass <em>Api Class
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getChannel()
 * @model abstract="true" ABounds="org.sercho.masp.models.Context.ChannelAPI"
 *        annotation=
 *        "Proxy proxyReference='api' proxyClassName='apiClass' proxyConfiguration='configuration' proxySetter='setApiInstance' callbacks='setNewAvailable,interactionRequested,interactionFinished'"
 * @generated
 */
public interface Channel<C extends ConcreteInteractor, A extends ChannelAPI> extends
        EnvironmentElement {

    /**
     * Returns the value of the '<em><b>Distribution State</b></em>' attribute.
     * The literals are from the enumeration
     * {@link org.sercho.masp.models.Context.DistributionState}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Distribution State</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Distribution State</em>' attribute.
     * @see org.sercho.masp.models.Context.DistributionState
     * @see #setDistributionState(DistributionState)
     * @see org.sercho.masp.models.Context.ContextPackage#getChannel_DistributionState()
     * @model required="true"
     * @generated
     */
    DistributionState getDistributionState();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Channel#getDistributionState
     * <em>Distribution State</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Distribution State</em>' attribute.
     * @see org.sercho.masp.models.Context.DistributionState
     * @see #getDistributionState()
     * @generated
     */
    void setDistributionState(DistributionState value);

    /**
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Interaction Resource</em>' container reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @model kind="operation"
     * @generated
     */
    InteractionResource getInteractionResource();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    void interactionRequested();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    void interactionFinished();

    /**
     * Returns the value of the '<em><b>Elements</b></em>' reference list. The
     * list contents are of type {@link C}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Elements</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Elements</em>' reference list.
     * @see org.sercho.masp.models.Context.ContextPackage#getChannel_Elements()
     * @model
     * @generated
     */
    EList<C> getElements();

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
     * @see #setApi(ChannelAPI)
     * @see org.sercho.masp.models.Context.ContextPackage#getChannel_Api()
     * @model transient="true"
     * @generated
     */
    A getApi();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Channel#getApi <em>Api</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Api</em>' attribute.
     * @see #getApi()
     * @generated
     */
    void setApi(A value);

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
     * @see org.sercho.masp.models.Context.ContextPackage#getChannel_Configuration()
     * @model containment="true"
     * @generated
     */
    EList<ConfigurationProperty> getConfiguration();

    /**
     * Returns the value of the '<em><b>Available</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Available</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Available</em>' attribute.
     * @see #setAvailable(boolean)
     * @see org.sercho.masp.models.Context.ContextPackage#getChannel_Available()
     * @model
     * @generated
     */
    boolean isAvailable();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Channel#isAvailable
     * <em>Available</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Available</em>' attribute.
     * @see #isAvailable()
     * @generated
     */
    void setAvailable(boolean value);

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
     * @see org.sercho.masp.models.Context.ContextPackage#getChannel_ApiClass()
     * @model
     * @generated
     */
    String getApiClass();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Channel#getApiClass
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model newDistributionStateRequired="true"
     * @generated
     */
    void setNewDistributionState(DistributionState newDistributionState);

    /**
     * <!-- begin-user-doc --> <code>add</code> puts an element on this channel.
     * 
     * @param element
     *            element to put on this channel
     * @throws NotSupportedInteractorException
     *             if <code>element</code> is not supported by the channel API (
     *             {@link #getApi()}) of this channel
     * @throws IllegalStateException
     *             if the channel API ({@link #getApi()}) of this channel is not
     *             <code>null</code> <!-- end-user-doc -->
     * @model elementRequired="true"
     * @generated
     */
    void add(C element) throws NotSupportedInteractorException;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model elementIDRequired="true"
     * @generated
     */
    void remove(C elementID);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model newAvailableRequired="true"
     * @generated
     */
    void setNewAvailable(boolean newAvailable);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model newAPIRequired="true" annotation="Situation modifies='api'"
     * @generated
     */
    void setApiInstance(A newAPI);

} // Channel
