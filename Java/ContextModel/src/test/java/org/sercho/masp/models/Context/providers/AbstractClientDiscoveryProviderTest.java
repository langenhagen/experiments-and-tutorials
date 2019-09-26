/**
 * 
 */
package org.sercho.masp.models.Context.providers;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

/**
 * <code>AbstractClientDiscoveryProviderTest</code> tests the
 * {@link AbstractClientDiscoveryProvider} and its compliance to the
 * {@link ClientDiscoveryProvider} specification.
 * 
 * @author Grzegorz Lehmann
 * @since 5.0.3
 */
public class AbstractClientDiscoveryProviderTest extends TestCase {

    private class TestClientDiscoveryProvider extends AbstractClientDiscoveryProvider {

        /**
         * <code>AbstractClientDiscoveryProviderTest.TestClientDiscoveryProvider</code>
         * constructor.
         */
        TestClientDiscoveryProvider() {
            // increased visibility
        }

        boolean started = false;

        boolean stopped = false;

        /**
         * {@inheritDoc}
         */
        @Override
        protected void started() {
            this.started = true;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void stopped() {
            this.stopped = true;
        }
    }

    private class TestClientDiscoveryObserver implements ClientDiscoveryObserver {

        /**
         * <code>serialVersionUID</code>
         */
        private static final long serialVersionUID = -3111791760754582748L;

        final Set<String> clientIDs = new HashSet<String>();

        /**
         * <code>AbstractClientDiscoveryProviderTest.TestClientDiscoveryObserver</code>
         * constructor.
         */
        TestClientDiscoveryObserver() {
            // increased visibility
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void clientGone(final String clientId) {
            if(!this.clientIDs.remove(clientId)) {
                fail("Got clientGone call for non-existing clientId: " + clientId);
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void newClient(final String clientId, final ClientAPI api) {
            if(!this.clientIDs.add(clientId)) {
                fail("Got newClient call for an existing clientId: " + clientId);
            }
        }
    }

    private final TestClientDiscoveryProvider provider = new TestClientDiscoveryProvider();

    private final TestClientDiscoveryObserver observer = new TestClientDiscoveryObserver();

    private final ClientAPI dummy = new ClientAPI() {

        /**
         * {@inheritDoc}
         */
        @Override
        public void setObserver(final ClientAPIObserver newObserver) {
            // do nothing, it's a dummy
        }
    };

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        this.provider.start(this.observer);
    }

    /**
     * Test method for
     * {@link org.sercho.masp.models.Context.providers.AbstractClientDiscoveryProvider#newClient(java.lang.String, org.sercho.masp.models.Context.providers.ClientAPI)}
     * .
     */
    public final void testNewClient() {
        final String clientID = "clientID";
        assertEquals(0, this.observer.clientIDs.size());
        this.provider.newClient(clientID, this.dummy);
        assertEquals(1, this.observer.clientIDs.size());
        assertTrue(this.observer.clientIDs.contains(clientID));
    }

    /**
     * Test method for
     * {@link org.sercho.masp.models.Context.providers.AbstractClientDiscoveryProvider#clientGone(java.lang.String)}
     * .
     */
    public final void testClientGone() {
        final String clientID = "clientID";
        this.provider.newClient(clientID, this.dummy);
        assertEquals(1, this.observer.clientIDs.size());
        assertTrue(this.observer.clientIDs.contains(clientID));
        this.provider.clientGone(clientID);
        assertEquals(0, this.observer.clientIDs.size());
        this.provider.clientGone(clientID);
        assertEquals(0, this.observer.clientIDs.size());
    }

    /**
     * Test method for
     * {@link org.sercho.masp.models.Context.providers.AbstractClientDiscoveryProvider#started()}
     * .
     */
    public final void testStarted() {
        assertTrue(this.provider.started);
    }

    /**
     * Test method for
     * {@link org.sercho.masp.models.Context.providers.AbstractClientDiscoveryProvider#stop()}
     * .
     */
    public final void testStop() {
        final String clientID = "clientID";
        this.provider.newClient(clientID, this.dummy);
        assertEquals(1, this.observer.clientIDs.size());
        assertTrue(this.observer.clientIDs.contains(clientID));
        assertFalse(this.provider.stopped);
        // stop the provider
        this.provider.stop();
        // should be stopped now
        assertTrue(this.provider.stopped);
        // all clients must be removed
        assertEquals(0, this.observer.clientIDs.size());
    }
}