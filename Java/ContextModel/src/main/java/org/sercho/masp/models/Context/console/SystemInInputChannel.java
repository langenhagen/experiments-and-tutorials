/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.MessageInputChannel;
import org.sercho.masp.models.UI.Command;
import org.sercho.masp.models.UI.MessageOutput;
import org.sercho.masp.models.UI.PredefinedMessage;
import org.sercho.masp.models.UI.UIFactory;
import org.sercho.masp.models.UI.UIModel;
import org.sercho.masp.models.UI.UIPackage;
import org.sercho.masp.models.channel.api.AbstractMessageInputChannelAPI;

import de.dailab.masp.models.MetaMetaModel.AlreadyStartedException;

/**
 * <code>SystemInInputChannel</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 */
public final class SystemInInputChannel extends AbstractMessageInputChannelAPI {

    static final transient Log LOG = LogFactory.getLog(SystemInInputChannel.class);

    /**
     * <code>main</code> for tests
     * 
     * @param args
     *            program arguments, ignored
     */
    public static void main(final String[] args) {
        final UIFactory uiFactory = UIPackage.eINSTANCE.getUIFactory();
        final UIModel uiModel = uiFactory.createUIModel();

        final Command navigation = uiFactory.createCommand();
        uiModel.getAbstractInteractors().add(navigation);
        final PredefinedMessage bye = uiFactory.createPredefinedMessage();
        bye.setMessage("bye");
        final PredefinedMessage goodbye = uiFactory.createPredefinedMessage();
        goodbye.setMessage("goodbye");
        navigation.getExecuteInput().add(bye);
        navigation.getExecuteInput().add(goodbye);
        final MessageOutput byeSignal = uiFactory.createMessageOutput();
        byeSignal.setMessage("Goodbye user!");
        navigation.getMessageOutput().add(byeSignal);
        navigation.activate();

        final Command navigation2 = uiFactory.createCommand();
        uiModel.getAbstractInteractors().add(navigation2);
        final PredefinedMessage ok = uiFactory.createPredefinedMessage();
        ok.setMessage("ok");
        final PredefinedMessage yes = uiFactory.createPredefinedMessage();
        yes.setMessage("yes");
        navigation2.getExecuteInput().add(ok);
        navigation2.getExecuteInput().add(yes);
        final MessageOutput okSignal = uiFactory.createMessageOutput();
        okSignal.setMessage("Please confirm!");
        navigation2.getMessageOutput().add(okSignal);
        navigation2.activate();

        final ContextFactory factory = ContextPackage.eINSTANCE.getContextFactory();
        final MessageInputChannel in = factory.createMessageInputChannel();

        final SystemInInputChannel channel = new SystemInInputChannel();
        channel.startHook(null);
        in.setApi(channel);
        // final MessageOutputChannel out =
        // factory.createMessageOutputChannel();

        // out.setApi(new SystemStreamOutputChannel(System.out));

        in.add(bye);
        in.add(goodbye);
        in.add(ok);
        in.add(yes);
        // out.add(byeSignal);
        // out.add(okSignal);

        try {
            Thread.sleep(15000);
        }
        catch(final InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Stopping");
        channel.stopHook();
        System.out.println("Stopped");
    }

    private Thread readThread;

    /**
     * {@inheritDoc}
     */
    @Override
    protected synchronized void startHook(final Map<String, String> configurationMap) {
        if(this.readThread != null) {
            throw new AlreadyStartedException(this);
        }
        this.running = true;
        // start read thread
        this.readThread = new Thread("SystemInInputChannel Thread") {

            /**
             * {@inheritDoc}
             */
            @Override
            public void run() {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    final StringBuffer buffer = new StringBuffer();
                    char character;
                    while(SystemInInputChannel.this.running) {
                        if(reader.ready()) {
                            character = (char)reader.read();
                            if(character == -1) {
                                // end of stream reached
                                break;
                            }
                            if(character == '\n' || character == '\r') {
                                // end of line reached, send message
                                if(buffer.length() > 0) {
                                    newMessage(buffer.toString());
                                }
                                // reset buffer
                                buffer.setLength(0);
                            } else {
                                // append to buffer and wait for end of line
                                buffer.append(character);
                            }
                        }
                        // no input available, sleep
                        else {
                            try {
                                sleep(100);
                            }
                            catch(final InterruptedException e) {
                                // interrupted, check the loop
                            }
                        }
                    }
                }
                catch(final IOException e) {
                    LOG.error("Error while reading input stream: " + e.getMessage(), e);
                }
                finally {
                    try {
                        reader.close();
                    }
                    catch(final IOException e) {
                        LOG.warn("Error while closing input stream reader: " + e.getMessage(), e);
                    }
                }
                System.out.println("SystemInInputChannel.startHook(...).new Thread() {...}.run()");
            }
        };
        LOG.debug("Starting input read thread");
        this.readThread.start();
        LOG.debug("Started input read thread");
    }

    volatile boolean running = false;

    /**
     * {@inheritDoc}
     */
    @Override
    protected synchronized void close() {
        if(this.readThread != null) {
            this.running = false;
            try {
                this.readThread.interrupt();
            }
            catch(final SecurityException e) {
                LOG.error("Failed to interrupt the input read thread: " + e.getMessage(), e);
            }
        }
    }
}