package org.sercho.masp.models.Context.gui.observer;

/**
 * @author Antonio Fernandez Zaragoza
 */
public interface VisualizerObserver {

    public void elementAdded(VisualizerEvent event);

    public void elementRemoved(VisualizerEvent event);

    public void elementSelected(VisualizerEvent event);

    public void elementDragged(VisualizerEvent event);

}
