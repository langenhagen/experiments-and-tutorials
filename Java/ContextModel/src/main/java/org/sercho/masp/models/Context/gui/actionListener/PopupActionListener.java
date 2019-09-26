package org.sercho.masp.models.Context.gui.actionListener;

import java.awt.event.ActionListener;

import org.sercho.masp.models.Context.gui.PopupInvoker;

/**
 * Superclass for {@link ActionListener}s handling events on pop-up-menus.
 * 
 * @author Andre Schulz
 * @since 1.2.17
 */
public class PopupActionListener {

    /**
     * The invoking class.
     */
    protected PopupInvoker popupInvoker;

    /**
     * Creates a new {@link PopupActionListener}.
     * 
     * @param popupInvoker
     *            The {@link PopupInvoker}.
     */
    public PopupActionListener(final PopupInvoker popupInvoker) {
        this.popupInvoker = popupInvoker;
    }
}
