/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import org.sercho.masp.models.UI.Pointing;
import org.sercho.masp.models.UI.TwoDimensional;
import org.sercho.masp.models.channel.api.PointingCallback;
import org.sercho.masp.models.channel.api.PointingInputChannelAPI;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Pointing Input Channel</b></em>'. <!-- end-user-doc -->
 * 
 * 
 * @see org.sercho.masp.models.Context.ContextPackage#getPointingInputChannel()
 * @model abstract="true"
 * @generated NOT
 */
public interface PointingInputChannel extends Channel<Pointing, PointingInputChannelAPI>,
        TwoDimensional, PointingCallback {

} // PointingInputChannel
