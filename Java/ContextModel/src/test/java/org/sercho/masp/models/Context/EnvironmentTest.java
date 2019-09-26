/**
 * 
 */
package org.sercho.masp.models.Context;

import java.io.IOException;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.jaxen.JaxenException;
import org.jaxen.SimpleVariableContext;
import org.sercho.masp.models.EMFTestCase;
import org.sercho.masp.models.XMIUtility;
import org.sercho.masp.models.Context.util.AppliancesUtility;

import ca.ecliptical.emf.xpath.EMFXPath;
import de.dailab.masp.models.MetaMetaModel.MetaModel;
import de.dailab.masp.models.MetaMetaModel.ecore.EcoreMetaModelConverter;
import de.dailab.masp.models.Properties.DoubleProperty;
import de.dailab.masp.models.Properties.PropertiesFactory;

/**
 * <code>EnvironmentTest</code> tests the {@link Environment} implementations.
 * 
 * @author Grzegorz Lehmann
 * @since 1.2.18
 */
public abstract class EnvironmentTest extends EMFTestCase<ContextFactory> {

    /**
     * <code>EnvironmentTest</code> constructor.
     * 
     * @param testFactory
     *            factory for the test
     */
    protected EnvironmentTest(final ContextFactory testFactory) {
        super(testFactory);
    }

