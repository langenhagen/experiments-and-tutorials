package org.sercho.masp.models.Context.gui;

import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.gui.exceptions.VisualizerException;
import org.sercho.masp.models.Context.gui.pathRecording.ElementPath;

/**
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public interface AbstractContextModelVisualizer {

    public void clearEnvironment() throws VisualizerException;

    public void clearPaths();

    public void filter(Object element, boolean check);

    public void hidePath(String elementId);

    public void hidePaths();

    public boolean isRecordModeActive();

    public void loadEnvironment(Environment contextModel);

    public void setAreaEditMode(boolean on);

    public void setEnvironment(Environment environment);

    public void showPath(String elementId, ElementPath elementPath);

    public void showPaths();

    public void startPlayMode();

    public void startRecordMode();

    public void stopPlayMode();

    public void stopRecordMode();
}
