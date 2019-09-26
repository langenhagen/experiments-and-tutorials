package org.sercho.masp.models.Context.gui.exceptions;

import org.sercho.masp.models.Context.gui.pathRecording.PathPlayer;

/**
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public class PlayerException extends ContextModelVisualizerException {

    /**
     * 
     */
    private static final long serialVersionUID = -2517793272607932917L;

    private final PathPlayer player;

    public PlayerException(final String specificMessage, final PathPlayer player) {
        super(specificMessage);
        this.player = player;
    }

    public PathPlayer getPathPlayer() {
        return this.player;
    }

}
