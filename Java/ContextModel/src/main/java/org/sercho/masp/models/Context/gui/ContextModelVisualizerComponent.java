/**
 * 
 */
package org.sercho.masp.models.Context.gui;

import java.awt.GraphicsEnvironment;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.JFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.Environment;

import de.dailab.masp.models.MegaModel.Component;
import de.dailab.masp.models.MegaModel.ModelResourceIdentifier;

/**
 * <code>ContextModelVisualizerComponent</code> starts the context model
 * visualization GUI for each context model passed to it.
 * 
 * @author Grzegorz Lehmann
 * @since 3.0.32
 */
public final class ContextModelVisualizerComponent extends Component {

    private volatile List<VisualizerManager> visualizers = new Vector<VisualizerManager>();

    /**
     * <code>LOG</code> for logging.
     */
    public static final transient Log LOG = LogFactory.getLog(ContextModelVisualizerComponent.class);

    /**
     * {@inheritDoc}
     */
    @Override
    final public void setModelResources(final Map<ModelResourceIdentifier, Object> modelResources) {
        if(GraphicsEnvironment.isHeadless()) {
            LOG.warn("Skipping start of context model visualizer because running in headless environment with no graphics support");
            return;
        }
        // pass the context models to the visualizer
        Object model;
        for(final Entry<ModelResourceIdentifier, Object> modelResourceEntry : modelResources.entrySet()) {
            model = modelResourceEntry.getValue();
            if(model instanceof Environment) {
                // start a visualizer for the context model
                try {
                    this.visualizers.add(new VisualizerManager((Environment)model, VisualizerManager.DisplayType.STANDALONE));
                    if(LOG.isDebugEnabled()) {
                        LOG.debug("Started visualizer for environment model " + modelResourceEntry.getKey());
                    }
                }
                catch(final Exception e) {
                    // log error and continue
                    LOG.error("Failed to start visualizer for environment model " + modelResourceEntry.getKey() + ", due to error: " + e.getMessage(), e);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void startHook(final Map<String, String> configuration) {
        // nothing to do here
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void stopHook() {
        // hide the GUIs
        synchronized(this.visualizers) {
            JFrame visualizerFrame;
            for(final VisualizerManager visualizer : this.visualizers) {
                // dispose the frame
                try {
                    visualizerFrame = visualizer.getMainFrame();
                    if(visualizerFrame != null) {
                        visualizerFrame.setVisible(false);
                        visualizerFrame.dispose();
                    }
                    // close the environment
                    visualizer.closeEnvironment();
                }
                catch(final RuntimeException e) {
                    LOG.warn("Unexpected error occurred while closing visualizer, error: " + e.getMessage(), e);
                }
            }
            // clear visualizers list
            this.visualizers.clear();
        }
    }
}