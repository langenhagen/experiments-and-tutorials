package org.sercho.masp.models.Context.gui.pathRecording;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.gui.exceptions.PlayerException;

/**
 * This class represents a player for path recording. Like a classical player,
 * there is the options for play, reverse, pause, stop..etc. The run method has
 * a loop where every each speed time the step method of each element path is
 * called. The field play represents the current mode.
 * 
 * -1 = reverse
 * 
 * 0 = pause
 * 
 * 1 = play
 * 
 * 2 = stop
 * 
 * If the loop mode is activated (true), when all paths reach their target
 * coordinates, the playback will be restarted.
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public class PathPlayer extends Thread {

    public static final transient Log LOG = LogFactory.getLog(PathPlayer.class);

    private volatile int play = 1;

    private static final int SPEED = 1000;

    private volatile int speed = SPEED;

    private volatile boolean loop = false;

    private final List<ElementPath> elementPathList = Collections.synchronizedList(new ArrayList<ElementPath>());

    private final Map<String, Boolean> elementPathStatus = Collections.synchronizedMap(new HashMap<String, Boolean>());

    public boolean setLoopMode() {
        if(this.loop) {
            this.loop = false;
            return false;
        } else {
            this.loop = true;
            return true;
        }
    }

    public void playPathRep() {
        this.play = 1;
    }

    public void pausePathRep() {
        this.play = 0;
    }

    public void reversePlayRep() {
        this.play = -1;
    }

    public void stopRep() {
        this.play = -2;
    }

    public void faster() {
        this.speed = this.speed / 2;
    }

    public void slower() {
        this.speed = this.speed * 2;
    }

    public void addElementPath(final ElementPath elementPath) {
        this.elementPathList.add(elementPath);
    }

    public void removeElementPath() {

    }

    @Override
    public void run() {
        try {
            // while non stop
            while(this.play != -2) {
                // Order to each element path to realize a step an stores its
                // current state, finished or not.
                for(final ElementPath elementPath : this.elementPathList) {
                    if(elementPath.getPaths().size() > 0) {
                        this.elementPathStatus.put(elementPath.getElementId(), elementPath.step(this.play));
                    }
                }
                // Test if all paths are finished and playback again if loop
                // mode is
                // active
                if(allPathsFinished()) {
                    LOG.info("All paths finished");
                    if(this.loop) {
                        LOG.info("Restarting playback due to loop mode");
                        this.elementPathStatus.clear();
                        for(final ElementPath elementPath : this.elementPathList) {
                            elementPath.softRestart();
                        }
                    }
                }
                try {
                    Thread.sleep(this.speed);
                }
                catch(final InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        catch(final Exception e) {
            final Exception playerException = new PlayerException("Error during playback", this);
            LOG.warn(playerException.getMessage());
        }
    }

    /**
     * Checks if the status of all the paths is true. In this case, return true
     * indicating that all the paths are finished. In other case returns false
     * that indicates that there are still paths not finished.
     * 
     * @return true if all the paths are finished, false if there are still
     *         paths not finished.
     */
    private boolean allPathsFinished() {
        for(final Boolean finished : this.elementPathStatus.values()) {
            if(!finished) {
                return false;
            }
        }
        return true;
    }

    public void clear() {
        // System.out.println(this.toString());
        this.elementPathList.clear();
        this.elementPathStatus.clear();
    }

    @Override
    public String toString() {
        return new String("Player mode: " + this.play + " | " + "Speed: " + this.speed / 1000 + "s");
    }
}
