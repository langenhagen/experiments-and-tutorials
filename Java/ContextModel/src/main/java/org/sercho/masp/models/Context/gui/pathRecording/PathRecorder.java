package org.sercho.masp.models.Context.gui.pathRecording;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.User;
import org.sercho.masp.models.Context.gui.VisualizerManager;
import org.sercho.masp.models.Context.gui.exceptions.LoadingRecordingException;
import org.sercho.masp.models.Context.gui.exceptions.NoExistingElementException;
import org.sercho.masp.models.Context.util.EnvironmentUtility;

/**
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public class PathRecorder {

    public static final transient Log LOG = LogFactory.getLog(PathRecorder.class);

    private final Map<String, ElementPath> elementsPath;

    private final static int SPEED_INCREASE = 10;

    private final static int SPEED_DECREASE = 10;

    private final Map<Integer, Path> allPaths;

    private PathPlayer player;

    private int pathId;

    private Environment environment;

    private VisualizerManager manager;

    private List<String> discardedPathList;

    private Path path;

    /**
     * Constructor thought to work without GUI, using directly the environment.
     * 
     * @param environment
     */
    public PathRecorder(final Environment environment) {
        this.manager = null;
        this.environment = environment;
        this.elementsPath = new HashMap<String, ElementPath>();
        this.allPaths = new HashMap<Integer, Path>();
        this.player = new PathPlayer();
        this.pathId = 0;
    }

    /**
     * Constructor thought to work with GUI, adding a reference to the manager
     * of the visualizer GUI.
     * 
     * @param environment
     */
    public PathRecorder(final Environment environment, final VisualizerManager manager) {
        this.manager = manager;
        this.environment = environment;
        this.elementsPath = new HashMap<String, ElementPath>();
        this.allPaths = new HashMap<Integer, Path>();
        this.player = new PathPlayer();
        this.pathId = 0;
    }

    /**
     * Constructor without giving references to an environment either the
     * manager. This two fields can be set using set methods.
     */
    public PathRecorder() {
        this.manager = null;
        this.environment = null;
        this.elementsPath = new HashMap<String, ElementPath>();
        this.allPaths = new HashMap<Integer, Path>();
        this.player = new PathPlayer();
        this.pathId = 0;
    }

    public void setEnvironment(final Environment environment) {
        clearPaths();
        this.environment = environment;
    }

    public void setVisualizerManager(final VisualizerManager manager) {
        clearPaths();
        this.manager = manager;
    }

    /**
     * Sets a new path using the automatic path's identifier.
     * 
     * @param elementId
     *            the identifier of the element owner of this new path
     * @param startTime
     *            the time when the path starts
     * @param initialX
     *            the initial value for x
     * @param targetX
     *            the final value for x
     * @param initialY
     *            the initial value for y
     * @param targetY
     *            the final value for y
     * @return true if is the first path of the element identified by elementId
     */
    public boolean setPath(final String elementId, final int startTime, final int initialX, final int targetX, final int initialY, final int targetY) {
        this.pathId++;
        return setPath(elementId, this.pathId, startTime, initialX, targetX, initialY, targetY, true);

    }

    /**
     * Sets a new path using a provide path's identifier.
     * 
     * @param elementId
     *            the identifier of the element owner of this new path
     * @param startTime
     *            the time when the path starts
     * @param initialX
     *            the initial value for x
     * @param targetX
     *            the final value for x
     * @param initialY
     *            the initial value for y
     * @param targetY
     *            the final value for y
     * @return true if is the first path of the element identified by elementId
     */
    public boolean setPath(final String elementId, final int pathId, final int startTime, final int initialX, final int targetX, final int initialY, final int targetY, final boolean auto) {
        boolean firstPath = false;
        this.path = new Path(elementId, pathId, startTime, initialX, targetX, initialY, targetY);
        this.allPaths.put(pathId, this.path);
        ElementPath elementPath = null;

        if((elementPath = this.elementsPath.get(elementId)) != null) {
            elementPath.addPath(this.path, auto);
        } else {
            firstPath = true;
            elementPath = new ElementPath(this, this.path.getElementId());
            elementPath.addPath(this.path, auto);
            this.elementsPath.put(elementId, elementPath);
            this.player.addElementPath(elementPath);
            LOG.info("Element path created: " + elementPath);

        }
        LOG.info("Adding path: " + this.path);
        return firstPath;
    }

    /**
     * Returns the last path that was set for any element.
     * 
     * @return the last path that was set for any element.
     */
    public Path getLastPath() {
        return this.path;
    }

    /**
     * Removes (from one element path) the path identified by pathId.
     */
    public void removePath(final int pathId) {
        final Path pathToBeRemoved = this.allPaths.get(pathId);
        final Iterator<Entry<String, ElementPath>> it = this.elementsPath.entrySet().iterator();
        if(pathToBeRemoved != null) {
            while(it.hasNext()) {
                it.next().getValue().removePath(pathToBeRemoved);
            }
        }
        this.allPaths.remove(pathId);
        LOG.info("Path removed: " + pathToBeRemoved);
    }

    /**
     * Loads a recording from a file, filtering those paths that make no sense
     * to add because the are paths of elements that currently does not exist in
     * the context model loaded.
     * 
     * @param file
     *            the path to the recording file.
     * @return a list of paths that were not added because of the filter
     *         process.
     * @throws LoadingRecordingException
     *             when there is an error loading the recording
     * @throws NoExistingElementException
     *             when some element's paths could no be possible to be loaded
     */
    public List<String> loadRecording(final String file) throws LoadingRecordingException, NoExistingElementException {
        int numPaths = 0;
        this.discardedPathList = new ArrayList<String>();
        this.pathId = 0;
        PathRecording record;
        try {
            record = PathRecording.getRecordFromMASPDirectory(file);
        }
        catch(final FileNotFoundException e) {
            throw new LoadingRecordingException("File not found", file);
        }
        catch(final JAXBException e) {
            throw new LoadingRecordingException("Unable to load/parse a recording from file", file);
        }
        // Filter and adding path to the recorder
        final Iterator<Path> it = record.getPaths().iterator();
        Path path = null;
        while(it.hasNext()) {
            path = it.next();
            if(filterPath(path)) {
                this.pathId++;
                numPaths++;
                setPath(path.getElementId(), path.getPathId(), path.getStartTime(), path.getInitialX(), path.getTargetX(), path.getInitialY(), path.getTargetY(), false);
            }
        }
        if((this.discardedPathList != null) && (this.discardedPathList.size() > 0)) {
            throw new NoExistingElementException("Some element's paths could not be loaded: ", this.discardedPathList);
        }
        // A list with discarded paths is returned.
        return this.discardedPathList;

    }

    public List<String> getDiscardedPathList() {
        return this.discardedPathList;
    }

    /**
     * Saves a path recording to a file in the file system.
     * 
     * @param filePath
     *            the path to the file where will be saved the path recording.
     * @throws JAXBException
     *             when an error occurs during the marshaller process.
     * @throws FileNotFoundException
     *             when is no possible to save into the file.
     */
    public void saveRecord(final String filePath) throws JAXBException, FileNotFoundException {
        final PathRecording recording = new PathRecording();
        final Iterator<Entry<Integer, Path>> it = this.allPaths.entrySet().iterator();
        Path path = null;
        while(it.hasNext()) {
            path = it.next().getValue();
            recording.addPath(path);
        }
        final Marshaller marshaller = JAXBContext.newInstance(PathRecording.class, Path.class).createMarshaller();
        final File file = new File(filePath);
        try {
            marshaller.marshal(recording, new FileOutputStream(file));
        }
        catch(final FileNotFoundException e) {
            new IllegalArgumentException("Failed to marshal object into file: " + filePath, e);
            throw e;
        }
    }

    public void clearPaths() {
        LOG.info("Reseting recorder");
        this.allPaths.clear();
        this.elementsPath.clear();
        this.player.clear();
    }

    public void play() {
        LOG.info("Playback started");
        if(this.player.getState() == Thread.State.NEW) {
            this.player.start();
        }
        this.player.playPathRep();
    }

    public void pause() {
        LOG.info("Playback paused");
        this.player.pausePathRep();
    }

    public void stop() {
        LOG.info("Playback stoped: Restarting player, restarting elements paths state");
        this.player.stopRep();
        // Restart player
        this.player = new PathPlayer();
        final Iterator<Entry<String, ElementPath>> it = this.elementsPath.entrySet().iterator();
        ElementPath elementPath = null;
        while(it.hasNext()) {
            elementPath = it.next().getValue();
            elementPath.restart();
            this.player.addElementPath(elementPath);
        }
    }

    public void reverse() {
        LOG.info("Reverse playback started");
        this.player.reversePlayRep();
    }

    public boolean setLoopMode() {
        return this.player.setLoopMode();
    }

    public void faster() {
        LOG.info("Playback speed increased");
        this.player.faster();
    }

    public void slower() {
        LOG.info("Playback speed decreased");
        this.player.slower();
    }

    public ElementPath getElementPath(final String elementId) {
        return this.elementsPath.get(elementId);
    }

    public Collection<ElementPath> getElementsPath() {
        return this.elementsPath.values();
    }

    /**
     * Advances the star time of the path identified by pathId.
     * 
     * @param pathId
     *            the identifier of the path that should be advanced.
     * @return the path that is delayed
     */
    public Path upPath(final int pathId) {
        ElementPath elementPath = null;
        final Path pathToModify = this.allPaths.get(pathId);
        final Iterator<Entry<String, ElementPath>> it = this.elementsPath.entrySet().iterator();
        if(pathToModify != null) {
            while(it.hasNext()) {
                elementPath = it.next().getValue();
                if(elementPath.getElementId().equals(pathToModify.getElementId())) {
                    elementPath.movePathAfter(pathToModify);
                    break;
                }

            }
            return pathToModify;
        } else {
            return null;
        }

    }

    /**
     * Delays the star time of the path identified by pathId.
     * 
     * @param pathId
     *            the identifier of the path that should be delayed.
     * @return the path that was delayed
     */
    public Path downPath(final int pathId) {

        ElementPath elementPath = null;
        final Path pathToModify = this.allPaths.get(pathId);
        final Iterator<Entry<String, ElementPath>> it = this.elementsPath.entrySet().iterator();

        if(pathToModify != null) {
            while(it.hasNext()) {
                elementPath = it.next().getValue();
                if(elementPath.getElementId().equals(pathToModify.getElementId())) {
                    elementPath.movePathBefore(pathToModify);
                    break;
                }
            }
            return pathToModify;
        } else {
            return null;
        }

    }

    /**
     * Returns a collection with all the paths sorted by their start time.
     * 
     * @return a collection with all the paths sorted by start time.
     */
    public Collection<Path> getAllPaths() {
        final Collection<Path> list = new ArrayList<Path>();
        int j = this.allPaths.size();
        int i = 0;
        while(j != 0) {
            for(final Path path : this.allPaths.values()) {
                if(path.getStartTime() == i) {
                    list.add(path);
                    j--;
                }

            }
            i++;
        }
        return list;
    }

    /**
     * Sets a new speed for the element identified by elementId.
     * 
     * @param elementPath
     *            the element's path.
     * @param newSpeed
     *            the new speed.
     */
    public void setSpeed(final ElementPath elementPath, final int newSpeed) {
        if(elementPath != null) {
            elementPath.setSpeed(newSpeed);
        }
    }

    /**
     * Increases the speed for the element identified by elementId.
     * 
     * @param elementId
     *            the identifier of the element which speed must be increased.
     */
    public int increaseSpeed(final String elementId) {
        final ElementPath elementPath = this.elementsPath.get(elementId);
        int speed = 0;
        if(elementPath != null) {
            speed = elementPath.getSpeed() + SPEED_INCREASE;
            setSpeed(elementPath, elementPath.getSpeed() + SPEED_INCREASE);
        }
        return speed;
    }

    /**
     * Decreases the speed for the element identified by elementId.
     * 
     * @param elementId
     *            the identifier of the element which speed must be decreased.
     */
    public int decreaseSpeed(final String elementId) {
        final ElementPath elementPath = this.elementsPath.get(elementId);
        int speed = 0;
        if(elementPath != null) {
            speed = elementPath.getSpeed() - SPEED_DECREASE;
            setSpeed(elementPath, elementPath.getSpeed() - SPEED_DECREASE);
        }
        return speed;
    }

    /**
     * Informs the GUI that a path was modified and should be updated.
     * 
     * @param path
     *            the path that was modified.
     */
    public void updatePathPanel(final Path path) {
        if(this.manager != null) {
            this.manager.updatePathPanel(path);
        }

    }

    /**
     * Changes the position of the element identified with the identifier
     * elementId. The new position is defined by xPos, yPos and xPos
     * 
     * 
     * @param elementId
     *            element's identifier
     * @param xPos
     *            new position for x
     * @param yPos
     *            new position for y
     * @param zPos
     *            new position for z
     */
    public void changeElementPosition(final String elementId, final double xPos, final double yPos, final double zPos) {
        // System.out.println("Element moving: " + elementId);
        this.manager.changeElementPosition(elementId, xPos, yPos, zPos);
        // User user;
        // InteractionResource resource;
        // if((user = findUser(elementId)) != null) {
        // user.setPosition(xPos, yPos, zPos);
        // }
        // if((resource = findIResource(elementId)) != null) {
        // resource.setPosition(xPos, yPos, zPos);
        // }

    }

    public User findUser(final String userId) {
        if(userId == null) {
            throw new IllegalArgumentException("userId argument must not be null in method findUser");
        }
        if(this.environment == null) {
            return null;
        }
        return EnvironmentUtility.getUser(this.environment, userId);
    }

    public InteractionResource findIResource(final String resourceId) {
        if(resourceId == null) {
            throw new IllegalArgumentException("resourceId argument must not be null in method findIResource");
        }
        if(this.environment == null) {
            return null;
        }
        for(final Device device : this.environment.getDevices()) {
            for(final InteractionResource resource : device.getResources()) {
                if(resourceId.equals(resource.getId())) {
                    return resource;
                }
            }
        }
        return null;
    }

    /**
     * Filters a path depending if the path's element owner exists in the
     * current environment.
     * 
     * @param path
     *            the path to be filtered.
     * @return false if the element owner does not exist, true if the element
     *         owner exists.
     */
    public boolean filterPath(final Path path) {
        final boolean existElement = findElement(path.getElementId());

        if(!existElement) {
            if(!this.discardedPathList.contains(path.getElementId())) {
                this.discardedPathList.add(path.getElementId());
            }

        }

        return existElement;

    }

    public boolean findElement(final String elementId) {
        if(EnvironmentUtility.getEnvironmentElement(this.environment, elementId) != null) {
            return true;
        }
        return false;
    }

    public String getSpeed(final String elementId) {
        for(final ElementPath elementPath : this.elementsPath.values()) {
            if(elementPath.getElementId().equals(elementId)) {
                return String.valueOf(elementPath.getSpeed());
            }
        }
        return new String("1");
    }

    public void removeElementPath(final String id) {
        this.elementsPath.remove(id);

    }
}
