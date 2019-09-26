package org.sercho.masp.context.providers.location.ubisense;

import java.util.LinkedList;

public class LinkedListThSafe {

    public LinkedListThSafe() {

        this.list = new LinkedList<String>();

    }

    public synchronized int size() {

        return this.list.size();

    }

    public synchronized String pop() {

        if(this.list.size() > 0) {
            return this.list.pop();
        } else {
            return null;
        }

    }

    public synchronized void push(final String elem) {

        if(this.list.size() < max_buffer_size) {
            this.list.push(elem);
        } else {
            // list.clear();
        }

    }

    public LinkedList<String> list;

    public static long max_buffer_size = 10;

}
