package fr.creativegames.cgstandardlib.exceptions;

import fr.creativegames.cgstandardlib.exceptions.errors.AbstractError;

import java.io.PrintStream;

/**
 * Main Error handler
 * @author Axicer
 */
public class ErrorHandler {

    /**
     * Throw an error on the given {@link PrintStream}
     * @param error {@link AbstractError} to throw
     * @param stream {@link PrintStream} to send the error
     */
    public static void throwError(AbstractError error, PrintStream stream){
        stream.print("["+error.getLevel().name()+"] "+error.getName() + " > "+error.getDescription());
        new Throwable().printStackTrace();
    }

    /**
     * Throw an error to the standard System.in {@link PrintStream}
     * @param error {@link AbstractError} to throw
     */
    public static void throwError(AbstractError error){
        throwError(error, System.out);
        new Throwable().printStackTrace();
    }
}
