/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.impl;

import org.eclipse.emf.ecore.EClass;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.HapticalInteractionResources;
import org.sercho.masp.models.Context.Modality;
import org.sercho.masp.models.Context.User;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Haptical Interaction Resources</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public abstract class HapticalInteractionResourcesImpl extends
        InputInteractionResourceImpl implements HapticalInteractionResources {

    /**
     * <code>ARM_LENGTH</code>
     */
    public static final double ARM_LENGTH = 50;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected HapticalInteractionResourcesImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.HAPTICAL_INTERACTION_RESOURCES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final double getContextRatingHook(final User user) {
        final double distance = this.getPosition().distance(user.getPosition());
        if(distance < ARM_LENGTH) {
            return MAXIMUM_CONTEXT_RATING;
        } else {
            final int factor = ((Double)(distance / ARM_LENGTH)).intValue();
            return MAXIMUM_CONTEXT_RATING / factor;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final Modality getModalityHook() {
        return Modality.HAPTIC;
    }

} // HapticalInteractionResourcesImpl
