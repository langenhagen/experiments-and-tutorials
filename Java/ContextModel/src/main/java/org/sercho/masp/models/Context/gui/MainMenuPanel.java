package org.sercho.masp.models.Context.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.sercho.masp.models.MASPDirectory;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.gui.enums.RootElementType;
import org.sercho.masp.models.Context.gui.panels.EnvironmentTreePanel;
import org.sercho.masp.models.Context.gui.panels.EnvironmentTreePanel.CheckBoxRootTreeNode;
import org.sercho.masp.models.Context.gui.panels.EnvironmentTreePanel.ConfigurationPropertyTreeNode;
import org.sercho.masp.models.Context.gui.panels.EnvironmentTreePanel.EnvironmentElementTreeNode;
import org.sercho.masp.models.Context.gui.panels.EnvironmentTreePanel.EnvironmentTreeListener;
import org.sercho.masp.models.Context.gui.panels.EnvironmentTreePanel.EnvironmentTreeNode;
import org.sercho.masp.models.Context.gui.panels.EnvironmentTreePanel.LocalisationProviderProxyTreeNode;
import org.sercho.masp.models.Context.gui.panels.EnvironmentTreePanel.LocalizationTagTreeNode;
import org.sercho.masp.models.Context.gui.panels.EnvironmentTreePanel.PropertyTreeNode;
import org.sercho.masp.models.Context.gui.panels.EnvironmentTreePanel.RootTreeNode;
import org.sercho.masp.models.Context.gui.panels.EnvironmentTreePanel.ServiceContainerTreeNode;
import org.sercho.masp.models.Context.gui.panels.EnvironmentTreePanel.ServiceContainersRootTreeNode;
import org.sercho.masp.models.Context.gui.panels.EnvironmentTreePanel.ServiceTreeNode;
import org.sercho.masp.models.Context.gui.panels.EnvironmentTreePanel.SimpleCheckBoxTreeNode;

/**
 * 
 * @author Antonio Fernandez Zaragoza
 * @author Andre Schulz
 * 
 */
