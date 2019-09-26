/**
 * 
 */
package org.sercho.masp.models;

import org.apache.commons.logging.Log;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentAdapter;

/**
 * <code>LoggingAdapter</code> logs all occurrences inside a model.
 * 
 * @author Grzegorz Lehmann
 * @since 1.0.3
 */
public final class LoggingAdapter extends EContentAdapter {

    private final Log log;

    /**
     * <code>LoggingAdapter</code> constructor.
     * 
     * @param logger
     *            log to use
     */
    public LoggingAdapter(final Log logger) {
        if(logger == null) {
            throw new IllegalArgumentException("logger is null");
        }
        this.log = logger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyChanged(final Notification notification) {
        super.notifyChanged(notification);
        final StringBuffer buffer = new StringBuffer();
        buffer.append(notification.getNotifier());
        buffer.append(' ');
        final Object feature = notification.getFeature();
        if(feature instanceof EStructuralFeature) {
            buffer.append(((EStructuralFeature)feature).getName());
        }
        buffer.append(' ');
        buffer.append(notification.getEventType());
        buffer.append(' ');
        buffer.append(notification.getOldValue());
        buffer.append(" -> ");
        buffer.append(notification.getNewValue());
        this.log.trace(buffer.toString());
    }
}
