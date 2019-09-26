/**
 * File     PointingInputChannelAPIProxy.java
 * Package  org.sercho.masp.models.channel.api.proxy
 * Project  ContextModel
 * Date     Mar 13, 2008
 * Web      www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.models.channel.api.proxy;

import org.sercho.masp.models.UI.Pointing;
import org.sercho.masp.models.channel.api.PointingCallback;
import org.sercho.masp.models.channel.api.PointingInputChannelAPI;
import org.sercho.masp.util.comm.MethodStringParser;

/**
 * <code>PointingInputChannelAPIProxy</code> encapsulates a remotely working
 * {@link PointingInputChannelAPI}.
 * 
 * @author Grzegorz Lehmann
 */
public final class PointingInputChannelAPIProxy extends
        ChannelAPIProxy<Pointing, PointingCallback> implements PointingInputChannelAPI {

    private static final transient String METHOD_NAME_NEW_POINTING = "newPointing";

    private static final transient String METHOD_NAME_NEW_SIZE = "newSize";

    private static final transient String METHOD_NAME_NEW_POSITION = "newPosition";

    private static final transient MethodStringParser CALLBACK_NEW_POINTING = createCallbackParser(METHOD_NAME_NEW_POINTING);

    private static final transient MethodStringParser CALLBACK_NEW_SIZE = createCallbackParser(METHOD_NAME_NEW_SIZE);

    private static final transient MethodStringParser CALLBACK_NEW_POSITION = createCallbackParser(METHOD_NAME_NEW_POSITION);

    /**
     * <code>PointingInputChannelAPIProxy</code> constructor.
     */
    public PointingInputChannelAPIProxy() {
        // increased visibility
    }

    private static MethodStringParser createCallbackParser(final String methodName) {
        return new MethodStringParser(getMethod(methodName, PointingCallback.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MethodStringParser getCallbackMessageParser(final String methodName) {
        if(METHOD_NAME_NEW_POINTING.equals(methodName)) {
            return CALLBACK_NEW_POINTING;
        }
        if(METHOD_NAME_NEW_SIZE.equals(methodName)) {
            return CALLBACK_NEW_SIZE;
        }
        if(METHOD_NAME_NEW_POSITION.equals(methodName)) {
            return CALLBACK_NEW_POSITION;
        }
        throw new IllegalArgumentException("Failed to find parser for unknown method name: " + methodName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void close() {
        // nothing to do here
    }
}