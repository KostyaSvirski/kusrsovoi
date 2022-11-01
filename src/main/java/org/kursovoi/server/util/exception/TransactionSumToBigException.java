package org.kursovoi.server.util.exception;

public class TransactionSumToBigException extends RuntimeException {

    public TransactionSumToBigException() {
    }

    public TransactionSumToBigException(String message) {
        super(message);
    }

    public TransactionSumToBigException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionSumToBigException(Throwable cause) {
        super(cause);
    }
}
