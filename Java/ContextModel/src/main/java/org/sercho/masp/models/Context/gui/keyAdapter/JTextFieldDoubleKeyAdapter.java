/**
 * 
 */
package org.sercho.masp.models.Context.gui.keyAdapter;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

/**
 * This {@link KeyAdapter} checks the text of an {@link JTextField} whether it
 * can be parsed to a double number or not. If not the {@link JTextField} is
 * colored to indicate the error.
 * 
 * @author Andre Schulz
 * @since 1.2.46
 */
public class JTextFieldDoubleKeyAdapter extends KeyAdapter {

    private static final Color TEXT_FIELD_BACKGROUND_OK_COLOR = Color.WHITE;

    private static final Color TEXT_FIELD_BACKGROUND_ERROR_COLOR = Color.RED;

    private static final Color TEXT_FIELD_FOREGROUND_OK_COLOR = Color.BLACK;

    private static final Color TEXT_FIELD_FOREGROUND_ERROR_COLOR = Color.WHITE;

    private final JTextField textField;

    public JTextFieldDoubleKeyAdapter(final JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        try {
            Double.parseDouble(this.textField.getText());
            // reset error
            this.textField.setBackground(TEXT_FIELD_BACKGROUND_OK_COLOR);
            this.textField.setForeground(TEXT_FIELD_FOREGROUND_OK_COLOR);
        }
        catch(final RuntimeException e2) {
            // indicate error
            this.textField.setBackground(TEXT_FIELD_BACKGROUND_ERROR_COLOR);
            this.textField.setForeground(TEXT_FIELD_FOREGROUND_ERROR_COLOR);
        }
    }
}
