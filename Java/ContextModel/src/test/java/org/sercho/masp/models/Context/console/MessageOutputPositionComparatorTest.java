/**
 * 
 */
package org.sercho.masp.models.Context.console;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.TestCase;

import org.sercho.masp.models.UI.MessageOutput;
import org.sercho.masp.models.UI.UIFactory;

/**
 * <code>MessageOutputPositionComparatorTest</code> tests the
 * {@link MessageOutputPositionComparator}.
 * 
 * @author Grzegorz Lehmann
 */
public class MessageOutputPositionComparatorTest extends TestCase {

    /**
     * Test method for
     * {@link org.sercho.masp.models.Context.console.MessageOutputPositionComparator#compare(MessageOutput, MessageOutput)}
     * .
     */
    public final void testCompare() {
        final MessageOutput m1 = UIFactory.eINSTANCE.createMessageOutput();
        m1.setPosition(1);
        final MessageOutput m2 = UIFactory.eINSTANCE.createMessageOutput();
        m2.setPosition(2);
        final MessageOutput m3 = UIFactory.eINSTANCE.createMessageOutput();
        m3.setPosition(3);

        final Set<MessageOutput> sorted = new TreeSet<MessageOutput>(new MessageOutputPositionComparator());
        sorted.add(m3);
        sorted.add(m2);
        sorted.add(m1);

        assertEquals(3, sorted.size());
        final Iterator<MessageOutput> iterator = sorted.iterator();
        assertSame(m1, iterator.next());
        assertSame(m2, iterator.next());
        assertSame(m3, iterator.next());
    }
}