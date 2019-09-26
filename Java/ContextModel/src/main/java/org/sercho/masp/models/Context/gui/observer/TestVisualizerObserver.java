package org.sercho.masp.models.Context.gui.observer;

/**
 * @author Antonio Fernandez Zaragoza
 */
public class TestVisualizerObserver implements VisualizerObserver {

    @Override
    public void elementAdded(final VisualizerEvent event) {
        System.out.println("Added!! " + event.getElementId());
    }

    @Override
    public void elementDragged(final VisualizerEvent event) {
        System.out.println("Dragged!! " + event.getElementId());

    }

    @Override
    public void elementRemoved(final VisualizerEvent event) {
        System.out.println("Removed!! " + event.getElementId());

    }

    @Override
    public void elementSelected(final VisualizerEvent event) {
        System.out.println("Selected!! " + event.getElementId());

    }

}
