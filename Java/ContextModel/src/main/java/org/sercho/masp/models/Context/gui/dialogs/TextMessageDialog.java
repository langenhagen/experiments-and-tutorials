/**
 * 
 */
package org.sercho.masp.models.Context.gui.dialogs;

import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.sercho.masp.models.Context.gui.dialogs.Dialog.Option;

/**
 * A {@link JDialog} to display a {@link JTextArea} containing a message.
 * 
 * @author Andre Schulz
 * @since 1.3.27
 */
public class TextMessageDialog extends JDialog implements PropertyChangeListener {

    /**
     * The <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = 1L;

    private String headline;

    /**
     * The message to display.
     */
    private String message;

    private JTextArea messageTextArea;

    protected JOptionPane optionPane;

    /**
     * The option to choose from.
     */
    protected Option[] options;

    public TextMessageDialog(Window parent, String title, String headline, String message) {
        super(parent);

        this.headline = headline;
        this.message = message;
        super.setTitle(title);
        this.init();
    }

    @Override
    public void propertyChange(PropertyChangeEvent arg0) {
        if(this.optionPane.getValue().equals(Option.OK)) {
            this.optionPane.removePropertyChangeListener(this);
            super.dispose();
        }
    }

    /**
     * 
     */
    private void init() {
        initComponents();
        this.pack();
        this.setLocationRelativeTo(super.getOwner());
        this.setVisible(true);
    }

    /**
     * 
     */
    private void initComponents() {
        this.messageTextArea = new JTextArea(message);
        this.messageTextArea.setEditable(false);

        this.options = new Option[]{Option.OK};
        final Object[] array = {headline, this.messageTextArea};
        this.optionPane = new JOptionPane(array, JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_OPTION, null, this.options, Option.OK);
        this.optionPane.addPropertyChangeListener(this);
        setContentPane(this.optionPane);
    }
}
