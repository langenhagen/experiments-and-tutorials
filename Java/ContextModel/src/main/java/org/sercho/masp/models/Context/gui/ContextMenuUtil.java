/**
 * 
 */
package org.sercho.masp.models.Context.gui;

import java.awt.Component;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.MenuElement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.sercho.masp.models.Context.Area;
import org.sercho.masp.models.Context.ContextPackage;
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
import org.sercho.masp.models.Context.Window;
import org.sercho.masp.models.Context.gui.actionListener.ConfigurationPropertyActionListener;
import org.sercho.masp.models.Context.gui.actionListener.DefinitionModificationElementActionListener;
import org.sercho.masp.models.Context.gui.actionListener.DiscoverServiceActionListener;
import org.sercho.masp.models.Context.gui.actionListener.ElementActionListener;
import org.sercho.masp.models.Context.gui.actionListener.ExecutableElementDialogActionListener;
import org.sercho.masp.models.Context.gui.actionListener.PropertyServiceRemovalActionListener;
import org.sercho.masp.models.Context.gui.actionListener.PropertySetServiceActionListener;
import org.sercho.masp.models.Context.gui.actionListener.ServiceActionListener;
import org.sercho.masp.models.Context.gui.actionListener.ServiceContainerActionListener;
import org.sercho.masp.models.Context.gui.actionListener.VectorActionListener;
import org.sercho.masp.models.Context.gui.enums.ActionType;
import org.sercho.masp.models.Context.gui.enums.RootElementType;
import org.sercho.masp.models.Context.gui.enums.ServiceType;
import org.sercho.masp.models.Context.gui.wrapper.Inlet;
import org.sercho.masp.models.Context.gui.wrapper.InletDoor;
import org.sercho.masp.models.Context.gui.wrapper.InletWindow;
import org.sercho.masp.models.Context.util.AppliancesUtility;

import de.dailab.masp.models.MetaMetaModel.DefinitionModificationElement;
import de.dailab.masp.models.MetaMetaModel.MetaClass;
import de.dailab.masp.models.MetaMetaModel.MetaMetaModelUtility;
import de.dailab.masp.models.MetaMetaModel.MetaModel;
import de.dailab.masp.models.MetaMetaModel.MetaType;
import de.dailab.masp.models.MetaMetaModel.ecore.EcoreMetaModelConverter;
import de.dailab.masp.models.Properties.Actor;
import de.dailab.masp.models.Properties.ConfigurationProperty;
import de.dailab.masp.models.Properties.Property;
import de.dailab.masp.models.Properties.Sensor;
import de.dailab.masp.models.Properties.Service;
import de.dailab.masp.models.Properties.ServiceContainer;

/**
 * Utility class to generate, extend and manipulate context menus.
 * 
 * @author Andre Schulz
 * @since 1.2.46
 */
public class ContextMenuUtil {

    /**
     * The logger.
     */
    private static final transient Log LOG = LogFactory.getLog(ContextMenuUtil.class);

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_AREA_PARENT = {"removeArea"};

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_DEVICE = {"addInteractionResource"};

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_DEVICE_PARENT = {"removeDevice"};

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_DOOR = {};

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_DOOR_PARENT = {"removeDoor"};

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_ELEMENT_WITH_POSITION = {
            "addTag", "removeTag"};

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_ENVIRONMENT = {
            "addDevice", "addOutdoors", "addRoom", "addServiceContainer", "addUser"};

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_INTERACTION_RESOURCE = {};

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_INTERACTION_RESOURCE_PARENT = {"removeInteractionResource"};

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_LOCALIZATION_PROVIDER = {"addTag"};

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_LOCALIZATION_PROVIDER_PARENT = {"removeProvider"};

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_LOCALIZATION_TAG_PARENT = {"removeTag"};

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_PHYSICAL_DEVICE = {"addSubDevice"};

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_PHYSICAL_DEVICE_PARENT = {
            "removeDevice", "removeSubDevice"};

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_PLACE = {
            "addArea", "addDoor", "addWindow", "setRoomType", "setNewFloor"};

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_PLACE_PARENT = {"removePlace"};

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_USER = {
            "setLeftHanded", "setNewBirthDate", "setRightHanded"};

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_USER_PARENT = {"removeUser"};

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_WINDOW = {};

    private static final String[] VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_WINDOW_PARENT = {"removeWindow"};

    /**
     * Appends the sub-menu-elements of an {@link JMenu} to an other
     * {@link JPopupMenu}.
     * 
     * @param targetMenu
     *            The {@link JPopupMenu} where the elements are appended.
     * @param sourceMenu
     *            The {@link JMenu} to get the elements from.
     */
    public static void addSubElements(final JPopupMenu targetMenu, final JMenu sourceMenu) {
        // if(LOG.isDebugEnabled()) {
        // LOG.debug("addSubElements " + sourceMenu + " " +
        // Arrays.toString(sourceMenu.getSubElements()));
        // }

        for(final MenuElement menuElement : sourceMenu.getSubElements()) {
            if(menuElement instanceof JPopupMenu) {
                for(final Component component : ((JPopupMenu)menuElement).getComponents()) {
                    if(component instanceof JMenu) {
                        targetMenu.add(component);
                    } else {
                        targetMenu.add(component);
                    }
                }
            } else if(menuElement instanceof JMenu) {
                targetMenu.add((JMenu)menuElement);
            } else if(menuElement instanceof JMenuItem) {
                targetMenu.add((JMenuItem)menuElement);
            } else if(menuElement instanceof JRadioButtonMenuItem) {
                targetMenu.add((JRadioButtonMenuItem)menuElement);
            } else if(menuElement instanceof JPopupMenu.Separator) {
                targetMenu.addSeparator();
            } else {
                LOG.warn("cannot add " + menuElement);
            }

        }
    }

    /**
     * Returns the menu text for an {@link EnvironmentElement}.
     * 
     * @param environmentElement
     *            The {@link EnvironmentElement}.
     * @return The text for a menu for the {@link EnvironmentElement}.
     */
    public static String getMenuText(final EnvironmentElement environmentElement) {
        if(environmentElement == null) {
            LOG.error("environmentElement is null");
            throw new IllegalArgumentException("environmentElement can not be null in method getMenuText");
        }

        if(environmentElement.getName() != null && !environmentElement.getName().isEmpty()) {
            return environmentElement.getName();
        } else if(environmentElement.getId() != null && !environmentElement.getId().isEmpty()) {
            return environmentElement.getId();
        }

        return environmentElement.eClass().getName();
    }

    /**
     * Returns a {@link JPopupMenu} for a given {@link JMenu}. All menu elements
     * are added to the new {@link JPopupMenu}.
     * 
     * @param menu
     *            The {@link JMenu} where to get the menu elements from.
     * @return A {@link JPopupMenu} containing all children of the {@link JMenu}
     *         .
     */
    public static JPopupMenu getPopupMenu(final JMenu menu) {
        final JPopupMenu popupMenu = new JPopupMenu();

        addSubElements(popupMenu, menu);

        return popupMenu;
    }

