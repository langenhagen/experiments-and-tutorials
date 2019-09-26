/**
 * 
 */
package org.sercho.masp.models.Context.gui.dialogs;

import java.awt.Frame;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Superclass for dialogs.
 * 
 * @author Andre Schulz
 * @since 1.2.47
 */
public abstract class Dialog extends JDialog implements PropertyChangeListener {

    public enum Option {

        /**
         * OK option.
         */
        OK("OK"),

        /**
         * Cancel option.
         */
        CANCEL("Cancel");

        private String text;

        private Option(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return this.text;
        }
    }

    /**
     * 
     */
    private static final long serialVersionUID = -8597697478548074744L;

    /**
     * The message to display.
     */
    private final String message;

    /**
     * The option to choose from.
     */
    protected Option[] options;

    protected JOptionPane optionPane;

    /**
     * Creates a new {@link Dialog}.
     * 
     * @param owner
     *            The Frame from which the dialog is displayed
     * 
     * @param message
     *            The message to display.
     */
    public Dialog(final Frame owner, final String message) {
        super(owner);
        this.options = new Option[]{Option.CANCEL, Option.OK};
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    protected void initComponents(final Object interactionObject) {
        final Object[] array = {this.message, interactionObject};
        this.optionPane = new JOptionPane(array, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null, this.options, Option.OK);
        this.optionPane.addPropertyChangeListener(this);
        setContentPane(this.optionPane);
    }
}
