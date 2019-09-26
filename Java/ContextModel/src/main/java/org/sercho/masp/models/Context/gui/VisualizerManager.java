package org.sercho.masp.models.Context.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;
import org.sercho.masp.models.Context.Assistant;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.ContextProvider;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.Door;
import org.sercho.masp.models.Context.ElementWithPosition;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.EnvironmentElement;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.LocalisationProviderProxy;
import org.sercho.masp.models.Context.LocalizationTag;
import org.sercho.masp.models.Context.PhysicalDevice;
import org.sercho.masp.models.Context.Place;
import org.sercho.masp.models.Context.User;
import org.sercho.masp.models.Context.Vector;
import org.sercho.masp.models.Context.gui.enums.ElementType;
import org.sercho.masp.models.Context.gui.exceptions.InitVisualizerException;
import org.sercho.masp.models.Context.gui.exceptions.InternalErrorException;
import org.sercho.masp.models.Context.gui.exceptions.LoadingContextModelException;
import org.sercho.masp.models.Context.gui.exceptions.LoadingRecordingException;
import org.sercho.masp.models.Context.gui.exceptions.NoExistingElementException;
import org.sercho.masp.models.Context.gui.exceptions.VisualizerException;
import org.sercho.masp.models.Context.gui.forms.DeviceForm;
import org.sercho.masp.models.Context.gui.forms.DisplayForm;
import org.sercho.masp.models.Context.gui.forms.ElementForm;
import org.sercho.masp.models.Context.gui.forms.KeyboardForm;
import org.sercho.masp.models.Context.gui.forms.MicrophoneForm;
import org.sercho.masp.models.Context.gui.forms.MouseForm;
import org.sercho.masp.models.Context.gui.forms.TouchpadForm;
import org.sercho.masp.models.Context.gui.forms.TouchscreenForm;
import org.sercho.masp.models.Context.gui.forms.UserForm;
import org.sercho.masp.models.Context.gui.observer.VisualizerEvent;
import org.sercho.masp.models.Context.gui.observer.VisualizerObserver;
import org.sercho.masp.models.Context.gui.pathRecording.ElementPath;
import org.sercho.masp.models.Context.gui.pathRecording.Path;
import org.sercho.masp.models.Context.gui.pathRecording.PathRecorder;
import org.sercho.masp.models.Context.impl.EnvironmentImpl;
import org.sercho.masp.models.Context.util.EnvironmentUtility;

/**
 * This class works like a controller between the views and the application's
 * logic. For example all the functions for path recording.
 * 
 * @author Antonio Fernandez Zaragoza
 * @author Andre Schulz
 * 
 */
public class VisualizerManager {

    public enum ViewMode {
        ONE_DISPLAY, TWO_DISPLAYS_S, TWO_DISPLAYS_D, ONE_DISPLAY_Q, TWO_DISPLAYS_S_Q, TWO_DISPLAYS_D_Q, RECORDING, CLOSING, OPENING
    }

    private static final transient Log LOG = LogFactory.getLog(ContextModelVisualizer.class);

