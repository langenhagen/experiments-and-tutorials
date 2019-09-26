package org.sercho.masp.models.Context.WikiTutorial;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Ignore;

import de.dailab.masp.models.Properties.AbstractSensorWrapper;

@Ignore
public class TutorialSensor extends AbstractSensorWrapper {

    /**
     * 
     */
    private static final long serialVersionUID = 1010159294251392507L;

    private final TimerTask timer = new TimerTask() {

        @Override
        public void run() {
            if(Wall.SINGLETON.getBottles() > 0) {
                // noch Flaschen an der Wand
                TutorialSensor.this.setAvailable(Boolean.TRUE);
                TutorialSensor.this.newValue(Integer.toString(Wall.SINGLETON.removeBottle()));
            } else {
                // keine Flaschen mehr an der Wand
                TutorialSensor.this.setAvailable(Boolean.FALSE);
            }
        }
    };

    @Override
    protected void startHook(final Map<String, String> configurationMap) {
        try {
            final int interval = Integer.parseInt(configurationMap.get("interval"));
            new Timer().schedule(this.timer, 0, interval);
        }
        catch(final Exception e) {
            throw new IllegalArgumentException("Could not parse interval argument.", e);
        }
    }

    @Override
    protected void stopHook() {
        this.timer.cancel();
    }
}
