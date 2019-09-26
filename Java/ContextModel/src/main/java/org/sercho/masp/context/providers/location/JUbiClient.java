package org.sercho.masp.context.providers.location;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class JUbiClient extends Thread {

    private Socket mySock = null;

    private BufferedReader in;

    private BufferedWriter out;

    private JUbiCallInterface caller;

    private Poss requestedPoss = null;

    private Args requestedArgs = null;

    private String[] requestedObjects = null;

    private boolean connected = false;

    private boolean keeprunning = true;

    private final long get_poss_timeout_ms = 200;

    private final long get_args_timeout_ms = 200;

    public JUbiClient(final String host, final int port) {
        try {
            this.mySock = new Socket(host, port);
            this.connected = true;
        }
        catch(final Exception e) {
            System.out.println("No server seems to be present, sorry.");
            this.connected = false;
        }

        try {
            this.in = new BufferedReader(new InputStreamReader(this.mySock.getInputStream()));
        }
        catch(final Exception e) {
            System.out.println(e);
        }

        try {
            this.out = new BufferedWriter(new OutputStreamWriter(this.mySock.getOutputStream()));
        }
        catch(final Exception e) {
            System.out.println(e);
        }

        this.start();
    }

    public boolean isConnected() {
        return this.connected;
    }

    @Override
    public void run() {
        String s = null;
        this.keeprunning = true;
        while((this.mySock != null) && (this.keeprunning)) {
            try {
                s = this.in.readLine();
                // System.out.println(s);
            }
            catch(final Exception e) {
                System.out.println(e);
            }
            if(s.startsWith("ERROR")) {

                final String ErrorCode = this.getArgument(2, s);
                // System.out.println(ErrorCode);
                if(ErrorCode.equals("POSITION_OF")) {
                    this.requestedPoss = new Poss(0, 0, 0);
                } else if(ErrorCode.equals("ANGLES_OF")) {
                    this.requestedArgs = new Args(0, 0, 0);
                } else {

                }
            } else if(s.startsWith("PUSH_COORDINATES_OF:")) {
                String x = null, y = null, z = null, roll = null, pitch = null, yaw = null, objectName = null;
                objectName = this.getArgument(2, s);

                x = this.getArgument(4, s);
                y = this.getArgument(6, s);
                z = this.getArgument(8, s);

                roll = this.getArgument(10, s);
                pitch = this.getArgument(12, s);
                yaw = this.getArgument(14, s);

                final Poss p = new Poss(Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));

                final Args a = new Args(Double.parseDouble(roll), Double.parseDouble(pitch), Double.parseDouble(yaw));

                if(this.caller != null) {
                    this.caller.on_coordinate_update(objectName, p, a);
                }
            } else if(s.startsWith("ENTRY_INTO_CONTAINMENT:")) {
                String objectName, containmentName;
                objectName = this.getArgument(2, s);
                containmentName = this.getArgument(3, s);
                if(this.caller != null) {
                    this.caller.on_area_entered(containmentName, objectName);
                }
            } else if(s.startsWith("EXIT_FROM_CONTAINMENT:")) {
                String objectName, containmentName;
                objectName = this.getArgument(2, s);
                containmentName = this.getArgument(3, s);
                if(this.caller != null) {
                    this.caller.on_area_left(containmentName, objectName);
                }
            } else if(s.startsWith("ENTRY_OF_PERSON:")) {
                String objectName, containmentName;
                objectName = this.getArgument(2, s);
                containmentName = this.getArgument(3, s);
                if(this.caller != null) {
                    this.caller.on_person_entering(objectName, containmentName);
                }
            } else if(s.startsWith("EXIT_OF_PERSON:")) {
                String objectName, containmentName;
                objectName = this.getArgument(2, s);
                containmentName = this.getArgument(3, s);
                if(this.caller != null) {
                    this.caller.on_person_leaving(objectName, containmentName);
                }
            } else if(s.startsWith("POSITION_OF:")) {
                String x = null, y = null, z = null;

                x = this.getArgument(4, s);
                y = this.getArgument(6, s);
                z = this.getArgument(8, s);

                final Poss p = new Poss(Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));

                this.requestedPoss = p;
            } else if(s.startsWith("ANGLES_OF:")) {
                String roll = null, pitch = null, yaw = null;

                roll = this.getArgument(4, s);
                pitch = this.getArgument(6, s);
                yaw = this.getArgument(8, s);

                final Args a = new Args(Double.parseDouble(roll), Double.parseDouble(pitch), Double.parseDouble(yaw));

                this.requestedArgs = a;
            } else if(s.startsWith("GET_OBJECTS:")) {
                String[] Objects = null;
                final String ObjectString = this.getArgument(2, s);
                Objects = ObjectString.split(",");

                this.requestedObjects = Objects;
            } else if(s.startsWith("GET_CONTENTS_OF:")) {
                String[] Objects = null;
                final String ObjectString = this.getArgument(3, s);
                Objects = ObjectString.split(",");

                this.requestedObjects = Objects;
            } else if(s.startsWith("GET_CONTAINMENTS_OF:")) {
                String[] Objects = null;
                final String ObjectString = this.getArgument(3, s);
                Objects = ObjectString.split(",");

                this.requestedObjects = Objects;
            }
        }
        return;
    }

    /**
     * determine position of a person
     * 
     * @param objectName
     * @return
     */
    public Poss getPositionOf(final String objectName) {
        try {
            this.out.write("POSITION_OF:" + objectName + "\n");
            this.out.flush();
        }
        catch(final Exception e) {
            System.out.println(e);
        }

        final Timeout timeout = new Timeout(this.get_poss_timeout_ms);

        while((this.requestedPoss == null) && (!timeout.TimeOut())) {

            try {

                sleep(100);

            }
            catch(final InterruptedException e) {

                e.printStackTrace();
            }

        }

        final Poss p = this.requestedPoss;
        this.requestedPoss = null;
        return p;
    }

    /**
     * determine angles of a person
     * 
     * @param objectName
     * @return
     */
    public Args getAnglesOf(final String objectName) {
        try {
            this.out.write("ANGLES_OF:" + objectName + "\n");
            this.out.flush();
        }
        catch(final Exception e) {
            System.out.println(e);
        }

        final Timeout timeout = new Timeout(this.get_args_timeout_ms);

        while((this.requestedArgs == null) && (!timeout.TimeOut())) {

            try {

                sleep(100);

            }
            catch(final InterruptedException e) {

                e.printStackTrace();
            }

        }
        final Args a = this.requestedArgs;
        this.requestedArgs = null;
        return a;
    }

    /**
     * which objects (persons/containers) are available?
     * 
     * @return
     */
    public String[] getObjects() {
        try {
            this.out.write("GET_OBJECTS:\n");
            this.out.flush();
        }
        catch(final Exception e) {
            System.out.println(e);
        }
        while(this.requestedObjects == null) {
            ;
        }
        final String[] o = this.requestedObjects;
        this.requestedObjects = null;
        return o;
    }

    /**
     * which objects are in a specific container?
     * 
     * @param container
     * @return
     */
    public String[] getObjectsIn(final String container) {
        try {
            this.out.write("GET_CONTENTS_OF:" + container + "\n");
            this.out.flush();
        }
        catch(final Exception e) {
            System.out.println(e);
        }
        while(this.requestedObjects == null) {
            ;
        }
        final String[] o = this.requestedObjects;
        this.requestedObjects = null;
        return o;
    }

    /**
     * in which container is a specific person?
     * 
     * @param object
     * @return
     */
    public String[] getContainersOf(final String object) {
        try {
            this.out.write("GET_CONTAINMENTS_OF:" + object + "\n");
            this.out.flush();
        }
        catch(final Exception e) {
            System.out.println(e);
        }
        while(this.requestedObjects == null) {
            ;
        }
        final String[] o = this.requestedObjects;
        this.requestedObjects = null;
        return o;
    }

    /**
     * receive events triggered by motion of an object
     * 
     * @param objectName
     */
    public void pushCoordinatesOf(final String objectName) {
        try {
            this.out.write("PUSH_COORDINATES_OF:" + objectName + "\n");
            this.out.flush();
        }
        catch(final Exception e) {
            System.out.println(e);
        }
    }

    /**
     * receive events triggered by a specific container that has been entered or
     * left
     * 
     * @param objectName
     */
    public void pushContainmentEventsOf(final String objectName) {
        try {
            this.out.write("PUSH_CONTAINMENT_EVENTS_OF:" + objectName + "\n");
            this.out.flush();
        }
        catch(final Exception e) {
            System.out.println(e);
        }
    }

    /**
     * receive events triggered by a specific person that has entered or left a
     * container
     * 
     * @param objectName
     */
    public void pushPersonEventsOf(final String objectName) {
        try {
            this.out.write("PUSH_OBJECT_EVENTS_OF:" + objectName + "\n");
            this.out.flush();
        }
        catch(final Exception e) {
            System.out.println(e);
        }
    }

    /**
     * don't receive this event anymore
     * 
     * @param objectName
     */
    public void unPushCoordinatesOf(final String objectName) {
        try {
            this.out.write("HALT_COORDINATES_OF:" + objectName + "\n");
            this.out.flush();
        }
        catch(final Exception e) {
            System.out.println(e);
        }
    }

    /**
     * don't receive this event anymore
     * 
     * @param objectName
     */
    public void unPushContainmentEventsOf(final String objectName) {
        try {
            this.out.write("HALT_CONTAINMENT_EVENTS_OF:" + objectName + "\n");
            this.out.flush();
        }
        catch(final Exception e) {
            System.out.println(e);
        }
    }

    /**
     * don't receive this event anymore
     * 
     * @param objectName
     */
    public void unPushPersonEventsOf(final String objectName) {
        try {
            this.out.write("HALT_OBJECT_EVENTS_OF:" + objectName + "\n");
            this.out.flush();
        }
        catch(final Exception e) {
            System.out.println(e);
        }
    }

    /**
     * register an object which implements a JUbiCallInterface for receiving
     * events
     * 
     * @param caller
     */
    public void registerCallback(final JUbiCallInterface caller) {
        this.caller = caller;
    }

    private String getArgument(final int n, final String s) {
        // search for n'th occurance of delimiter ":"
        int k = 0, l = 0;
        for(int i = 1; i < n; i++) {
            k = s.indexOf(":", k + 1);
        }
        l = s.indexOf(":", k + 1);

        if(l != -1) {
            return s.substring(k + 1, l);
        } else {
            return s.substring(k + 1).trim();
        }
    }

    public void stopIt() {
        this.keeprunning = false;
        try {
            this.mySock.close();
            this.in.close();
        }
        catch(final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
