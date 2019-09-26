/**
 * 
 */
package org.sercho.masp.models.Context;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.sercho.masp.context.providers.location.AbstractLocalisationProvider;
import org.sercho.masp.context.providers.location.Vector;
import org.sercho.masp.models.EMFTestCase;

import de.dailab.masp.models.MetaMetaModel.MetaModel;
import de.dailab.masp.models.MetaMetaModel.ecore.EcoreMetaModelConverter;

/**
 * <code>LocalisationProviderProxyTest</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since TODO
 */
public abstract class LocalisationProviderProxyTest extends EMFTestCase<ContextFactory> {

    public static final class TestLocalisationProvider extends
            AbstractLocalisationProvider {

        /**
         * <code>serialVersionUID</code>
         */
        private static final long serialVersionUID = 7810411447310109835L;

        boolean started = false;

        boolean stopped = false;

        /**
         * {@inheritDoc}
         */
        @Override
        public Vector getPosition(final String arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean register(final String arg0) {
            this.registered.add(arg0);
            return true;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void close() {
            if(this.stopped) {
                fail("Already stopped");
            }
            this.stopped = true;
        }

        final BlockingQueue<String> registered = new LinkedBlockingQueue<String>();

        /**
         * {@inheritDoc}
         */
        @Override
        public void unregister(final String arg0) {
            this.registered.remove(arg0);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void start(final Map<String, String> configuration) {
            // nothing to do here yet
            this.started = true;
        }
    }

    /**
     * <code>LocalisationProviderProxyTest</code> constructor.
     * 
     * @param testFactory
     *            context model factory under test
     */
    protected LocalisationProviderProxyTest(final ContextFactory testFactory) {
        super(testFactory);
    }

    public final void testStart() {
        final LocalisationProviderProxy provider = this.factory.createLocalisationProviderProxy();

        provider.setApiClass(TestLocalisationProvider.class.getName());

        final MetaModel metaModel = EcoreMetaModelConverter.convert(ContextPackage.eINSTANCE);
        metaModel.getMetaType().getMetaModel().start(metaModel);
        metaModel.start(provider);

        assertNotNull(provider.getApi());
        assertTrue(provider.getApi() instanceof TestLocalisationProvider);
        final TestLocalisationProvider testProvider = (TestLocalisationProvider)provider.getApi();
        System.out.println(testProvider.started);
        assertTrue(testProvider.started);
        assertFalse(testProvider.stopped);
    }

    /**
     * Test method for
     * {@link org.sercho.masp.models.Context.LocalisationProviderProxy#register(java.lang.String)}
     * .
     */
    public final void testRegister() {
        final LocalisationProviderProxy provider = this.factory.createLocalisationProviderProxy();

        final LocalizationTag tag1 = this.factory.createLocalizationTag();
        tag1.setId("tag1");
        tag1.setPosition(1, 1, 1);

        provider.setApiClass(TestLocalisationProvider.class.getName());
        provider.getTags().add(tag1);

        final MetaModel metaModel = EcoreMetaModelConverter.convert(ContextPackage.eINSTANCE);
        metaModel.getMetaType().getMetaModel().start(metaModel);
        metaModel.start(provider);

        assertNotNull(provider.getApi());
        assertTrue(provider.getApi() instanceof TestLocalisationProvider);
        final TestLocalisationProvider testProvider = (TestLocalisationProvider)provider.getApi();

        String tagID;
        try {
            tagID = testProvider.registered.poll(30, TimeUnit.SECONDS);
        }
        catch(final InterruptedException e) {
            fail(e.getMessage());
            return;
        }
        if(tagID == null || !tagID.equals(tag1.getId())) {
            fail(tag1.getId() + " is not registered");
        }
    }

    /**
     * Test method for
     * {@link org.sercho.masp.models.Context.ContextModelLocalisationProvider#newPosition(java.lang.String, int, int, int)}
     * .
     */
    public final void testNewPosition() {
        final LocalizationTag tag1 = this.factory.createLocalizationTag();
        tag1.setId("tag1");
        tag1.setPosition(1, 1, 1);
        assertFalse(tag1.isDetected());
        assertFalse(tag1.isRegister());

        final LocalizationTag tag2 = this.factory.createLocalizationTag();
        tag2.setId("tag2");
        tag2.setPosition(1, 1, 1);
        assertFalse(tag2.isDetected());
        assertFalse(tag2.isRegister());

        final LocalisationProviderProxy provider = this.factory.createLocalisationProviderProxy();
        provider.getTags().add(tag1);
        assertTrue(tag1.isRegister());
        provider.getTags().add(tag2);
        assertTrue(tag2.isRegister());

        provider.newPosition(tag1.getId(), 2, 2, 2);
        assertEquals(2, provider.getTags().size());
        assertSame(tag1, provider.getTags().get(0));
        assertSame(tag2, provider.getTags().get(1));
        assertTrue(tag1.isDetected());
        assertFalse(tag2.isDetected());
        assertNotNull(tag1.getPosition());
        assertEquals(2, tag1.getPosition().getX(), 0);
        assertEquals(2, tag1.getPosition().getY(), 0);
        assertEquals(2, tag1.getPosition().getZ(), 0);

        provider.newPosition(tag2.getId(), 3, 4, 5);
        assertEquals(2, provider.getTags().size());
        assertSame(tag1, provider.getTags().get(0));
        assertSame(tag2, provider.getTags().get(1));
        assertTrue(tag1.isDetected());
        assertTrue(tag2.isDetected());
        assertEquals(3, tag2.getPosition().getX(), 0);
        assertEquals(4, tag2.getPosition().getY(), 0);
        assertEquals(5, tag2.getPosition().getZ(), 0);

        provider.newPosition("tag2", 6, 7, 8);
        assertEquals(2, provider.getTags().size());
        assertSame(tag1, provider.getTags().get(0));
        assertSame(tag2, provider.getTags().get(1));
        assertEquals("tag2", tag2.getId());
        assertEquals(6, tag2.getPosition().getX(), 0);
        assertEquals(7, tag2.getPosition().getY(), 0);
        assertEquals(8, tag2.getPosition().getZ(), 0);

        provider.tagGone(tag2.getId());
        assertFalse(tag2.isDetected());
        // register is still valid
        assertTrue(tag2.isRegister());
        assertEquals(2, provider.getTags().size());
        assertSame(tag1, provider.getTags().get(0));
        assertSame(tag2, provider.getTags().get(1));

        provider.newPosition("tag3", 0, 0, 0);
        assertEquals(2, provider.getTags().size());
        assertSame(tag1, provider.getTags().get(0));
        assertSame(tag2, provider.getTags().get(1));

        provider.getTags().remove(tag2);
        assertFalse(tag2.isDetected());
        // now register is impossible
        assertFalse(tag2.isRegister());
    }
}