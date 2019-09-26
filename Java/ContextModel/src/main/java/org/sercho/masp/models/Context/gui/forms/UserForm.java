package org.sercho.masp.models.Context.gui.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.sercho.masp.models.Context.EnvironmentElement;
import org.sercho.masp.models.Context.User;
import org.sercho.masp.models.Context.gui.VisualizerManager;

/**
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public class UserForm extends ElementForm {

    private static final String[] FIELDS = {"Name", "Identifier", "Birthday"};

    private static final String[] BUTTONS = {"Accept", "Cancel"};

    public UserForm(final EnvironmentElement element, final int frameId) {
        super(FIELDS, BUTTONS, null, element, frameId);

    }

    @Override
    public ActionListener getListener(final VisualizerManager manager) {
        final ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if(((JButton)e.getSource()).getName().equals(BUTTONS[0])) {
                    UserForm.this.errors.clear();
                    if(checkValues(manager)) {
                        System.out.println("Aceptado");
                        createElement(manager);
                        manager.closeCreateElementPanel(UserForm.this.frameId);
                    } else {
                        System.out.println("Nein");
                        manager.formValuesError(UserForm.this);
                    }

                } else if(((JButton)e.getSource()).getName().equals(BUTTONS[1])) {
                    manager.closeCreateElementPanel(UserForm.this.frameId);
                }

            }

        };
        return listener;
    }

    @Override
    public void fillValues(final EnvironmentElement element) {
        if(element != null) {
            final User user = (User)element;
            super.setFieldValue(FIELDS[0], new JTextField(user.getName()));
            super.setFieldValue(FIELDS[1], new JTextField(user.getId()));
            super.setFieldValue(FIELDS[2], new JTextField(user.getBirthDate()));
        }

    }

    @Override
    protected void createElement(final VisualizerManager manager) {
        manager.addingElement();
        manager.createUser(UserForm.this.getFieldValue(FIELDS[0]), UserForm.this.getFieldValue(FIELDS[1]), UserForm.this.getFieldValue(FIELDS[2]));
    }

    @Override
    protected boolean checkValues(final VisualizerManager manager) {
        if(UserForm.this.getFieldValue(FIELDS[0]).length() < 1) {
            this.errors.put(FIELDS[0], new String("Min. one character"));
        }
        if(UserForm.this.getFieldValue(FIELDS[1]).length() < 1) {
            this.errors.put(FIELDS[1], new String("Min. one character"));
        }
        if(UserForm.this.getFieldValue(FIELDS[2]).length() < 1) {
            this.errors.put(FIELDS[2], new String("Date format: dd.mm.yy"));
        }

        final DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMAN);
        try {

            format.parse(UserForm.this.getFieldValue(FIELDS[2]));
        }
        catch(final ParseException e) {
            this.errors.put(FIELDS[2], new String("Date format: dd.mm.yy"));
        }

        if(!manager.checkUniqueId(UserForm.this.getFieldValue(FIELDS[1]))) {
            this.errors.put(FIELDS[1], "The identifiers is not unique");
        }

        if(this.errors.size() != 0) {
            return false;
        } else {
            return true;
        }

    }
}
