/**
 * 
 */
package org.sercho.masp.models.Context.gui.panels;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.Enumeration;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.sercho.masp.models.Context.ConfigurationProperty;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.ContextProvider;
import org.sercho.masp.models.Context.Door;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.EnvironmentElement;
import org.sercho.masp.models.Context.LocalisationProviderProxy;
import org.sercho.masp.models.Context.LocalizationTag;
import org.sercho.masp.models.Context.Place;
import org.sercho.masp.models.Context.Vector;
import org.sercho.masp.models.Context.Window;
import org.sercho.masp.models.Context.gui.ElementProperty;
import org.sercho.masp.models.Context.gui.Images;
import org.sercho.masp.models.Context.gui.VisualizerManager;
import org.sercho.masp.models.Context.gui.enums.ElementType;
import org.sercho.masp.models.Context.gui.enums.RootElementType;

import de.dailab.masp.models.Properties.Actor;
import de.dailab.masp.models.Properties.PropertiesPackage;
import de.dailab.masp.models.Properties.Sensor;
import de.dailab.masp.models.Properties.Service;
import de.dailab.masp.models.Properties.ServiceContainer;

/**
 * A {@link JPanel} for the environment tree representing an {@link Environment}
 * or an part of it.
 * 
 * @author Andre Schulz
 * @since 1.3.7
 */
public class EnvironmentTreePanel extends JPanel {

    // TODO implement getElement() methods at subclasses of EnvironmentTreeNode

    /**
     * Custom editor for nodes.
     * 
     * @author Antonio Fernandez Zaragoza
     * @author Andre Schulz
     * 
     */
    public class CheckBoxNodeEditor extends AbstractCellEditor implements TreeCellEditor,
            ActionListener {

        private static final long serialVersionUID = -6718525316250648150L;

        private final JCheckBox checkBox = new JCheckBox();

        private final JLabel label = new JLabel();

        private EnvironmentTreeNode node;

        private final JPanel panelRenderer = new JPanel();

        JComponent editedComponent;

        JTree tree;

        public CheckBoxNodeEditor(final JTree tree, final VisualizerManager manager) {
            // this.label.setIcon(createImageIcon(PATH + USERS_ICON));
            this.panelRenderer.add(this.label);
            this.panelRenderer.add(this.checkBox);
            this.checkBox.addActionListener(this);
            this.tree = tree;
            Font fontValue;
            fontValue = UIManager.getFont("Tree.font");
            if(fontValue != null) {
                this.checkBox.setFont(fontValue);
            }
            final Boolean booleanValue = (Boolean)UIManager.get("Tree.drawsFocusBorderAroundIcon");
            this.checkBox.setFocusPainted((booleanValue != null) && (booleanValue.booleanValue()));
        }// CheckBoxNodeEditor

        @Override
        public void actionPerformed(final ActionEvent e) {
            if(LOG.isDebugEnabled()) {
                LOG.debug(new StringBuilder("action: ").append(e).append(" source: ").append(e.getSource().getClass()));
                LOG.info(e.getSource().getClass());
            }
            // LOG.debug("getLastSelectedPathComponent:" +
            // this.tree.getLastSelectedPathComponent());

            // TreePath selPath =
            // MenuPanel.this.tree.getPathForLocation(e.getX(), e.getY());

            if(this.tree.getLastSelectedPathComponent() instanceof CheckBoxTreeNode) {
                ((CheckBoxTreeNode)this.tree.getLastSelectedPathComponent()).toggleSelected();
            }

            this.editedComponent = (JComponent)e.getSource();

            super.stopCellEditing();
        }// actionPerformed

        @Override
        public Object getCellEditorValue() {
            if(this.editedComponent == this.checkBox) {
                EnvironmentTreePanel.this.visualizerManager.filter(this.node != null ? this.node.getElement()
                        : null, this.checkBox.isSelected());
                this.editedComponent = null;
                return null;// checkBoxNode;
            }

            return null;
        }// getCellEditorValue

        @Override
        public Component getTreeCellEditorComponent(final JTree tree, final Object value, final boolean selected, final boolean expanded, final boolean leaf, final int row) {
            if(LOG.isDebugEnabled()) {
                LOG.debug(new StringBuilder("getTreeCellEditorComponent \nvalue:").append(value).toString());
            }

            final String stringValue = tree.convertValueToText(value, selected, expanded, leaf, row, false);
            this.checkBox.setText(stringValue);

            if(value instanceof EnvironmentTreeNode) {
                this.node = (EnvironmentTreeNode)value;
            }

            if(value != null) {
                // TODO adding of new elements

                if(value instanceof CheckBoxTreeNode) {
                    final CheckBoxTreeNode node = (CheckBoxTreeNode)value;
                    // setIcon(node, this.label);
                    this.checkBox.setText(node.getText());
                    this.checkBox.setSelected(node.isSelected());
                    this.checkBox.setName(node.getId());
                    // createPopupMenu(this.panelRenderer,
                    // this.checkBox.getName(), this.checkBox.getText());
                }

                final EnvironmentTreeNode treeNode = (EnvironmentTreeNode)value;

                // LOG.debug("node:" + treeNode);
                // LOG.debug("childs:" + treeNode.getChildrens());

                this.label.setIcon(treeNode.getIcon());

                if(treeNode instanceof PropertyTreeNode) {
                    // a node representing a property
                    // this.label.setIcon(treeNode.getIcon());
                    this.checkBox.setVisible(false);
                    this.label.setText(treeNode.getText());
                }
                if(treeNode instanceof CheckBoxTreeNode) {
                    // special case for nodes witch CheckBox
                    final CheckBoxTreeNode node = (CheckBoxTreeNode)treeNode;
                    // setIcon(node, this.label);
                    this.checkBox.setVisible(true);
                    this.checkBox.setText(node.getText());
                    this.checkBox.setSelected(node.isSelected());
                    this.checkBox.setName(node.getId());
                    this.label.setText("");
                } else if(treeNode instanceof EnvironmentTreeNode) {
                    // other nodes without CheckBox
                    // this.label.setIcon(treeNode.getIcon());
                    this.checkBox.setVisible(false);
                    this.label.setText(treeNode.getText());
                }
            }

            return this.panelRenderer;
        }// getTreeCellEditorComponent

        @Override
        public boolean isCellEditable(final EventObject event) {

            boolean returnValue = false;
            if(event instanceof MouseEvent) {
                final MouseEvent mouseEvent = (MouseEvent)event;
                final TreePath path = this.tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
                if(path != null) {
                    final Object node = path.getLastPathComponent();

                    if((node != null) && (node instanceof EnvironmentTreeNode)) {
                        if(node instanceof PropertyTreeNode) {
                            returnValue = false;
                        } else {
                            returnValue = (node instanceof CheckBoxTreeNode);
                        }

                    }
                }
            }
            return returnValue;
        }
    }// class CheckBoxNodeEditor

