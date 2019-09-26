package org.sercho.masp.models.Context.gui.dialogs;

import java.awt.Frame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JTextField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.gui.actionListener.StringDialogActionListener;
import org.sercho.masp.models.Context.gui.enums.ActionType;

/**
 * A {@link Dialog} for {@link String}s.
 * 
 * @author Andre Schulz
 * @since 1.2.8
 */
public class StringDialog extends Dialog {

    /**
     * The logger.
     */
    static final transient Log LOG = LogFactory.getLog(StringDialog.class);

    private static final long serialVersionUID = 1;

    private String string;

    private JTextField textField;

    private final String initialString;

    private final StringDialogActionListener stringDialogActionListener;

    private final ActionType actionType;

    /**
     * Constructs a new {@link StringDialog}.
     * 
     * @param parentComponent
     * @param title
     * @param message
     * @param initialString
     * @param messageType
     * @param stringDialogActionListener
     * @param actionType
     */
    public StringDialog(final Frame parentComponent, final String title,
            final String message, final String initialString, final int messageType,
            final StringDialogActionListener stringDialogActionListener,
            final ActionType actionType) {
        super(parentComponent, message);

        this.initialString = initialString;
        this.stringDialogActionListener = stringDialogActionListener;
        this.actionType = actionType;

        super.setTitle(title);
        this.init();
    }

    /**
     * Copy constructor.
     * 
     * @param stringDialog
     *            The {@link StringDialog} to copy.
     */
    public StringDialog(final StringDialog stringDialog) {
        super((Frame)stringDialog.getOwner(), stringDialog.getMessage());
        this.initialString = stringDialog.initialString;
        this.stringDialogActionListener = stringDialog.stringDialogActionListener;
        this.actionType = stringDialog.actionType;

        super.setTitle(stringDialog.getTitle());
        this.init();
    }

    public String getString() {
        super.optionPane.setValue(null);
        return this.string;
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
                StringDialog.this.string = null;
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

        initComponents();

        this.pack();
        this.setLocationRelativeTo(super.getOwner());
        this.setVisible(true);
    }

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
                this.string = this.textField.getText();
                this.stringDialogActionListener.finishAction(Dialog.Option.OK, this.actionType, this);
                super.setVisible(false);
            } else if(super.optionPane.getValue().equals(Dialog.Option.CANCEL)) {
                LOG.debug("Cancel");
                this.string = null;
                this.stringDialogActionListener.finishAction(Dialog.Option.CANCEL, this.actionType, this);
                super.setVisible(false);
            }
        }
    }

    private void initComponents() {
        this.textField = new JTextField(10);

        if(this.initialString != null) {
            this.textField.setText(this.initialString);
        }

        super.initComponents(this.textField);

        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentShown(final ComponentEvent ce) {
                StringDialog.this.textField.requestFocusInWindow();
            }
        });
    }

}// class StringDialog

