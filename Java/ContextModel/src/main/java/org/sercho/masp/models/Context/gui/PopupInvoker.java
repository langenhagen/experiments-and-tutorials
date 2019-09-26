package org.sercho.masp.models.Context.gui;

import java.awt.Frame;
import java.awt.Point;

/**
 * Interface for classes which open pup-ups.
 * 
 * @author Andre Schulz
 * @since 1.2.17
 */
public interface PopupInvoker {

    /**
     * Forces the {@link PopupInvoker} to disable the pop-up.
     */
    public void disablePopup();

    /**
     * Returns the parent {@link Frame} for the pop-up.
     * 
     * @return The parent {@link Frame}.
     */
    public Frame getParentFrame();

    /**
     * returns the location for the pop-up.
     * 
     * @return The location for the pop-up.
     */
    public Point getPopupLocation();
}
