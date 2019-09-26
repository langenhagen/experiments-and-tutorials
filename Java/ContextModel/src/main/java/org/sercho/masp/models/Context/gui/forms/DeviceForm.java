package org.sercho.masp.models.Context.gui.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import org.sercho.masp.models.Context.EnvironmentElement;
import org.sercho.masp.models.Context.gui.VisualizerManager;
import org.sercho.masp.models.Context.gui.enums.ElementType;

/**
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public class DeviceForm extends ElementForm {

    private static final String[] FIELDS = {"Name", "Identifier"};

    private static final String[] BUTTONS = {"Accept", "Cancel", "Add IRs"};

    private static final String[] COMBOS = {"mobile"};

    private static final String[] COMBO_MOBILE = {"true", "false"};

    public DeviceForm(final EnvironmentElement element, final int frameId) {
        super(FIELDS, BUTTONS, COMBOS, element, frameId);
        setComboOptions(COMBOS[0], COMBO_MOBILE);
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
                        manager.closeCreateElementPanel(DeviceForm.this.frameId);
                    } else if(((JButton)e.getSource()).getName().equals(BUTTONS[1])) {
                        manager.clearTempIR();
                        manager.closeCreateElementPanel(DeviceForm.this.frameId);
                    } else if(((JButton)e.getSource()).getName().equals(BUTTONS[2])) {
                        manager.startCreateElementPanel(ElementType.DISPLAY);
                    }
                } else if(e.getSource() instanceof JComboBox) {
                    if(((JComboBox)e.getSource()).getName().equals(COMBOS[0])) {
                        // manager.changeMenu((String)deviceMobile.getSelectedItem());
                        System.out.println("Testing");
                    }
                }
            }

        };
        return listener;
    }

    @Override
    protected void createElement(final VisualizerManager manager) {
        manager.createDevice(DeviceForm.this.getFieldValue(FIELDS[0]), DeviceForm.this.getFieldValue(FIELDS[1]), DeviceForm.this.getComboValue(COMBOS[0]));

    }

    @Override
    protected boolean checkValues(final VisualizerManager manager) {
        // TODO Auto-generated method stub
        return false;
    }

}
