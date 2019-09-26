/**
 * 
 */
package org.sercho.masp.models.Context.gui.frames;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.sercho.masp.models.Context.Area;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Door;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.EnvironmentElement;
import org.sercho.masp.models.Context.Place;
import org.sercho.masp.models.Context.Room;
import org.sercho.masp.models.Context.RoomType;
import org.sercho.masp.models.Context.Vector;
import org.sercho.masp.models.Context.Window;
import org.sercho.masp.models.Context.gui.VectorWrapper;
import org.sercho.masp.models.Context.gui.actionListener.EnvActionListener;
import org.sercho.masp.models.Context.gui.enums.ActionType;
import org.sercho.masp.models.Context.gui.panels.ValuePanel;
import org.sercho.masp.models.Context.gui.panels.VectorPanel;

/**
 * This {@link JFrame} is used to create an element for the {@link Environment}.
 * 
 * @author Andre Schulz
 * @since 1.2.46
 */
public class ElementCreationFrame extends JFrame implements ActionListener, ValueHolder {

    // TODO user EFeature or so

    /**
     * The logger.
     */
    private static final transient Log LOG = LogFactory.getLog(ElementCreationFrame.class);

    public static void main(final String[] args) {
        // for testing

        new ElementCreationFrame(null, ContextFactory.eINSTANCE.createRoom(), null, null, null);

        // new ElementCreationFrame(null, ContextFactory.eINSTANCE.createArea(),
        // null);
    }

    /**
     * The calling {@link ActionListener}, which will get the element later.
     */
    private final EnvActionListener actionListener;

    /**
     * The element to create.
     */
    private final EObject element;

    /**
     * The parent of the <code>element</code>.
     */
    private final EObject parent;

    /**
     * The <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = -7234672371277058026L;

    /**
     * The {@link JButton} to indicate that all values have been entered.
     */
    private JButton okButton;

    /**
     * The {@link JButton} to cancel the creation of the element.
     */
    private JButton cancelButton;

    /**
     * The text to inform the user what to do.
     */
    private static final String INFORMATION_TEXT = "Please enter the properties of the new ";

    /**
     * The {@link JTextField} for the ID of the element.<br>
     * (Only used if element is of type {@link EnvironmentElement}.)
     */
    private JTextField idTF;

    /**
     * The {@link JTextField} for the name of the element.<br>
     * (Only used if element is of type {@link EnvironmentElement}.)
     */
    private JTextField nameTF;

    /**
     * The {@link JComboBox} to select the {@link RoomType}.<br>
     * (Only used if element is of type {@link Room}.)
     */
    private JComboBox roomTypeCoB;

    /**
     * The {@link JComboBox} to select the source {@link Place} of an
     * {@link Door}.<br>
     * (Only used if element is of type {@link Door} of {@link Window}.)
     */
    private JComboBox sourceCoB;

    /**
     * The {@link JComboBox} to select the target {@link Place} of an
     * {@link Door}.<br>
     * (Only used if element is of type {@link Door} of {@link Window}.)
     */
    private JComboBox targetCoB;

    /**
     * The {@link VectorPanel} for the origin of an {@link Area}.<br>
     * (Only used if element is of type {@link Area}.)
     */
    private VectorPanel originVectorPanel;

    /**
     * The {@link VectorPanel} for the span of an {@link Area}.<br>
     * (Only used if element is of type {@link Area}.)
     */
    private VectorPanel spanVectorPanel;

    /**
     * The name of the class of the element to create.
     */
    private final String name;

    private final ActionType actionType;

    private final Environment environment;

    private Map<String, Place> places;

    private String[] placeTexts;

