/**
 * 
 */
package org.sercho.masp.models.Context.util;

import junit.framework.TestCase;

import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.Environment;

import de.dailab.masp.models.Properties.PropertiesFactory;
import de.dailab.masp.models.Properties.ServiceContainer;

/**
 * <code>SmartHomeUtilityTest</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since TODO
 */
public class SmartHomeUtilityTest extends TestCase {

    public void testGetServiceContainer() {
        final Environment environment = ContextFactory.eINSTANCE.createEnvironment();

        final ServiceContainer c1 = PropertiesFactory.eINSTANCE.createServiceContainer();
        c1.setId("c1");
        final ServiceContainer c1_1 = PropertiesFactory.eINSTANCE.createServiceContainer();
        c1_1.setId("c1_1");
        final ServiceContainer c1_2 = PropertiesFactory.eINSTANCE.createServiceContainer();
        c1_2.setId("c1_2");

        c1.getServices().add(c1_1);
        c1.getServices().add(c1_2);

        final ServiceContainer c2 = PropertiesFactory.eINSTANCE.createServiceContainer();
        c2.setId("c2");
        final ServiceContainer c2_1 = PropertiesFactory.eINSTANCE.createServiceContainer();
        c2_1.setId("c2_1");

        c2.getServices().add(c2_1);

        environment.getServiceContainers().add(c1);
        environment.getServiceContainers().add(c2);

        assertSame(c1, SmartHomeUtility.getServiceContainer(environment, c1.getId()));
        assertSame(c1_1, SmartHomeUtility.getServiceContainer(environment, c1_1.getId()));
        assertSame(c1_2, SmartHomeUtility.getServiceContainer(environment, c1_2.getId()));
        assertSame(c2, SmartHomeUtility.getServiceContainer(environment, c2.getId()));
        assertSame(c2_1, SmartHomeUtility.getServiceContainer(environment, c2_1.getId()));
    }
}
