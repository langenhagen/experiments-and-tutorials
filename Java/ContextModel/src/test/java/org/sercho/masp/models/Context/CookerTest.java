/**
 * 
 */
package org.sercho.masp.models.Context;

import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import de.dailab.masp.models.Properties.AbstractSensorWrapper;
import de.dailab.masp.models.Properties.BooleanProperty;
import de.dailab.masp.models.Properties.PropertiesFactory;
import de.dailab.masp.models.Properties.Sensor;

/**
 * <code>CookerTest</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 3.0
 */
public abstract class CookerTest extends TestCase {

    private final ContextFactory factory;

    /**
     * <code>CookerTest</code> constructor.
     */
    protected CookerTest(final ContextFactory testFactory) {
        if(testFactory == null) {
            throw new IllegalArgumentException("testFactory is null");
        }
        this.factory = testFactory;
    }

    /**
     * Test method for
     * {@link org.sercho.masp.models.Context.PhysicalDevice#getSubDevice()} .
     */
    public final void testGetSubDevice() {
        final Cooker cooker = this.factory.createCooker();
        final CookTop cookTop = this.factory.createCookTop();
        cooker.getSubDevice().add(cookTop);
        final Hob hob1 = this.factory.createHob();
        final Hob hob2 = this.factory.createHob();
        cookTop.getSubDevice().add(hob1);
        cookTop.getSubDevice().add(hob2);

        final List<PhysicalDevice> subDevices = cooker.getSubDevice();

        assertNotNull(subDevices);
        assertEquals(1, subDevices.size());

        /*
         * Since ContextModel=ControllerFramework getSubDevice() returns only
         * the direct children of a device
         * assertTrue(subDevices.contains(cookTop));
         * assertTrue(subDevices.contains(hob1));
         * assertTrue(subDevices.contains(hob2));
         * 
         * // test if getRootAppliance works assertSame(cooker,
         * hob1.getParentDevice()); assertSame(cooker, hob2.getParentDevice());
         * assertSame(cooker, cooker.getParentDevice());
         */
        assertSame(cooker, cookTop.getParentDevice());
    }

    /*
     * Test method for {@link
     * de.dailab.masp.cf.Appliances.ComplexAppliance#getAtomicSubAppliances()} .
     * 
     * public final void testGetAtomicSubAppliances() { final Cooker cooker =
     * this.factory.createCooker(); final CookTop cookTop =
     * this.factory.createCookTop(); cooker.getSubDevice().add(cookTop); final
     * Hob hob1 = this.factory.createHob(); final Hob hob2 =
     * this.factory.createHob(); cookTop.getSubDevice().add(hob1);
     * cookTop.getSubDevice().add(hob2);
     * 
     * final List<AtomicAppliance> atomicSubAppliances =
     * cooker.getAtomicSubAppliances(); assertNotNull(atomicSubAppliances);
     * assertEquals(2, atomicSubAppliances.size());
     * assertTrue(atomicSubAppliances.contains(hob1));
     * assertTrue(atomicSubAppliances.contains(hob2)); }
     */

    public static final class TestSensorWrapper extends AbstractSensorWrapper {

        /**
         * 
         */
        private static final long serialVersionUID = 2023884218391212670L;

        /**
         * {@inheritDoc}
         */
        @Override
        protected void startHook(final Map<String, String> configurationMap) {
            // TODO Auto-generated method stub

        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void stopHook() {
            // TODO Auto-generated method stub

        }

        public void setNewAvailable(final Boolean newAvailable) {
            setAvailable(newAvailable);
        }
    }

    public final void testOn() {
        final Cooker cooker = this.factory.createCooker();
        final CookTop cookTop = this.factory.createCookTop();
        cooker.getSubDevice().add(cookTop);
        final Hob hob1 = this.factory.createHob();
        final Hob hob2 = this.factory.createHob();
        assertTrue(hob1.getOn() == null);
        assertTrue(hob2.getOn() == null);

        cookTop.getSubDevice().add(hob1);
        cookTop.getSubDevice().add(hob2);

        final BooleanProperty hob1PowerStatus = PropertiesFactory.eINSTANCE.createBooleanProperty();
        hob1PowerStatus.setValue(true);
        hob1.setOn(hob1PowerStatus);
        assertTrue(hob1PowerStatus.isAvailable());
        assertTrue(hob1.getOn().getValue());

        final Sensor hob1PowerStatusSensor = PropertiesFactory.eINSTANCE.createSensor();
        hob1PowerStatus.setSensor(hob1PowerStatusSensor);

        assertFalse(hob1PowerStatusSensor.isAvailable());
        // assertFalse(hob1PowerStatus.isAvailable());
        // assertFalse(hob1.getOn().getValue());

        final TestSensorWrapper hob1PowerStatusSensorWrapper = new TestSensorWrapper();
        hob1PowerStatusSensor.setSensorWrapper(hob1PowerStatusSensorWrapper);
        assertFalse(hob1PowerStatusSensor.isAvailable());
        hob1.getOn().getSensor().setValue("true");
        System.out.println(hob1.getOn().getSensor().getValue());
        // assertFalse(hob1PowerStatus.isAvailable());
        // assertFalse(hob1.getOn().getValue());

        /*
         * isAvailable is no more used by physicaldevices
         * 
         * BooleanProperty hob2PowerStatus =
         * PropertiesFactory.eINSTANCE.createBooleanProperty();
         * hob2.setOn(hob2PowerStatus); final Sensor hob2PowerStatusSensor =
         * PropertiesFactory.eINSTANCE.createSensor();
         * hob2PowerStatus.setSensor(hob2PowerStatusSensor); final
         * TestSensorWrapper hob2PowerStatusSensorWrapper = new
         * TestSensorWrapper();
         * hob2PowerStatusSensor.setSensorWrapper(hob2PowerStatusSensorWrapper);
         * 
         * assertFalse(hob2PowerStatusSensor.isAvailable());
         * assertFalse(hob2.getOn().isAvailable());
         * assertFalse(cooker.getOn().isAvailable());
         * assertFalse(cookTop.getOn().isAvailable());
         * 
         * hob1PowerStatusSensor.setAvailable(true);
         * assertTrue(hob1PowerStatusSensor.isAvailable());
         * assertTrue(hob1.getOn().isAvailable());
         * assertFalse(cooker.getOn().isAvailable());
         * assertFalse(cookTop.getOn().isAvailable());
         * 
         * hob2PowerStatusSensor.setAvailable(true);
         * assertTrue(hob2.getOn().isAvailable());
         * assertTrue(hob2PowerStatus.isAvailable());
         * assertTrue(hob2PowerStatusSensor.isAvailable());
         * assertTrue(hob1.getOn().isAvailable());
         * assertTrue(hob1PowerStatus.isAvailable());
         * assertTrue(hob1PowerStatusSensor.isAvailable());
         * assertTrue(cooker.getOn().isAvailable());
         * assertTrue(cookTop.getOn().isAvailable());
         * 
         * hob2PowerStatus =
         * PropertiesFactory.eINSTANCE.createBooleanProperty();
         * hob2PowerStatus.setSensor
         * (PropertiesFactory.eINSTANCE.createSensor());
         * assertFalse(hob2PowerStatus.isAvailable());
         * hob2.setOn(hob2PowerStatus); assertTrue(hob1.getOn().isAvailable());
         * assertFalse(hob2.getOn().isAvailable());
         * assertFalse(cooker.getOn().isAvailable());
         * assertFalse(cookTop.getOn().isAvailable());
         */
    }

}