    /**
     * <code>main</code> TODO comment
     * 
     * @todo add missing JavaDoc
     * @param args
     */
    public static void main(final String[] args) {
        try {
            final Environment environment = (Environment)XMIUtility.convert(EnvironmentTest.class.getClassLoader().getResourceAsStream("org/sercho/masp/models/Context/EnvironmentTest.xmi"), ContextPackage.eINSTANCE).get(0);
            final MetaModel metaModel = EcoreMetaModelConverter.convert(ContextPackage.eINSTANCE);
            metaModel.getMetaType().getMetaModel().start(metaModel);
            metaModel.start(environment);
            final Lamp light = (Lamp)environment.getDevices().get(0);
            light.eAdapters().add(new EContentAdapter() {

                // smartHome.eAdapters().add(new EContentAdapter() {

                /**
                 * {@inheritDoc}
                 */
                @Override
                public void notifyChanged(final Notification msg) {
                    super.notifyChanged(msg);
                    System.out.println(((EStructuralFeature)msg.getFeature()).getName() + ": " + msg.getOldValue() + " -> " + msg.getNewValue());
                    // System.out.println(XMIUtility.convert(smartHome.getServiceProviders().get(0)));
                }
            });
            for(final Place place : environment.getPlaces()) {
                System.out.println(place.getName() + " \nDOORS");
                for(final Door door : place.getDoors()) {
                    System.out.println("Direct:" + door.toString());
                    System.out.println(door.eContents());

                }
            }
        }

        catch(final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private Environment getEnvironment() {
        try {
            return (Environment)XMIUtility.convert(EnvironmentTest.class.getClassLoader().getResourceAsStream("org/sercho/masp/models/Context/EnvironmentTest.xmi"), ContextPackage.eINSTANCE).get(0);
        }
        catch(final IOException e) {
            e.printStackTrace();
            fail("Failed to load test home: " + e.getMessage());
            return null;
        }
    }

    public final void testGetPlaceInfos() {
        final Environment environment = this.factory.createEnvironment();
        // kitchen
        final Place kitchen = this.factory.createRoom();
        kitchen.setId("Kitchen");
        environment.getPlaces().add(kitchen);
        final Oven oven = this.factory.createOven();
        kitchen.addElement(oven);

        // office
        final Place office = this.factory.createRoom();
        office.setId("Office");
        environment.getPlaces().add(office);

        // test
        final List<PlaceInfo> placeInfos = environment.getPlaceInfos();
        assertNotNull(placeInfos);
        assertEquals(environment.getPlaces().size(), placeInfos.size());
        boolean placeInfoFound;
        for(final Place place : environment.getPlaces()) {
            placeInfoFound = false;
            for(final PlaceInfo placeInfo : placeInfos) {
                if(isPlaceInfo(placeInfo, place)) {
                    placeInfoFound = true;
                    break;
                }
            }
            if(!placeInfoFound) {
                fail("No place info matching place " + place);
            }
        }
    }

    private final boolean isPlaceInfo(final PlaceInfo placeInfo, final Place place) {
        // check if identifiers match
        if(!place.getId().equals(placeInfo.id)) {
            return false;
        }
        int numberOfDevicesInPlace = 0;
        for(final ElementWithPosition element : place.getElements()) {
            if(element instanceof Device) {
                numberOfDevicesInPlace++;
                // check if device is in placeInfo
                if(!placeInfo.devices.contains(element)) {
                    return false;
                }
            }
        }
        // check if placeInfo does not have more devices than place
        return placeInfo.devices.size() == numberOfDevicesInPlace;
    }

    public final void testGetRooms() {
        final Environment home = getEnvironment();

        try {
            EMFXPath xpath = new EMFXPath("Context:places");
            xpath.addNamespace("Context", ContextPackage.eNS_URI);

            List<?> places = xpath.selectNodes(home);
            assertEquals(4, places.size());
            for(final Object place : places) {
                assertTrue(place instanceof Room);
            }

            xpath = new EMFXPath("Context:places[Context:name='Kitchen']");
            xpath.addNamespace("Context", ContextPackage.eNS_URI);
            places = xpath.selectNodes(home);
            assertEquals(1, places.size());
            final Room kitchen = (Room)places.get(0);
            assertTrue("Kitchen".equals(kitchen.getName()));

            final Cooker cooker = this.factory.createCooker();
            cooker.setName("Herd");
            final Oven oven = this.factory.createOven();
            oven.setName("oven");
            cooker.getSubDevice().add(oven);
            final CookTop cookTop = this.factory.createCookTop();
            cookTop.setName("CookTop");
            cooker.getSubDevice().add(cookTop);

            final Hob hob1 = this.factory.createHob();
            hob1.setName("hob1");
            final DoubleProperty powerUsageHob1 = PropertiesFactory.eINSTANCE.createDoubleProperty();
            hob1.setPowerUsage(powerUsageHob1);
            cookTop.getSubDevice().add(hob1);

            final Hob hob2 = this.factory.createHob();
            hob2.setName("hob2");
            final DoubleProperty powerUsageHob2 = PropertiesFactory.eINSTANCE.createDoubleProperty();
            hob2.setPowerUsage(powerUsageHob2);
            cookTop.getSubDevice().add(hob2);

            powerUsageHob1.setValue(234.0);

            xpath = new EMFXPath(".//*[Context:name='" + hob2.getName() + "']");
            xpath.addNamespace("Context", ContextPackage.eNS_URI);
            assertSame(hob2, xpath.selectSingleNode(cooker));
        }
        catch(final JaxenException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    public final void testGetAppliances() {
        final Environment home = getEnvironment();

        try {
            EMFXPath xpath = new EMFXPath("Context:places[Context:name='Kitchen']/Context:elements");
            // xpath = new EMFXPath(".//Context:Hob/..");
            xpath = new EMFXPath(".//*[Context:name='BSH Kochfeld']");
            xpath.addNamespace("Context", ContextPackage.eNS_URI);
            final Object cookTop = xpath.selectSingleNode(home);
            assertTrue(cookTop instanceof CookTop);
        }
        catch(final JaxenException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    public final void testGetTotalPowerUsage() {
        final Environment home = getEnvironment();

        int totalPowerUsage = 0;
        for(final Device dev : home.getDevices()) {
            if(dev instanceof PhysicalDevice) {
                totalPowerUsage += acquireTotalPowerUsage((PhysicalDevice)dev);
            }
        }
        assertEquals(0, totalPowerUsage, 0);

        final PhysicalDevice kochfeld = (PhysicalDevice)AppliancesUtility.getDevice(home, "Kochfeld1");
        assertNotNull(kochfeld);
        final DoubleProperty kochfeldPowerUsage = PropertiesFactory.eINSTANCE.createDoubleProperty();
        kochfeldPowerUsage.setValue(50.0);
        kochfeld.setPowerUsage(kochfeldPowerUsage);
        // kochfeldPowerUsage.setValueAndStoreInHistory("11");

        totalPowerUsage = 0;
        for(final Device dev : home.getDevices()) {
            if(dev instanceof PhysicalDevice) {
                totalPowerUsage += acquireTotalPowerUsage((PhysicalDevice)dev);
            }
        }

        assertEquals(50, totalPowerUsage, 0);
    }

    private final double acquireTotalPowerUsage(final PhysicalDevice currDevice) {
        double res = 0;

        try {
            if(currDevice.getPowerUsage() != null) {
                if(currDevice.getPowerUsage().getValue() != null) {
                    res += currDevice.getPowerUsage().getValue();
                }
            }
        }
        catch(final Exception e) {
            System.out.println("Error: Could not acquire total powerusage for device: " + currDevice.getName() + ", " + currDevice.getId());
        }

        for(final PhysicalDevice dev : currDevice.getSubDevice()) {
            res += acquireTotalPowerUsage(dev);
        }

        return res;
    }

    public final void testEMFXPath() {
        try {
            final Environment environment = ContextFactory.eINSTANCE.createEnvironment();
            final Room room1 = ContextFactory.eINSTANCE.createRoom();
            room1.setName("room1");
            environment.getPlaces().add(room1);

            final Room room2 = ContextFactory.eINSTANCE.createRoom();
            room2.setName("room2");
            environment.getPlaces().add(room2);

            final SimpleVariableContext variableContext = new SimpleVariableContext();
            variableContext.setVariableValue("roomName", room2.getName());

            final EMFXPath xpath = new EMFXPath("Context:places[Context:name=$roomName]");
            xpath.setVariableContext(variableContext);
            xpath.addNamespace("Context", ContextPackage.eNS_URI);

            assertSame(room2, xpath.selectSingleNode(environment));
        }
        catch(final JaxenException e) {
            e.printStackTrace();
            fail("A EMF-XPath problem occurred: " + e.getMessage());
        }
    }

    /**
     * Test method for {@link Environment#removeDevice(Device)}.
     */
    public void testRemoveDevice() {
        final Environment environment = super.factory.createEnvironment();

        environment.addOutdoors("testplace", "testplace", null);

        final Place place = environment.getPlaces().get(0);

        final TV tv = super.factory.createTV();
        tv.setId("tv");
        tv.setName("tv");

        tv.setPosition(1, 1, 1);
        tv.addSubDevice(super.factory.createBlind(), "blind1");
        tv.addSubDevice(super.factory.createBlind(), "blind2");
        tv.addSubDevice(super.factory.createBlind(), "blind3");
        tv.addSubDevice(super.factory.createBlind(), "blind4");

        final Loudspeaker loudspeaker = super.factory.createLoudspeaker();
        loudspeaker.setId("loudspeaker");
        loudspeaker.setPlace(place);
        place.addElement(loudspeaker);
        tv.addInteractionResource(loudspeaker, null);

        final Display display = super.factory.createDisplay();
        display.setId("testdisplay");
        display.setPlace(place);
        place.addElement(display);
        tv.addInteractionResource(display, null);

        final Keyboard keyboard = super.factory.createKeyboard();
        keyboard.setId("keyboard");
        keyboard.setPlace(place);
        place.addElement(keyboard);
        tv.addInteractionResource(keyboard, null);

        environment.addDevice(tv, null);

        environment.removeDevice(tv);
    }
}