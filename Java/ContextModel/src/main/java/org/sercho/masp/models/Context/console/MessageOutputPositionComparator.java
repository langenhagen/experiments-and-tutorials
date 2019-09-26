package org.sercho.masp.models.Context.console;

import java.util.Comparator;

import org.sercho.masp.models.UI.MessageOutput;

/**
 * <code>MessageOutputPositionComparator</code> compares {@link MessageOutput}s
 * according to their position.
 * 
 * @author Grzegorz Lehmann
 * @since 1.1.3
 */
public final class MessageOutputPositionComparator implements Comparator<MessageOutput> {

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final MessageOutput o1, final MessageOutput o2) {
        return o1.getPosition() < o2.getPosition() ? -1 : 1;
    }
}