package org.sercho.masp.context.providers.location;

public interface JUbiCallInterface {

    public void on_coordinate_update(String ObjectName, Poss positions, Args angles);

    public void on_person_entering(String person, String area);

    public void on_person_leaving(String person, String area);

    public void on_area_entered(String area, String person);

    public void on_area_left(String area, String person);
}
