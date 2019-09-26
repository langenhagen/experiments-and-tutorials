/**
 * File     AbstractMessageOutputChannelAPI.java
 * Package  org.sercho.masp.models.channel.api
 * Project  ContextModel
 * Date     15.10.2007
 * Web      http://www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.models.channel.api;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * <code>AbstractMessageOutputChannelAPI</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 */
@ThreadSafe
public abstract class AbstractMessageOutputChannelAPI extends AbstractOutputChannelAPI
        implements MessageOutputChannelAPI {

    /**
     * <code>Message</code> represents a message to display on the channel.
     * 
     * @author Grzegorz Lehmann
     */
    @ThreadSafe
    protected static final class Message implements Serializable {

        /**
         * <code>serialVersionUID</code>
         */
        private static final long serialVersionUID = -2128395348688160046L;

        public final String id;

        public volatile String text;

        @GuardedBy("this")
        private ElementState state = ElementState.INACTIVE;

        /**
         * <code>Message</code> constructor.
         * 
         * @param elementID
         *            ID of this message
         * @param messageText
         *            text of this message
         */
        Message(final String elementID, final String messageText) {
            this.id = elementID;
            this.text = messageText;
        }

        /**
         * <code>setState</code> this atomic setter allows to set a new state to
         * this message. The setter returns a boolean depending on whether the
         * state actually changed or already had the desired value.
         * 
         * @param newState
         *            new state to set
         * @return boolean - <code>true</code> if state of this message changed
         *         after the call of this setter, otherwise <code>false</code>
         */
        synchronized boolean setState(final ElementState newState) {
            if(this.state == newState) {
                return false;
            }
            this.state = newState;
            return true;
        }

        public synchronized ElementState getState() {
            return this.state;
        }
    }

    @GuardedBy("this")
    private final Map<String, Message> modifiableMessages = new HashMap<String, Message>();

    /**
     * <code>messages</code> is an unmodifiable map holding messages currently
     * on this channel.
     */
    protected final Map<String, Message> messages = Collections.unmodifiableMap(this.modifiableMessages);

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized final void activate(final String elementID) {
        if(elementID == null) {
            throw new IllegalArgumentException("elementID is null");
        }
        final Message message = this.modifiableMessages.get(elementID);
        if(message == null) {
            throw new IllegalArgumentException("No element exists with ID " + elementID);
        }
        if(message.setState(ElementState.ACTIVE)) {
            activated(elementID, message.text);
        }
    }

    /**
     * <code>activated</code> informs subtypes about a successfull
     * {@link #activate(String)} call.
     * 
     * @param elementID
     *            identifier of the activated message
     * @param text
     *            text of the activated message
     */
    protected abstract void activated(String elementID, String text);

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized final void focus(final String elementID) {
        if(elementID == null) {
            throw new IllegalArgumentException("elementID is null");
        }
        final Message message = this.modifiableMessages.get(elementID);
        if(message == null) {
            throw new IllegalArgumentException("No element exists with ID " + elementID);
        }
        if(message.setState(ElementState.FOCUSED)) {
            focused(elementID, message.text);
        }
    }

    /**
     * <code>focused</code> informs subtypes about a successfull
     * {@link #focus(String)} call.
     * 
     * @param elementID
     *            identifier of the focused message
     * @param text
     *            text of the focused message
     */
    protected abstract void focused(String elementID, String text);

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized final void select(final String elementID) {
        if(elementID == null) {
            throw new IllegalArgumentException("elementID is null");
        }
        final Message message = this.modifiableMessages.get(elementID);
        if(message == null) {
            throw new IllegalArgumentException("No element exists with ID " + elementID);
        }
        if(message.setState(ElementState.SELECTED)) {
            selected(elementID, message.text);
        }
    }

    /**
     * <code>selected</code> informs subtypes about a successfull
     * {@link #select(String)} call.
     * 
     * @param elementID
     *            identifier of the selected message
     * @param text
     *            text of the selected message
     */
    protected abstract void selected(String elementID, String text);

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized final void set(final String elementID, final String message) {
        if(elementID == null) {
            throw new IllegalArgumentException("elementID is null");
        }
        if(message == null) {
            throw new IllegalArgumentException("message is null");
        }
        this.modifiableMessages.put(elementID, new Message(elementID, message));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized final void remove(final String elementID) {
        if(elementID == null) {
            throw new IllegalArgumentException("elementID is null");
        }
        final Message message = this.modifiableMessages.remove(elementID);
        if(message == null) {
            throw new IllegalArgumentException("No element exists with ID " + elementID);
        }
        removed(elementID);
    }

    /**
     * <code>removed</code> informs subtypes about a successfull
     * {@link #remove(String)} call.
     * 
     * @param elementID
     *            identifier of the removed message
     * @param text
     *            text of the removed message
     */
    protected abstract void removed(String elementID);
}