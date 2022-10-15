package ru.mikheev.exception;

public class ATMException extends RuntimeException {

    public ATMException() {
        super();
    }

    public ATMException(String message) {
        super(message);
    }

    public ATMException(String message, Throwable cause) {
        super(message, cause);
    }
}