    public static void error(final Component parentComponent, final String message) {
        LOG.error(message);
        JOptionPane.showMessageDialog(parentComponent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(final String args[]) {
        final VisualizerManager manager = new VisualizerManager(null, DisplayType.NO_MENU);
        final JFrame frame = new JFrame("Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(manager.getMainPanel() != null) {
            frame.add(manager.getMainPanel());
        }
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

    public static void warning(final Component parentComponent, final String message) {
        LOG.warn(message);
        JOptionPane.showMessageDialog(parentComponent, message, "Error", JOptionPane.WARNING_MESSAGE);
    }

    private ServiceDiscovererManager serviceDiscovererManager;

    private boolean waitingClick = false;

    private User newUser;

    private final List<InteractionResource> resourcesTemp = new ArrayList<InteractionResource>();

    public final static int WARN = 0;

    public final static int INFO = 1;

    public final static int MENU_WIDTH = 350; // Pixels FLAG:AS old:207

    public final static int MENU_PATH_WIDTH = 250; // Pixels

    private ViewMode currentMode = ViewMode.ONE_DISPLAY;

    public int menuWidth = MENU_WIDTH + MENU_PATH_WIDTH;

    public boolean recordingMode = false;

    private AbstractContextModelVisualizer contextModelVisualizer;

    private MainMenuPanel mainMenuPanel;

    private PathsMenuPanel pathsMenuPanel;

    private JPanel mainPanel;

    private final List<JFrame> panels = new ArrayList<JFrame>();

    private JFrame exceptionWindowFrame;

    private JFrame visualizerFrame;

    private JFrame menuFrame;

    private JPanel menuPanel;

    private JSplitPane menuSplitpane;

    private PathRecorder recorder;

    private int order = 0;

    private int visualizerSize = 1;

    private Rectangle secondaryDisplayBounds;

    private Rectangle defaultDisplayBounds;

    private int widthDiference = 0;

    private int heightDiference = 0;

    private Rectangle menuBounds;

    private Rectangle visualizerBounds;

    private int numDisplays = 1;

    private List<String> filteredPathList;

    private Environment environment;

    private ContextMenuUtil contextMenuUtil;

    private JFrame mainFrame;

    private boolean menuSplit;

    private boolean playbackActive = false;

    public enum DisplayType {
        STANDALONE, EMBEDDED, NO_MENU
    }

    private final Collection<VisualizerObserver> observers = new ArrayList<VisualizerObserver>();

    public VisualizerManager(final Environment environment) {
        this(environment, DisplayType.STANDALONE);
        if(LOG.isDebugEnabled()) {
            LOG.debug("VisualizerManager " + environment);
        }
        // addObserver(new TestVisualizerObserver());
    }

    /**
     * Constructor that avoids selecting which version of the visualizer is
     * launched. STANDLONE: Launches both menu and visualizer in one frame.
     * EMBEBED : Creates the menu and visualizer, but the frame is no created.
     * NO_MENU : Creates the menu and visualizer, but only the visualizer is
     * added to a frame.
     * 
     * @param environment
     *            the environment that will be represented.
     * @param version
     *            the application's version to be created.
     */
    public VisualizerManager(final Environment environment, final DisplayType version) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("VisualizerManager " + version + " " + environment);
        }

        try {
            this.environment = environment;
            this.serviceDiscovererManager = ServiceDiscovererManager.create();
            this.contextMenuUtil = new ContextMenuUtil(this);
            this.recorder = new PathRecorder(this.environment, this);
            firstSetUpDisplays();

            try {
                switch(version) {
                    case STANDALONE:
                        startGUI(true);
                        break;
                    case EMBEDDED:
                        createMainPanel(true);
                        break;
                    case NO_MENU:
                        break;
                    default:
                        throw new IllegalArgumentException("unknown DISPLAY_TYPE " + version);
                }
            }
            catch(final Exception e) {
                throw new InitVisualizerException("Error starting the visualizer: ", version);
            }
        }
        catch(final InitVisualizerException e) {
            exception(new InternalErrorException(e), WARN);
        }
        catch(final Exception e) {
            exception(new InternalErrorException(e), WARN);
        }
    }

    public ContextMenuUtil getContextMenuUtil() {
        return this.contextMenuUtil;
    }

    /**
     * Indicates that the visualizer is waiting a click of the mouse in order to
     * finalize the proccess to create a new element in the context
     * 
     */
    public void addingElement() {
        this.waitingClick = true;
    }

    public void addObserver(final VisualizerObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Advances the start time of a path.
     * 
     * @param pathId
     *            the identifier of the path to be adanvanced.
     */
    public void advancePath(final int pathId) {
        this.recorder.upPath(pathId);
        this.pathsMenuPanel.updateAllPaths(this.recorder.getAllPaths());
        updatePositions();
        this.contextModelVisualizer.showPaths();
    }

    public void changeCreateElementPanel(final ElementType elementType, final String elementId, final int panelId) {
        ((JPanel)this.panels.get(panelId).getContentPane().getComponent(0)).setVisible(false);
        ((JPanel)this.panels.get(panelId).getContentPane().getComponent(0)).updateUI();
        startCreateElementPanel(elementType, elementId, panelId);

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
        // if(!this.playbackActive) {
        // User user;
        // InteractionResource resource;

        final EnvironmentElement element = EnvironmentUtility.getEnvironmentElement(this.environment, elementId);
        if(element != null) {
            if(element instanceof ElementWithPosition) {
                ((ElementWithPosition)element).setPosition(xPos, yPos, zPos);
                for(final VisualizerObserver observer : this.observers) {
                    observer.elementDragged(new VisualizerEvent(element, VisualizerEvent.DRAGGED));
                }
            }
        } else {
            // for(final Room room :
            // EnvironmentUtility.getAllRooms(this.environment)) {
            // TODO
            // }

        }

        // Device device;
        // if((user = findUser(elementId)) != null) {
        // user.setPosition(xPos, yPos, zPos);
        // for(VisualizerObserver observer : this.observers) {
        // observer.elementDragged(new VisualizerEvent(user,
        // VisualizerEvent.DRAGGED));
        // }
        // }
        // if((resource = findIResource(elementId)) != null) {
        // resource.setPosition(xPos, yPos, zPos);
        // for(VisualizerObserver observer : this.observers) {
        // observer.elementDragged(new VisualizerEvent(resource,
        // VisualizerEvent.DRAGGED));
        // }
        // }
        // }

    }

    /**
     * Changes the menu for one frame to other. If the menu is currently with
     * the visualizer in the same frame, the method splitMenuPanel is called. If
     * the menu is currently alone in one frame, it is moved with the visualizer
     * in the same frame.
     */
    public void changeMenuPanelPosition() {
        if(this.menuSplit) {
            joinMenuPanel();
        } else {
            splitMenuPanel();
        }
    }

    /**
     * Changes the recording mode status depending of the current status. If
     * currently is activated, then will be desactivated. If currently is
     * desactivated, then will be activated.
     */
    public void changeRecordingMode() {
        stopRecordingPlayback();
        if(!isRecordingModeActive()) {
            this.contextModelVisualizer.startRecordMode();
            showPaths();
            updatePositions();
            this.pathsMenuPanel.createRecordingMenu(1);
        } else {
            stopRecordingMode();
            this.pathsMenuPanel.createRecordingMenu(0);
        }
    }

    public void changeUserToLeftHanded(final String elementId) {
        findUser(elementId).setLeftHanded();

    }

    public void changeUserToRightHanded(final String elementId) {
        findUser(elementId).setRightHanded();

    }

    /**
     * Changes the current view mode and adjust the window's bounds.
     * 
     * @param mode
     *            the new mode.
     */
    public void changeViewMode(final ViewMode mode) {
        adjustWindows(mode);
    }

    public boolean checkUniqueId(final String id) {
        if((EnvironmentUtility.getEnvironmentElement(this.environment, id)) != null) {
            return false;
        }
        return true;
    }

    public void clearTempIR() {
        this.resourcesTemp.clear();
    }

    /**
     * Close the panel indicated by the panel's identifier
     * 
     * @param panelId
     *            panel's identifier to be removed
     */
    public void closeCreateElementPanel(final int panelId) {
        this.panels.get(panelId).setVisible(false);
        this.panels.get(panelId).dispose();
        this.panels.remove(panelId);
    }

    public void closeEnvironment() {
        LOG.info("Closing current environment");

        try {
            this.contextModelVisualizer.clearEnvironment();
        }
        catch(final VisualizerException e) {
            exception(e, WARN);
        }

        if(this.mainMenuPanel != null) {
            this.mainMenuPanel.clearMenu();
        }

        resetRec();
        this.recordingMode = false;
        this.environment = null;
    }

    /**
     * Closes the exception's window.
     */
    public void closeExceptionWindow() {
        if(this.exceptionWindowFrame != null) {
            this.exceptionWindowFrame.setVisible(false);
            this.exceptionWindowFrame.dispose();
        }
        this.exceptionWindowFrame = null;
    }

    public void createDevice(final String id, final String name, final String mobile) {

        final Device newDevice = ContextFactory.eINSTANCE.createDevice();
        newDevice.setEnvironment(this.environment);
        newDevice.setId(id);
        newDevice.setMobile(Boolean.valueOf(mobile));
        newDevice.setName(name);
        for(final InteractionResource ir : this.resourcesTemp) {
            ir.setDevice(newDevice);
        }

        final EnvironmentImpl contextModel = (EnvironmentImpl)this.environment;
        final List<Device> list = new ArrayList<Device>();
        list.addAll(getDevices());
        list.add(newDevice);
        contextModel.eSet(ContextPackage.ENVIRONMENT__DEVICES, list);
        this.mainMenuPanel.insertNode(id, name, "DEVICES", this.resourcesTemp);

    }

    public boolean createIR(final InteractionResource resource) {
        this.resourcesTemp.add(resource);
        return true;
    }

    /**
     * Creates the main panel. If the parameter menu is true, the main panel
     * includes the visualizer and the menu; if menu is false, the panel
     * includes only the visualizer.
     * 
     * @param menu
     *            the parameter that indicates if the menu is included in the
     *            panel.
     * @return the main panel.
     */
    public JPanel createMainPanel(final boolean menu) {
        final ContextModelVisualizer visualizer = new ContextModelVisualizer(this.environment, this);
        this.contextModelVisualizer = visualizer;

        this.mainMenuPanel = new MainMenuPanel(this);

        this.pathsMenuPanel = new PathsMenuPanel(this);

        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.LINE_AXIS));

        if(menu) {
            visualizer.setPreferredSize(new Dimension(this.visualizerBounds.width, this.visualizerBounds.height));
        } else {
            visualizer.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        }

        this.mainMenuPanel.setPreferredSize(new Dimension(MENU_WIDTH, this.menuBounds.height));
        this.mainMenuPanel.setMaximumSize(new Dimension(MENU_WIDTH, Integer.MAX_VALUE));
        this.mainMenuPanel.setMinimumSize(new Dimension(MENU_WIDTH, Integer.MIN_VALUE));

        this.pathsMenuPanel.setPreferredSize(new Dimension(MENU_WIDTH, this.menuBounds.height));
        this.pathsMenuPanel.setMaximumSize(new Dimension(MENU_WIDTH, Integer.MAX_VALUE));

        final Dimension minSize = new Dimension(1, 100);
        final Dimension prefSize = new Dimension(1, 100);
        final Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);
        final Box.Filler filler = new Box.Filler(minSize, prefSize, maxSize);