    /**
     * Appends the sub-menu-elements of an {@link JMenu} to an other
     * {@link JMenu}.
     * 
     * @param targetMenu
     *            The {@link JMenu} where the elements are appended.
     * @param sourceMenu
     *            The {@link JMenu} to get the elements from.
     */
    private static void addSubElements(final JMenu targetMenu, final JMenu sourceMenu) {
        // LOG.debug("addSubElements " + sourceMenu + " \n" +
        // Arrays.toString(sourceMenu.getSubElements()));
        for(final MenuElement menuElement : sourceMenu.getSubElements()) {
            // LOG.debug("process menuElement " + menuElement + " " +
            // menuElement.getClass() + " \n" +
            // Arrays.toString(menuElement.getSubElements()));
            if(menuElement instanceof JPopupMenu) {
                for(final Component component : ((JPopupMenu)menuElement).getComponents()) {
                    targetMenu.add(component);
                }
            } else if(menuElement instanceof JMenu) {
                for(final MenuElement subMenuElement : ((JMenu)menuElement).getSubElements()) {
                    if(subMenuElement instanceof JPopupMenu) {
                        addSubElements(targetMenu, (JPopupMenu)subMenuElement);
                    } else if(subMenuElement instanceof JMenu) {
                        targetMenu.add((JMenu)subMenuElement);
                    } else if(subMenuElement instanceof JMenuItem) {
                        targetMenu.add((JMenuItem)subMenuElement);
                    } else if(subMenuElement instanceof JRadioButtonMenuItem) {
                        targetMenu.add((JRadioButtonMenuItem)subMenuElement);
                    } else if(subMenuElement instanceof JPopupMenu.Separator) {
                        targetMenu.addSeparator();
                    }
                }
            } else if(menuElement instanceof JMenuItem) {
                targetMenu.add((JMenuItem)menuElement);
            } else {
                LOG.warn("failed to add " + menuElement);
            }
        }// for

        // LOG.debug("result: " + targetMenu + " \n" +
        // Arrays.toString(targetMenu.getSubElements()));
    }// addSubElements

    /**
     * Appends the sub-menu-elements of an {@link JPopupMenu} to an other
     * {@link JMenu}.
     * 
     * @param targetMenu
     *            The {@link JMenu} where the elements are appended.
     * @param sourceMenu
     *            The {@link JPopupMenu} to get the elements from.
     */
    private static void addSubElements(final JMenu targetMenu, final JPopupMenu sourceMenu) {
        // LOG.debug("addSubElements " + sourceMenu + " \n" +
        // Arrays.toString(sourceMenu.getSubElements()));
        for(final MenuElement menuElement : sourceMenu.getSubElements()) {
            // LOG.debug("process menuElement " + menuElement + " " +
            // menuElement.getClass() + " \n" +
            // Arrays.toString(menuElement.getSubElements()));
            if(menuElement instanceof JPopupMenu) {
                for(final Component component : ((JPopupMenu)menuElement).getComponents()) {
                    if(component instanceof JMenu) {
                        addSubElements(targetMenu, (JMenu)component);
                    } else {
                        targetMenu.add(component);
                    }
                }
            } else if(menuElement instanceof JMenu) {
                if(((JMenu)menuElement).getSubElements().length == 0) {
                    LOG.warn("no subelements at " + menuElement);
                    targetMenu.add((JMenu)menuElement);
                } else {
                    for(final MenuElement subMenuElement : ((JMenu)menuElement).getSubElements()) {
                        if(subMenuElement instanceof JPopupMenu) {
                            addSubElements(targetMenu, (JPopupMenu)subMenuElement);
                        } else if(subMenuElement instanceof JMenu) {
                            targetMenu.add((JMenu)subMenuElement);
                        } else if(subMenuElement instanceof JMenuItem) {
                            targetMenu.add((JMenuItem)subMenuElement);
                        } else if(subMenuElement instanceof JRadioButtonMenuItem) {
                            targetMenu.add((JRadioButtonMenuItem)subMenuElement);
                        } else if(subMenuElement instanceof JPopupMenu.Separator) {
                            targetMenu.addSeparator();
                        }
                    }
                }
            } else if(menuElement instanceof JMenuItem) {
                targetMenu.add((JMenuItem)menuElement);
            } else {
                LOG.warn("failed to add " + menuElement);
            }
        }// for

        // LOG.debug("result: " + targetMenu + " \n" +
        // Arrays.toString(targetMenu.getSubElements()));
    }// addSubElements

