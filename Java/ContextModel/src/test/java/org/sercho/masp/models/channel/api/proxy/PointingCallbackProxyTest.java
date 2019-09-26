/**
 * 
 */
package org.sercho.masp.models.channel.api.proxy;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.sercho.masp.models.Context.ConfigurationPropertyUtility;
import org.sercho.masp.models.channel.api.PointingCallback;
import org.sercho.masp.models.channel.api.PointingInputChannelAPI;
import org.sercho.masp.util.patterns.event.Observer;

import de.dailab.masp.models.MetaMetaModel.ModelCallback;

/**
 * <code>PointingCallbackProxyTest</code> tests {@link PointingCallbackProxy}.
 * 
 * @author Grzegorz Lehmann
 * @since TODO
 */
public class PointingCallbackProxyTest extends CallbackProxyTest {

    private final class TestPointingInputChannelAPI implements PointingInputChannelAPI {

        /**
         * <code>PointingCallbackProxyTest.TestPointingInputChannelAPI</code>
         * constructor.
         */
        public TestPointingInputChannelAPI() {
            // increased visibility
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setCallback(final PointingCallback callback) {
            // TODO Auto-generated method stub

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isAvailable() {
            return true;
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

    private final TestPointingInputChannelAPI testAPI = new TestPointingInputChannelAPI();

    private final PointingCallbackProxy proxy = new PointingCallbackProxy(this.testAPI, "TestChannel");

    private final PointingInputChannelAPIProxy pointingServer = new PointingInputChannelAPIProxy();

    /**
     * Tests the overall functionality of the {@link PointingCallbackProxy}.
     */
    public final void testCallback() {
        final AtomicInteger pointingX = new AtomicInteger();
        final AtomicInteger pointingY = new AtomicInteger();
        final AtomicInteger positionX = new AtomicInteger();
        final AtomicInteger positionY = new AtomicInteger();
        final AtomicInteger width = new AtomicInteger();
        final AtomicInteger height = new AtomicInteger();
        this.pointingServer.setCallback(new PointingCallback() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void newPointing(final int relativeX, final int relativeY) {
                pointingX.set(relativeX);
                pointingY.set(relativeY);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void newPosition(final int relativeX, final int relativeY) {
                positionX.set(relativeX);
                positionY.set(relativeY);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void newSize(final int newWidth, final int newHeight) {
                width.set(newWidth);
                height.set(newHeight);
            }
        });
        this.pointingServer.start(ConfigurationPropertyUtility.createConfigurationProperties(ChannelAPIProxy.PROPERTY_CHANNEL_ID, "TestChannel"), new ModelCallback() {

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
        final TestCommunicationPath path = new TestCommunicationPath("TestChannel", this.pointingServer);
        final Observer<String> observer = this.proxy.handle(path);
        assertNotNull(observer);
        int x = 30;
        int y = 40;
        this.proxy.newPointing(x, y);
        assertEquals(x, pointingX.get());
        assertEquals(y, pointingY.get());
        x = 40;
        y = 33;
        this.proxy.newPointing(x, y);
        assertEquals(x, pointingX.get());
        assertEquals(y, pointingY.get());
        // test position
        x = 30;
        y = 40;
        this.proxy.newPosition(x, y);
        assertEquals(x, positionX.get());
        assertEquals(y, positionY.get());
        x = 40;
        y = 33;
        this.proxy.newPosition(x, y);
        assertEquals(x, positionX.get());
        assertEquals(y, positionY.get());
        // test size
        x = 300;
        y = 400;
        this.proxy.newSize(x, y);
        assertEquals(x, width.get());
        assertEquals(y, height.get());
        x = 40;
        y = 33;
        this.proxy.newSize(x, y);
        assertEquals(x, width.get());
        assertEquals(y, height.get());
    }
}
