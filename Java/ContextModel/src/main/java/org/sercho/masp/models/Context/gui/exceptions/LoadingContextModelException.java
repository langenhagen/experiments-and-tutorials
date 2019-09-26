package org.sercho.masp.models.Context.gui.exceptions;

import java.io.File;

/**
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public class LoadingContextModelException extends ContextModelVisualizerException {

    private static final long serialVersionUID = 1L;

    private final File file;

    public LoadingContextModelException(final String specificMessage, final File file) {
        super(specificMessage + "( file name =  " + file.getName() + " )");
        this.file = file;
    }

    public Object getFile() {
        return this.file;
    }
}
