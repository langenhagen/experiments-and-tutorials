/**
 * 
 */
package org.sercho.masp.context.providers.location;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.ConfigurationPropertyUtility;

import de.dailab.masp.models.MetaMetaModel.AbstractExternalProcess;
import de.dailab.masp.models.MetaMetaModel.ModelCallback;

/**
 * <code>AbstractLocalisationProvider</code> helps implementing
 * {@link LocalisationProvider}s.
 * 
 * @author Grzegorz Lehmann
 * @since 1.1
 */
public abstract class AbstractLocalisationProvider extends AbstractExternalProcess
        implements LocalisationProvider {

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8123663781519243462L;

    /**
     * <code>CALLBACK_TAG_GONE</code>
     */
    private static final String CALLBACK_TAG_GONE = "tagGone";

    /**
     * <code>CALLBACK_NEW_POSITION</code>
     */
    private static final String CALLBACK_NEW_POSITION = "newPosition";

    protected final transient Log log = LogFactory.getLog(AbstractLocalisationProvider.class);

    private ModelCallback modelCallback;

    /**
     * {@inheritDoc}
     */
    @Override
    protected synchronized final void startHook(final Object configuration, final ModelCallback callback) {
        this.modelCallback = callback;
        start(ConfigurationPropertyUtility.getConfigurationMap(configuration));
    }

    protected abstract void start(Map<String, String> configuration);

    /**
     * {@inheritDoc}
     */
    @Override
    protected synchronized final void stopHook() {
        this.modelCallback = null;
        close();
    }

    protected abstract void close();

    /**
     * <code>newPosition</code> informs this observer about a new position of a
     * tag.
     * 
     * @param tagID
     *            identifier of the tag, <code>null</code> disallowed
     * @param x
     *            new X coordinate of the tag
     * @param y
     *            new Y coordinate of the tag
     * @param z
     *            new Z coordinate of the tag
     * @param position
     *            new position of the tag
     * 
     * @param confidence
     *            describes the probability of the position's correctness
     *            between 0 and 1
     * @throws IllegalArgumentException
     *             if any parameter is <code>null</code>
     */
    protected synchronized final void newPosition(final String tagID, final double x, final double y, final double z, final double confidence) {
        if(this.modelCallback == null) {
            this.log.warn("No ModelCallback set");
        }
        try {
            this.modelCallback.execute(CALLBACK_NEW_POSITION, tagID, Double.valueOf(x), Double.valueOf(y), Double.valueOf(z));
        }
        catch(final InvocationTargetException e) {
            this.log.error("Failed to execute " + CALLBACK_NEW_POSITION + " in ModelCallback, error: " + e.getTargetException().getMessage(), e);
        }
    }

    /**
     * <code>tagGone</code> informs this observer about the disappearance of a
     * tag.
     * 
     * @param tagID
     *            identifier of the tag, <code>null</code> disallowed
     * @throws IllegalArgumentException
     *             if <code>tagID</code> is <code>null</code>
     */
    protected synchronized final void tagGone(final String tagID) {
        this.log.debug("Tag is gone: " + tagID);
        if(this.modelCallback == null) {
            this.log.warn("No ModelCallback set");
        }
        try {
            this.modelCallback.execute(CALLBACK_TAG_GONE, tagID);
        }
        catch(final InvocationTargetException e) {
            this.log.error("Failed to execute " + CALLBACK_TAG_GONE + " in ModelCallback, error: " + e.getTargetException().getMessage(), e);
        }
    }
}