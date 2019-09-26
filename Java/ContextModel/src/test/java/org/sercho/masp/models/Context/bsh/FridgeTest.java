/**
 * 
 */
package org.sercho.masp.models.Context.bsh;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.SingletonAdapterImpl;
import org.junit.Ignore;
import org.sercho.masp.models.XMIUtility;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.Fridge;
import org.sercho.masp.models.Context.util.AppliancesUtility;

import de.dailab.masp.models.MetaMetaModel.MetaModel;
import de.dailab.masp.models.MetaMetaModel.ecore.EcoreMetaModelConverter;
import de.dailab.masp.models.Properties.ActorServiceCallException;
import de.dailab.masp.models.Properties.PropertiesPackage;

/**
 * <code>FridgeTest</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since TODO
 */
@Ignore
public final class FridgeTest {

    static final Logger LOG = Logger.getLogger(FridgeTest.class);

    /**
     * <code>main</code> TODO comment
     * 
     * @todo add missing JavaDoc
     * @param args
     */
    public static void main(final String[] args) {

        try {
            final Environment environment = (Environment)XMIUtility.convert(FridgeTest.class.getClassLoader().getResourceAsStream("org/sercho/masp/models/Context/bsh/FridgeTest.xmi"), ContextPackage.eINSTANCE).get(0);
            final MetaModel metaModel = EcoreMetaModelConverter.convert(ContextPackage.eINSTANCE);
            metaModel.getMetaType().getMetaModel().start(metaModel);
            metaModel.start(environment);

            LOG.info("Loaded test model");

            // save the model periodically
            new Thread() {

                /**
                 * {@inheritDoc}
                 */
                @Override
                public void run() {
                    LOG.info("Started save thread");
                    while(true) {
                        try {
                            Thread.sleep(java.util.concurrent.TimeUnit.MINUTES.toMillis(15));
                        }
                        catch(final InterruptedException e) {
                            return;
                        }
                        save(environment);
                    }
                }
            }.start();

            Fridge fridge = null;
            for(final Device dev : environment.getDevices()) {
                if(dev.getName().compareTo("BSH Fridge") == 0) {
                    fridge = (Fridge)dev;
                    break;
                }
            }

            // attach adapter
            fridge.getPowerUsage().eAdapters().add(new SingletonAdapterImpl() {

                /**
                 * {@inheritDoc}
                 */
                @Override
                public void notifyChanged(final Notification msg) {
                    super.notifyChanged(msg);
                    if(msg.getFeature() == PropertiesPackage.Literals.PROPERTY__VALUE) {
                        LOG.info("New power usage: " + msg.getNewValue());
                    }
                }
            });

            // setTemperature(fridge, 6, 1000);
            save(environment);

            // after 6 hours set target temperature to 2 degress, after next 6h
            // back to 6 degrees
            setTemperature(fridge, 2, TimeUnit.HOURS.toMillis(6));
            setTemperature(fridge, 6, TimeUnit.HOURS.toMillis(6));
        }
        catch(final IOException e) {
            e.printStackTrace();
        }
    }

    static void save(final Environment home) {
        final String pathname = System.getProperty("user.home") + "/Fridge.xmi";
        try {
            XMIUtility.saveAsXMI(home, new File(pathname));
            LOG.info("Saved model to " + pathname);
        }
        catch(final IOException e) {
            LOG.error("Failed to save model to " + pathname);
            e.printStackTrace();
        }
    }

    private static void setTemperature(final Fridge fridge, final int temperature, final long timeoutMillis) {
        // wait
        LOG.info("Going to sleep for " + timeoutMillis + " millis");
        try {
            Thread.sleep(timeoutMillis);
        }
        catch(final InterruptedException e) {
            LOG.error("Interrupted");
            return;
        }
        LOG.info("Woke up, setting target temperature to " + temperature);
        // set target temperature
        try {
            AppliancesUtility.set(fridge.getTemperature(), Integer.toString(temperature));
            LOG.info("Target temperature set to " + temperature);
        }
        catch(final ActorServiceCallException e) {
            LOG.warn("Failed to set target temperature set to " + temperature, e);
            e.printStackTrace();
        }
    }
}