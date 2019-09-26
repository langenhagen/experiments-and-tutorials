package org.sercho.masp.models.Context.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.MASPDirectory;
import org.sercho.masp.models.Context.gui.enums.ElementType;
import org.sercho.masp.models.Context.gui.pathRecording.Path;

/**
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public class PathsMenuPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    private static final transient Log LOG = LogFactory.getLog(MainMenuPanel.class);

    private final VisualizerManager manager;

    private final Map<Integer, Component> pathsMap = new HashMap<Integer, Component>();

    private final Map<String, Boolean> checks = new HashMap<String, Boolean>();

    public static final String UP = "UP";

    public static final String DOWN = "DOWN";

    public static final String DEL = "DEL";

    public static final String OPEN = "OPEN PATH";

    public boolean playing = false;

    private final JFileChooser fc;

    private final JPanel paths = new JPanel();

    private int tempPos = 0;

    private final JScrollPane pane;

    private final Map<String, JPanel> elementPanels = new HashMap<String, JPanel>();

    private final Map<String, JPanel> headPanels = new HashMap<String, JPanel>();

    private final Map<String, JPanel> pathsElementPanels = new HashMap<String, JPanel>();

    private final List<JButton> pathButtons = new ArrayList<JButton>();

    private final JPanel recorderPanel = new JPanel();

    private final JButton recordMode, recordPlay, recordStop, recordPause, recordFaster,
            recordSlower, recordReverse, openRecord, storeRecord, quickStoreRecord,
            loopPlay, resetRec;

    public static final String REC_BUTTON_TOOLTIP = "Starts / stops the recording mode";

    public static final String OPEN_REC_BUTTON_TOOLTIP = "Opens a stored path recording from file system";

    public static final String SAVE_REC_BUTTON_TOOLTIP = "Saves a recording path in the file system";

    public static final String QUICK_SAVE_REC_BUTTON_TOOLTIP = "Quick save of the recording path in the file system";

    public static final String RESET_REC_BUTTON_TOOLTIP = "Resets the current path recording";

    public static final String REV_BUTTON_TOOLTIP = "Plays the current recording path in reverse mode";

    public static final String PLAY_BUTTON_TOOLTIP = "Plays the current  path recording";

    public static final String FORWARD_BUTTON_TOOLTIP = "Plays the current playback faster";

    public static final String BACKWARD_BUTTON_TOOLTIP = "Plays the current playback slower";

    public static final String STOP_BUTTON_TOOLTIP = "Stops the current path recording playback";

    public static final String PAUSE_BUTTON_TOOLTIP = "Pauses the current path recording playback";

    public static final String LOOP_BUTTON_TOOLTIP = "Activates loop mode for the current path recording playback";

    public static final String ELEMENT_SPEED_TOOLTIP = "Element's individual speed";

    public static final String INCREASE_ELEMENT_SPEED_TOOLTIP = "Increases the element's speed";

    public static final String DECREASE_ELEMENT_SPEED_TOOLTIP = "Decreases the element's speed";

    public static final String INCREASE_PATH_STARTT_TOOTLTIP = "Increases this path's start time";

    public static final String DECREASE_PATH_STARTT_TOOTLTIP = "Decreases this path's start time";

    public static final String REMOVE_PATH_TOOLTIP = "Removes this path";

    public static final String SHOW_HIDE_PATHS_TOOLTIP = "Shows or hides the paths for this element";

    public static final String COORDINATES_TOOLTIP = "(Starting coordinates) | (Ending coordinates) ";

    // Icon's paths

    public static final String REC_DISABLED_ICON = "recDisabled_big";

    public static final String REC_ENABLED_ICON = "recEnabled_big";

    public static final String PLAY_ICON = "play_big";

    public static final String STOP_ICON = "stop_big";

    public static final String PAUSE_ICON = "pause_big";

    public static final String FORWARD_ICON = "forward_big";

    public static final String BACKWARD_ICON = "backward_big";

    public static final String REVERSE_ICON = "reverse_big";

    public static final String OPEN_ICON = "open_big";

    public static final String SAVE_ICON = "saveAs_big";

    public static final String QUICK_SAVE_ICON = "save_big";

    public static final String LOOP_SELECTED_ICON = "loopEnabled_big";

    public static final String LOOP_DESELECTED_ICON = "loopDisabled_big";

    public static final String RESET_ICON = "reset_big";

    public static final String PLUS_ICON = "plus";

    public static final String DEL_PATH_ICON = "delPath";

    public static final String MINUS_ICON = "minus";

    public static final String USER_PATH_ICON = "Users";

    public static final String DEVICE_PATH_ICON = "Devices2";

    public static final String IR_PATH_ICON = "xxx_ir2";

    // Borders

    public static final String RECORDING_BORDER = "Recording";

    // Background

    public static final Color ELEMENT_PANEL_C = Color.WHITE;

    public static final Color PATHS_ELEMENT_PANEL_C = Color.WHITE;

    public static final Color PATHS_PANEL_COLOR = Color.WHITE;

    private final static Color BACKGROUND_COLOR_BUTTONS_PANEL = new Color(98, 98, 98);

    private final static Color TITTLE_BORDER_COLOR = Color.WHITE;

    public static final String OPEN_RECORD = "openRec";

    public static final String STORE_RECORD = "Save record";

    private String fileName = null;

    public PathsMenuPanel(final VisualizerManager manager) {
        super(new BorderLayout());

        // RECORDER
        this.recordMode = new JButton();
        this.recordMode.setIcon(Images.getImageIcon(REC_DISABLED_ICON));
        this.recordMode.setToolTipText(REC_BUTTON_TOOLTIP);

        this.recordPlay = new JButton();
        this.recordPlay.setIcon(Images.getImageIcon(PLAY_ICON));
        this.recordPlay.setToolTipText(PLAY_BUTTON_TOOLTIP);

        this.recordStop = new JButton();
        this.recordStop.setIcon(Images.getImageIcon(STOP_ICON));
        this.recordStop.setToolTipText(STOP_BUTTON_TOOLTIP);

        this.recordPause = new JButton();
        this.recordPause.setIcon(Images.getImageIcon(PAUSE_ICON));
        this.recordPause.setToolTipText(PAUSE_BUTTON_TOOLTIP);

        this.recordFaster = new JButton();
        this.recordFaster.setIcon(Images.getImageIcon(FORWARD_ICON));
        this.recordFaster.setToolTipText(FORWARD_BUTTON_TOOLTIP);

        this.recordSlower = new JButton();
        this.recordSlower.setIcon(Images.getImageIcon(BACKWARD_ICON));
        this.recordSlower.setToolTipText(BACKWARD_BUTTON_TOOLTIP);

        this.recordReverse = new JButton();
        this.recordReverse.setIcon(Images.getImageIcon(REVERSE_ICON));
        this.recordReverse.setToolTipText(REV_BUTTON_TOOLTIP);

        this.openRecord = new JButton();
        this.openRecord.setIcon(Images.getImageIcon(OPEN_ICON));
        this.openRecord.setToolTipText(OPEN_REC_BUTTON_TOOLTIP);

        this.storeRecord = new JButton();
        this.storeRecord.setIcon(Images.getImageIcon(SAVE_ICON));
        this.storeRecord.setToolTipText(SAVE_REC_BUTTON_TOOLTIP);

        this.quickStoreRecord = new JButton();
        this.quickStoreRecord.setIcon(Images.getImageIcon(QUICK_SAVE_ICON));
        this.quickStoreRecord.setToolTipText(QUICK_SAVE_REC_BUTTON_TOOLTIP);

        this.loopPlay = new JButton();
        this.loopPlay.setIcon(Images.getImageIcon(LOOP_DESELECTED_ICON));
        this.loopPlay.setToolTipText(LOOP_BUTTON_TOOLTIP);

        this.resetRec = new JButton();
        this.resetRec.setIcon(Images.getImageIcon(RESET_ICON));
        this.resetRec.setToolTipText(RESET_REC_BUTTON_TOOLTIP);

        this.recordMode.addActionListener(this);
        this.recordPlay.addActionListener(this);
        this.recordStop.addActionListener(this);
        this.recordPause.addActionListener(this);
        this.recordFaster.addActionListener(this);
        this.recordSlower.addActionListener(this);
        this.recordReverse.addActionListener(this);
        this.openRecord.addActionListener(this);

        this.storeRecord.addActionListener(this);
        this.quickStoreRecord.addActionListener(this);
        this.loopPlay.addActionListener(this);
        this.resetRec.addActionListener(this);

        this.recorderPanel.setLayout(new GridLayout(3, 0));
        final TitledBorder recordingPanelBorder = BorderFactory.createTitledBorder(RECORDING_BORDER);
        recordingPanelBorder.setTitleColor(TITTLE_BORDER_COLOR);

        this.recorderPanel.setBorder(recordingPanelBorder);
        this.recorderPanel.setBackground(BACKGROUND_COLOR_BUTTONS_PANEL);
        this.recorderPanel.add(this.recordMode);
        this.recorderPanel.add(this.openRecord);
        this.recorderPanel.add(this.recordMode);
        this.recorderPanel.add(this.openRecord);
        this.recorderPanel.add(this.storeRecord);
        this.recorderPanel.add(this.quickStoreRecord);

        this.recorderPanel.add(this.recordReverse);
        this.recorderPanel.add(this.recordPlay);
        this.recorderPanel.add(this.recordPause);
        this.recorderPanel.add(this.recordStop);

        this.recorderPanel.add(this.recordSlower);
        this.recorderPanel.add(this.recordFaster);
        this.recorderPanel.add(this.loopPlay);
        this.recorderPanel.add(this.resetRec);

        // Set enable/disable for each button
        this.recordMode.setEnabled(true);
        this.openRecord.setEnabled(true);
        this.storeRecord.setEnabled(false);
        this.quickStoreRecord.setEnabled(false);

        this.resetRec.setEnabled(false);
        this.recordReverse.setEnabled(false);
        this.recordPlay.setEnabled(false);
        this.recordStop.setEnabled(false);
        this.loopPlay.setEnabled(false);

        this.recordSlower.setEnabled(false);
        this.recordFaster.setEnabled(false);
        this.recordPause.setEnabled(false);

        // this.paths.setLayout(new GridLayout(0, 1));
        this.paths.setLayout(new BoxLayout(this.paths, BoxLayout.PAGE_AXIS));
        this.paths.setBackground(PATHS_PANEL_COLOR);
        this.pane = new JScrollPane(this.paths);

        add(this.recorderPanel, BorderLayout.PAGE_START);
        add(this.pane, BorderLayout.CENTER);

        // Configure file chooser
        this.fc = new JFileChooser();
        this.fc.setCurrentDirectory(MASPDirectory.getMASPDirectory());
        this.manager = manager;
        this.setBackground(PATHS_PANEL_COLOR);
        updateUI();

    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        if(e.getSource() == this.recordMode) {
            this.manager.changeRecordingMode();
        } else if(e.getSource() == this.recordPlay) {
            if(this.manager.isRecordingModeActive()) {
                this.playing = true;
                disablePathButtons();
                this.manager.startRecordingPlayback();
            }
        } else if(e.getSource() == this.recordStop) {
            if(this.manager.isRecordingModeActive()) {
                this.playing = false;
                enablePathButtons();
                this.manager.stopRecordingPlayback();
                this.loopPlay.setIcon(Images.getImageIcon(LOOP_DESELECTED_ICON));
            }
        } else if(e.getSource() == this.recordPause) {
            if(this.manager.isRecordingModeActive()) {
                this.manager.pauseRecordingPlayback();
            }
        } else if(e.getSource() == this.recordFaster) {
            if(this.manager.isRecordingModeActive()) {
                this.manager.faster();
            }
        } else if(e.getSource() == this.recordSlower) {
            if(this.manager.isRecordingModeActive()) {
                this.manager.slower();
            }
        } else if(e.getSource() == this.recordReverse) {
            if(this.manager.isRecordingModeActive()) {
                this.manager.reverse();
            }
        } else if(e.getSource() == this.openRecord) {

            openFileSelector(OPEN_RECORD);
        } else if(e.getSource() == this.storeRecord) {
            openFileSelector(STORE_RECORD);
        } else if(e.getSource() == this.quickStoreRecord) {
            // System.out.println(this.fileName);
            if(this.fileName != null) {
                this.manager.saveRecordingToFile(this.fileName);
            } else {
                openFileSelector(STORE_RECORD);
            }
        } else if(e.getSource() == this.loopPlay) {
            if(this.manager.loopPlayback()) {
                this.loopPlay.setIcon(Images.getImageIcon(LOOP_SELECTED_ICON));
            } else {
                this.loopPlay.setIcon(Images.getImageIcon(LOOP_DESELECTED_ICON));
            }

        } else if(e.getSource() == this.resetRec) {
            this.manager.resetRec();
            this.fileName = null;
            this.quickStoreRecord.setEnabled(false);
            this.loopPlay.setIcon(Images.getImageIcon(LOOP_DESELECTED_ICON));
        }
    }

    /**
     * Creates or load a component with the information of the path, stores it.
     * 
     * @param path
     *            the path that will be represented.
     */
    public void addPath(final Path path, final ElementType elementType) {
        this.tempPos++;
        createPathComponent(path, elementType);

        updateUI();
    }

    /**
     * Adds all the paths in order to be represented in the panel.
     * 
     * @param paths
     *            the collection of paths to be added and then represented.
     */

    public void addPaths(final Collection<Path> paths) {
        for(final Path path : paths) {
            if((this.manager.findUser(path.getElementId()) != null)) {
                addPath(path, ElementType.USERS);
            } else if((this.manager.findIResource(path.getElementId()) != null)) {
                addPath(path, ElementType.DEVICES);
            }
        }

    }

    public void clearElementPanels() {
        for(final JPanel panel : this.pathsElementPanels.values()) {
            panel.removeAll();
        }
        this.pathsMap.clear();

        updateUI();
    }

    /**
     * Removes all the JPanels and paths stored and update the UI.
     */
    public void clearPaths() {

        this.elementPanels.clear();
        this.pathsElementPanels.clear();
        this.headPanels.clear();
        this.paths.removeAll();
        this.pathsMap.clear();

        updateUI();
    }

    /**
     * Gets the necessary information from the path and create the swing objects
     * that will represent this path.
     * 
     * @param path
     *            the path that will be repreresented in the panel.
     */
    private void createPathComponent(final Path path, final ElementType elementType) {
        JPanel elementPanel = null;
        JPanel pathsElementPanel = null;
        JPanel headPanel = null;

        // For the new path
        JLabel label = null;
        JPanel pathPanel = null;
        JPanel buttonPanel = null;

        // Buttons for the new path
        final JButton upST, downST, delete;

        // Create buttons
        downST = new JButton();
        upST = new JButton();
        delete = new JButton();
        this.pathButtons.add(downST);
        this.pathButtons.add(upST);
        this.pathButtons.add(delete);
        // Listener for the new path buttons
        final ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final JButton button = (JButton)e.getSource();
                if(!PathsMenuPanel.this.playing) {
                    if(button == upST) {
                        PathsMenuPanel.this.manager.advancePath(Integer.parseInt(button.getName()));
                    } else if(button == downST) {
                        PathsMenuPanel.this.manager.delayPath(Integer.parseInt(button.getName()));
                    } else if(button == delete) {
                        PathsMenuPanel.this.manager.removePath(Integer.parseInt(button.getName()));
                        removePath(PathsMenuPanel.this.pathsMap.remove(Integer.parseInt(button.getName())));

                    }

                }
            }
        };

        // Setup buttons
        upST.setName(String.valueOf(path.getPathId()));
        upST.setIcon(Images.getImageIcon(PLUS_ICON));
        upST.addActionListener(listener);
        upST.setToolTipText(INCREASE_PATH_STARTT_TOOTLTIP);

        downST.setName(String.valueOf(path.getPathId()));
        downST.setIcon(Images.getImageIcon(MINUS_ICON));
        downST.addActionListener(listener);
        downST.setToolTipText(DECREASE_PATH_STARTT_TOOTLTIP);

        delete.setName(String.valueOf(path.getPathId()));
        delete.setIcon(Images.getImageIcon(DEL_PATH_ICON));
        delete.addActionListener(listener);
        delete.setToolTipText(REMOVE_PATH_TOOLTIP);

        // Label for start time
        label = new JLabel();

        // Panel for the new path
        pathPanel = new JPanel(new GridBagLayout());
        pathPanel.setBackground(Color.white);

        // Panel for the buttons for the new path
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        buttonPanel.add(upST);
        buttonPanel.add(downST);
        buttonPanel.add(delete);

        if(this.playing) {
            upST.setEnabled(false);
            downST.setEnabled(false);
            delete.setEnabled(false);
        }

        final GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.gridx = 1;
        pathPanel.add(buttonPanel, c);

        c.gridy = 1;
        c.gridx = 0;
        label.setText(String.valueOf(path.getStartTime()) + "  ");
        pathPanel.add(label, c);

        this.pathsMap.put(path.getPathId(), pathPanel);
        final TitledBorder pathPanelBorder = BorderFactory.createTitledBorder(" ( " + path.getInitialX() + "," + path.getInitialY() + ") | (" + path.getTargetX() + ", " + path.getTargetY() + ")");
        pathPanel.setBorder(pathPanelBorder);

        if((elementPanel = this.elementPanels.get(path.getElementId())) == null) {
            final JButton headPanelDownSpeed, headPanelUpSpeed;
            final JLabel headPanelSpeedLabel = new JLabel(String.valueOf(PathsMenuPanel.this.manager.getSpeed(path.getElementId()) / 10));
            headPanelSpeedLabel.setToolTipText(ELEMENT_SPEED_TOOLTIP);

            headPanelDownSpeed = new JButton();
            headPanelUpSpeed = new JButton();

            final ActionListener listener2 = new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent e) {

                    if(e.getSource() instanceof JCheckBox) {
                        final JCheckBox checkBox = (JCheckBox)e.getSource();
                        if(checkBox.isSelected()) {
                            showElementPath(checkBox.getName());
                            PathsMenuPanel.this.checks.put(checkBox.getName(), Boolean.valueOf(true));
                        } else {
                            hideElementPath(checkBox.getName());
                            PathsMenuPanel.this.checks.put(checkBox.getName(), Boolean.valueOf(false));
                        }
                    } else if(e.getSource() instanceof JButton) {
                        final JButton button = (JButton)e.getSource();

                        if(button == headPanelUpSpeed) {
                            headPanelSpeedLabel.setText(String.valueOf(PathsMenuPanel.this.manager.increaseSpeed(button.getName()) / 10));
                            // speedLabel.setText(PathsMenuPanel.this.manager.getSpeed(button.getName()));
                        } else if(button == headPanelDownSpeed) {
                            headPanelSpeedLabel.setText(String.valueOf(PathsMenuPanel.this.manager.decreaseSpeed(button.getName()) / 10));
                            // speedLabel.setText(PathsMenuPanel.this.manager.getSpeed(button.getName()));
                        }
                    }
                }

            };
            headPanelUpSpeed.setName(path.getElementId());
            headPanelUpSpeed.setIcon(Images.getImageIcon(PLUS_ICON));
            headPanelUpSpeed.addActionListener(listener2);
            headPanelUpSpeed.setToolTipText(INCREASE_ELEMENT_SPEED_TOOLTIP);

            headPanelDownSpeed.setName(path.getElementId());
            headPanelDownSpeed.setIcon(Images.getImageIcon(MINUS_ICON));
            headPanelDownSpeed.addActionListener(listener2);
            headPanelDownSpeed.setToolTipText(DECREASE_ELEMENT_SPEED_TOOLTIP);
            // Check box used to show/hide the paths of an element in the
            // visualizer
            final JCheckBox headPanelCheck = new JCheckBox();
            // check.setBackground(Color.white);
            headPanelCheck.setName(path.getElementId());
            headPanelCheck.addActionListener(listener2);
            headPanelCheck.setToolTipText(SHOW_HIDE_PATHS_TOOLTIP);
            if(this.checks.get(path.getElementId()) != null) {
                headPanelCheck.setSelected(this.checks.get(path.getElementId()));
            } else {
                headPanelCheck.setSelected(true);
            }

            // This panel contains the checkbox for show/hide the path,
            // individual speed, buttons for increase/decrease individual speed
            headPanel = new JPanel();

            //
            elementPanel = new JPanel();
            elementPanel.setBackground(ELEMENT_PANEL_C);
            elementPanel.setLayout(new BorderLayout());
            pathsElementPanel = new JPanel();
            pathsElementPanel.setBackground(PATHS_ELEMENT_PANEL_C);
            pathsElementPanel.setLayout(new GridLayout(0, 1));
            pathsElementPanel.setLayout(new BoxLayout(pathsElementPanel, BoxLayout.PAGE_AXIS));
            JLabel headPanelLabel = null;
            switch(elementType) {
                case USERS:
                    headPanelLabel = new JLabel(Images.getImageIcon(USER_PATH_ICON));
                    break;
                case DEVICES:
                    headPanelLabel = new JLabel(Images.getImageIcon(DEVICE_PATH_ICON));
                    break;
                default:
                    headPanelLabel = new JLabel(Images.getImageIcon(USER_PATH_ICON));
            }

            headPanel.add(headPanelLabel);
            headPanel.add(headPanelCheck);
            headPanel.add(headPanelSpeedLabel);
            headPanel.add(headPanelUpSpeed);
            headPanel.add(headPanelDownSpeed);

            final TitledBorder elementPathBorder = BorderFactory.createTitledBorder(path.getElementId());
            elementPathBorder.setTitleColor(TITTLE_BORDER_COLOR);

            elementPanel.setBorder(elementPathBorder);

            // Are the panels are stored in maps
            this.elementPanels.put(path.getElementId(), elementPanel);
            this.headPanels.put(path.getElementId(), headPanel);
            this.pathsElementPanels.put(path.getElementId(), pathsElementPanel);
            elementPanel.setBackground(BACKGROUND_COLOR_BUTTONS_PANEL);

            elementPanel.add(headPanel, BorderLayout.PAGE_START);
            elementPanel.add(pathsElementPanel, BorderLayout.CENTER);

            this.paths.add(elementPanel);

        } else {
            pathsElementPanel = this.pathsElementPanels.get(path.getElementId());
        }

        pathsElementPanel.add(pathPanel);

    }

    /**
     * Enables or disables buttons depending the "menu version".
     * 
     * @param version
     *            the current version of the menu.
     */
    public void createRecordingMenu(final int version) {
        if(version == 0) {
            this.recordMode.setEnabled(true);
            this.recordMode.setEnabled(true);
            this.recordMode.setIcon(Images.getImageIcon(REC_DISABLED_ICON));
            this.storeRecord.setEnabled(false);
            this.quickStoreRecord.setEnabled(false);
            this.resetRec.setEnabled(false);
            this.recordReverse.setEnabled(false);
            this.recordPlay.setEnabled(false);
            this.recordStop.setEnabled(false);
            this.loopPlay.setEnabled(false);
            this.recordSlower.setEnabled(false);
            this.recordFaster.setEnabled(false);
        } else if(version == 1) {
            this.recordMode.setEnabled(true);
            this.recordMode.setEnabled(true);
            this.recordMode.setIcon(Images.getImageIcon(REC_ENABLED_ICON));
            this.storeRecord.setEnabled(true);
            if(this.fileName != null) {
                this.quickStoreRecord.setEnabled(true);
            }
            this.recordPause.setEnabled(false);
            this.resetRec.setEnabled(true);
            this.recordReverse.setEnabled(false);
            this.recordPlay.setEnabled(true);
            this.recordStop.setEnabled(false);
            this.loopPlay.setEnabled(true);
            this.recordSlower.setEnabled(false);
            this.recordFaster.setEnabled(false);

        } else if(version == 2) {
            this.recordMode.setEnabled(true);
            this.recordMode.setEnabled(true);
            this.storeRecord.setEnabled(true);
            this.resetRec.setEnabled(true);
            this.recordReverse.setEnabled(true);
            this.recordPause.setEnabled(true);
            this.recordStop.setEnabled(true);
            this.loopPlay.setEnabled(true);
            this.recordSlower.setEnabled(true);
            this.recordFaster.setEnabled(true);

        } else if(version == 3) {
            this.recordMode.setEnabled(true);
            this.recordMode.setEnabled(true);
            this.storeRecord.setEnabled(true);
            this.recordPause.setEnabled(true);
            this.resetRec.setEnabled(true);
            this.recordReverse.setEnabled(true);
            this.recordPlay.setEnabled(true);
            this.recordStop.setEnabled(true);
            this.loopPlay.setEnabled(true);
            this.recordSlower.setEnabled(true);
            this.recordFaster.setEnabled(true);

        } else if(version == 4) {

            this.recordMode.setEnabled(true);
            this.recordMode.setEnabled(true);
            this.storeRecord.setEnabled(true);
            this.recordPause.setEnabled(true);
            this.resetRec.setEnabled(true);
            this.recordReverse.setEnabled(true);
            this.recordPlay.setEnabled(true);
            this.recordStop.setEnabled(true);
            this.loopPlay.setEnabled(true);
            this.recordSlower.setEnabled(true);
            this.recordFaster.setEnabled(true);

        } else if(version == 5) {
            this.recordMode.setEnabled(true);
            this.storeRecord.setEnabled(true);
            this.recordPause.setEnabled(true);
            this.resetRec.setEnabled(true);
            this.recordReverse.setEnabled(true);
            this.recordPlay.setEnabled(true);
            this.recordStop.setEnabled(true);
            this.loopPlay.setEnabled(true);
            this.recordSlower.setEnabled(true);
            this.recordFaster.setEnabled(true);

        }
        updateUI();
    }

    private void disablePathButtons() {
        for(final JButton button : this.pathButtons) {
            button.setEnabled(false);
        }
    }

    private void enablePathButtons() {
        for(final JButton button : this.pathButtons) {
            button.setEnabled(true);
        }
    }

    /**
     * Requests the manager hiding in the visualizer the path of the element
     * identified by elementId.
     * 
     * @param elementId
     *            the identifier of the element that must be hided
     */
    private void hideElementPath(final String elementId) {
        this.manager.hidePath(elementId);
    }

    public void openFileSelector(final String option) {

        if(option.equals(OPEN_RECORD)) {
            final int returnVal = this.fc.showOpenDialog(PathsMenuPanel.this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                final File file = this.fc.getSelectedFile();
                if(this.manager.loadRecordingFromFile(file.getName())) {
                    this.fileName = file.toString();
                    this.quickStoreRecord.setEnabled(true);
                }
            } else {

                // log.append("Open command cancelled by user." + newline);
            }
        } else if(option.equals(STORE_RECORD)) {
            final int returnVal = this.fc.showSaveDialog(PathsMenuPanel.this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                final File file = this.fc.getSelectedFile();
                this.manager.saveRecordingToFile(file.toString());
                this.fileName = file.toString();
                this.quickStoreRecord.setEnabled(true);
            } else {

                // log.append("Open command cancelled by user." + newline);
            }
        }

    }

    /**
     * Removes a swing component.
     * 
     * @param component
     *            the swing component that will be removed.
     */
    public void removePath(final Component component) {
        final Iterator<Entry<String, JPanel>> it = this.pathsElementPanels.entrySet().iterator();
        JPanel pathsElementPanel = null;
        JPanel elementPanel = null;
        String elementPanelId = null;
        while(it.hasNext()) {
            pathsElementPanel = it.next().getValue();
            pathsElementPanel.remove(component);
            if(pathsElementPanel.getComponentCount() == 0) {
                final Iterator<Entry<String, JPanel>> it2 = this.elementPanels.entrySet().iterator();
                Entry<String, JPanel> entry = null;
                while(it2.hasNext()) {
                    entry = it2.next();
                    elementPanel = entry.getValue();
                    elementPanelId = entry.getKey();
                    elementPanel.remove(pathsElementPanel);
                    if(elementPanel.getComponentCount() == 1) {
                        this.paths.remove(elementPanel);
                        break;
                    }
                }

                this.elementPanels.remove(elementPanelId);
            }
        }
        this.pathsElementPanels.remove(elementPanelId);
        updateUI();
    }

    /**
     * Requests the manager showing in the visualizer the path of the element
     * identified by elementId.
     * 
     * @param elementId
     *            the identifier of the element that must be hided
     */
    private void showElementPath(final String elementId) {
        this.manager.showPath(elementId);
    }

    /**
     * Updates all the paths. This method should be called when several paths
     * changed due to its manipulation.
     * 
     * @param paths
     *            all the paths that should be added and represented in the
     *            panel.
     */
    public void updateAllPaths(final Collection<Path> paths) {
        clearElementPanels();
        addPaths(paths);
    }

    /**
     * Updates the information of a path.
     * 
     * @param path
     *            the path which information will be updated.
     */
    public void updatePath(final Path path) {
        final JPanel pathPanel = (JPanel)this.pathsMap.get(path.getPathId());
        final TitledBorder pathPanelBorder = BorderFactory.createTitledBorder(" ( " + path.getInitialX() + "," + path.getInitialY() + ") | (" + path.getTargetX() + ", " + path.getTargetY() + ")");
        pathPanel.setBorder(pathPanelBorder);
        updateUI();

    }

}
