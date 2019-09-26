/**
 * 
 */
package org.sercho.masp.models.Context.gui.panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A {@link JPanel} for simple values.
 * 
 * @author Andre Schulz
 * @since 1.2.46
 */
public class ValuePanel extends JPanel {

    /**
     * The {@link Dimension} for a {@link ValuePanel}.
     */
    private static final Dimension DIMENSION = new Dimension(300, 30);

    /**
     * The <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = 617185563465891516L;

    public ValuePanel(final String text, final JComponent component) {
        super();
        super.setPreferredSize(DIMENSION);
        super.setMaximumSize(DIMENSION);
        super.setLayout(new GridLayout(1, 2));

        super.add(new JLabel(text));
        super.add(component);
    }
}
