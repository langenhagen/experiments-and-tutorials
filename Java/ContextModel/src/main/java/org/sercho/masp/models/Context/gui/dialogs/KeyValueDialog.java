/**
 * 
 */
package org.sercho.masp.models.Context.gui.dialogs;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.gui.actionListener.KeyValueActionListener;
import org.sercho.masp.models.Context.gui.enums.ActionType;

/**
 * A {@link Dialog} for a key and a value.
 * 
 * @author Andre Schulz
 * @since 1.2.47
 */
public class KeyValueDialog extends Dialog {

    /**
     * 
     */
    private static final long serialVersionUID = 515526074357470896L;

    /**
     * The logger.
     */
    static final transient Log LOG = LogFactory.getLog(KeyValueDialog.class);

    private String key;

    private String value;

    private final String initialKey;

    private String initialValue;

    private final KeyValueActionListener keyValueActionListener;

    private final ActionType actionType;

    private JTextField keyTF;

    private JTextField valueTF;

    /**
     * The message to display.
     */
    private final String message;

    /**
     * @param owner
     * @param message
     */
    public KeyValueDialog(final Frame owner, final String title, final String message,
            final String initialKey, final String initialValue,
            final KeyValueActionListener keyValueActionListener,
            final ActionType actionType) {
        super(owner, message);

        this.message = message;
        this.initialKey = initialKey;
        this.initialValue = initialValue;
        this.keyValueActionListener = keyValueActionListener;
        this.actionType = actionType;

        super.setTitle(title);
        this.init();
    }

    /**
     * Copy constructor.
     * 
     * @param keyValueDialog
     */
    public KeyValueDialog(final KeyValueDialog keyValueDialog) {
        super((Frame)keyValueDialog.getOwner(), keyValueDialog.getMessage());

        this.message = keyValueDialog.getMessage();
        this.initialKey = keyValueDialog.initialKey;

        if(keyValueDialog.getValue() != null) {
            this.initialValue = keyValueDialog.getValue();
        } else {
            this.initialValue = keyValueDialog.initialValue;
        }

        this.keyValueActionListener = keyValueDialog.keyValueActionListener;
        this.actionType = keyValueDialog.actionType;

        super.setTitle(keyValueDialog.getTitle());
        this.init();
    }

    public String getKey() {
        return this.key;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public String getValue() {
        return this.value;
    }

    public void init() {
        super.addWindowListener(new WindowListener() {

            @Override
            public void windowActivated(final WindowEvent e) {
            }

            @Override
            public void windowClosed(final WindowEvent e) {
            }

            @Override
            public void windowClosing(final WindowEvent e) {
                KeyValueDialog.this.key = null;
                KeyValueDialog.this.value = null;
            }

            @Override
            public void windowDeactivated(final WindowEvent e) {
            }

            @Override
            public void windowDeiconified(final WindowEvent e) {
            }

            @Override
            public void windowIconified(final WindowEvent e) {
            }

            @Override
            public void windowOpened(final WindowEvent e) {
            }
        });

        this.initComponents();

        this.pack();
        this.setLocationRelativeTo(super.getOwner());
        this.setVisible(true);

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.
     * PropertyChangeEvent)
     */
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("propertyChange " + evt);
            LOG.debug(super.optionPane.getValue());
            if(super.optionPane.getValue() != null) {
                LOG.debug(super.optionPane.getValue().equals(Dialog.Option.OK) + " " + (super.optionPane.getValue().equals(Dialog.Option.CANCEL)));
            }
        }

        if(super.optionPane.getValue() != null) {
            if(super.optionPane.getValue().equals(Dialog.Option.OK)) {
                LOG.debug("OK");
                this.key = this.keyTF.getText();
                this.value = this.valueTF.getText();
                this.keyValueActionListener.finishAction(Dialog.Option.OK, this.actionType, this);
                super.setVisible(false);
            } else if(super.optionPane.getValue().equals(Dialog.Option.CANCEL)) {
                LOG.debug("Cancel");
                this.key = null;
                this.value = null;
                this.keyValueActionListener.finishAction(Dialog.Option.CANCEL, this.actionType, this);
                super.setVisible(false);
            }
        }
    }

    private void initComponents() {
        this.keyTF = new JTextField(10);
        this.valueTF = new JTextField(10);

        if(this.initialKey != null) {
            this.keyTF.setText(this.initialKey);
        }

        if(this.initialValue != null) {
            this.valueTF.setText(this.initialValue);
        }

        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("Key:"));
        panel.add(this.keyTF);
        panel.add(new JLabel("Value:"));
        panel.add(this.valueTF);

        super.initComponents(panel);

        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentShown(final ComponentEvent ce) {
                KeyValueDialog.this.keyTF.requestFocusInWindow();
            }
        });
    }

}
