package org.sercho.masp.models.Context.WikiTutorial;

import java.util.Map;

import org.junit.Ignore;

import de.dailab.masp.models.Properties.AbstractActorWrapper;
import de.dailab.masp.models.Properties.ActorServiceCallException;

@Ignore
public class TutorialActor extends AbstractActorWrapper {

    /**
     * 
     */
    private static final long serialVersionUID = 3847544311946649859L;

    @Override
    public void set(final String newValue) throws ActorServiceCallException {
        try {
            Wall.SINGLETON.setBottles(Integer.parseInt(newValue));
        }
        catch(final Exception e) {
            throw new ActorServiceCallException("Failed parsing value '" + newValue + "' to integer.", e);
        }
    }

    @Override
    protected void startHook(final Map<String, String> configurationMap) {
        setAvailable(Boolean.TRUE);
    }

    @Override
    protected void stopHook() {
        setAvailable(Boolean.FALSE);
    }
}
