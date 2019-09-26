/**
 * 
 */
package org.sercho.masp.models.channel.api;

/**
 * <code>AbstractOutputChannelAPI</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 */
public abstract class AbstractOutputChannelAPI extends AbstractChannelAPI implements
        OutputChannelAPI {

    protected static enum ElementState {
        INACTIVE, ACTIVE, FOCUSED, SELECTED;
    }
}