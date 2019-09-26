/**
 * 
 */
package org.sercho.masp.models.Context.gui.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.eclipse.emf.common.util.EList;

import de.dailab.masp.models.MetaMetaModel.DefinitionModificationElement;

/**
 * An {@link ActionListener} to execute a {@link DefinitionModificationElement}.
 * 
 * @author Andre Schulz
 * @since 1.2.47
 */
public class DefinitionModificationElementActionListener implements ActionListener {

    /**
     * The {@link DefinitionModificationElement} to execute.
     */
    DefinitionModificationElement definitionModificationElement;

    /**
     * The element where the action should take place.
     */
    Object element;

    /**
     * The parameters for the execution.
     */
    EList<Object> parameters;

    /**
     * Constructor for {@link DefinitionModificationElementActionListener}s.
     * 
     * @param definitionModificationElement
     *            The {@link DefinitionModificationElement} to execute.
     * @param element
     *            The element where the action should take place.
     * @param parameters
     *            The parameters for the execution.
     */
    public DefinitionModificationElementActionListener(
            final DefinitionModificationElement definitionModificationElement,
            final Object element, final EList<Object> parameters) {
        this.definitionModificationElement = definitionModificationElement;
        this.element = element;
        this.parameters = parameters;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        this.definitionModificationElement.execute(this.element, this.parameters);
    }
}
