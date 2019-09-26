package org.sercho.masp.models.Context.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.sercho.masp.models.Context.gui.enums.ElementType;
import org.sercho.masp.models.Context.gui.forms.ElementForm;

/**
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public class CreateElementPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 3559936677983673352L;

    private final VisualizerManager manager;

    public CreateElementPanel(final ElementType elementType,
            final VisualizerManager manager, final ElementForm form) {
        super(new BorderLayout());
        this.manager = manager;

        createFields(form);
    }

    private void createFields(final ElementForm form) {
        final JPanel panel = new JPanel();
        final JPanel panel2 = new JPanel();
        final JPanel labelPane = new JPanel(new GridLayout(0, 1));
        final JPanel fieldPane = new JPanel(new GridLayout(0, 1));
        final JPanel labelErrorPane = new JPanel(new GridLayout(0, 1));

        final JPanel buttonsPane = new JPanel(new GridLayout(0, form.getButtons().size()));

        for(final String fieldString : form.getFields()) {
            final JTextField field = new JTextField(form.getFieldValue(fieldString));
            form.setFieldValue(fieldString, field);
            fieldPane.add(field);
            labelPane.setForeground(Color.WHITE);
            labelPane.add(new JLabel(fieldString));

            final String error = form.getFieldErrorValue(fieldString);
            if(error == null) {

                labelErrorPane.add(new JLabel(""));
                labelErrorPane.setBackground(new Color(98, 98, 98));
            } else {
                final JLabel errorLabel = new JLabel(error);
                errorLabel.setForeground(Color.RED);
                labelErrorPane.add(errorLabel);
            }

        }

        for(final String comboString : form.getCombos()) {
            final JComboBox comboBox = new JComboBox(form.getComboOptions(comboString));
            form.setComboValue(comboString, comboBox);
            labelPane.add(new JLabel(comboString));
            comboBox.setName(comboString);
            comboBox.addActionListener(form.getListener(this.manager));
            fieldPane.add(comboBox);
        }

        for(final String buttonString : form.getButtons()) {
            final JButton button = new JButton(buttonString);
            button.setName(buttonString);
            button.addActionListener(form.getListener(this.manager));
            buttonsPane.add(button);
        }

        panel.add(labelPane, BorderLayout.CENTER);
        panel.add(fieldPane, BorderLayout.LINE_END);
        panel2.add(panel, BorderLayout.CENTER);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        if(form.containsErrors()) {
            panel2.add(labelErrorPane, BorderLayout.LINE_END);
        }
        add(panel2, BorderLayout.CENTER);
        labelPane.setBackground(new Color(98, 98, 98));
        add(buttonsPane, BorderLayout.PAGE_END);
        panel.setBackground(new Color(98, 98, 98));
        fieldPane.setBackground(new Color(98, 98, 98));
        labelPane.setBackground(new Color(98, 98, 98));
        panel2.setBackground(new Color(98, 98, 98));
        this.setBackground(new Color(98, 98, 98));
        final TitledBorder rotulo2 = BorderFactory.createTitledBorder("New element");
        rotulo2.setTitleColor(Color.white);
        panel2.setBorder(rotulo2);

    }
}
