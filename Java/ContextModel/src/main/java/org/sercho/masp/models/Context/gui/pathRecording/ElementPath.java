package org.sercho.masp.models.Context.gui.pathRecording;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public class ElementPath {

    public static final transient Log LOG = LogFactory.getLog(ElementPath.class);

    /**
     * Represents the speed of the element like pixels/step
     */
    private static final int SPEED = 20;

    private static final int MIN_SPEED = 1;

    /**
     * List of paths for the element
     */
    private int currentSpeed = SPEED;

    private final Map<Integer, Path> paths;

    private int pathIndex = 0;

    private int startTimeCount = 0;

    private String elementId;

    private int pathCount = 1;

    private boolean next = false;

    private boolean end = false;

    private boolean start = true;

    private final PathRecorder recorder;

    /**
     * First path of the element path. This path must be always updated in order
     * to recover the first original // position of the element
     */
    private Path firstPath;

    /**
     * Map where is stored a list of points for each path of the element.
     */
    private final Map<Integer, List<Point>> points;

    private List<Point> currentPathPoints = null;

    private Path currentPath = null;

    private int pointIndex = 0;

    public ElementPath(final PathRecorder recorder, final String elementId) {
        restart();
        this.paths = Collections.synchronizedMap(new HashMap<Integer, Path>());
        this.recorder = recorder;
        this.elementId = elementId;
        this.points = Collections.synchronizedMap(new HashMap<Integer, List<Point>>());
    }

    /**
     * Returns the element identifier.
     * 
     * @return the element identifier.
     */
    public String getElementId() {
        return this.elementId;
    }

    /**
     * Sets the element identifier.
     * 
     * @param elementId
     *            the new identifier for this element.
     */
    public void setElementId(final String elementId) {
        this.elementId = elementId;
    }

    /**
     * Calculates the points for a given path. The Archimedische Theorem is used
     * to calculate the distance in pixels of the path. In proportion to the
     * distance, the number of points to calculate are obtained. Then the points
     * are calculated using the equation of a line.
     * 
     * 
     * @param path
     *            the path which points must be calculated.
     */
    private void calculatePoints(final Path path) {
        LOG.info("Calculating points for the path: " + path);
        double xDif;
        double yDif;
        if((xDif = path.getInitialX() - path.getTargetX()) == 0) {
            path.setInitialX(path.getInitialX() - 5);
            xDif = path.getInitialX() - path.getTargetX();
            yDif = path.getInitialY() - path.getTargetY();
        }
        if((yDif = path.getInitialY() - path.getTargetY()) == 0) {
            path.setInitialY(path.getInitialY() - 5);
            xDif = path.getInitialX() - path.getTargetX();
            yDif = path.getInitialY() - path.getTargetY();
        }

        final double m = yDif / xDif;
        final List<Point> pointList = new ArrayList<Point>();
        this.points.put(path.getStartTime(), pointList);
        double increaseX;

        if(xDif < 0) {
            increaseX = Math.abs(xDif) / (Math.sqrt((Math.abs(xDif) * Math.abs(xDif)) + (Math.abs(yDif) * Math.abs(yDif))));
        } else {
            increaseX = -Math.abs(xDif) / (Math.sqrt((Math.abs(xDif) * Math.abs(xDif)) + (Math.abs(yDif) * Math.abs(yDif))));
        }
        // System.out.println(xDif + " | " + yDif + " | " + increaseX + " | ");
        double currentX = path.getInitialX();
        double currentY = path.getInitialY();

        pointList.add(new Point((int)currentX, (int)currentY));

        // Using equation of a line for calculate points.
        if(xDif < 0) {
            while(currentX < path.getTargetX()) {
                currentX = currentX + increaseX;
                currentY = (int)((m * (currentX - path.getInitialX())) + path.getInitialY());
                pointList.add(new Point((int)currentX, (int)currentY));
            }
        } else {
            while(currentX > path.getTargetX()) {
                currentX = currentX + increaseX;
                currentY = (int)((m * (currentX - path.getInitialX())) + path.getInitialY());
                pointList.add(new Point((int)currentX, (int)currentY));
            }
        }

        pointList.set(pointList.size() - 1, new Point(path.getTargetX(), path.getTargetY()));

    }

    /**
     * Indicates the manager that the element with identifier elementId must be
     * moved to a new position defined by the parameters xPos, yPos and zPos.
     * 
     * @param elementId
     *            the identifier of the element that must be moved
     * @param xPos
     *            the new x position of the element
     * @param yPos
     *            the new y position of the element
     * @param zPos
     *            the new z position of the element
     */
    private void changePosition(final String elementId, final double xPos, final double yPos, final double zPos) {
        this.recorder.changeElementPosition(elementId, xPos, yPos, zPos);
        // System.out.println("Ordeno pintar");
    }

    /**
     * Does a step, that can be move or nothing because is paused (play == 0).
     * If the current path is completed, the next one is set up.
     * 
     * @param play
     *            value that indicates the mode that is currently activated:
     *            play (play = 1), reverse (play = -1), pause (play = 0).
     */
    public boolean step(final int play) {
        try {
            if(play != 0) {
                if(this.next) {

                    if(play == 1) {
                        if(!this.end) {
                            this.pathIndex += (1 * play);
                            if((this.pathIndex >= 0) && (this.paths.get(this.pathIndex) != null)) {
                                // The next path
                                this.currentPath = this.paths.get(this.pathIndex);
                                // The points of the next path
                                this.currentPathPoints = this.points.get(this.pathIndex);
                                // Where start to get points of the new path
                                this.pointIndex = 0;
                                this.pathCount++;
                                this.next = false;
                                this.end = false;
                                this.start = false;
                            }
                        } else {
                            return true;

                        }
                    }

                    if(play == -1) {
                        if(!this.start) {
                            this.pathIndex += (1 * play);
                            if((this.pathIndex >= 0) && (this.paths.get(this.pathIndex) != null)) {
                                // The next path
                                this.currentPath = this.paths.get(this.pathIndex);
                                // The points of the next path
                                this.currentPathPoints = this.points.get(this.pathIndex);
                                // Where start to get points of the new path
                                this.pointIndex = this.currentPathPoints.size() - 1;
                                this.pathCount--;
                                this.next = false;
                                this.end = false;
                                this.start = false;
                            }
                        }
                    }

                } else {
                    this.next = pathFinished(play);
                    changePosition(this.currentPath.getElementId(), this.currentPathPoints.get(this.pointIndex).getX(), this.currentPathPoints.get(this.pointIndex).getY(), 0);

                    return false;
                }
            }
        }
        catch(final Exception e) {
            e.printStackTrace();
            System.out.println("Error....");
        }
        return false;
    }

    /**
     * Tests if the current path is completed. Depends of the current mode that
     * is activated, play (play = 1) or reverse (play = -1).
     * 
     * @param play
     *            parameter that indicates which mode is activated, play or
     *            reverse.
     * @return true if the current path is completed, false in other case.
     */
    private boolean pathFinished(final int play) {
        // Next pointIndex
        this.pointIndex += (this.currentSpeed * play);
        if(this.currentPathPoints != null) {
            if(play == 1) {
                if(this.pointIndex >= this.currentPathPoints.size()) {
                    this.pointIndex = this.currentPathPoints.size() - 1;
                    // The last path
                    if(this.pathCount == this.paths.size() - 1) {
                        // System.out.println("End!");
                        this.end = true;
                        this.pathIndex++;
                        this.pathCount++;
                    }
                    return true;
                }

            } else {
                if(this.pointIndex < 1) {
                    this.pointIndex = 0;
                    // Again the first path
                    if(this.pathCount == 0) {
                        // System.out.println("Start!");
                        this.start = true;
                        this.pathIndex = -1;
                        this.pathCount = -1;
                    }
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * Adds a new path in the path sequence.
     * 
     * @param path
     *            the new path to be added
     * @param auto
     *            the parameter that indicates if the star time for this new
     *            path must be established following the automatic start time or
     *            must be not set because the new path already has an start
     *            time.
     */
    public void addPath(final Path path, final boolean auto) {
        LOG.info("Adding the path: " + path + " for the element: " + path.getElementId());
        if(auto) {
            path.setStartTime(this.startTimeCount);
            this.startTimeCount++;
        } else {
            if(this.startTimeCount <= path.getStartTime()) {
                this.startTimeCount = path.getStartTime() + 1;
            }
        }

        insertPath(path, path.getStartTime());

    }

    /**
     * Moves a path to the previous position in the path sequence.
     * 
     * @param path
     *            the path to be moved.
     */
    public Path movePathBefore(final Path path) {
        // System.out.println("Path que se mueve" + path);
        if(isTheLastPath(path)) {
            this.paths.remove(path.getStartTime());
            this.points.remove(path.getStartTime());
            path.setStartTime(path.getStartTime() + 1);
            this.paths.put(path.getStartTime(), path);
            calculatePoints(path);
            updateTheFirstPath();
            this.startTimeCount++;
            return path;
        } else {
            final Path pathModified = this.paths.remove(path.getStartTime());
            this.points.remove(path.getStartTime());
            path.setStartTime(path.getStartTime() + 1);
            insertPath(path, path.getStartTime() - 1);
            updateTheFirstPath();
            return pathModified;
        }
    }

    /**
     * Moves a path to the next position in the path sequence .
     * 
     * @param path
     *            the path to be moved.
     */
    public Path movePathAfter(final Path path) {
        if(path.getStartTime() == 0) {
            return null;
        } else if(isTheFirstPath(path)) {
            this.paths.remove(path.getStartTime());
            this.points.remove(path.getStartTime());
            path.setStartTime(path.getStartTime() - 1);

            this.paths.put(path.getStartTime(), path);
            calculatePoints(path);

            updateTheFirstPath();
            return path;
        } else if((findPreviousPath(path).getStartTime() + 1) != path.getStartTime()) {
            this.paths.remove(path.getStartTime());
            this.points.remove(path.getStartTime());
            path.setStartTime(path.getStartTime() - 1);

            this.paths.put(path.getStartTime(), path);
            calculatePoints(path);
            updateTheFirstPath();
            return path;
        } else {
            final Path pathModified = this.paths.remove(path.getStartTime());
            this.points.remove(path.getStartTime());
            path.setStartTime(path.getStartTime() - 1);
            insertPath(path, path.getStartTime() + 1);
            updateTheFirstPath();
            return pathModified;
        }
    }

    /**
     * Looks for which path is now the first one and updates the reference to
     * this first path.
     */
    public void updateTheFirstPath() {
        Iterator<Entry<Integer, Path>> it = null;
        Path path = null;
        it = this.paths.entrySet().iterator();
        while(it.hasNext()) {
            path = it.next().getValue();
            if(isTheFirstPath(path)) {
                this.firstPath = path;
            }
        }
    }

    /**
     * Returns the first path of this element.
     * 
     * @return the first path of this element.
     */
    public Path getTheFirstPath() {
        return this.firstPath;
    }

    /**
     * Given a path, looks if is the first path of the element.
     * 
     * @param path
     *            the path that must be checked.
     * @return true if the path is the first path of the element, false in other
     *         case.
     */
    public boolean isTheFirstPath(final Path path) {
        if(findPreviousPath(path) != null) {
            return false;
        }
        return true;
    }

    /**
     * Given a path, looks if is the last path of the element.
     * 
     * @param path
     *            the path that must be checked.
     * @return true if the path is the last path of the element, false in other
     *         case.
     */
    public boolean isTheLastPath(final Path path) {
        if(findNextPath(path) != null) {
            return false;
        }
        return true;
    }

    /**
     * Find and returns the previous path for a given path.
     * 
     * @param path
     *            the path which previous path must be found.
     * @return the previous path of the given path.
     */
    private Path findPreviousPath(final Path path) {
        Iterator<Entry<Integer, Path>> it = null;
        Entry<Integer, Path> tempEntry = null;
        int previousStartTime = Integer.MIN_VALUE;
        int nextPathId = -1;
        int tempPathId = 0;
        Path tempPath = null;

        if(this.paths.size() <= 1) {
            return null;
        } else {
            it = this.paths.entrySet().iterator();
            while(it.hasNext()) {
                tempEntry = it.next();
                tempPath = tempEntry.getValue();
                tempPathId = tempEntry.getKey();
                if((tempPath.getStartTime() < path.getStartTime()) && (tempPath.getStartTime() > previousStartTime)) {
                    previousStartTime = tempPath.getStartTime();
                    nextPathId = tempPathId;
                }
            }
            if(nextPathId != -1) {
                return this.paths.get(nextPathId);
            } else {
                return null;
            }

        }
    }

    /**
     * Inserts a new path into the path sequence. If currently there is another
     * path that starts at the same time, both will be interchanged
     * 
     * @param path
     *            the new path that is inserted
     * @param oldStartTime
     *            the old start time if the path had
     */
    public void insertPath(final Path path, final int oldStartTime) {
        Path overlapPath = null;
        if((overlapPath = this.paths.get(path.getStartTime())) != null) {
            // Interchange path
            overlapPath.setStartTime(oldStartTime);

            this.paths.put(overlapPath.getStartTime(), overlapPath);
            this.paths.put(path.getStartTime(), path);
            if(oldStartTime < path.getStartTime()) {
                interchangeXY(overlapPath, path);
            } else {
                interchangeXY(path, overlapPath);

            }
            calculatePoints(overlapPath);
        }
        this.paths.put(path.getStartTime(), path);
        calculatePoints(path);

        updateTheFirstPath();
    }

    /**
     * Interchanges x and y coordinate between two paths.
     * 
     * @param path1
     *            first path
     * @param path2
     *            second path
     */
    private void interchangeXY(final Path path1, final Path path2) {
        Path pathTemp = null;

        path1.setInitialX(path2.getInitialX());
        path1.setInitialY(path2.getInitialY());

        path2.setInitialX(path1.getTargetX());
        path2.setInitialY(path1.getTargetY());

        if((pathTemp = findNextPath(path2)) != null) {
            pathTemp.setInitialX(path2.getTargetX());
            pathTemp.setInitialY(path2.getTargetY());
            calculatePoints(pathTemp);
        }

    }

    /**
     * Removes the path and restructure the path sequence
     * 
     * @param path
     */
    public boolean removePath(final Path path) {
        Path nextPath = null;
        if(this.paths.containsValue(path)) {
            if((nextPath = findNextPath(path)) != null) {
                nextPath.setInitialX(path.getInitialX());
                nextPath.setInitialY(path.getInitialY());
                calculatePoints(nextPath);
                this.recorder.updatePathPanel(nextPath);
            } else {
                this.startTimeCount--;
            }
            this.paths.remove(path.getStartTime());
            this.points.remove(path.getStartTime());
            updateTheFirstPath();
            if(this.paths.size() == 0) {
                this.startTimeCount = 0;
            }
            return true;
        }
        return false;
    }

    public Collection<Path> getPaths() {
        return this.paths.values();
    }

    /**
     * Look for the next path in the sequence
     * 
     * @param path
     *            path which next path are we looking for
     * @return the next path to the current path
     */
    public Path findNextPath(final Path path) {
        Iterator<Entry<Integer, Path>> it = null;
        Entry<Integer, Path> tempEntry = null;
        int nextStartTime = Integer.MAX_VALUE;
        int nextPathId = -1;
        int tempPathId = 0;
        Path tempPath = null;

        if(this.paths.size() <= 1) {
            return null;
        } else {
            it = this.paths.entrySet().iterator();
            while(it.hasNext()) {
                tempEntry = it.next();
                tempPath = tempEntry.getValue();
                tempPathId = tempEntry.getKey();
                if((tempPath.getStartTime() > path.getStartTime()) && (tempPath.getStartTime() < nextStartTime)) {
                    nextStartTime = tempPath.getStartTime();
                    nextPathId = tempPathId;
                }
            }
            if(nextPathId != -1) {
                return this.paths.get(nextStartTime);
            } else {
                return null;
            }

        }
    }

    /**
     * Reset some values including the speed.
     */
    public void restart() {
        this.next = true;
        this.pathIndex = -1;
        this.pointIndex = 0;
        this.pathCount = -1;
        this.currentSpeed = SPEED;
        this.start = true;
        this.end = false;

    }

    /**
     * Reset some values excluding the speed.
     */
    public void softRestart() {
        this.next = true;
        this.pathIndex = -1;
        this.pathCount = -1;
        this.pointIndex = 0;
        this.start = true;
        this.end = false;

    }

    /**
     * Sets the speed of this element.
     * 
     * @param newSpeed
     *            the new speed for this element.
     */
    public void setSpeed(final int newSpeed) {
        if(newSpeed >= MIN_SPEED) {
            this.currentSpeed = newSpeed;
        }

    }

    /**
     * Returns the current speed of this element.
     * 
     * @return the current speed of this element.
     */
    public int getSpeed() {
        return this.currentSpeed;
    }

    @Override
    public String toString() {
        return new String("Element Id: " + this.elementId + " | Num. paths: " + this.paths.size());
    }
}
