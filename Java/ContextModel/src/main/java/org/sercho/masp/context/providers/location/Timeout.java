package org.sercho.masp.context.providers.location;

public class Timeout {

    public Timeout(final long _timeout_ms) {

        set_timeout(_timeout_ms);
        this.start_time = System.currentTimeMillis();

    }

    public void restart() {

        this.start_time = System.currentTimeMillis();

    }

    public void set_timeout(final long _timeout_ms) {

        this.timeout_ms = _timeout_ms;

    }

    public boolean TimeOut() {

        final long current_time_ms = System.currentTimeMillis();
        return (current_time_ms > (this.start_time + this.timeout_ms));

    }

    long start_time;

    long timeout_ms;

}
