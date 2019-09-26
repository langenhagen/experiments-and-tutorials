package org.sercho.masp.models.Context.WikiTutorial;

import org.junit.Ignore;

@Ignore
public class Wall {

    public final static Wall SINGLETON = new Wall();

    private int bottles = 0;

    private Wall() {
        // nothing to do
    }

    public synchronized int getBottles() {
        return this.bottles;
    }

    public synchronized int removeBottle() {
        this.bottles--;
        return this.bottles;
    }

    public synchronized void setBottles(final int newBottles) {
        this.bottles = newBottles;
    }
}
