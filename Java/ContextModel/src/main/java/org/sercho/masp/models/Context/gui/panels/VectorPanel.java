/**
 * 
 */
package org.sercho.masp.models.Context.gui.panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.sercho.masp.models.Context.Vector;
import org.sercho.masp.models.Context.gui.VectorWrapper;
import org.sercho.masp.models.Context.gui.keyAdapter.JTextFieldDoubleKeyAdapter;

/**
 * A {@link JPanel} for a {@link Vector}.
 * 
 * @author Andre Schulz
 * @since 1.2.46
 */
public class VectorPanel extends JPanel {

    /**
     * The {@link Dimension} for a {@link VectorPanel}.
     */
    private static final Dimension DIMENSION = new Dimension(310, 100);

    /**
     * The default value for {@link Vector} components.
     */
    private static final String DEFAULT_VECTOR_COMPONENT_TEXT = "0.0";

    /**
     * The <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = -7300176189655692816L;

    /**
     * The {@link JTextField} for the x coordinate of the {@link Vector}.
     */
    private JTextField xTF;

    /**
     * The {@link JTextField} for the y coordinate of the {@link Vector}.
     */
    private JTextField yTF;

    /**
     * The {@link JTextField} for the z coordinate of the {@link Vector}.
     */
    private JTextField zTF;

    private final VectorWrapper vectorWrapper;

    /**
     * Creates a new {@link VectorPanel}.
     * 
     * @param vectorWrapper
     *            The {@link VectorWrapper} the {@link VectorPanel} is for.
     */
    public VectorPanel(final VectorWrapper vectorWrapper) {
        super();

        this.vectorWrapper = vectorWrapper;
        super.setPreferredSize(DIMENSION);
        super.setMaximumSize(DIMENSION);

        final TitledBorder titledBorder = BorderFactory.createTitledBorder(vectorWrapper.getName());
        super.setBorder(titledBorder);

        this.initComponents();

        this.xTF.addKeyListener(new JTextFieldDoubleKeyAdapter(this.xTF));
        this.yTF.addKeyListener(new JTextFieldDoubleKeyAdapter(this.yTF));
        this.zTF.addKeyListener(new JTextFieldDoubleKeyAdapter(this.zTF));
    }

    /**
     * Returns the parsed x coordinate of the {@link Vector} from the related
     * {@link JTextField}.
     * 
     * @return The parsed x coordinate of the {@link Vector}.
     */
    public double getVectorX() {
        final String text = this.xTF.getText();

        if(text != null) {
            return Double.parseDouble(text);
        } else {
            return 0;
        }
    }

    /**
     * Returns the parsed y coordinate of the {@link Vector} from the related
     * {@link JTextField}.
     * 
     * @return The parsed y coordinate of the {@link Vector}.
     */
    public double getVectorY() {
        final String text = this.yTF.getText();

        if(text != null) {
            return Double.parseDouble(text);
        } else {
            return 0;
        }
    }

    /**
     * Returns the parsed z coordinate of the {@link Vector} from the related
     * {@link JTextField}.
     * 
     * @return The parsed z coordinate of the {@link Vector}.
     */
    public double getVectorZ() {
        final String text = this.zTF.getText();

        if(text != null) {
            return Double.parseDouble(text);
        } else {
            return 0;
        }
    }

    /**
     * initializes the {@link JComponent}s.
     */
    private void initComponents() {
        super.setLayout(new GridLayout(3, 1));

        if(this.vectorWrapper.getVector() != null) {
            this.xTF = new JTextField(Double.toString(this.vectorWrapper.getVector().getX()));
            this.yTF = new JTextField(Double.toString(this.vectorWrapper.getVector().getY()));
            this.zTF = new JTextField(Double.toString(this.vectorWrapper.getVector().getZ()));
        } else {
            this.xTF = new JTextField(DEFAULT_VECTOR_COMPONENT_TEXT);
            this.yTF = new JTextField(DEFAULT_VECTOR_COMPONENT_TEXT);
            this.zTF = new JTextField(DEFAULT_VECTOR_COMPONENT_TEXT);
        }

        super.add(new ValuePanel("x", this.xTF));
        super.add(new ValuePanel("y", this.yTF));
        super.add(new ValuePanel("z", this.zTF));
    }
}
