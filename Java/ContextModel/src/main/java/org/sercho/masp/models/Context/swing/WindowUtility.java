/**
 * 
 */
package org.sercho.masp.models.Context.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Window;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <code>WindowUtility</code> helps working with AWT {@link Window}s.
 * 
 * @author Grzegorz Lehmann
 * @since 1.1.3
 */
public final class WindowUtility {

    /**
     * <code>WindowObserver</code> is notified when a {@link Window} with a
     * specified name appears.
     * 
     * @author Grzegorz Lehmann
     */
    public static abstract class WindowObserver {

        /**
         * <code>windowName</code> holds the name of the window that this
         * observer observes.
         */
        public final String windowName;

        WindowObserver(final String desiredWindowName) {
            if(desiredWindowName == null) {
                throw new IllegalArgumentException("desiredWindowName argument must not be null in method WindowObserver");
            }
            this.windowName = desiredWindowName;
        }

        abstract void newWindow(Window window);
    }

    /**
     * <code>TIMER</code> schedules a {@link #WINDOW_UPDATE_TASK}.
     */
    private static transient Timer TIMER;

    static final Map<String, Set<WindowObserver>> OBSERVERS = new Hashtable<String, Set<WindowObserver>>();

    /**
     * <code>WINDOW_UPDATE_TASK</code> checks available {@link Window}s with
     * {@link Window#getWindows()} and calls registered
     */
    static final transient TimerTask WINDOW_UPDATE_TASK = new TimerTask() {

        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
            // go through all windows
            Set<WindowObserver> observers;
            for(final Window window : Window.getWindows()) {
                if(window == null) {
                    continue;
                }
                observers = OBSERVERS.remove(window.getName());
                if(observers == null) {
                    // no observers for this window
                    continue;
                }
                for(final WindowObserver observer : observers) {
                    observer.newWindow(window);
                }
                cleanUpTimer();
            }
        }
    };

    /**
     * <code>addWindowObserver</code> registers a new {@link WindowObserver}.
     * 
     * @param observer
     *            observer to add
     * @throws IllegalArgumentException
     *             if <code>observer</code> is <code>null</code>
     */
    public static void addWindowObserver(final WindowObserver observer) {
        if(observer == null) {
            throw new IllegalArgumentException("observer argument must not be null in method addWindowObserver");
        }
        synchronized(OBSERVERS) {
            Set<WindowObserver> observers = OBSERVERS.get(observer.windowName);
            if(observers == null) {
                observers = new HashSet<WindowObserver>();
            } else {
                observers = new HashSet<WindowObserver>(observers);
            }
            observers.add(observer);
            OBSERVERS.put(observer.windowName, observers);

            if(TIMER == null) {
                // schedule a timer and a task
                TIMER = new Timer();
                TIMER.scheduleAtFixedRate(WINDOW_UPDATE_TASK, 100, 500);
            }
        }
    }

    static void cleanUpTimer() {
        synchronized(OBSERVERS) {
            if(OBSERVERS.isEmpty() && TIMER != null) {
                // no need to run the timer and its task
                TIMER.cancel();
                TIMER = null;
            }
        }
    }

    /**
     * <code>removeWindowObserver</code> unregisters an {@link WindowObserver}.
     * 
     * @param observer
     *            observer to remove
     * @throws IllegalArgumentException
     *             if <code>observer</code> is <code>null</code>
     */
    public static synchronized void removeWindowObserver(final WindowObserver observer) {
        if(observer == null) {
            throw new IllegalArgumentException("observer argument must not be null in method addWindowObserver");
        }
        synchronized(OBSERVERS) {
            Set<WindowObserver> observers = OBSERVERS.get(observer.windowName);
            if(observers == null) {
                // nothing to do
                return;
            }
            // copy observers set
            observers = new HashSet<WindowObserver>(observers);
            // remove observer from copied set
            observers.remove(observer);
            // check if any observers remain
            if(observers.isEmpty()) {
                // remove set from OBSERVERS
                OBSERVERS.remove(observer.windowName);
            } else {
                // store new observers set
                OBSERVERS.put(observer.windowName, observers);
            }
            cleanUpTimer();
        }
    }

    /**
     * <code>getWindow</code> returns an AWT window with the specified name.
     * Available names are retrieved using {@link Dialog#getWindows()}.
     * 
     * @param windowName
     *            name of the window to find
     * @return Window - window with the specified name, or <code>null</code> if
     *         no such window exists
     * @throws IllegalArgumentException
     *             if <code>windowName</code> is <code>null</code>
     * */
    public static Window getWindow(final String windowName) {
        if(windowName == null) {
            throw new IllegalArgumentException("windowName argument must not be null in method getWindow");
        }
        // go through all windows
        for(final Window w : Window.getWindows()) {
            if(w != null && windowName.equals(w.getName())) {
                // got the window
                return w;
            }
        }
        return null;
    }

    /**
     * <code>getAllComponents</code> returns all {@link Component}s of a
     * {@link Container} (including the container itself). This method traverses
     * the component hierarchy recursively, instead of only one level as
     * {@link Container#getComponents()}.
     * 
     * @param container
     *            container to get components of
     * @return Set&lt;Component&gt; - container, all components of the container
     *         and its children, never <code>null</code>
     */
    public static Set<Component> getAllComponents(final Container container) {
        if(container == null) {
            throw new IllegalArgumentException("container argument must not be null in method getAllComponents");
        }
        final Set<Component> allComponents = new HashSet<Component>();
        getAllComponents(container, allComponents);
        allComponents.add(container);
        return allComponents;
    }

    /**
     * <code>getAllComponents</code> retrieves all {@link Component}s of a
     * {@link Container}. This method traverses the component hierarchy
     * recursively, instead of only one level as
     * {@link Container#getComponents()} and fills <code>allComponents</code>
     * with all found components.
     * 
     * @param container
     *            container to get components of
     * @param allComponents
     *            all components found in the container
     */
    private static void getAllComponents(final Container container, final Set<Component> allComponents) {
        for(final Component component : container.getComponents()) {
            allComponents.add(component);
            // go recursively into component if it's a container
            if(component instanceof Container) {
                getAllComponents((Container)component, allComponents);
            }
        }
    }
}