        this.mainPanel.setBackground(Color.WHITE);
        if(!menu) {
            this.mainPanel.add(visualizer);
        }

        // this.mainPanel.add(filler); //what is this filler for? seems to be
        // redundant

        if(menu) {
            this.menuPanel = new JPanel(new BorderLayout());
            final JTabbedPane menuTabbedPane = new JTabbedPane();

            menuTabbedPane.addTab("Main Menu", this.mainMenuPanel);
            menuTabbedPane.addTab("Path Menu", this.pathsMenuPanel);
            this.menuPanel.add(menuTabbedPane);

            // Label for MASP logo
            final JLabel maspLogLabel = new JLabel();
            maspLogLabel.setIcon(Images.getImageIcon("masp"));
            maspLogLabel.setBackground(Color.black);
            final JPanel bottomPanel = new JPanel();
            bottomPanel.setBackground(MainMenuPanel.BACKGROUND_COLOR);
            bottomPanel.add(maspLogLabel);

            this.menuPanel.add(bottomPanel, BorderLayout.PAGE_END);
            this.menuSplitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, visualizer, this.menuPanel);
            this.menuSplitpane.setResizeWeight(0.6);
            this.menuSplitpane.setOneTouchExpandable(true);
            this.menuSplitpane.setDividerLocation(this.visualizerBounds.width);
            this.mainPanel.add(this.menuSplitpane);

