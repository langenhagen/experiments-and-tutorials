/**
 * 
 */
package org.sercho.masp.models.channel.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.SingletonAdapterImpl;
import org.sercho.masp.models.Context.MessageOutputChannel;
import org.sercho.masp.models.UI.InteractorState;
import org.sercho.masp.models.UI.MessageOutput;
import org.sercho.masp.models.UI.UIPackage;

/**
 * <code>MessageOutputAdapter</code> is used to handle {@link MessageOutput}s of
 * a {@link MessageOutputChannel}. This adapter observes the messages and calls
 * a {@link MessageOutputChannelAPI} appropriately. Therefore the
 * {@link MessageOutputChannel} no longer has to handle the message but only
 * tell this adapter when an element has been added or removed.
 * 
 * @author Grzegorz Lehmann
 */
public final class MessageOutputAdapter extends SingletonAdapterImpl {

    private static final transient Log LOG = LogFactory.getLog(MessageOutputAdapter.class);

    private final MessageOutputChannelAPI channelAPI;

    /**
     * <code>GraphicalOutputAdapter</code> constructor.
     * 
     * @param api
     *            channel API to use
     */
    public MessageOutputAdapter(final MessageOutputChannelAPI api) {
        if(api == null) {
            throw new IllegalArgumentException("api is null");
        }
        this.channelAPI = api;
    }

    /**
     * <code>messageOutputAdded</code> informs this adapter about a new
     * {@link MessageOutput} added to a channel.
     * 
     * @param messageOutput
     *            new message
     */
    public synchronized void messageOutputAdded(final MessageOutput messageOutput) {
        // observe the message
        messageOutput.eAdapters().add(this);
        // try to put message on channel (set method check if the message is
        // okay)
        set(messageOutput);
    }

    /**
     * <code>messageOutputRemoved</code> informs this adapter about a
     * {@link MessageOutput} that has been removed from the channel.
     * 
     * @param messageOutput
     *            removed message
     */
    public synchronized void messageOutputRemoved(final MessageOutput messageOutput) {
        // no longer observe the message
        messageOutput.eAdapters().remove(this);
        // if the message is on channel, remove it
        final InteractorState state = messageOutput.getState();
        if(state == null || state == InteractorState.INACTIVE) {
            // nothing to do here
            return;
        }
        // remove the message from channel
        this.channelAPI.remove(messageOutput.getId());
    }

    /**
     * <code>set</code> calls
     * {@link MessageOutputChannelAPI#set(String, String)} if everything is okay
     * with a {@link MessageOutput}.
     * 
     * @param messageOutput
     *            message to set on {@link #channelAPI}
     */
    private void set(final MessageOutput messageOutput) {
        if(messageOutput == null) {
            throw new IllegalArgumentException("messageOutput is null");
        }
        // check the state of the message
        final InteractorState state = messageOutput.getState();
        if(state == null || state == InteractorState.INACTIVE) {
            // nothing to do here
            return;
        }
        final String elementID = messageOutput.getId();
        if(elementID == null) {
            // nothing to do here
            LOG.warn("Got MessageOutput with ID null: " + messageOutput);
            return;
        }
        final String message = messageOutput.getMessage();
        if(message == null) {
            // nothing to do here
            LOG.warn("Got MessageOutput with message null: " + messageOutput);
            return;
        }
        // the message is either active, focused or selected so it must be put
        // on channel
        this.channelAPI.set(elementID, message);
        // activate the message so the channel displays it
        this.channelAPI.activate(elementID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyChanged(final Notification msg) {
        if(UIPackage.Literals.INTERACTOR__STATE == msg.getFeature()) {
            stateChanged((MessageOutput)msg.getNotifier(), (InteractorState)msg.getOldValue(), (InteractorState)msg.getNewValue());
        } else if(UIPackage.Literals.MESSAGE_OUTPUT__MESSAGE == msg.getFeature()) {
            set((MessageOutput)msg.getNotifier());
        } else if(UIPackage.Literals.INTERACTOR__ID == msg.getFeature()) {
            // this will probably never happen, but should be implemented just
            // in case...
            final MessageOutput messageOutput = (MessageOutput)msg.getNotifier();
            // check the state of the message
            final InteractorState state = messageOutput.getState();
            if(state == null || state == InteractorState.INACTIVE) {
                // nothing to do here
                return;
            }
            // message is active, focused or selected so it is on the channel
            final String oldID = msg.getOldStringValue();
            if(oldID != null) {
                // remove message with old id from channel
                this.channelAPI.remove(oldID);
            }
            // add the same message with new id
            set(messageOutput);
        }
    }

    /**
     * <code>stateChanged</code> reacts to a state change of a
     * {@link MessageOutput}.
     * 
     * @param messageOutput
     *            message in which the state changed
     * @param oldState
     *            old state of the message
     * @param newState
     *            new state of the message
     */
    private synchronized void stateChanged(final MessageOutput messageOutput, final InteractorState oldState, final InteractorState newState) {
        final String elementID = messageOutput.getId();
        if(elementID == null) {
            LOG.warn("Got notification about interactor with Id null: " + messageOutput);
            return;
        }
        switch(newState) {
            case INACTIVE:
                // remove inactive messages from the channel
                this.channelAPI.remove(elementID);
                break;
            case ACTIVE:
                // activate the message on the channel
                if(oldState == InteractorState.INACTIVE) {
                    // message not yet added
                    set(messageOutput);
                }
                // activate
                this.channelAPI.activate(elementID);
                break;
            case FOCUSED:
                // focus the message
                this.channelAPI.focus(elementID);
                break;
            case SELECTED:
                // select the message
                this.channelAPI.select(elementID);
                break;
            default:
                break;
        }
    }
}