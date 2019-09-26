package org.sercho.masp.models.Context.gui.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;

@SuppressWarnings("serial")
/**
 * @author Antonio Fernandez Zaragoza
 */
public class InternalErrorException extends Exception {

    private final Exception encapsulatedException;

    public InternalErrorException(final Exception exception) {
        this.encapsulatedException = exception;
    }

    @Override
    public String getMessage() {
        return this.encapsulatedException.getMessage();
    }

    public Exception getEncapsulatedException() {
        return this.encapsulatedException;
    }

    @Override
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override
    public void printStackTrace(final PrintStream printStream) {
        super.printStackTrace(printStream);
        printStream.println("***Information about encapsulated exception***");
        this.encapsulatedException.printStackTrace(printStream);
    }

    @Override
    public void printStackTrace(final PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        printWriter.println("***Information about encapsulated exception***");
        this.encapsulatedException.printStackTrace(printWriter);
    }
}
