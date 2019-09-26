/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.impl;

import org.eclipse.emf.ecore.EClass;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.Touchpad;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Touchpad</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public class TouchpadImpl extends HapticalInteractionResourcesImpl implements Touchpad {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TouchpadImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.TOUCHPAD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<? extends InteractionResource> getTypeHook() {
        return Touchpad.class;
    }

} // TouchpadImpl
