/**
 * 
 */
package org.sercho.masp.models.Context.gui.dialogs;

import java.awt.Frame;
import java.beans.PropertyChangeEvent;

import javax.swing.JDialog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.Vector;
import org.sercho.masp.models.Context.gui.VectorWrapper;
import org.sercho.masp.models.Context.gui.panels.VectorPanel;

/**
 * A {@link JDialog} to edit {@link Vector}s.
 * 
 * @author Andre Schulz
 * @since 1.2.47
 */
public class VectorDialog extends Dialog {

    /**
     * 
     */
    private static final long serialVersionUID = -7632263296003372132L;

    /**
     * The logger.
     */
    static final transient Log LOG = LogFactory.getLog(VectorDialog.class);

    private final VectorWrapper vectorWrapper;

    private VectorPanel vectorPanel;

    public VectorDialog(final Frame parentComponent, final String title,
            final String message, final VectorWrapper vectorWrapper, final int messageType) {
        super(parentComponent, message);

        this.vectorWrapper = vectorWrapper;

        super.setTitle(title);

        initComponents();

        this.pack();
        this.setLocationRelativeTo(parentComponent);
        this.setVisible(true);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("propertyChange " + evt);
        }

        if(super.optionPane.getValue().equals(Dialog.Option.OK)) {
            if(LOG.isDebugEnabled()) {
                LOG.debug("vector changed");
            }

            this.vectorWrapper.getVector().setCoordinates(this.vectorPanel.getVectorX(), this.vectorPanel.getVectorY(), this.vectorPanel.getVectorZ());
            super.setVisible(false);
        } else if(this.optionPane.getValue().equals(Dialog.Option.CANCEL)) {
            super.setVisible(false);
        }
    }

    /**
     * 
     */
    private void initComponents() {
        this.vectorPanel = new VectorPanel(this.vectorWrapper);
        super.initComponents(this.vectorPanel);
    }
}
