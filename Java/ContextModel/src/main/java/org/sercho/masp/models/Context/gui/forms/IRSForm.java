package org.sercho.masp.models.Context.gui.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.EnvironmentElement;
import org.sercho.masp.models.Context.gui.VisualizerManager;
import org.sercho.masp.models.Context.gui.enums.ElementType;

/**
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public abstract class IRSForm extends ElementForm {

    // private static String[] FIELDS;

    private static final String[] COMBOS = {"IR type"};

    private static final String[] BUTTONS = {"Add", "Finish"};

    public IRSForm(final String[] fields, final String[] buttons,
            final EnvironmentElement element, final String[] combo, final int frameId) {

        super(fields, BUTTONS, COMBOS, element, frameId);
        // FIELDS = fields;
        setComboOptions(COMBOS[0], combo);
        System.out.println(ContextPackage.Literals.ACTIVITY__DESCRIPTION.getName());
        // ContextPackage.Literals.INTERACTION_RESOURCE.get
    }

    @Override
    public void fillValues(final EnvironmentElement element) {
        // TODO Auto-generated method stub

    }

    @Override
    public ActionListener getListener(final VisualizerManager manager) {

        final ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                // // Handle open button action.
                if(e.getSource() instanceof JButton) {
                    if(((JButton)e.getSource()).getName().equals(BUTTONS[0])) {
                        System.out.println("Aceptado");
                        createElement(manager);
                        // manager.closeCreateElementPanel(frameId);
                    } else if(((JButton)e.getSource()).getName().equals(BUTTONS[1])) {
                        manager.closeCreateElementPanel(IRSForm.this.frameId);
                    }
                } else if(e.getSource() instanceof JComboBox) {
                    if(((JComboBox)e.getSource()).getName().equals(COMBOS[0])) {
                        manager.changeCreateElementPanel(ElementType.valueOf(getComboValue(((JComboBox)e.getSource()).getName())), "", IRSForm.this.frameId);
                        System.out.println("Testing");
                    }
                }
            }

        };
        return listener;
    }
}
