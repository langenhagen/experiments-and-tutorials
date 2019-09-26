/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Room</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.Room#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getRoom()
 * @model
 * @generated
 */
public interface Room extends Place {

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute. The default
     * value is <code>"0"</code>. The literals are from the enumeration
     * {@link org.sercho.masp.models.Context.RoomType}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Type</em>' attribute.
     * @see org.sercho.masp.models.Context.RoomType
     * @see #setType(RoomType)
     * @see org.sercho.masp.models.Context.ContextPackage#getRoom_Type()
     * @model default="0"
     * @generated
     */
    RoomType getType();

    /**
     * Sets the value of the '
     * {@link org.sercho.masp.models.Context.Room#getType <em>Type</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Type</em>' attribute.
     * @see org.sercho.masp.models.Context.RoomType
     * @see #getType()
     * @generated
     */
    void setType(RoomType value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model newRoomTypeRequired="true" annotation="Definition modifies='type'"
     * @generated
     */
    void setRoomType(RoomType newRoomType);

} // Room
