/**
 * 
 */
package org.sercho.masp.models.channel.api.proxy;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import org.sercho.masp.models.Context.ConfigurationPropertyUtility;
import org.sercho.masp.models.channel.api.MessageCallback;
import org.sercho.masp.models.channel.api.MessageInputChannelAPI;
import org.sercho.masp.util.patterns.event.Observer;

import de.dailab.masp.models.MetaMetaModel.ModelCallback;

/**
 * <code>MessageInputChannelAPIProxyTest</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since TODO
 */
public class MessageInputChannelAPIProxyTest extends CallbackProxyTest {

    private final class TestMessageInputChannelAPI implements MessageInputChannelAPI {

        /**
         * <code>TestMessageInputChannelAPI</code> constructor.
         */
        TestMessageInputChannelAPI() {
            // increased visibility
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setCallback(final MessageCallback newCallback) {
            // nothing to do here yet
        }

        /**
         * {@inheritDoc}
         */

        @Override
        public boolean isAvailable() {
            // TODO Auto-generated method stub
            return false;
        }

        /**
         * {@inheritDoc}
         */

        @Override
        public boolean isStarted() {
            // TODO Auto-generated method stub
            return false;
        }

        /**
         * {@inheritDoc}
         */

        @Override
        public void start(final Object configuration, final ModelCallback callback) {
            // TODO Auto-generated method stub

        }

        /**
         * {@inheritDoc}
         */

        @Override
        public void stop() {
            // TODO Auto-generated method stub

        }

    }

    private final TestMessageInputChannelAPI testAPI = new TestMessageInputChannelAPI();

    private final MessageCallbackProxy proxy = new MessageCallbackProxy(this.testAPI, "TestChannel");

    private final MessageInputChannelAPIProxy messageServer = new MessageInputChannelAPIProxy();

    /**
     * Tests the overall functionality of the {@link MessageCallbackProxy}.
     */
    public final void testCallback() {
        final AtomicReference<String> newMessage = new AtomicReference<String>();

        this.messageServer.start(ConfigurationPropertyUtility.createConfigurationProperties(ChannelAPIProxy.PROPERTY_CHANNEL_ID, "TestChannel"), new ModelCallback() {

            @Override
            public Set<String> getExecutableElementNames() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public void execute(final String executableElementName, final Object... arguments) throws InvocationTargetException {
                // TODO Auto-generated method stub

            }
        });

        this.messageServer.setCallback(new MessageCallback() {

            @Override
            public void newMessage(final String message) {
                newMessage.set(message);
            }
        });
        final TestCommunicationPath path = new TestCommunicationPath("TestChannel", this.messageServer);
        final Observer<String> observer = this.proxy.handle(path);
        assertNotNull(observer);

        String message = "MASP is great";
        this.proxy.newMessage(message);
        assertEquals(message, newMessage.get());
        message = "MASP is the best";
        this.proxy.newMessage(message);
        assertEquals(message, newMessage.get());
        message = "Have you ever thought about a meta-meta-meta-model?";
        this.proxy.newMessage(message);
        assertEquals(message, newMessage.get());
    }
}
