/**
 * 
 */
package org.sercho.masp.models.channel.api;

import de.dailab.masp.models.MetaMetaModel.ExternalProcess;

/**
 * <code>ChannelAPI</code> defines the communication between the abstract
 * channels in the context model and their interaction resource specific
 * counterparts.
 * 
 * @author Grzegorz Lehmann
 */
public interface ChannelAPI extends ExternalProcess {

    /**
     * <code>isAvailable</code> returns <code>true</code> if this channel is
     * available and ready to use.
     * 
     * @return boolean - <code>true</code> if this channel is available and
     *         ready to use, otherwise <code>false</code>
     */
    boolean isAvailable();
}