/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import org.sercho.masp.models.UI.MessageInput;
import org.sercho.masp.models.UI.OneDimensional;
import org.sercho.masp.models.channel.api.MessageCallback;
import org.sercho.masp.models.channel.api.MessageInputChannelAPI;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Message Input Channel</b></em>'. <!-- end-user-doc -->
 * 
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getMessageInputChannel()
 * @model abstract="true"
 * @generated NOT
 */
public interface MessageInputChannel extends
        Channel<MessageInput, MessageInputChannelAPI>, OneDimensional, MessageCallback {

} // MessageInputChannel
