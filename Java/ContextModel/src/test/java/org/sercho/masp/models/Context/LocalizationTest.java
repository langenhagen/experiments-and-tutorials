/**
 * 
 */
package org.sercho.masp.models.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import junit.framework.TestCase;

import org.sercho.masp.context.providers.location.AbstractLocalisationProvider;
import org.sercho.masp.context.providers.location.LocalisationProvider;
import org.sercho.masp.context.providers.location.Vector;
import org.sercho.masp.models.XMIUtility;
import org.sercho.masp.models.Context.util.EnvironmentUtility;

import de.dailab.masp.models.MetaMetaModel.MetaModel;
import de.dailab.masp.models.MetaMetaModel.ecore.EcoreMetaModelConverter;

/**
 * <code>LocalizationTest</code> tests the whole localization information flow,
 * starting with at a {@link LocalisationProvider} and ending with a
 * {@link User} in a {@link Place}.
 * 
 * @author Grzegorz Lehmann
 * @since 1.2
 */
public class LocalizationTest extends TestCase {

    public void testLocalization() throws IOException {

        final InputStream in = getClass().getClassLoader().getResourceAsStream("org/sercho/masp/models/Context/LocalizationTest.xmi");
        final Environment environment = (Environment)(XMIUtility.convert(in, ContextPackage.eINSTANCE).get(0));

        final User dirk = (User)EnvironmentUtility.getEnvironmentElement(environment, "Dirk");

        assertNotNull(dirk);
        assertNull(dirk.getPlace());

        final MetaModel metaModel = EcoreMetaModelConverter.convert(ContextPackage.eINSTANCE);
        metaModel.getMetaType().getMetaModel().start(metaModel);
        metaModel.start(environment);

        final LocalisationProviderProxy proxy = (LocalisationProviderProxy)environment.getProviders().get(0);
        assertNotNull(proxy);
        assertNotNull(proxy.getApi());
        assertEquals(TestLocalisationProvider.class, proxy.getApi().getClass());

        final TestLocalisationProvider provider = (TestLocalisationProvider)proxy.getApi();

        provider.newPosition(0, 0, 0);
        Place place = dirk.getPlace();
        assertNotNull(place);
        assertEquals(environment.getPlaces().get(0), place);

        provider.newPosition(60, 0, 0);
        place = dirk.getPlace();
        assertNotNull(place);
        assertEquals(environment.getPlaces().get(1), place);

    }

    public static final class TestLocalisationProvider extends
            AbstractLocalisationProvider {

        /**
         * 
         */
        private static final long serialVersionUID = -4091656632186938692L;

        private String testTag;

        private final Vector position = new Vector(0, 0, 0);

        void newPosition(final double x, final double y, final double z) {
            newPosition(this.testTag, x, y, z, 1);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void close() {
            this.testTag = null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void start(final Map<String, String> configuration) {
            this.testTag = configuration.get("TestTag");
        }

        /**
         * {@inheritDoc}
         */

        @Override
        public Vector getPosition(final String tagID) {
            return this.testTag.equals(tagID) ? this.position : null;
        }

        /**
         * {@inheritDoc}
         */

        @Override
        public boolean register(final String tagID) {
            return tagID.equals(this.testTag);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void unregister(final String tagID) {
            // nothing to do here
        }
    }
}