package org.sercho.masp.context.providers.location.ubisense;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.LF5Appender;
import org.eclipse.emf.common.util.BasicEList;
import org.sercho.masp.context.providers.location.AbstractLocalisationProvider;
import org.sercho.masp.models.Context.ConfigurationProperty;
import org.sercho.masp.models.Context.ConfigurationPropertyUtility;

import de.dailab.masp.models.MetaMetaModel.ModelCallback;

/**
 * <code>UBLocProvider</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Tilman Wekel
 * @author Grzegorz Lehmann
 * @since TODO
 */
public class UBLocProvider extends AbstractLocalisationProvider {

    /**
     * <code>LOG</code>
     */
    public static final transient Log LOG = LogFactory.getLog(UBLocProvider.class);

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -9032189277204839784L;

    /**
     * <code>PROPERTY_HOST</code> holds the key of the configuration property
     * holding the Ubisense host.
     */
    public static final transient String PROPERTY_HOST = "Host";

    /**
     * <code>PROPERTY_PORT</code> holds the key of the configuration property
     * holding the Ubisense port.
     */
    public static final transient String PROPERTY_PORT = "Port";

    /**
     * <code>PROPERTY_SCALE_FACTOR</code> holds the key of the configuration
     * property holding the scale factor.
     */
    public static final transient String PROPERTY_SCALE_FACTOR = "ScaleFactor";

    /**
     * <code>DEFAULT_SCALE_FACTOR</code> is the default scale factor of this
     * provider, which is 1.
     */
    public static final transient double DEFAULT_SCALE_FACTOR = 1;

    public static void main(final String[] args) {
        Logger.getRootLogger().addAppender(new LF5Appender());
        final UBLocProvider provider = new UBLocProvider();
        final List<ConfigurationProperty> configuration = new BasicEList<ConfigurationProperty>();
        configuration.add(ConfigurationPropertyUtility.createConfigurationProperty(PROPERTY_HOST, "192.168.1.32"));
        configuration.add(ConfigurationPropertyUtility.createConfigurationProperty(PROPERTY_PORT, "4443"));
        configuration.add(ConfigurationPropertyUtility.createConfigurationProperty(PROPERTY_SCALE_FACTOR, "100"));

        provider.start(configuration, new ModelCallback() {

            @Override
            public Set<String> getExecutableElementNames() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public void execute(final String executableElementName, final Object... arguments) throws InvocationTargetException {
                System.out.println(executableElementName);
            }
        });
        provider.register("Marco");
    }

    public UBLocProvider() {
        // empty constructor
    }

