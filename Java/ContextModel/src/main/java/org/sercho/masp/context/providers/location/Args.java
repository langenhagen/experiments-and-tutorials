package org.sercho.masp.context.providers.location;

public class Args {

    public Args() {
    }

    public Args(final double roll, final double pitch, final double yaw) {
        this.roll = roll;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    double roll;

    double pitch;

    double yaw;

    public double getPitch() {
        return this.pitch;
    }

    public void setPitch(final double pitch) {
        this.pitch = pitch;
    }

    public double getRoll() {
        return this.roll;
    }

    public void setRoll(final double roll) {
        this.roll = roll;
    }

    public double getYaw() {
        return this.yaw;
    }

    public void setYaw(final double yaw) {
        this.yaw = yaw;
    }
}
