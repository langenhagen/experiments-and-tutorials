/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Display</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Display#getXPixels <em>XPixels
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Display#getYPixels <em>YPixels
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Display#getDirection <em>Direction
 * </em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Display#getScreen <em>Screen</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.Display#getSize <em>Size</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getDisplay()
 * @model
 * @generated
 */
public interface Display extends OutputInteractionResource {

    /**
     * Returns the value of the '<em><b>XPixels</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>XPixels</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>XPixels</em>' attribute.
     * @see #setXPixels(int)
     * @see org.sercho.masp.models.Context.ContextPackage#getDisplay_XPixels()
     * @model
     * @generated
     */
    int getXPixels();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Display#getXPixels
     * <em>XPixels</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>XPixels</em>' attribute.
     * @see #getXPixels()
     * @generated
     */
    void setXPixels(int value);

    /**
     * Returns the value of the '<em><b>YPixels</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>YPixels</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>YPixels</em>' attribute.
     * @see #setYPixels(int)
     * @see org.sercho.masp.models.Context.ContextPackage#getDisplay_YPixels()
     * @model
     * @generated
     */
    int getYPixels();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Display#getYPixels
     * <em>YPixels</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>YPixels</em>' attribute.
     * @see #getYPixels()
     * @generated
     */
    void setYPixels(int value);

    /**
     * Returns the value of the '<em><b>Direction</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Direction</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Direction</em>' containment reference.
     * @see #setDirection(Vector)
     * @see org.sercho.masp.models.Context.ContextPackage#getDisplay_Direction()
     * @model containment="true"
     * @generated
     */
    Vector getDirection();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Display#getDirection
     * <em>Direction</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Direction</em>' containment
     *            reference.
     * @see #getDirection()
     * @generated
     */
    void setDirection(Vector value);

    /**
     * Returns the value of the '<em><b>Screen</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Screen</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Screen</em>' containment reference.
     * @see #setScreen(GraphicalOutputChannel)
     * @see org.sercho.masp.models.Context.ContextPackage#getDisplay_Screen()
     * @model containment="true"
     * @generated
     */
    GraphicalOutputChannel getScreen();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Display#getScreen <em>Screen</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Screen</em>' containment reference.
     * @see #getScreen()
     * @generated
     */
    void setScreen(GraphicalOutputChannel value);

    /**
     * Returns the value of the '<em><b>Size</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Size</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Size</em>' attribute.
     * @see #setSize(int)
     * @see org.sercho.masp.models.Context.ContextPackage#getDisplay_Size()
     * @model
     * @generated
     */
    int getSize();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Display#getSize <em>Size</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Size</em>' attribute.
     * @see #getSize()
     * @generated
     */
    void setSize(int value);

} // Display