    /**
     * Appends menu elements for an element in relation to the
     * <code>validDefinitionModificationElements</code>. The
     * {@link DefinitionModificationElement}s are taken from
     * <code>definitionModificationElements</code>.
     * 
     * @param menu
     *            The {@link JMenu} where the menu elements should be added.
     * @param element
     *            The element, the menu elements are for.
     * @param environment
     *            The {@link Environment} of the element.
     * @param definitionModificationElements
     *            All the {@link DefinitionModificationElement}s for the
     *            element.
     * @param validDefinitionModificationElements
     *            The valid names of the {@link DefinitionModificationElement}s.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    private static void appendDefinitionModificationMenuItems(final JMenu menu, final Object element, final Environment environment, final Collection<DefinitionModificationElement> definitionModificationElements, final String[] validDefinitionModificationElements, final PopupInvoker popupInvoker) {
        if(LOG.isDebugEnabled()) {
            final StringBuilder sb = new StringBuilder();
            sb.append("appendDefinitionModificationMenuItems ");
            sb.append(getNames(definitionModificationElements));
            sb.append(" valid: ");
            sb.append(Arrays.toString(validDefinitionModificationElements));
            LOG.debug(sb.toString());
        }

        JMenuItem menuItem;

        for(final DefinitionModificationElement definitionModificationElement : definitionModificationElements) {
            if(contains(validDefinitionModificationElements, definitionModificationElement.getName())) {
                if(definitionModificationElement.getParameters().size() == 0) {
                    // if executable element awaits no parameters
                    // => execute element directly on action
                    menuItem = new JMenuItem(definitionModificationElement.getName());
                    menuItem.addActionListener(new DefinitionModificationElementActionListener(definitionModificationElement, element, null));
                    menu.add(menuItem);
                } else {
                    menuItem = new JMenuItem(definitionModificationElement.getName());
                    menuItem.addActionListener(new ExecutableElementDialogActionListener(element, environment, definitionModificationElement, popupInvoker));
                    menu.add(menuItem);
                }
            }
        }
    }

    /**
     * Appends menu elements for an element in relation to a parent element and
     * the <code>validDefinitionModificationElements</code>. The
     * {@link DefinitionModificationElement}s are taken from
     * <code>definitionModificationElements</code>.
     * 
     * @param menu
     *            The {@link JMenu} where the menu elements should be added.
     * @param element
     *            The element, the menu elements are for.
     * @param parent
     *            The parent element of the <code>element</code>.
     * @param environment
     *            The {@link Environment} of the element.
     * @param parentDefinitionModificationElements
     *            All the {@link DefinitionModificationElement}s for the
     *            <code>parent</code> element.
     * @param validParentDefinitionModificationElementNames
     *            The valid names of the {@link DefinitionModificationElement}s.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    private static void appendParentDefinitionModificationMenuItems(final JMenu menu, final Object element, final Object parent, final Environment environment, final Collection<DefinitionModificationElement> parentDefinitionModificationElements, final String[] validParentDefinitionModificationElementNames, final PopupInvoker popupInvoker) {

        JMenuItem menuItem;

        for(final DefinitionModificationElement definitionModificationElement : parentDefinitionModificationElements) {
            if(contains(validParentDefinitionModificationElementNames, definitionModificationElement.getName())) {
                menuItem = new JMenuItem(definitionModificationElement.getName());
                final EList<Object> parameters = new BasicEList<Object>();
                parameters.add(element);
                menuItem.addActionListener(new DefinitionModificationElementActionListener(definitionModificationElement, parent, parameters));
                menu.add(menuItem);
            }
        }
    }

    /**
     * Appends menu elements for a {@link Vector}.
     * 
     * @param menu
     *            The {@link JMenu} where to append the menu elements.
     * @param vectorWrapper
     *            The {@link VectorWrapper} the menu elements are for.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    private static void appendVectorMenuItems(final JMenu menu, final VectorWrapper vectorWrapper, final PopupInvoker popupInvoker) {
        final VectorActionListener vectorActionListener = new VectorActionListener(vectorWrapper, popupInvoker);
        final JMenuItem menuItem = new JMenuItem(ActionType.EDIT.toString() + " " + vectorWrapper.getName());
        menuItem.addActionListener(vectorActionListener);
        menu.add(menuItem);
    }

    /**
     * Checks whether a {@link String} is in a list of {@link String}s. The case
     * will be ignored.
     * 
     * @param string
     *            The {@link String} to look for.
     * @param strings
     *            The list of {@link String}s.
     * @return Returns <code>true</code> if <code>string</code> occurs in
     *         <code>strings</code>, <code>false</code> otherwise.
     */
    private static boolean contains(final String[] strings, final String string) {
        for(final String s : strings) {
            if(string.equalsIgnoreCase(s)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns a {@link Collection} of all {@link DefinitionModificationElement}
     * s for an {@link EObject}.
     * 
     * @param element
     *            The {@link EObject} to get the
     *            {@link DefinitionModificationElement}s for.
     * @param environment
     *            The {@link Environment} of the <code>element</code>.
     * @return A {@link Collection} of all {@link DefinitionModificationElement}
     *         s.
     */
    private static Collection<DefinitionModificationElement> getDefinitionModificationElements(final EObject element, final Environment environment) {
        final MetaModel metaModel = EcoreMetaModelConverter.convert(ContextPackage.eINSTANCE);

        metaModel.getMetaType().getMetaModel().start(metaModel);

        MetaType type = null;

        try {
            type = metaModel.getType(element);
        }
        catch(final IllegalStateException e) {
            LOG.warn("exception", e);
            metaModel.getMetaType().getMetaModel().start(metaModel);
            type = metaModel.getType(element);
        }

        if(!(type instanceof MetaClass)) {
            throw new IllegalArgumentException("parameter argument must have a MetaClass type and not " + type);
        }

        final MetaClass metaClass = (MetaClass)type;

        final Set<DefinitionModificationElement> defintionModificationElements = MetaMetaModelUtility.getAllDefinitionModificationElements(metaClass);

        return MetaMetaModelUtility.sort(defintionModificationElements);
    }

    /**
     * Returns a {@link String} containing all names of the
     * {@link DefinitionModificationElement}s.
     * 
     * @param definitionModificationElements
     *            A {@link Collection} of {@link DefinitionModificationElement}
     *            s.
     * @return A {@link String}-representation of the {@link Collection}.
     */
    private static String getNames(final Collection<DefinitionModificationElement> definitionModificationElements) {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");

        for(final DefinitionModificationElement definitionModificationElement : definitionModificationElements) {
            sb.append(definitionModificationElement.getName());
            sb.append(", ");
        }

        if(definitionModificationElements.size() > 0) {
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append("]");
        return sb.toString();
    }

    /**
     * Checks whether a {@link JMenu} has sum menu elements.
     * 
     * @param menu
     *            The {@link JMenu} to check.
     * @return <code>true</code> if the <code>menu</code> has sub menu elements,
     *         <code>false</code> otherwise.
     */
    private static boolean hasSubMenu(final JMenu menu) {
        if(menu.getSubElements().length > 0) {
            return true;
        }

        return false;
    }

    private Environment environment;

    private final VisualizerManager visualizerManager;

    public ContextMenuUtil(final VisualizerManager visualizerManager) {
        if(visualizerManager == null) {
            throw new IllegalArgumentException("visualizerManager argument must not be null in constructor ContextMenuUtil!");
        }

        this.visualizerManager = visualizerManager;
        this.environment = visualizerManager.getEnvironment();
    }

    /**
     * Appends the menu elements for an {@link ConfigurationProperty} to an
     * {@link JMenu}.
     * 
     * @param menu
     *            The {@link JMenu} where the elements are appended.
     * @param service
     *            The {@link Service} the {@link ConfigurationProperty} belongs
     *            to.
     * @param configurationProperty
     *            The {@link ConfigurationProperty} itself.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    public void appendConfigurationPropertyMenuItems(final JMenu menu, final Service service, final ConfigurationProperty configurationProperty, final PopupInvoker popupInvoker) {
        final ConfigurationPropertyActionListener configurationPropertyActionListener = new ConfigurationPropertyActionListener(service, configurationProperty, popupInvoker);

        JMenuItem menuItem = new JMenuItem(ActionType.EDIT_KEY.toString());
        menuItem.addActionListener(configurationPropertyActionListener);
        menu.add(menuItem);

        // edit value of ConfigurationProperty
        menuItem = new JMenuItem(ActionType.EDIT_VALUE.toString());
        menuItem.addActionListener(configurationPropertyActionListener);
        menu.add(menuItem);

        // remove ConfigurationProperty
        menuItem = new JMenuItem(ActionType.DELETE_PROPERTY.toString());
        menuItem.addActionListener(configurationPropertyActionListener);
        menu.add(menuItem);
    }

    /**
     * Appends menu elements for an {@link Environment} in relation to the
     * {@link RootElementType}.
     * 
     * @param menu
     *            The {@link JMenu} where to add the menu elements.
     * @param rootElementType
     *            The type of the elements of the {@link Environment} the menu
     *            is for.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    public void appendEnvironmentMenuItems(final JMenu menu, final RootElementType rootElementType, final PopupInvoker popupInvoker) {

        final Collection<DefinitionModificationElement> definitionModificationElements = getDefinitionModificationElements(this.environment, this.environment);
        String[] validDefinitionModificationElements = null;

        if(definitionModificationElements == null) {
            LOG.error("failed to get DefinitionModificationElements of environment!");
            return;
        }
        // addDevice, addOutdoors, addRoom, addServiceContainer, addUser,
        // removeDevice, removePlace, removeServiceContainer, removeUser
        if(rootElementType != null) {
            switch(rootElementType) {
                case SERVICE_CONTAINERS:
                    validDefinitionModificationElements = new String[]{"addServiceContainer"};
                    break;
                case USERS:
                    validDefinitionModificationElements = new String[]{"addUser"};
                    break;
                case DEVICES:
                    validDefinitionModificationElements = new String[]{"addDevice"};
                    break;
                case PLACES:
                    validDefinitionModificationElements = new String[]{"addOutdoors",
                            "addRoom"};
                    break;
                case PROVIDERS:
                    validDefinitionModificationElements = new String[]{"addProvider"};
                    break;
                default:
                    LOG.warn("no emvironment menu for " + rootElementType);
            }
        } else {
            validDefinitionModificationElements = VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_ENVIRONMENT;
        }

        if(validDefinitionModificationElements != null) {
            appendDefinitionModificationMenuItems(menu, this.environment, this.environment, definitionModificationElements, validDefinitionModificationElements, popupInvoker);
        }
    }

    /**
     * Appends menu elements to a {@link JMenu}.<br>
     * Valid classes for the <code>element</code>:
     * <ul>
     * <li>{@link EnvironmentElement}</li>
     * <li>{@link Service}</li>
     * <li>{@link Vector}</li>
     * </ul>
     * (sub-classes are also valid.)
     * 
     * @param menu
     *            The {@link JMenu} where the elements are appended.
     * @param element
     *            The element the menu elements are for.
     * 
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    public void appendMenuItems(final JMenu menu, final Object element, final Object parent, final PopupInvoker popupInvoker) {
        if(LOG.isDebugEnabled()) {
            final StringBuilder sb = new StringBuilder();
            sb.append("appendMenuItems for ");
            sb.append(element);
            sb.append(", parent: ");
            sb.append(parent);
            LOG.debug(sb.toString());
        }

        Collection<DefinitionModificationElement> definitionModificationElements = null;
        Collection<DefinitionModificationElement> parentDefinitionModificationElements = null;

        // +++++ get definition modification elements +++++
        if(element instanceof EObject) {
            definitionModificationElements = getDefinitionModificationElements((EObject)element, this.environment);

            if(LOG.isDebugEnabled()) {
                LOG.debug("definitionModificationElements: " + getNames(definitionModificationElements));
            }
        }

        if(parent != null) {
            if(parent instanceof EObject) {
                parentDefinitionModificationElements = getDefinitionModificationElements((EObject)parent, this.environment);

                if(LOG.isDebugEnabled()) {
                    LOG.debug("parent definitionModificationElements: " + getNames(parentDefinitionModificationElements));
                }
            }
        }
        // ----- get definition modification elements -----

        if(element instanceof ElementWithPosition) {
            final JMenu propertyMenu = new JMenu("Properties");
            appendElementWithPositionPropertyMenuItems(propertyMenu, (ElementWithPosition)element, popupInvoker);

            if(hasSubMenu(propertyMenu)) {
                menu.add(propertyMenu);
            }
        }

        if(element instanceof Environment) {
            appendEnvironmentMenuItems(menu, definitionModificationElements, popupInvoker);
        }

        if(element instanceof Place) {
            appendPlaceMenuItems(menu, (Place)element, definitionModificationElements, parentDefinitionModificationElements, popupInvoker);
        } else if(element instanceof Area) {
            if(parent instanceof Place) {
                appendAreaMenuItems(menu, (Area)element, (Place)parent, definitionModificationElements, parentDefinitionModificationElements, popupInvoker);
            } else {
                LOG.warn("parent object must be of class Place, if element is of class Area");
            }
        } else if(element instanceof VectorWrapper) {
            appendVectorMenuItems(menu, (VectorWrapper)element, popupInvoker);
        }

        if(element instanceof User) {
            appendUserMenuItems(menu, (User)element, definitionModificationElements, parentDefinitionModificationElements, popupInvoker);
        }

        if(element instanceof Door) {
            if(parent instanceof Place) {
                appendDoorMenuItems(menu, (Door)element, (Place)parent, definitionModificationElements, parentDefinitionModificationElements, popupInvoker);
            } else {
                LOG.warn("parent object must be of class Place, if element is of class Door");
            }
        }

        if(element instanceof Window) {
            if(parent instanceof Place) {
                appendWindowMenuItems(menu, (Window)element, (Place)parent, definitionModificationElements, parentDefinitionModificationElements, popupInvoker);
            } else {
                LOG.warn("parent object must be of class Place, if element is of class Window");
            }
        }

        if(element instanceof PhysicalDevice) {
            appendPhysicalDeviceMenuItems(menu, (PhysicalDevice)element, parent, definitionModificationElements, parentDefinitionModificationElements, popupInvoker);
        } else if(element instanceof Device) {
            appendDeviceMenuItems(menu, (Device)element, parent, definitionModificationElements, parentDefinitionModificationElements, popupInvoker);
        }

        if(element instanceof InteractionResource) {
            if(parent instanceof Device) {
                appendInteractionResourceMenuItems(menu, (InteractionResource)element, (Device)parent, definitionModificationElements, parentDefinitionModificationElements, popupInvoker);
            } else {
                LOG.warn("parent object must be of class Device, if element is of class InteractionResource");
            }
        }

        if(element instanceof EnvironmentElement) {
            if(menu.getSubElements().length > 0) {
                menu.addSeparator();
            }

            appendEnvironmentElementMenuItems(menu, (EnvironmentElement)element, popupInvoker);
        }

        if(element instanceof ElementWithPosition) {
            // append menu items only if it is no LocalizationTag (it has
            // special menu items)
            if(!(element instanceof LocalizationTag)) {
                appendElementWithPositionMenuItems(menu, (ElementWithPosition)element, definitionModificationElements, popupInvoker);
            }
        }

        if(element instanceof Door || element instanceof Window) {
            Inlet openable = null;

            if(element instanceof Door) {
                openable = new InletDoor((Door)element);
            } else if(element instanceof Window) {
                openable = new InletWindow((Window)element);
            }

            if(openable != null) {
                appendInletMenuItems(menu, openable, popupInvoker);
            }
        }

        if(element instanceof LocalisationProviderProxy) {
            appendLocalizationProviderProxyMenuItems(menu, (LocalisationProviderProxy)element, definitionModificationElements, parentDefinitionModificationElements, popupInvoker);
        }

        if(element instanceof LocalizationTag) {
            if(parent instanceof LocalisationProviderProxy) {
                appendLocalizationTagMenuItems(menu, (LocalizationTag)element, (LocalisationProviderProxy)parent, definitionModificationElements, parentDefinitionModificationElements, popupInvoker);
            } else {
                LOG.warn("parent of LocalizationTag is no LocalisationProvider " + parent);
            }
        }

        if(element instanceof ServiceContainer) {
            appendServiceContainerMenuItems(menu, (ServiceContainer)element, popupInvoker);
        } else if(element instanceof Service) {
            appendServiceMenuItems(menu, (Service)element, popupInvoker);
        }
    }

    /**
     * Returns a {@link JPopupMenu} for an element. The valid element classes
     * are the same as at
     * {@link ContextMenuUtil#appendMenuItems(JMenu, Object, Object, PopupInvoker)}
     * .
     * 
     * @param element
     *            The element to the the menu elements for.
     * 
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     * @return A {@link JPopupMenu} for the <code>element</code>.
     */
    public JPopupMenu getPopupMenu(final Object element, final Object parent, final PopupInvoker popupInvoker) {
        final JMenu menu = new JMenu("temp");
        appendMenuItems(menu, element, parent, popupInvoker);
        final JPopupMenu popupMenu = new JPopupMenu();
        ContextMenuUtil.addSubElements(popupMenu, menu);

        return popupMenu;
    }

    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }

    /**
     * Appends menu elements for an {@link Area}.
     * 
     * @param menu
     *            The {@link JMenu} where to append the menu elements.
     * @param area
     *            The {@link Area} the menu elements are for.
     * @param place
     *            The {@link Place} of the {@link Area}.
     * 
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    private void appendAreaMenuItems(final JMenu menu, final Area area, final Place place, final Collection<DefinitionModificationElement> definitionModificationElements, final Collection<DefinitionModificationElement> parentDefinitionModificationElements, final PopupInvoker popupInvoker) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("appendAreaMenuItems");
        }

        appendVectorMenuItems(menu, new VectorWrapper(area.getOrigin(), "Origin"), popupInvoker);
        appendVectorMenuItems(menu, new VectorWrapper(area.getSpan(), "Span"), popupInvoker);

        appendParentDefinitionModificationMenuItems(menu, area, place, this.environment, parentDefinitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_AREA_PARENT, popupInvoker);
    }

    /**
     * TODO comment
     * 
     * @param menu
     * @param device
     * @param parent
     * @param definitionModificationElements
     * @param parentDefinitionModificationElements
     * @param popupInvoker
     */
    private void appendDeviceMenuItems(final JMenu menu, final Device device, final Object parent, final Collection<DefinitionModificationElement> definitionModificationElements, final Collection<DefinitionModificationElement> parentDefinitionModificationElements, final PopupInvoker popupInvoker) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("appendDeviceMenuItems");
        }
        appendDefinitionModificationMenuItems(menu, device, this.environment, definitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_DEVICE, popupInvoker);
        appendParentDefinitionModificationMenuItems(menu, device, parent, this.environment, parentDefinitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_DEVICE_PARENT, popupInvoker);
    }

    /**
     * Appends menu elements for {@link Door}s.
     * 
     * @param menu
     *            The {@link JMenu} where the elements should be added.
     * @param door
     *            The {@link Door}.
     * @param place
     *            The {@link Place} of the {@link Door}.
     * @param definitionModificationElements
     *            The {@link DefinitionModificationElement}s for {@link Door}s.
     * @param parentDefinitionModificationElements
     *            The {@link DefinitionModificationElement} for {@link Place}s.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    private void appendDoorMenuItems(final JMenu menu, final Door door, final Place place, final Collection<DefinitionModificationElement> definitionModificationElements, final Collection<DefinitionModificationElement> parentDefinitionModificationElements, final PopupInvoker popupInvoker) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("appendDoorMenuItems");
        }

        final JMenuItem menuItem = new JMenuItem(ActionType.EDIT_SPAN.toString());
        menuItem.addActionListener(new VectorActionListener(new VectorWrapper(door.getSpan(), "Span"), popupInvoker));
        menu.add(menuItem);

        appendDefinitionModificationMenuItems(menu, door, this.environment, definitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_DOOR, popupInvoker);
        appendParentDefinitionModificationMenuItems(menu, door, place, this.environment, parentDefinitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_DOOR_PARENT, popupInvoker);
    }

    /**
     * Appends menu items for {@link ElementWithPosition}.
     * 
     * @param menu
     *            The {@link JMenu} where the menu elements are appended.
     * @param element
     *            The {@link ElementWithPosition} the menu is for.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    private void appendElementWithPositionMenuItems(final JMenu menu, final ElementWithPosition element, final Collection<DefinitionModificationElement> definitionModificationElements, final PopupInvoker popupInvoker) {
        appendVectorMenuItems(menu, new VectorWrapper(element.getPosition(), "Position"), popupInvoker);

        appendDefinitionModificationMenuItems(menu, element, this.environment, definitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_ELEMENT_WITH_POSITION, popupInvoker);
    }

    /**
     * Appends menu elements for an {@link ElementWithPosition} to an
     * {@link JMenu}.
     * 
     * @param menu
     *            The {@link JMenu} where the menu elements are appended.
     * @param element
     *            The {@link ElementWithPosition} the menu is for.
     */
    private void appendElementWithPositionPropertyMenuItems(final JMenu menu, final ElementWithPosition element, final PopupInvoker popupInvoker) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("appendElementWithPositionPropertyMenuItems " + element.getId());
        }

