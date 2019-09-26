package org.sercho.masp.models.Context.gui.exceptions;

/**
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public class LoadingRecordingException extends ContextModelVisualizerException {

    private static final long serialVersionUID = 1L;

    private final String file;

    public LoadingRecordingException(final String specificMessage, final String file) {
        super(specificMessage + "( file name = " + file + " )");
        this.file = file;
    }

    public Object getFile() {
        return this.file;
    }
}
