package org.kursovoi.server.dto;

public enum OperationDescription {

    NEW_ACCOUNT("New account created"),
    NEW_LOAN_ORDER("New loan order created"),
    NEW_DEPOSIT_ORDER("New deposit order created"),
    MAKE_TRANSACTION("Transaction released");

    private final String message;

    OperationDescription(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
