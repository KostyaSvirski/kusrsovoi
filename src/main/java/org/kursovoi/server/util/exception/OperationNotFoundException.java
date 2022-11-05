package org.kursovoi.server.util.exception;


public class OperationNotFoundException extends RuntimeException {

    public OperationNotFoundException() {
    }

    public OperationNotFoundException(String message) {
        super(message);
    }

    public OperationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationNotFoundException(Throwable cause) {
        super(cause);
    }
}
