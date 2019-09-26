/**
 * File     ContextObserverObserver.java
 * Package  org.sercho.masp.models
 * Project  ContextModel
 * Date     07.12.2007
 * Web      http://www.dai-labor.de
 * 
 * @author  Dirk Roscher
 */
package org.sercho.masp.models;

import java.util.List;

import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.User;

/**
 * <code>ContextObserverObserver</code> TODO comment
 * 
 * @author Dirk Roscher
 */
public interface ContextObserverObserver {

    /**
     * <code>updateUsers</code> is called when users which can use
     * <code>resource</code> changed. Resource can be used by <code>users</code>
     * .
     * 
     * @param resource
     *            updated resource.
     * @param users
     */
    void updateUsers(InteractionResource resource, List<User> users);

    /**
     * <code>user</code> can only use {@link InteractionResource}s within
     * <code>resources</code>.
     * 
     * @param user
     *            updated user
     * @param resources
     *            {@link InteractionResource}s user can use
     */
    void updateResources(User user, List<InteractionResource> resources);

    /**
     * <code>updateContextRating</code> is called when user the rating changes.
     * 
     * @param user
     *            updated user
     * @param resources
     *            resources to update
     */
    void updateContextRating(User user, List<InteractionResource> resources);

}
