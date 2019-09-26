package org.sercho.masp.models.Context.gui.forms;

import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.EnvironmentElement;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.gui.VisualizerManager;

/**
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public class TouchpadForm extends IRSForm {

    private static final String[] FIELDS = {"Name", "Identifier"};

    private static final String[] BUTTONS = {"Accept", "Cancel"};

    private static final String[] COMBO_IRS = {"TOUCHPAD", "DISPLAY", "TOUCHSCREEN",
            "MOUSE", "MICROPHONE", "LOUDSPEAKER", "KEYBOARD"};

    public TouchpadForm(final EnvironmentElement element, final int frameId) {
        super(FIELDS, BUTTONS, element, COMBO_IRS, frameId);

    }

    @Override
    public void fillValues(final EnvironmentElement element) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void createElement(final VisualizerManager manager) {
        final InteractionResource resource = ContextFactory.eINSTANCE.createTouchpad();
        resource.setName(getFieldValue(FIELDS[0]));
        resource.setId(getFieldValue(FIELDS[1]));
        manager.createIR(resource);

    }

    @Override
    protected boolean checkValues(final VisualizerManager manager) {
        // TODO Auto-generated method stub
        return false;
    }

}
