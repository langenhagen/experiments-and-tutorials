/**
 * 
 */
package org.sercho.masp.models.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <code>PlaceInfo</code> holds unmodifiable information about a place.
 * 
 * @author Grzegorz Lehmann
 * @since 1.2.18
 */
public final class PlaceInfo {

    /**
     * <code>id</code> holds the identifier of the place.
     */
    public final String id;

    /**
     * <code>temperature</code> holds the temperature of the place or
     * <code>null</code> if the temperature is unknown.
     */
    public final Double temperature;

    /**
     * <code>devices</code> is an unmodifiable list of devices in the place.
     */
    public final List<Device> devices;

    /**
     * <code>PlaceInfo</code> constructor.
     * 
     * @param placeIdentifier
     *            identifier of the place, <code>null</code> disallowed
     * @param temperatureInPlace
     *            temperature of the place or <code>null</code> id it is unknown
     * @param devicesInPlace
     *            devices available in the place, <code>null</code> allowed
     */
    public PlaceInfo(final String placeIdentifier, final Double temperatureInPlace,
            final List<Device> devicesInPlace) {
        if(placeIdentifier == null) {
            throw new IllegalArgumentException("placeIdentifier argument must not be null in method PlaceInfo");
        }
        this.id = placeIdentifier;
        this.temperature = temperatureInPlace;
        if(devicesInPlace == null || devicesInPlace.isEmpty()) {
            this.devices = Collections.emptyList();
        } else {
            this.devices = Collections.unmodifiableList(new ArrayList<Device>(devicesInPlace));
        }
    }
}