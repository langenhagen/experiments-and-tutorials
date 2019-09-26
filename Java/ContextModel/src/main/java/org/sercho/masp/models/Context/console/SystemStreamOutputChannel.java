/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.console;

import java.io.PrintStream;
import java.util.Map;

import org.sercho.masp.models.Context.ConfigurationPropertyUtility;
import org.sercho.masp.models.channel.api.AbstractMessageOutputChannelAPI;
import org.sercho.masp.models.channel.api.OneDimensionalCallback;

/**
 * <code>SystemStreamOutputChannel</code> allows sending messages to
 * {@link System#out} or {@link System#err} streams.
 * 
 * @author Grzegorz Lehmann
 * @since 1.1.3
 */
public final class SystemStreamOutputChannel extends AbstractMessageOutputChannelAPI {

    /**
     * <code>PROPERTY_STREAM_TYPE</code> denotes the name of the configuration
     * property holding the type of this channel's system output stream. This
     * can be one of {@value #STREAM_TYPE_OUT} and {@value #STREAM_TYPE_ERR}.
     */
    public static final transient String PROPERTY_STREAM_TYPE = "StreamType";

    /**
     * <code>STREAM_TYPE_OUT</code> identifies the {@link System#out} stream
     * type.
     */
    public static final transient String STREAM_TYPE_OUT = "out";

    /**
     * <code>STREAM_TYPE_ERR</code> identifies the {@link System#err} stream
     * type.
     */
    public static final transient String STREAM_TYPE_ERR = "err";

    private PrintStream out;

    /**
     * @generated NOT
     */
    private synchronized void printOutput() {
        this.out.println("-------------------------------------------");
        for(final Message message : this.messages.values()) {
            if(message.getState() == ElementState.FOCUSED) {
                this.out.print("[FOCUS] ");
            }
            this.out.println(message.text);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setCallback(final OneDimensionalCallback newCallback) {
        // nothing to do here
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void activated(final String elementID, final String text) {
        printOutput();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void focused(final String elementID, final String text) {
        printOutput();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void removed(final String elementID) {
        printOutput();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void selected(final String elementID, final String text) {
        printOutput();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected synchronized void close() {
        this.out = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected synchronized void startHook(final Map<String, String> configurationMap) {
        this.out = STREAM_TYPE_ERR.equals(ConfigurationPropertyUtility.getPropertyValue(configurationMap, this, PROPERTY_STREAM_TYPE)) ? System.err
                : System.out;
    }
}