package org.sercho.masp.context.providers.location;

public class JUbiTestClass implements JUbiCallInterface {

    void doSth() {
        final JUbiClient C = new JUbiClient("localhost", 10587);

        // Callback for push
        C.registerCallback(this);

        // POSitionS Type
        Poss position;
        // ARGumentS Type
        Args angles;

        // getObjectNames
        String[] Objects = C.getObjects();
        for(int j = 0; j < Objects.length; j++) {
            System.out.println(" found Object: " + Objects[j]);
        }

        // Make a query on position
        position = C.getPositionOf("MarcoB");
        // Make a query on agles
        angles = C.getAnglesOf("MarcoB");

        // output
        System.out.println("Querying position and angles of MarcoB");
        System.out.println(" X=" + position.x);
        System.out.println(" Y=" + position.y);
        System.out.println(" Z=" + position.z);
        System.out.println(" Roll=" + angles.roll);
        System.out.println(" Pitch=" + angles.pitch);
        System.out.println(" Yaw=" + angles.yaw + "\n");

        System.out.println("Finding spatial relations of the objects:");

        // Finding out, which area(s) a person is in
        Objects = C.getContainersOf("MarcoB");
        for(int j = 0; j < Objects.length; j++) {
            System.out.println(" MarcoB is in the container: " + Objects[j] + ".");
        }

        // Finding out, which person(s) are in a specific area
        Objects = C.getObjectsIn("BigTV1");
        for(int j = 0; j < Objects.length; j++) {
            System.out.println(" The area BigTV1 contains the person: " + Objects[j] + ".");
        }

        try {
            Thread.sleep(2000);
        }
        catch(final InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Switch on the "push coordinates" feature
        // C.pushCoordinatesOf("MarcoB");

        // Switch on the "push person events" feature
        C.pushPersonEventsOf("MarcoB");

        // Switch on the "push person events" feature
        C.pushContainmentEventsOf("BigTV1");

        while(true) {
            try {
                Thread.sleep(500);
            }
            catch(final InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void on_coordinate_update(final String ObjectName, final Poss positions, final Args angles) {
        System.out.println(ObjectName + " has updated it's position to the new coordinates:");
        System.out.println(" X=" + positions.x);
        System.out.println(" Y=" + positions.y);
        System.out.println(" Z=" + positions.z);
        System.out.println(" Roll=" + angles.roll);
        System.out.println(" Pitch=" + angles.pitch);
        System.out.println(" Yaw=" + angles.yaw);
    }

    @Override
    public void on_person_entering(final String person, final String area) {
        System.out.println("The observed person " + person + " entered the area called " + area + ".");
    }

    @Override
    public void on_person_leaving(final String person, final String area) {
        System.out.println("The observed person " + person + " left the area called " + area + ".");
    }

    @Override
    public void on_area_entered(final String area, final String person) {
        System.out.println("The observed area " + area + " was entered by the person called " + person + ".");
    }

    @Override
    public void on_area_left(final String area, final String person) {
        System.out.println("The observed area " + area + " was left by the person called " + person + ".");
    }
}