    /**
     * A {@link RootTreeNode} with a {@link Checkbox}.
     * 
     * @author Andre Schulz
     * 
     */
    public class CheckBoxRootTreeNode extends RootTreeNode implements CheckBoxTreeNode,
            Adapter {

        private final String id;

        private boolean selected;

        /**
         * @param parent
         * @param type
         */
        public CheckBoxRootTreeNode(final EnvironmentTreeNode parent,
                final RootElementType type) {
            super(parent, type);
            this.selected = true;
            this.id = type.toString();

            switch(type) {
                case PLACES:
                    EnvironmentTreePanel.this.visualizerManager.getEnvironment().eAdapters().add(this);
                    break;
                case PROVIDERS:
                    EnvironmentTreePanel.this.visualizerManager.getEnvironment().eAdapters().add(this);
                    break;
            }
        }

        @Override
        public String getId() {
            return this.id;
        }

        @Override
        public Notifier getTarget() {
            return null;
        }

        @Override
        public boolean isAdapterForType(final Object arg0) {
            return false;
        }

        @Override
        public boolean isSelected() {
            return this.selected;
        }

        @Override
        public void notifyChanged(final Notification notification) {
            switch(super.type) {
                case PLACES:
                    switch(notification.getEventType()) {
                        case Notification.ADD:
                            if(notification.getFeature() == ContextPackage.Literals.ENVIRONMENT__PLACES) {
                                final PlaceTreeNode placeTreeNode = new PlaceTreeNode(this, (Place)notification.getNewValue());
                                super.add(placeTreeNode);
                                return;
                            } else {
                                // ignore other notifications
                                return;
                            }
                        case Notification.REMOVE:
                            if(notification.getFeature() == ContextPackage.Literals.ENVIRONMENT__PLACES) {
                                final Place place = (Place)notification.getOldValue();
                                EnvironmentTreeNode childToRemove = null;

                                for(final EnvironmentTreeNode child : super.getChildrens()) {
                                    if(child instanceof PlaceTreeNode) {
                                        if(((PlaceTreeNode)child).getElement() == place) {
                                            childToRemove = child;
                                        }
                                    }
                                }

                                if(childToRemove != null) {
                                    EnvironmentTreePanel.this.treeModel.removeNodeFromParent(childToRemove);
                                } else {
                                    LOG.warn("child node not present for Place " + notification.getOldValue());
                                }
                                return;
                            } else {
                                // ignore other notifications
                                return;
                            }
                    }// switch Notification
                    break;// PLACES
                case DEVICES:
                    switch(notification.getEventType()) {
                        case Notification.ADD:
                            if(notification.getNewValue() instanceof Door || notification.getNewValue() instanceof Window) {
                                // ignore notification
                                return;
                            }
                            break;
                        case Notification.REMOVE:
                            if(notification.getOldValue() instanceof Door) {
                                // ignore notification
                                return;
                            }
                            break;
                    }// switch Notification
                    break;// DEVICES
                case PROVIDERS:
                    if(notification.getFeature() == ContextPackage.Literals.ENVIRONMENT__PROVIDERS) {
                        switch(notification.getEventType()) {
                            case Notification.ADD:
                                final String id = getNextLocalizationProviderProxyId();
                                final LocalisationProviderProxyTreeNode localisationProviderProxyTreeNode = new LocalisationProviderProxyTreeNode((LocalisationProviderProxy)notification.getNewValue(), this, id, id, true);
                                super.add(localisationProviderProxyTreeNode);
                                return;
                            case Notification.REMOVE:
                                for(final EnvironmentTreeNode child : super.getChildrens()) {
                                    if(child instanceof LocalisationProviderProxyTreeNode) {
                                        if(((LocalisationProviderProxyTreeNode)child).getLocalisationProviderProxy() == notification.getOldValue()) {
                                            super.remove(child);
                                            return;
                                        }
                                    }
                                }

                                LOG.warn("child node not present for LocalizationProviderProxy " + notification.getOldValue());
                                return;
                        }
                    } else {
                        // ignore other notifications
                        return;
                    }
                    break;// PROVIDERS

            }// switch RootElementType

            LOG.warn(this.id + " unhandled notification " + notification);
        }

        @Override
        public void renewChildrens() {
            super.clear();

            switch(super.type) {
                case PLACES:
                    for(final Place place : EnvironmentTreePanel.this.visualizerManager.getPlaces()) {
                        // if(LOG.isDebugEnabled()) {
                        // LOG.debug("generate sub-tree for place " +
                        // place.getId());
                        // }
                        final PlaceTreeNode placeTreeNode = new PlaceTreeNode(this, place);
                        super.add(placeTreeNode);
                    }
                    break;
                default:
                    LOG.warn("cannot renew cildres for root tree node of type " + super.type);
            }// switch

            super.sortChildrens();
        }

        @Override
        public void setTarget(final Notifier arg0) {
        }

        @Override
        public void toggleSelected() {
            this.selected = this.selected == false;
        }
    }

    /**
     * Interface for {@link TreeNode}s with a {@link JCheckBox}.
     * 
     * @author Andre Schulz
     * @since 1.2.46
     */
    public interface CheckBoxTreeNode {

        public String getId();

        public String getText();

        public boolean isSelected();

        public void toggleSelected();
    }