            // this.mainPanel.add(this.menuPanel);
        }// if(menu)

        if(this.secondaryDisplayBounds == null) {
            this.mainMenuPanel.disableViewOptionsButtons();
        }

        visualizer.repaint();

        return this.mainPanel;
    }// createMainPanel

    public void createUser(final String id, final String name, final String birthday) {
        this.newUser = ContextFactory.eINSTANCE.createUser();
        this.newUser.setId(id);
        this.newUser.setName(name);
        this.newUser.setBirthDate(birthday);
    }

    /**
     * Decreases the movement's speed for an individual element.
     * 
     * @param elementId
     *            the identifier of the element which speed is decreased.
     */
    public int decreaseSpeed(final String elementId) {
        return this.recorder.decreaseSpeed(elementId);

    }

    /**
     * Delays the start time of a path.
     * 
     * @param pathId
     *            the identifier of the path to be delayed.
     */
    public void delayPath(final int pathId) {
        this.recorder.downPath(pathId);
        this.pathsMenuPanel.updateAllPaths(this.recorder.getAllPaths());
        updatePositions();
        this.contextModelVisualizer.showPaths();
    }

    public void elementSelected(final String elementId) {
        // LOG.debug("VisualizerManager.elementSelected " + elementId);
        final EnvironmentElement element = getElement(elementId);
        if(element != null) {
            for(final VisualizerObserver observer : this.observers) {
                observer.elementSelected(new VisualizerEvent(element, ""));
            }

        }
    }

    public void exception(final Exception e, final int level) {
        if(level == WARN) {
            LOG.warn(e.getMessage(), e);
        } else if(level == INFO) {
            LOG.info(e.getMessage(), e);
        }
        startExceptionWindow(e, level);
    }

    /**
     * Increases the playback's speed.
     */
    public void faster() {
        this.recorder.faster();
    }

    /**
     * Indicates the visualizer that must filter (do not show) the element.
     * 
     * @param element
     *            The element to filter.
     * @param isChecked
     *            Indicates whether the check box is checked or not.
     */
    public void filter(final Object element, final boolean isChecked) {
        if(LOG.isDebugEnabled()) {
            LOG.debug(new StringBuilder("VM.filter '").append(element).append("' display: ").append(isChecked));
        }

        this.contextModelVisualizer.filter(element, isChecked);
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
            if(!this.filteredPathList.contains(path.getElementId())) {
                this.filteredPathList.add(path.getElementId());
            }

        }

        return existElement;

    }

    public Device findDevice(final String deviceId) {
        if(deviceId == null) {
            throw new IllegalArgumentException("deviceId argument must not be null in method findUser");
        }
        if(this.environment == null) {
            return null;
        }

        for(final Device device : this.environment.getDevices()) {
            if(device.getId().equals(deviceId)) {
                return device;
            }
        }
        return null;
    }

    /**
     * Finds and returns a door of the environment.
     * 
     * @param doorId
     * @return The door itself.
     */
    public Door findDoor(final String doorId) {
        if(doorId == null) {
            throw new IllegalArgumentException("doorId argument must not be null in method findDoor");
        }

        if(this.environment == null) {
            return null;
        }

        return EnvironmentUtility.getDoor(this.environment, doorId);
    }

    public boolean findElement(final String elementId) {

        if(elementId == null) {
            throw new IllegalArgumentException("elementId argument must not be null in method findElement");
        }
        if(this.environment == null) {
            return false;
        }
        if(EnvironmentUtility.getEnvironmentElement(this.environment, elementId) != null) {
            return true;
        }
        return false;
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

    public LocalizationTag findLocalizationTag(final String localizationTagId) {
        if(localizationTagId == null) {
            throw new IllegalArgumentException("localizationTagId argument must not be null in method findIResource");
        }
        if(this.environment == null) {
            return null;
        }
        for(final ContextProvider provider : this.environment.getProviders()) {
            if(provider instanceof LocalisationProviderProxy) {
                for(final LocalizationTag tag : ((LocalisationProviderProxy)provider).getTags()) {
                    if(tag.getId().equals(localizationTagId)) {
                        return tag;
                    }
                }

            }
        }
        return null;
    }

    /**
     * Finds and returns a physical device of the environment.
     * 
     * @param physicalDeviceId
     *            The ID of the physical device.
     * @return The device itself.
     */
    public PhysicalDevice findPhysicalDevice(final String physicalDeviceId) {
        if(physicalDeviceId == null) {
            throw new IllegalArgumentException("doorId argument must not be null in method findDoor");
        }

        if(this.environment == null) {
            return null;
        }

        return EnvironmentUtility.getPhysicalDevice(this.environment, physicalDeviceId);
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

    /**
     * Returns a {@link Window} of the {@link Environment} related to the
     * <code>windowId</code>.
     * 
     * @param windowId
     *            The id of the {@link Window}.
     * @return The {@link Window} related to the <code>windowId</code>.
     */
    public org.sercho.masp.models.Context.Window findWindow(final String windowId) {
        if(windowId == null) {
            throw new IllegalArgumentException("windowId argument must not be null in method findWindow");
        }

        if(this.environment == null) {
            return null;
        }

        return EnvironmentUtility.getWindow(this.environment, windowId);
    }

    public void formValuesError(final ElementForm elementForm) {
        closeCreateElementPanel(elementForm.getFrameId());
        startCreateElementPanel(ElementType.USERS, elementForm, -1);
    }

    public EList<Assistant> getAssistants() {
        if(this.environment != null) {
            return this.environment.getAssistants();
        }
        return null;
    }

    public AbstractContextModelVisualizer getContextModelVisualizer() {
        return this.contextModelVisualizer;
    }

    public EList<Device> getDevices() {
        if(this.environment != null) {
            return this.environment.getDevices();
        }
        return null;
    }

    /**
     * Gets information about displays available.
     */
    public void getDisplaysInformation() {
        for(final GraphicsDevice device : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
            if(device != GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()) {
                this.secondaryDisplayBounds = device.getDefaultConfiguration().getBounds();
                this.numDisplays = 2;
                LOG.info("Visualizer configured for 2 displays");
                break;

            }
        }
        this.defaultDisplayBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        if(this.secondaryDisplayBounds == null) {
            this.widthDiference = 0;
            this.heightDiference = 0;
        } else {
            this.widthDiference = Math.abs(this.defaultDisplayBounds.width - this.secondaryDisplayBounds.width);
            this.heightDiference = Math.abs(this.defaultDisplayBounds.height - this.secondaryDisplayBounds.height);
        }
    }

    public EnvironmentElement getElement(final String elementId) {

        if(elementId == null) {
            throw new IllegalArgumentException("elementId argument must not be null in method findElement");
        }
        if(this.environment == null) {
            return null;
        }
        return EnvironmentUtility.getEnvironmentElement(this.environment, elementId);

    }

    public Environment getEnvironment() {
        return this.environment;
    }

    public JFrame getMainFrame() {
        return this.mainFrame;
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    public EList<Place> getPlaces() {
        if(this.environment != null) {
            return this.environment.getPlaces();
        }
        return null;
    }

    public ServiceDiscovererManager getServiceDiscovererManager() {
        return this.serviceDiscovererManager;
    }

    /**
     * Returns the movement's speed of a individual element.
     * 
     * @param elementId
     *            the identifier of the element which speed is retrieved.
     * @return the movement's speed of the individual element identified by
     *         <code>elementId</code>.
     */
    public int getSpeed(final String elementId) {
        return Integer.valueOf(this.recorder.getSpeed(elementId));
    }

    public EList<User> getUsers() {
        if(this.environment != null) {
            return this.environment.getUsers();
        }
        return null;
    }

    /**
     * Indicates to the visualizer that it must hide a specific path.
     * 
     * @param elementId
     *            identifier of the element owner of the path to be hidden.
     */
    public void hidePath(final String elementId) {
        this.contextModelVisualizer.hidePath(elementId);
    }

    /**
     * Increases the movement's speed for an individual element.
     * 
     * @param elementId
     *            the identifier of the element which speed is increased.
     */
    public int increaseSpeed(final String elementId) {
        return this.recorder.increaseSpeed(elementId);

    }

    /**
     * Checks if the recording mode is active.
     * 
     * @return true if the recording mode is active, false if is desactived.
     */
    public boolean isRecordingModeActive() {
        return this.contextModelVisualizer.isRecordModeActive();
    }

    public boolean isUser(final String id) {
        if(findUser(id) != null) {
            return true;
        }
        return false;
    }

    /**
     * Puts the menu in a frame together the visualizer.
     */
    public void joinMenuPanel() {
        if((this.menuSplit) && (this.mainPanel != null)) {
            // this.mainPanel.add(this.menuPanel);

            this.menuSplitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, (ContextModelVisualizer)this.contextModelVisualizer, this.menuPanel);
            this.menuSplitpane.setResizeWeight(0.6);
            this.menuSplitpane.setDividerLocation(this.mainFrame.getWidth() / 2);
            this.menuSplitpane.setOneTouchExpandable(true);
            this.mainPanel.add(menuSplitpane);

            // this.mainPanel.add(this.mainMenuPanel);
            // this.mainPanel.add(this.pathsMenuPanel);

            this.mainPanel.updateUI();
            this.menuFrame.dispose();
            this.menuFrame.setVisible(false);
            this.menuSplit = false;
        }
    }

    public boolean loadEnvironment(final File file) {
        if(file != null) {
            LOG.info("Loading environment from file: " + file.getName());
            closeEnvironment();

            try {
                this.environment = ContextModelLoader.loadEnvironmentFromFile(file);
                this.contextMenuUtil.setEnvironment(this.environment);
            }
            catch(final LoadingContextModelException e) {
                exception(new InternalErrorException(e), WARN);
            }
            catch(final Exception e) {
                exception(new InternalErrorException(e), WARN);
            }

            this.contextModelVisualizer.loadEnvironment(this.environment);
            this.recorder = new PathRecorder(this.environment, this);
            return true;
        } else {
            try {
                throw new Exception("File reference is null");
            }
            catch(final Exception e) {
                exception(new InternalErrorException(e), WARN);
            }

            return false;
        }
    }

    /**
     * Loads a recording from a file.
     * 
     * @param file
     *            the file of the recording.
     */
    public boolean loadRecordingFromFile(final String file) {
        boolean result = true;
        // InteractionResource resource = null;
        int lastStartTime = 0;
        Path lastPath = null;
        this.filteredPathList = new ArrayList<String>();
        // Adjusting menu size
        adjustWindows(ViewMode.RECORDING);
        // Activanting recording mode
        startRecordingMode();
        // If there is paths, remove all before load the new paths
        this.recorder.clearPaths();
        this.contextModelVisualizer.clearPaths();
        if(this.pathsMenuPanel != null) {
            this.pathsMenuPanel.clearPaths();
        }
        // Loading and adding all the paths
        try {
            this.filteredPathList = this.recorder.loadRecording(file);
            this.pathsMenuPanel.addPaths(this.recorder.getAllPaths());
        }
        catch(final LoadingRecordingException e) {
            exception(new InternalErrorException(e), WARN);
            result = false;
        }

        catch(final NoExistingElementException e) {
            exception(new InternalErrorException(e), INFO);
            result = false;
        }
        catch(final Exception e) {
            exception(new InternalErrorException(e), WARN);
            result = false;
        }

        // Showing the new paths in the visualizer
        for(final ElementPath elementPath : this.recorder.getElementsPath()) {
            this.contextModelVisualizer.showPath(elementPath.getElementId(), elementPath);
        }

        // Moving the users to the last point in the paths
        for(final ElementPath elementPath : this.recorder.getElementsPath()) {
            lastStartTime = -1;
            for(final Path path : elementPath.getPaths()) {
                if(lastStartTime < path.getStartTime()) {
                    lastPath = path;
                }
            }
            if(lastPath != null) {
                changeElementPosition(elementPath.getElementId(), lastPath.getTargetX(), lastPath.getTargetY(), 0);
            } else {
                changeElementPosition(elementPath.getElementId(), elementPath.getTheFirstPath().getInitialX(), elementPath.getTheFirstPath().getInitialY(), 0);
            }
            // if((user = findUser(elementPath.getElementId())) != null) {
            // if(lastPath != null) {
            // user.setPosition(lastPath.getTargetX(), lastPath.getTargetY(),
            // 0);
            // } else {
            // user.setPosition(elementPath.getTheFirstPath().getInitialX(),
            // elementPath.getTheFirstPath().getInitialY(), 0);
            // }
            // }
            //
            // if((resource = findIResource(elementPath.getElementId())) !=
            // null) {
            // if(lastPath != null) {
            // resource.setPosition(lastPath.getTargetX(),
            // lastPath.getTargetY(), 0);
            // } else {
            // resource.setPosition(elementPath.getTheFirstPath().getInitialX(),
            // elementPath.getTheFirstPath().getInitialY(), 0);
            // }
            //
            // }
        }
        LOG.warn("Path recording loaded correctly from file: " + file);
        return result;
    }

    /**
     * Changes the loop mode for the playback and returns the current mode
     * (on/off).
     * 
     * @return the current state for the loop mode, true if is on, false if is
     *         off.
     */
    public boolean loopPlayback() {
        return this.recorder.setLoopMode();
    }

    /**
     * Looks if the position defined by posX and posY is contained in a place.
     * In this case, calls the method addUser in order to create the user in the
     * place.
     * 
     * @param posX
     *            x position of the user
     * @param posY
     *            y position of the user
     */
    public void notifyClick(final double posX, final double posY) {
        if(this.waitingClick) {
            final Vector position = ContextFactory.eINSTANCE.createVector();
            position.setCoordinates(posX, posY, 0);
            for(final Place place : getPlaces()) {

                if(place.contains(position)) {
                    addUser(posX, posY, place);

                    this.waitingClick = false;
                }
            }

        }
    }

    /**
     * Pauses the current recording playback.
     */
    public void pauseRecordingPlayback() {
        this.pathsMenuPanel.createRecordingMenu(4);
        this.recorder.pause();
    }

    /**
     * Removes an element from the context.
     * 
     * @param elementId
     *            element's identifier to be removed
     */
    public void removeElement(final String elementId) {
        if(this.environment == null) {
            return;
        }
        User userToRemove = null;

        for(final User user : this.environment.getUsers()) {
            if(user.getId().equals(elementId)) {
                userToRemove = user;
            }
        }
        if(userToRemove != null) {
            this.environment.removeUser(userToRemove);
            for(final VisualizerObserver observer : this.observers) {
                observer.elementRemoved(new VisualizerEvent(userToRemove, ""));
            }
            this.recorder.removeElementPath(userToRemove.getId());
            this.contextModelVisualizer.hidePath(userToRemove.getId());
        }
    }

    /**
     * Removes a element's path from the recorder, updating the position of the
     * element.
     * 
     * @param pathId
     *            the identifier of the path to be removed.
     */
    public void removePath(final int pathId) {
        this.recorder.removePath(pathId);
        updatePositions();
        this.contextModelVisualizer.showPaths();
    }

    /**
     * Resets the current recording.
     */
    public void resetRec() {
        if(isRecordingModeActive()) {
            stopRecordingPlayback();
            returnToOriginalPos();
            this.recorder.clearPaths();
            this.contextModelVisualizer.clearPaths();
            this.pathsMenuPanel.clearPaths();
        }

    }

    /**
     * Indicates to the recorder that the playback must be in reverse mode.
     */
    public void reverse() {
        this.pathsMenuPanel.createRecordingMenu(3);
        this.recorder.reverse();
    }

    /**
     * Saves the current recording to a file.
     * 
     * @param file
     *            the file where the recroding is saved.
     */
    public void saveRecordingToFile(final String file) {
        try {
            this.recorder.saveRecord(file);
        }
        catch(final JAXBException e) {
            exception(e, WARN);
            e.printStackTrace();
        }
        catch(final FileNotFoundException e) {
            exception(e, WARN);
            e.printStackTrace();
        }
    }

    public void setAreaEditMode(final boolean selected) {
        this.contextModelVisualizer.setAreaEditMode(selected);
    }

    public void setMainFrame(final JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * Gives data of a {@link Path} to the recorder in order to create a new
     * path for the context element with the identifier elementId
     * 
     * @param elementId
     *            element's identifier
     * @param targetX
     *            final position of the path for x
     * @param targetY
     *            final position of the path for y
     */
    public void setPath(final String elementId, final int targetX, final int targetY) {

        final EnvironmentElement element = EnvironmentUtility.getEnvironmentElement(this.environment, elementId);
        if(element != null) {
            if(element instanceof ElementWithPosition) {
                addPath((ElementWithPosition)element, elementId, targetX, targetY, ElementType.USERS);
            }
        }

        // final User user = findUser(elementId);
        // final InteractionResource resource = findIResource(elementId);
        //
        // if(user != null) {
        // addPath(user, elementId, targetX, targetY, ElementTypes.USERS);
        // }
        // if(resource != null) {
        // addPath(resource, elementId, targetX, targetY, ElementTypes.DEVICES);
        // }

    }

    /**
     * Indicates to the visualizer that it must show a specific path.
     * 
     * @param elementId
     *            identifier of the element owner of the path to be shown.
     */
    public void showPath(final String elementId) {
        this.contextModelVisualizer.showPath(elementId, this.recorder.getElementPath(elementId));
    }

    /**
     * Reduces the playback's speed.
     */
    public void slower() {
        this.recorder.slower();
    }

    /**
     * Creates a new frame and puts the menu in this new frame, removing the
     * menu from the other frame where is the visualizer.
     */
    public void splitMenuPanel() {
        if((!this.menuSplit) && (this.mainPanel != null)) {
            final VisualizerManager VM = this;
            final JFrame frame = new JFrame("Menu");

            frame.addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent e) {
                    VM.joinMenuPanel();
                }
            });

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            this.mainPanel.remove(this.menuSplitpane);
            this.mainPanel.add((ContextModelVisualizer)this.contextModelVisualizer);

            frame.add(this.menuPanel);

            // this.mainPanel.remove(this.mainMenuPanel);
            // this.mainPanel.remove(this.pathsMenuPanel);
            // final JPanel panel = new JPanel(new GridLayout(1, 2));
            // panel.add(this.mainMenuPanel);
            // panel.add(this.pathsMenuPanel);
            // frame.add(panel);

            frame.pack();
            frame.setVisible(true);
            this.mainPanel.updateUI();
            this.menuFrame = frame;
            this.menuSplit = true;
        }
    }// splitMenuPanel

    public void startCreateElementPanel(final ElementType elementType) {
        startCreateElementPanel(elementType, "", -1);
    }

    public void startCreateElementPanel(final ElementType elementType, final String elementId) {
        startCreateElementPanel(elementType, elementId, -1);
    }

    public void startCreateElementPanel(final ElementType elementType, final String elementId, final int panelId) {
        int newPanelId;
        EnvironmentElement element = null;
        if(!elementId.equals("")) {
            // Look for the element

            for(final User user : getUsers()) {
                if(user.getId().equals(elementId)) {
                    element = user;
                }
            }

            for(final Device device : getDevices()) {
                for(final InteractionResource ir : device.getResources()) {
                    if((ir.getId() != null) && ir.getId().equals(elementId)) {
                        element = ir;
                    }
                }
            }
        }
        CreateElementPanel panel = null;

        if(panelId == -1) {
            newPanelId = this.panels.size();
        } else {
            newPanelId = panelId;
        }

        switch(elementType) {
            case USERS:
                panel = new CreateElementPanel(elementType, this, new UserForm(element, newPanelId));
                break;
            case USER:
                panel = new CreateElementPanel(elementType, this, new UserForm(element, newPanelId));
                break;
            case DEVICES:
                panel = new CreateElementPanel(elementType, this, new DeviceForm(element, newPanelId));
                break;
            case TOUCHPAD:
                panel = new CreateElementPanel(elementType, this, new TouchpadForm(element, newPanelId));
                break;
            case TOUCHSCREEN:
                panel = new CreateElementPanel(elementType, this, new TouchscreenForm(element, newPanelId));
                break;
            case MOUSE:
                panel = new CreateElementPanel(elementType, this, new MouseForm(element, newPanelId));
                break;
            case KEYBOARD:
                panel = new CreateElementPanel(elementType, this, new KeyboardForm(element, newPanelId));
                break;
            case MICROPHONE:
                panel = new CreateElementPanel(elementType, this, new MicrophoneForm(element, newPanelId));
                break;
            case DISPLAY:
                panel = new CreateElementPanel(elementType, this, new DisplayForm(element, newPanelId));
                break;
            case LOUDSPEAKER:
                panel = new CreateElementPanel(elementType, this, new DisplayForm(element, newPanelId));
                break;
        }

        if(panel == null) {
            LOG.warn("panel is null");
        }

        if(panelId == -1) {
            final JFrame frame = new JFrame("New element");
            frame.getContentPane().add(panel);

            // Display the window
            frame.pack();
            frame.setVisible(true);

            frame.setResizable(false);
            frame.setBounds(this.menuBounds.x, this.menuBounds.y + frame.getHeight(), frame.getWidth(), frame.getHeight());
            this.panels.add(frame);
        } else {
            this.panels.get(panelId).getContentPane().add(panel);
        }

    }

    /**
     * Starts the main frame. If menu is true, the menu is also included in the
     * frame.
     * 
     * @param menu
     *            the parameter that indicates if the menu panel must be added
     *            to the frame.
     */
    public void startGUI(final boolean menu) {

        LOG.info("Starting menu window");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                startMainFrame(menu);
            }
        });
    }

    /**
     * Creates the menu frame, with the controls of the application.
     */
    public void startMainFrame(final boolean menu) {
        final JFrame frame = new JFrame("Visualizer");

        frame.addWindowListener(new WindowListener() {

            @Override
            public void windowActivated(final WindowEvent e) {
                // empty
            }

            @Override
            public void windowClosed(final WindowEvent e) {
                // empty
            }

            @Override
            public void windowClosing(final WindowEvent e) {

                VisualizerManager.this.mainFrame.dispose();

                // dispose all other GUI windows
                for(final Window window : Window.getWindows()) {
                    if(window.getClass().toString().contains("org.sercho.masp.models.Context.gui")) {
                        window.dispose();
                    }
                }

                // System.exit(0);
            }// windowClosing

            @Override
            public void windowDeactivated(final WindowEvent e) {
                // empty
            }

            @Override
            public void windowDeiconified(final WindowEvent e) {
                // empty
            }

            @Override
            public void windowIconified(final WindowEvent e) {
                // empty
            }

            @Override
            public void windowOpened(final WindowEvent e) {
                // empty
            }
        });

        frame.add(createMainPanel(menu));
        frame.pack();
        frame.setVisible(true);

        // FLAG:AS test
        frame.setSize(1280, 800);
        // frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.mainFrame = frame;
    }

    /**
     * Starts the recording mode.
     */
    public void startRecordingMode() {
        stopRecordingPlayback();
        this.contextModelVisualizer.startRecordMode();
        showPaths();
    }

    /**
     * Starts a recording playback.
     */
    public void startRecordingPlayback() {
        this.playbackActive = true;
        this.pathsMenuPanel.createRecordingMenu(2);
        this.recorder.play();
        this.contextModelVisualizer.startPlayMode();
    }

    /**
     * Creates the main frame of the application where is visualized the rooms,
     * users, interaction resources, etc.
     */
    public void startVisualizer() {
        // Create and set up the window.
        final JFrame frame = new JFrame("Context Model GUI");
        // Create and set up the content pane.
        final ContextModelVisualizer visualizer = new ContextModelVisualizer(this.environment, this);
        frame.getContentPane().add(visualizer);
        // Display the window
        frame.setVisible(true);
        this.visualizerFrame = frame;
        this.visualizerFrame.setBounds(this.visualizerBounds);
        this.contextModelVisualizer = visualizer;
    }

    /**
     * Stops the recording mode.
     */
    public void stopRecordingMode() {
        this.playbackActive = false;
        returnToOriginalPos();
        this.contextModelVisualizer.stopRecordMode();
        hidePaths();
    }

    /**
     * Stops the recording playback and put the users in the last position of
     * their paths in order to continue editing the recording.
     */
    public void stopRecordingPlayback() {
        if(this.pathsMenuPanel != null) {
            this.pathsMenuPanel.createRecordingMenu(1);
        }
        this.recorder.stop();

        updatePositions();

        this.contextModelVisualizer.stopPlayMode();
        showPaths();
    }

    /**
     * Updates a path's information in the path panel.
     * 
     * @param pathChanged
     *            the path that has changes and which information must be
     *            updated in the path panel.
     */
    public void updatePathPanel(final Path pathChanged) {
        this.pathsMenuPanel.updatePath(pathChanged);

    }

    /**
     * Adds a new path.
     * 
     * @param element
     *            the element which new path is added.
     * @param elementId
     *            the identifier of the element which path new path is added.
     * @param targetX
     *            x coordinate of the target coordinates of the path.
     * @param targetY
     *            y coordinate of the target coordinates of the path.
     * @param elementType
     *            the type of element which new path is added.
     */
    private void addPath(final ElementWithPosition element, final String elementId, final int targetX, final int targetY, final ElementType elementType) {
        final int initialX;
        final int initialY;
        if(element.getPosition() != null) {
            initialX = Double.valueOf(element.getPosition().getX()).intValue();
            initialY = Double.valueOf(element.getPosition().getY()).intValue();
        } else {
            initialX = 0;
            initialY = 0;
        }

        if(element != null) {
            if(this.recorder.setPath(elementId, this.order, initialX, targetX, initialY, targetY)) {
                showPath(elementId);
            }
            this.order++;
        }
        this.pathsMenuPanel.addPath(this.recorder.getLastPath(), elementType);

    }

    /**
     * Adds a new user to context and notifies the menu panel in order to create
     * a new node in the menu tree
     * 
     * @param posX
     *            x position of the new user
     * @param posY
     *            y position of the new user
     * @param place
     *            place where the new user is positioned
     */
    private void addUser(final double posX, final double posY, final Place place) {
        User user = null;
        if(checkUniqueId(this.newUser.getId())) {
            final EnvironmentImpl contextModel = (EnvironmentImpl)this.environment;
            contextModel.addUser(this.newUser.getId(), this.newUser.getName(), this.newUser.getName(), this.newUser.getBirthDate());
            user = findUser(this.newUser.getId());
            if(user != null) {
                user.setPosition(posX, posY, 0);
                user.setEnvironment(this.environment);
                user.setPlace(place);
            }
            for(final VisualizerObserver observer : this.observers) {
                observer.elementAdded(new VisualizerEvent(user, ""));
            }
            this.mainMenuPanel.insertNode(this.newUser.getId(), this.newUser.getName(), "USERS", null);
        }

    }

    /**
     * Sets up the bounds for the visualizer and menu depending of the current
     * view mode.
     * 
     * @param mode
     *            the view mode.
     */
    private void adjustWindows(ViewMode mode) {
        if((mode != ViewMode.ONE_DISPLAY)) {
            if((this.currentMode == mode) && (this.visualizerSize == 1)) {
                this.visualizerSize = 2;
            } else if((this.currentMode == mode) && (this.visualizerSize == 2)) {
                this.visualizerSize = 1;
            }
        } else {
            this.visualizerSize = 1;
        }

        if(mode == ViewMode.CLOSING) {
            mode = this.currentMode;
        }

        if(mode == ViewMode.OPENING) {
            mode = this.currentMode;
        }

        if((mode == ViewMode.TWO_DISPLAYS_S) && (this.numDisplays > 1)) {
            splitMenuPanel();

            this.menuBounds = new Rectangle((this.defaultDisplayBounds.x + this.defaultDisplayBounds.width) - this.menuWidth, this.defaultDisplayBounds.y, this.menuWidth, this.defaultDisplayBounds.height - this.defaultDisplayBounds.y);
            this.visualizerBounds = new Rectangle(this.secondaryDisplayBounds.x, this.secondaryDisplayBounds.y, this.secondaryDisplayBounds.width, this.secondaryDisplayBounds.height);
            this.currentMode = ViewMode.TWO_DISPLAYS_S;
        } else if((mode == ViewMode.TWO_DISPLAYS_D) && (this.numDisplays > 1)) {
            splitMenuPanel();

            this.menuBounds = new Rectangle((this.secondaryDisplayBounds.x + this.secondaryDisplayBounds.width) - (this.menuWidth), this.secondaryDisplayBounds.y, this.menuWidth, this.secondaryDisplayBounds.height);
            this.visualizerBounds = new Rectangle(this.defaultDisplayBounds.x, this.defaultDisplayBounds.y, this.defaultDisplayBounds.width, this.defaultDisplayBounds.height - this.defaultDisplayBounds.y);
            this.currentMode = ViewMode.TWO_DISPLAYS_D;
        } else if((mode == ViewMode.ONE_DISPLAY)) {
            changeMenuPanelPosition();
            this.menuBounds = new Rectangle(this.defaultDisplayBounds.width - (this.menuWidth), this.defaultDisplayBounds.y, this.menuWidth, this.defaultDisplayBounds.height - this.defaultDisplayBounds.y);
            if(this.menuSplit) {
                this.visualizerBounds = new Rectangle(this.defaultDisplayBounds.x, this.defaultDisplayBounds.y, this.defaultDisplayBounds.width - (this.menuWidth), this.defaultDisplayBounds.height - this.defaultDisplayBounds.y);
            } else {
                this.visualizerBounds = new Rectangle(this.defaultDisplayBounds.x, this.defaultDisplayBounds.y, this.defaultDisplayBounds.width, this.defaultDisplayBounds.height);
            }

            this.currentMode = ViewMode.ONE_DISPLAY;
        }

        if(this.mainFrame != null) {
            this.mainFrame.setExtendedState(Frame.NORMAL);
            this.mainFrame.setBounds(this.visualizerBounds.x, this.visualizerBounds.y, this.visualizerBounds.width - this.widthDiference, this.visualizerBounds.height - this.heightDiference);
            this.mainFrame.setBounds(this.visualizerBounds.x, this.visualizerBounds.y, this.visualizerBounds.width / this.visualizerSize, this.visualizerBounds.height / this.visualizerSize);
        }

        if(this.menuFrame != null) {
            this.menuFrame.setBounds(this.menuBounds.x, this.menuBounds.y, this.menuBounds.width - this.widthDiference, this.menuBounds.height - this.heightDiference);
            this.menuFrame.setBounds(this.menuBounds);
        }

    }

    /**
     * Sets up the displays configuration for the first time.
     */
    private void firstSetUpDisplays() {
        getDisplaysInformation();
        adjustWindows(ViewMode.OPENING);
    }

    /**
     * Indicates to the visualizer that it must hide all the paths.
     */
    private void hidePaths() {
        this.contextModelVisualizer.hidePaths();
    }

    /**
     * Returns all the elements with a path recorded to its original position.
     * 
     */
    private void returnToOriginalPos() {
        // User user = null;
        // InteractionResource resource = null;
        // EnvironmentElement element = null;

        for(final ElementPath elementPath : this.recorder.getElementsPath()) {
            changeElementPosition(elementPath.getElementId(), elementPath.getTheFirstPath().getInitialX(), elementPath.getTheFirstPath().getInitialY(), 0);

            // if((user = findUser(elementPath.getElementId())) != null) {
            // user.setPosition(elementPath.getTheFirstPath().getInitialX(),
            // elementPath.getTheFirstPath().getInitialY(), 0);
            // }
            // if((resource = findIResource(elementPath.getElementId())) !=
            // null) {
            // resource.setPosition(elementPath.getTheFirstPath().getInitialX(),
            // elementPath.getTheFirstPath().getInitialY(), 0);
            //
            // }

        }
    }

    /**
     * Indicates to the visualizer that it must show all the paths.
     */
    private void showPaths() {
        this.contextModelVisualizer.showPaths();

    }

    private void startCreateElementPanel(final ElementType elementType, final ElementForm elementForm, final int panelId) {
        CreateElementPanel panel = null;

        switch(elementType) {
            case USERS:
                panel = new CreateElementPanel(elementType, this, elementForm);
                break;
            case DEVICES:
                panel = new CreateElementPanel(elementType, this, elementForm);
                break;
            case TOUCHPAD:
                panel = new CreateElementPanel(elementType, this, elementForm);
                break;
            case TOUCHSCREEN:
                panel = new CreateElementPanel(elementType, this, elementForm);
                break;
            case MOUSE:
                panel = new CreateElementPanel(elementType, this, elementForm);
                break;
            case KEYBOARD:
                panel = new CreateElementPanel(elementType, this, elementForm);
                break;
            case MICROPHONE:
                panel = new CreateElementPanel(elementType, this, elementForm);
                break;
            case DISPLAY:
                panel = new CreateElementPanel(elementType, this, elementForm);
                break;
            case LOUDSPEAKER:
                panel = new CreateElementPanel(elementType, this, elementForm);
                break;
        }

        if(panelId == -1) {
            final JFrame frame = new JFrame("New element");

            frame.getContentPane().add(panel);

            // Display the window
            frame.pack();
            frame.setVisible(true);

            frame.setResizable(false);
            frame.setBounds(this.menuBounds.x, this.menuBounds.y + frame.getHeight(), frame.getWidth(), frame.getHeight());
            this.panels.add(frame);
        } else {
            this.panels.get(panelId).getContentPane().add(panel);
        }

    }

    private void startExceptionWindow(final Exception exception, final int level) {
        if(this.exceptionWindowFrame == null) {
            LOG.warn("failed to open exception window: exception window already exists");
            return;
        }

        LOG.info("Starting exception window for exception : " + exception.getMessage());
        // Create and set up the window.
        final JFrame frame = new JFrame("Error");
        // Create and set up the content pane.
        final ExceptionWindow exceptionWindow = new ExceptionWindow(exception, this, level);

        exceptionWindow.setOpaque(true);

        frame.add(exceptionWindow);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
        // frame.setBounds(10, 200, 800, 200);
        this.exceptionWindowFrame = frame;
    }

    /**
     * Updates the position of all the elements with paths recorded.
     */
    private void updatePositions() {
        int lastStartTime = 0;
        Path lastPath = null;
        // User user = null;
        // InteractionResource resource = null;
        for(final ElementPath elementPath : this.recorder.getElementsPath()) {
            lastStartTime = 0;
            if(elementPath.getPaths().size() == 0) {
                lastPath = null;
            } else {
                for(final Path path : elementPath.getPaths()) {
                    if(lastStartTime <= path.getStartTime()) {
                        lastStartTime = path.getStartTime();
                        lastPath = path;
                    }
                }
            }
            if(lastPath != null) {
                changeElementPosition(elementPath.getElementId(), lastPath.getTargetX(), lastPath.getTargetY(), 0);
            } else {
                changeElementPosition(elementPath.getElementId(), elementPath.getTheFirstPath().getInitialX(), elementPath.getTheFirstPath().getInitialY(), 0);
            }

            // if((user = findUser(elementPath.getElementId())) != null) {
            // if(lastPath != null) {
            // user.setPosition(lastPath.getTargetX(), lastPath.getTargetY(),
            // 0);
            // } else {
            // user.setPosition(elementPath.getTheFirstPath().getInitialX(),
            // elementPath.getTheFirstPath().getInitialY(), 0);
            // }
            // }
            // if((resource = findIResource(elementPath.getElementId())) !=
            // null) {
            // if(lastPath != null) {
            // resource.setPosition(lastPath.getTargetX(),
            // lastPath.getTargetY(), 0);
            // } else {
            // resource.setPosition(elementPath.getTheFirstPath().getInitialX(),
            // elementPath.getTheFirstPath().getInitialY(), 0);
            // }
            // }
        }

    }

    /**
     * TODO comment
     * 
     * @param file
     *            The {@link File} where the {@link Environment} should be
     *            stored.
     * @param overwrite
     *            Indicates to overwrite an existing {@link File}.
     * @return <code>true</code> if the {@link File} was saved,
     *         <code>false</code> otherwise.
     */
    public boolean saveEnvironment(final File file, final boolean overwrite) {
        if(file != null) {
            LOG.info("Save environment to file: " + file.getName());
            // TODO implement saving
            return false;
        } else {
            exception(new InternalErrorException(new IOException("File reference is null")), WARN);
            return false;
        }
    }
}
