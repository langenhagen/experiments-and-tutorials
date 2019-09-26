package org.sercho.masp.models.Context.gui;

import org.eclipse.emf.ecore.EReference;
import org.sercho.masp.models.Context.EnvironmentElement;

import de.dailab.masp.models.Properties.PropertiesFactory;
import de.dailab.masp.models.Properties.Property;

/**
 * Ensemble of related elements to a {@link Property}.
 * 
 * @author Andre Schulz
 * @since 1.2.17
 */
public class ElementProperty {

    /**
     * The {@link EnvironmentElement} the {@link Property} belongs to.
     */
    private final EnvironmentElement element;

    /**
     * The {@link Property} itself.
     */
    private Property<?> property;

    /**
     * The {@link EReference} of the {@link Property}.
     */
    private final EReference propertyReference;

    public ElementProperty(final EnvironmentElement element, final Property<?> property,
            final EReference propertyReference) {
        this.element = element;
        this.property = property;
        this.propertyReference = propertyReference;

        if(this.property == null) {
            this.property = (Property<?>)PropertiesFactory.eINSTANCE.create(this.propertyReference.getEReferenceType());
            this.element.eSet(this.propertyReference, this.property);

        }
    }

    /**
     * Returns the {@link EnvironmentElement} of the {@link Property}.
     * 
     * @return The {@link EnvironmentElement} of the {@link Property}.
     */
    public EnvironmentElement getElement() {
        return this.element;
    }

    /**
     * Return the {@link Property}.
     * 
     * @return The {@link Property}.
     */
    public Property<?> getProperty() {
        return this.property;
    }

    /**
     * Returns the {@link EReference} of the {@link Property}.
     * 
     * @return The {@link EReference} of the {@link Property}.
     */
    public EReference getPropertyReference() {
        return this.propertyReference;
    }

    /**
     * Sets the {@link Property}, which should be related to the
     * {@link EReference}.
     * 
     * @param property
     *            The {@link Property}.
     */
    public void setProperty(final Property<?> property) {
        this.property = property;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.element.getId());
        sb.append(":");
        sb.append(this.property);
        sb.append("(");
        sb.append(this.propertyReference);
        sb.append(")");
        return sb.toString();
    }
}
