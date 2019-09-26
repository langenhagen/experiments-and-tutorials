/**
 * 
 */
package org.sercho.masp.models.Context.gui.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;
import org.sercho.masp.models.Context.gui.PopupInvoker;

import de.dailab.masp.models.MetaEditor.swing.ExecutableElementDialog;
import de.dailab.masp.models.MetaMetaModel.DataType;
import de.dailab.masp.models.MetaMetaModel.ExecutableElement;
import de.dailab.masp.models.MetaMetaModel.Parameter;

/**
 * An {@link ActionListener} for the {@link ExecutableElementDialog}.
 * 
 * @author Andre Schulz
 * @since 1.2.47
 */
public class ExecutableElementDialogActionListener implements ActionListener {

    /**
     * A {@link Thread} to execute the {@link ExecutableElement} when the
     * parameters are submitted.
     * 
     * @author Andre Schulz
     * @since 1.2.47
     */
    private class ExecutionThread extends Thread {

        /**
         * The related {@link ExecutableElementDialog}.
         */
        private final ExecutableElementDialog executableElementDialog;

        /**
         * The {@link ExecutableElement} to execute.
         */
        private final ExecutableElement executableElement;

        /**
         * The element where the action takes place.
         */
        private final Object element;

        /**
         * Constructor for {@link ExecutionThread}.
         * 
         * @param executableElementDialog
         *            The related {@link ExecutableElementDialog}.
         * @param executableElement
         *            The {@link ExecutableElement} to execute.
         * @param element
         *            The element where the action takes place.
         */
        public ExecutionThread(final ExecutableElementDialog executableElementDialog,
                final ExecutableElement executableElement, final Object element) {
            this.executableElementDialog = executableElementDialog;
            this.executableElement = executableElement;
            this.element = element;
        }

        @Override
        public void run() {
            try {
                final EList<Object> parameters = this.executableElementDialog.awaitSelection();

                if(parameters != null) {
                    if(LOG.isDebugEnabled()) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append("executing ");
                        sb.append(this.executableElement.getName());
                        sb.append(", parameters: ");
                        sb.append(parameters);
                        LOG.debug(sb.toString());
                    }

                    try {
                        this.executableElement.execute(this.element, parameters);
                    }
                    catch(final Exception e) {
                        LOG.error("exception while executing executable element", e);

                        if(e.getCause() != null) {
                            JOptionPane.showMessageDialog(ExecutableElementDialogActionListener.this.popupInvoker.getParentFrame(), e.getClass().getSimpleName() + ": " + e.getCause().getClass().getSimpleName() + ": " + e.getCause().getLocalizedMessage(), "Error while executing executable element", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(ExecutableElementDialogActionListener.this.popupInvoker.getParentFrame(), e.getClass().getSimpleName() + ": " + e.getLocalizedMessage(), "Error while executing executable element", JOptionPane.ERROR_MESSAGE);
                        }

                        // restart ExecutableElementDialog
                        ExecutableElementDialogActionListener.this.actionPerformed(new ActionEvent(this, NORM_PRIORITY, "retry action"));
                    }

                    if(LOG.isDebugEnabled()) {
                        LOG.debug("successfully executed " + this.executableElement.getName());
                    }
                }
            }
            catch(final InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * The logger.
     */
    private static final transient Log LOG = LogFactory.getLog(ExecutableElementDialogActionListener.class);

    /**
     * The {@link ExecutableElement}.
     */
    private final ExecutableElement executableElement;

    /**
     * The element where the action takes place.
     */
    private final Object element;

    /**
     * The {@link Object} representing the model where the element belongs to.
     */
    private final Object model;

    /**
     * The {@link PopupInvoker}.
     */
    private final PopupInvoker popupInvoker;

    /**
     * Constructor for {@link ExecutableElementDialogActionListener}s.
     * 
     * @param element
     *            The element where the action takes place.
     * @param model
     *            The model of the element.
     * @param executableElement
     *            The {@link ExecutableElement} to execute.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    public ExecutableElementDialogActionListener(final Object element,
            final Object model, final ExecutableElement executableElement,
            final PopupInvoker popupInvoker) {
        this.element = element;
        this.model = model;
        this.executableElement = executableElement;
        this.popupInvoker = popupInvoker;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final ExecutableElementDialog executableElementDialog = new ExecutableElementDialog(this.executableElement, this.model);

        if(LOG.isDebugEnabled()) {
            LOG.debug("parameters: " + this.executableElement.getParameters().size());
        }

        executableElementDialog.setSize(700, getHeight(this.executableElement.getParameters()));
        executableElementDialog.setLocationRelativeTo(null);
        executableElementDialog.setVisible(true);

        // start a thread to execute the executable element
        final Thread thread = new ExecutionThread(executableElementDialog, this.executableElement, this.element);
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Returns the height for the {@link ExecutableElementDialog} in relation to
     * the <code>parameters</code>.
     * 
     * @param parameters
     *            The {@link List} of {@link Parameter}s.
     * @return The height for the {@link ExecutableElementDialog}.
     */
    private int getHeight(final List<Parameter> parameters) {
        int height = 190;

        for(final Parameter parameter : parameters) {
            if(parameter.getType() instanceof DataType) {
                height += 60;
            } else {
                height += 120;
            }
        }

        return height;
    }
}
