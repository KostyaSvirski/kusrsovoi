package org.kursovoi.server.util.exception;

public class TransactionSumTooBigException extends RuntimeException {

    public TransactionSumTooBigException() {
    }

    public TransactionSumTooBigException(String message) {
        super(message);
    }

    public TransactionSumTooBigException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionSumTooBigException(Throwable cause) {
        super(cause);
    }
}