public class MainMenuPanel extends JPanel implements ActionListener, PopupInvoker,
        TreeSelectionListener, EnvironmentTreeListener {

    // TODO store notifier, to remove adapters while deleting nodes

    private final ContextMenuUtil contextMenuUtil;

    private static final long serialVersionUID = 1L;

    private static final transient Log LOG = LogFactory.getLog(MainMenuPanel.class);

    private final VisualizerManager manager;

    JFileChooser fc;

    private final JButton openButton;

    private final JButton saveButton;

    JButton changeViewButton1, changeViewButton2, changeViewButton3, changeViewButton4;

    public static final String OPEN = "Open record";

    public static final String OPEN_ENV = "openEnv";

    public static final String LOOP = "Loop";

    public static final String RESET = "Reset";

    public static final String VIEW = "1";

    public static final String VIEW2 = "2";

    public static final String VIEW3 = "2|2";

    public static final String VIEW4 = "2|3";

    public static final String OPEN_MODEL_BUTTON_TOOLTIP = "Opens a model from file system";

    public static final String CLOSE_MODEL_BUTTON_TOOLTIP = "Closes the current model";

    public static final String SAVE_MODEL_BUTTON_TOOLTIP = "Saves the current model ...";

    public static final String VIEW1_BUTTON_TOOLTIP = "Both visualizer and menus are displayed in one display";

    public static final String VIEW2_BUTTON_TOOLTIP = "The visualizer is displayed alone in the main display and menus in the secondary display";

    public static final String VIEW3_BUTTON_TOOLTIP = "The visualizer is displayed alone in one secondary display and menus in the main another";

    public static final String OPEN_RECORD = "openRec";

    public static final String REC_BUTTON_TOOLTIP = "Starts the recording mode";

    public static final String OPEN_REC_BUTTON_TOOLTIP = "Opens a stored path recording from file system";

    JButton recordMode, openRecord;

    private JTree environmentTree;

    private JScrollPane treePanel;

    // private EnvironmentTreeNode interactionRVector;

    private final static String VIEW0_ICON = "view0_big";

    private final static String VIEW1_ICON = "view1_big";

    private final static String VIEW2_ICON = "view2_big";

    private final static String VIEW_BORDER_TEXT = "View Modes";

    private final static String FILE_BORDER_TEXT = "File operations";

    private final static String EDIT_BORDER_TEXT = "Edit Modes";

    private final static String TREE_BORDER_TEXT = "Environment";

    protected final static Color BACKGROUND_COLOR = new Color(98, 98, 98);

    private final static String OPEN_MODEL_TEXT = "Load model ";

    private final static String OPEN_MODEL_ICON = "open_big";

    // private final static String CLOSE_MODEL_TEXT = "Close model";
    // private final static String CLOSE_MODEL_ICON = "close";

    private final static String SAVE_MODEL_TEXT = "Save model";

    private final static String SAVE_MODEL_ICON = "saveAs_big";

    private JPopupMenu popupMenu;

    EContentAdapter adapter;

    private final JCheckBox areaEditModeCB;

    /**
     * Constructor. A tree is created and filled with a environment's data. Then
     * all the panels, buttons and other graphical elements are created and
     * setup.
     * 
     * @param manager
     */
    public MainMenuPanel(final VisualizerManager manager) {
        super(new BorderLayout());

        this.manager = manager;
        this.contextMenuUtil = manager.getContextMenuUtil();

        // Create a file chooser
        this.fc = new JFileChooser();
        this.fc.setCurrentDirectory(MASPDirectory.getMASPDirectory());

        this.adapter = new EContentAdapter() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void notifyChanged(final Notification notification) {
                super.notifyChanged(notification);

                LOG.debug("notifyChanged: " + notification);
            }
        };

        // If is no possible to load information because the environment was not
        // loaded, a file selector is opened in order to load a environment from
        // a file
        if(this.manager.getEnvironment() != null) {
            this.createTreePanel();
        } else {
            openFileSelector(OPEN_ENV);
        }

        if(this.treePanel == null) {
            // create tree panel if no one exists
            this.createTreePanel();
        }

        this.changeViewButton1 = new JButton();
        this.changeViewButton1.setIcon(Images.getImageIcon(VIEW0_ICON));
        this.changeViewButton1.setToolTipText(VIEW1_BUTTON_TOOLTIP);
        this.changeViewButton1.addActionListener(this);

        this.changeViewButton2 = new JButton();
        this.changeViewButton2.setIcon(Images.getImageIcon(VIEW1_ICON));
        this.changeViewButton2.setToolTipText(VIEW2_BUTTON_TOOLTIP);
        this.changeViewButton2.addActionListener(this);

        this.changeViewButton3 = new JButton();
        this.changeViewButton3.setIcon(Images.getImageIcon(VIEW2_ICON));
        this.changeViewButton3.setToolTipText(VIEW3_BUTTON_TOOLTIP);
        this.changeViewButton3.addActionListener(this);

        this.openButton = new JButton(OPEN_MODEL_TEXT);
        this.openButton.setIcon(Images.getImageIcon(OPEN_MODEL_ICON));
        this.openButton.addActionListener(this);
        this.openButton.setToolTipText(OPEN_MODEL_BUTTON_TOOLTIP);

        this.saveButton = new JButton(SAVE_MODEL_TEXT);
        this.saveButton.setIcon(Images.getImageIcon(SAVE_MODEL_ICON));
        this.saveButton.addActionListener(this);
        this.saveButton.setToolTipText(SAVE_MODEL_BUTTON_TOOLTIP);
        // TODO remove disabling if environment is saveable
        this.saveButton.setEnabled(false);

        // ++++++++++ Panels initialization ++++++++++
        TitledBorder titledBorder;

        // +++++ view panel +++++
        final JPanel viewPanel = new JPanel(new GridLayout(1, 3));
        viewPanel.setBackground(BACKGROUND_COLOR);

        titledBorder = BorderFactory.createTitledBorder(VIEW_BORDER_TEXT);
        titledBorder.setTitleColor(Color.white);
        viewPanel.setBorder(titledBorder);

        viewPanel.add(this.changeViewButton1);
        viewPanel.add(this.changeViewButton2);
        viewPanel.add(this.changeViewButton3);
        // ----- view panel -----

        // +++++ file panel +++++
        final JPanel filePanel = new JPanel(new GridLayout(1, 2));
        filePanel.setBackground(BACKGROUND_COLOR);

        titledBorder = BorderFactory.createTitledBorder(FILE_BORDER_TEXT);
        titledBorder.setTitleColor(Color.white);
        filePanel.setBorder(titledBorder);

        filePanel.add(this.openButton);
        filePanel.add(this.saveButton);
        // ----- file panel -----

        // +++++ mode panel +++++
        final JPanel modePanel = new JPanel(new BorderLayout());
        modePanel.setBackground(BACKGROUND_COLOR);

        titledBorder = BorderFactory.createTitledBorder(EDIT_BORDER_TEXT);
        titledBorder.setTitleColor(Color.white);
        modePanel.setBorder(titledBorder);

        this.areaEditModeCB = new JCheckBox("Area edit mode");
        this.areaEditModeCB.addActionListener(this);
        this.areaEditModeCB.setBackground(BACKGROUND_COLOR);
        this.areaEditModeCB.setForeground(Color.white);
        this.manager.setAreaEditMode(this.areaEditModeCB.isSelected());

        modePanel.add(this.areaEditModeCB, BorderLayout.WEST);
        modePanel.setMaximumSize(new Dimension(500, 50));
        modePanel.setPreferredSize(new Dimension(200, 50));
        // ----- mode panel -----

        // +++++ button panel +++++
        final JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(BACKGROUND_COLOR);

        buttonPanel.add(viewPanel, BorderLayout.PAGE_START);
        buttonPanel.add(filePanel, BorderLayout.CENTER);
        buttonPanel.add(modePanel, BorderLayout.PAGE_END);
        // ----- button panel -----

        // +++++ tree panel +++++
        initTreePanel();
        // ----- tree panel -----

        // Adding all the panels
        add(buttonPanel, BorderLayout.PAGE_START);
        add(this.treePanel, BorderLayout.CENTER);
        // ---------- Panels initialization ----------
    }// MenuPanel

    private void initTreePanel() {
        this.treePanel.setBackground(BACKGROUND_COLOR);

        final TitledBorder titledBorder = BorderFactory.createTitledBorder(TREE_BORDER_TEXT);
        titledBorder.setTitleColor(Color.white);
        this.treePanel.setBorder(titledBorder);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if(e.getSource() == this.openButton) {
            openFileSelector(OPEN_ENV);
        } else if(e.getSource() == this.saveButton) {
            // TODO save environment

            openFileSelector("save");
            // this.manager.closeEnvironment();

        } else if(e.getSource() == this.changeViewButton1) {
            // this.manager.splitMenuPanel();
            this.manager.changeViewMode(VisualizerManager.ViewMode.ONE_DISPLAY);
        } else if(e.getSource() == this.changeViewButton2) {
            // this.manager.joinMenuPanel();
            this.manager.changeViewMode(VisualizerManager.ViewMode.TWO_DISPLAYS_D);
        } else if(e.getSource() == this.changeViewButton3) {
            this.manager.changeViewMode(VisualizerManager.ViewMode.TWO_DISPLAYS_S);
        } else if(e.getSource() == this.recordMode) {
            this.manager.changeRecordingMode();
        } else if(e.getSource() == this.openRecord) {
            openFileSelector(OPEN_RECORD);
        } else if(e.getSource() == this.areaEditModeCB) {
            this.manager.setAreaEditMode(this.areaEditModeCB.isSelected());
        }
    }// actionPerformed

    public void clearMenu() {
        // createTreePanel(null);
        remove(this.treePanel);
        repaint();
        updateUI();
    }

    // /**
    // * Creates and setup the popups for the tree's nodes.
    // *
    // * @param element
    // * the JComponent (node renderer) in which a listener for the
    // * popup is set.
    // * @param elementId
    // * the identifier of the element for which a popup will be
    // * created.
    // * @param elementName
    // * the name of the element for which a popup will be created.
    // */
    // public void createPopupMenu(final JComponent element, final String
    // elementId, final String elementName) {
    // final ElementType et =
    // MainMenuPanel.this.manager.getElementType(elementId + elementName,
    // elementId);
    // final JMenuItem menuItem = new JMenuItem();
    // final JMenuItem menuItem2 = new JMenuItem();
    // final JMenuItem menuItem3 = new JMenuItem();
    // String childElement;
    //
    // final ActionListener listener = new ActionListener() {
    //
    // @Override
    // public void actionPerformed(final ActionEvent e) {
    // final JMenuItem source = (JMenuItem)(e.getSource());
    // if(source.getText().equals(REMOVE_TEXT)) {
    // MainMenuPanel.this.manager.removeElement(source.getName());
    // removeCurrentNode();
    // } else if(source.getText().equals(RIGHT_HANDED_TEXT)) {
    // MainMenuPanel.this.manager.changeUserToRightHanded(elementId);
    // menuItem2.setIcon(MainMenuPanel.this.images.getImageIcon(SELECTED_HANDED_ICON));
    // menuItem3.setIcon(MainMenuPanel.this.images.getImageIcon(DESELECTED_HANDED_ICON));
    // } else if(source.getText().equals(LEFT_HANDED_TEXT)) {
    // MainMenuPanel.this.manager.changeUserToLeftHanded(elementId);
    // menuItem3.setIcon(MainMenuPanel.this.images.getImageIcon(SELECTED_HANDED_ICON));
    // menuItem2.setIcon(MainMenuPanel.this.images.getImageIcon(DESELECTED_HANDED_ICON));
    // } else {
    // MainMenuPanel.this.manager.startCreateElementPanel(et, elementId);
    // }
    //
    // }
    //
    // };
    //
    // final JPopupMenu popup = new JPopupMenu();
    // if(et != null) {
    // switch(et) {
    // case USERS:
    // childElement = USER_TEXT;
    // menuItem.addActionListener(listener);
    // menuItem.setText("New " + childElement + " for " + elementName);
    // menuItem.setIcon(MainMenuPanel.this.images.getImageIcon(ADD_USER_ICON));
    // break;
    // case PLACES:
    // childElement = ROOM_TEXT;
    // menuItem.setText(NO_ACTION);
    // break;
    // case DEVICES:
    // childElement = DEVICE_TEXT;
    // menuItem.setText(NO_ACTION);
    // break;
    // case ASSISTANTS:
    // childElement = ASSISTANT_TEXT;
    // menuItem.setText(NO_ACTION);
    // break;
    // case DEVICE:
    // childElement = IR_TEXT;
    // menuItem.setText(NO_ACTION);
    // break;
    // case USER:
    // menuItem.setText(REMOVE_TEXT);
    // menuItem.setName(elementId);
    // menuItem.addActionListener(listener);
    // menuItem.setIcon(MainMenuPanel.this.images.getImageIcon(REMOVE_USER_ICON));
    //
    // menuItem2.setText(RIGHT_HANDED_TEXT);
    // menuItem2.setName(elementId);
    // menuItem2.addActionListener(listener);
    // if(!MainMenuPanel.this.manager.findUser(elementId).isLeftHanded()) {
    // menuItem2.setIcon(MainMenuPanel.this.images.getImageIcon(SELECTED_HANDED_ICON));
    //
    // }
    //
    // menuItem3.setText(LEFT_HANDED_TEXT);
    // menuItem3.setName(elementId);
    // menuItem3.addActionListener(listener);
    // if(MainMenuPanel.this.manager.findUser(elementId).isLeftHanded()) {
    // menuItem3.setIcon(MainMenuPanel.this.images.getImageIcon(SELECTED_HANDED_ICON));
    // }
    //
    // popup.add(menuItem2);
    // popup.add(menuItem3);
    // break;
    // default:
    // menuItem.setText(NO_ACTION);
    // }
    // }
    //
    // popup.add(menuItem);
    // final MouseListener popupListener = new PopupListener(popup);
    // element.addMouseListener(popupListener);
    // }// createPopupMenu

    // private class PopupListener extends MouseAdapter {
    //
    // JPopupMenu popup;
    //
    // PopupListener(final JPopupMenu popupMenu) {
    // this.popup = popupMenu;
    // }
    //
    // @Override
    // public void mousePressed(final MouseEvent e) {
    // maybeShowPopup(e);
    // }
    //
    // @Override
    // public void mouseReleased(final MouseEvent e) {
    // maybeShowPopup(e);
    // }
    //
    // private void maybeShowPopup(final MouseEvent e) {
    // if(e.isPopupTrigger()) {
    // this.popup.show(e.getComponent(), e.getX(), e.getY());
    // }
    // }
    // }// class PopupListener

    @Override
    public void disablePopup() {
        this.popupMenu.setVisible(false);
        this.popupMenu = null;
    }

    /**
     * Disable the view options buttons.
     */
    public void disableViewOptionsButtons() {
        this.changeViewButton1.setEnabled(false);
        this.changeViewButton2.setEnabled(false);
        this.changeViewButton3.setEnabled(false);
    }

    @Override
    public Frame getParentFrame() {
        return this.manager.getMainFrame();
    }

    @Override
    public Point getPopupLocation() {
        return null;
    }

    /**
     * Inserts a node in the tree.
     * 
     * @param id
     *            the identifier of the element represented by the new node.
     * @param name
     *            the name of the element represented by the new node.
     */
    public synchronized void insertNode(final String id, final String name, final String elementType, final List<InteractionResource> resources) {
        // not used

        // if(elementType.equals("USERS")) {
        // final SimpleCheckBoxTreeNode node = new
        // SimpleCheckBoxTreeNode(this.userRootNode, id, name,
        // ElementType.USERS, true);
        // this.treeModel.insertNodeInto(node,
        // (MutableTreeNode)this.environmentTree.getSelectionPath().getLastPathComponent(),
        // 0);
        // this.environmentTree.scrollPathToVisible(new
        // TreePath(node.getPath()));
        //
        // } else if(elementType.equals("DEVICES")) {
        // final SimpleCheckBoxTreeNode resourceNode = new
        // SimpleCheckBoxTreeNode(this.userRootNode, id, name,
        // ElementType.DEVICE, true);
        // SimpleCheckBoxTreeNode resourceChildNode = null;
        //
        // this.treeModel.insertNodeInto(resourceNode,
        // (MutableTreeNode)this.environmentTree.getSelectionPath().getLastPathComponent(),
        // 0);
        // final TreePath path = new TreePath(resourceNode.getPath());
        // this.environmentTree.scrollPathToVisible(path);
        //
        // int i = 0;
        // for(final InteractionResource resource : resources) {
        // resourceChildNode = new SimpleCheckBoxTreeNode(resourceNode,
        // resource.getId(), resource.getName(), null, true);
        // if(resource instanceof Display) {
        // // System.out.println("sadfasdf");
        // resourceChildNode = new SimpleCheckBoxTreeNode(resourceNode,
        // resource.getId(), resource.getName(), ElementType.DISPLAY, true);
        // } else if(resource instanceof Mouse) {
        // resourceChildNode = new SimpleCheckBoxTreeNode(resourceNode,
        // resource.getId(), resource.getName(), ElementType.MOUSE, true);
        // } else if(resource instanceof Microphone) {
        // resourceChildNode = new SimpleCheckBoxTreeNode(resourceNode,
        // resource.getId(), resource.getName(), ElementType.MICROPHONE, true);
        // } else if(resource instanceof Touchpad) {
        // resourceChildNode = new SimpleCheckBoxTreeNode(resourceNode,
        // resource.getId(), resource.getName(), ElementType.TOUCHPAD, true);
        // } else if(resource instanceof Touchscreen) {
        // resourceChildNode = new SimpleCheckBoxTreeNode(resourceNode,
        // resource.getId(), resource.getName(), ElementType.TOUCHSCREEN, true);
        // } else if(resource instanceof Loudspeaker) {
        // resourceChildNode = new SimpleCheckBoxTreeNode(resourceNode,
        // resource.getId(), resource.getName(), ElementType.LOUDSPEAKER, true);
        // } else if(resource instanceof Keyboard) {
        // resourceChildNode = new SimpleCheckBoxTreeNode(resourceNode,
        // resource.getId(), resource.getName(), ElementType.KEYBOARD, true);
        // }
        //
        // this.treeModel.insertNodeInto(resourceChildNode,
        // (MutableTreeNode)path.getLastPathComponent(), i);
        // i++;
        // }
        // }
    }

    public void openFileSelector(final String option) {

        if(option.equals(OPEN_ENV)) {
            final int returnVal = this.fc.showOpenDialog(MainMenuPanel.this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                final File file = this.fc.getSelectedFile();
                if(this.manager.loadEnvironment(file)) {
                    startMenu();

                    // reset area edit mode after loading
                    if(this.areaEditModeCB != null) {
                        this.areaEditModeCB.setSelected(false);
                        this.manager.setAreaEditMode(this.areaEditModeCB.isSelected());
                    }
                }
            }
        } else if(option.equals(OPEN_RECORD)) {
            final int returnVal = this.fc.showOpenDialog(MainMenuPanel.this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                final File file = this.fc.getSelectedFile();
                this.manager.loadRecordingFromFile(file.getName());
            }
        } else if(option.equals("save")) {
            final int returnVal = this.fc.showSaveDialog(MainMenuPanel.this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                final File file = this.fc.getSelectedFile();

                this.manager.saveEnvironment(file, true);
            }
        }
    }

    public void startMenu() {
        createTreePanel();
        initTreePanel();
        add(this.treePanel, BorderLayout.CENTER);
        repaint();
        updateUI();
    }

    /**
     * 
     */
    private void createTreePanel() {
        EnvironmentTreePanel environmentTreePanel = null;
        if(this.manager.getEnvironment() != null) {
            environmentTreePanel = new EnvironmentTreePanel(this.manager.getEnvironment(), this.manager);
            environmentTreePanel.addEnvironmentTreeListener(this);

            this.environmentTree = environmentTreePanel.getEnvironmentTree();

            this.environmentTree.addTreeSelectionListener(this);

            this.treePanel = new JScrollPane(this.environmentTree);
        } else {
            this.treePanel = new JScrollPane();
        }

    }

    @Override
    public void valueChanged(final TreeSelectionEvent event) {
        try {
            // LOG.debug("valueChanged: " + event);

            if(this.environmentTree.getLastSelectedPathComponent() != null) {

                if(this.environmentTree.getLastSelectedPathComponent() instanceof DefaultMutableTreeNode) {
                    if(this.environmentTree.getLastSelectedPathComponent() instanceof SimpleCheckBoxTreeNode) {
                        this.manager.elementSelected(this.environmentTree.getLastSelectedPathComponent().toString());
                    }
                }
            }
        }
        catch(final Exception e) {

        }
    }

    /**
     * Displays a specialized pop-up depending on the clicked element.
     * 
     * @param selPath
     *            The selected path of the environment-tree.
     * @param point
     *            The location to display the pop-up.
     */
    @Override
    public void showPopupMenu(final TreePath selPath, final Point point) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("selPath:" + selPath + " at " + ContextModelVisualizer.toString(point));
        }

        if(selPath == null || !(selPath.getLastPathComponent() instanceof EnvironmentTreeNode)) {
            return;
        }

        final Object[] path = selPath.getPath();

        if(!(path[path.length - 1] instanceof EnvironmentTreeNode)) {
            return;
        }

        final EnvironmentTreeNode last = (EnvironmentTreeNode)path[path.length - 1];

        if(last == null) {
            LOG.warn("last selected element is null, path: " + Arrays.toString(path));
            return;
        }

        if(this.popupMenu != null) {
            this.popupMenu.setVisible(false);
        }

        this.popupMenu = new JPopupMenu();
        final JMenu menu = new JMenu();
        // LOG.debug("showPopup " + treeNode.getClass());

        // LOG.debug("userObject:"+userObject.getClass());
        if(last instanceof CheckBoxRootTreeNode) {
            this.contextMenuUtil.appendEnvironmentMenuItems(menu, ((RootTreeNode)last).getType(), this);
        } else if(last instanceof ServiceContainersRootTreeNode) {
            this.contextMenuUtil.appendEnvironmentMenuItems(menu, RootElementType.SERVICE_CONTAINERS, this);
        } else if(last instanceof ConfigurationPropertyTreeNode) {
            final ConfigurationPropertyTreeNode cptn = (ConfigurationPropertyTreeNode)last;

            final EnvironmentTreeNode parent = cptn.getParent();

            if(parent != null) {
                if(parent instanceof ServiceTreeNode) {
                    this.contextMenuUtil.appendConfigurationPropertyMenuItems(menu, ((ServiceTreeNode)parent).getService(), cptn.getConfigurationProperty(), this);
                } else {
                    LOG.error("Parent node of ConfigurationPropertyTreeNode is no ServiceTreeNode " + cptn);
                }
            } else {
                LOG.error("Parent node of ConfigurationPropertyTreeNode is null " + cptn);
            }
        } else if(last instanceof ServiceTreeNode) {
            final EnvironmentTreeNode parent = last.getParent();

            if(parent != null) {
                if(parent instanceof ServiceContainerTreeNode) {
                    this.contextMenuUtil.appendMenuItems(menu, ((ServiceTreeNode)last).getService(), ((ServiceContainerTreeNode)parent).getServiceContainer(), this);
                } else {
                    LOG.error("Parent node of ServiceTreeNode is no ServiceContainerTreeNode " + last);
                }
            } else {
                LOG.error("Parent node of ServiceTreeNode is null " + last);
            }
        } else if(last instanceof PropertyTreeNode) {
            this.contextMenuUtil.appendMenuItems(menu, ((PropertyTreeNode)last).getElementProperty(), ((PropertyTreeNode)last).getElementProperty().getElement(), this);
        } else if(last instanceof ServiceContainerTreeNode) {
            final ServiceContainerTreeNode serviceContainerTreeNode = (ServiceContainerTreeNode)last;
            Object parent = null;

            if(serviceContainerTreeNode.getParent() != null) {
                if(serviceContainerTreeNode.getParent() instanceof ServiceContainerTreeNode) {
                    parent = ((ServiceContainerTreeNode)serviceContainerTreeNode.getParent()).getServiceContainer();
                } else if(serviceContainerTreeNode.getParent() instanceof ServiceContainersRootTreeNode) {
                    parent = this.manager.getEnvironment();
                }
            }

            this.contextMenuUtil.appendMenuItems(menu, serviceContainerTreeNode.getServiceContainer(), parent, this);
        } else if(last instanceof LocalisationProviderProxyTreeNode) {
            this.contextMenuUtil.appendMenuItems(menu, ((LocalisationProviderProxyTreeNode)last).getLocalisationProviderProxy(), this.manager.getEnvironment(), this);
        } else if(last instanceof LocalizationTagTreeNode) {
            Object parent = this.manager.getEnvironment();

            LOG.debug("add LocalizationTagTreeNode menu items");

            if(last.getParent() != null) {
                final EnvironmentTreeNode parentNode = last.getParent();

                if(LOG.isDebugEnabled()) {
                    LOG.debug("parentNode: " + parentNode);
                }

                if(parentNode instanceof LocalisationProviderProxyTreeNode) {
                    parent = ((LocalisationProviderProxyTreeNode)parentNode).getLocalisationProviderProxy();
                }
            } else {
                LOG.warn("Parent node of LocalizationTagTreeNode is null: " + last);
            }

            this.contextMenuUtil.appendMenuItems(menu, ((LocalizationTagTreeNode)last).getLocalizationTag(), parent, this);
        } else if(last instanceof EnvironmentElementTreeNode) {
            // last case
            this.contextMenuUtil.appendMenuItems(menu, ((EnvironmentElementTreeNode)last).getElement(), this.manager.getEnvironment(), this);
        } else {
            LOG.warn("no menu for: " + last.getClass());
            return;
        }

        ContextMenuUtil.addSubElements(this.popupMenu, menu);

        // display no pop-up if no generated menu
        if(this.popupMenu.getSubElements().length == 0) {
            this.popupMenu = null;
            return;
        }

        // move the location of the pop-up in relation to this menu panel
        point.translate(-this.getLocationOnScreen().x, 0);
        point.translate(0, -this.getLocationOnScreen().y);

        this.popupMenu.show(this, point.x, point.y);
    }// showPopup
}
