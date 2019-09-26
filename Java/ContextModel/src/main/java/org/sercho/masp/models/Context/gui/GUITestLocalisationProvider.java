package org.sercho.masp.models.Context.gui;

import java.util.Map;

import org.sercho.masp.context.providers.location.AbstractLocalisationProvider;
import org.sercho.masp.context.providers.location.Vector;

public class GUITestLocalisationProvider extends AbstractLocalisationProvider {

    /**
     * 
     */
    private static final long serialVersionUID = 9035985529730318304L;

    @Override
    protected void close() {
        // TODO Auto-generated method stub

    }

    @Override
    public Vector getPosition(final String tagID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean register(final String tagID) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void start(final Map<String, String> configuration) {
        System.out.println("Hello, I am started!");
        final String tagID = configuration.get("TagToMove");
        final String tagID2 = configuration.get("TagToMove2");
        final String tagID3 = configuration.get("TagToMove3");
        new Thread("GUI Test Tag Thread") {

            int i = 0;

            @Override
            public void run() {
                newPosition(tagID2, 300, 300, 0, 3);
                while(this.i < 600) {
                    newPosition(tagID, this.i, this.i, 0, 3);
                    newPosition(tagID2, -this.i + 300, this.i + 300, 0, 3);
                    newPosition(tagID3, this.i, this.i, 0, 3);
                    try {
                        sleep(1000);
                    }
                    catch(final InterruptedException e) {
                        return;
                    }
                    this.i += 5;
                }
            }
        }.start();
    }

    @Override
    public void unregister(final String tagID) {
        // TODO Auto-generated method stub

    }

}