        // the menu items
        // JMenu propertyM;

        // went over all possible properties
        for(final EReference propertyReference : AppliancesUtility.getPropertyEReferences(element)) {
            final JMenu propertyMenu = new JMenu(propertyReference.getName());
            appendPropertyMenuItems(propertyMenu, new ElementProperty(element, (Property<?>)element.eGet(propertyReference), propertyReference), popupInvoker);
            menu.add(propertyMenu);
            // addSubElements(menu, propertyMenu);
        }// for
    }// addElementWithPositionPropertyMenu

    /**
     * Appends menu elements for an {@link EnvironmentElement}.
     * 
     * @param popupMenu
     *            The {@link JMenu} where to add the menu elements.
     * @param environmentElement
     *            The {@link EnvironmentElement} the menu elements are for.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    private void appendEnvironmentElementMenuItems(final JMenu popupMenu, final EnvironmentElement environmentElement, final PopupInvoker popupInvoker) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("appendEnvironmentElementMenuItems for " + environmentElement);
        }

        final ElementActionListener elementActionListener = new ElementActionListener(environmentElement, this.environment, popupInvoker);

        if(environmentElement instanceof Area) {
            elementActionListener.setArea((Area)environmentElement);
        }

        JMenuItem menuItem = new JMenuItem(ActionType.EDIT_ID.toString());
        menuItem.addActionListener(elementActionListener);
        menuItem.setActionCommand(ActionType.EDIT_ID.toString());
        popupMenu.add(menuItem);

        menuItem = new JMenuItem(ActionType.EDIT_NAME.toString());
        menuItem.addActionListener(elementActionListener);
        menuItem.setActionCommand(ActionType.EDIT_NAME.toString());
        popupMenu.add(menuItem);

        // menuItem = new JMenuItem(new
        // StringBuilder(ActionType.REMOVE_ENVIRONMENT_ELEMENT.toString()).append(" ").append(environmentElement.eClass().getName()).toString());
        // menuItem.addActionListener(elementActionListener);
        // menuItem.setActionCommand(ActionType.REMOVE_ENVIRONMENT_ELEMENT.toString());
        // popupMenu.add(menuItem);
    }

    /**
     * TODO comment
     * 
     * @param menu
     * @param definitionModificationElements
     * @param popupInvoker
     */
    private void appendEnvironmentMenuItems(final JMenu menu, final Collection<DefinitionModificationElement> definitionModificationElements, final PopupInvoker popupInvoker) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("appendEnvironmentMenuItems");
        }
        appendDefinitionModificationMenuItems(menu, this.environment, this.environment, definitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_ENVIRONMENT, popupInvoker);
    }

    /**
     * Appends menu items for {@link Inlet}s.
     * 
     * @param menu
     * @param openable
     * @param popupInvoker
     * @since 1.3.8
     */
    private void appendInletMenuItems(final JMenu menu, final Inlet openable, final PopupInvoker popupInvoker) {
        appendVectorMenuItems(menu, new VectorWrapper(openable.getSpan(), "Span"), popupInvoker);
    }

    /**
     * TODO comment
     * 
     * @param menu
     * @param interactionResource
     * @param device
     * @param definitionModificationElements
     * @param parentDefinitionModificationElements
     * @param popupInvoker
     */
    private void appendInteractionResourceMenuItems(final JMenu menu, final InteractionResource interactionResource, final Device device, final Collection<DefinitionModificationElement> definitionModificationElements, final Collection<DefinitionModificationElement> parentDefinitionModificationElements, final PopupInvoker popupInvoker) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("appendInteractionResourceMenuItems");
        }

        appendDefinitionModificationMenuItems(menu, interactionResource, this.environment, definitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_INTERACTION_RESOURCE, popupInvoker);
        appendParentDefinitionModificationMenuItems(menu, interactionResource, device, this.environment, parentDefinitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_INTERACTION_RESOURCE_PARENT, popupInvoker);
    }

    /**
     * TODO comment
     * 
     * @param menu
     * @param element
     * @param definitionModificationElements
     * @param parentDefinitionModificationElements
     * @param popupInvoker
     * @since 1.3.12
     */
    private void appendLocalizationProviderProxyMenuItems(final JMenu menu, final LocalisationProviderProxy element, final Collection<DefinitionModificationElement> definitionModificationElements, final Collection<DefinitionModificationElement> parentDefinitionModificationElements, final PopupInvoker popupInvoker) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("appendLocalizationProviderProxyMenuItems");
        }

        appendDefinitionModificationMenuItems(menu, element, this.environment, definitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_LOCALIZATION_PROVIDER, popupInvoker);
        appendParentDefinitionModificationMenuItems(menu, element, this.environment, this.environment, parentDefinitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_LOCALIZATION_PROVIDER_PARENT, popupInvoker);
    }

    /**
     * TODO comment
     * 
     * @param menu
     * @param element
     * @param definitionModificationElements
     * @param parentDefinitionModificationElements
     * @param popupInvoker
     * @since 1.3.12
     */
    private void appendLocalizationTagMenuItems(final JMenu menu, final LocalizationTag element, final LocalisationProviderProxy localisationProvider, final Collection<DefinitionModificationElement> definitionModificationElements, final Collection<DefinitionModificationElement> parentDefinitionModificationElements, final PopupInvoker popupInvoker) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("appendLocalizationTagMenuItems");
        }

        appendParentDefinitionModificationMenuItems(menu, element, localisationProvider, this.environment, parentDefinitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_LOCALIZATION_TAG_PARENT, popupInvoker);
    }

    /**
     * TODO comment
     * 
     * @param menu
     * @param physicalDevice
     * @param parent
     * @param definitionModificationElements
     * @param parentDefinitionModificationElements
     * @param popupInvoker
     */
    private void appendPhysicalDeviceMenuItems(final JMenu menu, final PhysicalDevice physicalDevice, final Object parent, final Collection<DefinitionModificationElement> definitionModificationElements, final Collection<DefinitionModificationElement> parentDefinitionModificationElements, final PopupInvoker popupInvoker) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("appendPhysicalDeviceMenuItems for " + physicalDevice);
        }

        if(physicalDevice.getSubDevice().size() > 0) {
            final JMenu subDevicesMenu = new JMenu("Sub devices");

            synchronized(physicalDevice.getSubDevice()) {
                for(final PhysicalDevice subDevice : physicalDevice.getSubDevice()) {
                    final JMenu subDeviceMenu = new JMenu(ContextMenuUtil.getMenuText(subDevice));
                    appendMenuItems(subDeviceMenu, subDevice, physicalDevice, popupInvoker);
                    subDevicesMenu.add(subDeviceMenu);
                }
            }

            menu.add(subDevicesMenu);
        }

        appendDefinitionModificationMenuItems(menu, physicalDevice, this.environment, definitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_PHYSICAL_DEVICE, popupInvoker);
        appendParentDefinitionModificationMenuItems(menu, physicalDevice, parent, this.environment, parentDefinitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_PHYSICAL_DEVICE_PARENT, popupInvoker);
    }

    /**
     * Appends menu elements for a {@link Place}.
     * 
     * @param menu
     *            The {@link JMenu} where to append the menu elements.
     * @param place
     *            The {@link Place} the menu elements are for.
     * @param definitionModificationElements
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    private void appendPlaceMenuItems(final JMenu menu, final Place place, final Collection<DefinitionModificationElement> definitionModificationElements, final Collection<DefinitionModificationElement> parentDefinitionModificationElements, final PopupInvoker popupInvoker) {
        appendDefinitionModificationMenuItems(menu, place, this.environment, definitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_PLACE, popupInvoker);
        appendParentDefinitionModificationMenuItems(menu, place, this.environment, this.environment, parentDefinitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_PLACE_PARENT, popupInvoker);
    }

    /**
     * Appends menu element for a {@link Property} of an
     * {@link EnvironmentElement}.
     * 
     * @param elementProperty
     *            The {@link ElementProperty} where the
     *            {@link EnvironmentElement} and the {@link EReference} are
     *            hold.
     */
    private void appendPropertyMenuItems(final JMenu menu, final ElementProperty elementProperty, final PopupInvoker popupInvoker) {
        if(LOG.isDebugEnabled()) {
            final StringBuilder sb = new StringBuilder();
            sb.append("appendPropertyMenuItems to ");
            sb.append(menu.getName() == null ? menu : menu.getName());
            sb.append(" \nelement: ");
            sb.append(elementProperty.getElement().getId() == null ? elementProperty.getElement()
                    : elementProperty.getElement().getId());
            sb.append(" \npropertyReference: ");
            sb.append(elementProperty.getPropertyReference().getName() == null ? elementProperty.getPropertyReference()
                    : elementProperty.getPropertyReference().getName());

            LOG.debug(sb.toString());
        }

        // the menu for an Actor or a Sensor
        JMenu serviceMenu;

        JMenuItem menuItem;

        // ElementProperty elementProperty = null;

        final EList<ServiceContainer> serviceContainers = this.environment.getServiceContainers();

        // the current container of an actor or a sensor
        ServiceContainer currentContainer = null;

        // ++++++++++ sensors ++++++++++
        serviceMenu = new JMenu("Edit Sensor");

        // +++++ current service container +++++
        if(elementProperty.getProperty() != null && elementProperty.getProperty().getSensor() != null) {
            currentContainer = elementProperty.getProperty().getSensor().getContainer();
        }

        if(currentContainer != null) {
            appendServiceContainerPropertyMenuItems(serviceMenu, elementProperty, elementProperty.getPropertyReference().getName(), elementProperty.getProperty().getSensor(), currentContainer, ServiceType.SENSOR, popupInvoker);
        }
        // ----- current service container -----

        // +++++ other service containers +++++
        if(serviceMenu.getSubElements().length > 0) {
            serviceMenu.addSeparator();
        }

        if(elementProperty.getProperty() == null) {
            LOG.warn("elementProperty.getProperty() is null");
            // elementProperty
        }

        // menus for the other containers
        for(final ServiceContainer serviceContainer : serviceContainers) {
            if((currentContainer == null) || !currentContainer.equals(serviceContainer)) {
                final JMenu m = new JMenu(serviceContainer.getId());
                appendServiceContainerPropertyMenuItems(m, elementProperty, elementProperty.getPropertyReference().getName(), null, serviceContainer, ServiceType.SENSOR, popupInvoker);
                serviceMenu.add(m);
            }
        }
        // ----- other service containers -----

        // menu to deselect Sensor
        if((elementProperty.getProperty() != null) && (elementProperty.getProperty().getSensor() != null)) {
            if(serviceMenu.getSubElements().length > 0) {
                serviceMenu.addSeparator();
            }

            menuItem = new JMenuItem("None");
            menuItem.addActionListener(new PropertyServiceRemovalActionListener(elementProperty, ServiceType.SENSOR, popupInvoker));
            serviceMenu.add(menuItem);
        }// if

        menu.add(serviceMenu);
        // ---------- sensors ----------

        currentContainer = null;

        // ++++++++++ actors ++++++++++
        serviceMenu = new JMenu("Edit Actor");

        // +++++ current service container +++++
        if(elementProperty.getProperty() != null && elementProperty.getProperty().getActor() != null) {
            currentContainer = elementProperty.getProperty().getActor().getContainer();
        }

        if(currentContainer != null) {
            appendServiceContainerPropertyMenuItems(serviceMenu, elementProperty, elementProperty.getPropertyReference().getName(), elementProperty.getProperty().getActor(), currentContainer, ServiceType.ACTOR, popupInvoker);
        }
        // ----- current service container -----

        if(serviceMenu.getSubElements().length > 0) {
            serviceMenu.addSeparator();
        }

        // menus for the other containers
        for(final ServiceContainer serviceContainer : serviceContainers) {
            if((currentContainer == null) || !currentContainer.equals(serviceContainer)) {
                final JMenu m = new JMenu(serviceContainer.getId());
                appendServiceContainerPropertyMenuItems(m, elementProperty, elementProperty.getPropertyReference().getName(), null, serviceContainer, ServiceType.ACTOR, popupInvoker);
                serviceMenu.add(m);
            }
        }

        // menu to deselect Actor
        if((elementProperty.getProperty() != null) && (elementProperty.getProperty().getActor() != null)) {
            if(serviceMenu.getSubElements().length > 0) {
                serviceMenu.addSeparator();
            }

            menuItem = new JMenuItem("None");
            menuItem.addActionListener(new PropertyServiceRemovalActionListener(elementProperty, ServiceType.ACTOR, popupInvoker));
            serviceMenu.add(menuItem);
        }// if

        menu.add(serviceMenu);
        // ---------- actors ----------
    }// getPropertyMenu

    /**
     * Appends menu elements for a {@link ServiceContainer}.
     * 
     * @param menu
     *            The {@link JMenu} where to add the menu elements.
     * @param serviceContainer
     *            The {@link ServiceContainer} the menu elements are for.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    private void appendServiceContainerMenuItems(final JMenu menu, final ServiceContainer serviceContainer, final PopupInvoker popupInvoker) {
        final ServiceContainerActionListener serviceContainerActionListener = new ServiceContainerActionListener(this.environment, serviceContainer, popupInvoker);

        // add actor
        JMenuItem menuItem = new JMenuItem(ActionType.NEW_ACTOR.toString());
        menuItem.addActionListener(serviceContainerActionListener);
        menu.add(menuItem);

        // add sensor
        menuItem = new JMenuItem(ActionType.NEW_SENSOR.toString());
        menuItem.addActionListener(serviceContainerActionListener);
        menu.add(menuItem);

        menuItem = new JMenuItem(ActionType.DISCOVER_SERVICE.toString());
        menuItem.addActionListener(new DiscoverServiceActionListener(null, null, serviceContainer, this.environment, this.visualizerManager, ServiceType.SERVICE, popupInvoker));
        menu.add(menuItem);

        // add sub-service container
        menuItem = new JMenuItem(ActionType.NEW_SERVICE_CONTAINER.toString());
        menuItem.addActionListener(serviceContainerActionListener);
        menu.add(menuItem);

        // edit ID
        menuItem = new JMenuItem(ActionType.EDIT_ID.toString());
        menuItem.addActionListener(serviceContainerActionListener);
        menu.add(menuItem);

        // remove ServiceContainer
        menu.addSeparator();
        menuItem = new JMenuItem(ActionType.DELETE_SERVICE_CONTAINER.toString());
        menuItem.addActionListener(serviceContainerActionListener);
        menu.add(menuItem);

    }

    /**
     * Appends menu elements for setting a {@link Service} for a
     * {@link Property}. The {@link Service} could be an {@link Actor} or a
     * {@link Sensor}.
     * 
     * @param elementProperty
     *            The {@link ElementProperty} to set the {@link Service}.
     * @param serviceContainer
     *            The {@link ServiceContainer} for which the menu should be.
     * @param type
     *            The type of {@link Service} for which the menu should be. (
     *            <code>ACTOR</code> indicates {@link Actor} and
     *            <code>SENSOR</code> indicates {@link Sensor}.)
     */
    private void appendServiceContainerPropertyMenuItems(final JMenu popupMenu, final ElementProperty elementProperty, final String propertyName, final Service currentService, final ServiceContainer serviceContainer, final ServiceType type, final PopupInvoker popupInvoker) {
        // if(LOG.isDebugEnabled()) {
        // LOG.debug("appendServiceContainerMenuItems pn=" + propertyName +
        // " cs=" + (currentService == null ? "null"
        // : currentService.getId()) + " sc=" + (serviceContainer == null ?
        // "null"
        // : serviceContainer.getId()) + " \n" + elementProperty);
        // }

        final JMenu containerM = new JMenu(serviceContainer.getId());
        final ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem serviceRBMI;

        for(final Service service : serviceContainer.getServices()) {

            // LOG.debug("process service " + service.getId());

            if(service.getId() == null) {
                LOG.warn("id of service is null! " + serviceContainer.getId());
                continue;
            }

            if(service instanceof ServiceContainer) {
                // if(LOG.isDebugEnabled()) {
                // LOG.debug("is ServiceContainer " + service.getId() + " " +
                // elementProperty);
                // }
                final JMenu serviceContainerMenu = new JMenu(service.getId());
                // process sub-ServiceContsainer

                if(elementProperty.getProperty() != null) {
                    if(type == ServiceType.ACTOR) {
                        // JPopupMenu m = new JPopupMenu();
                        // LOG.debug("old " + containerM + " \n" +
                        // Arrays.toString(containerM.getSubElements()));
                        appendServiceContainerPropertyMenuItems(serviceContainerMenu, elementProperty, propertyName, elementProperty.getProperty().getActor(), (ServiceContainer)service, type, popupInvoker);
                        // LOG.debug("to add: " +
                        // Arrays.toString(m.getSubElements()));
                        // addSubElements(containerM, m);
                        // LOG.debug("new " + containerM + " \n" +
                        // Arrays.toString(containerM.getSubElements()));
                    } else if(type == ServiceType.SENSOR) {
                        // JPopupMenu m = new JPopupMenu();
                        // LOG.debug("old " + containerM + " \n" +
                        // Arrays.toString(containerM.getSubElements()));
                        appendServiceContainerPropertyMenuItems(serviceContainerMenu, elementProperty, propertyName, elementProperty.getProperty().getSensor(), (ServiceContainer)service, type, popupInvoker);
                        // LOG.debug("to add: " +
                        // Arrays.toString(m.getSubElements()));
                        // addSubElements(containerM, m);
                        // LOG.debug("new " + containerM + " \n" +
                        // Arrays.toString(containerM.getSubElements()));
                    } else {
                        LOG.warn("unknown service type " + type);
                    }
                } else {
                    LOG.warn("elementProperty.getProperty() is null");
                }
                containerM.add(serviceContainerMenu);
                continue;
            }// if ServiceContainer

            switch(type) {
                case ACTOR:
                    if(service instanceof Actor) {
                        // if(LOG.isDebugEnabled()) {
                        // LOG.debug("is Actor " + service.getId());
                        // }
                        serviceRBMI = new JRadioButtonMenuItem(service.getId());
                        group.add(serviceRBMI);
                        if(service.equals(currentService)) {
                            serviceRBMI.setSelected(true);
                        }
                        serviceRBMI.addActionListener(new PropertySetServiceActionListener(elementProperty, null, (Actor)service, popupInvoker));
                        containerM.add(serviceRBMI);
                    }
                    break;
                case SENSOR:
                    // if(LOG.isDebugEnabled()) {
                    // LOG.debug("is Sensor " + service.getId());
                    // }
                    if(service instanceof Sensor) {
                        serviceRBMI = new JRadioButtonMenuItem(service.getId());
                        group.add(serviceRBMI);
                        if(service.equals(currentService)) {
                            serviceRBMI.setSelected(true);
                        }
                        serviceRBMI.addActionListener(new PropertySetServiceActionListener(elementProperty, (Sensor)service, null, popupInvoker));
                        containerM.add(serviceRBMI);
                    }
                    break;
            }// switch (propertyService)
        }// for serviceContainer.getServices()

        // TODO implement discovery of services
        // using ServiceDiscoveryFrame

        // // menu for creating new Services
        // if(containerM.getSubElements().length > 0) {
        // containerM.addSeparator();
        // }
        // JMenuItem menuItem = new
        // JMenuItem(ContextModelVisualizer.getCreateServiceString(type,
        // serviceContainer));
        // menuItem.addActionListener(new
        // ElementAddServiceActionListener(elementProperty, serviceContainer,
        // propertyName, type, popupInvoker));
        // containerM.add(menuItem);
        //
        // // menu to search for UPnP-Services
        // if(type == ServiceType.ACTOR) {
        // menuItem = new JMenuItem(ActionType.DISCOVER_UPNP_ACTOR.toString());
        // } else {
        // menuItem = new JMenuItem(ActionType.DISCOVER_UPNP_SENSOR.toString());
        // }
        //
        // menuItem.addActionListener(new
        // DiscoverServiceActionListener(elementProperty, propertyName,
        // serviceContainer, environment, null, type, popupInvoker));
        // containerM.add(menuItem);

        // LOG.debug("add " + containerM + " \n" +
        // Arrays.toString(containerM.getSubElements()));

        // popupMenu.add(containerM);
        addSubElements(popupMenu, containerM);
    }// getServiceContainerMenu

    /**
     * Appends menu elements for a {@link Service}.
     * 
     * @param menu
     *            The {@link JMenu} where to add the menu elements.
     * @param service
     *            The {@link Service} the menu elements are for.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    private void appendServiceMenuItems(final JMenu menu, final Service service, final PopupInvoker popupInvoker) {
        final ServiceActionListener serviceActionListener = new ServiceActionListener(service, this.environment, popupInvoker);

        // new ConfigurationProperty
        JMenuItem menuItem = new JMenuItem(ActionType.NEW_CONFIGURATION_PROPERTY.toString());
        menuItem.addActionListener(serviceActionListener);
        menu.add(menuItem);

        // edit ID
        menuItem = new JMenuItem(ActionType.EDIT_ID.toString());
        menuItem.addActionListener(serviceActionListener);
        menu.add(menuItem);

        // remove Service
        menu.addSeparator();
        menuItem = new JMenuItem(ActionType.DELETE_SERVICE.toString());
        menuItem.addActionListener(serviceActionListener);
        menu.add(menuItem);
    }

    /**
     * TODO comment
     * 
     * @param menu
     * @param user
     * @param definitionModificationElements
     * @param parentDefinitionModificationElements
     * @param popupInvoker
     */
    private void appendUserMenuItems(final JMenu menu, final User user, final Collection<DefinitionModificationElement> definitionModificationElements, final Collection<DefinitionModificationElement> parentDefinitionModificationElements, final PopupInvoker popupInvoker) {
        appendDefinitionModificationMenuItems(menu, user, this.environment, definitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_USER, popupInvoker);
        appendParentDefinitionModificationMenuItems(menu, user, this.environment, this.environment, parentDefinitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_USER_PARENT, popupInvoker);
    }

    /**
     * Appends menu elements for {@link Window}s.
     * 
     * @param menu
     *            The {@link JMenu} where the elements should be added.
     * @param window
     *            The {@link Window}.
     * @param place
     *            The {@link Place} of the {@link Window}.
     * @param definitionModificationElements
     *            The {@link DefinitionModificationElement}s for {@link Window}
     *            s.
     * @param parentDefinitionModificationElements
     *            The {@link DefinitionModificationElement} for {@link Place}s.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    private void appendWindowMenuItems(final JMenu menu, final Window window, final Place place, final Collection<DefinitionModificationElement> definitionModificationElements, final Collection<DefinitionModificationElement> parentDefinitionModificationElements, final PopupInvoker popupInvoker) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("appendWindowMenuItems");
        }

        appendDefinitionModificationMenuItems(menu, window, this.environment, definitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_WINDOW, popupInvoker);
        appendParentDefinitionModificationMenuItems(menu, window, place, this.environment, parentDefinitionModificationElements, VALID_DEFINITION_MODIFICATION_ELEMENT_NAMES_WINDOW_PARENT, popupInvoker);
    }
}