    /**
     * A Node in the tree of the {@link Environment} which represents a
     * {@link ConfigurationProperty} of a {@link Service}. The {@link Service}
     * is part of a {@link ConfigurationProperty} and could be an {@link Actor}
     * or a {@link Sensor}.
     * 
     * @author Andre Schulz
     * 
     */
    public class ConfigurationPropertyTreeNode extends EnvironmentTreeNode implements
            Adapter {

        private final de.dailab.masp.models.Properties.ConfigurationProperty configurationProperty;

        public ConfigurationPropertyTreeNode(
                final EnvironmentTreeNode parent,
                final de.dailab.masp.models.Properties.ConfigurationProperty configurationProperty) {
            super(parent, configurationProperty, getConfigurationPropertyNodeString(configurationProperty));
            this.configurationProperty = configurationProperty;
            this.configurationProperty.eAdapters().add(this);
        }

        @Override
        public int compareTo(final EnvironmentTreeNode otherNode) {
            if(otherNode instanceof ConfigurationPropertyTreeNode) {
                return super.getParent().compareChilds(this, otherNode);
            }

            return super.compareTo(otherNode);
        }

        public de.dailab.masp.models.Properties.ConfigurationProperty getConfigurationProperty() {
            return this.configurationProperty;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.emf.common.notify.Adapter#getTarget()
         */
        @Override
        public Notifier getTarget() {
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.
         * Object)
         */
        @Override
        public boolean isAdapterForType(final Object arg0) {
            return false;
        }

        @Override
        public void notifyChanged(final Notification notification) {
            switch(notification.getEventType()) {
                case Notification.SET:
                    if(notification.getFeature() == PropertiesPackage.Literals.CONFIGURATION_PROPERTY__KEY || notification.getFeature() == PropertiesPackage.Literals.CONFIGURATION_PROPERTY__VALUE) {
                        super.setText(getConfigurationPropertyNodeString(this.configurationProperty));
                        return;
                    }
                    break;
            }

            if(notification.getFeature() == PropertiesPackage.Literals.SERVICE_CONTAINER__SERVICES) {
                // ignore notification
                return;
            }

            LOG.warn(this.toString() + " unhandled notification " + notification);
        }

        @Override
        public void renewChildrens() {
            LOG.warn("Do not call me: I have no children! " + super.getText());
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common
         * .notify.Notifier)
         */
        @Override
        public void setTarget(final Notifier arg0) {
        }

        @Override
        public String toString() {
            return this.configurationProperty.getKey();
        }
    }// class ConfigurationPropertyTreeNode

    /**
     * A {@link TreeNode} for an {@link EnvironmentElement}.
     * 
     * @author Andre Schulz
     * @since 1.2.46
     */
    public class EnvironmentElementTreeNode extends EnvironmentTreeNode implements
            Adapter, CheckBoxTreeNode {

        private final EnvironmentElement element;

        private boolean selected;

        /**
         * Constructor for {@link EnvironmentElementTreeNode}s.
         * 
         * @param parent
         *            The parent {@link EnvironmentTreeNode}.
         * @param element
         *            The {@link EnvironmentElement} represented by this
         *            {@link EnvironmentElementTreeNode}-
         * @param iconObject
         *            The icon {@link Object}.
         * @param secondIconObject
         *            The second icon {@link Object}.
         */
        public EnvironmentElementTreeNode(final EnvironmentTreeNode parent,
                final EnvironmentElement element, final Object iconObject,
                final Object secondIconObject) {
            super(parent, iconObject, secondIconObject, EnvironmentTreePanel.getText(element));

            this.element = element;

            if(element != null) {
                this.element.eAdapters().add(this);
            }

            this.selected = true;
        }

        @Override
        public EnvironmentElement getElement() {
            return this.element;
        }

        @Override
        public String getId() {
            return this.element.getId();
        }

        @Override
        public Notifier getTarget() {
            return null;
        }

        @Override
        public boolean isAdapterForType(final Object arg0) {
            return false;
        }

        @Override
        public boolean isSelected() {
            return this.selected;
        }

        @Override
        public void notifyChanged(final Notification notification) {
            switch(notification.getEventType()) {
                case Notification.SET:
                    if(notification.getFeature() == ContextPackage.Literals.ENVIRONMENT_ELEMENT__NAME) {
                        super.setText((String)notification.getNewValue());
                        return;
                    } else if(notification.getFeature() == ContextPackage.Literals.ELEMENT_WITH_POSITION__POSITION_TIME_STAMP) {
                        // ignore notification
                        return;
                    } else if(notification.getFeature() == ContextPackage.Literals.PLACE__FLOOR) {
                        // ignore notification
                        return;
                    }
            }// switch

            LOG.warn(this.toString() + " unhandled notification " + notification);
        }

        @Override
        public void setTarget(final Notifier arg0) {

        }

        @Override
        public void toggleSelected() {
            this.selected = this.selected == false;
        }
    }

    /**
     * Interface for the listeners of the {@link EnvironmentTreePanel}.
     * 
     * @author Andre Schulz
     * 
     */
    public interface EnvironmentTreeListener {

        /**
         * Forces the listener to show a pop-up menu.
         * 
         * @param selPath
         *            The selected {@link TreePath}.
         * @param point
         *            The {@link Point} where to display the pop-up menu.
         */
        void showPopupMenu(TreePath selPath, Point point);
    }

    /**
     * A {@link TreeNode} for the environment tree.
     * 
     * @author Andre Schulz
     * 
     */
    public class EnvironmentTreeNode implements TreeNode, MutableTreeNode,
            Comparable<EnvironmentTreeNode> {

        private final LinkedList<EnvironmentTreeNode> childrens;

        private ImageIcon icon;

        private EnvironmentTreeNode parent;

        private String text;

        public EnvironmentTreeNode(final EnvironmentTreeNode parent,
                final Object iconObject, final Object secondIconObject, final String text) {

            // if(LOG.isDebugEnabled()) {
            // LOG.debug("create new EnvironmentTreeNode " + text);
            // }

            this.text = text;

            String iconName = null;

            if(iconObject != null) {
                if(iconObject instanceof String) {
                    iconName = (String)iconObject;
                } else if(iconObject instanceof EObject) {
                    final EClass eClass = ((EObject)iconObject).eClass();

                    // if(LOG.isDebugEnabled()) {
                    // LOG.debug("iconObject: " + eClass.getName());
                    // }

                    if(Images.hasImageIcon(eClass != null ? eClass.getName() : "default")) {
                        iconName = eClass != null ? eClass.getName() : "default";
                    }
                }
            }

            if(iconName == null && secondIconObject != null) {
                if(secondIconObject instanceof String) {
                    iconName = (String)secondIconObject;
                } else if(secondIconObject instanceof EObject) {
                    final EClass eClass = ((EObject)secondIconObject).eClass();

                    if(Images.hasImageIcon(eClass != null ? eClass.getName() : "default")) {
                        iconName = eClass != null ? eClass.getName() : "default";
                    }
                }
            }

            if(iconName != null) {
                this.icon = Images.getImageIcon(iconName);
            } else {
                this.icon = Images.getImageIcon("default");
            }

            this.parent = parent;
            this.childrens = new LinkedList<EnvironmentTreePanel.EnvironmentTreeNode>();
        }

        /**
         * Creates a new node for a {@link JTree}.
         * 
         * @param iconObject
         *            The {@link Object} to decide which icon is displayed for
         *            this node. A {@link String} will directly mapped to an
         *            icon, from any subclass of {@link EObject} the class name
         *            will be mapped to an icon, otherwise the default icon is
         *            taken.
         * @param text
         */
        public EnvironmentTreeNode(final EnvironmentTreeNode parent,
                final Object iconObject, final String text) {

            if(text == null) {
                throw new IllegalArgumentException("text can not be null!");
            }

            this.text = text;

            String iconName = null;

            if(iconObject != null) {
                if(iconObject instanceof String) {
                    iconName = (String)iconObject;
                } else if(iconObject instanceof EObject) {
                    final EClass eClass = ((EObject)iconObject).eClass();
                    iconName = eClass != null ? eClass.getName() : "default";
                } else {
                    iconName = "default";
                }

            } else {
                iconName = "default";
            }

            // LOG.debug("new EnvironmentTreeNode\n" + iconObject + "\nicon:" +
            // iconName);

            this.icon = Images.getImageIcon(iconName);

            this.parent = parent;
            this.childrens = new LinkedList<EnvironmentTreePanel.EnvironmentTreeNode>();
        }

        public void add(final EnvironmentTreeNode node) {
            // if(LOG.isDebugEnabled()) {
            // StringBuilder sb = new StringBuilder();
            // sb.append(this.toString());
            // sb.append(" add ");
            // sb.append(node);
            // LOG.debug(sb.toString());
            // }

            synchronized(this.childrens) {
                this.childrens.add(node);
            }

            this.sortChildrens();

            if(EnvironmentTreePanel.this.treeModel != null) {
                EnvironmentTreePanel.this.treeModel.nodesWereInserted(this, new int[]{this.getIndex(node)});
            }
        }

        @Override
        public Enumeration<?> children() {
            return (Enumeration<?>)this.childrens;
        }

        /**
         * Compares two child nodes related to
         * {@link Comparable#compareTo(Object)}.
         * 
         * @param one
         *            One {@link EnvironmentTreeNode}.
         * @param two
         *            An other {@link EnvironmentTreeNode}.
         * @return A negative integer, zero, or a positive integer as this
         *         object is less than, equal to, or greater than the specified
         *         object.
         */
        public int compareChilds(final EnvironmentTreeNode one, final EnvironmentTreeNode two) {
            return one.compareTo(two);
        }

        @Override
        public int compareTo(final EnvironmentTreeNode otherNode) {
            if(this.getClass().equals(EnvironmentTreeNode.class)) {
                // if(LOG.isDebugEnabled()) {
                // LOG.debug("compareTo EnvironmentTreeNode: " + this + " and "
                // + otherNode);
                // }

                return this.text.toLowerCase().compareTo(otherNode.text.toLowerCase());
            }

            if(!this.getClass().equals(otherNode.getClass()) && this.parent != null) {
                // if(LOG.isDebugEnabled()) {
                // LOG.debug("compareTo compareChilds: " + this + " and " +
                // otherNode);
                // }

                return this.parent.compareChilds(this, otherNode);
            }

            // if(LOG.isDebugEnabled()) {
            // LOG.debug("compareTo texts: " + this + " and " + otherNode);
            // }

            return this.text.toLowerCase().compareTo(otherNode.text.toLowerCase());
        }

        @Override
        public boolean equals(final Object obj) {

            if(this == obj) {
                return true;
            }

            if(obj instanceof EnvironmentTreeNode) {
                boolean result = this.toString().equals(((EnvironmentTreeNode)obj).toString());

                if(!result) {
                    return false;
                }

                synchronized(this.childrens) {
                    result = this.childrens.equals(((EnvironmentTreeNode)obj).childrens);
                }

                if(!result) {
                    return false;
                }

                return true;
            }

            return false;
        }

        @Override
        public boolean getAllowsChildren() {
            return true;
        }

        @Override
        public final TreeNode getChildAt(final int childIndex) {
            synchronized(this.childrens) {
                return this.childrens.get(childIndex);
            }
        }

        @Override
        public final int getChildCount() {
            synchronized(this.childrens) {
                return this.childrens.size();
            }
        }

        public final LinkedList<EnvironmentTreeNode> getChildrens() {
            return this.childrens;
        }

        public Object getElement() {
            return null;
        }

        public final ImageIcon getIcon() {
            return this.icon;
        }

        @Override
        public final int getIndex(final TreeNode node) {
            synchronized(this.childrens) {
                return this.childrens.indexOf(node);
            }
        }

        @Override
        public final EnvironmentTreeNode getParent() {
            return this.parent;
        }

        public final TreeNode[] getPath() {
            final LinkedList<TreeNode> path = new LinkedList<TreeNode>();
            TreeNode node = this;

            while(node != null) {
                path.addFirst(node);
                node = node.getParent();
            }

            return path.toArray(new TreeNode[path.size()]);
        }

        public final String getText() {
            if(this.text == null) {
                return "(null)";
            }

            return this.text;
        }

        @Override
        public void insert(final MutableTreeNode child, final int index) {
            if(child instanceof EnvironmentTreeNode) {
                synchronized(this.childrens) {
                    this.childrens.add(index, (EnvironmentTreeNode)child);
                }
            } else {
                throw new IllegalArgumentException("Child must be subclass of EnvironmentTreeNode!");
            }
        }

        @Override
        public final boolean isLeaf() {
            synchronized(this.childrens) {
                if(this.childrens == null || this.childrens.size() == 0) {
                    return true;
                }
            }

            return false;
        }

        /**
         * Re-links an {@link EnvironmentTreeNode} from one parent to an other
         * parent.
         * 
         * @param oldParent
         *            The old parent node.
         * @param newParent
         *            The new parent node.
         */
        public void relinkNode(final EnvironmentTreeNode oldParent, final EnvironmentTreeNode newParent) {
            if(LOG.isDebugEnabled()) {
                LOG.debug("relink node " + super.toString());
            }

            if(!oldParent.hashChild(this)) {
                throw new IllegalArgumentException("This node is no child of oldParent!");
            }

            oldParent.remove(this);
            newParent.add(this);
            this.parent = newParent;

            if(LOG.isDebugEnabled()) {
                LOG.debug("oldParent " + oldParent.childrens);
                LOG.debug("newParent " + newParent.childrens);
            }
        }

        @Override
        public final void remove(final int index) {
            if(LOG.isDebugEnabled()) {
                LOG.debug("remove node " + index + " from " + this.toString() + " " + this.childrens);
            }

            if(index >= 0) {
                synchronized(this.childrens) {
                    this.childrens.remove(index);
                }
            } else {
                LOG.warn("invalid index for removing child node " + index);
            }
        }

        @Override
        public final void remove(final MutableTreeNode node) {
            if(LOG.isDebugEnabled()) {
                LOG.debug("remove node " + node + " from " + this.toString());
            }

            EnvironmentTreePanel.this.treeModel.removeNodeFromParent(node);
        }

        @Override
        public final void removeFromParent() {
            if(this.parent != null) {
                this.parent.remove(this);
            } else {
                throw new NullPointerException("Parent is null!");
            }
        }

        public void renewChildrens() {
            throw new IllegalAccessError("This method is not available for this class, reimplement it in subclasses!");
        }

        @Override
        public final void setParent(final MutableTreeNode parent) {
            if(parent instanceof EnvironmentTreeNode) {
                this.parent = (EnvironmentTreeNode)parent;
            } else {
                throw new IllegalArgumentException("Parent must be subclass of EnvironmentTreeNode!");
            }
        }

        public final void setText(final String text) {
            this.text = text;
            if(EnvironmentTreePanel.this.treeModel != null) {
                EnvironmentTreePanel.this.treeModel.nodeChanged(this);
            }
        }

        @Override
        public final void setUserObject(final Object object) {
            // there is no user object
        }

        @Override
        public String toString() {
            if(this.text == null) {
                return "(null)";
            }

            return this.text;
        }

        /**
         * Checks whether this {@link EnvironmentTreeNode} has an other
         * {@link EnvironmentTreeNode} as child node.
         * 
         * @param environmentTreeNode
         *            The {@link EnvironmentTreeNode} to check whether it is a
         *            child of this or not.
         * @return <code>true</code> if environmentTreeNode is a child node of
         *         this, <code>false</code>.
         */
        private boolean hashChild(final EnvironmentTreeNode environmentTreeNode) {
            synchronized(this.childrens) {
                return this.childrens.contains(environmentTreeNode);
            }
        }

        /**
         * Clears all child nodes.
         */
        protected void clear() {
            // LOG.debug("clear " + this.text);
            synchronized(this.childrens) {
                this.childrens.clear();
            }
        }

        protected void invokeTreeModelUpdate() {
            final int indexOld = this.parent.getIndex(this);

            this.parent.sortChildrens();

            if(this.parent.getIndex(this) != indexOld) {
                EnvironmentTreePanel.this.treeModel.nodeStructureChanged(this.parent);
            } else {
                EnvironmentTreePanel.this.treeModel.nodeChanged(this);
            }
        }

        protected final void sortChildrens() {
            // if(LOG.isDebugEnabled()) {
            // LOG.debug(this.getText() + " sortChildrens " + this.childrens);
            // }

            synchronized(this.childrens) {
                Collections.sort(this.childrens);

                for(final EnvironmentTreeNode child : this.childrens) {
                    child.sortChildrens();
                }
            }
        }
    }// class EnvironmentTreeNode

    /**
     * Custom renderer for nodes.
     * 
     * @author Antonio Fernandez Zaragoza
     * @author Andre Schulz
     * 
     */
    public class EnvironmentTreeNodeRenderer implements TreeCellRenderer {

        private final JCheckBox checkBox = new JCheckBox();

        private final JLabel label = new JLabel();

        private final JPanel panel = new JPanel();

        private final Color selectionBackground, textForeground, textBackground;

        public EnvironmentTreeNodeRenderer() {

            this.panel.add(this.label);
            this.panel.add(this.checkBox);
            Font fontValue;
            fontValue = UIManager.getFont("Tree.font");

            if(fontValue != null) {
                this.checkBox.setFont(fontValue);
                this.label.setFont(fontValue);
            }

            this.selectionBackground = UIManager.getColor("Tree.selectionBackground");
            this.textForeground = UIManager.getColor("Tree.textForeground");
            this.textBackground = UIManager.getColor("Tree.textBackground");
        }

        @Override
        public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean selected, final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {
            Component returnValue;

            final String stringValue = tree.convertValueToText(value, selected, expanded, leaf, row, false);
            this.checkBox.setText(stringValue);

            this.checkBox.setSelected(false);

            this.checkBox.setEnabled(tree.isEnabled());

            this.checkBox.setForeground(this.textForeground);
            this.checkBox.setBackground(this.textBackground);
            this.panel.setForeground(this.textForeground);
            this.panel.setBackground(this.textBackground);

            if(value != null && value instanceof EnvironmentTreeNode) {
                // LOG.debug("value:" + value);
                // LOG.debug("value.class:" + value.getClass());
                final EnvironmentTreeNode treeNode = (EnvironmentTreeNode)value;

                // LOG.debug("node:" + treeNode + " " + treeNode.getClass());
                // LOG.debug("childs:" + treeNode.getChildrens());

                this.label.setIcon(treeNode.getIcon());

                // if(treeNode instanceof PropertyTreeNode) {
                // // a node representing a property
                // if(tree.getLastSelectedPathComponent() != null &&
                // tree.getLastSelectedPathComponent().equals(value)) {
                // LOG.debug("selcted " + value);
                // this.panel.setBackground(this.selectionBackground);
                // }
                //
                // this.checkBox.setVisible(true);
                // this.checkBox.setEnabled(false);
                // this.checkBox.setSelected(((PropertyTreeNode)treeNode).isReferenced());
                // this.checkBox.setText(treeNode.getText());
                // this.label.setText("");
                // } else
                if(treeNode instanceof CheckBoxTreeNode && !(treeNode instanceof NoCheckBoxTreeNode)) {
                    // special case for nodes witch CheckBox
                    final CheckBoxTreeNode node = (CheckBoxTreeNode)treeNode;
                    // setIcon(node, this.label);
                    this.checkBox.setVisible(true);
                    this.checkBox.setEnabled(true);
                    this.checkBox.setText(node.getText());
                    this.checkBox.setSelected(node.isSelected());
                    this.checkBox.setName(node.getId());
                    this.label.setText("");

                    if(treeNode instanceof PropertyTreeNode) {
                        this.checkBox.setEnabled(false);
                    }
                } else if(treeNode instanceof EnvironmentTreeNode) {
                    // other nodes without CheckBox

                    // this.label.setIcon(treeNode.getIcon());
                    this.checkBox.setVisible(false);
                    this.label.setText(treeNode.getText());
                }

                // set node as selected if it is last on a selected path
                if(tree.getSelectionPaths() != null) {
                    for(final TreePath treePath : tree.getSelectionPaths()) {
                        if(treePath.getLastPathComponent() != null && treePath.getLastPathComponent().equals(value)) {
                            this.panel.setBackground(this.selectionBackground);
                        }
                    }
                }
            }

            this.panel.doLayout();

            returnValue = this.panel;

            return returnValue;
        }
    }// class EnvironmentTreeNodeRenderer

    /**
     * A {@link TreeNode} for {@link LocalisationProviderProxy}s.
     * 
     * @author Andre Schulz
     * @since 1.3.12
     */
    public class LocalisationProviderProxyTreeNode extends SimpleCheckBoxTreeNode
            implements Adapter {

        private final LocalisationProviderProxy localisationProviderProxy;

        /**
         * Constructor for {@link LocalisationProviderProxyTreeNode}s.
         * 
         * @param localisationProviderProxy
         *            The {@link LocalisationProviderProxy} the node is for.
         * @param parent
         *            The parent {@link EnvironmentTreeNode}.
         * @param id
         *            The id of the node.
         * @param text
         *            The text of the node.
         * @param selected
         *            Indicates whether the node is selected (
         *            <code>true<code>) or not (<code>false</code>).
         */
        public LocalisationProviderProxyTreeNode(
                final LocalisationProviderProxy localisationProviderProxy,
                final EnvironmentTreeNode parent, final String id, final String text,
                final boolean selected) {
            super(parent, id, text, localisationProviderProxy, selected);

            this.localisationProviderProxy = localisationProviderProxy;
            this.localisationProviderProxy.eAdapters().add(this);
        }

        public LocalisationProviderProxy getLocalisationProviderProxy() {
            return this.localisationProviderProxy;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.emf.common.notify.Adapter#getTarget()
         */
        @Override
        public Notifier getTarget() {
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.
         * Object)
         */
        @Override
        public boolean isAdapterForType(final Object arg0) {
            return false;
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf
         * .common.notify.Notification)
         */
        @Override
        public void notifyChanged(final Notification notification) {
            if(notification.getFeature() == ContextPackage.Literals.LOCALISATION_PROVIDER_PROXY__TAGS) {
                switch(notification.getEventType()) {
                    case Notification.ADD:
                        final LocalizationTagTreeNode localizationTagTreeNode = new LocalizationTagTreeNode(this, (LocalizationTag)notification.getNewValue(), true);
                        super.add(localizationTagTreeNode);
                        break;
                    case Notification.REMOVE:
                        for(final EnvironmentTreeNode child : super.getChildrens()) {
                            if(child instanceof LocalizationTagTreeNode) {
                                if(((LocalizationTagTreeNode)child).getLocalizationTag() == notification.getOldValue()) {
                                    super.remove(child);
                                    return;
                                }
                            }
                        }// for

                        LOG.warn("child node not present for LocalizationTag " + notification.getOldValue());
                        return;
                }// switch
            }

            LOG.warn(this.toString() + " unhandled notification " + notification);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common
         * .notify.Notifier)
         */
        @Override
        public void setTarget(final Notifier arg0) {
        }
    }

    /**
     * A {@link TreeNode} for {@link LocalizationTag}s.
     * 
     * @author Andre Schulz
     * @since 1.3.12
     */
    public class LocalizationTagTreeNode extends EnvironmentElementTreeNode implements
            Adapter {

        private final LocalizationTag localizationTag;

        /**
         * Constructor for {@link LocalizationTagTreeNode}s.
         * 
         * @param localizationTag
         *            The {@link LocalizationTag} itself.
         * @param parent
         *            The parent {@link EnvironmentTreeNode} of this node.
         * @param selected
         *            Indicates whether the node is selected (
         *            <code>true<code>) or not (<code>false</code>).
         */
        public LocalizationTagTreeNode(final EnvironmentTreeNode parent,
                final LocalizationTag localizationTag, final boolean selected) {
            super(parent, localizationTag, localizationTag, localizationTag);
            this.localizationTag = localizationTag;
        }

        public LocalizationTag getLocalizationTag() {
            return this.localizationTag;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.emf.common.notify.Adapter#getTarget()
         */
        @Override
        public Notifier getTarget() {
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.
         * Object)
         */
        @Override
        public boolean isAdapterForType(final Object arg0) {
            return false;
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf
         * .common.notify.Notification)
         */
        @Override
        public void notifyChanged(final Notification notification) {
            if(notification.getFeature() == ContextPackage.Literals.ENVIRONMENT_ELEMENT__NAME) {
                switch(notification.getEventType()) {
                    case Notification.SET:
                        super.setText(notification.getNewStringValue());
                        return;
                }
            } else if(notification.getFeature() == ContextPackage.Literals.LOCALIZATION_TAG__PROVIDER) {
                // ignore notification
                return;
            }

            LOG.warn(this.toString() + " unhandled notification " + notification);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common
         * .notify.Notifier)
         */
        @Override
        public void setTarget(final Notifier arg0) {
        }
    }

    /**
     * Indicates that the {@link TreeNode} should not have a {@link JCheckBox}.
     * 
     * @author Andre Schulz
     * @since 1.2.46
     */
    public interface NoCheckBoxTreeNode {

    }

    /**
     * A {@link TreeNode} for {@link Place}s.
     * 
     * @author Andre Schulz
     * @since 1.2.46
     */
    public class PlaceTreeNode extends EnvironmentElementTreeNode {

        /**
         * Constructor for {@link PlaceTreeNode}s.
         * 
         * @param parent
         *            The parent {@link EnvironmentTreeNode}.
         * @param place
         *            The {@link Place} to represent.
         */
        public PlaceTreeNode(final EnvironmentTreeNode parent, final Place place) {
            super(parent, place, place, "Place");

            place.eAdapters().add(this);
        }

        @Override
        public int compareChilds(final EnvironmentTreeNode one, final EnvironmentTreeNode two) {
            if(one.getClass().equals(two.getClass())) {
                return one.compareTo(two);
            }

            return 0;
        }

        @Override
        public synchronized void notifyChanged(final Notification notification) {
            if(notification.getNotifier() == this.getElement()) {
                switch(notification.getEventType()) {
                    case Notification.ADD:
                        // nothing to do
                        return;
                    case Notification.REMOVE:
                        // nothing to do
                        return;
                }// switch
            }// if

            super.notifyChanged(notification);
        }
    }

    /**
     * A Node in the tree of the {@link Environment} which represents a property
     * of a PysicalDevice.
     * 
     * @author Andre Schulz
     * 
     */
    public class PropertyTreeNode extends EnvironmentTreeNode {

        private final ElementProperty elementProperty;

        public PropertyTreeNode(final EnvironmentTreeNode parent,
                final ElementProperty elementProperty) {
            super(parent, "Property", elementProperty.getPropertyReference().getName());

            this.elementProperty = elementProperty;
        }

        public ElementProperty getElementProperty() {
            return this.elementProperty;
        }

    }// class PropertyTreeNode

    /**
     * A {@link EnvironmentTreeNode} to represent root groups of the
     * {@link Environment}.
     * 
     * @author Andre Schulz
     * 
     */
    public class RootTreeNode extends EnvironmentTreeNode {

        /**
         * The type of this {@link RootTreeNode}.
         */
        private final RootElementType type;

        /**
         * Constructor for {@link RootTreeNode}s.
         * 
         * @param type
         *            The type of this {@link RootTreeNode}.
         * @param parent
         *            The parent {@link EnvironmentTreeNode}.
         */
        public RootTreeNode(final EnvironmentTreeNode parent, final RootElementType type) {
            super(parent, type.getIconKey(), type.toString());
            this.type = type;
        }

        @Override
        public int compareTo(final EnvironmentTreeNode otherNode) {
            if(otherNode instanceof RootTreeNode) {
                final RootTreeNode otherRootTreeNode = (RootTreeNode)otherNode;

                return this.type.getOrderID() - otherRootTreeNode.type.getOrderID();
            } else {
                return super.compareTo(otherNode);
            }
        }

        @Override
        public Object getElement() {
            switch(this.type) {
                case ASSISTANTS:
                    return ElementType.ASSISTANTS;
                case DEVICES:
                    return ElementType.DEVICES;
                case PLACES:
                    return ElementType.PLACES;
                case PROVIDERS:
                    return ElementType.PROVIDERS;
                case USERS:
                    return ElementType.USERS;
                default:
                    return null;
            }
        }

        public RootElementType getType() {
            return this.type;
        }
    }

    /**
     * A {@link RootTreeNode} for {@link ServiceContainer}s.
     * 
     * @author Andre Schulz
     * 
     */
    public class ServiceContainersRootTreeNode extends RootTreeNode implements Adapter {

        /**
         * Constructor creates a new {@link ServiceContainerTreeNode}.
         * 
         * @param parent
         *            The parent {@link EnvironmentTreeNode}.
         */
        public ServiceContainersRootTreeNode(final EnvironmentTreeNode parent) {
            super(parent, RootElementType.SERVICE_CONTAINERS);

            if(EnvironmentTreePanel.this.environment != null) {
                EnvironmentTreePanel.this.environment.eAdapters().add(this);
            }
        }

        @Override
        public int compareChilds(final EnvironmentTreeNode one, final EnvironmentTreeNode two) {
            if(one instanceof ServiceContainerTreeNode && two instanceof ServiceContainerTreeNode) {
                // both nodes must be only ServiceContainerTreeNode
                return one.toString().compareTo(two.toString());
            }

            return one.compareTo(two);
        }

        @Override
        public Notifier getTarget() {
            return null;
        }

        @Override
        public RootElementType getType() {
            return RootElementType.SERVICE_CONTAINERS;
        }

        @Override
        public boolean isAdapterForType(final Object arg0) {
            return false;
        }

        @Override
        public void notifyChanged(final Notification notification) {
            if(notification != null && notification.getNotifier() != null && notification.getNotifier().equals(EnvironmentTreePanel.this.environment)) {
                if(notification.getFeature() == ContextPackage.Literals.ENVIRONMENT__SERVICE_CONTAINERS) {
                    switch(notification.getEventType()) {
                        case Notification.ADD:
                            final ServiceContainer serviceContainer = (ServiceContainer)notification.getNewValue();

                            if(LOG.isDebugEnabled()) {
                                LOG.debug("add ServiceContainerTreeNode " + serviceContainer);
                            }

                            final ServiceContainerTreeNode serviceContainerTreeNode = new ServiceContainerTreeNode(this, serviceContainer, serviceContainer.getId());
                            super.add(serviceContainerTreeNode);
                            return;
                        case Notification.REMOVE:
                            for(final EnvironmentTreeNode child : super.getChildrens()) {
                                if(child instanceof ServiceContainerTreeNode) {
                                    if(((ServiceContainerTreeNode)child).getServiceContainer() == notification.getOldValue()) {
                                        super.remove(child);
                                        return;
                                    }
                                }
                            }// for

                            LOG.warn("child node not present for Device " + notification.getOldValue());
                            break;
                    }// switch
                } else {
                    // ignore notification
                    return;
                }
            }// if

            LOG.warn(this.toString() + " unhandled notification " + notification);
        }// notifyChanged

        @Override
        public void renewChildrens() {
            super.clear();

            if(EnvironmentTreePanel.this.environment != null) {
                ServiceContainerTreeNode serviceContainerTreeNode;

                for(final ServiceContainer serviceContainer : EnvironmentTreePanel.this.environment.getServiceContainers()) {
                    serviceContainerTreeNode = new ServiceContainerTreeNode(this, serviceContainer, serviceContainer.getId());
                    serviceContainerTreeNode.renewChildrens();
                    super.add(serviceContainerTreeNode);
                }
            }

            super.sortChildrens();
        }

        @Override
        public void setTarget(final Notifier arg0) {

        }
    }// class ServiceContainersRootNode

    /**
     * An {@link EnvironmentTreeNode} for {@link ServiceContainer}s.
     * 
     * @author Andre Schulz
     * 
     */
    public class ServiceContainerTreeNode extends EnvironmentTreeNode implements Adapter {

        protected ServiceContainer serviceContainer;

        /**
         * Constructor for {@link ServiceContainerTreeNode}s.
         * 
         * @param parent
         * @param serviceContainer
         * @param text
         */
        public ServiceContainerTreeNode(final EnvironmentTreeNode parent,
                final ServiceContainer serviceContainer, final String text) {
            super(parent, serviceContainer, text);
            this.serviceContainer = serviceContainer;
            this.serviceContainer.eAdapters().add(this);
            this.renewChildrens();
        }

        /**
         * Constructor for the subclasses.
         * 
         * @param parent
         * @param iconObject
         * @param serviceContainer
         * @param text
         */
        protected ServiceContainerTreeNode(final EnvironmentTreeNode parent,
                final Object iconObject, final ServiceContainer serviceContainer,
                final String text) {
            super(parent, iconObject, text);
            this.serviceContainer = serviceContainer;
        }

        @Override
        public int compareChilds(final EnvironmentTreeNode one, final EnvironmentTreeNode two) {
            if(one instanceof ServiceContainerTreeNode && two instanceof ServiceContainerTreeNode) {
                return ((ServiceContainerTreeNode)one).getServiceContainer().getId().toLowerCase().compareTo(((ServiceContainerTreeNode)two).getServiceContainer().getId().toLowerCase());
            } else if(one instanceof ServiceTreeNode) {
                if(two instanceof ServiceTreeNode) {
                    return one.toString().toLowerCase().compareTo(two.toString().toLowerCase());
                } else {
                    return 1;
                }
            } else if(two instanceof ServiceTreeNode) {
                return -1;
            }

            return one.compareTo(two);
        }

        public ServiceContainer getServiceContainer() {
            return this.serviceContainer;
        }

        @Override
        public Notifier getTarget() {
            return null;
        }

        @Override
        public boolean isAdapterForType(final Object arg0) {
            return false;
        }

        @Override
        public void notifyChanged(final Notification notification) {
            if(LOG.isDebugEnabled()) {
                LOG.debug(new StringBuilder(this.toString()).append(".notifyChanged ").append(" \n").append(notification));
            }

            if(notification != null && notification.getNotifier() != null && notification.getNotifier().equals(this.serviceContainer)) {

                switch(notification.getEventType()) {
                // +++++ REMOVE +++++
                    case Notification.REMOVE:
                        if(notification.getFeature() instanceof EReference) {
                            final EReference eReference = (EReference)notification.getFeature();

                            if(eReference == PropertiesPackage.Literals.SERVICE_CONTAINER__SERVICES) {
                                synchronized(super.childrens) {
                                    for(final EnvironmentTreeNode child : super.childrens) {
                                        if(child instanceof ServiceTreeNode) {
                                            if(((ServiceTreeNode)child).getService().equals(notification.getOldValue())) {
                                                super.remove(child);
                                                return;
                                            }
                                        } else if(child instanceof ServiceContainerTreeNode) {
                                            if(((ServiceContainerTreeNode)child).getServiceContainer().equals(notification.getOldValue())) {
                                                super.remove(child);
                                                return;
                                            }
                                        }
                                    }// for childrens
                                }
                            }// if SERVICE_CONTAINER__SERVICES
                        }// if EReference

                        LOG.warn("child node not present for " + notification.getOldValue());
                        return;
                        // ----- REMOVE -----
                        // +++++ ADD +++++
                    case Notification.ADD:
                        if(notification.getFeature() instanceof EReference) {
                            final EReference eReference = (EReference)notification.getFeature();
                            if(eReference == PropertiesPackage.Literals.SERVICE_CONTAINER__SERVICES) {
                                if(notification.getNewValue() instanceof ServiceContainer) {
                                    if(LOG.isDebugEnabled()) {
                                        LOG.debug("ADD ServiceContainer " + notification.getNewValue());
                                    }

                                    final ServiceContainerTreeNode serviceContainerTreeNode = new ServiceContainerTreeNode(this, (ServiceContainer)notification.getNewValue(), ((ServiceContainer)notification.getNewValue()).getId());
                                    super.add(serviceContainerTreeNode);
                                    return;
                                } else if(notification.getNewValue() instanceof Service) {
                                    if(LOG.isDebugEnabled()) {
                                        LOG.debug("ADD Service " + notification.getNewValue());
                                    }

                                    final ServiceTreeNode serviceTreeNode = new ServiceTreeNode(this, this.serviceContainer, (Service)notification.getNewValue());

                                    if(LOG.isDebugEnabled()) {
                                        synchronized(super.childrens) {
                                            LOG.debug("childrens: " + super.childrens);
                                        }
                                    }

                                    super.add(serviceTreeNode);
                                    return;
                                }
                            }
                        }// if EReference
                        break;
                    // ----- ADD -----
                    // +++++ SET +++++
                    case Notification.SET:
                        if(notification.getFeature() instanceof EAttribute) {
                            if(notification.getFeature() == PropertiesPackage.Literals.SERVICE__ID) {
                                super.setText(this.serviceContainer.getId());
                                super.invokeTreeModelUpdate();
                                return;
                            }
                        }
                        break;
                // ----- SET -----
                }
            }

            LOG.warn(this.toString() + " unhandled notification " + notification);
        }// notifyChanged

        @Override
        public void renewChildrens() {
            // LOG.debug("ServiceContainerTreeNode.renewChildrens " +
            // super.getText());
            super.clear();
            ServiceTreeNode serviceTreeNode;
            ServiceContainerTreeNode serviceContainerTreeNode;

            // LOG.debug(this.serviceContainer.getId());
            // LOG.debug(this.serviceContainer.getConfiguration());
            // LOG.debug(this.serviceContainer.getContainer());

            for(final Service service : this.serviceContainer.getServices()) {
                // try {
                serviceTreeNode = null;
                serviceContainerTreeNode = null;

                if(service instanceof ServiceContainer) {
                    serviceContainerTreeNode = new ServiceContainerTreeNode(this, ((ServiceContainer)service), ((ServiceContainer)service).getId());
                    serviceContainerTreeNode.renewChildrens();
                    super.add(serviceContainerTreeNode);
                } else if(service instanceof Sensor || service instanceof Actor) {
                    serviceTreeNode = new ServiceTreeNode(this, this.serviceContainer, service);
                    serviceTreeNode.renewChildrens();
                    super.add(serviceTreeNode);
                }
                // }
                // catch(final Exception e) {
                // LOG.warn(new
                // StringBuilder("error while generating child ").append(service.getId()).append(" of ").append(this.serviceContainer.getId()).toString(),
                // e);
                // // TODO display error
                // }
            }

            super.sortChildrens();
        }// renewChildrens

        @Override
        public void setTarget(final Notifier arg0) {
        }

        @Override
        public String toString() {
            if(this.serviceContainer.getId() == null) {
                return ServiceContainer.class.getSimpleName();
            }

            return this.serviceContainer.getId();
        }

    }// class ServiceContainerTreeNode

    /**
     * An {@link EnvironmentTreeNode} representing a {@link Service}.
     * 
     * @author Andre Schulz
     * 
     */
    public class ServiceTreeNode extends EnvironmentTreeNode implements Adapter {

        private final Service service;

        public ServiceTreeNode(final EnvironmentTreeNode parent,
                final ServiceContainer serviceContainer, final Service service) {
            super(parent, service, service.getId());

            if(!(service instanceof Sensor || service instanceof Actor)) {
                throw new IllegalArgumentException("service argunemt must be of class Actor or Service!");
            }

            this.service = service;
            this.renewChildrens();
            this.service.eAdapters().add(this);
        }

        /**
         * Constructor for the sub-classes.
         * 
         * @param parent
         * @param iconObject
         * @param serviceContainer
         * @param service
         * @param text
         */
        protected ServiceTreeNode(final EnvironmentTreeNode parent,
                final Object iconObject, final ServiceContainer serviceContainer,
                final Service service, final String text) {
            super(parent, iconObject, text);
            this.service = service;
        }

        @Override
        public int compareChilds(final EnvironmentTreeNode one, final EnvironmentTreeNode two) {
            if(one instanceof ConfigurationPropertyTreeNode && two instanceof ConfigurationPropertyTreeNode) {
                return one.text.toLowerCase().compareTo(two.text.toLowerCase());
            }

            return one.compareTo(two);
        }

        @Override
        public int compareTo(final EnvironmentTreeNode otherNode) {
            if(otherNode instanceof ServiceContainerTreeNode) {
                return super.getParent().compareChilds(this, otherNode);
            }

            return super.compareTo(otherNode);
        }

        public Service getService() {
            return this.service;
        }

        @Override
        public Notifier getTarget() {
            return null;
        }

        @Override
        public boolean isAdapterForType(final Object arg0) {
            return false;
        }

        @Override
        public void notifyChanged(final Notification notification) {
            if(LOG.isDebugEnabled()) {
                LOG.debug(new StringBuilder(this.toString()).append(".notifyChanged ").append(" \n").append(notification));
            }

            if(notification != null && notification.getNotifier() != null && notification.getNotifier().equals(this.service)) {

                switch(notification.getEventType()) {
                    case Notification.SET:
                        if(notification.getFeature() instanceof EAttribute) {
                            if(notification.getFeature() == PropertiesPackage.Literals.SERVICE__ID) {
                                super.setText(this.service.getId());
                                super.invokeTreeModelUpdate();
                                return;
                            }
                        } else if(notification.getFeature() instanceof EReference) {
                            final EReference eReference = (EReference)notification.getFeature();

                            if(eReference == PropertiesPackage.Literals.SERVICE__CONTAINER) {
                                // nothing to do
                                return;
                            }
                        }
                        break;// SET
                    case Notification.ADD:
                        if(notification.getFeature() instanceof EReference) {
                            final EReference eReference = (EReference)notification.getFeature();

                            if(eReference == PropertiesPackage.Literals.SERVICE__CONFIGURATION) {
                                if(notification.getNewValue() instanceof de.dailab.masp.models.Properties.ConfigurationProperty) {
                                    final ConfigurationPropertyTreeNode configurationPropertyTreeNode = new ConfigurationPropertyTreeNode(this, (de.dailab.masp.models.Properties.ConfigurationProperty)notification.getNewValue());
                                    super.add(configurationPropertyTreeNode);
                                    return;
                                }
                            }
                        }
                        break;// ADD
                    case Notification.REMOVE:
                        if(notification.getFeature() instanceof EReference) {
                            final EReference eReference = (EReference)notification.getFeature();

                            if(eReference == PropertiesPackage.Literals.SERVICE__CONFIGURATION) {
                                for(final EnvironmentTreeNode child : super.getChildrens()) {
                                    if(child instanceof ConfigurationPropertyTreeNode) {
                                        if(((ConfigurationPropertyTreeNode)child).getConfigurationProperty().equals(notification.getOldValue())) {
                                            super.remove(child);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                }// switch
            }// if

            LOG.warn(this.toString() + " unhandled notification " + notification);
        }// notifyChanged

        @Override
        public void renewChildrens() {
            // TODO optimize: only update changed properties
            super.clear();

            for(final de.dailab.masp.models.Properties.ConfigurationProperty configurationProperty : this.service.getConfiguration()) {
                super.add(new ConfigurationPropertyTreeNode(this, configurationProperty));
            }

            super.sortChildrens();
        }

        @Override
        public void setTarget(final Notifier arg0) {
        }

        @Override
        public String toString() {
            return this.service.getId();
        }
    }// class ServiceTreeNode;

    /**
     * Structure used to store information of the nodes.
     * 
     * @author Antonio Fernandez Zaragoza
     * @author Andre Schulz
     * 
     */
    public class SimpleCheckBoxTreeNode extends EnvironmentTreeNode implements
            CheckBoxTreeNode {

        private boolean selected;

        public SimpleCheckBoxTreeNode(final EnvironmentTreeNode parent, final String id,
                final String text, final Object iconObject, final boolean selected) {
            super(parent, iconObject, text);

            if(id == null) {
                throw new NullPointerException("ID must not be null!");
            }

            this.selected = selected;
        }

        @Override
        public String getId() {
            return super.getText();
        }

        @Override
        public boolean isSelected() {
            return this.selected;
        }

        public void setSelected(final boolean selected) {
            this.selected = selected;
        }

        @Override
        public void toggleSelected() {
            this.selected = this.selected == false;
        }
    }// class CheckBoxTreeNode

    /**
     * The logger.
     */
    private static final transient Log LOG = LogFactory.getLog(EnvironmentTreePanel.class);

    /**
     * 
     */
    private static final long serialVersionUID = -4926253892327462586L;

    public static String getConfigurationPropertyNodeString(final de.dailab.masp.models.Properties.ConfigurationProperty configurationProperty) {
        return new StringBuilder(configurationProperty.getKey()).append(" : ").append(configurationProperty.getValue()).toString();
    }

    /**
     * Returns the text to display an {@link EnvironmentElement}.
     * 
     * @param element
     *            The {@link EnvironmentElement} to get the text for.
     * @return The text to display.
     */
    private static String getText(final EnvironmentElement element) {
        if(element.getName() != null) {
            if(!element.getName().isEmpty()) {
                return element.getName();
            }
        }

        return element.getId();
    }

    /**
     * {@link Vector} of the {@link ServiceContainer}s.
     */
    private ServiceContainersRootTreeNode containerRootNode;

    private int currentLocalizationProviderProxyId;

    private EnvironmentTreeNode deviceRootNode;

    private final Environment environment;

    private JTree environmentTree;

    private EnvironmentTreeListener[] environmentTreeListeners;

    private final boolean multipleSelect;

    private EnvironmentTreeNode placeRootNode;

    private EnvironmentTreeNode providerRootNode;

    private EnvironmentTreeNode rootNode;

    private Set<Service> services;

    private DefaultTreeModel treeModel;

    private EnvironmentTreeNode userRootNode;

    private final VisualizerManager visualizerManager;

    public EnvironmentTreePanel(final Environment environment,
            final VisualizerManager visualizerManager) {
        if(environment == null) {
            throw new IllegalArgumentException("environment argument must not be null in contructor EnvironmentTreePanel!");
        } else if(visualizerManager == null) {
            throw new IllegalArgumentException("visualizerManager argument must nor be null in contructor EnvironmentTreePanel!");
        }

        this.environment = environment;
        this.visualizerManager = visualizerManager;

        this.multipleSelect = false;

        this.generateEnvironmentTree();
        this.createTreePanel();
    }

    /**
     * 
     * @param services
     * @param visualizerManager
     * @param multipleSelect
     */
    public EnvironmentTreePanel(final Set<Service> services,
            final VisualizerManager visualizerManager, final boolean multipleSelect) {
        if(services == null) {
            throw new IllegalArgumentException("services argument must not be null in contructor EnvironmentTreePanel!");
        } else if(visualizerManager == null) {
            throw new IllegalArgumentException("visualizerManager argument must nor be null in contructor EnvironmentTreePanel!");
        }

        this.environment = visualizerManager.getEnvironment();
        this.visualizerManager = visualizerManager;
        this.services = services;
        this.multipleSelect = multipleSelect;

        this.generateServiceTree();
        this.createTreePanel();

        this.addTreeSelectionListener();
    }

    public void addEnvironmentTreeListener(final EnvironmentTreeListener environmentTreeListener) {
        if(this.environmentTreeListeners == null) {
            this.environmentTreeListeners = new EnvironmentTreeListener[]{environmentTreeListener};
        } else {
            final EnvironmentTreeListener[] environmentTreeListeners = this.environmentTreeListeners;
            this.environmentTreeListeners = new EnvironmentTreeListener[environmentTreeListeners.length + 1];
            System.arraycopy(environmentTreeListeners, 0, this.environmentTreeListeners, 0, environmentTreeListeners.length);
            this.environmentTreeListeners[this.environmentTreeListeners.length - 1] = environmentTreeListener;
        }
    }

    public JTree getEnvironmentTree() {
        return this.environmentTree;
    }

    public String getNextLocalizationProviderProxyId() {
        return "LocalisationProvider_" + this.currentLocalizationProviderProxyId++;
    }

    /**
     * Removes from the tree the current node selected.
     */
    public void removeCurrentNode() {
        removeNode((DefaultMutableTreeNode)this.environmentTree.getSelectionPath().getLastPathComponent());
    }

    /**
     * Removes a node from the tree.
     * 
     * @param node
     *            the tree's node that must be removed
     */
    public void removeNode(final DefaultMutableTreeNode node) {
        final DefaultMutableTreeNode parent = (DefaultMutableTreeNode)node.getParent();

        if(parent != null) {
            this.environmentTree.setSelectionPath(null);
            this.treeModel.removeNodeFromParent(node);
        }

    }

    /**
     * 
     */
    private void addTreeSelectionListener() {
        this.environmentTree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(final TreeSelectionEvent e) {

            }
        });
    }

    /**
     * Creates a tree and fills it with the data stored in a vector. Also sets a
     * new node renderer and a new node editor.
     */
    private void createTreePanel() {
        this.treeModel = (DefaultTreeModel)this.environmentTree.getModel();

        this.environmentTree.setRootVisible(false);
        this.environmentTree.setEditable(true);

        if(this.multipleSelect) {
            if(LOG.isDebugEnabled()) {
                LOG.debug("DISCONTIGUOUS_TREE_SELECTION");
            }
            this.environmentTree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        } else {
            this.environmentTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        }

        this.environmentTree.setShowsRootHandles(true);

        final EnvironmentTreeNodeRenderer renderer = new EnvironmentTreeNodeRenderer();
        this.environmentTree.setCellRenderer(renderer);

        this.environmentTree.setCellEditor(new CheckBoxNodeEditor(this.environmentTree, this.visualizerManager));

        this.environmentTree.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(final MouseEvent e) {
                // empty
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                // empty
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                // empty
            }

            @Override
            public void mousePressed(final MouseEvent e) {
                // LOG.debug("mousePressed:" + e);

                // show pop-up if clicked at environment-tree
                if(e.getButton() == MouseEvent.BUTTON3) {
                    final TreePath selPath = EnvironmentTreePanel.this.environmentTree.getPathForLocation(e.getX(), e.getY());

                    if(selPath != null) {
                        for(final EnvironmentTreeListener environmentTreeListener : EnvironmentTreePanel.this.environmentTreeListeners) {
                            environmentTreeListener.showPopupMenu(selPath, e.getLocationOnScreen());
                        }
                    }
                }
            }// mousePressed

            @Override
            public void mouseReleased(final MouseEvent e) {
                // empty
            }
        });

    }// createTreePanel

    /**
     * Retrieves the data from the environment, users, rooms, devices... etc and
     * stores it, returning a vector with all the data.
     */
    private void generateEnvironmentTree() {
        // Load and organize the data for the tree. This first vectors are
        // the root nodes of the tree. If we want to include a new group of
        // elements in the tree, we have to create here a new vector. Then,
        // each vector must be filled with the elements that we want to
        // include or with subroot vectors.

        this.rootNode = new EnvironmentTreeNode(null, null, "root");

        this.userRootNode = new CheckBoxRootTreeNode(this.rootNode, RootElementType.USERS);
        this.placeRootNode = new CheckBoxRootTreeNode(this.rootNode, RootElementType.PLACES);
        this.deviceRootNode = new CheckBoxRootTreeNode(this.rootNode, RootElementType.DEVICES);
        this.providerRootNode = new CheckBoxRootTreeNode(this.rootNode, RootElementType.PROVIDERS);
        this.containerRootNode = new ServiceContainersRootTreeNode(this.rootNode);

        // this.interactionRVector = null;

        // Filling providers node
        for(final ContextProvider provider : this.visualizerManager.getEnvironment().getProviders()) {
            if(provider instanceof LocalisationProviderProxy) {
                // System.out.println("Provider - > " +
                // ((LocalisationProviderProxy)provider).getConfiguration());
                // TODO don't use null
                final String nodeId = getNextLocalizationProviderProxyId();

                final EnvironmentTreeNode providerNode = new LocalisationProviderProxyTreeNode((LocalisationProviderProxy)provider, this.providerRootNode, nodeId, nodeId, true);
                this.providerRootNode.add(providerNode);

                for(final LocalizationTag tag : ((LocalisationProviderProxy)provider).getTags()) {
                    // localizationTagList.add(tag.getId());

                    providerNode.add(new LocalizationTagTreeNode(providerNode, tag, true));
                }

                // localizationTagList = new ArrayList<String>();
            }
        }

        this.placeRootNode.renewChildrens();

        // fill ServiceContainers
        this.containerRootNode.renewChildrens();

        // LOG.debug("sort user root node");
        this.userRootNode.sortChildrens();
        // LOG.debug("sort device root node");
        this.deviceRootNode.sortChildrens();
        // LOG.debug("sort provider root node");
        this.providerRootNode.sortChildrens();

        this.rootNode.add(this.userRootNode);
        this.rootNode.add(this.placeRootNode);
        this.rootNode.add(this.deviceRootNode);
        this.rootNode.add(this.providerRootNode);
        this.rootNode.add(this.containerRootNode);

        this.environmentTree = new JTree(new DefaultTreeModel(this.rootNode));
    }

    /**
     * 
     */
    private void generateServiceTree() {
        this.rootNode = new ServiceContainersRootTreeNode(null);

        for(final Service service : this.services) {
            if(service instanceof Sensor || service instanceof Actor) {
                this.rootNode.add(new ServiceTreeNode(this.rootNode, null, service));
            } else if(service instanceof ServiceContainer) {
                this.rootNode.add(new ServiceContainerTreeNode(this.rootNode, (ServiceContainer)service, service.getId()));
            }

        }

        this.environmentTree = new JTree(new DefaultTreeModel(this.rootNode));
    }
}
