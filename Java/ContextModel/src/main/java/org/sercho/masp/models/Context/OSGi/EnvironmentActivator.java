package org.sercho.masp.models.Context.OSGi;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.sercho.masp.models.XMIUtility;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Environment;

import de.dailab.masp.models.MetaMetaModel.MetaModel;
import de.dailab.masp.models.MetaMetaModel.ecore.EcoreMetaModelConverter;

public class EnvironmentActivator implements BundleActivator {

    final static String MODEL_PATH = "/opt/masp/config/ContextModel.xmi";

    private Environment env;

    private MetaModel metaModel;

    private ServiceRegistration registration;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        // Modell aus File laden
        final File file = new File(MODEL_PATH);
        if(!file.isFile()) {
            throw new IllegalStateException("'" + MODEL_PATH + "' is not a file!");
        }
        if(!file.canRead()) {
            throw new IllegalStateException("'" + MODEL_PATH + "' is not readable!");
        }
        if(!file.canWrite()) {
            throw new IllegalStateException("'" + MODEL_PATH + "' is not writable!");
        }

        final EList<EObject> a = XMIUtility.convert(new FileInputStream(file), ContextPackage.eINSTANCE);

        this.env = (Environment)(a.get(0));

        if(this.env == null) {
            throw new IllegalStateException("ContextModel is null!");
        }

        // Modell starten
        this.metaModel = EcoreMetaModelConverter.convert(ContextPackage.eINSTANCE);
        this.metaModel.getMetaType().getMetaModel().start(this.metaModel);
        this.metaModel.start(this.env);

        // Modell an OSGi geben
        this.registration = context.registerService(Environment.class.getName(), this.env, new Properties());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        this.registration.unregister();

        this.metaModel.stop(this.env);
        this.metaModel.getMetaType().getMetaModel().stop(this.metaModel);
    }
}
