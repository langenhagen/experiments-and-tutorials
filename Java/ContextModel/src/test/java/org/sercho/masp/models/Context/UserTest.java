/**
 * 
 */
package org.sercho.masp.models.Context;

import java.util.Calendar;

import org.junit.Test;
import org.sercho.masp.models.EMFTestCase;

/**
 * <code>UserTest</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 1.1.3
 */
public abstract class UserTest extends EMFTestCase<ContextFactory> {

    protected UserTest(final ContextFactory testFactory) {
        super(testFactory);
    }

    /**
     * Test method for
     * {@link org.sercho.masp.models.Context.User#addCurrentAssistant(org.sercho.masp.models.Context.Assistant)}
     * .
     */
    @Test
    public final void testAddCurrentAssistant() {
        final User user = this.factory.createUser();

        final Assistant assistant = this.factory.createHomeOSAssistant();
        assistant.setId("MenuBar");

        assertTrue(user.getCurrentAssistants().isEmpty());
        assertTrue(user.getPastAssistants().isEmpty());

        user.addCurrentAssistant(assistant);

        assertEquals(1, user.getCurrentAssistants().size());
        assertTrue(user.getCurrentAssistants().contains(assistant));
        assertTrue(user.getPastAssistants().isEmpty());

        user.removeCurrentAssistant(assistant);

        assertTrue(user.getCurrentAssistants().isEmpty());
        assertEquals(1, user.getPastAssistants().size());
        assertTrue(user.getPastAssistants().contains(assistant));
    }

    /**
     * <code>testPositionTimestamp</code> tests whether the position time stamp
     * is set correctly {@link ElementWithPosition#getPositionTimeStamp()}.
     */
    public final void testPositionTimestamp() {
        final User user = this.factory.createUser();

        assertEquals(0, user.getPositionTimeStamp());

        final long before = System.currentTimeMillis();

        user.setPosition(1, 1, 1);

        assertTrue(user.getPositionTimeStamp() >= before);
    }

    /**
     * <code>testIsAdult</code> tests {@link User#isAdult()}.
     */
    public final void testIsAdult() {
        final User user = this.factory.createUser();
        assertFalse(user.isAdult());
        final Calendar today = Calendar.getInstance();
        user.setNewBirthDate(today.get(Calendar.DAY_OF_MONTH) + "." + today.get(Calendar.MONTH) + "." + today.get(Calendar.YEAR));
        assertFalse(user.isAdult());
        user.setNewBirthDate("01.01.1900");
        assertTrue(user.isAdult());
    }

    public final void testInstalledAssistants() {
        final Environment environment = ContextFactory.eINSTANCE.createEnvironment();
        final User user = this.factory.createUser();
        environment.getUsers().add(user);

        final Assistant assistant = this.factory.createHomeOSAssistant();
        environment.getAssistants().add(assistant);
        assistant.setId("MenuBar");

        assertTrue(user.getInstalledAssistants().isEmpty());
        assertTrue(user.getPastAssistants().isEmpty());
        assertTrue(user.getCurrentAssistants().isEmpty());

        user.installAssistant(assistant.getId());

        assertEquals(1, user.getInstalledAssistants().size());
        assertTrue(user.getInstalledAssistants().contains(assistant));
        assertTrue(user.getPastAssistants().isEmpty());
        assertTrue(user.getCurrentAssistants().isEmpty());

        user.uninstallAssistant(assistant.getId());

        assertTrue(user.getInstalledAssistants().isEmpty());
        assertTrue(user.getPastAssistants().isEmpty());
        assertTrue(user.getCurrentAssistants().isEmpty());
    }
}