    /**
     * Constructor for {@link ElementCreationFrame}.
     * 
     * @param elementActionListener
     * @param element
     */
    public ElementCreationFrame(final EnvActionListener elementActionListener,
            final EObject element, final EObject parent, final Environment environment,
            final ActionType actionType) {
        this.actionListener = elementActionListener;
        this.element = element;
        this.parent = parent;
        this.environment = environment;
        this.actionType = actionType;

        this.name = this.element.eClass().getName();
        super.setTitle("Create a new " + this.name);

        initComponents();

        super.pack();
        super.setResizable(false);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if(e.getSource() == this.okButton) {
            if(this.element instanceof EnvironmentElement) {
                if(this.idTF.getText() == null) {
                    // TODO implement error message
                    LOG.error("id cannot be null!");
                    return;
                } else if(this.idTF.getText().isEmpty()) {
                    // TODO implement error message
                    LOG.error("id cannot be empty!");
                    return;
                }

                // TODO check unique of id

                final EnvironmentElement element = (EnvironmentElement)this.element;
                element.setId(this.idTF.getText());
                element.setName(this.nameTF.getText());
            }

            if(this.element instanceof Room) {
                final Room room = (Room)this.element;
                room.setRoomType((RoomType)this.roomTypeCoB.getSelectedItem());
            }

            if(this.element instanceof Area) {
                final Area area = (Area)this.element;

                try {
                    area.setNewOrigin(this.originVectorPanel.getVectorX(), this.originVectorPanel.getVectorY(), this.originVectorPanel.getVectorZ());
                    area.setNewSpan(this.spanVectorPanel.getVectorX(), this.spanVectorPanel.getVectorY(), this.spanVectorPanel.getVectorZ());
                }
                catch(final NumberFormatException exception) {
                    JOptionPane.showMessageDialog(this, "One of the components of the vecors is no number!", "Error while parsing numbers", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if(this.element instanceof Door) {
                final Door door = (Door)this.element;

                LOG.debug("get values for " + door + " place=" + door.getPlace() + " env:=" + door.getEnvironment() + " adpters=" + door.eAdapters());

                try {
                    door.setPosition(this.originVectorPanel.getVectorX(), this.originVectorPanel.getVectorY(), this.originVectorPanel.getVectorZ());

                    final Vector span = ContextFactory.eINSTANCE.createVector();
                    span.setX(this.spanVectorPanel.getVectorX());
                    span.setY(this.spanVectorPanel.getVectorY());
                    span.setZ(this.spanVectorPanel.getVectorZ());
                    door.setSpan(span);
                }
                catch(final NumberFormatException exception) {
                    JOptionPane.showMessageDialog(this, "One of the components of the vecors is no number!", "Error while parsing numbers", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if(this.element instanceof Window) {
                final Window window = (Window)this.element;

                try {
                    window.setPosition(this.originVectorPanel.getVectorX(), this.originVectorPanel.getVectorY(), this.originVectorPanel.getVectorZ());

                    final Vector span = ContextFactory.eINSTANCE.createVector();
                    span.setX(this.spanVectorPanel.getVectorX());
                    span.setY(this.spanVectorPanel.getVectorY());
                    span.setZ(this.spanVectorPanel.getVectorZ());
                    window.setSpan(span);
                }
                catch(final NumberFormatException exception) {
                    JOptionPane.showMessageDialog(this, "One of the components of the vecors is no number!", "Error while parsing numbers", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            this.actionListener.finishAction(this.element, this);
            super.dispose();
        } else if(e.getSource() == this.cancelButton) {
            super.dispose();
        } else {
            LOG.warn("unknown source at action event " + e);
        }
    }

    @Override
    public ActionType getActionType() {
        return this.actionType;
    }

    @Override
    public Place getSource() {
        return this.places.get(this.sourceCoB.getSelectedItem());
    }

    @Override
    public Place getTarget() {
        return this.places.get(this.targetCoB.getSelectedItem());
    }

    private void generatePlaceTexts() {
        this.placeTexts = new String[this.environment.getPlaces().size()];
        this.places = new HashMap<String, Place>();

        int i = 0;

        for(final Place place : this.environment.getPlaces()) {
            this.placeTexts[i] = getPlaceText(place);
            this.places.put(this.placeTexts[i], place);
            i++;
        }

        Arrays.sort(this.placeTexts);
    }

    /**
     * Initializes and returns the element panel.
     * 
     * @return The element panel.
     */
    private Component getElementPanel() {
        if(LOG.isDebugEnabled()) {
            if(this.element instanceof EnvironmentElement) {
                ((EnvironmentElement)this.element).setId("test");
            }

            LOG.debug("creating a new " + this.name + //
            " \neContainingFeature: " + this.element.eContainingFeature() + //
            " \neContainmentFeature: " + this.element.eContainmentFeature() + //
            " \nENVIRONMENT_ELEMENT__ID: " + this.element.eGet(ContextPackage.Literals.ENVIRONMENT_ELEMENT__ID));

            StringBuilder sb = new StringBuilder("eAllContents: ");
            final TreeIterator<EObject> iter = this.element.eAllContents();
            while(iter.hasNext()) {
                sb.append(iter.next());
                sb.append(", ");
            }
            LOG.debug(sb.toString());

            sb = new StringBuilder("eContents: ");
            for(final EObject object : this.element.eContents()) {
                sb.append(object);
                sb.append(", ");
            }
            LOG.debug(sb.toString());

        }

        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // +++++ EnvironmentElement +++++
        if(this.element instanceof EnvironmentElement) {
            this.idTF = new JTextField();
            // TODO add KeyAdapter to check whether id is unique
            panel.add(new ValuePanel("ID", this.idTF));
            this.nameTF = new JTextField();
            panel.add(new ValuePanel("Name", this.nameTF));
        }
        // ----- EnvironmentElement -----

        // +++++ Room +++++
        if(this.element instanceof Room) {
            this.roomTypeCoB = new JComboBox(RoomType.values());
            panel.add(new ValuePanel("RoomType", this.roomTypeCoB));
        }
        // ----- Room -----

        // +++++ Area +++++
        if(this.element instanceof Area) {
            final Area area = (Area)this.element;
            this.originVectorPanel = new VectorPanel(new VectorWrapper(area.getOrigin(), "Origin"));
            panel.add(this.originVectorPanel);
            this.spanVectorPanel = new VectorPanel(new VectorWrapper(area.getSpan(), "Span"));
            panel.add(this.spanVectorPanel);
        }
        // ----- Area -----

        // +++++ Door +++++
        if(this.element instanceof Door) {
            final Door door = (Door)this.element;
            this.originVectorPanel = new VectorPanel(new VectorWrapper(door.getPosition(), "Position"));
            panel.add(this.originVectorPanel);
            this.spanVectorPanel = new VectorPanel(new VectorWrapper(door.getSpan(), "Span"));
            panel.add(this.spanVectorPanel);

            this.generatePlaceTexts();

            this.sourceCoB = new JComboBox(this.placeTexts);
            panel.add(new ValuePanel("Source place", this.sourceCoB));

            this.targetCoB = new JComboBox(this.placeTexts);
            panel.add(new ValuePanel("Target place", this.targetCoB));

            if(this.parent instanceof Place) {
                final String placeText = getPlaceText((Place)this.parent);
                this.sourceCoB.setSelectedItem(placeText);
                this.targetCoB.setSelectedItem(placeText);
            } else {
                LOG.warn("parent element is no Place while creating a new Door");
            }
        }
        // ----- Door -----

        // +++++ Window +++++
        if(this.element instanceof Window) {
            final Window window = (Window)this.element;

            this.originVectorPanel = new VectorPanel(new VectorWrapper(window.getPosition(), "Position"));
            panel.add(this.originVectorPanel);
            this.spanVectorPanel = new VectorPanel(new VectorWrapper(window.getSpan(), "Span"));
            panel.add(this.spanVectorPanel);

            this.generatePlaceTexts();

            this.sourceCoB = new JComboBox(this.placeTexts);
            panel.add(new ValuePanel("Source place", this.sourceCoB));

            this.targetCoB = new JComboBox(this.placeTexts);
            panel.add(new ValuePanel("Target place", this.targetCoB));

            if(this.parent instanceof Place) {
                final String placeText = getPlaceText((Place)this.parent);
                this.sourceCoB.setSelectedItem(placeText);
                this.targetCoB.setSelectedItem(placeText);
            } else {
                LOG.warn("parent element is no Place while creating a new Window");
            }
        }
        // ----- Window -----
        return panel;
    }

    /**
     * Returns the text for a {@link Place}.
     * 
     * @param place
     *            The {@link Place}.
     * @return A {@link String} representing the {@link Place}.
     */
    private String getPlaceText(final Place place) {
        if(place.getName() != null && !place.getName().isEmpty()) {
            return place.getName();
        }

        return place.getId();
    }

    /**
     * Initializes the {@link JComponent}s.
     */
    private void initComponents() {
        super.setLayout(new BoxLayout(super.getContentPane(), BoxLayout.Y_AXIS));

        final JPanel labelPanel = new JPanel();
        labelPanel.add(new JLabel(INFORMATION_TEXT + this.name + "."));

        this.okButton = new JButton("OK");
        this.okButton.addActionListener(this);
        this.cancelButton = new JButton("Cancel");
        this.cancelButton.addActionListener(this);
        final JPanel buttonPanel = new JPanel();
        buttonPanel.add(this.cancelButton);
        buttonPanel.add(this.okButton);

        super.add(labelPanel);
        super.add(getElementPanel());
        super.add(buttonPanel);
    }

}
