package org.sercho.masp.models.Context.WikiTutorial;

import org.apache.log4j.BasicConfigurator;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.junit.Ignore;

import de.dailab.masp.models.MetaMetaModel.MetaModel;
import de.dailab.masp.models.MetaMetaModel.ecore.EcoreMetaModelConverter;
import de.dailab.masp.models.Properties.Actor;
import de.dailab.masp.models.Properties.ActorServiceCallException;
import de.dailab.masp.models.Properties.PropertiesFactory;
import de.dailab.masp.models.Properties.PropertiesPackage;
import de.dailab.masp.models.Properties.Sensor;

@Ignore
public class Bottles {

    public static void main(final String[] args) throws ActorServiceCallException {
        BasicConfigurator.configure();
        // start metamodel
        final MetaModel metamodel = EcoreMetaModelConverter.convert(PropertiesPackage.eINSTANCE);
        metamodel.getMetaType().getMetaModel().start(metamodel);

        // Sensor starten
        final Sensor sensor = PropertiesFactory.eINSTANCE.createSensor();
        sensor.addConfigurationProperty("interval", "1000");
        sensor.setId("sensor");
        sensor.setSensorWrapperClassName("org.sercho.masp.models.Context.WikiTutorial.TutorialSensor");
        metamodel.start(sensor);

        // Actor starten
        final Actor actor = PropertiesFactory.eINSTANCE.createActor();
        actor.setId("actor");
        actor.setActorWrapperClassName("org.sercho.masp.models.Context.WikiTutorial.TutorialActor");
        metamodel.start(actor);

        // Flaschen initialisieren
        actor.set("10");

        // sensor ueberwachen
        sensor.eAdapters().add(new Adapter() {

            @Override
            public Notifier getTarget() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public boolean isAdapterForType(final Object arg0) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void notifyChanged(final Notification arg0) {

                // Abfangen ob Service not available ist
                if(arg0.getFeature().equals(PropertiesPackage.Literals.SERVICE__AVAILABLE)) {
                    if(arg0.getNewBooleanValue() == Boolean.FALSE) {
                        try {
                            actor.set(Integer.toString((int)Math.round(Math.random() * 20)));
                        }
                        catch(final ActorServiceCallException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }

                // neue Werte von Service abfangen
                if(arg0.getFeature().equals(PropertiesPackage.Literals.SENSOR__VALUE)) {
                    System.out.println(arg0.getNewStringValue() + " bottles of beer on the wall.");
                }
            }

            @Override
            public void setTarget(final Notifier arg0) {
                // TODO Auto-generated method stub

            }
        });

        try {
            Bottles.class.wait();
        }
        catch(final Exception e) {
        }
    }
}
