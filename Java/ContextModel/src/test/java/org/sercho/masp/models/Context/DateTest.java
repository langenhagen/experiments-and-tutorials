/**
 * 
 */
package org.sercho.masp.models.Context;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;
import de.dailab.masp.models.Properties.PropertiesFactory;

/**
 * <code>DateTest</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 3.0
 */
public abstract class DateTest extends TestCase {

    private final PropertiesFactory factory;

    /**
     * <code>DateTest</code> constructor.
     * 
     */
    protected DateTest(final PropertiesFactory testFactory) {
        if(testFactory == null) {
            throw new IllegalArgumentException("testFactory is null");
        }
        this.factory = testFactory;
    }

    /**
     * Test method for
     * {@link de.dailab.masp.cf.Appliances.Date#setValuesFromCalendar(java.util.Calendar)}
     * .
     */
    public final void testSetValuesFromCalendar() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2009);
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 26);
        calendar.set(Calendar.HOUR_OF_DAY, 2);
        calendar.set(Calendar.MINUTE, 56);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 6);
        final Date date = calendar.getTime();
        assertEquals(calendar.getTimeInMillis(), date.getTime());
    }

}
