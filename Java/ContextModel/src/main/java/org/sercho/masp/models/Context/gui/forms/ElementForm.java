package org.sercho.masp.models.Context.gui.forms;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.sercho.masp.models.Context.EnvironmentElement;
import org.sercho.masp.models.Context.gui.VisualizerManager;

/**
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public abstract class ElementForm {

    private final List<String> buttons;

    private final List<String> fields;

    private final List<String> combos;

    private final Map<String, JTextField> fieldValues;

    private final Map<String, JComboBox> combosValues;

    protected Map<String, String> errors = new HashMap<String, String>();

    private final Map<String, String[]> combosOptions;

    protected final int frameId;

    public ElementForm(final String[] newFields, final String[] newButtons,
            final String[] newCombos, final EnvironmentElement element, final int frameId) {
        this.frameId = frameId;
        this.fieldValues = new HashMap<String, JTextField>();
        this.combosValues = new HashMap<String, JComboBox>();
        this.combosOptions = new HashMap<String, String[]>();
        this.fields = new ArrayList<String>();
        this.buttons = new ArrayList<String>();
        this.combos = new ArrayList<String>();
        int i = 0;

        if(newFields != null) {
            for(i = 0; i < newFields.length; i++) {
                this.fields.add(newFields[i]);
            }
        }
        if(newButtons != null) {
            for(i = 0; i < newButtons.length; i++) {
                this.buttons.add(newButtons[i]);
            }
        }
        if(newCombos != null) {
            for(i = 0; i < newCombos.length; i++) {
                this.combos.add(newCombos[i]);
            }
        }

        fillValues(element);
    }

    public String getFieldValue(final String fieldName) {
        if(this.fieldValues.get(fieldName) != null) {
            return this.fieldValues.get(fieldName).getText();
        } else {
            return new String(fieldName);
        }

    }

    public String getComboValue(final String comboName) {
        return (String)this.combosValues.get(comboName).getSelectedItem();

    }

    public String[] getComboOptions(final String comboName) {
        return this.combosOptions.get(comboName);

    }

    public void setFieldValue(final String name, final JTextField value) {
        this.fieldValues.put(name, value);
    }

    public void setComboValue(final String name, final JComboBox value) {
        this.combosValues.put(name, value);
    }

    protected void setComboOptions(final String name, final String[] options) {
        this.combosOptions.put(name, options);
    }

    public abstract ActionListener getListener(final VisualizerManager manager);

    public List<String> getCombos() {
        return this.combos;
    }

    public List<String> getButtons() {
        return this.buttons;
    }

    public List<String> getFields() {
        return this.fields;
    }

    // public List<String> getComboOptions(String comboName, String[]
    // comboOptions) {
    //
    // }

    public abstract void fillValues(EnvironmentElement element);

    public int getFrameId() {
        return this.frameId;
    }

    protected abstract void createElement(VisualizerManager manager);

    // Fields
    // Bootons
    // Combos
    // Metodo acceso getFieldValue, getBottonValue, getComboValue
    // Metodo acceso getFields, getBottons, getCombos, getComboOptions(combo)

    public boolean containsErrors() {
        return (this.errors.size() > 0);
    }

    public String getFieldErrorValue(final String fieldString) {
        return this.errors.get(fieldString);
    }

    protected abstract boolean checkValues(VisualizerManager manager);

}
