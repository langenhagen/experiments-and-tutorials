package org.sercho.masp.context.providers.location;

public class Poss {

    public double x;

    public double y;

    public double z;

    public Poss() {

    }

    public Poss(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "";
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }
}
