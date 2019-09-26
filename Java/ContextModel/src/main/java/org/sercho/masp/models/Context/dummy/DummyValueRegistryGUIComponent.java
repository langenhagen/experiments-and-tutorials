/**
 * 
 */
package org.sercho.masp.models.Context.dummy;

import java.awt.GraphicsEnvironment;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.dailab.masp.models.MegaModel.Component;
import de.dailab.masp.models.MegaModel.ModelResourceIdentifier;

/**
 * <code>DummyValueRegistryGUIComponent</code> starts a {@link JFrame} with the
 * {@link DummyValueRegistryGUIComponent}. This component does not need any
 * models.
 * 
 * @author Grzegorz Lehmann
 * @since 3.0.32
 */
public final class DummyValueRegistryGUIComponent extends Component {

    private transient JFrame dummyRegistryFrame;

    /**
     * <code>LOG</code> for logging.
     */
    public static final transient Log LOG = LogFactory.getLog(DummyValueRegistryGUIComponent.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void setModelResources(final Map<ModelResourceIdentifier, Object> arg0) {
        // nothing to do here
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void startHook(final Map<String, String> configuration) {
        if(GraphicsEnvironment.isHeadless()) {
            LOG.warn("Skipping start of context model visualizer because running in headless environment with no graphics support");
            return;
        }
        this.dummyRegistryFrame = new JFrame("Dummy Sensor/Actor Value Registry");
        this.dummyRegistryFrame.getContentPane().add(new DummyValueRegistryJPanel());
        this.dummyRegistryFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.dummyRegistryFrame.setSize(500, 800);
        this.dummyRegistryFrame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void stopHook() {
        if(this.dummyRegistryFrame != null) {
            this.dummyRegistryFrame.setVisible(false);
            this.dummyRegistryFrame.dispose();
            this.dummyRegistryFrame = null;
        }
    }
}