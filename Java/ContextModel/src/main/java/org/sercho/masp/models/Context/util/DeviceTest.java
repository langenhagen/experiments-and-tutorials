/**
 * 
 */
package org.sercho.masp.models.Context.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.sercho.masp.models.XMIUtility;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.Environment;

import de.dailab.masp.models.MetaMetaModel.MetaModel;
import de.dailab.masp.models.MetaMetaModel.ecore.EcoreMetaModelConverter;
import de.dailab.masp.models.Properties.Property;

/**
 * TODO: setNewAvailable in PropertiesModel erstellen <code>DeviceTest</code>
 * (ControllerFrameworkDemonstration before) starts a context model and monitors
 * the properties of all its devices. Any property change is printed out on
 * System.out.
 * 
 * @author Grzegorz Lehmann
 * @since 3.0.36
 */
public class DeviceTest {

    /**
     * <code>main</code> starts a small demo of the ControllerFramework
     * 
     * @param args
     *            one argument must be provided - the path to the controller
     *            framework model XML file that should be started
     */
    public static void main(final String[] args) {
        String fileName;

        if(args.length < 1) {
            System.out.println("No context model provided. Working on default model (target/classes/org/sercho/masp/models/Context/util/DeviceTest.xmi)");
            fileName = "target/classes/org/sercho/masp/models/Context/util/DeviceTest.xmi";
        } else {
            fileName = args[0];
        }

        final Environment home;
        try {
            home = (Environment)XMIUtility.convert(new FileInputStream(fileName), ContextPackage.eINSTANCE).get(0);
        }
        catch(final FileNotFoundException e) {
            System.out.println("File not found: " + e.toString() + fileName);
            return;
        }
        catch(final IOException e) {
            System.out.println("I/O error while loading " + fileName);
            e.printStackTrace();
            return;
        }

        // simple observer that prints out property changes
        final Adapter adapter = new EContentAdapter() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void notifyChanged(final Notification notification) {
                super.notifyChanged(notification);
                final Object notifier = notification.getNotifier();
                if(notifier instanceof Property) {
                    final Property<?> property = (Property<?>)notifier;
                    final Device device = (Device)property.eContainer();
                    System.out.println(device.getName() + "." + property.eContainingFeature().getName() + ": " + notification.getOldValue() + " -> " + notification.getNewValue());
                }
            }
        };

        // attach observer to all devices
        for(final Device device : home.getDevices()) {
            device.eAdapters().add(adapter);
            System.out.println("Device: " + device.getName() + " Id: " + device.getId() + " Class: " + device.getClass());
        }

        // start metamodel
        final MetaModel metamodel = EcoreMetaModelConverter.convert(ContextPackage.eINSTANCE);
        metamodel.getMetaType().getMetaModel().start(metamodel);
        // use metamodel to start model
        metamodel.start(home);
        System.out.println("Environment '" + fileName + "' started");
    }
}