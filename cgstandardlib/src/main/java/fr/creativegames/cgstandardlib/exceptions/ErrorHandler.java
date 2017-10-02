package fr.creativegames.cgstandardlib.exceptions;

import fr.creativegames.cgstandardlib.exceptions.errors.AbstractError;

import java.io.PrintStream;

public class ErrorHandler {

    public static void throwError(AbstractError error, PrintStream stream){
        stream.print("["+error.getLevel().name()+"] "+error.getName() + " > "+error.getDescription());
        new Throwable().printStackTrace();
    }
    public static void throwError(AbstractError error){
        throwError(error, System.out);
    }
}
