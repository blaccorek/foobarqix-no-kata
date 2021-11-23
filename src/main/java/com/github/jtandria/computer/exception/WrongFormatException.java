package com.github.jtandria.computer.exception;

/**
 * Exception send when computer format is wrong.
 *
 * @author jtandria
 */
public class WrongFormatException extends Exception {

    public WrongFormatException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