    public UBLocProvider(final String host, final int port) {
        this.locProvider = new UBLocProviderClient(host, port);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized org.sercho.masp.context.providers.location.Vector getPosition(final String tagID) {
        if(this.acquThread == null) {
            throw new IllegalStateException("Not started yet");
        }
        return this.acquThread.getPosition(tagID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized boolean register(final String tagID) {
        if(this.acquThread == null) {
            throw new IllegalStateException("Not started yet");
        }
        return this.acquThread.register(tagID);
    }

    synchronized void newPositionScaled(final String tagID, final double x, final double y, final double z, final double confidence) {
        newPosition(tagID, x * this.scaleFactor, y * this.scaleFactor, z * this.scaleFactor, confidence);
    }

    private volatile double scaleFactor = DEFAULT_SCALE_FACTOR;

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void start(final Map<String, String> configuration) {
        if(this.acquThread != null) {
            // already started
            return;
        }
        final String host = ConfigurationPropertyUtility.getPropertyValue(configuration, PROPERTY_HOST);
        final int port = ConfigurationPropertyUtility.getPropertyValueInteger(configuration, PROPERTY_PORT);

        // check if scale factor is set
        final String scaleFactorString = configuration.get(PROPERTY_SCALE_FACTOR);
        if(scaleFactorString != null) {
            try {
                this.scaleFactor = Double.parseDouble(scaleFactorString);
            }
            catch(final NumberFormatException e) {
                this.scaleFactor = DEFAULT_SCALE_FACTOR;
            }
        }

        this.locProvider = new UBLocProviderClient(host, port);
        this.acquThread = new UBAcquisitionThread(this.locProvider);

        new Thread(UBLocProvider.class.getSimpleName() + " connect thread " + host + ":" + port) {

            /**
             * {@inheritDoc}
             */
            @Override
            public void run() {
                final long timeoutSeconds = 30;
                while(!connect()) {
                    LOG.warn("Failed to connect to " + host + ":" + port + ", next connect attempt in " + timeoutSeconds + " seconds");
                    try {
                        sleep(timeoutSeconds * 1000);
                    }
                    catch(final InterruptedException e) {
                        LOG.error("Interrupted");
                        return;
                    }
                }
            }
        }.start();
    }

    synchronized boolean connect() {
        if(this.acquThread == null) {
            // closed in meantime, skip connect
            return true;
        }
        if(!this.locProvider.connect()) {
            return false;
        }
        UBLocProvider.this.locProvider.start();
        UBLocProvider.this.acquThread.start();
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void close() {
        if(this.acquThread == null) {
            // already stopped
            return;
        }
        this.acquThread.running = false;
        this.acquThread = null;
        this.scaleFactor = DEFAULT_SCALE_FACTOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void unregister(final String tagID) {
        if(this.acquThread == null) {
            throw new IllegalStateException("Not started yet");
        }
        this.acquThread.unregister(tagID);
    }

    static String getTagID(final String msg) {

        final StringTokenizer token = new StringTokenizer(msg);

        int i = 0;

        while(token.hasMoreTokens()) {

            final String elem = token.nextToken();

            if((i == 0)) {
                return elem;
            }
            i++;
        }
        return null;

    }

    static double getConfidence(final String msg) {
        final StringTokenizer token = new StringTokenizer(msg);
        int i = 0;
        while(token.hasMoreTokens()) {
            final String elem = token.nextToken();
            if((i == 4)) {
                return Double.parseDouble(elem);
            }
            i++;
        }
        return -1;

    }

    static org.sercho.masp.context.providers.location.Vector getPos(final String msg) {
        final StringTokenizer tokenizer = new StringTokenizer(msg);
        int i = -2;
        double x = 0, y = 0, z = 0;
        String token;

        while(tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();
            if(i == -1) {
                x = Double.parseDouble(token);
            } else if(i == 0) {
                y = Double.parseDouble(token);
            } else if(i == 1) {
                z = Double.parseDouble(token);
            }
            i++;
        }

        return (i < 2) ? null
                : new org.sercho.masp.context.providers.location.Vector(x, y, z);
    }

    private class UBAcquisitionThread extends Thread {

        public UBAcquisitionThread(final UBLocProviderClient _provider) {
            super(UBAcquisitionThread.class.getSimpleName());
            this.registeredTags = new Vector<UBTag>();
            this.observedTags = new Vector<UBTag>();
            this.provider = _provider;
        }

        public void setRegisteredTag(final UBTag newTag) {
            for(int i = 0; i < this.registeredTags.size(); i++) {
                if(this.registeredTags.get(i).tagID.compareTo(newTag.tagID) == 0) {
                    this.registeredTags.get(i).position = newTag.position;
                    this.registeredTags.get(i).confidence = newTag.confidence;
                    newPositionScaled(newTag.tagID, newTag.position.getX(), newTag.position.getY(), newTag.position.getZ(), newTag.confidence);
                    return;
                }
            }
        }

        public void setObservedTag(final UBTag newTag) {
            for(int i = 0; i < this.observedTags.size(); i++) {
                if(this.observedTags.get(i).tagID.compareTo(newTag.tagID) == 0) {
                    this.observedTags.get(i).position = newTag.position;
                    this.observedTags.get(i).orientation = newTag.orientation;
                    this.observedTags.get(i).confidence = newTag.confidence;
                    return;
                }
            }
            this.observedTags.add(newTag);
        }

        public boolean register(final String tagID) {
            return this.registeredTags.add(new UBTag(tagID));
        }

        public void unregister(final String tagID) {
            int idx = -1;
            for(int i = 0; i < this.registeredTags.size(); i++) {
                if(this.registeredTags.get(i).tagID.compareTo(tagID) == 0) {
                    idx = i;
                }
            }
            if(idx != -1) {
                this.registeredTags.remove(idx);
            }
        }

        public org.sercho.masp.context.providers.location.Vector getPosition(final String tagID) {
            for(int i = 0; i < this.observedTags.size(); i++) {
                if(this.observedTags.get(i).tagID.compareTo(tagID) == 0) {
                    return this.observedTags.get(i).position;
                }
            }
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
            while(this.running) {
                final String msg = this.provider.get_info();
                if(msg != null) {
                    final String TagID = getTagID(msg);
                    final org.sercho.masp.context.providers.location.Vector pos = getPos(msg);
                    final double confidence = getConfidence(msg);
                    if((TagID != null) && (pos != null)) {
                        final UBTag currentTag = new UBTag(TagID);
                        currentTag.position = pos;
                        currentTag.confidence = confidence;
                        setObservedTag(currentTag);
                        setRegisteredTag(currentTag);
                    }
                }
                try {
                    sleep(10);
                }
                catch(final InterruptedException e) {
                    // stop thread
                    return;
                }
            }
        }

        volatile boolean running = true;

        private Vector<UBTag> registeredTags = null;

        private Vector<UBTag> observedTags = null;

        private UBLocProviderClient provider = null;

    }

    private volatile UBLocProviderClient locProvider = null;

    private volatile UBAcquisitionThread acquThread = null;
}