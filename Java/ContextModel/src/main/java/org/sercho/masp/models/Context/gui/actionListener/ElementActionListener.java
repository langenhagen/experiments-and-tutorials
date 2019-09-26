package org.sercho.masp.models.Context.gui.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EObject;
import org.sercho.masp.models.Context.Area;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.Door;
import org.sercho.masp.models.Context.ElementWithPosition;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.EnvironmentElement;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.PhysicalDevice;
import org.sercho.masp.models.Context.Place;
import org.sercho.masp.models.Context.User;
import org.sercho.masp.models.Context.Window;
import org.sercho.masp.models.Context.gui.PopupInvoker;
import org.sercho.masp.models.Context.gui.dialogs.Dialog.Option;
import org.sercho.masp.models.Context.gui.dialogs.StringDialog;
import org.sercho.masp.models.Context.gui.enums.ActionType;
import org.sercho.masp.models.Context.gui.frames.ElementCreationFrame;
import org.sercho.masp.models.Context.gui.frames.ValueHolder;

/**
 * {@link ActionListener} for an element.
 * 
 * @author Andre Schulz
 * @since 1.2.46
 */
public class ElementActionListener extends PopupActionListener implements
        EnvActionListener, StringDialogActionListener {

    private static final transient Log LOG = LogFactory.getLog(ElementActionListener.class);

    /**
     * The element the action is for.
     */
    private final EnvironmentElement element;

    /**
     * The {@link Environment}.
     */
    private final Environment environment;

    /**
     * The {@link Area} the action is for. (Not used in every case.)
     */
    private Area area;

    /**
     * Creates a new {@link ElementActionListener} to handle events related to
     * {@link Area}s.
     * 
     * @param element
     *            The {@link EnvironmentElement} this {@link ActionListener} is
     *            for.
     * @param area
     *            The {@link Area} the evets are related to.
     * @param environment
     *            The {@link Environment}.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    public ElementActionListener(final EnvironmentElement element, final Area area,
            final Environment environment, final PopupInvoker popupInvoker) {
        super(popupInvoker);
        this.element = element;
        this.area = area;
        this.environment = environment;
    }

    /**
     * Creates a new {@link ElementActionListener} to handle events for
     * {@link EnvironmentElement}s.
     * 
     * @param element
     *            The {@link EnvironmentElement} this {@link ActionListener} is
     *            for.
     * @param environment
     *            The {@link Environment}.
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    public ElementActionListener(final EnvironmentElement element,
            final Environment environment, final PopupInvoker popupInvoker) {
        super(popupInvoker);
        this.element = element;
        this.environment = environment;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final ActionType actionType = ActionType.getActionType(e.getActionCommand());

        if(actionType == null) {
            LOG.error("failed to get action type from action event " + e);
            return;
        }

        Place place;

        outer: switch(actionType) {
            case EDIT_NAME:
            case EDIT_ID:
                switch(actionType) {
                    case EDIT_NAME:
                        new StringDialog(super.popupInvoker.getParentFrame(), "Edit name of " + this.element.getId(), "Change the name or enter a new name:", this.element.getName(), JOptionPane.QUESTION_MESSAGE, this, actionType);
                        break;
                    case EDIT_ID:
                        new StringDialog(super.popupInvoker.getParentFrame(), "Edit ID of " + this.element.getId(), "Change the ID or enter a new ID:", this.element.getId(), JOptionPane.QUESTION_MESSAGE, this, actionType);
                        break;
                }
                break;
            case NEW_AREA:
                if(this.element instanceof Place) {
                    place = (Place)this.element;
                    new ElementCreationFrame(this, ContextFactory.eINSTANCE.createArea(), place, this.environment, actionType);
                } else {
                    LOG.warn("cannot add Area to Place: The Place is null ");
                }
                break;
            case REMOVE_PLACE:
                if(this.element instanceof Place) {
                    boolean delete = true;

                    place = (Place)this.element;

                    if(place.getAreas().size() > 0) {
                        final int option = JOptionPane.showConfirmDialog(this.popupInvoker.getParentFrame(), "<html>The place has areas. Do you realy want to delete it?</html>", "Deletion of Areas", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                        if(option == JOptionPane.YES_OPTION) {
                            delete = true;
                        } else {
                            delete = false;
                        }

                    }

                    if(delete) {
                        place = (Place)this.element;
                        place.getEnvironment().removePlace(place);
                    }
                }
                break;
            case REMOVE_AREA:
                if(this.area != null) {
                    place = ((Place)this.element);
                    place.removeArea(this.area);
                } else {
                    LOG.warn("cannot remove area: it is null!");
                }
                break;
            case NEW_DOOR:
                if(this.element instanceof Place) {
                    place = (Place)this.element;
                    new ElementCreationFrame(this, ContextFactory.eINSTANCE.createDoor(), place, this.environment, actionType);
                } else {
                    LOG.warn("cannot add Door to Place: The Place is null ");
                }
                break;
            case NEW_WINDOW:
                if(this.element instanceof Place) {
                    place = (Place)this.element;
                    new ElementCreationFrame(this, ContextFactory.eINSTANCE.createWindow(), place, this.environment, actionType);
                } else {
                    LOG.warn("cannot add Window to Place: The Place is null ");
                }
                break;
            case REMOVE_ENVIRONMENT_ELEMENT:
                if(this.area != null) {
                    // remove an area
                    if(this.element instanceof Place) {
                        place = ((Place)this.element);
                        place.removeArea(this.area);
                    } else {
                        LOG.warn("cannot remove Area: the element is no Place: " + this.element);
                    }

                } else if(this.element instanceof Place) {
                    // remove a place
                    place = (Place)this.element;
                    place.getEnvironment().removePlace(place);
                } else if(this.element instanceof Door) {
                    final Door door = (Door)this.element;
                    place = door.getPlace();

                    if(place != null) {
                        synchronized(place.getDoors()) {
                            place.getDoors().remove(door);
                        }
                    } else {
                        final Environment environment = door.getEnvironment();

                        if(environment != null) {
                            for(final Place p : environment.getPlaces()) {
                                if(p.getDoors().contains(door)) {
                                    synchronized(p.getDoors()) {
                                        p.getDoors().remove(door);
                                        break outer;
                                    }
                                }
                            }
                        } else {
                            LOG.warn("cannot remove Door from Environment: The Environment is null " + door);
                        }

                        LOG.warn("cannot remove Door from Place: The Place is null " + door);
                    }
                } else if(this.element instanceof User) {
                    final User user = (User)this.element;
                    final Environment environment = user.getEnvironment();

                    if(environment != null) {
                        environment.removeUser(user);
                    } else {
                        LOG.warn("cannot remove User from Environment: The Environment is null " + user);
                    }
                } else if(this.element instanceof InteractionResource) {
                    if(LOG.isDebugEnabled()) {
                        LOG.debug("remove InteractionResource " + this.element);
                    }

                    final InteractionResource interactionResource = (InteractionResource)this.element;
                    place = interactionResource.getPlace();

                    if(place != null) {
                        synchronized(place.getInteractionResources()) {
                            place.getInteractionResources().remove(interactionResource);
                            interactionResource.setPlace(null);
                        }
                    } else {
                        final Device device = interactionResource.getDevice();

                        if(device.getResources().contains(interactionResource)) {
                            synchronized(device.getResources()) {
                                device.getResources().remove(interactionResource);
                                break outer;
                            }
                        }

                        final Environment environment = interactionResource.getEnvironment();

                        if(environment != null) {
                            for(final Place p : environment.getPlaces()) {
                                // LOG.info(p + " " +
                                // ContextModelVisualizer.getIds(p.getInteractionResources())
                                // + " \n" +
                                // ContextModelVisualizer.getIds(p.getElements()));
                                if(p.getInteractionResources().contains(interactionResource)) {
                                    synchronized(p.getInteractionResources()) {
                                        p.getInteractionResources().remove(interactionResource);
                                        break outer;
                                    }
                                }
                            }
                        } else {
                            LOG.warn("cannot remove InteractionResource from Environment: The Environment is null " + interactionResource);
                        }

                        LOG.warn("cannot remove InteractionResource from Place: The Place is null " + interactionResource);
                    }
                } else if(this.element instanceof ElementWithPosition) {
                    if(LOG.isDebugEnabled()) {
                        LOG.debug("remove ElementWithPosition " + this.element);
                    }

                    if(this.element instanceof Device) {
                        this.environment.removeDevice((Device)this.element);
                    } else if(this.element instanceof User) {
                        this.environment.removeUser((User)this.element);
                    }

                    final ElementWithPosition elementWithPosition = (ElementWithPosition)this.element;
                    place = elementWithPosition.getPlace();

                    if(place != null) {
                        synchronized(place.getElements()) {
                            place.getElements().remove(elementWithPosition);
                            elementWithPosition.setPlace(null);
                        }
                    } else {
                        if(elementWithPosition instanceof PhysicalDevice) {
                            final PhysicalDevice pysicalDevice = (PhysicalDevice)elementWithPosition;

                            if(pysicalDevice.getParentDevice().getSubDevice().contains(pysicalDevice)) {
                                // remove from parent device
                                synchronized(pysicalDevice.getParentDevice().getSubDevice()) {
                                    pysicalDevice.getParentDevice().getSubDevice().remove(pysicalDevice);
                                    break outer;
                                }
                            }
                        }

                        if(this.environment != null) {
                            // remove from containing place
                            for(final Place p : this.environment.getPlaces()) {
                                if(p.getElements().contains(elementWithPosition)) {
                                    synchronized(p.getElements()) {
                                        p.getElements().remove(elementWithPosition);
                                        break outer;
                                    }
                                }
                            }
                        } else {
                            LOG.warn("cannot remove ElementWithPosition from Environment: The Environment is null " + elementWithPosition);
                        }

                        LOG.warn("cannot remove ElementWithPosition from Place: The Place is null " + elementWithPosition);
                    }
                } else {
                    LOG.warn("unknown element to remove: " + this.element);
                }
                break;
            default:
                LOG.warn("unknown action " + actionType.name());
        }// switch

        super.popupInvoker.disablePopup();
    }

    @Override
    public void finishAction(final EObject element, final ValueHolder valueHolder) {
        switch(valueHolder.getActionType()) {
            case NEW_AREA:
                if(element instanceof Area) {
                    if(this.element instanceof Place) {
                        final Area area = (Area)element;
                        ((Place)this.element).addArea(area.getOrigin().getX(), area.getOrigin().getY(), area.getOrigin().getZ(), area.getSpan().getX(), area.getSpan().getY(), area.getSpan().getZ());
                    } else {
                        throw new IllegalArgumentException("containing element must be of class Place");
                    }
                } else {
                    throw new IllegalArgumentException("element must be of class Area");
                }
                break;
            case NEW_DOOR:
                if(element instanceof Door) {
                    if(this.element instanceof Place) {
                        final Place place = (Place)this.element;
                        Door door = (Door)element;
                        door = place.addDoor(door.getId(), door.getName(), door.getPosition().getX(), door.getPosition().getY(), door.getPosition().getZ(), door.getSpan().getX(), door.getSpan().getY(), door.getSpan().getZ());
                        door.setSource(valueHolder.getSource());
                        door.setTarget(valueHolder.getTarget());
                    } else {
                        throw new IllegalArgumentException("containing element must be of class Place");
                    }
                } else {
                    throw new IllegalArgumentException("element must be of class Door");
                }
                break;
            case NEW_WINDOW:
                if(element instanceof Window) {
                    final Place place = (Place)this.element;
                    Window window = (Window)element;
                    window = place.addWindow(window.getId(), window.getName(), window.getPosition().getX(), window.getPosition().getY(), window.getPosition().getZ(), window.getSpan().getX(), window.getSpan().getY(), window.getSpan().getZ());
                    window.setSource(valueHolder.getSource());
                    window.setTarget(valueHolder.getTarget());
                } else {
                    throw new IllegalArgumentException("element must be of class Window");
                }
                break;
            default:
                throw new IllegalAccessError();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.sercho.masp.models.Context.gui.actionListener.StringDialogActionListener
     * #finishAction(org.sercho.masp.models.Context.gui.dialogs.StringDialog)
     */
    @Override
    public void finishAction(final Option option, final ActionType actionType, final StringDialog stringDialog) {
        if(option == null) {
            LOG.error("option can not be null!");
            throw new IllegalArgumentException("option can not be null!");
        } else if(actionType == null) {
            LOG.error("action type can not be null!");
            throw new IllegalArgumentException("action type can not be null!");
        } else if(stringDialog == null) {
            LOG.error("stringDialog can not be null!");
            throw new IllegalArgumentException("stringDialog can not be null!");

        }

        switch(option) {
            case OK:
                String text;
                boolean ok = false;

                text = stringDialog.getString();

                if(text == null) {
                    LOG.error("text can not be null");
                } else if(text.isEmpty()) {
                    ok = false;

                    switch(actionType) {
                        case EDIT_NAME:
                            JOptionPane.showMessageDialog(this.popupInvoker.getParentFrame(), "Name of element can not be empty!", "Error while editing name of element", JOptionPane.ERROR_MESSAGE);
                            break;
                        case EDIT_ID:
                            JOptionPane.showMessageDialog(this.popupInvoker.getParentFrame(), "Id of element can not be empty!", "Error while editing id of element", JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                } else {
                    ok = true;
                }

                if(ok) {
                    switch(actionType) {
                        case EDIT_NAME:
                            this.element.setName(text);
                            break;
                        case EDIT_ID:
                            this.element.setId(text);
                            break;
                    }
                } else {
                    new StringDialog(stringDialog);
                }

                break;
        }// switch
    }

    public void setArea(final Area area) {
        this.area = area;
    }
}// class ElementActionListener
