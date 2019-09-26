/**
 * 
 */
package org.sercho.masp.models.channel.api.proxy;

import junit.framework.TestCase;

import org.sercho.masp.util.comm.CommunicationPath;
import org.sercho.masp.util.comm.CommunicationPathException;
import org.sercho.masp.util.comm.CommunicationPathObserver;
import org.sercho.masp.util.patterns.event.Observer;

/**
 * <code>CallbackProxyTest</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since TODO
 */
public abstract class CallbackProxyTest extends TestCase {

    protected final class TestCommunicationPath implements CommunicationPath {

        /**
         * <code>serialVersionUID</code>
         */
        private static final long serialVersionUID = -6975370733809631325L;

        private final String type;

        private final Observer<String> observer;

        /**
         * <code>TestCommunicationPath</code> constructor.
         * 
         * @param pathType
         *            type for this path
         */
        protected TestCommunicationPath(final String pathType,
                final Observer<String> pathObserver) {
            if(pathType == null) {
                throw new IllegalArgumentException("pathType is null");
            }
            if(pathObserver == null) {
                throw new IllegalArgumentException("pathObserver is null");
            }
            this.type = pathType;
            this.observer = pathObserver;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void close() {
            // nothing to do here yet
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getType() {
            return this.type;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void send(final String arg0) throws CommunicationPathException {
            this.observer.update(arg0);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void addObserver(final CommunicationPathObserver arg0) {
            // TODO Auto-generated method stub

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isClosed() {
            // TODO Auto-generated method stub
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void removeObserver(final CommunicationPathObserver arg0) {
            // TODO Auto-generated method stub

        }
    }
}
