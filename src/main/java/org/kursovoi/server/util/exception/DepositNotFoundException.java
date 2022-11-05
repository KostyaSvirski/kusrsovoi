package org.kursovoi.server.util.exception;

public class DepositNotFoundException extends RuntimeException {

    public DepositNotFoundException() {
    }

    public DepositNotFoundException(String message) {
        super(message);
    }

    public DepositNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepositNotFoundException(Throwable cause) {
        super(cause);
    }
}
